// Made with Blockbench 3.9.2
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports

public static class Modelcanis_kyphos extends EntityModel<Entity> {
	private final ModelRenderer tail;
	private final ModelRenderer tail_tip;
	private final ModelRenderer tail_tip_r1;
	private final ModelRenderer left_fore_leg;
	private final ModelRenderer left_fore_leg_r1;
	private final ModelRenderer bone3;
	private final ModelRenderer bone3_r1;
	private final ModelRenderer bone3_r1_r1;
	private final ModelRenderer right_fore_leg;
	private final ModelRenderer right_fore_leg_r1;
	private final ModelRenderer bone2;
	private final ModelRenderer bone2_r1;
	private final ModelRenderer bone2_r1_r1;
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
	private final ModelRenderer bone9;
	private final ModelRenderer cube_r9;
	private final ModelRenderer cube_r10;
	private final ModelRenderer cube_r11;

	public Modelcanis_kyphos() {
		textureWidth = 128;
		textureHeight = 128;

		tail = new ModelRenderer(this);
		tail.setRotationPoint(0.0F, 9.4673F, 9.7517F);
		setRotationAngle(tail, 0.48F, 0.0F, 0.0F);
		tail.setTextureOffset(59, 65).addBox(-1.5F, -0.2173F, -1.7517F, 3.0F, 12.0F, 3.0F, 0.0F, false);

		tail_tip = new ModelRenderer(this);
		tail_tip.setRotationPoint(1.0F, 4.7373F, -4.6756F);
		tail.addChild(tail_tip);
		setRotationAngle(tail_tip, -0.3753F, 0.0F, 0.0F);

		tail_tip_r1 = new ModelRenderer(this);
		tail_tip_r1.setRotationPoint(-1.0F, 4.7954F, 6.6739F);
		tail_tip.addChild(tail_tip_r1);
		setRotationAngle(tail_tip_r1, 0.829F, 0.0F, 0.0F);
		tail_tip_r1.setTextureOffset(13, 72).addBox(-1.5F, -0.5F, -1.5F, 3.0F, 5.0F, 3.0F, 0.0F, false);

		left_fore_leg = new ModelRenderer(this);
		left_fore_leg.setRotationPoint(3.5F, 1.7705F, -8.2196F);
		setRotationAngle(left_fore_leg, -0.5672F, 0.0F, 0.0F);

		left_fore_leg_r1 = new ModelRenderer(this);
		left_fore_leg_r1.setRotationPoint(0.0F, 5.0105F, 4.2018F);
		left_fore_leg.addChild(left_fore_leg_r1);
		setRotationAngle(left_fore_leg_r1, 0.3927F, 0.0F, 0.0F);
		left_fore_leg_r1.setTextureOffset(0, 70).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 10.0F, 3.0F, 0.0F, false);
		left_fore_leg_r1.setTextureOffset(8, 25).addBox(-1.0F, 9.25F, -1.75F, 0.0F, 1.0F, 1.0F, 0.0F, false);
		left_fore_leg_r1.setTextureOffset(8, 25).addBox(0.0F, 9.25F, -1.75F, 0.0F, 1.0F, 1.0F, 0.0F, false);
		left_fore_leg_r1.setTextureOffset(8, 25).addBox(1.0F, 9.25F, -1.75F, 0.0F, 1.0F, 1.0F, 0.0F, false);

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
		bone3_r1_r1.setTextureOffset(25, 57).addBox(2.25F, -21.5F, -9.5F, 3.0F, 12.0F, 5.0F, 0.0F, false);

		right_fore_leg = new ModelRenderer(this);
		right_fore_leg.setRotationPoint(-3.5F, 1.7705F, -8.2196F);
		setRotationAngle(right_fore_leg, -0.5672F, 0.0F, 0.0F);

