// Made with Blockbench 3.9.2
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports

public static class Modelcanis_homini extends EntityModel<Entity> {
	private final ModelRenderer left_hind_leg;
	private final ModelRenderer bone6;
	private final ModelRenderer bone4;
	private final ModelRenderer bone8;
	private final ModelRenderer bone7;
	private final ModelRenderer bone7_r1;
	private final ModelRenderer right_hind_leg;
	private final ModelRenderer bone10;
	private final ModelRenderer bone;
	private final ModelRenderer bone11;
	private final ModelRenderer bone12;
	private final ModelRenderer bone12_r1;
	private final ModelRenderer whole_head;
	private final ModelRenderer whole_head_r1;
	private final ModelRenderer whole_head_r2;
	private final ModelRenderer bone17;
	private final ModelRenderer bone_r1;
	private final ModelRenderer jaw;
	private final ModelRenderer bone5;
	private final ModelRenderer right_fore_leg;
	private final ModelRenderer right_fore_leg_r1_r1;
	private final ModelRenderer right_fore_leg_r1_r2;
	private final ModelRenderer bone2;
	private final ModelRenderer bone2_r1;
	private final ModelRenderer bone2_r1_r1;
	private final ModelRenderer left_fore_leg;
	private final ModelRenderer left_fore_leg_r1_r1;
	private final ModelRenderer left_fore_leg_r1_r2;
	private final ModelRenderer bone3;
	private final ModelRenderer bone3_r1;
	private final ModelRenderer bone3_r1_r1;
	private final ModelRenderer tail;
	private final ModelRenderer tail_tip;
	private final ModelRenderer tail_tip_r1;
	private final ModelRenderer whole_body;
	private final ModelRenderer bone13;
	private final ModelRenderer bone14;
	private final ModelRenderer bone15;
	private final ModelRenderer bone16;
	private final ModelRenderer bone16_r1;
	private final ModelRenderer whole_mane;
	private final ModelRenderer mane2;
	private final ModelRenderer mane_rotation2;
	private final ModelRenderer mane_rotation2_r1;
	private final ModelRenderer mane3;
	private final ModelRenderer mane_rotation3;
	private final ModelRenderer mane_rotation3_r1;
	private final ModelRenderer mane_rotation3_r1_r1;
	private final ModelRenderer vertebrae;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer cube_r4;
	private final ModelRenderer bone18;
	private final ModelRenderer cube_r5;
	private final ModelRenderer cube_r6;
	private final ModelRenderer bone19;
	private final ModelRenderer cube_r7;
	private final ModelRenderer cube_r8;
	private final ModelRenderer CADAVER;
	private final ModelRenderer head;
	private final ModelRenderer bone_r7;
	private final ModelRenderer eyes;
	private final ModelRenderer jaw2;
	private final ModelRenderer bone_r2;
	private final ModelRenderer body;
	private final ModelRenderer bone_r3;
	private final ModelRenderer bone_r4;
	private final ModelRenderer bone_r4_r1;
	private final ModelRenderer right_arm;
	private final ModelRenderer cube_r9;
	private final ModelRenderer cube_r10;
	private final ModelRenderer right_arm_r1;
	private final ModelRenderer bone_r6_r1;
	private final ModelRenderer left_arm;
	private final ModelRenderer left_arm_r1;
	private final ModelRenderer bone_r5_r1;
	private final ModelRenderer ARCHER;
	private final ModelRenderer head2;
	private final ModelRenderer bone_r5;
	private final ModelRenderer eyes2;
	private final ModelRenderer jaw3;
	private final ModelRenderer bone_r6;
	private final ModelRenderer body2;
	private final ModelRenderer bone_r8;
	private final ModelRenderer bone_r4_r2;
	private final ModelRenderer right_arm2;
	private final ModelRenderer right_arm_r2;
	private final ModelRenderer bone_r6_r2;
	private final ModelRenderer left_arm2;
	private final ModelRenderer left_arm_r2;
	private final ModelRenderer bow_r1;
	private final ModelRenderer bow_r1_r1;
	private final ModelRenderer bone_r5_r2;

