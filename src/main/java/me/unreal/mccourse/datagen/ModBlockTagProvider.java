package me.unreal.mccourse.datagen;

import me.unreal.mccourse.block.ModBlocks;
import me.unreal.mccourse.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.FLUORITE_BLOCK,
                        ModBlocks.FLUORITE_ORE,
                        ModBlocks.FLUORITE_DEEPSLATE_ORE,
                        ModBlocks.FLUORITE_END_ORE,
                        ModBlocks.FLUORITE_NETHER_ORE,
                        ModBlocks.MAGIC_BLOCK
                );

        getOrCreateTagBuilder(ModTags.Blocks.NEEDS_FLUORITE_TOOL)
                .add(ModBlocks.FLUORITE_BLOCK
                );

        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.FLUORITE_END_ORE,
                        ModBlocks.FLUORITE_NETHER_ORE
                );

        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.FLUORITE_ORE,
                        ModBlocks.FLUORITE_DEEPSLATE_ORE
                );

        getOrCreateTagBuilder(BlockTags.WALLS).add(ModBlocks.FLUORITE_WALL);
        getOrCreateTagBuilder(BlockTags.FENCES).add(ModBlocks.FLUORITE_FENCE);
        getOrCreateTagBuilder(BlockTags.FENCE_GATES).add(ModBlocks.FLUORITE_FENCE_GATE);



        getOrCreateTagBuilder(
                BlockTags.INCORRECT_FOR_NETHERITE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_FLUORITE_TOOL);

        getOrCreateTagBuilder(
                BlockTags.INCORRECT_FOR_DIAMOND_TOOL)
                .addTag(ModTags.Blocks.NEEDS_FLUORITE_TOOL);
        getOrCreateTagBuilder(
                BlockTags.INCORRECT_FOR_GOLD_TOOL)
                .addTag(ModTags.Blocks.NEEDS_FLUORITE_TOOL);
        getOrCreateTagBuilder(
                BlockTags.INCORRECT_FOR_IRON_TOOL)
                .addTag(ModTags.Blocks.NEEDS_FLUORITE_TOOL);
        getOrCreateTagBuilder(
                BlockTags.INCORRECT_FOR_STONE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_FLUORITE_TOOL);
        getOrCreateTagBuilder(
                BlockTags.INCORRECT_FOR_WOODEN_TOOL)
                .addTag(ModTags.Blocks.NEEDS_FLUORITE_TOOL);


    }

}
