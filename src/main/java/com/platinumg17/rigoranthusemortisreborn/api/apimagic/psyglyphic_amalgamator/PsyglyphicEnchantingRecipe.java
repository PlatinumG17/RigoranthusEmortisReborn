package com.platinumg17.rigoranthusemortisreborn.api.apimagic.psyglyphic_amalgamator;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.PsyglyphicAmalgamatorTile;
import com.platinumg17.rigoranthusemortisreborn.magica.common.util.PortUtil;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.RecipeRegistry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.Collectors;

public class PsyglyphicEnchantingRecipe extends PsyglyphicAmalgamatorRecipe {
    public Enchantment enchantment;
    public int enchantLevel;
    public static final String RECIPE_ID = "enchantment";
    public PsyglyphicEnchantingRecipe(List<Ingredient> pedestalItems, Enchantment enchantment, int level, int dominionCost){
        this.pedestalItems = pedestalItems;
        this.enchantment = enchantment;
        this.enchantLevel = level;
        this.dominionCost = dominionCost;
        this.id = RigoranthusEmortisReborn.rl(enchantment.getRegistryName().getPath() +"_" + level);
    }

    public PsyglyphicEnchantingRecipe(ItemStack[] pedestalItems, Enchantment enchantment, int level, int dominionCost){
        List<Ingredient> ingres = new ArrayList<>();
        for(ItemStack i : pedestalItems){
            ingres.add(Ingredient.of(i.getItem()));
        }
        this.pedestalItems = ingres;
        this.enchantment = enchantment;
        this.enchantLevel = level;
        this.dominionCost = dominionCost;
        this.id = RigoranthusEmortisReborn.rl(enchantment.getRegistryName().getPath() +"_" + level);
    }

    public PsyglyphicEnchantingRecipe(Ingredient[] pedestalItems, Enchantment enchantment, int level, int dominionCost){
        this.pedestalItems =  Arrays.asList(pedestalItems);
        this.enchantment = enchantment;
        this.enchantLevel = level;
        this.dominionCost = dominionCost;
        this.id = RigoranthusEmortisReborn.rl(enchantment.getRegistryName().getPath() +"_" + level);
    }

    @Override
    public IRecipeType<?> getType() {
        return Registry.RECIPE_TYPE.get(RigoranthusEmortisReborn.rl(RECIPE_ID));
    }

    public boolean doesReagentMatch(ItemStack stack, PlayerEntity playerEntity){
        if(stack.isEmpty())
            return false;
        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(stack);
        int level = enchantments.getOrDefault(enchantment, 0);
        Collection<Enchantment> enchantList = EnchantmentHelper.getEnchantments(stack).keySet();
        enchantList.remove(enchantment);
        if(stack.getItem() != Items.BOOK && stack.getItem() != Items.ENCHANTED_BOOK && !enchantment.canEnchant(stack)){
            PortUtil.sendMessage(playerEntity, new TranslationTextComponent("rigoranthusemortisreborn.enchanting.incompatible"));
            return false;
        }

        if(!EnchantmentHelper.isEnchantmentCompatible(enchantList, enchantment)){
            PortUtil.sendMessage(playerEntity, new TranslationTextComponent("rigoranthusemortisreborn.enchanting.incompatible"));
            return false;
        }

        if (!(this.enchantLevel - level == 1)) {
            PortUtil.sendMessage(playerEntity, new TranslationTextComponent("rigoranthusemortisreborn.enchanting.bad_level"));
            return false;
        }

        return true;
    }

    // Override and move reagent match to the end so we can give feedback
    @Override
    public boolean isMatch(List<ItemStack> pedestalItems, ItemStack reagent, PsyglyphicAmalgamatorTile psyglyphicAmalgamatorTile, @Nullable PlayerEntity player) {
        pedestalItems = pedestalItems.stream().filter(itemStack -> !itemStack.isEmpty()).collect(Collectors.toList());
        return this.pedestalItems.size() == pedestalItems.size() && doItemsMatch(pedestalItems, this.pedestalItems) && doesReagentMatch(reagent, player);
    }

    @Override
    public boolean doesReagentMatch(ItemStack stack) {
        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(stack);
        int level = enchantments.getOrDefault(enchantment, 0);
        return enchantment.canEnchant(stack) && this.enchantLevel - level == 1 && EnchantmentHelper.isEnchantmentCompatible(EnchantmentHelper.getEnchantments(stack).keySet(), enchantment);
    }

    @Override
    public ItemStack assemble(PsyglyphicAmalgamatorTile inv) {
        ItemStack stack = inv.catalystItem.getItem() == Items.BOOK ? new ItemStack(Items.ENCHANTED_BOOK) : inv.catalystItem.copy();
        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(stack);
        enchantments.put(enchantment, enchantLevel);
        EnchantmentHelper.setEnchantments(enchantments, stack);
        return stack;
    }

    @Override
    public ItemStack getResult(List<ItemStack> pedestalItems, ItemStack reagent, PsyglyphicAmalgamatorTile tile) {
        return assemble(tile);
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return RecipeRegistry.ENCHANTMENT_SERIALIZER;
    }

    @Override
    public JsonElement asRecipe() {
        JsonObject jsonobject = new JsonObject();
        jsonobject.addProperty("type", "rigoranthusemortisreborn:" + PsyglyphicEnchantingRecipe.RECIPE_ID);
        jsonobject.addProperty("enchantment", enchantment.getRegistryName().toString());
        jsonobject.addProperty("level", enchantLevel);
        jsonobject.addProperty("dominion", dominionCost());

        int counter = 1;
        for(Ingredient i : pedestalItems){
            JsonArray item = new JsonArray();
            item.add(i.toJson());
            jsonobject.add("item_"+counter, item);
            counter++;
        }
        return jsonobject;
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<PsyglyphicEnchantingRecipe> {

        @Override
        public PsyglyphicEnchantingRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            Enchantment enchantment = ForgeRegistries.ENCHANTMENTS.getValue(new ResourceLocation(JSONUtils.getAsString(json, "enchantment")));
            int level = JSONUtils.getAsInt(json, "level", 1);
            int dominionCost = JSONUtils.getAsInt(json,"dominion", 0);
            List<Ingredient> stacks = new ArrayList<>();
            for(int i = 1; i < 9; i++){
                if(json.has("item_"+i))
                    stacks.add(Ingredient.fromJson(JSONUtils.getAsJsonArray(json, "item_" + i)));
            }
            return new PsyglyphicEnchantingRecipe( stacks,enchantment,level, dominionCost);
        }

        @Nullable
        @Override
        public PsyglyphicEnchantingRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) {
            int length = buffer.readInt();
            String enchantID = buffer.readUtf();
            int level = buffer.readInt();
            int dominionCost = buffer.readInt();
            List<Ingredient> stacks = new ArrayList<>();

            for(int i = 0; i < length; i++){
                try{ stacks.add(Ingredient.fromNetwork(buffer)); }catch (Exception e){
                    e.printStackTrace();
                    break;
                }
            }
            return new PsyglyphicEnchantingRecipe(stacks, ForgeRegistries.ENCHANTMENTS.getValue(new ResourceLocation(enchantID)), level, dominionCost);
        }

        @Override
        public void toNetwork(PacketBuffer buf, PsyglyphicEnchantingRecipe recipe) {
            buf.writeInt(recipe.pedestalItems.size());
            buf.writeUtf(recipe.enchantment.getRegistryName().toString());
            buf.writeInt(recipe.enchantLevel);
            buf.writeInt(recipe.dominionCost());
            for(Ingredient i : recipe.pedestalItems){
                i.toNetwork(buf);
            }
        }
    }
}