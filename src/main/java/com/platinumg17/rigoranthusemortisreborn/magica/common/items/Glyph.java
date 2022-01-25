package com.platinumg17.rigoranthusemortisreborn.magica.common.items;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.AbstractSpellPart;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.SpellSchool;
import com.platinumg17.rigoranthusemortisreborn.config.Config;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import net.minecraft.util.text.*;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class Glyph extends ModItem {
    public AbstractSpellPart spellPart;
    public Glyph(String registryName, AbstractSpellPart part) {
        super(registryName);
        this.spellPart = part;
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if(worldIn.isClientSide)
            return super.use(worldIn, playerIn, handIn);

        if(!Config.isSpellEnabled(this.spellPart.tag)){
            playerIn.sendMessage(new TranslationTextComponent("rigoranthusemortisreborn.spell.disabled"), Util.NIL_UUID);
            return super.use(worldIn, playerIn, handIn);
        }

        playerIn.inventory.items.forEach(itemStack -> {
            if(itemStack.getItem() instanceof SpellBook){
                if(SpellBook.getUnlockedSpells(itemStack.getTag()).contains(spellPart)){
                    playerIn.sendMessage(new StringTextComponent("You already know this spell!"),  Util.NIL_UUID);
                    return;
                }
                SpellBook.unlockSpell(itemStack.getTag(), this.spellPart.getTag());
                playerIn.getItemInHand(handIn).shrink(1);
                playerIn.sendMessage(new StringTextComponent("Unlocked " + this.spellPart.getName()), Util.NIL_UUID);
            }
        });
        return super.use(worldIn, playerIn, handIn);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip2, ITooltipFlag flagIn) {
        if(spellPart != null){
            if(!Config.isSpellEnabled(this.spellPart.tag)){
                tooltip2.add(new TranslationTextComponent("tooltip.rigoranthusemortisreborn.glyph_disabled"));
            }else if(spellPart != null){
                tooltip2.add(new TranslationTextComponent("tooltip.rigoranthusemortisreborn.glyph_level", spellPart.getTier().ordinal() + 1).setStyle(Style.EMPTY.withColor(TextFormatting.BLUE)));
                tooltip2.add(new TranslationTextComponent("rigoranthusemortisreborn.schools"));
                for(SpellSchool s : spellPart.getSchools()){
                    tooltip2.add(s.getTextComponent());
                }
            }
        }
    }

    public JsonElement asRecipe(){
        JsonObject jsonobject = new JsonObject();
        jsonobject.addProperty("type", "rigoranthusemortisreborn:glyph_recipe");
        jsonobject.addProperty("tier", this.spellPart.getTier().toString());
        if(this.spellPart.getCraftingReagent() != null)
            jsonobject.addProperty("input", this.spellPart.getCraftingReagent().getRegistryName().toString());
        jsonobject.addProperty("output", this.getRegistryName().toString());
        return jsonobject;
    }
}