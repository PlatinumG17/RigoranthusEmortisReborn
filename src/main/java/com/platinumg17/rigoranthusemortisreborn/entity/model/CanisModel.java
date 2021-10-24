package com.platinumg17.rigoranthusemortisreborn.entity.model;

//import com.mojang.blaze3d.matrix.MatrixStack;
//import com.mojang.blaze3d.vertex.IVertexBuilder;
//import com.platinumg17.rigoranthusemortisreborn.entity.mobs.CanisChordataEntity;
//import com.platinumg17.rigoranthusemortisreborn.entity.render.CanisChordataRenderer;
//import com.platinumg17.rigoranthusemortisreborn.entity.mobs.canis.CanisEvolutionLevels;
//import net.minecraft.client.renderer.entity.model.BipedModel;
//import net.minecraft.client.renderer.model.ModelRenderer;
//import net.minecraft.entity.LivingEntity;
//import net.minecraft.inventory.EquipmentSlotType;
//import software.bernie.geckolib3.model.provider.GeoModelProvider;
//import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
//
//public class CanisModel <T extends GeoEntityRenderer<CanisChordataEntity>> extends CanisChordataGeoModel {
//    protected final CanisEvolutionLevels levels;
//    protected ModelRenderer Body;
//
//    public CanisModel(CanisEvolutionLevels levels) {
//        this.levels = levels;
//    }
//
//    @Override
//    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
//        matrixStack.pushPose();
//
//        if (this.levels == CanisEvolutionLevels.CHORDATA) {
//            Body.copyFrom(this.Body);
//            Body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
//            matrixStack.popPose();
//        }
//    }
//}