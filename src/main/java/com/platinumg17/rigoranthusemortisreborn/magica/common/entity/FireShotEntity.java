package com.platinumg17.rigoranthusemortisreborn.magica.common.entity;

import com.platinumg17.rigoranthusemortisreborn.magica.client.particle.GlowParticleData;
import com.platinumg17.rigoranthusemortisreborn.magica.client.particle.ParticleColor;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.MagicItemsRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class FireShotEntity extends FireballEntity {

    private int age;
    int maxAge = 70;

    public FireShotEntity(World world, double x, double y, double z, double xD, double yD, double zD) {
        super(world, x, y, z, xD, yD, zD);
    }

    public FireShotEntity(World world, LivingEntity entity, double xD, double yD, double zD) {
        super(world, entity, xD, yD, zD);
    }

    public FireShotEntity(EntityType<? extends FireballEntity> fireShot, World world) {
        super(fireShot, world);
    }

    @Override
    protected void onHitEntity(EntityRayTraceResult pResult) {
        if (!this.level.isClientSide) {
            Entity entity = pResult.getEntity();
            Entity entity1 = this.getOwner();
            if (entity != entity1) {
                super.onHitEntity(pResult);
            }
        }
    }

    @Override
    protected @Nonnull ItemStack getItemRaw() {
        return MagicItemsRegistry.FIRE_SHOT.getStack();
    }

    @Override
    public @Nonnull ItemStack getItem() {
        ItemStack itemstack = MagicItemsRegistry.FIRE_SHOT.getStack();
        return itemstack.isEmpty() ? new ItemStack(MagicItemsRegistry.FIRE_SHOT.asItem()) : itemstack;
    }

    public void tick() {
        this.age++;
        super.tick();
        if(age > maxAge) {
            this.remove(false);
        }
        if(level.isClientSide && this.age > 1) {
            double deltaX = getX() - xOld;
            double deltaY = getY() - yOld;
            double deltaZ = getZ() - zOld;
            double dist = Math.ceil(Math.sqrt(deltaX*deltaX+deltaY*deltaY+deltaZ*deltaZ) * 20);
            int counter = 0;

            for (double i = 0; i < dist; i ++){
                double coeff = i/dist;
                counter += level.random.nextInt(2);
                if (counter % (Minecraft.getInstance().options.particles.getId() == 0 ? 1 : 2 * Minecraft.getInstance().options.particles.getId()) == 0) {
                    level.addParticle(GlowParticleData.createData(new ParticleColor(255, 0, 0)),
                            (float) (xo + deltaX * coeff), (float) (yo + deltaY * coeff), (float) (zo + deltaZ * coeff), 0.0125f * (random.nextFloat() - 0.5f), 0.0125f * (random.nextFloat() - 0.5f), 0.0125f * (random.nextFloat() - 0.5f));
                }
            }
        }
    }

    @Override
    public void baseTick() {
        super.baseTick();
    }
//    @Override
//    public Packet<?> getAddEntityPacket() {
//        return NetworkHooks.getEntitySpawningPacket(this);
//    }
//
//    public FireShotEntity(PlayMessages.SpawnEntity packet, Level world){
//        super(EntityType.FIREBALL, world);
//    }
//
//    @Override
//    public EntityType<?> getType() {
//        return EntityType.FIREBALL;
//    }
}