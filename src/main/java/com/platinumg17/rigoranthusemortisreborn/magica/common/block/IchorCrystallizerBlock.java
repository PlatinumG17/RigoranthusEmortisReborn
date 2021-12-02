package com.platinumg17.rigoranthusemortisreborn.magica.common.block;

import com.platinumg17.rigoranthusemortisreborn.blocks.BlockInit;
import com.platinumg17.rigoranthusemortisreborn.core.init.ItemInit;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.IchorCrystallizerTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.Property;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.stream.Stream;

public class IchorCrystallizerBlock extends ModBlock{
    public static final Property<Integer> stage = IntegerProperty.create("stage", 1, 31);

    public IchorCrystallizerBlock() {
        super(ModBlock.defaultProperties().noOcclusion(),"ichor_crystallizer");
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand
    handIn, BlockRayTraceResult p_225533_6_) {
        if(!world.isClientSide) {
            IchorCrystallizerTile tile = (IchorCrystallizerTile) world.getBlockEntity(pos);
            if(tile.isCrafting)
                return ActionResultType.PASS;

            if (tile.baseMaterial != null && !tile.baseMaterial.isEmpty() && player.getItemInHand(handIn).isEmpty()) {
                ItemEntity item = new ItemEntity(world, player.getX(), player.getY(), player.getZ(), tile.baseMaterial);
                world.addFreshEntity(item);
                tile.baseMaterial = ItemStack.EMPTY;
            }
            else if (!player.inventory.getSelected().isEmpty()) {
                if(player.getItemInHand(handIn).getItem() == ItemInit.BOTTLE_OF_ICHOR.get()
                        || player.getItemInHand(handIn).getItem() == ItemInit.GHAST_IRON_INGOT.get()
                        || player.getItemInHand(handIn).getItem() == ItemInit.BLIGHT_ICHOR.get()
                        || player.getItemInHand(handIn).getItem() == ItemInit.BUCKET_OF_CADAVEROUS_ICHOR.get()
                        || player.getItemInHand(handIn).getItem() == BlockInit.DWELLER_BRAIN.get().asItem()
                        || player.getItemInHand(handIn).getItem() == ItemInit.PACT_OF_SERVITUDE.get()
                        || player.getItemInHand(handIn).getItem() == ItemInit.PACT_OF_MYRMIDON.get()
                        || player.getItemInHand(handIn).getItem() == ItemInit.PACT_OF_PURTURBATION.get()
                        || player.getItemInHand(handIn).getItem() == Items.PAPER) {
                    if(tile.baseMaterial != null && !tile.baseMaterial.isEmpty()){
                        ItemEntity item = new ItemEntity(world, player.getX(), player.getY(), player.getZ(), tile.baseMaterial);
                        world.addFreshEntity(item);
                    }
                    tile.baseMaterial = player.inventory.removeItem(player.inventory.selected, 1);
                }else if(tile.baseMaterial != null && !tile.baseMaterial.isEmpty()){
                    if(tile.reagentItem != null && !tile.reagentItem.isEmpty()){
                        ItemEntity item = new ItemEntity(world, player.getX(), player.getY(), player.getZ(), tile.reagentItem);
                        world.addFreshEntity(item);
                    }
                    tile.reagentItem = player.inventory.removeItem(player.inventory.selected, 1);
                    if(!tile.craft(player) && player.inventory.add(tile.reagentItem)) {
                        tile.reagentItem = ItemStack.EMPTY;
                    }
                }
            }
            world.sendBlockUpdated(pos, state, state, 2);
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public void playerWillDestroy(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        super.playerWillDestroy(worldIn, pos, state, player);
        if(!(worldIn.getBlockEntity(pos) instanceof IchorCrystallizerTile) || worldIn.isClientSide)
            return;
        IchorCrystallizerTile tile = ((IchorCrystallizerTile) worldIn.getBlockEntity(pos));
        if(tile.baseMaterial != null && !tile.baseMaterial.isEmpty()){
            worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), tile.baseMaterial));
            if(tile.reagentItem != null && !tile.reagentItem.isEmpty()){
                worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), tile.reagentItem));
            }
        }
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new IchorCrystallizerTile();
    }

    @Override
    public BlockRenderType getRenderShape(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<net.minecraft.block.Block, BlockState> builder) { builder.add(stage); }

    public static final VoxelShape SHAPE = Stream.of(
            Block.box(7, 2, 11, 9, 3, 12),
            Block.box(7, 2, 4, 9, 3, 5),
            Block.box(4, 2, 7, 5, 3, 9),
            Block.box(11, 2, 7, 12, 3, 9),
            Block.box(5, 1.75, 5, 11, 3, 11),
            Block.box(2, 15, 2, 14, 16, 14),
            Block.box(2, 0, 2, 14, 1, 14),
            Block.box(4, 1, 4, 12, 2, 12),
            Block.box(3.75, 14, 3.75, 12.25, 15, 12.25),
            Block.box(15, 15, 11, 16, 16, 14),
            Block.box(11, 15, 15, 14, 16, 16),
            Block.box(2, 15, 15, 5, 16, 16),
            Block.box(2, 15, 0, 5, 16, 1),
            Block.box(11, 15, 0, 14, 16, 1),
            Block.box(15, 15, 11, 16, 16, 14),
            Block.box(15, 15, 2, 16, 16, 5),
            Block.box(0, 15, 2, 1, 16, 5),
            Block.box(0, 15, 11, 1, 16, 14),
            Block.box(1, 15, 11, 2, 16, 12),
            Block.box(11, 15, 1, 12, 16, 2),
            Block.box(15, 0, 11, 16, 1, 14),
            Block.box(11, 0, 15, 14, 1, 16),
            Block.box(0, 0, 14, 2, 16, 16),
            Block.box(4, 15, 14, 5, 16, 15),
            Block.box(11, 15, 14, 12, 16, 15),
            Block.box(11, 15, 15, 14, 16, 16),
            Block.box(14, 0, 14, 16, 16, 16),
            Block.box(15, 15, 11, 16, 16, 14),
            Block.box(14, 15, 11, 15, 16, 12),
            Block.box(4, 15, 1, 5, 16, 2),
            Block.box(1, 15, 4, 2, 16, 5),
            Block.box(14, 15, 4, 15, 16, 5),
            Block.box(14, 0, 0, 16, 16, 2),
            Block.box(0, 0, 0, 2, 16, 2),
            Block.box(11, 15, 15, 14, 16, 16),
            Block.box(15, 15, 11, 16, 16, 14),
            Block.box(11, 15, 15, 14, 16, 16),
            Block.box(15, 0, 11, 16, 1, 14),
            Block.box(11, 0, 15, 14, 1, 16),
            Block.box(15, 0, 11, 16, 1, 14),
            Block.box(15, 0, 2, 16, 1, 5),
            Block.box(0, 0, 2, 1, 1, 5),
            Block.box(0, 0, 11, 1, 1, 14),
            Block.box(11, 0, 15, 14, 1, 16),
            Block.box(11, 0, 15, 14, 1, 16),
            Block.box(2, 0, 15, 5, 1, 16),
            Block.box(11, 0, 0, 14, 1, 1),
            Block.box(2, 0, 0, 5, 1, 1),
            Block.box(14, 0, 11, 15, 2, 12),
            Block.box(14, 0, 4, 15, 2, 5),
            Block.box(11, 0, 14, 12, 2, 15),
            Block.box(4, 0, 14, 5, 2, 15),
            Block.box(11, 0, 1, 12, 2, 2),
            Block.box(4, 0, 1, 5, 2, 2),
            Block.box(1, 0, 11, 2, 2, 12),
            Block.box(1, 0, 4, 2, 2, 5)
    ).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    public BlockRenderType getRenderType(BlockState p_149645_1_) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }
}