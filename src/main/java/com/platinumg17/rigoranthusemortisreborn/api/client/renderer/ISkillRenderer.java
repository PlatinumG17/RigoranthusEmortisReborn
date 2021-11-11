package com.platinumg17.rigoranthusemortisreborn.api.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import com.platinumg17.rigoranthusemortisreborn.api.interfaces.AbstractCanisEntity;
import com.platinumg17.rigoranthusemortisreborn.api.registry.SkillInstance;

public interface ISkillRenderer<T extends AbstractCanisEntity> {
    default void render(LayerRenderer<T, EntityModel<T>> layer, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T canis, SkillInstance inst, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
    }
}