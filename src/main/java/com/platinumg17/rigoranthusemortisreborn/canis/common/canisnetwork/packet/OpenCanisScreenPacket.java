package com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.packet;

import com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.IPacket;
import com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.packet.data.OpenCanisScreenData;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.CanisEntity;
import com.platinumg17.rigoranthusemortisreborn.canis.common.inventory.screens.CanisScreens;
import com.platinumg17.rigoranthusemortisreborn.canis.common.skill.WaywardTravellerSkill;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.List;
import java.util.function.Supplier;

public class OpenCanisScreenPacket implements IPacket<OpenCanisScreenData> {

    @Override
    public OpenCanisScreenData decode(PacketBuffer buf) {
        return new OpenCanisScreenData();
    }

    @Override
    public void encode(OpenCanisScreenData data, PacketBuffer buf) {
    }

    @Override
    public void handle(OpenCanisScreenData data, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            if (ctx.get().getDirection().getReceptionSide() == LogicalSide.SERVER) {
                ServerPlayerEntity player = ctx.get().getSender();
                List<CanisEntity> canis = player.level.getEntitiesOfClass(CanisEntity.class, player.getBoundingBox().inflate(12D, 12D, 12D), WaywardTravellerSkill::hasInventory);
                CanisScreens.openCanisInventoriesScreen(player, canis);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
