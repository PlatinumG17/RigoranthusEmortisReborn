package com.platinumg17.rigoranthusemortisreborn.core.init;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.tileentity.gui.MasterfulSmelteryScreen;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = EmortisConstants.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ScreenInit {
	
    public static void init(final FMLClientSetupEvent event) {
    ScreenManager.register(Registration.MASTERFUL_SMELTERY_CONTAINER.get(), MasterfulSmelteryScreen::new);
    }
}