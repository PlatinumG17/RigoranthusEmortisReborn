package com.platinumg17.rigoranthusemortisreborn.entity.mobs.canis;

import com.platinumg17.rigoranthusemortisreborn.config.Config;
import com.platinumg17.rigoranthusemortisreborn.core.init.ItemInit;
import com.platinumg17.rigoranthusemortisreborn.entity.RigoranthusEntityTypes;
import com.platinumg17.rigoranthusemortisreborn.entity.mobs.SunderedCadaverEntity;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.GhastEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.horse.AbstractChestedHorseEntity;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.GameRules;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

public class CanisChordataEntity extends CanisEntity implements IAnimatable, ITameable, IAngerable {

    private static final DataParameter<Byte> CURRENT_EVOLUTION_LEVEL = EntityDataManager.defineId(CanisChordataEntity.class, DataSerializers.BYTE);

    protected static final DataParameter<Byte> DATA_FLAGS_ID = EntityDataManager.defineId(CanisChordataEntity.class, DataSerializers.BYTE);
    protected static final DataParameter<Optional<UUID>> DATA_OWNERUUID_ID = EntityDataManager.defineId(CanisChordataEntity.class, DataSerializers.OPTIONAL_UUID);
    private static final DataParameter<Integer> ANGER_TIME = EntityDataManager.defineId(CanisChordataEntity.class, DataSerializers.INT);
    private static final DataParameter<Integer> SUPREME_ANGER_MODE_TIME = EntityDataManager.defineId(CanisChordataEntity.class, DataSerializers.INT);
    private static final DataParameter<Integer> HUNGER = EntityDataManager.defineId(CanisChordataEntity.class, DataSerializers.INT);
    private static final DataParameter<Integer> WETNESS = EntityDataManager.defineId(CanisChordataEntity.class, DataSerializers.INT);
    private static final DataParameter<Boolean> SITTING = EntityDataManager.defineId(CanisChordataEntity.class, DataSerializers.BOOLEAN);
    public static final EntitySize SITTING_DIMENSIONS = EntitySize.scalable(0.6F, 1.0F);
    private static final RangedInteger ANGER_RANGE = TickRangeConverter.rangeOfSeconds(20, 39);
    private UUID lastHurtBy; //persistentAngerTarget
    private int attackTimer;
    private boolean orderedToSit;
    private int runningStamina = 20 + this.random.nextInt(40);
    public static final DataParameter<Integer> STATE = EntityDataManager.defineId(CanisChordataEntity.class, DataSerializers.INT);
    private final AnimationFactory animationFactory = new AnimationFactory(this);

    private static final DataParameter<Integer> DATA_REMAINING_ANGER_TIME = EntityDataManager.defineId(CanisChordataEntity.class, DataSerializers.INT);
    public static final Predicate<LivingEntity> PREY_SELECTOR = (p_213440_0_) -> {
        EntityType<?> entitytype = p_213440_0_.getType();
        return entitytype == EntityType.SHEEP || entitytype == EntityType.RABBIT || entitytype == EntityType.FOX;
    };
    private static final RangedInteger PERSISTENT_ANGER_TIME = TickRangeConverter.rangeOfSeconds(20, 39);
    private boolean isTamed;
    private UUID owner;

    public CanisChordataEntity(EntityType<? extends AbstractChestedHorseEntity> p_i48564_1_, World p_i48564_2_) {
        super(p_i48564_1_, p_i48564_2_);
        this.setTame(false);
        this.noCulling = true;
        this.moveControl = new MovementController(this);
    }

    private <E extends IAnimatable> PlayState animationPredicate(AnimationEvent<E> event) {
//        if (!this.dead && !this.isDeadOrDying()) {
//            if (this.getState() == State.ATTACKING) {
//                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.chordata.attack", false));
//                return PlayState.CONTINUE;
//            }
//        }
//        if (event.isMoving()) {
//            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.chordata.walk", true));
//        } else {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.chordata.idle", true));
//        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController<>(this, "controller", 0, this::animationPredicate));
    }

