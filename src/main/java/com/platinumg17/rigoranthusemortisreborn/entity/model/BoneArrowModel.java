package com.platinumg17.rigoranthusemortisreborn.entity.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BoneArrowModel<T extends Entity> extends SegmentedModel<T> {
    private final ModelRenderer main = new ModelRenderer(this);

    public BoneArrowModel() {
        this(0.0F);
    }

    public BoneArrowModel(float size) {
        this.main.texOffs(0, 0).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, size);
        this.main.setPos(0.0F, 0.0F, 0.0F);
    }

    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    public Iterable<ModelRenderer> parts() {
        return ImmutableList.of(this.main);
    }
}






//        //this.main.texOffs(0, 0).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, size);
//        this.main.setPos(-0.25F, 10.7071F, -0.1857F);
//
//        ModelRenderer bone_r1 = new ModelRenderer(this);
//        this.main.addChild(bone_r1);
//        bone_r1.setPos(0.25F, 0.25F, -1.0F);
//        bone_r1.texOffs(0, 0).addBox(7.0F, 0.0F, -3.5F, 14.0F, 0.0F, 7.0F, size);
//        setRotationAngle(bone_r1, 0.0F, 1.5708F, -1.5708F);
//
//
//        ModelRenderer bone_r2 = new ModelRenderer(this);
//        this.main.addChild(boner_r2);
//        bone_r2.setPos(-0.25F, -0.25F, 1.0F);
//        bone_r2.texOffs(0, 7).addBox(-5.0F, 0.5F, -3.0F, 10.0F, 0.0F, 7.0F, size);
//        setRotationAngle(bone_r2, 0.0F, 1.5708F, 0.0F);
//    }
//
//    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
//    }
//    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
//        modelRenderer.xRot = x;
//        modelRenderer.yRot = y;
//        modelRenderer.zRot = z;
//    }
//    public Iterable<ModelRenderer> parts() {
//        return ImmutableList.of(this.main);
//    }
//}