package me.unreal.mccourse.world.gen;

import me.unreal.mccourse.world.ModPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;

public class ModFlowerGeneration {
    public static void generateFlowers(){
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.PLAINS, BiomeKeys.FOREST, BiomeKeys.FLOWER_FOREST),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.DAHLIA_PLACED_KEY);
    }
}
