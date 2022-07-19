package com.tcn.superultraepicmod.mixin;

import com.tcn.superultraepicmod.access.BoatAccess;
import com.tcn.superultraepicmod.mixin.accessor.EntityAccessorMixin;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Boat.class)
public abstract class BoatMixin implements EntityAccessorMixin, BoatAccess
{
    private static final EntityDataAccessor<Byte> ID_FROST_WALKER = SynchedEntityData.defineId(Boat.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Boolean> ID_FOIL = SynchedEntityData.defineId(Boat.class, EntityDataSerializers.BOOLEAN);

    @Inject(
          method = "<init>(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/level/Level;)V",
          at = @At("TAIL")
    )
    public void addExtraEntityData(CallbackInfo ci) {
        this.accessorGetEntityData().set(ID_FOIL, true);
        this.accessorGetEntityData().set(ID_FROST_WALKER, (byte) 3);
    }

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
            var frostWalker = compoundTag.getByte("FrostWalker");
            this.accessorGetEntityData().set(ID_FROST_WALKER, frostWalker);
            this.accessorGetEntityData().set(ID_FOIL, frostWalker > 0);
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

    @Override
    public boolean isFoil() {
        return this.accessorGetEntityData().get(ID_FOIL);
    }

    private ItemStack getEnchantedStack(ItemLike itemLike) {
        ItemStack itemStack = new ItemStack(itemLike);
        if (this.isFoil()) {
            itemStack.enchant(Enchantments.FROST_WALKER, this.accessorGetEntityData().get(ID_FROST_WALKER));
        }
        return itemStack;
    }
}
