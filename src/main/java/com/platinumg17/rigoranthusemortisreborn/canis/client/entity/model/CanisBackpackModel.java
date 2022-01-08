package com.platinumg17.rigoranthusemortisreborn.canis.client.entity.model;

import com.google.common.collect.ImmutableList;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.CanisEntity;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class CanisBackpackModel extends SegmentedModel<CanisEntity> {

    public ModelRenderer satchel;
    public ModelRenderer right_satchel;
    public ModelRenderer left_satchel;

    public CanisBackpackModel(float scaleFactor) {
        texWidth = 32;
        texHeight = 32;

        satchel = new ModelRenderer(this);
        satchel.setPos(-0.075F, -2.5F, 5.5F);
        setRotationAngle(satchel, -0.3054F, 0.0F, 0.0F);
        satchel.texOffs(0, 0).addBox(-6.775F, -0.25F, -0.5F, 13.0F, 1.0F, 2.0F, scaleFactor);
        satchel.texOffs(11, 3).addBox(-1.625F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, scaleFactor);
            right_satchel = new ModelRenderer(this);
            right_satchel.setPos(-7.925F, 18.5F, 3.5F);
            satchel.addChild(right_satchel);
            right_satchel.texOffs(11, 20).addBox(-0.05F, -17.85F, -6.5F, 2.0F, 5.0F, 7.0F, scaleFactor);
            right_satchel.texOffs(0, 15).addBox(0.05F, -17.75F, -6.5F, 2.0F, 5.0F, 7.0F, scaleFactor);
        left_satchel = new ModelRenderer(this);
        left_satchel.setPos(-7.925F, 18.5F, 3.5F);
        satchel.addChild(left_satchel);
        left_satchel.texOffs(11, 8).addBox(13.9F, -17.85F, -6.5F, 2.0F, 5.0F, 7.0F, scaleFactor);
        left_satchel.texOffs(0, 3).addBox(14.0F, -17.75F, -6.5F, 2.0F, 5.0F, 7.0F, scaleFactor);
    }

    @Override
    public Iterable<ModelRenderer> parts() {
        return ImmutableList.of(this.satchel);
    }

    @Override
    public void prepareMobModel(CanisEntity canisIn, float limbSwing, float limbSwingAmount, float partialTickTime) {
        if (canisIn.isInSittingPose()) {
//            if (canisIn.isLying()) {
//            } else {
            satchel.setPos(-0.075F, 3.25F, 7.5F);
            setRotationAngle(satchel, -0.6545F, 0.0F, 0.0F);
            satchel.texOffs(0, 0).addBox(-6.775F, -0.25F, -0.5F, 13.0F, 1.0F, 2.0F, 0.0F, false);
            satchel.texOffs(11, 3).addBox(-1.625F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, 0.0F, false);
                right_satchel.setPos(-7.925F, 18.5F, 3.5F);
                right_satchel.texOffs(11, 20).addBox(-0.05F, -17.85F, -6.5F, 2.0F, 5.0F, 7.0F, 0.0F, false);
                right_satchel.texOffs(0, 15).addBox(0.05F, -17.75F, -6.5F, 2.0F, 5.0F, 7.0F, 0.0F, false);
            left_satchel.setPos(-7.925F, 18.5F, 3.5F);
            left_satchel.texOffs(11, 8).addBox(13.9F, -17.85F, -6.5F, 2.0F, 5.0F, 7.0F, 0.0F, false);
            left_satchel.texOffs(0, 3).addBox(14.0F, -17.75F, -6.5F, 2.0F, 5.0F, 7.0F, 0.0F, false);
//            }
        }
        else {
            satchel.setPos(-0.075F, -2.5F, 5.5F);
            setRotationAngle(satchel, -0.3054F, 0.0F, 0.0F);
            satchel.texOffs(0, 0).addBox(-6.775F, -0.25F, -0.5F, 13.0F, 1.0F, 2.0F, 0.0F, false);
            satchel.texOffs(11, 3).addBox(-1.625F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, 0.0F, false);
                right_satchel.setPos(-7.925F, 18.5F, 3.5F);
                right_satchel.texOffs(11, 20).addBox(-0.05F, -17.85F, -6.5F, 2.0F, 5.0F, 7.0F, 0.0F, false);
                right_satchel.texOffs(0, 15).addBox(0.05F, -17.75F, -6.5F, 2.0F, 5.0F, 7.0F, 0.0F, false);
            left_satchel.setPos(-7.925F, 18.5F, 3.5F);
            left_satchel.texOffs(11, 8).addBox(13.9F, -17.85F, -6.5F, 2.0F, 5.0F, 7.0F, 0.0F, false);
            left_satchel.texOffs(0, 3).addBox(14.0F, -17.75F, -6.5F, 2.0F, 5.0F, 7.0F, 0.0F, false);
        }
        this.satchel.zRot = canisIn.getShakeAngle(partialTickTime, -0.16F);
//        this.right_satchel.zRot = this.satchel.zRot;
//        this.left_satchel.zRot = this.satchel.zRot;
    }
    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
    @Override
    public void setupAnim(CanisEntity canisIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }
}
