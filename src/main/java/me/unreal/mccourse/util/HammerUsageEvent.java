package me.unreal.mccourse.util;

import me.unreal.mccourse.item.custom.HammerItem;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;

public class HammerUsageEvent implements PlayerBlockBreakEvents.Before{

    // Done with the help of https://github.com/CoFH/CoFHCore/blob/c23d117dcd3b3b3408a138716b15507f709494cd/src/main/java/cofh/core/event/AreaEffectEvents.java
    private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();

    @Override
    public boolean beforeBlockBreak(World world, PlayerEntity playerEntity, BlockPos blockPos, BlockState blockState, @Nullable BlockEntity blockEntity) {
        ItemStack mainHandItem = playerEntity.getMainHandStack();

        if (mainHandItem.getItem() instanceof HammerItem hammer && playerEntity instanceof ServerPlayerEntity serverPlayer) {
            if (HARVESTED_BLOCKS.contains(blockPos)) {
                return true;
            }

            for (BlockPos pos : HammerItem.getBlocksToBeDestroyed(2, blockPos, serverPlayer)) {
                if (blockPos == pos || !hammer.isCorrectForDrops(mainHandItem, world.getBlockState(pos))) {
                    continue;
                }

                HARVESTED_BLOCKS.add(pos);
                serverPlayer.interactionManager.tryBreakBlock(pos);
                HARVESTED_BLOCKS.remove(pos);
            }
        }

        return true;
    }
}
