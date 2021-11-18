package com.platinumg17.rigoranthusemortisreborn.magica.common.items;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.item.IScribeable;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.AbstractSpellPart;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.Spell;
import com.platinumg17.rigoranthusemortisreborn.magica.common.lib.LibItemNames;
import com.platinumg17.rigoranthusemortisreborn.magica.common.util.PortUtil;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.util.SpellRecipeUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class SpellParchment extends ModItem implements IScribeable {
    public SpellParchment() {
        super(LibItemNames.SPELL_PARCHMENT);
    }

    @Override
    public void inventoryTick(ItemStack stack, World p_77663_2_, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_) {
        if(!stack.hasTag())
            stack.setTag(new CompoundNBT());
    }

    public static void setSpell(ItemStack stack, String spellRecipe){
        stack.getOrCreateTag().putString("spell", spellRecipe);
    }

    @Deprecated
    public static List<AbstractSpellPart> getSpellRecipe(ItemStack stack){
        if(!stack.hasTag())
            return null;
        return SpellRecipeUtil.getSpellsFromTagString(stack.getOrCreateTag().getString("spell"));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag p_77624_4_) {
        if(!stack.hasTag() || stack.getOrCreateTag().getString("spell").equals(""))
            return;

        List<AbstractSpellPart> spellsFromTagString = SpellRecipeUtil.getSpellsFromTagString(stack.getOrCreateTag().getString("spell"));
        Spell spell = new Spell(spellsFromTagString);
        tooltip.add(new StringTextComponent(spell.getDisplayString()));
    }

    @Override
    public boolean onScribe(World world, BlockPos pos, PlayerEntity player, Hand handIn, ItemStack thisStack) {

        if(!(player.getItemInHand(handIn).getItem() instanceof SpellBook))
            return false;

        if(SpellBook.getMode(player.getItemInHand(handIn).getOrCreateTag()) == 0){
            PortUtil.sendMessage(player, new TranslationTextComponent("rigoranthusemortisreborn.spell_parchment.no_spell"));
            return false;
        }

        SpellParchment.setSpell(thisStack, SpellBook.getRecipeString(player.getItemInHand(handIn).getOrCreateTag(), SpellBook.getMode(player.getItemInHand(handIn).getOrCreateTag())));
        PortUtil.sendMessage(player,new TranslationTextComponent("rigoranthusemortisreborn.spell_parchment.inscribed"));
        return false;
    }
}