    @Override
    protected void updateControlFlags() {
        super.updateControlFlags();
        this.goalSelector.setControlFlag(Goal.Flag.MOVE, true);
        this.goalSelector.setControlFlag(Goal.Flag.JUMP, true);
        this.goalSelector.setControlFlag(Goal.Flag.LOOK, true);
    }

    @Override
    public boolean causeFallDamage(float distance, float damageMultiplier) {return false;}

    @Override
    public AnimationFactory getFactory() {return this.animationFactory;}

    public enum State {IDLE, ATTACKING}

    public State getState() {
        State[] states = State.values();
        return states[MathHelper.clamp(this.entityData.get(STATE), 0, states.length - 1)];
    }

    public void setState(State state) {this.entityData.set(STATE, state.ordinal());}

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
//        this.goalSelector.addGoal(1, new CanisSitGoal(this));
//        this.goalSelector.addGoal(4, new CanisAttackGoal(this, 1.25D));
//        this.goalSelector.addGoal(6, new BreedGoal(this, 1.0D));
//        this.goalSelector.addGoal(8, new CanisEatGoal(this));
//        this.goalSelector.addGoal(9, new CanisTemptGoal(this, 1.25D));
//        this.goalSelector.addGoal(10, new TemptGoal(this, 1.25D, Ingredient.of(RigoranthusTagRegistry.Items.CANIS_FOOD), false));
//        this.goalSelector.addGoal(11, new CanisFollowParentGoal(this, 1.25D));
//        this.goalSelector.addGoal(23, new CanisSupremeAngerModeGoal(this, 1.0D));
//        this.goalSelector.addGoal(24, new CanisRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(25, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(26, new LookAtGoal(this, MobEntity.class, 6.0F));
        this.goalSelector.addGoal(27, new LookRandomlyGoal(this));
//        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 10, true, false, this::isAngryAt));
//        this.targetSelector.addGoal(1, new CanisHurtByTargetGoal(this).setAlertOthers());
        this.targetSelector.addGoal(2, new ResetAngerGoal<>(this, true));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
//        this.entityData.define(CURRENT_EVOLUTION_LEVEL, (byte) 1);
        this.entityData.define(ANGER_TIME, 0);
        this.entityData.define(SUPREME_ANGER_MODE_TIME, 0);
        this.entityData.define(HUNGER, 0);
        this.entityData.define(WETNESS, 0);
        this.entityData.define(SITTING, false);

        this.entityData.define(DATA_FLAGS_ID, (byte) 0);
        this.entityData.define(DATA_OWNERUUID_ID, Optional.empty());
        this.entityData.define(DATA_REMAINING_ANGER_TIME, 0);
        this.entityData.define(STATE, 0);
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
//        compound.putByte("CurrentEvolutionLevel", (byte) getCurrentEvolutionLevel());
        this.addPersistentAngerSaveData(compound);
        compound.putInt("CanisEvolutionLevel", this.getCanisEvolutionLevel());
        compound.putInt("SupremeAngerModeTime", this.getSupremeAngerModeTime());
        compound.putInt("Hunger", this.getHunger());
        compound.putInt("Wetness", this.getWetness());
        compound.putBoolean("Sitting", this.isSitting());
        if (this.getOwnerUUID() != null) {
            compound.putUUID("Owner", this.getOwnerUUID());
            this.addPersistentAngerSaveData(compound);
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        UUID uuid;
        this.readPersistentAngerSaveData((ServerWorld) this.level, compound);
//        this.setCanisEvolutionLevel(compound.getByte("CanisEvolutionLevel"));
        this.setSupremeAngerModeTime(compound.getInt("SupremeAngerModeTime"));
        this.setHunger(compound.getInt("Hunger"));
        this.setDirtiness(compound.getInt("Wetness"));
        this.setSitting(compound.getBoolean("Sitting"));

        if (compound.hasUUID("Owner")) {
            uuid = compound.getUUID("Owner");
        } else {
            String s = compound.getString("Owner");
            uuid = PreYggdrasilConverter.convertMobOwnerIfNecessary(this.getServer(), s);
        }
        if (uuid != null) {
            try {
                this.setOwnerUUID(uuid);
                this.setTame(true);
            } catch (Throwable throwable) {
                this.setTame(false);
            }
        }
//        this.orderedToSit = compound.getBoolean("Sitting");
//        this.setInSittingPose(this.orderedToSit);
        if (!level.isClientSide)
            this.readPersistentAngerSaveData((ServerWorld) this.level, compound);
    }

