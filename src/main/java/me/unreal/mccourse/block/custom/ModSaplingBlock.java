package me.unreal.mccourse.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.SaplingGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

import java.util.List;


public class ModSaplingBlock extends SaplingBlock {

    private List<Block> blocksToSpawnOn;

    public ModSaplingBlock(SaplingGenerator generator, Settings settings, List<Block> blocksToSpawnOn) {
        super(generator, settings);
        this.blocksToSpawnOn = blocksToSpawnOn;
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return blocksToSpawnOn.stream().anyMatch(block -> floor.isOf(block));
    }


}
