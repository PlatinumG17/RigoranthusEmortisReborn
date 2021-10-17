package com.platinumg17.rigoranthusemortisreborn.core.init;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.blocks.trees.AzulorealTree;
import com.platinumg17.rigoranthusemortisreborn.blocks.trees.JessicTree;
import com.platinumg17.rigoranthusemortisreborn.config.Config;
import com.platinumg17.rigoranthusemortisreborn.core.registry.RigoranthusItemGroup;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.potion.Effects;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.block.trees.OakTree;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.fml.common.Mod;
import com.minecraftabnormals.abnormals_core.common.blocks.*;
import com.minecraftabnormals.abnormals_core.common.blocks.chest.AbnormalsChestBlock;
import com.minecraftabnormals.abnormals_core.common.blocks.chest.AbnormalsTrappedChestBlock;
import com.minecraftabnormals.abnormals_core.common.blocks.sign.AbnormalsStandingSignBlock;
import com.minecraftabnormals.abnormals_core.common.blocks.sign.AbnormalsWallSignBlock;
import com.minecraftabnormals.abnormals_core.common.blocks.wood.*;
import com.minecraftabnormals.abnormals_core.core.util.registry.BlockSubRegistryHelper;
import com.mojang.datafixers.util.Pair;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class BuildingBlockInit {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, RigoranthusEmortisReborn.MOD_ID);

//    public static final RegistryObject<Block> JESSIC_LOG = registerBlock("jessic_log",
//            () -> new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F).harvestTool(ToolType.AXE)));
//
//    public static final RegistryObject<Block> JESSIC_WOOD = registerBlock("jessic_wood",
//            () -> new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F).harvestTool(ToolType.AXE)));
//
//    public static final RegistryObject<Block> STRIPPED_JESSIC_LOG = registerBlock("stripped_jessic_log",
//            () -> new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F).harvestTool(ToolType.AXE)));
//
//    public static final RegistryObject<Block> STRIPPED_JESSIC_WOOD = registerBlock("stripped_jessic_wood",
//            () -> new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F).harvestTool(ToolType.AXE)));
//
//    public static final RegistryObject<Block> JESSIC_PLANKS = registerBlock("jessic_planks",
//            () -> new Block(AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F).harvestTool(ToolType.AXE)));
//
//    public static final RegistryObject<Block> JESSIC_STAIRS = registerBlock("jessic_stairs",
//            () -> new StairsBlock(() -> JESSIC_PLANKS.get().defaultBlockState(),
//                    AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F).harvestTool(ToolType.AXE)));
//
//    public static final RegistryObject<Block> JESSIC_FENCE = registerBlock("jessic_fence",
//            () -> new FenceBlock(AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F).harvestTool(ToolType.AXE)));
//
//    public static final RegistryObject<Block> JESSIC_FENCE_GATE = registerBlock("jessic_fence_gate",
//            () -> new FenceGateBlock(AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F).harvestTool(ToolType.AXE)));
//
//    public static final RegistryObject<Block> JESSIC_SLAB = registerBlock("jessic_slab",
//            () -> new SlabBlock(AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F).harvestTool(ToolType.AXE)));
//
//    public static final RegistryObject<Block> JESSIC_BUTTON = registerBlock("jessic_button",
//            () -> new WoodButtonBlock(AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F).harvestTool(ToolType.AXE).noCollission()));
//
//    public static final RegistryObject<Block> JESSIC_PRESSURE_PLATE = registerBlock("jessic_pressure_plate",
//            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
//                    AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F).harvestTool(ToolType.AXE)));

    public static final RegistryObject<Block> JESSIC_LAMP = registerBlock("jessic_lamp",
            () -> new RedstoneLampBlock(AbstractBlock.Properties.of(Material.BUILDABLE_GLASS).lightLevel(litBlockEmission(15)).strength(0.3F).sound(SoundType.SHROOMLIGHT)));
