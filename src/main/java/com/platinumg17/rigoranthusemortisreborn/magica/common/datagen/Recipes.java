package com.platinumg17.rigoranthusemortisreborn.magica.common.datagen;

import com.platinumg17.rigoranthusemortisreborn.blocks.BlockInit;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.magica.common.lib.LibBlockNames;
import com.platinumg17.rigoranthusemortisreborn.magica.common.lib.RitualLib;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.BlockRegistry;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.MagicItemsRegistry;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.block.Block;
import net.minecraft.data.*;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Consumer;

public class Recipes extends RecipeProvider {
    public Recipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

    public static ITag.INamedTag<Item> DOMINION_GEM_TAG = ItemTags.bind("forge:gems/mana");
    public static ITag.INamedTag<Item> DOMINION_GEM_BLOCK_TAG = ItemTags.bind("forge:storage_blocks/mana");
    public static ITag.INamedTag<Item> ARCHWOOD_LOG_TAG = ItemTags.bind("forge:logs/archwood");
    public static ITag.INamedTag<Block> DECORATIVE_AN =  BlockTags.createOptional(new ResourceLocation(EmortisConstants.MOD_ID, "an_decorative"));
    public static ITag.INamedTag<Block> MAGIC_SAPLINGS =  BlockTags.createOptional(new ResourceLocation(EmortisConstants.MOD_ID, "magic_saplings"));
    public static ITag.INamedTag<Block> MAGIC_PLANTS =  BlockTags.createOptional(new ResourceLocation(EmortisConstants.MOD_ID, "magic_plants"));
    public static ITag.INamedTag<Item> MAGIC_FOOD = ItemTags.bind("rigoranthusemortisreborn:magic_food");

    public static Ingredient DOMINION_GEM = Ingredient.of(DOMINION_GEM_TAG);
    public static Ingredient DOMINION_GEM_BLOCK = Ingredient.of(DOMINION_GEM_BLOCK_TAG);
    public static Ingredient ARCHWOOD_LOG = Ingredient.of(ARCHWOOD_LOG_TAG);
    @Override
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
        {
            makeArmor("novice", consumer, MagicItemsRegistry.MAGE_FIBER);
            makeArmor("apprentice", consumer, MagicItemsRegistry.BLAZE_FIBER);
            makeArmor("archmage", consumer, MagicItemsRegistry.END_FIBER);

//            CookingRecipeBuilder.smelting(Ingredient.of(BlockRegistry.ARCANE_ORE), MagicItemsRegistry.manaGem,0.5f, 200)
//                    .unlockedBy("has_ore", InventoryChangeTrigger.Instance.hasItems(BlockRegistry.ARCANE_ORE)).save(consumer);

            ShapelessRecipeBuilder.shapeless(MagicItemsRegistry.wornNotebook).unlockedBy("has_journal", InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.wornNotebook))
                    .requires(DOMINION_GEM, 1)
                    .requires(Items.BOOK).save(consumer);


//            ShapelessRecipeBuilder.shapeless(MagicItemsRegistry.magicClay).unlockedBy("has_journal", InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.wornNotebook))
//                    .requires(Items.CLAY_BALL)
//                    .requires(DOMINION_GEM, 1)
//                    .requires(Items.REDSTONE, 2)
//                    .save(consumer);
//            ShapelessRecipeBuilder.shapeless(MagicItemsRegistry.marvelousClay).unlockedBy("has_journal", InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.wornNotebook))
//                    .requires(MagicItemsRegistry.magicClay)
//                    .requires(Tags.Items.INGOTS_GOLD)
//                    .requires(DOMINION_GEM, 1)
//                    .requires(Items.LAPIS_LAZULI, 2)
//                    .save(consumer);
//
//            ShapelessRecipeBuilder.shapeless(MagicItemsRegistry.mythicalClay).unlockedBy("has_journal", InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.wornNotebook))
//                    .requires(MagicItemsRegistry.marvelousClay)
//                    .requires(Items.DIAMOND, 2)
//                    .requires(Items.BLAZE_POWDER, 2)
//                    .save(consumer);
//
//            ShapelessRecipeBuilder.shapeless(MagicItemsRegistry.MAGE_FIBER, 4).unlockedBy("has_journal", InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.wornNotebook))
//                    .requires(MagicItemsRegistry.MAGE_BLOOM)
//                    .save(consumer);

//            ShapelessRecipeBuilder.shapeless(MagicItemsRegistry.runicChalk, 1).unlockedBy("has_journal", InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.wornNotebook))
//                    .requires(MagicItemsRegistry.magicClay).requires(Items.BONE_MEAL).requires(MagicItemsRegistry.MAGE_FIBER)
//                    .save(consumer);

//            ShapelessRecipeBuilder.shapeless(MagicItemsRegistry.BLAZE_FIBER, 2).unlockedBy("has_journal", InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.wornNotebook))
//                    .requires(MagicItemsRegistry.MAGE_FIBER, 2)
//                    .requires(Items.BLAZE_POWDER)
//                    .save(consumer);
//
//            ShapelessRecipeBuilder.shapeless(MagicItemsRegistry.END_FIBER, 2).unlockedBy("has_journal", InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.wornNotebook))
//                    .requires(MagicItemsRegistry.BLAZE_FIBER, 2)
//                    .requires(Items.POPPED_CHORUS_FRUIT)
//                    .save(consumer);

