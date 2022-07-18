package com.tcn.superultraepicmod.items;

import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.BoatItem;

public class NetherBoatItem extends BoatItem {
    public NetherBoatItem(boolean bl, Boat.Type type, Properties properties) {
        super(bl, type, properties);
        this.getMaxStackSize();
    }
}
