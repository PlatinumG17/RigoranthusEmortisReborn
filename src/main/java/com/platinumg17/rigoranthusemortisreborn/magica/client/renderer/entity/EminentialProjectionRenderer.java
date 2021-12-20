package com.platinumg17.rigoranthusemortisreborn.magica.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.platinumg17.rigoranthusemortisreborn.magica.common.entity.EntityEminentialProjection;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.*;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.Entity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.ITextComponent;

public class EminentialProjectionRenderer extends LivingRenderer<EntityEminentialProjection, PlayerModel<EntityEminentialProjection>> {

    public EminentialProjectionRenderer(EntityRendererManager entity) {
        this(entity, false);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityEminentialProjection entity) {
        return entity.getSkinTextureLocation();
    }

    public EminentialProjectionRenderer(EntityRendererManager manager, boolean p_i46103_2_) {
        super(manager, new PlayerModel<>(0.0F, p_i46103_2_), 0.5F);
        this.addLayer(new BipedArmorLayer<>(this, new BipedModel(0.5F), new BipedModel(1.0F)));
        this.addLayer(new HeldItemLayer<>(this));
        this.addLayer(new ArrowLayer<>(this));

        this.addLayer(new HeadLayer<>(this));
        this.addLayer(new ElytraLayer<>(this));
        this.addLayer(new SpinAttackEffectLayer<>(this));
        this.addLayer(new BeeStingerLayer<>(this));
    }

    public void render(EntityEminentialProjection entity, float lightIn, float overlayIn, MatrixStack matrix, IRenderTypeBuffer renderTypeBuf, int scale) {
        this.setModelProperties(entity);
        super.render(entity, lightIn, overlayIn, matrix, renderTypeBuf, scale);
    }

    public Vector3d getRenderOffset(EntityEminentialProjection entity, float offset) {
        return entity.isCrouching() ? new Vector3d(0.0D, -0.125D, 0.0D) : super.getRenderOffset(entity, offset);
    }

    private void setModelProperties(EntityEminentialProjection entity) {
        PlayerModel<EntityEminentialProjection> playermodel = this.getModel();
        if (entity.isSpectator()) {
            playermodel.setAllVisible(false);
            playermodel.head.visible = true;
            playermodel.hat.visible = true;
        } else {
            playermodel.setAllVisible(true);
            playermodel.crouching = entity.isCrouching();
            BipedModel.ArmPose bipedmodel$armpose = getArmPose(entity, Hand.MAIN_HAND);
            BipedModel.ArmPose bipedmodel$armpose1 = getArmPose(entity, Hand.OFF_HAND);
            if (bipedmodel$armpose.isTwoHanded()) {
                bipedmodel$armpose1 = entity.getOffhandItem().isEmpty() ? BipedModel.ArmPose.EMPTY : BipedModel.ArmPose.ITEM;
            }
            if (entity.getMainArm() == HandSide.RIGHT) {
                playermodel.rightArmPose = bipedmodel$armpose;
                playermodel.leftArmPose = bipedmodel$armpose1;
            } else {
                playermodel.rightArmPose = bipedmodel$armpose1;
                playermodel.leftArmPose = bipedmodel$armpose;
            }
        }
    }

    private static BipedModel.ArmPose getArmPose(EntityEminentialProjection entity, Hand handIn) {
        ItemStack itemstack = entity.getItemInHand(handIn);
        if (itemstack.isEmpty()) {
            return BipedModel.ArmPose.EMPTY;
        } else {
            if (entity.getUsedItemHand() == handIn && entity.getUseItemRemainingTicks() > 0) {
                UseAction useaction = itemstack.getUseAnimation();
                if (useaction == UseAction.BLOCK) {
                    return BipedModel.ArmPose.BLOCK;
                }
                if (useaction == UseAction.BOW) {
                    return BipedModel.ArmPose.BOW_AND_ARROW;
                }
                if (useaction == UseAction.SPEAR) {
                    return BipedModel.ArmPose.THROW_SPEAR;
                }
                if (useaction == UseAction.CROSSBOW && handIn == entity.getUsedItemHand()) {
                    return BipedModel.ArmPose.CROSSBOW_CHARGE;
                }
            } else if (!entity.swinging && itemstack.getItem() == Items.CROSSBOW && CrossbowItem.isCharged(itemstack)) {
                return BipedModel.ArmPose.CROSSBOW_HOLD;
            }
            return BipedModel.ArmPose.ITEM;
        }
    }

