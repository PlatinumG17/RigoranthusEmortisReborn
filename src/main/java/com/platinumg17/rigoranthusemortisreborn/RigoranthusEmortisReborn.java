package com.platinumg17.rigoranthusemortisreborn;

import com.minecraftabnormals.abnormals_core.core.util.registry.RegistryHelper;
import com.platinumg17.rigoranthusemortisreborn.addon.AddonManager;
import com.platinumg17.rigoranthusemortisreborn.blocks.BlockInit;
import com.platinumg17.rigoranthusemortisreborn.blocks.BuildingBlockInit;
import com.platinumg17.rigoranthusemortisreborn.blocks.DecorativeOrStorageBlocks;
import com.platinumg17.rigoranthusemortisreborn.canis.*;
import com.platinumg17.rigoranthusemortisreborn.canis.client.ClientSetup;
import com.platinumg17.rigoranthusemortisreborn.canis.client.data.REBlockstateProvider;
import com.platinumg17.rigoranthusemortisreborn.canis.client.data.REItemModelProvider;
import com.platinumg17.rigoranthusemortisreborn.canis.client.entity.render.CanisRenderer;
import com.platinumg17.rigoranthusemortisreborn.canis.client.entity.render.world.HomeboundRenderer;
import com.platinumg17.rigoranthusemortisreborn.canis.client.event.ClientEventHandler;
import com.platinumg17.rigoranthusemortisreborn.canis.common.Capabilities;
import com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.CanisPacketHandler;
import com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.packet.data.REBlockTagsProvider;
import com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.packet.data.REItemTagsProvider;
import com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.packet.data.RERecipeProvider;
import com.platinumg17.rigoranthusemortisreborn.canis.common.commands.CanisReviveCommand;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.CanisEntity;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.HelmetInteractionHandler;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.MeatFoodHandler;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.accouterments.CanisAccouterments;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.accouterments.CanisAccoutrementTypes;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.CanisBedMaterials;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.canis.common.skill.ChungusPupperSkill;
import com.platinumg17.rigoranthusemortisreborn.canis.common.util.BackwardsCompat;
import com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.CanisEventHandler;
import com.platinumg17.rigoranthusemortisreborn.canis.common.SpecializedEntityTypes;
import com.platinumg17.rigoranthusemortisreborn.canis.common.util.CanisRecipeSerializers;
import com.platinumg17.rigoranthusemortisreborn.config.Config;
import com.platinumg17.rigoranthusemortisreborn.config.ConfigHandler;
import com.platinumg17.rigoranthusemortisreborn.core.events.advancements.REAdvancementProvider;
import com.platinumg17.rigoranthusemortisreborn.core.events.other.VanillaCompatRigoranthus;
import com.platinumg17.rigoranthusemortisreborn.core.init.ItemInit;
import com.platinumg17.rigoranthusemortisreborn.core.init.Registration;
import com.platinumg17.rigoranthusemortisreborn.core.init.fluid.FluidRegistry;
import com.platinumg17.rigoranthusemortisreborn.core.init.fluid.particles.EmortisParticleTypes;
import com.platinumg17.rigoranthusemortisreborn.core.init.network.REPacketHandler;
import com.platinumg17.rigoranthusemortisreborn.core.init.network.messages.Messages;
import com.platinumg17.rigoranthusemortisreborn.core.registry.RigoranthusSoundRegistry;
import com.platinumg17.rigoranthusemortisreborn.entity.RigoranthusEntityTypes;
import com.platinumg17.rigoranthusemortisreborn.entity.render.DelphicBloomRenderer;
import com.platinumg17.rigoranthusemortisreborn.entity.render.mobs.CanisChordataRenderer;
import com.platinumg17.rigoranthusemortisreborn.entity.render.mobs.LanguidDwellerRenderer;
import com.platinumg17.rigoranthusemortisreborn.entity.render.mobs.NecrawFasciiRenderer;
import com.platinumg17.rigoranthusemortisreborn.entity.render.mobs.SunderedCadaverRenderer;
import com.platinumg17.rigoranthusemortisreborn.entity.render.projectile.BoneArrowRenderer;
import com.platinumg17.rigoranthusemortisreborn.tileentity.RigoranthusTileEntities;
import com.platinumg17.rigoranthusemortisreborn.world.EmortisMobSpawns;
import com.platinumg17.rigoranthusemortisreborn.world.biome.EmortisBiomes;
import com.platinumg17.rigoranthusemortisreborn.world.biome.EmortisSurfaceBuilder;
import com.platinumg17.rigoranthusemortisreborn.world.trees.RigoranthusWoodTypes;
import net.minecraft.block.WoodType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.resources.IReloadableResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.platinumg17.rigoranthusemortisreborn.api.feature.FoodHandler;
import com.platinumg17.rigoranthusemortisreborn.api.feature.InteractionHandler;
import software.bernie.geckolib3.GeckoLib;
import software.bernie.geckolib3.network.GeckoLibNetwork;

