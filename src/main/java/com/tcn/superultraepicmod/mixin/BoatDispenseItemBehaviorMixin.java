package com.tcn.superultraepicmod.mixin;

import com.tcn.superultraepicmod.access.BoatAccess;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.BoatDispenseItemBehavior;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(BoatDispenseItemBehavior.class)
public class BoatDispenseItemBehaviorMixin
{
    @Inject(
          method = "execute",
          at = @At(
                value = "INVOKE",
                target = "Lnet/minecraft/world/entity/vehicle/Boat;setYRot(F)V"
          ),
          locals = LocalCapture.CAPTURE_FAILHARD
    )
    public void setEnchantment(BlockSource blockSource, ItemStack itemStack, CallbackInfoReturnable<ItemStack> cir, Direction direction, Level level, double d, double e, double f, BlockPos blockPos, double g, Boat boat) {
        ((BoatAccess) boat).setEnchantment(itemStack);
    }
}