            ShapedRecipeBuilder.shaped(BlockRegistry.DOMINION_JAR).unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.wornNotebook))
                    .pattern("xyx")
                    .pattern("x x")
                    .pattern("xxx").define('x', Tags.Items.GLASS).define('y', BlockInit.OPULENT_MAGMA.get()).save(consumer);

//            ShapedRecipeBuilder.shaped(BlockRegistry.GLYPH_PRESS_BLOCK).unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.wornNotebook))
//                    .pattern("xxx")
//                    .pattern("xyx")
//                    .pattern("aba").define('x', BlockRegistry.ARCANE_STONE).define('y', Items.PISTON)
//                    .define('a', Tags.Items.STONE).define('b', Tags.Items.STORAGE_BLOCKS_IRON).save(consumer);
//
//            ShapedRecipeBuilder.shaped(BlockRegistry.ARCANE_PEDESTAL).unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.wornNotebook))
//                    .pattern("xxx")
//                    .pattern(" x ")
//                    .pattern("xxx").define('x', BlockRegistry.ARCANE_STONE).save(consumer);

//            ShapedRecipeBuilder.shaped(BlockRegistry.ENCHANTING_APP_BLOCK).unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.wornNotebook))
//                    .pattern("xyx")
//                    .pattern("x x")
//                    .pattern("zzz").define('x', Tags.Items.INGOTS_IRON)
//                    .define('y', Tags.Items.GEMS_DIAMOND)
//                    .define('z', BlockRegistry.ARCANE_STONE).save(consumer);

            ShapedRecipeBuilder.shaped(MagicItemsRegistry.mundaneBelt).unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.wornNotebook))
                    .pattern("   ")
                    .pattern("xyx")
                    .pattern(" x ")
                    .define('x', Tags.Items.LEATHER)
                    .define('y', DOMINION_GEM).save(consumer);

            ShapedRecipeBuilder.shaped(MagicItemsRegistry.ringOfPotential).unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.wornNotebook))
                    .pattern("xxx")
                    .pattern("xyx")
                    .pattern("xxx").define('x', Tags.Items.NUGGETS_IRON).define('y', DOMINION_GEM).save(consumer);


//            ShapelessRecipeBuilder.shapeless(BlockRegistry.WARD_BLOCK, 2).unlockedBy("has_journal",
//                            InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.wornNotebook))
//                    .requires(BlockRegistry.ARCANE_STONE, 9)
//                    .save(consumer);

//            ShapedRecipeBuilder.shaped(BlockRegistry.ARCANE_BRICKS, 4).unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.wornNotebook))
//                    .pattern("xx ")
//                    .pattern("xx ")
//                    .pattern("   ").define('x', BlockRegistry.ARCANE_STONE).save(consumer);


//            ShapedRecipeBuilder.shaped(BlockRegistry.SCRIBES_BLOCK).unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.wornNotebook))
//                    .pattern("xxx")
//                    .pattern("yzy")
//                    .pattern("y y").define('x',Ingredient.of(BlockRegistry.ARCHWOOD_SLABS))
//                    .define('y', Items.STICK)
//                    .define('z', Ingredient.of(ARCHWOOD_LOG_TAG)).save(consumer);

//            ShapedRecipeBuilder.shaped(MagicItemsRegistry.dullTrinket).unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.wornNotebook))
//                    .pattern(" x ")
//                    .pattern("xyx")
//                    .pattern(" x ").define('x',  Tags.Items.NUGGETS_IRON).define('y',DOMINION_GEM).save(consumer);

//            ShapedRecipeBuilder.shaped(BlockRegistry.ARCANE_CORE_BLOCK).unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.wornNotebook))
//                    .pattern("xxx")
//                    .pattern("y y")
//                    .pattern("xxx").define('y',  Tags.Items.INGOTS_GOLD).define('x', BlockRegistry.ARCANE_STONE).save(consumer);
//
//
//            ShapedRecipeBuilder.shaped(BlockRegistry.ARCANE_STONE, 8).unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.wornNotebook))
//                    .pattern("xxx")
//                    .pattern("xyx")
//                    .pattern("xxx").define('y',DOMINION_GEM).define('x',  Tags.Items.STONE).save(consumer);
//
//            ShapedRecipeBuilder.shaped(BlockRegistry.CRYSTALLIZER_BLOCK.asItem(), 1).unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.wornNotebook))
//                    .pattern("yxy")
//                    .pattern("yzy")
//                    .pattern("yxy").define('x', BlockRegistry.ARCANE_STONE)
//                    .define('y', Tags.Items.INGOTS_GOLD)
//                    .define('z', DOMINION_GEM).save(consumer);

