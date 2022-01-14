package com.platinumg17.rigoranthusemortisreborn.canis.client.entity.model;

import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.CanisEntity;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import javax.annotation.Nullable;

public class CanisModel extends AnimatedGeoModel<CanisEntity> {


    private static final ResourceLocation FERAL_TEXTURE = new ResourceLocation(EmortisConstants.MOD_ID, "textures/entity/canis/feral_canis_chordata.png");
    private static final ResourceLocation TAMED_TEXTURE = new ResourceLocation(EmortisConstants.MOD_ID, "textures/entity/canis/tame_canis_chordata.png");

    @Override
    public void setLivingAnimations(CanisEntity entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        if(entity.partyCanis)
            return;
        IBone head = this.getAnimationProcessor().getBone("head");
        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationX(extraData.headPitch * 0.017453292F);
        head.setRotationY(extraData.netHeadYaw * 0.017453292F);
    }

    @Override
    public ResourceLocation getModelLocation(CanisEntity canis) {
        return new ResourceLocation(EmortisConstants.MOD_ID, "geo/canis/tame_canis_chordata.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(CanisEntity canis) {
        return canis.isTame() ? TAMED_TEXTURE : FERAL_TEXTURE;
    }

    @Override
    public ResourceLocation getAnimationFileLocation(CanisEntity canis) {
        return new ResourceLocation(EmortisConstants.MOD_ID, "animations/canis/tame_canis_chordata.json");
    }
}









//public class CanisModel<T extends AbstractCanisEntity> extends TintedAgeableModel<T> {
//    // Made by PlatinumG17  // Made with Blockbench 4.0.1
//    // Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
//    public ModelRenderer whole_head;
//    public ModelRenderer head;
//    public ModelRenderer head_r1;
//    public ModelRenderer head_r2;
//    public ModelRenderer head_r3;
//    public ModelRenderer jaw;
//    public ModelRenderer jaw_r1;
//    public ModelRenderer bone2;
//    public ModelRenderer legBackRight;
//    public ModelRenderer legBackRight_r1;
//    public ModelRenderer legBackRight_r2;
//    public ModelRenderer legBackRight_r3;
//    public ModelRenderer legBackLeft;
//    public ModelRenderer legBackLeft_r1;
//    public ModelRenderer legBackLeft_r2;
//    public ModelRenderer legBackLeft_r3;
//    public ModelRenderer legFrontRight;
//    public ModelRenderer legFrontRight_r1;
//    public ModelRenderer legFrontRight_r2;
//    public ModelRenderer legFrontLeft;
//    public ModelRenderer legFrontLeft_r1;
//    public ModelRenderer legFrontLeft_r2;
//    public ModelRenderer tail;
//    public ModelRenderer tail_r1;
//    public ModelRenderer tail_r2;
//    public ModelRenderer tail_r3;
//    public ModelRenderer tail_r4;
//    public ModelRenderer body;
//    public ModelRenderer body_r1;
//    public ModelRenderer body_r2;
//    public ModelRenderer body_r3;
//    public ModelRenderer mane;
//    public ModelRenderer mane_r1;
//    public ModelRenderer mane_r2;
//
//    public CanisModel() {
//        texWidth = 128;
//        texHeight = 128;
//
//        whole_head = new ModelRenderer(this);
//        whole_head.setPos(0.0F, 4.25F, -15.25F);
//        whole_head.texOffs(39, 59).addBox(-5.5F, -4.25F, -4.5F, 11.0F, 10.0F, 5.0F, 0.0F, false);
//
//        head = new ModelRenderer(this);
//        head.setPos(0.0F, 22.5F, 12.0F);
//        whole_head.addChild(head);
//        head.texOffs(51, 0).addBox(-4.25F, -25.25F, -19.5F, 8.0F, 8.0F, 4.0F, 0.0F, false);
//        head.texOffs(76, 0).addBox(-2.25F, -22.0F, -22.5F, 4.0F, 2.0F, 3.0F, 0.0F, false);
//
//        head_r1 = new ModelRenderer(this);
//        head_r1.setPos(-0.25F, -21.993F, -20.2638F);
//        head.addChild(head_r1);
//        setRotationAngle(head_r1, -0.9163F, 0.0F, 0.0F);
//        head_r1.texOffs(0, 6).addBox(-1.0F, -1.75F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);
//
//        head_r2 = new ModelRenderer(this);
//        head_r2.setPos(-3.6784F, -25.9265F, -18.7192F);
//        head.addChild(head_r2);
//        setRotationAngle(head_r2, 0.2597F, 0.0338F, -0.3883F);
//        head_r2.texOffs(1, 1).addBox(-1.5F, -1.5F, -0.5F, 3.0F, 3.0F, 1.0F, 0.0F, false);
//
//        head_r3 = new ModelRenderer(this);
//        head_r3.setPos(3.1411F, -25.9375F, -18.6847F);
//        head.addChild(head_r3);
//        setRotationAngle(head_r3, 0.2597F, -0.0338F, 0.3883F);
//        head_r3.texOffs(1, 1).addBox(-1.5F, -1.5F, -0.5F, 3.0F, 3.0F, 1.0F, 0.0F, false);
//
//        jaw = new ModelRenderer(this);
//        jaw.setPos(0.0F, -19.03F, -19.5F);
//        head.addChild(jaw);
//        setRotationAngle(jaw, 0.3491F, 0.0F, 0.0F);
//        jaw.texOffs(47, 75).addBox(-2.25F, -0.97F, -3.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
//
//        jaw_r1 = new ModelRenderer(this);
//        jaw_r1.setPos(-0.25F, 0.4591F, -1.2625F);
//        jaw.addChild(jaw_r1);
//        setRotationAngle(jaw_r1, -0.2182F, 0.0F, 0.0F);
//        jaw_r1.texOffs(76, 6).addBox(-1.5F, -1.0F, -1.5F, 3.0F, 2.0F, 3.0F, 0.0F, false);
//
//        bone2 = new ModelRenderer(this);
//        bone2.setPos(0.0F, 0.0F, 0.0F);
//        whole_head.addChild(bone2);
//
//        legBackRight = new ModelRenderer(this);
//        legBackRight.setPos(-4.5F, 4.5F, 10.5F);
//        setRotationAngle(legBackRight, 0.0873F, 0.0F, 0.0F);
//
//        legBackRight_r1 = new ModelRenderer(this);
//        legBackRight_r1.setPos(-0.25F, 3.8647F, -1.271F);
//        legBackRight.addChild(legBackRight_r1);
//        setRotationAngle(legBackRight_r1, -0.4363F, 0.0F, 0.0F);
//        legBackRight_r1.texOffs(17, 69).addBox(-1.5F, -5.0F, -2.5F, 3.0F, 10.0F, 5.0F, 0.0F, false);
//
//        legBackRight_r2 = new ModelRenderer(this);
//        legBackRight_r2.setPos(0.0F, 5.6769F, -3.5442F);
//        legBackRight.addChild(legBackRight_r2);
//        setRotationAngle(legBackRight_r2, 0.5672F, 0.0F, 0.0F);
//        legBackRight_r2.texOffs(34, 75).addBox(-1.5F, -0.0114F, -1.7613F, 3.0F, 8.0F, 3.0F, 0.0F, false);
//
//        legBackRight_r3 = new ModelRenderer(this);
//        legBackRight_r3.setPos(-0.1F, 12.2537F, 0.2167F);
//        legBackRight.addChild(legBackRight_r3);
//        setRotationAngle(legBackRight_r3, 3.0543F, 0.0F, 0.0F);
//        legBackRight_r3.texOffs(0, 24).addBox(-1.5F, -7.3537F, -1.5548F, 3.0F, 8.0F, 3.0F, 0.0F, false);
//
//        legBackLeft = new ModelRenderer(this);
//        legBackLeft.setPos(4.5F, 4.5F, 10.5F);
//        setRotationAngle(legBackLeft, 0.0873F, 0.0F, 0.0F);
//
//
//        legBackLeft_r1 = new ModelRenderer(this);
//        legBackLeft_r1.setPos(0.25F, 3.9611F, -1.2647F);
//        legBackLeft.addChild(legBackLeft_r1);
//        setRotationAngle(legBackLeft_r1, -0.4363F, 0.0F, 0.0F);
//        legBackLeft_r1.texOffs(17, 69).addBox(-1.5F, -5.0F, -2.5F, 3.0F, 10.0F, 5.0F, 0.0F, false);
//
//        legBackLeft_r2 = new ModelRenderer(this);
//        legBackLeft_r2.setPos(0.0F, 5.7734F, -3.5378F);
//        legBackLeft.addChild(legBackLeft_r2);
//        setRotationAngle(legBackLeft_r2, 0.5672F, 0.0F, 0.0F);
//        legBackLeft_r2.texOffs(34, 75).addBox(-1.5F, -0.0114F, -1.7613F, 3.0F, 8.0F, 3.0F, 0.0F, false);
//
//        legBackLeft_r3 = new ModelRenderer(this);
//        legBackLeft_r3.setPos(0.1F, 12.3502F, 0.223F);
//        legBackLeft.addChild(legBackLeft_r3);
//        setRotationAngle(legBackLeft_r3, 3.0543F, 0.0F, 0.0F);
//        legBackLeft_r3.texOffs(0, 24).addBox(-1.5F, -7.3537F, -1.5548F, 3.0F, 8.0F, 3.0F, 0.0F, false);
//
//        legFrontRight = new ModelRenderer(this);
//        legFrontRight.setPos(-3.5F, 7.0F, -12.0F);
//        setRotationAngle(legFrontRight, 0.1745F, 0.0F, 0.0F);
//
//
//        legFrontRight_r1 = new ModelRenderer(this);
//        legFrontRight_r1.setPos(-0.25F, 0.1449F, -2.0826F);
//        legFrontRight.addChild(legFrontRight_r1);
//        setRotationAngle(legFrontRight_r1, 0.3927F, 0.0F, 0.0F);
//        legFrontRight_r1.texOffs(0, 69).addBox(-1.5F, -2.3719F, -2.036F, 3.0F, 11.0F, 5.0F, 0.0F, false);
//
//        legFrontRight_r2 = new ModelRenderer(this);
//        legFrontRight_r2.setPos(0.0F, 8.1594F, 0.0467F);
//        legFrontRight.addChild(legFrontRight_r2);
//        setRotationAngle(legFrontRight_r2, -0.1745F, 0.0F, 0.0F);
//        legFrontRight_r2.texOffs(72, 34).addBox(-1.5F, -1.0342F, -0.9779F, 3.0F, 10.0F, 3.0F, 0.0F, false);
//
//        legFrontLeft = new ModelRenderer(this);
//        legFrontLeft.setPos(3.5F, 7.0F, -12.0F);
//        setRotationAngle(legFrontLeft, 0.1745F, 0.0F, 0.0F);
//
//
//        legFrontLeft_r1 = new ModelRenderer(this);
//        legFrontLeft_r1.setPos(0.25F, 0.1145F, -1.9854F);
//        legFrontLeft.addChild(legFrontLeft_r1);
//        setRotationAngle(legFrontLeft_r1, 0.3927F, 0.0F, 0.0F);
//        legFrontLeft_r1.texOffs(0, 69).addBox(-1.5F, -2.3719F, -2.036F, 3.0F, 11.0F, 5.0F, 0.0F, false);
//
//        legFrontLeft_r2 = new ModelRenderer(this);
//        legFrontLeft_r2.setPos(0.0F, 8.1594F, 1.0467F);
//        legFrontLeft.addChild(legFrontLeft_r2);
//        setRotationAngle(legFrontLeft_r2, -0.1745F, 0.0F, 0.0F);
//        legFrontLeft_r2.texOffs(72, 34).addBox(-1.5F, -0.9037F, -1.9693F, 3.0F, 10.0F, 3.0F, 0.0F, false);
//
//        tail = new ModelRenderer(this);
//        tail.setPos(0.0F, 6.0F, 13.0F);
//        setRotationAngle(tail, 0.9675F, 0.0741F, 0.2054F);
//
//        tail_r1 = new ModelRenderer(this);
//        tail_r1.setPos(-2.194F, 1.9752F, 11.4316F);
//        tail.addChild(tail_r1);
//        setRotationAngle(tail_r1, 1.9756F, -0.3639F, 0.0728F);
//        tail_r1.texOffs(64, 75).addBox(-1.5F, -2.5F, -1.5F, 3.0F, 5.0F, 3.0F, 0.0F, false);
//
//        tail_r2 = new ModelRenderer(this);
//        tail_r2.setPos(-1.0319F, 3.2812F, 7.0949F);
//        tail.addChild(tail_r2);
//        setRotationAngle(tail_r2, 1.6799F, -0.1888F, 0.1091F);
//        tail_r2.texOffs(72, 59).addBox(-1.5F, -3.0F, -1.5F, 3.0F, 6.0F, 3.0F, 0.0F, false);
//
//        tail_r3 = new ModelRenderer(this);
//        tail_r3.setPos(-0.3501F, 2.6437F, 2.9031F);
//        tail.addChild(tail_r3);
//        setRotationAngle(tail_r3, 1.0407F, -0.0096F, 0.1569F);
//        tail_r3.texOffs(72, 59).addBox(-1.5F, -2.5F, -1.5F, 3.0F, 5.0F, 3.0F, 0.0F, false);
//
//        tail_r4 = new ModelRenderer(this);
//        tail_r4.setPos(0.0798F, 0.2199F, 0.5744F);
//        tail.addChild(tail_r4);
//        setRotationAngle(tail_r4, 0.3924F, -0.0167F, 0.0403F);
//        tail_r4.texOffs(72, 59).addBox(-1.5F, -2.0F, -1.5F, 3.0F, 4.0F, 3.0F, 0.0F, false);
//
//        body = new ModelRenderer(this);
//        body.setPos(0.0F, 1.2385F, -2.2138F);
//        setRotationAngle(body, 0.3491F, 0.0F, 0.0F);
//
//        body_r1 = new ModelRenderer(this);
//        body_r1.setPos(0.0F, 8.1901F, 10.4007F);
//        body.addChild(body_r1);
//        setRotationAngle(body_r1, 0.7854F, 0.0F, 0.0F);
//        body_r1.texOffs(0, 50).addBox(-5.0F, -4.0F, -4.5F, 10.0F, 8.0F, 9.0F, 0.0F, false);
//
//        body_r2 = new ModelRenderer(this);
//        body_r2.setPos(0.0F, 2.5755F, 4.7517F);
//        body.addChild(body_r2);
//        setRotationAngle(body_r2, 0.8727F, 0.0F, 0.0F);
//        body_r2.texOffs(41, 14).addBox(-6.0F, -4.5F, -5.0F, 12.0F, 9.0F, 10.0F, 0.0F, false);
//
//        body_r3 = new ModelRenderer(this);
//        body_r3.setPos(0.0F, -1.4198F, -6.4415F);
//        body.addChild(body_r3);
//        setRotationAngle(body_r3, 1.5708F, 0.0F, 0.0F);
//        body_r3.texOffs(2, 26).addBox(-5.0F, -4.75F, -5.25F, 10.0F, 8.0F, 11.0F, 0.0F, false);
//
//        mane = new ModelRenderer(this);
//        mane.setPos(0.0F, -0.7588F, -5.4838F);
//        body.addChild(mane);
//        setRotationAngle(mane, -0.3927F, 0.0F, 0.0F);
//
//        mane_r1 = new ModelRenderer(this);
//        mane_r1.setPos(0.0F, -1.1936F, 3.8795F);
//        mane.addChild(mane_r1);
//        setRotationAngle(mane_r1, 1.5708F, 0.0F, 0.0F);
//        mane_r1.texOffs(0, 0).addBox(-6.5F, -5.0F, -6.0F, 13.0F, 10.0F, 12.0F, 0.0F, false);
//
//        mane_r2 = new ModelRenderer(this);
//        mane_r2.setPos(0.0F, 1.0154F, -4.8478F);
//        mane.addChild(mane_r2);
//        setRotationAngle(mane_r2, 1.6144F, 0.0F, 0.0F);
//        mane_r2.texOffs(36, 38).addBox(-6.0F, -4.5F, -5.5F, 12.0F, 9.0F, 11.0F, 0.0F, false);
//    }
//
//    @Override
//    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
//        whole_head.render(matrixStack, buffer, packedLight, packedOverlay);
//        legBackRight.render(matrixStack, buffer, packedLight, packedOverlay);
//        legBackLeft.render(matrixStack, buffer, packedLight, packedOverlay);
//        legFrontRight.render(matrixStack, buffer, packedLight, packedOverlay);
//        legFrontLeft.render(matrixStack, buffer, packedLight, packedOverlay);
//        tail.render(matrixStack, buffer, packedLight, packedOverlay);
//        body.render(matrixStack, buffer, packedLight, packedOverlay);
//    }
//
//    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
//        modelRenderer.xRot = x;
//        modelRenderer.yRot = y;
//        modelRenderer.zRot = z;
//    }
//
//    @Override
//    protected Iterable<ModelRenderer> headParts() {
//        return ImmutableList.of(this.whole_head);
//    }
//
//    @Override
//    protected Iterable<ModelRenderer> bodyParts() {
//        return ImmutableList.of(this.body, this.legBackRight, this.legBackLeft, this.legFrontRight, this.legFrontLeft, this.tail, this.mane);
//    }
//
//
//    @Override
//    public void prepareMobModel(T canis, float limbSwing, float limbSwingAmount, float partialTickTime) {
//        this.tail.zRot = canis.getWagAngle(limbSwing, limbSwingAmount, partialTickTime);
//
//        if (canis.isInSittingPose()) {
////            if (canis.isLying()) {
////            } else {
//            whole_head.setPos(0.0F, 1.5F, -12.0F);
////            head.setPos(0.0F, 22.5F, 12.0F);
////            head_r1.setPos(-0.25F, -21.993F, -20.2638F);
//////            setRotationAngle(head_r1, -0.9163F, 0.0F, 0.0F);
////
////            head_r2.setPos(-3.6784F, -25.9265F, -18.7192F);
//////            setRotationAngle(head_r2, 0.2597F, 0.0338F, -0.3883F);
////            head_r3.setPos(3.1411F, -25.9375F, -18.6847F);
//////            setRotationAngle(head_r3, 0.2597F, -0.0338F, 0.3883F);
//
//            jaw.setPos(0.0F, -19.03F, -19.5F);
////            setRotationAngle(jaw, 0.3491F, 0.0F, 0.0F);
////            jaw_r1.setPos(-0.25F, 0.4591F, -1.2625F);
////
//////            setRotationAngle(jaw_r1, -0.2182F, 0.0F, 0.0F);
////            bone2.setPos(0.0F, 0.0F, 0.0F);
//            legBackRight.setPos(-4.5F, 13.0F, 9.0F);
////            setRotationAngle(legBackRight, -0.5236F, 0.0F, 0.0F);
////            legBackRight_r1.setPos(-0.25F, 2.8647F, -1.271F);
//////            setRotationAngle(legBackRight_r1, -0.4363F, 0.0F, 0.0F);
////            legBackRight_r2.setPos(0.25F, 5.6769F, 0.4558F);
//////            setRotationAngle(legBackRight_r2, 1.658F, 0.0F, 0.0F);
////            legBackRight_r3.setPos(0.0F, 8.5037F, 2.7167F);
////            setRotationAngle(legBackRight_r3, -0.48F, 0.0F, 0.0F);
//
//            legBackLeft.setPos(4.5F, 13.0F, 9.0F);
//////            setRotationAngle(legBackLeft, -0.5236F, 0.0F, 0.0F);
////            legBackLeft_r1.setPos(0.25F, 2.9611F, -1.2647F);
//////            setRotationAngle(legBackLeft_r1, -0.4363F, 0.0F, 0.0F);
////            legBackLeft_r2.setPos(-0.25F, 5.7734F, 0.4622F);
//////            setRotationAngle(legBackLeft_r2, 1.658F, 0.0F, 0.0F);
////            legBackLeft_r3.setPos(0.0F, 8.6002F, 2.723F);
////            setRotationAngle(legBackLeft_r3, -0.48F, 0.0F, 0.0F);
//
//            legFrontRight.setPos(-3.5F, 7.0F, -9.0F);
////            legFrontRight_r1.setPos(-0.25F, 0.1449F, -1.0826F);
//////            setRotationAngle(legFrontRight_r1, 0.1745F, 0.0F, 0.0F);
////            legFrontRight_r2.setPos(0.0F, 8.1594F, 0.0467F);
////            setRotationAngle(legFrontRight_r2, -0.1309F, 0.0F, 0.0F);
//
//            legFrontLeft.setPos(3.5F, 7.0F, -9.0F);
////            legFrontLeft_r1.setPos(0.25F, 0.1145F, -0.7354F);
//////            setRotationAngle(legFrontLeft_r1, 0.1745F, 0.0F, 0.0F);
////            legFrontLeft_r2.setPos(0.0F, 8.1594F, 1.0467F);
////            setRotationAngle(legFrontLeft_r2, -0.1309F, 0.0F, 0.0F);
//
//            tail.setPos(0.0F, 14.0F, 13.0F);
//////            setRotationAngle(tail, 1.2293F, 0.0741F, 0.2054F);
////            tail_r1.setPos(-2.194F, 1.9752F, 11.4316F);
//////            setRotationAngle(tail_r1, 1.9756F, -0.3639F, 0.0728F);
////            tail_r2.setPos(-1.0319F, 3.2812F, 7.0949F);
//////            setRotationAngle(tail_r2, 1.6799F, -0.1888F, 0.1091F);
////            tail_r3.setPos(-0.3501F, 2.6437F, 2.9031F);
//////            setRotationAngle(tail_r3, 1.0407F, -0.0096F, 0.1569F);
////            tail_r4.setPos(0.0798F, 0.2199F, 0.5744F);
////            setRotationAngle(tail_r4, 0.3924F, -0.0167F, 0.0403F);
//
//            body.setPos(0.0F, 4.2385F, -1.2138F);
////            body_r1.setPos(0.0F, 8.1901F, 10.4007F);
//////            setRotationAngle(body_r1, 0.7854F, 0.0F, 0.0F);
////            body_r2.setPos(0.0F, 2.5755F, 4.7517F);
//////            setRotationAngle(body_r2, 0.8727F, 0.0F, 0.0F);
////            body_r3.setPos(0.0F, -1.4198F, -5.4415F);
////            setRotationAngle(body_r3, 1.4399F, 0.0F, 0.0F);
//
//            mane.setPos(0.0F, -0.7588F, -5.4838F);
////            setRotationAngle(mane, -0.3927F, 0.0F, 0.0F);
////            mane_r1.setPos(0.0F, -1.1936F, 4.8795F);
//////            setRotationAngle(mane_r1, 1.5272F, 0.0F, 0.0F);
////            mane_r2.setPos(0.0F, 0.2654F, -2.8478F);
////            setRotationAngle(mane_r2, 1.5708F, 0.0F, 0.0F);
//        } else {
//            whole_head.setPos(0.0F, 4.25F, -15.25F);
////            head.setPos(0.0F, 22.5F, 12.0F);
////            head_r1.setPos(-0.25F, -21.993F, -20.2638F);
//////            setRotationAngle(head_r1, -0.9163F, 0.0F, 0.0F);
////            head_r2.setPos(-3.6784F, -25.9265F, -18.7192F);
//////            setRotationAngle(head_r2, 0.2597F, 0.0338F, -0.3883F);
////            head_r3.setPos(3.1411F, -25.9375F, -18.6847F);
////            setRotationAngle(head_r3, 0.2597F, -0.0338F, 0.3883F);
//
//            jaw.setPos(0.0F, -19.03F, -19.5F);
//////            setRotationAngle(jaw, 0.3491F, 0.0F, 0.0F);
////            jaw_r1.setPos(-0.25F, 0.4591F, -1.2625F);
//////            setRotationAngle(jaw_r1, -0.2182F, 0.0F, 0.0F);
////            bone2.setPos(0.0F, 0.0F, 0.0F);
//
//            legBackRight.setPos(-4.5F, 4.5F, 10.5F);
////            setRotationAngle(legBackRight, 0.0873F, 0.0F, 0.0F);
////            legBackRight_r1.setPos(-0.25F, 3.8647F, -1.271F);
//////            setRotationAngle(legBackRight_r1, -0.4363F, 0.0F, 0.0F);
////            legBackRight_r2.setPos(0.0F, 5.6769F, -3.5442F);
//////            setRotationAngle(legBackRight_r2, 0.5672F, 0.0F, 0.0F);
////            legBackRight_r3.setPos(-0.1F, 12.2537F, 0.2167F);
////            setRotationAngle(legBackRight_r3, 3.0543F, 0.0F, 0.0F);
//
//            legBackLeft.setPos(4.5F, 4.5F, 10.5F);
//////            setRotationAngle(legBackLeft, 0.0873F, 0.0F, 0.0F);
////            legBackLeft_r1.setPos(0.25F, 3.9611F, -1.2647F);
//////            setRotationAngle(legBackLeft_r1, -0.4363F, 0.0F, 0.0F);
////            legBackLeft_r2.setPos(0.0F, 5.7734F, -3.5378F);
//////            setRotationAngle(legBackLeft_r2, 0.5672F, 0.0F, 0.0F);
////            legBackLeft_r3.setPos(0.1F, 12.3502F, 0.223F);
//////            setRotationAngle(legBackLeft_r3, 3.0543F, 0.0F, 0.0F);
//
//            legFrontRight.setPos(-3.5F, 7.0F, -12.0F);
////            setRotationAngle(legFrontRight, 0.1745F, 0.0F, 0.0F);
////            legFrontRight_r1.setPos(-0.25F, 0.1449F, -2.0826F);
//////            setRotationAngle(legFrontRight_r1, 0.3927F, 0.0F, 0.0F);
////            legFrontRight_r2.setPos(0.0F, 8.1594F, 0.0467F);
//////            setRotationAngle(legFrontRight_r2, -0.1745F, 0.0F, 0.0F);
//
//            legFrontLeft.setPos(3.5F, 7.0F, -12.0F);
////            setRotationAngle(legFrontLeft, 0.1745F, 0.0F, 0.0F);
////            legFrontLeft_r1.setPos(0.25F, 0.1145F, -1.9854F);
//////            setRotationAngle(legFrontLeft_r1, 0.3927F, 0.0F, 0.0F);
////            legFrontLeft_r2.setPos(0.0F, 8.1594F, 1.0467F);
//////            setRotationAngle(legFrontLeft_r2, -0.1745F, 0.0F, 0.0F);
//
//            tail.setPos(0.0F, 6.0F, 13.0F);
////            setRotationAngle(tail, 0.9675F, 0.0741F, 0.2054F);
////            tail_r1.setPos(-2.194F, 1.9752F, 11.4316F);
//////            setRotationAngle(tail_r1, 1.9756F, -0.3639F, 0.0728F);
////            tail_r2.setPos(-1.0319F, 3.2812F, 7.0949F);
//////            setRotationAngle(tail_r2, 1.6799F, -0.1888F, 0.1091F);
////            tail_r3.setPos(-0.3501F, 2.6437F, 2.9031F);
//////            setRotationAngle(tail_r3, 1.0407F, -0.0096F, 0.1569F);
////            tail_r4.setPos(0.0798F, 0.2199F, 0.5744F);
////            setRotationAngle(tail_r4, 0.3924F, -0.0167F, 0.0403F);
//
//            body.setPos(0.0F, 1.2385F, -2.2138F);
//////            setRotationAngle(body, 0.3491F, 0.0F, 0.0F);
////            body_r1.setPos(0.0F, 8.1901F, 10.4007F);
//////            setRotationAngle(body_r1, 0.7854F, 0.0F, 0.0F);
////            body_r2.setPos(0.0F, 2.5755F, 4.7517F);
//////            setRotationAngle(body_r2, 0.8727F, 0.0F, 0.0F);
////            body_r3.setPos(0.0F, -1.4198F, -6.4415F);
////            setRotationAngle(body_r3, 1.5708F, 0.0F, 0.0F);
//
//            mane.setPos(0.0F, -0.7588F, -5.4838F);
//////            setRotationAngle(mane, -0.3927F, 0.0F, 0.0F);
////            mane_r1.setPos(0.0F, -1.1936F, 3.8795F);
//////            setRotationAngle(mane_r1, 1.5708F, 0.0F, 0.0F);
////            mane_r2.setPos(0.0F, 1.0154F, -4.8478F);
////            setRotationAngle(mane_r2, 1.6144F, 0.0F, 0.0F);
////            this.legFrontRight.yRot = 0.0F;
////            this.legFrontLeft.yRot = 0.0F;
//        }
//
//        this.whole_head.zRot = canis.getInterestedAngle(partialTickTime) + canis.getShakeAngle(partialTickTime, 0.0F);
//        this.mane.zRot = canis.getShakeAngle(partialTickTime, -0.08F);
//        this.body.zRot = canis.getShakeAngle(partialTickTime, -0.16F);
//        this.tail.zRot = canis.getShakeAngle(partialTickTime, -0.2F);
//    }
//
//    @Override
//    public void setupAnim(T canisIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
//        this.legBackRight.xRot = MathHelper.cos(limbSwing * 0.3332F) * 1.1F * limbSwingAmount;
//        this.legBackLeft.xRot = MathHelper.cos(limbSwing * 0.3332F + (float)Math.PI) * 1.1F * limbSwingAmount;
//        this.legFrontRight.xRot = MathHelper.cos(limbSwing * 0.3332F + (float)Math.PI) * 1.1F * limbSwingAmount;
//        this.legFrontLeft.xRot = MathHelper.cos(limbSwing * 0.3332F) * 1.1F * limbSwingAmount;
//
//        this.whole_head.xRot = headPitch * ((float)Math.PI / 200F);
//        this.whole_head.yRot = netHeadYaw * (canisIn.isInSittingPose() && canisIn.isLying() ? 0.005F : (float)Math.PI / 200F);
//        this.tail.xRot = ageInTicks;
//    }
//
//    public void setVisible(boolean visible) {
//        this.whole_head.visible = visible;
//        this.body.visible = visible;
//        this.legBackRight.visible = visible;
//        this.legBackLeft.visible = visible;
//        this.legFrontRight.visible = visible;
//        this.legFrontLeft.visible = visible;
//        this.tail.visible = visible;
//        this.mane.visible = visible;
//    }
//}