package com.platinumg17.rigoranthusemortisreborn.magica.client.renderer.tile;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.platinumg17.rigoranthusemortisreborn.magica.client.particle.ParticleColor;
import com.platinumg17.rigoranthusemortisreborn.magica.client.particle.ParticleLineData;
import com.platinumg17.rigoranthusemortisreborn.magica.client.particle.ParticleUtil;
import com.platinumg17.rigoranthusemortisreborn.magica.client.renderer.item.FixedGeoBlockItemRenderer;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.DominionCrystallizerTile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

import java.util.Random;

public class DominionCrystallizerRenderer extends GeoBlockRenderer<DominionCrystallizerTile> {

    public DominionCrystallizerRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn, new DominionCrystallizerModel());
    }

    @Override
    public boolean shouldRenderOffScreen(TileEntity tileEntity) {
        return false;
    }

    @Override
    public void renderEarly(DominionCrystallizerTile dominionCrystallizerTile, MatrixStack ms, float ticks, IRenderTypeBuffer renderTypeBuffer, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float partialTicks) {
        World world = dominionCrystallizerTile.getLevel();
        BlockPos pos = dominionCrystallizerTile.getBlockPos();
        Random rand = world.random;
        if(Minecraft.getInstance().isPaused())
            return;
        double x = dominionCrystallizerTile.getBlockPos().getX();
        double y = dominionCrystallizerTile.getBlockPos().getY();
        double z = dominionCrystallizerTile.getBlockPos().getZ();

        boolean draining = (dominionCrystallizerTile.draining);
        boolean fluidNearby = (dominionCrystallizerTile.isDrainingDominionPossible() || dominionCrystallizerTile.isDrainingIchorPossible());
        int baseAge = draining ? 20 : 40;
        int randBound = draining ? 3 : 6;
        int numParticles = draining ? 2 : 1;
        float scaleAge = draining ?(float) ParticleUtil.inRange(0.1, 0.2) : (float) ParticleUtil.inRange(0.05, 0.15);
        if(world.random.nextInt(randBound)  == 0 && !Minecraft.getInstance().isPaused() && fluidNearby && dominionCrystallizerTile.stack.isEmpty()) {
            for(int i =0; i< numParticles; i++) {
                Vector3d particlePos = new Vector3d(pos.getX(), pos.getY(), pos.getZ()).add(0.5, 0.5, 0.5);
                particlePos = particlePos.add(ParticleUtil.pointInSphere());
                world.addParticle(ParticleLineData.createData(new ParticleColor(255,0,0) ,scaleAge, baseAge+world.random.nextInt(20)) , // purple ParticleColor(255,25,180)
                        particlePos.x(), particlePos.y(), particlePos.z(),
                        pos.getX() + 0.5  , pos.getY() +0.5 , pos.getZ()+ 0.5);
            }
        }
        if(dominionCrystallizerTile.timeAnimating > 40 && dominionCrystallizerTile.timeAnimating < 90) {
//            if(world.getGameTime() % 8 != 0)
//                return;
            for (int i = 0; i < 1; i++) {
                double posX = pos.getX();
                double posY = pos.getY();
                double posZ = pos.getZ();
                double randX = world.random.nextFloat() > 0.5 ? world.random.nextFloat() : -world.random.nextFloat();
                double randZ = world.random.nextFloat() > 0.5 ? world.random.nextFloat() : -world.random.nextFloat();
                double d0 = posX + 0.5 + randX * 0.2;
                double d1 = posY + 0.3;
                double d2 = posZ + 0.5 + randZ * 0.2;
                double spdX = world.random.nextFloat() > 0.5 ? world.random.nextFloat() : -world.random.nextFloat();
                double spdZ = world.random.nextFloat() > 0.5 ? world.random.nextFloat() : -world.random.nextFloat();
                world.addParticle(ParticleTypes.SOUL, d0, d1, d2,  spdX * 0.05, 0.4,  spdZ * 0.05);
            }
        }
        if(dominionCrystallizerTile.stack == null)
            return;
        if (dominionCrystallizerTile.entity == null || !ItemStack.matches(dominionCrystallizerTile.entity.getItem(), dominionCrystallizerTile.stack)) {
            dominionCrystallizerTile.entity = new ItemEntity(dominionCrystallizerTile.getLevel(), x ,y, z, dominionCrystallizerTile.stack);
        }
        x = x + 0;
        y = y + 0.9;
        z = z + 0;
        if(dominionCrystallizerTile.timeAnimating >= 90) {
            renderPressedItem(x, y, z, dominionCrystallizerTile.stack.getItem(), ms, renderTypeBuffer,packedLightIn,packedOverlayIn);
        }
    }

    public void renderPressedItem(double x, double y, double z, Item itemToRender, MatrixStack matrixStack, IRenderTypeBuffer iRenderTypeBuffer, int i, int il){
        matrixStack.pushPose();
        Direction direction1 = Direction.from2DDataValue((1 + Direction.NORTH.get2DDataValue()) % 4);
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(-direction1.toYRot()));
        matrixStack.mulPose(Vector3f.XP.rotationDegrees(90.0F));
        matrixStack.translate(0, 0D, -0.2d);
        matrixStack.scale(0.45f, 0.45f, 0.45F);
        Minecraft.getInstance().getItemRenderer().renderStatic(new ItemStack(itemToRender), ItemCameraTransforms.TransformType.FIXED, 150, il , matrixStack, iRenderTypeBuffer);
        matrixStack.popPose();
    }

    @Override
    public RenderType getRenderType(DominionCrystallizerTile animatable, float partialTicks, MatrixStack stack, IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(textureLocation);
    }

    public static FixedGeoBlockItemRenderer getISTER() {
        return new FixedGeoBlockItemRenderer(new DominionCrystallizerModel());
    }
}






//        ms.pushPose();
//        ms.translate(0.5, -0.5, 0.5);
//
//        boolean draining = (ichorCrystallizerTile.drainingIchor || ichorCrystallizerTile.drainingDominion);
//        int baseAge = draining ? 20 : 40;
//        int randBound = draining ? 3 : 6;
//        int numParticles = draining ? 2 : 1;
//        float scaleAge = draining ?(float) ParticleUtil.inRange(0.1, 0.2) : (float) ParticleUtil.inRange(0.05, 0.15);
//        if(world.random.nextInt(randBound)  == 0 && !Minecraft.getInstance().isPaused()){
//            for(int i =0; i< numParticles; i++){
//                Vector3d particlePos = new Vector3d(pos.getX(), pos.getY(), pos.getZ()).add(0.5, 0.5, 0.5);
//                particlePos = particlePos.add(ParticleUtil.pointInSphere());
//                world.addParticle(ParticleLineData.createData(new ParticleColor(255,25,180) ,scaleAge, baseAge+world.random.nextInt(20)) ,
//                        particlePos.x(), particlePos.y(), particlePos.z(),
//                        pos.getX() + 0.5  , pos.getY() +0.5 , pos.getZ()+ 0.5);
//            }
//        }
//        ichorCrystallizerTile.entity.setPos(x,y+1,z);
//        ItemEntity entityItem = ichorCrystallizerTile.entity;
//        ms.popPose();
//        ms.pushPose();
//        ms.scale(0.5f, 0.5f, 0.5f);
//        ms.translate(1D, 1f, 1D);
//        Minecraft.getInstance().getItemRenderer().renderStatic(entityItem.getItem(), ItemCameraTransforms.TransformType.FIXED, 15728880, overlay, ms, buffers);
//        ms.popPose();
//    }