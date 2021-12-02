package com.platinumg17.rigoranthusemortisreborn.magica.common.block;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.AbstractSpellPart;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.PsyglyphicCipherTile;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.RuneTile;
import com.platinumg17.rigoranthusemortisreborn.magica.common.items.SpellParchment;
import com.platinumg17.rigoranthusemortisreborn.magica.common.lib.LibBlockNames;
import com.platinumg17.rigoranthusemortisreborn.magica.common.spell.method.MethodTouch;
import com.platinumg17.rigoranthusemortisreborn.magica.common.util.PortUtil;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class PsyglyphicBlock extends ModBlock implements IWaterLoggable {

    public PsyglyphicBlock() {
        super(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).sound(SoundType.STONE).strength(10f, 15f).harvestLevel(3).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().noOcclusion().lightLevel(state -> 10), LibBlockNames.PSYGLYPHIC_CIPHER);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
    }
    public PsyglyphicBlock(AbstractBlock.Properties properties, String registryName){
        super(properties, registryName);
    }
    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    public static final DirectionProperty FACING = BlockStateProperties.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    protected static final VoxelShape SHAPE = Block.box(2, 0, 2, 14, 15, 14);

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
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
    public BlockRenderType getRenderType(BlockState p_149645_1_) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }
    @Override protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {builder.add(FACING, WATERLOGGED);}

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState state, World world, BlockPos pos, Random rand) {
        double lvt_5_1_ = (double)pos.getX() + 0.5D;
        double lvt_7_1_ = (double)pos.getY();
        double lvt_9_1_ = (double)pos.getZ() + 0.5D;
        if (rand.nextDouble() < 0.1D) {
            world.playLocalSound(lvt_5_1_, lvt_7_1_, lvt_9_1_, SoundEvents.PORTAL_AMBIENT, SoundCategory.BLOCKS, 0.4F, 0.4F, false);
        }
        for(int i = 0; i < 4; ++i) {
            double d0 = (double)pos.getX() + (double)rand.nextFloat();
            double d1 = (double)pos.getY() + (double)rand.nextFloat();
            double d2 = (double)pos.getZ() + (double)rand.nextFloat();
            double d3 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
            double d4 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
            double d5 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
            int j = rand.nextInt(2) * 2 - 1;
            if (world.getBlockState(pos.west()).getBlock() != this && world.getBlockState(pos.east()).getBlock() != this) {
                d0 = (double)pos.getX() + 0.5D + 0.25D * (double)j;
                d3 = rand.nextFloat() * 2.0F * (float)j;
            } else {
                d2 = (double)pos.getZ() + 0.5D + 0.25D * (double)j;
                d5 = rand.nextFloat() * 2.0F * (float)j;
            }
            world.addParticle(ParticleTypes.ENCHANT, d0, d1, d2, d3, d4, d5);
        }
    }
    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        ItemStack stack = player.getItemInHand(handIn);

        if(!worldIn.isClientSide && stack.getItem() == Items.PAPER){
            if(!((PsyglyphicCipherTile)worldIn.getBlockEntity(pos)).hasBeenUsed){
                ((PsyglyphicCipherTile)worldIn.getBlockEntity(pos)).hasBeenUsed = true;
                PortUtil.sendMessage(player, new TranslationTextComponent("rigoranthusemortisreborn.cipher.used"));
                return ActionResultType.SUCCESS;
            }
        }
//        ((PsyglyphicCipherTile)worldIn.getBlockEntity(pos)).save(this.hasBeenUsed);
//        PortUtil.sendMessage(player, new TranslationTextComponent("rigoranthusemortisreborn.spell_set"));
        return super.use(state, worldIn, pos, player, handIn, hit);
    }
    @Override
    public void setPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(worldIn, pos, state, placer, stack);
    } //TODO  is this needed? does this do anything?

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new PsyglyphicCipherTile();
    }
}