package com.tcn.superultraepicmod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;

public class SuperUltraEpicMod implements ModInitializer
{
    @Override
    public void onInitialize() {

    }

    public static final CreativeModeTab CREATIVE_TAB = FabricItemGroupBuilder.build(
            new ResourceLocation("super-ultra-epic-mod", "creative_tab"),
            () -> new ItemStack(Blocks.TURTLE_EGG) //TODO: find a better block for this
    );
}