@Mod(EmortisConstants.MOD_ID)
@Mod.EventBusSubscriber(modid = EmortisConstants.MOD_ID, bus = Bus.MOD)
public class RigoranthusEmortisReborn {
	public static final Logger LOGGER = LogManager.getLogger(EmortisConstants.MOD_ID);
	public static final String MOD_ID = "rigoranthusemortisreborn";
    public static final RegistryHelper REGISTRY_HELPER = RegistryHelper.create(MOD_ID, helper -> {});
    public static final SimpleChannel HANDLER = NetworkRegistry.ChannelBuilder.named(EmortisConstants.CHANNEL_NAME)
            .clientAcceptedVersions(EmortisConstants.PROTOCOL_VERSION::equals)
            .serverAcceptedVersions(EmortisConstants.PROTOCOL_VERSION::equals)
            .networkProtocolVersion(EmortisConstants.PROTOCOL_VERSION::toString)
            .simpleChannel();

	public RigoranthusEmortisReborn() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        REGISTRY_HELPER.register(modEventBus);
        modEventBus.addListener(this::gatherData);
		modEventBus.addListener(this::setup);
		modEventBus.addListener(this::enqueueIMC);
		modEventBus.addListener(this::processIMC);

        BlockInit.register(modEventBus);
		ItemInit.ITEMS.register(modEventBus);
        RigoranthusTileEntities.register(modEventBus);
        FluidRegistry.register(modEventBus);
        RigoranthusSoundRegistry.register(modEventBus);
        RigoranthusEntityTypes.register(modEventBus);
        BuildingBlockInit.register(modEventBus);
        EmortisBiomes.register();

        EmortisParticleTypes.PARTICLES.register(modEventBus);
        CanisBlocks.BLOCKS.register(modEventBus);
        CanisTileEntityTypes.TILE_ENTITIES.register(modEventBus);
        CanisItems.ITEMS.register(modEventBus);
        SpecializedEntityTypes.ENTITIES.register(modEventBus);
        CanisContainerTypes.CONTAINERS.register(modEventBus);
        CanisSerializers.SERIALIZERS.register(modEventBus);
        CanisSkills.SKILLS.register(modEventBus);
        CanisAttributes.ATTRIBUTES.register(modEventBus);
        CanisAccouterments.ACCOUTERMENTS.register(modEventBus);
        CanisAccoutrementTypes.ACCOUTREMENT_TYPE.register(modEventBus);
        CanisRecipeSerializers.RECIPE_SERIALIZERS.register(modEventBus);
        CanisBedMaterials.BEDDINGS.register(modEventBus);
        CanisBedMaterials.CASINGS.register(modEventBus);

        modEventBus.addListener(CanisRegistries::newRegistry);

        IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;

        modEventBus.addListener(this::doClientStuff);
        forgeEventBus.addListener(this::onServerStarting);
        forgeEventBus.addListener(this::registerCommands);
        forgeEventBus.register(new CanisEventHandler());
        forgeEventBus.register(new BackwardsCompat());

        Messages.registerMessages("rigoranthusemortisreborn_network");

        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.COMMON_CONFIG);
        modEventBus.register(Registration.class);
        Registration.init();
        Config.loadConfig(Config.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve("RigoranthusEmortisReborn-common.toml"));

            GeckoLib.initialize();
            GeckoLibNetwork.initialize();

        //  Client Events  //
        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
            modEventBus.addListener(this::doClientStuff);
