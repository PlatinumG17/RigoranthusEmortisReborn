package com.platinumg17.rigoranthusemortisreborn.magica.common.datagen;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.recipe.CraftingPressRecipe;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.core.init.ItemInit;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CraftingPressRecipeProvider implements IDataProvider {

    private final DataGenerator generator;
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();
    private static final Logger LOGGER = LogManager.getLogger();

    public CraftingPressRecipeProvider(DataGenerator generatorIn) {
        this.generator = generatorIn;
    }

    List<CraftingPressRecipe> recipes = new ArrayList<>();

    @Override
    public void run(DirectoryCache cache) throws IOException {
        addEntries();
        Path output = this.generator.getOutputFolder();
        for (CraftingPressRecipe g : recipes) {
                System.out.println(g);
                Path path = getRecipePath(output, ((CraftingPressRecipe) g).getId().getPath());
                IDataProvider.save(GSON, cache, ((CraftingPressRecipe) g).asRecipe(), path);

                if (g.getResultItem().isEmpty())
                    continue;
                Path path1 = getPressPath(output, (CraftingPressRecipe) g);
                try {
                    IDataProvider.save(GSON, cache, ((CraftingPressRecipe) g).serialize(), path1);
                    System.out.println(g);
                } catch (IOException ioexception) {
                    LOGGER.error("Couldn't save Crafting Press {}", path1, ioexception);
                }
        }
    }

    private static Path getPressPath(Path pathIn, CraftingPressRecipe e) {
        System.out.println(e.output.getItem().toString());
        return pathIn.resolve("data/rigoranthusemortisreborn/emortic_crafting_press/" + e.output.getItem().getRegistryName().toString().replace(EmortisConstants.MOD_ID + ":", "") + ".json");
    }

    public CraftingPressRecipeBuilder builder() {
        return CraftingPressRecipeBuilder.builder();
    }

    public void addEntries() {

        addRecipe(builder()
                .withResult(ItemInit.GHAST_IRON_INGOT.get())
                .withReagent(Ingredient.of(Items.GHAST_TEAR))
                .withBaseItem(Ingredient.of(Items.IRON_INGOT))
                .build());

    }

    public void addRecipe(CraftingPressRecipe recipe) {
        recipes.add(recipe);
    }

    private static Path getRecipePath(Path pathIn, Item item) {
        return getRecipePath(pathIn, item.getRegistryName().getPath());
    }

    private static Path getRecipePath(Path pathIn, String str) {
        return pathIn.resolve("data/rigoranthusemortisreborn/recipes/" + str + ".json");
    }

    @Override
    public String getName() {
        return "Emortic Crafting Press";
    }
}