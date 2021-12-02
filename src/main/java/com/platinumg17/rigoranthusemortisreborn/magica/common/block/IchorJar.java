package com.platinumg17.rigoranthusemortisreborn.magica.common.block;

import com.platinumg17.rigoranthusemortisreborn.core.init.ItemInit;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.IchorJarTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.Property;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Stream;

public class IchorJar extends IchorBlock {
    public static final VoxelShape JAR_SHAPE = Stream.of(
            Block.box(6, 14, 10, 10, 15, 11),
            Block.box(7, 0, 1, 9, 2, 2),
            Block.box(7, 1, 2, 9, 2, 5),
            Block.box(5, 1, 4, 7, 2, 5),
            Block.box(9, 1, 4, 12, 2, 5),
            Block.box(1, 0, 7, 2, 2, 9),
            Block.box(2, 1, 7, 5, 2, 9),
            Block.box(4, 1, 9, 5, 2, 11),
            Block.box(4, 1, 4, 5, 2, 7),
            Block.box(7, 0, 14, 9, 2, 15),
            Block.box(7, 1, 11, 9, 2, 14),
            Block.box(9, 1, 11, 11, 2, 12),
            Block.box(4, 1, 11, 7, 2, 12),
            Block.box(14, 0, 7, 15, 2, 9),
            Block.box(11, 1, 7, 14, 2, 9),
            Block.box(11, 1, 5, 12, 2, 7),
            Block.box(11, 1, 9, 12, 2, 12),
            Block.box(14, 12, 5, 15, 13, 11),
            Block.box(5, 12, 14, 11, 13, 15),
            Block.box(1, 12, 5, 2, 13, 11),
            Block.box(5, 12, 1, 11, 13, 2),
            Block.box(13, 2, 7, 14, 7, 9),
            Block.box(7, 2, 13, 9, 7, 14),
            Block.box(7, 8, 13, 9, 13, 14),
            Block.box(13, 8, 7, 14, 13, 9),
            Block.box(3, 7, 2, 14, 8, 3),
            Block.box(13, 7, 3, 14, 8, 14),
            Block.box(2, 7, 13, 13, 8, 14),
            Block.box(2, 7, 2, 3, 8, 13),
            Block.box(2, 2, 7, 3, 7, 9),
            Block.box(7, 2, 2, 9, 7, 3),
            Block.box(7, 8, 2, 9, 13, 3),
            Block.box(2, 8, 7, 3, 13, 9),
            Block.box(12, 2, 4, 13, 14, 13),
            Block.box(4, 15, 4, 12, 17, 5),
            Block.box(4, 15, 11, 12, 17, 12),
            Block.box(3, 2, 12, 12, 14, 13),
            Block.box(5, 15, 5, 11, 18, 11),
            Block.box(11, 15, 5, 12, 17, 11),
            Block.box(4, 15, 5, 5, 17, 11),
            Block.box(3, 2, 3, 4, 14, 12),
            Block.box(4, 2, 3, 13, 14, 4),
            Block.box(10, 14, 5, 11, 15, 11),
            Block.box(5, 14, 5, 6, 15, 11),
            Block.box(6, 14, 5, 10, 15, 6)
    ).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return JAR_SHAPE;
    }
    public static final Property<Integer> fill = IntegerProperty.create("fill", 0, 11);
    public IchorJar() {
        super(defaultProperties().noOcclusion(),"ichor_jar");
    }
    public IchorJar(Properties properties, String registryName){
        super(properties, registryName);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new IchorJarTile();
    }

    @Override
    public BlockRenderType getRenderShape(BlockState p_149645_1_) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<net.minecraft.block.Block, BlockState> builder) { builder.add(IchorJar.fill); }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return super.getStateForPlacement(context);
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState blockState, World worldIn, BlockPos pos) {
        IchorJarTile tile = (IchorJarTile) worldIn.getBlockEntity(pos);
        if (tile == null || tile.getCurrentIchor() <= 0) return 0;
        int step = (tile.getMaxIchor() - 1) / 14;
        return (tile.getCurrentIchor() - 1) / step + 1;
    }

    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(worldIn.isClientSide)
            return ActionResultType.SUCCESS;

        IchorJarTile tile = (IchorJarTile) worldIn.getBlockEntity(pos);
        if(tile == null)
            return ActionResultType.SUCCESS;
        ItemStack stack = player.getItemInHand(handIn);
        if(stack.getItem() == ItemInit.BOTTLE_OF_ICHOR.get()) {
            if (tile.getCurrentIchor() == 0) {
                tile.addIchor(100);
                if(!player.isCreative()) {
                    player.addItem(new ItemStack(Items.GLASS_BOTTLE));
                    stack.shrink(1);
                }
            }
            else if(tile.getCurrentIchor() < tile.getMaxIchor()){
                tile.addIchor(100);
                if(!player.isCreative()) {
                    player.addItem(new ItemStack(Items.GLASS_BOTTLE));
                    stack.shrink(1);
                }
            }
            worldIn.sendBlockUpdated(pos, worldIn.getBlockState(pos), worldIn.getBlockState(pos), 3);
        }
        if(stack.getItem() == ItemInit.BUCKET_OF_CADAVEROUS_ICHOR.get()) {
            if (tile.getCurrentIchor() == 0) {
                tile.addIchor(1000);
                if(!player.isCreative()) {
                    player.addItem(new ItemStack(Items.BUCKET));
                    stack.shrink(1);
                }
            }
            else if(tile.getCurrentIchor() < tile.getMaxIchor()){
                tile.addIchor(1000);
                if(!player.isCreative()) {
                    player.addItem(new ItemStack(Items.BUCKET));
                    stack.shrink(1);
                }
            }
            worldIn.sendBlockUpdated(pos, worldIn.getBlockState(pos), worldIn.getBlockState(pos), 3);
        }
        if(stack.getItem() == Items.GLASS_BOTTLE && tile.getCurrentIchor() >= 100){
            ItemStack ichor = new ItemStack(ItemInit.BOTTLE_OF_ICHOR.get());
            player.addItem(ichor);
            player.getItemInHand(handIn).shrink(1);
            tile.removeIchor(100);
        }
        if(stack.getItem() == Items.BUCKET && tile.getCurrentIchor() >= 1000){
            ItemStack ichor = new ItemStack(ItemInit.BUCKET_OF_CADAVEROUS_ICHOR.get());
            player.addItem(ichor);
            player.getItemInHand(handIn).shrink(1);
            tile.removeIchor(1000);
        }
        return super.use(state,worldIn,pos,player,handIn,hit);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        if(stack.getTag() == null)
            return;
        int ichor = stack.getTag().getCompound("BlockEntityTag").getInt("ichor");
        tooltip.add( new StringTextComponent((ichor*100) / 10000 + "% full"));
    }
}
