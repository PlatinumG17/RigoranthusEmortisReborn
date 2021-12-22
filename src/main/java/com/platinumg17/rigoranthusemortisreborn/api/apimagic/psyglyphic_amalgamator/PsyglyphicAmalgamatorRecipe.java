package com.platinumg17.rigoranthusemortisreborn.api.apimagic.psyglyphic_amalgamator;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.PsyglyphicAmalgamatorTile;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.RecipeRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PsyglyphicAmalgamatorRecipe implements IPsyglyphicRecipe {

    public Ingredient reagent; // Used in the splintered pedestal
    public ItemStack result; // Result item
    public List<Ingredient> pedestalItems; // Items part of the recipe
    public String category;
    public ResourceLocation id;
    public int dominionCost;

    public PsyglyphicAmalgamatorRecipe(ItemStack result, Ingredient reagent, List<Ingredient> pedestalItems, String category){
        this.reagent = reagent;
        this.pedestalItems = pedestalItems;
        this.result = result;
        this.category = category;
        dominionCost = 0;
        this.id = new ResourceLocation(EmortisConstants.MOD_ID, result.getItem().getRegistryName().getPath());
    }

    public PsyglyphicAmalgamatorRecipe(ResourceLocation id, List<Ingredient> pedestalItems, Ingredient reagent, ItemStack result) {
        this(id, pedestalItems, reagent, result, 200);
    }

    public PsyglyphicAmalgamatorRecipe(ResourceLocation id, List<Ingredient> pedestalItems, Ingredient reagent, ItemStack result, int cost){
        this.reagent = reagent;
        this.pedestalItems = pedestalItems;
        this.result = result;
        this.category = "";
        dominionCost = cost;
        this.id = id;
    }

    public PsyglyphicAmalgamatorRecipe(){
        reagent = Ingredient.EMPTY;
        result = ItemStack.EMPTY;
        pedestalItems = new ArrayList<>();
        dominionCost = 0;
        this.id = new ResourceLocation(EmortisConstants.MOD_ID, "empty");
    }

    public PsyglyphicAmalgamatorRecipe(Item result, Item reagent, Item[] pedestalItems, String category){
        ArrayList<Ingredient> stacks = new ArrayList<>();
        for(Item i : pedestalItems){
            stacks.add(Ingredient.of(i));
        }
        this.reagent = Ingredient.of(reagent);
        this.result = new ItemStack(result);
        this.pedestalItems = stacks;
        this.category = category;
        dominionCost = 200;
        this.id = new ResourceLocation(EmortisConstants.MOD_ID, result.getRegistryName().getPath());
    }

    @Override
    public boolean isMatch(List<ItemStack> pedestalItems, ItemStack reagent, PsyglyphicAmalgamatorTile psyglyphicAmalgamatorTile, @Nullable PlayerEntity player) {
        pedestalItems = pedestalItems.stream().filter(itemStack -> !itemStack.isEmpty()).collect(Collectors.toList());
        return doesReagentMatch(reagent) && this.pedestalItems.size() == pedestalItems.size() && doItemsMatch(pedestalItems, this.pedestalItems);
    }

    public boolean doesReagentMatch(ItemStack reag){
        return this.reagent.test(reag);
    }

    @Override
    public ItemStack getResult(List<ItemStack> pedestalItems, ItemStack reagent, PsyglyphicAmalgamatorTile psyglyphicAmalgamatorTile) {
        return result.copy();
    }

    // Function to check if both arrays are same
    static boolean doItemsMatch(List<ItemStack> inputs, List<Ingredient> recipeItems) {
        RecipeItemHelper recipeitemhelper = new RecipeItemHelper();
        for(ItemStack i : inputs)
            recipeitemhelper.accountStack(i, 1);

        return inputs.size() == recipeItems.size() && (net.minecraftforge.common.util.RecipeMatcher.findMatches(inputs,  recipeItems) != null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PsyglyphicAmalgamatorRecipe that = (PsyglyphicAmalgamatorRecipe) o;
        return Objects.equals(reagent, that.reagent) &&
                Objects.equals(pedestalItems, that.pedestalItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reagent, pedestalItems);
    }

    @Override
    public String toString() {
        return "PsyglyphicAmalgamatorRecipe{" +
                "catalyst = " + reagent +
                ", result = " + result +
                ", pedestalItems = " + pedestalItems +
                '}';
    }

    public JsonElement asRecipe(){
        JsonObject jsonobject = new JsonObject();
        jsonobject.addProperty("type", "rigoranthusemortisreborn:amalgamator_recipe");
        int counter = 1;
        for(Ingredient i : pedestalItems){
            JsonArray item = new JsonArray();
            item.add(i.toJson());
            jsonobject.add("item_" + counter, item);
            counter++;
        }
        JsonArray reagent =  new JsonArray();
        reagent.add(this.reagent.toJson());
        jsonobject.add("reagent", reagent);

        JsonObject resultObj = new JsonObject();
        resultObj.addProperty("item", this.result.getItem().getRegistryName().toString());
        int count = this.result.getCount();
        if (count > 1) {
            resultObj.addProperty("count", count);
        }
        jsonobject.add("output", resultObj);
        jsonobject.addProperty("dominionCost", dominionCost);
        return jsonobject;
    }

    /**
     * Converts to a patchouli documentation page
     */
    public JsonElement serialize() {
        JsonObject jsonobject = new JsonObject();

        jsonobject.addProperty("name", this.result.getItem().getDescriptionId());
        jsonobject.addProperty("icon",  this.result.getItem().getRegistryName().toString());
        jsonobject.addProperty("category", this.category);
        JsonArray jsonArray = new JsonArray();
        JsonObject descPage = new JsonObject();
        descPage.addProperty("type", "text");
        descPage.addProperty("text",EmortisConstants.MOD_ID + ".page." + this.result.getItem().getRegistryName().toString().replace(EmortisConstants.MOD_ID + ":", ""));
        JsonObject infoPage = new JsonObject();
        infoPage.addProperty("type", "amalgamator_recipe");
        infoPage.addProperty("recipe", this.result.getItem().getRegistryName().toString());

        jsonArray.add(descPage);
        jsonArray.add(infoPage);
        jsonobject.add("pages", jsonArray);
        return jsonobject;
    }

    @Override
    public boolean consumesDominion() {
        return dominionCost() > 0;
    }

    @Override
    public int dominionCost() {
        return dominionCost;
    }

    @Override
    public boolean matches(PsyglyphicAmalgamatorTile tile, World worldIn) {
        return isMatch(tile.getPedestalItems(), tile.catalystItem, tile, null);
    }

    public boolean matches(PsyglyphicAmalgamatorTile tile, World worldIn, @Nullable PlayerEntity playerEntity) {
        return isMatch(tile.getPedestalItems(), tile.catalystItem, tile, playerEntity);
    }

    @Override
    public ItemStack assemble(PsyglyphicAmalgamatorTile inv) {
        return this.result;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return false;
    }

    @Override
    public ItemStack getResultItem() {
        return this.result;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return RecipeRegistry.PSYGLYPHIC_SERIALIZER;
    }

    @Override
    public IRecipeType<?> getType() {
        return Registry.RECIPE_TYPE.get(new ResourceLocation(EmortisConstants.MOD_ID, "amalgamator_recipe"));
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<PsyglyphicAmalgamatorRecipe> {

        @Override
        public PsyglyphicAmalgamatorRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            Ingredient reagent = Ingredient.fromJson(JSONUtils.getAsJsonArray(json, "reagent"));
            ItemStack output = ShapedRecipe.itemFromJson(JSONUtils.getAsJsonObject(json, "output"));
            int cost = json.has("dominionCost") ? JSONUtils.getAsInt(json, "dominionCost") : 0;
            List<Ingredient> stacks = new ArrayList<>();
            for(int i = 1; i < 9; i++){
                if(json.has("item_"+i))
                    stacks.add(Ingredient.fromJson(JSONUtils.getAsJsonArray(json, "item_" + i)));
            }
            return new PsyglyphicAmalgamatorRecipe(recipeId, stacks, reagent, output, cost);
        }
        @Nullable
        @Override
        public PsyglyphicAmalgamatorRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) {
            int length = buffer.readInt();
            Ingredient reagent = Ingredient.fromNetwork(buffer);
            ItemStack output = buffer.readItem();
            List<Ingredient> stacks = new ArrayList<>();
            for(int i = 0; i < length; i++){
                try{ stacks.add(Ingredient.fromNetwork(buffer)); }catch (Exception e){
                    e.printStackTrace();
                    break;
                }
            }
            int cost = buffer.readInt();
            return new PsyglyphicAmalgamatorRecipe(recipeId, stacks, reagent, output, cost);
        }
        @Override
        public void toNetwork(PacketBuffer buf, PsyglyphicAmalgamatorRecipe recipe) {
            buf.writeInt(recipe.pedestalItems.size());
            recipe.reagent.toNetwork(buf);
            buf.writeItem(recipe.result);
            for(Ingredient i : recipe.pedestalItems){
                i.toNetwork(buf);
            }
            buf.writeInt(recipe.dominionCost);
        }
    }
}