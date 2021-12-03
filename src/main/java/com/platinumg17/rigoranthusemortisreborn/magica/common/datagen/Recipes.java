package com.platinumg17.rigoranthusemortisreborn.magica.common.datagen;

import com.platinumg17.rigoranthusemortisreborn.api.RigoranthusEmortisRebornAPI;
import com.platinumg17.rigoranthusemortisreborn.blocks.BlockInit;
import com.platinumg17.rigoranthusemortisreborn.blocks.DecorativeOrStorageBlocks;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.core.init.ItemInit;
import com.platinumg17.rigoranthusemortisreborn.core.init.Registration;
import com.platinumg17.rigoranthusemortisreborn.core.registry.RigoranthusTagRegistry;
import com.platinumg17.rigoranthusemortisreborn.magica.common.lib.RitualLib;
import com.platinumg17.rigoranthusemortisreborn.magica.common.spell.effect.EffectHeal;
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
    public static ITag.INamedTag<Block> DECORATIVE_RE =  BlockTags.createOptional(new ResourceLocation(EmortisConstants.MOD_ID, "re_decorative"));
    public static ITag.INamedTag<Block> MAGIC_SAPLINGS =  BlockTags.createOptional(new ResourceLocation(EmortisConstants.MOD_ID, "magic_saplings"));
    public static ITag.INamedTag<Block> MAGIC_PLANTS =  BlockTags.createOptional(new ResourceLocation(EmortisConstants.MOD_ID, "magic_plants"));
    public static ITag.INamedTag<Item> MAGIC_FOOD = ItemTags.bind("rigoranthusemortisreborn:magic_food");

    public static Ingredient DOMINION_GEM = Ingredient.of(DOMINION_GEM_TAG);
    public static Ingredient DOMINION_GEM_BLOCK = Ingredient.of(DOMINION_GEM_BLOCK_TAG);
    @Override
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
        {
            makeArmor("novice", consumer, MagicItemsRegistry.DOMINION_FIBER);
            makeArmor("apprentice", consumer, ItemInit.GHAST_IRON_INGOT.get());
            makeArmor("emortic", consumer, ItemInit.DWELLER_FLESH.get());

            CookingRecipeBuilder.smelting(Ingredient.of(Registration.RECONDITE_ORE.get()), MagicItemsRegistry.dominionGem,0.5f, 200)
                    .unlockedBy("has_ore", InventoryChangeTrigger.Instance.hasItems(Registration.RECONDITE_ORE.get())).save(consumer);

            ShapelessRecipeBuilder.shapeless(MagicItemsRegistry.emorticOrigins).unlockedBy("has_journal", InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.emorticOrigins))
                    .requires(DOMINION_GEM, 1)
                    .requires(Items.BOOK).save(consumer);
//            ShapelessRecipeBuilder.shapeless(MagicItemsRegistry.DOMINION_FIBER, 4).unlockedBy("has_journal", InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.emorticOrigins))
//                    .requires(MagicItemsRegistry.MAGE_BLOOM)
//                    .save(consumer);

//            ShapelessRecipeBuilder.shapeless(MagicItemsRegistry.runicChalk, 1).unlockedBy("has_journal", InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.emorticOrigins))
//                    .requires(MagicItemsRegistry.magicClay).requires(Items.BONE_MEAL).requires(MagicItemsRegistry.DOMINION_FIBER)
//                    .save(consumer);
            ShapedRecipeBuilder.shaped(BlockRegistry.DOMINION_JAR).unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.emorticOrigins))
                    .pattern("xyx")
                    .pattern("x x")
                    .pattern("xxx").define('x', Tags.Items.GLASS).define('y', BlockInit.OPULENT_MAGMA.get()).save(consumer);

            ShapedRecipeBuilder.shaped(BlockRegistry.GLYPH_PRESS_BLOCK).unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.emorticOrigins))
                    .pattern("xxx")
                    .pattern("xyx")
                    .pattern("aba").define('x', Registration.RECONDITE_ORE.get()).define('y', Items.PISTON)
                    .define('a', Tags.Items.STONE).define('b', Tags.Items.STORAGE_BLOCKS_IRON).save(consumer);

            ShapedRecipeBuilder.shaped(BlockRegistry.SPLINTERED_PEDESTAL).unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.emorticOrigins))
                    .pattern("xxx")
                    .pattern(" x ")
                    .pattern("xxx").define('x', Registration.RECONDITE_ORE.get()).save(consumer);

            ShapedRecipeBuilder.shaped(BlockRegistry.PSYGLYPHIC_AMALG_BLOCK).unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.emorticOrigins))
                    .pattern("xyx")
                    .pattern("x x")
                    .pattern("zzz").define('x', Tags.Items.INGOTS_IRON)
                    .define('y', Tags.Items.GEMS_DIAMOND)
                    .define('z', Registration.RECONDITE_ORE.get()).save(consumer);

