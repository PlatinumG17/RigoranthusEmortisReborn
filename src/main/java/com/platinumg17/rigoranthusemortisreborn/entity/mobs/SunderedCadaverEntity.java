package com.platinumg17.rigoranthusemortisreborn.entity.mobs;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.EntitySpellResolver;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.Spell;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.SpellContext;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.util.NBTUtil;
import com.platinumg17.rigoranthusemortisreborn.config.Config;
import com.platinumg17.rigoranthusemortisreborn.core.registry.RigoranthusSoundRegistry;
import com.platinumg17.rigoranthusemortisreborn.entity.goals.SunderedCadaverAttackGoal;
import com.platinumg17.rigoranthusemortisreborn.magica.client.particle.ParticleColor;
import com.platinumg17.rigoranthusemortisreborn.magica.client.particle.ParticleUtil;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.IAnimationListener;
import com.platinumg17.rigoranthusemortisreborn.magica.common.entity.EntityProjectileSpell;
import com.platinumg17.rigoranthusemortisreborn.magica.common.entity.ModEntities;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MoveTowardsTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
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

public class SunderedCadaverEntity extends ZombieEntity implements IAnimatable {
//    public static final DataParameter<Integer> STATE = EntityDataManager.defineId(SunderedCadaverEntity.class, DataSerializers.INT);
//    private final AnimationFactory animationFactory = new AnimationFactory(this);
//    public static final DataParameter<Boolean> POUNCING = EntityDataManager.defineId(SunderedCadaverEntity.class, DataSerializers.BOOLEAN);
//    public static final DataParameter<Boolean> CASTING = EntityDataManager.defineId(SunderedCadaverEntity.class, DataSerializers.BOOLEAN);
//    public static final DataParameter<Boolean> SUMMONED = EntityDataManager.defineId(SunderedCadaverEntity.class, DataSerializers.BOOLEAN);
//    public static final DataParameter<Optional<BlockPos>> HOME = EntityDataManager.defineId(SunderedCadaverEntity.class, DataSerializers.OPTIONAL_BLOCK_POS);
//    public int pounceCooldown;
//    public int castCooldown;
//    public Spell spell = Spell.EMPTY;
    public ParticleColor color = ParticleUtil.defaultParticleColor();

