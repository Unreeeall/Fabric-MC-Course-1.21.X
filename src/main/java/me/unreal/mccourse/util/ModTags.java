package me.unreal.mccourse.util;

import me.unreal.mccourse.MCCourseMod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_FLUORITE_TOOL = createTag("needs_fluorite_tool");
        public static final TagKey<Block> INCORRECT_FOR_FLUORITE_TOOL = createTag("needs_fluorite_tool");
        public static final TagKey<Block> PAXEL_MINEABLE = createTag("paxel_mineable");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(MCCourseMod.MOD_ID, name));
        }
    }


    public static class Items {
        public static final TagKey<Item> TRANSFORMABLE_ITEMS = createTag("transformable_items");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(MCCourseMod.MOD_ID, name));

        }

    }
}
