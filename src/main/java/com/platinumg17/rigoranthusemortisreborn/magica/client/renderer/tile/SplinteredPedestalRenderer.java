package com.platinumg17.rigoranthusemortisreborn.magica.client.renderer.tile;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.util.MappingUtil;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.SplinteredPedestalTile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class SplinteredPedestalRenderer extends TileEntityRenderer<SplinteredPedestalTile> {

    public SplinteredPedestalRenderer(TileEntityRendererDispatcher renderManager) {
        super(renderManager);
    }

    public void renderFloatingItem(SplinteredPedestalTile tileEntityIn, ItemEntity entityItem, double x, double y, double z, MatrixStack stack, IRenderTypeBuffer iRenderTypeBuffer){
        stack.pushPose();
        tileEntityIn.frames += 1.5f * Minecraft.getInstance().getDeltaFrameTime();
        entityItem.setYHeadRot(tileEntityIn.frames);
        ObfuscationReflectionHelper.setPrivateValue(ItemEntity.class, entityItem, (int) tileEntityIn.frames, MappingUtil.getItemEntityAge());
        Minecraft.getInstance().getEntityRenderDispatcher().render(entityItem, 0.5,1,0.5, entityItem.yRot, 2.0f,stack, iRenderTypeBuffer,15728880);
        Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(entityItem);
        stack.popPose();
    }

    @Override
    public void render(SplinteredPedestalTile tileEntityIn, float v, MatrixStack matrixStack, IRenderTypeBuffer iRenderTypeBuffer, int i, int i1) {
        double x = tileEntityIn.getBlockPos().getX();
        double y = tileEntityIn.getBlockPos().getY();
        double z = tileEntityIn.getBlockPos().getZ();
        if(tileEntityIn.stack == null)
            return;
        if (tileEntityIn.entity == null || !ItemStack.matches(tileEntityIn.entity.getItem(), tileEntityIn.stack)) {
            tileEntityIn.entity = new ItemEntity(tileEntityIn.getLevel(), x, y, z, tileEntityIn.stack);
        }
        ItemEntity entityItem = tileEntityIn.entity;
        x = x + .5;
        y = y + 0.9;
        z = z +.5;
        renderFloatingItem(tileEntityIn, entityItem, x, y , z, matrixStack, iRenderTypeBuffer);
    }
}