//            modEventBus.addListener(CanisBlocks::registerBlockColors);
            modEventBus.addListener(CanisItems::registerItemColors);
            modEventBus.addListener(ClientEventHandler::onModelBakeEvent);
                forgeEventBus.register(new ClientEventHandler());
                forgeEventBus.addListener(HomeboundRenderer::onWorldRenderLast);
//            Minecraft mc = Minecraft.getInstance();
//            if (mc != null) { // If mc is null we are running data gen so no need to add listener
//                ((IReloadableResourceManager) mc.getResourceManager()).registerReloadListener(CanisTextureManager.INSTANCE);
//            }
        }
        );
        ConfigHandler.init(modEventBus);
        AddonManager.init();
    }
    @SubscribeEvent public static void registerItems(RegistryEvent.Register<Item> event) {Registration.registerItems(event);}
//    @SubscribeEvent public static void registerBlocks(RegistryEvent.Register<Block> event) {Registration.registerBlocks(event);}

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {

            REPacketHandler.setupChannel();

            EmortisMobSpawns.registerSpawns();
            CanisPacketHandler.init();
            FoodHandler.registerHandler(new MeatFoodHandler());
            FoodHandler.registerDynPredicate(ChungusPupperSkill.INNER_DYN_PRED);
            InteractionHandler.registerHandler(new HelmetInteractionHandler());
            ConfigHandler.initSkillConfig();
            CanisReviveCommand.registerSerializers();
            SpecializedEntityTypes.addEntityAttributes();
            CanisEntity.initDataParameters();
            Capabilities.init();

            EmortisBiomes.registerBiomesToDictionary();
            EmortisBiomes.addBiomeTypes();
            EmortisBiomes.addBiomeVariants();
            EmortisSurfaceBuilder.Configured.registerConfiguredSurfaceBuilders();
//            VanillaCompatRigoranthus.registerDispenserBehaviors();
            VanillaCompatRigoranthus.registerCompostables();
            VanillaCompatRigoranthus.registerFlammables();
            WoodType.register(RigoranthusWoodTypes.AZULOREAL);
            WoodType.register(RigoranthusWoodTypes.JESSIC);
        });
    }
    @SubscribeEvent
    public void onServerStarting(final FMLServerStartingEvent event) {
        LOGGER.info("Ah baby! Da mod man make a message thing!");
    }
    public void registerCommands(final RegisterCommandsEvent event) {
        CanisReviveCommand.register(event.getDispatcher());
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ClientSetup.setupScreenManagers(event);
            ClientSetup.setupCollarRenderers(event);
            ClientSetup.setupEntityRenderers(event);
            ClientSetup.setupTileEntityRenderers(event);
            makeBow(ItemInit.BONE_BOW.get());
            Atlases.addWoodType(RigoranthusWoodTypes.AZULOREAL);
            Atlases.addWoodType(RigoranthusWoodTypes.JESSIC);
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
            RenderTypeLookup.setRenderLayer(Registration.SPECTABILIS_BUSH.get(), RenderType.cutout());
//            RenderTypeLookup.setRenderLayer(Registration.LUMISHROOM.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(Registration.MASTERFUL_SMELTERY.get(), RenderType.cutout());

            RenderTypeLookup.setRenderLayer(FluidRegistry.CADAVEROUS_ICHOR_FLUID.get(), RenderType.translucent());
            RenderTypeLookup.setRenderLayer(FluidRegistry.CADAVEROUS_ICHOR_BLOCK.get(), RenderType.translucent());
            RenderTypeLookup.setRenderLayer(FluidRegistry.CADAVEROUS_ICHOR_FLOWING.get(), RenderType.translucent());
        });
        RenderingRegistry.registerEntityRenderingHandler(SpecializedEntityTypes.CONSUMABLE_PROJECTILE.get(), manager -> new SpriteRenderer<>(manager, Minecraft.getInstance().getItemRenderer()));
        RenderingRegistry.registerEntityRenderingHandler(SpecializedEntityTypes.RETURNING_PROJECTILE.get(), manager -> new SpriteRenderer<>(manager, Minecraft.getInstance().getItemRenderer()));
        RenderingRegistry.registerEntityRenderingHandler(SpecializedEntityTypes.BOUNCING_PROJECTILE.get(), manager -> new SpriteRenderer<>(manager, Minecraft.getInstance().getItemRenderer()));
        RenderingRegistry.registerEntityRenderingHandler(SpecializedEntityTypes.BILI_BOMB.get(), manager -> new SpriteRenderer<>(manager, Minecraft.getInstance().getItemRenderer()));
