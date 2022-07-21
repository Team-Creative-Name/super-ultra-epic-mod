package com.tcn.superultraepicmod.entities;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.level.Level;

public class NetherBoatEntity extends Boat {
    public NetherBoatEntity(EntityType<? extends Boat> entityType, Level level) {
        super(entityType, level);
    }

    public NetherBoatEntity(Level level, double d, double e, double f) {
        super(level, d, e, f);
    }

}
