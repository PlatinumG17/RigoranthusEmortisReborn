package com.platinumg17.rigoranthusemortisreborn.blocks;

import com.minecraftabnormals.abnormals_core.common.blocks.*;
import com.minecraftabnormals.abnormals_core.common.blocks.chest.AbnormalsChestBlock;
import com.minecraftabnormals.abnormals_core.common.blocks.chest.AbnormalsTrappedChestBlock;
import com.minecraftabnormals.abnormals_core.common.blocks.sign.AbnormalsStandingSignBlock;
import com.minecraftabnormals.abnormals_core.common.blocks.sign.AbnormalsWallSignBlock;
import com.minecraftabnormals.abnormals_core.common.blocks.wood.*;
import com.minecraftabnormals.abnormals_core.core.util.registry.BlockSubRegistryHelper;
import com.mojang.datafixers.util.Pair;
import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.BlockRegistry;
import com.platinumg17.rigoranthusemortisreborn.world.plants.VerdurousLeavesBlock;
import com.platinumg17.rigoranthusemortisreborn.world.trees.JessicTree;
import com.platinumg17.rigoranthusemortisreborn.world.trees.AzulorealTree;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EmortisConstants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DecorativeOrStorageBlocks {
    public static final BlockSubRegistryHelper HELPER = RigoranthusEmortisReborn.REGISTRY_HELPER.getBlockSubHelper();

    //public static final RegistryObject<Block> JESSIC_CRAFTING_TABLE = registerBlock("jessic_crafting_table", () -> new CraftingTableBlock(AbstractBlock.Properties.of(Material.DECORATION).sound(SoundType.WOOD).strength(2.0F, 3.0F).harvestTool(ToolType.AXE)));
    //public static final RegistryObject<SkullBlock> SUNDERED_CADAVER_HEAD = registerBlock("sundered_cadaver_head", () -> new SkullBlock(SkullBlock.Types.ZOMBIE, AbstractBlock.Properties.of(Material.DECORATION).strength(1.0F)));
    //public static final RegistryObject<WallSkullBlock> SUNDERED_CADAVER_WALL_HEAD = registerBlock("sundered_cadaver_wall_head", () -> new WallSkullBlock(SkullBlock.Types.ZOMBIE, AbstractBlock.Properties.of(Material.DECORATION).strength(1.0F).dropsLike(SKELETON_SKULL)));
    //public static final RegistryObject<BarrelBlock> JESSIC_BARREL = registerBlock("jessic_barrel", () -> new BarrelBlock(AbstractBlock.Properties.of(Material.WOOD).strength(2.5F).sound(SoundType.WOOD)));

    /////    AZULOREAL    /////

    public static final RegistryObject<Block> AZULOREAL_PLANKS = HELPER.createBlock("azuloreal_planks", ()->new PlanksBlock(Properties.PLANKS), ItemGroup.TAB_BUILDING_BLOCKS);
//    public static final RegistryObject<Block> AZULOREAL_SLAB = HELPER.createBlock("azuloreal_slab", ()->new WoodSlabBlock(Properties.PLANKS), ItemGroup.TAB_BUILDING_BLOCKS);
//    public static final RegistryObject<Block> AZULOREAL_STAIRS = HELPER.createBlock("azuloreal_stairs", ()->new WoodStairsBlock(AZULOREAL_PLANKS.get().defaultBlockState(), Properties.PLANKS), ItemGroup.TAB_BUILDING_BLOCKS);
//    public static final RegistryObject<Block> AZULOREAL_PRESSURE_PLATE = HELPER.createBlock("azuloreal_pressure_plate", ()->new WoodPressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Properties.PRESSURE_PLATE), ItemGroup.TAB_REDSTONE);
//    public static final RegistryObject<Block> AZULOREAL_FENCE = HELPER.createFuelBlock("azuloreal_fence", ()->new WoodFenceBlock(Properties.PLANKS), 300, ItemGroup.TAB_DECORATIONS);
//    public static final RegistryObject<Block> AZULOREAL_FENCE_GATE = HELPER.createFuelBlock("azuloreal_fence_gate", ()->new WoodFenceGateBlock(Properties.PLANKS), 300, ItemGroup.TAB_REDSTONE);
//    public static final RegistryObject<Block> AZULOREAL_BUTTON = HELPER.createBlock("azuloreal_button", ()->new AbnormalsWoodButtonBlock(Properties.BUTTON), ItemGroup.TAB_REDSTONE);
//    public static final RegistryObject<Block> AZULOREAL_DOOR = HELPER.createBlock("azuloreal_door", ()->new WoodDoorBlock(Properties.DOOR), ItemGroup.TAB_REDSTONE);
//    public static final RegistryObject<Block> AZULOREAL_TRAPDOOR = HELPER.createBlock("azuloreal_trapdoor", ()->new WoodTrapDoorBlock(Properties.DOOR), ItemGroup.TAB_REDSTONE);
    public static final Pair<RegistryObject<AbnormalsStandingSignBlock>, RegistryObject<AbnormalsWallSignBlock>> AZULOREAL_SIGNS = HELPER.createSignBlock("azuloreal", MaterialColor.COLOR_CYAN);

    public static final RegistryObject<Block> AZULOREAL_BOOKSHELF = HELPER.createCompatFuelBlock("quark", "azuloreal_bookshelf", () -> new BookshelfBlock(Properties.BOOKSHELF), 300, ItemGroup.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> VERTICAL_AZULOREAL_PLANKS = HELPER.createCompatBlock("quark", "vertical_azuloreal_planks", () -> new Block(Properties.PLANKS), ItemGroup.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> VERTICAL_AZULOREAL_SLAB = HELPER.createCompatFuelBlock("quark", "vertical_azuloreal_slab", () -> new VerticalSlabBlock(Properties.PLANKS), 150, ItemGroup.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> AZULOREAL_LADDER = HELPER.createCompatFuelBlock("quark", "azuloreal_ladder", () -> new AbnormalsLadderBlock(Properties.LADDER), 300, ItemGroup.TAB_DECORATIONS);
    public static final RegistryObject<Block> AZULOREAL_LEAF_CARPET = HELPER.createCompatBlock("quark", "azuloreal_leaf_carpet", () -> new LeafCarpetBlock(Properties.AZULOREAL_CARPET), ItemGroup.TAB_DECORATIONS);
    public static final RegistryObject<Block> STRIPPED_AZULOREAL_POST = HELPER.createCompatFuelBlock("quark", "stripped_azuloreal_post", () -> new WoodPostBlock(Properties.PLANKS), 300, ItemGroup.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> AZULOREAL_POST = HELPER.createCompatFuelBlock("quark", "azuloreal_post", () -> new WoodPostBlock(STRIPPED_AZULOREAL_POST, Properties.PLANKS), 300, ItemGroup.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> AZULOREAL_HEDGE = HELPER.createCompatFuelBlock("quark", "azuloreal_hedge", () -> new HedgeBlock(Properties.HEDGE), 300, ItemGroup.TAB_DECORATIONS);
    public static final Pair<RegistryObject<AbnormalsChestBlock>, RegistryObject<AbnormalsTrappedChestBlock>> AZULOREAL_CHESTS = HELPER.createCompatChestBlocks("quark", "azuloreal", MaterialColor.COLOR_CYAN);

    /////    JESSIC    /////

    public static final RegistryObject<Block> JESSIC_PLANKS = HELPER.createBlock("jessic_planks", ()->new PlanksBlock(Properties.PLANKS), ItemGroup.TAB_BUILDING_BLOCKS);
//    public static final RegistryObject<Block> JESSIC_SLAB = HELPER.createBlock("jessic_slab", ()->new WoodSlabBlock(Properties.PLANKS), ItemGroup.TAB_BUILDING_BLOCKS);
//    public static final RegistryObject<Block> JESSIC_STAIRS = HELPER.createBlock("jessic_stairs", ()->new WoodStairsBlock(JESSIC_PLANKS.get().defaultBlockState(), Properties.PLANKS), ItemGroup.TAB_BUILDING_BLOCKS);
//    public static final RegistryObject<Block> JESSIC_PRESSURE_PLATE = HELPER.createBlock("jessic_pressure_plate", ()->new WoodPressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Properties.PRESSURE_PLATE), ItemGroup.TAB_REDSTONE);
//    public static final RegistryObject<Block> JESSIC_FENCE = HELPER.createFuelBlock("jessic_fence", ()->new WoodFenceBlock(Properties.PLANKS), 300, ItemGroup.TAB_DECORATIONS);
//    public static final RegistryObject<Block> JESSIC_FENCE_GATE = HELPER.createFuelBlock("jessic_fence_gate", ()->new WoodFenceGateBlock(Properties.PLANKS), 300, ItemGroup.TAB_REDSTONE);
//    public static final RegistryObject<Block> JESSIC_BUTTON = HELPER.createBlock("jessic_button", ()->new AbnormalsWoodButtonBlock(Properties.BUTTON), ItemGroup.TAB_REDSTONE);
//    public static final RegistryObject<Block> JESSIC_DOOR = HELPER.createBlock("jessic_door", ()->new WoodDoorBlock(Properties.DOOR), ItemGroup.TAB_REDSTONE);
//    public static final RegistryObject<Block> JESSIC_TRAPDOOR = HELPER.createBlock("jessic_trapdoor", ()->new WoodTrapDoorBlock(Properties.DOOR), ItemGroup.TAB_REDSTONE);
    public static final Pair<RegistryObject<AbnormalsStandingSignBlock>, RegistryObject<AbnormalsWallSignBlock>> JESSIC_SIGNS = HELPER.createSignBlock("jessic", MaterialColor.TERRACOTTA_MAGENTA);

    public static final RegistryObject<Block> JESSIC_BOOKSHELF = HELPER.createCompatFuelBlock("quark", "jessic_bookshelf", () -> new BookshelfBlock(Properties.BOOKSHELF), 300, ItemGroup.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> VERTICAL_JESSIC_PLANKS = HELPER.createCompatBlock("quark", "vertical_jessic_planks", () -> new Block(Properties.PLANKS), ItemGroup.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> VERTICAL_JESSIC_SLAB = HELPER.createCompatFuelBlock("quark", "vertical_jessic_slab", () -> new VerticalSlabBlock(Properties.PLANKS), 150, ItemGroup.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> JESSIC_LADDER = HELPER.createCompatFuelBlock("quark", "jessic_ladder", () -> new AbnormalsLadderBlock(Properties.LADDER), 300, ItemGroup.TAB_DECORATIONS);
    public static final RegistryObject<Block> JESSIC_LEAF_CARPET = HELPER.createCompatBlock("quark", "jessic_leaf_carpet", () -> new LeafCarpetBlock(Properties.JESSIC_CARPET), ItemGroup.TAB_DECORATIONS);
    public static final RegistryObject<Block> STRIPPED_JESSIC_POST = HELPER.createCompatFuelBlock("quark", "stripped_jessic_post", () -> new WoodPostBlock(Properties.PLANKS), 300, ItemGroup.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> JESSIC_POST = HELPER.createCompatFuelBlock("quark", "jessic_post", () -> new WoodPostBlock(STRIPPED_JESSIC_POST, Properties.PLANKS), 300, ItemGroup.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> JESSIC_HEDGE = HELPER.createCompatFuelBlock("quark", "jessic_hedge", () -> new HedgeBlock(Properties.HEDGE), 300, ItemGroup.TAB_DECORATIONS);
    public static final Pair<RegistryObject<AbnormalsChestBlock>, RegistryObject<AbnormalsTrappedChestBlock>> JESSIC_CHESTS = HELPER.createCompatChestBlocks("quark", "jessic", MaterialColor.TERRACOTTA_MAGENTA);

    public static class Properties {
        public static final AbstractBlock.Properties JESSIC_LEAVES = AbstractBlock.Properties.of(Material.LEAVES, MaterialColor.COLOR_PURPLE).harvestTool(ToolType.HOE).noOcclusion().lightLevel((p_235455_0_) -> 10).strength(0.2F).randomTicks().sound(SoundType.GRASS).isValidSpawn(DecorativeOrStorageBlocks.Properties::always).isSuffocating(DecorativeOrStorageBlocks.Properties::never).isViewBlocking(DecorativeOrStorageBlocks.Properties::never);
        public static final AbstractBlock.Properties JESSIC_CARPET = AbstractBlock.Properties.of(Material.CLOTH_DECORATION, MaterialColor.COLOR_PURPLE).strength(0.0F).sound(SoundType.GRASS).harvestTool(ToolType.HOE).noOcclusion();
        public static final AbstractBlock.Properties AZULOREAL_LEAVES = AbstractBlock.Properties.of(Material.LEAVES, MaterialColor.SNOW).harvestTool(ToolType.HOE).noOcclusion().lightLevel((p_235455_0_) -> 10).strength(0.2F).randomTicks().sound(SoundType.GRASS).isValidSpawn(DecorativeOrStorageBlocks.Properties::always).isSuffocating(DecorativeOrStorageBlocks.Properties::never).isViewBlocking(DecorativeOrStorageBlocks.Properties::never);
        public static final AbstractBlock.Properties AZULOREAL_CARPET = AbstractBlock.Properties.of(Material.CLOTH_DECORATION, MaterialColor.SNOW).strength(0.0F).sound(SoundType.GRASS).harvestTool(ToolType.HOE).noOcclusion();
        public static final AbstractBlock.Properties HEDGE = AbstractBlock.Properties.copy(Blocks.OAK_FENCE);
        public static final AbstractBlock.Properties PLANKS = AbstractBlock.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD);
        public static final AbstractBlock.Properties DOOR = AbstractBlock.Properties.of(Material.WOOD, MaterialColor.WOOD).noOcclusion().strength(3.0F).sound(SoundType.WOOD);
        public static final AbstractBlock.Properties BUTTON = AbstractBlock.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD);
        public static final AbstractBlock.Properties PRESSURE_PLATE = AbstractBlock.Properties.of(Material.WOOD).noCollission().strength(0.5F).sound(SoundType.WOOD);
        public static final AbstractBlock.Properties LADDER = AbstractBlock.Properties.of(Material.DECORATION).noOcclusion().harvestTool(ToolType.AXE).strength(0.4F).sound(SoundType.LADDER);
        public static final AbstractBlock.Properties BOOKSHELF = AbstractBlock.Properties.of(Material.WOOD).strength(1.5F).sound(SoundType.WOOD);
        public static final AbstractBlock.Properties LOG = AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN).strength(2.0F).sound(SoundType.WOOD);
        public static final AbstractBlock.Properties SAPLING = AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().strength(0.0F).sound(SoundType.GRASS);
        public static final AbstractBlock.Properties FLOWER_POT = AbstractBlock.Properties.of(Material.DECORATION).strength(0.0F).noOcclusion();

        public static boolean ocelotOrParrot(BlockState state, IBlockReader access, BlockPos pos, EntityType<?> entity) {return entity == EntityType.OCELOT || entity == EntityType.PARROT;}
        public static boolean always(BlockState state, IBlockReader reader, BlockPos pos, EntityType<?> entity) {return true;}
        public static boolean hasPostProcess(BlockState state, IBlockReader reader, BlockPos pos) {
            return true;
        }
        public static boolean never(BlockState state, IBlockReader reader, BlockPos pos) {
            return false;
        }
    }
}




//    public static final RegistryObject<Block> FLOWERING_JESSIC_LOG= HELPER.createBlock("flowering_jessic_log", ()->new FloweringLogBlock(JESSIC_LOG, STRIPPED_JESSIC_LOG, Properties.LOG), ItemGroup.TAB_BUILDING_BLOCKS);
//    public static final RegistryObject<Block> FLOWERING_JESSIC_WOOD= HELPER.createBlock("flowering_jessic_wood", ()->new FloweringWoodBlock(JESSIC_WOOD, STRIPPED_JESSIC_WOOD, Properties.LOG), ItemGroup.TAB_BUILDING_BLOCKS);
//    public static final RegistryObject<Block> FLOWERING_JESSIC_LEAVES = HELPER.createBlock("flowering_jessic_leaves", ()->new AbnormalsLeavesBlock(Properties.JESSIC_LEAVES), ItemGroup.TAB_DECORATIONS);
//    public static final RegistryObject<Block> FLOWERING_JESSIC_LEAF_CARPET = HELPER.createCompatBlock("quark", "flowering_jessic_leaf_carpet", ()->new LeafCarpetBlock(Properties.JESSIC_CARPET), ItemGroup.TAB_DECORATIONS);
//    public static final RegistryObject<Block> BUDDING_JESSIC_LEAVES = HELPER.createBlock("budding_jessic_leaves", ()->new AbnormalsLeavesBlock(Properties.JESSIC_LEAVES), ItemGroup.TAB_DECORATIONS);
//    public static final RegistryObject<Block> BUDDING_JESSIC_LEAF_CARPET = HELPER.createCompatBlock("quark", "budding_jessic_leaf_carpet", ()->new LeafCarpetBlock(Properties.JESSIC_CARPET), ItemGroup.TAB_DECORATIONS);
//    public static final RegistryObject<Block> BUDDING_JESSIC_HEDGE = HELPER.createCompatFuelBlock("quark", "budding_jessic_hedge", () -> new HedgeBlock(Properties.HEDGE), 300, ItemGroup.TAB_DECORATIONS);
//    public static final RegistryObject<Block> FLOWERING_JESSIC_HEDGE = HELPER.createCompatFuelBlock("quark", "flowering_jessic_hedge", () -> new HedgeBlock(Properties.HEDGE), 300, ItemGroup.TAB_DECORATIONS);
//    public static final RegistryObject<Block> FLOWERING_JESSIC_SAPLING = HELPER.createBlock("flowering_jessic_sapling", ()->new AbnormalsSaplingBlock(new FloweringJessicTree(), Properties.SAPLING), ItemGroup.TAB_DECORATIONS);
//    public static final RegistryObject<Block> POTTED_FLOWERING_JESSIC_SAPLING = HELPER.createBlockNoItem("potted_flowering_jessic_sapling", ()->new FlowerPotBlock(FLOWERING_JESSIC_SAPLING.get(), Properties.FLOWER_POT));
//    public static final RegistryObject<Block> JESSIC_BLOSSOM_CARPET = HELPER.createBlock("jessic_blossom_carpet", ()->new BlossomCarpetBlock(AbstractBlock.Properties.of(Material.CARPET, MaterialColor.COLOR_PURPLE).notSolid().hardnessAndResistance(0.0f).tickRandomly().sound(SoundType.GRASS).harvestTool(ToolType.HOE)), ItemGroup.TAB_DECORATIONS);