//            ShapedRecipeBuilder.shaped(MagicItemsRegistry.prosaicBelt).unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.emorticOrigins))
//                    .pattern("   ")
//                    .pattern("xyx")
//                    .pattern(" x ")
//                    .define('x', Tags.Items.LEATHER)
//                    .define('y', DOMINION_GEM).save(consumer);

            ShapedRecipeBuilder.shaped(MagicItemsRegistry.ringOfPotential).unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.emorticOrigins))
                    .pattern("xxx")
                    .pattern("xyx")
                    .pattern("xxx").define('x', Tags.Items.NUGGETS_IRON).define('y', DOMINION_GEM).save(consumer);

            ShapedRecipeBuilder.shaped(BlockRegistry.SCRIBES_BLOCK).unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.emorticOrigins))
                    .pattern("xxx")
                    .pattern("yzy")
                    .pattern("y y").define('x',Ingredient.of(DecorativeOrStorageBlocks.AZULOREAL_SLAB.get()))
                    .define('y', Items.STICK)
                    .define('z', Ingredient.of(RigoranthusTagRegistry.Items.AZULOREAL_LOGS)).save(consumer);

            ShapedRecipeBuilder.shaped(MagicItemsRegistry.unadornedAmulet).unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.emorticOrigins))
                    .pattern(" x ")
                    .pattern("xyx")
                    .pattern(" x ").define('x',  Tags.Items.NUGGETS_IRON).define('y',DOMINION_GEM).save(consumer);

            ShapedRecipeBuilder.shaped(BlockRegistry.EMORTIC_CORE_BLOCK).unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.emorticOrigins))
                    .pattern("xxx")
                    .pattern("y y")
                    .pattern("xxx").define('y',  Tags.Items.INGOTS_GOLD).define('x', BlockInit.OPULENT_MAGMA.get()).save(consumer);

            ShapedRecipeBuilder.shaped(MagicItemsRegistry.BLANK_PARCHMENT, 1).unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.emorticOrigins))
                    .pattern("yyy")
                    .pattern("yxy")
                    .pattern("yyy").define('x', Items.PAPER).define('y', MagicItemsRegistry.DOMINION_FIBER).save(consumer);

            ShapelessRecipeBuilder.shapeless(MagicItemsRegistry.spellParchment, 1).unlockedBy("has_journal", InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.emorticOrigins))
                    .requires(MagicItemsRegistry.BLANK_PARCHMENT, 1)
                    .requires(Ingredient.of(ItemTags.bind("forge:gems/mana")), 4)
                    .save(consumer);

            ShapedRecipeBuilder.shaped(BlockRegistry.DOMINION_EXTRACTOR_BLOCK).unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.emorticOrigins))
                    .pattern(" s ")
                    .pattern("gig")
                    .pattern(" s ")
                    .define('g', Tags.Items.INGOTS_GOLD)
                    .define('s', DOMINION_GEM)
                    .define('i', Items.LAVA_BUCKET).save(consumer);

            ShapelessRecipeBuilder.shapeless(BlockRegistry.LAVA_LILY, 8).unlockedBy("has_journal", InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.emorticOrigins))
                    .requires(Items.LILY_PAD, 1).requires(DOMINION_GEM, 8)
                    .save(consumer);

            shapelessBuilder(BlockRegistry.DOMINION_GEM_BLOCK,1).requires(DOMINION_GEM, 9).save(consumer);
            shapelessBuilder(MagicItemsRegistry.dominionGem, 9).requires(BlockRegistry.DOMINION_GEM_BLOCK,1).save(consumer, new ResourceLocation(EmortisConstants.MOD_ID, "dominion_gem_2"));
            ShapedRecipeBuilder.shaped(Items.ARROW, 2)
                    .unlockedBy("has_journal", InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.emorticOrigins))
                    .pattern(" x ")
                    .pattern(" y ")
                    .pattern(" z ")
                    .define('x', ItemInit.RAZORTOOTH_KUNAI.get())
                    .define('y', Items.STICK)
                    .define('z', Items.FEATHER)
                    .save(consumer, new ResourceLocation(EmortisConstants.MOD_ID, "spike_to_arrow"));

            shapelessBuilder(BlockRegistry.RITUAL_BLOCK)
                    .requires(BlockRegistry.SPLINTERED_PEDESTAL)
                    .requires(Recipes.DOMINION_GEM_BLOCK_TAG)
                    .requires(Ingredient.of(Tags.Items.INGOTS_GOLD), 3)
                    .save(consumer);

            shapelessBuilder(BlockRegistry.SCONCE_BLOCK)
                    .requires(Recipes.DOMINION_GEM)
                    .requires(Ingredient.of(Tags.Items.NUGGETS_GOLD), 2)
                    .save(consumer);

            shapelessBuilder(getRitualItem(RitualLib.RESTORATION))
                    .requires(BlockRegistry.ICHOR_JAR)
                    .requires(Items.GOLDEN_APPLE)
                    .requires(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(EffectHeal.INSTANCE), 1)
                    .save(consumer);

            shapelessBuilder(MagicItemsRegistry.GREATER_EXPERIENCE_GEM)
                    .requires(MagicItemsRegistry.EXPERIENCE_GEM, 4)
                    .save(consumer);
            shapelessBuilder(MagicItemsRegistry.EXPERIENCE_GEM, 4)
                    .requires(MagicItemsRegistry.GREATER_EXPERIENCE_GEM)
                    .save(consumer);

            STONECUTTER_COUNTER = 1;

            ShapedRecipeBuilder.shaped(BlockRegistry.EMORTIC_RELAY).unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.emorticOrigins))
                    .pattern("g g")
                    .pattern("gMg")
                    .pattern("g g")
                    .define('g', Tags.Items.INGOTS_GOLD)
                    .define('M', DOMINION_GEM_BLOCK)
                    .save(consumer);

            shapelessBuilder(getRitualItem(RitualLib.BINDING))
                    .requires(BlockRegistry.ICHOR_JAR)
                    .requires(MagicItemsRegistry.BLANK_PARCHMENT)
                    .requires(Items.ENDER_PEARL, 1)
                    .requires(DOMINION_GEM, 3)
                    .save(consumer);
