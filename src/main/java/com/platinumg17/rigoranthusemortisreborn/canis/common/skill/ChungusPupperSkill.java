package com.platinumg17.rigoranthusemortisreborn.canis.common.skill;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import com.platinumg17.rigoranthusemortisreborn.api.interfaces.AbstractCanisEntity;
import com.platinumg17.rigoranthusemortisreborn.api.interfaces.ICanisFoodHandler;
import com.platinumg17.rigoranthusemortisreborn.api.interfaces.ICanisFoodPredicate;
import com.platinumg17.rigoranthusemortisreborn.api.registry.Skill;
import com.platinumg17.rigoranthusemortisreborn.api.registry.SkillInstance;

public class ChungusPupperSkill extends SkillInstance implements ICanisFoodHandler {

    public static final ICanisFoodPredicate INNER_DYN_PRED = (stackIn) -> {
        Item item = stackIn.getItem();
        return item == Items.ROTTEN_FLESH || (item.isEdible() && item.is(ItemTags.FISHES));
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
        if (this.level() >= 3) {
            Item item = stackIn.getItem();
            if (item == Items.ROTTEN_FLESH) {
                return true;
            }
            if (this.level() >= 5 && item.isEdible() && item.is(ItemTags.FISHES)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ActionResultType consume(AbstractCanisEntity canisIn, ItemStack stackIn, Entity entityIn) {
        if (this.level() >= 3) {
            Item item = stackIn.getItem();
            if (item == Items.ROTTEN_FLESH) {
                canisIn.addHunger(30);
                canisIn.consumeItemFromStack(entityIn, stackIn);
                return ActionResultType.SUCCESS;
            }
            if (this.level() >= 5 && item.isEdible() && item.is(ItemTags.FISHES)) {
                canisIn.addHunger(item.getFoodProperties().getNutrition() * 5);
                canisIn.consumeItemFromStack(entityIn, stackIn);
                return ActionResultType.SUCCESS;
            }
        }
        return ActionResultType.FAIL;
    }
}