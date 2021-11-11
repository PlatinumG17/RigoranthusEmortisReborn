package com.platinumg17.rigoranthusemortisreborn.canis.client.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
//import com.platinumg17.rigoranthusemortisreborn.canis.client.CanisTextureManager;
import com.platinumg17.rigoranthusemortisreborn.canis.client.entity.model.CanisModel;
import com.platinumg17.rigoranthusemortisreborn.canis.client.entity.render.layer.BoneLayer;
import com.platinumg17.rigoranthusemortisreborn.canis.client.entity.render.layer.CanisAccoutrementLayer;
import com.platinumg17.rigoranthusemortisreborn.canis.client.entity.render.layer.CanisSkillLayer;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.CanisEntity;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.Resources;
import com.platinumg17.rigoranthusemortisreborn.config.Config;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class CanisRenderer extends MobRenderer<CanisEntity, CanisModel<CanisEntity>> {

    public CanisRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new CanisModel<>(), 0.5F);
        this.addLayer(new CanisSkillLayer(this));
        this.addLayer(new CanisAccoutrementLayer(this));
        this.addLayer(new BoneLayer(this));
    }

    @Override
    protected float getBob(CanisEntity livingBase, float partialTicks) {
        return livingBase.getTailRotation();
    }

    @Override
    public void render(CanisEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        if (entityIn.isCanisWet()) {
            float f = entityIn.getShadingWhileWet(partialTicks);
            this.model.setColor(f, f, f);
        }
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        if (this.shouldShowName(entityIn)) {
            double d0 = this.entityRenderDispatcher.distanceToSqr(entityIn);
            if (d0 <= 64 * 64) {
                String tip = entityIn.getMode().getTip();
                String label = String.format(Config.CANIS_GENDER.get() ? "%s(%d)%s" : "%s(%d)",(new TranslationTextComponent(tip)).getString(), MathHelper.ceil(entityIn.getCanisHunger()), (new TranslationTextComponent(entityIn.getGender().getUnlocalisedTip())).getString());
                RenderUtil.renderLabelWithScale(entityIn, this, label, matrixStackIn, bufferIn, packedLightIn, 0.01F, 0.12F);
                if (d0 <= 5 * 5 && this.entityRenderDispatcher.camera.getEntity().isShiftKeyDown()) {
                    RenderUtil.renderLabelWithScale(entityIn, this, entityIn.getOwnersName().orElseGet(() -> this.getNameUnknown(entityIn)), matrixStackIn, bufferIn, packedLightIn, 0.01F, -0.25F);
                }
            }
        }
        if (entityIn.isCanisWet()) {this.model.setColor(1.0F, 1.0F, 1.0F);}
    }


    private ITextComponent getNameUnknown(CanisEntity canisIn) {
        return new TranslationTextComponent(canisIn.getOwnerUUID() != null ? "entity.rigoranthusemortisreborn.canis.unknown_owner" : "entity.rigoranthusemortisreborn.canis.untamed");
    }

//    @Override
    public ResourceLocation getTextureLocation(CanisEntity canisIn) {
        return Resources.CHORDATA_TEXTURE;//CanisTextureManager.INSTANCE.getTexture(canisIn);
    }

//    @Override
//    protected void scale(CanisEntity canisIn, MatrixStack matrixStackIn, float partialTickTime) {
//        float size = (float)canisIn.getCanisSize() * 0.3F + 0.1F;
//        matrixStackIn.scale(size, size, size);
//        this.shadowRadius = size * 0.5F;
//    }
}