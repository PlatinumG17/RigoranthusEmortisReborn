package com.platinumg17.rigoranthusemortisreborn.api.apimagic.recipe;

import com.google.gson.JsonObject;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.RecipeRegistry;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public class IchorCrystallizerRecipe implements IRecipe<IInventory> {

    private final ResourceLocation id;
    public final ItemStack reagent;
    public final ItemStack output;

    public IchorCrystallizerRecipe(ResourceLocation id, ItemStack reagent, ItemStack output) {
        this.id = id;
        this.reagent = reagent;
        this.output = output;
    }

    @Override public boolean matches(IInventory inv, World worldIn) {
        return false;
    }
    @Override public ItemStack assemble(IInventory inv) {
        return output;
    }
    @Override public boolean canCraftInDimensions(int width, int height) {
        return false;
    }
    @Override public ItemStack getResultItem() {
        return output;
    }
    @Override public ResourceLocation getId() {
        return id;
    }
    @Override public IRecipeSerializer<?> getSerializer() {
        return RecipeRegistry.CRYSTALLIZER_SERIALIZER;
    }

    @Override
    public IRecipeType<?> getType() {
        return Registry.RECIPE_TYPE.get(new ResourceLocation(EmortisConstants.MOD_ID, "ichor_crystallizer_recipe"));
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<IchorCrystallizerRecipe> {
        @Override
        public IchorCrystallizerRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            ItemStack input = new ItemStack(JSONUtils.getAsItem(json, "input"));
            ItemStack output = new ItemStack(JSONUtils.getAsItem(json, "output"));
            return new IchorCrystallizerRecipe(recipeId, input, output);
        }
        @Nullable
        @Override
        public IchorCrystallizerRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) {
            ItemStack input = buffer.readItem();
            ItemStack output = buffer.readItem();
            return new IchorCrystallizerRecipe(recipeId, input, output);
        }
        @Override
        public void toNetwork(PacketBuffer buf, IchorCrystallizerRecipe recipe) {
            buf.writeItem(recipe.reagent);
            buf.writeItem(recipe.output);
        }
    }
}