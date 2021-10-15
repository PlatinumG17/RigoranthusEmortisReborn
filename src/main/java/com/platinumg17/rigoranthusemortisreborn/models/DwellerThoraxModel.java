package com.platinumg17.rigoranthusemortisreborn.models;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;

public class DwellerThoraxModel <T extends LivingEntity> extends ArmorModel<T> { //extends EntityModel<Entity> {
    // Made with Blockbench 3.9.2
    // Exported for Minecraft version 1.15 - 1.16 with MCP mappings
    // Paste this class into your mod and generate all required imports
    public DwellerThoraxModel(float modelSize, EquipmentSlotType slotType) {
        super(modelSize, slotType);

        final ModelRenderer Body;
        final ModelRenderer ribs;
        final ModelRenderer cube_r1_r1_r1;
        final ModelRenderer cube_r2_r1_r1;
        final ModelRenderer cube_r2_r2_r1;
        final ModelRenderer cube_r3_r1_r1;
        final ModelRenderer cube_r3_r2_r1;
        final ModelRenderer cube_r4_r1_r1;
        final ModelRenderer cube_r3_r3_r1;
        final ModelRenderer cube_r4_r2_r1;
        final ModelRenderer cube_r5_r1_r1;
        final ModelRenderer cube_r4_r3_r1;
        final ModelRenderer cube_r4_r4_r1;
        final ModelRenderer cube_r5_r2_r1;
        final ModelRenderer cube_r5_r3_r1;
        final ModelRenderer cube_r6_r1_r1;
        final ModelRenderer cube_r4_r5_r1;
        final ModelRenderer cube_r5_r4_r1;
        final ModelRenderer cube_r5_r5_r1;
        final ModelRenderer cube_r2_r3_r1;
        final ModelRenderer cube_r6_r2_r1;
        final ModelRenderer cube_r6_r3_r1;
        final ModelRenderer cube_r5_r6_r1;
        final ModelRenderer cube_r7_r1_r1;
        final ModelRenderer cube_r6_r4_r1;
        final ModelRenderer cube_r6_r5_r1;
        final ModelRenderer cube_r5_r7_r1;
        final ModelRenderer cube_r3_r4_r1;
        final ModelRenderer bone;
        final ModelRenderer cube_r14_r1_r1;
        final ModelRenderer cube_r7_r1_r2;
        final ModelRenderer cube_r4_r2_r2;
        final ModelRenderer cube_r1_r1_r2;
        final ModelRenderer right_back;
        final ModelRenderer cube_r19_r1;
        final ModelRenderer cube_r22_r1;
        final ModelRenderer cube_r18_r1;
        final ModelRenderer cube_r17_r1;
        final ModelRenderer cube_r3_r1;
        final ModelRenderer cube_r31_r1;
        final ModelRenderer cube_r23_r1;
        final ModelRenderer cube_r21_r1;
        final ModelRenderer cube_r20_r1;
        final ModelRenderer cube_r33_r1;
        final ModelRenderer cube_r5_r1;
        final ModelRenderer cube_r8_r1;
        final ModelRenderer cube_r24_r1;
        final ModelRenderer cube_r12_r1;
        final ModelRenderer cube_r11_r1;
        final ModelRenderer cube_r32_r3;
        final ModelRenderer cube_r33_r3_r1;
        final ModelRenderer left_back;
        final ModelRenderer cube_r20_r2;
        final ModelRenderer cube_r23_r2;
        final ModelRenderer cube_r19_r2;
        final ModelRenderer cube_r18_r2;
        final ModelRenderer cube_r4_r1;
        final ModelRenderer cube_r32_r1;
        final ModelRenderer cube_r32_r2;
        final ModelRenderer cube_r32_r2_r1;
        final ModelRenderer cube_r24_r2;
        final ModelRenderer cube_r22_r2;
        final ModelRenderer cube_r21_r2;
        final ModelRenderer cube_r34_r1;
        final ModelRenderer cube_r6_r1;
        final ModelRenderer cube_r9_r1;
        final ModelRenderer cube_r25_r1;
        final ModelRenderer cube_r13_r1;
        final ModelRenderer cube_r12_r2;

        //public DwellerThoraxModel() {
            texWidth = 64;
            texHeight = 64;

            Body = new ModelRenderer(this);
            Body.setPos(-0.2F, 3.1F, 1.0F);
            setRotationAngle(Body, 0.0873F, 0.0F, 0.0F);

            ribs = new ModelRenderer(this);
            ribs.setPos(0.3551F, 18.5101F, 16.6649F);
            Body.addChild(ribs);

            cube_r1_r1_r1 = new ModelRenderer(this);
            cube_r1_r1_r1.setPos(6.6605F, -5.3532F, 0.8434F);
            ribs.addChild(cube_r1_r1_r1);
            setRotationAngle(cube_r1_r1_r1, 1.7292F, 0.4437F, 0.2487F);
            cube_r1_r1_r1.texOffs(0, 14).addBox(3.5476F, -21.5668F, 7.2188F, 1.0F, 3.0F, 1.0F, 0.0F, false);

            cube_r2_r1_r1 = new ModelRenderer(this);
            cube_r2_r1_r1.setPos(14.2822F, -2.8481F, -6.7779F);
            ribs.addChild(cube_r2_r1_r1);
            setRotationAngle(cube_r2_r1_r1, 1.8797F, 1.0833F, 0.455F);
            cube_r2_r1_r1.texOffs(15, 10).addBox(5.2807F, -18.965F, 7.2188F, 1.0F, 1.0F, 1.0F, 0.0F, false);

            cube_r2_r2_r1 = new ModelRenderer(this);
            cube_r2_r2_r1.setPos(6.805F, -3.6013F, 0.2336F);
            ribs.addChild(cube_r2_r2_r1);
            setRotationAngle(cube_r2_r2_r1, 1.5473F, 0.4416F, 0.0546F);
            cube_r2_r2_r1.texOffs(0, 14).addBox(4.0965F, -21.9721F, 7.6237F, 1.0F, 3.0F, 1.0F, 0.0F, true);

            cube_r3_r1_r1 = new ModelRenderer(this);
            cube_r3_r1_r1.setPos(15.1249F, -3.2396F, -8.1122F);
            ribs.addChild(cube_r3_r1_r1);
            setRotationAngle(cube_r3_r1_r1, 1.5199F, 1.1394F, 0.0184F);
            cube_r3_r1_r1.texOffs(15, 10).addBox(5.9665F, -18.6333F, 7.6237F, 1.0F, 1.0F, 1.0F, 0.0F, false);

            cube_r3_r2_r1 = new ModelRenderer(this);
            cube_r3_r2_r1.setPos(8.1522F, -3.3746F, -0.5416F);
            ribs.addChild(cube_r3_r2_r1);
            setRotationAngle(cube_r3_r2_r1, 1.444F, 0.4684F, -0.1412F);
            cube_r3_r2_r1.texOffs(4, 14).addBox(4.7802F, -21.3601F, 8.1926F, 1.0F, 3.0F, 1.0F, 0.0F, false);

            cube_r4_r1_r1 = new ModelRenderer(this);
            cube_r4_r1_r1.setPos(16.1229F, -5.0115F, -9.0384F);
            ribs.addChild(cube_r4_r1_r1);
            setRotationAngle(cube_r4_r1_r1, 1.2875F, 1.1549F, -0.344F);
            cube_r4_r1_r1.texOffs(15, 10).addBox(6.131F, -17.7481F, 8.1926F, 1.0F, 1.0F, 1.0F, 0.0F, false);

            cube_r3_r3_r1 = new ModelRenderer(this);
            cube_r3_r3_r1.setPos(-7.1154F, -3.6014F, 0.2344F);
            ribs.addChild(cube_r3_r3_r1);
            setRotationAngle(cube_r3_r3_r1, 1.5473F, -0.4416F, -0.0546F);
            cube_r3_r3_r1.texOffs(0, 14).addBox(-5.2984F, -21.9721F, 7.6237F, 1.0F, 3.0F, 1.0F, 0.0F, false);

            cube_r4_r2_r1 = new ModelRenderer(this);
            cube_r4_r2_r1.setPos(-15.5332F, -3.2312F, -8.0143F);
            ribs.addChild(cube_r4_r2_r1);
            setRotationAngle(cube_r4_r2_r1, 1.5199F, -1.1394F, -0.0184F);
            cube_r4_r2_r1.texOffs(15, 10).addBox(-7.1684F, -18.6333F, 7.6237F, 1.0F, 1.0F, 1.0F, 0.0F, false);

            cube_r5_r1_r1 = new ModelRenderer(this);
            cube_r5_r1_r1.setPos(-16.5349F, -5.0091F, -8.944F);
            ribs.addChild(cube_r5_r1_r1);
            setRotationAngle(cube_r5_r1_r1, 1.2875F, -1.1549F, 0.344F);
            cube_r5_r1_r1.texOffs(15, 10).addBox(-7.3329F, -17.7481F, 8.1926F, 1.0F, 1.0F, 1.0F, 0.0F, false);

            cube_r4_r3_r1 = new ModelRenderer(this);
            cube_r4_r3_r1.setPos(-8.4627F, -3.3743F, -0.5407F);
            ribs.addChild(cube_r4_r3_r1);
            setRotationAngle(cube_r4_r3_r1, 1.444F, -0.4684F, 0.1412F);
            cube_r4_r3_r1.texOffs(4, 14).addBox(-5.9821F, -21.3601F, 8.1926F, 1.0F, 3.0F, 1.0F, 0.0F, false);

            cube_r4_r4_r1 = new ModelRenderer(this);
            cube_r4_r4_r1.setPos(9.7073F, -4.0321F, -1.2725F);
            ribs.addChild(cube_r4_r4_r1);
            setRotationAngle(cube_r4_r4_r1, 1.3448F, 0.473F, -0.3904F);
            cube_r4_r4_r1.texOffs(8, 14).addBox(5.7881F, -20.7612F, 8.4632F, 1.0F, 2.0F, 1.0F, 0.0F, true);

            cube_r5_r2_r1 = new ModelRenderer(this);
            cube_r5_r2_r1.setPos(17.6142F, -8.9174F, -13.3406F);
            ribs.addChild(cube_r5_r2_r1);
            setRotationAngle(cube_r5_r2_r1, 0.6963F, 1.3078F, -1.1433F);
            cube_r5_r2_r1.texOffs(15, 14).addBox(5.9422F, -14.9908F, 8.4632F, 1.0F, 1.0F, 1.0F, 0.0F, false);

            cube_r5_r3_r1 = new ModelRenderer(this);
            cube_r5_r3_r1.setPos(8.737F, -10.3427F, 0.2872F);
            ribs.addChild(cube_r5_r3_r1);
            setRotationAngle(cube_r5_r3_r1, 2.0774F, 0.4798F, 0.3487F);
            cube_r5_r3_r1.texOffs(8, 14).addBox(3.4862F, -20.1263F, 5.2683F, 1.0F, 2.0F, 1.0F, 0.0F, false);

            cube_r6_r1_r1 = new ModelRenderer(this);
            cube_r6_r1_r1.setPos(14.7272F, -7.02F, -5.4193F);
            ribs.addChild(cube_r6_r1_r1);
            setRotationAngle(cube_r6_r1_r1, 2.3441F, 0.907F, 0.7534F);
            cube_r6_r1_r1.texOffs(15, 10).addBox(4.362F, -18.5832F, 5.2683F, 1.0F, 1.0F, 1.0F, 0.0F, false);

            cube_r4_r5_r1 = new ModelRenderer(this);
            cube_r4_r5_r1.setPos(8.1319F, -7.2769F, 0.5121F);
            ribs.addChild(cube_r4_r5_r1);
            setRotationAngle(cube_r4_r5_r1, 1.858F, 0.5F, 0.2561F);
            cube_r4_r5_r1.texOffs(12, 11).addBox(3.6319F, -20.7919F, 6.0323F, 1.0F, 3.0F, 1.0F, 0.0F, false);

            cube_r5_r4_r1 = new ModelRenderer(this);
            cube_r5_r4_r1.setPos(13.9527F, -5.2129F, -4.866F);
            ribs.addChild(cube_r5_r4_r1);
            setRotationAngle(cube_r5_r4_r1, 2.0128F, 0.9508F, 0.483F);
            cube_r5_r4_r1.texOffs(15, 10).addBox(4.78F, -19.3513F, 6.0323F, 1.0F, 1.0F, 1.0F, 0.0F, false);

            cube_r5_r5_r1 = new ModelRenderer(this);
            cube_r5_r5_r1.setPos(9.6631F, -3.1881F, -1.5954F);
            ribs.addChild(cube_r5_r5_r1);
            setRotationAngle(cube_r5_r5_r1, 1.4645F, 0.6082F, -0.0609F);
            cube_r5_r5_r1.texOffs(15, 10).addBox(3.7471F, -20.1598F, 11.7822F, 1.0F, 1.0F, 1.0F, 0.0F, false);

            cube_r2_r3_r1 = new ModelRenderer(this);
            cube_r2_r3_r1.setPos(-6.9697F, -5.3512F, 0.8396F);
            ribs.addChild(cube_r2_r3_r1);
            setRotationAngle(cube_r2_r3_r1, 1.7292F, -0.4437F, -0.2487F);
            cube_r2_r3_r1.texOffs(0, 14).addBox(-4.7481F, -21.5619F, 7.2207F, 1.0F, 3.0F, 1.0F, 0.0F, true);

            cube_r6_r2_r1 = new ModelRenderer(this);
            cube_r6_r2_r1.setPos(-9.9717F, -3.1886F, -1.6005F);
            ribs.addChild(cube_r6_r2_r1);
            setRotationAngle(cube_r6_r2_r1, 1.4645F, -0.6082F, 0.0609F);
            cube_r6_r2_r1.texOffs(15, 10).addBox(-4.9471F, -20.1535F, 11.7822F, 1.0F, 1.0F, 1.0F, 0.0F, false);

            cube_r6_r3_r1 = new ModelRenderer(this);
            cube_r6_r3_r1.setPos(-14.3254F, -5.22F, -4.8065F);
            ribs.addChild(cube_r6_r3_r1);
            setRotationAngle(cube_r6_r3_r1, 2.0128F, -0.9508F, -0.483F);
            cube_r6_r3_r1.texOffs(15, 10).addBox(-5.9766F, -19.342F, 6.0335F, 1.0F, 1.0F, 1.0F, 0.0F, false);

            cube_r5_r6_r1 = new ModelRenderer(this);
            cube_r5_r6_r1.setPos(-8.4408F, -7.2749F, 0.5078F);
            ribs.addChild(cube_r5_r6_r1);
            setRotationAngle(cube_r5_r6_r1, 1.858F, -0.5F, -0.2561F);
            cube_r5_r6_r1.texOffs(12, 11).addBox(-4.8322F, -20.7863F, 6.0335F, 1.0F, 3.0F, 1.0F, 0.0F, true);

            cube_r7_r1_r1 = new ModelRenderer(this);
            cube_r7_r1_r1.setPos(-15.1093F, -7.0399F, -5.3612F);
            ribs.addChild(cube_r7_r1_r1);
            setRotationAngle(cube_r7_r1_r1, 2.3441F, -0.907F, -0.7534F);
            cube_r7_r1_r1.texOffs(15, 10).addBox(-5.5579F, -18.5735F, 5.2692F, 1.0F, 1.0F, 1.0F, 0.0F, false);

            cube_r6_r4_r1 = new ModelRenderer(this);
            cube_r6_r4_r1.setPos(-9.0458F, -10.34F, 0.2831F);
            ribs.addChild(cube_r6_r4_r1);
            setRotationAngle(cube_r6_r4_r1, 2.0774F, -0.4798F, -0.3487F);
            cube_r6_r4_r1.texOffs(12, 15).addBox(-4.6863F, -20.1204F, 5.2692F, 1.0F, 2.0F, 1.0F, 0.0F, false);

            cube_r6_r5_r1 = new ModelRenderer(this);
            cube_r6_r5_r1.setPos(-18.0595F, -8.94F, -13.2473F);
            ribs.addChild(cube_r6_r5_r1);
            setRotationAngle(cube_r6_r5_r1, 0.6963F, -1.3078F, 1.1433F);
            cube_r6_r5_r1.texOffs(15, 10).addBox(-7.1344F, -14.9804F, 8.4602F, 1.0F, 1.0F, 1.0F, 0.0F, false);

            cube_r5_r7_r1 = new ModelRenderer(this);
            cube_r5_r7_r1.setPos(-10.016F, -4.0352F, -1.2766F);
            ribs.addChild(cube_r5_r7_r1);
            setRotationAngle(cube_r5_r7_r1, 1.3448F, -0.473F, 0.3904F);
            cube_r5_r7_r1.texOffs(8, 14).addBox(-6.9881F, -20.7558F, 8.4602F, 1.0F, 2.0F, 1.0F, 0.0F, true);

            cube_r3_r4_r1 = new ModelRenderer(this);
            cube_r3_r4_r1.setPos(-14.6781F, -2.8429F, -6.6952F);
            ribs.addChild(cube_r3_r4_r1);
            setRotationAngle(cube_r3_r4_r1, 1.8797F, -1.0833F, -0.455F);
            cube_r3_r4_r1.texOffs(15, 10).addBox(-6.4762F, -18.9553F, 7.2207F, 1.0F, 1.0F, 1.0F, 0.0F, true);

            bone = new ModelRenderer(this);
            bone.setPos(0.2F, 4.1F, -0.5F);
            Body.addChild(bone);
            setRotationAngle(bone, 0.0524F, 0.0F, 0.0F);

            cube_r14_r1_r1 = new ModelRenderer(this);
            cube_r14_r1_r1.setPos(0.0883F, 32.13F, 23.7824F);
            bone.addChild(cube_r14_r1_r1);
            setRotationAngle(cube_r14_r1_r1, 1.4377F, 0.0083F, -0.0024F);
            cube_r14_r1_r1.texOffs(0, 7).addBox(-0.5223F, -25.0583F, 21.1745F, 1.0F, 1.0F, 3.0F, 0.0F, false);

            cube_r7_r1_r2 = new ModelRenderer(this);
            cube_r7_r1_r2.setPos(0.1519F, 20.3244F, 40.485F);
            bone.addChild(cube_r7_r1_r2);
            setRotationAngle(cube_r7_r1_r2, 1.8741F, 0.0086F, 0.0014F);
            cube_r7_r1_r2.texOffs(8, 0).addBox(-0.5492F, -28.8427F, 31.3443F, 1.0F, 1.0F, 3.0F, 0.0F, false);

            cube_r4_r2_r2 = new ModelRenderer(this);
            cube_r4_r2_r2.setPos(0.0948F, 31.538F, 25.626F);
            bone.addChild(cube_r4_r2_r2);
            setRotationAngle(cube_r4_r2_r2, 1.4814F, 0.0085F, -0.002F);
            cube_r4_r2_r2.texOffs(0, 11).addBox(-0.5263F, -24.9194F, 28.8014F, 1.0F, 1.0F, 2.0F, 0.0F, false);

            cube_r1_r1_r2 = new ModelRenderer(this);
            cube_r1_r1_r2.setPos(0.0672F, 33.0747F, 18.2171F);
            bone.addChild(cube_r1_r1_r2);
            setRotationAngle(cube_r1_r1_r2, 1.3068F, 0.008F, -0.0034F);
            cube_r1_r1_r2.texOffs(0, 0).addBox(-0.5204F, -22.8073F, 21.6261F, 1.0F, 1.0F, 6.0F, 0.0F, false);

            right_back = new ModelRenderer(this);
            right_back.setPos(-11.9115F, 11.7889F, 14.0609F);
            bone.addChild(right_back);

            cube_r19_r1 = new ModelRenderer(this);
            cube_r19_r1.setPos(0.5754F, 0.8963F, 0.0367F);
            right_back.addChild(cube_r19_r1);
            setRotationAngle(cube_r19_r1, 1.4512F, -0.7879F, -0.04F);
            cube_r19_r1.texOffs(14, 8).addBox(-0.9463F, -17.141F, 9.8436F, 2.0F, 1.0F, 1.0F, 0.0F, true);

            cube_r22_r1 = new ModelRenderer(this);
            cube_r22_r1.setPos(0.0F, 0.0F, 0.0F);
            right_back.addChild(cube_r22_r1);
            setRotationAngle(cube_r22_r1, 1.4725F, -0.7996F, 0.0073F);
            cube_r22_r1.texOffs(15, 10).addBox(-1.0601F, -17.3189F, 3.9726F, 1.0F, 1.0F, 1.0F, 0.0F, false);

            cube_r18_r1 = new ModelRenderer(this);
            cube_r18_r1.setPos(13.6131F, 9.5008F, -19.1057F);
            right_back.addChild(cube_r18_r1);
            setRotationAngle(cube_r18_r1, -0.1855F, -0.3435F, 0.0631F);
            cube_r18_r1.texOffs(6, 11).addBox(-2.5853F, -16.4887F, 4.4851F, 2.0F, 2.0F, 1.0F, 0.0F, false);

            cube_r17_r1 = new ModelRenderer(this);
            cube_r17_r1.setPos(12.3251F, 10.5721F, -16.4886F);
            right_back.addChild(cube_r17_r1);
            setRotationAngle(cube_r17_r1, -0.0169F, -0.342F, 0.0791F);
            cube_r17_r1.texOffs(13, 0).addBox(-2.5755F, -17.338F, 4.373F, 2.0F, 1.0F, 1.0F, 0.0F, false);

            cube_r3_r1 = new ModelRenderer(this);
            cube_r3_r1.setPos(14.3343F, 8.9313F, -20.037F);
            right_back.addChild(cube_r3_r1);
            setRotationAngle(cube_r3_r1, -0.3238F, -0.5788F, 0.2075F);
            cube_r3_r1.texOffs(8, 7).addBox(-2.4824F, -22.1334F, 4.5538F, 2.0F, 3.0F, 1.0F, 0.0F, false);

            cube_r31_r1 = new ModelRenderer(this);
            cube_r31_r1.setPos(10.4901F, -7.7667F, -11.8568F);
            right_back.addChild(cube_r31_r1);
            setRotationAngle(cube_r31_r1, -0.2312F, -0.3922F, 0.0108F);
            cube_r31_r1.texOffs(6, 11).addBox(-1.0F, -1.0F, -0.6F, 2.0F, 2.0F, 1.0F, 0.0F, false);

            cube_r23_r1 = new ModelRenderer(this);
            cube_r23_r1.setPos(0.6885F, 1.5407F, -0.29F);
            right_back.addChild(cube_r23_r1);
            setRotationAngle(cube_r23_r1, 1.4148F, -0.8F, -0.0448F);
            cube_r23_r1.texOffs(15, 10).addBox(-0.8589F, -17.3422F, 5.4654F, 1.0F, 1.0F, 1.0F, 0.0F, false);

            cube_r21_r1 = new ModelRenderer(this);
            cube_r21_r1.setPos(0.5369F, 1.5426F, -0.4449F);
            right_back.addChild(cube_r21_r1);
            setRotationAngle(cube_r21_r1, 1.371F, -0.7923F, 0.0165F);
            cube_r21_r1.texOffs(15, 10).addBox(-0.6093F, -17.3422F, 6.8675F, 1.0F, 1.0F, 1.0F, 0.0F, false);

            cube_r20_r1 = new ModelRenderer(this);
            cube_r20_r1.setPos(0.862F, 0.8497F, 0.3401F);
            right_back.addChild(cube_r20_r1);
            setRotationAngle(cube_r20_r1, 1.4213F, -0.7473F, 0.0068F);
            cube_r20_r1.texOffs(14, 8).addBox(-1.5687F, -17.1945F, 8.3579F, 2.0F, 1.0F, 1.0F, 0.0F, true);

            cube_r33_r1 = new ModelRenderer(this);
            cube_r33_r1.setPos(14.3579F, 10.0101F, -17.6708F);
            right_back.addChild(cube_r33_r1);
            setRotationAngle(cube_r33_r1, -0.0929F, -0.3515F, -0.0117F);
            cube_r33_r1.texOffs(0, 0).addBox(-2.6281F, -23.9173F, 4.6377F, 2.0F, 4.0F, 1.0F, 0.0F, false);

            cube_r5_r1 = new ModelRenderer(this);
            cube_r5_r1.setPos(17.2079F, 8.9175F, -18.6465F);
            right_back.addChild(cube_r5_r1);
            setRotationAngle(cube_r5_r1, -0.2298F, -0.6199F, -0.014F);
            cube_r5_r1.texOffs(13, 0).addBox(-1.5537F, -23.2904F, 4.3634F, 2.0F, 1.0F, 1.0F, 0.0F, false);

            cube_r8_r1 = new ModelRenderer(this);
            cube_r8_r1.setPos(14.1759F, 11.062F, -14.2091F);
            right_back.addChild(cube_r8_r1);
            setRotationAngle(cube_r8_r1, 0.0971F, -0.6057F, -0.0225F);
            cube_r8_r1.texOffs(13, 6).addBox(-1.5468F, -23.9987F, 6.2628F, 2.0F, 1.0F, 1.0F, 0.0F, false);

            cube_r24_r1 = new ModelRenderer(this);
            cube_r24_r1.setPos(-0.5021F, -3.4728F, 0.8843F);
            right_back.addChild(cube_r24_r1);
            setRotationAngle(cube_r24_r1, 1.7315F, -0.7929F, -0.0718F);
            cube_r24_r1.texOffs(15, 10).addBox(0.1769F, -15.6065F, 10.9678F, 1.0F, 1.0F, 1.0F, 0.0F, false);

            cube_r12_r1 = new ModelRenderer(this);
            cube_r12_r1.setPos(10.7569F, -14.0677F, -11.0692F);
            right_back.addChild(cube_r12_r1);
            setRotationAngle(cube_r12_r1, 0.2732F, -0.628F, -0.0278F);
            cube_r12_r1.texOffs(13, 0).addBox(-1.0F, -0.2F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);

            cube_r11_r1 = new ModelRenderer(this);
            cube_r11_r1.setPos(10.8558F, -14.5512F, -11.2036F);
            right_back.addChild(cube_r11_r1);
            setRotationAngle(cube_r11_r1, 0.4522F, -0.6493F, -0.0379F);
            cube_r11_r1.texOffs(13, 0).addBox(-1.0F, -0.6F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);

            cube_r32_r3 = new ModelRenderer(this);
            cube_r32_r3.setPos(10.4901F, -7.7667F, -11.8568F);
            right_back.addChild(cube_r32_r3);
            setRotationAngle(cube_r32_r3, -0.1003F, -0.3922F, 0.0108F);

            cube_r33_r3_r1 = new ModelRenderer(this);
            cube_r33_r3_r1.setPos(0.0F, -1.4809F, 0.0726F);
            cube_r32_r3.addChild(cube_r33_r3_r1);
            setRotationAngle(cube_r33_r3_r1, -0.0873F, 0.0087F, -0.0008F);
            cube_r33_r3_r1.texOffs(8, 4).addBox(-1.0F, -0.4F, -0.5167F, 2.0F, 1.0F, 1.0F, 0.0F, false);

            left_back = new ModelRenderer(this);
            left_back.setPos(11.5115F, 11.7889F, 14.0609F);
            bone.addChild(left_back);

            cube_r20_r2 = new ModelRenderer(this);
            cube_r20_r2.setPos(-0.1754F, 0.8963F, 0.0367F);
            left_back.addChild(cube_r20_r2);
            setRotationAngle(cube_r20_r2, 1.4512F, 0.7879F, 0.04F);
            cube_r20_r2.texOffs(14, 8).addBox(-1.3037F, -17.391F, 9.8436F, 2.0F, 1.0F, 1.0F, 0.0F, false);

            cube_r23_r2 = new ModelRenderer(this);
            cube_r23_r2.setPos(0.0F, 0.0F, 0.0F);
            left_back.addChild(cube_r23_r2);
            setRotationAngle(cube_r23_r2, 1.4725F, 0.7996F, -0.0073F);
            cube_r23_r2.texOffs(15, 10).addBox(0.0601F, -17.3189F, 3.9726F, 1.0F, 1.0F, 1.0F, 0.0F, true);

            cube_r19_r2 = new ModelRenderer(this);
            cube_r19_r2.setPos(-13.6131F, 9.5008F, -19.1057F);
            left_back.addChild(cube_r19_r2);
            setRotationAngle(cube_r19_r2, -0.1855F, 0.3435F, -0.0631F);
            cube_r19_r2.texOffs(6, 11).addBox(0.5853F, -16.4887F, 4.4851F, 2.0F, 2.0F, 1.0F, 0.0F, true);

            cube_r18_r2 = new ModelRenderer(this);
            cube_r18_r2.setPos(-12.3251F, 10.5721F, -16.4886F);
            left_back.addChild(cube_r18_r2);
            setRotationAngle(cube_r18_r2, -0.0169F, 0.342F, -0.0791F);
            cube_r18_r2.texOffs(13, 0).addBox(0.5755F, -17.338F, 4.373F, 2.0F, 1.0F, 1.0F, 0.0F, true);

            cube_r4_r1 = new ModelRenderer(this);
            cube_r4_r1.setPos(-14.3343F, 8.9313F, -20.037F);
            left_back.addChild(cube_r4_r1);
            setRotationAngle(cube_r4_r1, -0.3238F, 0.5788F, -0.2075F);
            cube_r4_r1.texOffs(8, 7).addBox(0.5824F, -22.1334F, 4.5538F, 2.0F, 3.0F, 1.0F, 0.0F, true);

            cube_r32_r1 = new ModelRenderer(this);
            cube_r32_r1.setPos(-10.4901F, -7.7667F, -11.8568F);
            left_back.addChild(cube_r32_r1);
            setRotationAngle(cube_r32_r1, -0.2312F, 0.3922F, -0.0108F);
            cube_r32_r1.texOffs(6, 11).addBox(-1.0F, -1.0F, -0.6F, 2.0F, 2.0F, 1.0F, 0.0F, true);

            cube_r32_r2 = new ModelRenderer(this);
            cube_r32_r2.setPos(-10.4901F, -7.7667F, -11.8568F);
            left_back.addChild(cube_r32_r2);
            setRotationAngle(cube_r32_r2, -0.1003F, 0.3922F, -0.0108F);

            cube_r32_r2_r1 = new ModelRenderer(this);
            cube_r32_r2_r1.setPos(0.0F, -1.4809F, 0.0726F);
            cube_r32_r2.addChild(cube_r32_r2_r1);
            setRotationAngle(cube_r32_r2_r1, -0.0873F, -0.0087F, 0.0008F);
            cube_r32_r2_r1.texOffs(8, 4).addBox(-1.0F, -0.4F, -0.5167F, 2.0F, 1.0F, 1.0F, 0.0F, true);

            cube_r24_r2 = new ModelRenderer(this);
            cube_r24_r2.setPos(-0.6885F, 1.5407F, -0.29F);
            left_back.addChild(cube_r24_r2);
            setRotationAngle(cube_r24_r2, 1.4148F, 0.8F, 0.0448F);
            cube_r24_r2.texOffs(15, 10).addBox(-0.1411F, -17.3422F, 5.4654F, 1.0F, 1.0F, 1.0F, 0.0F, true);

            cube_r22_r2 = new ModelRenderer(this);
            cube_r22_r2.setPos(-0.5369F, 1.5426F, -0.4449F);
            left_back.addChild(cube_r22_r2);
            setRotationAngle(cube_r22_r2, 1.371F, 0.7923F, -0.0165F);
            cube_r22_r2.texOffs(15, 10).addBox(-0.3907F, -17.3422F, 6.8675F, 1.0F, 1.0F, 1.0F, 0.0F, true);

            cube_r21_r2 = new ModelRenderer(this);
            cube_r21_r2.setPos(-0.862F, 0.8497F, 0.3401F);
            left_back.addChild(cube_r21_r2);
            setRotationAngle(cube_r21_r2, 1.4213F, 0.7473F, -0.0068F);
            cube_r21_r2.texOffs(14, 8).addBox(-0.4313F, -17.1945F, 8.3579F, 2.0F, 1.0F, 1.0F, 0.0F, false);

            cube_r34_r1 = new ModelRenderer(this);
            cube_r34_r1.setPos(-14.3579F, 10.0101F, -17.6708F);
            left_back.addChild(cube_r34_r1);
            setRotationAngle(cube_r34_r1, -0.0929F, 0.3515F, 0.0117F);
            cube_r34_r1.texOffs(0, 0).addBox(0.6281F, -23.9173F, 4.6377F, 2.0F, 4.0F, 1.0F, 0.0F, true);

            cube_r6_r1 = new ModelRenderer(this);
            cube_r6_r1.setPos(-17.2079F, 8.9175F, -18.6465F);
            left_back.addChild(cube_r6_r1);
            setRotationAngle(cube_r6_r1, -0.2298F, 0.6199F, 0.014F);
            cube_r6_r1.texOffs(13, 0).addBox(-0.4463F, -23.2904F, 4.3634F, 2.0F, 1.0F, 1.0F, 0.0F, true);

            cube_r9_r1 = new ModelRenderer(this);
            cube_r9_r1.setPos(-14.1759F, 11.062F, -14.2091F);
            left_back.addChild(cube_r9_r1);
            setRotationAngle(cube_r9_r1, 0.0971F, 0.6057F, 0.0225F);
            cube_r9_r1.texOffs(14, 4).addBox(-0.4532F, -23.9987F, 6.2628F, 2.0F, 1.0F, 1.0F, 0.0F, false);

            cube_r25_r1 = new ModelRenderer(this);
            cube_r25_r1.setPos(0.5021F, -3.4728F, 0.8843F);
            left_back.addChild(cube_r25_r1);
            setRotationAngle(cube_r25_r1, 1.7315F, 0.7929F, 0.0718F);
            cube_r25_r1.texOffs(15, 10).addBox(-1.1769F, -15.6065F, 10.9678F, 1.0F, 1.0F, 1.0F, 0.0F, true);

            cube_r13_r1 = new ModelRenderer(this);
            cube_r13_r1.setPos(-10.7569F, -14.0677F, -11.0692F);
            left_back.addChild(cube_r13_r1);
            setRotationAngle(cube_r13_r1, 0.2732F, 0.628F, 0.0278F);
            cube_r13_r1.texOffs(13, 6).addBox(-1.0F, -0.2F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, true);

            cube_r12_r2 = new ModelRenderer(this);
            cube_r12_r2.setPos(-10.8558F, -14.5512F, -11.2036F);
            left_back.addChild(cube_r12_r2);
            setRotationAngle(cube_r12_r2, 0.4522F, 0.6493F, 0.0379F);
            cube_r12_r2.texOffs(13, 0).addBox(-1.0F, -0.6F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, true);
        }

//        @Override
//        public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks,
//                                      float netHeadYaw, float headPitch) {
//        }

        @Override
        public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
                           float green, float blue, float alpha) {
            Body.render(matrixStack, buffer, packedLight, packedOverlay);
        }

        public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
            modelRenderer.xRot = x;
            modelRenderer.yRot = y;
            modelRenderer.zRot = z;
        }
    }