package com.platinumg17.rigoranthusemortisreborn.magica.common.potions;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

import java.util.ArrayList;
import java.util.List;

public class SummoningCooldownEffect extends Effect {
    protected SummoningCooldownEffect() {
        super(EffectType.HARMFUL, 2039587);
        setRegistryName(EmortisConstants.MOD_ID, "summoned_servant");
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return new ArrayList<>();
    }
}