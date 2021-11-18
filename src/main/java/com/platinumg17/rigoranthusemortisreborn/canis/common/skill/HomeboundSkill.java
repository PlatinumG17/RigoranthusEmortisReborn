package com.platinumg17.rigoranthusemortisreborn.canis.common.skill;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.entity.AbstractCanisEntity;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.registry.Skill;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.registry.SkillInstance;

public class HomeboundSkill extends SkillInstance {

    public HomeboundSkill(Skill SkillIn, int levelIn) {
        super(SkillIn, levelIn);
    }

    @Override
    public void livingTick(AbstractCanisEntity canis) {}

    @Override
    public ActionResultType processInteract(AbstractCanisEntity canisIn, World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (this.level() > 0) {
            if (!playerIn.hasPassenger(canisIn)) {
                if (playerIn.getItemInHand(handIn).getItem() == Items.BONE && canisIn.canInteract(playerIn)) {
                    if (canisIn.startRiding(playerIn)) {
                        if (!canisIn.level.isClientSide) {
                            canisIn.setOrderedToSit(true);
                        }
                        playerIn.displayClientMessage(new TranslationTextComponent("skills.rigoranthusemortisreborn.homebound.cavalier", canisIn.getGenderPronoun()), true);
                        return ActionResultType.SUCCESS;
                    }
                }
            } else {
                canisIn.stopRiding();
                return ActionResultType.SUCCESS;
            }
        }
        return ActionResultType.PASS;
    }
}