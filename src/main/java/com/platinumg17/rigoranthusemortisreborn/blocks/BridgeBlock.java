package com.platinumg17.rigoranthusemortisreborn.blocks;

import java.util.stream.Stream;

import com.platinumg17.rigoranthusemortisreborn.blocks.bridge.BridgeBase;
import com.platinumg17.rigoranthusemortisreborn.blocks.bridge.PartDefinition;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.SoundType;
import net.minecraft.block.AbstractBlock.Properties;
import net.minecraft.block.material.Material;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

public class BridgeBlock extends BridgeBase implements IWaterLoggable {
    private static final EnumProperty<PartDefinition> PART = EnumProperty.create("part", PartDefinition.class);
    private static final BooleanProperty WATERLOGGED;
    public static final BooleanProperty LIT;
    private static final DirectionProperty FACING;
    protected static final VoxelShape NS;
    protected static final VoxelShape WE;
    protected static final VoxelShape END_N;
    protected static final VoxelShape END_E;
    protected static final VoxelShape END_S;
    protected static final VoxelShape END_W;

    public VoxelShape getShape(BlockState state, IBlockReader blockReader, BlockPos pos, ISelectionContext selectionContext) {
        switch((Direction)state.getValue(FACING)) {
            case NORTH:
                PartDefinition part1 = (PartDefinition)state.getValue(PART);
                if (part1 == PartDefinition.MIDDLE) {
                    return NS;
                } else {
                    if (part1 == PartDefinition.END) {
                        return END_S;
                    }

                    return END_N;
                }
            case SOUTH:
                PartDefinition part2 = (PartDefinition)state.getValue(PART);
                if (part2 == PartDefinition.MIDDLE) {
                    return NS;
                } else {
                    if (part2 == PartDefinition.END) {
                        return END_N;
                    }

                    return END_S;
                }
            case EAST:
                PartDefinition part3 = (PartDefinition)state.getValue(PART);
                if (part3 == PartDefinition.MIDDLE) {
                    return WE;
                } else {
                    if (part3 == PartDefinition.END) {
                        return END_W;
                    }

                    return END_E;
                }
            case WEST:
            default:
                PartDefinition part4 = (PartDefinition)state.getValue(PART);
                if (part4 == PartDefinition.MIDDLE) {
                    return WE;
                } else {
                    return part4 == PartDefinition.END ? END_E : END_W;
                }
        }
    }

