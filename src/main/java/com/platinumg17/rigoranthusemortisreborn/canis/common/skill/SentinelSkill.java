package com.platinumg17.rigoranthusemortisreborn.canis.common.skill;

import com.platinumg17.rigoranthusemortisreborn.api.interfaces.AbstractCanisEntity;
import com.platinumg17.rigoranthusemortisreborn.api.registry.Skill;
import com.platinumg17.rigoranthusemortisreborn.api.registry.SkillInstance;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvents;

public class SentinelSkill extends SkillInstance {
    private int cooldown;

    public SentinelSkill(Skill SkillIn, int levelIn) {
        super(SkillIn, levelIn);
    }

    @Override
    public SkillInstance copy() {
        SentinelSkill inst = new SentinelSkill(this.getSkill(), this.level);
        inst.cooldown = this.cooldown;
        return inst;
    }

    @Override
    public void init(AbstractCanisEntity canisIn) {
        this.cooldown = canisIn.tickCount;
    }

    @Override
    public void writeToNBT(AbstractCanisEntity canisIn, CompoundNBT compound) {
        super.writeToNBT(canisIn, compound);
        int timeLeft = this.cooldown - canisIn.tickCount;
        compound.putInt("sentineltime", timeLeft);
    }

    @Override
    public void readFromNBT(AbstractCanisEntity canisIn, CompoundNBT compound) {
        super.readFromNBT(canisIn, compound);
        this.cooldown = canisIn.tickCount + compound.getInt("sentineltime");
    }

    @Override
    public ActionResult<Float> attackEntityFrom(AbstractCanisEntity canisIn, DamageSource damageSource, float damage) {
        if (canisIn.level.isClientSide) {
            return ActionResult.pass(damage);
        }
        Entity entity = damageSource.getEntity();
        if (entity != null) {
            int timeLeft =  this.cooldown - canisIn.tickCount;
            if (timeLeft <= 0) {
                int blockChance = this.level() + (this.level() >= 5 ? 1 : 0);
                if (canisIn.getRandom().nextInt(12) < blockChance) {
                    this.cooldown = canisIn.tickCount + 10;
                    canisIn.playSound(SoundEvents.ITEM_BREAK, canisIn.getSoundVolume() / 2, (canisIn.getRandom().nextFloat() - canisIn.getRandom().nextFloat()) * 0.2F + 1.0F);
                    return ActionResult.fail(0F);
                }
            }
        }
        return ActionResult.pass(damage);
    }
}