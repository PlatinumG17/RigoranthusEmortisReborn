public class UsefulCodeSnipits {
    /*














    ///_______________________  S L I M E  B L O C K  _______________________///

    public class CustomSlimeBlock extends SlimeBlock {
        public CustomSlimeBlock(Properties properties) {super(properties);}

        @Nullable
        @Override
        public ToolType getHarvestTool(BlockState state) {return ToolType.SHOVEL;}

        @Override
        public int getLightBlock(BlockState state, IBlockReader worldIn, BlockPos pos) {
            if (state.isSolidRender(worldIn, pos)) {
                return worldIn.getMaxLightLevel();
            }
            else {return state.propagatesSkylightDown(worldIn, pos) ? 0 : 1;}
        }
    }


    ///_______________________  R A N D O M  P A R T I C L E S  _______________________///

	@Override
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		super.animateTick(stateIn, worldIn, pos, rand);
		if(rand.nextInt(10) == 0)
			worldIn.addParticle(ParticleTypes.PORTAL, (float) pos.getX() + rand.nextFloat(), (float) pos.getY() + 1.1F, (float) pos.getZ() + rand.nextFloat(), 0.0D, 0.0D, 0.0D);
	}


    ///_______________________  R A N D O M   T E L E P O R T I N G  _______________________///

  	@Override
	@SuppressWarnings("deprecation")
	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
		if(!worldIn.isClientSide && random.nextFloat() >= .75F) {
			List<LivingEntity> list = worldIn.getEntitiesOfClass(LivingEntity.class, new AxisAlignedBB(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1, pos.getY(), pos.getZ() + 1));
			for(LivingEntity livingentity : list) {
				if(!livingentity.isShiftKeyDown() && !livingentity.isSpectator() && (livingentity instanceof ServerPlayerEntity)) {
					randomTeleport(worldIn, livingentity);
				}
			}
		}
	}
	public void randomTeleport(World worldIn, LivingEntity livingEntity) {
		double oldPosX = livingEntity.getX();
		double oldPosY = livingEntity.getY();
		double oldPosZ = livingEntity.getZ();

		for(int i = 0; i < 16; ++i) {
			double newPosX = livingEntity.getX() + (livingEntity.getRandom().nextDouble() - 0.5D) * 16.0D;
			double newPosY = MathHelper.clamp(livingEntity.getY() + (double) (livingEntity.getRandom().nextInt(16) - 8), 0.0D, worldIn.getHeight() - 1);//getActualHeight/getLogicalHeight
			double newPosZ = livingEntity.getZ() + (livingEntity.getRandom().nextDouble() - 0.5D) * 16.0D;
			if(livingEntity.isPassenger()) {
				livingEntity.stopRiding();
			}
			if(livingEntity.randomTeleport(newPosX, newPosY, newPosZ, true)) {
				worldIn.playSound(null, oldPosX, oldPosY, oldPosZ, SoundEvents.CHORUS_FRUIT_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
				livingEntity.playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 1.0F, 1.0F);
				break;
			}
		}
	}
     */
}
