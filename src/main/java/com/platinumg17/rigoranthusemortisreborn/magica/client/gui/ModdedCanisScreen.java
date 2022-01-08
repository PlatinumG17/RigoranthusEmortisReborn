package com.platinumg17.rigoranthusemortisreborn.magica.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.MainWindow;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

/**
 * @author BaileyHolley
 */
@OnlyIn(Dist.CLIENT)
public class ModdedCanisScreen extends Screen {

    public int maxScale;
    public float scaleFactor;
    public List<ITextComponent> tooltip;

    public ModdedCanisScreen(ITextComponent titleIn) {
        super(titleIn);
    }

    @Override
    public void init() {
        super.init();
        MainWindow res = getMinecraft().getWindow();
        double oldGuiScale = res.calculateScale(minecraft.options.guiScale, minecraft.isEnforceUnicode());
        maxScale = getMaxAllowedScale();
        int persistentScale = Math.min(0, maxScale);;
        double newGuiScale = res.calculateScale(persistentScale, minecraft.isEnforceUnicode());

        if(persistentScale > 0 && newGuiScale != oldGuiScale) {
            scaleFactor = (float) newGuiScale / (float) res.getGuiScale();

            res.setGuiScale(newGuiScale);
            width = res.getGuiScaledWidth();
            height = res.getGuiScaledHeight();
            res.setGuiScale(oldGuiScale);
        } else scaleFactor = 1;
    }
    public boolean isMouseInRelativeRange(int mouseX, int mouseY, int x, int y, int w, int h) {
        return mouseX >= x && mouseX <= x + w && mouseY >= y && mouseY <= y + h;
    }
    public final void drawTooltip(MatrixStack stack, int mouseX, int mouseY) {
        if (tooltip != null && !tooltip.isEmpty()) {
            this.renderWrappedToolTip(stack, tooltip, mouseX, mouseY, font);
        }
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY,partialTicks);
    }

    public final void resetTooltip() {
        tooltip = null;
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    int getMaxAllowedScale() {
        return getMinecraft().getWindow().calculateScale(0, minecraft.isEnforceUnicode());
    }
}