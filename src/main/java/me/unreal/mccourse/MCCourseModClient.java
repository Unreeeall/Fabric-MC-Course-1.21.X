package me.unreal.mccourse;

import me.unreal.mccourse.block.ModBlocks;
import me.unreal.mccourse.util.ModModelPredicates;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class MCCourseModClient  implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FLUORITE_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FLUORITE_TRAPDOOR, RenderLayer.getCutout());

        ModModelPredicates.registerModelPredicates();
    }
}
