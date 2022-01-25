package com.platinumg17.rigoranthusemortisreborn.magica.common.datagen;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.platinumg17.rigoranthusemortisreborn.api.RigoranthusEmortisRebornAPI;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.psyglyphic_amalgamator.IPsyglyphicRecipe;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.psyglyphic_amalgamator.PsyglyphicAmalgamatorRecipe;
import com.platinumg17.rigoranthusemortisreborn.blocks.BlockInit;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.core.init.ItemInit;
import com.platinumg17.rigoranthusemortisreborn.core.init.Registration;
import com.platinumg17.rigoranthusemortisreborn.magica.common.enchantment.EnchantmentRegistry;
import com.platinumg17.rigoranthusemortisreborn.magica.common.spell.augment.AugmentAmplify;
import com.platinumg17.rigoranthusemortisreborn.magica.common.spell.augment.AugmentExtendTime;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.BlockRegistry;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.MagicItemsRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.Tags;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PsyglyphicRecipeProvider implements IDataProvider {
    private final DataGenerator generator;
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();
    private static final Logger LOGGER = LogManager.getLogger();
    public PsyglyphicRecipeProvider(DataGenerator generatorIn) {
        this.generator = generatorIn;
    }

    List<PsyglyphicAmalgamatorRecipe> recipes = new ArrayList<>();
    @Override
    public void run(DirectoryCache cache) throws IOException {
        addEntries();
        Path output = this.generator.getOutputFolder();
        for(IPsyglyphicRecipe g : recipes){
            if(g instanceof PsyglyphicAmalgamatorRecipe){
                System.out.println(g);
                Path path = getRecipePath(output, ((PsyglyphicAmalgamatorRecipe) g).getId().getPath());
                IDataProvider.save(GSON, cache, ((PsyglyphicAmalgamatorRecipe) g).asRecipe(), path);

                if(g.getResultItem().isEmpty())
                    continue;
                Path path1 = getAmalgamatorPath(output, (PsyglyphicAmalgamatorRecipe) g);
                try {
                    IDataProvider.save(GSON, cache, ((PsyglyphicAmalgamatorRecipe)g).serialize(), path1);
                    System.out.println(g);
                } catch (IOException ioexception) {
                    LOGGER.error("Couldn't save amalgamator {}", path1, ioexception);
                }
            }
        }
    }
    private static Path getAmalgamatorPath(Path pathIn, PsyglyphicAmalgamatorRecipe e) {
        System.out.println(e.result.getItem().toString());
        return pathIn.resolve("data/rigoranthusemortisreborn/amalgamator/" + e.result.getItem().getRegistryName().toString().replace(EmortisConstants.MOD_ID + ":", "") + ".json");
    }

    public PsyglyphicRecipeBuilder builder(){
        return PsyglyphicRecipeBuilder.builder();
    }

    public void addEntries(){

        addRecipe(builder()
                .withResult(ItemInit.CRY_OF_DESPERATION.get())
                .withReagent(BlockInit.BLOCK_OF_ESOTERICUM.get().asItem())
                .withPedestalItem(2,  Ingredient.of(Tags.Items.STORAGE_BLOCKS_GOLD))
                .withPedestalItem(4,  Ingredient.of(Items.DARK_OAK_PLANKS))
                .withPedestalItem(2,  Recipes.DOMINION_GEM)
                .build());
        addRecipe(builder()
                .withResult(ItemInit.MORRAI.get())
                .withReagent(ItemInit.EMORTIC_WEAPON_GRIP.get())
                .withPedestalItem(2,  Ingredient.of(ItemInit.IRON_SLIME_BALL.get()))
                .withPedestalItem(1,  Ingredient.of(Items.LEAD))
                .withPedestalItem(1,  Recipes.DOMINION_GEM)
                .build());
        addRecipe(builder()
                .withResult(ItemInit.ANDURIL.get())
                .withReagent(ItemInit.EMORTIC_WEAPON_GRIP.get())
                .withPedestalItem(1,  Ingredient.of(Items.FIRE_CHARGE))
                .withPedestalItem(1,  Recipes.DOMINION_GEM)
                .withPedestalItem(2,  Ingredient.of(ItemInit.GHAST_IRON_INGOT.get()))
                .build());
        addRecipe(builder()
                .withResult(ItemInit.BLIGHT_ICHOR.get())
                .withReagent(Items.WITHER_SKELETON_SKULL)
                .withPedestalItem(2,  Ingredient.of(MagicItemsRegistry.BOTTLE_OF_ICHOR))
                .withPedestalItem(2,  Ingredient.of(Items.SPIDER_EYE))
                .withPedestalItem(2,  Ingredient.of(Items.NETHER_WART))
                .build());
        addRecipe(builder()
                .withResult(ItemInit.RAZORTOOTH_FRISBEE.get())
                .withReagent(ItemInit.EMORTIC_WEAPON_GRIP.get())
                .withPedestalItem(1,  Recipes.DOMINION_GEM)
                .withPedestalItem(7,  Ingredient.of(ItemInit.RAZORTOOTH_KUNAI.get()))
                .build());
        addRecipe(builder()
                .withResult(MagicItemsRegistry.ADONIS)
                .withReagent(Items.BOW)
                .withPedestalItem(2, Ingredient.of(ItemInit.EMORTIC_WEAPON_GRIP.get()))
                .withPedestalItem(1, Ingredient.of(ItemInit.PSYGLYPHIC_SCRIPT.get()))
                .withPedestalItem(4, Ingredient.of(Tags.Items.INGOTS_GOLD))
                .withPedestalItem(1,  Recipes.DOMINION_GEM)
                .build());
        addRecipe(builder()
                .withResult(MagicItemsRegistry.unadornedRing)
                .withReagent(Ingredient.of(Tags.Items.INGOTS_IRON))
                .withPedestalItem(4,  Ingredient.of(Tags.Items.NUGGETS_IRON))
                .build());
        addRecipe(builder()
                .withResult(BlockRegistry.DOMINION_BERRY_BUSH.asItem())
                .withReagent(Ingredient.of(Tags.Items.SEEDS))
                .withPedestalItem(2, Recipes.DOMINION_GEM)
                .withPedestalItem(2, Ingredient.of(Items.SWEET_BERRIES))
                .build());
        addRecipe(builder()
                .withResult(MagicItemsRegistry.ringOfLesserConservation)
                .withReagent(MagicItemsRegistry.unadornedRing)
                .withPedestalItem(2,  Ingredient.of(Tags.Items.GEMS_DIAMOND))
                .withPedestalItem(2,  Ingredient.of(Registration.POWDERED_ESOTERICUM.get()))
                .withPedestalItem(2,  Recipes.DOMINION_GEM)
                .withPedestalItem(2,  Ingredient.of(Tags.Items.ENDER_PEARLS))
                .build());
        addRecipe(builder()
                .withResult(MagicItemsRegistry.ringOfGreaterConservation)
                .withReagent(MagicItemsRegistry.ringOfLesserConservation)
                .withPedestalItem(2,  Ingredient.of(Tags.Items.GEMS_DIAMOND))
                .withPedestalItem(2,  Ingredient.of(Tags.Items.RODS_BLAZE))
                .withPedestalItem(2,  Ingredient.of(Registration.POWDERED_ESOTERICUM.get()))
                .withPedestalItem(2, Recipes.DOMINION_GEM)
                .build());

        addRecipe(builder()
                .withResult(MagicItemsRegistry.unadornedAmulet)
                .withReagent(Ingredient.of(Tags.Items.INGOTS_IRON))
                .withPedestalItem(3,  Ingredient.of(Tags.Items.NUGGETS_IRON))
                .withPedestalItem(3, Ingredient.of(Tags.Items.LEATHER))
                .build());
        addRecipe(builder()
                .withResult(MagicItemsRegistry.amuletOfDominionBoost)
                .withReagent(MagicItemsRegistry.unadornedAmulet)
                .withPedestalItem(2,Ingredient.of(Tags.Items.GEMS_DIAMOND))
                .withPedestalItem(2,  Ingredient.of(Registration.POWDERED_ESOTERICUM.get()))
                .withPedestalItem(3, Recipes.DOMINION_GEM)
                .build());
        addRecipe(builder()
                .withResult(MagicItemsRegistry.amuletOfDominionRegen)
                .withReagent(MagicItemsRegistry.unadornedAmulet)
                .withPedestalItem(2,Ingredient.of(Tags.Items.GEMS_DIAMOND))
                .withPedestalItem(2,Ingredient.of(Tags.Items.INGOTS_GOLD))
                .withPedestalItem(4, Recipes.DOMINION_GEM)
                .build());
        addRecipe(builder()
                .withResult(MagicItemsRegistry.dominionWand)
                .withReagent(Items.STICK)
                .withPedestalItem(2, Recipes.DOMINION_GEM)
                .withPedestalItem(Ingredient.of(Tags.Items.INGOTS_GOLD))
                .build());

/*
    TODO -->    Use 1 Powdered Esotericum to make 1 Blank Script

    TODO -->    Players find naturally generated Psyglyphic Blocks around the world.
                    Dwellers will ALWAYS spawn near a Psyglyphic Block
                    Right click with a blank script to obtain a psyglyphic script -> cipher turns to stone.

    TODO -->    Players place a Decipher Kit on one of the faces of the Psyglyphic Block ("Psyglyphic Cipher")
                   [Eval Kit == Metal Frame w/ Animated Scanning Feature (render fake face of Psyglyphic Block in window of frame) --> After Anim, Psyglyphic Script is Printed, and the Block changes to stone]
*/

        addRecipe(builder()
                .withPedestalItem(1,  Ingredient.of(Items.SUGAR))
                .withPedestalItem(1,  Ingredient.of(Items.IRON_PICKAXE))
                .withPedestalItem(2, Recipes.DOMINION_GEM_BLOCK)
                .buildPsyglyphicRecipe(Enchantments.BLOCK_EFFICIENCY, 1, 2000));
        addRecipe(builder()
                .withPedestalItem(1,  Ingredient.of(Tags.Items.STORAGE_BLOCKS_REDSTONE))
                .withPedestalItem(1,  Ingredient.of(Items.GOLDEN_PICKAXE))
                .withPedestalItem(2, Recipes.DOMINION_GEM_BLOCK)
                .buildPsyglyphicRecipe(Enchantments.BLOCK_EFFICIENCY, 2, 3500));
        addRecipe(builder()
                .withPedestalItem(2,  Ingredient.of(Tags.Items.STORAGE_BLOCKS_REDSTONE))
                .withPedestalItem(1,  Ingredient.of(Items.GOLDEN_PICKAXE))
                .withPedestalItem(3, Recipes.DOMINION_GEM_BLOCK)
                .withPedestalItem(2,  Ingredient.of(Tags.Items.OBSIDIAN))
                .buildPsyglyphicRecipe(Enchantments.BLOCK_EFFICIENCY, 3, 5000));
        addRecipe(builder()
                .withPedestalItem(2,  Ingredient.of(Tags.Items.STORAGE_BLOCKS_REDSTONE))
                .withPedestalItem(1,  Ingredient.of(Items.DIAMOND_PICKAXE))
                .withPedestalItem(1,  Ingredient.of(Items.IRON_SHOVEL))
                .withPedestalItem(2, Recipes.DOMINION_GEM_BLOCK)
                .withPedestalItem(2,  Ingredient.of(Tags.Items.OBSIDIAN))
                .buildPsyglyphicRecipe(Enchantments.BLOCK_EFFICIENCY, 4, 6500));
        addRecipe(builder()
                .withPedestalItem(2,  Ingredient.of(Tags.Items.STORAGE_BLOCKS_REDSTONE))
                .withPedestalItem(1,  Ingredient.of(Items.DIAMOND_PICKAXE))
                .withPedestalItem(1,  Ingredient.of(Items.DIAMOND_SHOVEL))
                .withPedestalItem(2, Recipes.DOMINION_GEM_BLOCK)
                .withPedestalItem(2,  Ingredient.of(Tags.Items.STORAGE_BLOCKS_LAPIS))
                .buildPsyglyphicRecipe(Enchantments.BLOCK_EFFICIENCY, 5, 8000));

        addRecipe(builder()
                .withPedestalItem(2, Ingredient.of(Tags.Items.STORAGE_BLOCKS_QUARTZ))
                .withPedestalItem(1, Recipes.DOMINION_GEM_BLOCK)
                .withPedestalItem(1, Ingredient.of(Tags.Items.STORAGE_BLOCKS_LAPIS))
                .buildPsyglyphicRecipe(Enchantments.SHARPNESS, 1, 2000));
        addRecipe(builder()
                .withPedestalItem(2, Ingredient.of(Tags.Items.STORAGE_BLOCKS_QUARTZ))
                .withPedestalItem(1, Recipes.DOMINION_GEM_BLOCK)
                .withPedestalItem(1, Ingredient.of(Tags.Items.STORAGE_BLOCKS_LAPIS))
                .buildPsyglyphicRecipe(Enchantments.SHARPNESS, 2, 3500));

        addRecipe(builder()
                .withPedestalItem(2, Ingredient.of(Items.BONE_BLOCK))
                .withPedestalItem(1, Recipes.DOMINION_GEM_BLOCK)
                .withPedestalItem(1, Ingredient.of(Tags.Items.STORAGE_BLOCKS_LAPIS))
                .buildPsyglyphicRecipe(Enchantments.SMITE, 1, 2000));
        addRecipe(builder()
                .withPedestalItem(3, Ingredient.of(Items.BONE_BLOCK))
                .withPedestalItem(1, Recipes.DOMINION_GEM_BLOCK)
                .withPedestalItem(1, Ingredient.of(Tags.Items.STORAGE_BLOCKS_LAPIS))
                .buildPsyglyphicRecipe(Enchantments.SMITE, 2, 3500));

        addRecipe(builder()
                .withPedestalItem(4, Ingredient.of(Items.BONE_BLOCK))
                .withPedestalItem(1, Recipes.DOMINION_GEM_BLOCK)
                .withPedestalItem(1, Ingredient.of(Tags.Items.STORAGE_BLOCKS_LAPIS))
                .buildPsyglyphicRecipe(Enchantments.SMITE, 3, 500));

        addRecipe(builder()
                .withPedestalItem(4, Ingredient.of(Items.BONE_BLOCK))
                .withPedestalItem(2, Recipes.DOMINION_GEM_BLOCK)
                .withPedestalItem(1, Ingredient.of(Tags.Items.STORAGE_BLOCKS_LAPIS))
                .buildPsyglyphicRecipe(Enchantments.SMITE, 4, 6500));

        addRecipe(builder()
                .withPedestalItem(4, Ingredient.of(Items.BONE_BLOCK))
                .withPedestalItem(2, Recipes.DOMINION_GEM_BLOCK)
                .withPedestalItem(2, Ingredient.of(Tags.Items.STORAGE_BLOCKS_LAPIS))
                .buildPsyglyphicRecipe(Enchantments.SMITE, 5, 8000));

        addRecipe(builder()
                .withPedestalItem(1, BlockRegistry.DOMINION_BERRY_BUSH)
                .withPedestalItem(4, Recipes.DOMINION_GEM_BLOCK)
                .buildPsyglyphicRecipe(EnchantmentRegistry.DOMINION_BOOST_ENCHANTMENT, 1, 2000));
        addRecipe(builder()
                .withPedestalItem(1, BlockRegistry.DOMINION_BERRY_BUSH)
                .withPedestalItem(1, Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(AugmentAmplify.INSTANCE)))
                .withPedestalItem(4, Recipes.DOMINION_GEM_BLOCK)
                .withPedestalItem(2, Ingredient.of(Tags.Items.STORAGE_BLOCKS_LAPIS))
                .buildPsyglyphicRecipe(EnchantmentRegistry.DOMINION_BOOST_ENCHANTMENT, 2, 3500));

        addRecipe(builder()
                .withPedestalItem(1, BlockRegistry.DOMINION_BERRY_BUSH)
                .withPedestalItem(2, Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(AugmentAmplify.INSTANCE)))
                .withPedestalItem(4, Recipes.DOMINION_GEM_BLOCK)
                .withPedestalItem(1, Ingredient.of(Tags.Items.STORAGE_BLOCKS_LAPIS))
                .buildPsyglyphicRecipe(EnchantmentRegistry.DOMINION_BOOST_ENCHANTMENT, 3, 5000));

        addRecipe(builder()
                .withPedestalItem(2, BlockRegistry.DOMINION_BERRY_BUSH)
                .withPedestalItem(2, Recipes.DOMINION_GEM_BLOCK)
                .withPedestalItem(2, Ingredient.of(Tags.Items.STORAGE_BLOCKS_LAPIS))
                .buildPsyglyphicRecipe(EnchantmentRegistry.DOMINION_REGEN_ENCHANTMENT, 1, 2000));
        addRecipe(builder()
                .withPedestalItem(2, BlockRegistry.DOMINION_BERRY_BUSH)
                .withPedestalItem(2, Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(AugmentExtendTime.INSTANCE)))
                .withPedestalItem(2, Recipes.DOMINION_GEM_BLOCK)
                .withPedestalItem(2, Ingredient.of(Tags.Items.STORAGE_BLOCKS_LAPIS))
                .buildPsyglyphicRecipe(EnchantmentRegistry.DOMINION_REGEN_ENCHANTMENT, 2, 3500));

        addRecipe(builder()
                .withPedestalItem(2, BlockRegistry.DOMINION_BERRY_BUSH)
                .withPedestalItem(3, Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(AugmentExtendTime.INSTANCE)))
                .withPedestalItem(2, Recipes.DOMINION_GEM_BLOCK)
                .withPedestalItem(1, Ingredient.of(Tags.Items.STORAGE_BLOCKS_LAPIS))
                .buildPsyglyphicRecipe(EnchantmentRegistry.DOMINION_REGEN_ENCHANTMENT, 3, 5000));

        addRecipe(builder()
                .withResult(MagicItemsRegistry.LUSTERIC_SHIELD)
                .withReagent(Items.SHIELD)
                .withPedestalItem(2, Ingredient.of(Tags.Items.STORAGE_BLOCKS_GOLD))
                .withPedestalItem(2, Recipes.DOMINION_GEM)
                .build());

        addRecipe(builder()
                .withResult(MagicItemsRegistry.SUMMONERS_STRENGTH)
                .withReagent(Recipes.DOMINION_GEM_BLOCK)
                .withPedestalItem(ItemInit.BONE_FRAGMENT.get())
                .withPedestalItem(Ingredient.of(Tags.Items.INGOTS_GOLD))
                .build());

        addRecipe(builder()
                .withResult(BlockRegistry.RELAY_DEPOSIT)
                .withReagent(BlockRegistry.EMORTIC_RELAY)
                .withPedestalItem(4,Ingredient.of(Items.HOPPER))
                .build());
    }

    public void addRecipe(PsyglyphicAmalgamatorRecipe recipe){
        recipes.add(recipe);
    }

    private static Path getRecipePath(Path pathIn, Item item) {
        return getRecipePath(pathIn, item.getRegistryName().getPath());
    }

    private static Path getRecipePath(Path pathIn, String str){
        return pathIn.resolve("data/rigoranthusemortisreborn/recipes/amalgamator/" + str + ".json");
    }
    @Override
    public String getName() {
        return new TranslationTextComponent("block.rigoranthusemortisreborn.psyglyphic_amalgamator").getString();
    }
}




