		right_fore_leg_r1 = new ModelRenderer(this);
		right_fore_leg_r1.setRotationPoint(0.0F, 5.0105F, 4.2018F);
		right_fore_leg.addChild(right_fore_leg_r1);
		setRotationAngle(right_fore_leg_r1, 0.3927F, 0.0F, 0.0F);
		right_fore_leg_r1.setTextureOffset(0, 70).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 10.0F, 3.0F, 0.0F, false);
		right_fore_leg_r1.setTextureOffset(8, 25).addBox(1.0F, 9.25F, -1.75F, 0.0F, 1.0F, 1.0F, 0.0F, false);
		right_fore_leg_r1.setTextureOffset(8, 25).addBox(-1.0F, 9.25F, -1.75F, 0.0F, 1.0F, 1.0F, 0.0F, false);
		right_fore_leg_r1.setTextureOffset(8, 25).addBox(0.0F, 9.25F, -1.75F, 0.0F, 1.0F, 1.0F, 0.0F, false);

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
		bone2_r1_r1.setTextureOffset(25, 57).addBox(-5.25F, -21.5F, -9.5F, 3.0F, 12.0F, 5.0F, 0.0F, false);

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
		bone4.setTextureOffset(42, 65).addBox(2.25F, -29.4251F, 0.5798F, 3.0F, 11.0F, 5.0F, 0.0F, false);

		bone8 = new ModelRenderer(this);
		bone8.setRotationPoint(-2.5F, -1.0F, 6.0F);
		bone6.addChild(bone8);
		setRotationAngle(bone8, 1.2653F, 0.0F, 0.0F);
		bone8.setTextureOffset(66, 16).addBox(2.25F, -6.9251F, 0.5798F, 3.0F, 7.0F, 3.0F, 0.0F, false);

		bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(0.2F, 1.0F, 9.9F);
		bone6.addChild(bone7);
		setRotationAngle(bone7, 0.5672F, 0.0F, 0.0F);

		bone7_r1 = new ModelRenderer(this);
		bone7_r1.setRotationPoint(1.3F, -5.1751F, 0.1798F);
		bone7.addChild(bone7_r1);
		setRotationAngle(bone7_r1, 0.2618F, 0.0F, 0.0F);
		bone7_r1.setTextureOffset(53, 16).addBox(-1.5F, -1.0F, -2.0F, 3.0F, 7.0F, 3.0F, 0.0F, false);
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
		bone.setTextureOffset(42, 65).addBox(-1.25F, -3.4251F, -1.6702F, 3.0F, 11.0F, 5.0F, 0.0F, false);

		bone11 = new ModelRenderer(this);
		bone11.setRotationPoint(1.5F, -0.9409F, 5.8377F);
		bone10.addChild(bone11);
		setRotationAngle(bone11, 1.2653F, 0.0F, 0.0F);
		bone11.setTextureOffset(66, 16).addBox(-6.25F, -6.9251F, 0.5798F, 3.0F, 7.0F, 3.0F, 0.0F, false);

		bone12 = new ModelRenderer(this);
		bone12.setRotationPoint(-2.2F, 1.0591F, 9.7377F);
		bone10.addChild(bone12);
		setRotationAngle(bone12, 0.5672F, 0.0F, 0.0F);

		bone12_r1 = new ModelRenderer(this);
		bone12_r1.setRotationPoint(-1.3F, -5.1751F, 0.1798F);
		bone12.addChild(bone12_r1);
		setRotationAngle(bone12_r1, 0.2618F, 0.0F, 0.0F);
		bone12_r1.setTextureOffset(53, 16).addBox(-1.5F, -1.0F, -2.0F, 3.0F, 7.0F, 3.0F, 0.0F, false);
		bone12_r1.setTextureOffset(0, 8).addBox(-1.5F, 5.0F, -3.0F, 3.0F, 1.0F, 1.0F, 0.0F, false);
		bone12_r1.setTextureOffset(8, 25).addBox(1.0F, 4.75F, -3.25F, 0.0F, 1.0F, 1.0F, 0.0F, false);
		bone12_r1.setTextureOffset(8, 25).addBox(-1.0F, 4.75F, -3.25F, 0.0F, 1.0F, 1.0F, 0.0F, false);
		bone12_r1.setTextureOffset(8, 25).addBox(0.0F, 4.75F, -3.25F, 0.0F, 1.0F, 1.0F, 0.0F, false);

		whole_head = new ModelRenderer(this);
		whole_head.setRotationPoint(0.0F, -3.3613F, -12.3158F);
		setRotationAngle(whole_head, 0.0437F, 0.0F, 0.0F);
		whole_head.setTextureOffset(49, 0).addBox(-5.5F, -4.1387F, -4.4342F, 11.0F, 10.0F, 5.0F, 0.0F, false);
		whole_head.setTextureOffset(0, 57).addBox(-4.25F, -2.6387F, -7.4342F, 8.0F, 8.0F, 4.0F, 0.0F, false);
		whole_head.setTextureOffset(72, 27).addBox(-2.25F, 0.6113F, -10.4342F, 4.0F, 2.0F, 3.0F, 0.0F, false);

		whole_head_r1 = new ModelRenderer(this);
		whole_head_r1.setRotationPoint(0.0F, 17.5F, 15.0F);
		whole_head.addChild(whole_head_r1);
		setRotationAngle(whole_head_r1, 0.258F, -0.045F, 0.1687F);
		whole_head_r1.setTextureOffset(38, 0).addBox(-3.0F, -28.1387F, -12.9342F, 3.0F, 3.0F, 1.0F, 0.0F, false);

		whole_head_r2 = new ModelRenderer(this);
		whole_head_r2.setRotationPoint(0.0F, 17.5F, 15.0F);
		whole_head.addChild(whole_head_r2);
		setRotationAngle(whole_head_r2, 0.258F, 0.045F, -0.1687F);
		whole_head_r2.setTextureOffset(38, 0).addBox(0.0F, -28.1387F, -12.9342F, 3.0F, 3.0F, 1.0F, 0.0F, false);

		bone17 = new ModelRenderer(this);
		bone17.setRotationPoint(1.5F, 6.25F, 1.5F);
		whole_head.addChild(bone17);

		bone_r1 = new ModelRenderer(this);
		bone_r1.setRotationPoint(-1.5F, 17.5588F, -0.707F);
		bone17.addChild(bone_r1);
		setRotationAngle(bone_r1, -0.9163F, 0.0F, 0.0F);
		bone_r1.setTextureOffset(0, 38).addBox(-1.25F, -8.4475F, -24.9772F, 2.0F, 3.0F, 2.0F, 0.0F, false);

		jaw = new ModelRenderer(this);
		jaw.setRotationPoint(0.0F, 2.97F, -7.5F);
		whole_head.addChild(jaw);
		setRotationAngle(jaw, 0.829F, 0.0F, 0.0F);
		jaw.setTextureOffset(36, 19).addBox(-2.25F, -0.2247F, -2.9701F, 4.0F, 2.0F, 4.0F, 0.0F, false);

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(-1.5F, 1.2355F, 20.8633F);
		jaw.addChild(bone5);
		setRotationAngle(bone5, -0.2182F, 0.0F, 0.0F);
		bone5.setTextureOffset(72, 65).addBox(-0.25F, 3.7898F, -23.0834F, 3.0F, 2.0F, 3.0F, 0.0F, false);

		whole_body = new ModelRenderer(this);
		whole_body.setRotationPoint(0.0F, -2.4692F, 0.2672F);
		setRotationAngle(whole_body, -0.829F, 0.0F, 0.0F);

		bone13 = new ModelRenderer(this);
		bone13.setRotationPoint(0.0F, 6.935F, 1.1943F);
		whole_body.addChild(bone13);
		setRotationAngle(bone13, 1.1781F, 0.0F, 0.0F);
		bone13.setTextureOffset(43, 46).addBox(-5.0F, 1.7841F, 5.0386F, 10.0F, 9.0F, 9.0F, 0.0F, false);

		bone14 = new ModelRenderer(this);
		bone14.setRotationPoint(0.0F, 13.2669F, 8.0908F);
		whole_body.addChild(bone14);
		setRotationAngle(bone14, 1.4836F, 0.0F, 0.0F);
		bone14.setTextureOffset(37, 28).addBox(-6.0F, -8.4977F, 9.3921F, 12.0F, 7.0F, 10.0F, 0.0F, false);

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
		bone16_r1.setTextureOffset(0, 38).addBox(-5.5F, -16.1499F, 4.3499F, 11.0F, 8.0F, 10.0F, 0.0F, false);

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
		mane_rotation2_r1.setTextureOffset(0, 19).addBox(-6.0F, -8.5F, 6.25F, 12.0F, 7.0F, 11.0F, 0.0F, false);

		mane3 = new ModelRenderer(this);
		mane3.setRotationPoint(1.0F, 14.0F, 12.0F);
		whole_mane.addChild(mane3);
		setRotationAngle(mane3, -0.6545F, 0.0F, 0.0F);
		mane3.setTextureOffset(4, 40).addBox(-5.25F, -0.4658F, -15.2115F, 8.0F, 8.0F, 8.0F, 0.0F, false);

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
		mane_rotation3_r1_r1.setTextureOffset(0, 0).addBox(-6.5F, -7.25F, 13.0F, 13.0F, 7.0F, 11.0F, 0.0F, false);

		vertebrae = new ModelRenderer(this);
		vertebrae.setRotationPoint(-0.5F, -5.065F, 8.6943F);
		whole_body.addChild(vertebrae);
		setRotationAngle(vertebrae, 1.3526F, 0.0F, 0.0F);
		vertebrae.setTextureOffset(36, 26).addBox(0.0F, -1.0F, -0.8115F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.5F, -14.2292F, -6.7723F);
		vertebrae.addChild(cube_r1);
		setRotationAngle(cube_r1, 1.2217F, 0.0F, 0.0F);
		cube_r1.setTextureOffset(38, 5).addBox(-0.5F, -1.8366F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(0.5F, -12.2151F, -3.8963F);
		vertebrae.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.9163F, 0.0F, 0.0F);
		cube_r2.setTextureOffset(36, 26).addBox(-0.5F, -0.7507F, -0.5152F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(0.5F, -9.9081F, -1.1878F);
		vertebrae.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.9163F, 0.0F, 0.0F);
		cube_r3.setTextureOffset(36, 26).addBox(-0.5F, -0.5577F, -1.1737F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(0.5F, -4.0425F, 0.162F);
		vertebrae.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.3054F, 0.0F, 0.0F);
		cube_r4.setTextureOffset(36, 26).addBox(-0.5F, -3.0F, -0.0235F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		cube_r4.setTextureOffset(36, 26).addBox(-0.5F, 0.0767F, -0.6235F, 1.0F, 2.0F, 1.0F, 0.0F, false);

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
		cube_r6.setTextureOffset(36, 26).addBox(0.5184F, -0.2021F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		bone9 = new ModelRenderer(this);
		bone9.setRotationPoint(2.5F, -15.7658F, -8.4615F);
		vertebrae.addChild(bone9);
		setRotationAngle(bone9, 0.0873F, -0.3491F, 0.0F);

		cube_r9 = new ModelRenderer(this);
		cube_r9.setRotationPoint(0.0886F, -0.724F, -1.5511F);
		bone9.addChild(cube_r9);
		setRotationAngle(cube_r9, 1.2955F, 0.0979F, 0.3417F);
		cube_r9.setTextureOffset(0, 0).addBox(-1.3599F, -5.076F, -0.4489F, 3.0F, 6.0F, 1.0F, 0.0F, false);

		cube_r10 = new ModelRenderer(this);
		cube_r10.setRotationPoint(-3.8905F, -0.4354F, -0.5652F);
		bone9.addChild(cube_r10);
		setRotationAngle(cube_r10, 1.2955F, 0.0979F, 0.3417F);
		cube_r10.setTextureOffset(0, 26).addBox(3.3714F, -0.113F, 0.9262F, 2.0F, 2.0F, 1.0F, 0.0F, false);

		cube_r11 = new ModelRenderer(this);
		cube_r11.setRotationPoint(-3.8905F, -0.4354F, -0.5652F);
		bone9.addChild(cube_r11);
		setRotationAngle(cube_r11, 1.2371F, 0.5987F, 0.1764F);
		cube_r11.setTextureOffset(36, 26).addBox(2.0478F, 1.2039F, 0.9262F, 1.0F, 2.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		tail.render(matrixStack, buffer, packedLight, packedOverlay);
		left_fore_leg.render(matrixStack, buffer, packedLight, packedOverlay);
		right_fore_leg.render(matrixStack, buffer, packedLight, packedOverlay);
		left_hind_leg.render(matrixStack, buffer, packedLight, packedOverlay);
		right_hind_leg.render(matrixStack, buffer, packedLight, packedOverlay);
		whole_head.render(matrixStack, buffer, packedLight, packedOverlay);
		whole_body.render(matrixStack, buffer, packedLight, packedOverlay);
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
		this.right_hind_leg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
	}
}