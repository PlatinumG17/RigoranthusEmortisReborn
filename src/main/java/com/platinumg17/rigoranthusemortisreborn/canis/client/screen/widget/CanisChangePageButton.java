package com.platinumg17.rigoranthusemortisreborn.canis.client.screen.widget;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SimpleSound;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CanisChangePageButton extends Button {
    private final boolean isForward;
    private final boolean playTurnSound;
    public static final ResourceLocation BOOK_LOC = RigoranthusEmortisReborn.rl("textures/gui/book.png");

    public CanisChangePageButton(int x, int y, boolean forward, Button.IPressable onPress, boolean playSound) {
        super(x, y, 23, 13, StringTextComponent.EMPTY, onPress);
        this.isForward = forward;
        this.playTurnSound = playSound;
    }

    public void renderButton(MatrixStack matrixStack, int parX, int parY, float partialTicks) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft.getInstance().getTextureManager().bind(BOOK_LOC);
        int i = 0;
        int j = 192;
        if (this.isHovered()) {
            i += 23;
        }
        if (!this.isForward) {
            j += 13;
        }
        this.blit(matrixStack, this.x, this.y, i, j, 23, 13);
    }

    public void playDownSound(SoundHandler sound) {
        if (this.playTurnSound) {
            sound.play(SimpleSound.forUI(SoundEvents.BOOK_PAGE_TURN, 1.0F));
        }
    }
}