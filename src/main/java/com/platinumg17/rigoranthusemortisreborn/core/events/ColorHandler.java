package com.platinumg17.rigoranthusemortisreborn.core.events;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.platinumg17.rigoranthusemortisreborn.blocks.DecorativeOrStorageBlocks;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.core.init.Registration;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.StemBlock;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.ItemStack;
import net.minecraft.state.Property;
import net.minecraft.world.GrassColors;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;
import java.util.Set;

//@Mod.EventBusSubscriber(modid = EmortisConstants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
//public class ColorHandler {
//    private final Map<Block, Set<Property<?>>> coloringStates = Maps.newHashMap();
//    private final java.util.Map<net.minecraftforge.registries.IRegistryDelegate<Block>, IBlockColor> blockColors = new java.util.HashMap<>();
//    @SubscribeEvent
//    public static void onItemColors(ColorHandlerEvent.Item event) {
//        ItemColors itemColors = event.getItemColors();
//
////        itemColors.register((stack, tintIndex) -> BlockColor__block here__.handleColorTint(com.platinumg17.rigoranthusemortisreborn.util.ColorHandler.getColorFromStack(stack), tintIndex), ModItems.APPLE, ModItems.POTION);
////        itemColors.register(new ModItemColor(), ModItems.ITEM);
//    }
//    @SubscribeEvent
//    public static void initBlockColors(ColorHandlerEvent.Block event) {
//        BlockColors colors = event.getBlockColors();
//        colors.register((state, worldIn, pos, tintIndex) -> {
//            int age = state.getValue(StemBlock.AGE);
//            int red = age * 32;
//            int green = 255 - age * 8;
//            int blue = age * 4;
//            return red << 16 | green << 8 | blue;
//        }, Registration.SPECTABILIS_BUSH);
//
//        colors.register((p_228064_0_, worldIn, blockPos, tintIndex) -> {
//            return worldIn != null && blockPos != null ? BiomeColors.getAverageGrassColor(worldIn, blockPos) : GrassColors.get(0.5D, 0.8D);
//        }, DecorativeOrStorageBlocks.AZULOREAL_ORCHID.get(), DecorativeOrStorageBlocks.IRIDESCENT_SPROUTS.get());
//
////        colors.addColoringStates(StemBlock.AGE, Registration.SPECTABILIS_STEM);
//    }
//}