//
//    public static final RegistryObject<Block> JESSIC_DOOR = registerBlock("jessic_door",
//            () -> new DoorBlock(AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F).harvestTool(ToolType.AXE).noOcclusion()));
//
//    public static final RegistryObject<Block> JESSIC_TRAPDOOR = registerBlock("jessic_trapdoor",
//            () -> new TrapDoorBlock(AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F).harvestTool(ToolType.AXE).noOcclusion()));
//
//    public static final RegistryObject<Block> JESSIC_LADDER = registerBlock("jessic_ladder",
//            () -> new LadderBlock(AbstractBlock.Properties.of(Material.DECORATION).sound(SoundType.LADDER).strength(0.4F).harvestTool(ToolType.AXE).noOcclusion()));
//
//    public static final RegistryObject<Block> JESSIC_LEAVES = registerBlock("jessic_leaves",
//            () -> new LeavesBlock(AbstractBlock.Properties.of(Material.LEAVES).sound(SoundType.GRASS).strength(0.2F).randomTicks().noOcclusion().lightLevel((p_235455_0_) -> {return 10;})));
//
//    public static final RegistryObject<Block> JESSIC_SAPLING = registerBlock("jessic_sapling",
//            () -> new SaplingBlock(new JessicTree(), AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
//
//    public static final RegistryObject<Block> AZULOREAL_LOG = registerBlock("azuloreal_log",
//            () -> new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F).harvestTool(ToolType.AXE)));
//
//    public static final RegistryObject<Block> AZULOREAL_WOOD = registerBlock("azuloreal_wood",
//            () -> new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F).harvestTool(ToolType.AXE)));
//
//    public static final RegistryObject<Block> STRIPPED_AZULOREAL_LOG = registerBlock("stripped_azuloreal_log",
//            () -> new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F).harvestTool(ToolType.AXE)));
//
//    public static final RegistryObject<Block> STRIPPED_AZULOREAL_WOOD = registerBlock("stripped_azuloreal_wood",
//            () -> new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F).harvestTool(ToolType.AXE)));
//
//    public static final RegistryObject<Block> AZULOREAL_PLANKS = registerBlock("azuloreal_planks",
//            () -> new Block(AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F).harvestTool(ToolType.AXE)));
//
//    public static final RegistryObject<Block> AZULOREAL_STAIRS = registerBlock("azuloreal_stairs",
//            () -> new StairsBlock(() -> AZULOREAL_PLANKS.get().defaultBlockState(),
//                    AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F).harvestTool(ToolType.AXE)));
//
//    public static final RegistryObject<Block> AZULOREAL_FENCE = registerBlock("azuloreal_fence",
//            () -> new FenceBlock(AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F).harvestTool(ToolType.AXE)));
//
//    public static final RegistryObject<Block> AZULOREAL_FENCE_GATE = registerBlock("azuloreal_fence_gate",
//            () -> new FenceGateBlock(AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F).harvestTool(ToolType.AXE)));
//
//    public static final RegistryObject<Block> AZULOREAL_SLAB = registerBlock("azuloreal_slab",
//            () -> new SlabBlock(AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F).harvestTool(ToolType.AXE)));
//
//    public static final RegistryObject<Block> AZULOREAL_BUTTON = registerBlock("azuloreal_button",
//            () -> new WoodButtonBlock(AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F).harvestTool(ToolType.AXE).noCollission()));
//
//    public static final RegistryObject<Block> AZULOREAL_PRESSURE_PLATE = registerBlock("azuloreal_pressure_plate",
//            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
//                    AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F).harvestTool(ToolType.AXE)));

    public static final RegistryObject<Block> AZULOREAL_LAMP = registerBlock("azuloreal_lamp",
            () -> new RedstoneLampBlock(AbstractBlock.Properties.of(Material.BUILDABLE_GLASS).lightLevel(litBlockEmission(15)).strength(0.3F).sound(SoundType.SHROOMLIGHT)));
//
//    public static final RegistryObject<Block> AZULOREAL_DOOR = registerBlock("azuloreal_door",
//            () -> new DoorBlock(AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F).harvestTool(ToolType.AXE).noOcclusion()));
//
//    public static final RegistryObject<Block> AZULOREAL_TRAPDOOR = registerBlock("azuloreal_trapdoor",
//            () -> new TrapDoorBlock(AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F).harvestTool(ToolType.AXE).noOcclusion()));
//
//    public static final RegistryObject<Block> AZULOREAL_LADDER = registerBlock("azuloreal_ladder",
//            () -> new LadderBlock(AbstractBlock.Properties.of(Material.DECORATION).sound(SoundType.LADDER).strength(0.4F).harvestTool(ToolType.AXE).noOcclusion()));
//
//    public static final RegistryObject<Block> AZULOREAL_LEAVES = registerBlock("azuloreal_leaves",
//            () -> new LeavesBlock(AbstractBlock.Properties.of(Material.LEAVES).sound(SoundType.GRASS).strength(0.2F).randomTicks().noOcclusion().lightLevel((p_235455_0_) -> {
//                return 12;
//            })));
//    //public static final RegistryObject<Block> AZULOREAL_LEAVES = registerBlock("azuloreal_leaves", leaves());
//    public static final RegistryObject<Block> AZULOREAL_SAPLING = registerBlock("azuloreal_sapling",
//            () -> new SaplingBlock(new AzulorealTree(), AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
//
//    public static final RegistryObject<Block> AZULOREAL_ORCHID = registerBlock("azuloreal_orchid",
//            () -> new FlowerBlock(Effects.HEAL, 9, AbstractBlock.Properties.of(Material.PLANT).sound(SoundType.GRASS).noCollission().randomTicks().instabreak().noOcclusion()));
//
//    public static final RegistryObject<Block> IRIDESCENT_SPROUTS = registerBlock("iridescent_sprouts",
//            () -> new FlowerBlock(Effects.NIGHT_VISION, 10, AbstractBlock.Properties.of(Material.PLANT).sound(SoundType.GRASS).lightLevel((p_235452_0_) -> { return 5; }).noCollission().randomTicks().instabreak().noOcclusion()));
//
    public static final RegistryObject<Block> LISIANTHUS = registerBlock("lisianthus",
            () -> new TallFlowerBlock(AbstractBlock.Properties.of(Material.REPLACEABLE_PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));

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
                new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)) {
        });
    }

    public static void register(IEventBus bus) {
        if (Config.enableNewWoodTypes.get()) {
            BLOCKS.register(bus);
        }
    }
}