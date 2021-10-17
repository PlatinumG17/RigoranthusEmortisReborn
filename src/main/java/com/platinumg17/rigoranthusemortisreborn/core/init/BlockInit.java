package com.platinumg17.rigoranthusemortisreborn.core.init;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

import com.google.common.collect.Maps;
import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.blocks.*;
import com.platinumg17.rigoranthusemortisreborn.blocks.trees.AzulorealTree;
import com.platinumg17.rigoranthusemortisreborn.blocks.trees.JessicTree;
import com.platinumg17.rigoranthusemortisreborn.config.Config;
import com.platinumg17.rigoranthusemortisreborn.core.registry.RigoranthusItemGroup;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;

public class BlockInit {

	public static final DeferredRegister<Block> BLOCKS =
			DeferredRegister.create(ForgeRegistries.BLOCKS,	RigoranthusEmortisReborn.MOD_ID);

	public static final RegistryObject<Block> ABYSSALITE = registerBlock("abyssalite",
			() -> new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK)
					.strength(60f, 1800f).harvestTool(ToolType.PICKAXE).harvestLevel(3).requiresCorrectToolForDrops().sound(SoundType.STONE)), "tooltip.block.rigoranthusemortisreborn.abyssalite");

	public static final RegistryObject<Block> OXYLITE = registerBlock("oxylite",
			() -> new Block(AbstractBlock.Properties.of(Material.ICE_SOLID, MaterialColor.COLOR_BLUE)
					.strength(12f, 20f).friction(0.5f).harvestTool(ToolType.PICKAXE).harvestLevel(3).requiresCorrectToolForDrops().sound(SoundType.GLASS)), "tooltip.block.rigoranthusemortisreborn.oxylite");

	public static final RegistryObject<Block> SANDESITE = registerBlock("sandesite",
			() -> new GravelBlock(AbstractBlock.Properties.of(Material.SAND, MaterialColor.CLAY).strength(0.6f, 0.6f)
					.harvestTool(ToolType.SHOVEL).harvestLevel(0).sound(SoundType.GRAVEL)), "tooltip.block.rigoranthusemortisreborn.sandesite");

	public static final RegistryObject<Block> FORTIFIED_SANDESITE = registerBlock("fortified_sandesite",
			() -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.STONE).strength(8f, 10f)
					.harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().sound(SoundType.STONE)), "tooltip.block.rigoranthusemortisreborn.fortified_sandesite");

	public static final RegistryObject<Block> FRAGMENTED_COBBLESTONE = registerBlock("fragmented_cobblestone",
			() -> new GravelBlock(AbstractBlock.Properties.of(Material.SAND, MaterialColor.STONE).strength(0.8f, 0.8f)
					.harvestTool(ToolType.SHOVEL).harvestLevel(0).sound(SoundType.GRAVEL)), "tooltip.block.rigoranthusemortisreborn.fragmented_cobblestone");

	public static final RegistryObject<Block> FRAGMENTED_NETHERRACK = registerBlock("fragmented_netherrack",
			() -> new GravelBlock(AbstractBlock.Properties.of(Material.SAND, MaterialColor.NETHER).strength(0.6f, 0.6f)
					.harvestTool(ToolType.SHOVEL).harvestLevel(0).sound(SoundType.NETHERRACK)), "tooltip.block.rigoranthusemortisreborn.fragmented_netherrack");

	public static final RegistryObject<Block> OPULENT_MAGMA = registerBlock("opulent_magma",
			() -> new MagmaBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.GOLD).lightLevel((p_235452_0_) -> { return 15; }).strength(8f, 10f)
					.harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().sound(SoundType.LANTERN)), "tooltip.block.rigoranthusemortisreborn.opulent_magma");

	public static final RegistryObject<Block> GOLD_AMALGAM = registerBlock("gold_amalgam",
			() -> new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).strength(10f, 15f)
					.harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops().sound(SoundType.STONE)), "tooltip.block.rigoranthusemortisreborn.gold_amalgam");

	public static final RegistryObject<Block> HIMALAYAN_SALT = registerBlock("himalayan_salt",
			() -> new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_PINK).strength(7f, 10f)
					.harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops().sound(SoundType.NETHER_ORE)), "tooltip.block.rigoranthusemortisreborn.himalayan_salt");

	public static final RegistryObject<Block> PINK_SALT = registerBlock("pink_salt",
			() -> new GravelBlock(AbstractBlock.Properties.of(Material.SAND, MaterialColor.COLOR_PINK).strength(0.6f, 0.6f)
					.harvestTool(ToolType.SHOVEL).harvestLevel(0).sound(SoundType.GRAVEL)), "tooltip.block.rigoranthusemortisreborn.pink_salt");

	public static final RegistryObject<Block> BLOCK_OF_ESOTERICUM = registerBlock("block_of_esotericum",
			() -> new GravelBlock(AbstractBlock.Properties.of(Material.SAND, MaterialColor.STONE).strength(1f, 1f)
					.harvestTool(ToolType.SHOVEL).harvestLevel(0).sound(SoundType.GRAVEL)), "tooltip.block.rigoranthusemortisreborn.block_of_esotericum");


    private static ToIntFunction<BlockState> litBlockEmission(int var0) {
		return (p_lambda$litBlockEmission$34_1_) -> {
			return (Boolean)p_lambda$litBlockEmission$34_1_.getValue(BlockStateProperties.LIT) ? var0 : 0;
		};
	}

    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block, String tooltipKey) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
  	        registerBlockItem(name, toReturn, tooltipKey);
        return toReturn;
    }

	private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block, String tooltipKey) {
        ItemInit.ITEMS.register(name, () -> new BlockItem(block.get(),
            	new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)) {
			@Override
			public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
				tooltip.add(new TranslationTextComponent(tooltipKey));
				super.appendHoverText(stack, worldIn, tooltip, flagIn);
			}
		});
    }
	public static final Map<Supplier<Block>, Supplier<Block>> FALLABLES = Util.make(Maps.newHashMap(), (fallables) -> {
		fallables.put(() -> Blocks.SANDSTONE, () -> Blocks.SAND);
		fallables.put(() -> Blocks.RED_SANDSTONE, () -> Blocks.RED_SAND);
		fallables.put(() -> Blocks.COBBLESTONE, () -> Blocks.GRAVEL);
	});

	public static final Map<Supplier<Block>, Supplier<Block>> ATMOSHPERIC_FALLABLES = ModList.get().isLoaded("atmospheric") ? Util.make(Maps.newHashMap(), (fallables) -> {
		fallables.put(() -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("atmospheric:arid_sandstone")), () -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("atmospheric:arid_sand")));
		fallables.put(() -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("atmospheric:red_arid_sandstone")), () -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("atmospheric:red_arid_sand")));
	}) : null;

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
    }
}
