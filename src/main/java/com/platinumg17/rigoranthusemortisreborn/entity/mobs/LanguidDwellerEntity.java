package com.platinumg17.rigoranthusemortisreborn.entity.mobs;

import com.platinumg17.rigoranthusemortisreborn.config.Config;
import com.platinumg17.rigoranthusemortisreborn.core.registry.RigoranthusSoundRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.SilverfishEntity;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;

public class LanguidDwellerEntity extends SpiderEntity {
    public LanguidDwellerEntity(EntityType<? extends SpiderEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.createMobAttributes()
            .add(Attributes.MAX_HEALTH, Config.languidDwellerMaxHealth.get())
            .add(Attributes.MOVEMENT_SPEED, Config.languidDwellerMovementSpeed.get())
            .add(Attributes.ATTACK_DAMAGE, Config.languidDwellerAttackDamage.get())
            .add(Attributes.ARMOR, Config.languidDwellerArmorValue.get())
            .add(Attributes.ATTACK_KNOCKBACK, Config.languidDwellerAttackKnockback.get())
            .add(Attributes.KNOCKBACK_RESISTANCE, Config.languidDwellerKnockbackResistance.get())
            .add(Attributes.FOLLOW_RANGE, 16.0D);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal( 1, new NearestAttackableTargetGoal<>( this, PlayerEntity.class, true));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.3D, false));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 0.5D));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers(this.getClass()));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
    }

    @Override
    protected int getExperienceReward(PlayerEntity player)
    {
        return 30 + this.level.random.nextInt(5);
    }
    @Override
    protected SoundEvent getAmbientSound()
    {
        return RigoranthusSoundRegistry.DWELLER_AMBIENT.get();
    }
    @Override
    protected SoundEvent getDeathSound() {return RigoranthusSoundRegistry.DWELLER_DEATH.get();}
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return RigoranthusSoundRegistry.DWELLER_HURT.get();}
    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {this.playSound(RigoranthusSoundRegistry.UNDEAD_STEP.get(), 0.20F, 0.5F);}

    @Override
    public boolean doHurtTarget(Entity entityIn) {
        if (!super.doHurtTarget(entityIn)) {
            return false;
        } else {
            if (entityIn instanceof LivingEntity) {
                ((LivingEntity) entityIn).addEffect(new EffectInstance(Effects.UNLUCK, 200));
            }
            return true;
        }
    }
    @Override
    public boolean hurt(DamageSource source, float amount) {
    if (source.getEntity() instanceof PlayerEntity) {
        if ((Math.random() < 0.1)) {
            if (level instanceof ServerWorld) {
                MobEntity entityToSpawn = new SilverfishEntity(EntityType.SILVERFISH, level);
                entityToSpawn.moveTo(this.getX(), this.getY(), this.getZ(), level.getRandom().nextFloat() * 360F, 0);
                entityToSpawn.finalizeSpawn((ServerWorld) level, level.getCurrentDifficultyAt(this.blockPosition()),
                        SpawnReason.MOB_SUMMONED, null, null);
                level.addFreshEntity(entityToSpawn);
            }
        }
    }
    if (source == DamageSource.FALL)
        return false;
    if (source == DamageSource.DROWN)
        return false;
    if (source == DamageSource.LIGHTNING_BOLT)
        return false;
    return super.hurt(source, amount);
    }

    public void aiStep() {
        super.aiStep();
        for(int i = 0; i < 3; i++){
            this.level.addParticle(ParticleTypes.ENCHANT, this.getRandomX(1.0), this.getRandomY(), this.getRandomZ(1.0), 0, 0, 0);
        }
    }
}
