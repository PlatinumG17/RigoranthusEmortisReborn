package com.platinumg17.rigoranthusemortisreborn.magica.client.gui.buttons;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.magica.client.gui.book.GuiFamiliarScreen;
import com.platinumg17.rigoranthusemortisreborn.magica.client.gui.book.GuiSpellBook;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.entity.familiar.AbstractFamiliarHolder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.ArrayList;
import java.util.List;

public class FamiliarButton extends Button {
    public boolean isCraftingSlot;
    public String resourceIcon;
    public String tooltip = "tooltip";

    GuiFamiliarScreen parent;
    public AbstractFamiliarHolder familiarHolder;
    public FamiliarButton(GuiFamiliarScreen parent, int x, int y, boolean isCraftingSlot, AbstractFamiliarHolder familiar) {
        super(x, y,  16, 16, ITextComponent.nullToEmpty(""), parent::onGlyphClick);
        this.parent = parent;
        this.x = x;
        this.y = y;
        this.width = 16;
        this.height = 16;
        this.isCraftingSlot = isCraftingSlot;
        this.resourceIcon = familiar.getImagePath();
        this.familiarHolder = familiar;
    }

    @Override
    public boolean isHovered() {
        return super.isHovered();
    }

    @Override
    public void render(MatrixStack ms, int mouseX, int mouseY, float partialTicks) {
        if (visible) {
            if(this.resourceIcon != null && !this.resourceIcon.equals("")) {
                GuiSpellBook.drawFromTexture(new ResourceLocation(EmortisConstants.MOD_ID, "textures/items/" + this.resourceIcon), x, y, 0, 0, 16, 16,16,16 , ms);
            }
            if(parent.isMouseInRelativeRange(mouseX, mouseY, x, y, width, height)){
                List<ITextComponent> tip = new ArrayList<>();
                if(Screen.hasShiftDown()){
                    tip.add(familiarHolder.getLangDescription());
                }else{
                    tip.add(familiarHolder.getLangName());
                    tip.add(new TranslationTextComponent("tooltip.rigoranthusemortisreborn.hold_shift"));
                }
                parent.tooltip = tip;
            }
        }
    }
}