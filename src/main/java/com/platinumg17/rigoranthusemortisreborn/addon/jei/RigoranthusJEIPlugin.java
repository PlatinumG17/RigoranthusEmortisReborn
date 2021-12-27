package com.platinumg17.rigoranthusemortisreborn.addon.jei;

import com.platinumg17.rigoranthusemortisreborn.api.RigoranthusEmortisRebornAPI;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.psyglyphic_amalgamator.PsyglyphicAmalgamatorRecipe;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.psyglyphic_amalgamator.PsyglyphicEnchantingRecipe;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.recipe.CraftingPressRecipe;
import com.platinumg17.rigoranthusemortisreborn.blocks.tileentity.gui.SmelteryScreenBase;
import com.platinumg17.rigoranthusemortisreborn.canis.CanisBlocks;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.canis.common.util.CanisBedUtil;
import com.platinumg17.rigoranthusemortisreborn.config.ConfigValues;
import com.platinumg17.rigoranthusemortisreborn.core.init.Registration;
import com.platinumg17.rigoranthusemortisreborn.magica.common.crafting.CrushRecipe;
import com.platinumg17.rigoranthusemortisreborn.magica.common.potions.ModPotions;
import com.platinumg17.rigoranthusemortisreborn.magica.common.spell.effect.EffectCrush;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.BlockRegistry;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.MagicItemsRegistry;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.ModIds;
import mezz.jei.api.constants.VanillaRecipeCategoryUid;
import mezz.jei.api.recipe.vanilla.IJeiBrewingRecipe;
import mezz.jei.api.registration.*;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.tuple.Pair;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.registry.IBeddingMaterial;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.registry.ICasingMaterial;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@JeiPlugin
public class RigoranthusJEIPlugin implements IModPlugin {

	@Override
	public ResourceLocation getPluginUid() {
		return new ResourceLocation(EmortisConstants.MOD_ID, "plugin_" + EmortisConstants.MOD_ID);
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		registry.addRecipeCategories(
				new CraftingPressRecipeCategory(registry.getJeiHelpers().getGuiHelper()),
				new PsyglyphicAmalgamatorRecipeCategory(registry.getJeiHelpers().getGuiHelper())
//				new CrushRecipeCategory(registry.getJeiHelpers().getGuiHelper())
		);
	}

	@Override
	public void registerAdvanced(IAdvancedRegistration registration) {
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registry) {
		if (ConfigValues.enableJeiPlugin && ConfigValues.enableJeiCatalysts) {
			registry.addRecipeCatalyst(new ItemStack(BlockRegistry.EMORTIC_CRAFTING_PRESS_BLOCK), CraftingPressRecipeCategory.UID);
			registry.addRecipeCatalyst(new ItemStack(BlockRegistry.PSYGLYPHIC_AMALG_BLOCK), PsyglyphicAmalgamatorRecipeCategory.UID);
//			registry.addRecipeCatalyst(new ItemStack(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(EffectCrush.INSTANCE)), CrushRecipeCategory.UID);

			registry.addRecipeCatalyst(new ItemStack(Registration.MASTERFUL_SMELTERY.get()), VanillaRecipeCategoryUid.FURNACE);
			registry.addRecipeCatalyst(new ItemStack(Registration.MASTERFUL_SMELTERY.get()), VanillaRecipeCategoryUid.FUEL);
		}
	}

	@Override
	public void registerGuiHandlers(IGuiHandlerRegistration registry) {
		if (ConfigValues.enableJeiPlugin && ConfigValues.enableJeiClickArea) {
			registry.addRecipeClickArea(SmelteryScreenBase.class, 79, 35, 24, 17, VanillaRecipeCategoryUid.FUEL, VanillaRecipeCategoryUid.FURNACE);
		}
	}

	@Override
	public void registerItemSubtypes(ISubtypeRegistration registration) {
		if (ConfigValues.enableAllCanisBedRecipes) {
			registration.registerSubtypeInterpreter(CanisBlocks.CANIS_BED.get().asItem(), stack -> {
				Pair<ICasingMaterial, IBeddingMaterial> materials = CanisBedUtil.getMaterials(stack);

				String casingKey = materials.getLeft() != null
						? materials.getLeft().getRegistryName().toString()
						: "rigoranthusemortisreborn:casing_missing";

				String beddingKey = materials.getRight() != null
						? materials.getRight().getRegistryName().toString()
						: "rigoranthusemortisreborn:bedding_missing";

				return casingKey + "+" + beddingKey;
			});
		}
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		List<CraftingPressRecipe> pressRecipes = new ArrayList<>();
		List<PsyglyphicAmalgamatorRecipe> amalgamator = new ArrayList<>();
		List<CrushRecipe> crushRecipes = new ArrayList<>();
		RecipeManager manager = Minecraft.getInstance().level.getRecipeManager();
		for(IRecipe i : manager.getRecipes()){
			if(i instanceof CraftingPressRecipe){
				pressRecipes.add((CraftingPressRecipe) i);
			}
			if(i instanceof PsyglyphicAmalgamatorRecipe && !(i instanceof PsyglyphicEnchantingRecipe)){
				amalgamator.add((PsyglyphicAmalgamatorRecipe) i);
			}
//			if(i instanceof CrushRecipe){
//				crushRecipes.add((CrushRecipe) i);
//			}
		}
//		registration.addRecipes(crushRecipes, CrushRecipeCategory.UID);
		registration.addRecipes(pressRecipes, CraftingPressRecipeCategory.UID);
		registration.addRecipes(amalgamator, PsyglyphicAmalgamatorRecipeCategory.UID);

		ItemStack dominionPot = PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.DOMINION_REGEN_POTION);
		ItemStack necroPot = PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.NECROTIZING_FASCIITIS);

		IJeiBrewingRecipe dominionPotionRecipe = registration.getVanillaRecipeFactory().createBrewingRecipe(Collections.singletonList(new ItemStack(BlockRegistry.DOMINION_BERRY_BUSH)),
				PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.AWKWARD), dominionPot );
		IJeiBrewingRecipe necroPotionRecipe = registration.getVanillaRecipeFactory().createBrewingRecipe(Collections.singletonList(new ItemStack(MagicItemsRegistry.DWELLER_FLESH)),
				PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.AWKWARD), necroPot );

		registration.addRecipes(Collections.singletonList(dominionPotionRecipe), new ResourceLocation(ModIds.MINECRAFT_ID, "brewing"));
		registration.addRecipes(Collections.singletonList(necroPotionRecipe), new ResourceLocation(ModIds.MINECRAFT_ID, "brewing"));

		registration.addRecipes(CanisBedRecipeMaker.createCanisBedRecipes(), VanillaRecipeCategoryUid.CRAFTING);
	}
}