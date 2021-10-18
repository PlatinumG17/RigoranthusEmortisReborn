package com.platinumg17.rigoranthusemortisreborn.entity.mobs;

import com.platinumg17.rigoranthusemortisreborn.config.Config;
import com.platinumg17.rigoranthusemortisreborn.core.registry.RigoranthusSoundRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class SunderedCadaverEntity extends ZombieEntity implements IAnimatable {
    public SunderedCadaverEntity(EntityType<? extends ZombieEntity> type, World worldIn) {
        super(type, worldIn);
        this.noCulling = true;
    }
    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
//    @Override
//    public CreatureAttribute getMobType() {
//        return CreatureAttribute.UNDEAD;
//    }

    public static final DataParameter<Integer> STATE = EntityDataManager.defineId(SunderedCadaverEntity.class, DataSerializers.INT);
    private final AnimationFactory animationFactory = new AnimationFactory(this);

    private <E extends IAnimatable> PlayState animationPredicate(AnimationEvent<E> event) {
        if (!this.dead && !this.isDeadOrDying()) {
            if (this.getState() == SunderedCadaverEntity.State.ATTACKING) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.sundered_cadaver.attacking", false));
                return PlayState.CONTINUE;
            }
        }
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.sundered_cadaver.walking", true));
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.sundered_cadaver.idle", true));
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController<>(this, "controller", 0, this::animationPredicate));
    }
    @Override
    public AnimationFactory getFactory() {
        return this.animationFactory;
    }

    public enum State {
        ATTACKING, IDLE//, WALKING//, MOVING
    }

    public SunderedCadaverEntity.State getState() {
        SunderedCadaverEntity.State[] states = SunderedCadaverEntity.State.values();
        return states[MathHelper.clamp(this.entityData.get(STATE), 0, states.length - 1)];
    }

    public void setState(SunderedCadaverEntity.State state) {
        this.entityData.set(STATE, state.ordinal());
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(STATE, 0);
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return 8;//Config.sunderedCadaverMaxGroupSize.get();
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, Config.sunderedCadaverMaxHealth.get())
                .add(Attributes.MOVEMENT_SPEED, Config.sunderedCadaverMovementSpeed.get())
                .add(Attributes.ATTACK_DAMAGE, Config.sunderedCadaverAttackDamage.get())
                .add(Attributes.ARMOR, Config.sunderedCadaverArmorValue.get())
                .add(Attributes.ATTACK_KNOCKBACK, Config.sunderedCadaverAttackKnockback.get())
                .add(Attributes.KNOCKBACK_RESISTANCE, Config.sunderedCadaverKnockbackResistance.get())
                .add(Attributes.FOLLOW_RANGE, 50.0D).add(Attributes.SPAWN_REINFORCEMENTS_CHANCE);
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source == DamageSource.FALL)
            return false;
        return super.hurt(source, amount);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal( 1, new NearestAttackableTargetGoal<>( this, PlayerEntity.class, true));
        this.goalSelector.addGoal(2, new ZombieAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, (float) 1.1));
        this.goalSelector.addGoal(5, new FollowMobGoal(this, (float) 1, 10, 5));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new SwimGoal(this));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers(this.getClass()));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
    }

    @Override
    protected int getExperienceReward(PlayerEntity player) {
        return 10 + this.level.random.nextInt(5);
    }
    @Override
    protected SoundEvent getAmbientSound() {
        return RigoranthusSoundRegistry.CADAVER_AMBIENT.get();
    }
    @Override
    protected SoundEvent getDeathSound() {
        return RigoranthusSoundRegistry.CADAVER_DEATH.get();
    }
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) { return RigoranthusSoundRegistry.CADAVER_HURT.get();}
    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) { this.playSound(RigoranthusSoundRegistry.UNDEAD_STEP.get(), 0.20F, 0.5F);}
}
