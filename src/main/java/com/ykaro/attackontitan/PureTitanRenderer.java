package com.ykaro.attackontitan;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class PureTitanRenderer extends GeoEntityRenderer<PureTitanEntity> {
    public PureTitanRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new PureTitanModel());
    }

    @Override
    public void render(PureTitanEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.pushPose();

        // Multiplica o tamanho do modelo para preencher a hitbox alta
        poseStack.scale(6.0f, 6.0f, 6.0f);

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);

        poseStack.popPose();
    }
}