package com.platinumg17.rigoranthusemortisreborn.canis.client.entity.render.layer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.platinumg17.rigoranthusemortisreborn.canis.client.entity.model.CanisSaviorModel;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.Resources;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.CanisEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.client.ISkillRenderer;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.registry.SkillInstance;

public class SaviorCanisRenderer implements ISkillRenderer<CanisEntity> {

    private final EntityModel<CanisEntity> model;

    public SaviorCanisRenderer() {
        this.model = new CanisSaviorModel();
    }

    @Override
    public void render(LayerRenderer<CanisEntity, EntityModel<CanisEntity>> layer, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, CanisEntity canisIn, SkillInstance inst, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {

        if (!canisIn.isInvisible() && inst.level() >= 5) {
            layer.getParentModel().copyPropertiesTo(this.model);
            this.model.prepareMobModel(canisIn, limbSwing, limbSwingAmount, partialTicks);
            this.model.setupAnim(canisIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

            LayerRenderer.renderColoredCutoutModel(this.model, Resources.SKILL_SAVIOR, matrixStackIn, bufferIn, packedLightIn, canisIn, 1.0F, 1.0F, 1.0F);
        }
    }
}
