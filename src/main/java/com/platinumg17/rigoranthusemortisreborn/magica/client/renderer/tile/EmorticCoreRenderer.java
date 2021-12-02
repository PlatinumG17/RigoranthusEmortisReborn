package com.platinumg17.rigoranthusemortisreborn.magica.client.renderer.tile;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.magica.client.particle.GlowParticleData;
import com.platinumg17.rigoranthusemortisreborn.magica.client.particle.ParticleColor;
import com.platinumg17.rigoranthusemortisreborn.magica.client.particle.ParticleUtil;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.EmorticCoreTile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class EmorticCoreRenderer extends TileEntityRenderer<EmorticCoreTile> {
    public static final ResourceLocation texture = new ResourceLocation(EmortisConstants.MOD_ID + ":textures/entity/emortic_core.png");
    public static final EmorticCoreModel model = new EmorticCoreModel();

    public EmorticCoreRenderer(TileEntityRendererDispatcher p_i226006_1_) {
        super(p_i226006_1_);
    }

    @Override
    public void render(EmorticCoreTile tileEntityIn, float partialTicks, MatrixStack ms, IRenderTypeBuffer buffers, int light, int overlay) {
        ms.pushPose();
        ms.translate(0.5, -0.5, 0.5);
        IVertexBuilder buffer = buffers.getBuffer(model.renderType(texture));
        model.renderToBuffer(ms, buffer, light, overlay, 1, 1, 1, 1);
        ms.popPose();
        World world = tileEntityIn.getLevel();
        Random rand = world.random;
        BlockPos pos = tileEntityIn.getBlockPos();
        ParticleColor color = new ParticleColor(50 +rand.nextInt(175),50+ rand.nextInt(175), 50+rand.nextInt(175));
        ParticleColor randColor = new ParticleColor(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
        if(Minecraft.getInstance().isPaused())
            return;

        for(int i = 0; i < 2; i++) {
            world.addParticle(
                    GlowParticleData.createData(randColor),
                    pos.getX() + 0.5 + ParticleUtil.inRange(-0.3, 0.3), pos.getY() + 0.5 + ParticleUtil.inRange(-0.3, 0.3), pos.getZ() + 0.5 + ParticleUtil.inRange(-0.3, 0.3),
                    0, 0, 0);
        }
    }

    public static class ISRender extends ItemStackTileEntityRenderer {
        public ISRender(){ }
        @Override
        public void renderByItem(ItemStack stack, ItemCameraTransforms.TransformType p_239207_2_, MatrixStack ms, IRenderTypeBuffer buffers, int light, int overlay) {
            ms.pushPose();
            ms.scale(0.75f, 0.75f, 0.75f);
            ms.translate(0.75, -0.40, 0.6);
            IVertexBuilder buffer = buffers.getBuffer(model.renderType(texture));
            model.renderToBuffer(ms, buffer, light, overlay, 1, 1, 1, 1);
            ms.popPose();
        }
    }
}