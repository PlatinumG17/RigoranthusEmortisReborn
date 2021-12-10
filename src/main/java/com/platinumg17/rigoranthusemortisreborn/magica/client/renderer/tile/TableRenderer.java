package com.platinumg17.rigoranthusemortisreborn.magica.client.renderer.tile;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.platinumg17.rigoranthusemortisreborn.magica.client.renderer.item.GenericItemRenderer;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.TableBlock;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.TableTile;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.BlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.BedPart;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

import javax.annotation.Nullable;

public class TableRenderer extends GeoBlockRenderer<TableTile> {

    public TableRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn, new TableModel());
    }

    @Override
    public void renderEarly(TableTile tile, MatrixStack matrixStack, float ticks, IRenderTypeBuffer iRenderTypeBuffer, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float partialTicks) {
        try{
            if(tile.getLevel().getBlockState(tile.getBlockPos()).getBlock() != BlockRegistry.TABLE_BLOCK)
                return;
            if(tile.getLevel().getBlockState(tile.getBlockPos()).getValue(TableBlock.PART) != BedPart.HEAD)
                return;
            if (tile.stack == null) {
                return;
            }
            double x = tile.getBlockPos().getX();
            double y = tile.getBlockPos().getY();
            double z = tile.getBlockPos().getZ();
            if (tile.entity == null || !ItemStack.matches(tile.entity.getItem(), tile.stack)) {
                tile.entity = new ItemEntity(tile.getLevel(), x, y, z, tile.stack);
            }

            ItemEntity entityItem = tile.entity;
            renderPressedItem(tile, entityItem.getItem().getItem(), matrixStack, iRenderTypeBuffer, packedLightIn, packedOverlayIn);
        }catch (Throwable t){
            t.printStackTrace();
            // Mercy for HORRIBLE RENDER CHANGING MODS
        }
    }


    @Override
    public void render(TableTile tile, float partialTicks, MatrixStack stack, IRenderTypeBuffer bufferIn, int packedLightIn) {
        try{
            if(tile.getLevel().getBlockState(tile.getBlockPos()).getBlock() != BlockRegistry.TABLE_BLOCK)
                return;
            if(tile.getLevel().getBlockState(tile.getBlockPos()).getValue(TableBlock.PART) != BedPart.HEAD)
                return;
            Direction direction = tile.getLevel().getBlockState(tile.getBlockPos()).getValue(TableBlock.FACING);
            stack.pushPose();

            if(direction == Direction.NORTH){
                stack.mulPose(Vector3f.YP.rotationDegrees(-90));
                stack.translate(1, 0, -1);
            }

            if(direction == Direction.SOUTH){
                stack.mulPose(Vector3f.YP.rotationDegrees(90));
                stack.translate(-1, 0, 0);
            }

            if(direction == Direction.WEST){
                stack.mulPose(Vector3f.YP.rotationDegrees(90));
                stack.translate(-1, 0, 0);

            }

            if(direction == Direction.EAST){
                stack.mulPose(Vector3f.YP.rotationDegrees(-90));
                stack.translate(0, 0, 0);

            }

            super.render(tile, partialTicks, stack, bufferIn, packedLightIn);
            stack.popPose();
        }catch (Throwable t){
            t.printStackTrace();
            // why must people change the rendering order of tesrs
        }
    }
    public void renderPressedItem(TableTile tile, Item itemToRender, MatrixStack matrixStack, IRenderTypeBuffer iRenderTypeBuffer, int i, int il){
        Direction direction = tile.getLevel().getBlockState(tile.getBlockPos()).getValue(TableBlock.FACING);

        matrixStack.pushPose();
        matrixStack.translate(0, 1.D, 0);
        BlockState state = tile.getLevel().getBlockState(tile.getBlockPos());
        if(!(state.getBlock() instanceof TableBlock))
            return;
        float y = state.getValue(TableBlock.FACING).getClockWise().toYRot();
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(-y + 90f));
        matrixStack.mulPose(Vector3f.XP.rotationDegrees(90f));
        matrixStack.mulPose(Vector3f.ZP.rotationDegrees(180F));
        if(direction == Direction.WEST){
            matrixStack.mulPose(Vector3f.ZP.rotationDegrees(90f));
        }
        if(direction == Direction.EAST){
            matrixStack.mulPose(Vector3f.ZP.rotationDegrees(-90f));
        }
        if(direction == Direction.SOUTH){
            matrixStack.mulPose(Vector3f.ZP.rotationDegrees(180));
        }
        matrixStack.scale(0.6f, 0.6f, 0.6f);

        Minecraft.getInstance().getItemRenderer().renderStatic(new ItemStack(itemToRender), ItemCameraTransforms.TransformType.FIXED, i, il, matrixStack, iRenderTypeBuffer);
        matrixStack.popPose();
    }
    public static GenericItemRenderer getISTER(){
        return new GenericItemRenderer(new TableModel()).withTranslucency();
    }

    @Override
    public RenderType getRenderType(TableTile animatable, float partialTicks, MatrixStack stack, @Nullable IRenderTypeBuffer renderTypeBuffer, @Nullable IVertexBuilder vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(textureLocation);
    }

    @Override
    public boolean shouldRenderOffScreen(TileEntity p_188185_1_) {
        return false;
    }
}