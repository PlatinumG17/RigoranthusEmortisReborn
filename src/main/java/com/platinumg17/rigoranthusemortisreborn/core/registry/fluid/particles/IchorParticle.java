package com.platinumg17.rigoranthusemortisreborn.core.registry.fluid.particles;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nullable;
import java.util.Random;

@OnlyIn(Dist.CLIENT)
public class IchorParticle extends SpriteTexturedParticle {

    // thanks to understanding simibubi's code from the Create mod for rendering particles I was able to render my own :D
    public static final Vector3d[] CUBE = {
            // bottom render   //right
            new Vector3d(0.5, -0.1, -0.5), // >^
            new Vector3d(0.5, -0.1, 0.5),  // >V
            new Vector3d(0.25, -0.1, 0.5), // <V
            new Vector3d(0.25, -0.1, -0.5),// <^
            //left
            new Vector3d(-0.25, -0.1, -0.5), // >^
            new Vector3d(-0.25, -0.1, 0.5),  // >V
            new Vector3d(-0.5, -0.1, 0.5), // <V
            new Vector3d(-0.5, -0.1, -0.5),// <^
            //front
            new Vector3d(0.25, -0.1, -0.5), // >^
            new Vector3d(0.25, -0.1, -0.25),  // >V
            new Vector3d(-0.25, -0.1, -0.25), // <V
            new Vector3d(-0.25, -0.1, -0.5),// <^
            //back
            new Vector3d(0.25, 0.1, 0.5), // >^
            new Vector3d(0.25, 0.1, 0.25),  // >V
            new Vector3d(-0.25, 0.1, 0.25), // <V
            new Vector3d(-0.25, 0.1, 0.5),// <^
            // top render
            new Vector3d(0.25, 0.1, -0.5), // >^
            new Vector3d(0.25, 0.1, 0.5),  // >V
            new Vector3d(0.5, 0.1, 0.5), // <V
            new Vector3d(0.5, 0.1, -0.5),// <^
            //left
            new Vector3d(-0.5, 0.1, -0.5), // >^
            new Vector3d(-0.5, 0.1, 0.5),  // >V
            new Vector3d(-0.25, 0.1, 0.5), // <V
            new Vector3d(-0.25, 0.1, -0.5),// <^
            //front
            new Vector3d(-0.25, 0.1, -0.5), // >^
            new Vector3d(-0.25, 0.1, -0.25),  // >V
            new Vector3d(0.25, 0.1, -0.25), // <V
            new Vector3d(0.25, 0.1, -0.5),// <^
            //back
            new Vector3d(-0.25, -0.1, 0.5), // >^
            new Vector3d(-0.25, -0.1, 0.25),  // >V
            new Vector3d(0.25, -0.1, 0.25), // <V
            new Vector3d(0.25, -0.1, 0.5),// <^
            // front render
            new Vector3d(-0.5, -0.1, -0.5),
            new Vector3d(-0.5, 0.1, -0.5),
            new Vector3d(0.5, 0.1, -0.5),
            new Vector3d(0.5, -0.1, -0.5),
            // back render
            new Vector3d(0.5, -0.1, 0.5),
            new Vector3d(0.5, 0.1, 0.5),
            new Vector3d(-0.5, 0.1, 0.5),
            new Vector3d(-0.5, -0.1, 0.5),
            // left render
            new Vector3d(0.5, -0.1, -0.5),
            new Vector3d(0.5, 0.1, -0.5),
            new Vector3d(0.5, 0.1, 0.5),
            new Vector3d(0.5, -0.1, 0.5),
            // right render
            new Vector3d(-0.5, -0.1, 0.5),
            new Vector3d(-0.5, 0.1, 0.5),
            new Vector3d(-0.5, 0.1, -0.5),
            new Vector3d(-0.5, -0.1, -0.5),
            // inside       // front render
            new Vector3d(-0.25, -0.1, 0.25),
            new Vector3d(-0.25, 0.1, 0.25),
            new Vector3d(0.25, 0.1, 0.25),
            new Vector3d(0.25, -0.1, 0.25),
            // back render
            new Vector3d(0.25, -0.1, -0.25),
            new Vector3d(0.25, 0.1, -0.25),
            new Vector3d(-0.25, 0.1, -0.25),
            new Vector3d(-0.25, -0.1, -0.25),
            // left render
            new Vector3d(-0.25, -0.1, -0.25),
            new Vector3d(-0.25, 0.1, -0.25),
            new Vector3d(-0.25, 0.1, 0.25),
            new Vector3d(-0.25, -0.1, 0.25),
            // right render
            new Vector3d(0.25, -0.1, 0.25),
            new Vector3d(0.25, 0.1, 0.25),
            new Vector3d(0.25, 0.1, -0.25),
            new Vector3d(0.25, -0.1, -0.25),
            //middle top inside
            new Vector3d(0.25, -0.01, -0.25),
            new Vector3d(0.25, -0.01, 0.25),
            new Vector3d(-0.25, -0.01, 0.25),
            new Vector3d(-0.25, -0.01, -0.25),
            // middle bottom render
            new Vector3d(-0.25, 0.01, -0.25),
            new Vector3d(-0.25, 0.01, 0.25),
            new Vector3d(0.25, 0.01, 0.25),
            new Vector3d(0.25, 0.01, -0.25),
    };
    public static final Vector3d[] CUBE_NORMALS = {
            // modified normals for the sides
            new Vector3d(0, 0.1, 0),
            new Vector3d(0, 0.1, 0),
            new Vector3d(0, 0.1, 0),
            new Vector3d(0, 0.1, 0),
            new Vector3d(0, -0.5, 0),
            new Vector3d(0, -0.5, 0),
            new Vector3d(0, -0.5, 0),
            new Vector3d(0, -0.5, 0),
            new Vector3d(0, 0, 0.5),
            new Vector3d(0, 0, 0.5),
            new Vector3d(0, 0, 0.5),
            new Vector3d(0, 0, 0.5),
            new Vector3d(0, 0, 0.5),
            new Vector3d(0, 0, 0.5),
            new Vector3d(0, 0, 0.5),
            new Vector3d(0, 0, 0.5),
            new Vector3d(0, 0, 0.5),
            new Vector3d(0, 0, 0.5),
    };

