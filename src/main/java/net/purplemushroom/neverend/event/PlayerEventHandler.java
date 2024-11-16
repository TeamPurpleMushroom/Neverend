package net.purplemushroom.neverend.event;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.purplemushroom.neverend.Neverend;
import net.purplemushroom.neverend.capability.player.NEPlayer;
import net.purplemushroom.neverend.capability.player.data.PlayerTracker;
import net.purplemushroom.neverend.capability.player.data.RiftFishingData;
import net.purplemushroom.neverend.content.blocks.DeathObeliskBlock;
import net.purplemushroom.neverend.content.blocks.tile.DeathObeliskBlockEntity;
import net.purplemushroom.neverend.content.entities.FishingRift;
import net.purplemushroom.neverend.registry.NEBlocks;
import net.purplemushroom.neverend.registry.NEItems;
import net.purplemushroom.neverend.util.EntityUtil;
import net.purplemushroom.neverend.util.MathUtil;

import java.util.UUID;

import static net.purplemushroom.neverend.content.blocks.DeathObeliskBlock.HALF;

@Mod.EventBusSubscriber(modid = Neverend.MODID)
public class PlayerEventHandler {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            Player player = event.player;
            Level level = player.level();
            NEPlayer playerCap = NEPlayer.from(player);
            if (!event.player.level().isClientSide()) {
                if (playerCap != null) {
                    /**
                     * Player BlockPos Handler
                     */
                    PlayerTracker fallTracker = playerCap.playerTracker;
                    if (level.getGameTime() % 20L == 0L) {
                        if (player.blockPosition() != fallTracker.getLastGroundPos()) {
                            if (EntityUtil.isAtStableLocation(player)) {
                                fallTracker.setLastGroundStats(player.blockPosition(), level.dimension().location());
                            } else if (player.isPassenger() && player.getVehicle() != null && EntityUtil.isAtStableLocation(player.getVehicle())) {
                                Entity vehicle = player.getVehicle();
                                fallTracker.setLastGroundStats(vehicle.blockPosition(), level.dimension().location());
                            }
                        }
                        playerCap.detectAndSendChanges();
                    }

                    /**
                     * Rift Fishing Handler
                     */
                    RiftFishingData riftFishingData = playerCap.riftFishingData;
                    if (riftFishingData.isActive()) { // FIXME: will break if you exit world while doing the rift thingie
                        FishingRift fishingRift = riftFishingData.getFishingRift(level);
                        if (player.distanceTo(fishingRift) > 16 || !EntityUtil.isHolding(player, NEItems.SHIFTERINE_ROD)) {
                            riftFishingData.stopFishingFromRift();
                            playerCap.detectAndSendChanges();
                        } else {
                            float looking = (float) EntityUtil.lookingAt(player, EntityUtil.getCenterPos(fishingRift));
                            if (looking <= 0.0f) riftFishingData.stopFishingFromRift();
                            riftFishingData.progressFishing(looking / 100);
                            System.out.println(riftFishingData.getProgress());
                        }
                    }
                }
            } else {
                if (playerCap != null) {
                    RiftFishingData riftFishingData = playerCap.riftFishingData;
                    if (riftFishingData.isActive()) { // FIXME: will break if you exit world while doing the rift thingie
                        FishingRift fishingRift = riftFishingData.getFishingRift(level);
                        Vec3 playerPos = event.player.getEyePosition();
                        Vec3 riftPos = EntityUtil.getCenterPos(fishingRift);
                        Vec3 extraPoint = playerPos.add(player.getViewVector(0.0f).scale(10));
                        for (Vec3 node : MathUtil.createBezierCurve(playerPos, riftPos, extraPoint, 20)) {
                            level.addParticle(ParticleTypes.CRIT, node.x, node.y, node.z, 0, 0, 0);
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerDeath(LivingDeathEvent event) {
        Entity entity = event.getEntity();
        Level level = event.getEntity().level();
        if (entity instanceof Player player) {
            NEPlayer playerCap = NEPlayer.from(player);
            if (playerCap != null) {
                if (!player.level().isClientSide()) {
                    PlayerTracker playerTracker = playerCap.playerTracker;
                    BlockPos lastPos = playerTracker.getLastGroundPos();
                    level.setBlock(lastPos, NEBlocks.DEATH_OBELISK.defaultBlockState(), 3);  //fixme single block
                    playerTracker.setPlayerID(player.getUUID());
                    playerTracker.setDeathID(UUID.randomUUID());
                    BlockEntity blockEntity = level.getBlockEntity(lastPos);
                    if (blockEntity instanceof DeathObeliskBlockEntity deathObeliskBlockEntity) {

                    }
                }
                playerCap.detectAndSendChanges();
            }
        }
    }
}
