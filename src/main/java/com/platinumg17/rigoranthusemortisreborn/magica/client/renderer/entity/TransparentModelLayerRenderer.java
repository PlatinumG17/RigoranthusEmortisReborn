package com.platinumg17.rigoranthusemortisreborn.magica.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.platinumg17.rigoranthusemortisreborn.canis.client.entity.model.CanisModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.Entity;
import net.minecraft.item.ArmorItem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

import software.bernie.geckolib3.core.util.Color;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TransparentModelLayerRenderer<T extends Entity & IAnimatable> extends GeoLayerRenderer implements IGeoRenderer<T> {
    AnimatedGeoModel geoModelProvider;
    private static Map<Class<? extends ArmorItem>, GeoArmorRenderer> renderers = new ConcurrentHashMap<>();

    static {
        AnimationController.addModelFetcher((IAnimatable object) -> {
            if (object instanceof Entity) {
            }
            return null;
        });
    }
    public TransparentModelLayerRenderer(IGeoRenderer<T> entityRendererIn, AnimatedGeoModel modelProvider) {
        super(entityRendererIn);
        this.geoModelProvider = modelProvider;
    }

    @Override
    public AnimatedGeoModel<T> getGeoModelProvider() {
        return geoModelProvider;
    }

    @Override
    public ResourceLocation getTextureLocation(T instance) {
        return geoModelProvider.getTextureLocation(instance);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, Entity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        matrixStackIn.pushPose();
        EntityModelData entityModelData = new EntityModelData();
        entityModelData.headPitch = headPitch;
        entityModelData.netHeadYaw = netHeadYaw;
        GeoModel model = geoModelProvider.getModel(geoModelProvider.getModelLocation(entity));
        AnimationEvent predicate = new AnimationEvent((IAnimatable)entity, limbSwing, limbSwingAmount, partialTicks, !(limbSwingAmount > -0.15F && limbSwingAmount < 0.15F), Collections.singletonList(entityModelData));
        IBone canis = ((CanisModel)getEntityModel()).getBone("canis");
        geoModelProvider.setLivingAnimations((IAnimatable) entity, this.getUniqueID((T) entity), predicate);
        IBone head = ((CanisModel)getEntityModel()).getBone("head");
        matrixStackIn.translate((head.getPositionX())/32f, (head.getPositionY())/16f ,
                (head.getPositionZ()));
        matrixStackIn.translate((canis.getPositionX())/32f, (canis.getPositionY())/16f ,
                (canis.getPositionZ()));
        Quaternion quaternion = Vector3f.ZP.rotationDegrees(canis.getRotationZ());
        matrixStackIn.mulPose(quaternion);

        Minecraft.getInstance().textureManager.bind(getTextureLocation((T) entity));
        Color renderColor = getRenderColor((T) entity, partialTicks, matrixStackIn, bufferIn, null, packedLightIn);
        RenderType renderType = RenderType.entityTranslucent(getTextureLocation((T) entity));
        render(model, (T) entity, partialTicks, renderType, matrixStackIn, bufferIn, null, packedLightIn, OverlayTexture.NO_OVERLAY, (float) renderColor.getRed() / 255f, (float) renderColor.getGreen() / 255f, (float) renderColor.getBlue() / 255f, (float) renderColor.getAlpha() / 255);
        matrixStackIn.popPose();
    }
}