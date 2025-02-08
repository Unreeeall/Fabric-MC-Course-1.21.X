package me.unreal.mccourse.datagen;

import me.unreal.mccourse.MCCourseMod;
import me.unreal.mccourse.block.ModBlocks;
import me.unreal.mccourse.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeGenerator extends FabricRecipeProvider {
    public ModRecipeGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter recipeExporter) {
        List<ItemConvertible> FLUORITE_SMELTABLES = List.of(
                ModItems.RAW_FLUORITE,
                ModBlocks.FLUORITE_ORE,
                ModBlocks.FLUORITE_DEEPSLATE_ORE,
                ModBlocks.FLUORITE_END_ORE,
                ModBlocks.FLUORITE_NETHER_ORE);
        offerSmelting(recipeExporter, FLUORITE_SMELTABLES, RecipeCategory.MISC, ModItems.FLUORITE, 0.2f, 200, "fluorite");
        offerBlasting(recipeExporter, FLUORITE_SMELTABLES, RecipeCategory.MISC, ModItems.FLUORITE, 0.1f, 100, "fluorite");


        offerReversibleCompactingRecipes(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModItems.FLUORITE, RecipeCategory.DECORATIONS, ModBlocks.FLUORITE_BLOCK);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RAW_FLUORITE)
                .pattern("SFS")
                .pattern("SFS")
                .pattern("SFS")
                .input('S', Blocks.STONE)
                .input('F', ModBlocks.FLUORITE_BLOCK)
                .criterion(hasItem(Blocks.STONE), conditionsFromItem(Blocks.STONE))
                .criterion(hasItem(ModBlocks.FLUORITE_BLOCK), conditionsFromItem(ModBlocks.FLUORITE_BLOCK))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RAW_FLUORITE)
                .pattern("SSS")
                .pattern("FFF")
                .pattern("SSS")
                .input('S', Blocks.STONE)
                .input('F', ModBlocks.FLUORITE_BLOCK)
                .criterion(hasItem(Blocks.STONE), conditionsFromItem(Blocks.STONE))
                .criterion(hasItem(ModBlocks.FLUORITE_BLOCK), conditionsFromItem(ModBlocks.FLUORITE_BLOCK))
                .offerTo(recipeExporter, Identifier.of(MCCourseMod.MOD_ID, "raw_fluorite_2"));
    }
}
