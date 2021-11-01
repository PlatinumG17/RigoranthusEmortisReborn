package com.platinumg17.rigoranthusemortisreborn.world.biome;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Random;

public class VerdurousSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig> {
    public VerdurousSurfaceBuilder(Codec<SurfaceBuilderConfig> function) {
        super(function);
    }

    @Override
    public void apply(Random random, IChunk chunk, Biome biome, int x, int z, int height, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig surfaceBlocks) {
        if (height > 80) {
            SurfaceBuilder.DEFAULT.apply(random, chunk, biome, x, z, height, noise, defaultBlock, defaultFluid, seaLevel, seed, new SurfaceBuilderConfig(Blocks.GRASS_BLOCK.defaultBlockState(), Blocks.GRASS_BLOCK.defaultBlockState(), Blocks.GRAVEL.defaultBlockState()));
            return;
        }
        BlockState state = Blocks.GRASS_BLOCK.defaultBlockState();
        if (noise > -0.5 && noise < 2.3) {
            state = Blocks.SAND.defaultBlockState();
            if (noise > 0.6 && noise < 2.2) {
                state = Blocks.PODZOL.defaultBlockState();
                if (noise > 0.9 && noise < 1.9) {
                    state = Blocks.GRASS_PATH.defaultBlockState();
                }
            }
        }
        SurfaceBuilder.DEFAULT.apply(random, chunk, biome, x, z, height, noise, defaultBlock, defaultFluid, seaLevel, seed, new SurfaceBuilderConfig(state, state, state));
    }
}