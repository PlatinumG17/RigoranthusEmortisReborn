package com.platinumg17.rigoranthusemortisreborn.entity.mobs;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.core.init.ItemInit;
import com.platinumg17.rigoranthusemortisreborn.core.registry.RigoranthusSoundRegistry;
import com.platinumg17.rigoranthusemortisreborn.entity.ITameable;
import com.platinumg17.rigoranthusemortisreborn.entity.RigoranthusEntityTypes;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.AbstractSkeletonEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.GhastEntity;
import net.minecraft.entity.monster.ZombifiedPiglinEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.passive.horse.AbstractChestedHorseEntity;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.state.IntegerProperty;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.GameRules;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Predicate;

public class CanisChordataEntity extends AbstractChestedHorseEntity implements IAnimatable, ITameable, IAngerable {
    public static final IntegerProperty EVOLUTION_LEVEL = IntegerProperty.create("evolution_level", 0, 4);
    public static final DataParameter<Integer> STATE = EntityDataManager.defineId(CanisChordataEntity.class, DataSerializers.INT);
    private final AnimationFactory animationFactory = new AnimationFactory(this);

    private static final DataParameter<Boolean> DATA_INTERESTED_ID = EntityDataManager.defineId(CanisChordataEntity.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> DATA_REMAINING_ANGER_TIME = EntityDataManager.defineId(CanisChordataEntity.class, DataSerializers.INT);
    public static final Predicate<LivingEntity> PREY_SELECTOR = (p_213440_0_) -> {
        EntityType<?> entitytype = p_213440_0_.getType();
        return entitytype == EntityType.SHEEP || entitytype == EntityType.RABBIT || entitytype == EntityType.FOX;
    };
    private float interestedAngle;
    private float interestedAngleO;
    private static final RangedInteger PERSISTENT_ANGER_TIME = TickRangeConverter.rangeOfSeconds(20, 39);
    private UUID persistentAngerTarget;
    //private boolean isTamed;
    //private UUID owner;

    public CanisChordataEntity(EntityType<? extends AbstractChestedHorseEntity> p_i48564_1_, World p_i48564_2_) {
        super(p_i48564_1_, p_i48564_2_);
        this.setTame(false);
        this.noCulling = true;
        this.moveControl = new MovementController(this);
    }

    private <E extends IAnimatable> PlayState animationPredicate(AnimationEvent<E> event) {
        if (!this.dead && !this.isDeadOrDying()) {
            if (this.getState() == State.ATTACKING) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.Canis_Chordata_idle.new", false));
                return PlayState.CONTINUE;
            }
        }
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.canis_chordata.idle", true));
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.canis_chordata.idle", true));
        }
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
    public boolean causeFallDamage(float distance, float damageMultiplier) {
        return false;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.animationFactory;
    }

    public enum State {
        IDLE, ATTACKING
    }

    public State getState() {
        State[] states = State.values();
        return states[MathHelper.clamp(this.entityData.get(STATE), 0, states.length - 1)];
    }

    public void setState(State state) {
        this.entityData.set(STATE, state.ordinal());
    }

    @Override
    public boolean checkSpawnRules(@Nonnull IWorld worldIn, @Nonnull SpawnReason spawnReasonIn) {
        return super.checkSpawnRules(worldIn, spawnReasonIn) && this.blockPosition().getY() < 60 && !worldIn.canSeeSky(this.blockPosition()); /// use the block pos for dweller
    }

    public static boolean canSpawn(EntityType<CanisChordataEntity> type, IServerWorld world, SpawnReason reason, BlockPos pos, Random random) {
        //noinspection deprecation
        if (pos.getY() >= world.getSeaLevel() - 10 || world.canSeeSky(pos) || random.nextDouble() < 0.15) {
            return false;
        } else {
            return checkMobSpawnRules(type, world, reason, pos, random);
        }
    }

