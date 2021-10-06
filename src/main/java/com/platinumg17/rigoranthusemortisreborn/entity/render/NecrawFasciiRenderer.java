package com.platinumg17.rigoranthusemortisreborn.entity.render;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.entity.mobs.NecrawFasciiEntity;
import com.platinumg17.rigoranthusemortisreborn.entity.model.NecrawFasciiModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class NecrawFasciiRenderer extends MobRenderer<NecrawFasciiEntity, NecrawFasciiModel<NecrawFasciiEntity>>
{
    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(RigoranthusEmortisReborn.MOD_ID, "textures/entity/necraw_fascii.png");

    public NecrawFasciiRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new NecrawFasciiModel<>(), 0.7F);
    }

    @Override
    public ResourceLocation getTextureLocation(NecrawFasciiEntity entity) {
        return TEXTURE;
    }
}