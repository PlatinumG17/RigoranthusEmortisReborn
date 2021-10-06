package com.platinumg17.rigoranthusemortisreborn.entity.render;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.entity.mobs.LanguidDwellerEntity;
import com.platinumg17.rigoranthusemortisreborn.entity.model.LanguidDwellerModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class LanguidDwellerRenderer extends MobRenderer<LanguidDwellerEntity, LanguidDwellerModel<LanguidDwellerEntity>>
{
    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(RigoranthusEmortisReborn.MOD_ID, "textures/entity/languid_dweller.png");

    public LanguidDwellerRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new LanguidDwellerModel<>(), 0.7F);
    }

    @Override
    public ResourceLocation getTextureLocation(LanguidDwellerEntity entity) {
        return TEXTURE;
    }
}