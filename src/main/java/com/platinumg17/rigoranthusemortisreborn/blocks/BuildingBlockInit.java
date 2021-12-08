package com.platinumg17.rigoranthusemortisreborn.blocks;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.core.init.ItemInit;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class BuildingBlockInit {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, EmortisConstants.MOD_ID);

    public static final RegistryObject<Block> JESSIC_LAMP = registerBlock("jessic_lamp",
            () -> new RedstoneLampBlock(AbstractBlock.Properties.of(Material.BUILDABLE_GLASS).lightLevel(litBlockEmission(15)).strength(0.3F).sound(SoundType.SHROOMLIGHT)));

    public static final RegistryObject<Block> AZULOREAL_LAMP = registerBlock("azuloreal_lamp",
            () -> new RedstoneLampBlock(AbstractBlock.Properties.of(Material.BUILDABLE_GLASS).lightLevel(litBlockEmission(15)).strength(0.3F).sound(SoundType.SHROOMLIGHT)));

    public static final RegistryObject<Block> APOGEAN_NETHERITE_BLOCK = registerBlock("apogean_netherite_block",
            () -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.METAL).lightLevel((p_235452_0_) -> { return 5; }).strength(25f, 30f)
                    .harvestTool(ToolType.PICKAXE).harvestLevel(4).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static final RegistryObject<Block> AQUEOUS_NETHERITE_BLOCK = registerBlock("aqueous_netherite_block",
            () -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.METAL).lightLevel((p_235452_0_) -> { return 5; }).strength(25f, 30f)
                    .harvestTool(ToolType.PICKAXE).harvestLevel(4).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static final RegistryObject<Block> ATROPHYING_NETHERITE_BLOCK = registerBlock("atrophying_netherite_block",
            () -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.METAL).lightLevel((p_235452_0_) -> { return 5; }).strength(25f, 30f)
                    .harvestTool(ToolType.PICKAXE).harvestLevel(4).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static final RegistryObject<Block> OPULENT_NETHERITE_BLOCK = registerBlock("opulent_netherite_block",
            () -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.METAL).lightLevel((p_235452_0_) -> { return 5; }).strength(25f, 30f)
                    .harvestTool(ToolType.PICKAXE).harvestLevel(4).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static final RegistryObject<Block> PERNICIOUS_NETHERITE_BLOCK = registerBlock("pernicious_netherite_block",
            () -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.METAL).lightLevel((p_235452_0_) -> { return 5; }).strength(25f, 30f)
                    .harvestTool(ToolType.PICKAXE).harvestLevel(4).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static final RegistryObject<Block> PHANTASMAL_NETHERITE_BLOCK = registerBlock("phantasmal_netherite_block",
            () -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.METAL).lightLevel((p_235452_0_) -> { return 5; }).strength(25f, 30f)
                    .harvestTool(ToolType.PICKAXE).harvestLevel(4).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static final RegistryObject<Block> INCORPOREAL_NETHERITE_BLOCK = registerBlock("incorporeal_netherite_block",
            () -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.METAL).lightLevel((p_235452_0_) -> { return 5; }).strength(25f, 30f)
                    .harvestTool(ToolType.PICKAXE).harvestLevel(4).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static final RegistryObject<Block> INFERNAL_NETHERITE_BLOCK = registerBlock("infernal_netherite_block",
            () -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.METAL).lightLevel((p_235452_0_) -> { return 5; }).strength(25f, 30f)
                    .harvestTool(ToolType.PICKAXE).harvestLevel(4).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static final RegistryObject<Block> REMEX_NETHERITE_BLOCK = registerBlock("remex_netherite_block",
            () -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.METAL).lightLevel((p_235452_0_) -> { return 5; }).strength(25f, 30f)
                    .harvestTool(ToolType.PICKAXE).harvestLevel(4).requiresCorrectToolForDrops().sound(SoundType.STONE)));


    private static ToIntFunction<BlockState> litBlockEmission(int var0) {
        return (p_lambda$litBlockEmission$34_1_) -> {
            return (Boolean) p_lambda$litBlockEmission$34_1_.getValue(BlockStateProperties.LIT) ? var0 : 0;
        };
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ItemInit.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(RigoranthusEmortisReborn.RIGORANTHUS_EMORTIS_GROUP)) {
        });
    }
    public static void register(IEventBus modEventBus) {BLOCKS.register(modEventBus);}
}