package net.purplemushroom.neverend.registry;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.purplemushroom.neverend.Neverend;
import net.purplemushroom.neverend.client.render.NEBlockStateResources;
import net.purplemushroom.neverend.content.blocks.*;
import net.purplemushroom.neverend.content.blocks.tile.DeathObeliskBlockEntity;
import net.purplemushroom.neverend.content.blocks.tile.EndAltarBlockEntity;
import net.purplemushroom.neverend.content.blocks.tile.EndAnchorBlockEntity;
import net.purplemushroom.neverend.content.blocks.tile.GazeCrystalBlockEntity;
import ru.timeconqueror.timecore.api.client.resource.BlockModels;
import ru.timeconqueror.timecore.api.registry.BlockRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;
import ru.timeconqueror.timecore.api.registry.util.BlockPropsFactory;

import java.util.function.Supplier;

@AutoRegistrable.Entries("block")
public class NEBlocks {
    public static Block PALE_END_STONE;
    public static Block SHIFTERINE_ORE;
    public static Block LUDUNITE_ORE;
    public static Block RAW_ALDORES_ORE;
    public static Block ALDORES_ORE;
    public static Block DRAGON_BONE;
    public static Block DRAGONBONE_ORE;
    public static Block ENCHANTMENT_CRYSTAL_ORE;
    public static Block END_ALTAR;
    public static Block ENCHANTMENT_SHRINE;
    public static Block DEATH_OBELISK;
    public static Block END_ANCHOR;
    public static Block GAZE_CRYSTAL;
    public static Block QUANTUM_CAKE;

    @AutoRegistrable
    private static final BlockRegister BLOCKS = new BlockRegister(Neverend.MODID);

