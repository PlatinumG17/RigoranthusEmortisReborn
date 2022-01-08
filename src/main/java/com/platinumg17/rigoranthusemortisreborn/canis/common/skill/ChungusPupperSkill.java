package com.platinumg17.rigoranthusemortisreborn.canis.common.skill;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.CanisTags;
import com.platinumg17.rigoranthusemortisreborn.core.init.ItemInit;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.MagicItemsRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.entity.AbstractCanisEntity;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.interfaces.ICanisFoodHandler;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.interfaces.ICanisFoodPredicate;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.registry.Skill;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.registry.SkillInstance;

public class ChungusPupperSkill extends SkillInstance implements ICanisFoodHandler {

    public static final ICanisFoodPredicate INNER_DYN_PRED = (stackIn) -> {
        Item item = stackIn.getItem();
        return item == Items.ROTTEN_FLESH || (item.isEdible() && item.is(ItemTags.FISHES)) || item.is(CanisTags.BONES) || item == MagicItemsRegistry.BOTTLE_OF_ICHOR;
    };

    public ChungusPupperSkill(Skill SkillIn, int levelIn) {
        super(SkillIn, levelIn);
    }

    @Override
    public ActionResult<Float> setCanisHunger(AbstractCanisEntity canisIn, float hunger, float diff) {
        hunger += diff / 10 * this.level();
        return ActionResult.success(hunger);
    }

    @Override
    public boolean isFood(ItemStack stackIn) {
        return ChungusPupperSkill.INNER_DYN_PRED.isFood(stackIn);
    }

    @Override
    public boolean canConsume(AbstractCanisEntity canisIn, ItemStack stackIn, Entity entityIn) {
        if (this.level() >= 1) {
            Item item = stackIn.getItem();
            if(item == MagicItemsRegistry.BOTTLE_OF_ICHOR)
                return true;
            if (this.level() >= 2) {
                if (item == Items.ROTTEN_FLESH)
                    return true;
                if (this.level() >= 3) {
                    if (item.isEdible() && item.is(ItemTags.FISHES))
                        return true;
                    if(this.level() >= 4)
                    return item.is(CanisTags.BONES);
                }
            }
        }
        return false;
    }

    @Override
    public ActionResultType consume(AbstractCanisEntity canisIn, ItemStack stackIn, Entity entityIn) {
        if(this.level() >= 1) {
            Item item = stackIn.getItem();
            if(item == MagicItemsRegistry.BOTTLE_OF_ICHOR) {
                canisIn.addEffect(new EffectInstance(Effects.SATURATION, 200, 1));
                canisIn.addEffect(new EffectInstance(Effects.REGENERATION, 200, 1));
                canisIn.addHunger(40);
                canisIn.consumeItemFromStack(entityIn, stackIn);
            }
            if (this.level() >= 2) {
                if (item == Items.ROTTEN_FLESH) {
                    canisIn.addHunger(30);
                    canisIn.consumeItemFromStack(entityIn, stackIn);
                    return ActionResultType.SUCCESS;
                }
                if (this.level() >= 3) {
                    if (item.isEdible() && item.is(ItemTags.FISHES)) {
                        canisIn.addHunger(item.getFoodProperties().getNutrition() * 5);
                        canisIn.consumeItemFromStack(entityIn, stackIn);
                        return ActionResultType.SUCCESS;
                    }
                    if (this.level() >= 4 && (item.is(CanisTags.BONES))) {
                        canisIn.addHunger(20);
                        canisIn.consumeItemFromStack(entityIn, stackIn);
                        return ActionResultType.SUCCESS;
                    }
                }
            }
        }
        return ActionResultType.FAIL;
    }
}