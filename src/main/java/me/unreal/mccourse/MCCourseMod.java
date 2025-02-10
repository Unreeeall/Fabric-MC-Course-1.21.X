package me.unreal.mccourse;

import me.unreal.mccourse.components.ModDataComponentTypes;
import me.unreal.mccourse.item.ModItems;
import me.unreal.mccourse.util.HammerUsageEvent;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static me.unreal.mccourse.block.ModBlocks.registerModBlocks;
import static me.unreal.mccourse.item.ModItemGroups.registerItemGroups;
import static me.unreal.mccourse.item.ModItems.registerModItems;

public class MCCourseMod implements ModInitializer {
	public static final String MOD_ID = "mccourse";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		registerModItems();
		registerItemGroups();
		registerModBlocks();
		ModDataComponentTypes.registerDataComponentTypes();

		FuelRegistry.INSTANCE.add(ModItems.STARLIGHT_ASHES, 600);

		PlayerBlockBreakEvents.BEFORE.register(new HammerUsageEvent());
		LOGGER.info("Hello Fabric world!");
	}
}