    @AutoRegistrable.Init
    private static void register() {
        BLOCKS.register("pale_end_stone", () -> new Block(new BlockPropsFactory(() -> BlockBehaviour.Properties.copy(Blocks.END_STONE)
                        .strength(1.0F, 3.0F)
                        .lightLevel(value -> 5)
                        .requiresCorrectToolForDrops()).create()))
                .oneVarStateAndCubeAllModel()
                .defaultBlockItem(NECreativeTabs.BLOCKS)
                .name("Pale End Stone");
        BLOCKS.register("shifterine_ore", () -> new Block(new BlockPropsFactory(() -> BlockBehaviour.Properties.copy(Blocks.END_STONE)
                        .strength(2.0F, 6.0F)
                        .requiresCorrectToolForDrops()).create()))
                .oneVarStateAndCubeAllModel()
                .defaultBlockItem(NECreativeTabs.BLOCKS)
                .name("Shifterine Ore");
        BLOCKS.register("ludunite_ore", () -> new Block(new BlockPropsFactory(() -> BlockBehaviour.Properties.copy(Blocks.END_STONE)
                        .strength(2.0F, 6.0F)
                        .requiresCorrectToolForDrops()).create()))
                .oneVarStateAndCubeAllModel()
                .defaultBlockItem(NECreativeTabs.BLOCKS)
                .name("Ludunite Ore");
        BLOCKS.register("aldores_ore", () -> new Block(new BlockPropsFactory(() -> BlockBehaviour.Properties.copy(Blocks.END_STONE)
                        .strength(2.0F, 6.0F)
                        .requiresCorrectToolForDrops()).create()))
                .oneVarStateAndCubeAllModel()
                .defaultBlockItem(NECreativeTabs.BLOCKS)
                .name("Aldores Ore");
        BLOCKS.register("raw_aldores_ore", () -> new Block(new BlockPropsFactory(() -> BlockBehaviour.Properties.copy(Blocks.ANCIENT_DEBRIS)
                        .lightLevel(light -> 20)
                        .emissiveRendering(NEBlocks::always)
                        .strength(30.0F, 50.0F)
                        .requiresCorrectToolForDrops()).create()))
                .oneVarStateAndCubeAllModel()
                .defaultBlockItem(NECreativeTabs.BLOCKS)
                .name("Raw Aldores Ore");

        registerColumnRenderedBlock("dragon_bone", "Dragon Bone", () -> new RotatedPillarBlock(new BlockPropsFactory(() -> BlockBehaviour.Properties.copy(Blocks.BONE_BLOCK)
                .sound(NESoundTypes.DRAGON_BONE)
                .strength(5.0F, 10.0F)
                .requiresCorrectToolForDrops()).create()), "dragon_bone_top", "dragon_bone_side");

        registerColumnRenderedBlock("dragonbone_ore", "Dragonbone Ore", () -> new RotatedPillarBlock(new BlockPropsFactory(() -> BlockBehaviour.Properties.copy(Blocks.ANCIENT_DEBRIS)
                .sound(NESoundTypes.DRAGON_BONE)
                .strength(20.0F, 40.0F)
                .requiresCorrectToolForDrops()).create()), "dragonbone_ore_top", "dragonbone_ore_side");

        BLOCKS.register("enchantment_crystal_ore", () -> new Block(new BlockPropsFactory(() -> BlockBehaviour.Properties.copy(Blocks.END_STONE)
                        .strength(2.0F, 6.0F)
                        .requiresCorrectToolForDrops()).create()))
                .oneVarStateAndCubeAllModel()
                .defaultBlockItem(NECreativeTabs.BLOCKS)
                .name("Enchantment Crystal Ore");
        BLOCKS.register("end_altar", () -> new EndAltarBlock(new BlockPropsFactory(() -> BlockBehaviour.Properties.copy(Blocks.END_STONE)
                        .strength(2.0F, 6.0F)
                        .requiresCorrectToolForDrops()).create(), EndAltarBlockEntity::new))
                .oneVarStateAndCubeAllModel()
                .defaultBlockItem(NECreativeTabs.BLOCKS)
                .name("End Altar");
        BLOCKS.register("enchantment_shrine", () -> new EnchantmentShrineBlock(new BlockPropsFactory(() -> BlockBehaviour.Properties.copy(Blocks.END_STONE)
                        .strength(2.0F, 6.0F)
                        .requiresCorrectToolForDrops()).create()))
                .oneVarStateAndCubeAllModel()
                .defaultBlockItem(NECreativeTabs.BLOCKS)
                .name("Enchantment Shrine");
        BLOCKS.register("death_obelisk", () -> new DeathObeliskBlock(new BlockPropsFactory(() -> BlockBehaviour.Properties.copy(Blocks.END_STONE)
                        .strength(2.0F, 6.0F)
                        .lightLevel((level) -> 4)
                        .requiresCorrectToolForDrops()).create(), DeathObeliskBlockEntity::new))

                .renderLayer(() -> RenderTypeWrappers.TRANSLUCENT)
                .state(NEBlockStateResources.halfState(Neverend.bml("block/death_obelisk_bottom"), Neverend.bml("block/death_obelisk_top")))

                .model(Neverend.bml("block/death_obelisk_top"), () -> BlockModels.cubeColumnModel(
                        Neverend.tl("block/death_obelisk_bottom"),
                        Neverend.tl("block/death_obelisk_top_front")))

                .model(Neverend.bml("block/death_obelisk_bottom"), BlockModels.cubeColumnModel(
                        Neverend.tl("block/death_obelisk_bottom"),
                        Neverend.tl("block/death_obelisk_bottom_side")))
                .defaultBlockItem(NECreativeTabs.BLOCKS)
                .name("Death Obelisk");
        BLOCKS.register("end_anchor", () -> new EndAnchorBlock(new BlockPropsFactory(() -> BlockBehaviour.Properties.copy(Blocks.END_STONE)
                        .strength(2.0F, 6.0F)
                        .requiresCorrectToolForDrops()).create(), EndAnchorBlockEntity::new))

                .renderLayer(() -> RenderTypeWrappers.TRANSLUCENT)
                .state(NEBlockStateResources.anchorState(Neverend.bml(
                        "block/end_anchor_0"), Neverend.bml(
                        "block/end_anchor_1"), Neverend.bml(
                        "block/end_anchor_2"), Neverend.bml(
                        "block/end_anchor_3"), Neverend.bml(
                        "block/end_anchor_4")))

                .model(Neverend.bml("block/end_anchor_0"), () -> BlockModels.cubeOrientableModel(
                        Neverend.tl("block/end_anchor_top"),
                        Neverend.tl("block/end_anchor_front_0"),
                        Neverend.tl("block/end_anchor_side")))
                .model(Neverend.bml("block/end_anchor_1"), () -> BlockModels.cubeOrientableModel(
                        Neverend.tl("block/end_anchor_top"),
                        Neverend.tl("block/end_anchor_front_1"),
                        Neverend.tl("block/end_anchor_side")))
                .model(Neverend.bml("block/end_anchor_2"), () -> BlockModels.cubeOrientableModel(
                        Neverend.tl("block/end_anchor_top"),
                        Neverend.tl("block/end_anchor_front_2"),
                        Neverend.tl("block/end_anchor_side")))
                .model(Neverend.bml("block/end_anchor_3"), () -> BlockModels.cubeOrientableModel(
                        Neverend.tl("block/end_anchor_top"),
                        Neverend.tl("block/end_anchor_front_3"),
                        Neverend.tl("block/end_anchor_side")))
                .model(Neverend.bml("block/end_anchor_4"), () -> BlockModels.cubeOrientableModel(
                        Neverend.tl("block/end_anchor_top"),
                        Neverend.tl("block/end_anchor_front_4"),
                        Neverend.tl("block/end_anchor_side")))
                .defaultBlockItem(NECreativeTabs.BLOCKS)
                .name("End Anchor");
        BLOCKS.register("gaze_crystal", () -> new GazeCrystalBlock(new BlockPropsFactory(() -> BlockBehaviour.Properties.copy(Blocks.END_STONE)
                        .strength(2.0F, 6.0F)
                        .lightLevel((light) -> 4)
                        .requiresCorrectToolForDrops()).create(), GazeCrystalBlockEntity::new))
                .oneVarStateAndCubeAllModel()
                .defaultBlockItem(NECreativeTabs.BLOCKS)
                .name("Gaze Crystal");

        BLOCKS.register("quantum_cake", () -> new QuantumCakeBlock(new BlockPropsFactory(() -> BlockBehaviour.Properties.copy(Blocks.CAKE)
        ).create()))
                .defaultBlockItem(NECreativeTabs.BLOCKS)
                .name("Quantum Cake");
    }

