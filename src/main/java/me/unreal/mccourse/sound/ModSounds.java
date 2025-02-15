package me.unreal.mccourse.sound;

import me.unreal.mccourse.MCCourseMod;
import net.minecraft.client.sound.Sound;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {

    public static final SoundEvent CHAINSAW_CUT = registerSoundEvent("chainsaw_cut");
    public static final SoundEvent CHAINSAW_PULL = registerSoundEvent("chainsaw_pull");


    private static SoundEvent registerSoundEvent(String name) {
        return Registry.register(Registries.SOUND_EVENT, Identifier.of(MCCourseMod.MOD_ID, name),
                SoundEvent.of(Identifier.of(MCCourseMod.MOD_ID, name)));
    }

    public static void registerSounds() {
        MCCourseMod.LOGGER.info("Registering Mod Sounds for " + MCCourseMod.MOD_ID);
    }
}
