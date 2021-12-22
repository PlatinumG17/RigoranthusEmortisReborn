package com.platinumg17.rigoranthusemortisreborn.magica.common.block;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.recipe.IIchoricRecipe;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.util.DominionUtil;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.IchorCrystallizerTile;
import com.platinumg17.rigoranthusemortisreborn.magica.common.util.PortUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
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
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.stream.Stream;

public class IchorCrystallizerBlock extends ModBlock {
    public static final Property<Integer> stage = IntegerProperty.create("stage", 1, 31);

    public IchorCrystallizerBlock() {
        super(ModBlock.defaultProperties().noOcclusion(),"ichor_crystallizer");
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult rayTraceResult) {
        if (world.isClientSide || handIn != Hand.MAIN_HAND)
            return ActionResultType.SUCCESS;
        IchorCrystallizerTile tile = (IchorCrystallizerTile) world.getBlockEntity(pos);
        if (tile.isCrafting)
            return ActionResultType.PASS;

        if (tile.baseMaterial != null && !tile.baseMaterial.isEmpty() && player.getItemInHand(handIn).isEmpty()) {
            ItemEntity item = new ItemEntity(world, player.getX(), player.getY(), player.getZ(), tile.baseMaterial);
            world.addFreshEntity(item);
            tile.baseMaterial = ItemStack.EMPTY;
        }
        else if (!player.inventory.getSelected().isEmpty()) {

            if (tile.baseMaterial == null || tile.baseMaterial.isEmpty()) {
                if (!player.inventory.getSelected().isEmpty()) {
                    tile.baseMaterial = player.inventory.removeItem(player.inventory.selected, 1);
                }
            }
            else if(tile.baseMaterial != null && !tile.baseMaterial.isEmpty()) {
                if(tile.reagentItem != null && !tile.reagentItem.isEmpty()){
                    ItemEntity item = new ItemEntity(world, player.getX(), player.getY(), player.getZ(), tile.reagentItem);
                    world.addFreshEntity(item);
                }
                tile.reagentItem = player.inventory.removeItem(player.inventory.selected, 1);
                if(!tile.isCrafting && player.inventory.add(tile.reagentItem)) {
                    tile.reagentItem = ItemStack.EMPTY;
                }
                IIchoricRecipe recipe = tile.getRecipe(player.getMainHandItem(), tile.baseMaterial, player);
                if (recipe == null) {
                    PortUtil.sendMessage(player, new TranslationTextComponent("rigoranthusemortisreborn.amalgamator.norecipe"));
                } else if (recipe.consumesDominion() && !DominionUtil.hasDominionNearby(tile.getBlockPos(), tile.getLevel(), 10, recipe.dominionCost())) {
                    PortUtil.sendMessage(player, new TranslationTextComponent("rigoranthusemortisreborn.amalgamator.nodominion"));
                } else {
                    if (tile.attemptCraft(player.getMainHandItem(), tile.baseMaterial, player)) {
                        tile.reagentItem = player.inventory.removeItem(player.inventory.selected, 1);
                    }
                }
            }
            else {
                tile.reagentItem = ItemStack.EMPTY;
                if (tile.attemptCraft(player.getMainHandItem(), tile.baseMaterial, player)) {
                    tile.reagentItem = player.inventory.removeItem(player.inventory.selected, 1);
                }
            }
        }
        world.sendBlockUpdated(pos, state, state, 2);
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

    @Override public BlockRenderType getRenderShape(BlockState state) { return BlockRenderType.ENTITYBLOCK_ANIMATED; }
    @Override protected void createBlockStateDefinition(StateContainer.Builder<net.minecraft.block.Block, BlockState> builder) { builder.add(stage); }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) { return new IchorCrystallizerTile(); }
    public static final VoxelShape SHAPE = Stream.of(
            Block.box(3, 15.025, 3, 13, 16, 13),
            Block.box(3, 0, 3, 13, 1, 13),
            Block.box(13, 0, 11, 14, 2, 12),
            Block.box(13, 0, 4, 14, 2, 5),
            Block.box(11, 0, 13, 12, 2, 14),
            Block.box(4, 0, 13, 5, 2, 14),
            Block.box(11, 0, 2, 12, 2, 3),
            Block.box(4, 0, 2, 5, 2, 3),
            Block.box(2, 0, 11, 3, 2, 12),
            Block.box(2, 0, 4, 3, 2, 5),
            Block.box(2, 15, 11, 3, 16, 12),
            Block.box(4, 15, 13, 5, 16, 14),
            Block.box(2, 15, 4, 3, 16, 5),
            Block.box(4, 15, 2, 5, 16, 3),
            Block.box(13, 15, 4, 14, 16, 5),
            Block.box(13, 15, 11, 14, 16, 12),
            Block.box(14, 15, 11, 15, 16, 13),
            Block.box(14, 15, 3, 15, 16, 5),
            Block.box(1, 15, 3, 2, 16, 5),
            Block.box(1, 15, 11, 2, 16, 13),
            Block.box(11, 15, 2, 12, 16, 3),
            Block.box(11, 15, 1, 13, 16, 2),
            Block.box(3, 15, 1, 5, 16, 2),
            Block.box(11, 15, 14, 13, 16, 15),
            Block.box(3, 15, 14, 5, 16, 15),
            Block.box(11, 0, 14, 13, 1, 15),
            Block.box(3, 0, 14, 5, 1, 15),
            Block.box(14, 0, 11, 15, 1, 13),
            Block.box(1, 0, 11, 2, 1, 13),
            Block.box(1, 0, 3, 2, 1, 5),
            Block.box(3, 0, 1, 5, 1, 2),
            Block.box(11, 0, 1, 13, 1, 2),
            Block.box(14, 0, 3, 15, 1, 5),
            Block.box(11, 15, 13, 12, 16, 14),
            Block.box(1, 0, 1, 3, 3, 3),
            Block.box(1, 0, 13, 3, 3, 15),
            Block.box(13, 0, 13, 15, 3, 15),
            Block.box(13, 0, 1, 15, 3, 3),
            Block.box(13, 13, 1, 15, 16, 3),
            Block.box(1, 13, 1, 3, 16, 3),
            Block.box(1, 13, 13, 3, 16, 15),
            Block.box(13, 13, 13, 15, 16, 15)
    ).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();
    @Override public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) { return SHAPE; }
}