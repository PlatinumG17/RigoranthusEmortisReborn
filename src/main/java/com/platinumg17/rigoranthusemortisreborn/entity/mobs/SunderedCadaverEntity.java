package com.platinumg17.rigoranthusemortisreborn.entity.mobs;

import com.platinumg17.rigoranthusemortisreborn.config.Config;
import com.platinumg17.rigoranthusemortisreborn.core.registry.RigoranthusSoundRegistry;
import com.platinumg17.rigoranthusemortisreborn.core.registry.effects.RigoranthusEffectRegistry;
import com.platinumg17.rigoranthusemortisreborn.entity.goals.SunderedCadaverAttackGoal;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.brain.task.WalkRandomlyTask;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
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
    public static final DataParameter<Integer> STATE = EntityDataManager.defineId(SunderedCadaverEntity.class, DataSerializers.INT);
    private final AnimationFactory animationFactory = new AnimationFactory(this);

    public SunderedCadaverEntity(EntityType<? extends ZombieEntity> type, World worldIn) {
        super(type, worldIn);
        this.noCulling = true;
        //this.moveControl = new MovementController(this);
    }

    private <E extends IAnimatable> PlayState animationPredicate(AnimationEvent<E> event) {
        if (!this.dead && !this.isDeadOrDying()) {
            if (this.getState() == State.ATTACKING) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.sundered_cadaver.attacking", false));
                return PlayState.CONTINUE;
            }
            else if (this.getState() == State.WALKING) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.sundered_cadaver.walking", true));
                return PlayState.CONTINUE;
            }
        }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.sundered_cadaver.idle", true));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController<SunderedCadaverEntity>(this, "controller", 0, this::animationPredicate));
    }
    @Override
    public AnimationFactory getFactory() {
        return this.animationFactory;
    }

    public enum State {
        IDLE, ATTACKING, WALKING
    }

    public State getState() {
        State[] states = State.values();
        return states[MathHelper.clamp(this.entityData.get(STATE), 0, states.length - 1)];
    }

    public void setState(State state) {
        this.entityData.set(STATE, state.ordinal());
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(STATE, 0);
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return 8;
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
    public boolean doHurtTarget(Entity entityIn) {
        if (!super.doHurtTarget(entityIn)) {
            return false;
        } else {
            if (entityIn instanceof LivingEntity) {
                this.setState(State.ATTACKING);
            }
            return true;
        }
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
        this.goalSelector.addGoal(0, new SwimGoal(this));
        //this.goalSelector.addGoal(2, new RandomWalkingGoal(this, 1.0f));
        this.goalSelector.addGoal(5, new MoveTowardsTargetGoal(this, 1.0f, 8));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new SunderedCadaverAttackGoal(this, 1, false));
//        this.goalSelector.addGoal(5, new FollowMobGoal(this, (float) 1, 10, 5));
//        this.targetSelector.addGoal(2, (new HurtByTargetGoal(this)).setAlertOthers(this.getClass()));
    }

//    @Override
//    protected void updateControlFlags() {
//        super.updateControlFlags();
//        this.goalSelector.setControlFlag(Goal.Flag.MOVE, true);
//        this.goalSelector.setControlFlag(Goal.Flag.JUMP, true);
//        this.goalSelector.setControlFlag(Goal.Flag.LOOK, true);
//    }

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
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(RigoranthusSoundRegistry.UNDEAD_STEP.get(), 0.20F, 0.5F);
        this.setState(State.WALKING);
    }
}
