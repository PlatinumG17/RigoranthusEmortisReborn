package com.platinumg17.rigoranthusemortisreborn.api.apimagic.item;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.client.renderer.IDisplayDominion;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.interfaces.ISpellCaster;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.Spell;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.SpellCaster;
import com.platinumg17.rigoranthusemortisreborn.magica.common.items.SpellBook;
import com.platinumg17.rigoranthusemortisreborn.magica.common.util.PortUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.*;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * An interface for caster items that provides default behavior for scribing, displaying dominion level, and tooltips
 */
public interface ICasterTool extends IScribeable, IDisplayDominion {
    @Override
    default boolean onScribe(World world, BlockPos pos, PlayerEntity player, Hand handIn, ItemStack stack) {
        ItemStack heldStack = player.getItemInHand(handIn);
        ISpellCaster caster = getSpellCaster(stack);

        if(!(heldStack.getItem() instanceof SpellBook) || /*(heldStack.getItem() instanceof SpellParchment)) ||*/ heldStack.getTag() == null)
            return false;
        boolean success = false;
        Spell spell = new Spell();
        if(heldStack.getItem() instanceof SpellBook) {
            spell = SpellBook.getRecipeFromTag(heldStack.getTag(), SpellBook.getMode(heldStack.getTag()));
            caster.setColor(SpellBook.getSpellColor(heldStack.getTag(), SpellBook.getMode(heldStack.getTag())));
            caster.setFlavorText(SpellBook.getSpellName(heldStack.getTag()));
        }
        else if(heldStack.getItem() instanceof ICasterTool){
            SpellCaster heldCaster = SpellCaster.deserialize(heldStack);
            spell = heldCaster.getSpell();
            caster.setColor(heldCaster.getColor());
            caster.setFlavorText(heldCaster.getFlavorText());
        }
        if(isScribedSpellValid(caster, player, handIn, stack, spell)){
            success = setSpell(caster, player, handIn, stack, spell);
            if(success){
                sendSetMessage(player);
                return success;
            }
        }else{
            sendInvalidMessage(player);
        }
        return success;
    }

    default void sendSetMessage(PlayerEntity player){
        PortUtil.sendMessageNoSpam(player, new TranslationTextComponent("rigoranthusemortisreborn.set_spell"));
    }

    default void sendInvalidMessage(PlayerEntity player){
        PortUtil.sendMessageNoSpam(player, new TranslationTextComponent("rigoranthusemortisreborn.invalid_spell"));
    }

    default @Nonnull ISpellCaster getSpellCaster(ItemStack stack){
        return SpellCaster.deserialize(stack);
    }

    default boolean setSpell(ISpellCaster caster, PlayerEntity player, Hand hand, ItemStack stack, Spell spell){
        caster.setSpell(spell);
        return true;
    }

    default boolean isScribedSpellValid(ISpellCaster caster, PlayerEntity player, Hand hand, ItemStack stack, Spell spell){
        return spell.isValid();
    }

    @Override
    default boolean shouldDisplay(ItemStack stack) {
        return true;
    }

    default void getInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip2, ITooltipFlag flagIn) {
//        if(worldIn == null)
//            return;
//        ISpellCaster caster = getSpellCaster(stack);
//
//        if(caster.getSpell().isEmpty()){
//            tooltip2.add(new TranslationTextComponent("rigoranthusemortisreborn.tooltip.can_inscribe"));
//            return;
//        }
//
//        Spell spell = caster.getSpell();
//        tooltip2.add(new StringTextComponent(spell.getDisplayString()));
//        if(!caster.getFlavorText().isEmpty())
//            tooltip2.add(new StringTextComponent(caster.getFlavorText()).withStyle(Style.EMPTY.withItalic(true).withColor(TextFormatting.BLUE)));
    }
}