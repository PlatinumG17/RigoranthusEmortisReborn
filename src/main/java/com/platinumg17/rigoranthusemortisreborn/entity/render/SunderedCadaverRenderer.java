package com.platinumg17.rigoranthusemortisreborn.entity.render;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.entity.mobs.SunderedCadaverEntity;
import com.platinumg17.rigoranthusemortisreborn.entity.model.SunderedCadaverModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class SunderedCadaverRenderer extends MobRenderer<SunderedCadaverEntity, SunderedCadaverModel<SunderedCadaverEntity>>
{
    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(RigoranthusEmortisReborn.MOD_ID, "textures/entity/sundered_cadaver.png");

    public SunderedCadaverRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new SunderedCadaverModel<>(), 0.7F);
    }

    @Override
    public ResourceLocation getTextureLocation(SunderedCadaverEntity entity) {
        return TEXTURE;
    }
}