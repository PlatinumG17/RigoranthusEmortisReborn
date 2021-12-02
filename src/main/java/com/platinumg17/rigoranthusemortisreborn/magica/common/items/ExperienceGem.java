package com.platinumg17.rigoranthusemortisreborn.magica.common.items;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public abstract class ExperienceGem extends ModItem{

    public ExperienceGem(Properties properties) {
        super(properties);
    }

    public ExperienceGem(Properties properties, String registryName){
        this(properties);
        setRegistryName(EmortisConstants.MOD_ID, registryName);
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