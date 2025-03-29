package me.unreal.mccourse.world.tree;

import me.unreal.mccourse.MCCourseMod;
import me.unreal.mccourse.world.ModConfiguredFeatures;
import net.minecraft.block.SaplingGenerator;

import java.util.Optional;

public class ModSaplingGenerators {

    public static final SaplingGenerator BLACKWOOD = new SaplingGenerator(MCCourseMod.MOD_ID + ":blackwood",
            Optional.empty(), Optional.of(ModConfiguredFeatures.BLACKWOOD_KEY), Optional.empty());
}
