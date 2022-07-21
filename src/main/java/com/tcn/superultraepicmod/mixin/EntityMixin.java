package com.tcn.superultraepicmod.mixin;

import com.tcn.superultraepicmod.access.EntityAccess;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Entity.class)
public class EntityMixin implements EntityAccess
{

    @Shadow
    @Final
    protected RandomSource random;

    @Override
    public RandomSource getRandomSource() {
        return this.random;
    }
}
