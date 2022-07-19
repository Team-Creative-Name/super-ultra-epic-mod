package com.tcn.superultraepicmod.mixin;

import com.tcn.superultraepicmod.access.BoatAccess;
import com.tcn.superultraepicmod.mixin.accessor.EntityAccessorMixin;
import com.tcn.superultraepicmod.util.FrostWalker;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.include.com.google.common.base.Objects;

@Mixin(Boat.class)
public abstract class BoatMixin implements EntityAccessorMixin, BoatAccess
{
    private static final EntityDataAccessor<Byte> ID_FROST_WALKER = SynchedEntityData.defineId(Boat.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Boolean> ID_FOIL = SynchedEntityData.defineId(Boat.class, EntityDataSerializers.BOOLEAN);

    private BlockPos lastPos;

    @Inject(
          method = "defineSynchedData",
          at = @At("TAIL")
    )
    public void defineExtraEntityData(CallbackInfo ci) {
        this.accessorGetEntityData().define(ID_FROST_WALKER, (byte) 0);
        this.accessorGetEntityData().define(ID_FOIL, false);
    }

    @Inject(
          method = "addAdditionalSaveData",
          at = @At("TAIL")
    )
    public void addEnchantmentSaveData(CompoundTag compoundTag, CallbackInfo ci) {
        compoundTag.putByte("FrostWalker", this.accessorGetEntityData().get(ID_FROST_WALKER));
    }

    @Inject(
          method = "readAdditionalSaveData",
          at = @At("TAIL")
    )
    public void readEnchantmentSaveData(CompoundTag compoundTag, CallbackInfo ci) {
        if (compoundTag.contains("FrostWalker", Tag.TAG_BYTE)) {
            var level = compoundTag.getByte("FrostWalker");
            this.accessorGetEntityData().set(ID_FROST_WALKER, level);
            this.accessorGetEntityData().set(ID_FOIL, level > 0);
        }
    }

    @Redirect(
          method = "destroy",
          at = @At(
                value = "INVOKE",
                target = "Lnet/minecraft/world/entity/vehicle/Boat;spawnAtLocation(Lnet/minecraft/world/level/ItemLike;)Lnet/minecraft/world/entity/item/ItemEntity;"
          )
    )
    public ItemEntity redirectSpawnAtLocation(Boat instance, ItemLike itemLike) {
        return instance.spawnAtLocation(this.getEnchantedStack(itemLike));
    }

    @Redirect(
          method = "getPickResult",
          at = @At(
                value = "NEW",
                target = "net/minecraft/world/item/ItemStack"
          )
    )
    public ItemStack redirectItemStack(ItemLike itemLike) {
        return this.getEnchantedStack(itemLike);
    }

    @Inject(
          method = "tick",
          at = @At(
                value = "INVOKE",
                target = "Lnet/minecraft/world/entity/vehicle/Boat;tickLerp()V"
          )
    )
    public void tickFrostWalker(CallbackInfo ci) {
        if (!this.accessorGetLevel().isClientSide) {
            BlockPos blockPos = this.invokerBlockPosition();
            if (!Objects.equal(blockPos, this.lastPos)) {
                this.lastPos = blockPos;
                int i = this.accessorGetEntityData().get(ID_FROST_WALKER);
                if (i > 0) {
                    FrostWalker.onEntityMovedNonLiving((Boat) (Object) this, this.accessorGetLevel(), blockPos, i);
                }
            }
        }
    }

    @Override
    public boolean isFoil() {
        return this.accessorGetEntityData().get(ID_FOIL);
    }

    @Override
    public void setEnchantment(ItemStack stack) {
        int level = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FROST_WALKER, stack);
        this.accessorGetEntityData().set(ID_FROST_WALKER, (byte) level);
        this.accessorGetEntityData().set(ID_FOIL, level > 0);
    }

    private ItemStack getEnchantedStack(ItemLike itemLike) {
        ItemStack itemStack = new ItemStack(itemLike);
        if (this.isFoil()) {
            itemStack.enchant(Enchantments.FROST_WALKER, this.accessorGetEntityData().get(ID_FROST_WALKER));
        }
        return itemStack;
    }
}
