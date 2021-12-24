package com.platinumg17.rigoranthusemortisreborn.magica.client.renderer.tile;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.util.MappingUtil;
import com.platinumg17.rigoranthusemortisreborn.magica.client.ClientInfo;
import com.platinumg17.rigoranthusemortisreborn.magica.client.particle.GlowParticleData;
import com.platinumg17.rigoranthusemortisreborn.magica.client.particle.ParticleColor;
import com.platinumg17.rigoranthusemortisreborn.magica.client.particle.ParticleLineData;
import com.platinumg17.rigoranthusemortisreborn.magica.client.particle.ParticleUtil;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.PsyglyphicAmalgamatorTile;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.client.model.data.EmptyModelData;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.util.Random;

public class PsyglyphicAmalgamatorRenderer extends TileEntityRenderer<PsyglyphicAmalgamatorTile> {

    public PsyglyphicAmalgamatorRenderer(TileEntityRendererDispatcher rendererDispatcher) {
        super(rendererDispatcher);
    }

    @Override
    public void render(PsyglyphicAmalgamatorTile tileEntityIn, float v, MatrixStack matrixStack, IRenderTypeBuffer iRenderTypeBuffer, int lightIn, int overlayIn) {
        double x = tileEntityIn.getBlockPos().getX();
        double y = tileEntityIn.getBlockPos().getY();
        double z = tileEntityIn.getBlockPos().getZ();
        if(tileEntityIn.catalystItem == null)
            return;

        if (tileEntityIn.entity == null || !ItemStack.matches(tileEntityIn.entity.getItem(), tileEntityIn.catalystItem)) {
            tileEntityIn.entity = new ItemEntity(tileEntityIn.getLevel(), x, y, z, tileEntityIn.catalystItem);
        }
        matrixStack.pushPose();
        double sinOffset = Math.pow(Math.cos((ClientInfo.ticksInGame + v)  /10)/4, 2);
        matrixStack.translate(0.5D,  0.5 + sinOffset, 0.5D);
        float angle = ((ClientInfo.ticksInGame + v)/5.0f) % 360;
        if(tileEntityIn.isCrafting){
            World world = tileEntityIn.getLevel();
            BlockPos pos  = tileEntityIn.getBlockPos().offset(0, 0.5, 0);
            Random rand = world.getRandom();
            for(int i =0; i< 5; i++){
                Vector3d particlePos = new Vector3d(pos.getX(), pos.getY(), pos.getZ()).add(0.5, 0, 0.5);
                particlePos = particlePos.add(ParticleUtil.pointInSphere());
                world.addParticle(ParticleLineData.createData(new ParticleColor(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255))),
                        particlePos.x(), particlePos.y(), particlePos.z(),
                        pos.getX()  +0.5, pos.getY() + 1  , pos.getZ() +0.5);
            }
            tileEntityIn.pedestalList().forEach((b) ->{
                BlockPos pos2 = b.immutable();
                for(int i = 0; i < 1; i++){
                    tileEntityIn.getLevel().addParticle(
                            GlowParticleData.createData(new ParticleColor(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255))),
                            pos2.getX() +0.5 + ParticleUtil.inRange(-0.2, 0.2)  , pos2.getY() +1.5  + ParticleUtil.inRange(-0.3, 0.3) , pos2.getZ() +0.5 + ParticleUtil.inRange(-0.2, 0.2),
                            0,0,0);
                }});
        }
        matrixStack.popPose();
        ItemEntity entityItem = tileEntityIn.entity;
        matrixStack.pushPose();
        matrixStack.translate(0.5D, 0.55f +sinOffset, 0.5D);
        matrixStack.scale(0.35f, 0.35f, 0.35F);
        Minecraft.getInstance().getItemRenderer().renderStatic(entityItem.getItem(), ItemCameraTransforms.TransformType.FIXED, 15728880, overlayIn, matrixStack, iRenderTypeBuffer);
        matrixStack.popPose();
    }

    public void renderFloatingItem(PsyglyphicAmalgamatorTile tileEntityIn, ItemEntity entityItem, double x, double y, double z, MatrixStack stack, IRenderTypeBuffer iRenderTypeBuffer){
        stack.pushPose();
        tileEntityIn.frames += 1.5f * Minecraft.getInstance().getDeltaFrameTime();
        entityItem.setYHeadRot(tileEntityIn.frames);
        ObfuscationReflectionHelper.setPrivateValue(ItemEntity.class, entityItem, (int) tileEntityIn.frames, MappingUtil.getItemEntityAge());
        Minecraft.getInstance().getEntityRenderDispatcher().render(entityItem, 0.5,1,0.5, entityItem.yRot, 2.0f,stack, iRenderTypeBuffer,15728880);
        Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(entityItem);
        stack.popPose();
    }
}