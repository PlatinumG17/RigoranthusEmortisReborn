package com.platinumg17.rigoranthusemortisreborn.jei;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.config.ConfigValues;
import com.platinumg17.rigoranthusemortisreborn.tileentity.gui.MasterfulSmelteryScreen;
import com.platinumg17.rigoranthusemortisreborn.core.init.Registration;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaRecipeCategoryUid;
import mezz.jei.api.registration.IAdvancedRegistration;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

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
}