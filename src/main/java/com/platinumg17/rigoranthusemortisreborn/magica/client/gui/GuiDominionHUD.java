package com.platinumg17.rigoranthusemortisreborn.magica.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.client.renderer.IDisplayDominion;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.dominion.IDominion;
import com.platinumg17.rigoranthusemortisreborn.magica.client.ClientInfo;
import com.platinumg17.rigoranthusemortisreborn.magica.common.capability.DominionCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.item.ItemStack;

public class GuiDominionHUD extends AbstractGui {
    private static final Minecraft minecraft = Minecraft.getInstance();

    public boolean shouldDisplayBar(){
        ItemStack mainHand = minecraft.player.getMainHandItem();
        ItemStack offHand = minecraft.player.getOffhandItem();
        return (mainHand.getItem() instanceof IDisplayDominion && ((IDisplayDominion) mainHand.getItem()).shouldDisplay(mainHand))
                || (offHand.getItem() instanceof IDisplayDominion && ((IDisplayDominion) offHand.getItem()).shouldDisplay(offHand));
    }

    public void drawHUD(MatrixStack ms, float pt) {
        if(!shouldDisplayBar())
            return;
        IDominion dominion = DominionCapability.getDominion(minecraft.player).orElse(null);
        if(dominion == null)
            return;
        int offsetLeft = 10;
        int dominionLength = 96;
        dominionLength = (int) ((dominionLength) * ((dominion.getCurrentDominion()) / ((double) dominion.getMaxDominion() - 0.0)));
        int height = minecraft.getWindow().getGuiScaledHeight() - 5;
        Minecraft.getInstance().textureManager.bind(RigoranthusEmortisReborn.rl("textures/gui/dominionbar_gui_border.png"));
        blit(ms,offsetLeft, height - 18, 0, 0, 108, 18, 256, 256);
        int dominionOffset = (int) (((ClientInfo.ticksInGame + pt) / 3 % (33))) * 6;
        // 96
        Minecraft.getInstance().textureManager.bind(RigoranthusEmortisReborn.rl("textures/gui/dominionbar_gui_dominion.png"));
        blit(ms,offsetLeft + 9, height - 9, 0, dominionOffset, dominionLength,6, 256, 256);
        Minecraft.getInstance().textureManager.bind(RigoranthusEmortisReborn.rl("textures/gui/dominionbar_gui_border.png"));
        blit(ms,offsetLeft, height - 17, 0, 18, 108, 20, 256, 256);
    }
}