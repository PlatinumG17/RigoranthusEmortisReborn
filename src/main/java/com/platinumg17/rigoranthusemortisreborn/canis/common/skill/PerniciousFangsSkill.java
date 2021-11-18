package com.platinumg17.rigoranthusemortisreborn.canis.common.skill;

import com.platinumg17.rigoranthusemortisreborn.api.apicanis.entity.AbstractCanisEntity;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.registry.Skill;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.registry.SkillInstance;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class PerniciousFangsSkill extends SkillInstance {
    public PerniciousFangsSkill(Skill SkillIn, int levelIn) {
        super(SkillIn, levelIn);
    }

    @Override
    public ActionResultType processInteract(AbstractCanisEntity canisIn, World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (canisIn.isTame()) {
            if (this.level() < 5) {
                return ActionResultType.PASS;
            }
            ItemStack stack = playerIn.getItemInHand(handIn);
            if (stack.getItem() == Items.SPIDER_EYE) {
                if (playerIn.getEffect(Effects.POISON) == null || canisIn.getCanisHunger() < 30) {
                    return ActionResultType.FAIL;
                }
                if (!worldIn.isClientSide) {
                    playerIn.removeAllEffects();
                    canisIn.setCanisHunger(canisIn.getCanisHunger() - 30);
                    canisIn.consumeItemFromStack(playerIn, stack);
                }
                return ActionResultType.SUCCESS;
            }
        }
        return ActionResultType.PASS;
    }

    @Override
    public ActionResultType isPotionApplicable(AbstractCanisEntity canisIn, EffectInstance effectIn) {
        if (this.level() >= 3) {
            if (effectIn.getEffect() == Effects.POISON) {
                return ActionResultType.FAIL;
            }
        }
        return ActionResultType.PASS;
    }

    @Override
    public ActionResultType attackEntityAsMob(AbstractCanisEntity dog, Entity entity) {
        if (entity instanceof LivingEntity) {
            if (this.level() > 0) {
                ((LivingEntity) entity).addEffect(new EffectInstance(Effects.POISON, this.level() * 20, 0));
                return ActionResultType.PASS;
            }
        }
        return ActionResultType.PASS;
    }
}
