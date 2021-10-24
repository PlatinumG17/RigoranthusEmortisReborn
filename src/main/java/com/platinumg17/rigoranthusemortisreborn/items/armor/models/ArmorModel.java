package com.platinumg17.rigoranthusemortisreborn.items.armor.models;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;

public class ArmorModel <T extends LivingEntity> extends BipedModel<T> {
    protected final EquipmentSlotType slotType;
    protected ModelRenderer Body;

    public ArmorModel(float modelSize, EquipmentSlotType slotType) {
        super(modelSize, 0, 32, 32);
        this.slotType = slotType;
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        matrixStack.pushPose();

        if (this.slotType == EquipmentSlotType.CHEST) {
            Body.copyFrom(this.Body);
            Body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
            matrixStack.popPose();
        }
    }
}