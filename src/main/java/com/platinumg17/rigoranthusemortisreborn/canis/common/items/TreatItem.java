package com.platinumg17.rigoranthusemortisreborn.canis.common.items;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.feature.CanisLevel;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.entity.AbstractCanisEntity;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.interfaces.ICanisItem;

public class TreatItem extends Item implements ICanisItem {

    private final int maxLevel;
    private final CanisLevel.Type type;

    public TreatItem(int maxLevel, CanisLevel.Type typeIn, Properties properties) {
        super(properties);
        this.maxLevel = maxLevel;
        this.type = typeIn;
    }

    @Override
    public ActionResultType processInteract(AbstractCanisEntity canisIn, World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (!canisIn.isTame() || !canisIn.canInteract(playerIn)) {
            return ActionResultType.FAIL;
        }

        CanisLevel canisLevel = canisIn.getLevel();

        if (canisIn.getAge() < 0) {

            if (!worldIn.isClientSide) {
                worldIn.broadcastEntityEvent(canisIn, EmortisConstants.EntityState.CANIS_SMOKE);
                playerIn.sendMessage(new TranslationTextComponent("treat."+this.type.getName()+".too_young"), canisIn.getUUID());
            }

            return ActionResultType.CONSUME;
        } else if (!canisLevel.canIncrease(this.type)) {

            if (!worldIn.isClientSide) {
                worldIn.broadcastEntityEvent(canisIn, EmortisConstants.EntityState.CANIS_SMOKE);
                playerIn.sendMessage(new TranslationTextComponent("treat."+this.type.getName()+".low_level"), canisIn.getUUID());
            }

            return ActionResultType.CONSUME;
        }
        else if (canisLevel.getLevel(this.type) < this.maxLevel) {

            if (!playerIn.level.isClientSide) {
                if (!playerIn.abilities.instabuild) {
                    playerIn.getItemInHand(handIn).shrink(1);
                }

                canisIn.increaseLevel(this.type);
                canisIn.setHealth(canisIn.getMaxHealth());
                canisIn.setOrderedToSit(true);
                worldIn.broadcastEntityEvent(canisIn, EmortisConstants.EntityState.CANIS_HEARTS);
                playerIn.sendMessage(new TranslationTextComponent("treat."+this.type.getName()+".level_up"), canisIn.getUUID());
            }

            return ActionResultType.SUCCESS;
        }
        else {

            if (!worldIn.isClientSide) {
                worldIn.broadcastEntityEvent(canisIn, EmortisConstants.EntityState.CANIS_SMOKE);
                playerIn.sendMessage(new TranslationTextComponent("treat."+this.type.getName()+".max_level"), canisIn.getUUID());
            }

            return ActionResultType.CONSUME;
        }
    }
}