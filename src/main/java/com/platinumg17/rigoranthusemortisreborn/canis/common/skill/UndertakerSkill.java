package com.platinumg17.rigoranthusemortisreborn.canis.common.skill;

import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.particles.ParticleTypes;
import com.platinumg17.rigoranthusemortisreborn.api.interfaces.AbstractCanisEntity;
import com.platinumg17.rigoranthusemortisreborn.api.registry.Skill;
import com.platinumg17.rigoranthusemortisreborn.api.registry.SkillInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityPredicates;

import java.util.List;

public class UndertakerSkill extends SkillInstance {
    public UndertakerSkill(Skill SkillIn, int levelIn) {
        super(SkillIn, levelIn);
    }

    @Override
    public void livingTick(AbstractCanisEntity canisIn) {
        if (canisIn.level.isClientSide || canisIn.tickCount % 2 == 0) {
            return;
        }
        if (this.level() >= 0) {
            byte damage = 1;

            if (this.level() >= 5) {
                damage = 2;
            }
            List<ZombieEntity> list = canisIn.level.getEntitiesOfClass(
                    ZombieEntity.class, canisIn.getBoundingBox().inflate(this.level() * 3, 4D, this.level() * 3), EntityPredicates.ENTITY_STILL_ALIVE
            );
            for (ZombieEntity zombie : list) {
                if (canisIn.getRandom().nextInt(10) == 0) {
                    zombie.hurt(DamageSource.GENERIC, damage);
                    canisIn.level.addParticle(ParticleTypes.ENCHANT, canisIn.getRandomX(1.5D), canisIn.getRandomY() + 0.8D, canisIn.getRandomZ(1.5D), 0.0D, 0.0D, 0.0D);
                }
            }
        }
    }
}