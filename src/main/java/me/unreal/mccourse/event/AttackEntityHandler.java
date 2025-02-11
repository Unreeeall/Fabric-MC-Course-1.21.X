package me.unreal.mccourse.event;

import me.unreal.mccourse.item.ModItems;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class AttackEntityHandler implements AttackEntityCallback {
    @Override
    public ActionResult interact(PlayerEntity playerEntity, World world, Hand hand, Entity entity, @Nullable EntityHitResult entityHitResult) {

        if (entity instanceof SheepEntity && !world.isClient()) {
            if (playerEntity.getMainHandStack().getItem() == ModItems.METAL_DETECTOR) {
                playerEntity.sendMessage(Text.literal(playerEntity.getName().getString() + " tried metal detecting a sheep!"));
            }

            if (playerEntity.getMainHandStack().getItem() == ModItems.STRAWBERRY) {
                playerEntity.sendMessage(Text.literal(playerEntity.getName().getString() + " attacked sheep with a Strawberry instead of feeding it!"));
                ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 100, 1 ,true, false));
                ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 100, 10 ,true, false));

                playerEntity.getMainHandStack().decrement(1);
            }
        }

        return ActionResult.PASS;
    }
}
