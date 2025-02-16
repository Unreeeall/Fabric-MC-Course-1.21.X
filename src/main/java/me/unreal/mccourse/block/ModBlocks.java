package me.unreal.mccourse.block;

import me.unreal.mccourse.MCCourseMod;
import me.unreal.mccourse.block.custom.*;
import me.unreal.mccourse.sound.ModSounds;
import net.minecraft.block.*;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class ModBlocks {

    public static final Block FLUORITE_BLOCK = registerBlock("fluorite_block",
            new Block(AbstractBlock.Settings.create()
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)
                    .strength(4f)
                    .requiresTool()
            ));

    public static final Block FLUORITE_ORE = registerBlock("fluorite_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(2, 4),
                    AbstractBlock.Settings.create()
                    .strength(4f)
                    .requiresTool()
            ));

    public static final Block FLUORITE_DEEPSLATE_ORE = registerBlock("fluorite_deepslate_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(4, 6),
                    AbstractBlock.Settings.create()
                            .strength(6f)
                            .requiresTool()
            ));

    public static final Block FLUORITE_END_ORE = registerBlock("fluorite_end_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(6, 8),
                    AbstractBlock.Settings.create()
                            .strength(5f)
                            .requiresTool()
            ));

    public static final Block FLUORITE_NETHER_ORE = registerBlock("fluorite_nether_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(4, 6),
                    AbstractBlock.Settings.create()
                            .strength(3f)
                            .requiresTool()
            ));

    public static final Block MAGIC_BLOCK = registerBlock("magic_block",
            new MagicBlock(AbstractBlock.Settings.create()
                    .strength(2f)
                    .requiresTool()
                    .sounds(ModSounds.MAGIC_BLOCK_SOUNDS)
            ));

    public static final Block FLUORITE_STAIRS = registerBlock("fluorite_stairs",
            new StairsBlock(ModBlocks.FLUORITE_BLOCK.getDefaultState(), AbstractBlock.Settings.create()
                    .strength(2f)
                    .requiresTool()
            ));

    public static final Block FLUORITE_SLAB = registerBlock("fluorite_slab",
            new SlabBlock(AbstractBlock.Settings.create()
                    .strength(2f)
                    .requiresTool()
            ));

    public static final Block FLUORITE_BUTTON = registerBlock("fluorite_button",
            new ButtonBlock(BlockSetType.IRON, 2, AbstractBlock.Settings.create()
                    .requiresTool()
            ));

    public static final Block FLUORITE_PRESSURE_PLATE = registerBlock("fluorite_pressure_plate",
            new PressurePlateBlock(BlockSetType.IRON, AbstractBlock.Settings.create()
                    .requiresTool()
            ));

    public static final Block FLUORITE_FENCE = registerBlock("fluorite_fence",
            new FenceBlock(AbstractBlock.Settings.create()
                    .requiresTool()
            ));

    public static final Block FLUORITE_FENCE_GATE = registerBlock("fluorite_fence_gate",
            new FenceGateBlock(WoodType.ACACIA, AbstractBlock.Settings.create()
                    .requiresTool()
            ));

    public static final Block FLUORITE_WALL = registerBlock("fluorite_wall",
            new WallBlock(AbstractBlock.Settings.create()
                    .requiresTool()
            ));

    public static final Block FLUORITE_DOOR = registerBlock("fluorite_door",
            new DoorBlock(BlockSetType.IRON, AbstractBlock.Settings.create()
                    .requiresTool()
                    .nonOpaque()
            ));

    public static final Block FLUORITE_TRAPDOOR = registerBlock("fluorite_trapdoor",
            new TrapdoorBlock(BlockSetType.IRON, AbstractBlock.Settings.create()
                    .requiresTool()
                    .nonOpaque()
            ));

    public static final Block FLUORITE_LAMP = registerBlock("fluorite_lamp",
            new FluoriteLampBlock(AbstractBlock.Settings.create()
                    .strength(2f).luminance(state ->state.get(FluoriteLampBlock.CLICKED) ? 15 : 0)
            ));

    public static final Block STRAWBERRY_CROP =registerBlockWithoutItem("strawberry_crop",
            new StrawberryCropBlock(AbstractBlock.Settings.copy(Blocks.WHEAT)));

    public static final Block DAHLIA = registerBlock("dahlia",
            new FlowerBlock(StatusEffects.INVISIBILITY, 4, AbstractBlock.Settings.copy(Blocks.ALLIUM)
            ));

    public static final Block POTTED_DAHLIA = registerBlockWithoutItem("potted_dahlia",
            new FlowerPotBlock(DAHLIA ,AbstractBlock.Settings.copy(Blocks.POTTED_ALLIUM)
            ));

    public static final Block COLORED_LEAVES = registerBlock("colored_leaves",
            new LeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES)
            ));

    public static final Block PEDESTAL = registerBlock("pedestal",
            new PedestalBlock(AbstractBlock.Settings.create()
                    .strength(4f)
                    .requiresTool()
                    .nonOpaque()
            ));

    public static final Block CRYSTALLIZER = registerBlock("crystallizer",
            new CrystallizerBlock(AbstractBlock.Settings.create()
                    .strength(2f)
                    .requiresTool()
            ));











    private static Block registerBlock(String name, Block block) {
        registerBLockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(MCCourseMod.MOD_ID, name), block);
    }

    private static void registerBLockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(MCCourseMod.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    private static Block registerBlockWithoutItem(String name, Block block) {
        return Registry.register(Registries.BLOCK, Identifier.of(MCCourseMod.MOD_ID, name), block);
    }


    public static void registerModBlocks() {
        MCCourseMod.LOGGER.info("Registering Blocks for " + MCCourseMod.MOD_ID);
    }
}
