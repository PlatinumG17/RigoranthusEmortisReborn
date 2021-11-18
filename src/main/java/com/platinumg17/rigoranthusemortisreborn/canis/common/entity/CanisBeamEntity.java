package com.platinumg17.rigoranthusemortisreborn.canis.common.entity;

import com.google.common.base.Predicates;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.canis.common.SpecializedEntityTypes;
import com.platinumg17.rigoranthusemortisreborn.canis.common.util.EntityUtil;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.feature.EnumMode;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.UUID;

public class CanisBeamEntity extends ThrowableEntity implements IEntityAdditionalSpawnData {

    public CanisBeamEntity(EntityType<? extends ThrowableEntity> type, World worldIn) {
        super(type, worldIn);
    }
    public CanisBeamEntity(World worldIn, LivingEntity livingEntityIn) {super(SpecializedEntityTypes.CANIS_BEAM.get(), livingEntityIn, worldIn);}
    public CanisBeamEntity(FMLPlayMessages.SpawnEntity packet, World worldIn) {super(SpecializedEntityTypes.CANIS_BEAM.get(), worldIn);}

    @Override
    protected void onHit(RayTraceResult result) {
        if (result.getType() == RayTraceResult.Type.ENTITY) {
            Entity entityHit = ((EntityRayTraceResult) result).getEntity();
            Entity thrower = this.getOwner();

            if (thrower instanceof LivingEntity && entityHit instanceof LivingEntity) {
                LivingEntity livingThrower = (LivingEntity) thrower;
                LivingEntity livingEntity = (LivingEntity) entityHit;

                this.level.getEntitiesOfClass(CanisEntity.class, this.getBoundingBox().inflate(64D, 16D, 64D)).stream()
                        .filter(Predicates.not(CanisEntity::isInSittingPose))
                        .filter(d -> d.isMode(EnumMode.AGGRESIVE, EnumMode.TACTICAL, EnumMode.BERSERKER))
                        .filter(d -> d.canInteract(livingThrower))
                        .filter(d -> d != livingEntity && d.wantsToAttack(livingEntity, d.getOwner()))
                        .filter(d -> d.distanceTo(entityHit) < EntityUtil.getFollowRange(d))
                        .forEach(d -> d.setTarget(livingEntity));
            }
            for (int j = 0; j < 8; ++j) {
                this.level.addParticle(ParticleTypes.PORTAL, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D); // was ITEM_SNOWBALL
            }
        }
        if (!this.level.isClientSide) {
            this.level.broadcastEntityEvent(this, EmortisConstants.EntityState.DEATH);
            this.remove();
        }
    }
    @Override public IPacket<?> getAddEntityPacket() {return NetworkHooks.getEntitySpawningPacket(this);}
    @Override
    public void writeSpawnData(PacketBuffer buffer) {
        UUID ownerId = this.uuid;
        buffer.writeBoolean(ownerId != null);
        if (ownerId != null) {
            buffer.writeUUID(ownerId);
        }
    }

    @Override
    public void readSpawnData(PacketBuffer buffer) {
        boolean hasThrower = buffer.readBoolean();
        if (hasThrower) {
            this.uuid = buffer.readUUID();
        }
    }

    @Override
    protected void defineSynchedData() {
        // TODO Auto-generated method stub
    }
}
