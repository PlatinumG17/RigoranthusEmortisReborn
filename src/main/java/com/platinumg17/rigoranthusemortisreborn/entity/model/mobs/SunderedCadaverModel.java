package com.platinumg17.rigoranthusemortisreborn.entity.model.mobs;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.platinumg17.rigoranthusemortisreborn.entity.mobs.SunderedCadaverEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

//public class SunderedCadaverModel extends EntityModel<SunderedCadaverEntity> {
//    private final ModelRenderer head;
//    private final ModelRenderer bone_r1;
//    private final ModelRenderer eyes;
//    private final ModelRenderer jaw;
//    private final ModelRenderer bone_r2;
//    private final ModelRenderer bone_r2_r1;
//    private final ModelRenderer body;
//    private final ModelRenderer bone_r3;
//    private final ModelRenderer bone_r3_r1;
//    private final ModelRenderer bone_r4;
//    private final ModelRenderer left_arm;
//    private final ModelRenderer bone_r5;
//    private final ModelRenderer right_arm;
//    private final ModelRenderer bone_r6;
//
//    public SunderedCadaverModel() {
//        texWidth = 64;
//        texHeight = 64;
//
//        head = new ModelRenderer(this);
//        head.setPos(0.0F, 14.0475F, -4.0298F);
//        head.texOffs(0, 0).addBox(-4.0F, -7.2975F, -6.9702F, 8.0F, 7.0F, 8.0F, 0.0F, false);
//
//        bone_r1 = new ModelRenderer(this);
//        bone_r1.setPos(0.1194F, -0.6592F, -1.0637F);
//        head.addChild(bone_r1);
//        setRotationAngle(bone_r1, -1.4835F, 0.0F, 0.0F);
//        bone_r1.texOffs(24, 0).addBox(-3.1194F, -0.6383F, -1.1565F, 6.0F, 3.0F, 4.0F, 0.0F, false);
//
//        eyes = new ModelRenderer(this);
//        eyes.setPos(-0.3806F, -3.2911F, -6.6378F);
//        head.addChild(eyes);
//        eyes.texOffs(0, 1).addBox(-2.1194F, -0.5064F, -0.5824F, 1.0F, 1.0F, 1.0F, 0.0F, false);
//        eyes.texOffs(0, 1).addBox(1.8806F, -0.5064F, -0.5824F, 1.0F, 1.0F, 1.0F, 0.0F, false);
//
//        jaw = new ModelRenderer(this);
//        jaw.setPos(0.1194F, -0.2331F, -1.7378F);
//        head.addChild(jaw);
//        setRotationAngle(jaw, 0.0872F, 0.0F, 0.0F);
//
//        bone_r2 = new ModelRenderer(this);
//        bone_r2.setPos(0.0F, 0.4645F, -0.9442F);
//        jaw.addChild(bone_r2);
//        setRotationAngle(bone_r2, 0.3054F, 0.0F, 0.0F);
//
//        bone_r2_r1 = new ModelRenderer(this);
//        bone_r2_r1.setPos(-0.1194F, 0.2211F, 1.2118F);
//        bone_r2.addChild(bone_r2_r1);
//        setRotationAngle(bone_r2_r1, -0.0436F, 0.0F, 0.0F);
//        bone_r2_r1.texOffs(27, 10).addBox(-3.5F, -0.25F, -5.5F, 7.0F, 2.0F, 6.0F, 0.0F, false);
//
//        body = new ModelRenderer(this);
//        body.setPos(-1.65F, 20.5205F, 4.4413F);
//
//        bone_r3 = new ModelRenderer(this);
//        bone_r3.setPos(-0.25F, -1.5231F, -5.5411F);
//        body.addChild(bone_r3);
//        setRotationAngle(bone_r3, 0.9599F, 0.0F, 0.0F);
//
//        bone_r3_r1 = new ModelRenderer(this);
//        bone_r3_r1.setPos(1.9F, 5.0026F, 1.0998F);
//        bone_r3.addChild(bone_r3_r1);
//        setRotationAngle(bone_r3_r1, -0.0873F, 0.0F, 0.0F);
//        bone_r3_r1.texOffs(0, 16).addBox(-4.0F, -11.0F, -3.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);
//
//        bone_r4 = new ModelRenderer(this);
//        bone_r4.setPos(0.25F, 1.523F, 1.5411F);
//        body.addChild(bone_r4);
//        setRotationAngle(bone_r4, 1.5708F, 0.0F, 0.0F);
//        bone_r4.texOffs(25, 19).addBox(-2.1F, -3.0435F, -1.4824F, 7.0F, 6.0F, 3.0F, 0.0F, false);
//
//        left_arm = new ModelRenderer(this);
//        left_arm.setPos(-8.0F, 16.1813F, -3.6327F);
//        left_arm.texOffs(35, 36).addBox(0.5F, 1.8187F, -1.1173F, 3.0F, 6.0F, 4.0F, 0.0F, false);
//
//        bone_r5 = new ModelRenderer(this);
//        bone_r5.setPos(2.0F, 0.785F, -0.1821F);
//        left_arm.addChild(bone_r5);
//        setRotationAngle(bone_r5, 0.6109F, 0.0F, 0.0F);
//        bone_r5.texOffs(21, 29).addBox(-2.0F, -2.9663F, -1.9352F, 4.0F, 6.0F, 4.0F, 0.0F, false);
//
//        right_arm = new ModelRenderer(this);
//        right_arm.setPos(8.0F, 16.0054F, -4.239F);
//        right_arm.texOffs(14, 40).addBox(-3.5F, 1.9946F, -0.511F, 3.0F, 6.0F, 4.0F, 0.0F, false);
//
//        bone_r6 = new ModelRenderer(this);
//        bone_r6.setPos(-2.0F, 1.0054F, 0.511F);
//        right_arm.addChild(bone_r6);
//        setRotationAngle(bone_r6, 0.6109F, 0.0F, 0.0F);
//        bone_r6.texOffs(0, 33).addBox(-2.0F, -3.0108F, -2.022F, 4.0F, 6.0F, 4.0F, 0.0F, false);
//    }
//
//    @Override
//    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight,
//                               int packedOverlay, float red, float green, float blue, float alpha) {
//        head.render(matrixStack, buffer, packedLight, packedOverlay);
//        body.render(matrixStack, buffer, packedLight, packedOverlay);
//        left_arm.render(matrixStack, buffer, packedLight, packedOverlay);
//        right_arm.render(matrixStack, buffer, packedLight, packedOverlay);
//    }
//
//    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
//        modelRenderer.xRot = x;
//        modelRenderer.yRot = y;
//        modelRenderer.zRot = z;
//    }
//
//    @Override
//    public void setupAnim(SunderedCadaverEntity entityIn, float limbSwing, float limbSwingAmount,
//                          float ageInTicks, float netHeadYaw, float headPitch) {
//        this.jaw.xRot = limbSwing / (180F / (float) Math.PI);
//        this.right_arm.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount;
//        this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
//        this.head.xRot = headPitch / (180F / (float) Math.PI);
//        this.left_arm.xRot = MathHelper.cos(limbSwing * 0.6662F) * limbSwingAmount;
//    }
//}