//            makeStonecutter(consumer, BlockRegistry.ARCANE_STONE, BlockRegistry.AB_ALTERNATE, LibBlockNames.ARCANE_STONE);
//            makeStonecutter(consumer, BlockRegistry.ARCANE_STONE, BlockRegistry.ARCANE_BRICKS, LibBlockNames.ARCANE_STONE);
//            makeStonecutter(consumer, BlockRegistry.ARCANE_STONE, BlockRegistry.AB_HERRING, LibBlockNames.ARCANE_STONE);
//            makeStonecutter(consumer, BlockRegistry.ARCANE_STONE, BlockRegistry.AB_BASKET, LibBlockNames.ARCANE_STONE);
//            makeStonecutter(consumer, BlockRegistry.ARCANE_STONE, BlockRegistry.AB_MOSAIC, LibBlockNames.ARCANE_STONE);
//            makeStonecutter(consumer, BlockRegistry.ARCANE_STONE, BlockRegistry.AB_CLOVER, LibBlockNames.ARCANE_STONE);
//            makeStonecutter(consumer, BlockRegistry.ARCANE_STONE, BlockRegistry.AB_SMOOTH, LibBlockNames.ARCANE_STONE);
//            makeStonecutter(consumer, BlockRegistry.ARCANE_STONE, BlockRegistry.AB_SMOOTH_SLAB, LibBlockNames.ARCANE_STONE);
//
//            makeStonecutter(consumer, BlockRegistry.ARCANE_STONE, BlockRegistry.AB_SMOOTH_BASKET, LibBlockNames.ARCANE_STONE);
//            makeStonecutter(consumer, BlockRegistry.ARCANE_STONE, BlockRegistry.AB_SMOOTH_CLOVER, LibBlockNames.ARCANE_STONE);
//            makeStonecutter(consumer, BlockRegistry.ARCANE_STONE, BlockRegistry.AB_SMOOTH_HERRING, LibBlockNames.ARCANE_STONE);
//            makeStonecutter(consumer, BlockRegistry.ARCANE_STONE, BlockRegistry.AB_SMOOTH_MOSAIC, LibBlockNames.ARCANE_STONE);
//            makeStonecutter(consumer, BlockRegistry.ARCANE_STONE, BlockRegistry.AB_SMOOTH_ALTERNATING, LibBlockNames.ARCANE_STONE);
//            makeStonecutter(consumer, BlockRegistry.ARCANE_STONE, BlockRegistry.AB_SMOOTH_ASHLAR, LibBlockNames.ARCANE_STONE);

            ShapedRecipeBuilder.shaped(MagicItemsRegistry.BLANK_PARCHMENT, 1).unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.wornNotebook))
                    .pattern("yyy")
                    .pattern("yxy")
                    .pattern("yyy").define('x', Items.PAPER).define('y', MagicItemsRegistry.MAGE_FIBER).save(consumer);

            ShapelessRecipeBuilder.shapeless(MagicItemsRegistry.spellParchment, 1).unlockedBy("has_journal", InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.wornNotebook))
                    .requires(MagicItemsRegistry.BLANK_PARCHMENT, 1)
                    .requires(Ingredient.of(ItemTags.bind("forge:gems/mana")), 4)
                    .save(consumer);

            ShapelessRecipeBuilder.shapeless(MagicItemsRegistry.ALLOW_ITEM_SCROLL, 1).unlockedBy("has_journal", InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.wornNotebook))
                    .requires(MagicItemsRegistry.BLANK_PARCHMENT, 1)
                    .requires(Ingredient.of(Tags.Items.CHESTS), 1)
                    .save(consumer);

            ShapelessRecipeBuilder.shapeless(MagicItemsRegistry.DENY_ITEM_SCROLL, 1).unlockedBy("has_journal", InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.wornNotebook))
                    .requires(MagicItemsRegistry.BLANK_PARCHMENT, 1)
                    .requires(Ingredient.of(Tags.Items.COBBLESTONE), 1)
                    .save(consumer);

            ShapelessRecipeBuilder.shapeless(MagicItemsRegistry.warpScroll).unlockedBy("has_journal", InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.wornNotebook))
                    .requires(Ingredient.of(Tags.Items.GEMS_LAPIS), 4).requires(MagicItemsRegistry.BLANK_PARCHMENT).requires(DOMINION_GEM, 4)
                    .save(consumer);

//            ShapedRecipeBuilder.shaped(BlockRegistry.VOLCANIC_BLOCK).unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.wornNotebook))
//                    .pattern(" s ")
//                    .pattern("gig")
//                    .pattern(" s ")
//                    .define('g', Tags.Items.INGOTS_GOLD)
//                    .define('s', DOMINION_GEM)
//                    .define('i', Items.LAVA_BUCKET).save(consumer);

            ShapelessRecipeBuilder.shapeless(BlockRegistry.LAVA_LILY, 8).unlockedBy("has_journal", InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.wornNotebook))
                    .requires(Items.LILY_PAD, 1).requires(DOMINION_GEM, 8)
                    .save(consumer);