    protected void scale(EntityEminentialProjection entity, MatrixStack matrix, float num) {
        float f = 0.9375F;
        matrix.scale(0.9375F, 0.9375F, 0.9375F);
    }

    protected void renderNameTag(EntityEminentialProjection entity, ITextComponent name, MatrixStack matrix, IRenderTypeBuffer renderTypeBuf, int size) {
        double d0 = this.entityRenderDispatcher.distanceToSqr(entity);
        matrix.pushPose();

        super.renderNameTag(entity, name, matrix, renderTypeBuf, size);
        matrix.popPose();
    }

    public void renderRightHand(MatrixStack matrix, IRenderTypeBuffer renderType, int scale, EntityEminentialProjection entity) {
        this.renderHand(matrix, renderType, scale, entity, (this.model).rightArm, (this.model).rightSleeve);
    }

    public void renderLeftHand(MatrixStack matrix, IRenderTypeBuffer renderType, int scale, EntityEminentialProjection entity) {
        this.renderHand(matrix, renderType, scale, entity, (this.model).leftArm, (this.model).leftSleeve);
    }

    private void renderHand(MatrixStack matrix, IRenderTypeBuffer renderType, int scale, EntityEminentialProjection entity, ModelRenderer model1, ModelRenderer model2) {
        PlayerModel<EntityEminentialProjection> playermodel = this.getModel();
        this.setModelProperties(entity);
        playermodel.attackTime = 0.0F;
        playermodel.crouching = false;
        playermodel.swimAmount = 0.0F;
        playermodel.setupAnim(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        model1.xRot = 0.0F;
        model1.render(matrix, renderType.getBuffer(RenderType.entitySolid(entity.getSkinTextureLocation())), scale, OverlayTexture.NO_OVERLAY);
        model2.xRot = 0.0F;
        model2.render(matrix, renderType.getBuffer(RenderType.entityTranslucent(entity.getSkinTextureLocation())), scale, OverlayTexture.NO_OVERLAY);
    }

    protected void setupRotations(EntityEminentialProjection entity, MatrixStack matrix, float x, float y, float z) {
        float f = entity.getSwimAmount(z);
        if (entity.isFallFlying()) {
            super.setupRotations(entity, matrix, x, y, z);
            float f1 = (float)entity.getFallFlyingTicks() + z;
            float f2 = MathHelper.clamp(f1 * f1 / 100.0F, 0.0F, 1.0F);
            if (!entity.isAutoSpinAttack()) {
                matrix.mulPose(Vector3f.XP.rotationDegrees(f2 * (-90.0F - entity.xRot)));
            }
            Vector3d vector3d = entity.getViewVector(z);
            Vector3d vector3d1 = entity.getDeltaMovement();
            double d0 = Entity.getHorizontalDistanceSqr(vector3d1);
            double d1 = Entity.getHorizontalDistanceSqr(vector3d);
            if (d0 > 0.0D && d1 > 0.0D) {
                double d2 = (vector3d1.x * vector3d.x + vector3d1.z * vector3d.z) / Math.sqrt(d0 * d1);
                double d3 = vector3d1.x * vector3d.z - vector3d1.z * vector3d.x;
                matrix.mulPose(Vector3f.YP.rotation((float)(Math.signum(d3) * Math.acos(d2))));
            }
        } else if (f > 0.0F) {
            super.setupRotations(entity, matrix, x, y, z);
            float f3 = entity.isInWater() ? -90.0F - entity.xRot : -90.0F;
            float f4 = MathHelper.lerp(f, 0.0F, f3);
            matrix.mulPose(Vector3f.XP.rotationDegrees(f4));
            if (entity.isVisuallySwimming()) {
                matrix.translate(0.0D, -1.0D, 0.3F);
            }
        } else {
            super.setupRotations(entity, matrix, x, y, z);
        }
    }
}