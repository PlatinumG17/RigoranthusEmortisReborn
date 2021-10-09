package com.platinumg17.rigoranthusemortisreborn;

import com.google.common.collect.ImmutableMap;
import com.platinumg17.rigoranthusemortisreborn.blocks.custom.RigoranthusWoodTypes;
import com.platinumg17.rigoranthusemortisreborn.config.Config;
import com.platinumg17.rigoranthusemortisreborn.core.events.RigoranthusForgeEventBusSubscriber;
import com.platinumg17.rigoranthusemortisreborn.core.init.*;
import com.platinumg17.rigoranthusemortisreborn.core.init.network.messages.Messages;
import com.platinumg17.rigoranthusemortisreborn.core.registry.RigoranthusSoundRegistry;
import com.platinumg17.rigoranthusemortisreborn.entity.RigoranthusEntityTypes;
import com.platinumg17.rigoranthusemortisreborn.entity.render.CanisChordataRenderer;
import com.platinumg17.rigoranthusemortisreborn.entity.render.LanguidDwellerRenderer;
import com.platinumg17.rigoranthusemortisreborn.entity.render.NecrawFasciiRenderer;
import com.platinumg17.rigoranthusemortisreborn.entity.render.SunderedCadaverRenderer;
import com.platinumg17.rigoranthusemortisreborn.fluid.CadaverousIchorFluid;
import com.platinumg17.rigoranthusemortisreborn.tileentity.RigoranthusTileEntities;
import net.minecraft.block.Block;
import net.minecraft.block.WoodType;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.tileentity.SignTileEntityRenderer;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("rigoranthusemortisreborn")
@Mod.EventBusSubscriber(modid = RigoranthusEmortisReborn.MOD_ID, bus = Bus.MOD)
public class RigoranthusEmortisReborn {
	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MOD_ID = "rigoranthusemortisreborn";

	public RigoranthusEmortisReborn() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		bus.addListener(this::setup);
		bus.addListener(this::enqueueIMC);
		bus.addListener(this::processIMC);
		bus.addListener(this::doClientStuff);
		//bus.addListener(this::onServerStarting);

		ItemInit.ITEMS.register(bus);
		BlockInit.register(bus);
        RigoranthusTileEntities.register(bus);
        CadaverousIchorFluid.register(bus);
        RigoranthusSoundRegistry.register(bus);
        RigoranthusEntityTypes.register(bus);
        //RigoranthusBiomes.register(bus);
        //RigoranthusStructures.register(bus);
        if (Config.enableNewWoodTypes.get()) {
            BuildingBlockInit.register(bus);
        }
		
		MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new RigoranthusForgeEventBusSubscriber());
        Messages.registerMessages("rigoranthusemortisreborn_network");

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.COMMON_CONFIG);

        bus.register(Registration.class);
        Registration.init();

        Config.loadConfig(Config.CLIENT_CONFIG, FMLPaths.CONFIGDIR.get().resolve("rigoranthusemortisreborn-client.toml"));
        Config.loadConfig(Config.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve("rigoranthusemortisreborn.toml"));

        FMLJavaModLoadingContext.get().getModEventBus().addListener(ScreenInit::init);
	}
	
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        Registration.registerItems(event);
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
        Registration.registerBlocks(event);
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            AxeItem.STRIPABLES = new ImmutableMap.Builder<Block, Block>().putAll(AxeItem.STRIPABLES)
                    .put(BuildingBlockInit.JESSIC_LOG.get(), BuildingBlockInit.STRIPPED_JESSIC_LOG.get())
                    .put(BuildingBlockInit.JESSIC_WOOD.get(), BuildingBlockInit.STRIPPED_JESSIC_WOOD.get())
                    .put(BuildingBlockInit.AZULOREAL_LOG.get(), BuildingBlockInit.STRIPPED_AZULOREAL_LOG.get())
                    .put(BuildingBlockInit.AZULOREAL_WOOD.get(), BuildingBlockInit.STRIPPED_AZULOREAL_WOOD.get()).build();
            WoodType.register(RigoranthusWoodTypes.AZULOREAL);
            WoodType.register(RigoranthusWoodTypes.JESSIC);
        });
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            RenderTypeLookup.setRenderLayer(BuildingBlockInit.JESSIC_DOOR.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(BuildingBlockInit.JESSIC_TRAPDOOR.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(BuildingBlockInit.JESSIC_LEAVES.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(BuildingBlockInit.JESSIC_SAPLING.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(BuildingBlockInit.AZULOREAL_DOOR.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(BuildingBlockInit.AZULOREAL_TRAPDOOR.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(BuildingBlockInit.AZULOREAL_LEAVES.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(BuildingBlockInit.AZULOREAL_SAPLING.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(Registration.MASTERFUL_SMELTERY.get(), RenderType.cutout());

            RenderTypeLookup.setRenderLayer(CadaverousIchorFluid.CADAVEROUS_ICHOR_FLUID.get(), RenderType.translucent());
            RenderTypeLookup.setRenderLayer(CadaverousIchorFluid.CADAVEROUS_ICHOR_BLOCK.get(), RenderType.translucent());
            RenderTypeLookup.setRenderLayer(CadaverousIchorFluid.CADAVEROUS_ICHOR_FLOWING.get(), RenderType.translucent());

            makeBow(ItemInit.BONE_BOW.get());
            ClientRegistry.bindTileEntityRenderer(RigoranthusTileEntities.SIGN_TILE_ENTITIES.get(),
                    SignTileEntityRenderer::new);
            Atlases.addWoodType(RigoranthusWoodTypes.AZULOREAL);
            Atlases.addWoodType(RigoranthusWoodTypes.JESSIC);
        });
        RenderingRegistry.registerEntityRenderingHandler(RigoranthusEntityTypes.CANIS_CHORDATA.get(), CanisChordataRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(RigoranthusEntityTypes.NECRAW_FASCII.get(), NecrawFasciiRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(RigoranthusEntityTypes.SUNDERED_CADAVER.get(), SunderedCadaverRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(RigoranthusEntityTypes.LANGUID_DWELLER.get(), LanguidDwellerRenderer::new);
    }
    private void makeBow(Item item) {
        ItemModelsProperties.register(item, new ResourceLocation("pull"),
                (p_239429_0_, p_239429_1_, p_239429_2_) -> {
            if (p_239429_2_ == null) {
                return 0.0F;
            } else {
                return p_239429_2_.getUseItem() != p_239429_0_ ? 0.0F : (float) (p_239429_0_.getUseDuration() - p_239429_2_.getUseItemRemainingTicks()) / 20.0F;
            }
        });
        ItemModelsProperties.register(item, new ResourceLocation("pulling"),
                (p_239428_0_, p_239428_1_, p_239428_2_) -> {
            return p_239428_2_ != null && p_239428_2_.isUsingItem() && p_239428_2_.getUseItem() == p_239428_0_ ? 1.0F : 0.0F;
        });
    }
    private void enqueueIMC(final InterModEnqueueEvent event) {
    }

    private void processIMC(final InterModProcessEvent event) {
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        LOGGER.info("Ah baby! Da mod man make a message thing!");
        //RigoranthusRandomRegistry.init();
    }
}