package com.platinumg17.rigoranthusemortisreborn.entity.mobs;

import com.platinumg17.rigoranthusemortisreborn.core.registry.RigoranthusSoundRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.SilverfishEntity;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.entity.monster.ZombifiedPiglinEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LanguidDwellerEntity extends SpiderEntity {
    public LanguidDwellerEntity(EntityType<? extends SpiderEntity> p_i48550_1_, World p_i48550_2_) {
        super(p_i48550_1_, p_i48550_2_);
    }
    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 100.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ATTACK_DAMAGE, 7.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 2.0D)
                .add(Attributes.ATTACK_KNOCKBACK, 1.2D)
                .add(Attributes.ARMOR, 12.0D)
                .add(Attributes.FOLLOW_RANGE, 50.0D)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE, 4);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal( 1, new NearestAttackableTargetGoal<>( this, PlayerEntity.class, true));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.4D, false));
        //this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, (float) 0.3));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 0.5D));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers(this.getClass()));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
    }

    @Override
    protected int getExperienceReward(PlayerEntity player)
    {
        return 40 + this.level.random.nextInt(5);
    }
    @Override
    protected SoundEvent getAmbientSound()
    {
        return RigoranthusSoundRegistry.DWELLER_AMBIENT.get();
    }
    @Override
    protected SoundEvent getDeathSound() {return RigoranthusSoundRegistry.DWELLER_DEATH.get();}
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return RigoranthusSoundRegistry.DWELLER_HURT.get();}
    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {this.playSound(RigoranthusSoundRegistry.UNDEAD_STEP.get(), 0.20F, 0.5F);}

    @Override
    public boolean doHurtTarget(Entity entityIn) {
        if (!super.doHurtTarget(entityIn)) {
            return false;
        } else {
            if (entityIn instanceof LivingEntity) {
                ((LivingEntity) entityIn).addEffect(new EffectInstance(Effects.UNLUCK, 200));
            }
            return true;
        }
    }
@Override
public boolean hurt(DamageSource source, float amount) {
//Entity sourceentity = source.getEntity();{
    if  ((Math.random() < 0.1)) {
        if (level instanceof ServerWorld) {
            MobEntity entityToSpawn = new SilverfishEntity(EntityType.SILVERFISH, level);
            entityToSpawn.moveTo(this.getX(), this.getY(), this.getZ(), level.getRandom().nextFloat() * 360F, 0);
            entityToSpawn.finalizeSpawn((ServerWorld) level, level.getCurrentDifficultyAt(this.blockPosition()),
                    SpawnReason.MOB_SUMMONED, null, null);
            level.addFreshEntity(entityToSpawn);
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
    public CreatureAttribute getMobType() {
        return CreatureAttribute.UNDEAD;
    }
    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
    public void aiStep() {
        super.aiStep();
        for(int i = 0; i < 3; i++){
            this.level.addParticle(ParticleTypes.ENCHANT, this.getRandomX(1.0), this.getRandomY(), this.getRandomZ(1.0), 0, 0, 0);
        }
    }
    @SubscribeEvent
    public void addFeatureToBiomes(BiomeLoadingEvent event) {
        boolean biomeCriteria = false;
        if (new ResourceLocation("desert").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("mountains").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("swamp").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("mushroom_fields").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("mushroom_field_shore").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("desert_hills").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("jungle").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("jungle_hills").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("jungle_edge").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("stone_shore").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("giant_tree_taiga").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("giant_tree_taiga_hills").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("wooded_mountains").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("savanna").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("savanna_plateau").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("badlands").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("wooded_badlands_plateau").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("badlands_plateau").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("gravelly_mountains").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("taiga_mountains").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("swamp_hills").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("modified_jungle").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("modified_jungle_edge").equals(event.getName()))
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
        if (new ResourceLocation("shattered_savanna").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("shattered_savanna_plateau").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("eroded_badlands").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("modified_wooded_badlands_plateau").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("modified_badlands_plateau").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("biome.cavebiomes.caves").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("dripstone_caves").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("lush_caves").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("rocky_dunes").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("rocky_dunes_hills").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("flourishing_dunes").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("petrified_dunes").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("dunes").equals(event.getName()))
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
        if (new ResourceLocation("marsh").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("mushroom_marsh").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("baobab_savanna").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("bayou").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("bluff_steeps").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("boreal_forest").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("canyons").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("cold_swamplands").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("coniferous_forest").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("crag_gardens").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("cypress_swamplands").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("dover_mountains").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("glowshroom_bayou").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("grassland_plateau").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("guiana_shield").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("mangrove_marshes").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("mojave_desert").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("orchard").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("prairie").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("red_desert").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("red_oak_forest").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("red_rock_mountains").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("redwood_tropics").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("shrublands").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("sierra_valley").equals(event.getName()))
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
        if (new ResourceLocation("alps").equals(event.getName()))
            biomeCriteria = true;
        if (new ResourceLocation("aspen_forest").equals(event.getName()))
            biomeCriteria = true;
        if (!biomeCriteria)
            return;
        event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(getType(), 15, 1, 1));
    }


}
