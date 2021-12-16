package com.platinumg17.rigoranthusemortisreborn.entity.mobs;

import com.platinumg17.rigoranthusemortisreborn.canis.common.SpecializedEntityTypes;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.CanisEntity;
import com.platinumg17.rigoranthusemortisreborn.config.Config;
import com.platinumg17.rigoranthusemortisreborn.core.init.ItemInit;
import com.platinumg17.rigoranthusemortisreborn.entity.goals.CanisAttackGoal;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.IAnimationListener;
import com.platinumg17.rigoranthusemortisreborn.magica.common.entity.ModEntities;
import com.platinumg17.rigoranthusemortisreborn.magica.common.util.PortUtil;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.function.Predicate;

public class FeralCanisEntity extends MonsterEntity implements IAnimatable, IAnimationListener {

    private static final Predicate<Difficulty> DOOR_BREAKING_PREDICATE = (p_213697_0_) -> {return p_213697_0_ == Difficulty.HARD;};
    public static final DataParameter<Integer> STATE = EntityDataManager.defineId(FeralCanisEntity.class, DataSerializers.INT);
    private final AnimationFactory animationFactory = new AnimationFactory(this);
    private final BreakDoorGoal breakDoorGoal = new BreakDoorGoal(this, DOOR_BREAKING_PREDICATE);
    private boolean canBreakDoors;

    public FeralCanisEntity(EntityType<FeralCanisEntity> entity, World worldIn) {
        super(entity, worldIn);
        this.noCulling = true;
    }

    public FeralCanisEntity(World p_i50190_2_) {
        super(ModEntities.FERAL_CANIS, p_i50190_2_);
    }

    @Override
    public EntityType<?> getType() {
        return ModEntities.FERAL_CANIS;
    }