//            shapelessBuilder(BlockRegistry.ARCHWOOD_PLANK, 4).requires(ARCHWOOD_LOG).save(consumer);
//            makeWood(BlockRegistry.VEXING_LOG, BlockRegistry.VEXING_WOOD, 3).save(consumer);
//            makeWood(BlockRegistry.CASCADING_LOG, BlockRegistry.CASCADING_WOOD, 3).save(consumer);
//            makeWood(BlockRegistry.BLAZING_LOG, BlockRegistry.BLAZING_WOOD, 3).save(consumer);
//            makeWood(BlockRegistry.FLOURISHING_LOG, BlockRegistry.FLOURISHING_WOOD, 3).save(consumer);
//            shapedWoodenStairs(consumer, BlockRegistry.ARCHWOOD_STAIRS, BlockRegistry.ARCHWOOD_PLANK);
//            shapelessWoodenButton(consumer, BlockRegistry.ARCHWOOD_BUTTON, BlockRegistry.ARCHWOOD_PLANK);
//            shapedWoodenDoor(consumer, BlockRegistry.ARCHWOOD_DOOR, BlockRegistry.ARCHWOOD_PLANK);
//            shapedWoodenFence(consumer, BlockRegistry.ARCHWOOD_FENCE, BlockRegistry.ARCHWOOD_PLANK);
//            shapedWoodenFenceGate(consumer, BlockRegistry.ARCHWOOD_FENCE_GATE, BlockRegistry.ARCHWOOD_PLANK);
//            shapedWoodenPressurePlate(consumer, BlockRegistry.ARCHWOOD_PPlate, BlockRegistry.ARCHWOOD_PLANK);
//            shapedWoodenSlab(consumer, BlockRegistry.ARCHWOOD_SLABS, BlockRegistry.ARCHWOOD_PLANK);

//            strippedLogToWood(consumer, BlockRegistry.STRIPPED_AWLOG_BLUE, BlockRegistry.STRIPPED_AWWOOD_BLUE);
//            strippedLogToWood(consumer, BlockRegistry.STRIPPED_AWLOG_GREEN, BlockRegistry.STRIPPED_AWWOOD_GREEN);
//            strippedLogToWood(consumer, BlockRegistry.STRIPPED_AWLOG_RED, BlockRegistry.STRIPPED_AWWOOD_RED);
//            strippedLogToWood(consumer, BlockRegistry.STRIPPED_AWLOG_PURPLE, BlockRegistry.STRIPPED_AWWOOD_PURPLE);
//            shapedWoodenTrapdoor(consumer, BlockRegistry.ARCHWOOD_TRAPDOOR, BlockRegistry.ARCHWOOD_PLANK);
            shapelessBuilder(BlockRegistry.DOMINION_GEM_BLOCK,1).requires(DOMINION_GEM, 9).save(consumer);
            shapelessBuilder(MagicItemsRegistry.manaGem, 9).requires(BlockRegistry.DOMINION_GEM_BLOCK,1).save(consumer, new ResourceLocation(EmortisConstants.MOD_ID, "mana_gem_2"));
//            shapelessBuilder(Items.LEATHER, 1).requires(MagicItemsRegistry.WILDEN_WING).save(consumer,  new ResourceLocation(EmortisConstants.MOD_ID, "wing_to_leather"));
//            shapelessBuilder(Items.BONE_MEAL, 3).requires(MagicItemsRegistry.WILDEN_HORN).save(consumer,  new ResourceLocation(EmortisConstants.MOD_ID, "horn_to_bonemeal"));
//            shapelessBuilder(Items.ORANGE_DYE, 5).requires(MagicItemsRegistry.WILDEN_SPIKE).save(consumer,  new ResourceLocation(EmortisConstants.MOD_ID, "spike_to_dye"));
//            ShapedRecipeBuilder.shaped(Items.ARROW, 32)
//                    .unlockedBy("has_journal", InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.wornNotebook))
//                    .pattern(" x ")
//                    .pattern(" y ")
//                    .pattern(" z ")
//                    .define('x', MagicItemsRegistry.WILDEN_SPIKE)
//                    .define('y', Items.STICK)
//                    .define('z', Items.FEATHER)
//                    .save(consumer, new ResourceLocation(EmortisConstants.MOD_ID, "spike_to_arrow"));

            shapelessBuilder(BlockRegistry.POTION_JAR)
                    .requires(BlockRegistry.DOMINION_JAR)
                    .requires(Items.NETHER_WART)
                    .save(consumer);

//            shapelessBuilder(BlockRegistry.RITUAL_BLOCK)
//                    .requires(BlockRegistry.ARCANE_PEDESTAL)
//                    .requires(Recipes.DOMINION_GEM_BLOCK_TAG)
//                    .requires(Ingredient.of(Tags.Items.INGOTS_GOLD), 3)
//                    .save(consumer);

            shapelessBuilder(BlockRegistry.SCONCE_BLOCK)
                    .requires(Recipes.DOMINION_GEM)
                    .requires(Ingredient.of(Tags.Items.NUGGETS_GOLD), 2)
                    .save(consumer);