    public boolean wantsToAttack(LivingEntity p_142018_1_, LivingEntity p_142018_2_) {
        if (!(p_142018_1_ instanceof CreeperEntity) && !(p_142018_1_ instanceof GhastEntity)) {
            if (p_142018_1_ instanceof CanisChordataEntity) {
                CanisChordataEntity canisChordataEntity = (CanisChordataEntity)p_142018_1_;
                return !canisChordataEntity.isTame() || canisChordataEntity.getOwner() != p_142018_2_;
            } else if (p_142018_1_ instanceof PlayerEntity && p_142018_2_ instanceof PlayerEntity && !((PlayerEntity)p_142018_2_).canHarmPlayer((PlayerEntity)p_142018_1_)) {
                return false;
            } else if (p_142018_1_ instanceof AbstractHorseEntity && ((AbstractHorseEntity)p_142018_1_).isTamed()) {
                return false;
            } else {
                return !(p_142018_1_ instanceof TameableEntity) || !((TameableEntity)p_142018_1_).isTame();
            }
        } else {
            return false;
        }
    }
    @Override
    public boolean canBeLeashed(PlayerEntity p_184652_1_) {
        return this.isTamed() && !this.isLeashed() && !this.isAngry();
    }

    @OnlyIn(Dist.CLIENT)
    public Vector3d getLeashOffset() {
        return new Vector3d(0.0D, (double)(0.6F * this.getEyeHeight()), (double)(this.getBbWidth() * 0.4F));
    }
/*    @OnlyIn(Dist.CLIENT)
    public float getTailAngle() {
        if (this.isAngry()) {
            return 1.5393804F;
        } else {
            return this.isTame() ? (0.55F - (this.getMaxHealth() - this.getHealth()) * 0.02F) * (float)Math.PI : ((float)Math.PI / 5F);
        }
    }*/
    @Override
    public CanisChordataEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        CanisChordataEntity canisChordataEntity = RigoranthusEntityTypes.CANIS_CHORDATA.get().create(p_241840_1_);
        UUID uuid = this.getOwnerUUID();
        if (uuid != null) {
            canisChordataEntity.setOwnerUUID(uuid);
            canisChordataEntity.setTame(true);
        }
        return canisChordataEntity;
    }
    @Override
    public int getRemainingPersistentAngerTime() {
        return this.entityData.get(DATA_REMAINING_ANGER_TIME);
    }
    @Override
    public void setRemainingPersistentAngerTime(int p_230260_1_) {
        this.entityData.set(DATA_REMAINING_ANGER_TIME, p_230260_1_);
    }
    @Override
    public void startPersistentAngerTimer() {
        this.setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.randomValue(this.random));
    }
    @Override
    @Nullable
    public UUID getPersistentAngerTarget() {
        return this.persistentAngerTarget;
    }
    @Override
    public void setPersistentAngerTarget(@Nullable UUID p_230259_1_) {
        this.persistentAngerTarget = p_230259_1_;
    }
    @Override
    public void aiStep() {
        super.aiStep();
        if (!this.level.isClientSide && !this.isPathFinding() && this.onGround) {
            this.level.broadcastEntityEvent(this, (byte)8);
        }
        if (!this.level.isClientSide) {
            this.updatePersistentAnger((ServerWorld)this.level, true);
        }
    }
    @Override
    protected int getInventorySize() {
        return this.hasChest() ? 17 : super.getInventorySize();
    }
    @Override
    public int getInventoryColumns() {
        return 5;
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return 8;
    }
    @Override
    public boolean isFood(ItemStack p_70877_1_) {
        Item item = p_70877_1_.getItem();
        return item.isEdible() && item.getFoodProperties().isMeat();
    }
    @Override
    public boolean tameWithName(PlayerEntity p_110263_1_) {
        this.setOwnerUUID(p_110263_1_.getUUID());
        this.setTamed(true);
        if (p_110263_1_ instanceof ServerPlayerEntity) {
            CriteriaTriggers.TAME_ANIMAL.trigger((ServerPlayerEntity)p_110263_1_, this);
        }

        this.level.broadcastEntityEvent(this, (byte)7);
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
                    this.setTamed(true);
                    this.setOwnerUUID(uuid);
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
        if (source == DamageSource.LIGHTNING_BOLT)
            return false;
        return super.hurt(source, amount);
    }
        Entity sourceentity = (Entity) dependencies.get("sourceentity");
        IWorld world = (IWorld) dependencies.get("world");
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
                .add(Attributes.MAX_HEALTH, 100.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.27D)
                .add(Attributes.ATTACK_DAMAGE, 4.0D)
                .add(Attributes.FOLLOW_RANGE, 64.0D)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE, 4)
                .add(Attributes.JUMP_STRENGTH, 1.4)
                .add(Attributes.ARMOR, 6)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.4)
                .add(Attributes.ATTACK_KNOCKBACK, 1.25);
    }
