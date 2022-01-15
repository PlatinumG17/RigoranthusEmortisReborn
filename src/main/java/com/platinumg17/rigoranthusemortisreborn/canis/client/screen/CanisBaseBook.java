package com.platinumg17.rigoranthusemortisreborn.canis.client.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.magica.client.gui.ModdedCanisScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.opengl.GL11;

/**
 * @author BaileyHolley
 * lightly edited by PlatinumG17
 */
@OnlyIn(Dist.CLIENT)
public class CanisBaseBook extends ModdedCanisScreen {

    public final int FULL_WIDTH = 290;
    public final int FULL_HEIGHT = 190;

    public static ResourceLocation background = RigoranthusEmortisReborn.rl("textures/gui/canis_interface.png");
    public int bookLeft;
    public int bookTop;
    public int bookRight;
    public int bookBottom;
    public int bookMiddle;

    public CanisBaseBook() {
        super(new TranslationTextComponent("canisskills.screen.canis.title"));
    }

    @Override
    public void init() {
        super.init();
        this.minecraft.keyboardHandler.setSendRepeatsToGui(true);
        bookLeft = width / 2 - FULL_WIDTH / 2;
        bookTop = height / 2 - FULL_HEIGHT / 2;
        bookRight = width / 2 + FULL_WIDTH / 2;
        bookBottom = height / 2 + FULL_HEIGHT / 2;
        bookMiddle = width / 2;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        GlStateManager._pushMatrix();
        if(scaleFactor != 1) {
            GlStateManager._scalef(scaleFactor, scaleFactor, scaleFactor);
            mouseX /= scaleFactor;
            mouseY /= scaleFactor;
        }
        drawScreenAfterScale(matrixStack, mouseX, mouseY, partialTicks);
        GlStateManager._popMatrix();
    }

    public void drawBackgroundElements(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        Minecraft.getInstance().textureManager.bind(background);
        drawFromTexture(background,0, 0, 0, 0, FULL_WIDTH, FULL_HEIGHT, FULL_WIDTH, FULL_HEIGHT, stack);
    }

    public static void drawFromTexture(ResourceLocation resourceLocation, int x, int y, int u, int v, int w, int h, int fileWidth, int fileHeight, MatrixStack stack) {
        Minecraft.getInstance().textureManager.bind(resourceLocation);
        blit(stack,x, y, u, v, w, h, fileWidth, fileHeight);
    }

    public void drawForegroundElements(int mouseX, int mouseY, float partialTicks) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    public void drawScreenAfterScale(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        resetTooltip();
        renderBackground(stack);
        stack.pushPose();
        stack.translate(bookLeft, bookTop, 0);
        RenderSystem.color3f(1F, 1F, 1F);
        drawBackgroundElements(stack,mouseX, mouseY, partialTicks);
        drawForegroundElements(mouseX, mouseY, partialTicks);
        stack.popPose();
        super.render(stack, mouseX, mouseY, partialTicks);
        drawTooltip(stack, mouseX, mouseY);
    }
}
