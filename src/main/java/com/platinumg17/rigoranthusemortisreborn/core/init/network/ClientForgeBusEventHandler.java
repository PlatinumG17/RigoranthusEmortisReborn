package com.platinumg17.rigoranthusemortisreborn.core.init.network;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.core.init.fluid.IIchorFog;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EmortisConstants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientForgeBusEventHandler {
    @SubscribeEvent
    public static void onFogRender(EntityViewRenderEvent.FogDensity event)
    {
        if (event.getInfo().getFluidInCamera().createLegacyBlock().getBlock() instanceof IIchorFog)
        {
            IIchorFog fog = (IIchorFog)event.getInfo().getFluidInCamera().createLegacyBlock().getBlock();
            float fogDensity = fog.getRigorFogDensity();

            event.setCanceled(true);
            event.setDensity(fogDensity);
            RenderSystem.fogMode(GlStateManager.FogMode.EXP);
        }
    }

    /**
     * used to changes colors of the fog overlay
     */
    @SubscribeEvent
    public static void addFogColor(EntityViewRenderEvent.FogColors event)
    {
        BlockState state = event.getInfo().getFluidInCamera().createLegacyBlock();
        IWorldReader world = event.getInfo().getEntity().level;
        BlockPos pos = event.getInfo().getBlockPosition();
        Entity entity = event.getInfo().getEntity();
        Vector3d originalColor = new Vector3d(event.getRed(), event.getGreen(), event.getBlue());
        float partialTick = (float) (event.getRenderPartialTicks());

        if(state.getBlock() instanceof IIchorFog)
        {
            IIchorFog fog = (IIchorFog) (state.getBlock());
            Vector3d fogColor = fog.getRigorFogColor(state, world, pos, entity, originalColor, partialTick);

            event.setRed((float) fogColor.x());
            event.setGreen((float) fogColor.y());
            event.setBlue((float) fogColor.z());
        }
    }
}