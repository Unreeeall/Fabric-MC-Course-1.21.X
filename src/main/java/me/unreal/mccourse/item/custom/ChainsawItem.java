package me.unreal.mccourse.item.custom;

import me.unreal.mccourse.components.ModDataComponentTypes;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;
import java.util.Objects;

public class ChainsawItem extends Item {
    public ChainsawItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();

        if(!world.isClient()) {
            if (world.getBlockState(context.getBlockPos()).isIn(BlockTags.LOGS)) {
                world.breakBlock(context.getBlockPos(), true, context.getPlayer());

                context.getStack().damage(1, ((ServerWorld) world), ((ServerPlayerEntity) context.getPlayer()), item ->
                        Objects.requireNonNull(context.getPlayer()).sendEquipmentBreakStatus(item, EquipmentSlot.MAINHAND));

                context.getStack().set(ModDataComponentTypes.COORDINATES, context.getBlockPos());
            }
        }

        return ActionResult.CONSUME;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        ItemStack stack = user.getEquippedStack(EquipmentSlot.MAINHAND);

        if (stack.get(ModDataComponentTypes.COORDINATES) != null) {
            stack.set(ModDataComponentTypes.COORDINATES, null);
        }
        return TypedActionResult.success(stack);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if(!Screen.hasShiftDown()) {
          tooltip.add(Text.translatable("tooltip.mccourse.chainsaw.tooltip.shift"));
        } else {
            tooltip.add(Text.translatable("tooltip.mccourse.chainsaw.tooltip.1"));
            tooltip.add(Text.translatable("tooltip.mccourse.chainsaw.tooltip.2"));
        }

        if (stack.get(ModDataComponentTypes.COORDINATES) != null) {
            tooltip.add(Text.literal("Last Chopped Tree at " + stack.get(ModDataComponentTypes.COORDINATES)));
        }

        super.appendTooltip(stack, context, tooltip, type);
    }
}
