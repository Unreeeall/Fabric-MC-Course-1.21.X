package me.unreal.mccourse.world.gen;

import me.unreal.mccourse.world.ModPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.gen.GenerationStep;

public class ModGeodeGeneration {
    public static void generateGeodes(){
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.LOCAL_MODIFICATIONS, ModPlacedFeatures.FLUORITE_GEODE_PLACED_KEY);
    }
}
