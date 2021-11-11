package com.platinumg17.rigoranthusemortisreborn.canis.client.data;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.CanisEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.WolfModel;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

//@OnlyIn(Dist.CLIENT)
//public class CanisCollarLayer extends LayerRenderer<CanisEntity, WolfModel<CanisEntity>> {
//    private static final ResourceLocation CANIS_COLLAR_LOCATION = new ResourceLocation("rigoranthusemortisreborn:textures/entity/canis/canis_collar.png");
//
//    public CanisCollarLayer(IEntityRenderer<CanisEntity, WolfModel<CanisEntity>> p_i50914_1_) {
//        super(p_i50914_1_);
//    }
//
//    public void render(MatrixStack p_225628_1_, IRenderTypeBuffer p_225628_2_, int p_225628_3_, CanisEntity p_225628_4_, float p_225628_5_, float p_225628_6_, float p_225628_7_, float p_225628_8_, float p_225628_9_, float p_225628_10_) {
//        if (p_225628_4_.isTame() && !p_225628_4_.isInvisible()) {
//            float[] afloat = p_225628_4_.getCollarColor().getTextureDiffuseColors();
//            renderColoredCutoutModel(this.getParentModel(), CANIS_COLLAR_LOCATION, p_225628_1_, p_225628_2_, p_225628_3_, p_225628_4_, afloat[0], afloat[1], afloat[2]);
//        }
//    }
//}