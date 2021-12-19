package com.platinumg17.rigoranthusemortisreborn.magica.common.items;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public abstract class ExperienceGem extends ModItem{

    public ExperienceGem(Properties properties) {
        super(properties);
    }

    public ExperienceGem(Properties properties, String registryName){
        this(properties);
        setRegistryName(EmortisConstants.MOD_ID, registryName);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (Screen.hasShiftDown()) {
            tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".exp_gem").setStyle(Style.EMPTY));
            tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".exp_gem2").setStyle(Style.EMPTY));
        } else {
            tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".hold_shift").setStyle(Style.EMPTY));
        }
    }

    public ActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        if(!world.isClientSide) {
            if(playerEntity.isCrouching()){
                playerEntity.giveExperiencePoints(getValue() * playerEntity.getItemInHand(hand).getCount());
                playerEntity.getItemInHand(hand).shrink( playerEntity.getItemInHand(hand).getCount());
            }else{
                playerEntity.giveExperiencePoints(getValue());
                playerEntity.getItemInHand(hand).shrink(1);
            }
        }
        return ActionResult.pass(playerEntity.getItemInHand(hand));
    }
    public abstract int getValue();
}