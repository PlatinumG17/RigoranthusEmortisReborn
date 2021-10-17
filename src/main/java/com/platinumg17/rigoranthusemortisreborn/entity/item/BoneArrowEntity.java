package com.platinumg17.rigoranthusemortisreborn.entity.item;

import com.platinumg17.rigoranthusemortisreborn.core.init.Registration;
import com.platinumg17.rigoranthusemortisreborn.core.registry.RigoranthusDamageSources;
import com.platinumg17.rigoranthusemortisreborn.entity.RigoranthusEntityTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;

public class BoneArrowEntity extends ArrowEntity {
    private int amplifier = 0;

    public BoneArrowEntity(EntityType<? extends BoneArrowEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public BoneArrowEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        this(RigoranthusEntityTypes.BONE_ARROW_ENTITY.get(), world);
    }

//    public BoneArrowEntity(World worldIn, double x, double y, double z) {
//        super(RigoranthusEntityTypes.BONE_ARROW_ENTITY.get(), x, y, z, worldIn);
//    }
//
//    @Override
//    public BoneArrowEntity(World worldIn, LivingEntity throwerIn) {
//        super(RigoranthusEntityTypes.BONE_ARROW_ENTITY.get(), throwerIn, worldIn);
//    }

    protected void onHit(RayTraceResult result) {
        super.onHit(result);
        if (result.getType() == RayTraceResult.Type.ENTITY) {
            Entity entity = ((EntityRayTraceResult) result).getEntity();
            entity.hurt(RigoranthusDamageSources.causeBoneArrowDamage(this, this.getOwner()), 0.5F + amplifier);
            if (this.getOwner() instanceof ServerPlayerEntity) {
                ServerPlayerEntity serverplayerentity = (ServerPlayerEntity) this.getOwner();
//                if (!entity.getCommandSenderWorld().isClientSide()) {
//                    CriteriaTriggersRigoranthus.FIRE_BONE_ARROW.trigger(serverplayerentity);
//                }
            }
        }
//        if (!this.level.isClientSide) {
//            this.remove();
//        }
    }

    @Override
    public double getBaseDamage() {
        return 2.0D;
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(Registration.BONE_ARROW);
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
