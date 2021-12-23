package com.platinumg17.rigoranthusemortisreborn.magica.client.renderer.tile;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.RelayDepositTile;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class RelayDepositModel extends AnimatedGeoModel<RelayDepositTile> {

    @Override
    public ResourceLocation getModelLocation(RelayDepositTile relayDepositTile) {
        return new ResourceLocation(EmortisConstants.MOD_ID, "geo/relays/relay_deposit.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(RelayDepositTile relayDepositTile) {
        return new ResourceLocation(EmortisConstants.MOD_ID, "textures/blocks/relay_deposit.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(RelayDepositTile relayDepositTile) {
        return new ResourceLocation(EmortisConstants.MOD_ID, "animations/relays/relay_deposit_animations.json");
    }
}