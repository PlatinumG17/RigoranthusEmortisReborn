package WeirdFailedProjects;

import com.google.gson.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.loot.IRandomRange;
import net.minecraft.loot.RandomRanges;
import net.minecraft.util.JSONUtils;

import java.lang.reflect.Type;
import java.util.Random;

//public class PrimevalCoinPricing {
//    private final Ingredient ingredient;
//    private final IRandomRange priceRange;
//
//    public PrimevalCoinPricing(Ingredient ingredient, IRandomRange priceRange) {
//        this.ingredient = ingredient;
//        this.priceRange = priceRange;
//    }
//    public int generatePrice(Random random) {return priceRange.getInt(random);}
//    public boolean appliesTo(ItemStack stack) {return ingredient.test(stack);}
//
//    public static class Serializer implements JsonDeserializer<PrimevalCoinPricing>, JsonSerializer<PrimevalCoinPricing> {
//        @Override
//        public PrimevalCoinPricing deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
//            JsonObject json = JSONUtils.convertToJsonObject(jsonElement, "primeval coin pricing");
//            return new PrimevalCoinPricing(Ingredient.fromJson(json.get("ingredient")), RandomRanges.deserialize(json.get("range"), context));
//        }
//
//        @Override
//        public JsonElement serialize(PrimevalCoinPricing pricing, Type type, JsonSerializationContext context) {
//            JsonObject json = new JsonObject();
//            json.add("ingredient", pricing.ingredient.toJson());
//            json.add("range", RandomRanges.serialize(pricing.priceRange, context));
//            return json;
//        }
//    }
//}
