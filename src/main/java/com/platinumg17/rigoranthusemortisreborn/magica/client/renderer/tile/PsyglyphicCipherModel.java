package com.platinumg17.rigoranthusemortisreborn.magica.client.renderer.tile;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.PsyglyphicCipherTile;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class PsyglyphicCipherModel extends AnimatedGeoModel<PsyglyphicCipherTile> {
    @Override
    public ResourceLocation getModelLocation(PsyglyphicCipherTile cipherTile) {
        return new ResourceLocation(EmortisConstants.MOD_ID , "geo/psyglyphic_cipher.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(PsyglyphicCipherTile cipherTile) {
        return new ResourceLocation(EmortisConstants.MOD_ID, "textures/blocks/psyglyphic_cipher.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(PsyglyphicCipherTile cipherTile) {
        return new ResourceLocation(EmortisConstants.MOD_ID , "animations/psyglyphic_cipher_animation.json");
    }
}