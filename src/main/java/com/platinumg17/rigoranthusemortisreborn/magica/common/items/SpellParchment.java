package com.platinumg17.rigoranthusemortisreborn.magica.common.items;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.item.ICasterTool;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.AbstractSpellPart;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.Spell;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.SpellCaster;
import com.platinumg17.rigoranthusemortisreborn.magica.common.lib.LibItemNames;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class SpellParchment extends ModItem implements ICasterTool {
    public SpellParchment() {
        super(LibItemNames.SPELL_PARCHMENT);
    }

    public static void setSpell(ItemStack stack, String spellRecipe){
        stack.getOrCreateTag().putString("spell", spellRecipe);
    }

    @Deprecated
    public static List<AbstractSpellPart> getSpellRecipe(ItemStack stack){
        return getSpell(stack).recipe;
    }

    public static @Nonnull
    Spell getSpell(ItemStack stack){
        return SpellCaster.deserialize(stack).getSpell();
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip2, ITooltipFlag flagIn) {
        getInformation(stack, worldIn, tooltip2, flagIn);
        super.appendHoverText(stack, worldIn, tooltip2, flagIn);
    }
}
//    public SpellParchment() {
//        super(LibItemNames.SPELL_PARCHMENT);
//    }
//
//    @Override
//    public void inventoryTick(ItemStack stack, World p_77663_2_, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_) {
//        if(!stack.hasTag())
//            stack.setTag(new CompoundNBT());
//    }
//
//    public static void setSpell(ItemStack stack, String spellRecipe){
//        stack.getOrCreateTag().putString("spell", spellRecipe);
//    }
//
//    @Deprecated
//    public static List<AbstractSpellPart> getSpellRecipe(ItemStack stack){
//        if(!stack.hasTag())
//            return null;
//        return SpellRecipeUtil.getSpellsFromTagString(stack.getOrCreateTag().getString("spell"));
//    }
//
//    @Override
//    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag p_77624_4_) {
//        if(!stack.hasTag() || stack.getOrCreateTag().getString("spell").equals(""))
//            return;
//        List<AbstractSpellPart> spellsFromTagString = SpellRecipeUtil.getSpellsFromTagString(stack.getOrCreateTag().getString("spell"));
//        Spell spell = new Spell(spellsFromTagString);
//        tooltip.add(new StringTextComponent(spell.getDisplayString()));
//    }
//
//    @Override
//    public boolean onScribe(World world, BlockPos pos, PlayerEntity player, Hand handIn, ItemStack thisStack) {
//        if(!(player.getItemInHand(handIn).getItem() instanceof SpellBook))
//            return false;
//        if(SpellBook.getMode(player.getItemInHand(handIn).getOrCreateTag()) == 0){
//            PortUtil.sendMessage(player, new TranslationTextComponent("rigoranthusemortisreborn.spell_parchment.no_spell"));
//            return false;
//        }
//        SpellParchment.setSpell(thisStack, SpellBook.getRecipeString(player.getItemInHand(handIn).getOrCreateTag(), SpellBook.getMode(player.getItemInHand(handIn).getOrCreateTag())));
//        PortUtil.sendMessage(player,new TranslationTextComponent("rigoranthusemortisreborn.spell_parchment.inscribed"));
//        return false;
//    }
//}