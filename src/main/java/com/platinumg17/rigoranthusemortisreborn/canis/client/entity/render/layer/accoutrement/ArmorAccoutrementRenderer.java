package com.platinumg17.rigoranthusemortisreborn.canis.client.entity.render.layer.accoutrement;
//
//import com.mojang.blaze3d.matrix.MatrixStack;
//import com.mojang.blaze3d.vertex.IVertexBuilder;
//import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.CanisEntity;
//import com.platinumg17.rigoranthusemortisreborn.canis.client.entity.model.CanisModel;
//import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.accouterments.ArmorAccoutrement;
//import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.accouterments.CanisAccoutrementTypes;
//import net.minecraft.client.renderer.IRenderTypeBuffer;
//import net.minecraft.client.renderer.ItemRenderer;
//import net.minecraft.client.renderer.RenderType;
//import net.minecraft.client.renderer.entity.layers.LayerRenderer;
//import net.minecraft.client.renderer.entity.model.EntityModel;
//import net.minecraft.client.renderer.texture.OverlayTexture;
//import net.minecraft.entity.LivingEntity;
//import net.minecraft.util.ResourceLocation;
//import com.platinumg17.rigoranthusemortisreborn.api.apicanis.client.IAccoutrementRenderer;
//import com.platinumg17.rigoranthusemortisreborn.api.apicanis.entity.AbstractCanisEntity;
//import com.platinumg17.rigoranthusemortisreborn.api.apicanis.interfaces.IColoredObject;
//import com.platinumg17.rigoranthusemortisreborn.api.apicanis.registry.AccoutrementInstance;
//import net.minecraft.util.math.vector.Quaternion;
//import net.minecraft.util.math.vector.Vector3f;
//import software.bernie.geckolib3.core.IAnimatable;
//import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
//import software.bernie.geckolib3.core.processor.IBone;
//import software.bernie.geckolib3.geo.render.built.GeoModel;
//import software.bernie.geckolib3.model.provider.GeoModelProvider;
//import software.bernie.geckolib3.model.provider.data.EntityModelData;
//import software.bernie.geckolib3.renderers.geo.IGeoRenderer;
//
//import java.util.Collections;
//
//
//public class ArmorAccoutrementRenderer implements IAccoutrementRenderer<CanisEntity> {
//
//    private final CanisModel<CanisEntity> model;
//    private ResourceLocation texture;
//
//    public ArmorAccoutrementRenderer(ResourceLocation textureIn) {
//        this.model = new CanisModel<>(1.0F);
//        this.texture = textureIn;
//    }
//
//    @Override
//    public void render(LayerRenderer<CanisEntity, EntityModel<CanisEntity>> layer, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, CanisEntity canis, AccoutrementInstance data, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
//        if (canis.isTame() && !canis.isInvisible()) {
//            ArmorAccoutrement.Instance armorInstance = data.cast(ArmorAccoutrement.Instance.class);
//            layer.getParentModel().copyPropertiesTo(this.model);
//            this.model.prepareMobModel(canis, limbSwing, limbSwingAmount, partialTicks);
//            this.model.setupAnim(canis, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
//            this.model.setVisible(false);
//
//            if (armorInstance.ofType(CanisAccoutrementTypes.FEET)) {
//                this.model.legBackLeft.visible = true;
//                this.model.legBackRight.visible = true;
//                this.model.legFrontLeft.visible = true;
//                this.model.legFrontRight.visible = true;
//            } else if (armorInstance.ofType(CanisAccoutrementTypes.HEAD)) {
//                this.model.head.visible = true;
//            } else if (armorInstance.ofType(CanisAccoutrementTypes.GARMENTS)) {
//                this.model.body.visible = true;
//                this.model.mane.visible = true;
//            } else if (armorInstance.ofType(CanisAccoutrementTypes.TAIL)) {
//                this.model.tail.visible = true;
//            }
//
//            if (armorInstance instanceof IColoredObject) {
//                float[] color = ((IColoredObject) armorInstance).getColor();
//                this.renderArmorCutout(this.model, this.getTexture(canis, data), matrixStackIn, bufferIn, packedLightIn, canis, color[0], color[1], color[2], armorInstance.hasEffect());
//            } else {
//                this.renderArmorCutout(this.model, this.getTexture(canis, data), matrixStackIn, bufferIn, packedLightIn, canis, 1.0F, 1.0F, 1.0F, armorInstance.hasEffect());
//            }
//        }
//    }
//
//    public static <T extends LivingEntity> void renderArmorCutout(EntityModel<T> modelIn, ResourceLocation textureLocationIn, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entityIn, float red, float green, float blue, boolean enchanted) {
//        IVertexBuilder ivertexbuilder = ItemRenderer.getArmorFoilBuffer(bufferIn, RenderType.armorCutoutNoCull(textureLocationIn), false, enchanted);
//        modelIn.renderToBuffer(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, red, green, blue, 1.0F);
//    }
//
//    public <T extends AbstractCanisEntity> ResourceLocation getTexture(T canis, AccoutrementInstance data) {
//        return this.texture;
//    }
//}