//
//            shapelessBuilder(getRitualItem(RitualLib.MOONFALL))
//                    .requires(BlockRegistry.CASCADING_LOG)
//                    .requires(Items.INK_SAC)
//                    .requires(Tags.Items.STORAGE_BLOCKS_COAL)
//                    .requires(Items.CLOCK)
//                    .save(consumer);
//            shapelessBuilder(getRitualItem(RitualLib.MOONFALL))
//                    .requires(BlockRegistry.CASCADING_LOG)
//                    .requires(MagicItemsRegistry.WILDEN_WING)
//                    .save(consumer, new ResourceLocation(EmortisConstants.MOD_ID, "moonfall_2"));
//            shapelessBuilder(getRitualItem(RitualLib.SUNRISE))
//                    .requires(BlockRegistry.BLAZING_LOG)
//                    .requires(Items.DANDELION, 3)
//                    .requires(Items.CLOCK)
//                    .save(consumer);
//            shapelessBuilder(getRitualItem(RitualLib.SUNRISE))
//                    .requires(BlockRegistry.BLAZING_LOG)
//                    .requires(Items.SUNFLOWER)
//                    .save(consumer,  new ResourceLocation(EmortisConstants.MOD_ID, "sunrise_2"));
//            shapelessBuilder(getRitualItem(RitualLib.DIG))
//                    .requires(BlockRegistry.FLOURISHING_LOG)
//                    .requires(Items.IRON_PICKAXE)
//                    .requires(Tags.Items.STORAGE_BLOCKS_COAL)
//                    .save(consumer);
//            shapelessBuilder(getRitualItem(RitualLib.CLOUDSHAPER))
//                    .requires(BlockRegistry.CASCADING_LOG)
//                    .requires(Items.FEATHER)
//                    .requires(DOMINION_GEM_BLOCK_TAG)
//                    .save(consumer);
//            shapelessBuilder(getRitualItem(RitualLib.CHALLENGE))
//                    .requires(BlockRegistry.VEXING_LOG)
//                    .requires(Items.EMERALD_BLOCK)
//                    .requires(Items.INK_SAC)
//                    .save(consumer);
//            shapelessBuilder(getRitualItem(RitualLib.CHALLENGE))
//                    .requires(BlockRegistry.VEXING_LOG)
//                    .requires(MagicItemsRegistry.WILDEN_HORN)
//                    .requires(Items.EMERALD)
//                    .save(consumer, new ResourceLocation(EmortisConstants.MOD_ID, "challenge_2"));
//            shapelessBuilder(getRitualItem(RitualLib.OVERGROWTH))
//                    .requires(BlockRegistry.FLOURISHING_LOG)
//                    .requires(MagicItemsRegistry.MAGE_BLOOM, 3)
//                    .requires(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(EffectGrow.INSTANCE), 2)
//                    .save(consumer);
//            shapelessBuilder(getRitualItem(RitualLib.FERTILITY))
//                    .requires(BlockRegistry.FLOURISHING_LOG)
//                    .requires(Items.WHEAT, 3)
//                    .requires(Items.GOLDEN_APPLE)
//                    .requires(Items.BLAZE_POWDER, 2)
//                    .save(consumer);
//            shapelessBuilder(getRitualItem(RitualLib.RESTORATION))
//                    .requires(BlockRegistry.FLOURISHING_LOG)
//                    .requires(Items.GOLDEN_APPLE)
//                    .requires(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(EffectHeal.INSTANCE), 1)
//                    .save(consumer);
//            shapelessBuilder(getRitualItem(RitualLib.DISINTEGRATION))
//                    .requires(BlockRegistry.BLAZING_LOG)
//                    .requires(Items.GOLDEN_SWORD, 3)
//                    .requires(Items.BOOK, 3)
//                    .save(consumer);
//            shapelessBuilder(getRitualItem(RitualLib.WARP))
//                    .requires(BlockRegistry.VEXING_LOG)
//                    .requires(MagicItemsRegistry.warpScroll)
//                    .save(consumer);

            shapelessBuilder(MagicItemsRegistry.GREATER_EXPERIENCE_GEM)
                    .requires(MagicItemsRegistry.EXPERIENCE_GEM, 4)
                    .save(consumer);
            shapelessBuilder(MagicItemsRegistry.EXPERIENCE_GEM, 4)
                    .requires(MagicItemsRegistry.GREATER_EXPERIENCE_GEM)
                    .save(consumer);

            shapelessBuilder(MagicItemsRegistry.ALLOW_ITEM_SCROLL)
                    .requires(MagicItemsRegistry.ALLOW_ITEM_SCROLL)
                    .save(consumer, new ResourceLocation(EmortisConstants.MOD_ID, "clear_allow"));

            shapelessBuilder(MagicItemsRegistry.DENY_ITEM_SCROLL)
                    .requires(MagicItemsRegistry.DENY_ITEM_SCROLL)
                    .save(consumer, new ResourceLocation(EmortisConstants.MOD_ID, "clear_deny"));

