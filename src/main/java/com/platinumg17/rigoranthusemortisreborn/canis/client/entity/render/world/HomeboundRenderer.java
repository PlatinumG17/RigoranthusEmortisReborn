package com.platinumg17.rigoranthusemortisreborn.canis.client.entity.render.world;

//import java.util.Optional;
//import org.lwjgl.opengl.GL11;
//import com.mojang.blaze3d.matrix.MatrixStack;
//import com.mojang.blaze3d.platform.GlStateManager;
//import com.mojang.blaze3d.systems.RenderSystem;
//import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.CanisEntity;
//import com.platinumg17.rigoranthusemortisreborn.canis.CanisSkills;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.renderer.BufferBuilder;
//import net.minecraft.client.renderer.Tessellator;
//import net.minecraft.client.renderer.WorldRenderer;
//import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.util.math.AxisAlignedBB;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.math.vector.Vector3d;
//import net.minecraftforge.client.event.RenderWorldLastEvent;
//
//public class HomeboundRenderer {
//
//    public static void onWorldRenderLast(RenderWorldLastEvent event) {
//        PlayerEntity player = Minecraft.getInstance().player;
//        for (Entity passenger : player.getPassengers()) {
//            if (passenger instanceof CanisEntity) {
//                CanisEntity canis = (CanisEntity) passenger;
//                Optional<BlockPos> bedPosOpt = canis.getBedPos();
//
//                if (bedPosOpt.isPresent()) {
//                    BlockPos bedPos = bedPosOpt.get();
//                    int level = canis.getLevel(CanisSkills.HOMEBOUND);
//                    double distance = (level * 200D) - Math.sqrt(bedPos.distSqr(canis.blockPosition()));
//                    if (level == 5 || distance >= 0.0D) {
//                        MatrixStack stack = event.getMatrixStack();
//
//                        AxisAlignedBB boundingBox = new AxisAlignedBB(bedPos).inflate(0.5D);
//                        drawSelectionBox(stack, boundingBox);
//                    }
//                }
//            }
//        }
//    }
//
//    public static void drawSelectionBox(MatrixStack stack, AxisAlignedBB boundingBox) {
//        RenderSystem.disableAlphaTest();
//        RenderSystem.disableLighting(); //Make the line see thought blocks
//        RenderSystem.depthMask(false);
//        RenderSystem.disableDepthTest(); //Make the line see thought blocks
//        RenderSystem.enableBlend();
//        RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
//        //TODO Used when drawing outline of bounding box
//        RenderSystem.lineWidth(2.0F);
//
//        RenderSystem.disableTexture();
//        Vector3d vec3d = Minecraft.getInstance().gameRenderer.getMainCamera().getPosition();
//        double d0 = vec3d.x();
//        double d1 = vec3d.y();
//        double d2 = vec3d.z();
//
//        BufferBuilder buf = Tessellator.getInstance().getBuilder();
//        buf.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION_COLOR);
//        WorldRenderer.renderLineBox(stack, buf, boundingBox.move(-d0, -d1, -d2), 1F, 1F, 0, 1F);
//        Tessellator.getInstance().end();
//        RenderSystem.color4f(0.0F, 0.0F, 0.0F, 0.3F);
//        RenderSystem.enableDepthTest(); //Make the line see thought blocks
//        RenderSystem.depthMask(true);
//        RenderSystem.enableTexture();
//        RenderSystem.enableLighting(); //Make the line see thought blocks
//        RenderSystem.disableBlend();
//        RenderSystem.enableAlphaTest();
//    }
//}