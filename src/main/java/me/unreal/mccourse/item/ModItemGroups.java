package me.unreal.mccourse.item;

import me.unreal.mccourse.MCCourseMod;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    public static final ItemGroup FLUORITE_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(MCCourseMod.MOD_ID, "fluorite"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.fluorite"))
                    .icon(()-> new ItemStack(ModItems.FLUORITE)).entries((displayContext, entries) -> {
                        entries.add(ModItems.FLUORITE);
                        entries.add(ModItems.RAW_FLUORITE);
                    }).build()
    );

    public static final ItemGroup FLUORITE_BLOCK_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(MCCourseMod.MOD_ID, "fluorite_blocks"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.fluorite_blocks"))
                    .icon(()-> new ItemStack(Blocks.BAMBOO.asItem())).entries((displayContext, entries) -> {
                        entries.add(Blocks.BAMBOO);
                    }).build()
    );

    public static void registerItemGroups() {
        MCCourseMod.LOGGER.info("Registering Item Groups for " + MCCourseMod.MOD_ID);
    }
}
