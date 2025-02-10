package me.unreal.mccourse.util;

import me.unreal.mccourse.MCCourseMod;
import me.unreal.mccourse.components.ModDataComponentTypes;
import me.unreal.mccourse.item.ModItems;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;

public class ModModelPredicates {

    public static void registerModelPredicates() {
        ModelPredicateProviderRegistry.register(ModItems.DATA_TABLET, Identifier.of(MCCourseMod.MOD_ID, "on"),
                (stack, world, entity, seed) -> stack.get(ModDataComponentTypes.FOUND_BLOCK) != null ? 1f : 0f);
    }
}