//            shapelessBuilder(getRitualItem(RitualLib.SCRYING))
//                    .requires(BlockRegistry.VEXING_LOG)
//                    .requires(Items.SPIDER_EYE, 3)
//                    .requires(Items.GLOWSTONE)
//                    .requires(Recipes.DOMINION_GEM_BLOCK)
//                    .save(consumer);
//
//            shapelessBuilder(getRitualItem(RitualLib.FLIGHT))
//                    .requires(BlockRegistry.VEXING_LOG)
//                    .requires(MagicItemsRegistry.WILDEN_WING, 3)
//                    .requires(Ingredient.of(Tags.Items.GEMS_DIAMOND), 2)
//                    .requires(Items.ENDER_PEARL)
//                    .save(consumer);

            shapelessBuilder(MagicItemsRegistry.MIMIC_ITEM_SCROLL)
                    .requires(MagicItemsRegistry.ALLOW_ITEM_SCROLL)
                    .requires(Items.CHEST)
                    .save(consumer);
//
//            shapelessBuilder(getRitualItem(RitualLib.WILDEN_SUMMON))
//                    .requires(BlockRegistry.VEXING_LOG)
//                    .requires(MagicItemsRegistry.WILDEN_HORN, 1)
//                    .requires(MagicItemsRegistry.WILDEN_SPIKE, 1)
//                    .requires(MagicItemsRegistry.WILDEN_WING, 1)
//                    .requires(Items.LAPIS_BLOCK)
//                    .save(consumer);
//            shapelessBuilder(getRitualItem(RitualLib.WILDEN_SUMMON))
//                    .requires(BlockRegistry.VEXING_LOG)
//                    .requires(MagicItemsRegistry.WILDEN_HORN, 3)
//                    .requires(Items.LAPIS_BLOCK)
//                    .save(consumer, new ResourceLocation(EmortisConstants.MOD_ID, "wilden_summon_2"));
//            shapelessBuilder(getRitualItem(RitualLib.WILDEN_SUMMON))
//                    .requires(BlockRegistry.VEXING_LOG)
//                    .requires(MagicItemsRegistry.WILDEN_SPIKE, 3)
//                    .requires(Items.LAPIS_BLOCK)
//                    .save(consumer, new ResourceLocation(EmortisConstants.MOD_ID, "wilden_summon_3"));
//            shapelessBuilder(getRitualItem(RitualLib.WILDEN_SUMMON))
//                    .requires(BlockRegistry.VEXING_LOG)
//                    .requires(MagicItemsRegistry.WILDEN_WING, 3)
//                    .requires(Items.LAPIS_BLOCK)
//                    .save(consumer, new ResourceLocation(EmortisConstants.MOD_ID, "wilden_summon_4"));
//
//            shapelessBuilder(getRitualItem(RitualLib.WILDEN_SUMMON))
//                    .requires(BlockRegistry.VEXING_LOG)
//                    .requires(Items.EMERALD_BLOCK, 1)
//                    .requires(Items.IRON_SWORD, 1)
//                    .requires(Items.BOW, 1)
//                    .requires(Items.LAPIS_BLOCK)
//                    .save(consumer, new ResourceLocation(EmortisConstants.MOD_ID, "wilden_summon_5"));

//            ShapedRecipeBuilder.shaped(BlockRegistry.AS_GOLD_STONE, 8).unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.wornNotebook))
//                    .pattern("xxx")
//                    .pattern("xyx")
//                    .pattern("xxx").define('y',Tags.Items.NUGGETS_GOLD).define('x',  BlockRegistry.ARCANE_STONE).save(consumer);

            STONECUTTER_COUNTER = 1;
