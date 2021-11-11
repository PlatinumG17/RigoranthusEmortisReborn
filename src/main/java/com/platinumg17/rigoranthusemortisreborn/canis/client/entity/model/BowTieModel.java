package com.platinumg17.rigoranthusemortisreborn.canis.client.entity.model;

import com.google.common.collect.ImmutableList;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.CanisEntity;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class BowTieModel extends SegmentedModel<CanisEntity> {

    public ModelRenderer rightChest;
    public ModelRenderer middleChest;
    public ModelRenderer leftChest;

    public BowTieModel(float scaleFactor) {
        this.texHeight = 16;
        this.texWidth = 16;
        this.rightChest = new ModelRenderer(this, 52, 0);
        this.rightChest.addBox(-2.0F, 1.5F, -9.0F, 1, 2, 1, scaleFactor);
        this.rightChest.setPos(0.0F, 14.0F, 2.0F);
        this.middleChest = new ModelRenderer(this, 52, 0);
        this.middleChest.addBox(0.0F, 14.0F, -0.0F, 2, 1, 1, scaleFactor);
        this.middleChest.setPos(-1.0F, 2.0F, -9.0F);//0.0F, 14.0F, 2.0F);
        this.leftChest = new ModelRenderer(this, 52, 0);
        this.leftChest.addBox(1.0F, 1.5F, -9.0F, 1, 2, 1, scaleFactor);
        this.leftChest.setPos(0.0F, 14.0F, 2.0F);
    }

    @Override
    public Iterable<ModelRenderer> parts() {
        return ImmutableList.of(this.rightChest, this.middleChest, this.leftChest);
    }

    @Override
    public void prepareMobModel(CanisEntity canisIn, float limbSwing, float limbSwingAmount, float partialTickTime) {
        this.rightChest.xRot += 0.1;
        this.middleChest.xRot += 0.1;
        this.leftChest.xRot += 0.1;
        this.rightChest.zRot = canisIn.getShakeAngle(partialTickTime, -0.16F);
        this.leftChest.zRot = this.rightChest.zRot;
    }

    @Override
    public void setupAnim(CanisEntity canisIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }
}
