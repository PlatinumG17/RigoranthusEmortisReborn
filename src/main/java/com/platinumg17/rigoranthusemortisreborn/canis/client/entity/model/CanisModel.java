package com.platinumg17.rigoranthusemortisreborn.canis.client.entity.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.TintedAgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import com.platinumg17.rigoranthusemortisreborn.api.interfaces.AbstractCanisEntity;
import net.minecraft.util.math.MathHelper;

public class CanisModel<T extends AbstractCanisEntity> extends TintedAgeableModel<T> {

    // Made by PlatinumG17  // Made with Blockbench 4.0.1
    // Exported for Minecraft version 1.15 - 1.16 with MCP mappings
    public ModelRenderer head;
    public ModelRenderer body;
    public ModelRenderer legBackRight;
    public ModelRenderer legBackLeft;
    public ModelRenderer legFrontRight;
    public ModelRenderer legFrontLeft;
    public ModelRenderer mane;
    public ModelRenderer tail;

    public CanisModel() {
        this(0.0F);
    }

    public CanisModel(float scaleFactor) {
        float f1 = 6.5F;//13.5F;
        texWidth = 128;
        texHeight = 128;

        //_____ H E A D  _____//  can rotate:  X = +/- 25 deg   Y = +/- 20 deg    Z = +/- 20 deg
        head = new ModelRenderer(this, 0, 0);
        head.setPos(0.0F, f1, -15.0F);
        head.texOffs(39, 59).addBox(-5.5F, -4.3133F, -4.5964F, 11.0F, 10.0F, 5.0F, scaleFactor);// neck   (not that it really ahs one)
        head.texOffs(51, 0).addBox(-4.248F, -2.75F, -7.5964F, 8.0F, 8.0F, 4.0F, scaleFactor);// face-hole
        head.texOffs(76, 0).addBox(-2.248F, 0.5F, -10.5964F, 4.0F, 2.0F, 3.0F, scaleFactor);// nose
        head.texOffs(0, 6).addBox(-1.0F, -1.5F, -1.0F, 2.0F, 3.0F, 2.0F, scaleFactor);// nasal bridge

        //_____ E A R S _____// can rotate X axis -40 degrees
        head.texOffs(1, 1).addBox(-5.0F, -6.8133F, -4.0964F, 3.0F, 3.0F, 1.0F, scaleFactor);// Right  -15 Z axis
        head.texOffs(1, 1).addBox(2.0F, -6.8133F, -4.0964F, 3.0F, 3.0F, 1.0F, scaleFactor);// Left  +15 Z axis


        //_____ B O T T O M   J A W _____// can rotate X axis -40 degrees
        head.texOffs(47, 75).addBox(-2.248F, -0.97F, -3.0964F, 4.0F, 2.0F, 4.0F, scaleFactor);
        head.texOffs(76, 6).addBox(-1.75F, -9.7975F, -24.8015F, 3.0F, 2.0F, 3.0F, scaleFactor);


        //_____ B O D Y _____//  maybe 5 deg X and Z
        this.body = new ModelRenderer(this, 18, 14);
        body.setPos(0.0F, 2.0F, -3.0F);
        body.texOffs(0, 24).addBox(-5.0F, -20.8114F, 4.0018F, 10.0F, 11.0F, 13.0F, scaleFactor);// shoulders
        body.texOffs(41, 14).addBox(-5.998F, -19.9655F, 14.8897F, 12.0F, 9.0F, 10.0F, scaleFactor);// abdomen
        body.texOffs(0, 50).addBox(-5.0F, -11.2114F, 6.9253F, 10.0F, 9.0F, 9.0F, scaleFactor);// pelvis


        //_____ BACK RIGHT _____//
        this.legBackRight = new ModelRenderer(this, 0, 18);
        legBackRight.setPos(-4.5F, 6.0F, 11.0F);
        legBackRight.texOffs(0, 24).addBox(-1.571F, 3.9787F, 2.589F, 3.0F, 8.0F, 3.0F, scaleFactor); // calf paw
        legBackRight.texOffs(34, 75).addBox(-1.5F, -4.0F, -1.5F, 3.0F, 8.0F, 3.0F, scaleFactor); // calf
        legBackRight.texOffs(17, 69).addBox(-1.5F, -5.5F, -2.5F, 3.0F, 11.0F, 5.0F, scaleFactor); // thigh


        //_____ BACK LEFT _____//
        legBackLeft = new ModelRenderer(this, 0, 18);
        legBackLeft.setPos(4.5F, 6.0F, 11.0F);
        legBackLeft.texOffs(0, 24).addBox(-1.425F, 3.9787F, 2.589F, 3.0F, 8.0F, 3.0F, scaleFactor); // calf paw
        legBackLeft.texOffs(34, 75).addBox(-1.5F, -4.0F, -1.5F, 3.0F, 8.0F, 3.0F, scaleFactor); // calf
        legBackLeft.texOffs(17, 69).addBox(-1.5F, -5.5F, -2.5F, 3.0F, 11.0F, 5.0F, scaleFactor); // thigh


        //_____ FRONT RIGHT _____//
        legFrontRight = new ModelRenderer(this, 0, 18);
        legFrontRight.setPos(-3.8F, 11.0F, -10.5F);
        legFrontRight.texOffs(72, 34).addBox(-1.5F, -1.0F, -1.5F, 3.0F, 10.0F, 3.0F, scaleFactor); // calf
        legFrontRight.texOffs(0, 69).addBox(-1.5F, -6.0F, -2.5F, 3.0F, 12.0F, 5.0F, scaleFactor); // thigh


        //_____ FRONT LEFT _____//
        legFrontLeft = new ModelRenderer(this, 0, 18);
        legFrontLeft.setPos(3.7F, 11.0F, -10.5F);
        legFrontLeft.texOffs(72, 34).addBox(-1.5F, -1.0F, -1.5F, 3.0F, 10.0F, 3.0F, scaleFactor); // calf
        legFrontLeft.texOffs(0, 69).addBox(-1.5F, -6.0F, -2.5F, 3.0F, 12.0F, 5.0F, scaleFactor); // thigh


        //_____ MANE _____//
        this.mane = new ModelRenderer(this, 21, 0);
        mane.setPos(0.0F, 3.0F, -9.0F);
        mane.texOffs(0, 0).addBox(-6.5F, -5.5F, -6.0F, 13.0F, 11.0F, 12.0F, scaleFactor);
        mane.texOffs(36, 38).addBox(-6.0F, -4.5F, -5.5F, 12.0F, 9.0F, 11.0F, scaleFactor);


        //_____ TAIL _____//
        this.tail = new ModelRenderer(this, 9, 18);
        tail.setPos(0.1498F, 6.6321F, 14.1299F);
        tail.texOffs(64, 75).addBox(-1.5F, -2.5F, -1.5F, 3.0F, 5.0F, 3.0F, scaleFactor);
        tail.texOffs(72, 59).addBox(-1.5F, -3.0F, -1.5F, 3.0F, 6.0F, 3.0F, scaleFactor);
        tail.texOffs(72, 59).addBox(-1.5F, -2.5F, -1.5F, 3.0F, 5.0F, 3.0F, scaleFactor);
        tail.texOffs(72, 59).addBox(-1.5F, -2.0F, -1.5F, 3.0F, 4.0F, 3.0F, scaleFactor);
    }

