package net.purplemushroom.everend.registry;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.purplemushroom.everend.Everend;
import net.purplemushroom.everend.content.entities.EnderLord;
import net.purplemushroom.everend.content.entities.FishingRift;
import net.purplemushroom.everend.content.entities.VoidRift;
import ru.timeconqueror.timecore.api.registry.EntityRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;
import ru.timeconqueror.timecore.api.registry.util.Promised;

@AutoRegistrable.Entries("entity")
public class EEEntities {

    @AutoRegistrable
    private static final EntityRegister ENTITIES = new EntityRegister(Everend.MODID);

    public static final Promised<EntityType<FishingRift>> FISHING_RIFT_TYPE = ENTITIES.register(
            "fishing_rift",
            EntityType.Builder.of(FishingRift::new, MobCategory.MISC)
                    .sized(5.0f, 5.0f)).asPromised();

    public static final Promised<EntityType<VoidRift>> VOID_RIFT_TYPE = ENTITIES.register(
            "void_rift", EntityType.Builder.of(VoidRift::new, MobCategory.MISC)
                    .sized(5.0f, 5.0f)).asPromised();

    public static final Promised<EntityType<EnderLord>> ENDER_LORD_TYPE = ENTITIES.register(
            "ender_lord", EntityType.Builder.of(EnderLord::new, MobCategory.MONSTER)
                    .sized(0.6F, 2.9F)
                    .clientTrackingRange(8)).asPromised();
}



