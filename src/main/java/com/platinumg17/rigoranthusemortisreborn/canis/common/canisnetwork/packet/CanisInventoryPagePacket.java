package com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.packet;

import com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.IPacket;
import com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.packet.data.CanisInventoryPageData;
import com.platinumg17.rigoranthusemortisreborn.canis.common.inventory.container.CanisInventoriesContainer;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class CanisInventoryPagePacket implements IPacket<CanisInventoryPageData> {

    @Override
    public CanisInventoryPageData decode(PacketBuffer buf) {
        return new CanisInventoryPageData(buf.readInt());
    }

    @Override
    public void encode(CanisInventoryPageData data, PacketBuffer buf) {
        buf.writeInt(data.page);
    }

    @Override
    public void handle(CanisInventoryPageData data, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            if (ctx.get().getDirection().getReceptionSide() == LogicalSide.SERVER) {
                ServerPlayerEntity player = ctx.get().getSender();
                Container container = player.containerMenu;
                if (container instanceof CanisInventoriesContainer) {
                    CanisInventoriesContainer inventories = (CanisInventoriesContainer) container;
                    int page = MathHelper.clamp(data.page, 0, Math.max(0, inventories.getTotalNumColumns() - 9));

                    inventories.setPage(page);
                    inventories.setData(0, page);
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