/*        addRecipe(builder()
                .withPedestalItem(2, Ingredient.of(Tags.Items.STORAGE_BLOCKS_QUARTZ))
                .withPedestalItem(1,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(AugmentAmplify.INSTANCE)))
                .withPedestalItem(1, Ingredient.of(Tags.Items.STORAGE_BLOCKS_LAPIS))
                .buildPsyglyphicRecipe(Enchantments.SHARPNESS, 3, 500));

        addRecipe(builder()
                .withPedestalItem(2, Ingredient.of(Tags.Items.STORAGE_BLOCKS_QUARTZ))
                .withPedestalItem(2, Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(AugmentAmplify.INSTANCE)))
                .withPedestalItem(1, Ingredient.of(Tags.Items.STORAGE_BLOCKS_LAPIS))
                .buildPsyglyphicRecipe(Enchantments.SHARPNESS, 4, 6500));

        addRecipe(builder()
                .withPedestalItem(2, Ingredient.of(Tags.Items.STORAGE_BLOCKS_QUARTZ))
                .withPedestalItem(2, Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(AugmentAmplify.INSTANCE)))
                .withPedestalItem(2, Ingredient.of(Tags.Items.STORAGE_BLOCKS_LAPIS))
                .buildPsyglyphicRecipe(Enchantments.SHARPNESS, 5, 8000));

        addRecipe(builder()
                .withPedestalItem(2, Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(AugmentExtract.INSTANCE)))
                .withPedestalItem(2, Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(AugmentAmplify.INSTANCE)))
                .withPedestalItem(2, Recipes.DOMINION_GEM_BLOCK)
                .withPedestalItem(2, Ingredient.of(Tags.Items.STORAGE_BLOCKS_LAPIS))
                .buildPsyglyphicRecipe(Enchantments.SILK_TOUCH, 1, 9000));

        addRecipe(builder()
                .withPedestalItem(1, Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(AugmentAOE.INSTANCE)))
                .withPedestalItem(1, Recipes.DOMINION_GEM_BLOCK)
                .withPedestalItem(1, Ingredient.of(Tags.Items.STORAGE_BLOCKS_LAPIS))
                .buildPsyglyphicRecipe(Enchantments.SWEEPING_EDGE, 1, 2000));
        addRecipe(builder()
                .withPedestalItem(2, Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(AugmentAOE.INSTANCE)))
                .withPedestalItem(2, Recipes.DOMINION_GEM_BLOCK)
                .withPedestalItem(2, Ingredient.of(Tags.Items.STORAGE_BLOCKS_LAPIS))
                .buildPsyglyphicRecipe(Enchantments.SWEEPING_EDGE, 2, 3500));

        addRecipe(builder()
                .withPedestalItem(3, Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(AugmentAOE.INSTANCE)))
                .withPedestalItem(2, Recipes.DOMINION_GEM_BLOCK)
                .withPedestalItem(3, Ingredient.of(Tags.Items.STORAGE_BLOCKS_LAPIS))
                .buildPsyglyphicRecipe(Enchantments.SWEEPING_EDGE, 3, 5000));

        addRecipe(builder()
                .withPedestalItem(1, Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(EffectShield.INSTANCE)))
                .withPedestalItem(1, Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(AugmentAmplify.INSTANCE)))
                .withPedestalItem(1, Recipes.DOMINION_GEM_BLOCK)
                .withPedestalItem(1, Ingredient.of(Tags.Items.STORAGE_BLOCKS_LAPIS))
                .buildPsyglyphicRecipe(Enchantments.UNBREAKING, 1, 2000));

        addRecipe(builder()
                .withPedestalItem(1, Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(EffectShield.INSTANCE)))
                .withPedestalItem(2, Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(AugmentAmplify.INSTANCE)))
                .withPedestalItem(2, Recipes.DOMINION_GEM_BLOCK)
                .withPedestalItem(1, Ingredient.of(Tags.Items.STORAGE_BLOCKS_LAPIS))
                .buildPsyglyphicRecipe(Enchantments.UNBREAKING, 2, 3500));

        addRecipe(builder()
                .withPedestalItem(1, Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(EffectShield.INSTANCE)))
                .withPedestalItem(3, Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(AugmentAmplify.INSTANCE)))
                .withPedestalItem(2, Recipes.DOMINION_GEM_BLOCK)
                .withPedestalItem(2, Ingredient.of(Tags.Items.STORAGE_BLOCKS_LAPIS))
                .buildPsyglyphicRecipe(Enchantments.UNBREAKING, 3, 5000));

        addRecipe(builder()
                .withResult(MagicItemsRegistry.WAND)
                .withReagent(ItemInit.FORGOTTEN_RECORD.get())
                .withPedestalItem(4, Recipes.DOMINION_GEM)
                .withPedestalItem(2, Ingredient.of(ItemInit.PSYGLYPHIC_SCRIPT.get()))
                .withPedestalItem(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(MethodProjectile.INSTANCE))
                .withPedestalItem(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(AugmentAccelerate.INSTANCE))
                .build());

        addRecipe(builder()
                .withResult(new ItemStack(MagicItemsRegistry.AMPLIFY_ARROW, 32))
                .withReagent( Ingredient.of(ItemTags.ARROWS))
                .withPedestalItem(2, Recipes.DOMINION_GEM_BLOCK)
                .withPedestalItem(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(AugmentAmplify.INSTANCE))
                .build());
        addRecipe(builder()
                .withResult(new ItemStack(MagicItemsRegistry.SPLIT_ARROW, 32))
                .withReagent( Ingredient.of(ItemTags.ARROWS))
                .withPedestalItem(2, Recipes.DOMINION_GEM_BLOCK)
                .withPedestalItem(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(AugmentSplit.INSTANCE))
                .build());
        addRecipe(builder()
                .withResult(new ItemStack(MagicItemsRegistry.PIERCE_ARROW, 32))
                .withReagent( Ingredient.of(ItemTags.ARROWS))
                .withPedestalItem(2, Recipes.DOMINION_GEM_BLOCK)
                .withPedestalItem(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(AugmentPierce.INSTANCE))
                .build());
        addRecipe(builder()
                .withPedestalItem(4, Ingredient.of(ItemTags.FISHES))
                .withPedestalItem(2, Recipes.DOMINION_GEM_BLOCK)
                .withPedestalItem(2, Ingredient.of(Tags.Items.STORAGE_BLOCKS_LAPIS))
                .buildPsyglyphicRecipe(Enchantments.AQUA_AFFINITY, 1, 5000));

        addRecipe(builder()
                .withPedestalItem(4, Ingredient.of(Items.BLAZE_POWDER))
                .withPedestalItem(1,Ingredient.of(Tags.Items.STORAGE_BLOCKS_GOLD))
                .withPedestalItem(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(AugmentExtendTime.INSTANCE))
                .withPedestalItem(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(AugmentAOE.INSTANCE))
                .withPedestalItem(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(AugmentDampen.INSTANCE))
                .buildPsyglyphicRecipe(EnchantmentRegistry.REFLEX_SUMMON_ENCHANTMENT, 2, 6000));

        addRecipe(builder()
                .withPedestalItem(4, MagicItemsRegistry.DWELLER_FLESH)
                .withPedestalItem(1,Ingredient.of(Tags.Items.ENDER_PEARLS))
                .withPedestalItem(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(AugmentPierce.INSTANCE))
                .withPedestalItem(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(AugmentExtract.INSTANCE))
                .withPedestalItem(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(AugmentFortune.INSTANCE))
                .buildPsyglyphicRecipe(EnchantmentRegistry.REFLEX_SUMMON_ENCHANTMENT, 3, 9000));

        addRecipe(builder()
                .withPedestalItem(2, Ingredient.of(Items.SPIDER_EYE))
                .withPedestalItem(1, Recipes.DOMINION_GEM_BLOCK)
                .withPedestalItem(1, Ingredient.of(Tags.Items.STORAGE_BLOCKS_LAPIS))
                .buildPsyglyphicRecipe(Enchantments.BANE_OF_ARTHROPODS, 1, 2000));
        addRecipe(builder()
                .withPedestalItem(3, Ingredient.of(Items.SPIDER_EYE))
                .withPedestalItem(1, Recipes.DOMINION_GEM_BLOCK)
                .withPedestalItem(1, Ingredient.of(Tags.Items.STORAGE_BLOCKS_LAPIS))
                .buildPsyglyphicRecipe(Enchantments.BANE_OF_ARTHROPODS, 2, 3500));

        addRecipe(builder()
                .withPedestalItem(4, Ingredient.of(Items.FERMENTED_SPIDER_EYE))
                .withPedestalItem(1, Recipes.DOMINION_GEM_BLOCK)
                .withPedestalItem(1, Ingredient.of(Tags.Items.STORAGE_BLOCKS_LAPIS))
                .buildPsyglyphicRecipe(Enchantments.BANE_OF_ARTHROPODS, 3, 500));

        addRecipe(builder()
                .withPedestalItem(4, Ingredient.of(Items.FERMENTED_SPIDER_EYE))
                .withPedestalItem(2, Recipes.DOMINION_GEM_BLOCK)
                .withPedestalItem(1, Ingredient.of(Tags.Items.STORAGE_BLOCKS_LAPIS))
                .buildPsyglyphicRecipe(Enchantments.BANE_OF_ARTHROPODS, 4, 6500));

        addRecipe(builder()
                .withPedestalItem(4, Ingredient.of(Items.FERMENTED_SPIDER_EYE))
                .withPedestalItem(2, Recipes.DOMINION_GEM_BLOCK)
                .withPedestalItem(2, Ingredient.of(Tags.Items.STORAGE_BLOCKS_LAPIS))
                .buildPsyglyphicRecipe(Enchantments.BANE_OF_ARTHROPODS, 5, 8000));

        addRecipe(builder()
                .withPedestalItem(2, Ingredient.of(Tags.Items.STORAGE_BLOCKS_IRON))
                .withPedestalItem(1, Recipes.DOMINION_GEM_BLOCK)
                .withPedestalItem(0, Ingredient.of(Tags.Items.STORAGE_BLOCKS_LAPIS))
                .buildPsyglyphicRecipe(Enchantments.BLAST_PROTECTION, 1, 2000));

        addRecipe(builder()
                .withPedestalItem(2, Ingredient.of(Tags.Items.STORAGE_BLOCKS_IRON))
                .withPedestalItem(1, Recipes.DOMINION_GEM_BLOCK)
                .withPedestalItem(2, Ingredient.of(Tags.Items.STORAGE_BLOCKS_LAPIS))
                .buildPsyglyphicRecipe(Enchantments.BLAST_PROTECTION, 2, 4000));
        addRecipe(builder()
                .withPedestalItem(2, Ingredient.of(Tags.Items.GEMS_DIAMOND))
                .withPedestalItem(2, Recipes.DOMINION_GEM_BLOCK)
                .withPedestalItem(2, Ingredient.of(Tags.Items.OBSIDIAN))
                .buildPsyglyphicRecipe(Enchantments.BLAST_PROTECTION, 3, 6000));
        addRecipe(builder()
                .withPedestalItem(4, Ingredient.of(Tags.Items.GEMS_DIAMOND))
                .withPedestalItem(2, Recipes.DOMINION_GEM_BLOCK)
                .withPedestalItem(2, Ingredient.of(Tags.Items.STORAGE_BLOCKS_GOLD))
                .buildPsyglyphicRecipe(Enchantments.BLAST_PROTECTION, 4, 8000));

        addRecipe(builder()
                .withPedestalItem(2,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(EffectAquatic.INSTANCE)))
                .withPedestalItem(2, Recipes.DOMINION_GEM_BLOCK)
                .buildPsyglyphicRecipe(Enchantments.DEPTH_STRIDER, 1, 3000));
        addRecipe(builder()
                .withPedestalItem(2,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(EffectAquatic.INSTANCE)))
                .withPedestalItem(2, Recipes.DOMINION_GEM_BLOCK)
                .withPedestalItem(2, Ingredient.of(Items.PRISMARINE_SHARD))
                .buildPsyglyphicRecipe(Enchantments.DEPTH_STRIDER, 2, 6000));
        addRecipe(builder()
                .withPedestalItem(4,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(EffectAquatic.INSTANCE)))
                .withPedestalItem(3, Recipes.DOMINION_GEM_BLOCK)
                .withPedestalItem(1, Ingredient.of(Items.HEART_OF_THE_SEA))
                .buildPsyglyphicRecipe(Enchantments.DEPTH_STRIDER, 3, 9000));


        addRecipe(builder()
                .withPedestalItem(1,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(EffectSlowfall.INSTANCE)))
                .withPedestalItem(1,  Ingredient.of(Tags.Items.SLIMEBALLS))
                .withPedestalItem(2, Recipes.DOMINION_GEM_BLOCK)
                .buildPsyglyphicRecipe(Enchantments.FALL_PROTECTION, 1, 2000));
        addRecipe(builder()
                .withPedestalItem(2,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(EffectSlowfall.INSTANCE)))
                .withPedestalItem(1,  Ingredient.of(Tags.Items.SLIMEBALLS))
                .withPedestalItem(2, Recipes.DOMINION_GEM_BLOCK)
                .buildPsyglyphicRecipe(Enchantments.FALL_PROTECTION, 2, 3500));
        addRecipe(builder()
                .withPedestalItem(3,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(EffectSlowfall.INSTANCE)))
                .withPedestalItem(1,  Ingredient.of(Tags.Items.SLIMEBALLS))
                .withPedestalItem(2, Recipes.DOMINION_GEM_BLOCK)
                .withPedestalItem(2,  Ingredient.of(Tags.Items.STORAGE_BLOCKS_LAPIS))
                .buildPsyglyphicRecipe(Enchantments.FALL_PROTECTION, 3, 5000));
        addRecipe(builder()
                .withPedestalItem(4,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(EffectSlowfall.INSTANCE)))
                .withPedestalItem(2, Recipes.DOMINION_GEM_BLOCK)
                .withPedestalItem(2,  Ingredient.of(Tags.Items.STORAGE_BLOCKS_LAPIS))
                .buildPsyglyphicRecipe(Enchantments.FALL_PROTECTION, 4, 6500));

        addRecipe(builder()
                .withPedestalItem(1,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(EffectIgnite.INSTANCE)))
                .withPedestalItem(2, Recipes.DOMINION_GEM_BLOCK)
                .withPedestalItem(2,  Ingredient.of(Tags.Items.STORAGE_BLOCKS_LAPIS))
                .buildPsyglyphicRecipe(Enchantments.FIRE_ASPECT, 1, 2000));

        addRecipe(builder()
                .withPedestalItem(2,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(EffectIgnite.INSTANCE)))
                .withPedestalItem(2, Recipes.DOMINION_GEM_BLOCK)
                .withPedestalItem(2,  Ingredient.of(Tags.Items.STORAGE_BLOCKS_LAPIS))
                .buildPsyglyphicRecipe(Enchantments.FIRE_ASPECT, 2, 4000));

        addRecipe(builder()
                .withPedestalItem(1,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(EffectIgnite.INSTANCE)))
                .withPedestalItem(1,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(EffectShield.INSTANCE)))
                .withPedestalItem(2, Recipes.DOMINION_GEM_BLOCK)
                .buildPsyglyphicRecipe(Enchantments.FIRE_PROTECTION, 1, 2000));
        addRecipe(builder()
                .withPedestalItem(2,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(EffectIgnite.INSTANCE)))
                .withPedestalItem(1,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(EffectShield.INSTANCE)))
                .withPedestalItem(2, Recipes.DOMINION_GEM_BLOCK)
                .buildPsyglyphicRecipe(Enchantments.FIRE_PROTECTION, 2, 3500));
        addRecipe(builder()
                .withPedestalItem(2,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(EffectIgnite.INSTANCE)))
                .withPedestalItem(1,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(EffectShield.INSTANCE)))
                .withPedestalItem(3, Recipes.DOMINION_GEM_BLOCK)
                .withPedestalItem(1,  Ingredient.of(Tags.Items.RODS_BLAZE))
                .buildPsyglyphicRecipe(Enchantments.FIRE_PROTECTION, 3, 5000));
        addRecipe(builder()
                .withPedestalItem(2,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(EffectIgnite.INSTANCE)))
                .withPedestalItem(2,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(EffectShield.INSTANCE)))
                .withPedestalItem(2, Recipes.DOMINION_GEM_BLOCK)
                .withPedestalItem(2,  Ingredient.of(Tags.Items.RODS_BLAZE))
                .buildPsyglyphicRecipe(Enchantments.FIRE_PROTECTION, 4, 6500));

        addRecipe(builder()
                .withPedestalItem(3,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(EffectIgnite.INSTANCE)))
                .withPedestalItem(3,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(EffectShield.INSTANCE)))
                .withPedestalItem(1, Recipes.DOMINION_GEM_BLOCK)
                .withPedestalItem(1, Ingredient.of(Tags.Items.RODS_BLAZE))
                .buildPsyglyphicRecipe(Enchantments.FIRE_PROTECTION, 5, 8000));

        addRecipe(builder()
                .withPedestalItem(1,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(EffectIgnite.INSTANCE)))
                .withPedestalItem(1,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(MethodProjectile.INSTANCE)))
                .withPedestalItem(1, Ingredient.of(Tags.Items.RODS_BLAZE))
                .buildPsyglyphicRecipe(Enchantments.FLAMING_ARROWS, 1, 5000));

        addRecipe(builder()
                .withPedestalItem(1,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(AugmentFortune.INSTANCE)))
                .withPedestalItem(1,  Ingredient.of(Tags.Items.GEMS_DIAMOND))
                .withPedestalItem(6, Recipes.DOMINION_GEM_BLOCK)
                .buildPsyglyphicRecipe(Enchantments.BLOCK_FORTUNE, 1, 6000));
        addRecipe(builder()
                .withPedestalItem(2,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(AugmentFortune.INSTANCE)))
                .withPedestalItem(4,  Ingredient.of(Tags.Items.GEMS_DIAMOND))
                .withPedestalItem(2, Recipes.DOMINION_GEM_BLOCK)
                .buildPsyglyphicRecipe(Enchantments.BLOCK_FORTUNE, 2, 8000));
        addRecipe(builder()
                .withPedestalItem(3,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(AugmentFortune.INSTANCE)))
                .withPedestalItem(1,  Ingredient.of(Tags.Items.STORAGE_BLOCKS_DIAMOND))
                .withPedestalItem(2,  Ingredient.of(Tags.Items.STORAGE_BLOCKS_LAPIS))
                .withPedestalItem(2, Recipes.DOMINION_GEM_BLOCK)
                .buildPsyglyphicRecipe(Enchantments.BLOCK_FORTUNE, 3, 9000));

        addRecipe(builder()
                .withPedestalItem(7,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(MethodProjectile.INSTANCE)))
                .withPedestalItem(1,  Ingredient.of(Tags.Items.STORAGE_BLOCKS_DIAMOND))
                .buildPsyglyphicRecipe(Enchantments.INFINITY_ARROWS, 1, 9000));

        addRecipe(builder()
                .withPedestalItem(1,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(MethodTouch.INSTANCE)))
                .withPedestalItem(1,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(EffectKnockback.INSTANCE)))
                .withPedestalItem(2, Recipes.DOMINION_GEM_BLOCK)
                .buildPsyglyphicRecipe(Enchantments.KNOCKBACK, 1, 2000));
        addRecipe(builder()
                .withPedestalItem(1,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(MethodTouch.INSTANCE)))
                .withPedestalItem(2,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(EffectKnockback.INSTANCE)))
                .withPedestalItem(2, Recipes.DOMINION_GEM_BLOCK)
                .buildPsyglyphicRecipe(Enchantments.KNOCKBACK, 2, 4000));

        addRecipe(builder()
                .withPedestalItem(1,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(AugmentFortune.INSTANCE)))
                .withPedestalItem(1,  Ingredient.of(Tags.Items.GEMS_EMERALD))
                .withPedestalItem(6, Recipes.DOMINION_GEM_BLOCK)
                .buildPsyglyphicRecipe(Enchantments.MOB_LOOTING, 1, 6000));
        addRecipe(builder()
                .withPedestalItem(2,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(AugmentFortune.INSTANCE)))
                .withPedestalItem(4,  Ingredient.of(Tags.Items.GEMS_EMERALD))
                .withPedestalItem(2, Recipes.DOMINION_GEM_BLOCK)
                .buildPsyglyphicRecipe(Enchantments.MOB_LOOTING, 2, 8000));
        addRecipe(builder()
                .withPedestalItem(3,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(AugmentFortune.INSTANCE)))
                .withPedestalItem(1,  Ingredient.of(Tags.Items.STORAGE_BLOCKS_EMERALD))
                .withPedestalItem(2,  Ingredient.of(Tags.Items.STORAGE_BLOCKS_LAPIS))
                .withPedestalItem(2, Recipes.DOMINION_GEM_BLOCK)
                .buildPsyglyphicRecipe(Enchantments.MOB_LOOTING, 3, 9000));

        addRecipe(builder()
                .withPedestalItem(3,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(AugmentSplit.INSTANCE)))
                .withPedestalItem(5, Recipes.DOMINION_GEM_BLOCK)
                .buildPsyglyphicRecipe(Enchantments.MULTISHOT, 1, 9000));

        addRecipe(builder()
                .withPedestalItem(1,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(AugmentPierce.INSTANCE)))
                .withPedestalItem(2, Recipes.DOMINION_GEM_BLOCK)
                .withPedestalItem(2,  Ingredient.of(Tags.Items.STORAGE_BLOCKS_LAPIS))
                .buildPsyglyphicRecipe(Enchantments.PIERCING, 1, 2500));
        addRecipe(builder()
                .withPedestalItem(2,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(AugmentPierce.INSTANCE)))
                .withPedestalItem(2, Recipes.DOMINION_GEM_BLOCK)
                .withPedestalItem(2,  Ingredient.of(Tags.Items.STORAGE_BLOCKS_LAPIS))
                .buildPsyglyphicRecipe(Enchantments.PIERCING, 2, 5000));
        addRecipe(builder()
                .withPedestalItem(3,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(AugmentPierce.INSTANCE)))
                .withPedestalItem(2, Recipes.DOMINION_GEM_BLOCK)
                .withPedestalItem(2,  Ingredient.of(Tags.Items.STORAGE_BLOCKS_LAPIS))
                .buildPsyglyphicRecipe(Enchantments.PIERCING, 3, 7500));
        addRecipe(builder()
                .withPedestalItem(4,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(AugmentPierce.INSTANCE)))
                .withPedestalItem(2, Recipes.DOMINION_GEM_BLOCK)
                .withPedestalItem(2,  Ingredient.of(Tags.Items.STORAGE_BLOCKS_LAPIS))
                .buildPsyglyphicRecipe(Enchantments.PIERCING, 4, 9000));

        addRecipe(builder()
                .withPedestalItem(1,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(AugmentAmplify.INSTANCE)))
                .withPedestalItem(1,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(MethodProjectile.INSTANCE)))
                .withPedestalItem(1, Recipes.DOMINION_GEM_BLOCK)
                .buildPsyglyphicRecipe(Enchantments.POWER_ARROWS, 1, 2000));
        addRecipe(builder()
                .withPedestalItem(2,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(AugmentAmplify.INSTANCE)))
                .withPedestalItem(1,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(MethodProjectile.INSTANCE)))
                .withPedestalItem(2, Recipes.DOMINION_GEM_BLOCK)
                .buildPsyglyphicRecipe(Enchantments.POWER_ARROWS, 2, 3500));
        addRecipe(builder()
                .withPedestalItem(3,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(AugmentAmplify.INSTANCE)))
                .withPedestalItem(1,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(MethodProjectile.INSTANCE)))
                .withPedestalItem(2, Recipes.DOMINION_GEM_BLOCK)
                .withPedestalItem(2,  Ingredient.of(Tags.Items.STORAGE_BLOCKS_LAPIS))
                .buildPsyglyphicRecipe(Enchantments.POWER_ARROWS, 3, 5000));
        addRecipe(builder()
                .withPedestalItem(4,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(AugmentAmplify.INSTANCE)))
                .withPedestalItem(1,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(MethodProjectile.INSTANCE)))
                .withPedestalItem(1, Recipes.DOMINION_GEM_BLOCK)
                .withPedestalItem(2,  Ingredient.of(Tags.Items.STORAGE_BLOCKS_LAPIS))
                .buildPsyglyphicRecipe(Enchantments.POWER_ARROWS, 4, 6500));
        addRecipe(builder()
                .withPedestalItem(5,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(AugmentAmplify.INSTANCE)))
                .withPedestalItem(1,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(MethodProjectile.INSTANCE)))
                .withPedestalItem(1, Recipes.DOMINION_GEM_BLOCK)
                .withPedestalItem(1,  Ingredient.of(Tags.Items.STORAGE_BLOCKS_LAPIS))
                .buildPsyglyphicRecipe(Enchantments.POWER_ARROWS, 5, 9000));


        addRecipe(builder()
                .withPedestalItem(1,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(MethodProjectile.INSTANCE)))
                .withPedestalItem(1,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(EffectShield.INSTANCE)))
                .withPedestalItem(2, Recipes.DOMINION_GEM_BLOCK)
                .buildPsyglyphicRecipe(Enchantments.PROJECTILE_PROTECTION, 1, 2000));
        addRecipe(builder()
                .withPedestalItem(2,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(MethodProjectile.INSTANCE)))
                .withPedestalItem(1,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(EffectShield.INSTANCE)))
                .withPedestalItem(2, Recipes.DOMINION_GEM_BLOCK)
                .buildPsyglyphicRecipe(Enchantments.PROJECTILE_PROTECTION, 2, 3500));
        addRecipe(builder()
                .withPedestalItem(2,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(MethodProjectile.INSTANCE)))
                .withPedestalItem(1,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(EffectShield.INSTANCE)))
                .withPedestalItem(3, Recipes.DOMINION_GEM_BLOCK)
                .buildPsyglyphicRecipe(Enchantments.PROJECTILE_PROTECTION, 3, 5000));
        addRecipe(builder()
                .withPedestalItem(2,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(MethodProjectile.INSTANCE)))
                .withPedestalItem(2,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(EffectShield.INSTANCE)))
                .withPedestalItem(2, Recipes.DOMINION_GEM_BLOCK)
                .buildPsyglyphicRecipe(Enchantments.PROJECTILE_PROTECTION, 4, 6500));

        addRecipe(builder()
                .withPedestalItem(3,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(MethodProjectile.INSTANCE)))
                .withPedestalItem(3,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(EffectShield.INSTANCE)))
                .withPedestalItem(1, Recipes.DOMINION_GEM_BLOCK)
                .buildPsyglyphicRecipe(Enchantments.PROJECTILE_PROTECTION, 5, 8000));


        addRecipe(builder()
                .withPedestalItem(1,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(MethodTouch.INSTANCE)))
                .withPedestalItem(1,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(EffectShield.INSTANCE)))
                .withPedestalItem(1,  Ingredient.of(Tags.Items.STORAGE_BLOCKS_LAPIS))
                .withPedestalItem(2, Recipes.DOMINION_GEM_BLOCK)
                .buildPsyglyphicRecipe(Enchantments.ALL_DAMAGE_PROTECTION, 1, 2000));
        addRecipe(builder()
                .withPedestalItem(2,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(MethodTouch.INSTANCE)))
                .withPedestalItem(1,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(EffectShield.INSTANCE)))
                .withPedestalItem(2, Recipes.DOMINION_GEM_BLOCK)
                .withPedestalItem(2,  Ingredient.of(Tags.Items.STORAGE_BLOCKS_LAPIS))
                .buildPsyglyphicRecipe(Enchantments.ALL_DAMAGE_PROTECTION, 2, 3500));
        addRecipe(builder()
                .withPedestalItem(2,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(MethodTouch.INSTANCE)))
                .withPedestalItem(1,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(EffectShield.INSTANCE)))
                .withPedestalItem(3, Recipes.DOMINION_GEM_BLOCK)
                .withPedestalItem(2,  Ingredient.of(Tags.Items.STORAGE_BLOCKS_LAPIS))
                .buildPsyglyphicRecipe(Enchantments.ALL_DAMAGE_PROTECTION, 3, 5000));
        addRecipe(builder()
                .withPedestalItem(2,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(MethodTouch.INSTANCE)))
                .withPedestalItem(2,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(EffectShield.INSTANCE)))
                .withPedestalItem(2, Recipes.DOMINION_GEM_BLOCK)
                .withPedestalItem(2,  Ingredient.of(Tags.Items.STORAGE_BLOCKS_LAPIS))
                .buildPsyglyphicRecipe(Enchantments.ALL_DAMAGE_PROTECTION, 4, 6500));

        addRecipe(builder()
                .withPedestalItem(3,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(MethodTouch.INSTANCE)))
                .withPedestalItem(3,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(EffectShield.INSTANCE)))
                .withPedestalItem(1, Recipes.DOMINION_GEM_BLOCK)
                .withPedestalItem(1,  Ingredient.of(Tags.Items.STORAGE_BLOCKS_DIAMOND))
                .buildPsyglyphicRecipe(Enchantments.ALL_DAMAGE_PROTECTION, 5, 8000));


        addRecipe(builder()
                .withPedestalItem(1,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(MethodProjectile.INSTANCE)))
                .withPedestalItem(1,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(EffectKnockback.INSTANCE)))
                .withPedestalItem(2, Recipes.DOMINION_GEM_BLOCK)
                .buildPsyglyphicRecipe(Enchantments.PUNCH_ARROWS, 1, 2000));
        addRecipe(builder()
                .withPedestalItem(1,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(MethodProjectile.INSTANCE)))
                .withPedestalItem(2,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(EffectKnockback.INSTANCE)))
                .withPedestalItem(2, Recipes.DOMINION_GEM_BLOCK)
                .buildPsyglyphicRecipe(Enchantments.PUNCH_ARROWS, 2, 4000));

        addRecipe(builder()
                .withPedestalItem(1,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(MethodProjectile.INSTANCE)))
                .withPedestalItem(1,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(EffectHaste.INSTANCE)))
                .withPedestalItem(2, Recipes.DOMINION_GEM_BLOCK)
                .buildPsyglyphicRecipe(Enchantments.QUICK_CHARGE, 1, 2000));
        addRecipe(builder()
                .withPedestalItem(1,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(MethodProjectile.INSTANCE)))
                .withPedestalItem(2,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(EffectHaste.INSTANCE)))
                .withPedestalItem(2, Recipes.DOMINION_GEM_BLOCK)
                .buildPsyglyphicRecipe(Enchantments.QUICK_CHARGE, 2, 4000));
        addRecipe(builder()
                .withPedestalItem(1,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(MethodProjectile.INSTANCE)))
                .withPedestalItem(2,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(EffectHaste.INSTANCE)))
                .withPedestalItem(2, Recipes.DOMINION_GEM_BLOCK)
                .buildPsyglyphicRecipe(Enchantments.QUICK_CHARGE, 3, 6000));


        addRecipe(builder()
                .withPedestalItem(2,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(EffectAquatic.INSTANCE)))
                .withPedestalItem(2, Recipes.DOMINION_GEM_BLOCK)
                .buildPsyglyphicRecipe(Enchantments.RESPIRATION, 1, 3000));
        addRecipe(builder()
                .withPedestalItem(4,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(EffectAquatic.INSTANCE)))
                .withPedestalItem(4, Recipes.DOMINION_GEM_BLOCK)
                .buildPsyglyphicRecipe(Enchantments.RESPIRATION, 2, 6000));
        addRecipe(builder()
                .withPedestalItem(6,  Ingredient.of(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(EffectAquatic.INSTANCE)))
                .withPedestalItem(2, Recipes.DOMINION_GEM_BLOCK)
                .buildPsyglyphicRecipe(Enchantments.RESPIRATION, 3, 9000));*/
