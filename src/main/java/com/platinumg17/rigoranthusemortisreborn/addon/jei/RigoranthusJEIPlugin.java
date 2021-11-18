package com.platinumg17.rigoranthusemortisreborn.addon.jei;

import com.platinumg17.rigoranthusemortisreborn.canis.CanisBlocks;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.canis.common.util.CanisBedUtil;
import com.platinumg17.rigoranthusemortisreborn.config.ConfigValues;
import com.platinumg17.rigoranthusemortisreborn.blocks.tileentity.gui.MasterfulSmelteryScreen;
import com.platinumg17.rigoranthusemortisreborn.core.init.Registration;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaRecipeCategoryUid;
import mezz.jei.api.registration.*;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.tuple.Pair;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.registry.IBeddingMaterial;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.registry.ICasingMaterial;

@JeiPlugin
public class RigoranthusJEIPlugin implements IModPlugin {

	@Override
	public ResourceLocation getPluginUid() {
		return new ResourceLocation(EmortisConstants.MOD_ID, "plugin_" + EmortisConstants.MOD_ID);
	}

	@Override
	public void registerAdvanced(IAdvancedRegistration registration) {
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registry) {
		if (ConfigValues.enableJeiPlugin && ConfigValues.enableJeiCatalysts) {
			registry.addRecipeCatalyst(new ItemStack(Registration.MASTERFUL_SMELTERY.get()), VanillaRecipeCategoryUid.FURNACE);
			registry.addRecipeCatalyst(new ItemStack(Registration.MASTERFUL_SMELTERY.get()), VanillaRecipeCategoryUid.FUEL);
		}
	}

	@Override
	public void registerGuiHandlers(IGuiHandlerRegistration registry) {
		if (ConfigValues.enableJeiPlugin && ConfigValues.enableJeiClickArea) {
			registry.addRecipeClickArea(MasterfulSmelteryScreen.class, 79, 35, 24, 17, VanillaRecipeCategoryUid.FUEL, VanillaRecipeCategoryUid.FURNACE);
		}
	}

	@Override
	public void registerItemSubtypes(ISubtypeRegistration registration) {
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

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		registration.addRecipes(CanisBedRecipeMaker.createCanisBedRecipes(), VanillaRecipeCategoryUid.CRAFTING);
	}
}