//            makeStonecutter(consumer, BlockRegistry.AS_GOLD_STONE, BlockRegistry.AS_GOLD_ALT, LibBlockNames.AS_GOLD_STONE);
//            makeStonecutter(consumer, BlockRegistry.AS_GOLD_STONE, BlockRegistry.AS_GOLD_ASHLAR, LibBlockNames.AS_GOLD_STONE);
//            makeStonecutter(consumer, BlockRegistry.AS_GOLD_STONE, BlockRegistry.AS_GOLD_BASKET, LibBlockNames.AS_GOLD_STONE);
//            makeStonecutter(consumer, BlockRegistry.AS_GOLD_STONE, BlockRegistry.AS_GOLD_CLOVER, LibBlockNames.AS_GOLD_STONE);
//            makeStonecutter(consumer, BlockRegistry.AS_GOLD_STONE, BlockRegistry.AS_GOLD_HERRING, LibBlockNames.AS_GOLD_STONE);
//            makeStonecutter(consumer, BlockRegistry.AS_GOLD_STONE, BlockRegistry.AS_GOLD_MOSAIC, LibBlockNames.AS_GOLD_STONE);
//            makeStonecutter(consumer, BlockRegistry.AS_GOLD_STONE, BlockRegistry.AS_GOLD_SLAB, LibBlockNames.AS_GOLD_STONE);
//            ShapedRecipeBuilder.shaped(BlockRegistry.ALCHEMICAL_BLOCK).unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.wornNotebook))
//                    .pattern(" s ")
//                    .pattern("gig")
//                    .pattern(" s ")
//                    .define('g', Tags.Items.INGOTS_GOLD)
//                    .define('s', DOMINION_GEM)
//                    .define('i', Items.BREWING_STAND).save(consumer);
//
//            ShapedRecipeBuilder.shaped(BlockRegistry.VITALIC_BLOCK).unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.wornNotebook))
//                    .pattern(" s ")
//                    .pattern("gig")
//                    .pattern(" s ")
//                    .define('g', Tags.Items.INGOTS_GOLD)
//                    .define('s', DOMINION_GEM)
//                    .define('i', Items.GLISTERING_MELON_SLICE).save(consumer);
//
//            ShapedRecipeBuilder.shaped(BlockRegistry.MYCELIAL_BLOCK).unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.wornNotebook))
//                    .pattern(" s ")
//                    .pattern("gig")
//                    .pattern(" s ")
//                    .define('g', Tags.Items.INGOTS_GOLD)
//                    .define('s', DOMINION_GEM)
//                    .define('i', Items.MUSHROOM_STEW).save(consumer);
//
//            ShapedRecipeBuilder.shaped(BlockRegistry.AGRONOMIC_SOURCELINK).unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.wornNotebook))
//                    .pattern(" s ")
//                    .pattern("gig")
//                    .pattern(" s ")
//                    .define('g', Tags.Items.INGOTS_GOLD)
//                    .define('s', DOMINION_GEM)
//                    .define('i', Items.WHEAT).save(consumer);
//
//            shapelessBuilder(MagicItemsRegistry.SOURCE_BERRY_PIE)
//                    .requires(Items.EGG)
//                    .requires(Items.SUGAR)
//                    .requires(MagicItemsRegistry.MAGE_BLOOM)
//                    .requires(BlockRegistry.DOMINION_BERRY_BUSH, 3)
//                    .save(consumer);
//
//            shapelessBuilder(MagicItemsRegistry.SOURCE_BERRY_ROLL)
//                    .requires(Items.WHEAT, 3)
//                    .requires(BlockRegistry.DOMINION_BERRY_BUSH)
//                    .save(consumer);
//            ShapedRecipeBuilder.shaped(BlockRegistry.ARCANE_RELAY).unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.wornNotebook))
//                    .pattern("g g")
//                    .pattern("gMg")
//                    .pattern("g g")
//                    .define('g', Tags.Items.INGOTS_GOLD)
//                    .define('M', DOMINION_GEM_BLOCK)
//                    .save(consumer);
//            shapelessBuilder(getRitualItem(RitualLib.BINDING))
//                    .requires(BlockRegistry.VEXING_LOG)
//                    .requires(MagicItemsRegistry.BLANK_PARCHMENT)
//                    .requires(Items.ENDER_PEARL, 1)
//                    .requires(DOMINION_GEM, 3)
//                    .save(consumer);
//
//            ShapedRecipeBuilder.shaped(BlockRegistry.BASIC_SPELL_TURRET).unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.wornNotebook))
//                    .pattern("xxx")
//                    .pattern("xzy")
//                    .pattern("yyy")
//                    .define('z', Ingredient.of(Tags.Items.STORAGE_BLOCKS_REDSTONE))
//                    .define('x', DOMINION_GEM)
//                    .define('y', Ingredient.of(Tags.Items.INGOTS_GOLD))
//                    .save(consumer);
//
//            ShapedRecipeBuilder.shaped(BlockRegistry.ARCHWOOD_CHEST).unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.wornNotebook))
//                    .pattern("xxx")
//                    .pattern("xyx")
//                    .pattern("xxx")
//                    .define('x', BlockRegistry.ARCHWOOD_PLANK)
//                    .define('y', Items.GOLD_NUGGET)
//                    .save(consumer);
//
//            ShapedRecipeBuilder.shaped(BlockRegistry.SPELL_PRISM).unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.wornNotebook))
//                    .pattern("gxg")
//                    .pattern("xnx")
//                    .pattern("gxg")
//                    .define('x', BlockRegistry.ARCHWOOD_PLANK)
//                    .define('g', Ingredient.of(Tags.Items.INGOTS_GOLD))
//                    .define('n', Ingredient.of(Tags.Items.STORAGE_BLOCKS_QUARTZ))
//                    .save(consumer);
//
//            shapelessBuilder(Items.CHEST).requires(BlockRegistry.ARCHWOOD_CHEST).save(consumer, new ResourceLocation(EmortisConstants.MOD_ID, "archwood_to_chest"));
//
//            shapelessBuilder(getRitualItem(RitualLib.AWAKENING))
//                    .requires(BlockRegistry.FLOURISHING_LOG)
//                    .requires(BlockRegistry.BLAZING_SAPLING)
//                    .requires(BlockRegistry.CASCADING_SAPLING)
//                    .requires(BlockRegistry.FLOURISHING_SAPLING)
//                    .requires(BlockRegistry.VEXING_SAPLING)
//                    .requires(DOMINION_GEM, 4)
//                    .save(consumer);
        }
    }

