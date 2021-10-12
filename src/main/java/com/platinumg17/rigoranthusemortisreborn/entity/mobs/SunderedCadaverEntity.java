package com.platinumg17.rigoranthusemortisreborn.entity.mobs;

import com.platinumg17.rigoranthusemortisreborn.core.registry.RigoranthusSoundRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.ai.goal.ZombieAttackGoal;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.monster.ZombifiedPiglinEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.Optional;

public class SunderedCadaverEntity extends ZombieEntity implements IAnimatable {
    public SunderedCadaverEntity(EntityType<? extends ZombieEntity> type, World worldIn) {
        super(type, worldIn);
    }
    public static final DataParameter<Integer> STATE = EntityDataManager.defineId(SunderedCadaverEntity.class, DataSerializers.INT);
    private final AnimationFactory animationFactory = new AnimationFactory(this);

    private <E extends IAnimatable> PlayState animationPredicate(AnimationEvent<E> event) {
        if (!this.dead && !this.isDeadOrDying()) {
            if (this.getState() == SunderedCadaverEntity.State.ATTACKING) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.sundered_cadaver_attacking.new", false));
                return PlayState.CONTINUE;
            }
        }
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.sundered_cadaver_walking.new", true));
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.sundered_cadaver_idle_breathing.new", true));
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
        IDLE, ATTACKING
    }

    public SunderedCadaverEntity.State getState() {
        SunderedCadaverEntity.State[] states = SunderedCadaverEntity.State.values();
        return states[MathHelper.clamp(this.entityData.get(STATE), 0, states.length - 1)];
    }

    public void setState(SunderedCadaverEntity.State state) {
        this.entityData.set(STATE, state.ordinal());
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return 8;
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ATTACK_DAMAGE, 4.0D)
                .add(Attributes.FOLLOW_RANGE, 50.0D)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.goalSelector.addGoal(2, new ZombieAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers(ZombifiedPiglinEntity.class));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, TurtleEntity.class, 10, true, false, TurtleEntity.BABY_ON_LAND_SELECTOR));
    }

    @Override
    protected int getExperienceReward(PlayerEntity player) {
        return 3 + this.level.random.nextInt(20);
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
    @Override

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(STATE, 0);
    }
//    @Override
//    public boolean doHurtTarget(Entity entityIn) {
//        if (!super.doHurtTarget(entityIn)) {
//            return false;
//        } else {
//            if (entityIn instanceof LivingEntity) {
//                ((LivingEntity) entityIn).addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 200, 3));
//                ((LivingEntity) entityIn).addEffect(new EffectInstance(Effects.WEAKNESS, 200));
//                ((LivingEntity) entityIn).addEffect(new EffectInstance(Effects.WEAKNESS, 200));
//            }
//            return true;
//        }
//    }
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
}
