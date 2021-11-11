package com.platinumg17.rigoranthusemortisreborn.canis.client.entity.model;

import com.google.common.collect.ImmutableList;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.CanisEntity;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class CanisSaviorModel extends SegmentedModel<CanisEntity> {

    public ModelRenderer saviorBox;

    public CanisSaviorModel() {
        this.saviorBox = new ModelRenderer(this, 0, 0);
        this.saviorBox.addBox(-1F, -4F, -4.5F, 4, 2, 2);
        this.saviorBox.setPos(-1F, 14F, -3F);
        this.saviorBox.xRot = (float) (Math.PI / 2);
    }

    @Override
    public Iterable<ModelRenderer> parts() {
        return ImmutableList.of(this.saviorBox);
    }

    @Override
    public void prepareMobModel(CanisEntity canisIn, float limbSwing, float limbSwingAmount, float partialTickTime) {
        if (canisIn.isInSittingPose()) { // Mapping is wrong isEntitySleeping should be isSitting
            if (canisIn.isLying()) {
                this.saviorBox.setPos(-1F, 20F, -2F);
                this.saviorBox.xRot = (float) (Math.PI / 2);
            }
            else {
                this.saviorBox.setPos(-1, 16, -3);
                this.saviorBox.xRot = (float) (Math.PI * 2 / 5);
            }
        }
        else {
            this.saviorBox.setPos(-1F, 14F, -3F);
            this.saviorBox.xRot = (float) (Math.PI / 2);
        }
    }

    @Override
    public void setupAnim(CanisEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }
}
