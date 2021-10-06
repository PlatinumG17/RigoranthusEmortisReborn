package com.platinumg17.rigoranthusemortisreborn.entity.model;

import com.platinumg17.rigoranthusemortisreborn.entity.mobs.NecrawFasciiEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class NecrawFasciiModel <T extends NecrawFasciiEntity> extends EntityModel<T> {
    private final ModelRenderer right_leg;
    private final ModelRenderer right_foot2_r1;
    private final ModelRenderer right_foot_r1;
    private final ModelRenderer right_leg2_r1;
    private final ModelRenderer right_leg_r1;
    private final ModelRenderer left_leg;
    private final ModelRenderer left_foot2_r1;
    private final ModelRenderer left_foot_r1;
    private final ModelRenderer left_leg2_r1;
    private final ModelRenderer left_leg_r1;
    private final ModelRenderer bone;
    private final ModelRenderer right_arm;
    private final ModelRenderer right_arm_r5_r1;
    private final ModelRenderer right_arm_r4_r1;
    private final ModelRenderer right_arm_r3_r1;
    private final ModelRenderer left_arm;
    private final ModelRenderer right_arm_r2_r1;
    private final ModelRenderer right_arm_r1_r1;
    private final ModelRenderer body;
    private final ModelRenderer bone_r3_r2_r1;
    private final ModelRenderer body_r2_r1;
    private final ModelRenderer body_r1_r1;
    private final ModelRenderer cube_r5;
    private final ModelRenderer cube_r4;
    private final ModelRenderer cube_r3;
    private final ModelRenderer cube_r2;
    private final ModelRenderer cube_r1;
    private final ModelRenderer cube_r10;
    private final ModelRenderer cube_r9;
    private final ModelRenderer cube_r8;
    private final ModelRenderer cube_r7;
    private final ModelRenderer cube_r6;
    private final ModelRenderer head;
    private final ModelRenderer bone_r1_r1;
    private final ModelRenderer skull_r1_r1;
    private final ModelRenderer bone_r4_r1_r1;
    private final ModelRenderer jaw;
    private final ModelRenderer bone_r3_r1_r1;
    private final ModelRenderer bone3;
    private final ModelRenderer cube_r11;
    private final ModelRenderer cube_r12;
    private final ModelRenderer cube_r13;
    private final ModelRenderer cube_r14;
    private final ModelRenderer cube_r15;

    public NecrawFasciiModel() {
        texWidth = 64;
        texHeight = 64;

        right_leg = new ModelRenderer(this);
        right_leg.setPos(-2.5F, 12.0F, 6.0F);

        right_foot2_r1 = new ModelRenderer(this);
        right_foot2_r1.setPos(-0.0432F, 11.0F, 0.4894F);
        right_leg.addChild(right_foot2_r1);
        setRotationAngle(right_foot2_r1, -0.0873F, -0.0873F, 0.0F);
        right_foot2_r1.texOffs(1, 32).addBox(-1.5068F, -0.9F, -3.1894F, 3.0F, 2.0F, 5.0F, 0.0F, false);

        right_foot_r1 = new ModelRenderer(this);
        right_foot_r1.setPos(0.1F, 6.0F, 0.0F);
        right_leg.addChild(right_foot_r1);
        setRotationAngle(right_foot_r1, 0.0F, -0.0873F, 0.0F);
        right_foot_r1.texOffs(0, 32).addBox(-2.1F, 4.0F, -4.0F, 4.0F, 2.0F, 5.0F, 0.0F, false);

        right_leg2_r1 = new ModelRenderer(this);
        right_leg2_r1.setPos(2.1F, 7.7305F, -0.4444F);
        right_leg.addChild(right_leg2_r1);
        setRotationAngle(right_leg2_r1, -0.5236F, -0.0873F, 0.0F);
        right_leg2_r1.texOffs(32, 5).addBox(-4.1F, -3.4805F, -2.0056F, 4.0F, 7.0F, 4.0F, 0.0F, false);

        right_leg_r1 = new ModelRenderer(this);
        right_leg_r1.setPos(2.1F, 1.8195F, 0.7131F);
        right_leg.addChild(right_leg_r1);
        setRotationAngle(right_leg_r1, 0.0834F, -0.091F, 0.0436F);
        right_leg_r1.texOffs(48, 5).addBox(-4.0F, -3.0195F, -2.0131F, 4.0F, 7.0F, 4.0F, 0.0F, true);

        left_leg = new ModelRenderer(this);
        left_leg.setPos(2.5F, 12.0F, 6.0F);

        left_foot2_r1 = new ModelRenderer(this);
        left_foot2_r1.setPos(0.0432F, 11.0F, 0.4894F);
        left_leg.addChild(left_foot2_r1);
        setRotationAngle(left_foot2_r1, -0.0873F, 0.0873F, 0.0F);
        left_foot2_r1.texOffs(2, 32).addBox(-1.4932F, -0.9F, -3.1894F, 3.0F, 2.0F, 5.0F, 0.0F, true);

        left_foot_r1 = new ModelRenderer(this);
        left_foot_r1.setPos(-4.1F, 6.0F, 0.0F);
        left_leg.addChild(left_foot_r1);
        setRotationAngle(left_foot_r1, 0.0F, 0.0873F, 0.0F);
        left_foot_r1.texOffs(0, 32).addBox(2.1F, 4.0F, -3.7F, 4.0F, 2.0F, 5.0F, 0.0F, false);

        left_leg2_r1 = new ModelRenderer(this);
        left_leg2_r1.setPos(-2.1F, 7.7305F, -0.4444F);
        left_leg.addChild(left_leg2_r1);
        setRotationAngle(left_leg2_r1, -0.5236F, 0.0873F, 0.0F);
        left_leg2_r1.texOffs(32, 5).addBox(0.1F, -3.4805F, -2.0056F, 4.0F, 7.0F, 4.0F, 0.0F, false);

        left_leg_r1 = new ModelRenderer(this);
        left_leg_r1.setPos(-2.1F, 1.8195F, 0.7131F);
        left_leg.addChild(left_leg_r1);
        setRotationAngle(left_leg_r1, 0.0834F, 0.091F, -0.0436F);
        left_leg_r1.texOffs(48, 5).addBox(0.0F, -3.0195F, -2.0131F, 4.0F, 7.0F, 4.0F, 0.0F, false);

        bone = new ModelRenderer(this);
        bone.setPos(-5.6963F, 6.5724F, -2.7667F);
        setRotationAngle(bone, 0.1309F, 0.0F, 0.0F);

        right_arm = new ModelRenderer(this);
        right_arm.setPos(1.4F, 0.0F, 0.0F);
        bone.addChild(right_arm);
        setRotationAngle(right_arm, 0.0876F, 0.0869F, 0.0076F);

        right_arm_r5_r1 = new ModelRenderer(this);
        right_arm_r5_r1.setPos(4.1714F, 16.6634F, 11.1954F);
        right_arm.addChild(right_arm_r5_r1);
        setRotationAngle(right_arm_r5_r1, 0.6127F, 0.0715F, 0.0501F);
        right_arm_r5_r1.texOffs(16, 5).addBox(-8.0F, -21.5F, -2.45F, 4.0F, 7.0F, 4.0F, 0.0F, false);

        right_arm_r4_r1 = new ModelRenderer(this);
        right_arm_r4_r1.setPos(-8.0928F, 15.759F, 10.5193F);
        right_arm.addChild(right_arm_r4_r1);
        setRotationAngle(right_arm_r4_r1, 2.5289F, -0.0715F, -3.0915F);
        right_arm_r4_r1.texOffs(4, 19).addBox(-8.05F, -19.45F, -2.9F, 4.0F, 5.0F, 4.0F, 0.0F, false);

        right_arm_r3_r1 = new ModelRenderer(this);
        right_arm_r3_r1.setPos(-0.7188F, -4.3274F, 18.9228F);
        right_arm.addChild(right_arm_r3_r1);
        setRotationAngle(right_arm_r3_r1, 2.013F, 0.158F, 0.0744F);
        right_arm_r3_r1.texOffs(40, 16).addBox(0.3F, -18.6F, -1.5F, 4.0F, 7.0F, 4.0F, 0.0F, false);

        left_arm = new ModelRenderer(this);
        left_arm.setPos(10.0926F, 0.0F, 0.0F);
        bone.addChild(left_arm);
        setRotationAngle(left_arm, 0.0876F, -0.0869F, -0.0076F);

        right_arm_r2_r1 = new ModelRenderer(this);
        right_arm_r2_r1.setPos(-4.2711F, 16.6634F, 11.2041F);
        left_arm.addChild(right_arm_r2_r1);
        setRotationAngle(right_arm_r2_r1, 0.6127F, -0.0715F, -0.0501F);
        right_arm_r2_r1.texOffs(40, 16).addBox(4.0F, -21.5F, -2.45F, 4.0F, 7.0F, 4.0F, 0.0F, true);

        right_arm_r1_r1 = new ModelRenderer(this);
        right_arm_r1_r1.setPos(0.9363F, 2.825F, 3.2515F);
        left_arm.addChild(right_arm_r1_r1);
        setRotationAngle(right_arm_r1_r1, 2.1003F, -0.158F, -0.0744F);
        right_arm_r1_r1.texOffs(40, 16).addBox(-1.6F, -2.15F, -1.7F, 4.0F, 7.0F, 4.0F, 0.0F, true);

        body = new ModelRenderer(this);
        body.setPos(5.6963F, 3.1677F, 4.1317F);
        bone.addChild(body);
        setRotationAngle(body, 0.3927F, 0.0F, 0.0F);

        bone_r3_r2_r1 = new ModelRenderer(this);
        bone_r3_r2_r1.setPos(0.0F, 10.9758F, -17.8853F);
        body.addChild(bone_r3_r2_r1);
        setRotationAngle(bone_r3_r2_r1, -0.1746F, 0.0F, 0.0F);
        bone_r3_r2_r1.texOffs(0, 39).addBox(-3.0F, -18.55F, 7.85F, 6.0F, 3.0F, 4.0F, 0.0F, false);

        body_r2_r1 = new ModelRenderer(this);
        body_r2_r1.setPos(0.0F, 16.1363F, 4.0013F);
        body.addChild(body_r2_r1);
        setRotationAngle(body_r2_r1, 0.4363F, 0.0F, 0.0F);
        body_r2_r1.texOffs(40, 44).addBox(-4.0F, -16.35F, 1.7F, 8.0F, 6.0F, 4.0F, 0.0F, false);

        body_r1_r1 = new ModelRenderer(this);
        body_r1_r1.setPos(0.0F, 16.5871F, 4.2966F);
        body.addChild(body_r1_r1);
        setRotationAngle(body_r1_r1, 0.6545F, 0.0F, 0.0F);
        body_r1_r1.texOffs(16, 16).addBox(-4.0F, -22.35F, 5.1F, 8.0F, 7.0F, 4.0F, 0.0F, false);

        cube_r5 = new ModelRenderer(this);
        cube_r5.setPos(2.35F, -5.6721F, -0.865F);
        body.addChild(cube_r5);
        setRotationAngle(cube_r5, -0.8181F, -0.4724F, -0.4029F);
        cube_r5.texOffs(0, 0).addBox(-1.1F, -0.618F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        cube_r4 = new ModelRenderer(this);
        cube_r4.setPos(1.8067F, -1.5289F, 2.4024F);
        body.addChild(cube_r4);
        setRotationAngle(cube_r4, -0.9427F, -0.5389F, -0.2432F);
        cube_r4.texOffs(0, 0).addBox(-0.3067F, -1.0112F, -0.5174F, 1.0F, 2.0F, 1.0F, 0.0F, false);

        cube_r3 = new ModelRenderer(this);
        cube_r3.setPos(2.6014F, -4.8117F, 0.0671F);
        body.addChild(cube_r3);
        setRotationAngle(cube_r3, -0.8583F, -0.2857F, -0.3457F);
        cube_r3.texOffs(0, 0).addBox(-0.5014F, -1.0784F, -0.4821F, 1.0F, 2.0F, 1.0F, 0.0F, false);

        cube_r2 = new ModelRenderer(this);
        cube_r2.setPos(2.6097F, -3.742F, 0.8912F);
        body.addChild(cube_r2);
        setRotationAngle(cube_r2, -0.8826F, -0.3974F, -0.2054F);
        cube_r2.texOffs(0, 0).addBox(-0.5097F, -1.4981F, -0.5062F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        cube_r1 = new ModelRenderer(this);
        cube_r1.setPos(2.3849F, -2.5539F, 1.642F);
        body.addChild(cube_r1);
        setRotationAngle(cube_r1, -0.962F, -0.5023F, -0.0493F);
        cube_r1.texOffs(0, 0).addBox(-0.4849F, -1.4862F, -0.507F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        cube_r10 = new ModelRenderer(this);
        cube_r10.setPos(-2.35F, -5.6721F, -0.865F);
        body.addChild(cube_r10);
        setRotationAngle(cube_r10, -0.8181F, 0.4724F, 0.4029F);
        cube_r10.texOffs(0, 0).addBox(0.1F, -0.618F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, true);

        cube_r9 = new ModelRenderer(this);
        cube_r9.setPos(-1.8067F, -1.5289F, 2.4024F);
        body.addChild(cube_r9);
        setRotationAngle(cube_r9, -0.9427F, 0.5389F, 0.2432F);
        cube_r9.texOffs(0, 0).addBox(-0.6933F, -1.0112F, -0.5174F, 1.0F, 2.0F, 1.0F, 0.0F, true);

        cube_r8 = new ModelRenderer(this);
        cube_r8.setPos(-2.6014F, -4.8117F, 0.0671F);
        body.addChild(cube_r8);
        setRotationAngle(cube_r8, -0.8583F, 0.2857F, 0.3457F);
        cube_r8.texOffs(0, 0).addBox(-0.4986F, -1.0784F, -0.4821F, 1.0F, 2.0F, 1.0F, 0.0F, true);

        cube_r7 = new ModelRenderer(this);
        cube_r7.setPos(-2.6097F, -3.742F, 0.8912F);
        body.addChild(cube_r7);
        setRotationAngle(cube_r7, -0.8826F, 0.3974F, 0.2054F);
        cube_r7.texOffs(0, 0).addBox(-0.4903F, -1.4981F, -0.5062F, 1.0F, 3.0F, 1.0F, 0.0F, true);

        cube_r6 = new ModelRenderer(this);
        cube_r6.setPos(-2.3849F, -2.5539F, 1.642F);
        body.addChild(cube_r6);
        setRotationAngle(cube_r6, -0.962F, 0.5023F, 0.0493F);
        cube_r6.texOffs(0, 0).addBox(-0.5151F, -1.4862F, -0.507F, 1.0F, 3.0F, 1.0F, 0.0F, true);

        head = new ModelRenderer(this);
        head.setPos(5.6963F, 1.797F, -3.0624F);
        bone.addChild(head);
        setRotationAngle(head, 0.1309F, 0.0F, -3.1416F);
        head.texOffs(6, 51).addBox(-4.0F, -5.5194F, -6.4209F, 8.0F, 5.0F, 8.0F, 0.0F, false);
        head.texOffs(0, 0).addBox(1.0F, -7.5194F, -3.4209F, 3.0F, 2.0F, 5.0F, 0.0F, false);
        head.texOffs(30, 52).addBox(0.75F, -7.2694F, -4.7209F, 3.0F, 2.0F, 5.0F, 0.0F, false);
        head.texOffs(20, 39).addBox(-0.3F, -7.1194F, -6.0209F, 4.0F, 2.0F, 3.0F, 0.0F, false);
        head.texOffs(38, 54).addBox(-4.0F, -7.5194F, -6.4209F, 5.0F, 2.0F, 8.0F, 0.0F, false);
        head.texOffs(14, 17).addBox(1.5F, -3.5194F, -6.6709F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        head.texOffs(14, 17).addBox(-2.5F, -3.5194F, -6.6709F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        bone_r1_r1 = new ModelRenderer(this);
        bone_r1_r1.setPos(0.0F, 18.787F, -10.5764F);
        head.addChild(bone_r1_r1);
        setRotationAngle(bone_r1_r1, -1.4835F, 0.0F, 0.0F);
        bone_r1_r1.texOffs(42, 33).addBox(-3.0F, -11.4F, -20.35F, 6.0F, 2.0F, 4.0F, 0.0F, false);

        skull_r1_r1 = new ModelRenderer(this);
        skull_r1_r1.setPos(-9.8823F, 17.9306F, -6.2192F);
        head.addChild(skull_r1_r1);
        setRotationAngle(skull_r1_r1, 0.0F, -1.5708F, 0.0F);
        skull_r1_r1.texOffs(30, 52).addBox(-0.05F, -25.2F, -12.2F, 3.0F, 2.0F, 5.0F, 0.0F, false);

        bone_r4_r1_r1 = new ModelRenderer(this);
        bone_r4_r1_r1.setPos(0.0F, 18.2915F, 4.2289F);
        head.addChild(bone_r4_r1_r1);
        setRotationAngle(bone_r4_r1_r1, -0.0436F, 0.0F, 0.0F);
        bone_r4_r1_r1.texOffs(16, 44).addBox(-3.5F, -18.85F, -11.35F, 7.0F, 2.0F, 5.0F, 0.0F, false);

        jaw = new ModelRenderer(this);
        jaw.setPos(0.1194F, 1.0423F, -1.1651F);
        head.addChild(jaw);
        setRotationAngle(jaw, 0.349F, 0.0F, 0.0F);

        bone_r3_r1_r1 = new ModelRenderer(this);
        bone_r3_r1_r1.setPos(-0.1194F, 13.6666F, 7.2698F);
        jaw.addChild(bone_r3_r1_r1);
        setRotationAngle(bone_r3_r1_r1, 0.2618F, 0.0F, 0.0F);
        bone_r3_r1_r1.texOffs(16, 44).addBox(-3.5F, -17.2F, -7.95F, 7.0F, 2.0F, 5.0F, 0.0F, false);
        bone_r3_r1_r1.texOffs(18, 32).addBox(-3.5F, -16.3F, -8.15F, 7.0F, 2.0F, 5.0F, 0.0F, false);

        bone3 = new ModelRenderer(this);
        bone3.setPos(0.0F, 24.0F, 0.0F);
        bone3.texOffs(0, 0).addBox(-0.5F, -19.0F, -4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        cube_r11 = new ModelRenderer(this);
        cube_r11.setPos(0.5F, 0.0F, 0.0F);
        bone3.addChild(cube_r11);
        setRotationAngle(cube_r11, 0.5236F, 0.0F, 0.0F);
        cube_r11.texOffs(0, 0).addBox(-1.5F, -17.8F, 3.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        cube_r12 = new ModelRenderer(this);
        cube_r12.setPos(0.5F, 0.0F, 0.0F);
        bone3.addChild(cube_r12);
        setRotationAngle(cube_r12, 0.4363F, 0.0F, 0.0F);
        cube_r12.texOffs(0, 0).addBox(-1.0F, -18.35F, 2.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        cube_r13 = new ModelRenderer(this);
        cube_r13.setPos(0.5F, 0.0F, 0.0F);
        bone3.addChild(cube_r13);
        setRotationAngle(cube_r13, 0.2618F, 0.0F, 0.0F);
        cube_r13.texOffs(0, 0).addBox(-1.5F, -18.75F, -0.75F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        cube_r14 = new ModelRenderer(this);
        cube_r14.setPos(0.5F, 0.0F, 0.0F);
        bone3.addChild(cube_r14);
        setRotationAngle(cube_r14, 0.2182F, 0.0F, 0.0F);
        cube_r14.texOffs(0, 0).addBox(-1.0F, -19.0F, -1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        cube_r15 = new ModelRenderer(this);
        cube_r15.setPos(0.5F, 0.0F, 0.0F);
        bone3.addChild(cube_r15);
        setRotationAngle(cube_r15, 0.0873F, 0.0F, 0.0F);
        cube_r15.texOffs(0, 0).addBox(-1.5F, -19.0F, -3.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight,
                       int packedOverlay, float red, float green, float blue, float alpha) {
        right_leg.render(matrixStack, buffer, packedLight, packedOverlay);
        left_leg.render(matrixStack, buffer, packedLight, packedOverlay);
        bone.render(matrixStack, buffer, packedLight, packedOverlay);
        bone3.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount,
                          float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
        this.head.xRot = headPitch / (180F / (float) Math.PI);
        this.right_leg.xRot = MathHelper.cos(limbSwing * 1.0F) * 1.0F * limbSwingAmount;
        this.jaw.xRot = netHeadYaw / (180F / (float) Math.PI);
        this.left_leg.xRot = MathHelper.cos(limbSwing * 1.0F) * -1.0F * limbSwingAmount;
    }
}