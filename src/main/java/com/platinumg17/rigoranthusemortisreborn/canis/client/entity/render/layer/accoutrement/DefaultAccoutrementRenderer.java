package com.platinumg17.rigoranthusemortisreborn.canis.client.entity.render.layer.accoutrement;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.CanisEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import com.platinumg17.rigoranthusemortisreborn.api.client.renderer.IAccoutrementRenderer;
import com.platinumg17.rigoranthusemortisreborn.api.interfaces.AbstractCanisEntity;
import com.platinumg17.rigoranthusemortisreborn.api.registry.AccoutrementInstance;

public class DefaultAccoutrementRenderer implements IAccoutrementRenderer<CanisEntity> {
    private ResourceLocation texture;
    public DefaultAccoutrementRenderer(ResourceLocation textureIn) {
        this.texture = textureIn;
    }

    @Override
    public void render(LayerRenderer<CanisEntity, EntityModel<CanisEntity>> layer, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, CanisEntity canis, AccoutrementInstance data, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (canis.isTame() && !canis.isInvisible()) {
            LayerRenderer.renderColoredCutoutModel(layer.getParentModel(), this.getTexture(canis, data), matrixStackIn, bufferIn, packedLightIn, canis, 1.0f, 1.0f, 1.0f);
        }
    }

    public <T extends AbstractCanisEntity> ResourceLocation getTexture(T canis, AccoutrementInstance data) {
        return this.texture;
    }
}
