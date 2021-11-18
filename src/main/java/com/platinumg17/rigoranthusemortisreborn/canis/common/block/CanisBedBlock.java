package com.platinumg17.rigoranthusemortisreborn.canis.common.block;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.CanisEntity;
import com.platinumg17.rigoranthusemortisreborn.canis.common.util.CanisBedUtil;
import com.platinumg17.rigoranthusemortisreborn.canis.common.block.tileentity.CanisBedTileEntity;
import com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.packet.data.storage.CanisRespawnData;
import com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.packet.data.storage.CanisRespawnStorage;
import com.platinumg17.rigoranthusemortisreborn.canis.common.SpecializedEntityTypes;
import com.platinumg17.rigoranthusemortisreborn.canis.common.util.EntityUtil;
import com.platinumg17.rigoranthusemortisreborn.canis.common.util.WorldUtil;
import com.platinumg17.rigoranthusemortisreborn.api.RigoranthusEmortisRebornAPI;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.registry.IBeddingMaterial;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.registry.ICasingMaterial;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;
import org.apache.commons.lang3.tuple.Pair;
import com.platinumg17.rigoranthusemortisreborn.canis.common.util.NBTUtilities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.NonNullList;
import net.minecraft.util.Rotation;
import net.minecraft.util.Util;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.Constants;

