package com.tcn.superultraepicmod.mixin.accessor;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Entity.class)
public interface EntityAccessorMixin
{
    @Accessor("entityData")
    SynchedEntityData accessorGetEntityData();

    @Accessor("level")
    Level accessorGetLevel();

    @Invoker("blockPosition")
    BlockPos invokerBlockPosition();
}
