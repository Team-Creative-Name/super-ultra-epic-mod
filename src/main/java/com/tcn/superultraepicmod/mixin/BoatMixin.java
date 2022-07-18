package com.tcn.superultraepicmod.mixin;

import com.tcn.superultraepicmod.access.BoatAccess;
import com.tcn.superultraepicmod.mixin.accessor.EntityAccessorMixin;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.vehicle.Boat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Boat.class)
public abstract class BoatMixin implements EntityAccessorMixin, BoatAccess
{
    private static final EntityDataAccessor<Boolean> ID_FOIL = SynchedEntityData.defineId(Boat.class, EntityDataSerializers.BOOLEAN);

    @Inject(
          method = "<init>(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/level/Level;)V",
          at = @At("TAIL")
    )
    public void addFoil(CallbackInfo ci) {
        this.accessorGetEntityData().set(ID_FOIL, true);
    }

    @Inject(
          method = "defineSynchedData",
          at = @At("TAIL")
    )
    public void defineFoil(CallbackInfo ci) {
        this.accessorGetEntityData().define(ID_FOIL, false);
    }

    @Override
    public boolean isFoil() {
        return this.accessorGetEntityData().get(ID_FOIL);
    }
}
