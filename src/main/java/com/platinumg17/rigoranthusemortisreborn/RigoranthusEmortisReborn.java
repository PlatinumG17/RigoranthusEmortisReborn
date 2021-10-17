package com.platinumg17.rigoranthusemortisreborn;

import com.minecraftabnormals.abnormals_core.core.util.registry.RegistryHelper;
import com.platinumg17.rigoranthusemortisreborn.blocks.custom.DecorativeOrStorageBlocks;
import com.platinumg17.rigoranthusemortisreborn.config.Config;
import com.platinumg17.rigoranthusemortisreborn.core.init.*;
import com.platinumg17.rigoranthusemortisreborn.core.init.network.messages.Messages;
import com.platinumg17.rigoranthusemortisreborn.core.other.VanillaCompatRigoranthus;
import com.platinumg17.rigoranthusemortisreborn.core.registry.BiomeRegistration;
import com.platinumg17.rigoranthusemortisreborn.core.registry.RigoranthusSoundRegistry;
import com.platinumg17.rigoranthusemortisreborn.entity.RigoranthusEntityTypes;
import com.platinumg17.rigoranthusemortisreborn.entity.render.*;
import com.platinumg17.rigoranthusemortisreborn.fluid.CadaverousIchorFluid;
import com.platinumg17.rigoranthusemortisreborn.tileentity.RigoranthusTileEntities;
import com.platinumg17.rigoranthusemortisreborn.world.biome.EmortisBiomes;
import com.platinumg17.rigoranthusemortisreborn.world.biome.EmortisSurfaceBuilder;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
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
    public static final RegistryHelper REGISTRY_HELPER = RegistryHelper.create(MOD_ID, helper -> {
    });

	public RigoranthusEmortisReborn() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        REGISTRY_HELPER.register(bus);
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
        EmortisBiomes.register();
        BuildingBlockInit.register(bus);
		
		MinecraftForge.EVENT_BUS.register(this);
        Messages.registerMessages("rigoranthusemortisreborn_network");

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.COMMON_CONFIG);

        bus.register(Registration.class);
        Registration.init();

        Config.loadConfig(Config.CLIENT_CONFIG, FMLPaths.CONFIGDIR.get().resolve("rigoranthusemortisreborn-client.toml"));
        Config.loadConfig(Config.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve("rigoranthusemortisreborn.toml"));

        FMLJavaModLoadingContext.get().getModEventBus().addListener(ScreenInit::init);
        registerBiomeBuilders();
    }

    private void registerBiomeBuilders() {
        BiomeRegistration.init();
        EmortisBiomes.register();
        EmortisSurfaceBuilder.register();
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

    public static ResourceLocation resourceLoc(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
//            EmortisStructures.setupStructures();
            VanillaCompatRigoranthus.registerCompostables();
            VanillaCompatRigoranthus.registerFlammables();
//            AxeItem.STRIPABLES = new ImmutableMap.Builder<Block, Block>().putAll(AxeItem.STRIPABLES)
//                    .put(DecorativeOrStorageBlocks.JESSIC_LOG.get(), DecorativeOrStorageBlocks.STRIPPED_JESSIC_LOG.get())
//                    .put(DecorativeOrStorageBlocks.JESSIC_WOOD.get(), DecorativeOrStorageBlocks.STRIPPED_JESSIC_WOOD.get())
//                    .put(DecorativeOrStorageBlocks.AZULOREAL_LOG.get(), DecorativeOrStorageBlocks.STRIPPED_AZULOREAL_LOG.get())
//                    .put(DecorativeOrStorageBlocks.AZULOREAL_WOOD.get(), DecorativeOrStorageBlocks.STRIPPED_AZULOREAL_WOOD.get()).build();
//            WoodType.register(RigoranthusWoodTypes.AZULOREAL);
//            WoodType.register(RigoranthusWoodTypes.JESSIC);
        });
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            RenderTypeLookup.setRenderLayer(DecorativeOrStorageBlocks.JESSIC_DOOR.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(DecorativeOrStorageBlocks.JESSIC_TRAPDOOR.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(DecorativeOrStorageBlocks.JESSIC_LEAVES.get(), RenderType.cutoutMipped());
            RenderTypeLookup.setRenderLayer(DecorativeOrStorageBlocks.JESSIC_LEAF_CARPET.get(), RenderType.cutoutMipped());
            RenderTypeLookup.setRenderLayer(DecorativeOrStorageBlocks.JESSIC_SAPLING.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(DecorativeOrStorageBlocks.POTTED_JESSIC_SAPLING.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(DecorativeOrStorageBlocks.JESSIC_POST.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(DecorativeOrStorageBlocks.STRIPPED_JESSIC_POST.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(DecorativeOrStorageBlocks.JESSIC_HEDGE.get(), RenderType.cutoutMipped());

            RenderTypeLookup.setRenderLayer(DecorativeOrStorageBlocks.AZULOREAL_DOOR.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(DecorativeOrStorageBlocks.AZULOREAL_TRAPDOOR.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(DecorativeOrStorageBlocks.AZULOREAL_LEAVES.get(), RenderType.cutoutMipped());
            RenderTypeLookup.setRenderLayer(DecorativeOrStorageBlocks.AZULOREAL_LEAF_CARPET.get(), RenderType.cutoutMipped());
            RenderTypeLookup.setRenderLayer(DecorativeOrStorageBlocks.AZULOREAL_SAPLING.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(DecorativeOrStorageBlocks.POTTED_AZULOREAL_SAPLING.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(DecorativeOrStorageBlocks.AZULOREAL_POST.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(DecorativeOrStorageBlocks.STRIPPED_AZULOREAL_POST.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(DecorativeOrStorageBlocks.AZULOREAL_HEDGE.get(), RenderType.cutoutMipped());

            RenderTypeLookup.setRenderLayer(DecorativeOrStorageBlocks.AZULOREAL_ORCHID.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(DecorativeOrStorageBlocks.IRIDESCENT_SPROUTS.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(BuildingBlockInit.LISIANTHUS.get(), RenderType.cutout());

            RenderTypeLookup.setRenderLayer(Registration.MASTERFUL_SMELTERY.get(), RenderType.cutout());
            //RenderTypeLookup.setRenderLayer(//new DwellerThoraxModel(), RenderType.armorCutoutNoCull(new ResourceLocation("rigoranthusemortisreborn")));
            //        Block.byItem(ItemInit.DWELLER_THORAX.get()), RenderType.armorCutoutNoCull(new ResourceLocation("rigoranthusemortisreborn:textures/dweller_chestplate.png")));

            RenderTypeLookup.setRenderLayer(CadaverousIchorFluid.CADAVEROUS_ICHOR_FLUID.get(), RenderType.translucent());
            RenderTypeLookup.setRenderLayer(CadaverousIchorFluid.CADAVEROUS_ICHOR_BLOCK.get(), RenderType.translucent());
            RenderTypeLookup.setRenderLayer(CadaverousIchorFluid.CADAVEROUS_ICHOR_FLOWING.get(), RenderType.translucent());

            makeBow(ItemInit.BONE_BOW.get());
//            Atlases.addWoodType(RigoranthusWoodTypes.AZULOREAL);
//            Atlases.addWoodType(RigoranthusWoodTypes.JESSIC);
        });
        RenderingRegistry.registerEntityRenderingHandler(RigoranthusEntityTypes.CANIS_CHORDATA.get(), CanisChordataRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(RigoranthusEntityTypes.NECRAW_FASCII.get(), NecrawFasciiRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(RigoranthusEntityTypes.SUNDERED_CADAVER.get(), SunderedCadaverRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(RigoranthusEntityTypes.LANGUID_DWELLER.get(), LanguidDwellerRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(RigoranthusEntityTypes.BONE_ARROW_ENTITY.get(), BoneArrowRenderer::new);
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
    }
}