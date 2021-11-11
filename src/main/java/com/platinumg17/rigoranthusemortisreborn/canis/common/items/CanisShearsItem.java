package com.platinumg17.rigoranthusemortisreborn.canis.common.items;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import com.platinumg17.rigoranthusemortisreborn.api.feature.DataKey;
import com.platinumg17.rigoranthusemortisreborn.api.interfaces.AbstractCanisEntity;
import com.platinumg17.rigoranthusemortisreborn.api.interfaces.ICanisItem;
import com.platinumg17.rigoranthusemortisreborn.api.registry.AccoutrementInstance;

import java.util.List;

public class CanisShearsItem extends Item implements ICanisItem {

    private static DataKey<Integer> COOLDOWN = DataKey.make();

    public CanisShearsItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType processInteract(AbstractCanisEntity canisIn, World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (canisIn.isOwnedBy(playerIn)) {
            List<AccoutrementInstance> accouterments = canisIn.getAccouterments();
            if (accouterments.isEmpty()) {
                if (!canisIn.isTame()) {
                    return ActionResultType.CONSUME;
                }
                if (!worldIn.isClientSide) {
                    int cooldownLeft = canisIn.getDataOrDefault(COOLDOWN, canisIn.tickCount) - canisIn.tickCount;

                    if (cooldownLeft <= 0) {
                        worldIn.broadcastEntityEvent(canisIn, EmortisConstants.EntityState.CANIS_SMOKE);
                        canisIn.untame();
                    }
                }
                return ActionResultType.SUCCESS;
            }

            if (!worldIn.isClientSide) {
                for (AccoutrementInstance inst : accouterments) {
                    ItemStack returnItem = inst.getReturnItem();
                    canisIn.spawnAtLocation(returnItem, 1);
                }
                canisIn.removeAccouterments();
                canisIn.setData(COOLDOWN, canisIn.tickCount + 40);

                return ActionResultType.SUCCESS;
            }
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.FAIL;
    }
}