    /**
     * Registers a block with a "block/cube_column" model type
     */
    private static void registerColumnRenderedBlock(String name, String enName, Supplier<Block> blockSupplier, String topTexture, String sideTexture) {
        BLOCKS.register(name, blockSupplier)
                .name(enName)
                .defaultBlockItem(NECreativeTabs.BLOCKS)
                .state(NEBlockStateResources.rotatablePillarState(Neverend.bml("block/" + name)))
                .model(Neverend.bml("block/" + name),
                        () -> BlockModels.cubeColumnModel(Neverend.tl("block/" + topTexture), Neverend.tl("block/" + sideTexture)));
    }

    public static class RenderTypeWrappers {
        public static final BlockRegister.RenderTypeWrapper CUTOUT = new BlockRegister.RenderTypeWrapper(RenderType.cutout());
        public static final BlockRegister.RenderTypeWrapper CUTOUT_MIPPED = new BlockRegister.RenderTypeWrapper(RenderType.cutoutMipped());
        public static final BlockRegister.RenderTypeWrapper TRANSLUCENT = new BlockRegister.RenderTypeWrapper(RenderType.translucent());
        public static final BlockRegister.RenderTypeWrapper TRANSLUCENT_NO_CRUMBLING = new BlockRegister.RenderTypeWrapper(RenderType.translucentNoCrumbling());
    }

    private static boolean always(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return true;
    }
}
