package com.platinumg17.rigoranthusemortisreborn.magica.client.gui.buttons;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.canis.client.screen.CanisInfoScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@OnlyIn(Dist.CLIENT)
public class CanisGuiImageButton extends Button {
    protected boolean wasHovered = false;
    private ITextComponent message;
    private ResourceLocation image;
    public String resourceIcon;
    int u, v, image_width, image_height;
    CanisInfoScreen parent;
    TranslationTextComponent toolTip;

    public CanisGuiImageButton( int x, int y,int u,int v,int w, int h, int image_width, int image_height, String resource_image, Button.IPressable onPress) {
        super(x, y, w, h, new StringTextComponent(""), onPress);
        this.x = x;
        this.y = y;
        this.resourceIcon = resource_image;
        this.u = u;
        this.v = v;
        this.image_height = image_height;
        this.image_width = image_width;

        image = RigoranthusEmortisReborn.rl(resource_image);
    }

    public CanisGuiImageButton withTooltip(CanisInfoScreen parent, TranslationTextComponent toolTip){
        this.parent = parent;
        this.toolTip = toolTip;
        return this;
    }

    @Override
    protected void renderBg(MatrixStack matrixStack, Minecraft minecraft, int height, int width) {
    }

    @Override
    public void render(@Nonnull MatrixStack ms, int mouseX, int mouseY, float partialTicks) {
        if (visible) {
            isHovered = mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height;
            beforeRender(ms, mouseX, mouseY, partialTicks);
            renderButton(ms, mouseX, mouseY, partialTicks);
            afterRender(ms, mouseX, mouseY, partialTicks);
            wasHovered = isHovered();

            if(parent != null && parent.isMouseInRelativeRange(mouseX, mouseY, x, y, width, height) && toolTip != null){
                if(!toolTip.toString().isEmpty()){
                    List<ITextComponent> tip = new ArrayList<>();
                    tip.add(toolTip);
                    parent.tooltip = tip;
                }
            }
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            CanisInfoScreen.drawFromTexture(image, x, y, u, v, width, height, image_width, image_height,ms);
        }
    }

    protected void beforeRender(@Nonnull MatrixStack ms, int mouseX, int mouseY, float partialTicks) {
        ms.pushPose();
    }
    @Override
    public void renderButton(@Nonnull MatrixStack ms, int mouseX, int mouseY, float partialTicks) {
    }
    protected void afterRender(@Nonnull MatrixStack ms, int mouseX, int mouseY, float partialTicks) {
        ms.popPose();
    }
    @Override
    public void setMessage(ITextComponent msg) {
        if (!Objects.equals(msg.getString(), this.message.getString())) {
            this.queueNarration(250);
        }
        this.message = msg;
    }
}