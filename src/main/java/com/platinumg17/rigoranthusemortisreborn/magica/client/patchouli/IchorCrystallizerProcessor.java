package com.platinumg17.rigoranthusemortisreborn.magica.client.patchouli;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.recipe.GlyphPressRecipe;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.recipe.IchorCrystallizerRecipe;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.AbstractSpellPart;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.SpellSchool;
import com.platinumg17.rigoranthusemortisreborn.magica.common.items.Glyph;
import net.minecraft.client.Minecraft;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import vazkii.patchouli.api.IComponentProcessor;
import vazkii.patchouli.api.IVariable;
import vazkii.patchouli.api.IVariableProvider;

public class IchorCrystallizerProcessor implements IComponentProcessor {
    IchorCrystallizerRecipe recipe;
    @Override
    public void setup(IVariableProvider variables) {
        RecipeManager manager = Minecraft.getInstance().level.getRecipeManager();
        String recipeID = variables.get("recipe").asString();
        recipe = (IchorCrystallizerRecipe) manager.byKey(new ResourceLocation(recipeID)).orElse(null);
    }
    @Override
    public IVariable process(String s) {
        if(recipe == null)
            return null;
//        if(s.equals("material_type"))
//            return IVariable.from(recipe.getMaterial());
        if(s.equals("reagent"))
            return IVariable.from(recipe.reagent);
        if(s.equals("dominion_cost") ){
//            if(recipe.output.getItem() instanceof Glyph){
//                int cost =  ((Glyph) recipe.output.getItem()).spellPart.getDominionCost();
//                String costLang = "";
//                if(cost == 0)
//                    costLang = new TranslationTextComponent("rigoranthusemortisreborn.dominion_cost.none").getString();
//                if(cost < 20)
//                    costLang = new TranslationTextComponent("rigoranthusemortisreborn.dominion_cost.low").getString();
//                if(cost < 50)
//                    costLang = new TranslationTextComponent("rigoranthusemortisreborn.dominion_cost.medium").getString();
//                if(cost >= 50)
//                    costLang = new TranslationTextComponent("rigoranthusemortisreborn.dominion_cost.high").getString();
//                return IVariable.wrap(costLang);
//            }
            return IVariable.wrap("");
        }
        return null;
    }
}