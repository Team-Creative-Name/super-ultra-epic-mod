package com.tcn.superultraepicmod.mixin;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.tcn.superultraepicmod.access.BoatAccess;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.entity.vehicle.Boat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BoatRenderer.class)
public class BoatRendererMixin
{
    @Redirect(
          method = "render(Lnet/minecraft/world/entity/vehicle/Boat;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
          at = @At(
                value = "INVOKE",
                target = "Lnet/minecraft/client/renderer/MultiBufferSource;getBuffer(Lnet/minecraft/client/renderer/RenderType;)Lcom/mojang/blaze3d/vertex/VertexConsumer;",
                ordinal = 0
          )
    )
    private VertexConsumer addFoil(MultiBufferSource instance, RenderType renderType, Boat boat) {
        return ItemRenderer.getFoilBufferDirect(instance, renderType, false, ((BoatAccess) boat).isFoil());
    }
}
