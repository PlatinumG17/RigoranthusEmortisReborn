package com.platinumg17.rigoranthusemortisreborn.entity.render;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.entity.mobs.CanisChordataEntity;
import com.platinumg17.rigoranthusemortisreborn.entity.model.CanisChordataModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class CanisChordataRenderer extends MobRenderer<CanisChordataEntity, CanisChordataModel<CanisChordataEntity>>
{
    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(RigoranthusEmortisReborn.MOD_ID, "textures/entity/canis_chordata.png");

    public CanisChordataRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new CanisChordataModel<>(), 0.7F);
    }

    @Override
    public ResourceLocation getTextureLocation(CanisChordataEntity entity) {
        return TEXTURE;
    }
}