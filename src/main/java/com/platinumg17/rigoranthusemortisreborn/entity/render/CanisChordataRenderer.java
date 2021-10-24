package com.platinumg17.rigoranthusemortisreborn.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.platinumg17.rigoranthusemortisreborn.entity.mobs.canis.CanisChordataEntity;
import com.platinumg17.rigoranthusemortisreborn.entity.model.CanisChordataGeoModel;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;

public class CanisChordataRenderer extends GeoEntityRenderer<CanisChordataEntity> { //implements IGeoRenderer<T> {
//    private static final RenderType[] GLOW = new RenderType[2];
//
//    protected final List<GeoLayerRenderer<T>> layerRenderers = Lists.newArrayList();
//    private final AnimatedGeoModel<T> modelProvider;

    public CanisChordataRenderer(EntityRendererManager renderManager) {
        super(renderManager, new CanisChordataGeoModel());
        this.shadowRadius = 0.7F;

//        this.addLayer(new AbstractEyesLayer<CanisChordataEntity, CanisChordataModel>(this) {
//            @Override
//            public RenderType renderType() {return GLOW[0];}
//            @Override
//            public void render(MatrixStack matrices, IRenderTypeBuffer vertexConsumers, int light, CanisChordataEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
//                IVertexBuilder ivertexbuilder = vertexConsumers.getBuffer(GLOW[entity.getCanisEvolutionLevel()]);
//                this.getParentModel().renderToBuffer(matrices, ivertexbuilder, 15728640, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
//            }
//        });
    }
//    static {
//        GLOW[0] = RenderType.eyes(new ResourceLocation(RigoranthusEmortisReborn.MOD_ID, ("textures/entity/canis/canis_chordata_e.png")));
//    }
    @Override
    public RenderType getRenderType(CanisChordataEntity animatable, float partialTicks, MatrixStack stack, @Nullable IRenderTypeBuffer renderTypeBuffer, @Nullable IVertexBuilder vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(this.getTextureLocation(animatable));
    }
//    @Override
//    public void render(CanisChordataEntity entity, float entityYaw, float partialTicks, MatrixStack stack, IRenderTypeBuffer bufferIn,
//                       int packedLightIn) {
//        stack.pushPose();
//    EntityModelData entityModelData = new EntityModelData();
//    entityModelData.isCanis = entity.isCanis();
}