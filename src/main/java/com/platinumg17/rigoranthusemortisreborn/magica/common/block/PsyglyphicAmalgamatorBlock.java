package com.platinumg17.rigoranthusemortisreborn.magica.common.block;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.psyglyphic_amalgamator.IPsyglyphicRecipe;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.util.DominionUtil;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.PsyglyphicAmalgamatorTile;
import com.platinumg17.rigoranthusemortisreborn.magica.common.util.PortUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.stream.Stream;

public class PsyglyphicAmalgamatorBlock extends ModBlock {

    public PsyglyphicAmalgamatorBlock() {
        super(ModBlock.defaultProperties().noOcclusion(),"psyglyphic_amalgamator");
    }

    @Override public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override public void attack(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) { super.attack(state, worldIn, pos, player); }

    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (world.isClientSide || handIn != Hand.MAIN_HAND)
            return ActionResultType.SUCCESS;
        PsyglyphicAmalgamatorTile tile = (PsyglyphicAmalgamatorTile) world.getBlockEntity(pos);
        if (tile.isCrafting)
            return ActionResultType.SUCCESS;
        if (!(world.getBlockState(pos.below()).getBlock() instanceof EmorticCortex)) {
            PortUtil.sendMessage(player, new TranslationTextComponent("alert.core"));
            return ActionResultType.SUCCESS;
        }
        if (tile.catalystItem == null || tile.catalystItem.isEmpty()) {
            IPsyglyphicRecipe recipe = tile.getRecipe(player.getMainHandItem(), player);
            if (recipe == null) {
                PortUtil.sendMessage(player, new TranslationTextComponent("rigoranthusemortisreborn.amalgamator.norecipe"));
            } else if (recipe.consumesDominion() && !DominionUtil.hasDominionNearby(tile.getBlockPos(), tile.getLevel(), 10, recipe.dominionCost())) {
                PortUtil.sendMessage(player, new TranslationTextComponent("rigoranthusemortisreborn.amalgamator.nodominion"));
            } else {
                if(tile.attemptCraft(player.getMainHandItem(), player)) {
                    tile.catalystItem = player.inventory.removeItem(player.inventory.selected, 1);
                }
            }
        } else {
            ItemEntity item = new ItemEntity(world, player.getX(), player.getY(), player.getZ(), tile.catalystItem);
            world.addFreshEntity(item);
            tile.catalystItem = ItemStack.EMPTY;
            if (tile.attemptCraft(player.getMainHandItem(), player)) {
                tile.catalystItem = player.inventory.removeItem(player.inventory.selected, 1);
            }
        }
        world.sendBlockUpdated(pos, state, state, 2);
        return ActionResultType.SUCCESS;
    }
    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) { return new PsyglyphicAmalgamatorTile(); }
    public static final VoxelShape SHAPE = Stream.of(
            Block.box(14, 1.449, 2, 15, 5.7, 14),
            Block.box(13.25, 0.75, 13.25, 16, 7.75, 16),
            Block.box(0, 0.75, 13.25, 2.75, 7.75, 16),
            Block.box(13.25, 0.75, 0, 16, 7.75, 2.75),
            Block.box(0, 0.75, 0, 2.75, 7.75, 2.75),
            Block.box(2, 1.45, 14, 14, 5.7, 15),
            Block.box(2, 1.45, 1, 14, 5.7, 2),
            Block.box(1, 1.45, 2, 2, 5.7, 14),
            Block.box(4, 4.7, 4, 12, 5.7, 12)
    ).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();
    @Override public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) { return SHAPE; }
    @Override public BlockRenderType getRenderShape(BlockState p_149645_1_) {return BlockRenderType.MODEL;}

    @Override
    public void playerWillDestroy(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        super.playerWillDestroy(worldIn, pos, state, player);
        if (worldIn.getBlockEntity(pos) instanceof PsyglyphicAmalgamatorTile && ((PsyglyphicAmalgamatorTile) worldIn.getBlockEntity(pos)).catalystItem != null) {
            worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), ((PsyglyphicAmalgamatorTile) worldIn.getBlockEntity(pos)).catalystItem));
        }
    }
}