package com.platinumg17.rigoranthusemortisreborn.canis.client.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.interfaces.IThrowableItem;
import com.platinumg17.rigoranthusemortisreborn.canis.client.entity.model.CanisModel;
import com.platinumg17.rigoranthusemortisreborn.canis.client.entity.render.layer.CanisShadesModel;
import com.platinumg17.rigoranthusemortisreborn.canis.client.entity.render.layer.SaddleModel;
import com.platinumg17.rigoranthusemortisreborn.canis.client.entity.render.layer.SatchelModel;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.CanisEntity;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.accouterments.CanisAccouterments;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.config.Config;
import com.platinumg17.rigoranthusemortisreborn.magica.client.renderer.entity.ModelLayerRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import software.bernie.geckolib3.util.RenderUtils;

import javax.annotation.Nullable;

public class CanisRenderer extends GeoEntityRenderer<CanisEntity> {
    private static final ResourceLocation WHITE = new ResourceLocation(EmortisConstants.MOD_ID, "textures/entity/canis/collar_colors/canis_white.png");
    private static final ResourceLocation LIGHT_GRAY = new ResourceLocation(EmortisConstants.MOD_ID, "textures/entity/canis/collar_colors/canis_light_gray.png");
    private static final ResourceLocation GRAY = new ResourceLocation(EmortisConstants.MOD_ID, "textures/entity/canis/collar_colors/canis_gray.png");
    private static final ResourceLocation BLACK = new ResourceLocation(EmortisConstants.MOD_ID, "textures/entity/canis/collar_colors/canis_black.png");
    private static final ResourceLocation BROWN = new ResourceLocation(EmortisConstants.MOD_ID, "textures/entity/canis/collar_colors/canis_brown.png");
    private static final ResourceLocation RED = new ResourceLocation(EmortisConstants.MOD_ID, "textures/entity/canis/collar_colors/canis_red.png");
    private static final ResourceLocation ORANGE = new ResourceLocation(EmortisConstants.MOD_ID, "textures/entity/canis/collar_colors/canis_orange.png");
    private static final ResourceLocation YELLOW = new ResourceLocation(EmortisConstants.MOD_ID, "textures/entity/canis/collar_colors/canis_yellow.png");
    private static final ResourceLocation LIME = new ResourceLocation(EmortisConstants.MOD_ID, "textures/entity/canis/collar_colors/canis_lime.png");
    private static final ResourceLocation GREEN = new ResourceLocation(EmortisConstants.MOD_ID, "textures/entity/canis/collar_colors/canis_green.png");
    private static final ResourceLocation CYAN = new ResourceLocation(EmortisConstants.MOD_ID, "textures/entity/canis/collar_colors/canis_cyan.png");
    private static final ResourceLocation LIGHT_BLUE = new ResourceLocation(EmortisConstants.MOD_ID, "textures/entity/canis/collar_colors/canis_light_blue.png");
    private static final ResourceLocation BLUE = new ResourceLocation(EmortisConstants.MOD_ID, "textures/entity/canis/collar_colors/canis_blue.png");
    private static final ResourceLocation PURPLE = new ResourceLocation(EmortisConstants.MOD_ID, "textures/entity/canis/collar_colors/canis_purple.png");
    private static final ResourceLocation MAGENTA = new ResourceLocation(EmortisConstants.MOD_ID, "textures/entity/canis/collar_colors/canis_magenta.png");
    private static final ResourceLocation PINK = new ResourceLocation(EmortisConstants.MOD_ID, "textures/entity/canis/collar_colors/canis_pink.png");
    private static final ResourceLocation CHORDATA = new ResourceLocation(EmortisConstants.MOD_ID, "textures/entity/canis/tame_canis_chordata.png");

    public CanisRenderer(EntityRendererManager renderManager) {
        super(renderManager, new CanisModel());
        this.addLayer(new ModelLayerRenderer(this, new CanisShadesModel(this.getGeoModelProvider())));
        this.addLayer(new ModelLayerRenderer(this, new SatchelModel(this.getGeoModelProvider())));
        this.addLayer(new ModelLayerRenderer(this, new SaddleModel(this.getGeoModelProvider())));
//        this.addLayer(new CanisSkillLayer(this));
        this.shadowRadius = 0.7F;
    }
    CanisEntity canis;
    IRenderTypeBuffer buffer;
    ResourceLocation text;

    private float r = 0.3F;
    private float g = 0.3F;
    private float b = 0.3F;
//    private float a = 0.3F;

    public void setColor(float red, float green, float blue/*, float alpha*/) {
        this.r = red;
        this.g = green;
        this.b = blue;
/*        this.a = alpha;*/
    }

    @Override
    protected void applyRotations(CanisEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
    }

    @Override
    public void renderEarly(CanisEntity animatable, MatrixStack stackIn, float ticks, IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float partialTicks) {
        this.canis = animatable;
        this.buffer = renderTypeBuffer;
        this.text = this.getTextureLocation(animatable);
        super.renderEarly(animatable, stackIn, ticks, renderTypeBuffer, vertexBuilder, packedLightIn, packedOverlayIn, red, green, blue, partialTicks);
    }

