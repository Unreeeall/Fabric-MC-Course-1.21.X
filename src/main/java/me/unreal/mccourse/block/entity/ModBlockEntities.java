package me.unreal.mccourse.block.entity;

import me.unreal.mccourse.MCCourseMod;
import me.unreal.mccourse.block.ModBlocks;
import me.unreal.mccourse.block.entity.custom.PedestalBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {

    public static final BlockEntityType<PedestalBlockEntity> PEDESTAL_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(MCCourseMod.MOD_ID, "pedestal_be"),
                    BlockEntityType.Builder.create(PedestalBlockEntity::new, ModBlocks.PEDESTAL).build(null));

    public static void registerBlockEntities() {
        MCCourseMod.LOGGER.info("Registering Block entities for " + MCCourseMod.MOD_ID);
    }
}
