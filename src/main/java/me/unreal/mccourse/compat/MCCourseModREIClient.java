package me.unreal.mccourse.compat;

import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import me.unreal.mccourse.block.ModBlocks;
import me.unreal.mccourse.recipe.CrystallizerRecipe;
import me.unreal.mccourse.recipe.ModRecipes;
import me.unreal.mccourse.screen.custom.CrystallizerScreen;

public class MCCourseModREIClient implements REIClientPlugin {
    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new CrystallizerCategory());

        registry.addWorkstations(CrystallizerCategory.CRYSTALLIZER, EntryStacks.of(ModBlocks.CRYSTALLIZER));
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerRecipeFiller(CrystallizerRecipe.class, ModRecipes.CRYSTALLIZER_RECIPE_TYPE,
                CrystallizerDisplay::new);
    }

    @Override
    public void registerScreens(ScreenRegistry registry) {
        registry.registerClickArea(screen -> new Rectangle(((screen.width - 176) / 2) + 78,
                ((screen.height - 166) / 2) + 30, 20, 25),
                CrystallizerScreen.class, CrystallizerCategory.CRYSTALLIZER);
    }
}
