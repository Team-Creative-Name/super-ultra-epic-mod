package com.tcn.superultraepicmod;

import com.tcn.superultraepicmod.items.NetherBoatItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class SuperUltraEpicMod implements ModInitializer
{
    public static final String MOD_ID = "super-ultra-epic-mod";
    @Override
    public void onInitialize() {
        //item registry
        Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "crimson_boat"), CRIMSON_BOAT);
        Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "warped_boat"), WARPED_BOAT);
        Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "flesh_block_drop"), new BlockItem(ROTTEN_FLESH_BLOCK, new FabricItemSettings().group(CREATIVE_TAB)));
        //block registry
        Registry.register(Registry.BLOCK, new ResourceLocation(MOD_ID, "flesh_block"), ROTTEN_FLESH_BLOCK);
    }

    //Creative tabs
    public static final CreativeModeTab CREATIVE_TAB = FabricItemGroupBuilder.build(
            new ResourceLocation(MOD_ID, "creative_tab"),
            () -> new ItemStack(Blocks.TURTLE_EGG) //TODO: find a better block for this
    );

    //item instantiations
    public static final Item CRIMSON_BOAT = new NetherBoatItem(false, Boat.Type.MANGROVE, new FabricItemSettings().group(CREATIVE_TAB).fireResistant());
    public static final Item WARPED_BOAT = new NetherBoatItem(false, Boat.Type.JUNGLE, new FabricItemSettings().group(CREATIVE_TAB).fireResistant());

    //block instantiation
    public static final Block ROTTEN_FLESH_BLOCK = new Block(FabricBlockSettings.of(Material.WOOD).strength(0.6f).sound(SoundType.SLIME_BLOCK));
}