    private <E extends IAnimatable> PlayState walkPredicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", true));
            return PlayState.CONTINUE;
        }
        return PlayState.STOP;
    }

    private <E extends Entity> PlayState attackPredicate(AnimationEvent event) {
        return PlayState.CONTINUE;
    }

    private <E extends Entity> PlayState idlePredicate(AnimationEvent event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
        return PlayState.CONTINUE;
    }

    public static AttributeModifierMap.MutableAttribute attributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, Config.feralCanisChordataMaxHealth.get())
                .add(Attributes.MOVEMENT_SPEED, Config.feralCanisChordataMovementSpeed.get())
                .add(Attributes.ATTACK_DAMAGE, Config.feralCanisChordataAttackDamage.get())
                .add(Attributes.ARMOR, Config.feralCanisChordataArmorValue.get())
                .add(Attributes.ATTACK_KNOCKBACK, Config.feralCanisChordataAttackKnockback.get())
                .add(Attributes.KNOCKBACK_RESISTANCE, Config.feralCanisChordataKnockbackResistance.get())
                .add(Attributes.FOLLOW_RANGE, 25.0D);
    }

    public boolean canAttack(){
        return getTarget() != null && this.getHealth() >= 1;
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController<>(this, "walkController", 0, this::walkPredicate));
        animationData.addAnimationController(new AnimationController<>(this, "attackController", 1, this::attackPredicate));
        animationData.addAnimationController(new AnimationController<>(this, "idleController", 0, this::idlePredicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.animationFactory;
    }

    @Override
    public boolean causeFallDamage(float distance, float damageMultiplier) {
        return false;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(STATE, 0);
    }

    public enum State {IDLE, ATTACKING}

    public State getState() {
        State[] states = State.values();
        return states[MathHelper.clamp(this.entityData.get(STATE), 0, states.length - 1)];
    }

    public void setState(State state) {
        this.entityData.set(STATE, state.ordinal());
    }

    @Override
    protected void updateControlFlags() {
        super.updateControlFlags();
        this.goalSelector.setControlFlag(Goal.Flag.MOVE, true);
        this.goalSelector.setControlFlag(Goal.Flag.JUMP, true);
        this.goalSelector.setControlFlag(Goal.Flag.LOOK, true);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.addBehaviourGoals();
    }
    protected void addBehaviourGoals() {
        this.goalSelector.addGoal(2, new CanisAttackGoal(this,true));
        this.goalSelector.addGoal(5, new MoveTowardsTargetGoal(this, 1.0f, 8));
        this.goalSelector.addGoal(6, new MoveThroughVillageGoal(this, 1.0D, true, 4, this::canBreakDoors));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new SwimGoal(this));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers(this.getClass()));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
    }

    public boolean canBreakDoors() {
        return this.canBreakDoors;
    }

    public void setCanBreakDoors(boolean canBreak) {
        if (this.supportsBreakDoorGoal() && GroundPathHelper.hasGroundPathNavigation(this)) {
            if (this.canBreakDoors != canBreak) {
                this.canBreakDoors = canBreak;
                ((GroundPathNavigator)this.getNavigation()).setCanOpenDoors(canBreak);
                if (canBreak) {
                    this.goalSelector.addGoal(1, this.breakDoorGoal);
                } else {
                    this.goalSelector.removeGoal(this.breakDoorGoal);
                }
            }
        } else if (this.canBreakDoors) {
            this.goalSelector.removeGoal(this.breakDoorGoal);
            this.canBreakDoors = false;
        }
    }

    protected boolean supportsBreakDoorGoal() {
        return true;
    }

    public void addAdditionalSaveData(CompoundNBT nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putBoolean("CanBreakDoors", this.canBreakDoors());
    }

    public void readAdditionalSaveData(CompoundNBT nbt) {
        super.readAdditionalSaveData(nbt);
        this.setCanBreakDoors(nbt.getBoolean("CanBreakDoors"));
    }

    private int ticks = 0;
    private float waitTicks;

    @Override
    public ActionResultType mobInteract(PlayerEntity player, Hand hand) {

        BlockPos blockpos = this.getOnPos();
        ItemStack stack = player.getItemInHand(hand);
        SunderedCadaverEntity sunderedCadaver = ModEntities.SUNDERED_CADAVER.create(level);
        SunderedCadaverEntity sunderedCadaver2 = ModEntities.SUNDERED_CADAVER.create(level);
        CanisEntity canis = SpecializedEntityTypes.CANIS.get().create(level);

        if (stack.getItem() == ItemInit.PACT_OF_SERVITUDE.get()) {
            if (!player.abilities.instabuild) {
                stack.shrink(1);
            }
            player.swing(hand);
            if (!player.level.isClientSide) {

                level.playSound(player, blockpos, SoundEvents.BOOK_PAGE_TURN, SoundCategory.NEUTRAL, 1.0F, (level.random.nextFloat() - level.random.nextFloat()) * 0.8F + 1.0F);
                if ((Math.random() <= 0.15)) {

                    level.addParticle(ParticleTypes.SOUL, this.getRandomX(1.0D), this.getRandomY() + 0.5D, this.getRandomZ(1.0D), 0.0D, 0.0D, 0.0D);
                    level.addParticle(ParticleTypes.SOUL, this.getRandomX(1.5D), this.getRandomY() + 0.8D, this.getRandomZ(1.5D), 0.0D, 0.0D, 0.0D);
                    level.playSound(null, blockpos, SoundEvents.WOLF_HOWL, SoundCategory.NEUTRAL, 1f, 0.8f);

                    this.navigation.stop();
                    this.setSecondsOnFire(3);
                    PortUtil.sendMessageCenterScreen(player, (new TranslationTextComponent("rigoranthusemortisreborn.canis.successfully_tamed")));

                    this.waitTicks = 60;

                    if (this.ticks <= this.waitTicks)
                        canis.setTame(true);
                        canis.setOwnerUUID(player.getUUID());
                        canis.setHealth(canis.getMaxHealth());
                        canis.setOrderedToSit(false);
                        canis.absMoveTo(this.getX(), this.getY(), this.getZ(), this.yRot, this.xRot);
                        level.addFreshEntity(canis);
                    this.remove();
                }
                else if ((Math.random() <= 0.15)) {
                    if (level instanceof ServerWorld) {
                        LightningBoltEntity lightningBoltEntity = EntityType.LIGHTNING_BOLT.create(level);

                        lightningBoltEntity.absMoveTo(this.getX(), this.getY(), this.getZ(), this.yRot, this.xRot);
                        lightningBoltEntity.setVisualOnly(true);

                        level.addFreshEntity(lightningBoltEntity);

                        sunderedCadaver.absMoveTo(this.getX(), this.getY(), this.getZ(), this.yRot, this.xRot);
                        sunderedCadaver2.absMoveTo(this.getX(), this.getY(), this.getZ(), this.yRot, this.xRot);

                        sunderedCadaver.finalizeSpawn((ServerWorld)level, level.getCurrentDifficultyAt(player.blockPosition()), SpawnReason.MOB_SUMMONED, (ILivingEntityData)null, (CompoundNBT)null);
                        sunderedCadaver2.finalizeSpawn((ServerWorld)level, level.getCurrentDifficultyAt(player.blockPosition()), SpawnReason.MOB_SUMMONED, (ILivingEntityData)null, (CompoundNBT)null);

                        level.addFreshEntity(sunderedCadaver);
                        level.addFreshEntity(sunderedCadaver2);

                        sunderedCadaver.setCustomName(new TranslationTextComponent("entity.rigoranthusemortisreborn.summoned_servant").withStyle(Style.EMPTY.withItalic(true)));
                        sunderedCadaver2.setCustomName(new TranslationTextComponent("entity.rigoranthusemortisreborn.summoned_servant").withStyle(Style.EMPTY.withItalic(true)));

                        sunderedCadaver.setCustomNameVisible(true);
                        sunderedCadaver2.setCustomNameVisible(true);
                    }
                    this.level.playSound(null, blockpos, SoundEvents.WOLF_GROWL, SoundCategory.NEUTRAL, 1f, 0.8f);

                    PortUtil.sendMessageCenterScreen(player, (new TranslationTextComponent("rigoranthusemortisreborn.canis.failed_to_tame")));
                }
            }
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {

        SunderedCadaverEntity sunderedCadaver = ModEntities.SUNDERED_CADAVER.create(level);
        SunderedCadaverEntity sunderedCadaver2 = ModEntities.SUNDERED_CADAVER.create(level);

        if (this.isInvulnerableTo(source)) {
            return false;
        }
        if (!super.hurt(source, amount)) {
            return false;
        }
        if (this.getLastHurtByMob() instanceof PlayerEntity && this.lastHurtByPlayer != null) {
            if ((Math.random() < 0.1)) {
                PortUtil.sendMessageCenterScreen((PlayerEntity) lastHurtByPlayer, (new TranslationTextComponent("rigoranthusemortisreborn.canis.failed_to_tame")));
                if (level instanceof ServerWorld) {

                    LightningBoltEntity lightningBoltEntity = EntityType.LIGHTNING_BOLT.create(level);

                    lightningBoltEntity.absMoveTo(this.getX(), this.getY(), this.getZ(), this.yRot, this.xRot);
                    lightningBoltEntity.setVisualOnly(true);

                    level.addFreshEntity(lightningBoltEntity);

                    sunderedCadaver.absMoveTo(this.getX(), this.getY(), this.getZ(), this.yRot, this.xRot);
                    sunderedCadaver2.absMoveTo(this.getX(), this.getY(), this.getZ(), this.yRot, this.xRot);

                    sunderedCadaver.finalizeSpawn((ServerWorld)level, level.getCurrentDifficultyAt(lastHurtByPlayer.blockPosition()), SpawnReason.MOB_SUMMONED, (ILivingEntityData)null, (CompoundNBT)null);
                    sunderedCadaver2.finalizeSpawn((ServerWorld)level, level.getCurrentDifficultyAt(lastHurtByPlayer.blockPosition()), SpawnReason.MOB_SUMMONED, (ILivingEntityData)null, (CompoundNBT)null);

                    level.addFreshEntity(sunderedCadaver);
                    level.addFreshEntity(sunderedCadaver2);

                    sunderedCadaver.setCustomName(new TranslationTextComponent("entity.rigoranthusemortisreborn.summoned_servant").withStyle(Style.EMPTY.withItalic(true)));
                    sunderedCadaver2.setCustomName(new TranslationTextComponent("entity.rigoranthusemortisreborn.summoned_servant").withStyle(Style.EMPTY.withItalic(true)));

                    sunderedCadaver.setCustomNameVisible(true);
                    sunderedCadaver2.setCustomNameVisible(true);
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

    @Override
    public void startAnimation(int arg) {
        try{
            if(arg == Animations.BITING.ordinal()){
                AnimationController controller = this.animationFactory.getOrCreateAnimationData(this.hashCode()).getAnimationControllers().get("attackController");
                controller.markNeedsReload();
                controller.setAnimation(new AnimationBuilder().addAnimation("attack", false));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public enum Animations{ BITING }
}