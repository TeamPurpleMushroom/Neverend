package net.purplemushroom.neverend.registry;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.purplemushroom.neverend.Neverend;
import ru.timeconqueror.timecore.api.registry.BlockRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;
import ru.timeconqueror.timecore.api.registry.util.BlockPropsFactory;

@AutoRegistrable.Entries("block")
public class NEBlocks {
    public static Block PALE_END_STONE;

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
    }
}
