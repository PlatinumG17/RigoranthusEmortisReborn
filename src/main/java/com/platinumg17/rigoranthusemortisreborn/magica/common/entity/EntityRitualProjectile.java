package com.platinumg17.rigoranthusemortisreborn.magica.common.entity;

import com.platinumg17.rigoranthusemortisreborn.magica.client.particle.ParticleColor;
import com.platinumg17.rigoranthusemortisreborn.magica.client.particle.ParticleSparkleData;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.RitualTile;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;

public class EntityRitualProjectile extends ColoredProjectile{

    public BlockPos tilePos;

    public EntityRitualProjectile(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    public EntityRitualProjectile(World worldIn, BlockPos pos) {
        super(worldIn, pos.getX(), pos.getY(), pos.getZ());
    }

    public EntityRitualProjectile(EntityType<EntityRitualProjectile> entityAOEProjectileEntityType, World world) {
        super(entityAOEProjectileEntityType, world);
    }
    @Override
    public void tick() {
        if(!level.isClientSide() && (tilePos == null || !(level.getBlockEntity(tilePos) instanceof RitualTile) || ((RitualTile) level.getBlockEntity(tilePos)).ritual == null )) {
            this.remove();
            return;
        }
        xOld = getX();
        yOld = getY();
        zOld = getZ();

        this.setPos(getX(), getY() + Math.sin(level.getGameTime()/10D)/10, getZ());
        xo = getX();
        yo = getY();
        zo = getZ();

        if(level.isClientSide) {
            int counter = 0;
            for (double j = 0; j < 3; j++) {

                counter += level.random.nextInt(3);
                if (counter % (Minecraft.getInstance().options.particles.getId() == 0 ? 1 : 2 * Minecraft.getInstance().options.particles.getId()) == 0) {
                    level.addParticle(ParticleSparkleData.createData(getParticleColor()),
                            (float) (position().x()) + Math.sin(level.getGameTime()/3D),
                            (float) (position().y()),
                            (float) (position().z()) + Math.cos(level.getGameTime()/3D),
                            0.0225f * (random.nextFloat() ), 0.0225f * (random.nextFloat()), 0.0225f * (random.nextFloat() ));
                }
            }
            for (double j = 0; j < 3; j++) {

                counter += level.random.nextInt(3);
                if (counter % (Minecraft.getInstance().options.particles.getId() == 0 ? 1 : 2 * Minecraft.getInstance().options.particles.getId()) == 0) {
                    level.addParticle(ParticleSparkleData.createData(new ParticleColor(2, 0, 144)),
                            (float) (position().x()) - Math.sin(level.getGameTime()/3D),
                            (float) (position().y()),
                            (float) (position().z()) - Math.cos(level.getGameTime()/3D),
                            0.0225f * (random.nextFloat() ), 0.0225f * (random.nextFloat()), 0.0225f * (random.nextFloat() ));
                }
            }
        }
    }

    @Override
    public EntityType<?> getType() {
        return ModEntities.ENTITY_RITUAL;
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public EntityRitualProjectile(FMLPlayMessages.SpawnEntity packet, World world){
        super(ModEntities.ENTITY_RITUAL, world);
    }

    @Override
    public boolean save(CompoundNBT tag) {
        if(tilePos != null)
            tag.put("ritpos", NBTUtil.writeBlockPos(tilePos));
        return super.save(tag);
    }

    @Override
    public void load(CompoundNBT compound) {
        super.load(compound);
        if(compound.contains("ritpos")){
            tilePos = NBTUtil.readBlockPos(compound.getCompound("ritpos"));
        }
    }
}