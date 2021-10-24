package com.platinumg17.rigoranthusemortisreborn.entity.mobs.canis;

import com.mojang.serialization.DataResult;
import com.mojang.serialization.Dynamic;
import com.platinumg17.rigoranthusemortisreborn.config.Config;
import com.platinumg17.rigoranthusemortisreborn.core.init.network.datasync.DataSerializersRigoranthus;
import com.platinumg17.rigoranthusemortisreborn.core.registry.RigoranthusSoundRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.horse.AbstractChestedHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTDynamicOps;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CanisEntity extends AbstractChestedHorseEntity {
    private static final DataParameter<CanisEvolutionData> CANIS_EVOLUTION_DATA = EntityDataManager.defineId(CanisEntity.class, DataSerializersRigoranthus.CANIS_EVOLUTION_DATA);

    protected CanisEntity(EntityType<? extends AbstractChestedHorseEntity> entity, World worldIn) {
        super(entity, worldIn);
    }
    public CanisEvolutionData getCanisEvolutionData() {
        return this.entityData.get(CANIS_EVOLUTION_DATA);
    }

    public void setCanisEvolutionData(@Nonnull CanisEvolutionData data) {
        this.entityData.set(CANIS_EVOLUTION_DATA, data);
    }

    protected void levelUp() {
        this.setCanisEvolutionData(this.getCanisEvolutionData().withLevel(this.getCanisEvolutionData().getLevel() + 1));
    }

    @Override
    public void addAdditionalSaveData(@Nonnull CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        CanisEvolutionData.CODEC.encodeStart(NBTDynamicOps.INSTANCE, this.getCanisEvolutionData()).resultOrPartial(LOGGER::error).ifPresent((data) -> {
            compound.put("CanisEvolutionData", data);
        });
    }

    @Override
    public void readAdditionalSaveData(@Nonnull CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("CanisEvolutionData", 4)) {
            DataResult<CanisEvolutionData> dataresult = CanisEvolutionData.CODEC.parse(new Dynamic<>(NBTDynamicOps.INSTANCE, compound.get("CanisEvolutionData")));
            dataresult.resultOrPartial(LOGGER::error).ifPresent(this::setCanisEvolutionData);
        }
    }

    public void setNewStatsByLevel(CanisEvolutionLevels levels) {
        int currentLevel = this.getCanisEvolutionData().getLevel();
        if (currentLevel == 1) {
            this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(CanisStatsByEvolutionLevel.CHORDATA.getMaxHealthForLevel(levels));
            this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(CanisStatsByEvolutionLevel.CHORDATA.getMovementSpeedForLevel(levels));
            this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(CanisStatsByEvolutionLevel.CHORDATA.getAttackDamageForLevel(levels));
            this.getAttribute(Attributes.ARMOR).setBaseValue(CanisStatsByEvolutionLevel.CHORDATA.getArmorForLevel(levels));
            this.getAttribute(Attributes.ATTACK_KNOCKBACK).setBaseValue(CanisStatsByEvolutionLevel.CHORDATA.getAttackKnockbackForLevel(levels));
            this.getAttribute(Attributes.KNOCKBACK_RESISTANCE).setBaseValue(CanisStatsByEvolutionLevel.CHORDATA.getKnockbackResistanceForLevel(levels));
        }
        if (currentLevel == 2) {
            this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(CanisStatsByEvolutionLevel.KYPHOS.getMaxHealthForLevel(levels));
            this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(CanisStatsByEvolutionLevel.KYPHOS.getMovementSpeedForLevel(levels));
            this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(CanisStatsByEvolutionLevel.KYPHOS.getAttackDamageForLevel(levels));
            this.getAttribute(Attributes.ARMOR).setBaseValue(CanisStatsByEvolutionLevel.KYPHOS.getArmorForLevel(levels));
            this.getAttribute(Attributes.ATTACK_KNOCKBACK).setBaseValue(CanisStatsByEvolutionLevel.KYPHOS.getAttackKnockbackForLevel(levels));
            this.getAttribute(Attributes.KNOCKBACK_RESISTANCE).setBaseValue(CanisStatsByEvolutionLevel.KYPHOS.getKnockbackResistanceForLevel(levels));
        }
        if (currentLevel == 3) {
            this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(CanisStatsByEvolutionLevel.CAVALIER.getMaxHealthForLevel(levels));
            this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(CanisStatsByEvolutionLevel.CAVALIER.getMovementSpeedForLevel(levels));
            this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(CanisStatsByEvolutionLevel.CAVALIER.getAttackDamageForLevel(levels));
            this.getAttribute(Attributes.ARMOR).setBaseValue(CanisStatsByEvolutionLevel.CAVALIER.getArmorForLevel(levels));
            this.getAttribute(Attributes.ATTACK_KNOCKBACK).setBaseValue(CanisStatsByEvolutionLevel.CAVALIER.getAttackKnockbackForLevel(levels));
            this.getAttribute(Attributes.KNOCKBACK_RESISTANCE).setBaseValue(CanisStatsByEvolutionLevel.CAVALIER.getKnockbackResistanceForLevel(levels));
        }
        if (currentLevel == 4) {
            this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(CanisStatsByEvolutionLevel.HOMINI.getMaxHealthForLevel(levels));
            this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(CanisStatsByEvolutionLevel.HOMINI.getMovementSpeedForLevel(levels));
            this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(CanisStatsByEvolutionLevel.HOMINI.getAttackDamageForLevel(levels));
            this.getAttribute(Attributes.ARMOR).setBaseValue(CanisStatsByEvolutionLevel.HOMINI.getArmorForLevel(levels));
            this.getAttribute(Attributes.ATTACK_KNOCKBACK).setBaseValue(CanisStatsByEvolutionLevel.HOMINI.getAttackKnockbackForLevel(levels));
            this.getAttribute(Attributes.KNOCKBACK_RESISTANCE).setBaseValue(CanisStatsByEvolutionLevel.HOMINI.getKnockbackResistanceForLevel(levels));
        }
        throw new IllegalArgumentException("Invalid Evolution Level used while trying to set New Attributes for Canis Chordata");
    }
    @Override protected int getExperienceReward(PlayerEntity player) {return 25 + this.level.random.nextInt(5);}
    protected SoundEvent getAngrySound() {super.getAngrySound();return RigoranthusSoundRegistry.CANIS_HUFF.get();}
    @Nullable protected SoundEvent getEatingSound() {return SoundEvents.MULE_EAT;}
    @Override protected SoundEvent getAmbientSound() {return RigoranthusSoundRegistry.CANIS_AMBIENT.get();}
    @Override protected SoundEvent getDeathSound() {return RigoranthusSoundRegistry.CANIS_DEATH.get();}
    @Override protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return RigoranthusSoundRegistry.CANIS_HURT.get();}
    protected void playChestEquipsSound() {this.playSound(SoundEvents.MULE_CHEST, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);}
}
