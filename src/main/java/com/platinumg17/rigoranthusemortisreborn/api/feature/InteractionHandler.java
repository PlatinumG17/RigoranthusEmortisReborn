package com.platinumg17.rigoranthusemortisreborn.api.feature;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import com.platinumg17.rigoranthusemortisreborn.api.interfaces.AbstractCanisEntity;
import com.platinumg17.rigoranthusemortisreborn.api.interfaces.ICanisItem;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InteractionHandler {

    private static final List<ICanisItem> HANDLERS = Collections.synchronizedList(new ArrayList<>(4));

    public static void registerHandler(ICanisItem handler) {HANDLERS.add(handler);}

    public static ActionResultType getMatch(@Nullable AbstractCanisEntity canisIn, ItemStack stackIn, PlayerEntity playerIn, Hand handIn) {
        if (stackIn.getItem() instanceof ICanisItem) {
            ICanisItem item = (ICanisItem) stackIn.getItem();
            ActionResultType result = item.processInteract(canisIn, canisIn.level, playerIn, handIn);
            if (result != ActionResultType.PASS) {
                return result;
            }
        }

        for (ICanisItem handler : HANDLERS) {
            ActionResultType result = handler.processInteract(canisIn, canisIn.level, playerIn, handIn);
            if (result != ActionResultType.PASS) {
                return result;
            }
        }
        return ActionResultType.PASS;
    }
}