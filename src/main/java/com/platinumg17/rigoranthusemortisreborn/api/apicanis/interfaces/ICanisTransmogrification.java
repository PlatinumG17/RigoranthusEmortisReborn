package com.platinumg17.rigoranthusemortisreborn.api.apicanis.interfaces;

import com.platinumg17.rigoranthusemortisreborn.api.apicanis.entity.AbstractCanisEntity;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.feature.WetSource;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;

public interface ICanisTransmogrification {
    /**
     * Called when ever this instance is first added to a canis, this is called when
     * the level is first set on the canis or when it is loaded from NBT and when the
     * skills are synced to the client
     *
     * @param canisIn The canis
     */
    default void init(AbstractCanisEntity canisIn) {}
    default void remove(AbstractCanisEntity canisIn) {}
    default void onWrite(AbstractCanisEntity canisIn, CompoundNBT compound) {}
    default void onRead(AbstractCanisEntity canisIn, CompoundNBT compound) {}
    /**
     * Called at the end of tick
     */
    default void tick(AbstractCanisEntity canisIn) {
    }
    /**
     * Called at the end of livingTick
     */
    default void livingTick(AbstractCanisEntity canisIn) {
    }
    default ActionResult<Integer> hungerTick(AbstractCanisEntity canisIn, int hungerTick) {return ActionResult.pass(hungerTick);}
    default ActionResult<Integer> healingTick(AbstractCanisEntity canisIn, int healingTick) {return ActionResult.pass(healingTick);}
    default ActionResultType processInteract(AbstractCanisEntity canisIn, World worldIn, PlayerEntity playerIn, Hand handIn) {return ActionResultType.PASS;}
    default ActionResultType canBeRiddenInWater(AbstractCanisEntity canisIn, Entity rider) {return ActionResultType.PASS;}
    default ActionResultType canTrample(AbstractCanisEntity canisIn, BlockState state, BlockPos pos, float fallDistance) {return ActionResultType.PASS;}
    default ActionResult<Float> calculateFallDistance(AbstractCanisEntity canisIn, float distance) {return ActionResult.pass(0F);}
    default ActionResultType canBreatheUnderwater(AbstractCanisEntity canisIn) {return ActionResultType.PASS;}
    default ActionResultType canAttack(AbstractCanisEntity canisIn, LivingEntity target) {return ActionResultType.PASS;}
    default ActionResultType canAttack(AbstractCanisEntity canisIn, EntityType<?> entityType) {return ActionResultType.PASS;}
    default ActionResultType shouldAttackEntity(AbstractCanisEntity canis, LivingEntity target, LivingEntity owner) {return ActionResultType.PASS;}
    default ActionResultType hitByEntity(AbstractCanisEntity canis, Entity entity) {return ActionResultType.PASS;}
    default ActionResultType attackEntityAsMob(AbstractCanisEntity canisIn, Entity target) {return ActionResultType.PASS;}
    default ActionResult<Float> attackEntityFrom(AbstractCanisEntity canis, DamageSource source, float damage) {return ActionResult.pass(damage);}
    default ActionResultType canBlockDamageSource(AbstractCanisEntity canis, DamageSource source) {return ActionResultType.PASS;}
    default void onDeath(AbstractCanisEntity canis, DamageSource source) {}
    default void spawnDrops(AbstractCanisEntity canis, DamageSource source) {}
    default void dropLoot(AbstractCanisEntity canis, DamageSource source, boolean recentlyHitIn) {}
    default void dropInventory(AbstractCanisEntity canisIn) {}
    default ActionResult<Float> attackEntityFrom(AbstractCanisEntity canisIn, float distance, float damageMultiplier) {return ActionResult.pass(distance);}
    default ActionResult<Integer> decreaseAirSupply(AbstractCanisEntity canisIn, int air) {return ActionResult.pass(air);}
    default ActionResult<Integer> determineNextAir(AbstractCanisEntity canisIn, int currentAir) {return ActionResult.pass(currentAir);}
    default ActionResult<Integer> setFire(AbstractCanisEntity canisIn, int second) {return ActionResult.pass(second);}
    default ActionResultType isImmuneToFire(AbstractCanisEntity canisIn) {return ActionResultType.PASS;}
    default ActionResultType isInvulnerableTo(AbstractCanisEntity canisIn, DamageSource source) {return ActionResultType.PASS;}
    default ActionResultType isInvulnerable(AbstractCanisEntity canisIn) {return ActionResultType.PASS;}
    default ActionResultType onLivingFall(AbstractCanisEntity canisIn, float distance, float damageMultiplier) {return ActionResultType.PASS;}
    default <T> LazyOptional<T> getCapability(AbstractCanisEntity canisIn, Capability<T> cap, Direction side) {return null;}
    default void invalidateCapabilities(AbstractCanisEntity canisIn) {}
    default ActionResult<Float> getMaxHunger(AbstractCanisEntity canisIn, float currentMax) {return ActionResult.pass(currentMax);}
    default ActionResult<Float> setCanisHunger(AbstractCanisEntity canisIn, float hunger, float diff) {return ActionResult.pass(hunger);}
    default ActionResultType isPotionApplicable(AbstractCanisEntity canisIn, EffectInstance effectIn) {return ActionResultType.PASS;}
    /**
     * Only called serverside
     * @param canisIn The canis
     * @param source How the canis initially got wet
     */
    default void onShakingDry(AbstractCanisEntity canisIn, WetSource source) {
    }
}