    public SunderedCadaverEntity(EntityType<? extends ZombieEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public SunderedCadaverEntity(World p_i50190_2_) {
        super(ModEntities.SUNDERED_CADAVER, p_i50190_2_);
    }


    AnimationFactory factory = new AnimationFactory(this);


    @Override
    public EntityType<?> getType() {
        return ModEntities.SUNDERED_CADAVER;
    }

    public void onSyncedDataUpdated(DataParameter<?> p_184206_1_) {
        super.onSyncedDataUpdated(p_184206_1_);
    }
    //    public enum Animations{
//        CAST,
//        POUNCE
//    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
    @Override
    public void tick() {
        super.tick();
//        if(pounceCooldown > 0)
//            pounceCooldown--;
//        if(castCooldown > 0)
//            castCooldown--;
        if(!level.isClientSide && level.getGameTime() % 20 == 0 && !this.isDeadOrDying()){
            this.heal(1.0f);
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

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<SunderedCadaverEntity>
                (this, "controller", 0, this::animationPredicate));
//        data.addAnimationController(new AnimationController(this,"run_controller", 3.0f, this::runController));
//        data.addAnimationController(new AnimationController(this,"attack_controller", 5f, this::attackController));
    }
//    private PlayState attackController(AnimationEvent animationEvent) {
//        return PlayState.CONTINUE;
//    }
//    private PlayState runController(AnimationEvent animationEvent) {
//        if(entityData.get(POUNCING) || entityData.get(CASTING))
//            return PlayState.STOP;
//        if(animationEvent.getController().getCurrentAnimation() != null && !(animationEvent.getController().getCurrentAnimation().animationName.equals("animation.sundered_cadaver.walking"))) {
//            return PlayState.STOP;
//        }
//        if(animationEvent.isMoving()){
//            animationEvent.getController().setAnimation(new AnimationBuilder().addAnimation("animation.sundered_cadaver.walking"));
//            return PlayState.CONTINUE;
//        }
//        return PlayState.STOP;
//    }



//    private <P extends IAnimatable> PlayState idlePredicate(AnimationEvent<P> event) {
//        if(getNavigation().isInProgress()) {
//            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.sundered_cadaver.idle"));
//            return PlayState.CONTINUE;
//        }
//        else if (getNavigation().isDone()) {
//            return PlayState.STOP;
//        }
//        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.sundered_cadaver.idle"));
//        return PlayState.CONTINUE;
//    }
//    private <P extends IAnimatable> PlayState attackPredicate(AnimationEvent<P> event) {return PlayState.CONTINUE;}
//    private <P extends IAnimatable> PlayState walkingPredicate(AnimationEvent<P> event) {return PlayState.CONTINUE;}

    private <E extends IAnimatable> PlayState animationPredicate(AnimationEvent<E> event) {
        if (!(animationSpeed > -0.10F && animationSpeed < 0.05F) && !this.isAggressive()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.sundered_cadaver.walking", true));
            return PlayState.CONTINUE;
        }
//        if ((this.dead || this.getHealth() < 0.01 || this.isDeadOrDying())) {
//            if (level.isClientSide) {
//                event.getController().setAnimation(new AnimationBuilder().addAnimation("death", false));
//                return PlayState.CONTINUE;
//            }
//        }
        if (this.isAggressive() && !(this.dead || this.getHealth() < 0.01 || this.isDeadOrDying())) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.sundered_cadaver.attacking", true));
            return PlayState.CONTINUE;
        }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.sundered_cadaver.idle", true));
        return PlayState.CONTINUE;
    }

//    @Override
//    public void startAnimation(int arg) {
//        try{
//            if(arg == Animations.POUNCE.ordinal()){
//                AnimationController controller = this.factory.getOrCreateAnimationData(this.hashCode()).getAnimationControllers().get("attack_controller");
//
//                if(controller.getCurrentAnimation() != null && (controller.getCurrentAnimation().animationName.equals("attacking"))) {
//                    return;
//                }
//                controller.markNeedsReload();
//                controller.setAnimation(new AnimationBuilder().addAnimation("animation.sundered_cadaver.attacking", false).addAnimation("animation.sundered_cadaver.idle", false));
//            }
//            if(arg == Animations.CAST.ordinal()){
//                AnimationController controller = this.factory.getOrCreateAnimationData(this.hashCode()).getAnimationControllers().get("attack_controller");
//                if(controller.getCurrentAnimation() != null && controller.getCurrentAnimation().animationName.equals("animation.sundered_cadaver.attacking")) {/*cast*/
//                    return;
//                }
//                controller.markNeedsReload();
//                controller.setAnimation(new AnimationBuilder().addAnimation("animation.sundered_cadaver.attacking"/*cast*/, false).addAnimation("animation.sundered_cadaver.idle", false));
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

//    @Override
//    protected void defineSynchedData() {
//        super.defineSynchedData();
//        this.entityData.define(POUNCING, false);
//        this.entityData.define(CASTING, false);
//    }
//    @Override
//    public void addAdditionalSaveData(CompoundNBT tag) {
//        super.addAdditionalSaveData(tag);
////        NBTUtil.storeBlockPos(tag, "home", getHome());
//        tag.putInt("pounce", pounceCooldown);
////        tag.putInt("cast", castCooldown);
//    }
//    @Override
//    public void readAdditionalSaveData(CompoundNBT tag) {
//        super.readAdditionalSaveData(tag);
////        if(NBTUtil.hasBlockPos(tag, "home")){
////            setHome(NBTUtil.getBlockPos(tag, "home"));}
//        this.pounceCooldown = tag.getInt("pounce");
////        this.castCooldown = tag.getInt("cast");
//    }
//    @Override
//    public void performRangedAttack(LivingEntity p_82196_1_, float p_82196_2_) {
//        EntitySpellResolver resolver = new EntitySpellResolver(new SpellContext(spell, this).withColors(color.toWrapper()));
//        EntityProjectileSpell projectileSpell = new EntityProjectileSpell(level, resolver);
//        projectileSpell.setColor(color.toWrapper());
//        projectileSpell.shoot(this, this.xRot, this.yRot, 0.0F, 1.0f, 0.8f);
//        level.addFreshEntity(projectileSpell);
//        this.castCooldown = 40;
//    }

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
//    @Override
//    public boolean doHurtTarget(Entity entityIn) {
//        if (!super.doHurtTarget(entityIn)) {
//            return false;
//        } else {
//            if (entityIn instanceof LivingEntity) {
//                //this.setState(State.ATTACKING);
//            }
//            return true;
//        }
//    }

    @Override
    public boolean hurt(@Nonnull DamageSource source, float amount) {
        if(source == DamageSource.FALL || source == DamageSource.IN_WALL || source == DamageSource.SWEET_BERRY_BUSH || source == DamageSource.CACTUS)
            return false;
        return super.hurt(source, amount);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new SwimGoal(this));
//        this.goalSelector.addGoal(2, new PounceAttackGoal(this, true,() -> pounceCooldown <= 0 /*&& !this.entityData.get(SUMMONED)*/, Animations.POUNCE.ordinal(), 72, 5));
//        this.goalSelector.addGoal(2, new RandomWalkingGoal(this, 1.0f));
//        this.goalSelector.addGoal(2, new CastSpellGoal(this, 1.2d, 20,15f, () -> castCooldown <= 0 && !this.entityData.get(SUMMONED), Animations.CAST.ordinal(), 20));
        this.goalSelector.addGoal(5, new MoveTowardsTargetGoal(this, 1.0f, 8));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new SunderedCadaverAttackGoal(this, 1, false));
//        this.goalSelector.addGoal(5, new FollowMobGoal(this, (float) 1, 10, 5));
//        this.targetSelector.addGoal(2, (new HurtByTargetGoal(this)).setAlertOthers(this.getClass()));
    }
    @Override protected int getExperienceReward(PlayerEntity player) {return 10 + this.level.random.nextInt(5);}
    @Override protected SoundEvent getAmbientSound() {return RigoranthusSoundRegistry.CADAVER_AMBIENT.get();}
    @Override protected SoundEvent getDeathSound() {return RigoranthusSoundRegistry.CADAVER_DEATH.get();}
    @Override protected SoundEvent getHurtSound(DamageSource damageSourceIn) { return RigoranthusSoundRegistry.CADAVER_HURT.get();}
    @Override protected void playStepSound(BlockPos pos, BlockState blockIn) {this.playSound(RigoranthusSoundRegistry.UNDEAD_STEP.get(), 0.20F, 0.5F);}



}
