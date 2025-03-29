package me.unreal.mccourse.datagen;

import me.unreal.mccourse.block.ModBlocks;
import me.unreal.mccourse.block.custom.StrawberryCropBlock;
import me.unreal.mccourse.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModLootTableGenerator extends FabricBlockLootTableProvider {
    public ModLootTableGenerator(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.FLUORITE_BLOCK);
        addDrop(ModBlocks.MAGIC_BLOCK);
        addDrop(ModBlocks.FLUORITE_ORE, oreDrops(ModBlocks.FLUORITE_ORE, ModItems.RAW_FLUORITE));
        addDrop(ModBlocks.FLUORITE_DEEPSLATE_ORE, multipleOreDrops(ModBlocks.FLUORITE_DEEPSLATE_ORE, ModItems.RAW_FLUORITE,2, 5));
        addDrop(ModBlocks.FLUORITE_END_ORE, multipleOreDrops(ModBlocks.FLUORITE_END_ORE, ModItems.RAW_FLUORITE, 4, 6));
        addDrop(ModBlocks.FLUORITE_NETHER_ORE, multipleOreDrops(ModBlocks.FLUORITE_NETHER_ORE, ModItems.RAW_FLUORITE, 3, 6));

        addDrop(ModBlocks.FLUORITE_STAIRS);
        addDrop(ModBlocks.FLUORITE_SLAB, slabDrops(ModBlocks.FLUORITE_SLAB));
        addDrop(ModBlocks.FLUORITE_BUTTON);
        addDrop(ModBlocks.FLUORITE_PRESSURE_PLATE);
        addDrop(ModBlocks.FLUORITE_FENCE);
        addDrop(ModBlocks.FLUORITE_FENCE_GATE);
        addDrop(ModBlocks.FLUORITE_TRAPDOOR);
        addDrop(ModBlocks.FLUORITE_DOOR, doorDrops(ModBlocks.FLUORITE_DOOR));
        addDrop(ModBlocks.MAGIC_BLOCK);
        addDrop(ModBlocks.FLUORITE_LAMP);
        addDrop(ModBlocks.COLORED_LEAVES);
        addDrop(ModBlocks.PEDESTAL);
        addDrop(ModBlocks.CRYSTALLIZER);

        addDrop(ModBlocks.BLACKWOOD_LOG);
        addDrop(ModBlocks.BLACKWOOD_WOOD);
        addDrop(ModBlocks.STRIPPED_BLACKWOOD_LOG);
        addDrop(ModBlocks.STRIPPED_BLACKWOOD_WOOD);
        addDrop(ModBlocks.BLACKWOOD_PLANKS);
        addDrop(ModBlocks.BLACKWOOD_SAPLING);
        addDrop(ModBlocks.BLACKWOOD_LEAVES, leavesDrops(ModBlocks.BLACKWOOD_LEAVES, ModBlocks.BLACKWOOD_SAPLING, 0.0625f));


        BlockStatePropertyLootCondition.Builder builder = BlockStatePropertyLootCondition.builder(ModBlocks.STRAWBERRY_CROP)
                .properties(StatePredicate.Builder.create().exactMatch(StrawberryCropBlock.AGE, 5));
        this.addDrop(ModBlocks.STRAWBERRY_CROP, this.cropDrops(ModBlocks.STRAWBERRY_CROP, ModItems.STRAWBERRY, ModItems.STRAWBERRY_SEEDS, builder));

        addDrop(ModBlocks.DAHLIA);
        addDrop(ModBlocks.POTTED_DAHLIA, pottedPlantDrops(ModBlocks.DAHLIA));
    }



    public LootTable.Builder multipleOreDrops(Block drop, Item item, float minDrops, float maxDrops) {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        return this.dropsWithSilkTouch(drop, this.applyExplosionDecay(drop, ((LeafEntry.Builder<?>)
                ItemEntry.builder(item).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(minDrops, maxDrops))))
                .apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))));
    }
}
