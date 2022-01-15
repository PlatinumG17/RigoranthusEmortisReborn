package com.platinumg17.rigoranthusemortisreborn.magica.client.renderer.tile;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.IchorCrystallizerTile;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class IchorCrystallizerModel extends AnimatedGeoModel<IchorCrystallizerTile> {

    @Override
    public ResourceLocation getModelLocation(IchorCrystallizerTile object) {
        return RigoranthusEmortisReborn.rl("geo/ichor_crystallizer.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(IchorCrystallizerTile object) {
        return RigoranthusEmortisReborn.rl("textures/blocks/ichor_crystallizer.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(IchorCrystallizerTile animatable) {
        return RigoranthusEmortisReborn.rl("animations/ichor_crystallizer_animations.json");
    }
}