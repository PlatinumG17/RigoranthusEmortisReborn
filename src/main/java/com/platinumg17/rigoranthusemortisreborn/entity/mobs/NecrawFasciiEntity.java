package com.platinumg17.rigoranthusemortisreborn.entity.mobs;

import com.platinumg17.rigoranthusemortisreborn.config.Config;
import com.platinumg17.rigoranthusemortisreborn.core.registry.RigoranthusSoundRegistry;
import com.platinumg17.rigoranthusemortisreborn.magica.client.particle.ParticleColor;
import com.platinumg17.rigoranthusemortisreborn.magica.client.particle.ParticleUtil;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.IAnimationListener;
import com.platinumg17.rigoranthusemortisreborn.magica.common.entity.ModEntities;
import com.platinumg17.rigoranthusemortisreborn.magica.common.potions.ModPotions;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.core.processor.IBone;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class NecrawFasciiEntity extends ZombieEntity implements IAnimatable, IAnimationListener {

    private final AnimationFactory animationFactory = new AnimationFactory(this);

    public NecrawFasciiEntity(EntityType<? extends ZombieEntity> type, World worldIn) {
        super(type, worldIn);
        this.noCulling = true;
    }

    public NecrawFasciiEntity(World world) {
        super(ModEntities.NECRAW_FASCII, world);
    }

    @Override
    public EntityType<?> getType() {
        return ModEntities.NECRAW_FASCII;
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntitySize size) {
        return 0.8F;
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level.isClientSide) {
            if (level.getGameTime() % 20 == 0 && !this.isDeadOrDying() && this.hasCustomName()) {
                this.heal(0.1f);
            }
        }
    }
    protected boolean isSunSensitive() {
        return !this.hasCustomName();
    }

    @Override
    public void aiStep() {
        if (this.isAlive()) {
            for(int i = 0; i < 3; i++){
                this.level.addParticle(ParticleTypes.FALLING_NECTAR, this.getRandomX(1.0), this.getRandomY(), this.getRandomZ(1.0), 0.0D, 0.0D, 0.0D);
            }
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

    public boolean canAttack(){
        return getTarget() != null && this.getHealth() >= 1;
    }

    public static AttributeModifierMap.MutableAttribute attributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, Config.necrawFasciiMaxHealth.get())
                .add(Attributes.MOVEMENT_SPEED, Config.necrawFasciiMovementSpeed.get())
                .add(Attributes.ATTACK_DAMAGE, Config.necrawFasciiAttackDamage.get())
                .add(Attributes.ARMOR, Config.necrawFasciiArmorValue.get())
                .add(Attributes.ATTACK_KNOCKBACK, Config.necrawFasciiAttackKnockback.get())
                .add(Attributes.KNOCKBACK_RESISTANCE, Config.necrawFasciiKnockbackResistance.get())
                .add(Attributes.FOLLOW_RANGE, 20.0D).add((Attributes.SPAWN_REINFORCEMENTS_CHANCE));
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController<>(this, "walkController", 0, this::walkPredicate));
//        animationData.addAnimationController(new AnimationController<>(this, "attackController", 1, this::attackPredicate));
        animationData.addAnimationController(new AnimationController<>(this, "idleController", 0, this::idlePredicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.animationFactory;
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
        super.registerGoals();
        this.goalSelector.addGoal( 1, new NearestAttackableTargetGoal<>( this, PlayerEntity.class, true));
        this.goalSelector.addGoal(2, new ZombieAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(5, new MoveTowardsTargetGoal(this, 1.0f, 8));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 0.8D));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers(this.getClass()));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
    }

    @Override
    public void startAnimation(int arg) {
        try{
            if(arg == NecrawFasciiEntity.Animations.LUNGING.ordinal()){
                AnimationController controller = this.animationFactory.getOrCreateAnimationData(this.hashCode()).getAnimationControllers().get("attackController");
                controller.markNeedsReload();
                controller.setAnimation(new AnimationBuilder().addAnimation("attack", false));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public enum Animations{ LUNGING }

    @Override
    protected int getExperienceReward(PlayerEntity player)
    {
        return 10 + this.level.random.nextInt(5);
    }
    @Override
    protected SoundEvent getAmbientSound()
    {
        return RigoranthusSoundRegistry.NECRAW_AMBIENT.get();
    }
    @Override
    protected SoundEvent getDeathSound()
    {
        return RigoranthusSoundRegistry.NECRAW_DEATH.get();
    }
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return RigoranthusSoundRegistry.NECRAW_HURT.get();}
    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {this.playSound(SoundEvents.DROWNED_STEP, 0.20F, 0.5F);}

    @Override
    public boolean doHurtTarget(Entity entityIn) {
        if (!super.doHurtTarget(entityIn)) {
            return false;
        } else {
            if (entityIn instanceof PlayerEntity) {
                ((PlayerEntity)entityIn).addEffect(new EffectInstance(ModPotions.NECROTIZING_FASCIITIS_EFFECT, 5000));
            }
            return true;
        }
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source == DamageSource.WITHER)
            return false;
        return super.hurt(source, amount);
    }

    @Nullable
    public ILivingEntityData finalizeSpawn(IServerWorld serverWorld, DifficultyInstance difficulty, SpawnReason spawnReason, @Nullable ILivingEntityData entityData, @Nullable CompoundNBT nbt) {
        entityData = super.finalizeSpawn(serverWorld, difficulty, spawnReason, entityData, nbt);
        float f = difficulty.getSpecialMultiplier();

        this.handleAttributes(f);
        return entityData;
    }

    protected void handleAttributes(float multiplier) {
        this.randomizeReinforcementsChance();
        this.getAttribute(Attributes.KNOCKBACK_RESISTANCE).addPermanentModifier(new AttributeModifier("Random spawn bonus", this.random.nextDouble() * (double)0.05F, AttributeModifier.Operation.ADDITION));
        double d0 = this.random.nextDouble() * 1.5D * (double)multiplier;
        if (d0 > 1.0D) {
            this.getAttribute(Attributes.FOLLOW_RANGE).addPermanentModifier(new AttributeModifier("Random Necraw-spawn bonus", d0, AttributeModifier.Operation.MULTIPLY_TOTAL));
        }
        if (this.random.nextFloat() < multiplier * 0.05F) {
            this.getAttribute(Attributes.SPAWN_REINFORCEMENTS_CHANCE).addPermanentModifier(new AttributeModifier("Leader Necraw bonus", this.random.nextDouble() * 0.25D + 0.5D, AttributeModifier.Operation.ADDITION));
            this.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier("Leader Necraw bonus", this.random.nextDouble() * 3.0D + 1.0D, AttributeModifier.Operation.MULTIPLY_TOTAL));
            this.setCanBreakDoors(this.supportsBreakDoorGoal());
        }
    }
}