//
//            ShapedRecipeBuilder.shaped(BlockRegistry.BASIC_SPELL_TURRET).unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.emorticOrigins))
//                    .pattern("xxx")
//                    .pattern("xzy")
//                    .pattern("yyy")
//                    .define('z', Ingredient.of(Tags.Items.STORAGE_BLOCKS_REDSTONE))
//                    .define('x', DOMINION_GEM)
//                    .define('y', Ingredient.of(Tags.Items.INGOTS_GOLD))
//                    .save(consumer);
        }
    }

    public Item getRitualItem(String id){
        return RigoranthusEmortisRebornAPI.getInstance().getRitualItemMap().get(id);
    }

//    public static ShapedRecipeBuilder makeWood(IItemProvider logs, IItemProvider wood, int count){
//        return ShapedRecipeBuilder.shaped(wood, count).unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.emorticOrigins))
//                .pattern("xx ")
//                .pattern("xx ").define('x', logs);
//    }
//    private static void shapedWoodenTrapdoor(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider trapdoor, IItemProvider input) {
//        ShapedRecipeBuilder.shaped(trapdoor, 2).define('#', input).pattern("###").pattern("###").group("wooden_trapdoor")
//                .unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.emorticOrigins)).save(recipeConsumer);
//    }
//    public static void shapedWoodenStairs(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider stairs, IItemProvider input) {
//        ShapedRecipeBuilder.shaped(stairs, 4)
//                .define('#', input)
//                .pattern("#  ")
//                .pattern("## ")
//                .pattern("###").unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.emorticOrigins))
//                .save(recipeConsumer);
//    }
//    private static void shapelessWoodenButton(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider button, IItemProvider input) {
//        ShapelessRecipeBuilder.shapeless(button).requires(input)
//                .unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.emorticOrigins))
//                .save(recipeConsumer);
//    }
//    private static void strippedLogToWood(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider stripped, IItemProvider output) {
//        ShapedRecipeBuilder.shaped(output, 3).define('#', stripped).pattern("##").pattern("##").group("bark")
//                .unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.emorticOrigins))
//                .save(recipeConsumer);
//    }
//    private static void shapedWoodenDoor(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider door, IItemProvider input) {
//        ShapedRecipeBuilder.shaped(door, 3).define('#', input).pattern("##").pattern("##").pattern("##").group("wooden_door")
//                .unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.emorticOrigins))
//                .save(recipeConsumer);
//    }
//    private static void shapedWoodenFence(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider fence, IItemProvider input) {
//        ShapedRecipeBuilder.shaped(fence, 3).define('#', Items.STICK).define('W', input).pattern("W#W").pattern("W#W").group("wooden_fence")
//                .unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.emorticOrigins))
//                .save(recipeConsumer);
//    }
//    private static void shapedWoodenFenceGate(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider fenceGate, IItemProvider input) {
//        ShapedRecipeBuilder.shaped(fenceGate).define('#', Items.STICK).define('W', input).pattern("#W#").pattern("#W#").group("wooden_fence_gate")
//                .unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.emorticOrigins))
//                .save(recipeConsumer);
//    }
//    private static void shapedWoodenPressurePlate(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider pressurePlate, IItemProvider input) {
//        ShapedRecipeBuilder.shaped(pressurePlate).define('#', input).pattern("##").group("wooden_pressure_plate")
//                .unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.emorticOrigins))
//                .save(recipeConsumer);
//    }
//    private static void shapedWoodenSlab(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider slab, IItemProvider input) {
//        ShapedRecipeBuilder.shaped(slab, 6).define('#', input).pattern("###").group("wooden_slab")
//                .unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.emorticOrigins))
//                .save(recipeConsumer);
//    }

    public ShapelessRecipeBuilder shapelessBuilder(IItemProvider result){
        return shapelessBuilder(result, 1);
    }

    public ShapelessRecipeBuilder shapelessBuilder(IItemProvider result, int resultCount){
        return ShapelessRecipeBuilder.shapeless(result, resultCount).unlockedBy("has_journal", InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.emorticOrigins));
    }

    private static int STONECUTTER_COUNTER = 0;
    public static void makeStonecutter(Consumer<IFinishedRecipe> consumer, IItemProvider input, IItemProvider output, String reg){
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(input), output).unlocks("has_journal",InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.emorticOrigins)).save(consumer, new ResourceLocation(EmortisConstants.MOD_ID, reg + "_"+STONECUTTER_COUNTER));
        STONECUTTER_COUNTER++;
    }

    public static void makeArmor(String prefix, Consumer<IFinishedRecipe> consumer, Item material){
        ShapedRecipeBuilder.shaped(ForgeRegistries.ITEMS.getValue(new ResourceLocation(EmortisConstants.MOD_ID, prefix + "_boots")))
                .pattern("   ")
                .pattern("x x")
                .pattern("x x").define('x', material).group(EmortisConstants.MOD_ID)
                .unlockedBy("has_journal", InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.emorticOrigins))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ForgeRegistries.ITEMS.getValue(new ResourceLocation(EmortisConstants.MOD_ID, prefix + "_leggings")))
                .pattern("xxx")
                .pattern("x x")
                .pattern("x x").define('x', material).group(EmortisConstants.MOD_ID)
                .unlockedBy("has_journal", InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.emorticOrigins))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ForgeRegistries.ITEMS.getValue(new ResourceLocation(EmortisConstants.MOD_ID, prefix + "_hood")))
                .pattern("xxx")
                .pattern("x x")
                .pattern("   ").define('x', material).group(EmortisConstants.MOD_ID)
                .unlockedBy("has_journal", InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.emorticOrigins))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ForgeRegistries.ITEMS.getValue(new ResourceLocation(EmortisConstants.MOD_ID, prefix + "_robes")))
                .pattern("x x")
                .pattern("xxx")
                .pattern("xxx").define('x', material).group(EmortisConstants.MOD_ID)
                .unlockedBy("has_journal", InventoryChangeTrigger.Instance.hasItems(MagicItemsRegistry.emorticOrigins))
                .save(consumer);
    }
}