    @Override
    protected Iterable<ModelRenderer> headParts() {
        return ImmutableList.of(this.head);
    }

    @Override
    protected Iterable<ModelRenderer> bodyParts() {
        return ImmutableList.of(this.body, this.legBackRight, this.legBackLeft, this.legFrontRight, this.legFrontLeft, this.tail, this.mane);
    }

    @Override
    public void prepareMobModel(T canis, float limbSwing, float limbSwingAmount, float partialTickTime) {
        this.tail.zRot = canis.getWagAngle(limbSwing, limbSwingAmount, partialTickTime);

        if (canis.isInSittingPose()) {
            if (canis.isLying()) {
                this.legBackLeft.setPos(4.5F, 16.3995F, 5.7202F);
//                this.legBackLeft.texOffs(17, 69).addBox(-1.5F, -5.0F, -2.5F, 3.0F, 10.0F, 5.0F);
//                this.legBackLeft.texOffs(34, 75).addBox(-1.5F, -3.0F, -1.5F, 3.0F, 6.0F, 3.0F);
//                this.legBackLeft.texOffs(0, 24).addBox(-1.5F, -4.0F, -1.5F, 3.0F, 8.0F, 3.0F);
                this.legFrontRight.setPos(-3.5F, 17.1527F, -13.1608F);
//                this.legFrontRight.texOffs(0, 69).addBox(-1.5F, -2.1219F, -2.286F, 3.0F, 11.0F, 5.0F);
//                this.legFrontRight.texOffs(72, 34).addBox(-1.5F, -5.0F, -1.5F, 3.0F, 10.0F, 3.0F);

                this.tail.setPos(0.0F, 16.9712F, 9.821F);
//                this.tail.texOffs(64, 75).addBox(-1.5F, -2.5F, -1.5F, 3.0F, 5.0F, 3.0F);
//                this.tail.texOffs(72, 59).addBox(-1.5F, -3.0F, -1.5F, 3.0F, 6.0F, 3.0F);
//                this.tail.texOffs(72, 59).addBox(-1.5F, -2.5F, -1.5F, 3.0F, 5.0F, 3.0F);
//                this.tail.texOffs(72, 59).addBox(-1.5F, -2.0F, -1.5F, 3.0F, 4.0F, 3.0F);

                this.legFrontLeft.setPos(3.5F, 16.9177F, -13.2464F);
//                this.legFrontLeft.texOffs(0, 69).addBox(-1.5F, -5.5F, -2.5F, 3.0F, 11.0F, 5.0F);
//                this.legFrontLeft.texOffs(72, 34).addBox(-1.5F, -5.0F, -1.5F, 3.0F, 10.0F, 3.0F);
                this.legBackRight.setPos(-4.5F, 16.3995F, 5.7202F);
//                this.legBackRight.texOffs(17, 69).addBox(-1.5F, -5.0F, -2.5F, 3.0F, 10.0F, 5.0F);
//                this.legBackRight.texOffs(34, 75).addBox(-1.5F, -3.0F, -1.5F, 3.0F, 6.0F, 3.0F);
//                this.legBackRight.texOffs(0, 24).addBox(-1.5F, -4.0F, -1.5F, 3.0F, 8.0F, 3.0F);

                this.head.setPos(0.0F, 12.7755F, -17.9465F);
//                this.head.texOffs(39, 59).addBox(-5.5F, -4.25F, -4.5F, 11.0F, 10.0F, 5.0F);
//                this.head.texOffs(51, 0).addBox(-4.25F, -2.75F, -7.5F, 8.0F, 8.0F, 4.0F);
//                this.head.texOffs(76, 0).addBox(-2.25F, 0.5F, -10.5F, 4.0F, 2.0F, 3.0F);
//                this.head.texOffs(0, 6).addBox(-1.0F, -1.75F, -1.0F, 2.0F, 3.0F, 2.0F);
//                this.head.texOffs(1, 1).addBox(-1.5F, -1.25F, -0.5F, 3.0F, 3.0F, 1.0F);
//                this.head.texOffs(1, 1).addBox(-1.5F, -1.25F, -0.5F, 3.0F, 3.0F, 1.0F);
//                this.head.texOffs(47, 75).addBox(-2.25F, -0.97F, -3.0F, 4.0F, 2.0F, 4.0F);
//                this.head.texOffs(76, 6).addBox(-1.5F, -1.0F, -1.5F, 3.0F, 2.0F, 3.0F);

                this.body.setPos(0.0F, 11.6598F, -6.8742F);
//                this.body.texOffs(0, 50).addBox(-5.0F, -4.0F, -4.5F, 10.0F, 8.0F, 9.0F);
//                this.body.texOffs(41, 14).addBox(-6.0F, -4.5F, -5.0F, 12.0F, 9.0F, 10.0F);
//                this.body.texOffs(2, 26).addBox(-5.0F, -4.75F, -5.25F, 10.0F, 8.0F, 11.0F);

                this.mane.setPos(0.0F, 12.8223F, -12.2868F);
//                this.mane.texOffs(0, 0).addBox(-6.5F, -5.0F, -6.0F, 13.0F, 10.0F, 12.0F);
//                this.mane.texOffs(36, 38).addBox(-6.0F, -4.5F, -5.5F, 12.0F, 9.0F, 11.0F);

                this.body.xRot = (float)Math.PI / 2F;
                this.mane.xRot = this.body.xRot;
                this.legBackRight.xRot = -(float)Math.PI / 2F;
                this.legBackLeft.xRot = -(float)Math.PI / 2F;
                this.legFrontRight.xRot = -(float)Math.PI / 2F;
                this.legFrontLeft.xRot = -(float)Math.PI / 2F;
            }
            else if (canis.isLying() && false)
            {
                this.head.setPos(0.0F, 1.5F, -12.0F);
//                this.head.texOffs(39, 59).addBox(-5.5F, -4.25F, -4.5F, 11.0F, 10.0F, 5.0F);
//                this.head.texOffs(51, 0).addBox(-4.25F, -2.75F, -7.5F, 8.0F, 8.0F, 4.0F);
//                this.head.texOffs(76, 0).addBox(-2.25F, 0.5F, -10.5F, 4.0F, 2.0F, 3.0F);
//                this.head.texOffs(0, 6).addBox(-1.0F, -1.75F, -1.0F, 2.0F, 3.0F, 2.0F);
//                this.head.texOffs(1, 1).addBox(-1.5F, -1.5F, -0.5F, 3.0F, 3.0F, 1.0F);
//                this.head.texOffs(1, 1).addBox(-1.5F, -1.5F, -0.5F, 3.0F, 3.0F, 1.0F);
//                this.head.texOffs(47, 75).addBox(-2.25F, -0.97F, -3.0F, 4.0F, 2.0F, 4.0F);
//                this.head.texOffs(76, 6).addBox(-1.5F, -1.0F, -1.5F, 3.0F, 2.0F, 3.0F);

                this.legBackRight.setPos(-4.5F, 13.0F, 9.0F);
//                this.legBackRight.texOffs(17, 69).addBox(-1.5F, -5.0F, -2.5F, 3.0F, 10.0F, 5.0F);
//                this.legBackRight.texOffs(34, 75).addBox(-1.5F, -3.0F, -1.5F, 3.0F, 6.0F, 3.0F);
//                this.legBackRight.texOffs(0, 24).addBox(-1.5F, -4.0F, -1.5F, 3.0F, 8.0F, 3.0F);

                this.legBackLeft.setPos(4.5F, 13.0F, 9.0F);
//                this.legBackLeft.texOffs(17, 69).addBox(-1.5F, -5.0F, -2.5F, 3.0F, 10.0F, 5.0F);
//                this.legBackLeft.texOffs(34, 75).addBox(-1.5F, -3.0F, -1.5F, 3.0F, 6.0F, 3.0F);
//                this.legBackLeft.texOffs(0, 24).addBox(-1.5F, -4.0F, -1.5F, 3.0F, 8.0F, 3.0F);

                this.legFrontRight.setPos(-3.5F, 7.0F, -9.0F);
//                this.legFrontRight.texOffs(0, 69).addBox(-1.5F, -2.3719F, -2.036F, 3.0F, 11.0F, 5.0F);
//                this.legFrontRight.texOffs(72, 34).addBox(-1.5F, -1.0342F, -0.9779F, 3.0F, 10.0F, 3.0F);

                this.legFrontLeft.setPos(3.5F, 7.0F, -9.0F);
//                this.legFrontLeft.texOffs(0, 69).addBox(-1.5F, -2.3719F, -2.036F, 3.0F, 11.0F, 5.0F);
//                this.legFrontLeft.texOffs(72, 34).addBox(-1.5F, -0.9037F, -1.9693F, 3.0F, 10.0F, 3.0F);

                this.tail.setPos(0.0F, 14.0F, 13.0F);
//                this.tail.texOffs(64, 75).addBox(-1.5F, -2.5F, -1.5F, 3.0F, 5.0F, 3.0F);
//                this.tail.texOffs(72, 59).addBox(-1.5F, -3.0F, -1.5F, 3.0F, 6.0F, 3.0F);
//                this.tail.texOffs(72, 59).addBox(-1.5F, -2.5F, -1.5F, 3.0F, 5.0F, 3.0F);
//                this.tail.texOffs(72, 59).addBox(-1.5F, -2.0F, -1.5F, 3.0F, 4.0F, 3.0F);

                this.mane.setPos(0.0F, 3.4797F, -6.6976F);
//                this.mane.texOffs(0, 0).addBox(-6.5F, -5.0F, -6.0F, 13.0F, 10.0F, 12.0F);
//                this.mane.texOffs(36, 38).addBox(-6.0F, -4.5F, -5.5F, 12.0F, 9.0F, 11.0F);

                this.body.setPos(0.0F, 4.2385F, -1.2138F);
//                this.body.texOffs(0, 50).addBox(-5.0F, -4.0F, -4.5F, 10.0F, 8.0F, 9.0F);
//                this.body.texOffs(41, 14).addBox(-6.0F, -4.5F, -5.0F, 12.0F, 9.0F, 10.0F);
//                this.body.texOffs(0, 24).addBox(-5.0F, -4.75F, -5.25F, 10.0F, 8.0F, 11.0F);

                this.legBackRight.xRot = -(float)Math.PI / 2.6F;
                this.legBackLeft.xRot = -(float)Math.PI / 2.6F;

                this.legFrontRight.xRot = -(float)Math.PI / 2;
                this.legFrontRight.yRot = (float)Math.PI / 10;
                this.legFrontLeft.xRot = -(float)Math.PI / 2;
                this.legFrontLeft.yRot = -(float)Math.PI / 10;
            } else {
                legBackLeft.setPos(4.5F, 16.3995F, 5.7202F);
//                legBackLeft.setPos(0.25F, 2.9611F, -1.2647F);
//                legBackLeft.setPos(-0.25F, 5.7734F, 0.4622F);
//                legBackLeft.setPos(0.0F, 8.6002F, 2.723F);
                legBackRight.setPos(-4.5F, 16.3995F, 5.7202F);
//                legBackRight.setPos(-0.25F, 2.8647F, -1.271F);
//                legBackRight.setPos(0.25F, 5.6769F, 0.4558F);
//                legBackRight.setPos(0.0F, 8.5037F, 2.7167F);
                tail.setPos(0.0F, 22.5054F, 12.5969F);
//                tail.setPos(-2.194F, 1.9752F, 11.4316F);
//                tail.setPos(-1.0319F, 3.2812F, 7.0949F);
//                tail.setPos(-0.3501F, 2.6437F, 2.9031F);
//                tail.setPos(0.0798F, 0.2199F, 0.5744F);
                legFrontRight.setPos(-3.5F, 11.1537F, -7.4207F);
//                legFrontRight.setPos(-0.25F, 0.1449F, -1.0826F);
//                legFrontRight.setPos(0.0F, 1.8133F, 6.3927F);
                legFrontLeft.setPos(3.5F, 10.9096F, -7.3666F);
//                legFrontLeft.setPos(0.25F, 0.1145F, -0.7354F);
//                legFrontLeft.setPos(0.0F, 1.8648F, 6.3965F);
                head.setPos(0.0F, 4.8907F, -9.105F);
//                head.setPos(-0.25F, 0.507F, -8.2638F);
//                head.setPos(-3.6784F, -3.4265F, -6.7192F);
//                head.setPos(3.1411F, -3.4375F, -6.6847F);
//                head.setPos(0.0F, 3.47F, -7.5F);
//                head.setPos(-0.25F, 0.4591F, -1.2625F);
                mane.setPos(0.0F, 7.9711F, -4.3568F);
//                mane.setPos(0.0F, -1.1936F, 4.8795F);
//                mane.setPos(0.0F, 0.2654F, -2.8478F);
                body.setPos(0.0F, 9.8989F, 0.8328F);
//                body.setPos(0.0F, 8.1901F, 10.4007F);
//                body.setPos(0.0F, 2.5755F, 4.7517F);
//                body.setPos(0.0F, -1.4198F, -5.4415F);
//
//                this.head.setPos(-1.0F, 13.5F, -7.0F);
                this.legFrontRight.yRot = 0;
                this.legFrontLeft.yRot = 0;

                this.mane.xRot = ((float)Math.PI * 2F / 5F);
                this.mane.yRot = 0.0F;

                this.body.xRot = ((float)Math.PI / 4F);
                this.legBackRight.xRot = ((float)Math.PI * 3F / 2F);
                this.legBackLeft.xRot = ((float)Math.PI * 3F / 2F);
                this.legFrontRight.xRot = 5.811947F;
                this.legFrontLeft.xRot = 5.811947F;
            }
        }
        else
        {
            this.body.setPos(0.0F, 14.0F, 2.0F);
            this.body.xRot = ((float)Math.PI / 2F);
            this.mane.setPos(-1.0F, 14.0F, -3.0F);
            this.mane.xRot = this.body.xRot;
            this.tail.setPos(-0.5F, 12.0F, 8.0F);
            this.legBackRight.setPos(-2.5F, 16.0F, 7.0F);
            this.legBackLeft.setPos(0.5F, 16.0F, 7.0F);
            this.legFrontRight.setPos(-2.5F, 16.0F, -4.0F);
            this.legFrontLeft.setPos(0.5F, 16.0F, -4.0F);
            this.legBackRight.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
            this.legBackLeft.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
            this.legFrontRight.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
            this.legFrontLeft.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

            this.head.setPos(-1.0F, 13.5F, -7.0F);
            this.legFrontRight.yRot = 0.0F;
            this.legFrontLeft.yRot = 0.0F;
        }

        this.head.zRot = canis.getInterestedAngle(partialTickTime) + canis.getShakeAngle(partialTickTime, 0.0F);
        this.mane.zRot = canis.getShakeAngle(partialTickTime, -0.08F);
        this.body.zRot = canis.getShakeAngle(partialTickTime, -0.16F);
        this.tail.zRot = canis.getShakeAngle(partialTickTime, -0.2F);
    }

