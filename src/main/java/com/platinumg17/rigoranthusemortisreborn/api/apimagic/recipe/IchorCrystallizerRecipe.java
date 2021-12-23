package com.platinumg17.rigoranthusemortisreborn.api.apimagic.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.IchorCrystallizerTile;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.RecipeRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;
import java.util.Objects;

public class IchorCrystallizerRecipe implements IIchoricRecipe {

    public String category;
    public ResourceLocation id;
    public Ingredient base;    // Item part of the recipe
    public Ingredient reagent; // Placed in the claw grip
    public ItemStack output;  // Result item
    public int dominionCost;

    public IchorCrystallizerRecipe(ItemStack output, Ingredient reagent, Ingredient base, String category) {
        this.reagent = reagent;
        this.base = base;
        this.output = output;
        this.category = category;
        this.dominionCost = 0;
        this.id = new ResourceLocation(EmortisConstants.MOD_ID, output.getItem().getRegistryName().getPath());
    }

    public IchorCrystallizerRecipe(ResourceLocation id, Ingredient base, Ingredient reagent, ItemStack output) {
        this(id, output, base, reagent, 200);
    }

    public IchorCrystallizerRecipe(ResourceLocation id, ItemStack output, Ingredient reagent, Ingredient base, int dominionCost) {
        this.reagent = reagent;
        this.base = base;
        this.output = output;
        this.category = "";
        this.dominionCost = dominionCost;
        this.id = id;
    }

    public IchorCrystallizerRecipe() {
        base = Ingredient.EMPTY;
        reagent = Ingredient.EMPTY;
        output = ItemStack.EMPTY;
        dominionCost = 0;
        this.id = new ResourceLocation(EmortisConstants.MOD_ID, "empty");
    }

    public IchorCrystallizerRecipe(Item output, Item base, Item reagent, String category) {
        this.base = Ingredient.of(base);
        this.reagent = Ingredient.of(reagent);
        this.output = new ItemStack(output);
        this.category = category;

        this.id = new ResourceLocation(EmortisConstants.MOD_ID, output.getRegistryName().getPath());
    }

    @Override
    public boolean isMatch(ItemStack base, ItemStack reagent, IchorCrystallizerTile crystallizerTile, @Nullable PlayerEntity player) {
        return doesReagentMatch(reagent) && doesBaseMatch(base);
    }
    public boolean doesReagentMatch(ItemStack reagent){
        return this.reagent.test(reagent);
    }
    public boolean doesBaseMatch(ItemStack base){
        return this.base.test(base);
    }

    public boolean matches(IchorCrystallizerTile tile, World worldIn) {
        return isMatch(tile.baseMaterial, tile.reagentItem, tile, null);
    }
    public boolean matches(IchorCrystallizerTile tile, World worldIn, @Nullable PlayerEntity playerEntity) {
        return isMatch(tile.baseMaterial, tile.reagentItem, tile, playerEntity);
    }

    public ItemStack getResult(ItemStack base, ItemStack reagent, IchorCrystallizerTile crystallizerTile) {
        return output.copy();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IchorCrystallizerRecipe that = (IchorCrystallizerRecipe) o;
        return Objects.equals(reagent, that.reagent) &&
                Objects.equals(base, that.base);
    }
    @Override
    public int hashCode() {
        return Objects.hash(reagent, base);
    }

    @Override
    public String toString() {
        return "IchorCrystallizerRecipe{" +
                "reagent = " + reagent +
                ", output = " + output +
                ", base = " + base +
                '}';
    }

    public JsonElement asRecipe() {
        JsonObject jsonobject = new JsonObject();
        jsonobject.addProperty("type", "rigoranthusemortisreborn:ichor_crystallizer_recipe");

        JsonArray base =  new JsonArray();
        base.add(this.base.toJson());
        jsonobject.add("base", base);

        JsonArray reagent =  new JsonArray();
        reagent.add(this.reagent.toJson());
        jsonobject.add("reagent", reagent);

        JsonObject resultObj = new JsonObject();
        resultObj.addProperty("item", this.output.getItem().getRegistryName().toString());
        int count = this.output.getCount();
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
        jsonobject.addProperty("name", this.output.getItem().getDescriptionId());
        jsonobject.addProperty("icon", this.output.getItem().getRegistryName().toString());
        jsonobject.addProperty("category", this.category);
        JsonArray jsonArray = new JsonArray();
        JsonObject descPage = new JsonObject();
        descPage.addProperty("type", "text");
        descPage.addProperty("text", EmortisConstants.MOD_ID + ".page." + this.output.getItem().getRegistryName().toString().replace(EmortisConstants.MOD_ID + ":", ""));
        JsonObject infoPage = new JsonObject();
        infoPage.addProperty("type", "ichor_crystallizer_recipe");
        infoPage.addProperty("recipe", this.output.getItem().getRegistryName().toString());

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

    @Override public ItemStack assemble(IchorCrystallizerTile inv) {
        return this.output;
    }
    @Override public boolean canCraftInDimensions(int width, int height) {
        return false;
    }
    @Override public ItemStack getResultItem() { return this.output; }
    @Override public ResourceLocation getId() { return id; }

    @Override
    public IRecipeSerializer<?> getSerializer() { return RecipeRegistry.CRYSTALLIZER_SERIALIZER; }
    @Override
    public IRecipeType<?> getType() {
        return Registry.RECIPE_TYPE.get(new ResourceLocation(EmortisConstants.MOD_ID, "ichor_crystallizer_recipe"));
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<IchorCrystallizerRecipe> {

        @Override
        public IchorCrystallizerRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            Ingredient base = Ingredient.fromJson(JSONUtils.getAsJsonObject(json, "base"));
            Ingredient reagent = Ingredient.fromJson(JSONUtils.getAsJsonObject(json, "reagent"));
            ItemStack output = ShapedRecipe.itemFromJson(JSONUtils.getAsJsonObject(json, "output"));

            int cost = json.has("dominionCost") ? JSONUtils.getAsInt(json, "dominionCost") : 0;
            return new IchorCrystallizerRecipe(recipeId, output, base, reagent, cost);
        }

        @Nullable
        @Override
        public IchorCrystallizerRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) {
            Ingredient base = Ingredient.fromNetwork(buffer);
            Ingredient reagent = Ingredient.fromNetwork(buffer);
            ItemStack output = buffer.readItem();

            int cost = buffer.readInt();
            return new IchorCrystallizerRecipe(recipeId, output, base, reagent, cost);
        }

        @Override
        public void toNetwork(PacketBuffer buf, IchorCrystallizerRecipe recipe) {
            recipe.base.toNetwork(buf);
            recipe.reagent.toNetwork(buf);
            buf.writeItem(recipe.output);
            buf.writeInt(recipe.dominionCost);
        }
    }
}