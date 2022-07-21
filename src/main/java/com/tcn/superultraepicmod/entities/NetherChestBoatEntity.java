package com.tcn.superultraepicmod.entities;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.level.Level;

public class NetherChestBoatEntity extends ChestBoat {
    public NetherChestBoatEntity(EntityType<? extends Boat> entityType, Level level) {
        super(entityType, level);
    }

    public NetherChestBoatEntity(Level level, double d, double e, double f) {
        super(level, d, e, f);
    }
}
