package com.platinumg17.rigoranthusemortisreborn.canis.client.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.platinumg17.rigoranthusemortisreborn.canis.common.inventory.container.FoodBowlContainer;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.Resources;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FoodBowlScreen extends ContainerScreen<FoodBowlContainer> {

    public FoodBowlScreen(FoodBowlContainer foodBowl, PlayerInventory playerInventory, ITextComponent displayName) {
        super(foodBowl, playerInventory, displayName);
        this.imageHeight = 127;
    }

    @Override
    public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(stack);
        super.render(stack, mouseX, mouseY, partialTicks);
        this.renderTooltip(stack, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(MatrixStack stack, int mouseX, int mouseY) {
        this.font.draw(stack, this.title.getString(), 10.0F, 8.0F, 4210752);
    }

    @Override
    protected void renderBg(MatrixStack stack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bind(Resources.GUI_FOOD_BOWL);
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        this.blit(stack, x, y, 0, 0, this.imageWidth, this.imageHeight);
    }
}
