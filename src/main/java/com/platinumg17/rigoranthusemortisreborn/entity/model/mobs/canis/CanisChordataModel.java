package com.platinumg17.rigoranthusemortisreborn.entity.model.mobs.canis;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.platinumg17.rigoranthusemortisreborn.entity.mobs.FeralCanisEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class CanisChordataModel extends EntityModel<FeralCanisEntity> {
    // Made by PlatinumG17  // Made with Blockbench 4.0.1
    // Exported for Minecraft version 1.15 - 1.16 with MCP mappings
    private final ModelRenderer head;
    private final ModelRenderer bridge;
    private final ModelRenderer bridge_r1;
    private final ModelRenderer jaw;
    private final ModelRenderer bone5;
    private final ModelRenderer bone_r1;
    private final ModelRenderer body;
    private final ModelRenderer body_rotation;
    private final ModelRenderer body2;
    private final ModelRenderer body_rotation2;
    private final ModelRenderer body3;
    private final ModelRenderer body_rotation3;
    private final ModelRenderer right_hind_leg;
    private final ModelRenderer hind_leg1;
    private final ModelRenderer bone;
    private final ModelRenderer bone_r2;
    private final ModelRenderer leg1_rotation2;
    private final ModelRenderer leg1_rotation3;
    private final ModelRenderer left_hind_leg;
    private final ModelRenderer hind_leg2;
    private final ModelRenderer bone4;
    private final ModelRenderer bone_r3;
    private final ModelRenderer leg6;
    private final ModelRenderer leg2;
    private final ModelRenderer right_fore_leg;
    private final ModelRenderer bone2;
    private final ModelRenderer left_fore_leg;
    private final ModelRenderer bone3;
    private final ModelRenderer tail;
    private final ModelRenderer tail_tip;
    private final ModelRenderer mane;
    private final ModelRenderer mane2;
    private final ModelRenderer bone6;
    private final ModelRenderer mane3;
    private final ModelRenderer mane_rotation3;
    private final ModelRenderer bone_r4;

    public CanisChordataModel() {
        texWidth = 128;
        texHeight = 128;

        head = new ModelRenderer(this);
        head.setPos(0.0F, 6.5F, -15.0F);
        head.texOffs(39, 59).addBox(-5.5F, -4.3133F, -4.5964F, 11.0F, 10.0F, 5.0F, 0.0F, false);
        head.texOffs(51, 0).addBox(-4.248F, -2.75F, -7.5964F, 8.0F, 8.0F, 4.0F, 0.0F, false);
        head.texOffs(1, 1).addBox(-5.0F, -6.8133F, -4.0964F, 3.0F, 3.0F, 1.0F, 0.0F, false);
        head.texOffs(1, 1).addBox(2.0F, -6.8133F, -4.0964F, 3.0F, 3.0F, 1.0F, 0.0F, false);
        head.texOffs(76, 0).addBox(-2.248F, 0.5F, -10.5964F, 4.0F, 2.0F, 3.0F, 0.0F, false);

        bridge = new ModelRenderer(this);
        bridge.setPos(1.5F, 6.25F, 1.5F);
        head.addChild(bridge);

        bridge_r1 = new ModelRenderer(this);
        bridge_r1.setPos(0.0F, 0.0F, 0.0F);
        bridge.addChild(bridge_r1);
        setRotationAngle(bridge_r1, -0.9163F, 0.0F, 0.0F);
        bridge_r1.texOffs(0, 6).addBox(-2.748F, 2.7234F, -11.435F, 2.0F, 3.0F, 2.0F, 0.0F, false);

        jaw = new ModelRenderer(this);
        jaw.setPos(0.0F, 3.47F, -7.5F);
        head.addChild(jaw);
        setRotationAngle(jaw, 0.3927F, 0.0F, 0.0F);
        jaw.texOffs(47, 75).addBox(-2.248F, -0.97F, -3.0964F, 4.0F, 2.0F, 4.0F, 0.0F, false);

        bone5 = new ModelRenderer(this);
        bone5.setPos(-1.5F, -11.5F, 6.0F);
        jaw.addChild(bone5);

        bone_r1 = new ModelRenderer(this);
        bone_r1.setPos(0.0F, 12.03F, 15.0F);
        bone5.addChild(bone_r1);
        setRotationAngle(bone_r1, -0.2182F, 0.0F, 0.0F);
        bone_r1.texOffs(76, 6).addBox(-0.25F, 3.7025F, -23.3015F, 3.0F, 2.0F, 3.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setPos(0.0F, 2.0F, 0.0F);

        body_rotation = new ModelRenderer(this);
        body_rotation.setPos(0.0F, 9.0F, 2.0F);
        body.addChild(body_rotation);
        setRotationAngle(body_rotation, 1.1781F, 0.0F, 0.0F);
        body_rotation.texOffs(0, 50).addBox(-5.0F, 1.7886F, 4.9253F, 10.0F, 9.0F, 9.0F, 0.0F, false);

        body2 = new ModelRenderer(this);
        body2.setPos(1.0F, 15.0F, 3.0F);
        body.addChild(body2);
        setRotationAngle(body2, -0.6545F, 0.0F, 0.0F);

        body_rotation2 = new ModelRenderer(this);
        body_rotation2.setPos(-1.0F, -1.5F, 2.5F);
        body2.addChild(body_rotation2);
        setRotationAngle(body_rotation2, 1.9199F, 0.0F, 0.0F);
        body_rotation2.texOffs(41, 14).addBox(-5.998F, -11.4655F, 9.3897F, 12.0F, 9.0F, 10.0F, 0.0F, false);

        body3 = new ModelRenderer(this);
        body3.setPos(1.0F, 12.0F, 2.0F);
        body.addChild(body3);

        body_rotation3 = new ModelRenderer(this);
        body_rotation3.setPos(-1.0F, -2.5F, -2.5F);
        body3.addChild(body_rotation3);
        setRotationAngle(body_rotation3, 2.0944F, 0.0F, 0.0F);
        body_rotation3.texOffs(0, 24).addBox(-5.0F, -8.3114F, 4.5018F, 10.0F, 11.0F, 13.0F, 0.0F, false);

        right_hind_leg = new ModelRenderer(this);
        right_hind_leg.setPos(-4.5F, 6.0F, 11.0F);

        hind_leg1 = new ModelRenderer(this);
        hind_leg1.setPos(3.5F, 9.9409F, -10.8377F);
        right_hind_leg.addChild(hind_leg1);
        setRotationAngle(hind_leg1, -0.5672F, 0.0F, 0.0F);

        bone = new ModelRenderer(this);
        bone.setPos(0.002F, 14.4958F, 0.7413F);
        hind_leg1.addChild(bone);

        bone_r2 = new ModelRenderer(this);
        bone_r2.setPos(0.0F, 0.0F, 0.0F);
        bone.addChild(bone_r2);
        setRotationAngle(bone_r2, 0.1309F, 0.0F, 0.0F);
        bone_r2.texOffs(17, 69).addBox(-5.25F, -30.8653F, 4.3316F, 3.0F, 11.0F, 5.0F, 0.0F, false);

        leg1_rotation2 = new ModelRenderer(this);
        leg1_rotation2.setPos(-0.5F, -1.9409F, 5.8377F);
        hind_leg1.addChild(leg1_rotation2);
        setRotationAngle(leg1_rotation2, 1.2653F, 0.0F, 0.0F);
        leg1_rotation2.texOffs(34, 75).addBox(-4.448F, -4.5024F, 3.4943F, 3.0F, 8.0F, 3.0F, 0.0F, false);

        leg1_rotation3 = new ModelRenderer(this);
        leg1_rotation3.setPos(-2.2F, 1.0591F, 9.7377F);
        hind_leg1.addChild(leg1_rotation3);
        setRotationAngle(leg1_rotation3, 0.5672F, 0.0F, 0.0F);
        leg1_rotation3.texOffs(0, 24).addBox(-2.89F, -6.2633F, 2.5036F, 3.0F, 8.0F, 3.0F, 0.0F, false);

        left_hind_leg = new ModelRenderer(this);
        left_hind_leg.setPos(4.5F, 6.0F, 11.0F);

        hind_leg2 = new ModelRenderer(this);
        hind_leg2.setPos(-1.5F, 10.0F, -11.0F);
        left_hind_leg.addChild(hind_leg2);
        setRotationAngle(hind_leg2, -0.5672F, 0.0F, 0.0F);

        bone4 = new ModelRenderer(this);
        bone4.setPos(-1.998F, 14.4367F, 0.9036F);
        hind_leg2.addChild(bone4);

        bone_r3 = new ModelRenderer(this);
        bone_r3.setPos(0.0F, 0.0F, 0.0F);
        bone4.addChild(bone_r3);
        setRotationAngle(bone_r3, 0.1309F, 0.0F, 0.0F);
        bone_r3.texOffs(17, 69).addBox(2.25F, -30.8653F, 4.3316F, 3.0F, 11.0F, 5.0F, 0.0F, false);

        leg6 = new ModelRenderer(this);
        leg6.setPos(-2.5F, -2.0F, 6.0F);
        hind_leg2.addChild(leg6);
        setRotationAngle(leg6, 1.2653F, 0.0F, 0.0F);
        leg6.texOffs(34, 75).addBox(2.302F, -4.5024F, 3.4943F, 3.0F, 8.0F, 3.0F, 0.0F, false);

        leg2 = new ModelRenderer(this);
        leg2.setPos(0.2F, 1.0F, 9.9F);
        hind_leg2.addChild(leg2);
        setRotationAngle(leg2, 0.5672F, 0.0F, 0.0F);
        leg2.texOffs(0, 24).addBox(-0.248F, -6.2633F, 2.5036F, 3.0F, 8.0F, 3.0F, 0.0F, false);

        right_fore_leg = new ModelRenderer(this);
        right_fore_leg.setPos(-3.8F, 11.0F, -10.5F);
        right_fore_leg.texOffs(72, 34).addBox(-1.448F, 2.9367F, 0.0036F, 3.0F, 10.0F, 3.0F, 0.0F, false);

        bone2 = new ModelRenderer(this);
        bone2.setPos(3.5F, 7.0F, 5.0F);
        right_fore_leg.addChild(bone2);
        setRotationAngle(bone2, 0.5236F, 0.0F, 0.0F);
        bone2.texOffs(0, 69).addBox(-5.248F, -15.5316F, -3.5754F, 3.0F, 12.0F, 5.0F, 0.0F, false);

        left_fore_leg = new ModelRenderer(this);
        left_fore_leg.setPos(3.7F, 11.0F, -10.5F);
        left_fore_leg.texOffs(72, 34).addBox(-1.548F, 2.9367F, 0.0036F, 3.0F, 10.0F, 3.0F, 0.0F, false);

        bone3 = new ModelRenderer(this);
        bone3.setPos(-3.5F, 7.0F, 5.0F);
        left_fore_leg.addChild(bone3);
        setRotationAngle(bone3, 0.5236F, 0.0F, 0.0F);
        bone3.texOffs(0, 69).addBox(2.252F, -15.5316F, -3.5754F, 3.0F, 12.0F, 5.0F, 0.0F, false);

        tail = new ModelRenderer(this);
        tail.setPos(0.0F, 5.0F, 16.0F);
        tail.texOffs(72, 59).addBox(-1.498F, -1.0633F, -1.0964F, 3.0F, 12.0F, 3.0F, 0.0F, false);

        tail_tip = new ModelRenderer(this);
        tail_tip.setPos(1.0F, 9.0F, -8.0F);
        tail.addChild(tail_tip);
        setRotationAngle(tail_tip, 0.3054F, 0.0F, 0.0F);
        tail_tip.texOffs(64, 75).addBox(-2.498F, 4.0366F, 5.9536F, 3.0F, 5.0F, 3.0F, 0.0F, false);

        mane = new ModelRenderer(this);
        mane.setPos(0.0F, 3.0F, -9.0F);

        mane2 = new ModelRenderer(this);
        mane2.setPos(1.0F, 11.0F, 8.0F);
        mane.addChild(mane2);

        bone6 = new ModelRenderer(this);
        bone6.setPos(-1.0F, 2.5F, -2.5F);
        mane2.addChild(bone6);
        setRotationAngle(bone6, 1.5708F, 0.0F, 0.0F);
        bone6.texOffs(36, 38).addBox(-6.0F, -13.0633F, 5.4036F, 12.0F, 9.0F, 11.0F, 0.0F, false);

        mane3 = new ModelRenderer(this);
        mane3.setPos(1.0F, 14.0F, 12.0F);
        mane.addChild(mane3);
        setRotationAngle(mane3, -0.6545F, 0.0F, 0.0F);

        mane_rotation3 = new ModelRenderer(this);
        mane_rotation3.setPos(-1.0F, -1.5F, -1.5F);
        mane3.addChild(mane_rotation3);
        setRotationAngle(mane_rotation3, 2.0944F, 0.0F, 0.0F);

        bone_r4 = new ModelRenderer(this);
        bone_r4.setPos(0.002F, 13.4367F, -0.5964F);
        mane_rotation3.addChild(bone_r4);
        setRotationAngle(bone_r4, 0.0873F, 0.0F, 0.0F);
        bone_r4.texOffs(0, 0).addBox(-6.502F, -24.8916F, 9.4464F, 13.0F, 11.0F, 12.0F, 0.0F, false);
    }

    @Override
    public void setupAnim(FeralCanisEntity entityIn, float limbSwing, float limbSwingAmount,
                          float ageInTicks, float netHeadYaw, float headPitch) {
        this.left_hind_leg.xRot = MathHelper.cos(limbSwing * 0.6662F) * limbSwingAmount;
        this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
        this.head.xRot = headPitch / (180F / (float) Math.PI);
        this.right_hind_leg.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount;
        this.jaw.xRot = netHeadYaw / (180F / (float) Math.PI);
        this.right_fore_leg.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount;
        this.left_fore_leg.xRot = MathHelper.cos(limbSwing * 0.6662F) * limbSwingAmount;
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight,
                               int packedOverlay, float red, float green, float blue, float alpha) {
        head.render(matrixStack, buffer, packedLight, packedOverlay);
        body.render(matrixStack, buffer, packedLight, packedOverlay);
        right_hind_leg.render(matrixStack, buffer, packedLight, packedOverlay);
        left_hind_leg.render(matrixStack, buffer, packedLight, packedOverlay);
        right_fore_leg.render(matrixStack, buffer, packedLight, packedOverlay);
        left_fore_leg.render(matrixStack, buffer, packedLight, packedOverlay);
        tail.render(matrixStack, buffer, packedLight, packedOverlay);
        mane.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}