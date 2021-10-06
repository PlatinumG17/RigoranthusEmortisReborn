// Made with Blockbench 3.9.2
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports

public static class Modelcanis_chordata extends EntityModel<Entity> {
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
	private final ModelRenderer whole_mane;
	private final ModelRenderer mane2;
	private final ModelRenderer mane_rotation2;
	private final ModelRenderer mane3;
	private final ModelRenderer mane_rotation3;
	private final ModelRenderer mane_rotation3_r1;
	private final ModelRenderer right_hind_leg;
	private final ModelRenderer bone10;
	private final ModelRenderer bone;
	private final ModelRenderer bone11;
	private final ModelRenderer bone12;
	private final ModelRenderer left_hind_leg;
	private final ModelRenderer bone6;
	private final ModelRenderer bone4;
	private final ModelRenderer bone8;
	private final ModelRenderer bone7;
	private final ModelRenderer right_fore_leg;
	private final ModelRenderer bone2;
	private final ModelRenderer bone2_r1;
	private final ModelRenderer left_fore_leg;
	private final ModelRenderer bone3;
	private final ModelRenderer bone3_r1;
	private final ModelRenderer tail;
	private final ModelRenderer tail_tip;

	public Modelcanis_chordata() {
		textureWidth = 128;
		textureHeight = 128;

		whole_head = new ModelRenderer(this);
		whole_head.setRotationPoint(0.0F, 2.5F, -16.0F);
		setRotationAngle(whole_head, 0.0873F, 0.0F, 0.0F);
		whole_head.setTextureOffset(39, 59).addBox(-5.5F, -4.25F, -4.5F, 11.0F, 10.0F, 5.0F, 0.0F, false);
		whole_head.setTextureOffset(51, 0).addBox(-4.25F, -2.75F, -7.5F, 8.0F, 8.0F, 4.0F, 0.0F, false);
		whole_head.setTextureOffset(76, 0).addBox(-2.25F, 0.5F, -10.5F, 4.0F, 2.0F, 3.0F, 0.0F, false);

		whole_head_r1 = new ModelRenderer(this);
		whole_head_r1.setRotationPoint(0.0F, 17.5F, 15.0F);
		whole_head.addChild(whole_head_r1);
		setRotationAngle(whole_head_r1, 0.258F, -0.045F, 0.1687F);
		whole_head_r1.setTextureOffset(1, 1).addBox(-3.0F, -28.25F, -13.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);

		whole_head_r2 = new ModelRenderer(this);
		whole_head_r2.setRotationPoint(0.0F, 17.5F, 15.0F);
		whole_head.addChild(whole_head_r2);
		setRotationAngle(whole_head_r2, 0.258F, 0.045F, -0.1687F);
		whole_head_r2.setTextureOffset(1, 1).addBox(0.0F, -28.25F, -13.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);

		bone17 = new ModelRenderer(this);
		bone17.setRotationPoint(1.5F, 6.25F, 1.5F);
		whole_head.addChild(bone17);

		bone_r1 = new ModelRenderer(this);
		bone_r1.setRotationPoint(-1.5F, 17.5588F, -0.707F);
		bone17.addChild(bone_r1);
		setRotationAngle(bone_r1, -0.9163F, 0.0F, 0.0F);
		bone_r1.setTextureOffset(0, 6).addBox(-1.25F, -8.5F, -25.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);

		jaw = new ModelRenderer(this);
		jaw.setRotationPoint(0.0F, 3.47F, -7.5F);
		whole_head.addChild(jaw);
		setRotationAngle(jaw, 0.3491F, 0.0F, 0.0F);
		jaw.setTextureOffset(47, 75).addBox(-2.25F, -0.97F, -3.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(-1.5F, 0.53F, 21.0F);
		jaw.addChild(bone5);
		setRotationAngle(bone5, -0.2182F, 0.0F, 0.0F);
		bone5.setTextureOffset(76, 6).addBox(-0.25F, 3.75F, -23.25F, 3.0F, 2.0F, 3.0F, 0.0F, false);

		whole_body = new ModelRenderer(this);
		whole_body.setRotationPoint(0.0F, -1.0F, -2.0F);
		setRotationAngle(whole_body, -0.1745F, 0.0F, 0.0F);

		bone13 = new ModelRenderer(this);
		bone13.setRotationPoint(0.0F, 9.6375F, 4.1433F);
		whole_body.addChild(bone13);
		setRotationAngle(bone13, 1.1781F, 0.0F, 0.0F);
		bone13.setTextureOffset(0, 50).addBox(-5.0F, 1.75F, 5.0F, 10.0F, 9.0F, 9.0F, 0.0F, false);

		bone14 = new ModelRenderer(this);
		bone14.setRotationPoint(0.0F, 15.9694F, 8.0398F);
		whole_body.addChild(bone14);
		setRotationAngle(bone14, 1.2654F, 0.0F, 0.0F);
		bone14.setTextureOffset(41, 14).addBox(-6.0F, -11.5F, 9.5F, 12.0F, 9.0F, 10.0F, 0.0F, false);

		bone15 = new ModelRenderer(this);
		bone15.setRotationPoint(1.0F, 12.6375F, 4.1433F);
		whole_body.addChild(bone15);

		bone16 = new ModelRenderer(this);
		bone16.setRotationPoint(-1.0F, -2.5F, -2.5F);
		bone15.addChild(bone16);
		setRotationAngle(bone16, 2.0944F, 0.0F, 0.0F);
		bone16.setTextureOffset(0, 24).addBox(-5.0F, -8.0F, 4.5F, 10.0F, 11.0F, 13.0F, 0.0F, false);

		whole_mane = new ModelRenderer(this);
		whole_mane.setRotationPoint(0.0F, 1.6375F, -6.8567F);
		whole_body.addChild(whole_mane);

		mane2 = new ModelRenderer(this);
		mane2.setRotationPoint(1.0F, 11.0F, 8.0F);
		whole_mane.addChild(mane2);

		mane_rotation2 = new ModelRenderer(this);
		mane_rotation2.setRotationPoint(-1.0F, 2.5F, -2.5F);
		mane2.addChild(mane_rotation2);
		setRotationAngle(mane_rotation2, 1.5708F, 0.0F, 0.0F);
		mane_rotation2.setTextureOffset(36, 38).addBox(-6.0F, -13.0F, 5.5F, 12.0F, 9.0F, 11.0F, 0.0F, false);

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
		mane_rotation3_r1.setTextureOffset(0, 0).addBox(-6.5F, -25.0F, 9.25F, 13.0F, 11.0F, 12.0F, 0.0F, false);

		right_hind_leg = new ModelRenderer(this);
		right_hind_leg.setRotationPoint(-4.5F, 6.0F, 10.0F);

		bone10 = new ModelRenderer(this);
		bone10.setRotationPoint(3.5F, 9.9409F, -9.8377F);
		right_hind_leg.addChild(bone10);
		setRotationAngle(bone10, -0.5672F, 0.0F, 0.0F);

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 14.5591F, 0.8377F);
		bone10.addChild(bone);
		setRotationAngle(bone, 0.1309F, 0.0F, 0.0F);
		bone.setTextureOffset(17, 69).addBox(-5.25F, -30.5774F, 3.3437F, 3.0F, 11.0F, 5.0F, 0.0F, false);

		bone11 = new ModelRenderer(this);
		bone11.setRotationPoint(-0.5F, -1.9409F, 5.8377F);
		bone10.addChild(bone11);
		setRotationAngle(bone11, 1.2653F, 0.0F, 0.0F);
		bone11.setTextureOffset(34, 75).addBox(-4.25F, -5.1428F, 2.7339F, 3.0F, 8.0F, 3.0F, 0.0F, false);

		bone12 = new ModelRenderer(this);
		bone12.setRotationPoint(-2.2F, 1.0591F, 9.7377F);
		bone10.addChild(bone12);
		setRotationAngle(bone12, 0.5672F, 0.0F, 0.0F);
		bone12.setTextureOffset(0, 24).addBox(-2.8F, -6.25F, 1.6F, 3.0F, 8.0F, 3.0F, 0.0F, false);

		left_hind_leg = new ModelRenderer(this);
		left_hind_leg.setRotationPoint(4.5F, 6.0F, 10.0F);

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(-1.5F, 10.0F, -10.0F);
		left_hind_leg.addChild(bone6);
		setRotationAngle(bone6, -0.5672F, 0.0F, 0.0F);

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(-2.0F, 14.5F, 1.0F);
		bone6.addChild(bone4);
		setRotationAngle(bone4, 0.1309F, 0.0F, 0.0F);
		bone4.setTextureOffset(17, 69).addBox(2.25F, -30.5774F, 3.3437F, 3.0F, 11.0F, 5.0F, 0.0F, false);

		bone8 = new ModelRenderer(this);
		bone8.setRotationPoint(-2.5F, -2.0F, 6.0F);
		bone6.addChild(bone8);
		setRotationAngle(bone8, 1.2653F, 0.0F, 0.0F);
		bone8.setTextureOffset(34, 75).addBox(2.25F, -5.1428F, 2.7339F, 3.0F, 8.0F, 3.0F, 0.0F, false);

		bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(0.2F, 1.0F, 9.9F);
		bone6.addChild(bone7);
		setRotationAngle(bone7, 0.5672F, 0.0F, 0.0F);
		bone7.setTextureOffset(0, 24).addBox(-0.2F, -6.25F, 1.6F, 3.0F, 8.0F, 3.0F, 0.0F, false);

		right_fore_leg = new ModelRenderer(this);
		right_fore_leg.setRotationPoint(-3.5F, 6.0F, -13.0F);
		right_fore_leg.setTextureOffset(72, 34).addBox(-1.5F, 8.0F, 2.0F, 3.0F, 10.0F, 3.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(3.5F, 12.0F, 7.0F);
		right_fore_leg.addChild(bone2);
		setRotationAngle(bone2, 0.5236F, 0.0F, 0.0F);

		bone2_r1 = new ModelRenderer(this);
		bone2_r1.setRotationPoint(0.0F, 6.0F, 6.0F);
		bone2.addChild(bone2_r1);
		setRotationAngle(bone2_r1, -0.0436F, 0.0F, 0.0F);
		bone2_r1.setTextureOffset(0, 69).addBox(-5.25F, -21.5F, -9.5F, 3.0F, 12.0F, 5.0F, 0.0F, false);

		left_fore_leg = new ModelRenderer(this);
		left_fore_leg.setRotationPoint(3.5F, 6.0F, -13.0F);
		left_fore_leg.setTextureOffset(72, 34).addBox(-1.5F, 8.0F, 2.0F, 3.0F, 10.0F, 3.0F, 0.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(-3.5F, 12.0F, 7.0F);
		left_fore_leg.addChild(bone3);
		setRotationAngle(bone3, 0.5236F, 0.0F, 0.0F);

		bone3_r1 = new ModelRenderer(this);
		bone3_r1.setRotationPoint(0.0F, 6.0F, 6.0F);
		bone3.addChild(bone3_r1);
		setRotationAngle(bone3_r1, -0.0436F, 0.0F, 0.0F);
		bone3_r1.setTextureOffset(0, 69).addBox(2.25F, -21.5F, -9.5F, 3.0F, 12.0F, 5.0F, 0.0F, false);

		tail = new ModelRenderer(this);
		tail.setRotationPoint(0.0F, 5.0F, 16.0F);
		tail.setTextureOffset(72, 59).addBox(-1.5F, -1.0F, -1.0F, 3.0F, 12.0F, 3.0F, 0.0F, false);

		tail_tip = new ModelRenderer(this);
		tail_tip.setRotationPoint(1.0F, 4.0F, -4.0F);
		tail.addChild(tail_tip);
		setRotationAngle(tail_tip, -0.3753F, 0.0F, 0.0F);
		tail_tip.setTextureOffset(64, 75).addBox(-2.5F, 4.3F, 5.1F, 3.0F, 5.0F, 3.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		whole_head.render(matrixStack, buffer, packedLight, packedOverlay);
		whole_body.render(matrixStack, buffer, packedLight, packedOverlay);
		right_hind_leg.render(matrixStack, buffer, packedLight, packedOverlay);
		left_hind_leg.render(matrixStack, buffer, packedLight, packedOverlay);
		right_fore_leg.render(matrixStack, buffer, packedLight, packedOverlay);
		left_fore_leg.render(matrixStack, buffer, packedLight, packedOverlay);
		tail.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.left_hind_leg.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
		this.whole_head.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.whole_head.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.right_hind_leg.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
		this.jaw.rotateAngleX = f3 / (180F / (float) Math.PI);
		this.right_fore_leg.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
		this.left_fore_leg.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
	}
}