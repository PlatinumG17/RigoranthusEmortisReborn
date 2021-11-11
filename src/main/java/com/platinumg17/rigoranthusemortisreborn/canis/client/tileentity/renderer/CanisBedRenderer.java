package com.platinumg17.rigoranthusemortisreborn.canis.client.tileentity.renderer;

import com.google.common.base.Objects;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.platinumg17.rigoranthusemortisreborn.canis.client.entity.render.RenderUtil;
import com.platinumg17.rigoranthusemortisreborn.canis.common.block.tileentity.CanisBedTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;

public class CanisBedRenderer extends TileEntityRenderer<CanisBedTileEntity> {
    public CanisBedRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {super(rendererDispatcherIn);}
    @Override
    public void render(CanisBedTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        if (tileEntityIn.getBedName() != null && this.isLookingAtBed(tileEntityIn)) {
            matrixStackIn.pushPose();
            matrixStackIn.translate(0.5D, 0.5D, 0.5D);
            RenderUtil.renderLabelWithScale(true, Minecraft.getInstance().getEntityRenderDispatcher(), tileEntityIn.getBedName(), matrixStackIn, bufferIn, combinedLightIn, 0.025F, 1.2F);
            matrixStackIn.popPose();
        }
    }

    public boolean isLookingAtBed(CanisBedTileEntity tileEntityIn) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.hitResult != null && mc.hitResult.getType() == RayTraceResult.Type.BLOCK) {
            BlockPos blockpos = ((BlockRayTraceResult) mc.hitResult).getBlockPos();
            return Objects.equal(blockpos, tileEntityIn.getBlockPos());
        }
        return false;
    }
}