//        RenderingRegistry.registerEntityRenderingHandler(SpecializedEntityTypes.EMINENTIAL_PROJECTION, EminentialRenderer::new);
//        RenderingRegistry.registerEntityRenderingHandler(SpecializedEntityTypes.PAINTING, manager -> new RenderHangingArt<>(manager, new ResourceLocation("rigoranthusemortisreborn:painting")));
        RenderingRegistry.registerEntityRenderingHandler(SpecializedEntityTypes.DELPHIC_BLOOM.get(), DelphicBloomRenderer::new);
//        RenderingRegistry.registerEntityRenderingHandler(SpecializedEntityTypes.CANIS.get(), CanisRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(RigoranthusEntityTypes.FERAL_CANIS.get(), CanisChordataRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(RigoranthusEntityTypes.NECRAW_FASCII.get(), NecrawFasciiRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(RigoranthusEntityTypes.SUNDERED_CADAVER.get(), SunderedCadaverRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(RigoranthusEntityTypes.LANGUID_DWELLER.get(), LanguidDwellerRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(RigoranthusEntityTypes.BONE_ARROW.get(), BoneArrowRenderer::new);
    }
    private void makeBow(Item item) {
        ItemModelsProperties.register(item, new ResourceLocation("pull"), (p_239429_0_, p_239429_1_, p_239429_2_) -> {
            if (p_239429_2_ == null) {return 0.0F;}
            else {return p_239429_2_.getUseItem() != p_239429_0_ ? 0.0F : (float) (p_239429_0_.getUseDuration() - p_239429_2_.getUseItemRemainingTicks()) / 20.0F;}
        });
        ItemModelsProperties.register(item, new ResourceLocation("pulling"), (p_239428_0_, p_239428_1_, p_239428_2_) -> p_239428_2_ != null && p_239428_2_.isUsingItem() && p_239428_2_.getUseItem() == p_239428_0_ ? 1.0F : 0.0F);
    }
    private void enqueueIMC(final InterModEnqueueEvent event) {
    }

    protected void processIMC(final InterModProcessEvent event) {
//        BackwardsCompat.init();
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        AddonManager.init();
    }

    private void gatherData(final GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();

        if (event.includeClient()) {
            REBlockstateProvider blockstates = new REBlockstateProvider(gen, event.getExistingFileHelper());
            gen.addProvider(blockstates);
            gen.addProvider(new REItemModelProvider(gen, blockstates.getExistingHelper()));
        }

        if (event.includeServer()) {
            gen.addProvider(new REAdvancementProvider(gen));
//            gen.addProvider(new RELootTableProvider(gen));
            REBlockTagsProvider blockTagProvider = new REBlockTagsProvider(gen, event.getExistingFileHelper());
            gen.addProvider(blockTagProvider);
            gen.addProvider(new REItemTagsProvider(gen, blockTagProvider, event.getExistingFileHelper()));
            gen.addProvider(new RERecipeProvider(gen));

//            gen.addProvider(new RECostsProvider(gen));
//            gen.addProvider(new RECombinationsProvider(gen));
//            gen.addProvider(new GeneratedCostConfigProvider(gen, EmortisConstants.MOD_ID));
//            gen.addProvider(new PrimevalCoinPricingProvider(gen, EmortisConstants.MOD_ID));
//
//            gen.addProvider(new REEnUsLanguageProvider(gen));
//            gen.addProvider(new REBiomeProvider(gen));
//            gen.addProvider(new REFluidTagsProvider(gen, event.getExistingFileHelper()));
//            gen.addProvider(new REEntityTypeTagsProvider(gen, event.getExistingFileHelper()));

        }
    }
}