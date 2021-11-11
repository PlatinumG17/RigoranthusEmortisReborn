package com.platinumg17.rigoranthusemortisreborn.canis.client.entity.render.layer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.platinumg17.rigoranthusemortisreborn.canis.client.entity.model.CanisModel;
import com.platinumg17.rigoranthusemortisreborn.canis.client.entity.render.CollarRenderManager;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.CanisEntity;
import com.platinumg17.rigoranthusemortisreborn.api.client.renderer.IAccoutrementRenderer;
import com.platinumg17.rigoranthusemortisreborn.api.registry.AccoutrementInstance;

import java.util.List;

public class CanisAccoutrementLayer extends LayerRenderer<CanisEntity, CanisModel<CanisEntity>> {

    public CanisAccoutrementLayer(IEntityRenderer<CanisEntity, CanisModel<CanisEntity>> rendererIn) {
        super(rendererIn);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, CanisEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        List<AccoutrementInstance> accouterments = entitylivingbaseIn.getAccouterments();

        for (AccoutrementInstance inst : accouterments) {
            IAccoutrementRenderer renderer = CollarRenderManager.getRendererFor(inst.getAccoutrement());

            if (renderer != null) {
                renderer.render(this, matrixStackIn, bufferIn, packedLightIn, entitylivingbaseIn, inst, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch);
            }
        };
    }
}
