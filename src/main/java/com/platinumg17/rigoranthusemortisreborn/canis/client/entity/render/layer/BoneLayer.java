package com.platinumg17.rigoranthusemortisreborn.canis.client.entity.render.layer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.platinumg17.rigoranthusemortisreborn.canis.client.entity.model.CanisModel;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.CanisEntity;
import com.platinumg17.rigoranthusemortisreborn.canis.client.entity.render.CanisRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.util.math.vector.Vector3f;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.interfaces.IThrowableItem;

public class BoneLayer extends LayerRenderer<CanisEntity, CanisModel<CanisEntity>> {

    public BoneLayer(CanisRenderer canisRendererIn) {
        super(canisRendererIn);
    }

    @Override
    public void render(MatrixStack matrixStack, IRenderTypeBuffer bufferSource, int packedLight, CanisEntity canis, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (canis.hasBone()) {

            matrixStack.pushPose();
            CanisModel<CanisEntity> model = this.getParentModel();
//            if (model.young) { matrixStack.translate(0.0F, 5.0F / 16.0F, 2.0F / 16.0F); }
            model.whole_head.translateAndRotate(matrixStack);
            matrixStack.translate(-0.11F, 0.2F, -0.67F);
            matrixStack.mulPose(Vector3f.YP.rotationDegrees(45.0F));
            matrixStack.mulPose(Vector3f.XP.rotationDegrees(95.0F));

            IThrowableItem throwableItem = canis.getThrowableItem();
            Minecraft.getInstance().getItemInHandRenderer().renderItem(canis, throwableItem != null ? throwableItem.getRenderStack(canis.getBoneVariant()) : canis.getBoneVariant(), ItemCameraTransforms.TransformType.GROUND, false, matrixStack, bufferSource, packedLight);
            matrixStack.popPose();
        }
    }
}