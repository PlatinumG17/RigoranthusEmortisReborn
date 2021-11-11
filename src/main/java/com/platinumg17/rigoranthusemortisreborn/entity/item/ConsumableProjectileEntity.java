package com.platinumg17.rigoranthusemortisreborn.entity.item;

import com.platinumg17.rigoranthusemortisreborn.core.init.ItemInit;
import com.platinumg17.rigoranthusemortisreborn.items.weapons.type.projectiles.basetype.IProjectileDamaging;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class ConsumableProjectileEntity extends ProjectileItemEntity {
    public ConsumableProjectileEntity(EntityType<? extends ConsumableProjectileEntity> type, World worldIn) {super(type, worldIn);}

    public ConsumableProjectileEntity(EntityType<? extends ConsumableProjectileEntity> type, double x, double y, double z, World worldIn) {super(type, x, y, z, worldIn);}

    public ConsumableProjectileEntity(EntityType<? extends ConsumableProjectileEntity> type, LivingEntity livingEntityIn, World worldIn) {super(type, livingEntityIn, worldIn);}

    private static boolean isNonCreativePlayer(Entity entity) {return entity instanceof PlayerEntity && !((PlayerEntity) entity).isCreative();}

    @Override
    protected void onHit(RayTraceResult result) {
        int damage = IProjectileDamaging.getDamageFromItem(getItemFromItemStack().getItem());

        if(result.getType() == RayTraceResult.Type.ENTITY) {
            Entity entity = ((EntityRayTraceResult) result).getEntity();
                entity.hurt(DamageSource.thrown(this, getOwner()), damage);
        }
        if(isNonCreativePlayer(getOwner())) {
            if(random.nextFloat() < 0.99F) {
                ItemEntity itemEntity = new ItemEntity(this.level, this.getX(), this.getY(), this.getZ(), this.getItemFromItemStack());
                level.addFreshEntity(itemEntity);
            }
            else {this.level.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.ITEM_BREAK, SoundCategory.NEUTRAL, 0.8F, 1.5F);}
        }
        this.remove();
    }
    @Override
    public IPacket<?> getAddEntityPacket() {return NetworkHooks.getEntitySpawningPacket(this);}

    @Override
    protected Item getDefaultItem() {
        return ItemInit.RAZORTOOTH_KUNAI.get();
    }

    public ItemStack getItemFromItemStack() {
        ItemStack itemstack = this.getItemRaw();
        return itemstack.isEmpty() ? new ItemStack(this.getDefaultItem()) : itemstack;
    }
}