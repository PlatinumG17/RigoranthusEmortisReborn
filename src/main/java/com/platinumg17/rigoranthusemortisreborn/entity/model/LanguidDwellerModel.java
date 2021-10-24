package com.platinumg17.rigoranthusemortisreborn.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.platinumg17.rigoranthusemortisreborn.entity.mobs.LanguidDwellerEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class LanguidDwellerModel <T extends LanguidDwellerEntity> extends EntityModel<T> {
    private final ModelRenderer bone2; private final ModelRenderer whole_head; private final ModelRenderer jaw_rotation; private final ModelRenderer mandible;
    private final ModelRenderer cube_r238; private final ModelRenderer cube_r239; private final ModelRenderer cube_r240; private final ModelRenderer cube_r241;
    private final ModelRenderer cube_r242; private final ModelRenderer cube_r243; private final ModelRenderer cube_r244; private final ModelRenderer cube_r245;
    private final ModelRenderer cube_r246; private final ModelRenderer cube_r248; private final ModelRenderer cube_r249; private final ModelRenderer cube_r250;
    private final ModelRenderer cube_r252; private final ModelRenderer cube_r253; private final ModelRenderer cube_r254; private final ModelRenderer cube_r255;
    private final ModelRenderer cube_r256; private final ModelRenderer cube_r257; private final ModelRenderer cube_r258; private final ModelRenderer cube_r259;
    private final ModelRenderer cube_r260; private final ModelRenderer cube_r261; private final ModelRenderer cube_r262; private final ModelRenderer cube_r263;
    private final ModelRenderer cube_r264; private final ModelRenderer cube_r265; private final ModelRenderer cube_r266; private final ModelRenderer cube_r267;
    private final ModelRenderer cube_r268; private final ModelRenderer bottom_teeth; private final ModelRenderer cube_r269; private final ModelRenderer cube_r270;
    private final ModelRenderer cube_r271; private final ModelRenderer cube_r272; private final ModelRenderer upper_jaw; private final ModelRenderer cube_r273;
    private final ModelRenderer cube_r274; private final ModelRenderer cube_r275; private final ModelRenderer cube_r276; private final ModelRenderer cube_r277;
    private final ModelRenderer cube_r278; private final ModelRenderer cube_r279; private final ModelRenderer cube_r280; private final ModelRenderer cube_r281;
    private final ModelRenderer cube_r282; private final ModelRenderer cube_r283; private final ModelRenderer cube_r284; private final ModelRenderer cube_r285;
    private final ModelRenderer cube_r286; private final ModelRenderer cube_r287; private final ModelRenderer cube_r288; private final ModelRenderer cube_r289;
    private final ModelRenderer cube_r290; private final ModelRenderer cube_r291; private final ModelRenderer cube_r292; private final ModelRenderer cube_r293;
    private final ModelRenderer cube_r294; private final ModelRenderer cube_r295; private final ModelRenderer cube_r296; private final ModelRenderer cube_r297;
    private final ModelRenderer cube_r298; private final ModelRenderer cube_r299; private final ModelRenderer cube_r300; private final ModelRenderer cube_r301;
    private final ModelRenderer cube_r302; private final ModelRenderer cube_r303; private final ModelRenderer cube_r304; private final ModelRenderer cube_r305;
    private final ModelRenderer cube_r306; private final ModelRenderer cube_r307; private final ModelRenderer cube_r308; private final ModelRenderer cube_r309;
    private final ModelRenderer cube_r310; private final ModelRenderer top_teeth; private final ModelRenderer cube_r311; private final ModelRenderer cube_r312;
    private final ModelRenderer cube_r313; private final ModelRenderer cube_r314; private final ModelRenderer cube_r315; private final ModelRenderer cube_r316;
    private final ModelRenderer mouth; private final ModelRenderer cube_r317; private final ModelRenderer cube_r318; private final ModelRenderer head;
    private final ModelRenderer cube_r319; private final ModelRenderer cube_r320; private final ModelRenderer cube_r321; private final ModelRenderer cube_r322;
    private final ModelRenderer cube_r323; private final ModelRenderer cube_r324; private final ModelRenderer cube_r325; private final ModelRenderer cube_r326;
    private final ModelRenderer cube_r327; private final ModelRenderer cube_r328; private final ModelRenderer cube_r330; private final ModelRenderer cube_r331;
    private final ModelRenderer cube_r332; private final ModelRenderer cube_r333; private final ModelRenderer cube_r335; private final ModelRenderer cube_r336;
    private final ModelRenderer cube_r337; private final ModelRenderer cube_r338; private final ModelRenderer cube_r339; private final ModelRenderer cube_r340;
    private final ModelRenderer cube_r341; private final ModelRenderer cube_r342; private final ModelRenderer cube_r343; private final ModelRenderer cube_r344;
    private final ModelRenderer cube_r345; private final ModelRenderer cube_r346; private final ModelRenderer cube_r347; private final ModelRenderer cube_r348;
    private final ModelRenderer cube_r349; private final ModelRenderer cube_r350; private final ModelRenderer cube_r351; private final ModelRenderer cube_r352;
    private final ModelRenderer cube_r353; private final ModelRenderer cube_r354; private final ModelRenderer cube_r355; private final ModelRenderer cube_r356;
    private final ModelRenderer cube_r357; private final ModelRenderer cube_r358; private final ModelRenderer cube_r359; private final ModelRenderer cube_r360;
    private final ModelRenderer cube_r361; private final ModelRenderer cube_r362; private final ModelRenderer cube_r363; private final ModelRenderer cube_r364;
    private final ModelRenderer cube_r365; private final ModelRenderer upper_neck; private final ModelRenderer cube_r228; private final ModelRenderer cube_r229;
    private final ModelRenderer cube_r230; private final ModelRenderer cube_r231; private final ModelRenderer cube_r232; private final ModelRenderer cube_r233;
    private final ModelRenderer cube_r234; private final ModelRenderer cube_r235; private final ModelRenderer cube_r236; private final ModelRenderer cube_r237;
    private final ModelRenderer lower_neck; private final ModelRenderer cube_r217; private final ModelRenderer cube_r218; private final ModelRenderer cube_r219;
    private final ModelRenderer cube_r220; private final ModelRenderer cube_r221; private final ModelRenderer cube_r222; private final ModelRenderer cube_r223;
    private final ModelRenderer cube_r224; private final ModelRenderer cube_r225; private final ModelRenderer cube_r226; private final ModelRenderer cube_r227;
    private final ModelRenderer cervical_spine; private final ModelRenderer cube_r212; private final ModelRenderer cube_r213; private final ModelRenderer cube_r214;
    private final ModelRenderer thighs; private final ModelRenderer right_thigh; private final ModelRenderer cube_r196; private final ModelRenderer cube_r197;
    private final ModelRenderer cube_r198; private final ModelRenderer cube_r199; private final ModelRenderer cube_r200; private final ModelRenderer cube_r201;
    private final ModelRenderer cube_r202; private final ModelRenderer cube_r203; private final ModelRenderer left_thigh; private final ModelRenderer cube_r204;
    private final ModelRenderer cube_r205; private final ModelRenderer cube_r206; private final ModelRenderer cube_r207; private final ModelRenderer cube_r208;
    private final ModelRenderer cube_r209; private final ModelRenderer cube_r210; private final ModelRenderer cube_r211; private final ModelRenderer legs;
    private final ModelRenderer right_leg; private final ModelRenderer cube_r160; private final ModelRenderer cube_r161; private final ModelRenderer cube_r162;
    private final ModelRenderer cube_r163; private final ModelRenderer cube_r164; private final ModelRenderer cube_r165; private final ModelRenderer cube_r166;
    private final ModelRenderer cube_r167; private final ModelRenderer cube_r168; private final ModelRenderer cube_r169; private final ModelRenderer cube_r170;
    private final ModelRenderer cube_r171; private final ModelRenderer cube_r172; private final ModelRenderer cube_r173; private final ModelRenderer cube_r174;
    private final ModelRenderer cube_r175; private final ModelRenderer cube_r176; private final ModelRenderer cube_r177; private final ModelRenderer left_leg;
    private final ModelRenderer cube_r178; private final ModelRenderer cube_r179; private final ModelRenderer cube_r180; private final ModelRenderer cube_r181;
    private final ModelRenderer cube_r182; private final ModelRenderer cube_r183; private final ModelRenderer cube_r184; private final ModelRenderer cube_r185;
    private final ModelRenderer cube_r186; private final ModelRenderer cube_r187; private final ModelRenderer cube_r188; private final ModelRenderer cube_r189;
    private final ModelRenderer cube_r190; private final ModelRenderer cube_r191; private final ModelRenderer cube_r192; private final ModelRenderer cube_r193;
    private final ModelRenderer cube_r194; private final ModelRenderer cube_r195; private final ModelRenderer body; private final ModelRenderer abdomino_thoracic_region;
    private final ModelRenderer bone; private final ModelRenderer body_r8_r1; private final ModelRenderer body_r7_r1; private final ModelRenderer body_r6_r1;
    private final ModelRenderer body_r5_r1; private final ModelRenderer body_r4_r1; private final ModelRenderer body_r3_r1; private final ModelRenderer body_r2_r1;
    private final ModelRenderer body_r1_r1; private final ModelRenderer body_r6_r2; private final ModelRenderer body_r5_r2; private final ModelRenderer body_r6_r3;
    private final ModelRenderer body_r7_r2; private final ModelRenderer body_r10_r1; private final ModelRenderer body_r9_r1; private final ModelRenderer body_r10_r2;
    private final ModelRenderer body_r9_r2; private final ModelRenderer body_r13_r1; private final ModelRenderer body_r12_r1; private final ModelRenderer body_r3_r2;
    private final ModelRenderer body_r5_r3; private final ModelRenderer body_r6_r4; private final ModelRenderer body_r6_r5; private final ModelRenderer body_r7_r3;
    private final ModelRenderer body_r2_r2; private final ModelRenderer body_r3_r3; private final ModelRenderer cube_r52; private final ModelRenderer cube_r53;
    private final ModelRenderer cube_r54; private final ModelRenderer cube_r55; private final ModelRenderer cube_r56; private final ModelRenderer cube_r57;
    private final ModelRenderer cube_r58; private final ModelRenderer cube_r59; private final ModelRenderer cube_r60; private final ModelRenderer cube_r61;
    private final ModelRenderer cube_r62; private final ModelRenderer cube_r63; private final ModelRenderer cube_r64; private final ModelRenderer cube_r65;
    private final ModelRenderer cube_r66; private final ModelRenderer cube_r67; private final ModelRenderer cube_r68; private final ModelRenderer cube_r69;
    private final ModelRenderer cube_r70; private final ModelRenderer cube_r71; private final ModelRenderer cube_r72; private final ModelRenderer cube_r73;
    private final ModelRenderer cube_r74; private final ModelRenderer cube_r75; private final ModelRenderer cube_r76; private final ModelRenderer cube_r77;
    private final ModelRenderer cube_r78; private final ModelRenderer cube_r79; private final ModelRenderer cube_r80; private final ModelRenderer cube_r81;
    private final ModelRenderer cube_r82; private final ModelRenderer cube_r83; private final ModelRenderer cube_r84; private final ModelRenderer cube_r85;
    private final ModelRenderer cube_r86; private final ModelRenderer cube_r87; private final ModelRenderer body_r6_r6; private final ModelRenderer body_r5_r4;
    private final ModelRenderer ribcage; private final ModelRenderer cube_r3_r1; private final ModelRenderer cube_r5_r1; private final ModelRenderer cube_r6_r1;
    private final ModelRenderer cube_r6_r2; private final ModelRenderer cube_r7_r1; private final ModelRenderer cube_r5_r2; private final ModelRenderer cube_r6_r3;
    private final ModelRenderer cube_r6_r4; private final ModelRenderer cube_r2_r1; private final ModelRenderer cube_r5_r3; private final ModelRenderer cube_r5_r4;
    private final ModelRenderer cube_r4_r1; private final ModelRenderer cube_r6_r5; private final ModelRenderer cube_r5_r5; private final ModelRenderer cube_r5_r6;
    private final ModelRenderer cube_r4_r2; private final ModelRenderer cube_r4_r3; private final ModelRenderer cube_r5_r7; private final ModelRenderer cube_r4_r4;
    private final ModelRenderer cube_r3_r2; private final ModelRenderer cube_r4_r5; private final ModelRenderer cube_r3_r3; private final ModelRenderer cube_r3_r4;
    private final ModelRenderer cube_r2_r2; private final ModelRenderer cube_r2_r3; private final ModelRenderer cube_r1_r1; private final ModelRenderer spine;
    private final ModelRenderer cube_r88; private final ModelRenderer cube_r89; private final ModelRenderer cube_r90; private final ModelRenderer cube_r91;
    private final ModelRenderer cube_r92; private final ModelRenderer cube_r93; private final ModelRenderer cube_r94; private final ModelRenderer cube_r95;
    private final ModelRenderer cube_r96; private final ModelRenderer cube_r97; private final ModelRenderer cube_r98; private final ModelRenderer cube_r99;
    private final ModelRenderer cube_r100; private final ModelRenderer cube_r101; private final ModelRenderer cube_r102; private final ModelRenderer cube_r103;
    private final ModelRenderer cube_r104; private final ModelRenderer cube_r105; private final ModelRenderer cube_r106; private final ModelRenderer cube_r107;
    private final ModelRenderer cube_r108; private final ModelRenderer cube_r109; private final ModelRenderer cube_r110; private final ModelRenderer cube_r111;
    private final ModelRenderer cube_r112; private final ModelRenderer cube_r113; private final ModelRenderer cube_r114; private final ModelRenderer cube_r115;
    private final ModelRenderer cube_r116; private final ModelRenderer cube_r117; private final ModelRenderer cube_r118; private final ModelRenderer cube_r119;
    private final ModelRenderer cube_r120; private final ModelRenderer pelvis; private final ModelRenderer cube_r121; private final ModelRenderer cube_r122;
    private final ModelRenderer cube_r123; private final ModelRenderer cube_r124; private final ModelRenderer cube_r125; private final ModelRenderer cube_r126;
    private final ModelRenderer cube_r127; private final ModelRenderer cube_r128; private final ModelRenderer cube_r129; private final ModelRenderer cube_r130;
    private final ModelRenderer cube_r131; private final ModelRenderer cube_r132; private final ModelRenderer cube_r133; private final ModelRenderer cube_r134;
    private final ModelRenderer cube_r135; private final ModelRenderer cube_r136; private final ModelRenderer cube_r137; private final ModelRenderer cube_r138;
    private final ModelRenderer cube_r139; private final ModelRenderer cube_r140; private final ModelRenderer cube_r141; private final ModelRenderer cube_r142;
    private final ModelRenderer cube_r143; private final ModelRenderer cube_r144; private final ModelRenderer cube_r145; private final ModelRenderer cube_r146;
    private final ModelRenderer cube_r147; private final ModelRenderer cube_r148; private final ModelRenderer cube_r149; private final ModelRenderer cube_r150;
    private final ModelRenderer cube_r151; private final ModelRenderer cube_r152; private final ModelRenderer cube_r153; private final ModelRenderer cube_r154;
    private final ModelRenderer cube_r155; private final ModelRenderer cube_r156; private final ModelRenderer cube_r157; private final ModelRenderer cube_r158;
    private final ModelRenderer cube_r159; private final ModelRenderer arms; private final ModelRenderer right_arm; private final ModelRenderer right_forearm;
    private final ModelRenderer cube_r1; private final ModelRenderer cube_r2; private final ModelRenderer cube_r3; private final ModelRenderer cube_r4;
    private final ModelRenderer cube_r5; private final ModelRenderer cube_r6; private final ModelRenderer cube_r7; private final ModelRenderer cube_r8;
    private final ModelRenderer cube_r9; private final ModelRenderer cube_r10; private final ModelRenderer cube_r11; private final ModelRenderer cube_r12;
    private final ModelRenderer cube_r13; private final ModelRenderer cube_r14; private final ModelRenderer cube_r15; private final ModelRenderer cube_r16;
    private final ModelRenderer cube_r17; private final ModelRenderer cube_r18; private final ModelRenderer cube_r19; private final ModelRenderer right_bicep;
    private final ModelRenderer cube_r20; private final ModelRenderer cube_r21; private final ModelRenderer cube_r22; private final ModelRenderer cube_r23;
    private final ModelRenderer cube_r24; private final ModelRenderer cube_r25; private final ModelRenderer cube_r26; private final ModelRenderer left_arm;
    private final ModelRenderer left_bicep; private final ModelRenderer cube_r27; private final ModelRenderer cube_r28; private final ModelRenderer cube_r29;
    private final ModelRenderer cube_r30; private final ModelRenderer cube_r31; private final ModelRenderer cube_r32; private final ModelRenderer cube_r33;
    private final ModelRenderer left_forearm; private final ModelRenderer cube_r34; private final ModelRenderer cube_r35; private final ModelRenderer cube_r36;
    private final ModelRenderer cube_r37; private final ModelRenderer cube_r38; private final ModelRenderer cube_r39; private final ModelRenderer cube_r40;
    private final ModelRenderer cube_r41; private final ModelRenderer cube_r42; private final ModelRenderer cube_r43; private final ModelRenderer cube_r44;
    private final ModelRenderer cube_r45; private final ModelRenderer cube_r46; private final ModelRenderer cube_r47; private final ModelRenderer cube_r48;
    private final ModelRenderer cube_r49; private final ModelRenderer cube_r50; private final ModelRenderer cube_r51;

    public LanguidDwellerModel() {
        texWidth = 128;
        texHeight = 128;

        bone2 = new ModelRenderer(this);
        bone2.setPos(0.0F, 24.0F, 0.0F);

        whole_head = new ModelRenderer(this);
        whole_head.setPos(0.0F, -29.0F, -15.5F);
        bone2.addChild(whole_head);

        jaw_rotation = new ModelRenderer(this);
        jaw_rotation.setPos(0.0F, 0.0F, 0.0F);
        whole_head.addChild(jaw_rotation);

        mandible = new ModelRenderer(this);
        mandible.setPos(-0.2084F, 0.3978F, -0.2696F);
        jaw_rotation.addChild(mandible);
        setRotationAngle(mandible, 0.6108F, 0.0F, 0.0F);

        cube_r238 = new ModelRenderer(this);
        cube_r238.setPos(1.2132F, -0.647F, -9.7016F);
        mandible.addChild(cube_r238);
        setRotationAngle(cube_r238, 0.5029F, -0.3955F, 2.6849F);
        cube_r238.texOffs(76, 82).addBox(-1.0F, -1.0F, -1.0488F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r239 = new ModelRenderer(this);
        cube_r239.setPos(-0.7966F, -0.647F, -9.7016F);
        mandible.addChild(cube_r239);
        setRotationAngle(cube_r239, 0.5029F, 0.3955F, -2.6849F);
        cube_r239.texOffs(76, 82).addBox(-1.0F, -1.0F, -1.0488F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r240 = new ModelRenderer(this);
        cube_r240.setPos(0.671F, -0.2244F, -4.989F);
        mandible.addChild(cube_r240);
        setRotationAngle(cube_r240, -0.0047F, -0.0008F, -3.1337F);
        cube_r240.texOffs(16, 60).addBox(-2.0F, -1.0F, -4.0F, 4.0F, 2.0F, 8.0F, 0.0F, false);

        cube_r241 = new ModelRenderer(this);
        cube_r241.setPos(-0.2544F, -0.2244F, -4.989F);
        mandible.addChild(cube_r241);
        setRotationAngle(cube_r241, -0.0066F, 0.1054F, 3.1156F);
        cube_r241.texOffs(16, 60).addBox(-2.0F, -1.0F, -4.0F, 4.0F, 2.0F, 8.0F, 0.0F, true);

        cube_r242 = new ModelRenderer(this);
        cube_r242.setPos(-3.9774F, -0.8882F, -1.572F);
        mandible.addChild(cube_r242);
        setRotationAngle(cube_r242, -0.0072F, -0.8623F, 3.1362F);
        cube_r242.texOffs(8, 0).addBox(-1.4F, -1.4F, 0.8F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r243 = new ModelRenderer(this);
        cube_r243.setPos(-2.323F, 0.3712F, -1.6032F);
        mandible.addChild(cube_r243);
        setRotationAngle(cube_r243, -0.0254F, -0.0042F, -2.4195F);
        cube_r243.texOffs(8, 0).addBox(-1.0288F, -1.0552F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r244 = new ModelRenderer(this);
        cube_r244.setPos(-3.9774F, -0.8882F, -1.572F);
        mandible.addChild(cube_r244);
        setRotationAngle(cube_r244, -0.0047F, -0.0333F, 3.1309F);
        cube_r244.texOffs(8, 0).addBox(-2.2F, -1.4F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r245 = new ModelRenderer(this);
        cube_r245.setPos(-3.5284F, 0.2226F, -1.6066F);
        mandible.addChild(cube_r245);
        setRotationAngle(cube_r245, 0.6454F, -0.2188F, 2.1035F);
        cube_r245.texOffs(8, 0).addBox(-1.4F, -1.4F, 0.8F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r246 = new ModelRenderer(this);
        cube_r246.setPos(0.0122F, 0.3258F, -1.5702F);
        mandible.addChild(cube_r246);
        setRotationAngle(cube_r246, -1.2269F, 0.0129F, 3.1158F);
        cube_r246.texOffs(48, 12).addBox(-2.1836F, -1.217F, -1.0556F, 4.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r248 = new ModelRenderer(this);
        cube_r248.setPos(-2.4932F, 0.2174F, -4.0782F);
        mandible.addChild(cube_r248);
        setRotationAngle(cube_r248, 0.0496F, -0.0324F, -2.5135F);
        cube_r248.texOffs(84, 60).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);

        cube_r249 = new ModelRenderer(this);
        cube_r249.setPos(-2.283F, -0.0022F, -6.7672F);
        mandible.addChild(cube_r249);
        setRotationAngle(cube_r249, 0.1419F, 0.1332F, -2.5295F);
        cube_r249.texOffs(84, 60).addBox(-1.0F, -1.0F, -3.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);

        cube_r250 = new ModelRenderer(this);
        cube_r250.setPos(0.4044F, 0.3258F, -1.5702F);
        mandible.addChild(cube_r250);
        setRotationAngle(cube_r250, -1.2269F, -0.0129F, -3.1158F);
        cube_r250.texOffs(48, 12).addBox(-1.8164F, -1.217F, -1.0556F, 4.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r252 = new ModelRenderer(this);
        cube_r252.setPos(2.7396F, 0.3712F, -1.6032F);
        mandible.addChild(cube_r252);
        setRotationAngle(cube_r252, -0.0254F, 0.0042F, 2.4195F);
        cube_r252.texOffs(8, 0).addBox(-0.9712F, -1.0552F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r253 = new ModelRenderer(this);
        cube_r253.setPos(2.9098F, 0.2174F, -4.0782F);
        mandible.addChild(cube_r253);
        setRotationAngle(cube_r253, 0.0496F, 0.0324F, 2.5135F);
        cube_r253.texOffs(84, 60).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 4.0F, 0.0F, true);

        cube_r254 = new ModelRenderer(this);
        cube_r254.setPos(3.945F, 0.2226F, -1.6066F);
        mandible.addChild(cube_r254);
        setRotationAngle(cube_r254, 0.6454F, 0.2188F, -2.1035F);
        cube_r254.texOffs(8, 0).addBox(-0.6F, -1.4F, 0.8F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r255 = new ModelRenderer(this);
        cube_r255.setPos(4.394F, -0.8882F, -1.572F);
        mandible.addChild(cube_r255);
        setRotationAngle(cube_r255, -0.0072F, 0.8623F, -3.1362F);
        cube_r255.texOffs(8, 0).addBox(-0.6F, -1.4F, 0.8F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r256 = new ModelRenderer(this);
        cube_r256.setPos(4.394F, -0.8882F, -1.572F);
        mandible.addChild(cube_r256);
        setRotationAngle(cube_r256, -0.0047F, 0.0333F, -3.1309F);
        cube_r256.texOffs(8, 0).addBox(0.2F, -1.4F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r257 = new ModelRenderer(this);
        cube_r257.setPos(1.1296F, 1.2228F, -4.9806F);
        mandible.addChild(cube_r257);
        setRotationAngle(cube_r257, 0.1341F, -0.0571F, 3.122F);
        cube_r257.texOffs(32, 30).addBox(-1.0F, -0.6F, -4.6F, 2.0F, 2.0F, 8.0F, 0.0F, true);

        cube_r258 = new ModelRenderer(this);
        cube_r258.setPos(-0.713F, 1.2228F, -4.9806F);
        mandible.addChild(cube_r258);
        setRotationAngle(cube_r258, 0.1341F, 0.0571F, -3.122F);
        cube_r258.texOffs(32, 30).addBox(-1.0F, -0.6F, -4.6F, 2.0F, 2.0F, 8.0F, 0.0F, false);

        cube_r259 = new ModelRenderer(this);
        cube_r259.setPos(2.0016F, 0.3166F, -5.5192F);
        mandible.addChild(cube_r259);
        setRotationAngle(cube_r259, 0.128F, 0.0631F, 2.3055F);
        cube_r259.texOffs(32, 11).addBox(-1.0F, -1.0F, -4.0F, 2.0F, 2.0F, 8.0F, 0.0F, true);

        cube_r260 = new ModelRenderer(this);
        cube_r260.setPos(-1.6376F, 0.4486F, -4.5294F);
        mandible.addChild(cube_r260);
        setRotationAngle(cube_r260, 0.128F, -0.0631F, -2.3055F);
        cube_r260.texOffs(32, 11).addBox(-1.0F, -1.0F, -5.0F, 2.0F, 2.0F, 8.0F, 0.0F, false);

        cube_r261 = new ModelRenderer(this);
        cube_r261.setPos(2.6996F, -0.0022F, -6.7672F);
        mandible.addChild(cube_r261);
        setRotationAngle(cube_r261, 0.1419F, -0.1332F, 2.5295F);
        cube_r261.texOffs(84, 60).addBox(-1.0F, -1.0F, -3.0F, 2.0F, 2.0F, 4.0F, 0.0F, true);

        cube_r262 = new ModelRenderer(this);
        cube_r262.setPos(-1.4F, -0.2F, -10.0F);
        mandible.addChild(cube_r262);
        setRotationAngle(cube_r262, 0.3984F, 0.4792F, -2.4956F);
        cube_r262.texOffs(47, 28).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r263 = new ModelRenderer(this);
        cube_r263.setPos(1.8166F, -0.2F, -10.0F);
        mandible.addChild(cube_r263);
        setRotationAngle(cube_r263, 0.3984F, -0.4792F, 2.4956F);
        cube_r263.texOffs(56, 30).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r264 = new ModelRenderer(this);
        cube_r264.setPos(-0.381F, -0.0388F, -10.45F);
        mandible.addChild(cube_r264);
        setRotationAngle(cube_r264, 0.3776F, -0.2443F, -2.8784F);
        cube_r264.texOffs(18, 46).addBox(-0.8F, -1.0F, -1.2F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r265 = new ModelRenderer(this);
        cube_r265.setPos(0.7976F, -0.0388F, -10.45F);
        mandible.addChild(cube_r265);
        setRotationAngle(cube_r265, 0.3776F, 0.2443F, 2.8784F);
        cube_r265.texOffs(18, 46).addBox(-1.2F, -1.0F, -1.2F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r266 = new ModelRenderer(this);
        cube_r266.setPos(0.211F, 1.7978F, -8.2992F);
        mandible.addChild(cube_r266);
        setRotationAngle(cube_r266, 1.5644F, 0.4101F, 1.5611F);
        cube_r266.texOffs(20, 40).addBox(-1.9092F, -3.9218F, -0.9968F, 2.0F, 4.0F, 2.0F, 0.0F, true);

        cube_r267 = new ModelRenderer(this);
        cube_r267.setPos(0.2278F, 0.7654F, -8.0004F);
        mandible.addChild(cube_r267);
        setRotationAngle(cube_r267, 1.568F, 0.052F, 1.5612F);
        cube_r267.texOffs(26, 46).addBox(-0.8392F, -0.2F, -1.0246F, 2.0F, 4.0F, 2.0F, 0.0F, true);

        cube_r268 = new ModelRenderer(this);
        cube_r268.setPos(0.2276F, 1.3412F, -6.9122F);
        mandible.addChild(cube_r268);
        setRotationAngle(cube_r268, 1.5679F, 0.3225F, 1.5604F);
        cube_r268.texOffs(48, 62).addBox(-1.8392F, 1.4362F, -1.0246F, 2.0F, 4.0F, 2.0F, 0.0F, true);

        bottom_teeth = new ModelRenderer(this);
        bottom_teeth.setPos(2.2028F, -0.485F, -4.7156F);
        mandible.addChild(bottom_teeth);

        cube_r269 = new ModelRenderer(this);
        cube_r269.setPos(0.0F, 0.0F, 0.0F);
        bottom_teeth.addChild(cube_r269);
        setRotationAngle(cube_r269, 0.0318F, 0.6975F, 2.785F);
        cube_r269.texOffs(76, 82).addBox(-1.8F, -1.0F, 0.0F, 2.0F, 2.0F, 2.0F, 0.0F, true);
        cube_r269.texOffs(76, 82).addBox(-1.0F, -1.2F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r270 = new ModelRenderer(this);
        cube_r270.setPos(-0.3388F, -0.1956F, -4.3734F);
        bottom_teeth.addChild(cube_r270);
        setRotationAngle(cube_r270, 0.0318F, 0.6975F, 2.785F);
        cube_r270.texOffs(76, 82).addBox(-3.2F, -1.6F, 1.2F, 2.0F, 2.0F, 2.0F, 0.0F, true);
        cube_r270.texOffs(76, 82).addBox(-2.2F, -1.4F, 0.4F, 2.0F, 2.0F, 2.0F, 0.0F, true);
        cube_r270.texOffs(76, 82).addBox(-1.0F, -1.2F, -0.6F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r271 = new ModelRenderer(this);
        cube_r271.setPos(-3.989F, 0.0F, 0.0F);
        bottom_teeth.addChild(cube_r271);
        setRotationAngle(cube_r271, 0.0318F, -0.6975F, -2.785F);
        cube_r271.texOffs(76, 82).addBox(-0.2F, -1.0F, 0.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
        cube_r271.texOffs(76, 82).addBox(-1.0F, -1.2F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r272 = new ModelRenderer(this);
        cube_r272.setPos(-3.6502F, -0.1956F, -4.3734F);
        bottom_teeth.addChild(cube_r272);
        setRotationAngle(cube_r272, 0.0318F, -0.6975F, -2.785F);
        cube_r272.texOffs(76, 82).addBox(1.2F, -1.6F, 1.2F, 2.0F, 2.0F, 2.0F, 0.0F, false);
        cube_r272.texOffs(76, 82).addBox(0.2F, -1.4F, 0.4F, 2.0F, 2.0F, 2.0F, 0.0F, false);
        cube_r272.texOffs(76, 82).addBox(-1.0F, -1.2F, -0.6F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        upper_jaw = new ModelRenderer(this);
        upper_jaw.setPos(-1.6084F, -2.1978F, -1.4696F);
        whole_head.addChild(upper_jaw);

        cube_r273 = new ModelRenderer(this);
        cube_r273.setPos(-1.0932F, -0.2478F, -1.2388F);
        upper_jaw.addChild(cube_r273);
        setRotationAngle(cube_r273, 0.0918F, 0.0701F, 2.5162F);
        cube_r273.texOffs(8, 0).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r274 = new ModelRenderer(this);
        cube_r274.setPos(-1.0794F, 0.397F, 0.5566F);
        upper_jaw.addChild(cube_r274);
        setRotationAngle(cube_r274, 0.2887F, 0.2463F, 2.4621F);
        cube_r274.texOffs(8, 0).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r275 = new ModelRenderer(this);
        cube_r275.setPos(-1.4434F, 1.1876F, 0.2434F);
        upper_jaw.addChild(cube_r275);
        setRotationAngle(cube_r275, -0.0261F, 0.3777F, 1.5141F);
        cube_r275.texOffs(47, 28).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r276 = new ModelRenderer(this);
        cube_r276.setPos(-0.3726F, 1.4528F, 1.0166F);
        upper_jaw.addChild(cube_r276);
        setRotationAngle(cube_r276, 0.7208F, 0.3662F, 1.5282F);
        cube_r276.texOffs(8, 0).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r277 = new ModelRenderer(this);
        cube_r277.setPos(1.4122F, -0.5258F, -0.5702F);
        upper_jaw.addChild(cube_r277);
        setRotationAngle(cube_r277, 1.2269F, 0.0129F, -3.1158F);
        cube_r277.texOffs(44, 11).addBox(-2.1836F, -0.783F, -1.0556F, 4.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r278 = new ModelRenderer(this);
        cube_r278.setPos(1.0036F, -0.5364F, -0.5756F);
        upper_jaw.addChild(cube_r278);
        setRotationAngle(cube_r278, 1.2269F, 0.0129F, -3.1158F);
        cube_r278.texOffs(44, 11).addBox(-2.2164F, -0.783F, -1.0556F, 4.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r279 = new ModelRenderer(this);
        cube_r279.setPos(-1.169F, -0.4408F, -2.9254F);
        upper_jaw.addChild(cube_r279);
        setRotationAngle(cube_r279, -0.1199F, -0.0125F, 2.549F);
        cube_r279.texOffs(8, 0).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r280 = new ModelRenderer(this);
        cube_r280.setPos(-0.2898F, -0.9746F, -3.3286F);
        upper_jaw.addChild(cube_r280);
        setRotationAngle(cube_r280, -0.1531F, -0.0735F, 2.1959F);
        cube_r280.texOffs(24, 4).addBox(-1.0F, -1.0F, -3.4F, 2.0F, 2.0F, 6.0F, 0.0F, false);

        cube_r281 = new ModelRenderer(this);
        cube_r281.setPos(0.7728F, -0.9694F, -6.3024F);
        upper_jaw.addChild(cube_r281);
        setRotationAngle(cube_r281, -0.1774F, 0.0496F, 2.924F);
        cube_r281.texOffs(24, 4).addBox(-1.2F, -0.882F, -0.4F, 2.0F, 2.0F, 6.0F, 0.0F, false);

        cube_r282 = new ModelRenderer(this);
        cube_r282.setPos(3.5064F, -0.9746F, -3.3286F);
        upper_jaw.addChild(cube_r282);
        setRotationAngle(cube_r282, -0.1531F, 0.0735F, -2.1959F);
        cube_r282.texOffs(24, 4).addBox(-1.0F, -1.0F, -3.4F, 2.0F, 2.0F, 6.0F, 0.0F, true);

        cube_r283 = new ModelRenderer(this);
        cube_r283.setPos(2.515F, -1.1088F, -6.0692F);
        upper_jaw.addChild(cube_r283);
        setRotationAngle(cube_r283, -0.3874F, -0.0925F, -2.9163F);
        cube_r283.texOffs(20, 44).addBox(-0.8F, -0.882F, -2.4F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r284 = new ModelRenderer(this);
        cube_r284.setPos(2.2908F, -1.5382F, -5.8908F);
        upper_jaw.addChild(cube_r284);
        setRotationAngle(cube_r284, -1.9376F, 0.105F, -2.2681F);
        cube_r284.texOffs(37, 47).addBox(-2.4F, 0.6F, -0.6F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r285 = new ModelRenderer(this);
        cube_r285.setPos(2.4438F, -0.9694F, -6.3024F);
        upper_jaw.addChild(cube_r285);
        setRotationAngle(cube_r285, -0.1774F, -0.0496F, -2.924F);
        cube_r285.texOffs(24, 4).addBox(-0.8F, -0.882F, -0.4F, 2.0F, 2.0F, 6.0F, 0.0F, true);

        cube_r286 = new ModelRenderer(this);
        cube_r286.setPos(0.7016F, -1.1088F, -6.0692F);
        upper_jaw.addChild(cube_r286);
        setRotationAngle(cube_r286, -0.3874F, 0.0925F, 2.9163F);
        cube_r286.texOffs(20, 44).addBox(-1.2F, -0.882F, -2.4F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r287 = new ModelRenderer(this);
        cube_r287.setPos(0.9258F, -1.5382F, -5.8908F);
        upper_jaw.addChild(cube_r287);
        setRotationAngle(cube_r287, -1.9376F, -0.105F, 2.2681F);
        cube_r287.texOffs(37, 46).addBox(0.4F, 0.6F, -0.6F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r288 = new ModelRenderer(this);
        cube_r288.setPos(-0.6942F, -0.1576F, -6.7484F);
        upper_jaw.addChild(cube_r288);
        setRotationAngle(cube_r288, -0.1419F, 0.1332F, 2.5295F);
        cube_r288.texOffs(33, 45).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);

        cube_r289 = new ModelRenderer(this);
        cube_r289.setPos(1.8044F, -0.5258F, -0.5702F);
        upper_jaw.addChild(cube_r289);
        setRotationAngle(cube_r289, 1.2269F, -0.0129F, 3.1158F);
        cube_r289.texOffs(44, 11).addBox(-1.8164F, -0.783F, -1.0556F, 4.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r290 = new ModelRenderer(this);
        cube_r290.setPos(2.213F, -0.5364F, -0.5756F);
        upper_jaw.addChild(cube_r290);
        setRotationAngle(cube_r290, 1.2269F, -0.0129F, 3.1158F);
        cube_r290.texOffs(44, 11).addBox(-1.7836F, -0.783F, -1.0556F, 4.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r291 = new ModelRenderer(this);
        cube_r291.setPos(4.0996F, -0.1978F, -5.7672F);
        upper_jaw.addChild(cube_r291);
        setRotationAngle(cube_r291, -0.1419F, -0.1332F, -2.5295F);
        cube_r291.texOffs(33, 45).addBox(-1.0F, -1.0F, -3.0F, 2.0F, 2.0F, 4.0F, 0.0F, true);

        cube_r292 = new ModelRenderer(this);
        cube_r292.setPos(4.3098F, -0.4174F, -3.0782F);
        upper_jaw.addChild(cube_r292);
        setRotationAngle(cube_r292, -0.0671F, 0.0148F, -2.5142F);
        cube_r292.texOffs(8, 0).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r293 = new ModelRenderer(this);
        cube_r293.setPos(4.3098F, -0.2478F, -1.2388F);
        upper_jaw.addChild(cube_r293);
        setRotationAngle(cube_r293, 0.0918F, -0.0701F, -2.5162F);
        cube_r293.texOffs(8, 0).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r294 = new ModelRenderer(this);
        cube_r294.setPos(4.296F, 0.397F, 0.5566F);
        upper_jaw.addChild(cube_r294);
        setRotationAngle(cube_r294, 0.2887F, -0.2463F, -2.4621F);
        cube_r294.texOffs(8, 0).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r295 = new ModelRenderer(this);
        cube_r295.setPos(4.66F, 1.1876F, 0.2434F);
        upper_jaw.addChild(cube_r295);
        setRotationAngle(cube_r295, -0.0261F, -0.3777F, -1.5141F);
        cube_r295.texOffs(8, 0).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r296 = new ModelRenderer(this);
        cube_r296.setPos(3.5892F, 1.4528F, 1.0166F);
        upper_jaw.addChild(cube_r296);
        setRotationAngle(cube_r296, 0.7208F, -0.3662F, -1.5282F);
        cube_r296.texOffs(8, 0).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r297 = new ModelRenderer(this);
        cube_r297.setPos(0.0F, 0.0F, -9.0F);
        upper_jaw.addChild(cube_r297);
        setRotationAngle(cube_r297, -0.3984F, 0.4792F, 2.4956F);
        cube_r297.texOffs(50, 14).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r298 = new ModelRenderer(this);
        cube_r298.setPos(3.2166F, 0.0F, -9.0F);
        upper_jaw.addChild(cube_r298);
        setRotationAngle(cube_r298, -0.3984F, -0.4792F, -2.4956F);
        cube_r298.texOffs(61, 14).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r299 = new ModelRenderer(this);
        cube_r299.setPos(1.019F, -0.1612F, -9.45F);
        upper_jaw.addChild(cube_r299);
        setRotationAngle(cube_r299, -0.3776F, -0.2443F, 2.8784F);
        cube_r299.texOffs(49, 14).addBox(-0.8F, -1.0F, -1.2F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r300 = new ModelRenderer(this);
        cube_r300.setPos(2.1976F, -0.1612F, -9.45F);
        upper_jaw.addChild(cube_r300);
        setRotationAngle(cube_r300, -0.3776F, 0.2443F, -2.8784F);
        cube_r300.texOffs(49, 14).addBox(-1.2F, -1.0F, -1.2F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r301 = new ModelRenderer(this);
        cube_r301.setPos(1.027F, -0.0236F, -8.5932F);
        upper_jaw.addChild(cube_r301);
        setRotationAngle(cube_r301, -0.1381F, -0.1009F, 2.8481F);
        cube_r301.texOffs(48, 13).addBox(-1.0F, -0.6F, -0.4F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r302 = new ModelRenderer(this);
        cube_r302.setPos(2.1896F, -0.0236F, -8.5932F);
        upper_jaw.addChild(cube_r302);
        setRotationAngle(cube_r302, -0.1381F, 0.1009F, -2.8481F);
        cube_r302.texOffs(48, 13).addBox(-1.0F, -0.6F, -0.4F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r303 = new ModelRenderer(this);
        cube_r303.setPos(1.624F, -1.1456F, -6.7692F);
        upper_jaw.addChild(cube_r303);
        setRotationAngle(cube_r303, -1.5719F, 0.2792F, -1.5799F);
        cube_r303.texOffs(32, 52).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r304 = new ModelRenderer(this);
        cube_r304.setPos(2.6494F, -0.7572F, -6.4216F);
        upper_jaw.addChild(cube_r304);
        setRotationAngle(cube_r304, -0.0261F, 0.0415F, -2.1812F);
        cube_r304.texOffs(8, 46).addBox(0.0F, -1.482F, -1.6F, 2.0F, 2.0F, 6.0F, 0.0F, true);

        cube_r305 = new ModelRenderer(this);
        cube_r305.setPos(0.5672F, -0.7572F, -6.4216F);
        upper_jaw.addChild(cube_r305);
        setRotationAngle(cube_r305, -0.0261F, -0.0415F, 2.1812F);
        cube_r305.texOffs(8, 46).addBox(-2.0F, -1.482F, -1.6F, 2.0F, 2.0F, 6.0F, 0.0F, false);

        cube_r306 = new ModelRenderer(this);
        cube_r306.setPos(1.6248F, -2.9612F, -5.8034F);
        upper_jaw.addChild(cube_r306);
        setRotationAngle(cube_r306, 1.5662F, -0.9247F, 1.5839F);
        cube_r306.texOffs(36, 49).addBox(9.0F, 2.6F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r307 = new ModelRenderer(this);
        cube_r307.setPos(1.6194F, -2.2672F, -6.2054F);
        upper_jaw.addChild(cube_r307);
        setRotationAngle(cube_r307, -1.5657F, -0.9952F, -1.5656F);
        cube_r307.texOffs(16, 15).addBox(6.8F, -8.4F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

        cube_r308 = new ModelRenderer(this);
        cube_r308.setPos(1.6162F, -3.3576F, -1.2658F);
        upper_jaw.addChild(cube_r308);
        setRotationAngle(cube_r308, -1.568F, -0.1225F, -1.5617F);
        cube_r308.texOffs(38, 47).addBox(-1.4F, -4.8F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

        cube_r309 = new ModelRenderer(this);
        cube_r309.setPos(1.616F, -3.291F, -1.419F);
        upper_jaw.addChild(cube_r309);
        setRotationAngle(cube_r309, -1.568F, -0.0353F, -1.5615F);
        cube_r309.texOffs(37, 50).addBox(-1.4F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r310 = new ModelRenderer(this);
        cube_r310.setPos(1.6268F, -2.2398F, -3.8562F);
        upper_jaw.addChild(cube_r310);
        setRotationAngle(cube_r310, -1.5767F, 0.401F, -1.5791F);
        cube_r310.texOffs(37, 49).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

        top_teeth = new ModelRenderer(this);
        top_teeth.setPos(3.6028F, 0.285F, -3.7156F);
        upper_jaw.addChild(top_teeth);

        cube_r311 = new ModelRenderer(this);
        cube_r311.setPos(0.0F, 0.0F, 0.0F);
        top_teeth.addChild(cube_r311);
        setRotationAngle(cube_r311, -0.0318F, 0.6975F, -2.785F);
        cube_r311.texOffs(76, 82).addBox(-1.8F, -1.0F, 0.0F, 2.0F, 2.0F, 2.0F, 0.0F, true);
        cube_r311.texOffs(76, 82).addBox(-1.0F, -0.8F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r312 = new ModelRenderer(this);
        cube_r312.setPos(-0.3388F, 0.1956F, -4.3734F);
        top_teeth.addChild(cube_r312);
        setRotationAngle(cube_r312, -0.0318F, 0.6975F, -2.785F);
        cube_r312.texOffs(76, 82).addBox(-3.2F, -0.4F, 1.2F, 2.0F, 2.0F, 2.0F, 0.0F, true);
        cube_r312.texOffs(76, 82).addBox(-2.2F, -0.6F, 0.4F, 2.0F, 2.0F, 2.0F, 0.0F, true);
        cube_r312.texOffs(76, 82).addBox(-1.0F, -0.8F, -0.6F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r313 = new ModelRenderer(this);
        cube_r313.setPos(-3.989F, 0.0F, 0.0F);
        top_teeth.addChild(cube_r313);
        setRotationAngle(cube_r313, -0.0318F, -0.6975F, 2.785F);
        cube_r313.texOffs(76, 82).addBox(-0.2F, -1.0F, 0.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
        cube_r313.texOffs(76, 82).addBox(-1.0F, -0.8F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r314 = new ModelRenderer(this);
        cube_r314.setPos(-3.6502F, 0.1956F, -4.3734F);
        top_teeth.addChild(cube_r314);
        setRotationAngle(cube_r314, -0.0318F, -0.6975F, 2.785F);
        cube_r314.texOffs(76, 82).addBox(1.2F, -0.4F, 1.2F, 2.0F, 2.0F, 2.0F, 0.0F, false);
        cube_r314.texOffs(76, 82).addBox(0.2F, -0.6F, 0.4F, 2.0F, 2.0F, 2.0F, 0.0F, false);
        cube_r314.texOffs(76, 82).addBox(-1.0F, -0.8F, -0.6F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r315 = new ModelRenderer(this);
        cube_r315.setPos(-2.9994F, 0.162F, -4.9862F);
        top_teeth.addChild(cube_r315);
        setRotationAngle(cube_r315, -0.0478F, -0.1062F, 2.7594F);
        cube_r315.texOffs(76, 82).addBox(-1.0F, -1.0F, -1.0488F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r316 = new ModelRenderer(this);
        cube_r316.setPos(-0.9896F, 0.162F, -4.9862F);
        top_teeth.addChild(cube_r316);
        setRotationAngle(cube_r316, -0.0478F, 0.1062F, -2.7594F);
        cube_r316.texOffs(76, 82).addBox(-1.0F, -1.0F, -1.0488F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        mouth = new ModelRenderer(this);
        mouth.setPos(2.6132F, 0.647F, -8.7016F);
        upper_jaw.addChild(mouth);

        cube_r317 = new ModelRenderer(this);
        cube_r317.setPos(-1.4676F, -0.4226F, 4.7126F);
        mouth.addChild(cube_r317);
        setRotationAngle(cube_r317, 0.0047F, 0.1404F, -3.133F);
        cube_r317.texOffs(16, 60).addBox(-1.8F, -1.0F, -4.0F, 4.0F, 2.0F, 8.0F, 0.0F, true);

        cube_r318 = new ModelRenderer(this);
        cube_r318.setPos(-0.5422F, -0.4226F, 4.7126F);
        mouth.addChild(cube_r318);
        setRotationAngle(cube_r318, 0.0047F, -0.1404F, 3.133F);
        cube_r318.texOffs(16, 60).addBox(-2.2F, -1.0F, -4.0F, 4.0F, 2.0F, 8.0F, 0.0F, false);

        head = new ModelRenderer(this);
        head.setPos(0.0F, -0.6F, -0.3F);
        whole_head.addChild(head);
        setRotationAngle(head, 1.2217F, 0.0F, 0.0F);

        cube_r319 = new ModelRenderer(this);
        cube_r319.setPos(-0.8292F, 0.8612F, 3.5092F);
        head.addChild(cube_r319);
        setRotationAngle(cube_r319, -1.4995F, 0.6F, 2.8603F);
        cube_r319.texOffs(23, 18).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r320 = new ModelRenderer(this);
        cube_r320.setPos(-1.1636F, 0.7352F, 4.2688F);
        head.addChild(cube_r320);
        setRotationAngle(cube_r320, -1.1159F, 0.6122F, 2.7816F);
        cube_r320.texOffs(38, 51).addBox(-0.7946F, -0.2974F, -2.0746F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r321 = new ModelRenderer(this);
        cube_r321.setPos(0.8292F, 0.8612F, 3.5092F);
        head.addChild(cube_r321);
        setRotationAngle(cube_r321, -1.4995F, -0.6F, -2.8603F);
        cube_r321.texOffs(45, 27).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r322 = new ModelRenderer(this);
        cube_r322.setPos(1.1636F, 0.7352F, 4.2688F);
        head.addChild(cube_r322);
        setRotationAngle(cube_r322, -1.1159F, -0.6122F, -2.7816F);
        cube_r322.texOffs(47, 28).addBox(-1.2054F, -0.2974F, -2.0746F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r323 = new ModelRenderer(this);
        cube_r323.setPos(2.5184F, 0.4214F, 1.0806F);
        head.addChild(cube_r323);
        setRotationAngle(cube_r323, -1.6704F, -1.4755F, -2.6831F);
        cube_r323.texOffs(78, 42).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r324 = new ModelRenderer(this);
        cube_r324.setPos(1.6454F, 0.0858F, 1.822F);
        head.addChild(cube_r324);
        setRotationAngle(cube_r324, 1.6534F, -1.4558F, 0.2773F);
        cube_r324.texOffs(47, 28).addBox(-3.4F, -2.2F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r325 = new ModelRenderer(this);
        cube_r325.setPos(0.7502F, 0.8004F, 3.5402F);
        head.addChild(cube_r325);
        setRotationAngle(cube_r325, -1.5895F, -1.0396F, -2.7661F);
        cube_r325.texOffs(20, 42).addBox(-2.4F, -1.4F, 0.0F, 2.0F, 4.0F, 2.0F, 0.0F, true);

        cube_r326 = new ModelRenderer(this);
        cube_r326.setPos(1.9778F, -1.8238F, 2.029F);
        head.addChild(cube_r326);
        setRotationAngle(cube_r326, -1.1476F, -1.0379F, -2.7726F);
        cube_r326.texOffs(47, 28).addBox(-1.0F, -3.0F, -3.8F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r327 = new ModelRenderer(this);
        cube_r327.setPos(0.338F, 2.053F, 1.9518F);
        head.addChild(cube_r327);
        setRotationAngle(cube_r327, -0.432F, -1.0379F, -2.7726F);
        cube_r327.texOffs(47, 28).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r328 = new ModelRenderer(this);
        cube_r328.setPos(-0.0502F, 1.3314F, 3.0802F);
        head.addChild(cube_r328);
        setRotationAngle(cube_r328, -0.8821F, 0.0067F, -3.118F);
        cube_r328.texOffs(47, 28).addBox(-1.0F, -0.9186F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r330 = new ModelRenderer(this);
        cube_r330.setPos(3.1044F, 1.6266F, 1.5668F);
        head.addChild(cube_r330);
        setRotationAngle(cube_r330, -0.3001F, -1.4318F, 2.6297F);
        cube_r330.texOffs(78, 42).addBox(-1.3798F, -0.0016F, -2.0328F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r331 = new ModelRenderer(this);
        cube_r331.setPos(1.8368F, 1.6118F, 0.1954F);
        head.addChild(cube_r331);
        setRotationAngle(cube_r331, 0.7828F, -1.383F, 1.5352F);
        cube_r331.texOffs(58, 32).addBox(-2.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r332 = new ModelRenderer(this);
        cube_r332.setPos(0.6818F, 2.1666F, 0.851F);
        head.addChild(cube_r332);
        setRotationAngle(cube_r332, 0.4155F, -1.4318F, 2.6297F);
        cube_r332.texOffs(56, 31).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r333 = new ModelRenderer(this);
        cube_r333.setPos(0.7884F, 1.9376F, -0.8796F);
        head.addChild(cube_r333);
        setRotationAngle(cube_r333, 1.4061F, -1.3651F, 1.618F);
        cube_r333.texOffs(58, 32).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r335 = new ModelRenderer(this);
        cube_r335.setPos(-2.5184F, 0.4214F, 1.0806F);
        head.addChild(cube_r335);
        setRotationAngle(cube_r335, -1.6704F, 1.4755F, 2.6831F);
        cube_r335.texOffs(53, 26).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r336 = new ModelRenderer(this);
        cube_r336.setPos(-0.7502F, 0.8004F, 3.5402F);
        head.addChild(cube_r336);
        setRotationAngle(cube_r336, -1.5895F, 1.0396F, 2.7661F);
        cube_r336.texOffs(37, 47).addBox(0.4F, -1.4F, 0.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

        cube_r337 = new ModelRenderer(this);
        cube_r337.setPos(-1.3018F, 1.5326F, 2.3712F);
        head.addChild(cube_r337);
        setRotationAngle(cube_r337, -1.1476F, 1.0379F, 2.7726F);
        cube_r337.texOffs(56, 32).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r338 = new ModelRenderer(this);
        cube_r338.setPos(-0.338F, 2.053F, 1.9518F);
        head.addChild(cube_r338);
        setRotationAngle(cube_r338, -0.432F, 1.0379F, 2.7726F);
        cube_r338.texOffs(47, 28).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r339 = new ModelRenderer(this);
        cube_r339.setPos(-1.726F, 1.6574F, 1.0132F);
        head.addChild(cube_r339);
        setRotationAngle(cube_r339, -0.3001F, 1.4318F, -2.6297F);
        cube_r339.texOffs(54, 27).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r340 = new ModelRenderer(this);
        cube_r340.setPos(-0.6818F, 2.1666F, 0.851F);
        head.addChild(cube_r340);
        setRotationAngle(cube_r340, 0.4155F, 1.4318F, -2.6297F);
        cube_r340.texOffs(56, 31).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r341 = new ModelRenderer(this);
        cube_r341.setPos(-1.6454F, 0.0858F, 1.822F);
        head.addChild(cube_r341);
        setRotationAngle(cube_r341, 1.6534F, 1.4558F, -0.2773F);
        cube_r341.texOffs(47, 28).addBox(1.4F, -2.2F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r342 = new ModelRenderer(this);
        cube_r342.setPos(-1.8368F, 1.6118F, 0.1954F);
        head.addChild(cube_r342);
        setRotationAngle(cube_r342, 0.7828F, 1.383F, -1.5352F);
        cube_r342.texOffs(58, 32).addBox(0.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r343 = new ModelRenderer(this);
        cube_r343.setPos(-0.7884F, 1.9376F, -0.8796F);
        head.addChild(cube_r343);
        setRotationAngle(cube_r343, 1.4061F, 1.3651F, -1.618F);
        cube_r343.texOffs(54, 32).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r344 = new ModelRenderer(this);
        cube_r344.setPos(-2.8126F, -0.9234F, 0.9166F);
        head.addChild(cube_r344);
        setRotationAngle(cube_r344, -2.8726F, 1.3369F, 1.8036F);
        cube_r344.texOffs(47, 28).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r345 = new ModelRenderer(this);
        cube_r345.setPos(-0.2114F, 0.2548F, 4.204F);
        head.addChild(cube_r345);
        setRotationAngle(cube_r345, -1.7923F, -0.0848F, 3.1135F);
        cube_r345.texOffs(47, 28).addBox(-1.0F, -1.0F, 1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r346 = new ModelRenderer(this);
        cube_r346.setPos(-1.352F, -0.5696F, 3.574F);
        head.addChild(cube_r346);
        setRotationAngle(cube_r346, -2.017F, 1.0267F, 2.7097F);
        cube_r346.texOffs(16, 32).addBox(0.4F, -1.4F, 0.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

        cube_r347 = new ModelRenderer(this);
        cube_r347.setPos(-1.3754F, -0.5926F, 3.6788F);
        head.addChild(cube_r347);
        setRotationAngle(cube_r347, -1.8481F, 0.6164F, 2.9351F);
        cube_r347.texOffs(37, 50).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r348 = new ModelRenderer(this);
        cube_r348.setPos(-3.1132F, -1.1706F, 2.051F);
        head.addChild(cube_r348);
        setRotationAngle(cube_r348, 2.6903F, 1.3199F, 1.0635F);
        cube_r348.texOffs(47, 28).addBox(2.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r349 = new ModelRenderer(this);
        cube_r349.setPos(0.2114F, 0.2548F, 4.204F);
        head.addChild(cube_r349);
        setRotationAngle(cube_r349, -1.7923F, 0.0848F, -3.1135F);
        cube_r349.texOffs(47, 28).addBox(-1.0F, -1.0F, 1.0F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r350 = new ModelRenderer(this);
        cube_r350.setPos(1.3754F, -0.5926F, 3.6788F);
        head.addChild(cube_r350);
        setRotationAngle(cube_r350, -1.8481F, -0.6164F, -2.9351F);
        cube_r350.texOffs(45, 27).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r351 = new ModelRenderer(this);
        cube_r351.setPos(2.8126F, -0.9234F, 0.9166F);
        head.addChild(cube_r351);
        setRotationAngle(cube_r351, -2.8726F, -1.3369F, -1.8036F);
        cube_r351.texOffs(79, 40).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r352 = new ModelRenderer(this);
        cube_r352.setPos(3.1132F, -1.1706F, 2.051F);
        head.addChild(cube_r352);
        setRotationAngle(cube_r352, 2.6903F, -1.3199F, -1.0635F);
        cube_r352.texOffs(79, 41).addBox(-4.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r353 = new ModelRenderer(this);
        cube_r353.setPos(1.352F, -0.5696F, 3.574F);
        head.addChild(cube_r353);
        setRotationAngle(cube_r353, -2.017F, -1.0267F, -2.7097F);
        cube_r353.texOffs(21, 44).addBox(-2.4F, -1.4F, 0.0F, 2.0F, 4.0F, 2.0F, 0.0F, true);

        cube_r354 = new ModelRenderer(this);
        cube_r354.setPos(0.0144F, -5.075F, 2.6758F);
        head.addChild(cube_r354);
        setRotationAngle(cube_r354, -1.2452F, 0.0463F, 0.1496F);
        cube_r354.texOffs(47, 28).addBox(-1.0F, -1.0F, 1.0F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r355 = new ModelRenderer(this);
        cube_r355.setPos(-1.2266F, -4.1762F, 2.6548F);
        head.addChild(cube_r355);
        setRotationAngle(cube_r355, -1.1696F, -0.631F, -0.1153F);
        cube_r355.texOffs(36, 49).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r356 = new ModelRenderer(this);
        cube_r356.setPos(-1.0036F, -3.7844F, 2.457F);
        head.addChild(cube_r356);
        setRotationAngle(cube_r356, -0.181F, -0.6431F, -0.1333F);
        cube_r356.texOffs(47, 28).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r357 = new ModelRenderer(this);
        cube_r357.setPos(-1.205F, -4.1394F, 2.5536F);
        head.addChild(cube_r357);
        setRotationAngle(cube_r357, -0.9318F, -1.0137F, -0.4327F);
        cube_r357.texOffs(47, 34).addBox(-2.4F, -1.4F, 0.0F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r358 = new ModelRenderer(this);
        cube_r358.setPos(-2.7738F, -2.5774F, 0.4514F);
        head.addChild(cube_r358);
        setRotationAngle(cube_r358, -0.082F, -1.2489F, -1.3545F);
        cube_r358.texOffs(47, 28).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r359 = new ModelRenderer(this);
        cube_r359.setPos(-0.0144F, -5.075F, 2.6758F);
        head.addChild(cube_r359);
        setRotationAngle(cube_r359, -1.2452F, -0.0463F, -0.1496F);
        cube_r359.texOffs(47, 28).addBox(-1.0F, -1.0F, 1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r360 = new ModelRenderer(this);
        cube_r360.setPos(1.2266F, -4.1762F, 2.6548F);
        head.addChild(cube_r360);
        setRotationAngle(cube_r360, -1.1696F, 0.631F, 0.1153F);
        cube_r360.texOffs(46, 28).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r361 = new ModelRenderer(this);
        cube_r361.setPos(0.998F, -3.8058F, 2.446F);
        head.addChild(cube_r361);
        setRotationAngle(cube_r361, -0.1985F, 0.6431F, 0.1333F);
        cube_r361.texOffs(47, 28).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r362 = new ModelRenderer(this);
        cube_r362.setPos(1.205F, -4.1394F, 2.5536F);
        head.addChild(cube_r362);
        setRotationAngle(cube_r362, -0.9318F, 1.0137F, 0.4327F);
        cube_r362.texOffs(21, 45).addBox(0.4F, -1.4F, 0.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r363 = new ModelRenderer(this);
        cube_r363.setPos(-1.8742F, -3.677F, 1.423F);
        head.addChild(cube_r363);
        setRotationAngle(cube_r363, -0.0265F, -1.0311F, -0.4301F);
        cube_r363.texOffs(14, 60).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r364 = new ModelRenderer(this);
        cube_r364.setPos(1.8312F, -3.6978F, 1.4358F);
        head.addChild(cube_r364);
        setRotationAngle(cube_r364, -0.0281F, 1.066F, 0.4282F);
        cube_r364.texOffs(14, 60).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r365 = new ModelRenderer(this);
        cube_r365.setPos(2.7738F, -2.5774F, 0.4514F);
        head.addChild(cube_r365);
        setRotationAngle(cube_r365, -0.082F, 1.2489F, 1.3545F);
        cube_r365.texOffs(47, 28).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        upper_neck = new ModelRenderer(this);
        upper_neck.setPos(1.439F, -30.405F, -14.0816F);
        bone2.addChild(upper_neck);

        cube_r228 = new ModelRenderer(this);
        cube_r228.setPos(-1.6018F, 0.9368F, -0.2614F);
        upper_neck.addChild(cube_r228);
        setRotationAngle(cube_r228, 1.0979F, 0.1054F, -0.0996F);
        cube_r228.texOffs(64, 40).addBox(-1.0F, -1.0F, -3.0F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r229 = new ModelRenderer(this);
        cube_r229.setPos(0.9954F, 3.8312F, -1.2718F);
        upper_neck.addChild(cube_r229);
        setRotationAngle(cube_r229, -0.0102F, 0.9449F, -2.4583F);
        cube_r229.texOffs(8, 0).addBox(0.0096F, -1.9544F, -0.1278F, 2.0F, 2.0F, 2.0F, 0.0F, false);
        cube_r229.texOffs(8, 0).addBox(0.0096F, -2.7544F, -0.7278F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r230 = new ModelRenderer(this);
        cube_r230.setPos(-3.8736F, 3.8312F, -1.2718F);
        upper_neck.addChild(cube_r230);
        setRotationAngle(cube_r230, -0.0102F, -0.9449F, 2.4583F);
        cube_r230.texOffs(8, 0).addBox(-2.0096F, -2.7544F, -0.7278F, 2.0F, 2.0F, 2.0F, 0.0F, true);
        cube_r230.texOffs(8, 0).addBox(-2.0096F, -1.9544F, -0.1278F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r231 = new ModelRenderer(this);
        cube_r231.setPos(-0.1754F, 2.686F, -1.3616F);
        upper_neck.addChild(cube_r231);
        setRotationAngle(cube_r231, 0.658F, 0.9634F, -2.5125F);
        cube_r231.texOffs(8, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r232 = new ModelRenderer(this);
        cube_r232.setPos(1.0996F, 1.7674F, -1.5366F);
        upper_neck.addChild(cube_r232);
        setRotationAngle(cube_r232, 0.2953F, -0.0234F, -3.0272F);
        cube_r232.texOffs(8, 0).addBox(-0.128F, 0.079F, -1.1066F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r233 = new ModelRenderer(this);
        cube_r233.setPos(-0.0368F, 2.2126F, -1.799F);
        upper_neck.addChild(cube_r233);
        setRotationAngle(cube_r233, 0.3017F, -1.2511F, 1.6279F);
        cube_r233.texOffs(8, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r234 = new ModelRenderer(this);
        cube_r234.setPos(-2.7028F, 2.686F, -1.3616F);
        upper_neck.addChild(cube_r234);
        setRotationAngle(cube_r234, 0.658F, -0.9634F, 2.5125F);
        cube_r234.texOffs(8, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r235 = new ModelRenderer(this);
        cube_r235.setPos(-3.9778F, 1.7674F, -1.5366F);
        upper_neck.addChild(cube_r235);
        setRotationAngle(cube_r235, 0.2953F, 0.0234F, 3.0272F);
        cube_r235.texOffs(8, 0).addBox(-1.872F, 0.079F, -1.1066F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r236 = new ModelRenderer(this);
        cube_r236.setPos(-2.8412F, 2.2126F, -1.799F);
        upper_neck.addChild(cube_r236);
        setRotationAngle(cube_r236, 0.3017F, 1.2511F, -1.6279F);
        cube_r236.texOffs(8, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r237 = new ModelRenderer(this);
        cube_r237.setPos(-1.2764F, 0.9368F, -0.2614F);
        upper_neck.addChild(cube_r237);
        setRotationAngle(cube_r237, 1.0979F, -0.1054F, 0.0996F);
        cube_r237.texOffs(64, 40).addBox(-1.0F, -1.0F, -3.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        lower_neck = new ModelRenderer(this);
        lower_neck.setPos(0.4748F, -37.792F, 3.2116F);
        bone2.addChild(lower_neck);
        setRotationAngle(lower_neck, -0.2182F, 0.0F, 0.0F);

        cube_r217 = new ModelRenderer(this);
        cube_r217.setPos(-1.877F, 15.3044F, -16.5618F);
        lower_neck.addChild(cube_r217);
        setRotationAngle(cube_r217, 0.1807F, 1.4682F, -1.7469F);
        cube_r217.texOffs(8, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r218 = new ModelRenderer(this);
        cube_r218.setPos(-3.0136F, 14.8128F, -16.402F);
        lower_neck.addChild(cube_r218);
        setRotationAngle(cube_r218, 0.0786F, -0.0019F, 3.0249F);
        cube_r218.texOffs(8, 0).addBox(-1.872F, 0.079F, -1.1066F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r219 = new ModelRenderer(this);
        cube_r219.setPos(-0.575F, 18.0622F, -15.176F);
        lower_neck.addChild(cube_r219);
        setRotationAngle(cube_r219, -0.1175F, -0.8989F, 3.1314F);
        cube_r219.texOffs(8, 0).addBox(-1.4F, -1.8F, -0.8F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r220 = new ModelRenderer(this);
        cube_r220.setPos(-0.3748F, 18.0622F, -15.176F);
        lower_neck.addChild(cube_r220);
        setRotationAngle(cube_r220, -0.1493F, 0.899F, -3.0607F);
        cube_r220.texOffs(8, 0).addBox(-0.6F, -1.8F, -0.8F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r221 = new ModelRenderer(this);
        cube_r221.setPos(-0.4748F, 17.3034F, -15.7006F);
        lower_neck.addChild(cube_r221);
        setRotationAngle(cube_r221, -0.3455F, 0.8102F, -2.9884F);
        cube_r221.texOffs(8, 0).addBox(-1.3312F, -0.8124F, -1.3024F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r222 = new ModelRenderer(this);
        cube_r222.setPos(-0.4748F, 17.3034F, -15.7006F);
        lower_neck.addChild(cube_r222);
        setRotationAngle(cube_r222, -0.3455F, -0.8102F, 2.9884F);
        cube_r222.texOffs(8, 0).addBox(-0.6688F, -0.8124F, -1.3024F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r223 = new ModelRenderer(this);
        cube_r223.setPos(-2.9094F, 16.7706F, -15.6968F);
        lower_neck.addChild(cube_r223);
        setRotationAngle(cube_r223, -0.3593F, -1.0578F, 2.7535F);
        cube_r223.texOffs(8, 0).addBox(-2.0096F, -1.9544F, -0.1278F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r224 = new ModelRenderer(this);
        cube_r224.setPos(-1.7386F, 15.6718F, -16.0322F);
        lower_neck.addChild(cube_r224);
        setRotationAngle(cube_r224, 0.2891F, -1.0641F, 2.8271F);
        cube_r224.texOffs(8, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r225 = new ModelRenderer(this);
        cube_r225.setPos(0.9272F, 15.3044F, -16.5618F);
        lower_neck.addChild(cube_r225);
        setRotationAngle(cube_r225, 0.1807F, -1.4682F, 1.7469F);
        cube_r225.texOffs(8, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r226 = new ModelRenderer(this);
        cube_r226.setPos(0.7888F, 15.6718F, -16.0322F);
        lower_neck.addChild(cube_r226);
        setRotationAngle(cube_r226, 0.2891F, 1.0641F, -2.8271F);
        cube_r226.texOffs(8, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r227 = new ModelRenderer(this);
        cube_r227.setPos(1.9596F, 16.7706F, -15.6968F);
        lower_neck.addChild(cube_r227);
        setRotationAngle(cube_r227, -0.3593F, 1.0578F, -2.7535F);
        cube_r227.texOffs(8, 0).addBox(0.0096F, -1.9544F, -0.1278F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cervical_spine = new ModelRenderer(this);
        cervical_spine.setPos(-1.2F, 4.6348F, -40.1972F);
        bone2.addChild(cervical_spine);
        setRotationAngle(cervical_spine, -2.7489F, 0.0F, -3.1416F);

        cube_r212 = new ModelRenderer(this);
        cube_r212.setPos(-1.209F, -34.9978F, -28.363F);
        cervical_spine.addChild(cube_r212);
        setRotationAngle(cube_r212, 0.5672F, 0.0F, 0.0F);
        cube_r212.texOffs(88, 36).addBox(-1.891F, 9.5664F, 10.0764F, 4.0F, 2.0F, 2.0F, 0.0F, false);
        cube_r212.texOffs(64, 82).addBox(-0.891F, 9.1664F, 10.9764F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r213 = new ModelRenderer(this);
        cube_r213.setPos(-1.209F, -34.9978F, -28.363F);
        cervical_spine.addChild(cube_r213);
        setRotationAngle(cube_r213, 0.5236F, 0.0F, 0.0F);
        cube_r213.texOffs(88, 36).addBox(-1.891F, 8.3248F, 12.4984F, 4.0F, 2.0F, 2.0F, 0.0F, false);
        cube_r213.texOffs(64, 22).addBox(-0.891F, 7.7964F, 13.391F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r214 = new ModelRenderer(this);
        cube_r214.setPos(-1.209F, -34.9978F, -28.363F);
        cervical_spine.addChild(cube_r214);
        setRotationAngle(cube_r214, 0.2618F, 0.0F, 0.0F);
        cube_r214.texOffs(88, 36).addBox(-1.891F, 3.2462F, 16.1456F, 4.0F, 2.0F, 2.0F, 0.0F, false);

        thighs = new ModelRenderer(this);
        thighs.setPos(0.0F, -14.4F, 19.2F);
        bone2.addChild(thighs);
        setRotationAngle(thighs, -0.1309F, 0.0F, 0.0F);

        right_thigh = new ModelRenderer(this);
        right_thigh.setPos(-6.549F, -9.4474F, 6.2028F);
        thighs.addChild(right_thigh);
        setRotationAngle(right_thigh, -0.5309F, -0.1247F, 0.0307F);

        cube_r196 = new ModelRenderer(this);
        cube_r196.setPos(2.5286F, 22.0532F, -22.3598F);
        right_thigh.addChild(cube_r196);
        setRotationAngle(cube_r196, 1.3614F, 0.0F, 0.0F);
        cube_r196.texOffs(4, 58).addBox(-3.5414F, 16.264F, 19.769F, 2.0F, 2.0F, 6.0F, 0.0F, true);

        cube_r197 = new ModelRenderer(this);
        cube_r197.setPos(-0.011F, 15.0754F, -2.432F);
        right_thigh.addChild(cube_r197);
        setRotationAngle(cube_r197, 0.5061F, 0.0F, 0.0F);
        cube_r197.texOffs(44, 0).addBox(-2.0F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r198 = new ModelRenderer(this);
        cube_r198.setPos(2.5286F, 22.0532F, -22.3598F);
        right_thigh.addChild(cube_r198);
        setRotationAngle(cube_r198, 1.0036F, 0.0F, 0.0F);
        cube_r198.texOffs(44, 0).addBox(-4.5396F, 12.4138F, 16.9938F, 4.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r199 = new ModelRenderer(this);
        cube_r199.setPos(2.5286F, 22.0532F, -22.3598F);
        right_thigh.addChild(cube_r199);
        setRotationAngle(cube_r199, 1.3526F, 0.0F, 0.0F);
        cube_r199.texOffs(4, 5).addBox(-4.5414F, 18.0504F, 12.9546F, 4.0F, 2.0F, 12.0F, 0.0F, true);

        cube_r200 = new ModelRenderer(this);
        cube_r200.setPos(2.5286F, 22.0532F, -22.3598F);
        right_thigh.addChild(cube_r200);
        setRotationAngle(cube_r200, 1.1575F, -0.7308F, 0.175F);
        cube_r200.texOffs(0, 38).addBox(10.1802F, 10.1426F, 12.3076F, 2.0F, 2.0F, 14.0F, 0.0F, true);

        cube_r201 = new ModelRenderer(this);
        cube_r201.setPos(2.5286F, 22.0532F, -22.3598F);
        right_thigh.addChild(cube_r201);
        setRotationAngle(cube_r201, -0.1047F, -1.2654F, 1.5708F);
        cube_r201.texOffs(24, 22).addBox(15.6562F, -2.1708F, 12.5076F, 2.0F, 2.0F, 16.0F, 0.0F, true);

        cube_r202 = new ModelRenderer(this);
        cube_r202.setPos(2.5286F, 22.0532F, -22.3598F);
        right_thigh.addChild(cube_r202);
        setRotationAngle(cube_r202, 1.2654F, 0.0F, 0.0F);
        cube_r202.texOffs(0, 0).addBox(-4.5286F, 14.0934F, 12.7562F, 4.0F, 4.0F, 16.0F, 0.0F, true);

        cube_r203 = new ModelRenderer(this);
        cube_r203.setPos(2.5286F, 22.0532F, -22.3598F);
        right_thigh.addChild(cube_r203);
        setRotationAngle(cube_r203, 1.2217F, 0.0F, 0.0F);
        cube_r203.texOffs(64, 82).addBox(-3.5414F, 12.5282F, 27.777F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        left_thigh = new ModelRenderer(this);
        left_thigh.setPos(6.549F, -9.4474F, 6.2028F);
        thighs.addChild(left_thigh);
        setRotationAngle(left_thigh, -0.5309F, 0.1247F, -0.0307F);

        cube_r204 = new ModelRenderer(this);
        cube_r204.setPos(0.011F, 15.0754F, -2.432F);
        left_thigh.addChild(cube_r204);
        setRotationAngle(cube_r204, 0.5061F, 0.0F, 0.0F);
        cube_r204.texOffs(44, 0).addBox(-2.0F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r205 = new ModelRenderer(this);
        cube_r205.setPos(-2.5106F, 22.0524F, -22.3576F);
        left_thigh.addChild(cube_r205);
        setRotationAngle(cube_r205, 1.3614F, 0.0F, 0.0F);
        cube_r205.texOffs(4, 58).addBox(1.5234F, 16.262F, 19.7678F, 2.0F, 2.0F, 6.0F, 0.0F, false);

        cube_r206 = new ModelRenderer(this);
        cube_r206.setPos(-2.5106F, 22.0524F, -22.3576F);
        left_thigh.addChild(cube_r206);
        setRotationAngle(cube_r206, 1.0036F, 0.0F, 0.0F);
        cube_r206.texOffs(44, 0).addBox(0.5216F, 12.4122F, 16.992F, 4.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r207 = new ModelRenderer(this);
        cube_r207.setPos(-2.5106F, 22.0524F, -22.3576F);
        left_thigh.addChild(cube_r207);
        setRotationAngle(cube_r207, 1.3526F, 0.0F, 0.0F);
        cube_r207.texOffs(4, 5).addBox(0.5234F, 18.0484F, 12.9536F, 4.0F, 2.0F, 12.0F, 0.0F, false);

        cube_r208 = new ModelRenderer(this);
        cube_r208.setPos(-2.5106F, 22.0524F, -22.3576F);
        left_thigh.addChild(cube_r208);
        setRotationAngle(cube_r208, 1.1575F, 0.7308F, -0.175F);
        cube_r208.texOffs(0, 38).addBox(-12.192F, 10.1292F, 12.3044F, 2.0F, 2.0F, 14.0F, 0.0F, false);

        cube_r209 = new ModelRenderer(this);
        cube_r209.setPos(-2.5106F, 22.0524F, -22.3576F);
        left_thigh.addChild(cube_r209);
        setRotationAngle(cube_r209, -0.1047F, 1.2654F, -1.5708F);
        cube_r209.texOffs(0, 1).addBox(-17.6542F, -2.1886F, 12.5044F, 2.0F, 2.0F, 16.0F, 0.0F, false);

        cube_r210 = new ModelRenderer(this);
        cube_r210.setPos(-2.5106F, 22.0524F, -22.3576F);
        left_thigh.addChild(cube_r210);
        setRotationAngle(cube_r210, 1.2654F, 0.0F, 0.0F);
        cube_r210.texOffs(0, 0).addBox(0.5106F, 14.0916F, 12.7548F, 4.0F, 4.0F, 16.0F, 0.0F, false);

        cube_r211 = new ModelRenderer(this);
        cube_r211.setPos(-2.5106F, 22.0524F, -22.3576F);
        left_thigh.addChild(cube_r211);
        setRotationAngle(cube_r211, 1.2217F, 0.0F, 0.0F);
        cube_r211.texOffs(64, 82).addBox(1.5234F, 12.5262F, 27.7756F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        legs = new ModelRenderer(this);
        legs.setPos(0.0F, -0.4F, -3.2F);
        bone2.addChild(legs);

        right_leg = new ModelRenderer(this);
        right_leg.setPos(-6.7F, -19.8678F, 29.9246F);
        legs.addChild(right_leg);
        setRotationAngle(right_leg, 0.0436F, 0.0F, 0.0F);

        cube_r160 = new ModelRenderer(this);
        cube_r160.setPos(6.209F, -2.105F, -31.176F);
        right_leg.addChild(cube_r160);
        setRotationAngle(cube_r160, -1.8359F, -0.1704F, 0.0381F);
        cube_r160.texOffs(64, 82).addBox(-1.725F, -35.2036F, 10.539F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r161 = new ModelRenderer(this);
        cube_r161.setPos(6.209F, -2.105F, -31.176F);
        right_leg.addChild(cube_r161);
        setRotationAngle(cube_r161, -0.8323F, -0.1704F, 0.0381F);
        cube_r161.texOffs(18, 40).addBox(-2.7746F, -11.1392F, 34.9062F, 4.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r162 = new ModelRenderer(this);
        cube_r162.setPos(6.209F, -2.105F, -31.176F);
        right_leg.addChild(cube_r162);
        setRotationAngle(cube_r162, -1.6417F, -0.7516F, 1.5285F);
        cube_r162.texOffs(64, 82).addBox(33.2348F, -11.1842F, 4.549F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r163 = new ModelRenderer(this);
        cube_r163.setPos(6.209F, -2.105F, -31.176F);
        right_leg.addChild(cube_r163);
        setRotationAngle(cube_r163, -1.2014F, 0.6738F, -1.1614F);
        cube_r163.texOffs(64, 82).addBox(-35.5082F, -11.1842F, 3.078F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r164 = new ModelRenderer(this);
        cube_r164.setPos(6.209F, -2.105F, -31.176F);
        right_leg.addChild(cube_r164);
        setRotationAngle(cube_r164, 0.0398F, -0.0396F, 0.0434F);
        cube_r164.texOffs(58, 74).addBox(-4.7696F, 21.4528F, 29.6738F, 2.0F, 2.0F, 6.0F, 0.0F, false);

        cube_r165 = new ModelRenderer(this);
        cube_r165.setPos(6.209F, -2.105F, -31.176F);
        right_leg.addChild(cube_r165);
        setRotationAngle(cube_r165, 0.0417F, -0.3011F, 0.0326F);
        cube_r165.texOffs(58, 74).addBox(1.332F, 21.4528F, 29.8632F, 2.0F, 2.0F, 6.0F, 0.0F, false);

        cube_r166 = new ModelRenderer(this);
        cube_r166.setPos(6.209F, -2.105F, -31.176F);
        right_leg.addChild(cube_r166);
        setRotationAngle(cube_r166, -0.0469F, -0.1704F, 0.0381F);
        cube_r166.texOffs(8, 46).addBox(-1.725F, 18.5622F, 31.6816F, 2.0F, 2.0F, 6.0F, 0.0F, false);

        cube_r167 = new ModelRenderer(this);
        cube_r167.setPos(6.209F, -2.105F, -31.176F);
        right_leg.addChild(cube_r167);
        setRotationAngle(cube_r167, 1.8293F, -0.1704F, 0.0381F);
        cube_r167.texOffs(32, 84).addBox(-1.725F, 24.9214F, -27.8406F, 2.0F, 2.0F, 4.0F, 0.0F, false);

        cube_r168 = new ModelRenderer(this);
        cube_r168.setPos(6.209F, -2.105F, -31.176F);
        right_leg.addChild(cube_r168);
        setRotationAngle(cube_r168, -0.3087F, -0.1704F, 0.0381F);
        cube_r168.texOffs(12, 32).addBox(-2.725F, 8.6722F, 35.0314F, 4.0F, 2.0F, 4.0F, 0.0F, false);

        cube_r169 = new ModelRenderer(this);
        cube_r169.setPos(6.209F, -2.105F, -31.176F);
        right_leg.addChild(cube_r169);
        setRotationAngle(cube_r169, -1.6613F, -0.1704F, 0.0381F);
        cube_r169.texOffs(12, 32).addBox(-2.7346F, -33.1076F, 15.4252F, 4.0F, 2.0F, 4.0F, 0.0F, false);

        cube_r170 = new ModelRenderer(this);
        cube_r170.setPos(6.209F, -2.105F, -31.176F);
        right_leg.addChild(cube_r170);
        setRotationAngle(cube_r170, 3.1383F, -0.1704F, 0.0381F);
        cube_r170.texOffs(44, 26).addBox(-2.725F, -19.6992F, -31.3486F, 4.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r171 = new ModelRenderer(this);
        cube_r171.setPos(6.209F, -2.105F, -31.176F);
        right_leg.addChild(cube_r171);
        setRotationAngle(cube_r171, -0.8747F, -0.04F, 0.0263F);
        cube_r171.texOffs(92, 36).addBox(-5.203F, -15.1968F, 38.7478F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r172 = new ModelRenderer(this);
        cube_r172.setPos(6.209F, -2.105F, -31.176F);
        right_leg.addChild(cube_r172);
        setRotationAngle(cube_r172, -0.8788F, -0.3007F, 0.0505F);
        cube_r172.texOffs(90, 36).addBox(1.7396F, -15.3366F, 38.8876F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r173 = new ModelRenderer(this);
        cube_r173.setPos(6.209F, -2.105F, -31.176F);
        right_leg.addChild(cube_r173);
        setRotationAngle(cube_r173, -1.3546F, -0.04F, 0.0263F);
        cube_r173.texOffs(91, 36).addBox(-5.203F, -32.208F, 29.1884F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r174 = new ModelRenderer(this);
        cube_r174.setPos(6.209F, -2.105F, -31.176F);
        right_leg.addChild(cube_r174);
        setRotationAngle(cube_r174, -1.3588F, -0.3007F, 0.0505F);
        cube_r174.texOffs(90, 36).addBox(1.7396F, -32.3812F, 29.2026F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r175 = new ModelRenderer(this);
        cube_r175.setPos(-0.202F, 8.6744F, -0.5516F);
        right_leg.addChild(cube_r175);
        setRotationAngle(cube_r175, -1.6177F, -0.1704F, 0.0381F);
        cube_r175.texOffs(0, 0).addBox(-2.0F, -0.4F, -9.0F, 4.0F, 2.0F, 16.0F, 0.0F, false);

        cube_r176 = new ModelRenderer(this);
        cube_r176.setPos(6.209F, -2.105F, -31.176F);
        right_leg.addChild(cube_r176);
        setRotationAngle(cube_r176, -1.705F, -0.1704F, 0.0381F);
        cube_r176.texOffs(0, 0).addBox(-2.725F, -33.7904F, -2.2556F, 4.0F, 2.0F, 16.0F, 0.0F, false);
        cube_r176.texOffs(32, 84).addBox(-1.725F, -34.1904F, 9.7444F, 2.0F, 2.0F, 4.0F, 0.0F, false);

        cube_r177 = new ModelRenderer(this);
        cube_r177.setPos(6.209F, -2.105F, -31.176F);
        right_leg.addChild(cube_r177);
        setRotationAngle(cube_r177, -1.6177F, -0.1704F, 0.0381F);
        cube_r177.texOffs(4, 58).addBox(-1.725F, -33.1892F, 6.8076F, 2.0F, 2.0F, 6.0F, 0.0F, false);

        left_leg = new ModelRenderer(this);
        left_leg.setPos(6.7F, -19.8678F, 30.1246F);
        legs.addChild(left_leg);
        setRotationAngle(left_leg, 0.0436F, 0.0F, 0.0F);

        cube_r178 = new ModelRenderer(this);
        cube_r178.setPos(0.202F, 8.6398F, -0.7486F);
        left_leg.addChild(cube_r178);
        setRotationAngle(cube_r178, -1.6177F, 0.1704F, -0.0381F);
        cube_r178.texOffs(0, 0).addBox(-2.0F, -0.4F, -9.0F, 4.0F, 2.0F, 16.0F, 0.0F, false);

        cube_r179 = new ModelRenderer(this);
        cube_r179.setPos(-6.191F, -2.1398F, -31.373F);
        left_leg.addChild(cube_r179);
        setRotationAngle(cube_r179, -1.705F, 0.1704F, -0.0381F);
        cube_r179.texOffs(0, 1).addBox(-1.2928F, -33.7874F, -2.256F, 4.0F, 2.0F, 16.0F, 0.0F, false);
        cube_r179.texOffs(32, 84).addBox(-0.2928F, -34.1874F, 9.744F, 2.0F, 2.0F, 4.0F, 0.0F, false);

        cube_r180 = new ModelRenderer(this);
        cube_r180.setPos(-6.191F, -2.1398F, -31.373F);
        left_leg.addChild(cube_r180);
        setRotationAngle(cube_r180, -1.6177F, 0.1704F, -0.0381F);
        cube_r180.texOffs(4, 58).addBox(-0.2928F, -33.186F, 6.807F, 2.0F, 2.0F, 6.0F, 0.0F, false);

        cube_r181 = new ModelRenderer(this);
        cube_r181.setPos(-6.191F, -2.1398F, -31.373F);
        left_leg.addChild(cube_r181);
        setRotationAngle(cube_r181, -1.3588F, 0.3007F, -0.0505F);
        cube_r181.texOffs(89, 36).addBox(-3.757F, -32.376F, 29.2006F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r182 = new ModelRenderer(this);
        cube_r182.setPos(-6.191F, -2.1398F, -31.373F);
        left_leg.addChild(cube_r182);
        setRotationAngle(cube_r182, -0.8788F, 0.3007F, -0.0505F);
        cube_r182.texOffs(88, 36).addBox(-3.757F, -15.333F, 38.8834F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r183 = new ModelRenderer(this);
        cube_r183.setPos(-6.191F, -2.1398F, -31.373F);
        left_leg.addChild(cube_r183);
        setRotationAngle(cube_r183, -1.3546F, 0.04F, -0.0263F);
        cube_r183.texOffs(91, 36).addBox(3.1848F, -32.2074F, 29.1878F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r184 = new ModelRenderer(this);
        cube_r184.setPos(-6.191F, -2.1398F, -31.373F);
        left_leg.addChild(cube_r184);
        setRotationAngle(cube_r184, -0.8747F, 0.04F, -0.0263F);
        cube_r184.texOffs(90, 36).addBox(3.1848F, -15.1966F, 38.747F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r185 = new ModelRenderer(this);
        cube_r185.setPos(-6.191F, -2.1398F, -31.373F);
        left_leg.addChild(cube_r185);
        setRotationAngle(cube_r185, 3.1383F, 0.1704F, -0.0381F);
        cube_r185.texOffs(44, 26).addBox(-1.2928F, -19.6984F, -31.3456F, 4.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r186 = new ModelRenderer(this);
        cube_r186.setPos(-6.191F, -2.1398F, -31.373F);
        left_leg.addChild(cube_r186);
        setRotationAngle(cube_r186, -1.6613F, 0.1704F, -0.0381F);
        cube_r186.texOffs(12, 32).addBox(-1.249F, -33.1044F, 15.4248F, 4.0F, 2.0F, 4.0F, 0.0F, false);

        cube_r187 = new ModelRenderer(this);
        cube_r187.setPos(-6.191F, -2.1398F, -31.373F);
        left_leg.addChild(cube_r187);
        setRotationAngle(cube_r187, -0.3087F, 0.1704F, -0.0381F);
        cube_r187.texOffs(12, 32).addBox(-1.2928F, 8.6726F, 35.0282F, 4.0F, 2.0F, 4.0F, 0.0F, false);

        cube_r188 = new ModelRenderer(this);
        cube_r188.setPos(-6.191F, -2.1398F, -31.373F);
        left_leg.addChild(cube_r188);
        setRotationAngle(cube_r188, 1.8293F, 0.1704F, -0.0381F);
        cube_r188.texOffs(32, 84).addBox(-0.2928F, 24.9186F, -27.839F, 2.0F, 2.0F, 4.0F, 0.0F, false);

        cube_r189 = new ModelRenderer(this);
        cube_r189.setPos(-6.191F, -2.1398F, -31.373F);
        left_leg.addChild(cube_r189);
        setRotationAngle(cube_r189, -0.0469F, 0.1704F, -0.0381F);
        cube_r189.texOffs(8, 46).addBox(-0.2928F, 18.5616F, 31.6784F, 2.0F, 2.0F, 6.0F, 0.0F, false);

        cube_r190 = new ModelRenderer(this);
        cube_r190.setPos(-6.191F, -2.1398F, -31.373F);
        left_leg.addChild(cube_r190);
        setRotationAngle(cube_r190, 0.0417F, 0.3011F, -0.0326F);
        cube_r190.texOffs(58, 66).addBox(-3.3494F, 21.452F, 29.8578F, 2.0F, 2.0F, 6.0F, 0.0F, false);

        cube_r191 = new ModelRenderer(this);
        cube_r191.setPos(-6.191F, -2.1398F, -31.373F);
        left_leg.addChild(cube_r191);
        setRotationAngle(cube_r191, 0.0398F, 0.0396F, -0.0434F);
        cube_r191.texOffs(58, 74).addBox(2.7514F, 21.452F, 29.6732F, 2.0F, 2.0F, 6.0F, 0.0F, false);

        cube_r192 = new ModelRenderer(this);
        cube_r192.setPos(-6.191F, -2.1398F, -31.373F);
        left_leg.addChild(cube_r192);
        setRotationAngle(cube_r192, -1.2014F, -0.6738F, 1.1614F);
        cube_r192.texOffs(64, 82).addBox(33.5026F, -11.1726F, 3.0952F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r193 = new ModelRenderer(this);
        cube_r193.setPos(-6.191F, -2.1398F, -31.373F);
        left_leg.addChild(cube_r193);
        setRotationAngle(cube_r193, -1.6417F, 0.7516F, -1.5285F);
        cube_r193.texOffs(64, 82).addBox(-35.2354F, -11.1726F, 4.531F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r194 = new ModelRenderer(this);
        cube_r194.setPos(-6.191F, -2.1398F, -31.373F);
        left_leg.addChild(cube_r194);
        setRotationAngle(cube_r194, -0.8323F, 0.1704F, -0.0381F);
        cube_r194.texOffs(18, 40).addBox(-1.249F, -11.1374F, 34.9036F, 4.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r195 = new ModelRenderer(this);
        cube_r195.setPos(-6.191F, -2.1398F, -31.373F);
        left_leg.addChild(cube_r195);
        setRotationAngle(cube_r195, -1.8359F, 0.1704F, -0.0381F);
        cube_r195.texOffs(63, 82).addBox(-0.2928F, -35.2004F, 10.5392F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setPos(0.0F, -16.6942F, 29.0472F);
        bone2.addChild(body);
        setRotationAngle(body, 1.9199F, 0.0F, 0.0F);

        abdomino_thoracic_region = new ModelRenderer(this);
        abdomino_thoracic_region.setPos(-2.1624F, -26.3374F, 10.486F);
        body.addChild(abdomino_thoracic_region);
        setRotationAngle(abdomino_thoracic_region, -1.5708F, 0.0F, 0.0F);

        bone = new ModelRenderer(this);
        bone.setPos(2.1624F, -10.7678F, -11.8514F);
        abdomino_thoracic_region.addChild(bone);

        body_r8_r1 = new ModelRenderer(this);
        body_r8_r1.setPos(0.009F, 10.9724F, 10.381F);
        bone.addChild(body_r8_r1);
        setRotationAngle(body_r8_r1, 0.3438F, -0.2509F, -2.2376F);
        body_r8_r1.texOffs(64, 40).addBox(1.2874F, 5.1484F, -14.7084F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        body_r7_r1 = new ModelRenderer(this);
        body_r7_r1.setPos(0.009F, 10.9724F, 10.381F);
        bone.addChild(body_r7_r1);
        setRotationAngle(body_r7_r1, 0.3438F, 0.2509F, 2.2376F);
        body_r7_r1.texOffs(64, 40).addBox(-3.2764F, 5.1628F, -14.7106F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        body_r6_r1 = new ModelRenderer(this);
        body_r6_r1.setPos(0.009F, 10.9724F, 10.381F);
        bone.addChild(body_r6_r1);
        setRotationAngle(body_r6_r1, 2.2179F, 0.1059F, -0.139F);
        body_r6_r1.texOffs(28, 9).addBox(-4.2728F, -1.9562F, 14.215F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        body_r5_r1 = new ModelRenderer(this);
        body_r5_r1.setPos(0.009F, 10.9724F, 10.381F);
        bone.addChild(body_r5_r1);
        setRotationAngle(body_r5_r1, 2.2179F, -0.1059F, 0.139F);
        body_r5_r1.texOffs(8, 0).addBox(2.2548F, -1.9562F, 14.2118F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        body_r4_r1 = new ModelRenderer(this);
        body_r4_r1.setPos(0.009F, 10.9724F, 10.381F);
        bone.addChild(body_r4_r1);
        setRotationAngle(body_r4_r1, 2.2253F, 0.0F, 0.0F);
        body_r4_r1.texOffs(50, 14).addBox(-6.009F, -2.3562F, 13.727F, 12.0F, 2.0F, 2.0F, 0.0F, false);

        body_r3_r1 = new ModelRenderer(this);
        body_r3_r1.setPos(0.009F, 10.9724F, 10.381F);
        bone.addChild(body_r3_r1);
        setRotationAngle(body_r3_r1, 1.9635F, 0.0F, 0.0F);
        body_r3_r1.texOffs(48, 13).addBox(-7.009F, -4.3918F, 13.1098F, 14.0F, 2.0F, 2.0F, 0.0F, false);

        body_r2_r1 = new ModelRenderer(this);
        body_r2_r1.setPos(0.009F, 10.9724F, 10.381F);
        bone.addChild(body_r2_r1);
        setRotationAngle(body_r2_r1, 1.2217F, 0.0F, 0.0F);
        body_r2_r1.texOffs(46, 56).addBox(-7.009F, -11.9692F, 7.5786F, 14.0F, 2.0F, 2.0F, 0.0F, false);

        body_r1_r1 = new ModelRenderer(this);
        body_r1_r1.setPos(0.009F, 10.9724F, 10.381F);
        bone.addChild(body_r1_r1);
        setRotationAngle(body_r1_r1, 1.0472F, 0.0F, 0.0F);
        body_r1_r1.texOffs(32, 40).addBox(-7.009F, -11.8544F, 3.3118F, 14.0F, 14.0F, 2.0F, 0.0F, false);

        body_r6_r2 = new ModelRenderer(this);
        body_r6_r2.setPos(0.0556F, 11.3868F, 10.6008F);
        bone.addChild(body_r6_r2);
        setRotationAngle(body_r6_r2, 0.5551F, 0.6156F, 0.3351F);
        body_r6_r2.texOffs(64, 42).addBox(0.3344F, -0.2168F, 6.9332F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        body_r5_r2 = new ModelRenderer(this);
        body_r5_r2.setPos(-0.0342F, 11.0256F, 10.4288F);
        bone.addChild(body_r5_r2);
        setRotationAngle(body_r5_r2, 0.5551F, -0.6156F, -0.3351F);
        body_r5_r2.texOffs(64, 42).addBox(-2.3484F, 0.1834F, 6.9448F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        body_r6_r3 = new ModelRenderer(this);
        body_r6_r3.setPos(0.009F, 10.9724F, 10.381F);
        bone.addChild(body_r6_r3);
        setRotationAngle(body_r6_r3, 1.0743F, 0.3713F, 0.593F);
        body_r6_r3.texOffs(0, 0).addBox(0.0908F, -12.2024F, 5.3336F, 2.0F, 12.0F, 4.0F, 0.0F, false);

        body_r7_r2 = new ModelRenderer(this);
        body_r7_r2.setPos(0.009F, 10.9724F, 10.381F);
        bone.addChild(body_r7_r2);
        setRotationAngle(body_r7_r2, 1.0743F, -0.3713F, -0.593F);
        body_r7_r2.texOffs(0, 0).addBox(-2.105F, -12.2024F, 5.3452F, 2.0F, 12.0F, 4.0F, 0.0F, false);

        body_r10_r1 = new ModelRenderer(this);
        body_r10_r1.setPos(-0.009F, 10.9724F, 10.381F);
        bone.addChild(body_r10_r1);
        setRotationAngle(body_r10_r1, -1.7614F, -0.4973F, -1.2661F);
        body_r10_r1.texOffs(0, 54).addBox(2.2896F, 4.0338F, -8.579F, 2.0F, 8.0F, 2.0F, 0.0F, true);

        body_r9_r1 = new ModelRenderer(this);
        body_r9_r1.setPos(0.009F, 10.9724F, 10.381F);
        bone.addChild(body_r9_r1);
        setRotationAngle(body_r9_r1, -1.7614F, 0.4973F, 1.2661F);
        body_r9_r1.texOffs(0, 54).addBox(-4.2896F, 4.0338F, -8.579F, 2.0F, 8.0F, 2.0F, 0.0F, false);

        body_r10_r2 = new ModelRenderer(this);
        body_r10_r2.setPos(-6.4612F, 5.728F, 11.3868F);
        bone.addChild(body_r10_r2);
        setRotationAngle(body_r10_r2, -1.0467F, -0.1737F, -2.9428F);
        body_r10_r2.texOffs(92, 92).addBox(-0.9388F, -1.9286F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, true);

        body_r9_r2 = new ModelRenderer(this);
        body_r9_r2.setPos(6.4612F, 5.728F, 11.3868F);
        bone.addChild(body_r9_r2);
        setRotationAngle(body_r9_r2, -1.0467F, 0.1737F, 2.9428F);
        body_r9_r2.texOffs(67, 16).addBox(-1.0612F, -1.9286F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

        body_r13_r1 = new ModelRenderer(this);
        body_r13_r1.setPos(-0.009F, 10.9724F, 10.381F);
        bone.addChild(body_r13_r1);
        setRotationAngle(body_r13_r1, -1.0289F, -0.1765F, -2.9128F);
        body_r13_r1.texOffs(92, 92).addBox(6.5858F, 8.1396F, 2.0626F, 2.0F, 4.0F, 2.0F, 0.0F, true);

        body_r12_r1 = new ModelRenderer(this);
        body_r12_r1.setPos(0.009F, 10.9724F, 10.381F);
        bone.addChild(body_r12_r1);
        setRotationAngle(body_r12_r1, -1.0289F, 0.1765F, 2.9128F);
        body_r12_r1.texOffs(92, 92).addBox(-8.5858F, 8.1396F, 2.0626F, 2.0F, 4.0F, 2.0F, 0.0F, false);

        body_r3_r2 = new ModelRenderer(this);
        body_r3_r2.setPos(0.009F, 10.9724F, 10.381F);
        bone.addChild(body_r3_r2);
        setRotationAngle(body_r3_r2, 0.9599F, 0.0F, 0.0F);
        body_r3_r2.texOffs(78, 88).addBox(1.991F, -2.1522F, 5.1482F, 4.0F, 2.0F, 2.0F, 0.0F, false);
        body_r3_r2.texOffs(78, 88).addBox(-6.009F, -2.1522F, 5.1482F, 4.0F, 2.0F, 2.0F, 0.0F, false);

        body_r5_r3 = new ModelRenderer(this);
        body_r5_r3.setPos(0.009F, 10.9724F, 10.381F);
        bone.addChild(body_r5_r3);
        setRotationAngle(body_r5_r3, 0.6894F, 0.5639F, 0.417F);
        body_r5_r3.texOffs(92, 92).addBox(0.4266F, -2.9744F, 6.9282F, 2.0F, 4.0F, 2.0F, 0.0F, false);

        body_r6_r4 = new ModelRenderer(this);
        body_r6_r4.setPos(0.009F, 10.9724F, 10.381F);
        bone.addChild(body_r6_r4);
        setRotationAngle(body_r6_r4, 0.6894F, -0.5639F, -0.417F);
        body_r6_r4.texOffs(92, 92).addBox(-2.4406F, -2.9744F, 6.9398F, 2.0F, 4.0F, 2.0F, 0.0F, false);

        body_r6_r5 = new ModelRenderer(this);
        body_r6_r5.setPos(0.009F, 10.9724F, 10.381F);
        bone.addChild(body_r6_r5);
        setRotationAngle(body_r6_r5, 1.5527F, 1.0031F, 1.5513F);
        body_r6_r5.texOffs(92, 92).addBox(-5.271F, -2.9744F, 5.5888F, 2.0F, 4.0F, 2.0F, 0.0F, false);

        body_r7_r3 = new ModelRenderer(this);
        body_r7_r3.setPos(0.009F, 10.9724F, 10.381F);
        bone.addChild(body_r7_r3);
        setRotationAngle(body_r7_r3, 1.5527F, -1.0031F, -1.5513F);
        body_r7_r3.texOffs(92, 92).addBox(3.2708F, -2.9744F, 5.607F, 2.0F, 4.0F, 2.0F, 0.0F, false);

        body_r2_r2 = new ModelRenderer(this);
        body_r2_r2.setPos(0.009F, 10.9724F, 10.381F);
        bone.addChild(body_r2_r2);
        setRotationAngle(body_r2_r2, 0.5672F, 0.0F, 0.0F);
        body_r2_r2.texOffs(62, 18).addBox(-6.009F, -0.924F, 4.6222F, 12.0F, 2.0F, 2.0F, 0.0F, false);
        body_r2_r2.texOffs(78, 88).addBox(1.991F, -2.924F, 4.6222F, 4.0F, 2.0F, 2.0F, 0.0F, false);
        body_r2_r2.texOffs(66, 88).addBox(-6.009F, -2.924F, 4.6222F, 4.0F, 2.0F, 2.0F, 0.0F, false);

        body_r3_r3 = new ModelRenderer(this);
        body_r3_r3.setPos(0.009F, 10.9724F, 10.381F);
        bone.addChild(body_r3_r3);
        setRotationAngle(body_r3_r3, 0.4363F, 0.0F, 0.0F);
        body_r3_r3.texOffs(56, 60).addBox(-6.009F, 0.1484F, 4.6966F, 12.0F, 4.0F, 2.0F, 0.0F, false);

        cube_r52 = new ModelRenderer(this);
        cube_r52.setPos(0.009F, 10.9724F, 10.381F);
        bone.addChild(cube_r52);
        setRotationAngle(cube_r52, 0.6981F, 0.0F, 0.0F);
        cube_r52.texOffs(14, 16).addBox(-7.009F, 3.7346F, 3.8862F, 14.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r53 = new ModelRenderer(this);
        cube_r53.setPos(0.009F, 10.9724F, 10.381F);
        bone.addChild(cube_r53);
        setRotationAngle(cube_r53, 0.9182F, 0.0561F, 0.0669F);
        cube_r53.texOffs(62, 34).addBox(-5.7762F, 0.394F, 0.6492F, 4.0F, 4.0F, 2.0F, 0.0F, false);

        cube_r54 = new ModelRenderer(this);
        cube_r54.setPos(0.009F, 10.9724F, 10.381F);
        bone.addChild(cube_r54);
        setRotationAngle(cube_r54, 0.9182F, -0.0561F, -0.0669F);
        cube_r54.texOffs(62, 34).addBox(1.758F, 0.3942F, 0.6508F, 4.0F, 4.0F, 2.0F, 0.0F, false);

        cube_r55 = new ModelRenderer(this);
        cube_r55.setPos(-0.1946F, 10.812F, 10.2632F);
        bone.addChild(cube_r55);
        setRotationAngle(cube_r55, 0.9163F, 0.0427F, -0.0877F);
        cube_r55.texOffs(35, 43).addBox(-7.632F, 3.336F, 1.2934F, 2.0F, 2.0F, 4.0F, 0.0F, true);

        cube_r56 = new ModelRenderer(this);
        cube_r56.setPos(0.1946F, 10.812F, 10.2632F);
        bone.addChild(cube_r56);
        setRotationAngle(cube_r56, 0.9163F, -0.0427F, 0.0877F);
        cube_r56.texOffs(35, 43).addBox(5.632F, 3.336F, 1.2934F, 2.0F, 2.0F, 4.0F, 0.0F, false);

        cube_r57 = new ModelRenderer(this);
        cube_r57.setPos(0.009F, 10.9724F, 10.381F);
        bone.addChild(cube_r57);
        setRotationAngle(cube_r57, 0.9168F, -0.0266F, -0.0346F);
        cube_r57.texOffs(23, 13).addBox(-7.5132F, -0.2026F, 1.3578F, 4.0F, 4.0F, 4.0F, 0.0F, false);

        cube_r58 = new ModelRenderer(this);
        cube_r58.setPos(0.009F, 10.9724F, 10.381F);
        bone.addChild(cube_r58);
        setRotationAngle(cube_r58, 0.9168F, 0.0266F, 0.0346F);
        cube_r58.texOffs(23, 12).addBox(3.495F, -0.2026F, 1.357F, 4.0F, 4.0F, 4.0F, 0.0F, false);

        cube_r59 = new ModelRenderer(this);
        cube_r59.setPos(0.009F, 10.9724F, 10.381F);
        bone.addChild(cube_r59);
        setRotationAngle(cube_r59, 0.6333F, 0.1133F, -0.0657F);
        cube_r59.texOffs(18, 40).addBox(3.4656F, -3.5604F, 1.392F, 4.0F, 4.0F, 2.0F, 0.0F, false);

        cube_r60 = new ModelRenderer(this);
        cube_r60.setPos(0.009F, 10.9724F, 10.381F);
        bone.addChild(cube_r60);
        setRotationAngle(cube_r60, 0.6333F, -0.1133F, 0.0657F);
        cube_r60.texOffs(18, 40).addBox(-7.4836F, -3.5582F, 1.393F, 4.0F, 4.0F, 2.0F, 0.0F, false);

        cube_r61 = new ModelRenderer(this);
        cube_r61.setPos(0.009F, 10.9724F, 10.381F);
        bone.addChild(cube_r61);
        setRotationAngle(cube_r61, 1.0468F, -0.0378F, 0.0218F);
        cube_r61.texOffs(24, 22).addBox(3.6632F, -11.4256F, 2.4906F, 4.0F, 12.0F, 2.0F, 0.0F, false);

        cube_r62 = new ModelRenderer(this);
        cube_r62.setPos(0.009F, 10.9724F, 10.381F);
        bone.addChild(cube_r62);
        setRotationAngle(cube_r62, 1.0468F, 0.0378F, -0.0218F);
        cube_r62.texOffs(0, 20).addBox(-7.6814F, -11.4264F, 2.4906F, 4.0F, 12.0F, 2.0F, 0.0F, false);

        cube_r63 = new ModelRenderer(this);
        cube_r63.setPos(0.009F, 10.9724F, 10.381F);
        bone.addChild(cube_r63);
        setRotationAngle(cube_r63, 1.1631F, -0.3409F, -0.5717F);
        cube_r63.texOffs(18, 40).addBox(3.5596F, -11.4256F, -2.6838F, 4.0F, 8.0F, 2.0F, 0.0F, false);
        cube_r63.texOffs(6, 66).addBox(5.5596F, -3.4256F, -2.6838F, 2.0F, 4.0F, 2.0F, 0.0F, false);

        cube_r64 = new ModelRenderer(this);
        cube_r64.setPos(0.009F, 10.9724F, 10.381F);
        bone.addChild(cube_r64);
        setRotationAngle(cube_r64, 0.6831F, -0.3409F, -0.5717F);
        cube_r64.texOffs(44, 22).addBox(3.5596F, -12.88F, -7.669F, 4.0F, 4.0F, 2.0F, 0.0F, false);

        cube_r65 = new ModelRenderer(this);
        cube_r65.setPos(0.009F, 10.9724F, 10.381F);
        bone.addChild(cube_r65);
        setRotationAngle(cube_r65, 1.2774F, -0.9002F, -1.5087F);
        cube_r65.texOffs(38, 46).addBox(-1.447F, -13.08F, -10.657F, 2.0F, 4.0F, 2.0F, 0.0F, false);

        cube_r66 = new ModelRenderer(this);
        cube_r66.setPos(0.009F, 10.9724F, 10.381F);
        bone.addChild(cube_r66);
        setRotationAngle(cube_r66, 1.2774F, 0.9002F, 1.5087F);
        cube_r66.texOffs(34, 46).addBox(-0.5538F, -13.0756F, -10.6746F, 2.0F, 4.0F, 2.0F, 0.0F, false);

        cube_r67 = new ModelRenderer(this);
        cube_r67.setPos(0.009F, 10.9724F, 10.381F);
        bone.addChild(cube_r67);
        setRotationAngle(cube_r67, 1.5985F, 0.9133F, 1.921F);
        cube_r67.texOffs(48, 12).addBox(0.003F, -12.34F, -10.5806F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r68 = new ModelRenderer(this);
        cube_r68.setPos(0.009F, 10.9724F, 10.381F);
        bone.addChild(cube_r68);
        setRotationAngle(cube_r68, 1.5985F, -0.9133F, -1.921F);
        cube_r68.texOffs(61, 13).addBox(-1.9992F, -12.3444F, -10.5634F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r69 = new ModelRenderer(this);
        cube_r69.setPos(0.009F, 10.9724F, 10.381F);
        bone.addChild(cube_r69);
        setRotationAngle(cube_r69, 0.6371F, 0.0059F, -0.3016F);
        cube_r69.texOffs(48, 4).addBox(4.4726F, -12.88F, -5.4478F, 2.0F, 4.0F, 2.0F, 0.0F, false);

        cube_r70 = new ModelRenderer(this);
        cube_r70.setPos(0.009F, 10.9724F, 10.381F);
        bone.addChild(cube_r70);
        setRotationAngle(cube_r70, 0.6371F, -0.0059F, 0.3016F);
        cube_r70.texOffs(48, 4).addBox(-6.49F, -12.8756F, -5.451F, 2.0F, 4.0F, 2.0F, 0.0F, false);

        cube_r71 = new ModelRenderer(this);
        cube_r71.setPos(0.009F, 10.9724F, 10.381F);
        bone.addChild(cube_r71);
        setRotationAngle(cube_r71, 0.6831F, 0.3409F, 0.5717F);
        cube_r71.texOffs(44, 22).addBox(-7.574F, -12.8756F, -7.6792F, 4.0F, 4.0F, 2.0F, 0.0F, false);

        cube_r72 = new ModelRenderer(this);
        cube_r72.setPos(0.009F, 10.9724F, 10.381F);
        bone.addChild(cube_r72);
        setRotationAngle(cube_r72, 1.1631F, 0.3409F, 0.5717F);
        cube_r72.texOffs(18, 40).addBox(-7.574F, -11.4264F, -2.6948F, 4.0F, 8.0F, 2.0F, 0.0F, false);
        cube_r72.texOffs(6, 66).addBox(-7.574F, -3.4264F, -2.6948F, 2.0F, 4.0F, 2.0F, 0.0F, false);

        cube_r73 = new ModelRenderer(this);
        cube_r73.setPos(0.009F, 10.9724F, 10.381F);
        bone.addChild(cube_r73);
        setRotationAngle(cube_r73, 1.0849F, -0.208F, -0.2856F);
        cube_r73.texOffs(0, 20).addBox(0.3706F, -11.334F, -1.4328F, 4.0F, 8.0F, 2.0F, 0.0F, false);

        cube_r74 = new ModelRenderer(this);
        cube_r74.setPos(0.009F, 10.9724F, 10.381F);
        bone.addChild(cube_r74);
        setRotationAngle(cube_r74, 1.0849F, 0.208F, 0.2856F);
        cube_r74.texOffs(0, 20).addBox(-4.3876F, -11.3348F, -1.439F, 4.0F, 8.0F, 2.0F, 0.0F, false);

        cube_r75 = new ModelRenderer(this);
        cube_r75.setPos(0.009F, 10.9724F, 10.381F);
        bone.addChild(cube_r75);
        setRotationAngle(cube_r75, 1.2163F, -0.1976F, -0.2904F);
        cube_r75.texOffs(44, 22).addBox(0.4034F, -3.552F, -0.994F, 4.0F, 4.0F, 2.0F, 0.0F, false);

        cube_r76 = new ModelRenderer(this);
        cube_r76.setPos(0.009F, 10.9724F, 10.381F);
        bone.addChild(cube_r76);
        setRotationAngle(cube_r76, 1.2163F, 0.1976F, 0.2904F);
        cube_r76.texOffs(44, 22).addBox(-4.4204F, -3.5534F, -1.0F, 4.0F, 4.0F, 2.0F, 0.0F, false);

        cube_r77 = new ModelRenderer(this);
        cube_r77.setPos(0.009F, 10.9724F, 10.381F);
        bone.addChild(cube_r77);
        setRotationAngle(cube_r77, 1.2474F, 0.3055F, 0.5723F);
        cube_r77.texOffs(6, 66).addBox(-5.9582F, -3.5888F, -2.32F, 2.0F, 4.0F, 2.0F, 0.0F, false);

        cube_r78 = new ModelRenderer(this);
        cube_r78.setPos(0.009F, 10.9724F, 10.381F);
        bone.addChild(cube_r78);
        setRotationAngle(cube_r78, 1.2474F, -0.3055F, -0.5723F);
        cube_r78.texOffs(6, 66).addBox(3.9436F, -3.5874F, -2.3092F, 2.0F, 4.0F, 2.0F, 0.0F, false);

        cube_r79 = new ModelRenderer(this);
        cube_r79.setPos(0.004F, 11.0636F, 10.4188F);
        bone.addChild(cube_r79);
        setRotationAngle(cube_r79, 0.8118F, 0.1758F, -0.195F);
        cube_r79.texOffs(35, 45).addBox(-8.5654F, 2.9962F, 1.6684F, 2.0F, 2.0F, 4.0F, 0.0F, true);

        cube_r80 = new ModelRenderer(this);
        cube_r80.setPos(7.0096F, 15.769F, 19.244F);
        bone.addChild(cube_r80);
        setRotationAngle(cube_r80, 0.9587F, 0.0223F, -0.0384F);
        cube_r80.texOffs(25, 10).addBox(-1.2F, 1.4306F, -2.2F, 2.0F, 2.0F, 4.0F, 0.0F, false);

        cube_r81 = new ModelRenderer(this);
        cube_r81.setPos(-7.0096F, 15.769F, 19.244F);
        bone.addChild(cube_r81);
        setRotationAngle(cube_r81, 0.9587F, -0.0223F, 0.0384F);
        cube_r81.texOffs(28, 10).addBox(-0.8F, 1.4306F, -2.2F, 2.0F, 2.0F, 4.0F, 0.0F, true);

        cube_r82 = new ModelRenderer(this);
        cube_r82.setPos(-6.6412F, 14.9898F, 19.3284F);
        bone.addChild(cube_r82);
        setRotationAngle(cube_r82, 0.7405F, -0.0223F, 0.0384F);
        cube_r82.texOffs(26, 6).addBox(-1.0F, 0.4F, -2.2F, 2.0F, 2.0F, 4.0F, 0.0F, true);

        cube_r83 = new ModelRenderer(this);
        cube_r83.setPos(-6.4856F, 14.7346F, 19.1494F);
        bone.addChild(cube_r83);
        setRotationAngle(cube_r83, 0.6863F, -0.1396F, 0.1682F);
        cube_r83.texOffs(35, 46).addBox(-0.9544F, -1.0F, -2.131F, 2.0F, 2.0F, 4.0F, 0.0F, true);

        cube_r84 = new ModelRenderer(this);
        cube_r84.setPos(0.0134F, 11.0696F, 10.4756F);
        bone.addChild(cube_r84);
        setRotationAngle(cube_r84, 0.6981F, 0.0F, 0.0F);
        cube_r84.texOffs(0, 40).addBox(-7.191F, 5.7176F, 2.2428F, 2.0F, 2.0F, 4.0F, 0.0F, true);
        cube_r84.texOffs(0, 40).addBox(5.164F, 5.7176F, 2.2428F, 2.0F, 2.0F, 4.0F, 0.0F, false);

        cube_r85 = new ModelRenderer(this);
        cube_r85.setPos(6.6412F, 14.9898F, 19.3284F);
        bone.addChild(cube_r85);
        setRotationAngle(cube_r85, 0.7405F, 0.0223F, -0.0384F);
        cube_r85.texOffs(26, 6).addBox(-1.0F, 0.4F, -2.2F, 2.0F, 2.0F, 4.0F, 0.0F, false);

        cube_r86 = new ModelRenderer(this);
        cube_r86.setPos(6.4856F, 14.7346F, 19.1494F);
        bone.addChild(cube_r86);
        setRotationAngle(cube_r86, 0.6863F, 0.1396F, -0.1682F);
        cube_r86.texOffs(28, 41).addBox(-1.0456F, -1.0F, -2.131F, 2.0F, 2.0F, 4.0F, 0.0F, false);

        cube_r87 = new ModelRenderer(this);
        cube_r87.setPos(-0.004F, 11.0636F, 10.4188F);
        bone.addChild(cube_r87);
        setRotationAngle(cube_r87, 0.8118F, -0.1758F, 0.195F);
        cube_r87.texOffs(35, 45).addBox(6.5654F, 2.9962F, 1.6684F, 2.0F, 2.0F, 4.0F, 0.0F, false);

        body_r6_r6 = new ModelRenderer(this);
        body_r6_r6.setPos(0.009F, 10.9724F, 10.381F);
        bone.addChild(body_r6_r6);
        setRotationAngle(body_r6_r6, 0.8726F, 0.0F, 0.0F);
        body_r6_r6.texOffs(78, 40).addBox(-2.109F, -15.0258F, -2.09F, 4.0F, 2.0F, 2.0F, 0.0F, false);

        body_r5_r4 = new ModelRenderer(this);
        body_r5_r4.setPos(0.009F, 10.9724F, 10.381F);
        bone.addChild(body_r5_r4);
        setRotationAngle(body_r5_r4, 1.3962F, 0.0F, 0.0F);
        body_r5_r4.texOffs(50, 12).addBox(-5.009F, -13.065F, 6.8956F, 10.0F, 4.0F, 2.0F, 0.0F, false);

        ribcage = new ModelRenderer(this);
        ribcage.setPos(1.6026F, -7.7568F, -2.8182F);
        abdomino_thoracic_region.addChild(ribcage);

        cube_r3_r1 = new ModelRenderer(this);
        cube_r3_r1.setPos(0.5688F, 7.9612F, 1.3478F);
        ribcage.addChild(cube_r3_r1);
        setRotationAngle(cube_r3_r1, -0.2419F, 0.7415F, 0.9636F);
        cube_r3_r1.texOffs(92, 36).addBox(-10.8012F, -3.0648F, -5.744F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r5_r1 = new ModelRenderer(this);
        cube_r5_r1.setPos(0.5688F, 7.9612F, 1.3478F);
        ribcage.addChild(cube_r5_r1);
        setRotationAngle(cube_r5_r1, -0.4851F, -0.0163F, 0.6035F);
        cube_r5_r1.texOffs(34, 92).addBox(-11.6542F, -6.0656F, -7.8098F, 2.0F, 4.0F, 2.0F, 0.0F, false);

        cube_r6_r1 = new ModelRenderer(this);
        cube_r6_r1.setPos(0.5688F, 7.9612F, 1.3478F);
        ribcage.addChild(cube_r6_r1);
        setRotationAngle(cube_r6_r1, -0.3237F, 0.3683F, 1.455F);
        cube_r6_r1.texOffs(92, 36).addBox(-11.8982F, 3.56F, -7.8098F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r6_r2 = new ModelRenderer(this);
        cube_r6_r2.setPos(0.5688F, 7.9612F, 1.3478F);
        ribcage.addChild(cube_r6_r2);
        setRotationAngle(cube_r6_r2, -0.9646F, 0.5387F, 0.2404F);
        cube_r6_r2.texOffs(34, 92).addBox(-7.818F, -5.0066F, -2.4916F, 2.0F, 4.0F, 2.0F, 0.0F, false);

        cube_r7_r1 = new ModelRenderer(this);
        cube_r7_r1.setPos(0.5688F, 7.9612F, 1.3478F);
        ribcage.addChild(cube_r7_r1);
        setRotationAngle(cube_r7_r1, -0.6271F, 0.9224F, 0.7316F);
        cube_r7_r1.texOffs(88, 36).addBox(-9.2708F, -2.4284F, -2.4916F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r5_r2 = new ModelRenderer(this);
        cube_r5_r2.setPos(0.5688F, 7.9612F, 1.3478F);
        ribcage.addChild(cube_r5_r2);
        setRotationAngle(cube_r5_r2, -0.7585F, 0.4747F, 0.3028F);
        cube_r5_r2.texOffs(34, 92).addBox(-8.0612F, -6.1166F, -3.7654F, 2.0F, 4.0F, 2.0F, 0.0F, false);

        cube_r6_r3 = new ModelRenderer(this);
        cube_r6_r3.setPos(0.5688F, 7.9612F, 1.3478F);
        ribcage.addChild(cube_r6_r3);
        setRotationAngle(cube_r6_r3, -0.4745F, 0.7586F, 0.7828F);
        cube_r6_r3.texOffs(90, 36).addBox(-9.9684F, -3.7094F, -3.7654F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r6_r4 = new ModelRenderer(this);
        cube_r6_r4.setPos(0.5688F, 7.9612F, 1.3478F);
        ribcage.addChild(cube_r6_r4);
        setRotationAngle(cube_r6_r4, -0.4418F, 0.2909F, 0.5451F);
        cube_r6_r4.texOffs(64, 82).addBox(-8.2526F, -5.0618F, -13.3466F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r2_r1 = new ModelRenderer(this);
        cube_r2_r1.setPos(0.5688F, 7.9612F, 1.3478F);
        ribcage.addChild(cube_r2_r1);
        setRotationAngle(cube_r2_r1, -0.6562F, 0.4426F, 0.2507F);
        cube_r2_r1.texOffs(64, 22).addBox(-7.921F, -7.4092F, -5.744F, 2.0F, 6.0F, 2.0F, 0.0F, false);

        cube_r5_r3 = new ModelRenderer(this);
        cube_r5_r3.setPos(0.5688F, 7.9612F, 1.3478F);
        ribcage.addChild(cube_r5_r3);
        setRotationAngle(cube_r5_r3, -0.4418F, -0.2909F, -0.5451F);
        cube_r5_r3.texOffs(64, 82).addBox(6.2378F, -5.0722F, -13.3466F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r5_r4 = new ModelRenderer(this);
        cube_r5_r4.setPos(0.5688F, 7.9612F, 1.3478F);
        ribcage.addChild(cube_r5_r4);
        setRotationAngle(cube_r5_r4, -0.4745F, -0.7586F, -0.7828F);
        cube_r5_r4.texOffs(89, 36).addBox(7.959F, -3.7248F, -3.7632F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r4_r1 = new ModelRenderer(this);
        cube_r4_r1.setPos(0.5688F, 7.9612F, 1.3478F);
        ribcage.addChild(cube_r4_r1);
        setRotationAngle(cube_r4_r1, -0.7585F, -0.4747F, -0.3028F);
        cube_r4_r1.texOffs(34, 92).addBox(6.0456F, -6.126F, -3.7632F, 2.0F, 4.0F, 2.0F, 0.0F, false);

        cube_r6_r5 = new ModelRenderer(this);
        cube_r6_r5.setPos(0.5688F, 7.9612F, 1.3478F);
        ribcage.addChild(cube_r6_r5);
        setRotationAngle(cube_r6_r5, -0.6271F, -0.9224F, -0.7316F);
        cube_r6_r5.texOffs(88, 36).addBox(7.2626F, -2.4446F, -2.49F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r5_r5 = new ModelRenderer(this);
        cube_r5_r5.setPos(0.5688F, 7.9612F, 1.3478F);
        ribcage.addChild(cube_r5_r5);
        setRotationAngle(cube_r5_r5, -0.9646F, -0.5387F, -0.2404F);
        cube_r5_r5.texOffs(34, 92).addBox(5.8028F, -5.0166F, -2.49F, 2.0F, 4.0F, 2.0F, 0.0F, false);

        cube_r5_r6 = new ModelRenderer(this);
        cube_r5_r6.setPos(0.5688F, 7.9612F, 1.3478F);
        ribcage.addChild(cube_r5_r6);
        setRotationAngle(cube_r5_r6, -0.3237F, -0.3683F, -1.455F);
        cube_r5_r6.texOffs(92, 36).addBox(9.8962F, 3.5426F, -7.815F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r4_r2 = new ModelRenderer(this);
        cube_r4_r2.setPos(0.5688F, 7.9612F, 1.3478F);
        ribcage.addChild(cube_r4_r2);
        setRotationAngle(cube_r4_r2, -0.4851F, 0.0163F, -0.6035F);
        cube_r4_r2.texOffs(34, 92).addBox(9.6394F, -6.0748F, -7.815F, 2.0F, 4.0F, 2.0F, 0.0F, false);

        cube_r4_r3 = new ModelRenderer(this);
        cube_r4_r3.setPos(0.5506F, 7.9612F, 1.3478F);
        ribcage.addChild(cube_r4_r3);
        setRotationAngle(cube_r4_r3, -0.4807F, 0.1567F, 0.4638F);
        cube_r4_r3.texOffs(34, 92).addBox(-9.9594F, -7.0728F, -7.3638F, 2.0F, 4.0F, 2.0F, 0.0F, true);

        cube_r5_r7 = new ModelRenderer(this);
        cube_r5_r7.setPos(0.5506F, 7.9612F, 1.3478F);
        ribcage.addChild(cube_r5_r7);
        setRotationAngle(cube_r5_r7, -0.2776F, 0.4258F, 1.14F);
        cube_r5_r7.texOffs(90, 36).addBox(-12.211F, -1.0528F, -7.3638F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r4_r4 = new ModelRenderer(this);
        cube_r4_r4.setPos(0.5506F, 7.9612F, 1.3478F);
        ribcage.addChild(cube_r4_r4);
        setRotationAngle(cube_r4_r4, -0.2184F, 0.5554F, 1.0564F);
        cube_r4_r4.texOffs(88, 36).addBox(-11.9368F, -2.5282F, -6.4158F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r3_r2 = new ModelRenderer(this);
        cube_r3_r2.setPos(0.5506F, 7.9612F, 1.3478F);
        ribcage.addChild(cube_r3_r2);
        setRotationAngle(cube_r3_r2, -0.5246F, 0.2896F, 0.3425F);
        cube_r3_r2.texOffs(64, 22).addBox(-8.82F, -8.0928F, -6.4158F, 2.0F, 6.0F, 2.0F, 0.0F, true);

        cube_r4_r5 = new ModelRenderer(this);
        cube_r4_r5.setPos(0.5688F, 7.9612F, 1.3478F);
        ribcage.addChild(cube_r4_r5);
        setRotationAngle(cube_r4_r5, -0.2776F, -0.4258F, -1.14F);
        cube_r4_r5.texOffs(90, 36).addBox(10.211F, -1.0528F, -7.3638F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r3_r3 = new ModelRenderer(this);
        cube_r3_r3.setPos(0.5688F, 7.9612F, 1.3478F);
        ribcage.addChild(cube_r3_r3);
        setRotationAngle(cube_r3_r3, -0.4807F, -0.1567F, -0.4638F);
        cube_r3_r3.texOffs(34, 92).addBox(7.9594F, -7.0728F, -7.3638F, 2.0F, 4.0F, 2.0F, 0.0F, false);

        cube_r3_r4 = new ModelRenderer(this);
        cube_r3_r4.setPos(0.5688F, 7.9612F, 1.3478F);
        ribcage.addChild(cube_r3_r4);
        setRotationAngle(cube_r3_r4, -0.2184F, -0.5554F, -1.0564F);
        cube_r3_r4.texOffs(88, 36).addBox(9.9368F, -2.5282F, -6.4158F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r2_r2 = new ModelRenderer(this);
        cube_r2_r2.setPos(0.5688F, 7.9612F, 1.3478F);
        ribcage.addChild(cube_r2_r2);
        setRotationAngle(cube_r2_r2, -0.5246F, -0.2896F, -0.3425F);
        cube_r2_r2.texOffs(64, 22).addBox(6.82F, -8.0928F, -6.4158F, 2.0F, 6.0F, 2.0F, 0.0F, false);

        cube_r2_r3 = new ModelRenderer(this);
        cube_r2_r3.setPos(0.5688F, 7.9612F, 1.3478F);
        ribcage.addChild(cube_r2_r3);
        setRotationAngle(cube_r2_r3, -0.2419F, -0.7415F, -0.9636F);
        cube_r2_r3.texOffs(92, 36).addBox(8.7936F, -3.081F, -5.7408F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r1_r1 = new ModelRenderer(this);
        cube_r1_r1.setPos(0.5688F, 7.9612F, 1.3478F);
        ribcage.addChild(cube_r1_r1);
        setRotationAngle(cube_r1_r1, -0.6562F, -0.4426F, -0.2507F);
        cube_r1_r1.texOffs(64, 22).addBox(5.9052F, -7.4174F, -5.7408F, 2.0F, 6.0F, 2.0F, 0.0F, false);

        spine = new ModelRenderer(this);
        spine.setPos(2.1714F, 0.2044F, -1.4704F);
        abdomino_thoracic_region.addChild(spine);

        cube_r88 = new ModelRenderer(this);
        cube_r88.setPos(0.0F, 0.0F, 0.0F);
        spine.addChild(cube_r88);
        setRotationAngle(cube_r88, -0.6169F, -0.4077F, -0.5745F);
        cube_r88.texOffs(64, 82).addBox(-2.2466F, 2.5104F, -11.9984F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r89 = new ModelRenderer(this);
        cube_r89.setPos(0.0F, 0.0F, 0.0F);
        spine.addChild(cube_r89);
        setRotationAngle(cube_r89, -0.2881F, -0.4426F, -0.6488F);
        cube_r89.texOffs(64, 82).addBox(-0.6896F, -0.5396F, -2.7846F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r90 = new ModelRenderer(this);
        cube_r90.setPos(0.0F, 0.0F, 0.0F);
        spine.addChild(cube_r90);
        setRotationAngle(cube_r90, -0.3599F, -0.4193F, -0.7028F);
        cube_r90.texOffs(64, 82).addBox(-0.3776F, -0.525F, -0.3168F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r91 = new ModelRenderer(this);
        cube_r91.setPos(0.0F, 0.0F, 0.0F);
        spine.addChild(cube_r91);
        setRotationAngle(cube_r91, -0.2666F, -0.4102F, -0.6988F);
        cube_r91.texOffs(64, 82).addBox(-1.1302F, -0.5292F, -5.128F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r92 = new ModelRenderer(this);
        cube_r92.setPos(0.0F, 0.0F, 0.0F);
        spine.addChild(cube_r92);
        setRotationAngle(cube_r92, -0.3287F, -0.396F, -0.6517F);
        cube_r92.texOffs(88, 36).addBox(-1.4454F, -0.1946F, -7.6186F, 4.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r93 = new ModelRenderer(this);
        cube_r93.setPos(0.0F, 0.0F, 0.0F);
        spine.addChild(cube_r93);
        setRotationAngle(cube_r93, -0.3358F, -0.4302F, -0.6297F);
        cube_r93.texOffs(88, 36).addBox(-2.2004F, -0.1272F, -10.0884F, 4.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r94 = new ModelRenderer(this);
        cube_r94.setPos(0.0F, 0.0F, 0.0F);
        spine.addChild(cube_r94);
        setRotationAngle(cube_r94, -0.5763F, 0.4666F, 0.6701F);
        cube_r94.texOffs(64, 82).addBox(0.454F, 2.5164F, -11.9892F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r95 = new ModelRenderer(this);
        cube_r95.setPos(0.0F, 0.0F, 0.0F);
        spine.addChild(cube_r95);
        setRotationAngle(cube_r95, -0.272F, 0.4523F, 0.686F);
        cube_r95.texOffs(64, 82).addBox(-1.439F, -0.3762F, -2.8186F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r96 = new ModelRenderer(this);
        cube_r96.setPos(0.0F, 0.0F, 0.0F);
        spine.addChild(cube_r96);
        setRotationAngle(cube_r96, -0.3599F, 0.4193F, 0.7028F);
        cube_r96.texOffs(64, 82).addBox(-1.7744F, -0.369F, -0.3306F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r97 = new ModelRenderer(this);
        cube_r97.setPos(0.0F, 0.0F, 0.0F);
        spine.addChild(cube_r97);
        setRotationAngle(cube_r97, -0.2666F, 0.4102F, 0.6988F);
        cube_r97.texOffs(64, 82).addBox(-1.023F, -0.3762F, -5.1554F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r98 = new ModelRenderer(this);
        cube_r98.setPos(0.0F, 0.0F, 0.0F);
        spine.addChild(cube_r98);
        setRotationAngle(cube_r98, -0.3287F, 0.396F, 0.6517F);
        cube_r98.texOffs(88, 36).addBox(-2.622F, -0.1302F, -7.6392F, 4.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r99 = new ModelRenderer(this);
        cube_r99.setPos(0.0F, 0.0F, 0.0F);
        spine.addChild(cube_r99);
        setRotationAngle(cube_r99, -0.3164F, 0.4441F, 0.6753F);
        cube_r99.texOffs(88, 36).addBox(-1.918F, -0.041F, -10.1156F, 4.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r100 = new ModelRenderer(this);
        cube_r100.setPos(0.0F, 0.0F, 0.0F);
        spine.addChild(cube_r100);
        setRotationAngle(cube_r100, 1.1578F, 0.1451F, 0.3186F);
        cube_r100.texOffs(44, 22).addBox(-4.3164F, 1.0462F, -1.1846F, 4.0F, 4.0F, 2.0F, 0.0F, false);

        cube_r101 = new ModelRenderer(this);
        cube_r101.setPos(0.0F, 0.0F, 0.0F);
        spine.addChild(cube_r101);
        setRotationAngle(cube_r101, 0.985F, 0.1317F, 0.326F);
        cube_r101.texOffs(44, 22).addBox(-4.3F, -2.7028F, -0.9978F, 4.0F, 4.0F, 2.0F, 0.0F, false);

        cube_r102 = new ModelRenderer(this);
        cube_r102.setPos(0.0F, 0.0F, 0.0F);
        spine.addChild(cube_r102);
        setRotationAngle(cube_r102, 0.985F, -0.1317F, -0.326F);
        cube_r102.texOffs(18, 40).addBox(0.283F, -2.704F, -0.9916F, 4.0F, 4.0F, 2.0F, 0.0F, false);

        cube_r103 = new ModelRenderer(this);
        cube_r103.setPos(0.0F, 0.0F, 0.0F);
        spine.addChild(cube_r103);
        setRotationAngle(cube_r103, 1.1578F, -0.1451F, -0.3186F);
        cube_r103.texOffs(44, 22).addBox(0.2994F, 1.0462F, -1.1784F, 4.0F, 4.0F, 2.0F, 0.0F, false);

        cube_r104 = new ModelRenderer(this);
        cube_r104.setPos(0.0F, 0.0F, 0.0F);
        spine.addChild(cube_r104);
        setRotationAngle(cube_r104, -0.4778F, -0.0028F, -0.0082F);
        cube_r104.texOffs(4, 58).addBox(-1.1322F, -0.5484F, -0.0796F, 2.0F, 2.0F, 6.0F, 0.0F, false);

        cube_r105 = new ModelRenderer(this);
        cube_r105.setPos(0.0F, 0.0F, 0.0F);
        spine.addChild(cube_r105);
        setRotationAngle(cube_r105, 0.7538F, -0.4549F, -0.4229F);
        cube_r105.texOffs(44, 0).addBox(-2.9994F, -12.3176F, -6.0048F, 4.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r106 = new ModelRenderer(this);
        cube_r106.setPos(0.0F, 0.0F, 0.0F);
        spine.addChild(cube_r106);
        setRotationAngle(cube_r106, 0.7538F, 0.4549F, 0.4229F);
        cube_r106.texOffs(44, 0).addBox(-1.0156F, -12.317F, -6.0152F, 4.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r107 = new ModelRenderer(this);
        cube_r107.setPos(0.0F, 0.0F, 0.0F);
        spine.addChild(cube_r107);
        setRotationAngle(cube_r107, 0.5793F, 0.4549F, 0.4229F);
        cube_r107.texOffs(44, 0).addBox(-1.0156F, -13.0438F, -8.0516F, 4.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r108 = new ModelRenderer(this);
        cube_r108.setPos(0.0F, 0.0F, 0.0F);
        spine.addChild(cube_r108);
        setRotationAngle(cube_r108, 0.5793F, -0.4549F, -0.4229F);
        cube_r108.texOffs(44, 0).addBox(-2.9994F, -13.0462F, -8.0414F, 4.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r109 = new ModelRenderer(this);
        cube_r109.setPos(0.0F, 0.0F, 0.0F);
        spine.addChild(cube_r109);
        setRotationAngle(cube_r109, 0.961F, -0.3488F, -0.5062F);
        cube_r109.texOffs(88, 0).addBox(-1.4294F, -11.471F, -4.1372F, 4.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r110 = new ModelRenderer(this);
        cube_r110.setPos(0.0F, 0.0F, 0.0F);
        spine.addChild(cube_r110);
        setRotationAngle(cube_r110, 0.961F, 0.3488F, 0.5062F);
        cube_r110.texOffs(88, 0).addBox(-2.5854F, -11.4704F, -4.1474F, 4.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r111 = new ModelRenderer(this);
        cube_r111.setPos(0.0F, 0.0F, 0.0F);
        spine.addChild(cube_r111);
        setRotationAngle(cube_r111, -0.9141F, -0.0061F, -0.0063F);
        cube_r111.texOffs(70, 70).addBox(-1.1754F, 5.1212F, -13.81F, 2.0F, 2.0F, 4.0F, 0.0F, false);

        cube_r112 = new ModelRenderer(this);
        cube_r112.setPos(0.0F, 0.0F, 0.0F);
        spine.addChild(cube_r112);
        setRotationAngle(cube_r112, 1.2887F, -0.3496F, -0.5231F);
        cube_r112.texOffs(18, 40).addBox(-1.4178F, -10.2874F, -0.9716F, 4.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r113 = new ModelRenderer(this);
        cube_r113.setPos(0.0F, 0.0F, 0.0F);
        spine.addChild(cube_r113);
        setRotationAngle(cube_r113, 1.2887F, 0.3496F, 0.5231F);
        cube_r113.texOffs(18, 40).addBox(-2.597F, -10.29F, -0.9818F, 4.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r114 = new ModelRenderer(this);
        cube_r114.setPos(0.0F, 0.0F, 0.0F);
        spine.addChild(cube_r114);
        setRotationAngle(cube_r114, -0.5214F, -0.0032F, -0.0081F);
        cube_r114.texOffs(70, 70).addBox(-1.1412F, 0.8816F, -11.7928F, 2.0F, 2.0F, 4.0F, 0.0F, false);

        cube_r115 = new ModelRenderer(this);
        cube_r115.setPos(-0.0182F, 0.0F, 0.0F);
        spine.addChild(cube_r115);
        setRotationAngle(cube_r115, 0.8165F, 0.2443F, 0.2519F);
        cube_r115.texOffs(24, 14).addBox(-6.0538F, 2.411F, -0.0552F, 6.0F, 6.0F, 2.0F, 0.0F, true);

        cube_r116 = new ModelRenderer(this);
        cube_r116.setPos(0.0F, 0.0F, 0.0F);
        spine.addChild(cube_r116);
        setRotationAngle(cube_r116, 0.8165F, -0.2443F, -0.2519F);
        cube_r116.texOffs(24, 14).addBox(0.0538F, 2.411F, -0.0552F, 6.0F, 6.0F, 2.0F, 0.0F, false);

        cube_r117 = new ModelRenderer(this);
        cube_r117.setPos(0.0F, 0.0F, 0.0F);
        spine.addChild(cube_r117);
        setRotationAngle(cube_r117, -0.8268F, -0.0055F, -0.0068F);
        cube_r117.texOffs(6, 60).addBox(-1.1212F, -1.8072F, 2.215F, 2.0F, 2.0F, 4.0F, 0.0F, false);

        cube_r118 = new ModelRenderer(this);
        cube_r118.setPos(0.0F, 0.0F, 0.0F);
        spine.addChild(cube_r118);
        setRotationAngle(cube_r118, 1.2744F, 0.1733F, 0.5888F);
        cube_r118.texOffs(0, 38).addBox(-4.145F, -8.3618F, -1.2992F, 4.0F, 10.0F, 2.0F, 0.0F, false);

        cube_r119 = new ModelRenderer(this);
        cube_r119.setPos(0.0F, 0.0F, 0.0F);
        spine.addChild(cube_r119);
        setRotationAngle(cube_r119, 1.2744F, -0.1733F, -0.5888F);
        cube_r119.texOffs(0, 19).addBox(0.13F, -8.3622F, -1.2888F, 4.0F, 10.0F, 2.0F, 0.0F, false);

        cube_r120 = new ModelRenderer(this);
        cube_r120.setPos(0.0F, 0.0F, 0.0F);
        spine.addChild(cube_r120);
        setRotationAngle(cube_r120, -0.3469F, -0.0017F, -0.0085F);
        cube_r120.texOffs(0, 54).addBox(-1.1332F, -0.5684F, -8.2852F, 2.0F, 2.0F, 10.0F, 0.0F, false);

        pelvis = new ModelRenderer(this);
        pelvis.setPos(2.1714F, -2.9956F, -3.6704F);
        abdomino_thoracic_region.addChild(pelvis);

        cube_r121 = new ModelRenderer(this);
        cube_r121.setPos(0.0F, 0.0F, 0.0F);
        pelvis.addChild(cube_r121);
        setRotationAngle(cube_r121, -0.4125F, -0.319F, 0.1458F);
        cube_r121.texOffs(64, 82).addBox(4.9166F, 5.2742F, 14.4886F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r122 = new ModelRenderer(this);
        cube_r122.setPos(0.0F, 0.0F, 0.0F);
        pelvis.addChild(cube_r122);
        setRotationAngle(cube_r122, -0.4125F, 0.319F, -0.1458F);
        cube_r122.texOffs(64, 82).addBox(-7.1336F, 5.2742F, 14.4824F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r123 = new ModelRenderer(this);
        cube_r123.setPos(0.0F, 0.0F, 0.0F);
        pelvis.addChild(cube_r123);
        setRotationAngle(cube_r123, -1.1618F, -0.0398F, 0.1153F);
        cube_r123.texOffs(6, 60).addBox(-0.4066F, -4.539F, 5.9954F, 2.0F, 2.0F, 4.0F, 0.0F, false);

        cube_r124 = new ModelRenderer(this);
        cube_r124.setPos(0.0F, 0.0F, 0.0F);
        pelvis.addChild(cube_r124);
        setRotationAngle(cube_r124, -1.1618F, 0.0398F, -0.1153F);
        cube_r124.texOffs(6, 60).addBox(-1.8114F, -4.5392F, 5.9932F, 2.0F, 2.0F, 4.0F, 0.0F, false);

        cube_r125 = new ModelRenderer(this);
        cube_r125.setPos(-0.5472F, 7.455F, 6.1624F);
        pelvis.addChild(cube_r125);
        setRotationAngle(cube_r125, 0.2823F, 0.3786F, 0.1068F);
        cube_r125.texOffs(67, 48).addBox(-6.105F, -3.4142F, 0.2492F, 6.0F, 4.0F, 2.0F, 0.0F, true);

        cube_r126 = new ModelRenderer(this);
        cube_r126.setPos(0.529F, 7.455F, 6.1624F);
        pelvis.addChild(cube_r126);
        setRotationAngle(cube_r126, 0.2823F, -0.3786F, -0.1068F);
        cube_r126.texOffs(67, 48).addBox(0.105F, -3.4142F, 0.2492F, 6.0F, 4.0F, 2.0F, 0.0F, false);

        cube_r127 = new ModelRenderer(this);
        cube_r127.setPos(0.0F, -0.219F, -0.1518F);
        pelvis.addChild(cube_r127);
        setRotationAngle(cube_r127, 0.1745F, 0.0F, 0.0F);
        cube_r127.texOffs(16, 16).addBox(-7.009F, 5.7988F, 7.6874F, 14.0F, 4.0F, 2.0F, 0.0F, false);

        cube_r128 = new ModelRenderer(this);
        cube_r128.setPos(-5.009F, 4.9186F, 10.1512F);
        pelvis.addChild(cube_r128);
        setRotationAngle(cube_r128, 0.6981F, 0.0F, 0.0F);
        cube_r128.texOffs(80, 14).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, true);
        cube_r128.texOffs(80, 14).addBox(9.0F, -2.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

        cube_r129 = new ModelRenderer(this);
        cube_r129.setPos(-5.009F, 8.0436F, 12.2394F);
        pelvis.addChild(cube_r129);
        setRotationAngle(cube_r129, 0.48F, 0.0F, 0.0F);
        cube_r129.texOffs(48, 12).addBox(-1.0F, -1.9999F, -1.0001F, 2.0F, 4.0F, 2.0F, 0.0F, true);
        cube_r129.texOffs(50, 12).addBox(9.0F, -1.9999F, -1.0001F, 2.0F, 4.0F, 2.0F, 0.0F, false);

        cube_r130 = new ModelRenderer(this);
        cube_r130.setPos(-0.0374F, -0.1798F, -0.1308F);
        pelvis.addChild(cube_r130);
        setRotationAngle(cube_r130, -0.5184F, 0.2429F, 0.0142F);
        cube_r130.texOffs(80, 16).addBox(-9.059F, 1.1144F, 13.133F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        cube_r131 = new ModelRenderer(this);
        cube_r131.setPos(-0.0182F, 0.0F, 0.0F);
        pelvis.addChild(cube_r131);
        setRotationAngle(cube_r131, 0.4996F, 0.27F, 0.1446F);
        cube_r131.texOffs(32, 46).addBox(-6.2544F, 10.7862F, 4.5657F, 6.0F, 4.0F, 2.0F, 0.0F, true);

        cube_r132 = new ModelRenderer(this);
        cube_r132.setPos(0.0F, 0.0F, 0.0F);
        pelvis.addChild(cube_r132);
        setRotationAngle(cube_r132, 0.7128F, -0.2342F, -0.1979F);
        cube_r132.texOffs(32, 43).addBox(-0.4833F, 8.2436F, 2.2231F, 6.0F, 4.0F, 2.0F, 0.0F, false);

        cube_r133 = new ModelRenderer(this);
        cube_r133.setPos(-0.0182F, 0.0F, 0.0F);
        pelvis.addChild(cube_r133);
        setRotationAngle(cube_r133, 0.7128F, 0.2342F, 0.1979F);
        cube_r133.texOffs(32, 43).addBox(-5.5166F, 8.2436F, 2.2232F, 6.0F, 4.0F, 2.0F, 0.0F, true);

        cube_r134 = new ModelRenderer(this);
        cube_r134.setPos(0.0F, 0.0F, 0.0F);
        pelvis.addChild(cube_r134);
        setRotationAngle(cube_r134, 0.4996F, -0.27F, -0.1446F);
        cube_r134.texOffs(32, 45).addBox(0.2545F, 10.7862F, 4.5656F, 6.0F, 4.0F, 2.0F, 0.0F, false);

        cube_r135 = new ModelRenderer(this);
        cube_r135.setPos(0.0192F, -0.1798F, -0.1308F);
        pelvis.addChild(cube_r135);
        setRotationAngle(cube_r135, -0.5184F, -0.2429F, -0.0142F);
        cube_r135.texOffs(80, 16).addBox(7.0596F, 1.115F, 13.1325F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r136 = new ModelRenderer(this);
        cube_r136.setPos(-5.795F, 11.0086F, 14.7876F);
        pelvis.addChild(cube_r136);
        setRotationAngle(cube_r136, -0.5247F, 0.1384F, -0.024F);
        cube_r136.texOffs(76, 22).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, true);

        cube_r137 = new ModelRenderer(this);
        cube_r137.setPos(5.7768F, 11.0086F, 14.7876F);
        pelvis.addChild(cube_r137);
        setRotationAngle(cube_r137, -0.5247F, -0.1384F, 0.024F);
        cube_r137.texOffs(76, 22).addBox(-1.9998F, -1.9998F, -2.0001F, 4.0F, 4.0F, 4.0F, 0.0F, false);

        cube_r138 = new ModelRenderer(this);
        cube_r138.setPos(0.0F, 0.0F, 0.0F);
        pelvis.addChild(cube_r138);
        setRotationAngle(cube_r138, -0.6576F, -0.2163F, 0.0289F);
        cube_r138.texOffs(19, 49).addBox(7.0558F, 0.6568F, 13.7F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r139 = new ModelRenderer(this);
        cube_r139.setPos(0.0F, 0.0F, 0.0F);
        pelvis.addChild(cube_r139);
        setRotationAngle(cube_r139, -0.7582F, -0.4607F, -0.4504F);
        cube_r139.texOffs(0, 41).addBox(5.9534F, -0.8126F, 12.841F, 2.0F, 2.0F, 4.0F, 0.0F, false);

        cube_r140 = new ModelRenderer(this);
        cube_r140.setPos(0.0F, 0.0F, 0.0F);
        pelvis.addChild(cube_r140);
        setRotationAngle(cube_r140, -0.5267F, -0.2163F, 0.0289F);
        cube_r140.texOffs(10, 30).addBox(3.251F, 1.7008F, 14.9424F, 4.0F, 2.0F, 6.0F, 0.0F, false);

        cube_r141 = new ModelRenderer(this);
        cube_r141.setPos(0.0F, 0.0F, 0.0F);
        pelvis.addChild(cube_r141);
        setRotationAngle(cube_r141, -0.4954F, -0.1447F, -0.1133F);
        cube_r141.texOffs(10, 10).addBox(0.5988F, 3.3012F, 15.4394F, 4.0F, 2.0F, 6.0F, 0.0F, false);

        cube_r142 = new ModelRenderer(this);
        cube_r142.setPos(0.0F, 0.0F, 0.0F);
        pelvis.addChild(cube_r142);
        setRotationAngle(cube_r142, -0.5956F, -0.2595F, -0.0491F);
        cube_r142.texOffs(76, 41).addBox(2.4152F, 1.973F, 13.4184F, 6.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r143 = new ModelRenderer(this);
        cube_r143.setPos(0.0F, 0.0F, 0.0F);
        pelvis.addChild(cube_r143);
        setRotationAngle(cube_r143, -0.7701F, -0.2595F, -0.0491F);
        cube_r143.texOffs(68, 50).addBox(2.8152F, -0.4286F, 11.946F, 6.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r144 = new ModelRenderer(this);
        cube_r144.setPos(0.0F, 0.0F, 0.0F);
        pelvis.addChild(cube_r144);
        setRotationAngle(cube_r144, -0.9447F, -0.2595F, -0.0491F);
        cube_r144.texOffs(70, 4).addBox(2.6152F, -2.554F, 10.1802F, 6.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r145 = new ModelRenderer(this);
        cube_r145.setPos(-3.6212F, 10.9558F, 15.6466F);
        pelvis.addChild(cube_r145);
        setRotationAngle(cube_r145, -0.2885F, 0.4702F, 0.7194F);
        cube_r145.texOffs(70, 0).addBox(-1.0F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, 0.0F, true);

        cube_r146 = new ModelRenderer(this);
        cube_r146.setPos(3.6032F, 10.9558F, 15.6466F);
        pelvis.addChild(cube_r146);
        setRotationAngle(cube_r146, -0.2885F, -0.4702F, -0.7194F);
        cube_r146.texOffs(70, 0).addBox(-1.0F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, 0.0F, false);

        cube_r147 = new ModelRenderer(this);
        cube_r147.setPos(0.0F, 0.0F, 0.0F);
        pelvis.addChild(cube_r147);
        setRotationAngle(cube_r147, -0.5214F, -0.0087F, 0.0002F);
        cube_r147.texOffs(32, 84).addBox(-0.4614F, 3.1162F, 12.9576F, 2.0F, 2.0F, 4.0F, 0.0F, false);

        cube_r148 = new ModelRenderer(this);
        cube_r148.setPos(0.0F, 0.0F, 0.0F);
        pelvis.addChild(cube_r148);
        setRotationAngle(cube_r148, -0.3784F, -0.5927F, -0.889F);
        cube_r148.texOffs(34, 32).addBox(0.3264F, 0.3608F, 14.2188F, 2.0F, 2.0F, 6.0F, 0.0F, false);

        cube_r149 = new ModelRenderer(this);
        cube_r149.setPos(0.0F, 0.0F, 0.0F);
        pelvis.addChild(cube_r149);
        setRotationAngle(cube_r149, -0.0111F, 0.6912F, 1.5585F);
        cube_r149.texOffs(40, 60).addBox(-1.8374F, -1.0608F, 12.2122F, 2.0F, 2.0F, 8.0F, 0.0F, false);

        cube_r150 = new ModelRenderer(this);
        cube_r150.setPos(0.0F, 0.0F, 0.0F);
        pelvis.addChild(cube_r150);
        setRotationAngle(cube_r150, -0.3784F, 0.5927F, 0.889F);
        cube_r150.texOffs(34, 32).addBox(-2.336F, 0.3762F, 14.218F, 2.0F, 2.0F, 6.0F, 0.0F, false);

        cube_r151 = new ModelRenderer(this);
        cube_r151.setPos(0.0F, 0.0F, 0.0F);
        pelvis.addChild(cube_r151);
        setRotationAngle(cube_r151, -0.5214F, 0.0087F, -0.0002F);
        cube_r151.texOffs(32, 84).addBox(-1.7568F, 3.1162F, 12.9574F, 2.0F, 2.0F, 4.0F, 0.0F, false);

        cube_r152 = new ModelRenderer(this);
        cube_r152.setPos(0.0F, 0.0F, 0.0F);
        pelvis.addChild(cube_r152);
        setRotationAngle(cube_r152, -0.8268F, -0.0087F, 0.0002F);
        cube_r152.texOffs(32, 84).addBox(-0.5544F, -1.07F, 10.0418F, 2.0F, 2.0F, 4.0F, 0.0F, false);

        cube_r153 = new ModelRenderer(this);
        cube_r153.setPos(0.0F, 0.0F, 0.0F);
        pelvis.addChild(cube_r153);
        setRotationAngle(cube_r153, -0.8268F, 0.0087F, -0.0002F);
        cube_r153.texOffs(32, 84).addBox(-1.6638F, -1.07F, 10.0418F, 2.0F, 2.0F, 4.0F, 0.0F, false);

        cube_r154 = new ModelRenderer(this);
        cube_r154.setPos(0.0F, 0.0F, 0.0F);
        pelvis.addChild(cube_r154);
        setRotationAngle(cube_r154, -0.9447F, 0.2595F, 0.0491F);
        cube_r154.texOffs(70, 4).addBox(-8.8328F, -2.5498F, 10.1782F, 6.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r155 = new ModelRenderer(this);
        cube_r155.setPos(0.0F, 0.0F, 0.0F);
        pelvis.addChild(cube_r155);
        setRotationAngle(cube_r155, -0.7701F, 0.2595F, 0.0491F);
        cube_r155.texOffs(68, 50).addBox(-9.0328F, -0.4248F, 11.9432F, 6.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r156 = new ModelRenderer(this);
        cube_r156.setPos(0.0F, 0.0F, 0.0F);
        pelvis.addChild(cube_r156);
        setRotationAngle(cube_r156, -0.5956F, 0.2595F, 0.0491F);
        cube_r156.texOffs(76, 41).addBox(-8.4328F, 1.9762F, 13.415F, 6.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r157 = new ModelRenderer(this);
        cube_r157.setPos(0.0F, 0.0F, 0.0F);
        pelvis.addChild(cube_r157);
        setRotationAngle(cube_r157, -0.4954F, 0.1447F, 0.1133F);
        cube_r157.texOffs(10, 10).addBox(-4.6168F, 3.3042F, 15.4382F, 4.0F, 2.0F, 6.0F, 0.0F, false);

        cube_r158 = new ModelRenderer(this);
        cube_r158.setPos(0.0F, 0.0F, 0.0F);
        pelvis.addChild(cube_r158);
        setRotationAngle(cube_r158, -0.5267F, 0.2163F, -0.0289F);
        cube_r158.texOffs(10, 30).addBox(-7.2688F, 1.7024F, 14.9388F, 4.0F, 2.0F, 6.0F, 0.0F, false);

        cube_r159 = new ModelRenderer(this);
        cube_r159.setPos(0.0F, 0.0F, 0.0F);
        pelvis.addChild(cube_r159);
        setRotationAngle(cube_r159, -0.6576F, 0.2163F, -0.0289F);
        cube_r159.texOffs(46, 20).addBox(-9.0736F, 0.6588F, 13.6966F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        arms = new ModelRenderer(this);
        arms.setPos(0.0F, 0.0F, -6.0F);
        bone2.addChild(arms);

        right_arm = new ModelRenderer(this);
        right_arm.setPos(-5.5F, -22.5F, -4.5F);
        arms.addChild(right_arm);

        right_forearm = new ModelRenderer(this);
        right_forearm.setPos(-4.691F, 8.0488F, -10.5824F);
        right_arm.addChild(right_forearm);

        cube_r1 = new ModelRenderer(this);
        cube_r1.setPos(10.0F, -5.0F, 20.0F);
        right_forearm.addChild(cube_r1);
        setRotationAngle(cube_r1, 1.35F, -0.1719F, -3.111F);
        cube_r1.texOffs(64, 82).addBox(5.448F, -24.6442F, 11.0978F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r2 = new ModelRenderer(this);
        cube_r2.setPos(10.0F, -5.0F, 20.0F);
        right_forearm.addChild(cube_r2);
        setRotationAngle(cube_r2, 2.3536F, -0.1719F, -3.111F);
        cube_r2.texOffs(84, 72).addBox(4.2976F, -4.9946F, 26.3008F, 4.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r3 = new ModelRenderer(this);
        cube_r3.setPos(10.0F, -5.0F, 20.0F);
        right_forearm.addChild(cube_r3);
        setRotationAngle(cube_r3, 1.5025F, -0.7952F, -1.615F);
        cube_r3.texOffs(64, 82).addBox(-25.7664F, -5.0024F, 8.6908F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r4 = new ModelRenderer(this);
        cube_r4.setPos(10.0F, -5.0F, 20.0F);
        right_forearm.addChild(cube_r4);
        setRotationAngle(cube_r4, 1.9632F, 0.7137F, 1.9949F);
        cube_r4.texOffs(64, 82).addBox(26.0274F, -5.0024F, -4.0524F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r5 = new ModelRenderer(this);
        cube_r5.setPos(10.0F, -5.0F, 20.0F);
        right_forearm.addChild(cube_r5);
        setRotationAngle(cube_r5, -3.0581F, -0.0414F, -3.0999F);
        cube_r5.texOffs(58, 66).addBox(7.1072F, 18.8106F, 18.7758F, 2.0F, 2.0F, 6.0F, 0.0F, false);

        cube_r6 = new ModelRenderer(this);
        cube_r6.setPos(10.0F, -5.0F, 20.0F);
        right_forearm.addChild(cube_r6);
        setRotationAngle(cube_r6, -3.0542F, -0.3023F, -3.1225F);
        cube_r6.texOffs(58, 66).addBox(3.6786F, 18.8106F, 20.4592F, 2.0F, 2.0F, 6.0F, 0.0F, false);

        cube_r7 = new ModelRenderer(this);
        cube_r7.setPos(10.0F, -5.0F, 20.0F);
        right_forearm.addChild(cube_r7);
        setRotationAngle(cube_r7, 3.139F, -0.1719F, -3.111F);
        cube_r7.texOffs(58, 74).addBox(5.448F, 16.8222F, 21.2516F, 2.0F, 2.0F, 6.0F, 0.0F, false);

        cube_r8 = new ModelRenderer(this);
        cube_r8.setPos(10.0F, -5.0F, 20.0F);
        right_forearm.addChild(cube_r8);
        setRotationAngle(cube_r8, -1.268F, -0.1719F, -3.111F);
        cube_r8.texOffs(32, 84).addBox(5.448F, 15.4976F, -23.0448F, 2.0F, 2.0F, 4.0F, 0.0F, false);

        cube_r9 = new ModelRenderer(this);
        cube_r9.setPos(10.0F, -5.0F, 20.0F);
        right_forearm.addChild(cube_r9);
        setRotationAngle(cube_r9, 2.8772F, -0.1719F, -3.111F);
        cube_r9.texOffs(44, 64).addBox(4.448F, 9.691F, 24.5066F, 4.0F, 2.0F, 4.0F, 0.0F, false);

        cube_r10 = new ModelRenderer(this);
        cube_r10.setPos(10.0F, -5.0F, 20.0F);
        right_forearm.addChild(cube_r10);
        setRotationAngle(cube_r10, 1.5245F, -0.1719F, -3.111F);
        cube_r10.texOffs(44, 64).addBox(4.4576F, -22.6118F, 14.1418F, 4.0F, 2.0F, 4.0F, 0.0F, false);

        cube_r11 = new ModelRenderer(this);
        cube_r11.setPos(10.0F, -5.0F, 20.0F);
        right_forearm.addChild(cube_r11);
        setRotationAngle(cube_r11, 0.041F, -0.1719F, -3.111F);
        cube_r11.texOffs(44, 26).addBox(4.448F, -17.506F, -21.0046F, 4.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r12 = new ModelRenderer(this);
        cube_r12.setPos(10.0F, -5.0F, 20.0F);
        right_forearm.addChild(cube_r12);
        setRotationAngle(cube_r12, 2.3106F, -0.0411F, -3.117F);
        cube_r12.texOffs(89, 36).addBox(7.507F, -8.2178F, 29.954F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r13 = new ModelRenderer(this);
        cube_r13.setPos(10.0F, -5.0F, 20.0F);
        right_forearm.addChild(cube_r13);
        setRotationAngle(cube_r13, 2.3084F, -0.3026F, -3.1047F);
        cube_r13.texOffs(64, 82).addBox(3.3044F, -9.414F, 31.1502F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r14 = new ModelRenderer(this);
        cube_r14.setPos(10.0F, -5.0F, 20.0F);
        right_forearm.addChild(cube_r14);
        setRotationAngle(cube_r14, 1.8306F, -0.0411F, -3.117F);
        cube_r14.texOffs(64, 82).addBox(7.507F, -21.957F, 24.6106F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r15 = new ModelRenderer(this);
        cube_r15.setPos(10.0F, -5.0F, 20.0F);
        right_forearm.addChild(cube_r15);
        setRotationAngle(cube_r15, 1.8285F, -0.3026F, -3.1047F);
        cube_r15.texOffs(89, 36).addBox(3.3044F, -23.555F, 25.0742F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r16 = new ModelRenderer(this);
        cube_r16.setPos(10.0F, -5.0F, 20.0F);
        right_forearm.addChild(cube_r16);
        setRotationAngle(cube_r16, 1.6118F, -0.1719F, -3.111F);
        cube_r16.texOffs(46, 24).addBox(4.4418F, -21.099F, 5.7744F, 4.0F, 2.0F, 10.0F, 0.0F, false);

        cube_r17 = new ModelRenderer(this);
        cube_r17.setPos(10.0F, -5.0F, 20.0F);
        right_forearm.addChild(cube_r17);
        setRotationAngle(cube_r17, 1.4373F, -0.1719F, -3.111F);
        cube_r17.texOffs(46, 24).addBox(4.448F, -23.747F, 1.9208F, 4.0F, 2.0F, 10.0F, 0.0F, false);

        cube_r18 = new ModelRenderer(this);
        cube_r18.setPos(10.0F, -5.0F, 20.0F);
        right_forearm.addChild(cube_r18);
        setRotationAngle(cube_r18, 1.5682F, -0.1719F, -3.111F);
        cube_r18.texOffs(32, 84).addBox(5.448F, -22.7594F, 7.0678F, 2.0F, 2.0F, 4.0F, 0.0F, false);

        cube_r19 = new ModelRenderer(this);
        cube_r19.setPos(10.0F, -5.0F, 20.0F);
        right_forearm.addChild(cube_r19);
        setRotationAngle(cube_r19, 1.4809F, -0.1719F, -3.111F);
        cube_r19.texOffs(32, 84).addBox(5.448F, -23.6486F, 8.92F, 2.0F, 2.0F, 4.0F, 0.0F, false);

        right_bicep = new ModelRenderer(this);
        right_bicep.setPos(-4.6924F, 7.269F, -11.2354F);
        right_arm.addChild(right_bicep);
        setRotationAngle(right_bicep, 1.2011F, -0.3061F, -3.0232F);

        cube_r20 = new ModelRenderer(this);
        cube_r20.setPos(-3.1072F, 22.599F, 5.7452F);
        right_bicep.addChild(cube_r20);
        setRotationAngle(cube_r20, -0.1047F, 1.2654F, -1.5708F);
        cube_r20.texOffs(26, 24).addBox(9.475F, -0.8226F, 4.8936F, 2.0F, 2.0F, 14.0F, 0.0F, false);

        cube_r21 = new ModelRenderer(this);
        cube_r21.setPos(-3.1072F, 22.599F, 5.7452F);
        right_bicep.addChild(cube_r21);
        setRotationAngle(cube_r21, 1.2559F, -0.2928F, 0.0302F);
        cube_r21.texOffs(40, 60).addBox(-1.2136F, -12.157F, 4.8458F, 4.0F, 2.0F, 8.0F, 0.0F, false);

        cube_r22 = new ModelRenderer(this);
        cube_r22.setPos(-3.1072F, 22.599F, 5.7452F);
        right_bicep.addChild(cube_r22);
        setRotationAngle(cube_r22, 1.2674F, 0.1883F, -0.1193F);
        cube_r22.texOffs(40, 60).addBox(4.2588F, -11.2518F, 4.9038F, 4.0F, 2.0F, 8.0F, 0.0F, false);

        cube_r23 = new ModelRenderer(this);
        cube_r23.setPos(-3.1072F, 22.599F, 5.7452F);
        right_bicep.addChild(cube_r23);
        setRotationAngle(cube_r23, 1.2654F, 0.0F, 0.0F);
        cube_r23.texOffs(0, 0).addBox(1.1072F, -12.875F, 5.0254F, 4.0F, 4.0F, 16.0F, 0.0F, false);

        cube_r24 = new ModelRenderer(this);
        cube_r24.setPos(-3.1072F, 22.599F, 5.7452F);
        right_bicep.addChild(cube_r24);
        setRotationAngle(cube_r24, 1.1575F, 0.7308F, -0.175F);
        cube_r24.texOffs(26, 24).addBox(7.1434F, -8.9496F, 4.8936F, 2.0F, 2.0F, 14.0F, 0.0F, false);

        cube_r25 = new ModelRenderer(this);
        cube_r25.setPos(0.0F, 2.1898F, -0.3598F);
        right_bicep.addChild(cube_r25);
        setRotationAngle(cube_r25, 1.3963F, 0.0F, 0.0F);
        cube_r25.texOffs(32, 84).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);

        cube_r26 = new ModelRenderer(this);
        cube_r26.setPos(-3.1072F, 22.599F, 5.7452F);
        right_bicep.addChild(cube_r26);
        setRotationAngle(cube_r26, 1.2217F, 0.0F, 0.0F);
        cube_r26.texOffs(64, 82).addBox(2.1072F, -14.0494F, 18.8864F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        left_arm = new ModelRenderer(this);
        left_arm.setPos(5.5F, -22.5F, -4.5F);
        arms.addChild(left_arm);

        left_bicep = new ModelRenderer(this);
        left_bicep.setPos(4.6924F, 7.269F, -11.2354F);
        left_arm.addChild(left_bicep);
        setRotationAngle(left_bicep, 1.2011F, 0.3061F, 3.0232F);

        cube_r27 = new ModelRenderer(this);
        cube_r27.setPos(3.09F, 22.5932F, 5.7454F);
        left_bicep.addChild(cube_r27);
        setRotationAngle(cube_r27, 1.2559F, 0.2928F, -0.0302F);
        cube_r27.texOffs(40, 60).addBox(-2.7702F, -12.1504F, 4.8412F, 4.0F, 2.0F, 8.0F, 0.0F, false);

        cube_r28 = new ModelRenderer(this);
        cube_r28.setPos(3.09F, 22.5932F, 5.7454F);
        left_bicep.addChild(cube_r28);
        setRotationAngle(cube_r28, 1.2674F, -0.1883F, 0.1193F);
        cube_r28.texOffs(40, 60).addBox(-8.2414F, -11.254F, 4.8992F, 4.0F, 2.0F, 8.0F, 0.0F, false);

        cube_r29 = new ModelRenderer(this);
        cube_r29.setPos(3.09F, 22.5932F, 5.7454F);
        left_bicep.addChild(cube_r29);
        setRotationAngle(cube_r29, -0.1047F, -1.2654F, 1.5708F);
        cube_r29.texOffs(26, 24).addBox(-11.4732F, -0.8392F, 4.8862F, 2.0F, 2.0F, 14.0F, 0.0F, false);

        cube_r30 = new ModelRenderer(this);
        cube_r30.setPos(3.09F, 22.5932F, 5.7454F);
        left_bicep.addChild(cube_r30);
        setRotationAngle(cube_r30, 1.2654F, 0.0F, 0.0F);
        cube_r30.texOffs(0, 0).addBox(-5.09F, -12.8732F, 5.0198F, 4.0F, 4.0F, 16.0F, 0.0F, false);

        cube_r31 = new ModelRenderer(this);
        cube_r31.setPos(3.09F, 22.5932F, 5.7454F);
        left_bicep.addChild(cube_r31);
        setRotationAngle(cube_r31, 1.1575F, -0.7308F, 0.175F);
        cube_r31.texOffs(26, 24).addBox(-9.13F, -8.9596F, 4.8862F, 2.0F, 2.0F, 14.0F, 0.0F, false);

        cube_r32 = new ModelRenderer(this);
        cube_r32.setPos(0.0F, 2.1898F, -0.3598F);
        left_bicep.addChild(cube_r32);
        setRotationAngle(cube_r32, 1.3963F, 0.0F, 0.0F);
        cube_r32.texOffs(32, 84).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);

        cube_r33 = new ModelRenderer(this);
        cube_r33.setPos(3.09F, 22.5932F, 5.7454F);
        left_bicep.addChild(cube_r33);
        setRotationAngle(cube_r33, 1.2217F, 0.0F, 0.0F);
        cube_r33.texOffs(64, 82).addBox(-4.09F, -14.0476F, 18.8808F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        left_forearm = new ModelRenderer(this);
        left_forearm.setPos(6.709F, 8.0078F, -10.559F);
        left_arm.addChild(left_forearm);

        cube_r34 = new ModelRenderer(this);
        cube_r34.setPos(-12.0F, -5.0F, 20.0F);
        left_forearm.addChild(cube_r34);
        setRotationAngle(cube_r34, 1.35F, 0.1719F, 3.111F);
        cube_r34.texOffs(64, 82).addBox(-7.4302F, -24.6412F, 11.098F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r35 = new ModelRenderer(this);
        cube_r35.setPos(-12.0F, -5.0F, 20.0F);
        left_forearm.addChild(cube_r35);
        setRotationAngle(cube_r35, 2.3536F, 0.1719F, 3.111F);
        cube_r35.texOffs(84, 72).addBox(-8.2798F, -4.9928F, 26.2984F, 4.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r36 = new ModelRenderer(this);
        cube_r36.setPos(-12.0F, -5.0F, 20.0F);
        left_forearm.addChild(cube_r36);
        setRotationAngle(cube_r36, 1.5025F, 0.7952F, 1.615F);
        cube_r36.texOffs(64, 82).addBox(23.767F, -5.0072F, 8.6728F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r37 = new ModelRenderer(this);
        cube_r37.setPos(-12.0F, -5.0F, 20.0F);
        left_forearm.addChild(cube_r37);
        setRotationAngle(cube_r37, 1.9632F, -0.7137F, -1.9949F);
        cube_r37.texOffs(64, 82).addBox(-28.0218F, -5.0072F, -4.0352F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r38 = new ModelRenderer(this);
        cube_r38.setPos(-12.0F, -5.0F, 20.0F);
        left_forearm.addChild(cube_r38);
        setRotationAngle(cube_r38, -3.0581F, 0.0414F, 3.0999F);
        cube_r38.texOffs(58, 66).addBox(-9.089F, 18.8096F, 18.7752F, 2.0F, 2.0F, 6.0F, 0.0F, false);

        cube_r39 = new ModelRenderer(this);
        cube_r39.setPos(-12.0F, -5.0F, 20.0F);
        left_forearm.addChild(cube_r39);
        setRotationAngle(cube_r39, -3.0542F, 0.3023F, 3.1225F);
        cube_r39.texOffs(58, 66).addBox(-5.6612F, 18.8096F, 20.4538F, 2.0F, 2.0F, 6.0F, 0.0F, false);

        cube_r40 = new ModelRenderer(this);
        cube_r40.setPos(-12.0F, -5.0F, 20.0F);
        left_forearm.addChild(cube_r40);
        setRotationAngle(cube_r40, 3.139F, 0.1719F, 3.111F);
        cube_r40.texOffs(58, 74).addBox(-7.4302F, 16.8218F, 21.2486F, 2.0F, 2.0F, 6.0F, 0.0F, false);

        cube_r41 = new ModelRenderer(this);
        cube_r41.setPos(-12.0F, -5.0F, 20.0F);
        left_forearm.addChild(cube_r41);
        setRotationAngle(cube_r41, -1.268F, 0.1719F, 3.111F);
        cube_r41.texOffs(32, 84).addBox(-7.4302F, 15.4948F, -23.0434F, 2.0F, 2.0F, 4.0F, 0.0F, false);

        cube_r42 = new ModelRenderer(this);
        cube_r42.setPos(-12.0F, -5.0F, 20.0F);
        left_forearm.addChild(cube_r42);
        setRotationAngle(cube_r42, 2.8772F, 0.1719F, 3.111F);
        cube_r42.texOffs(74, 76).addBox(-8.4302F, 9.6914F, 24.5034F, 4.0F, 2.0F, 4.0F, 0.0F, false);

        cube_r43 = new ModelRenderer(this);
        cube_r43.setPos(-12.0F, -5.0F, 20.0F);
        left_forearm.addChild(cube_r43);
        setRotationAngle(cube_r43, 1.5245F, 0.1719F, 3.111F);
        cube_r43.texOffs(44, 64).addBox(-8.4398F, -22.6086F, 14.1414F, 4.0F, 2.0F, 4.0F, 0.0F, false);

        cube_r44 = new ModelRenderer(this);
        cube_r44.setPos(-12.0F, -5.0F, 20.0F);
        left_forearm.addChild(cube_r44);
        setRotationAngle(cube_r44, 0.041F, 0.1719F, 3.111F);
        cube_r44.texOffs(44, 26).addBox(-8.4302F, -17.5052F, -21.0014F, 4.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r45 = new ModelRenderer(this);
        cube_r45.setPos(-12.0F, -5.0F, 20.0F);
        left_forearm.addChild(cube_r45);
        setRotationAngle(cube_r45, 2.3106F, 0.0411F, 3.117F);
        cube_r45.texOffs(88, 36).addBox(-9.4888F, -8.2176F, 29.953F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r46 = new ModelRenderer(this);
        cube_r46.setPos(-12.0F, -5.0F, 20.0F);
        left_forearm.addChild(cube_r46);
        setRotationAngle(cube_r46, 2.3084F, 0.3026F, 3.1047F);
        cube_r46.texOffs(92, 36).addBox(-5.287F, -9.4104F, 31.146F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r47 = new ModelRenderer(this);
        cube_r47.setPos(-12.0F, -5.0F, 20.0F);
        left_forearm.addChild(cube_r47);
        setRotationAngle(cube_r47, 1.8306F, 0.0411F, 3.117F);
        cube_r47.texOffs(89, 36).addBox(-9.4888F, -21.9564F, 24.61F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r48 = new ModelRenderer(this);
        cube_r48.setPos(-12.0F, -5.0F, 20.0F);
        left_forearm.addChild(cube_r48);
        setRotationAngle(cube_r48, 1.8285F, 0.3026F, 3.1047F);
        cube_r48.texOffs(90, 36).addBox(-5.287F, -23.55F, 25.0722F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        cube_r49 = new ModelRenderer(this);
        cube_r49.setPos(-12.0F, -5.0F, 20.0F);
        left_forearm.addChild(cube_r49);
        setRotationAngle(cube_r49, 1.5682F, 0.1719F, 3.111F);
        cube_r49.texOffs(46, 24).addBox(-8.4238F, -21.7204F, 4.8482F, 4.0F, 2.0F, 10.0F, 0.0F, false);
        cube_r49.texOffs(32, 84).addBox(-7.4302F, -22.7562F, 7.0672F, 2.0F, 2.0F, 4.0F, 0.0F, false);

        cube_r50 = new ModelRenderer(this);
        cube_r50.setPos(-12.0F, -5.0F, 20.0F);
        left_forearm.addChild(cube_r50);
        setRotationAngle(cube_r50, 1.4373F, 0.1719F, 3.111F);
        cube_r50.texOffs(46, 24).addBox(-8.4302F, -23.7438F, 1.9206F, 4.0F, 2.0F, 10.0F, 0.0F, false);

        cube_r51 = new ModelRenderer(this);
        cube_r51.setPos(-12.0F, -5.0F, 20.0F);
        left_forearm.addChild(cube_r51);
        setRotationAngle(cube_r51, 1.4809F, 0.1719F, 3.111F);
        cube_r51.texOffs(32, 84).addBox(-7.4302F, -23.6454F, 8.9198F, 2.0F, 2.0F, 4.0F, 0.0F, false);
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        bone2.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.whole_head.yRot = netHeadYaw / (180F / (float) Math.PI);
        this.whole_head.xRot = headPitch / (180F / (float) Math.PI);
        this.jaw_rotation.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount;
        this.left_forearm.xRot = MathHelper.cos(limbSwing) * -1.0F * limbSwingAmount;
        this.right_leg.xRot = MathHelper.cos(limbSwing) * 1.0F * limbSwingAmount;
        this.left_leg.xRot = MathHelper.cos(limbSwing) * -1.0F * limbSwingAmount;
        this.right_forearm.xRot = MathHelper.cos(limbSwing) * 1.0F * limbSwingAmount;
    }
}