//    public Item getRitualItem(String id){
//        return RigoranthusEmortisRebornAPI.getInstance().getRitualItemMap().get(id);
//    }

    public static ShapedRecipeBuilder makeWood(IItemProvider logs, IItemProvider wood, int count){
        return ShapedRecipeBuilder.shaped(wood, count).unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.wornNotebook))
                .pattern("xx ")
                .pattern("xx ").define('x', logs);
    }
    private static void shapedWoodenTrapdoor(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider trapdoor, IItemProvider input) {
        ShapedRecipeBuilder.shaped(trapdoor, 2).define('#', input).pattern("###").pattern("###").group("wooden_trapdoor")
                .unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.wornNotebook)).save(recipeConsumer);
    }

    public static void shapedWoodenStairs(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider stairs, IItemProvider input) {
        ShapedRecipeBuilder.shaped(stairs, 4)
                .define('#', input)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###").unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.wornNotebook))
                .save(recipeConsumer);

    }
//
//    private static void shapelessWoodenButton(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider button, IItemProvider input) {
//        ShapelessRecipeBuilder.shapeless(button).requires(input)
//                .unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.wornNotebook))
//                .save(recipeConsumer);
//    }
//
//    private static void strippedLogToWood(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider stripped, IItemProvider output) {
//        ShapedRecipeBuilder.shaped(output, 3).define('#', stripped).pattern("##").pattern("##").group("bark")
//                .unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.wornNotebook))
//                .save(recipeConsumer);
//    }
//    private static void shapedWoodenDoor(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider door, IItemProvider input) {
//        ShapedRecipeBuilder.shaped(door, 3).define('#', input).pattern("##").pattern("##").pattern("##").group("wooden_door")
//                .unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.wornNotebook))
//                .save(recipeConsumer);
//    }
//
//    private static void shapedWoodenFence(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider fence, IItemProvider input) {
//        ShapedRecipeBuilder.shaped(fence, 3).define('#', Items.STICK).define('W', input).pattern("W#W").pattern("W#W").group("wooden_fence")
//                .unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.wornNotebook))
//                .save(recipeConsumer);
//    }
//
//    private static void shapedWoodenFenceGate(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider fenceGate, IItemProvider input) {
//        ShapedRecipeBuilder.shaped(fenceGate).define('#', Items.STICK).define('W', input).pattern("#W#").pattern("#W#").group("wooden_fence_gate")
//                .unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.wornNotebook))
//                .save(recipeConsumer);
//    }
//
//    private static void shapedWoodenPressurePlate(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider pressurePlate, IItemProvider input) {
//        ShapedRecipeBuilder.shaped(pressurePlate).define('#', input).pattern("##").group("wooden_pressure_plate")
//                .unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.wornNotebook))
//                .save(recipeConsumer);
//    }
//
//    private static void shapedWoodenSlab(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider slab, IItemProvider input) {
//        ShapedRecipeBuilder.shaped(slab, 6).define('#', input).pattern("###").group("wooden_slab")
//                .unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.wornNotebook))
//                .save(recipeConsumer);
//    }

    public ShapelessRecipeBuilder shapelessBuilder(IItemProvider result){
        return shapelessBuilder(result, 1);
    }

    public ShapelessRecipeBuilder shapelessBuilder(IItemProvider result, int resultCount){
        return ShapelessRecipeBuilder.shapeless(result, resultCount).unlockedBy("has_journal", InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.wornNotebook));
    }

    private static int STONECUTTER_COUNTER = 0;
    public static void makeStonecutter(Consumer<IFinishedRecipe> consumer, IItemProvider input, IItemProvider output, String reg){
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(input), output).unlocks("has_journal",InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.wornNotebook)).save(consumer, new ResourceLocation(EmortisConstants.MOD_ID, reg + "_"+STONECUTTER_COUNTER));
        STONECUTTER_COUNTER++;
    }

    public static void makeArmor(String prefix, Consumer<IFinishedRecipe> consumer, Item material){
        ShapedRecipeBuilder.shaped(ForgeRegistries.ITEMS.getValue(new ResourceLocation(EmortisConstants.MOD_ID, prefix + "_boots")))
                .pattern("   ")
                .pattern("x x")
                .pattern("x x").define('x', material).group(EmortisConstants.MOD_ID)
                .unlockedBy("has_journal", InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.wornNotebook))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ForgeRegistries.ITEMS.getValue(new ResourceLocation(EmortisConstants.MOD_ID, prefix + "_leggings")))
                .pattern("xxx")
                .pattern("x x")
                .pattern("x x").define('x', material).group(EmortisConstants.MOD_ID)
                .unlockedBy("has_journal", InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.wornNotebook))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ForgeRegistries.ITEMS.getValue(new ResourceLocation(EmortisConstants.MOD_ID, prefix + "_hood")))
                .pattern("xxx")
                .pattern("x x")
                .pattern("   ").define('x', material).group(EmortisConstants.MOD_ID)
                .unlockedBy("has_journal", InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.wornNotebook))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ForgeRegistries.ITEMS.getValue(new ResourceLocation(EmortisConstants.MOD_ID, prefix + "_robes")))
                .pattern("x x")
                .pattern("xxx")
                .pattern("xxx").define('x', material).group(EmortisConstants.MOD_ID)
                .unlockedBy("has_journal", InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.wornNotebook))
                .save(consumer);
    }
}