    @Override
    public int getAmbientSoundInterval() {
        return this.getSupremeAngerModeTime() > 0 ? 20 : 120;
    }

    @Override
    protected void customServerAiStep() {
        this.updatePersistentAnger((ServerWorld) this.level, true);

        if (this.isAngry()) {
            this.lastHurtByPlayerTime = this.tickCount;
        }

        if (this.getSupremeAngerModeTime() > 0 && this.getMoveControl().hasWanted()) {
            double d0 = this.getMoveControl().getSpeedModifier();
            if (d0 >= 1.0D) {
                this.setSprinting(true);
            } else {
                this.setSprinting(false);
            }
        } else {
            this.setSprinting(false);
        }
        super.customServerAiStep();
    }

    @Override
    public boolean doHurtTarget(Entity entityIn) {
        this.level.broadcastEntityEvent(this, (byte) 4);
        float f = (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
        float f1 = (int) f > 0 ? f / 2.0F + (float) this.random.nextInt((int) f) : f;
        boolean flag = entityIn.hurt(DamageSource.mobAttack(this), f1);
        if (flag) {
            this.doEnchantDamageEffects(this, entityIn);
        }
        return flag;
    }

    private void spawnParticles() {
        if (this.isWet()) {
            if (this.tickCount % 6 == 0) {
                double d0 = ((double) this.random.nextFloat() + 1.0D) * 0.06D;
                double d1 = this.random.nextInt(360) - 360.0D;
                double d2 = ((double) this.random.nextFloat() + 1.0D) * 14.0D;
                d2 *= this.random.nextBoolean() ? 1.0D : -1.0D;

                level.addParticle(ParticleTypes.RAIN, this.getRandomX(0.5D), this.getEyeY() + this.random.nextDouble() * 0.2D + 0.3D, this.getRandomZ(0.5D), d0, d1, d2);
            }
        }
        if (this.isEating()) {
            ItemStack food = this.getSnack();
            if (this.tickCount % 10 == 0 && !food.isEmpty()) {
                if (this.level.isClientSide) {
                    for (int i = 0; i < 6; ++i) {
                        Vector3d vector3d = new Vector3d(((double) this.random.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, ((double) this.random.nextFloat() - 0.5D) * 0.1D);
                        vector3d = vector3d.xRot(-this.xRot * ((float) Math.PI / 180F));
                        vector3d = vector3d.yRot(-this.yRot * ((float) Math.PI / 180F));
                        double d0 = (double) (-this.random.nextFloat()) * 0.2D;
                        Vector3d vector3d1 = new Vector3d(((double) this.random.nextFloat() - 0.5D) * 0.2D, d0, 0.6D * this.getScale() + ((double) this.random.nextFloat() - 0.5D) * 0.2D);
                        vector3d1 = vector3d1.yRot(-this.yBodyRot * ((float) Math.PI / 180F));
                        vector3d1 = vector3d1.add(this.getX(), this.getEyeY(), this.getZ());
                        this.level.addParticle(new ItemParticleData(ParticleTypes.ITEM, this.getItemInHand(this.getSnackHand())), vector3d1.x, vector3d1.y, vector3d1.z, vector3d.x, vector3d.y + 0.05D, vector3d.z);
                    }
                }

                this.playSound(SoundEvents.GENERIC_EAT, 0.25F + 0.5F * (float) this.random.nextInt(2), (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
            }
        }
    }

    public void eatSnack() {
        if (!this.getSnack().isEmpty()) {
            if (this.getSnack().isEdible()) {
                this.heal((float) this.getSnack().getItem().getFoodProperties().getNutrition());
            }
            this.setItemInHand(this.getSnackHand(), this.getSnack().finishUsingItem(this.level, this));
        }
        this.setHunger(0);
    }

    @Override
    public EntitySize getDimensions(Pose pose) {
        if (this.isSitting()) {
            return SITTING_DIMENSIONS.scale(this.getScale());
        } else {
            return super.getDimensions(pose);
        }
    }

    @Override
    public double getMyRidingOffset() {
        return this.isBaby() ? -0.05D : -0.3D;
    }

    public int getSupremeAngerModeTime() {
        return this.entityData.get(SUPREME_ANGER_MODE_TIME);
    }

    public void setSupremeAngerModeTime(int time) {
        this.entityData.set(SUPREME_ANGER_MODE_TIME, time);
    }

    private int getItemValue(ItemStack stack) {
        if (this.isSnack(stack)) {
            return this.isHungry() ? 2 : 1;
        } else {
            return 0;
        }
    }

    @Override
    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        spawnDataIn = super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        this.setHunger(this.random.nextInt(4800));
        this.setDirtiness(this.random.nextInt(4800));
        this.entityData.set(CURRENT_EVOLUTION_LEVEL, (byte) 1);
        if (dataTag != null) {
            if (dataTag.contains("current_evolution_level"))
                this.entityData.set(CURRENT_EVOLUTION_LEVEL, dataTag.getByte("current_evolution_level"));
        }
        return spawnDataIn;
    }

//    @Override
//    public CanisChordataEntity getBreedOffspring(ServerWorld world, AgeableEntity ageableEntity) {
//        CanisChordataEntity baby = RigoranthusEntityTypes.CANIS_CHORDATA.get().create(world);
//        baby.setCanisEvolutionLevel(this.random.nextBoolean() ? this.getCanisEvolutionLevel() : ((CanisChordataEntity)ageableEntity).getCanisEvolutionLevel());
//        return baby;
//    }

    // DATA //
    public int getCanisEvolutionLevel() {
        int i = this.getCanisEvolutionData().getLevel();
        return i;
    }

    public boolean isCanis() {
        int i = this.getCanisEvolutionLevel();
        return i == 1;
    }

    public boolean isKyphos() {
        int i = this.getCanisEvolutionLevel();
        return i == 2;
    }

    public boolean isCavalier() {
        int i = this.getCanisEvolutionLevel();
        return i == 3;
    }

    public boolean isHomini() {
        int i = this.getCanisEvolutionLevel();
        return i == 4;
    }

    public int getAttackTimer() {
        return this.attackTimer;
    }

    @Override
    public int getRemainingPersistentAngerTime() {
        return this.entityData.get(ANGER_TIME);
    }

    @Override
    public void setRemainingPersistentAngerTime(int time) {
        this.entityData.set(ANGER_TIME, time);
    }

    public boolean isSitting() {
        return this.entityData.get(SITTING);
    }

    public void setSitting(boolean sitting) {
        this.entityData.set(SITTING, sitting);
    }

    @Override
    public UUID getPersistentAngerTarget() {
        return this.lastHurtBy;
    }

    @Override
    public void setPersistentAngerTarget(UUID target) {
        this.lastHurtBy = target;
    }

    @Override
    public void startPersistentAngerTimer() {
        this.setRemainingPersistentAngerTime(ANGER_RANGE.randomValue(this.random));
    }

    // NEED STUFF //

    public int getHunger() {
        return this.entityData.get(HUNGER);
    }

    public void setHunger(int amount) {
        this.entityData.set(HUNGER, amount);
    }

    public boolean isHungry() {
        return this.getHunger() >= 9600;
    }

    public boolean needsSnack() {
        return this.isHungry() && this.getSnack().isEmpty();
    }

    public Hand getSnackHand() {
        if (!this.getSnack(Hand.MAIN_HAND).isEmpty()) {
            return Hand.MAIN_HAND;
        } else {
            return Hand.OFF_HAND;
        }
    }

    public ItemStack getSnack() {
        if (!this.getSnack(Hand.MAIN_HAND).isEmpty()) {
            return this.getSnack(Hand.MAIN_HAND);
        } else if (!this.getSnack(Hand.OFF_HAND).isEmpty()) {
            return this.getSnack(Hand.OFF_HAND);
        }
        return ItemStack.EMPTY;
    }

    public ItemStack getSnack(Hand hand) {
        ItemStack snack = this.getItemInHand(hand);
        if (this.isSnack(snack)) {
            return snack;
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.getItem().isEdible();
    }//(RigoranthusTagRegistry.Items.CANIS_FOOD);}

    public boolean isSnack(ItemStack stack) {
        return stack.getItem().isEdible();
    }//(RigoranthusTagRegistry.Items.CANIS_SNACKS);}

    public int getWetness() {
        return this.entityData.get(WETNESS);
    }

    public void setDirtiness(int amount) {
        this.entityData.set(WETNESS, amount);
    }

    public boolean isWet() {
        return this.getWetness() >= 12000;
    }

    public void getCleaned() {
        this.setDirtiness(0);
    }

    public boolean wantsToAttack(LivingEntity entity, LivingEntity player) {
        if (!(entity instanceof CreeperEntity) && !(entity instanceof GhastEntity)) {
            if (entity instanceof CanisChordataEntity) {
                CanisChordataEntity canisChordataEntity = (CanisChordataEntity) entity;
                return !canisChordataEntity.isTame() || canisChordataEntity.getOwner() != player;
            } else if (entity instanceof PlayerEntity && player instanceof PlayerEntity && !((PlayerEntity) player).canHarmPlayer((PlayerEntity) entity)) {
                return false;
            } else if (entity instanceof AbstractHorseEntity && ((AbstractHorseEntity) entity).isTamed()) {
                return false;
            } else {
                return !(entity instanceof TameableEntity) || !((TameableEntity) entity).isTame();
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean canBeLeashed(PlayerEntity player) {
        return this.isTamed() && !this.isLeashed();
    }

    @OnlyIn(Dist.CLIENT)
    public Vector3d getLeashOffset() {
        return new Vector3d(0.0D, (0.6F * this.getEyeHeight()), (this.getBbWidth() * 0.4F));
    }

    @Override
    public CanisChordataEntity getBreedOffspring(ServerWorld world, AgeableEntity ageableEntity) {
        CanisChordataEntity canisChordataEntity = RigoranthusEntityTypes.CANIS_CHORDATA.get().create(world);
        UUID uuid = this.getOwnerUUID();
        if (uuid != null) {
            canisChordataEntity.setOwnerUUID(uuid);
            canisChordataEntity.setTame(true);
        }
        return canisChordataEntity;
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (!this.level.isClientSide && !this.isPathFinding() && this.onGround) {
            this.level.broadcastEntityEvent(this, (byte) 8);
        }
        if (!this.level.isClientSide) {
            if (this.getSupremeAngerModeTime() > 0) {
                this.setSupremeAngerModeTime(this.getSupremeAngerModeTime() - 1);
            }
            if (!this.isHungry() && this.getHunger() >= 0) {
                this.setHunger(this.getHunger() + 1);
            }
            if (!this.isWet() && this.getWetness() >= 0) {
                this.setDirtiness(this.getWetness() + 1);
            }
            this.updatePersistentAnger((ServerWorld) this.level, true);
        }
        if (this.attackTimer > 0) {
            --this.attackTimer;
        } // make sure i need this
    }
//        if (this.jukeboxPosition == null || !this.jukeboxPosition.closerThan(this.position(), 3.46D) || this.level.getBlockState(jukeboxPosition).getBlock() != Blocks.JUKEBOX) {
//        this.refreshDimensions();


    @Override
    public boolean tameWithName(PlayerEntity player) {
        this.setOwnerUUID(player.getUUID());
        this.setTamed(true);
        if (player instanceof ServerPlayerEntity) {
            CriteriaTriggers.TAME_ANIMAL.trigger((ServerPlayerEntity) player, this);
        }

        this.level.broadcastEntityEvent(this, (byte) 7);
        return true;
    }

    @Override
    public ActionResultType mobInteract(PlayerEntity sourceentity, Hand hand) {
        ItemStack itemstack = sourceentity.getItemInHand(hand);
        Item item = itemstack.getItem();
        BlockPos blockpos = sourceentity.blockPosition();
        if (this.level.isClientSide) {
            boolean flag = this.isOwnedBy(sourceentity) || this.isTame() || item == Items.BONE && !this.isTame() && !this.isAngry();
            return flag ? ActionResultType.CONSUME : ActionResultType.PASS;
        }
        if (this.isTamed() && sourceentity.isSecondaryUseActive()) {
            this.openInventory(sourceentity);
            return ActionResultType.sidedSuccess(this.level.isClientSide);
        }

        if (this.isVehicle()) {
            return super.mobInteract(sourceentity, hand);
        }
        if (!itemstack.isEmpty()) {
            if (this.isSnack(itemstack)) {
                this.stopBeingAngry();
            }

            if (this.isFood(itemstack) && this.getHealth() < this.getMaxHealth()) {
                this.heal(10);
                sourceentity.swing(hand);
                if (this.level.isClientSide()) {
                    level.playSound(null, blockpos, SoundEvents.GENERIC_EAT, SoundCategory.NEUTRAL, 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
                }
                if (!sourceentity.isCreative()) {
                    itemstack.shrink(1);
                }
            }
            if (itemstack.getItem() == ItemInit.PACT_OF_SERVITUDE.get()) {
                sourceentity.swing(Hand.MAIN_HAND, true);
                if (!sourceentity.isCreative()) {
                    itemstack.shrink(1);
                }
                if (this.level.isClientSide()) {
                    level.playSound(null, blockpos, SoundEvents.BOOK_PAGE_TURN, SoundCategory.NEUTRAL, 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
                }
                this.interact(sourceentity, hand);
                if ((Math.random() <= 0.15)) {
                    if (sourceentity.level.isClientSide()) {
                        this.level.addParticle(ParticleTypes.HAPPY_VILLAGER, this.getRandomX(1.0D), this.getRandomY() + 0.5D, this.getRandomZ(1.0D), 0.0D, 0.0D, 0.0D); //((ServerWorld) level).sendParticles(sourceentity, true, 5, 3, 3, IPacket <?> level) //    (ParticleTypes.SOUL, this.blockPosition(), 5, 3, 3, 3, 1);
                        this.level.addParticle(ParticleTypes.HAPPY_VILLAGER, this.getRandomX(1.0D), this.getRandomY() + 0.5D, this.getRandomZ(1.0D), 0.0D, 0.0D, 0.0D);
                    }
                    this.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 100, 7));
                    if (sourceentity.level.isClientSide) {
                        sourceentity.displayClientMessage(
                                new StringTextComponent("\u00A76The Pact was Successful. \u00A7cThe Beasts Impurities will now be Expelled."), (true));
                    }
                    if (!level.isClientSide) {
                        level.playSound(null, blockpos, SoundEvents.WOLF_HOWL, SoundCategory.NEUTRAL, 1f, 0.8f);
                    }
                    this.setSecondsOnFire(3);
//                    this.setTamed(true);
                    this.tameWithName(sourceentity);
                }
            }

            if (!this.isTamed()) {
                this.makeMad();
                return ActionResultType.sidedSuccess(this.level.isClientSide);
            }

            if (!this.hasChest() && itemstack.getItem() == Blocks.CHEST.asItem()) {
                this.setChest(true);
                this.playChestEquipsSound();
                if (!sourceentity.abilities.instabuild) {
                    itemstack.shrink(1);
                }
                this.createInventory();
                return ActionResultType.sidedSuccess(this.level.isClientSide);
            }

            if (!this.isSaddled() && itemstack.getItem() == Items.SADDLE) {
                this.openInventory(sourceentity);
                return ActionResultType.sidedSuccess(this.level.isClientSide);
            }
        }
        {
            this.doPlayerRide(sourceentity);
            return ActionResultType.sidedSuccess(this.level.isClientSide);
        }
    }
//       return ActionResultType.sidedSuccess(this.level.isClientSide);
//   }
//   return ActionResultType.PASS;
//}
/*

    public void thunderHit(ServerWorld serverWorld, LightningBoltEntity entityLightningBolt) {
        super.thunderHit(serverWorld, entityLightningBolt);

            CanisChordataStruckByLightning

        IWorld world = (IWorld) dependencies.get("world");
        if (world instanceof World && !((World) world).isClientSide) {
            ((World) world).playSound(null, new BlockPos((int) x, (int) y, (int) z),
                    (SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.wolf.howl")),
                    SoundCategory.NEUTRAL, (float) 1, (float) 1);
        } else {
            ((World) world).playSound(x, y, z,
                    (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.wolf.howl")),
                    SoundCategory.NEUTRAL, 1f, 1f, false);
        }
        if (world instanceof ServerWorld) {
            Entity entityToSpawn = new SunderedCadaverEntity(RigoranthusEntityTypes.SUNDERED_CADAVER.get(), (World) world);
            entityToSpawn.setLocationAndAngles(x, y, z, world.getRandom().nextFloat() * 360F, 0);
            if (entityToSpawn instanceof MobEntity)
                ((MobEntity) entityToSpawn).onInitialSpawn((ServerWorld) world, world.getCurrentDifficultyAt(entityToSpawn.getPosition()),
                        SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
            world.addEntity(entityToSpawn);
        }
        if (world instanceof ServerWorld) {
            Entity entityToSpawn = new SunderedCadaverEntity(RigoranthusEntityTypes.SUNDERED_CADAVER.get(), (World) world);
            entityToSpawn.setLocationAndAngles(x, y, z, world.getRandom().nextFloat() * 360F, 0);
            if (entityToSpawn instanceof MobEntity)
                ((MobEntity) entityToSpawn).onInitialSpawn((ServerWorld) world, world.getCurrentDifficultyAt(entityToSpawn.getPosition()),
                        SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
            world.addEntity(entityToSpawn);
        }
    }


    public boolean attackEntityFrom(DamageSource source, float amount) {

                                    CanisChordataIsHurt
        if (((sourceentity instanceof PlayerEntity) && (Math.random() <= 0.1))) {
            if (world instanceof World && !((World) world).isClientSide) {
                ((World) world).playSound(null, new BlockPos((int) x, (int) y, (int) z),
                        (SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.wolf.growl")),
                        SoundCategory.NEUTRAL, 1f, 0.8f);
            } else {
                ((World) world).playSound(x, y, z,
                        (SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.wolf.growl")),
                        SoundCategory.NEUTRAL, 1f, 0.8f, false);
            }
            if (sourceentity instanceof PlayerEntity && !sourceentity.level.isClientSide) {
                ((PlayerEntity) sourceentity).displayClientMessage(new StringTextComponent("Make yourself scarce, weakling..."), (false));
            }
            if (world instanceof ServerWorld) {
                LightningBoltEntity _ent = EntityType.LIGHTNING_BOLT.create((World) world);
                _ent.moveForced(Vector3d.copyCenteredHorizontally(new BlockPos((int) x, (int) y, (int) z)));
                _ent.setEffectOnly(true);
                ((World) world).addEntity(_ent);
            }
            if (world instanceof ServerWorld) {
                Entity entityToSpawn = new SunderedCadaverEntity(RigoranthusEntityTypes.SUNDERED_CADAVER.get(), (World) world);
                entityToSpawn.setLocationAndAngles(x, y, z, world.getRandom().nextFloat() * 360F, 0);
                if (entityToSpawn instanceof MobEntity)
                    ((MobEntity) entityToSpawn).onInitialSpawn((ServerWorld) world, world.getCurrentDifficultyAt(entityToSpawn.getPosition()),
                            SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
                world.addEntity(entityToSpawn);
            }
            if (world instanceof ServerWorld) {
                Entity entityToSpawn = new SunderedCadaverEntity(RigoranthusEntityTypes.SUNDERED_CADAVER.get(), (World) world);
                entityToSpawn.setLocationAndAngles(x, y, z, world.getRandom().nextFloat() * 360F, 0);
                if (entityToSpawn instanceof MobEntity)
                    ((MobEntity) entityToSpawn).onInitialSpawn((ServerWorld) world, world.getCurrentDifficultyAt(entityToSpawn.getPosition()),
                            SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
                world.addEntity(entityToSpawn);
            }
        }
    }
*/
//    @Override
//    protected boolean canRide(@Nonnull Entity entityIn) {
//        return false;
//    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.FOLLOW_RANGE, 64.0D)
                .add(Attributes.JUMP_STRENGTH, 1.4)
                .add(Attributes.MAX_HEALTH, Config.canisChordataMaxHealth.get())
                .add(Attributes.MOVEMENT_SPEED, Config.canisChordataMovementSpeed.get())
                .add(Attributes.ATTACK_DAMAGE, Config.canisChordataAttackDamage.get())
                .add(Attributes.ARMOR, Config.canisChordataArmorValue.get())
                .add(Attributes.ATTACK_KNOCKBACK, Config.canisChordataAttackKnockback.get())
                .add(Attributes.KNOCKBACK_RESISTANCE, Config.canisChordataKnockbackResistance.get());
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    protected void spawnTamingParticles(boolean particles) {
        IParticleData iparticledata = ParticleTypes.HEART;
        if (!particles) {iparticledata = ParticleTypes.SMOKE;}
        for(int i = 0; i < 7; ++i) {
            double d0 = this.random.nextGaussian() * 0.02D;
            double d1 = this.random.nextGaussian() * 0.02D;
            double d2 = this.random.nextGaussian() * 0.02D;
            this.level.addParticle(iparticledata, this.getRandomX(1.0D), this.getRandomY() + 0.5D, this.getRandomZ(1.0D), d0, d1, d2);
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void handleEntityEvent(byte byteId) {
        if (byteId == 7) {
            this.spawnTamingParticles(true);
        } else if (byteId == 6) {
            this.spawnTamingParticles(false);
        } else {
            super.handleEntityEvent(byteId);
        }
    }

    public boolean isTame() {
        return (this.entityData.get(DATA_FLAGS_ID) & 4) != 0;
    }

    public void setTame(boolean tame) {
        byte b0 = this.entityData.get(DATA_FLAGS_ID);
        if (tame) {
            this.entityData.set(DATA_FLAGS_ID, (byte)(b0 | 4));
        } else {
            this.entityData.set(DATA_FLAGS_ID, (byte)(b0 & -5));
        }
    }

    public boolean isInSittingPose() {
        return (this.entityData.get(DATA_FLAGS_ID) & 1) != 0;
    }

    public void setInSittingPose(boolean sit) {
        byte b0 = this.entityData.get(DATA_FLAGS_ID);
        if (sit) {
            this.entityData.set(DATA_FLAGS_ID, (byte)(b0 | 1));
        } else {
            this.entityData.set(DATA_FLAGS_ID, (byte)(b0 & -2));
        }
    }
    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        }
        else {
            Entity entity = source.getEntity();
            this.setOrderedToSit(false);
            if (entity != null && !(entity instanceof PlayerEntity) && !(entity instanceof AbstractArrowEntity)) {
                amount = (amount + 1.0F) / 2.0F;
            }
        }
        if(!this.isTame()) {
            if ((Math.random() < 0.1)) {
                if (level instanceof ServerWorld) {
                    MobEntity entityToSpawn = new SunderedCadaverEntity(RigoranthusEntityTypes.SUNDERED_CADAVER.get(), level);
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

    @Override
    @Nullable
    public UUID getOwnerUUID() {
        return this.entityData.get(DATA_OWNERUUID_ID).orElse(null);
    }
    @Override
    public void setOwnerUUID(@Nullable UUID uuid) {
        this.entityData.set(DATA_OWNERUUID_ID, Optional.ofNullable(uuid));
    }

    @Nullable
    public LivingEntity getOwner() {
        try {
            UUID uuid = this.getOwnerUUID();
            return uuid == null ? null : this.level.getPlayerByUUID(uuid);
        } catch (IllegalArgumentException illegalargumentexception) {
            return null;
        }
    }
    @Override
    public boolean canAttack(LivingEntity targetEntity) {
        return !this.isOwnedBy(targetEntity) && super.canAttack(targetEntity);
    }

    public boolean isOwnedBy(LivingEntity player) {
        return player == this.getOwner();
    }

    @Override
    public void die(DamageSource source) {
        if (!this.level.isClientSide && this.level.getGameRules().getBoolean(GameRules.RULE_SHOWDEATHMESSAGES) && this.getOwner() instanceof ServerPlayerEntity) {
            this.getOwner().sendMessage(this.getCombatTracker().getDeathMessage(), Util.NIL_UUID);
        }
        super.die(source);
    }
    public void setOrderedToSit(boolean sit) {this.orderedToSit = sit;}
}