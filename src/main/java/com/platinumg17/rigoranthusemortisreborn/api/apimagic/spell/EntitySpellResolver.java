package com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;

import java.util.List;

/**
 * A special Spell resolver that ignores player limits such as dominion.
 */
public class EntitySpellResolver extends SpellResolver {

    @Deprecated
    public EntitySpellResolver(List<AbstractSpellPart> spell_recipe, SpellContext context) {
        super(spell_recipe, context);
    }

    public EntitySpellResolver(SpellContext context){
        super(context);
    }

    public void onCastOnEntity(LivingEntity target){
        super.onCastOnEntity(ItemStack.EMPTY, spellContext.caster, target, Hand.MAIN_HAND);
    }

    @Override
    boolean enoughDominion(LivingEntity entity) {
        return true;
    }
}