    public BridgeBlock() {
        super(Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(3.0F, 5.0F).lightLevel(getLightLevel(15)).noOcclusion());
        this.registerDefaultState(this.stateDefinition.any().setValue(LIT, false).setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false).setValue(LIT, false).setValue(PART, PartDefinition.MIDDLE));
    }

    static {
        WATERLOGGED = BlockStateProperties.WATERLOGGED;
        LIT = BlockStateProperties.LIT;
        FACING = HorizontalBlock.FACING;
        NS = (VoxelShape)Stream.of(Block.box(7.0D, 3.0D, 24.01D, 9.0D, 16.0D, 26.01D), Block.box(0.0D, 0.0D, -8.0D, 16.0D, 1.0D, 8.0D), Block.box(0.0D, 0.0D, 8.0D, 16.0D, 1.0D, 24.0D), Block.box(-0.01D, 0.0D, -11.0D, 15.99D, 3.0D, -8.0D), Block.box(-0.01D, 0.0D, 24.0D, 15.99D, 3.0D, 27.0D), Block.box(7.0D, 3.0D, -10.01D, 9.0D, 16.0D, -8.01D), Block.box(0.0D, 15.0D, -9.0D, 16.0D, 17.0D, -8.0D), Block.box(0.0D, 15.0D, 24.0D, 16.0D, 17.0D, 25.0D)).reduce((v1, v2) -> {
            return VoxelShapes.join(v1, v2, IBooleanFunction.OR);
        }).get();
        WE = (VoxelShape)Stream.of(Block.box(-10.010000000000002D, 3.0D, 7.0D, -8.010000000000002D, 16.0D, 9.0D), Block.box(8.0D, 0.0D, 0.0D, 24.0D, 1.0D, 16.0D), Block.box(-8.0D, 0.0D, 0.0D, 8.0D, 1.0D, 16.0D), Block.box(24.0D, 0.0D, -0.009999999999999787D, 27.0D, 3.0D, 15.99D), Block.box(-11.0D, 0.0D, -0.009999999999999787D, -8.0D, 3.0D, 15.99D), Block.box(24.009999999999998D, 3.0D, 7.0D, 26.009999999999998D, 16.0D, 9.0D), Block.box(24.0D, 15.0D, 0.0D, 25.0D, 17.0D, 16.0D), Block.box(-9.0D, 15.0D, 0.0D, -8.0D, 17.0D, 16.0D)).reduce((v1, v2) -> {
            return VoxelShapes.join(v1, v2, IBooleanFunction.OR);
        }).get();
        END_N = (VoxelShape)Stream.of(Block.box(0.0D, 0.0D, 8.0D, 16.0D, 1.0D, 24.0D), Block.box(0.0D, 0.0D, -8.0D, 16.0D, 1.0D, 8.0D), Block.box(-2.01D, 0.0D, 24.0D, 15.99D, 3.0D, 27.0D), Block.box(-2.01D, 0.0D, -11.0D, 15.99D, 3.0D, -8.0D), Block.box(7.0D, 3.0D, 24.01D, 9.0D, 16.0D, 26.01D), Block.box(7.0D, 3.0D, -10.01D, 9.0D, 16.0D, -8.01D), Block.box(7.0D, 15.0D, 24.0D, 16.0D, 17.0D, 25.0D), Block.box(7.0D, 15.0D, -9.0D, 16.0D, 17.0D, -8.0D), Block.box(-2.0D, 0.0D, 8.0D, 0.0D, 1.0D, 24.0D), Block.box(-2.0D, 0.0D, -8.0D, 0.0D, 1.0D, 8.0D)).reduce((v1, v2) -> {
            return VoxelShapes.join(v1, v2, IBooleanFunction.OR);
        }).get();
        END_E = (VoxelShape)Stream.of(Block.box(-8.0D, 0.0D, 0.0D, 8.0D, 1.0D, 16.0D), Block.box(8.0D, 0.0D, 0.0D, 24.0D, 1.0D, 16.0D), Block.box(-11.0D, 0.0D, -2.01D, -8.0D, 3.0D, 15.99D), Block.box(24.0D, 0.0D, -2.01D, 27.0D, 3.0D, 15.99D), Block.box(-10.010000000000002D, 3.0D, 7.0D, -8.010000000000002D, 16.0D, 9.0D), Block.box(24.009999999999998D, 3.0D, 7.0D, 26.009999999999998D, 16.0D, 9.0D), Block.box(-9.0D, 15.0D, 7.0D, -8.0D, 17.0D, 16.0D), Block.box(24.0D, 15.0D, 7.0D, 25.0D, 17.0D, 16.0D), Block.box(-8.0D, 0.0D, -2.0D, 8.0D, 1.0D, 0.0D), Block.box(8.0D, 0.0D, -2.0D, 24.0D, 1.0D, 0.0D)).reduce((v1, v2) -> {
            return VoxelShapes.join(v1, v2, IBooleanFunction.OR);
        }).get();
        END_S = (VoxelShape)Stream.of(Block.box(0.0D, 0.0D, -8.0D, 16.0D, 1.0D, 8.0D), Block.box(0.0D, 0.0D, 8.0D, 16.0D, 1.0D, 24.0D), Block.box(0.009999999999999787D, 0.0D, -11.0D, 18.009999999999998D, 3.0D, -8.0D), Block.box(0.009999999999999787D, 0.0D, 24.0D, 18.009999999999998D, 3.0D, 27.0D), Block.box(7.0D, 3.0D, -10.010000000000002D, 9.0D, 16.0D, -8.010000000000002D), Block.box(7.0D, 3.0D, 24.009999999999998D, 9.0D, 16.0D, 26.009999999999998D), Block.box(0.0D, 15.0D, -9.0D, 9.0D, 17.0D, -8.0D), Block.box(0.0D, 15.0D, 24.0D, 9.0D, 17.0D, 25.0D), Block.box(16.0D, 0.0D, -8.0D, 18.0D, 1.0D, 8.0D), Block.box(16.0D, 0.0D, 8.0D, 18.0D, 1.0D, 24.0D)).reduce((v1, v2) -> {
            return VoxelShapes.join(v1, v2, IBooleanFunction.OR);
        }).get();
        END_W = (VoxelShape)Stream.of(Block.box(8.0D, 0.0D, 0.0D, 24.0D, 1.0D, 16.0D), Block.box(-8.0D, 0.0D, 0.0D, 8.0D, 1.0D, 16.0D), Block.box(24.0D, 0.0D, 0.009999999999999787D, 27.0D, 3.0D, 18.009999999999998D), Block.box(-11.0D, 0.0D, 0.009999999999999787D, -8.0D, 3.0D, 18.009999999999998D), Block.box(24.01D, 3.0D, 7.0D, 26.01D, 16.0D, 9.0D), Block.box(-10.009999999999998D, 3.0D, 7.0D, -8.009999999999998D, 16.0D, 9.0D), Block.box(24.0D, 15.0D, 0.0D, 25.0D, 17.0D, 9.0D), Block.box(-9.0D, 15.0D, 0.0D, -8.0D, 17.0D, 9.0D), Block.box(8.0D, 0.0D, 16.0D, 24.0D, 1.0D, 18.0D), Block.box(-8.0D, 0.0D, 16.0D, 8.0D, 1.0D, 18.0D)).reduce((v1, v2) -> {
            return VoxelShapes.join(v1, v2, IBooleanFunction.OR);
        }).get();
    }
}