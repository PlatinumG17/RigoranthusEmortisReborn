package com.platinumg17.rigoranthusemortisreborn.magica.client.renderer.tile;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.platinumg17.rigoranthusemortisreborn.magica.client.particle.GlowParticleData;
import com.platinumg17.rigoranthusemortisreborn.magica.client.particle.ParticleColor;
import com.platinumg17.rigoranthusemortisreborn.magica.client.particle.ParticleUtil;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.LightTile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class LightRenderer extends TileEntityRenderer<LightTile> {
    public LightRenderer(TileEntityRendererDispatcher p_i226006_1_) {
        super(p_i226006_1_);
    }

    @Override
    public void render(LightTile lightTile, float v, MatrixStack matrixStack, IRenderTypeBuffer iRenderTypeBuffer, int i, int i1) {
        World world = lightTile.getLevel();
        BlockPos pos = lightTile.getBlockPos();
        Random rand = world.random;
        if(Minecraft.getInstance().isPaused())
            return;
        world.addParticle(
                GlowParticleData.createData(new ParticleColor(
                        rand.nextInt(lightTile.red),
                        rand.nextInt(lightTile.green),
                        rand.nextInt(lightTile.blue)
                )),
                pos.getX() +0.5 + ParticleUtil.inRange(-0.1, 0.1)  , pos.getY() +0.5  + ParticleUtil.inRange(-0.1, 0.1) , pos.getZ() +0.5 + ParticleUtil.inRange(-0.1, 0.1),
                0,0,0);
    }
}