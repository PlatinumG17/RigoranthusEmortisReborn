package com.platinumg17.rigoranthusemortisreborn.canis.client.entity.render.layer;

import java.util.List;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.platinumg17.rigoranthusemortisreborn.canis.client.entity.model.CanisModel;
import com.platinumg17.rigoranthusemortisreborn.canis.client.entity.render.CollarRenderManager;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.CanisEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import com.platinumg17.rigoranthusemortisreborn.api.client.renderer.ISkillRenderer;
import com.platinumg17.rigoranthusemortisreborn.api.registry.SkillInstance;

public class CanisSkillLayer extends LayerRenderer<CanisEntity, CanisModel<CanisEntity>> {

    public CanisSkillLayer(IEntityRenderer<CanisEntity, CanisModel<CanisEntity>> rendererIn) {
        super(rendererIn);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, CanisEntity canisIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        List<SkillInstance> skills = canisIn.getSkillMap();

        for (SkillInstance inst : skills) {
            if (inst.level() > 0 && inst.hasRenderer()) {
                ISkillRenderer renderer = CollarRenderManager.getRendererFor(inst.getSkill());

                if (renderer != null) {
                    renderer.render(this, matrixStackIn, bufferIn, packedLightIn, canisIn, inst, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch);
                }
            }
        };
    }
}