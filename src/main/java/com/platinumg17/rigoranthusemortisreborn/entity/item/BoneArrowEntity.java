package com.platinumg17.rigoranthusemortisreborn.entity.item;

import com.platinumg17.rigoranthusemortisreborn.core.init.ItemInit;
import com.platinumg17.rigoranthusemortisreborn.core.init.Registration;
import com.platinumg17.rigoranthusemortisreborn.core.registry.RigoranthusDamageSources;
import com.platinumg17.rigoranthusemortisreborn.core.registry.effects.RigoranthusEffectRegistry;
import com.platinumg17.rigoranthusemortisreborn.entity.RigoranthusEntityTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;

public class BoneArrowEntity extends AbstractArrowEntity {
    public boolean impacted = false;
    private int amplifier = 0;

    public BoneArrowEntity(EntityType<? extends BoneArrowEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public BoneArrowEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        this(RigoranthusEntityTypes.BONE_ARROW.get(), world);
    }

    public BoneArrowEntity(World worldIn, double x, double y, double z) {
        super(RigoranthusEntityTypes.BONE_ARROW.get(), x, y, z, worldIn);
    }

    public BoneArrowEntity(World worldIn, LivingEntity shooter) {
        super(RigoranthusEntityTypes.BONE_ARROW.get(), shooter, worldIn);
    }

    @Override
    protected void onHitBlock(BlockRayTraceResult result) {
        super.onHitBlock(result);
        if (!impacted) {
            BoneArrowEntity boneArrow = RigoranthusEntityTypes.BONE_ARROW.get().create(level);
            boneArrow.moveTo(this.getX(), this.getY(), this.getZ(), 0.0F, 0.0F);
            this.level.addFreshEntity(boneArrow);
            this.impacted = true;
        }
    }

    @Override
    protected void onHitEntity(EntityRayTraceResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();
        if (!impacted && !(entity instanceof BoneArrowEntity)) {
            BoneArrowEntity boneArrow = RigoranthusEntityTypes.BONE_ARROW.get().create(level);
            boneArrow.moveTo(this.getX(), this.getY(), this.getZ(), 0.0F, 0.0F);
            this.level.addFreshEntity(boneArrow);
            this.impacted = true;
            if (entity instanceof LivingEntity && !level.isClientSide()) {
                entity.hurt(RigoranthusDamageSources.causeBoneArrowDamage(this, this.getOwner()), 0.5F + amplifier);
                if (Math.random() <= 0.1) {
                    ((LivingEntity) entity).addEffect(new EffectInstance(RigoranthusEffectRegistry.NECROTIZING_FASCIITIS, 100));
                }
            }
        }
    }

    @Override
    public double getBaseDamage() {
        return 2.0D;
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(!this.impacted ? Registration.BONE_ARROW.getItem() : ItemInit.BONE_FRAGMENT.get());
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
