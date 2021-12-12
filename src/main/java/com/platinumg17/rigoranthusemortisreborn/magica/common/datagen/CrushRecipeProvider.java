package com.platinumg17.rigoranthusemortisreborn.magica.common.datagen;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.platinumg17.rigoranthusemortisreborn.magica.common.crafting.CrushRecipe;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CrushRecipeProvider implements IDataProvider {
    private final DataGenerator generator;
    List<CrushRecipe> recipes = new ArrayList<>();
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();
    private static final Logger LOGGER = LogManager.getLogger();

    public CrushRecipeProvider(DataGenerator generatorIn){
        this.generator = generatorIn;
    }

    @Override
    public void run(DirectoryCache cache) throws IOException {
        recipes.add(new CrushRecipe("stone", Ingredient.of(Tags.Items.STONE))
                .withItems(Items.GRAVEL.getDefaultInstance(), 1.0f));
        recipes.add(new CrushRecipe("gravel", Ingredient.of(Tags.Items.GRAVEL))
                .withItems(Items.SAND.getDefaultInstance(), 1.0f)
                .withItems(Items.FLINT.getDefaultInstance(), 0.02f));

        recipes.add(new CrushRecipe("white_dye", Ingredient.of(Items.LILY_OF_THE_VALLEY)).withItems(new ItemStack(Items.WHITE_DYE,2)));
        recipes.add(new CrushRecipe("orange_dye", Ingredient.of(Items.ORANGE_TULIP)).withItems(new ItemStack(Items.ORANGE_DYE,2)));
        recipes.add(new CrushRecipe("magenta_dye", Ingredient.of(Items.ALLIUM)).withItems(new ItemStack(Items.MAGENTA_DYE,2)));
        recipes.add(new CrushRecipe("light_blue_dye", Ingredient.of(Items.BLUE_ORCHID)).withItems(new ItemStack(Items.LIGHT_BLUE_DYE,2)));
        recipes.add(new CrushRecipe("yellow_dye", Ingredient.of(Items.DANDELION)).withItems(new ItemStack(Items.YELLOW_DYE,2)));
        recipes.add(new CrushRecipe("pink_dye", Ingredient.of(Items.PINK_TULIP)).withItems(new ItemStack(Items.PINK_DYE,2)));
        recipes.add(new CrushRecipe("light_gray_dye_oxeye", Ingredient.of(Items.OXEYE_DAISY)).withItems(new ItemStack(Items.LIGHT_GRAY_DYE,2)));
        recipes.add(new CrushRecipe("light_gray_dye_azure", Ingredient.of(Items.AZURE_BLUET)).withItems(new ItemStack(Items.LIGHT_GRAY_DYE,2)));
        recipes.add(new CrushRecipe("light_gray_dye_tulip", Ingredient.of(Items.WHITE_TULIP)).withItems(new ItemStack(Items.LIGHT_GRAY_DYE,2)));
        recipes.add(new CrushRecipe("blue_dye", Ingredient.of(Items.CORNFLOWER)).withItems(new ItemStack(Items.BLUE_DYE, 2)));
        recipes.add(new CrushRecipe("brown_dye", Ingredient.of(Items.COCOA_BEANS)).withItems(new ItemStack(Items.BROWN_DYE,2)));
        recipes.add(new CrushRecipe("red_dye_tulip", Ingredient.of(Items.RED_TULIP)).withItems(new ItemStack(Items.RED_DYE,2)));
        recipes.add(new CrushRecipe("red_dye_beetroot", Ingredient.of(Items.BEETROOT)).withItems(new ItemStack(Items.RED_DYE,2)));
        recipes.add(new CrushRecipe("red_dye_poppy", Ingredient.of(Items.POPPY)).withItems(new ItemStack(Items.RED_DYE,2)));
        recipes.add(new CrushRecipe("red_dye_rose_bush", Ingredient.of(Items.ROSE_BUSH)).withItems(new ItemStack(Items.RED_DYE,4)));

        recipes.add(new CrushRecipe("terracotta", Ingredient.of(Items.TERRACOTTA)).withItems(Items.RED_SAND.getDefaultInstance()));
        recipes.add(new CrushRecipe("sugar_cane", Ingredient.of(Items.SUGAR_CANE)).withItems(new ItemStack(Items.SUGAR, 2)));
        Path output = this.generator.getOutputFolder();
        for(CrushRecipe g : recipes){
            Path path = getRecipePath(output, g.getId().getPath());
            IDataProvider.save(GSON, cache,  g.asRecipe(), path);
        }
    }

    private static Path getRecipePath(Path pathIn, String str){
        return pathIn.resolve("data/rigoranthusemortisreborn/recipes/" + str + ".json");
    }
    @Override
    public String getName() {
        return "Crush";
    }
}