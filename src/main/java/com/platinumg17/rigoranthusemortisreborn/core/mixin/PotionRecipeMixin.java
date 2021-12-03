package com.platinumg17.rigoranthusemortisreborn.core.mixin;

//import com.platinumg17.rigoranthusemortisreborn.api.RigoranthusEmortisRebornAPI;
//import com.platinumg17.rigoranthusemortisreborn.api.apimagic.recipe.VanillaPotionRecipe;
//import net.minecraft.item.Item;
//import net.minecraft.potion.Potion;
//import net.minecraft.potion.PotionBrewing;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//@Mixin(PotionBrewing.class)
//public class PotionRecipeMixin {
//
//    @Inject(method = "addMix", at = @At("HEAD"))
//    private static void addMix(Potion potionEntry, Item potionIngredient, Potion potionResult, CallbackInfo ci) {
//        RigoranthusEmortisRebornAPI.getInstance().vanillaPotionRecipes.add(new VanillaPotionRecipe(potionEntry, potionIngredient, potionResult));
//    }
//}