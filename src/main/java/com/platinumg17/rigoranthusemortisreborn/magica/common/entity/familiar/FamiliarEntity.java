package com.platinumg17.rigoranthusemortisreborn.magica.common.entity.familiar;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.entity.IDispellable;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.event.FamiliarSummonEvent;
import com.platinumg17.rigoranthusemortisreborn.magica.client.particle.ParticleUtil;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.entity.familiar.IFamiliar;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;
import java.util.*;

public class FamiliarEntity extends CreatureEntity implements IAnimatable, IFamiliar, IDispellable {

    private static final DataParameter<Optional<UUID>> OWNER_UUID = EntityDataManager.defineId(FamiliarEntity.class, DataSerializers.OPTIONAL_UUID);

    public static Set<FamiliarEntity> FAMILIAR_SET = Collections.newSetFromMap(new WeakHashMap<>());

    public boolean terminatedFamiliar;

    public FamiliarEntity(EntityType<? extends CreatureEntity> p_i48575_1_, World p_i48575_2_) {
        super(p_i48575_1_, p_i48575_2_);
        if(!level.isClientSide)
            FAMILIAR_SET.add(this);
    }

    @Override
    public void remove() {
        super.remove();
    }

    @Override
    public boolean removeWhenFarAway(double p_213397_1_) {
        return false;
    }

    @Override
    public void onRemovedFromWorld() {
        super.onRemovedFromWorld();
    }

    public double getDominionReserveModifier(){
        return 0.15;
    }

    @Override
    public boolean isAlive() {
        return super.isAlive() && !terminatedFamiliar && (level.isClientSide || FamiliarEntity.FAMILIAR_SET.contains(this));
    }

    @Override
    public void tick() {
        super.tick();
        if(this.terminatedFamiliar){
            this.remove();
            FamiliarEntity.FAMILIAR_SET.remove(this);
        }
        if(level.getGameTime() % 20 == 0 && !level.isClientSide){
            if(getOwnerID() == null || ((ServerWorld)level).getEntity(getOwnerID()) == null || terminatedFamiliar){
                this.remove();
                this.terminatedFamiliar = true;
                FAMILIAR_SET.remove(this);
            }
        }
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if(source == DamageSource.DROWN || source == DamageSource.IN_WALL || source == DamageSource.FLY_INTO_WALL || source == DamageSource.FALL)
            return false;
        if(source.getEntity() == null)
            return false;
        return super.hurt(source, amount);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(3, new FamiliarFollowGoal(this, 2, 6, 4));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0f));
        this.targetSelector.addGoal(1, new FamiliarOwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new FamiliarOwnerHurtTargetGoal(this));
    }

    public PlayState walkPredicate(AnimationEvent event) {
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "walkController", 1, this::walkPredicate));
    }

    public boolean canTeleport(){
        return getOwner() != null && getOwner().isOnGround();
    }

    public @Nullable LivingEntity getOwner(){
        if(level.isClientSide || getOwnerID() == null)
            return null;

        return (LivingEntity) ((ServerWorld)level).getEntity(getOwnerID());
    }

    public AnimationFactory factory = new AnimationFactory(this);

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(OWNER_UUID, Optional.empty());
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT tag) {
        super.addAdditionalSaveData(tag);
        if(getOwnerID() != null)
            tag.putUUID("ownerID", getOwnerID());
        tag.putBoolean("terminated", terminatedFamiliar);
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT tag) {
        super.readAdditionalSaveData(tag);
        if(tag.hasUUID("ownerID"))
            setOwnerID(tag.getUUID("ownerID"));
        terminatedFamiliar = tag.getBoolean("terminated");
    }

    public @Nullable UUID getOwnerID() {
        return this.getEntityData().get(OWNER_UUID).orElse(null);
    }

    public void setOwnerID(UUID uuid) {
        this.getEntityData().set(OWNER_UUID, Optional.of(uuid));
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public static AttributeModifierMap.MutableAttribute familiarAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 40d)
                .add(Attributes.MOVEMENT_SPEED, 0.2d).add(Attributes.FLYING_SPEED, Attributes.FLYING_SPEED.getDefaultValue())
                .add(Attributes.FOLLOW_RANGE, 16D);
    }

    @Override
    public boolean canTrample(BlockState state, BlockPos pos, float fallDistance) {
        return false;
    }

    @Override
    protected boolean canRide(Entity p_184228_1_) {
        return false;
    }

    @Override
    public boolean onDispel(@Nullable LivingEntity caster) {
        if(!level.isClientSide && getOwner() != null && getOwner().equals(caster)){
            this.remove();
            ParticleUtil.spawnPoof((ServerWorld) level, blockPosition());
            return true;
        }
        return false;
    }

    @Override
    public void remove(boolean keepData) {
        super.remove(keepData);
    }

    @Override
    public void onFamiliarSpawned(FamiliarSummonEvent event) {
        if(level.isClientSide)
            return;
        IFamiliar.super.onFamiliarSpawned(event);
        if(!event.getEntity().equals(this) && event.owner.equals(this.getOwner()))
            this.terminatedFamiliar = true;
    }
}