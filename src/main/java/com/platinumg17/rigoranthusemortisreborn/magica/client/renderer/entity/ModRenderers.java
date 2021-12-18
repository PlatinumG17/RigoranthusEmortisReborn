package com.platinumg17.rigoranthusemortisreborn.magica.client.renderer.entity;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.entity.render.mobs.FeralCanisRenderer;
import com.platinumg17.rigoranthusemortisreborn.entity.render.mobs.LanguidDwellerRenderer;
import com.platinumg17.rigoranthusemortisreborn.entity.render.mobs.NecrawFasciiRenderer;
import com.platinumg17.rigoranthusemortisreborn.entity.render.mobs.SunderedCadaverRenderer;
import com.platinumg17.rigoranthusemortisreborn.magica.common.entity.ModEntities;
import net.minecraft.client.renderer.entity.HorseRenderer;
import net.minecraft.client.renderer.entity.LightningBoltRenderer;
import net.minecraft.client.renderer.entity.TippedArrowRenderer;
import net.minecraft.client.renderer.entity.WolfRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = EmortisConstants.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModRenderers {
    @SubscribeEvent
    public static void register(final FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.FERAL_CANIS, FeralCanisRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.NECRAW_FASCII, NecrawFasciiRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.SUNDERED_CADAVER, SunderedCadaverRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.LANGUID_DWELLER, LanguidDwellerRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.FAMILIAR_CADAVER, FamiliarCadaverRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.BONE_ARROW_ENTITY, TippedArrowRenderer::new);

        RenderingRegistry.registerEntityRenderingHandler( ModEntities.SPELL_PROJ,
                renderManager -> new RenderSpell(renderManager, new ResourceLocation(EmortisConstants.MOD_ID, "textures/entity/spell_proj.png")));
        RenderingRegistry.registerEntityRenderingHandler( ModEntities.ENTITY_FOLLOW_PROJ,
                renderManager -> new RenderBlank(renderManager, new ResourceLocation(EmortisConstants.MOD_ID, "textures/entity/spell_proj.png")));
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.ENTITY_EVOKER_FANGS_ENTITY_TYPE, RenderFangs::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.ALLY_VEX, RenderAllyVex::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.SUMMON_WOLF, WolfRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.SUMMON_HORSE, HorseRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.LIGHTNING_ENTITY, LightningBoltRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.ENTITY_FLYING_ITEM, RenderFlyingItem::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.ENTITY_RITUAL,
                renderManager -> new RenderRitualProjectile(renderManager, new ResourceLocation(EmortisConstants.MOD_ID, "textures/entity/spell_proj.png")));
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.ENTITY_SPELL_ARROW, TippedArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.ENTITY_DUMMY, DummyRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.ENTITY_WARD,  renderManager -> new RenderRitualProjectile(renderManager, new ResourceLocation(EmortisConstants.MOD_ID, "textures/entity/spell_proj.png")));
        RenderingRegistry.registerEntityRenderingHandler( ModEntities.LINGER_SPELL,
                renderManager -> new RenderBlank(renderManager, new ResourceLocation(EmortisConstants.MOD_ID, "textures/entity/spell_proj.png")));
    }
}