package com.platinumg17.rigoranthusemortisreborn.entity.mobs;

import com.platinumg17.rigoranthusemortisreborn.config.Config;
import com.platinumg17.rigoranthusemortisreborn.core.registry.RigoranthusSoundRegistry;
import com.platinumg17.rigoranthusemortisreborn.entity.goals.SunderedCadaverAttackGoal;
import com.platinumg17.rigoranthusemortisreborn.magica.client.particle.ParticleColor;
import com.platinumg17.rigoranthusemortisreborn.magica.client.particle.ParticleUtil;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.IAnimationListener;
import com.platinumg17.rigoranthusemortisreborn.magica.common.entity.ModEntities;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.ClimberPathNavigator;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.GroundPathHelper;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

public class SunderedCadaverEntity extends CreatureEntity implements IAnimatable, IAnimationListener {

    private static final DataParameter<Byte> DATA_FLAGS_ID = EntityDataManager.defineId(SunderedCadaverEntity.class, DataSerializers.BYTE);
    private static final Predicate<Difficulty> DOOR_BREAKING_PREDICATE = (p_213697_0_) -> {return p_213697_0_ == Difficulty.HARD;};
    private final AnimationFactory animationFactory = new AnimationFactory(this);
    private final BreakDoorGoal breakDoorGoal = new BreakDoorGoal(this, DOOR_BREAKING_PREDICATE);
    private boolean canBreakDoors;
//    public static final DataParameter<Boolean> CASTING = EntityDataManager.defineId(SunderedCadaverEntity.class, DataSerializers.BOOLEAN);
//    public static final DataParameter<Boolean> SUMMONED = EntityDataManager.defineId(SunderedCadaverEntity.class, DataSerializers.BOOLEAN);
//    public static final DataParameter<Optional<BlockPos>> HOME = EntityDataManager.defineId(SunderedCadaverEntity.class, DataSerializers.OPTIONAL_BLOCK_POS);
//    public Spell spell = Spell.EMPTY;
    public ParticleColor color = ParticleUtil.defaultParticleColor();

    public SunderedCadaverEntity(EntityType<SunderedCadaverEntity> type, World worldIn) {
        super(type, worldIn);
        this.noCulling = true;
    }

    public SunderedCadaverEntity(World world) {
        super(ModEntities.SUNDERED_CADAVER, world);
    }

    @Override
    public EntityType<?> getType() {
        return ModEntities.SUNDERED_CADAVER;
    }

    @Override
    public void onSyncedDataUpdated(DataParameter<?> data) {super.onSyncedDataUpdated(data);}

