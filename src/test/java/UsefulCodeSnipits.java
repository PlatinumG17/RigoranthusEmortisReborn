public class UsefulCodeSnipits {
    /*



https://github.com/bernie-g/geckolib/blob/1.16/src/main/resources/assets/geckolib3/models/item/jackintheboxitem.json

https://geckolib.com/en/latest/3.0.0/item_animations/#:~:text=Item%20Animations%20Item%20animations%20in%20GeckoLib%20are%20a,unfortunately%20we%20don%27t%20have%20much%20control%20over%20this.








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






















    ///_______________________  R A N D O M   P O T I O N   E F F E C T S  _______________________///

	public class EraticEnchanter extends RigoranthusEmortisRebornCurio {
    public EraticEnchanter(String registry){
        super(registry);
    }
    public static ArrayList<Effect> effectTable = new ArrayList<>(Arrays.asList(
            Effects.SLOW_FALLING, Effects.NIGHT_VISION, Effects.CONDUIT_POWER, Effects.ABSORPTION, Effects.DAMAGE_BOOST,
            Effects.FIRE_RESISTANCE, Effects.DIG_SPEED, Effects.MOVEMENT_SPEED, Effects.REGENERATION, Effects.DAMAGE_RESISTANCE
    ));

    @Override
    public void wearableTick(LivingEntity wearer) {
        World world = wearer.getCommandSenderWorld();
        if(world.isClientSide())
            return;
        if(world.getGameTime() % (20 * 6)  == 0){
            wearer.addEffect(new EffectInstance(effectTable.get(new Random().nextInt(effectTable.size())), 6 * 20, new Random().nextInt(3)));
        }
    }
}

    ///_______________________  " M O B S   C A N N O T   W A L K   O N   B L O C K "  _______________________///

public class WardBlock extends ModBlock {
    public WardBlock() {
        super(defaultProperties().lightLevel((bs)->7), "warding_stone");
    }
    public static final DirectionProperty FACING = HorizontalBlock.FACING;
    @Override public void entityInside(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {super.entityInside(state, worldIn, pos, entityIn);}
    @Nullable @Override public PathNodeType getAiPathNodeType(BlockState state, IBlockReader world, BlockPos pos, @Nullable MobEntity entity) {return PathNodeType.LAVA;}
    @Deprecated
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        if(context.getEntity() == null)
            return state.getShape(worldIn, pos);
        if(context.getEntity().level.isClientSide)
            return state.getShape(worldIn, pos);
        if(!(context.getEntity() instanceof PlayerEntity))
            return VoxelShapes.block().move(0, 1, 0);
        return VoxelShapes.block();
    }
    @Override
    public void setPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity entity, ItemStack stack) {
        if (entity != null) {world.setBlock(pos, state.setValue(BlockStateProperties.FACING, getFacingFromEntity(pos, entity)), 2);}
    }
    @Override public boolean collisionExtendsVertically(BlockState state, IBlockReader world, BlockPos pos, Entity collidingEntity) {return collidingEntity instanceof MobEntity;}
    public static Direction getFacingFromEntity(BlockPos clickedBlock, LivingEntity entity) {
        Vector3d vec = entity.position();
        return Direction.getNearest((float) (vec.x - clickedBlock.getX()), (float) (vec.y - clickedBlock.getY()), (float) (vec.z - clickedBlock.getZ()));
    }
    @Override protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {builder.add(BlockStateProperties.FACING);}
}





    ///_______________________  V O I D S   I T E M S  _______________________///

public class VoidJar extends ModItem implements IScribeable {
    public VoidJar() {super(MagicItemsRegistry.defaultItemProperties().stacksTo(1), LibItemNames.VOID_JAR);}
    public void toggleStatus(PlayerEntity playerEntity, ItemStack stack){
        CompoundNBT tag = stack.getTag();
        if(tag.getBoolean("on")){
            tag.putBoolean("on", false);
            PortUtil.sendMessage(playerEntity, new TranslationTextComponent("rigoranthusemortisreborn.off"));
        }else{
            tag.putBoolean("on", true);
            PortUtil.sendMessage(playerEntity, new TranslationTextComponent("rigoranthusemortisreborn.on"));}}
    public static boolean tryVoiding(PlayerEntity player, ItemStack pickingUp) {
        NonNullList<ItemStack> list =  player.inventory.items;
        boolean voided = false;
        for(int i = 0; i < 9; i++){
            ItemStack jar = list.get(i);
            if(jar.getItem() == MagicItemsRegistry.VOID_JAR){
                if(isActive(jar) && containsItem(pickingUp, jar.getTag())){
                    DominionCapability.getDominion(player).ifPresent(iMana -> iMana.addDominion(5.0 * pickingUp.getCount()));
                    pickingUp.setCount(0);
                    voided = true;
                    break;}}}return voided;}
    public static boolean isActive(ItemStack stack){
        return stack.hasTag() && stack.getTag().getBoolean("on");
    }
    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity player, Hand handIn) {
        if(worldIn.isClientSide)
            return super.use(worldIn, player, handIn);
        ItemStack stack = player.getItemInHand(handIn);
        CompoundNBT tag = stack.getOrCreateTag();
        if(handIn == Hand.MAIN_HAND){
            ItemStack stackToWrite = player.getOffhandItem();
            if(player.isShiftKeyDown()){
                toggleStatus(player, stack);
                return ActionResult.consume(stack);}
            if(!stackToWrite.isEmpty()){
                if(containsItem(stackToWrite, tag)) {
                    PortUtil.sendMessage(player, new TranslationTextComponent("rigoranthusemortisreborn.scribe.item_removed"));
                    ItemScroll.removeItem(stackToWrite, tag);
                    player.startUsingItem(handIn);
                    return ActionResult.fail(stack);}
                PortUtil.sendMessage(player, new TranslationTextComponent("rigoranthusemortisreborn.scribe.item_added"));
                ItemScroll.addItem(stackToWrite, tag);
                player.startUsingItem(handIn);
                return ActionResult.fail(stack);}
        }return ActionResult.success(stack);}
    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip2, ITooltipFlag flagIn) {
        CompoundNBT tag = stack.getTag();
        if(tag == null)
            return;
        if(tag.getBoolean("on")){tooltip2.add(new TranslationTextComponent("rigoranthusemortisreborn.on"));
        }else{tooltip2.add(new TranslationTextComponent("rigoranthusemortisreborn.off"));}
        super.appendHoverText(stack, worldIn, tooltip2, flagIn);
        List<ItemStack> stacks = new ArrayList<>();
        for(String s : tag.getAllKeys()){
            if(s.contains(ITEM_PREFIX)){
                stacks.add(ItemStack.of(tag.getCompound(s)));
            }}for(ItemStack s : stacks){
            tooltip2.add(s.getHoverName());}}
    @Override
    public boolean onScribe(World world, BlockPos pos, PlayerEntity player, Hand handIn, ItemStack thisStack) {return ItemScroll.scribe(world, pos, player, handIn, thisStack);}
}

     */
}
