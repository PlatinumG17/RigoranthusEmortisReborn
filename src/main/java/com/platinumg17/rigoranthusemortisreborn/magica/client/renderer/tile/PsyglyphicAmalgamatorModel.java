package com.platinumg17.rigoranthusemortisreborn.magica.client.renderer.tile;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
//
//public class PsyglyphicAmalgamatorModel extends Model {
//    public final ModelRenderer bone;
//    private final ModelRenderer bone2;
//    private final ModelRenderer cube_r1;
//    private final ModelRenderer cube_r2;
//    private final ModelRenderer bone3;
//    private final ModelRenderer cube_r3;
//    private final ModelRenderer cube_r4;
//
//    public PsyglyphicAmalgamatorModel() {
//        super(RenderType::entityTranslucent);
//        texWidth = 64;
//        texHeight = 64;
//
//        bone = new ModelRenderer(this);
//        bone.setPos(-0.0022F, 11.95F, -0.0043F);
//        bone.texOffs(48, 32).addBox(-2.4978F, 0.2F, -1.7457F, 5.0F, 7.0F, 3.0F, 0.0F, false);
//        bone.texOffs(29, 3).addBox(-3.9978F, -3.95F, -3.9957F, 8.0F, 1.0F, 8.0F, 0.0F, false);
//        bone.texOffs(32, 18).addBox(-2.9978F, -1.8F, -2.9957F, 6.0F, 1.0F, 6.0F, 0.0F, false);
//        bone.texOffs(39, 48).addBox(-2.7478F, 1.05F, 0.7543F, 2.0F, 6.0F, 2.0F, 0.0F, false);
//        bone.texOffs(39, 43).addBox(-2.7478F, 1.05F, -2.7457F, 2.0F, 6.0F, 2.0F, 0.0F, false);
//        bone.texOffs(13, 25).addBox(-2.8478F, 2.25F, -2.8457F, 0.0F, 4.0F, 0.0F, 0.0F, false);
//        bone.texOffs(13, 23).addBox(2.0772F, 2.25F, -2.8457F, 0.0F, 4.0F, 0.0F, 0.0F, false);
//        bone.texOffs(13, 23).addBox(2.0772F, 2.25F, 2.0793F, 0.0F, 4.0F, 0.0F, 0.0F, false);
//        bone.texOffs(13, 25).addBox(-2.8478F, 2.25F, 2.0793F, 0.0F, 4.0F, 0.0F, 0.0F, false);
//        bone.texOffs(39, 43).addBox(0.7522F, 1.05F, -2.7457F, 2.0F, 6.0F, 2.0F, 0.0F, false);
//        bone.texOffs(39, 45).addBox(0.7522F, 1.05F, 0.7543F, 2.0F, 6.0F, 2.0F, 0.0F, false);
//        bone.texOffs(18, 43).addBox(-3.0978F, -2.45F, -3.4957F, 6.0F, 0.0F, 0.0F, 0.0F, false);
//        bone.texOffs(2, 16).addBox(-3.0978F, -2.45F, 3.1543F, 6.0F, 0.0F, 0.0F, 0.0F, false);
//        bone.texOffs(2, 5).addBox(3.2022F, -2.45F, -3.0957F, 0.0F, 0.0F, 6.0F, 0.0F, false);
//        bone.texOffs(2, 6).addBox(-3.4978F, -2.45F, -3.0957F, 0.0F, 0.0F, 6.0F, 0.0F, false);
//        bone.texOffs(10, 27).addBox(-4.0978F, -4.05F, -4.0957F, 1.0F, 1.0F, 1.0F, 0.0F, false);
//        bone.texOffs(33, 58).addBox(-3.5978F, -2.85F, -3.5957F, 0.0F, 0.0F, 0.0F, 0.0F, true);
//        bone.texOffs(14, 27).addBox(2.6022F, -4.05F, -4.0957F, 1.0F, 1.0F, 1.0F, 0.0F, false);
//        bone.texOffs(36, 57).addBox(3.1022F, -2.85F, -3.5957F, 0.0F, 0.0F, 0.0F, 0.0F, true);
//        bone.texOffs(14, 27).addBox(2.6022F, -4.05F, 2.6043F, 1.0F, 1.0F, 1.0F, 0.0F, false);
//        bone.texOffs(34, 57).addBox(3.1022F, -2.85F, 3.1043F, 0.0F, 0.0F, 0.0F, 0.0F, false);
//        bone.texOffs(10, 27).addBox(-4.0978F, -4.05F, 2.6043F, 1.0F, 1.0F, 1.0F, 0.0F, false);
//        bone.texOffs(33, 58).addBox(-3.5978F, -2.85F, 3.1043F, 0.0F, 0.0F, 0.0F, 0.0F, false);
//
//        bone2 = new ModelRenderer(this);
//        bone2.setPos(4.2522F, 0.5088F, -6.5522F);
//        bone.addChild(bone2);
//        bone2.texOffs(29, 43).addBox(-7.0F, -1.3088F, 4.5566F, 5.0F, 1.0F, 4.0F, 0.0F, false);
//
//        cube_r1 = new ModelRenderer(this);
//        cube_r1.setPos(-8.0F, 0.0F, 8.0F);
//        bone2.addChild(cube_r1);
//        setRotationAngle(cube_r1, 0.3927F, 0.0F, 0.0F);
//        cube_r1.texOffs(37, 48).addBox(1.1F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
//        cube_r1.texOffs(33, 47).addBox(4.6F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
//
//        cube_r2 = new ModelRenderer(this);
//        cube_r2.setPos(-4.5F, 0.0F, 5.1131F);
//        bone2.addChild(cube_r2);
//        setRotationAngle(cube_r2, -0.3927F, 0.0F, 0.0F);
//        cube_r2.texOffs(32, 48).addBox(1.1F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
//        cube_r2.texOffs(35, 48).addBox(-2.4F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
//
//        bone3 = new ModelRenderer(this);
//        bone3.setPos(6.0022F, 10.747F, -7.9957F);
//        bone.addChild(bone3);
//        bone3.texOffs(35, 20).addBox(-8.75F, -2.847F, 6.0F, 5.0F, 1.0F, 4.0F, 0.0F, false);
//
//        cube_r3 = new ModelRenderer(this);
//        cube_r3.setPos(-9.75F, -0.1558F, 9.4434F);
//        bone3.addChild(cube_r3);
//        setRotationAngle(cube_r3, -0.3927F, 0.0F, 0.0F);
//        cube_r3.texOffs(37, 48).addBox(1.1F, -3.7716F, -2.1481F, 1.0F, 2.0F, 2.0F, 0.0F, false);
//        cube_r3.texOffs(33, 47).addBox(4.6F, -3.7716F, -2.1481F, 1.0F, 2.0F, 2.0F, 0.0F, false);
//
//        cube_r4 = new ModelRenderer(this);
//        cube_r4.setPos(-6.25F, -0.1558F, 6.5566F);
//        bone3.addChild(cube_r4);
//        setRotationAngle(cube_r4, 0.3927F, 0.0F, 0.0F);
//        cube_r4.texOffs(32, 47).addBox(1.1F, -3.7716F, 0.1481F, 1.0F, 2.0F, 2.0F, 0.0F, false);
//        cube_r4.texOffs(35, 48).addBox(-2.4F, -3.7716F, 0.1481F, 1.0F, 2.0F, 2.0F, 0.0F, false);
//    }
//
////    public final ModelRenderer frame_all;
////    private final ModelRenderer cap_top;
////    public final ModelRenderer cap_bot;
////    public final ModelRenderer frame_top;
////    private final ModelRenderer frame_top1;
////    private final ModelRenderer frame_top2;
////    private final ModelRenderer frame_top3;
////    private final ModelRenderer frame_top4;
////    public final ModelRenderer frame_bot;
////    private final ModelRenderer frame_bot1;
////    private final ModelRenderer frame_bot2;
////    private final ModelRenderer frame_bot3;
////    private final ModelRenderer frame_bot4;
////
////    public PsyglyphicAmalgamatorModel() {
////        super(RenderType::entityCutout);
////        texWidth = 32;
////        texHeight = 32;
////
////        frame_all = new ModelRenderer(this);
////        frame_all.setPos(0.0F, 1.0F, 0.0F);
////
////        cap_top = new ModelRenderer(this);
////        cap_top.setPos(0.0F, 0.0F, 0.0F);
////        frame_all.addChild(cap_top);
////        cap_top.texOffs(0, 0).addBox(-2.5F, -6.5F, -2.5F, 5.0F, 1.0F, 5.0F, 0.0F, false);
////        cap_top.texOffs(0, 6).addBox(-1.5F, -5.5F, -1.5F, 3.0F, 2.0F, 3.0F, 0.0F, false);
////
////        cap_bot = new ModelRenderer(this);
////        cap_bot.setPos(0.0F, 0.0F, 0.0F);
////        frame_all.addChild(cap_bot);
////        setRotationAngle(cap_bot, 0.0F, 0.0F, -3.1416F);
////        cap_bot.texOffs(0, 0).addBox(-2.5F, -6.5F, -2.5F, 5.0F, 1.0F, 5.0F, 0.0F, false);
////        cap_bot.texOffs(0, 6).addBox(-1.5F, -5.5F, -1.5F, 3.0F, 2.0F, 3.0F, 0.0F, false);
////
////        frame_top = new ModelRenderer(this);
////        frame_top.setPos(0.0F, 0.0F, 0.0F);
////        frame_all.addChild(frame_top);
////
////        frame_top1 = new ModelRenderer(this);
////        frame_top1.setPos(0.0F, -2.5F, 0.0F);
////        frame_top.addChild(frame_top1);
////        setRotationAngle(frame_top1, 0.0F, -1.5708F, 0.0F);
////        frame_top1.texOffs(10, 13).addBox(-4.5F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
////        frame_top1.texOffs(15, 0).addBox(-3.5F, -1.0F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);
////        frame_top1.texOffs(12, 8).addBox(-6.5F, 1.0F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F, false);
////        frame_top1.texOffs(6, 13).addBox(-6.5F, -3.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
////        frame_top1.texOffs(0, 11).addBox(-5.5F, -3.0F, -0.5F, 4.0F, 1.0F, 1.0F, 0.0F, false);
////
////        frame_top2 = new ModelRenderer(this);
////        frame_top2.setPos(0.0F, -2.5F, 0.0F);
////        frame_top.addChild(frame_top2);
////        setRotationAngle(frame_top2, 0.0F, 3.1416F, 0.0F);
////        frame_top2.texOffs(10, 13).addBox(-4.5F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
////        frame_top2.texOffs(15, 0).addBox(-3.5F, -1.0F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);
////        frame_top2.texOffs(12, 8).addBox(-6.5F, 1.0F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F, false);
////        frame_top2.texOffs(6, 13).addBox(-6.5F, -3.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
////        frame_top2.texOffs(0, 11).addBox(-5.5F, -3.0F, -0.5F, 4.0F, 1.0F, 1.0F, 0.0F, false);
////
////        frame_top3 = new ModelRenderer(this);
////        frame_top3.setPos(0.0F, -2.5F, 0.0F);
////        frame_top.addChild(frame_top3);
////        setRotationAngle(frame_top3, 0.0F, 1.5708F, 0.0F);
////        frame_top3.texOffs(10, 13).addBox(-4.5F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
////        frame_top3.texOffs(15, 0).addBox(-3.5F, -1.0F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);
////        frame_top3.texOffs(12, 8).addBox(-6.5F, 1.0F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F, false);
////        frame_top3.texOffs(6, 13).addBox(-6.5F, -3.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
////        frame_top3.texOffs(0, 11).addBox(-5.5F, -3.0F, -0.5F, 4.0F, 1.0F, 1.0F, 0.0F, false);
////
////        frame_top4 = new ModelRenderer(this);
////        frame_top4.setPos(0.0F, -2.5F, 0.0F);
////        frame_top.addChild(frame_top4);
////        setRotationAngle(frame_top4, 0.0F, 0.0F, 0.0F);
////        frame_top4.texOffs(10, 13).addBox(-4.5F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
////        frame_top4.texOffs(15, 0).addBox(-3.5F, -1.0F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);
////        frame_top4.texOffs(12, 8).addBox(-6.5F, 1.0F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F, false);
////        frame_top4.texOffs(6, 13).addBox(-6.5F, -3.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
////        frame_top4.texOffs(0, 11).addBox(-5.5F, -3.0F, -0.5F, 4.0F, 1.0F, 1.0F, 0.0F, false);
////
////        frame_bot = new ModelRenderer(this);
////        frame_bot.setPos(0.0F, 0.0F, 0.0F);
////        frame_all.addChild(frame_bot);
////        setRotationAngle(frame_bot, 3.1416F, 0.0F, 0.0F);
////
////        frame_bot1 = new ModelRenderer(this);
////        frame_bot1.setPos(0.0F, -2.5F, 0.0F);
////        frame_bot.addChild(frame_bot1);
////        setRotationAngle(frame_bot1, 0.0F, 1.5708F, 0.0F);
////        frame_bot1.texOffs(0, 13).addBox(-4.5F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
////        frame_bot1.texOffs(15, 15).addBox(-3.5F, -1.0F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);
////        frame_bot1.texOffs(10, 11).addBox(-6.5F, 1.0F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F, false);
////        frame_bot1.texOffs(0, 0).addBox(-6.5F, -3.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
////        frame_bot1.texOffs(9, 6).addBox(-5.5F, -3.0F, -0.5F, 4.0F, 1.0F, 1.0F, 0.0F, false);
////
////        frame_bot2 = new ModelRenderer(this);
////        frame_bot2.setPos(0.0F, -2.5F, 0.0F);
////        frame_bot.addChild(frame_bot2);
////        setRotationAngle(frame_bot2, 0.0F, -3.1416F, 0.0F);
////        frame_bot2.texOffs(0, 13).addBox(-4.5F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
////        frame_bot2.texOffs(15, 15).addBox(-3.5F, -1.0F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);
////        frame_bot2.texOffs(10, 11).addBox(-6.5F, 1.0F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F, false);
////        frame_bot2.texOffs(0, 0).addBox(-6.5F, -3.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
////        frame_bot2.texOffs(9, 6).addBox(-5.5F, -3.0F, -0.5F, 4.0F, 1.0F, 1.0F, 0.0F, false);
////
////        frame_bot3 = new ModelRenderer(this);
////        frame_bot3.setPos(0.0F, -2.5F, 0.0F);
////        frame_bot.addChild(frame_bot3);
////        setRotationAngle(frame_bot3, 0.0F, -1.5708F, 0.0F);
////        frame_bot3.texOffs(0, 13).addBox(-4.5F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
////        frame_bot3.texOffs(15, 15).addBox(-3.5F, -1.0F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);
////        frame_bot3.texOffs(10, 11).addBox(-6.5F, 1.0F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F, false);
////        frame_bot3.texOffs(0, 0).addBox(-6.5F, -3.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
////        frame_bot3.texOffs(9, 6).addBox(-5.5F, -3.0F, -0.5F, 4.0F, 1.0F, 1.0F, 0.0F, false);
////
////        frame_bot4 = new ModelRenderer(this);
////        frame_bot4.setPos(0.0F, -2.5F, 0.0F);
////        frame_bot.addChild(frame_bot4);
////        setRotationAngle(frame_bot4, 0.0F, 0.0F, 0.0F);
////        frame_bot4.texOffs(0, 13).addBox(-4.5F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
////        frame_bot4.texOffs(15, 15).addBox(-3.5F, -1.0F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);
////        frame_bot4.texOffs(10, 11).addBox(-6.5F, 1.0F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F, false);
////        frame_bot4.texOffs(0, 0).addBox(-6.5F, -3.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
////        frame_bot4.texOffs(9, 6).addBox(-5.5F, -3.0F, -0.5F, 4.0F, 1.0F, 1.0F, 0.0F, false);
////    }
//
//    @Override
//    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
//        bone.render(matrixStack, buffer, packedLight, packedOverlay);
//    }
//
//    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
//        modelRenderer.xRot = x;
//        modelRenderer.yRot = y;
//        modelRenderer.zRot = z;
//    }
//}