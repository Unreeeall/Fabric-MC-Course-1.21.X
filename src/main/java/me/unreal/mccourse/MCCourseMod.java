package me.unreal.mccourse;

import me.unreal.mccourse.command.ReturnHomeCommand;
import me.unreal.mccourse.command.SetHomeCommand;
import me.unreal.mccourse.components.ModDataComponentTypes;
import me.unreal.mccourse.event.AttackEntityHandler;
import me.unreal.mccourse.item.ModItems;
import me.unreal.mccourse.util.HammerUsageEvent;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.ComposterBlock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static me.unreal.mccourse.block.ModBlocks.registerModBlocks;
import static me.unreal.mccourse.effect.ModEffects.registerEffects;
import static me.unreal.mccourse.item.ModItemGroups.registerItemGroups;
import static me.unreal.mccourse.item.ModItems.registerModItems;
import static me.unreal.mccourse.sound.ModSounds.registerSounds;

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
		registerSounds();
		registerEffects();
		ModDataComponentTypes.registerDataComponentTypes();
		CommandRegistrationCallback.EVENT.register(SetHomeCommand::register);
		CommandRegistrationCallback.EVENT.register(ReturnHomeCommand::register);

		AttackEntityCallback.EVENT.register(new AttackEntityHandler());

		FuelRegistry.INSTANCE.add(ModItems.STARLIGHT_ASHES, 600);

		PlayerBlockBreakEvents.BEFORE.register(new HammerUsageEvent());

		ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.STRAWBERRY, 0.5f);
		ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.STRAWBERRY_SEEDS, 0.2f);

		LOGGER.info("Hello Fabric world!");
	}
}