/*
public class ArmorAccoutrementRenderer implements IGeoRenderer<CanisEntity> {

    private final GeoModelProvider<CanisEntity> model;
    private ResourceLocation texture;

    public ArmorAccoutrementRenderer(ResourceLocation textureIn) {
        this.model = new CanisModel(*/
/*1.0F*//*
);
        this.texture = textureIn;
    }

    @Override
    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, CanisEntity canis, AccoutrementInstance data, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (canis.isTame() && !canis.isInvisible()) {
            ArmorAccoutrement.Instance armorInstance = data.cast(ArmorAccoutrement.Instance.class);
            matrixStackIn.pushPose();
            EntityModelData entityModelData = new EntityModelData();
            entityModelData.headPitch = headPitch;
            entityModelData.netHeadYaw = netHeadYaw;
            GeoModel geoModel = model.getModel(model.getModelLocation(canis));
            AnimationEvent predicate = new AnimationEvent((IAnimatable)canis, limbSwing, limbSwingAmount, partialTicks, !(limbSwingAmount > -0.15F && limbSwingAmount < 0.15F), Collections.singletonList(entityModelData));

            IBone canis = ((CanisModel)getEntityModel()).getBone("canis");
            model.setLivingAnimations((IAnimatable) canis, this.getUniqueID((CanisEntity) canis), predicate);
            IBone head = ((CanisModel)getEntityModel()).getBone("head");

            matrixStackIn.translate((canis.getPositionX())/32f, (canis.getPositionY())/16f ,
                    (canis.getPositionZ()));
            Quaternion quaternion = Vector3f.ZP.rotationDegrees(canis.getRotationZ());
            matrixStackIn.mulPose(quaternion);

            if (armorInstance.ofType(CanisAccoutrementTypes.FEET)) {
                IBone caniss = ((CanisModel) model).getBone("canis");
                IBone parentHead = model.getModel(model.getModelLocation(la)).getBone("head").get();
                head.setPivotX(parentHead.getPivotX());
                head.setPivotY(parentHead.getPivotY());
                head.setPivotZ(parentHead.getPivotZ());
                head.setRotationX(parentHead.getRotationX());
                head.setRotationY(parentHead.getRotationY());
                head.setRotationZ(parentHead.getRotationZ());
                float scale = 11f;
                head.setPositionY(canis.getPositionY() / 16f);
                head.setPositionZ(canis.getPositionZ() * -1.2f);
                this.model.legBackLeft.visible = true;
                this.model.legBackRight.visible = true;
                this.model.legFrontLeft.visible = true;
                this.model.legFrontRight.visible = true;
            } else if (armorInstance.ofType(CanisAccoutrementTypes.HEAD)) {
                this.model.head.visible = true;
            } else if (armorInstance.ofType(CanisAccoutrementTypes.GARMENTS)) {
                this.model.body.visible = true;
                this.model.mane.visible = true;
            } else if (armorInstance.ofType(CanisAccoutrementTypes.TAIL)) {
                this.model.tail.visible = true;
            }

            if (armorInstance instanceof IColoredObject) {
                float[] color = ((IColoredObject) armorInstance).getColor();
                this.renderArmorCutout(this.model, this.getTexture(canis, data), matrixStackIn, bufferIn, packedLightIn, canis, color[0], color[1], color[2], armorInstance.hasEffect());
            } else {
                this.renderArmorCutout(this.model, this.getTexture(canis, data), matrixStackIn, bufferIn, packedLightIn, canis, 1.0F, 1.0F, 1.0F, armorInstance.hasEffect());
            }
        }
    }

    public static <T extends LivingEntity> void renderArmorCutout(EntityModel<T> modelIn, ResourceLocation textureLocationIn, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entityIn, float red, float green, float blue, boolean enchanted) {
        IVertexBuilder ivertexbuilder = ItemRenderer.getArmorFoilBuffer(bufferIn, RenderType.armorCutoutNoCull(textureLocationIn), false, enchanted);
        modelIn.renderToBuffer(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, red, green, blue, 1.0F);
    }

    public <T extends AbstractCanisEntity> ResourceLocation getTexture(T canis, AccoutrementInstance data) {
        return this.texture;
    }

    @Override
    public GeoModelProvider getGeoModelProvider() {
        return model;
    }

    @Override
    public ResourceLocation getTextureLocation(CanisEntity instance) {
        return this.texture;
    }
}*/
