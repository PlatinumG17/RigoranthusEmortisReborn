package com.platinumg17.rigoranthusemortisreborn.canis.common.items;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.entity.AbstractCanisEntity;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.interfaces.ICanisItem;

public class ChangeMasterItem extends Item implements ICanisItem {

    public ChangeMasterItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType processInteract(AbstractCanisEntity canisIn, World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (!canisIn.isOwnedBy(playerIn)) {

            if (!worldIn.isClientSide) {
                canisIn.tame(playerIn);
                canisIn.getNavigation().stop();
                canisIn.setTarget((LivingEntity) null);
                canisIn.setOrderedToSit(true);
                worldIn.broadcastEntityEvent(canisIn, EmortisConstants.EntityState.CANIS_HEARTS);

                //TODO playerIn.sendMessage(new TranslationTextComponent(""));
            }

            return ActionResultType.SUCCESS;
        }

        //TODO playerIn.sendMessage(new TranslationTextComponent(""));
        return ActionResultType.FAIL;
    }
}
