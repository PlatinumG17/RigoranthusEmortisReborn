package com.platinumg17.rigoranthusemortisreborn.magica.client.renderer.tile;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.EmorticRelayTile;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class EmorticRelayModel extends AnimatedGeoModel<EmorticRelayTile> {

    @Override
    public ResourceLocation getModelLocation(EmorticRelayTile relayTile) {
        return new ResourceLocation(EmortisConstants.MOD_ID, "geo/relays/emortic_relay.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EmorticRelayTile relayTile) {
        return new ResourceLocation(EmortisConstants.MOD_ID, "textures/blocks/emortic_relay.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EmorticRelayTile relayTile) {
        return new ResourceLocation(EmortisConstants.MOD_ID, "animations/relays/emortic_relay_animations.json");
    }
}