    private static final IParticleRenderType renderType = new IParticleRenderType() {
        @Override
        public void begin(BufferBuilder bufferBuilder, TextureManager textureManager) {
            RenderSystem.disableTexture();
            // HELPER FOR RENDERING THE PARTICLE CAN CHANGE FOR RENDERING TYPES
//            RenderSystem.depthMask(false);
            RenderSystem.enableBlend();
            RenderSystem.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
            RenderSystem.enableLighting();
            RenderSystem.enableColorMaterial();
            // opaque
            RenderSystem.depthMask(true);
//			RenderSystem.disableBlend();
//			RenderSystem.enableLighting();
            bufferBuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);
        }

        @Override
        public void end(Tessellator tesselator) {
            tesselator.end(); // was draw
            RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA,
                    GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            RenderSystem.disableLighting();
            RenderSystem.enableTexture();
        }
    };
    protected float scale;
    protected boolean hot;
    protected float rotationDirection;
    protected float rotation;
    protected float rotationOffsetYaw;
    protected float rotationOffsetPitch;
    protected float rotationOffsetRoll;
    protected float colorOffset;

    public IchorParticle(ClientWorld world, double x, double y, double z, double motionX, double motionY, double motionZ) {
        super(world, x, y, z);
        this.xd = motionX;
        this.yd = motionY;
        this.zd = motionZ;
        this.rotation = 0;

        averageAge(80);
        Random random = new Random();
        this.colorOffset = (random.nextFloat() * 0.25f);
        this.rotationOffsetYaw = random.nextFloat();
        this.rotationOffsetPitch = random.nextFloat();
        this.rotationOffsetRoll = random.nextFloat();
        setScale(0.2F);
        setRotationDirection(random.nextFloat() - 0.5f);
    }

    public void setScale(float scale) {
        this.scale = scale;
        this.setSize(scale * 0.5f, scale * 0.5f);
    }

    public void averageAge(int age) {
        Random random = new Random();
        this.lifetime = (int) (age + (random.nextDouble() * 2D - 1D) * 8);
    }

    public void setHot(boolean hot) {this.hot = hot;}
    public void setRotationDirection(float rotationDirection) {this.rotationDirection = rotationDirection;}
    private boolean billowing = false;

    @Override
    public void tick() {
        // motion for the particle
        if (this.hot && this.age > 0) {
            if (this.yo == this.y) {
                billowing = true;
                this.hasPhysics = false; // was hasCollision     Prevent motion being ignored due to vertical collision
                if (this.xd == 0 && this.zd == 0) {
                    Vector3d diff = Vector3d.atCenterOf(new BlockPos(this.x, this.y, this.z)).add(0.5, 0.5, 0.5).subtract(this.x, this.y, this.z);
                    this.xd = -diff.x * 0.1;
                    this.zd = -diff.z * 0.1;
                }
                this.xd *= 1.1;
                this.yd *= 0.9;
                this.zd *= 1.1;
            } else if (billowing) {this.yd *= 1.2;}
        }
        this.rotation = (this.rotationDirection * 0.1f) + this.rotation;
        super.tick();
    }

    @Override
    public void render(IVertexBuilder buffer, ActiveRenderInfo renderInfo, float partialTicks) {
        Vector3d projectedView = renderInfo.getPosition();
        float lerpedX = (float) (MathHelper.lerp(partialTicks, this.xo, this.x) - projectedView.x());
        float lerpedY = (float) (MathHelper.lerp(partialTicks, this.yo, this.y) - projectedView.y());
        float lerpedZ = (float) (MathHelper.lerp(partialTicks, this.zo, this.z) - projectedView.z());

        int light = 15728880;// 15<<20 && 15<<4
        double ageMultiplier = 1 - Math.pow(age, 3) / Math.pow(this.lifetime, 3);

        for (int i = 0; i < CUBE.length / 4; i++) {
            // 10 faces to a blood particle
            for (int j = 0; j < 4; j++) {
                Vector3d vec = CUBE[i * 4 + j];
                vec = vec
                    .yRot(this.rotation + this.rotationOffsetYaw)
                    .xRot(this.rotation + this.rotationOffsetPitch)
                    .zRot(this.rotation + this.rotationOffsetRoll)
                    .scale(scale * ageMultiplier)
                    //.mul(1, 0.25 + 0.55 * (age/4f), 1) //scale non uniform based off age (maybe)
                    .add(lerpedX, lerpedY, lerpedZ);
                Vector3d normal = CUBE_NORMALS[i];
                buffer.vertex((float) vec.x, (float) vec.y, (float) vec.z, this.rCol + this.colorOffset, this.gCol, this.bCol, this.alpha, 0, 0, 0, light,(float) normal.x, (float) normal.y, (float) normal.z);
            }
        }
    }
    @Override public IParticleRenderType getRenderType() {return renderType;}

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite spriteSet;
        public Factory(IAnimatedSprite sprite) {this.spriteSet = sprite;}

        @Nullable
        @Override
        public Particle createParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            IchorParticle cauldronParticle = new IchorParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
            Random random = new Random();
            float colorOffset = (random.nextFloat() * 0.20f);
            cauldronParticle.setColor(0.05f + colorOffset, 0.0f, 0.0f);
            cauldronParticle.setAlpha(2.0f);
            cauldronParticle.pickSprite(this.spriteSet);
            return cauldronParticle;
        }
    }
}
