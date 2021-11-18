package com.platinumg17.rigoranthusemortisreborn.magica.common.util;

import com.platinumg17.rigoranthusemortisreborn.magica.common.network.Networking;
import com.platinumg17.rigoranthusemortisreborn.magica.common.network.PacketNoSpamChatMessage;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Util;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class PortUtil {
    public static void sendMessage(Entity playerEntity, ITextComponent component){
        playerEntity.sendMessage(component, Util.NIL_UUID);
    }

    public static void sendMessageNoSpam(Entity playerEntity, ITextComponent component){
        if (playerEntity instanceof PlayerEntity) {
            Networking.sendToPlayer(new PacketNoSpamChatMessage(component, 0, false), (PlayerEntity) playerEntity);
        }
    }

    public static void sendMessageCenterScreen(Entity playerEntity, ITextComponent component){
        if (playerEntity instanceof PlayerEntity) {
            Networking.sendToPlayer(new PacketNoSpamChatMessage(component, 0, true), (PlayerEntity) playerEntity);
        }
    }

    public static void sendMessage(Entity playerEntity, String message){
        sendMessage(playerEntity, new StringTextComponent(message));
    }
}