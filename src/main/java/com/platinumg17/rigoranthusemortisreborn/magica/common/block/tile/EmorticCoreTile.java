package com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile;

import com.platinumg17.rigoranthusemortisreborn.magica.client.particle.GlowParticleData;
import com.platinumg17.rigoranthusemortisreborn.magica.client.particle.ParticleColor;
import com.platinumg17.rigoranthusemortisreborn.magica.client.particle.ParticleUtil;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.BlockRegistry;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;

public class EmorticCoreTile extends TileEntity implements ITickableTileEntity {
    public EmorticCoreTile() {
        super(BlockRegistry.EMORTIC_CORE_TILE);
    }
    @Override
    public void tick() {
        if(level.isClientSide) {
            ParticleColor randColor = new ParticleColor(level.random.nextInt(255), level.random.nextInt(255), level.random.nextInt(255));
            for (int i = 0; i < 6; i++) {
                level.addParticle(
                    GlowParticleData.createData(randColor),
                    worldPosition.getX() + 0.5 + ParticleUtil.inRange(-0.3, 0.3), worldPosition.getY() + 0.5 + ParticleUtil.inRange(-0.3, 0.3), worldPosition.getZ() + 0.5 + ParticleUtil.inRange(-0.3, 0.3),
                    0, 0, 0);
            }
        }
    }
}