	public Modelcanis_homini() {
		textureWidth = 128;
		textureHeight = 128;

		left_hind_leg = new ModelRenderer(this);
		left_hind_leg.setRotationPoint(4.5F, 7.4251F, 6.4202F);
		setRotationAngle(left_hind_leg, -0.2618F, 0.0F, 0.0F);

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(-1.5F, 10.0F, -7.0F);
		left_hind_leg.addChild(bone6);
		setRotationAngle(bone6, -0.5672F, 0.0F, 0.0F);

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(-2.0F, 14.5F, 1.0F);
		bone6.addChild(bone4);
		setRotationAngle(bone4, 0.1309F, 0.0F, 0.0F);
		bone4.setTextureOffset(17, 80).addBox(2.25F, -29.4251F, 0.5798F, 3.0F, 11.0F, 5.0F, 0.0F, false);

		bone8 = new ModelRenderer(this);
		bone8.setRotationPoint(-2.5F, -1.0F, 6.0F);
		bone6.addChild(bone8);
		setRotationAngle(bone8, 1.2653F, 0.0F, 0.0F);
		bone8.setTextureOffset(44, 93).addBox(2.25F, -6.9251F, 0.5798F, 3.0F, 7.0F, 3.0F, 0.0F, false);

		bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(0.2F, 1.0F, 9.9F);
		bone6.addChild(bone7);
		setRotationAngle(bone7, 0.5672F, 0.0F, 0.0F);

		bone7_r1 = new ModelRenderer(this);
		bone7_r1.setRotationPoint(1.3F, -5.1751F, 0.1798F);
		bone7.addChild(bone7_r1);
		setRotationAngle(bone7_r1, 0.2618F, 0.0F, 0.0F);
		bone7_r1.setTextureOffset(92, 4).addBox(-1.5F, -1.0F, -2.0F, 3.0F, 7.0F, 3.0F, 0.0F, false);
		bone7_r1.setTextureOffset(0, 8).addBox(-1.5F, 5.0F, -3.0F, 3.0F, 1.0F, 1.0F, 0.0F, false);
		bone7_r1.setTextureOffset(8, 25).addBox(1.0F, 4.75F, -3.25F, 0.0F, 1.0F, 1.0F, 0.0F, false);
		bone7_r1.setTextureOffset(8, 25).addBox(-1.0F, 4.75F, -3.25F, 0.0F, 1.0F, 1.0F, 0.0F, false);
		bone7_r1.setTextureOffset(8, 25).addBox(0.0F, 4.75F, -3.25F, 0.0F, 1.0F, 1.0F, 0.0F, false);

		right_hind_leg = new ModelRenderer(this);
		right_hind_leg.setRotationPoint(-4.5F, 7.4251F, 6.4202F);
		setRotationAngle(right_hind_leg, -0.2618F, 0.0F, 0.0F);

		bone10 = new ModelRenderer(this);
		bone10.setRotationPoint(3.5F, 9.9409F, -6.8377F);
		right_hind_leg.addChild(bone10);
		setRotationAngle(bone10, -0.5672F, 0.0F, 0.0F);

		bone = new ModelRenderer(this);
		bone.setRotationPoint(-4.0F, -11.4409F, -0.1623F);
		bone10.addChild(bone);
		setRotationAngle(bone, 0.1309F, 0.0F, 0.0F);
		bone.setTextureOffset(17, 80).addBox(-1.25F, -3.4251F, -1.6702F, 3.0F, 11.0F, 5.0F, 0.0F, false);

		bone11 = new ModelRenderer(this);
		bone11.setRotationPoint(1.5F, -0.9409F, 5.8377F);
		bone10.addChild(bone11);
		setRotationAngle(bone11, 1.2653F, 0.0F, 0.0F);
		bone11.setTextureOffset(44, 93).addBox(-6.25F, -6.9251F, 0.5798F, 3.0F, 7.0F, 3.0F, 0.0F, false);

		bone12 = new ModelRenderer(this);
		bone12.setRotationPoint(-2.2F, 1.0591F, 9.7377F);
		bone10.addChild(bone12);
		setRotationAngle(bone12, 0.5672F, 0.0F, 0.0F);

		bone12_r1 = new ModelRenderer(this);
		bone12_r1.setRotationPoint(-1.3F, -5.1751F, 0.1798F);
		bone12.addChild(bone12_r1);
		setRotationAngle(bone12_r1, 0.2618F, 0.0F, 0.0F);
		bone12_r1.setTextureOffset(92, 4).addBox(-1.5F, -1.0F, -2.0F, 3.0F, 7.0F, 3.0F, 0.0F, false);
		bone12_r1.setTextureOffset(0, 8).addBox(-1.5F, 5.0F, -3.0F, 3.0F, 1.0F, 1.0F, 0.0F, false);
		bone12_r1.setTextureOffset(8, 25).addBox(1.0F, 4.75F, -3.25F, 0.0F, 1.0F, 1.0F, 0.0F, false);
		bone12_r1.setTextureOffset(8, 25).addBox(-1.0F, 4.75F, -3.25F, 0.0F, 1.0F, 1.0F, 0.0F, false);
		bone12_r1.setTextureOffset(8, 25).addBox(0.0F, 4.75F, -3.25F, 0.0F, 1.0F, 1.0F, 0.0F, false);

		whole_head = new ModelRenderer(this);
		whole_head.setRotationPoint(0.0F, -9.5814F, -7.2753F);
		setRotationAngle(whole_head, 0.0001F, 0.0F, 0.0F);
		whole_head.setTextureOffset(0, 57).addBox(-5.5F, -4.1686F, -4.4747F, 11.0F, 10.0F, 5.0F, 0.0F, false);
		whole_head.setTextureOffset(72, 23).addBox(-4.25F, -2.6686F, -7.4747F, 8.0F, 8.0F, 4.0F, 0.0F, false);
		whole_head.setTextureOffset(92, 80).addBox(-2.25F, 0.5814F, -10.4747F, 4.0F, 2.0F, 3.0F, 0.0F, false);

		whole_head_r1 = new ModelRenderer(this);
		whole_head_r1.setRotationPoint(0.0F, 17.5F, 15.0F);
		whole_head.addChild(whole_head_r1);
		setRotationAngle(whole_head_r1, 0.258F, -0.045F, 0.1687F);
		whole_head_r1.setTextureOffset(43, 46).addBox(-3.0F, -28.1686F, -12.9747F, 3.0F, 3.0F, 1.0F, 0.0F, false);

		whole_head_r2 = new ModelRenderer(this);
		whole_head_r2.setRotationPoint(0.0F, 17.5F, 15.0F);
		whole_head.addChild(whole_head_r2);
		setRotationAngle(whole_head_r2, 0.258F, 0.045F, -0.1687F);
		whole_head_r2.setTextureOffset(43, 46).addBox(0.0F, -28.1686F, -12.9747F, 3.0F, 3.0F, 1.0F, 0.0F, false);

		bone17 = new ModelRenderer(this);
		bone17.setRotationPoint(1.5F, 6.25F, 1.5F);
		whole_head.addChild(bone17);

		bone_r1 = new ModelRenderer(this);
		bone_r1.setRotationPoint(-1.5F, 17.5588F, -0.707F);
		bone17.addChild(bone_r1);
		setRotationAngle(bone_r1, -0.9163F, 0.0F, 0.0F);
		bone_r1.setTextureOffset(0, 38).addBox(-1.25F, -8.4774F, -25.0177F, 2.0F, 3.0F, 2.0F, 0.0F, false);

		jaw = new ModelRenderer(this);
		jaw.setRotationPoint(0.0F, 3.5814F, -7.7247F);
		whole_head.addChild(jaw);
		setRotationAngle(jaw, 0.9599F, 0.0F, 0.0F);
		jaw.setTextureOffset(0, 91).addBox(-2.25F, -0.75F, -2.75F, 4.0F, 2.0F, 4.0F, 0.0F, false);

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(-1.5F, 0.7401F, 21.1239F);
		jaw.addChild(bone5);
		setRotationAngle(bone5, -0.2182F, 0.0F, 0.0F);
		bone5.setTextureOffset(93, 20).addBox(-0.25F, 3.7599F, -23.1239F, 3.0F, 2.0F, 3.0F, 0.0F, false);

		right_fore_leg = new ModelRenderer(this);
		right_fore_leg.setRotationPoint(-5.5F, -3.7002F, -6.121F);
		setRotationAngle(right_fore_leg, -0.5606F, -0.3236F, 0.3344F);

		right_fore_leg_r1_r1 = new ModelRenderer(this);
		right_fore_leg_r1_r1.setRotationPoint(1.0F, 9.7002F, 5.621F);
		right_fore_leg.addChild(right_fore_leg_r1_r1);
		setRotationAngle(right_fore_leg_r1_r1, 0.2967F, 0.0654F, -0.2094F);
		right_fore_leg_r1_r1.setTextureOffset(84, 66).addBox(-1.5F, -5.0F, -1.5F, 3.0F, 10.0F, 3.0F, 0.0F, false);

		right_fore_leg_r1_r2 = new ModelRenderer(this);
		right_fore_leg_r1_r2.setRotationPoint(7.25F, 19.2002F, 12.871F);
		right_fore_leg.addChild(right_fore_leg_r1_r2);
		setRotationAngle(right_fore_leg_r1_r2, 0.2967F, 0.0654F, -0.2094F);
		right_fore_leg_r1_r2.setTextureOffset(8, 25).addBox(-2.5F, -8.0F, -5.75F, 0.0F, 1.0F, 1.0F, 0.0F, false);
		right_fore_leg_r1_r2.setTextureOffset(8, 25).addBox(-4.5F, -8.0F, -5.75F, 0.0F, 1.0F, 1.0F, 0.0F, false);
		right_fore_leg_r1_r2.setTextureOffset(8, 25).addBox(-3.5F, -8.0F, -5.75F, 0.0F, 1.0F, 1.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(3.5F, 8.9337F, 7.7018F);
		right_fore_leg.addChild(bone2);
		setRotationAngle(bone2, 0.5236F, 0.0F, 0.0F);

		bone2_r1 = new ModelRenderer(this);
		bone2_r1.setRotationPoint(0.0F, 6.0F, 6.0F);
		bone2.addChild(bone2_r1);
		setRotationAngle(bone2_r1, -0.0436F, 0.0F, 0.0F);

		bone2_r1_r1 = new ModelRenderer(this);
		bone2_r1_r1.setRotationPoint(0.0F, 0.0768F, 0.0F);
		bone2_r1.addChild(bone2_r1_r1);
		setRotationAngle(bone2_r1_r1, 0.0873F, 0.0F, 0.0F);
		bone2_r1_r1.setTextureOffset(0, 73).addBox(-5.25F, -21.5603F, -9.5808F, 3.0F, 12.0F, 5.0F, 0.0F, false);

		left_fore_leg = new ModelRenderer(this);
		left_fore_leg.setRotationPoint(5.5F, -3.7002F, -6.121F);
		setRotationAngle(left_fore_leg, -0.5606F, 0.3236F, -0.3344F);

		left_fore_leg_r1_r1 = new ModelRenderer(this);
		left_fore_leg_r1_r1.setRotationPoint(-1.0F, 9.7002F, 5.621F);
		left_fore_leg.addChild(left_fore_leg_r1_r1);
		setRotationAngle(left_fore_leg_r1_r1, 0.3002F, -0.0576F, 0.1825F);
		left_fore_leg_r1_r1.setTextureOffset(84, 66).addBox(-1.25F, -5.0F, -1.5F, 3.0F, 10.0F, 3.0F, 0.0F, false);

		left_fore_leg_r1_r2 = new ModelRenderer(this);
		left_fore_leg_r1_r2.setRotationPoint(-6.5F, 19.4502F, 12.871F);
		left_fore_leg.addChild(left_fore_leg_r1_r2);
		setRotationAngle(left_fore_leg_r1_r2, 0.3011F, -0.0524F, 0.1658F);
		left_fore_leg_r1_r2.setTextureOffset(8, 25).addBox(4.75F, -8.0F, -5.75F, 0.0F, 1.0F, 1.0F, 0.0F, false);
		left_fore_leg_r1_r2.setTextureOffset(8, 25).addBox(2.75F, -8.0F, -5.75F, 0.0F, 1.0F, 1.0F, 0.0F, false);
		left_fore_leg_r1_r2.setTextureOffset(8, 25).addBox(3.75F, -8.0F, -5.75F, 0.0F, 1.0F, 1.0F, 0.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(-3.5F, 8.9337F, 7.7018F);
		left_fore_leg.addChild(bone3);
		setRotationAngle(bone3, 0.5236F, 0.0F, 0.0F);

		bone3_r1 = new ModelRenderer(this);
		bone3_r1.setRotationPoint(0.0F, 6.0F, 6.0F);
		bone3.addChild(bone3_r1);
		setRotationAngle(bone3_r1, -0.0436F, 0.0F, 0.0F);

		bone3_r1_r1 = new ModelRenderer(this);
		bone3_r1_r1.setRotationPoint(0.0F, 0.0768F, 0.0F);
		bone3_r1.addChild(bone3_r1_r1);
		setRotationAngle(bone3_r1_r1, 0.0873F, 0.0F, 0.0F);
		bone3_r1_r1.setTextureOffset(0, 73).addBox(2.25F, -21.5603F, -9.5808F, 3.0F, 12.0F, 5.0F, 0.0F, false);

		tail = new ModelRenderer(this);
		tail.setRotationPoint(0.0F, 8.679F, 9.1341F);
		setRotationAngle(tail, 0.1309F, 0.0F, 0.0F);
		tail.setTextureOffset(34, 80).addBox(-1.5F, -0.179F, -1.6341F, 3.0F, 12.0F, 3.0F, 0.0F, false);

		tail_tip = new ModelRenderer(this);
		tail_tip.setRotationPoint(1.0F, 4.7373F, -4.6756F);
		tail.addChild(tail_tip);
		setRotationAngle(tail_tip, -0.3753F, 0.0F, 0.0F);

		tail_tip_r1 = new ModelRenderer(this);
		tail_tip_r1.setRotationPoint(-1.0F, 4.7954F, 6.6739F);
		tail_tip.addChild(tail_tip_r1);
		setRotationAngle(tail_tip_r1, 0.829F, 0.0F, 0.0F);
		tail_tip_r1.setTextureOffset(94, 43).addBox(-1.5F, -0.4617F, -1.3824F, 3.0F, 5.0F, 3.0F, 0.0F, false);

		whole_body = new ModelRenderer(this);
		whole_body.setRotationPoint(0.0F, -4.7815F, 3.3041F);
		setRotationAngle(whole_body, -1.1781F, 0.0F, 0.0F);

		bone13 = new ModelRenderer(this);
		bone13.setRotationPoint(0.0F, 6.935F, 1.1943F);
		whole_body.addChild(bone13);
		setRotationAngle(bone13, 1.1781F, 0.0F, 0.0F);
		bone13.setTextureOffset(43, 46).addBox(-5.0F, 1.8465F, 5.0016F, 10.0F, 9.0F, 9.0F, 0.0F, false);

		bone14 = new ModelRenderer(this);
		bone14.setRotationPoint(0.0F, 13.2669F, 8.0908F);
		whole_body.addChild(bone14);
		setRotationAngle(bone14, 1.4836F, 0.0F, 0.0F);
		bone14.setTextureOffset(37, 28).addBox(-6.0F, -8.4854F, 9.3551F, 12.0F, 7.0F, 10.0F, 0.0F, false);

		bone15 = new ModelRenderer(this);
		bone15.setRotationPoint(1.0F, 9.935F, 1.1943F);
		whole_body.addChild(bone15);

		bone16 = new ModelRenderer(this);
		bone16.setRotationPoint(-1.0F, -2.5F, -2.5F);
		bone15.addChild(bone16);
		setRotationAngle(bone16, 2.0944F, 0.0F, 0.0F);

		bone16_r1 = new ModelRenderer(this);
		bone16_r1.setRotationPoint(0.0F, 14.7841F, 0.2886F);
		bone16.addChild(bone16_r1);
		setRotationAngle(bone16_r1, 0.3054F, 0.0F, 0.0F);
		bone16_r1.setTextureOffset(0, 38).addBox(-5.5F, -16.1876F, 4.213F, 11.0F, 8.0F, 10.0F, 0.0F, false);

		whole_mane = new ModelRenderer(this);
		whole_mane.setRotationPoint(0.0F, -1.065F, -9.8057F);
		whole_body.addChild(whole_mane);

		mane2 = new ModelRenderer(this);
		mane2.setRotationPoint(1.0F, 11.0F, 8.0F);
		whole_mane.addChild(mane2);

		mane_rotation2 = new ModelRenderer(this);
		mane_rotation2.setRotationPoint(-1.0F, 2.5F, -2.5F);
		mane2.addChild(mane_rotation2);
		setRotationAngle(mane_rotation2, 1.5708F, 0.0F, 0.0F);

		mane_rotation2_r1 = new ModelRenderer(this);
		mane_rotation2_r1.setRotationPoint(0.0F, 9.7841F, 3.2886F);
		mane_rotation2.addChild(mane_rotation2_r1);
		setRotationAngle(mane_rotation2_r1, 0.9599F, 0.0F, 0.0F);
		mane_rotation2_r1.setTextureOffset(0, 19).addBox(-6.0F, -8.4376F, 6.213F, 12.0F, 7.0F, 11.0F, 0.0F, false);

		mane3 = new ModelRenderer(this);
		mane3.setRotationPoint(1.0F, 14.0F, 12.0F);
		whole_mane.addChild(mane3);
		setRotationAngle(mane3, -0.6545F, 0.0F, 0.0F);

		mane_rotation3 = new ModelRenderer(this);
		mane_rotation3.setRotationPoint(-1.0F, -1.5F, -1.5F);
		mane3.addChild(mane_rotation3);
		setRotationAngle(mane_rotation3, 2.0944F, 0.0F, 0.0F);

		mane_rotation3_r1 = new ModelRenderer(this);
		mane_rotation3_r1.setRotationPoint(0.0F, 13.5F, -0.5F);
		mane_rotation3.addChild(mane_rotation3_r1);
		setRotationAngle(mane_rotation3_r1, 0.0873F, 0.0F, 0.0F);

		mane_rotation3_r1_r1 = new ModelRenderer(this);
		mane_rotation3_r1_r1.setRotationPoint(0.0F, -2.7159F, -1.2114F);
		mane_rotation3_r1.addChild(mane_rotation3_r1_r1);
		setRotationAngle(mane_rotation3_r1_r1, 0.5236F, 0.0F, 0.0F);
		mane_rotation3_r1_r1.setTextureOffset(0, 0).addBox(-6.5F, -7.1876F, 12.963F, 13.0F, 7.0F, 11.0F, 0.0F, false);

		vertebrae = new ModelRenderer(this);
		vertebrae.setRotationPoint(-0.5F, -5.065F, 8.6943F);
		whole_body.addChild(vertebrae);
		setRotationAngle(vertebrae, 1.3526F, 0.0F, 0.0F);
		vertebrae.setTextureOffset(36, 24).addBox(0.0F, -0.9658F, -0.7115F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.5F, -14.2292F, -6.7723F);
		vertebrae.addChild(cube_r1);
		setRotationAngle(cube_r1, 1.2217F, 0.0F, 0.0F);
		cube_r1.setTextureOffset(36, 19).addBox(-0.5F, -1.7366F, -0.4392F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(0.5F, -12.2151F, -3.8963F);
		vertebrae.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.9163F, 0.0F, 0.0F);
		cube_r2.setTextureOffset(36, 24).addBox(-0.5F, -0.7507F, -0.5652F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(0.5F, -9.9081F, -1.1878F);
		vertebrae.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.9163F, 0.0F, 0.0F);
		cube_r3.setTextureOffset(36, 24).addBox(-0.5F, -0.5577F, -1.2737F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(0.5F, -4.0425F, 0.162F);
		vertebrae.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.3054F, 0.0F, 0.0F);
		cube_r4.setTextureOffset(36, 24).addBox(-0.5F, -2.9233F, -0.1235F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		cube_r4.setTextureOffset(36, 24).addBox(-0.5F, 0.0767F, -0.6235F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		bone18 = new ModelRenderer(this);
		bone18.setRotationPoint(-1.5F, -15.7658F, -9.4615F);
		vertebrae.addChild(bone18);
		setRotationAngle(bone18, 0.0873F, 0.3491F, 0.0F);

		cube_r5 = new ModelRenderer(this);
		cube_r5.setRotationPoint(-0.4201F, -0.4813F, -0.058F);
		bone18.addChild(cube_r5);
		setRotationAngle(cube_r5, 1.2955F, -0.0979F, -0.3417F);
		cube_r5.setTextureOffset(0, 0).addBox(-1.5174F, -5.589F, -0.5F, 3.0F, 6.0F, 1.0F, 0.0F, false);
		cube_r5.setTextureOffset(0, 26).addBox(-1.5174F, 0.411F, -0.5F, 2.0F, 2.0F, 1.0F, 0.0F, false);

		cube_r6 = new ModelRenderer(this);
		cube_r6.setRotationPoint(-0.4201F, -0.4813F, -0.058F);
		bone18.addChild(cube_r6);
		setRotationAngle(cube_r6, 1.2463F, -0.5574F, -0.1932F);
		cube_r6.setTextureOffset(36, 24).addBox(0.5184F, -0.2021F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		bone19 = new ModelRenderer(this);
		bone19.setRotationPoint(-1.5F, -15.7658F, -9.4615F);
		vertebrae.addChild(bone19);
		setRotationAngle(bone19, 0.0873F, 0.3491F, 0.0F);

		cube_r7 = new ModelRenderer(this);
		cube_r7.setRotationPoint(4.1069F, -0.1334F, 1.9046F);
		bone19.addChild(cube_r7);
		setRotationAngle(cube_r7, 1.026F, -0.4941F, 0.4101F);
		cube_r7.setTextureOffset(0, 19).addBox(-1.4826F, -4.7557F, -0.5F, 3.0F, 5.0F, 1.0F, 0.0F, false);
		cube_r7.setTextureOffset(0, 26).addBox(-0.4826F, 0.2443F, -0.5F, 2.0F, 2.0F, 1.0F, 0.0F, true);

		cube_r8 = new ModelRenderer(this);
		cube_r8.setRotationPoint(4.1069F, -0.1334F, 1.9046F);
		bone19.addChild(cube_r8);
		setRotationAngle(cube_r8, 1.0956F, -0.0731F, 0.1678F);
		cube_r8.setTextureOffset(36, 24).addBox(-1.4414F, -0.3499F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, true);

		CADAVER = new ModelRenderer(this);
		CADAVER.setRotationPoint(0.0F, 24.0F, 0.0F);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -41.4038F, 8.5661F);
		CADAVER.addChild(head);
		setRotationAngle(head, -0.0873F, 0.0F, 0.0F);
		head.setTextureOffset(41, 11).addBox(-4.0F, -7.3462F, -5.0661F, 8.0F, 7.0F, 8.0F, 0.0F, false);

		bone_r7 = new ModelRenderer(this);
		bone_r7.setRotationPoint(0.1194F, 31.3408F, -2.0637F);
		head.addChild(bone_r7);
		setRotationAngle(bone_r7, -1.4835F, 0.0F, 0.0F);
		bone_r7.setTextureOffset(82, 36).addBox(-3.1194F, -4.687F, -33.0024F, 6.0F, 3.0F, 3.0F, 0.0F, false);

		eyes = new ModelRenderer(this);
		eyes.setRotationPoint(-0.3806F, 28.7089F, -7.6378F);
		head.addChild(eyes);
		eyes.setTextureOffset(38, 0).addBox(-2.1194F, -32.5551F, 2.3217F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		eyes.setTextureOffset(38, 0).addBox(1.8806F, -32.5551F, 2.3217F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		jaw2 = new ModelRenderer(this);
		jaw2.setRotationPoint(0.1194F, -0.2331F, 0.2622F);
		head.addChild(jaw2);
		setRotationAngle(jaw2, 0.2181F, 0.0F, 0.0F);

		bone_r2 = new ModelRenderer(this);
		bone_r2.setRotationPoint(0.0F, 31.4577F, -9.4543F);
		jaw2.addChild(bone_r2);
		setRotationAngle(bone_r2, 0.3054F, 0.0F, 0.0F);
		bone_r2.setTextureOffset(73, 46).addBox(-3.6194F, -27.8208F, 13.126F, 7.0F, 2.0F, 6.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -24.7527F, 21.7727F);
		CADAVER.addChild(body);
		setRotationAngle(body, -0.7854F, 0.0F, 0.0F);

		bone_r3 = new ModelRenderer(this);
		bone_r3.setRotationPoint(0.0F, 2.1021F, -18.0669F);
		body.addChild(bone_r3);
		setRotationAngle(bone_r3, 0.829F, 0.0F, 0.0F);
		bone_r3.setTextureOffset(58, 65).addBox(-4.0F, -5.3494F, 0.0442F, 8.0F, 12.0F, 4.0F, 0.0F, false);

		bone_r4 = new ModelRenderer(this);
		bone_r4.setRotationPoint(-3.4F, -17.2065F, -11.0176F);
		body.addChild(bone_r4);
		setRotationAngle(bone_r4, 1.5708F, 0.0F, 0.0F);

		bone_r4_r1 = new ModelRenderer(this);
		bone_r4_r1.setRotationPoint(3.4F, 0.9565F, -22.9824F);
		bone_r4.addChild(bone_r4_r1);
		setRotationAngle(bone_r4_r1, -0.4363F, 0.0F, 0.0F);
		bone_r4_r1.setTextureOffset(74, 13).addBox(-3.5F, -2.9973F, -2.5227F, 7.0F, 6.0F, 3.0F, 0.0F, false);

		right_arm = new ModelRenderer(this);
		right_arm.setRotationPoint(5.4408F, -36.8777F, 7.0549F);
		CADAVER.addChild(right_arm);
		setRotationAngle(right_arm, -0.3491F, 0.0F, 0.0F);

		cube_r9 = new ModelRenderer(this);
		cube_r9.setRotationPoint(-9.1908F, 1.61F, -5.7728F);
		right_arm.addChild(cube_r9);
		setRotationAngle(cube_r9, 1.0297F, 1.1388F, 0.8814F);
		cube_r9.setTextureOffset(87, 0).addBox(-3.75F, -0.2323F, -1.5321F, 7.0F, 0.0F, 3.0F, 0.0F, false);

		cube_r10 = new ModelRenderer(this);
		cube_r10.setRotationPoint(-1.6908F, 1.36F, -5.7728F);
		right_arm.addChild(cube_r10);
		setRotationAngle(cube_r10, 2.3169F, 1.117F, 2.4086F);
		cube_r10.setTextureOffset(87, 0).addBox(-3.75F, 0.0177F, -1.5321F, 7.0F, 0.0F, 3.0F, 0.0F, false);

		right_arm_r1 = new ModelRenderer(this);
		right_arm_r1.setRotationPoint(-0.1908F, 1.11F, -1.2728F);
		right_arm.addChild(right_arm_r1);
		setRotationAngle(right_arm_r1, -1.2174F, 0.4232F, -0.3883F);
		right_arm_r1.setTextureOffset(88, 87).addBox(-1.5F, -3.7323F, -2.0321F, 3.0F, 6.0F, 4.0F, 0.0F, false);

		bone_r6_r1 = new ModelRenderer(this);
		bone_r6_r1.setRotationPoint(-0.1908F, -1.89F, 0.9772F);
		right_arm.addChild(bone_r6_r1);
		setRotationAngle(bone_r6_r1, -0.1702F, 0.4232F, -0.3883F);
		bone_r6_r1.setTextureOffset(82, 55).addBox(-2.0F, -2.9823F, -1.7821F, 4.0F, 6.0F, 4.0F, 0.0F, false);

		left_arm = new ModelRenderer(this);
		left_arm.setRotationPoint(-5.377F, -36.8815F, 6.5926F);
		CADAVER.addChild(left_arm);
		setRotationAngle(left_arm, -0.3491F, 0.0F, 0.0F);

		left_arm_r1 = new ModelRenderer(this);
		left_arm_r1.setRotationPoint(-0.123F, 1.9554F, -0.8371F);
		left_arm.addChild(left_arm_r1);
		setRotationAngle(left_arm_r1, -1.2348F, -0.3622F, 0.3185F);
		left_arm_r1.setTextureOffset(88, 87).addBox(-1.5F, -4.0739F, -3.0055F, 3.0F, 6.0F, 4.0F, 0.0F, false);

		bone_r5_r1 = new ModelRenderer(this);
		bone_r5_r1.setRotationPoint(0.127F, -1.7946F, 0.9129F);
		left_arm.addChild(bone_r5_r1);
		setRotationAngle(bone_r5_r1, -0.144F, -0.3622F, 0.3185F);
		bone_r5_r1.setTextureOffset(82, 55).addBox(-2.0F, -3.0739F, -1.2555F, 4.0F, 6.0F, 4.0F, 0.0F, false);

		ARCHER = new ModelRenderer(this);
		ARCHER.setRotationPoint(0.0F, 20.25F, 7.0F);
		setRotationAngle(ARCHER, 0.3491F, 0.0F, 0.0F);

		head2 = new ModelRenderer(this);
		head2.setRotationPoint(0.0F, -20.4038F, 0.3161F);
		ARCHER.addChild(head2);
		setRotationAngle(head2, -0.0873F, 0.0F, 0.0F);
		head2.setTextureOffset(25, 65).addBox(-4.0F, -6.3462F, -5.0661F, 8.0F, 6.0F, 8.0F, 0.0F, false);

		bone_r5 = new ModelRenderer(this);
		bone_r5.setRotationPoint(0.1194F, 31.3408F, -2.0637F);
		head2.addChild(bone_r5);
		setRotationAngle(bone_r5, -1.4835F, 0.0F, 0.0F);
		bone_r5.setTextureOffset(82, 36).addBox(-3.1194F, -4.687F, -33.0024F, 6.0F, 3.0F, 3.0F, 0.0F, false);

		eyes2 = new ModelRenderer(this);
		eyes2.setRotationPoint(-0.3806F, 28.7089F, -7.6378F);
		head2.addChild(eyes2);
		eyes2.setTextureOffset(38, 0).addBox(-2.1194F, -32.5551F, 2.3217F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		eyes2.setTextureOffset(38, 0).addBox(1.8806F, -32.5551F, 2.3217F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		jaw3 = new ModelRenderer(this);
		jaw3.setRotationPoint(0.1194F, -0.2331F, 0.2622F);
		head2.addChild(jaw3);
		setRotationAngle(jaw3, -0.0873F, 0.0F, 0.0F);

		bone_r6 = new ModelRenderer(this);
		bone_r6.setRotationPoint(0.0F, 31.4577F, -9.4543F);
		jaw3.addChild(bone_r6);
		setRotationAngle(bone_r6, 0.3054F, 0.0F, 0.0F);
		bone_r6.setTextureOffset(38, 0).addBox(-3.6194F, -27.8208F, 13.126F, 7.0F, 2.0F, 6.0F, 0.0F, false);

		body2 = new ModelRenderer(this);
		body2.setRotationPoint(0.0F, -0.7527F, 12.7727F);
		ARCHER.addChild(body2);
		setRotationAngle(body2, -0.7854F, 0.0F, 0.0F);

		bone_r8 = new ModelRenderer(this);
		bone_r8.setRotationPoint(0.0F, 2.1021F, -18.0669F);
		body2.addChild(bone_r8);
		setRotationAngle(bone_r8, 0.829F, 0.0F, 0.0F);

		bone_r4_r2 = new ModelRenderer(this);
		bone_r4_r2.setRotationPoint(0.0F, 26.9006F, -0.9558F);
		bone_r8.addChild(bone_r4_r2);
		setRotationAngle(bone_r4_r2, 0.1309F, 0.0F, 0.0F);
		bone_r4_r2.setTextureOffset(66, 0).addBox(-4.0F, -33.25F, 7.0F, 8.0F, 8.0F, 4.0F, 0.0F, false);

		right_arm2 = new ModelRenderer(this);
		right_arm2.setRotationPoint(5.4408F, -15.8777F, 0.0549F);
		ARCHER.addChild(right_arm2);
		setRotationAngle(right_arm2, -0.3491F, 0.0F, 0.0F);

		right_arm_r2 = new ModelRenderer(this);
		right_arm_r2.setRotationPoint(-0.1908F, 1.11F, -1.2728F);
		right_arm2.addChild(right_arm_r2);
		setRotationAngle(right_arm_r2, -1.2174F, 0.4232F, -0.3883F);
		right_arm_r2.setTextureOffset(64, 87).addBox(-1.5F, -3.7323F, -2.0321F, 3.0F, 6.0F, 4.0F, 0.0F, false);

		bone_r6_r2 = new ModelRenderer(this);
		bone_r6_r2.setRotationPoint(-0.1908F, -1.89F, 0.9772F);
		right_arm2.addChild(bone_r6_r2);
		setRotationAngle(bone_r6_r2, -0.1702F, 0.4232F, -0.3883F);
		bone_r6_r2.setTextureOffset(47, 82).addBox(-2.0F, -2.9823F, -1.7821F, 4.0F, 6.0F, 4.0F, 0.0F, false);

		left_arm2 = new ModelRenderer(this);
		left_arm2.setRotationPoint(-5.377F, -15.8815F, -0.4074F);
		ARCHER.addChild(left_arm2);
		setRotationAngle(left_arm2, -0.3491F, 0.0F, 0.0F);

		left_arm_r2 = new ModelRenderer(this);
		left_arm_r2.setRotationPoint(-0.123F, 1.9554F, -0.8371F);
		left_arm2.addChild(left_arm_r2);
		setRotationAngle(left_arm_r2, -1.2348F, -0.3622F, 0.3185F);
		left_arm_r2.setTextureOffset(64, 87).addBox(-1.5F, -4.0739F, -3.0055F, 3.0F, 6.0F, 4.0F, 0.0F, false);

		bow_r1 = new ModelRenderer(this);
		bow_r1.setRotationPoint(-1.5F, 1.6761F, -0.5055F);
		left_arm_r2.addChild(bow_r1);
		setRotationAngle(bow_r1, 0.2051F, -0.1222F, -3.0587F);

		bow_r1_r1 = new ModelRenderer(this);
		bow_r1_r1.setRotationPoint(-3.0F, 0.75F, -0.75F);
		bow_r1.addChild(bow_r1_r1);
		setRotationAngle(bow_r1_r1, 0.0F, 0.0F, -0.6545F);
		bow_r1_r1.setTextureOffset(75, 74).addBox(0.0F, -4.0F, -4.0F, 0.0F, 8.0F, 8.0F, 0.0F, false);

		bone_r5_r2 = new ModelRenderer(this);
		bone_r5_r2.setRotationPoint(0.127F, -1.7946F, 0.9129F);
		left_arm2.addChild(bone_r5_r2);
		setRotationAngle(bone_r5_r2, -0.144F, -0.3622F, 0.3185F);
		bone_r5_r2.setTextureOffset(47, 82).addBox(-2.0F, -3.0739F, -1.2555F, 4.0F, 6.0F, 4.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		left_hind_leg.render(matrixStack, buffer, packedLight, packedOverlay);
		right_hind_leg.render(matrixStack, buffer, packedLight, packedOverlay);
		whole_head.render(matrixStack, buffer, packedLight, packedOverlay);
		right_fore_leg.render(matrixStack, buffer, packedLight, packedOverlay);
		left_fore_leg.render(matrixStack, buffer, packedLight, packedOverlay);
		tail.render(matrixStack, buffer, packedLight, packedOverlay);
		whole_body.render(matrixStack, buffer, packedLight, packedOverlay);
		CADAVER.render(matrixStack, buffer, packedLight, packedOverlay);
		ARCHER.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.left_hind_leg.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.whole_head.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.whole_head.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.jaw.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.jaw2.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.head2.rotateAngleY = f4 / (180F / (float) Math.PI);
		this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.right_hind_leg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
	}
}