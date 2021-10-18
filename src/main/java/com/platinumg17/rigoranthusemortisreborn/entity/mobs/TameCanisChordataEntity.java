package com.platinumg17.rigoranthusemortisreborn.entity.mobs;

import com.platinumg17.rigoranthusemortisreborn.config.Config;
import com.platinumg17.rigoranthusemortisreborn.core.init.ItemInit;
import com.platinumg17.rigoranthusemortisreborn.core.registry.RigoranthusSoundRegistry;
import com.platinumg17.rigoranthusemortisreborn.core.registry.RigoranthusTagRegistry;
import com.platinumg17.rigoranthusemortisreborn.entity.RigoranthusEntityTypes;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.BodyController;
import net.minecraft.entity.ai.controller.LookController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.horse.AbstractChestedHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.*;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.UUID;
//
//public class TameCanisChordataEntity extends AbstractChestedHorseEntity implements IAngerable {
//    private static final DataParameter<Integer> EVOLUTION_LEVEL = EntityDataManager.defineId(CanisChordataEntity.class, DataSerializers.INT);
//    private static final DataParameter<Integer> ANGER_TIME = EntityDataManager.defineId(CanisChordataEntity.class, DataSerializers.INT);
//    private static final DataParameter<Integer> SUPREME_ANGER_MODE_TIME = EntityDataManager.defineId(CanisChordataEntity.class, DataSerializers.INT);
//    private static final DataParameter<Integer> HUNGER = EntityDataManager.defineId(CanisChordataEntity.class, DataSerializers.INT);
//    private static final DataParameter<Integer> WETNESS = EntityDataManager.defineId(CanisChordataEntity.class, DataSerializers.INT);
//    private static final DataParameter<Boolean> SITTING = EntityDataManager.defineId(CanisChordataEntity.class, DataSerializers.BOOLEAN);
//    private static final DataParameter<Byte> ACTION = EntityDataManager.defineId(CanisChordataEntity.class, DataSerializers.BYTE);
//    private static final DataParameter<Byte> RUNNING = EntityDataManager.defineId(CanisChordataEntity.class, DataSerializers.BYTE);
//    private static final DataParameter<Direction> FACING = EntityDataManager.defineId(CanisChordataEntity.class, DataSerializers.DIRECTION);
//
//    public static final EntitySize SITTING_DIMENSIONS = EntitySize.scalable(0.6F, 1.0F);
//
//    private static final RangedInteger ANGER_RANGE = TickRangeConverter.rangeOfSeconds(20, 39);
//    private UUID lastHurtBy;
//    private int attackTimer;
//    private int runningStamina = 20 + this.random.nextInt(40);
//
//    private float runAnim;
//    private float runAnim0;
//
//    private int headShakeAnim;
//    private int headShakeAnim0;
//
//public boolean isPartying = false;
//        BlockPos jukeboxPosition;
//
//    public TameCanisChordataEntity(EntityType<? extends AbstractChestedHorseEntity> type, World worldIn) {
//        super(type, worldIn);
//        this.lookControl = new TameCanisChordataEntity.LookHelperController();
//        this.setCanPickUpLoot(true);
//    }
//
//    @Override
//    protected void registerGoals() {
//        this.goalSelector.addGoal(0, new SwimGoal(this));
//        this.goalSelector.addGoal(1, new CanisSitGoal(this));
//        this.goalSelector.addGoal(2, new CanisGetScaredGoal(this, 1.25D));
//
//        this.goalSelector.addGoal(4, new CanisAttackGoal(this, 1.25D));
//        this.goalSelector.addGoal(5, new CanisPanicGoal(this, 1.25D));
//        this.goalSelector.addGoal(6, new BreedGoal(this, 1.0D));
//
//        this.goalSelector.addGoal(8, new CanisEatGoal(this));
//        this.goalSelector.addGoal(9, new CanisTemptGoal(this, 1.25D));
//        this.goalSelector.addGoal(10, new TemptGoal(this, 1.25D, Ingredient.of(RigoranthusTagRegistry.Items.CANIS_FOOD), false));
//        this.goalSelector.addGoal(11, new CanisFollowParentGoal(this, 1.25D));
//
//        this.goalSelector.addGoal(15, new CanisFollowOthersGoal(this, 1.0D));
//
//        this.goalSelector.addGoal(18, new CanisCryGoal(this));
//        this.goalSelector.addGoal(19, new CanisShakeHeadGoal(this));
//        this.goalSelector.addGoal(20, new CanisLookAtItemGoal(this));
//        this.goalSelector.addGoal(21, new CanisJumpOnBouncyGoal(this, 1.0D, 16));
//        this.goalSelector.addGoal(22, new CanisPlayNoteBlockGoal(this, 1.0D, 16));
//        this.goalSelector.addGoal(23, new CanisSupremeAngerModeGoal(this, 1.0D));
//        this.goalSelector.addGoal(24, new CanisRandomWalkingGoal(this, 1.0D));
//        this.goalSelector.addGoal(25, new LookAtGoal(this, PlayerEntity.class, 6.0F));
//        this.goalSelector.addGoal(26, new LookAtGoal(this, MobEntity.class, 6.0F));
//        this.goalSelector.addGoal(27, new LookRandomlyGoal(this));
//
//        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 10, true, false, this::isAngryAt));
//        this.targetSelector.addGoal(1, new CanisHurtByTargetGoal(this).setAlertOthers());
//        this.targetSelector.addGoal(2, new ResetAngerGoal<>(this, true));
//    }
//
//    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
//        return MobEntity.createMobAttributes()
//                .add(Attributes.FOLLOW_RANGE, 64.0D)
//                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE, 4)
//                .add(Attributes.JUMP_STRENGTH, 1.4)
//                .add(Attributes.MAX_HEALTH, Config.canisChordataMaxHealth.get())
//                .add(Attributes.MOVEMENT_SPEED, Config.canisChordataMovementSpeed.get())
//                .add(Attributes.ATTACK_DAMAGE, Config.canisChordataAttackDamage.get())
//                .add(Attributes.ARMOR, Config.canisChordataArmorValue.get())
//                .add(Attributes.ATTACK_KNOCKBACK, Config.canisChordataAttackKnockback.get())
//                .add(Attributes.KNOCKBACK_RESISTANCE, Config.canisChordataKnockbackResistance.get());
//    }
//
//    @Override
//    protected void defineSynchedData() {
//        super.defineSynchedData();
//        this.entityData.define(EVOLUTION_LEVEL, 0);
//        this.entityData.define(ANGER_TIME, 0);
//        this.entityData.define(SUPREME_ANGER_MODE_TIME, 0);
//        this.entityData.define(HUNGER, 0);
//        this.entityData.define(WETNESS, 0);
//        this.entityData.define(SITTING, false);
//        this.entityData.define(ACTION, (byte) 0);
//        this.entityData.define(RUNNING, (byte) 0);
//        this.entityData.define(FACING, Direction.DOWN);
//    }
//
//    @Override
//    public void addAdditionalSaveData(CompoundNBT compound) {
//        super.addAdditionalSaveData(compound);
//        this.addPersistentAngerSaveData(compound);
//        compound.putInt("CanisEvolutionLevel", this.getCanisEvolutionLevel());
//        compound.putInt("SupremeAngerModeTime", this.getSupremeAngerModeTime());
//        compound.putInt("Hunger", this.getHunger());
//        compound.putInt("Wetness", this.getWetness());
//        compound.putBoolean("Sitting", this.isSitting());
//    }
//
//    @Override
//    public void readAdditionalSaveData(CompoundNBT compound) {
//        super.readAdditionalSaveData(compound);
//        this.readPersistentAngerSaveData((ServerWorld) this.level, compound);
//        this.setCanisEvolutionLevel(compound.getInt("CanisEvolutionLevel"));
//        this.setSupremeAngerModeTime(compound.getInt("SupremeAngerModeTime"));
//        this.setHunger(compound.getInt("Hunger"));
//        this.setWetness(compound.getInt("Wetness"));
//        this.setSitting(compound.getBoolean("Sitting"));
//    }
//
//    @Override
//    protected BodyController createBodyControl() {
//        return new CanisChordataEntity.BodyHelperController();
//    }
//
//    @Override
//    protected PathNavigator createNavigation(World worldIn) {
//        return new RunnerPathNavigator(this, worldIn);
//    }
//
//    protected SoundEvent getAngrySound() {
//        super.getAngrySound();
//        return RigoranthusSoundRegistry.CANIS_HUFF.get();
//    }
//
//    @Override
//    public int getAmbientSoundInterval() {
//        return this.getSupremeAngerModeTime() > 0 ? 20 : 120;
//    }
//    @Nullable
//    protected SoundEvent getEatingSound() {
//        return SoundEvents.MULE_EAT;
//    }
//    @Override
//    protected SoundEvent getAmbientSound() {
//        return RigoranthusSoundRegistry.CANIS_AMBIENT.get();
//    }
//    @Override
//    protected SoundEvent getDeathSound() {
//        return RigoranthusSoundRegistry.CANIS_DEATH.get();
//    }
//    @Override
//    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
//        return RigoranthusSoundRegistry.CANIS_HURT.get();
//    }
//    protected void playChestEquipsSound() {
//        this.playSound(SoundEvents.MULE_CHEST, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
//    }
//
//    @Override
//    protected void customServerAiStep() {
//        this.updatePersistentAnger((ServerWorld) this.level, true);
//
//        if (this.isAngry()) {
//            this.lastHurtByPlayerTime = this.tickCount;
//        }
//
//        if (this.getSupremeAngerModeTime() > 0 && this.getMoveControl().hasWanted()) {
//            double d0 = this.getMoveControl().getSpeedModifier();
//            if (d0 >= 1.0D) {
//                this.setSprinting(true);
//            } else {
//                this.setSprinting(false);
//            }
//        } else {
//            this.setSprinting(false);
//        }
//
//        super.customServerAiStep();
//    }
//
//
//    @Override
//    public boolean doHurtTarget(Entity entityIn) {
//        this.swingArms();
//        this.level.broadcastEntityEvent(this, (byte) 4);
//        float f = (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
//        float f1 = (int) f > 0 ? f / 2.0F + (float) this.random.nextInt((int) f) : f;
//        boolean flag = entityIn.hurt(DamageSource.mobAttack(this), f1);
//        if (flag) {
//            this.doEnchantDamageEffects(this, entityIn);
//        }
//
//        return flag;
//    }
//
//    @Override
//    public boolean hurt(DamageSource source, float amount) {
//        if (this.isInvulnerableTo(source)) {
//            return false;
//        } else {
//            this.setSitting(false);
//            return super.hurt(source, amount);
//        }
//    }
//
//    @Override
//    public void tick() {
//        if (!this.level.isClientSide) {
//            if (this.isDoingAction(CanisAction.DEFAULT, CanisAction.RUNNING)) {
//                this.setDefaultAction();
//            }
//        }
//
//        super.tick();
//
//        if (this.isAlive()) {
//            if (!this.level.isClientSide) {
//                this.handleRunning();
//            }
//
//            this.spawnParticles();
//        }
//
//        this.runAnim0 = this.runAnim;
//        if (this.isDoingAction(CanisAction.RUNNING)) {
//            this.runAnim = Math.min(this.runAnim + 0.125F, 0.75F);
//        } else if (this.isDoingAction(CanisAction.SHAKING)) {
//            this.runAnim = Math.min(this.runAnim + 0.125F, 1);
//        } else {
//            this.runAnim = Math.max(this.runAnim - 0.125F, 0);
//        }
//
//        this.sitAnim0 = this.sitAnim;
//        if (this.isSitting()) {
//            this.sitAnim = Math.min(this.sitAnim + 0.167F, 1);
//        } else {
//            this.sitAnim = Math.max(this.sitAnim - 0.167F, 0);
//        }
//
//        this.headShakeAnim0 = this.headShakeAnim;
//        if (this.headShakeAnim > 0) {
//            --this.headShakeAnim;
//        }
//    }
//
//    @Override
//    public void aiStep() {
//        super.aiStep();
//        if (this.attackTimer > 0) {
//            --this.attackTimer;
//        }
//
//        if (this.jukeboxPosition == null || !this.jukeboxPosition.closerThan(this.position(), 3.46D) || this.level.getBlockState(jukeboxPosition).getBlock() != Blocks.JUKEBOX) {
//            this.isPartying = false;
//            this.jukeboxPosition = null;
//        }
//
//        this.refreshDimensions();
//
//        if (!this.level.isClientSide) {
//            if (this.getSupremeAngerModeTime() > 0) {
//                this.setSupremeAngerModeTime(this.getSupremeAngerModeTime() - 1);
//            }
//
//            if (!this.isHungry() && this.getHunger() >= 0) {
//                this.setHunger(this.getHunger() + 1);
//            }
//
//            if (!this.isWet() && this.getWetess() >= 0) {
//                this.setWetness(this.getWetess() + 1);
//            }
//
//            if (this.shouldRun() && this.verticalCollision) {
//                if (--this.runningStamina <= 0) {
//                    this.runningStamina = -20 - this.random.nextInt(20);
//                }
//            } else if (this.onGround) {
//                if (this.runningStamina < 0) {
//                    ++this.runningStamina;
//                } else {
//                    this.runningStamina = 20 + this.random.nextInt(40);
//                }
//            }
//        }
//    }
//
//
//    private void spawnParticles() {
//        if (this.isWet()) {
//            if (this.tickCount % 6 == 0) {
//                double d0 = ((double) this.random.nextFloat() + 1.0D) * 0.06D;
//                double d1 = this.random.nextInt(360) - 360.0D;
//                double d2 = ((double) this.random.nextFloat() + 1.0D) * 14.0D;
//                d2 *= this.random.nextBoolean() ? 1.0D : -1.0D;
//
//                level.addParticle(ParticleTypes.RAIN, this.getRandomX(0.5D), this.getEyeY() + this.random.nextDouble() * 0.2D + 0.3D, this.getRandomZ(0.5D), d0, d1, d2);
//            }
//        }
//
//        if (this.isDoingAction(CanisAction.EATING)) {
//            ItemStack food = this.getSnack();
//            if (this.tickCount % 10 == 0 && !food.isEmpty()) {
//                if (this.level.isClientSide) {
//                    for (int i = 0; i < 6; ++i) {
//                        Vector3d vector3d = new Vector3d(((double) this.random.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, ((double) this.random.nextFloat() - 0.5D) * 0.1D);
//                        vector3d = vector3d.xRot(-this.xRot * ((float) Math.PI / 180F));
//                        vector3d = vector3d.yRot(-this.yRot * ((float) Math.PI / 180F));
//                        double d0 = (double) (-this.random.nextFloat()) * 0.2D;
//                        Vector3d vector3d1 = new Vector3d(((double) this.random.nextFloat() - 0.5D) * 0.2D, d0, 0.6D * this.getScale() + ((double) this.random.nextFloat() - 0.5D) * 0.2D);
//                        vector3d1 = vector3d1.yRot(-this.yBodyRot * ((float) Math.PI / 180F));
//                        vector3d1 = vector3d1.add(this.getX(), this.getEyeY(), this.getZ());
//                        this.level.addParticle(new ItemParticleData(ParticleTypes.ITEM, this.getItemInHand(this.getSnackHand())), vector3d1.x, vector3d1.y, vector3d1.z, vector3d.x, vector3d.y + 0.05D, vector3d.z);
//                    }
//                }
//
//                this.playSound(SoundEvents.GENERIC_EAT, 0.25F + 0.5F * (float) this.random.nextInt(2), (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
//            }
//        } else if (this.isDoingAction(CanisAction.CRYING)) {
//            if (this.level.isClientSide) {
//                if (this.tickCount % 2 == 0 && this.random.nextInt(4) > 0) {
//                    for (int i = 0; i < 2; ++i) {
//                        double d0 = i == 0 ? (double) (this.random.nextFloat()) * 0.15D + 0.1D : (double) (-this.random.nextFloat()) * 0.15D - 0.1D;
//                        double d1 = ((double) this.random.nextFloat()) * 0.1D + 0.15D;
//                        double d2 = i == 0 ? 0.15D : -0.15D;
//
//                        Vector3d vector3d = new Vector3d(d0, Math.random() * 0.2D + 0.1D, (double) (this.random.nextFloat()) * 0.2D + 0.1D);
//                        vector3d = vector3d.yRot(-this.yBodyRot * ((float) Math.PI / 180F));
//                        Vector3d vector3d1 = new Vector3d(d2, d1, 0.35D);
//                        vector3d1 = vector3d1.yRot(-this.yBodyRot * ((float) Math.PI / 180F));
//                        vector3d1 = vector3d1.add(this.getX(), this.getEyeY(), this.getZ());
//
//                        this.level.addParticle(ParticleTypes.RAIN, vector3d1.x, vector3d1.y, vector3d1.z, vector3d.x, vector3d.y + 0.05D, vector3d.z);
//                    }
//                }
//            }
//        }
//    }
//
//    @Override
//    public ActionResultType mobInteract(PlayerEntity player, Hand hand) {
//        ItemStack itemstack = player.getItemInHand(hand);
//
//        if (!itemstack.isEmpty()) {
//            if (!(this.isFood(itemstack) && !this.isHungry())) {
//                if (this.getMainHandItem().isEmpty() || (this.isHungry() && this.isSnack(itemstack) && !this.isSnack(this.getMainHandItem()))) {
//                    if (!this.getMainHandItem().isEmpty()) {
//                        this.dropItem(this.getMainHandItem());
//                    }
//
//                    if (this.isSnack(itemstack)) {
//                        this.stopBeingAngry();
//                    }
//
//                    ItemStack itemstack1 = itemstack.copy();
//                    itemstack1.setCount(1);
//                    this.setItemInHand(Hand.MAIN_HAND, itemstack1);
//                    this.handDropChances[EquipmentSlotType.MAINHAND.getIndex()] = 2.0F;
//                    this.usePlayerItem(player, itemstack);
//
//                    return ActionResultType.sidedSuccess(this.level.isClientSide);
//                }
//                return ActionResultType.PASS;
//            }
//        }
//
//        return super.mobInteract(player, hand);
//    }
//
//    public void eatSnack() {
//        if (!this.getSnack().isEmpty()) {
//            if (this.getSnack().isEdible()) {
//                this.heal((float)this.getSnack().getItem().getFoodProperties().getNutrition());
//            }
//            this.setItemInHand(this.getSnackHand(), this.getSnack().finishUsingItem(this.level, this));
//        }
//        this.setHunger(0);
//    }
//
//    @Override
//    public void calculateEntityAnimation(LivingEntity entity, boolean isFlying) {
//        this.animationSpeedOld = this.animationSpeed;
//        double d0 = this.getX() - this.xo;
//        double d1 = this.isDoingAction(CanisAction.RUNNING) ? this.getY() - this.yo : 0.0D;
//        double d2 = this.getZ() - this.zo;
//        float f = MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2) * 4.0F;
//        if (f > 1.0F) {
//            f = 1.0F;
//        }
//
//        this.animationSpeed += (f - this.animationSpeed) * 0.4F;
//        this.animationPosition += this.animationSpeed;
//    }
//
//    @Override
//    public boolean onClimbable() {
//        return this.isDoingAction(CanisAction.DEFAULT, CanisAction.RUNNING) && this.isBesideClimbableBlock() && !this.isSitting() && this.runningStamina > 0;
//    }
//
//    public boolean shouldClimb() {
//        return !this.onGround && this.onClimbable();
//    }
//
//    @Override
//    public boolean causeFallDamage(float distance, float damageMultiplier) {
//        return false;
//    }
//
//    public boolean isBesideClimbableBlock() {
//        return (this.entityData.get(RUNNING) & 1) != 0;
//    }
//
//    public void setBesideClimbableBlock(boolean running) {
//        byte b0 = this.entityData.get(RUNNING);
//        if (running) {
//            b0 = (byte) (b0 | 1);
//        } else {
//            b0 = (byte) (b0 & -2);
//        }
//
//        this.entityData.set(RUNNING, b0);
//    }
//
//    @Override
//    public EntitySize getDimensions(Pose pose) {
//        if (this.isSitting()) {
//            return SITTING_DIMENSIONS.scale(this.getScale());
//        } else {
//            return super.getDimensions(pose);
//        }
//    }
//
//    @Override
//    public double getMyRidingOffset() {
//        return this.isBaby() ? -0.05D : -0.3D;
//    }
//
//    @Override
//    public boolean canTakeItem(ItemStack itemstackIn) {
//        EquipmentSlotType equipmentslottype = MobEntity.getEquipmentSlotForItem(itemstackIn);
//        if (!this.getItemBySlot(equipmentslottype).isEmpty()) {
//            return false;
//        } else {
//            return equipmentslottype == EquipmentSlotType.MAINHAND && super.canTakeItem(itemstackIn);
//        }
//    }
//
//    public int getSupremeAngerModeTime() {
//        return this.entityData.get(SUPREME_ANGER_MODE_TIME);
//    }
//
//    public void setSupremeAngerModeTime(int time) {
//        this.entityData.set(SUPREME_ANGER_MODE_TIME, time);
//    }
//
//    @Override
//    public boolean canHoldItem(ItemStack stack) {
//        ItemStack heldstack = this.getItemBySlot(EquipmentSlotType.MAINHAND);
//        return heldstack.isEmpty() || this.getItemValue(stack) > this.getItemValue(heldstack);
//    }
//
//    private int getItemValue(ItemStack stack) {
//        if (this.isSnack(stack)) {
//            return this.isHungry() ? 2 : 1;
//        } else {
//            return 0;
//        }
//    }
//
//    @Override
//    protected void pickUpItem(ItemEntity itemEntity) {
//        ItemStack itemstack = itemEntity.getItem();
//        if (!this.isDoingAction(CanisAction.LOOKING_AT_ITEM) && this.canHoldItem(itemstack)) {
//            int i = itemstack.getCount();
//            if (i > 1) {
//                ItemEntity itementity = new ItemEntity(this.level, this.getX(), this.getY(), this.getZ(), itemstack.split(i - 1));
//                this.level.addFreshEntity(itementity);
//            }
//
//            this.dropItem(this.getMainHandItem());
//
//            this.onItemPickup(itemEntity);
//            this.setItemSlot(EquipmentSlotType.MAINHAND, itemstack.split(1));
//            this.handDropChances[EquipmentSlotType.MAINHAND.getIndex()] = 2.0F;
//            this.take(itemEntity, itemstack.getCount());
//            itemEntity.remove();
//
//            if (this.isSnack(itemstack)) {
//                this.stopBeingAngry();
//            }
//        }
//    }
//
//    public void throwHeldItem(Hand hand) {
//        ItemStack itemstack = this.getItemInHand(hand);
//        if (!itemstack.isEmpty() && !this.level.isClientSide) {
//            Item item = itemstack.getItem();
//
//            ItemEntity itementity = new ItemEntity(this.level, this.getX() + this.getLookAngle().x * 0.2D, this.getY() + this.getBbHeight() * 0.625F, this.getZ() + this.getLookAngle().z * 0.2D, itemstack);
//            Vector3d vector3d = new Vector3d(this.getLookAngle().x * 0.25D, 0.0D, this.getLookAngle().z * 0.25D);
//            itementity.setDeltaMovement(vector3d);
//            itementity.setPickUpDelay(40);
//            itementity.setThrower(this.getUUID());
//            this.level.addFreshEntity(itementity);
//            this.setItemInHand(hand, ItemStack.EMPTY);
//
//            this.swingArms();
//            this.level.broadcastEntityEvent(this, (byte)4);
//        }
//    }
//
//    public void dropItem(ItemStack itemStack) {
//        ItemEntity itementity = new ItemEntity(this.level, this.getX(), this.getEyeY() - (double)0.3F, this.getZ(), itemStack);
//        itementity.setPickUpDelay(40);
//        itementity.setThrower(this.getUUID());
//        this.level.addFreshEntity(itementity);
//    }
//
//
//    // SPAWNING //
//
//    public static boolean canChimpanzeeSpawn(EntityType<CanisChordataEntity> entity, IWorld world, SpawnReason reason, BlockPos pos, Random random) {
//        return world.getRawBrightness(pos, 0) > 8;
//    }
//
//    @Override
//    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
//        spawnDataIn = super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
//        this.setTypeForPosition(this, worldIn);
//        this.setHunger(this.random.nextInt(4800));
//        this.setWetness(this.random.nextInt(4800));
//        this.populateDefaultEquipmentSlots(difficultyIn);
//        return spawnDataIn;
//    }
//
//    public void setTypeForPosition(CanisChordataEntity entity, IWorld worldIn) {
//        if (worldIn.getBiome(this.blockPosition()).getRegistryName().getPath().contains("rainforest")) {
//            entity.setCanisEvolutionLevel(CanisEvolutionLevels.RAINFOREST.getId());
//        } else if (worldIn.getBiome(this.blockPosition()).getRegistryName().getPath().contains("bamboo")) {
//            entity.setCanisEvolutionLevel(CanisEvolutionLevels.BAMBOO.getId());
//        } else {
//            entity.setCanisEvolutionLevel(CanisEvolutionLevels.JUNGLE.getId());
//        }
//    }
//
//    protected void populateDefaultEquipmentSlots(DifficultyInstance difficultyIn) {
//        if (this.random.nextFloat() < 0.1F) {
//            float f = this.random.nextFloat();
//            ItemStack itemstack;
//            if (f < 0.6F) {
//                if (this.level.getBiome(this.blockPosition()).getRegistryName().getPath().contains("bamboo")) {
//                    itemstack = new ItemStack(ItemInit.BONE_BOW.get());
//                }
//            }
//            this.setItemSlot(EquipmentSlotType.MAINHAND, itemstack);
//        }
//
//    }
//
//    @Override
//    public CanisChordataEntity getBreedOffspring(ServerWorld world, AgeableEntity ageableEntity) {
//        CanisChordataEntity baby = RigoranthusEntityTypes.CANIS_CHORDATA.get().create(world);
//        baby.setCanisEvolutionLevel(this.random.nextBoolean() ? this.getCanisEvolutionLevel() : ((CanisChordataEntity)ageableEntity).getCanisEvolutionLevel());
//        return baby;
//    }
//
//    // DATA //
//
//    public int getCanisEvolutionLevel() {
//        return this.entityData.get(EVOLUTION_LEVEL);
//    }
//
//    public void setCanisEvolutionLevel(int type) {
//        this.entityData.set(EVOLUTION_LEVEL, type);
//    }
//
//    public int getAttackTimer() {
//        return this.attackTimer;
//    }
//
//    @Override
//    public int getRemainingPersistentAngerTime() {
//        return this.entityData.get(ANGER_TIME);
//    }
//
//    @Override
//    public void setRemainingPersistentAngerTime(int time) {
//        this.entityData.set(ANGER_TIME, time);
//    }
//
//    public Direction getFacing() {
//        return this.entityData.get(FACING);
//    }
//
//    public void setFacing(Direction direction) {
//        this.entityData.set(FACING, direction);
//    }
//
//    public boolean isSitting() {
//        return this.entityData.get(SITTING);
//    }
//
//    public void setSitting(boolean sitting) {
//        this.entityData.set(SITTING, sitting);
//    }
//
//    @Override
//    public UUID getPersistentAngerTarget() {
//        return this.lastHurtBy;
//    }
//
//    @Override
//    public void setPersistentAngerTarget(UUID target) {
//        this.lastHurtBy = target;
//    }
//
//    @Override
//    public void startPersistentAngerTimer() {
//        this.setRemainingPersistentAngerTime(ANGER_RANGE.randomValue(this.random));
//    }
//
//    // NEED STUFF //
//
//    public int getHunger() {
//        return this.entityData.get(HUNGER);
//    }
//
//    public void setHunger(int amount) {
//        this.entityData.set(HUNGER, amount);
//    }
//
//    public boolean isHungry() {
//        return this.getHunger() >= 9600;
//    }
//
//    public boolean needsSnack() {
//        return this.isHungry() && this.getSnack().isEmpty();
//    }
//
//    public Hand getSnackHand() {
//        if (!this.getSnack(Hand.MAIN_HAND).isEmpty()) {
//            return Hand.MAIN_HAND;
//        } else {
//            return Hand.OFF_HAND;
//        }
//    }
//
//    public ItemStack getSnack() {
//        if (!this.getSnack(Hand.MAIN_HAND).isEmpty()) {
//            return this.getSnack(Hand.MAIN_HAND);
//        } else if (!this.getSnack(Hand.OFF_HAND).isEmpty()) {
//            return this.getSnack(Hand.OFF_HAND);
//        }
//        return ItemStack.EMPTY;
//    }
//
//    public ItemStack getSnack(Hand hand) {
//        ItemStack snack = this.getItemInHand(hand);
//        if (this.isSnack(snack)) {
//            return snack;
//        }
//        return ItemStack.EMPTY;
//    }
//
//    @Override
//    public boolean isFood(ItemStack stack) {
//        return stack.getItem().is(RigoranthusTagRegistry.Items.CANIS_FOOD);
//    }
//
//    public boolean isSnack(ItemStack stack) {
//        return stack.getItem().is(RigoranthusTagRegistry.Items.CANIS_SNACKS);
//    }
//
//    public int getWetness() {
//        return this.entityData.get(WETNESS);
//    }
//
//    public void setDirtiness(int amount) {
//        this.entityData.set(WETNESS, amount);
//    }
//
//    public boolean isWet() {
//        return this.getWetness() >= 12000;
//    }
//
//    public void getCleaned() {
//        this.setDirtiness(0);
//    }
//
//    public boolean isInSunlight() {
//        BlockPos blockpos = this.getVehicle() instanceof BoatEntity ? (new BlockPos(this.getX(), (double)Math.round(this.getY()), this.getZ())).above() : new BlockPos(this.getX(), (double)Math.round(this.getY()), this.getZ());
//        return this.level.getBrightness(LightType.SKY, blockpos) > 8;
//    }
//
//    // ACTION STUFF //
//
//    public CanisAction getAction() {
//        return CanisAction.byId(this.entityData.get(ACTION));
//    }
//
//    public boolean isDoingAction(CanisAction... actions) {
//        for (CanisAction action : actions) {
//            if (this.getAction() == action) {
//                return true;
//            }
//        }
//
//        return false;
//    }
//
//    public void setAction(CanisAction action) {
//        this.entityData.set(ACTION, (byte)action.getId());
//    }
//
//    public void setDefaultAction() {
//        boolean flag = !this.isPassenger() && this.shouldClimb();
//
//        if (flag) {
//            this.setAction(CanisAction.RUNNING);
//        } else {
//            this.setAction(CanisAction.DEFAULT);
//        }
//    }
//
//    // ANIMATION //
//
//    public boolean isPartying() {
//        return this.isPartying;
//    }
//
//    @Override
//    @OnlyIn(Dist.CLIENT)
//    public void setRecordPlayingNearby(BlockPos pos, boolean isPartying) {
//        this.jukeboxPosition = pos;
//        this.isPartying = isPartying;
//    }
//
//    public boolean isMouthOpen() {
//        if (this.isDoingAction(CanisAction.EATING)) {
//            return Math.sin(Math.PI * this.tickCount * 0.2D) > 0;
//        } else if (this.isDoingAction(CanisAction.CRYING, CanisAction.JUMPING)) {
//            return true;
//        } else if (this.getApeModeTime() > 0 || this.isAngry() || this.isHungry() || this.isPartying()) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    @OnlyIn(Dist.CLIENT)
//    public float getRunningAnim(float partialTicks) {
//        return MathHelper.lerp(partialTicks, this.runAnim0, this.runAnim);
//    }
//
//    @OnlyIn(Dist.CLIENT)
//    public float getSitAnim(float partialTicks) {
//        return MathHelper.lerp(partialTicks, this.sitAnim0, this.sitAnim);
//    }
//
//    @OnlyIn(Dist.CLIENT)
//    public float getHeadShakeAnim(float partialTicks) {
//        return MathHelper.lerp(partialTicks, this.headShakeAnim0, this.headShakeAnim);
//    }
//
//    public void swingArms() {
//        this.attackTimer = 10;
//    }
//
//    public void shakeHead() {
//        this.headShakeAnim = 40;
//        this.headShakeAnim0 = 40;
//    }
//
//    public void shakeHead(IParticleData particleData) {
//        this.shakeHead();
//
//        double d0 = this.random.nextGaussian() * 0.02D;
//        double d1 = this.random.nextGaussian() * 0.02D;
//        double d2 = this.random.nextGaussian() * 0.02D;
//
//        this.level.addParticle(particleData, this.getX(), this.getY(1.0D), this.getZ(), d0, d1, d2);
//    }
//
//    @Override
//    public void handleEntityEvent(byte id) {
//        if (id == 4) {
//            this.swingArms();
//        } else if (id == 8) {
//            this.shakeHead(ParticleTypes.CANIS_NEEDS_FOOD.get());
//        } else {
//            super.handleEntityEvent(id);
//        }
//    }
//
//    // CONTROLLERS //
//
//    class LookHelperController extends LookController {
//
//        public LookHelperController() {
//            super(CanisChordataEntity.this);
//        }
//
//        public void tick() {
//            if (this.resetXRotOnTick()) {
//                CanisChordataEntity.this.xRot = 0.0F;
//            }
//
//            if (this.hasWanted) {
//                this.hasWanted = false;
//                CanisChordataEntity.this.yHeadRot = this.rotateTowards(CanisChordataEntity.this.yHeadRot, this.getYRotD(), this.yMaxRotSpeed);
//                CanisChordataEntity.this.xRot = this.rotateTowards(CanisChordataEntity.this.xRot, this.getXRotD(), this.xMaxRotAngle);
//            } else {
//                CanisChordataEntity.this.yHeadRot = this.rotateTowards(CanisChordataEntity.this.yHeadRot, CanisChordataEntity.this.yBodyRot, 10.0F);
//            }
//
//            Direction facing = CanisChordataEntity.this.getFacing();
//            if (CanisChordataEntity.this.isDoingAction(CanisAction.RUNNING) && facing != Direction.DOWN) {
//                CanisChordataEntity.this.yHeadRot = MathHelper.rotateIfNecessary(CanisChordataEntity.this.yHeadRot, facing.toYRot(), (float)CanisChordataEntity.this.getMaxHeadYRot());
//            } else if (!CanisChordataEntity.this.getNavigation().isDone()) {
//                CanisChordataEntity.this.yHeadRot = MathHelper.rotateIfNecessary(CanisChordataEntity.this.yHeadRot, CanisChordataEntity.this.yBodyRot, (float)CanisChordataEntity.this.getMaxHeadYRot());
//            }
//        }
//    }
//
//    class BodyHelperController extends BodyController {
//        private int bodyRotationTickCounter;
//        private Direction prevFacing;
//
//        public BodyHelperController() {
//            super(CanisChordataEntity.this);
//        }
//
//        public void clientTick() {
//            super.clientTick();
//
//            Direction facing = CanisChordataEntity.this.getFacing();
//
//            if (facing != this.prevFacing || !CanisChordataEntity.this.isDoingAction(CanisAction.RUNNING)) {
//                this.bodyRotationTickCounter = 10;
//            }
//
//            this.prevFacing = facing;
//
//            if (facing != Direction.DOWN && CanisChordataEntity.this.isDoingAction(CanisAction.RUNNING)) {
//                int i = this.bodyRotationTickCounter;
//                float f = MathHelper.clamp((float)i / 10.0F, 0.0F, 1.0F);
//                float f1 = 90.0F * f;
//                CanisChordataEntity.this.yBodyRot = MathHelper.rotateIfNecessary(CanisChordataEntity.this.yBodyRot, facing.toYRot(), f1);
//
//                if (this.bodyRotationTickCounter > 0) {
//                    --this.bodyRotationTickCounter;
//                }
//            } else if (facing != Direction.DOWN && CanisChordataEntity.this.isDoingAction(CanisAction.RUNNING)) {
//                int i = this.bodyRotationTickCounter;
//                float f = MathHelper.clamp((float)i / 10.0F, 0.0F, 1.0F);
//                float f1 = 90.0F * f;
//                CanisChordataEntity.this.yBodyRot = MathHelper.rotateIfNecessary(CanisChordataEntity.this.yBodyRot, facing.toYRot(), f1);
//
//                if (this.bodyRotationTickCounter > 0) {
//                    --this.bodyRotationTickCounter;
//                }
//            }
//        }
//    }
//}