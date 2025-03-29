package me.unreal.mccourse;

import me.unreal.mccourse.block.ModBlocks;
import me.unreal.mccourse.fluid.ModFluids;
import me.unreal.mccourse.util.ModModelPredicates;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.world.biome.FoliageColors;

public class MCCourseModClient  implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FLUORITE_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FLUORITE_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.STRAWBERRY_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DAHLIA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_DAHLIA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BLACKWOOD_SAPLING, RenderLayer.getCutout());


        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> world != null && pos != null ?
                BiomeColors.getFoliageColor(world, pos) : FoliageColors.getDefaultColor(), ModBlocks.COLORED_LEAVES);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> FoliageColors.getDefaultColor(), ModBlocks.COLORED_LEAVES);

        ModModelPredicates.registerModelPredicates();

        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_FLUORITE_WATER, ModFluids.FLOWING_FLUORITE_WATER,
                SimpleFluidRenderHandler.coloredWater(0xA1E038D0));
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(),
                ModFluids.STILL_FLUORITE_WATER, ModFluids.FLOWING_FLUORITE_WATER);
    }
}
