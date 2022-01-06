package com.platinumg17.rigoranthusemortisreborn.magica.common.items;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.item.ICasterTool;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.interfaces.ISpellCaster;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.Spell;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.util.DominionUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.*;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class LostTome extends ModItem implements ICasterTool {
    public LostTome(Properties properties) {
        super(properties);
    }

    public LostTome(Properties properties, String registryName) {
        super(properties, registryName);
    }

    public LostTome(String registryName) {
        super(registryName);
    }

    @Override
    public boolean onScribe(World world, BlockPos pos, PlayerEntity player, Hand handIn, ItemStack stack) {
        return player.isCreative() && ICasterTool.super.onScribe(world, pos, player, handIn, stack);
    }


    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack stack = playerIn.getItemInHand(handIn);
        ISpellCaster caster = getSpellCaster(stack);
        Spell spell = caster.getSpell();
        spell.setCost(Math.min(spell.getCastingCost()/2, DominionUtil.getMaxDominion(playerIn))); // Let even a new player cast 1 charge of a tome
        return caster.castSpell(worldIn, playerIn, handIn, new TranslationTextComponent(""), spell);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip2, ITooltipFlag flagIn) {
        if(worldIn == null)
            return;
        ISpellCaster caster = getSpellCaster(stack);

        Spell spell = caster.getSpell();
        tooltip2.add(new StringTextComponent(spell.getDisplayString()));
        if(!caster.getFlavorText().isEmpty())
            tooltip2.add(new StringTextComponent(caster.getFlavorText()).withStyle(Style.EMPTY.withItalic(true).withColor(TextFormatting.BLUE)));

        tooltip2.add(new TranslationTextComponent("tooltip.rigoranthusemortisreborn.lost_tome"));
        super.appendHoverText(stack, worldIn, tooltip2, flagIn);
    }
}