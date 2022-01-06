package com.platinumg17.rigoranthusemortisreborn.magica.client.gui.buttons;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.magica.client.gui.book.GuiSpellBook;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class GuiImageButton extends Button {

    private ResourceLocation image;
    public String resourceIcon;
    int u, v, image_width, image_height;
    GuiSpellBook parent;
    TranslationTextComponent toolTip;

    public GuiImageButton( int x, int y,int u,int v,int w, int h, int image_width, int image_height, String resource_image, Button.IPressable onPress) {
        super(x, y, w, h, new StringTextComponent(""), onPress);
        this.x = x;
        this.y = y;
        this.resourceIcon = resource_image;
        this.u = u;
        this.v = v;
        this.image_height = image_height;
        this.image_width = image_width;
        image = new ResourceLocation(EmortisConstants.MOD_ID, resource_image);
    }

    public GuiImageButton withTooltip(GuiSpellBook parent, TranslationTextComponent toolTip){
        this.parent = parent;
        this.toolTip = toolTip;
        return this;
    }

    @Override
    protected void renderBg(MatrixStack matrixStack, Minecraft minecraft, int height, int width) {
    }

    @Override
    public void render(MatrixStack ms, int parX, int parY, float partialTicks) {
        if (visible) {
            if(parent != null && parent.isMouseInRelativeRange(parX, parY, x, y, width, height) && toolTip != null){
                if(!toolTip.toString().isEmpty()){
                    List<ITextComponent> tip = new ArrayList<>();
                    tip.add(toolTip);
                    parent.tooltip = tip;
                }
            }
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GuiSpellBook.drawFromTexture(image, x, y, u, v, width, height, image_width, image_height,ms);
        }
    }
}