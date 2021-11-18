package com.platinumg17.rigoranthusemortisreborn.canis.client.entity.render.layer.accoutrement;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.platinumg17.rigoranthusemortisreborn.canis.client.entity.model.CanisModel;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.CanisEntity;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.accouterments.DyeableAccoutrement.DyeableAccoutrementInstance;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.client.IAccoutrementRenderer;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.registry.AccoutrementInstance;

public class LeatherArmorAccoutrementRenderer implements IAccoutrementRenderer<CanisEntity> {

    private final EntityModel<CanisEntity> model;
    private ResourceLocation texture;

    public LeatherArmorAccoutrementRenderer(ResourceLocation textureIn) {
        this.model = new CanisModel<>(0.4F);
        this.texture = textureIn;
    }

    @Override
    public void render(LayerRenderer<CanisEntity, EntityModel<CanisEntity>> layer, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, CanisEntity canis, AccoutrementInstance data, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (canis.isTame() && !canis.isInvisible()) {
            float[] color = data.cast(DyeableAccoutrementInstance.class).getFloatArray();

            layer.getParentModel().copyPropertiesTo(this.model);
            this.model.prepareMobModel(canis, limbSwing, limbSwingAmount, partialTicks);
            this.model.setupAnim(canis, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

            LayerRenderer.renderColoredCutoutModel(this.model, this.getTexture(canis, data), matrixStackIn, bufferIn, packedLightIn, canis, color[0], color[1], color[2]);
        }
    }

    public ResourceLocation getTexture(CanisEntity canis, AccoutrementInstance data) {
        return this.texture;
    }
}
