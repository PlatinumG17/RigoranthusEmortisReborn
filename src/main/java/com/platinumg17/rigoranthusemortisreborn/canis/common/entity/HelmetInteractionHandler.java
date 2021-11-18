package com.platinumg17.rigoranthusemortisreborn.canis.common.entity;

import com.google.common.collect.ImmutableMap;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.accouterments.CanisAccouterments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.IRegistryDelegate;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.entity.AbstractCanisEntity;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.interfaces.ICanisItem;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.registry.Accoutrement;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.registry.AccoutrementInstance;

import java.util.Map;

public class HelmetInteractionHandler implements ICanisItem {

    private static final Map<IRegistryDelegate<? extends Item>, RegistryObject<? extends Accoutrement>> MAPPING = new ImmutableMap.Builder<IRegistryDelegate<? extends Item>, RegistryObject<? extends Accoutrement>>()
            .put(Items.IRON_HELMET.delegate,          CanisAccouterments.IRON_HELMET)
            .put(Items.DIAMOND_HELMET.delegate,       CanisAccouterments.DIAMOND_HELMET)
            .put(Items.GOLDEN_HELMET.delegate,        CanisAccouterments.GOLDEN_HELMET)
            .put(Items.CHAINMAIL_HELMET.delegate,     CanisAccouterments.CHAINMAIL_HELMET)
            .put(Items.TURTLE_HELMET.delegate,        CanisAccouterments.TURTLE_HELMET)
            .put(Items.NETHERITE_HELMET.delegate,     CanisAccouterments.NETHERITE_HELMET)
            .put(Items.IRON_BOOTS.delegate,           CanisAccouterments.IRON_BOOTS)
            .put(Items.DIAMOND_BOOTS.delegate,        CanisAccouterments.DIAMOND_BOOTS)
            .put(Items.GOLDEN_BOOTS.delegate,         CanisAccouterments.GOLDEN_BOOTS)
            .put(Items.CHAINMAIL_BOOTS.delegate,      CanisAccouterments.CHAINMAIL_BOOTS)
            .put(Items.NETHERITE_BOOTS.delegate,      CanisAccouterments.NETHERITE_BOOTS)
            .put(Items.IRON_CHESTPLATE.delegate,      CanisAccouterments.IRON_BODY_PIECE)
            .put(Items.DIAMOND_CHESTPLATE.delegate,   CanisAccouterments.DIAMOND_BODY_PIECE)
            .put(Items.GOLDEN_CHESTPLATE.delegate,    CanisAccouterments.GOLDEN_BODY_PIECE)
            .put(Items.CHAINMAIL_CHESTPLATE.delegate, CanisAccouterments.CHAINMAIL_BODY_PIECE)
            .put(Items.NETHERITE_CHESTPLATE.delegate, CanisAccouterments.NETHERITE_BODY_PIECE)
            .put(Items.LEATHER_HELMET.delegate,       CanisAccouterments.LEATHER_HELMET)
            .put(Items.LEATHER_BOOTS.delegate,        CanisAccouterments.LEATHER_BOOTS)
            .put(Items.LEATHER_CHESTPLATE.delegate,   CanisAccouterments.LEATHER_BODY_PIECE)
            .build();

    @Override
    public ActionResultType processInteract(AbstractCanisEntity canisIn, World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (canisIn.isTame() && canisIn.canInteract(playerIn)) {
            ItemStack stack = playerIn.getItemInHand(handIn);

            if (!stack.isEmpty()) {
                RegistryObject<? extends Accoutrement> associatedAccoutrement = MAPPING.get(stack.getItem().delegate);

                if (associatedAccoutrement != null) {
                    AccoutrementInstance inst = associatedAccoutrement.get().createFromStack(stack.copy().split(1));

                    if (canisIn.addAccoutrement(inst)) {
                        canisIn.consumeItemFromStack(playerIn, stack);
                        return ActionResultType.SUCCESS;
                    }
                }
            }
        }
        return ActionResultType.PASS;
    }
}
