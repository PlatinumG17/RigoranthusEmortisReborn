package com.platinumg17.rigoranthusemortisreborn.canis.client.entity.model;

import com.google.common.collect.ImmutableList;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.CanisEntity;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class CanisBackpackModel extends SegmentedModel<CanisEntity> {

    public ModelRenderer rightChest;
    public ModelRenderer leftChest;

    public CanisBackpackModel(float scaleFactor) {
        this.rightChest = new ModelRenderer(this, 52, 0);
        this.rightChest.addBox(2.0F, -1F, 0F, 2, 7, 4, scaleFactor);
        this.rightChest.setPos(0.0F, 14.0F, 2.0F);
        this.leftChest = new ModelRenderer(this, 52, 0);
        this.leftChest.addBox(-4.0F, -1F, 0F, 2, 7, 4, scaleFactor);
        this.leftChest.setPos(0.0F, 14.0F, 2.0F);
    }

    @Override
    public Iterable<ModelRenderer> parts() {
        return ImmutableList.of(this.rightChest, this.leftChest);
    }

    @Override
    public void prepareMobModel(CanisEntity canisIn, float limbSwing, float limbSwingAmount, float partialTickTime) {
        if (canisIn.isInSittingPose()) { // Mapping is wrong isEntitySleeping should be isSitting
            if (canisIn.isLying()) {
                this.rightChest.setPos(0.0F, 20.0F, 2.0F);
                this.rightChest.xRot = ((float)Math.PI / 2F);
                this.leftChest.setPos(0.0F, 20.0F, 2.0F);
                this.leftChest.xRot = ((float)Math.PI / 2F);
            } else {
                this.rightChest.setPos(0.0F, 18.0F, 0.0F);
                this.rightChest.xRot = ((float)Math.PI / 4F);
                this.leftChest.setPos(0.0F, 18.0F, 0.0F);
                this.leftChest.xRot = ((float)Math.PI / 4F);
            }
        }
        else {
            this.rightChest.setPos(0.0F, 14.0F, 2.0F);
            this.rightChest.xRot = ((float)Math.PI / 2F);
            this.leftChest.setPos(0.0F, 14.0F, 2.0F);
            this.leftChest.xRot = ((float)Math.PI / 2F);
        }
        this.rightChest.zRot = canisIn.getShakeAngle(partialTickTime, -0.16F);
        this.leftChest.zRot = this.rightChest.zRot;
    }

    @Override
    public void setupAnim(CanisEntity canisIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }
}