/*   @Override
   public boolean isTamed() {
      return this.getFlag(2);
   }
   @Override
   public void setTamed(boolean p_110234_1_) {
      this.setFlag(2, p_110234_1_);
   }*/

    @Override
    protected int getExperienceReward(PlayerEntity player) {
        return 25 + this.level.random.nextInt(5);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        if (!this.isTamed()) {
            this.goalSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
            this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
            this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, false));
            this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
        }
//        if (this.isTamed()) {
//        this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
        this.goalSelector.addGoal(7, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(10, new LookAtGoal(this, PlayerEntity.class, 8.0F));
//        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
//        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 10, true, false, this::isAngryAt));
//        this.targetSelector.addGoal(5, new NonTamedTargetGoal<>(this, AnimalEntity.class, false, PREY_SELECTOR));
//        this.targetSelector.addGoal(6, new NonTamedTargetGoal<>(this, TurtleEntity.class, false, TurtleEntity.BABY_ON_LAND_SELECTOR));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, AbstractSkeletonEntity.class, false));
        this.targetSelector.addGoal(8, new ResetAngerGoal<>(this, true));
//        }
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2, false));
        this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, (float) 0.2));
        this.goalSelector.addGoal(5, new FollowMobGoal(this, (float) 1, 20, 10));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(7, new RandomWalkingGoal(this, 1));
        this.goalSelector.addGoal(8, new SwimGoal(this));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers(this.getClass()));
    }

    protected SoundEvent getAngrySound() {
        super.getAngrySound();
        return RigoranthusSoundRegistry.CANIS_HUFF.get();
    }
    @Nullable
    protected SoundEvent getEatingSound() {
        return SoundEvents.MULE_EAT;
    }
    @Override
    protected SoundEvent getAmbientSound() {
        return RigoranthusSoundRegistry.CANIS_AMBIENT.get();
    }
    @Override
    protected SoundEvent getDeathSound() {
        return RigoranthusSoundRegistry.CANIS_DEATH.get();
    }
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return RigoranthusSoundRegistry.CANIS_HURT.get();
    }
    protected void playChestEquipsSound() {
        this.playSound(SoundEvents.MULE_CHEST, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
    }

    @SubscribeEvent
    public void addFeatureToBiomes(BiomeLoadingEvent event) {
        boolean biomeCriteria = false;
        if (new ResourceLocation("plains").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("mountains").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("forest").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("taiga").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("frozen_river").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("snowy_tundra").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("snowy_mountains").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("wooded_hills").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("taiga_hills").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("mountain_edge").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("jungle").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("jungle_hills").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("jungle_edge").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("birch_forest").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("birch_forest_hills").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("dark_forest").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("snowy_taiga").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("snowy_taiga_hills").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("giant_tree_taiga").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("giant_tree_taiga_hills").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("wooded_mountains").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("badlands").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("wooded_badlands_plateau").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("sunflower_plains").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("gravelly_mountains").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("flower_forest").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("taiga_mountains").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("modified_jungle").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("tall_birch_forest").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("tall_birch_hills").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("dark_forest_hills").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("snowy_taiga_mountains").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("giant_spruce_taiga").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("giant_spruce_taiga_hills").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("modified_gravelly_mountains").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("rainforest").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("rainforest_plateau").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("sparse_rainforest_plateau").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("rainforest_basin").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("sparse_rainforest_basin").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("rainforest_mountains").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("rainforest").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("blossom_woods").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("blossom_hills").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("blossom_valleys").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("blossom_highlands").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("marsh").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("mushroom_marsh").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("allium_fields").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("amaranth_fields").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("ancient_forest").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("araucaria_savanna").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("baobab_savanna").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("bayou").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("blue_taiga").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("bluff_steeps").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("boreal_forest").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("cherry_blossom_forest").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("cika_woods").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("cold_swamplands").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("coniferous_forest").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("crag_gardens").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("cypress_swamplands").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("deciduous_forest").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("dover_mountains").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("ebony_woods").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("enchanted_forest").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("evergreen_taiga").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("glowshroom_bayou").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("grassland_plateau").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("jacaranda_forest").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("mangrove_marshes").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("maple_taiga").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("meadow").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("orchard").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("prairie").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("red_oak_forest").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("redwood_tropics").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("rose_fields").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("shrublands").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("sierra_valley").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("skyris_highlands").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("stone_forest").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("the_black_forest").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("tropical_fungal_forest").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("tropical_rainforest").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("twilight_valley").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("vibrant_swamplands").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("weeping_witch_forest").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("woodlands").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("zelkova_forest").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("snowy_evergreen_taiga").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("snowy_deciduous_forest").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("snowy_coniferous_forest").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("snowy_blue_taiga").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("shattered_glacier").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("seasonal_taiga").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("seasonal_forest").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("seasonal_deciduous_forest").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("seasonal_birch_forest").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("lush_tundra").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("autumnal_valley").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("alps").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("aspen_forest").equals(event.getName()))
            biomeCriteria = true;
        if (!biomeCriteria)
            return;
    }


    protected static final DataParameter<Byte> DATA_FLAGS_ID = EntityDataManager.defineId(CanisChordataEntity.class, DataSerializers.BYTE);
    protected static final DataParameter<Optional<UUID>> DATA_OWNERUUID_ID = EntityDataManager.defineId(CanisChordataEntity.class, DataSerializers.OPTIONAL_UUID);
    private boolean orderedToSit;
    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_FLAGS_ID, (byte)0);
        this.entityData.define(DATA_OWNERUUID_ID, Optional.empty());
        this.entityData.define(DATA_INTERESTED_ID, false);
        this.entityData.define(DATA_REMAINING_ANGER_TIME, 0);
        this.entityData.define(STATE, 0);
    }
    @Override
    public void addAdditionalSaveData(CompoundNBT p_213281_1_) {
        super.addAdditionalSaveData(p_213281_1_);
        this.setChest(p_213281_1_.getBoolean("ChestedCanis"));
        p_213281_1_.putBoolean("ChestedCanis", this.hasChest());
        if (this.hasChest()) {
            ListNBT listnbt = new ListNBT();

            for (int i = 2; i < this.inventory.getContainerSize(); ++i) {
                ItemStack itemstack = this.inventory.getItem(i);
                if (!itemstack.isEmpty()) {
                    CompoundNBT compoundnbt = new CompoundNBT();
                    compoundnbt.putByte("Slot", (byte) i);
                    itemstack.save(compoundnbt);
                    listnbt.add(compoundnbt);
                }
            }
            if (this.getOwnerUUID() != null) {
                p_213281_1_.putUUID("Owner", this.getOwnerUUID());
                this.addPersistentAngerSaveData(p_213281_1_);
            }
            p_213281_1_.put("Items", listnbt);
            p_213281_1_.putBoolean("Sitting", this.orderedToSit);
        }
    }
    @Override
    public void readAdditionalSaveData(CompoundNBT p_70037_1_) {
        super.readAdditionalSaveData(p_70037_1_);
        this.setChest(p_70037_1_.getBoolean("ChestedCanis"));
        if (this.hasChest()) {
            ListNBT listnbt = p_70037_1_.getList("Items", 10);
            this.createInventory();

            for(int i = 0; i < listnbt.size(); ++i) {
                CompoundNBT compoundnbt = listnbt.getCompound(i);
                int j = compoundnbt.getByte("Slot") & 255;
                if (j >= 2 && j < this.inventory.getContainerSize()) {
                    this.inventory.setItem(j, ItemStack.of(compoundnbt));
                }
            }
            UUID uuid;
            if (p_70037_1_.hasUUID("Owner")) {
                uuid = p_70037_1_.getUUID("Owner");
            } else {
                String s = p_70037_1_.getString("Owner");
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
        }

        this.updateContainerEquipment();
        //this.orderedToSit = p_70037_1_.getBoolean("Sitting");
       /// this.setInSittingPose(this.orderedToSit);
        if(!level.isClientSide)
            this.readPersistentAngerSaveData((ServerWorld)this.level, p_70037_1_);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    protected void spawnTamingParticles(boolean p_70908_1_) {
        IParticleData iparticledata = ParticleTypes.HEART;
        if (!p_70908_1_) {
            iparticledata = ParticleTypes.SMOKE;
        }
        for(int i = 0; i < 7; ++i) {
            double d0 = this.random.nextGaussian() * 0.02D;
            double d1 = this.random.nextGaussian() * 0.02D;
            double d2 = this.random.nextGaussian() * 0.02D;
            this.level.addParticle(iparticledata, this.getRandomX(1.0D), this.getRandomY() + 0.5D, this.getRandomZ(1.0D), d0, d1, d2);
        }
    }
    @Override
    @OnlyIn(Dist.CLIENT)
    public void handleEntityEvent(byte p_70103_1_) {
        if (p_70103_1_ == 7) {
            this.spawnTamingParticles(true);
        } else if (p_70103_1_ == 6) {
            this.spawnTamingParticles(false);
        } else {
            super.handleEntityEvent(p_70103_1_);
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
        if (tame) {
            this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(10.0D);
            this.setHealth(100.0F);
        } else {
            this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(4.0D);
        }

        this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(4.0D);
    }

/*    public boolean isInSittingPose() {
        return (this.entityData.get(DATA_FLAGS_ID) & 1) != 0;
    }

    public void setInSittingPose(boolean p_233686_1_) {
        byte b0 = this.entityData.get(DATA_FLAGS_ID);
        if (p_233686_1_) {
            this.entityData.set(DATA_FLAGS_ID, (byte)(b0 | 1));
        } else {
            this.entityData.set(DATA_FLAGS_ID, (byte)(b0 & -2));
        }
    }
    @Override
    public void tick() {
        CanisChordataEntity.this.setTarget((LivingEntity)null);
        super.tick();
        if (this.isAlive()) {
            this.interestedAngleO = this.interestedAngle;
            if (this.isInterested()) {
                this.interestedAngle += (1.0F - this.interestedAngle) * 0.4F;
            } else {
                this.interestedAngle += (0.0F - this.interestedAngle) * 0.4F;
            }

        }
    }
    public void setIsInterested(boolean p_70918_1_) {
        this.entityData.set(DATA_INTERESTED_ID, p_70918_1_);
    }

    public boolean isInterested() {
        return this.entityData.get(DATA_INTERESTED_ID);
    }*/
    @Override
    public boolean hurt(DamageSource p_70097_1_, float p_70097_2_) {
        if (this.isInvulnerableTo(p_70097_1_)) {
            return false;
        } else {
            Entity entity = p_70097_1_.getEntity();
            this.setOrderedToSit(false);
            if (entity != null && !(entity instanceof PlayerEntity) && !(entity instanceof AbstractArrowEntity)) {
                p_70097_2_ = (p_70097_2_ + 1.0F) / 2.0F;
            }

            return super.hurt(p_70097_1_, p_70097_2_);
        }
    }
    /*
    @Override
    public boolean doHurtTarget(Entity p_70652_1_) {
        boolean flag = p_70652_1_.hurt(DamageSource.mobAttack(this), (float)((int)this.getAttributeValue(Attributes.ATTACK_DAMAGE)));
        if (flag) {
            this.doEnchantDamageEffects(this, p_70652_1_);
        }
        return flag;
    }*/
    @Override
    @Nullable
    public UUID getOwnerUUID() {
        return this.entityData.get(DATA_OWNERUUID_ID).orElse((UUID)null);
    }
    @Override
    public void setOwnerUUID(@Nullable UUID p_184754_1_) {
        this.entityData.set(DATA_OWNERUUID_ID, Optional.ofNullable(p_184754_1_));
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
    public Team getTeam() {
        if (this.isTame()) {
            LivingEntity livingentity = this.getOwner();
            if (livingentity != null) {
                return livingentity.getTeam();
            }
        }
        return super.getTeam();
    }
    /*
    @Override
    public boolean isAlliedTo(Entity p_184191_1_) {
        if (this.isTame()) {
            LivingEntity livingentity = this.getOwner();
            if (p_184191_1_ == livingentity) {
                return true;
            }
            if (livingentity != null) {
                return livingentity.isAlliedTo(p_184191_1_);
            }
        }
        return super.isAlliedTo(p_184191_1_);
    }
    @Override
    public void die(DamageSource p_70645_1_) {
        if (!this.level.isClientSide && this.level.getGameRules().getBoolean(GameRules.RULE_SHOWDEATHMESSAGES) && this.getOwner() instanceof ServerPlayerEntity) {
            this.getOwner().sendMessage(this.getCombatTracker().getDeathMessage(), Util.NIL_UUID);
        }
        super.die(p_70645_1_);
    }*/

    public void setOrderedToSit(boolean p_233687_1_) {
        this.orderedToSit = p_233687_1_;
    }
}