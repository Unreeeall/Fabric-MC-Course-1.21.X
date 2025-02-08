package me.unreal.mccourse;

import me.unreal.mccourse.datagen.*;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class MCCourseModDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModRecipeGenerator::new);
		pack.addProvider(ModLootTableGenerator::new);
		pack.addProvider(ModItemTagProvider::new);
		pack.addProvider(ModBlockTagProvider::new);
	}
}
