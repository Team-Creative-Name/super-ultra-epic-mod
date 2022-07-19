package com.tcn.superultraepicmod;

import com.tcn.superultraepicmod.items.NetherBoatItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;

public class SuperUltraEpicMod implements ModInitializer
{
    public static final String MOD_ID = "super-ultra-epic-mod";
    @Override
    public void onInitialize() {
        Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "crimson_boat"), CRIMSON_BOAT);
        Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "warped_boat"), WARPED_BOAT);
    }

    //Creative tabs
    public static final CreativeModeTab CREATIVE_TAB = FabricItemGroupBuilder.build(
            new ResourceLocation(MOD_ID, "creative_tab"),
            () -> new ItemStack(Blocks.TURTLE_EGG) //TODO: find a better block for this
    );

    //item instantiations
    public static final Item CRIMSON_BOAT = new NetherBoatItem(false, Boat.Type.MANGROVE, new FabricItemSettings().group(CREATIVE_TAB).fireResistant().stacksTo(1));
    public static final Item WARPED_BOAT = new NetherBoatItem(false, Boat.Type.JUNGLE, new FabricItemSettings().group(CREATIVE_TAB).fireResistant().stacksTo(1));

    //block instantiation
}