    protected PathNavigator createNavigation(World world) {
        return new ClimberPathNavigator(this, world);
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level.isClientSide) {
            this.setClimbing(this.horizontalCollision);
            if (level.getGameTime() % 20 == 0 && !this.isDeadOrDying() && this.hasCustomName()) {
                this.heal(0.1f);
            }
        }
    }

    @Override
    public void die(DamageSource source) {
        if(!level.isClientSide){
            refreshDimensions();
            ParticleUtil.spawnPoof((ServerWorld) level, blockPosition());
            if(source.getEntity() != null && source.getEntity() instanceof MobEntity)
                ((MobEntity) source.getEntity()).setTarget(null);
            return;
        }
        super.die(source);
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

    private <E extends IAnimatable> PlayState idlePredicate(AnimationEvent<E> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
        return PlayState.CONTINUE;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_FLAGS_ID, (byte)0);
    }

    @Override
    protected void updateControlFlags() {
        super.updateControlFlags();
        this.goalSelector.setControlFlag(Goal.Flag.MOVE, true);
        this.goalSelector.setControlFlag(Goal.Flag.JUMP, true);
        this.goalSelector.setControlFlag(Goal.Flag.LOOK, true);
    }

    @Override public AnimationFactory getFactory() {
        return this.animationFactory;
    }
    @Override public boolean causeFallDamage(float distance, float damageMultiplier) {
        return false;
    }
    protected boolean isSunSensitive() {
        return !this.hasCustomName();
    }

    @Override
    public void aiStep() {
        if (this.isAlive()) {
            boolean flag = this.isSunSensitive() && this.isSunBurnTick();
            if (flag) {
                ItemStack itemstack = this.getItemBySlot(EquipmentSlotType.HEAD);
                if (!itemstack.isEmpty()) {
                    if (itemstack.isDamageableItem()) {
                        itemstack.setDamageValue(itemstack.getDamageValue() + this.random.nextInt(2));
                        if (itemstack.getDamageValue() >= itemstack.getMaxDamage()) {
                            this.broadcastBreakEvent(EquipmentSlotType.HEAD);
                            this.setItemSlot(EquipmentSlotType.HEAD, ItemStack.EMPTY);
                        }
                    }
                    flag = false;
                }
                if (flag) { this.setSecondsOnFire(8); }
            }
        }
        super.aiStep();
    }

    @Override
    public CreatureAttribute getMobType() {
        return CreatureAttribute.UNDEAD;
    }

    public static AttributeModifierMap.MutableAttribute attributes() {
        return MobEntity.createMobAttributes()
            .add(Attributes.MAX_HEALTH, Config.sunderedCadaverMaxHealth.get())
            .add(Attributes.MOVEMENT_SPEED, Config.sunderedCadaverMovementSpeed.get())
            .add(Attributes.ATTACK_DAMAGE, Config.sunderedCadaverAttackDamage.get())
            .add(Attributes.ARMOR, Config.sunderedCadaverArmorValue.get())
            .add(Attributes.ATTACK_KNOCKBACK, Config.sunderedCadaverAttackKnockback.get())
            .add(Attributes.KNOCKBACK_RESISTANCE, Config.sunderedCadaverKnockbackResistance.get())
            .add(Attributes.FOLLOW_RANGE, 30.0D).add(Attributes.SPAWN_REINFORCEMENTS_CHANCE);
    }

    @Override
    public boolean hurt(@Nonnull DamageSource source, float amount) {
        if(source == DamageSource.FALL || source == DamageSource.IN_WALL || source == DamageSource.SWEET_BERRY_BUSH || source == DamageSource.CACTUS)
            return false;
        return super.hurt(source, amount);
    }

    public enum Animations{ POUNCING }

    @Override
    public void startAnimation(int arg) {
        try{
            if(arg == SunderedCadaverEntity.Animations.POUNCING.ordinal()) {
                AnimationController controller = this.animationFactory.getOrCreateAnimationData(this.hashCode()).getAnimationControllers().get("attackController");
                controller.markNeedsReload();
                controller.setAnimation(new AnimationBuilder().addAnimation("attack", false));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.addBehaviourGoals();
    }

    protected void addBehaviourGoals() {
        this.goalSelector.addGoal(1, new SunderedCadaverAttackGoal(this, true));
//        this.goalSelector.addGoal(2, new CastSpellGoal(this, 1.2d, 20,15f, () -> castCooldown <= 0 && !this.entityData.get(SUMMONED), Animations.CAST.ordinal(), 20));
        this.goalSelector.addGoal(2, new FollowMobGoal(this, (float) 1, 10, 5));
        this.goalSelector.addGoal(5, new MoveTowardsTargetGoal(this, 1.0f, 8));
        this.goalSelector.addGoal(6, new MoveThroughVillageGoal(this, 1.0D, true, 4, this::canBreakDoors));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new RandomWalkingGoal(this, 1.0f));
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
//        NBTUtil.storeBlockPos(tag, "home", getHome());
//        tag.putInt("cast", castCooldown);
    }

    public void readAdditionalSaveData(CompoundNBT nbt) {
        super.readAdditionalSaveData(nbt);
        this.setCanBreakDoors(nbt.getBoolean("CanBreakDoors"));
//        if(NBTUtil.hasBlockPos(tag, "home")){
//            setHome(NBTUtil.getBlockPos(tag, "home"));}
//        this.castCooldown = tag.getInt("cast");
    }

    @Nullable
    public ILivingEntityData finalizeSpawn(IServerWorld serverWorld, DifficultyInstance difficulty, SpawnReason spawnReason, @Nullable ILivingEntityData entityData, @Nullable CompoundNBT nbt) {
        entityData = super.finalizeSpawn(serverWorld, difficulty, spawnReason, entityData, nbt);
        float f = difficulty.getSpecialMultiplier();
//        this.setCanPickUpLoot(this.random.nextFloat() < 0.55F * f);
        if (entityData == null) {
            entityData = new SunderedCadaverEntity.GroupData(true);
            if (difficulty.getDifficulty() == Difficulty.HARD && serverWorld.getRandom().nextFloat() < 0.1F * difficulty.getSpecialMultiplier()) {
                ((SunderedCadaverEntity.GroupData)entityData).setRandomEffect(serverWorld.getRandom());
            }
        }
        if (entityData instanceof SunderedCadaverEntity.GroupData) {
            SunderedCadaverEntity.GroupData cadaverGroupData = (SunderedCadaverEntity.GroupData)entityData;
            Effect effect = (cadaverGroupData).effect;

            if (effect != null) {
                this.addEffect(new EffectInstance(effect, Integer.MAX_VALUE));
            }
            if (cadaverGroupData.canSpawnJockey) {
                if ((double)serverWorld.getRandom().nextFloat() < 0.05D) {
                    List<ChickenEntity> list = serverWorld.getEntitiesOfClass(ChickenEntity.class, this.getBoundingBox().inflate(5.0D, 3.0D, 5.0D), EntityPredicates.ENTITY_NOT_BEING_RIDDEN);
                    if (!list.isEmpty()) {
                        ChickenEntity chickenentity = list.get(0);
                        chickenentity.setChickenJockey(true);
                        this.startRiding(chickenentity);
                    }
                } else if ((double)serverWorld.getRandom().nextFloat() < 0.05D) {
                    ChickenEntity chickenentity1 = EntityType.CHICKEN.create(this.level);
                    chickenentity1.moveTo(this.getX(), this.getY(), this.getZ(), this.yRot, 0.0F);
                    chickenentity1.finalizeSpawn(serverWorld, difficulty, SpawnReason.JOCKEY, (ILivingEntityData)null, (CompoundNBT)null);
                    chickenentity1.setChickenJockey(true);
                    this.startRiding(chickenentity1);
                    serverWorld.addFreshEntity(chickenentity1);
                }
            }
//            this.populateDefaultEquipmentSlots(difficulty);
//            this.populateDefaultEquipmentEnchantments(difficulty);
        }
//        if (this.getItemBySlot(EquipmentSlotType.HEAD).isEmpty()) {
//            LocalDate localdate = LocalDate.now();
//            int i = localdate.get(ChronoField.DAY_OF_MONTH);
//            int j = localdate.get(ChronoField.MONTH_OF_YEAR);
//            if (j == 10 && i == 31 && this.random.nextFloat() < 0.25F) {
//                this.setItemSlot(EquipmentSlotType.HEAD, new ItemStack(this.random.nextFloat() < 0.1F ? Blocks.JACK_O_LANTERN : Blocks.CARVED_PUMPKIN));
//                this.armorDropChances[EquipmentSlotType.HEAD.getIndex()] = 0.0F;
//            }
//        }
        this.setCanBreakDoors(this.supportsBreakDoorGoal() && this.random.nextFloat() < f * 0.1F);
        this.handleAttributes(f);
        return entityData;
    }

    @Override
    public boolean onClimbable() {
        return this.isClimbing();
    }

    public boolean isClimbing() {
        return (this.entityData.get(DATA_FLAGS_ID) & 1) != 0;
    }

    public void setClimbing(boolean climbing) {
        byte b0 = this.entityData.get(DATA_FLAGS_ID);
        if (climbing) {
            b0 = (byte)(b0 | 1);
        } else {
            b0 = (byte)(b0 & -2);
        }

        this.entityData.set(DATA_FLAGS_ID, b0);
    }
    @Override
    protected float getStandingEyeHeight(Pose pose, EntitySize size) {
        return 0.85F;
    }

    public static class GroupData implements ILivingEntityData {

        public Effect effect;
        public final boolean canSpawnJockey;

        public GroupData(boolean canBeJockey) {
            this.canSpawnJockey = canBeJockey;
        }
        public void setRandomEffect(Random rand) {
            int i = rand.nextInt(5);
            if (i <= 1) {
                this.effect = Effects.MOVEMENT_SPEED;
            } else if (i <= 2) {
                this.effect = Effects.DAMAGE_BOOST;
            } else if (i <= 3) {
                this.effect = Effects.REGENERATION;
            }
        }
    }

    protected void handleAttributes(float multiplier) {
        this.randomizeReinforcementsChance();
        this.getAttribute(Attributes.KNOCKBACK_RESISTANCE).addPermanentModifier(new AttributeModifier("Random spawn bonus", this.random.nextDouble() * (double)0.05F, AttributeModifier.Operation.ADDITION));
        double d0 = this.random.nextDouble() * 1.5D * (double)multiplier;
        if (d0 > 1.0D) {
            this.getAttribute(Attributes.FOLLOW_RANGE).addPermanentModifier(new AttributeModifier("Random Cadaver-spawn bonus", d0, AttributeModifier.Operation.MULTIPLY_TOTAL));
        }
        if (this.random.nextFloat() < multiplier * 0.05F) {
            this.getAttribute(Attributes.SPAWN_REINFORCEMENTS_CHANCE).addPermanentModifier(new AttributeModifier("Leader Cadaver bonus", this.random.nextDouble() * 0.25D + 0.5D, AttributeModifier.Operation.ADDITION));
            this.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier("Leader Cadaver bonus", this.random.nextDouble() * 3.0D + 1.0D, AttributeModifier.Operation.MULTIPLY_TOTAL));
        }
    }

    protected void randomizeReinforcementsChance() {
        this.getAttribute(Attributes.SPAWN_REINFORCEMENTS_CHANCE).setBaseValue(this.random.nextDouble() * net.minecraftforge.common.ForgeConfig.SERVER.zombieBaseSummonChance.get());
    }

    @Override protected int getExperienceReward(PlayerEntity player) {return 10 + this.level.random.nextInt(5);}
    @Override protected SoundEvent getAmbientSound() {return RigoranthusSoundRegistry.CADAVER_AMBIENT.get();}
    @Override protected SoundEvent getDeathSound() {return RigoranthusSoundRegistry.CADAVER_DEATH.get();}
    @Override protected SoundEvent getHurtSound(DamageSource damageSourceIn) { return RigoranthusSoundRegistry.CADAVER_HURT.get();}
    @Override protected void playStepSound(BlockPos pos, BlockState blockIn) {this.playSound(RigoranthusSoundRegistry.UNDEAD_STEP.get(), 0.20F, 0.5F);}
}


//    @Override
//    public void performRangedAttack(LivingEntity p_82196_1_, float p_82196_2_) {
//        EntitySpellResolver resolver = new EntitySpellResolver(new SpellContext(spell, this).withColors(color.toWrapper()));
//        EntityProjectileSpell projectileSpell = new EntityProjectileSpell(level, resolver);
//        projectileSpell.setColor(color.toWrapper());
//        projectileSpell.shoot(this, this.xRot, this.yRot, 0.0F, 1.0f, 0.8f);
//        level.addFreshEntity(projectileSpell);
//        this.castCooldown = 40;
//    }