package com.platinumg17.rigoranthusemortisreborn.canis.client.entity.render.layer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.client.IAccoutrementRenderer;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.registry.AccoutrementInstance;
import com.platinumg17.rigoranthusemortisreborn.canis.client.entity.model.CanisModel;
import com.platinumg17.rigoranthusemortisreborn.canis.client.entity.render.CollarRenderManager;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.CanisEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.provider.GeoModelProvider;
import software.bernie.geckolib3.model.provider.data.EntityModelData;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

import java.awt.*;
import java.util.Collections;
import java.util.List;

public class CanisAccoutrementLayer extends GeoLayerRenderer<CanisEntity> implements IGeoRenderer<CanisEntity> {
    public GeoModelProvider<CanisEntity> modelProvider;
    public CanisAccoutrementLayer(IGeoRenderer<CanisEntity> geoModelProvider) {
        super(geoModelProvider);
//        this.modelProvider = geoModelProvider;
    }

//    @Override
//    public ResourceLocation getModelLocation(CanisEntity canis) {
//        return RigoranthusEmortisReborn.rl("geo/canis/shades.geo.json");
//    }

    @Override
    public GeoModelProvider getGeoModelProvider() {
        return modelProvider;
    }

    @Override
    public ResourceLocation getTextureLocation(CanisEntity canis) {
        List<AccoutrementInstance> accouterments = canis.getAccouterments();

        for (AccoutrementInstance inst : accouterments) {
//            IAccoutrementRenderer renderer = CollarRenderManager.getRendererFor(inst.getAccoutrement());
            ResourceLocation location = inst.getAccoutrement().getRegistryName();
            return location;
        }
        return getTextureLocation(canis);
    }

//    @Override
//    public ResourceLocation getAnimationFileLocation(CanisEntity canis) {
//        return RigoranthusEmortisReborn.rl("animations/canis/tame_canis_chordata.json");
//    }

    @Override
    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, CanisEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        List<AccoutrementInstance> accouterments = entitylivingbaseIn.getAccouterments();
        matrixStackIn.pushPose();
        EntityModelData entityModelData = new EntityModelData();
        entityModelData.headPitch = headPitch;
        entityModelData.netHeadYaw = netHeadYaw;
        GeoModel model = modelProvider.getModel(modelProvider.getModelLocation(entitylivingbaseIn));
        AnimationEvent predicate = new AnimationEvent((IAnimatable)entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, !(limbSwingAmount > -0.15F && limbSwingAmount < 0.15F), Collections.singletonList(entityModelData));

        IBone canis = ((CanisModel)getEntityModel()).getBone("canis");
//        modelProvider.setLivingAnimations((IAnimatable) entitylivingbaseIn, this.getUniqueID((CanisEntity) entitylivingbaseIn), predicate);
        IBone head = ((CanisModel)getEntityModel()).getBone("head");
        IBone whole_body = ((CanisModel)getEntityModel()).getBone("whole_body");
        IBone body = ((CanisModel)getEntityModel()).getBone("body");
        IBone mane = ((CanisModel)getEntityModel()).getBone("mane");

        matrixStackIn.translate((canis.getPositionX())/32f, (canis.getPositionY())/16f ,
                (canis.getPositionZ()));
        Quaternion quaternion = Vector3f.ZP.rotationDegrees(canis.getRotationZ());
        matrixStackIn.mulPose(quaternion);

        Minecraft.getInstance().textureManager.bind(getTextureLocation((CanisEntity) entitylivingbaseIn));
        Color renderColor = getRenderColor((CanisEntity) entitylivingbaseIn, partialTicks, matrixStackIn, bufferIn, null, packedLightIn);
        RenderType renderType = getRenderType((CanisEntity) entitylivingbaseIn, partialTicks, matrixStackIn, bufferIn, null, packedLightIn, getTextureLocation((CanisEntity) entitylivingbaseIn));
        render(model, (CanisEntity) entitylivingbaseIn, partialTicks, renderType, matrixStackIn, bufferIn, null, packedLightIn, OverlayTexture.NO_OVERLAY, (float) renderColor.getRed() / 255f, (float) renderColor.getGreen() / 255f, (float) renderColor.getBlue() / 255f, (float) renderColor.getAlpha() / 255);
        matrixStackIn.popPose();
        for (AccoutrementInstance inst : accouterments) {
            IAccoutrementRenderer renderer = CollarRenderManager.getRendererFor(inst.getAccoutrement());

//            if (renderer != null) {
//                renderer.render(, matrixStackIn, bufferIn, packedLightIn, entitylivingbaseIn, inst, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch);
//            }
        };
    }
}