    @Override
    public void setupAnim(T canisIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.xRot = headPitch * ((float)Math.PI / 180F);
        this.head.yRot = netHeadYaw * (canisIn.isInSittingPose() && canisIn.isLying() ? 0.005F : (float)Math.PI / 180F);
        this.tail.xRot = ageInTicks;
    }

    public void setVisible(boolean visible) {
        this.head.visible = visible;
        this.body.visible = visible;
        this.legBackRight.visible = visible;
        this.legBackLeft.visible = visible;
        this.legFrontRight.visible = visible;
        this.legFrontLeft.visible = visible;
        this.tail.visible = visible;
        this.mane.visible = visible;
    }
}


/*
    public ModelRenderer head;
    public ModelRenderer body;
    public ModelRenderer legBackRight;
    public ModelRenderer legBackLeft;
    public ModelRenderer legFrontRight;
    public ModelRenderer legFrontLeft;
    public ModelRenderer tail;
    public ModelRenderer mane;

    public CanisModel() {
        this(0.0F);
    }

    public CanisModel(float scaleFactor) {
        float f1 = 13.5F;

        // COORDS
        // x is left/right of the canis
        // y is back and forward

        //Head
        this.head = new ModelRenderer(this, 0, 0);
        this.head.addBox(-2.0F, -3.0F, -2.0F, 6, 6, 4, scaleFactor);
        this.head.setPos(-1.0F, f1, -7.0F);

        //Body
        this.body = new ModelRenderer(this, 18, 14);
        this.body.addBox(-3.0F, -2.0F, -3.0F, 6, 9, 6, scaleFactor);
        this.body.setPos(0.0F, 14.0F, 2.0F);

        //Mane
        this.mane = new ModelRenderer(this, 21, 0);
        this.mane.addBox(-3.0F, -3.0F, -3.0F, 8, 6, 7, scaleFactor);
        this.mane.setPos(-1.0F, 14.0F, 2.0F);

        //Limbs
        this.legBackRight = new ModelRenderer(this, 0, 18);
        this.legBackRight.addBox(0.0F, 0.0F, -1.0F, 2, 8, 2, scaleFactor);
        this.legBackRight.setPos(-2.5F, 16.0F, 7.0F);
        this.legBackLeft = new ModelRenderer(this, 0, 18);
        this.legBackLeft.addBox(0.0F, 0.0F, -1.0F, 2, 8, 2, scaleFactor);
        this.legBackLeft.setPos(0.5F, 16.0F, 7.0F);
        this.legFrontRight = new ModelRenderer(this, 0, 18);
        this.legFrontRight.addBox(0.0F, 0.0F, -1.0F, 2, 8, 2, scaleFactor);
        this.legFrontRight.setPos(-2.5F, 16.0F, -4.0F);
        this.legFrontLeft = new ModelRenderer(this, 0, 18);
        this.legFrontLeft.addBox(0.0F, 0.0F, -1.0F, 2, 8, 2, scaleFactor);
        this.legFrontLeft.setPos(0.5F, 16.0F, -4.0F);

        //Tail1
        this.tail = new ModelRenderer(this, 9, 18);
        this.tail.addBox(-0.5F, 0.0F, -1.0F, 2, 8, 2, scaleFactor);
        this.tail.setPos(-0.5F, 12.0F, 8.0F);

        //Tail2
        this.tail.texOffs(45, 0).addBox(0.0F, 0.0F, 0.0F, 2, 3, 1).setPos(90.0F, 0.0F, 0.0F);

        //Tail3
        this.tail.texOffs(43, 19).addBox(-1.0F, 0F, -2F, 3, 10, 3).setPos(-1.0F, 12.0F, 8.0F);

        //HeadMain EarsNormal
        this.head.texOffs(16, 14).addBox(-2.0F, -5.0F, 0.0F, 2, 2, 1, scaleFactor);
        this.head.texOffs(16, 14).addBox(2.0F, -5.0F, 0.0F, 2, 2, 1, scaleFactor);

        //HeadMain EarsBoni
        this.head.texOffs(52, 0).addBox(-3.0F, -3.0F, -1.5F, 1, 5, 3, scaleFactor);
        this.head.texOffs(52, 0).addBox(4.0F, -3.0F, -1.5F, 1, 5, 3, scaleFactor);

        //HeadMain EarsSmall
        this.head.texOffs(18, 0).addBox(-2.8F, -3.5F, -1.0F, 2, 1, 2, scaleFactor);
        this.head.texOffs(18, 0).addBox(2.8F, -3.5F, -1.0F, 2, 1, 2, scaleFactor);

        //HeadMain Nose
        this.head.texOffs(0, 10).addBox(-0.5F, 0.0F, -5.0F, 3, 3, 4, scaleFactor);
    }

    @Override
    protected Iterable<ModelRenderer> headParts() {
        return ImmutableList.of(this.head);
    }

    @Override
    protected Iterable<ModelRenderer> bodyParts() {
        return ImmutableList.of(this.body, this.legBackRight, this.legBackLeft, this.legFrontRight, this.legFrontLeft, this.tail, this.mane);
    }

    @Override
    public void prepareMobModel(T canis, float limbSwing, float limbSwingAmount, float partialTickTime) {
        this.tail.yRot = canis.getWagAngle(limbSwing, limbSwingAmount, partialTickTime);

        if (canis.isInSittingPose()) {
            if (canis.isLying()) {
                this.head.setPos(-1, 19.5F, -7);
                this.body.setPos(0, 20, 2);
                this.body.xRot = (float)Math.PI / 2F;
                this.mane.setPos(-1, 20, -2);
                this.mane.xRot = this.body.xRot;
                this.tail.setPos(-1, 18, 8);
                this.legBackRight.setPos(-4.5F, 23, 7);
                this.legBackRight.xRot = -(float)Math.PI / 2F;
                this.legBackLeft.setPos(2.5F, 23, 7);
                this.legBackLeft.xRot = -(float)Math.PI / 2F;
                this.legFrontRight.setPos(-4.5F, 23, -4);
                this.legFrontRight.xRot = -(float)Math.PI / 2F;
                this.legFrontLeft.setPos(2.5F, 23, -4);
                this.legFrontLeft.xRot = -(float)Math.PI / 2F;

            }
            else if (canis.isLying() && false) {
                this.body.setPos(0.0F, 19.0F, 2.0F);
                this.body.xRot = ((float)Math.PI / 2F);
                this.mane.setPos(-1.0F, 19.0F, -3.0F);
                this.mane.xRot = this.body.xRot;
                this.head.setPos(-1.0F, 17.0F, -7.0F);

                this.tail.setPos(-0.5F, 17.0F, 8.0F); // +4.0D
                this.legBackRight.setPos(-4.5F, 20.0F, 7.0F);
                this.legBackLeft.setPos(2.5F, 20.0F, 7.0F);
                this.legFrontRight.setPos(-3.0F, 22.0F, -3.0F);
                this.legFrontLeft.setPos(1.0F, 22.0F, -3.0F);

                this.legBackRight.xRot = -(float)Math.PI / 2.6F;
                this.legBackLeft.xRot = -(float)Math.PI / 2.6F;

                this.legFrontRight.xRot = -(float)Math.PI / 2;
                this.legFrontRight.yRot = (float)Math.PI / 10;
                this.legFrontLeft.xRot = -(float)Math.PI / 2;
                this.legFrontLeft.yRot = -(float)Math.PI / 10;
            } else {
                this.head.setPos(-1.0F, 13.5F, -7.0F);
                this.mane.setPos(-1.0F, 16.0F, -3.0F);
                this.mane.xRot = ((float)Math.PI * 2F / 5F);
                this.mane.yRot = 0.0F;
                this.body.setPos(0.0F, 18.0F, 0.0F);
                this.body.xRot = ((float)Math.PI / 4F);
                this.tail.setPos(-0.5F, 21.0F, 6.0F);
                this.legBackRight.setPos(-2.5F, 22.0F, 2.0F);
                this.legBackRight.xRot = ((float)Math.PI * 3F / 2F);
                this.legBackLeft.setPos(0.5F, 22.0F, 2.0F);
                this.legBackLeft.xRot = ((float)Math.PI * 3F / 2F);
                this.legFrontRight.xRot = 5.811947F;
                this.legFrontRight.setPos(-2.49F, 17.0F, -4.0F);
                this.legFrontLeft.xRot = 5.811947F;
                this.legFrontLeft.setPos(0.51F, 17.0F, -4.0F);


                this.head.setPos(-1.0F, 13.5F, -7.0F);
                this.legFrontRight.yRot = 0;
                this.legFrontLeft.yRot = 0;
            }
        } else {
            this.body.setPos(0.0F, 14.0F, 2.0F);
            this.body.xRot = ((float)Math.PI / 2F);
            this.mane.setPos(-1.0F, 14.0F, -3.0F);
            this.mane.xRot = this.body.xRot;
            this.tail.setPos(-0.5F, 12.0F, 8.0F);
            this.legBackRight.setPos(-2.5F, 16.0F, 7.0F);
            this.legBackLeft.setPos(0.5F, 16.0F, 7.0F);
            this.legFrontRight.setPos(-2.5F, 16.0F, -4.0F);
            this.legFrontLeft.setPos(0.5F, 16.0F, -4.0F);
            this.legBackRight.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
            this.legBackLeft.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
            this.legFrontRight.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
            this.legFrontLeft.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

            this.head.setPos(-1.0F, 13.5F, -7.0F);
            this.legFrontRight.yRot = 0.0F;
            this.legFrontLeft.yRot = 0.0F;
        }

        this.head.zRot = canis.getInterestedAngle(partialTickTime) + canis.getShakeAngle(partialTickTime, 0.0F);
        this.mane.zRot = canis.getShakeAngle(partialTickTime, -0.08F);
        this.body.zRot = canis.getShakeAngle(partialTickTime, -0.16F);
        this.tail.zRot = canis.getShakeAngle(partialTickTime, -0.2F);
    }

    @Override
    public void setupAnim(T canisIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.xRot = headPitch * ((float)Math.PI / 180F);
        this.head.yRot = netHeadYaw * (canisIn.isInSittingPose() && canisIn.isLying() ? 0.005F : (float)Math.PI / 180F);
        this.tail.xRot = ageInTicks;
    }

    public void setVisible(boolean visible) {
        this.head.visible = visible;
        this.body.visible = visible;
        this.legBackRight.visible = visible;
        this.legBackLeft.visible = visible;
        this.legFrontRight.visible = visible;
        this.legFrontLeft.visible = visible;
        this.tail.visible = visible;
        this.mane.visible = visible;
    }
}*/