public class CanisBedBlock extends Block {

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final DirectionProperty FACING = HorizontalBlock.FACING;
    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 9.0D, 16.0D);
    protected static final VoxelShape SHAPE_COLLISION = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 7.0D, 16.0D);

    public CanisBedBlock() {
        super(Block.Properties.of(Material.WOOD).strength(3.0F, 5.0F).sound(SoundType.WOOD));
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
    }
    @Override protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {builder.add(FACING, WATERLOGGED);}
    @Override public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {return SHAPE;}
    @Override public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext selectionContext) {return SHAPE_COLLISION;}
    @Override public boolean hasTileEntity(BlockState state) {return true;}
    @Override public TileEntity createTileEntity(BlockState state, IBlockReader world) {return new CanisBedTileEntity();}
    @Override public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos) {return Block.canSupportCenter(worldIn, pos.below(), Direction.UP);}
    @Override
    public void setPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        state = state.setValue(FACING, placer.getDirection().getOpposite());
        CanisBedTileEntity canisBedTileEntity = WorldUtil.getTileEntity(worldIn, pos, CanisBedTileEntity.class);
        if (canisBedTileEntity != null) {
            CanisBedUtil.setBedVariant(canisBedTileEntity, stack);
            canisBedTileEntity.setPlacer(placer);
            CompoundNBT tag = stack.getTagElement("rigoranthusemortisreborn");
            if (tag != null) {
                ITextComponent name = NBTUtilities.getTextComponent(tag, "name");
                UUID ownerId = NBTUtilities.getUniqueId(tag, "ownerId");
                canisBedTileEntity.setBedName(name);
                canisBedTileEntity.setOwner(ownerId);
            }
        }
        worldIn.setBlock(pos, state, Constants.BlockFlags.BLOCK_UPDATE);
    }

    @Override
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (stateIn.getValue(WATERLOGGED)) {
            worldIn.getLiquidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
        }
        return facing == Direction.DOWN && !stateIn.canSurvive(worldIn, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }
    @Override public FluidState getFluidState(BlockState state) {return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);}
    @Override public BlockState rotate(BlockState state, Rotation rot) {return state.setValue(FACING, rot.rotate(state.getValue(FACING)));}
    @Override public BlockState mirror(BlockState state, Mirror mirrorIn) {return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));}

    @Override
    @Deprecated
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (worldIn.isClientSide) {
            return ActionResultType.SUCCESS;
        } else {
            CanisBedTileEntity canisBedTileEntity = WorldUtil.getTileEntity(worldIn, pos, CanisBedTileEntity.class);
            if (canisBedTileEntity != null) {
                ItemStack stack = player.getItemInHand(handIn);
                if (stack.getItem() == Items.NAME_TAG && stack.hasCustomHoverName()) {
                    canisBedTileEntity.setBedName(stack.getHoverName());
                    if (!player.abilities.instabuild) {
                        stack.shrink(1);
                    }
                    worldIn.sendBlockUpdated(pos, state, state, Constants.BlockFlags.DEFAULT);
                    return ActionResultType.SUCCESS;
                } else if (player.isShiftKeyDown() && canisBedTileEntity.getOwnerUUID() == null) {
                    List<CanisEntity> caniss = worldIn.getEntities(SpecializedEntityTypes.CANIS.get(), new AxisAlignedBB(pos).inflate(10D), (canis) -> canis.isAlive() && canis.isOwnedBy(player));
                    Collections.sort(caniss, new EntityUtil.Sorter(new Vector3d(pos.getX(), pos.getY(), pos.getZ())));
                    CanisEntity closestStanding = null;
                    CanisEntity closestSitting = null;
                    for (CanisEntity canis : caniss) {
                        if (closestSitting != null && closestSitting != null) {
                            break;
                        }
                        if (closestSitting == null && canis.isInSittingPose()) {
                            closestSitting = canis;
                        } else if (closestStanding == null && !canis.isInSittingPose()) {
                            closestStanding = canis;
                        }
                    }
                    CanisEntity closests = closestStanding != null ? closestStanding : closestSitting;
                    if (closests != null) {
                        closests.setTargetBlock(pos);
                    }
                } else if (canisBedTileEntity.getOwnerUUID() != null) {
                    CanisRespawnData storage = CanisRespawnStorage.get(worldIn).remove(canisBedTileEntity.getOwnerUUID());
                    if (storage != null) {
                        CanisEntity canis = storage.respawn((ServerWorld) worldIn, player, pos.above());
                        canisBedTileEntity.setOwner(canis);
                        canis.setBedPos(canis.level.dimension(), pos);
                        return ActionResultType.SUCCESS;
                    } else {
                        ITextComponent name = canisBedTileEntity.getOwnerName();
                        player.sendMessage(new TranslationTextComponent("block.rigoranthusemortisreborn.canis_bed.owner", name != null ? name : "someone"), Util.NIL_UUID);
                        return ActionResultType.FAIL;
                    }
                } else {
                    player.sendMessage(new TranslationTextComponent("block.rigoranthusemortisreborn.canis_bed.set_owner_help"), Util.NIL_UUID);
                    return ActionResultType.SUCCESS;
                }
            }
            return ActionResultType.SUCCESS;
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        Pair<ICasingMaterial, IBeddingMaterial> materials = CanisBedUtil.getMaterials(stack);
        tooltip.add(materials.getLeft() != null
                ? materials.getLeft().getTooltip()
                : new TranslationTextComponent("canisbed.casing.null").withStyle(TextFormatting.RED));
        tooltip.add(materials.getRight() != null
                ? materials.getRight().getTooltip()
                : new TranslationTextComponent("canisbed.bedding.null").withStyle(TextFormatting.RED));
        if (materials.getLeft() == null && materials.getRight() == null) {
            tooltip.add(new TranslationTextComponent("canisbed.explain.missing").withStyle(TextFormatting.ITALIC));
        }
        CompoundNBT tag = stack.getTagElement("rigoranthusemortisreborn");
        if (tag != null) {
            UUID ownerId = NBTUtilities.getUniqueId(tag, "ownerId");
            ITextComponent name = NBTUtilities.getTextComponent(tag, "name");
            ITextComponent ownerName = NBTUtilities.getTextComponent(tag, "ownerName");
            if (name != null) {
                tooltip.add(new StringTextComponent("Bed Name: ").withStyle(TextFormatting.WHITE).append(name));
            }
            if (ownerName != null) {
                tooltip.add(new StringTextComponent("Name: ").withStyle(TextFormatting.DARK_AQUA).append(ownerName));
            }
            if (ownerId != null && (flagIn.isAdvanced() || Screen.hasShiftDown())) {
                tooltip.add(new StringTextComponent("UUID: ").withStyle(TextFormatting.AQUA).append(new StringTextComponent(ownerId.toString())));
            }
        }
    }

    @Override
    public void fillItemCategory(ItemGroup group, NonNullList<ItemStack> items) {
        for (IBeddingMaterial beddingId : RigoranthusEmortisRebornAPI.BEDDING_MATERIAL.getValues()) {
            for (ICasingMaterial casingId : RigoranthusEmortisRebornAPI.CASING_MATERIAL.getValues()) {
                items.add(CanisBedUtil.createItemStack(casingId, beddingId));
            }
        }
    }

    @Override
    public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player) {
        CanisBedTileEntity canisBedTileEntity = WorldUtil.getTileEntity(world, pos, CanisBedTileEntity.class);
        if (canisBedTileEntity != null) {
            return CanisBedUtil.createItemStack(canisBedTileEntity.getCasing(), canisBedTileEntity.getBedding());
        }
        RigoranthusEmortisReborn.LOGGER.debug("Unable to pick block on canis bed.");
        return ItemStack.EMPTY;
    }
}