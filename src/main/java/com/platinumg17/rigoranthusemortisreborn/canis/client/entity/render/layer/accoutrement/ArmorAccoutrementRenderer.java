package com.platinumg17.rigoranthusemortisreborn.canis.client.entity.render.layer.accoutrement;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.CanisEntity;
import com.platinumg17.rigoranthusemortisreborn.canis.client.entity.model.CanisModel;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.accouterments.ArmorAccoutrement;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.accouterments.CanisAccoutrementTypes;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.client.IAccoutrementRenderer;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.entity.AbstractCanisEntity;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.interfaces.IColoredObject;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.registry.AccoutrementInstance;

public class ArmorAccoutrementRenderer implements IAccoutrementRenderer<CanisEntity> {

    private final CanisModel<CanisEntity> model;
    private ResourceLocation texture;

    public ArmorAccoutrementRenderer(ResourceLocation textureIn) {
        this.model = new CanisModel<>(1.0F);
        this.texture = textureIn;
    }

    @Override
    public void render(LayerRenderer<CanisEntity, EntityModel<CanisEntity>> layer, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, CanisEntity canis, AccoutrementInstance data, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (canis.isTame() && !canis.isInvisible()) {
            ArmorAccoutrement.Instance armorInstance = data.cast(ArmorAccoutrement.Instance.class);
            layer.getParentModel().copyPropertiesTo(this.model);
            this.model.prepareMobModel(canis, limbSwing, limbSwingAmount, partialTicks);
            this.model.setupAnim(canis, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            this.model.setVisible(false);

            if (armorInstance.ofType(CanisAccoutrementTypes.FEET)) {
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
}