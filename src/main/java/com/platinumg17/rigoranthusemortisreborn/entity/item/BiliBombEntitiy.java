package com.platinumg17.rigoranthusemortisreborn.entity.item;

import com.platinumg17.rigoranthusemortisreborn.core.init.ItemInit;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class BiliBombEntitiy extends ProjectileItemEntity implements IRendersAsItem {
    private boolean shouldDestroy = true;

    public BiliBombEntitiy(EntityType<? extends BiliBombEntitiy> type, World worldIn) {super(type, worldIn);}

    public BiliBombEntitiy(EntityType<? extends BiliBombEntitiy> type, double x, double y, double z, World worldIn) {super(type, x, y, z, worldIn);}

    public BiliBombEntitiy(EntityType<? extends BiliBombEntitiy> type, LivingEntity livingEntityIn, World worldIn, boolean shouldDestroy) {
        super(type, livingEntityIn, worldIn);
        this.shouldDestroy = shouldDestroy;
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("shouldDestroy", shouldDestroy);
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        shouldDestroy = compound.getBoolean("shouldDestroy");
    }

    @Override
    protected void onHit(RayTraceResult result) {
        if(!this.level.isClientSide) {
            level.explode(null, result.getLocation().x, result.getLocation().y, result.getLocation().z, 3F, shouldDestroy ? Explosion.Mode.DESTROY : Explosion.Mode.NONE);
        }
        this.remove();
    }
    @Override
    public IPacket<?> getAddEntityPacket() {return NetworkHooks.getEntitySpawningPacket(this);}

    @Override
    protected Item getDefaultItem() {return ItemInit.BILI_BOMB.get();}
}