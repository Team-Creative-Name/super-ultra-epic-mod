package com.tcn.superultraepicmod.client;

import com.tcn.superultraepicmod.SuperUltraEpicMod;
import com.tcn.superultraepicmod.renderer.NetherBoatRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;


@Environment(EnvType.CLIENT)
public class SuperUltraEpicModClient implements ClientModInitializer
{
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(SuperUltraEpicMod.NETHER_BOAT_ENTITY, (NetherBoatRenderer::new));

    }
}
