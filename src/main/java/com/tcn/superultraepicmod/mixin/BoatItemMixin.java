package com.tcn.superultraepicmod.mixin;

import com.tcn.superultraepicmod.access.BoatAccess;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.BoatItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

@Mixin(BoatItem.class)
public class BoatItemMixin
{
    @Inject(
          method = "use",
          at = @At(
                value = "INVOKE",
                target = "Lnet/minecraft/world/entity/player/Player;getYRot()F"
          ),
          locals = LocalCapture.CAPTURE_FAILHARD
    )
    public void setEnchantment(Level level, Player player, InteractionHand interactionHand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir, ItemStack itemStack, HitResult hitResult, Vec3 vec3, double d, List<Entity> list, Boat boat) {
        ((BoatAccess) boat).setEnchantment(itemStack);
    }
}
