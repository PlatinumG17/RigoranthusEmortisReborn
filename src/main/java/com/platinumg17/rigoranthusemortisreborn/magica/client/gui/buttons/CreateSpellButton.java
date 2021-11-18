package com.platinumg17.rigoranthusemortisreborn.magica.client.gui.buttons;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.magica.client.gui.book.GuiSpellBook;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.interfaces.SpellValidationError;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public class CreateSpellButton extends GuiImageButton {
    private final ResourceLocation image = new ResourceLocation(EmortisConstants.MOD_ID, "textures/gui/create_icon.png");

    public CreateSpellButton(GuiSpellBook parent, int x, int y, Button.IPressable onPress) {
        super(x, y, 0,0,50, 12, 50, 12, "textures/gui/create_icon.png", onPress);
        this.parent = parent;
    }

    @Override
    public void render(MatrixStack ms, int parX, int parY, float partialTicks) {
        if (visible) {
            if (parent.validationErrors.isEmpty()) {
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            } else {
                GL11.glColor4f(1.0F, 0.7F, 0.7F, 1.0F);
            }

            GuiSpellBook.drawFromTexture(image, x, y, u, v, width, height, image_width, image_height, ms);

            if (parent.isMouseInRelativeRange(parX, parY, x, y, width, height)) {
                if (!parent.validationErrors.isEmpty()) {
                    List<ITextComponent> tooltip = new ArrayList<>();
                    boolean foundGlyphErrors = false;

                    tooltip.add(new TranslationTextComponent("rigoranthusemortisreborn.spell.validation.crafting.invalid").withStyle(TextFormatting.RED));
                    // Add any spell-wide errors
                    for (SpellValidationError error : parent.validationErrors) {
                        if (error.getPosition() < 0) {
                            tooltip.add(error.makeTextComponentExisting());
                        } else {
                            foundGlyphErrors = true;
                        }
                    }
                    // Show a single placeholder for all the per-glyph errors
                    if (foundGlyphErrors) {
                        tooltip.add(new TranslationTextComponent("rigoranthusemortisreborn.spell.validation.crafting.invalid_glyphs"));
                    }
                    parent.tooltip = tooltip;
                }
            }
        }
    }
}
