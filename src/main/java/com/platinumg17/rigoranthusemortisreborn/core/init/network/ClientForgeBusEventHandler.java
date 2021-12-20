package com.platinumg17.rigoranthusemortisreborn.core.init.network;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.core.registry.fluid.FluidRegistry;
import com.platinumg17.rigoranthusemortisreborn.magica.common.potions.ModPotions;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EmortisConstants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientForgeBusEventHandler {

    @SubscribeEvent
    public static void getFogDensity(EntityViewRenderEvent.RenderFogEvent.FogDensity event) {
        ActiveRenderInfo info = event.getInfo();
        FluidState fluidState = info.getFluidInCamera();
        if (fluidState.isEmpty())
            return;
        Fluid fluid = fluidState.getType();
        if (fluid == FluidRegistry.CADAVEROUS_ICHOR_FLUID.get() || fluid == FluidRegistry.CADAVEROUS_ICHOR_FLOWING.get()) {
            event.setDensity(3f);
            event.setCanceled(true);
            RenderSystem.fogMode(GlStateManager.FogMode.EXP);
            return;
        }
    }
    /**
     * used to change colors of the fog overlay
     */
    @SubscribeEvent
    public static void getFogColor(EntityViewRenderEvent.FogColors event) {
        ActiveRenderInfo info = event.getInfo();
        FluidState fluidState = info.getFluidInCamera();
        if (fluidState.isEmpty())
            return;
        Fluid fluid = fluidState.getType();
        if (fluid == FluidRegistry.CADAVEROUS_ICHOR_FLUID.get() || fluid == FluidRegistry.CADAVEROUS_ICHOR_FLOWING.get()) {
            event.setRed(48 / 256f);
            event.setGreen(4 / 256f);
            event.setBlue(4 / 256f);
        }
    }

    @SubscribeEvent
    public void onFOVUpdate(FOVUpdateEvent event) {
        if (event.getEntity().hasEffect(ModPotions.NECROTIZING_FASCIITIS_EFFECT)) {
            event.setNewfov(1.0F);
        }
    }
}