    @Override
    public void render(CanisEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        if (entityIn.isCanisWet()) {
            float f = entityIn.getShadingWhileWet(partialTicks);
            this.setColor(f, f, f);
        }
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        if (this.shouldShowName(entityIn) && !entityIn.isVehicle()) {
            double d0 = this.entityRenderDispatcher.distanceToSqr(entityIn);
            if (d0 <= 64 * 64) {
                String tip = entityIn.getMode().getTip();
                String label = String.format(Config.CANIS_GENDER.get() ? "%s[%d]%s" : "%s[%d]",(new TranslationTextComponent(tip)).getString(), MathHelper.ceil(entityIn.getCanisHunger()), (new TranslationTextComponent(entityIn.getGender().getUnlocalisedTip())).getString());
                RenderUtil.renderLabelWithScale(entityIn, this, label, matrixStackIn, bufferIn, packedLightIn, 0.01F, 0.12F);
                if (d0 <= 5 * 5 && this.entityRenderDispatcher.camera.getEntity().isShiftKeyDown()) {
                    RenderUtil.renderLabelWithScale(entityIn, this, entityIn.getOwnersName().orElseGet(() -> this.getNameUnknown(entityIn)), matrixStackIn, bufferIn, packedLightIn, 0.01F, -0.25F);
                }
            }
        }
        if (entityIn.isCanisWet()) {this.setColor(r, g, b);}
    }

    @Override
    public void renderRecursively(GeoBone bone, MatrixStack stack, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
//        float r = canis.isCanisWet() ? 0.01F : 0.0F;
//        float g = canis.isCanisWet() ? 0.01F : 0.0F;
//        float b = canis.isCanisWet() ? 0.01F : 0.0F;
        if (canis.hasBone()) {
            if (bone.getName().equals("jaw")) {
                stack.pushPose();
                RenderUtils.moveToPivot(bone, stack);
                stack.translate(-0.1F, 0.05F, -0.22F);
                stack.mulPose(Vector3f.YP.rotationDegrees(45.0F));
                stack.mulPose(Vector3f.XP.rotationDegrees(90.0F));

                IThrowableItem throwableItem = canis.getThrowableItem();
                Minecraft.getInstance().getItemInHandRenderer().renderItem(canis, throwableItem != null ? throwableItem.getRenderStack(canis.getBoneVariant()) : canis.getBoneVariant(), ItemCameraTransforms.TransformType.GROUND, false, stack, this.buffer, packedLightIn);
                stack.popPose();
                bufferIn = buffer.getBuffer(RenderType.entityCutoutNoCull(text));
            }
        }
        super.renderRecursively(bone, stack, bufferIn, packedLightIn, packedOverlayIn, red/* + r*/, green/* + g*/, blue/* + b*/, /*this.a **/ alpha);
    }

    public ResourceLocation getColor(CanisEntity e){
        String color = e.getEntityData().get(CanisEntity.COLOR).toLowerCase();
        if(color.isEmpty())
            return WHITE;
        return new ResourceLocation(EmortisConstants.MOD_ID, "textures/entity/canis/collar_colors/canis_" + color +".png");
    }

    @Override
    public ResourceLocation getTextureLocation(CanisEntity entity) {
        return entity.getAccoutrement(CanisAccouterments.DYEABLE_COLLAR.get()).isPresent() ? getColor(entity) : CHORDATA;
    }

    private ITextComponent getNameUnknown(CanisEntity canisIn) {
        return new TranslationTextComponent(canisIn.getOwnerUUID() != null ? "entity.rigoranthusemortisreborn.canis.unknown_owner" : "entity.rigoranthusemortisreborn.canis.untamed");
    }

    @Override
    public RenderType getRenderType(CanisEntity animatable, float partialTicks, MatrixStack stack, @Nullable IRenderTypeBuffer renderTypeBuffer, @Nullable IVertexBuilder vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return RenderType.entityCutoutNoCull(textureLocation);
    }
}



//public class CanisRenderer extends MobRenderer<CanisEntity, CanisModel<CanisEntity>> {
//    public CanisRenderer(EntityRendererManager renderManagerIn) {
//        super(renderManagerIn, new CanisModel<>(), 0.5F);
//        this.addLayer(new CanisSkillLayer(this));
//        this.addLayer(new CanisAccoutrementLayer(this));
//        this.addLayer(new BoneLayer(this));
//        this.shadowRadius = 0.7F;
//    }
//    @Override protected float getBob(CanisEntity livingBase, float partialTicks) { return livingBase.getTailRotation(); }
//    @Override
//    public void render(CanisEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
//        if (entityIn.isCanisWet()) {
//            float f = entityIn.getShadingWhileWet(partialTicks);
//            this.model.setColor(f, f, f);
//        }
//        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
//        if (this.shouldShowName(entityIn)) {
//            double d0 = this.entityRenderDispatcher.distanceToSqr(entityIn);
//            if (d0 <= 64 * 64) {
//                String tip = entityIn.getMode().getTip();
//                String label = String.format(Config.CANIS_GENDER.get() ? "%s[%d]%s" : "%s[%d]",(new TranslationTextComponent(tip)).getString(), MathHelper.ceil(entityIn.getCanisHunger()), (new TranslationTextComponent(entityIn.getGender().getUnlocalisedTip())).getString());
//                RenderUtil.renderLabelWithScale(entityIn, this, label, matrixStackIn, bufferIn, packedLightIn, 0.01F, 0.12F);
//                if (d0 <= 5 * 5 && this.entityRenderDispatcher.camera.getEntity().isShiftKeyDown()) {
//                    RenderUtil.renderLabelWithScale(entityIn, this, entityIn.getOwnersName().orElseGet(() -> this.getNameUnknown(entityIn)), matrixStackIn, bufferIn, packedLightIn, 0.01F, -0.25F);
//                }
//            }
//        }
//        if (entityIn.isCanisWet()) {this.model.setColor(1.0F, 1.0F, 1.0F);}
//    }