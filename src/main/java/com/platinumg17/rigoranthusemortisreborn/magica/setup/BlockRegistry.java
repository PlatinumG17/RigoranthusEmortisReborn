package com.platinumg17.rigoranthusemortisreborn.magica.setup;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.magica.client.renderer.tile.*;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.*;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.*;
import com.platinumg17.rigoranthusemortisreborn.magica.common.items.AnimBlockItem;
import com.platinumg17.rigoranthusemortisreborn.magica.common.items.FluidBlockItem;
import com.platinumg17.rigoranthusemortisreborn.magica.common.lib.LibBlockNames;
import com.platinumg17.rigoranthusemortisreborn.magica.common.lib.LibItemNames;
import com.platinumg17.rigoranthusemortisreborn.magica.common.world.tree.SupplierBlockStateProvider;
import com.platinumg17.rigoranthusemortisreborn.world.plants.VerdurousLeavesBlock;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.gen.blockstateprovider.BlockStateProviderType;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(EmortisConstants.MOD_ID)
public class BlockRegistry {
    public static AbstractBlock.Properties LOG_PROP = AbstractBlock.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD);
    public static AbstractBlock.Properties SAP_PROP = AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS);

//    @ObjectHolder(LibBlockNames.AZULOREAL_LOG) public static StrippableLog AZULOREAL_LOG;
//    @ObjectHolder(LibBlockNames.AZULOREAL_LEAVES) public static VerdurousLeavesBlock AZULOREAL_LEAVES;
//    @ObjectHolder(LibBlockNames.AZULOREAL_SAPLING) public static SaplingBlock AZULOREAL_SAPLING;
//    @ObjectHolder(LibBlockNames.AZULOREAL_WOOD) public static StrippableLog AZULOREAL_WOOD;
//    @ObjectHolder(LibBlockNames.STRIPPED_AZULOREAL_LOG) public static RotatedPillarBlock STRIPPED_AZULOREAL_LOG;
//    @ObjectHolder(LibBlockNames.STRIPPED_AZULOREAL_WOOD) public static RotatedPillarBlock STRIPPED_AZULOREAL_WOOD;
//    @ObjectHolder(LibBlockNames.JESSIC_LOG) public static StrippableLog JESSIC_LOG;
//    @ObjectHolder(LibBlockNames.JESSIC_LEAVES) public static VerdurousLeavesBlock JESSIC_LEAVES;
//    @ObjectHolder(LibBlockNames.JESSIC_SAPLING) public static SaplingBlock JESSIC_SAPLING;
//    @ObjectHolder(LibBlockNames.JESSIC_WOOD) public static StrippableLog JESSIC_WOOD;
//    @ObjectHolder(LibBlockNames.STRIPPED_JESSIC_LOG) public static RotatedPillarBlock STRIPPED_JESSIC_LOG;
//    @ObjectHolder(LibBlockNames.STRIPPED_JESSIC_WOOD) public static RotatedPillarBlock STRIPPED_JESSIC_WOOD;

    /////    FLOWERS    /////
//    @ObjectHolder(LibBlockNames.AZULOREAL_ORCHID) public static AbnormalsFlowerBlock AZULOREAL_ORCHID;
//    @ObjectHolder(LibBlockNames.IRIDESCENT_SPROUTS) public static AbnormalsFlowerBlock IRIDESCENT_SPROUTS;

//    @ObjectHolder(LibBlockNames.FRAGMENTED_COBBLESTONE) public static FragmentedBlock FRAGMENTED_COBBLESTONE;

    @ObjectHolder(LibBlockNames.PHANTOM_BLOCK) public static PhantomBlock PHANTOM_BLOCK;
    @ObjectHolder(LibBlockNames.PHANTOM_BLOCK) public static TileEntityType<PhantomBlockTile> PHANTOM_TILE;
    @ObjectHolder(LibBlockNames.LIGHT_BLOCK) public static LightBlock LIGHT_BLOCK;
    @ObjectHolder(LibBlockNames.LIGHT_BLOCK) public static TileEntityType<LightTile> LIGHT_TILE;
    @ObjectHolder(LibBlockNames.PSYGLYPHIC_AMALGAMATOR) public static TileEntityType<PsyglyphicAmalgamatorTile> PSYGLYPHIC_AMALG_TILE;
    @ObjectHolder(LibBlockNames.PSYGLYPHIC_AMALGAMATOR) public static PsyglyphicAmalgamatorBlock PSYGLYPHIC_AMALG_BLOCK;
    @ObjectHolder(LibBlockNames.GLYPH_PRESS) public static GlyphPressBlock GLYPH_PRESS_BLOCK;
    @ObjectHolder(LibBlockNames.GLYPH_PRESS) public static TileEntityType<GlyphPressTile> GLYPH_PRESS_TILE;
    @ObjectHolder(LibBlockNames.ICHOR_CRYSTALLIZER) public static IchorCrystallizerBlock ICHOR_CRYSTALLIZER_BLOCK;
    @ObjectHolder(LibBlockNames.ICHOR_CRYSTALLIZER) public static TileEntityType<IchorCrystallizerTile> ICHOR_CRYSTALLIZER_TILE;
    @ObjectHolder(LibBlockNames.SPLINTERED_PEDESTAL) public static TileEntityType<SplinteredPedestalTile> SPLINTERED_PEDESTAL_TILE;
    @ObjectHolder(LibBlockNames.SPLINTERED_PEDESTAL) public static SplinteredPedestal SPLINTERED_PEDESTAL;
    @ObjectHolder(LibBlockNames.DOMINION_JAR) public static DominionJar DOMINION_JAR;
    @ObjectHolder(LibBlockNames.DOMINION_JAR) public static TileEntityType<DominionJarTile> DOMINION_JAR_TILE;
    @ObjectHolder(LibBlockNames.SCRIBES_BLOCK) public static ScribesBlock SCRIBES_BLOCK;
    @ObjectHolder(LibBlockNames.SCRIBES_BLOCK) public static TileEntityType<ScribesTile> SCRIBES_TABLE_TILE;
    @ObjectHolder(LibBlockNames.PORTAL) public static PortalBlock PORTAL_BLOCK;
    @ObjectHolder(LibBlockNames.PORTAL) public static TileEntityType<PortalTile> PORTAL_TILE_TYPE;
    @ObjectHolder(LibBlockNames.INTANGIBLE_AIR) public static IntangibleAirBlock INTANGIBLE_AIR;
    @ObjectHolder(LibBlockNames.INTANGIBLE_AIR) public static  TileEntityType<IntangibleAirTile> INTANGIBLE_AIR_TYPE;
    @ObjectHolder(LibBlockNames.LAVA_LILY) public static LavaLily LAVA_LILY;
    @ObjectHolder(LibBlockNames.DOMINION_BERRY_BUSH) public static DominionBerryBush DOMINION_BERRY_BUSH;
    @ObjectHolder(LibBlockNames.CREATIVE_DOMINION_JAR) public static CreativeDominionJar CREATIVE_DOMINION_JAR;
    @ObjectHolder(LibBlockNames.CREATIVE_DOMINION_JAR) public static TileEntityType<CreativeDominionJarTile> CREATIVE_DOMINION_JAR_TILE;
    @ObjectHolder(LibBlockNames.RITUAL_CIRCLE) public static RitualBlock RITUAL_BLOCK;
    @ObjectHolder(LibBlockNames.RITUAL_CIRCLE) public static TileEntityType<RitualTile> RITUAL_TILE;
    @ObjectHolder(LibBlockNames.DOMINION_GEM_BLOCK) public static ModBlock DOMINION_GEM_BLOCK;

    @ObjectHolder(LibBlockNames.SCONCE) public static SconceBlock SCONCE_BLOCK;
    @ObjectHolder(LibBlockNames.SCONCE) public static TileEntityType<SconceTile> SCONCE_TILE;
    @ObjectHolder(LibBlockNames.ICHOR_JAR) public static IchorJar ICHOR_JAR;
    @ObjectHolder(LibBlockNames.ICHOR_JAR) public static TileEntityType<IchorJarTile> ICHOR_JAR_TILE;
    @ObjectHolder(LibBlockNames.CREATIVE_ICHOR_JAR) public static CreativeIchorJar CREATIVE_ICHOR_JAR;
    @ObjectHolder(LibBlockNames.CREATIVE_ICHOR_JAR) public static TileEntityType<CreativeIchorJarTile> CREATIVE_ICHOR_JAR_TILE;

    @ObjectHolder(LibBlockNames.RELAY_DEPOSIT) public static RelayDepositBlock RELAY_DEPOSIT;
    @ObjectHolder(LibBlockNames.RELAY_DEPOSIT) public static TileEntityType<RelayDepositTile> RELAY_DEPOSIT_TILE;
    @ObjectHolder(LibBlockNames.EMORTIC_RELAY) public static TileEntityType<EmorticRelayTile> EMORTIC_RELAY_TILE;
    @ObjectHolder(LibBlockNames.EMORTIC_RELAY) public static EmorticRelay EMORTIC_RELAY;
    @ObjectHolder(LibBlockNames.EMORTIC_RELAY_SPLITTER) public static EmorticRelaySplitter EMORTIC_RELAY_SPLITTER;
    @ObjectHolder(LibBlockNames.EMORTIC_RELAY_SPLITTER) public static TileEntityType<EmorticRelaySplitterTile> EMORTIC_RELAY_SPLITTER_TILE;
    @ObjectHolder(LibBlockNames.DOMINION_EXTRACTOR) public static DominionExtractorBlock DOMINION_EXTRACTOR_BLOCK;
    @ObjectHolder(LibBlockNames.DOMINION_EXTRACTOR) public static  TileEntityType<DominionExtractorTile> DOMINION_EXTRACTOR_TILE;

    @ObjectHolder(LibBlockNames.ICHOR_EXTRACTOR) public static IchorExtractorBlock ICHOR_EXTRACTOR_BLOCK;
    @ObjectHolder(LibBlockNames.ICHOR_EXTRACTOR) public static TileEntityType<IchorExtractorTile> ICHOR_EXTRACTOR_TILE;
    @ObjectHolder(LibBlockNames.RUNE) public static TileEntityType<RuneTile> RUNE_TILE;
    @ObjectHolder(LibBlockNames.RUNE) public static RuneBlock RUNE_BLOCK;
    @ObjectHolder(LibBlockNames.EMORTIC_CORE) public static EmorticCore EMORTIC_CORE_BLOCK;
    @ObjectHolder(LibBlockNames.EMORTIC_CORE) public static TileEntityType<EmorticCoreTile> EMORTIC_CORE_TILE;
    @ObjectHolder(LibBlockNames.CRYSTALLIZER) public static CrystallizerBlock CRYSTALLIZER_BLOCK;
    @ObjectHolder(LibBlockNames.CRYSTALLIZER) public static TileEntityType<CrystallizerTile> CRYSTALLIZER_TILE;

    @ObjectHolder(LibBlockNames.PSYGLYPHIC_CIPHER) public static PsyglyphicBlock PSYGLYPHIC_CIPHER;
    @ObjectHolder(LibBlockNames.PSYGLYPHIC_CIPHER) public static TileEntityType<PsyglyphicCipherTile> PSYGLYPHIC_TILE;
//    @ObjectHolder(LibBlockNames.SPELL_TURRET) public static TileEntityType<SpellTurretTile> SPELL_TURRET_TYPE;
//    @ObjectHolder(LibBlockNames.SPELL_TURRET) public static SpellTurret SPELL_TURRET;
//    @ObjectHolder(LibBlockNames.BASIC_SPELL_TURRET) public static BasicSpellTurret BASIC_SPELL_TURRET;
//    @ObjectHolder(LibBlockNames.BASIC_SPELL_TURRET) public static TileEntityType<BasicSpellTurretTile> BASIC_SPELL_TURRET_TILE;
    @ObjectHolder(LibBlockNames.STATE_PROVIDER) public static BlockStateProviderType stateProviderType;
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            IForgeRegistry<Block> registry = blockRegistryEvent.getRegistry();
            registry.register(new PhantomBlock());
            registry.register(new LightBlock());
            registry.register(new DominionJar());
            registry.register(new GlyphPressBlock());
            registry.register(new IchorCrystallizerBlock());
            registry.register(new PsyglyphicAmalgamatorBlock());
            registry.register(new SplinteredPedestal());
            registry.register(new ScribesBlock());
            registry.register(new PortalBlock());
            registry.register(new IntangibleAirBlock());
            registry.register(new LavaLily());
            registry.register(new DominionBerryBush(AbstractBlock.Properties.of(Material.PLANT).randomTicks().noCollission().sound(SoundType.SWEET_BERRY_BUSH)));
            registry.register(new CreativeDominionJar());
            registry.register(new RitualBlock(LibBlockNames.RITUAL_CIRCLE));
            registry.register(new ModBlock(ModBlock.defaultProperties().noOcclusion().lightLevel((s) -> 6),LibBlockNames.DOMINION_GEM_BLOCK));
            registry.register(new SconceBlock(LibBlockNames.SCONCE));
            registry.register(new IchorJar());
            registry.register(new CreativeIchorJar());
            registry.register(new RelayDepositBlock());
            registry.register(new EmorticRelay());
            registry.register(new RuneBlock());
            registry.register(new EmorticRelaySplitter());
            registry.register(new EmorticCore());
            registry.register(new CrystallizerBlock());
            registry.register(new DominionExtractorBlock());
            registry.register(new IchorExtractorBlock());
            registry.register(new PsyglyphicBlock());
//            registry.register(new SpellTurret());
//            registry.register(new BasicSpellTurret());
//            registry.register(new TimerSpellTurret());
//            registry.register(new StrippableLog(LOG_PROP, LibBlockNames.AZULOREAL_LOG, () ->BlockRegistry.STRIPPED_AZULOREAL_LOG));
//            registry.register(new RotatedPillarBlock(LOG_PROP).setRegistryName(LibBlockNames.STRIPPED_AZULOREAL_LOG));
//            registry.register(createAzulorealLeavesBlock().setRegistryName(LibBlockNames.AZULOREAL_LEAVES));
//            registry.register(new StrippableLog(LOG_PROP, LibBlockNames.AZULOREAL_WOOD, () ->BlockRegistry.STRIPPED_AZULOREAL_WOOD));
//            registry.register(new RotatedPillarBlock(LOG_PROP).setRegistryName(LibBlockNames.STRIPPED_AZULOREAL_WOOD));
//            registry.register(new SaplingBlock(new AzulorealTree(WorldEvent.AZULOREAL_TREE), SAP_PROP).setRegistryName(LibBlockNames.AZULOREAL_SAPLING));
//
//            registry.register(new StrippableLog(LOG_PROP, LibBlockNames.JESSIC_LOG, () ->BlockRegistry.STRIPPED_JESSIC_LOG));
//            registry.register(new RotatedPillarBlock(LOG_PROP).setRegistryName(LibBlockNames.STRIPPED_JESSIC_LOG));
//            registry.register(createJessicLeavesBlock().setRegistryName(LibBlockNames.JESSIC_LEAVES));
//            registry.register(new StrippableLog(LOG_PROP, LibBlockNames.JESSIC_WOOD, () ->BlockRegistry.STRIPPED_JESSIC_WOOD));
//            registry.register(new RotatedPillarBlock(LOG_PROP).setRegistryName(LibBlockNames.STRIPPED_JESSIC_WOOD));
//            registry.register(new SaplingBlock(new JessicTree(WorldEvent.JESSIC_TREE), SAP_PROP).setRegistryName(LibBlockNames.JESSIC_SAPLING));
//
//            registry.register(createAzulorealOrchidBlock().setRegistryName(LibBlockNames.AZULOREAL_ORCHID));
//            registry.register(createIridescentSproutBlock().setRegistryName(LibBlockNames.IRIDESCENT_SPROUTS));
//            registry.register(new FragmentedBlock(FragmentedBlock.defaultProperties(), LibBlockNames.FRAGMENTED_COBBLESTONE).withTooltip(new TranslationTextComponent("tooltip.block.rigoranthusemortisreborn.fragmented_cobblestone")));
        }
//        public static AbnormalsFlowerBlock createAzulorealOrchidBlock() {
//            return new AbnormalsFlowerBlock(()-> Effects.HEAL, 8, AbstractBlock.Properties.copy(Blocks.AZURE_BLUET));
//        }
//        public static AbnormalsFlowerBlock createIridescentSproutBlock() {
//            return new AbnormalsFlowerBlock(()-> Effects.NIGHT_VISION, 10, AbstractBlock.Properties.copy(Blocks.NETHER_SPROUTS));
//        }
        static Block.Properties woodProp = AbstractBlock.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD);
        public static VerdurousLeavesBlock createAzulorealLeavesBlock() {
            return new VerdurousLeavesBlock(AbstractBlock.Properties.of(Material.LEAVES, MaterialColor.SNOW)
                    .harvestTool(ToolType.HOE).noOcclusion().lightLevel((p_235455_0_) -> 10).strength(0.2F).randomTicks().sound(SoundType.GRASS)
                    .isValidSpawn(BlockRegistry::allowsSpawnOnLeaves).isSuffocating(BlockRegistry::isntSolid).isViewBlocking(BlockRegistry::isntSolid));
        }
        public static VerdurousLeavesBlock createJessicLeavesBlock() {
            return new VerdurousLeavesBlock(AbstractBlock.Properties.of(Material.LEAVES, MaterialColor.COLOR_PURPLE)
                    .harvestTool(ToolType.HOE).noOcclusion().lightLevel((p_235455_0_) -> 10).strength(0.2F).randomTicks().sound(SoundType.GRASS)
                    .isValidSpawn(BlockRegistry::allowsSpawnOnLeaves).isSuffocating(BlockRegistry::isntSolid).isViewBlocking(BlockRegistry::isntSolid));
        }

        @SubscribeEvent
        public static void onTileEntityRegistry(final RegistryEvent.Register<TileEntityType<?>> event){
            event.getRegistry().register(TileEntityType.Builder.of(PhantomBlockTile::new, BlockRegistry.PHANTOM_BLOCK).build(null).setRegistryName(LibBlockNames.PHANTOM_BLOCK));
            event.getRegistry().register(TileEntityType.Builder.of(DominionJarTile::new, BlockRegistry.DOMINION_JAR).build(null).setRegistryName(LibBlockNames.DOMINION_JAR));
            event.getRegistry().register(TileEntityType.Builder.of(LightTile::new, BlockRegistry.LIGHT_BLOCK).build(null).setRegistryName(LibBlockNames.LIGHT_BLOCK));
            event.getRegistry().register(TileEntityType.Builder.of(GlyphPressTile::new, BlockRegistry.GLYPH_PRESS_BLOCK).build(null).setRegistryName(LibBlockNames.GLYPH_PRESS));
            event.getRegistry().register(TileEntityType.Builder.of(IchorCrystallizerTile::new, BlockRegistry.ICHOR_CRYSTALLIZER_BLOCK).build(null).setRegistryName(LibBlockNames.ICHOR_CRYSTALLIZER));
            event.getRegistry().register(TileEntityType.Builder.of(PsyglyphicAmalgamatorTile::new, BlockRegistry.PSYGLYPHIC_AMALG_BLOCK).build(null).setRegistryName(LibBlockNames.PSYGLYPHIC_AMALGAMATOR));
            event.getRegistry().register(TileEntityType.Builder.of(SplinteredPedestalTile::new, BlockRegistry.SPLINTERED_PEDESTAL).build(null).setRegistryName(LibBlockNames.SPLINTERED_PEDESTAL));
            event.getRegistry().register(TileEntityType.Builder.of(ScribesTile::new, BlockRegistry.SCRIBES_BLOCK).build(null).setRegistryName(LibBlockNames.SCRIBES_BLOCK));
            event.getRegistry().register(TileEntityType.Builder.of(PortalTile::new, BlockRegistry.PORTAL_BLOCK).build(null).setRegistryName(LibBlockNames.PORTAL));
            event.getRegistry().register(TileEntityType.Builder.of(IntangibleAirTile::new, BlockRegistry.INTANGIBLE_AIR).build(null).setRegistryName(LibBlockNames.INTANGIBLE_AIR));
            event.getRegistry().register(TileEntityType.Builder.of(CreativeDominionJarTile::new, BlockRegistry.CREATIVE_DOMINION_JAR).build(null).setRegistryName(LibBlockNames.CREATIVE_DOMINION_JAR));
            event.getRegistry().register(TileEntityType.Builder.of(RitualTile::new, BlockRegistry.RITUAL_BLOCK).build(null).setRegistryName(LibBlockNames.RITUAL_CIRCLE));
            event.getRegistry().register(TileEntityType.Builder.of(SconceTile::new, BlockRegistry.SCONCE_BLOCK).build(null).setRegistryName(LibBlockNames.SCONCE));
            event.getRegistry().register(TileEntityType.Builder.of(IchorJarTile::new, BlockRegistry.ICHOR_JAR).build(null).setRegistryName(LibBlockNames.ICHOR_JAR));
            event.getRegistry().register(TileEntityType.Builder.of(CreativeIchorJarTile::new, BlockRegistry.CREATIVE_ICHOR_JAR).build(null).setRegistryName(LibBlockNames.CREATIVE_ICHOR_JAR));
            event.getRegistry().register(TileEntityType.Builder.of(RelayDepositTile::new, BlockRegistry.RELAY_DEPOSIT).build(null).setRegistryName(LibBlockNames.RELAY_DEPOSIT));
            event.getRegistry().register(TileEntityType.Builder.of(EmorticRelayTile::new, BlockRegistry.EMORTIC_RELAY).build(null).setRegistryName(LibBlockNames.EMORTIC_RELAY));
            event.getRegistry().register(TileEntityType.Builder.of(DominionExtractorTile::new, BlockRegistry.DOMINION_EXTRACTOR_BLOCK).build(null).setRegistryName(LibBlockNames.DOMINION_EXTRACTOR));
            event.getRegistry().register(TileEntityType.Builder.of(IchorExtractorTile::new, BlockRegistry.ICHOR_EXTRACTOR_BLOCK).build(null).setRegistryName(LibBlockNames.ICHOR_EXTRACTOR));
            event.getRegistry().register(TileEntityType.Builder.of(EmorticRelaySplitterTile::new, BlockRegistry.EMORTIC_RELAY_SPLITTER).build(null).setRegistryName(LibBlockNames.EMORTIC_RELAY_SPLITTER));
            event.getRegistry().register(TileEntityType.Builder.of(RuneTile::new, BlockRegistry.RUNE_BLOCK).build(null).setRegistryName(LibBlockNames.RUNE));
            event.getRegistry().register(TileEntityType.Builder.of(EmorticCoreTile::new, BlockRegistry.EMORTIC_CORE_BLOCK).build(null).setRegistryName(LibBlockNames.EMORTIC_CORE));
            event.getRegistry().register(TileEntityType.Builder.of(CrystallizerTile::new, BlockRegistry.CRYSTALLIZER_BLOCK).build(null).setRegistryName(LibBlockNames.CRYSTALLIZER));
            event.getRegistry().register(TileEntityType.Builder.of(PsyglyphicCipherTile::new, BlockRegistry.PSYGLYPHIC_CIPHER).build(null).setRegistryName(LibBlockNames.PSYGLYPHIC_CIPHER));
        }
        @SubscribeEvent
        public static void onMagicItemsRegistry(final RegistryEvent.Register<Item> itemRegistryEvent) {
            IForgeRegistry<Item> registry = itemRegistryEvent.getRegistry();
            registry.register(new BlockItem(BlockRegistry.PHANTOM_BLOCK, MagicItemsRegistry.defaultItemProperties()).setRegistryName(LibBlockNames.PHANTOM_BLOCK));
            registry.register(new BlockItem(BlockRegistry.LIGHT_BLOCK, new Item.Properties()).setRegistryName(LibBlockNames.LIGHT_BLOCK));
            registry.register(new BlockItem(BlockRegistry.DOMINION_JAR, MagicItemsRegistry.defaultItemProperties()).setRegistryName(LibBlockNames.DOMINION_JAR));
            registry.register(new AnimBlockItem(BlockRegistry.GLYPH_PRESS_BLOCK, MagicItemsRegistry.defaultItemProperties().setISTER(() -> PressRenderer::getISTER)).setRegistryName(LibBlockNames.GLYPH_PRESS));
            registry.register(new AnimBlockItem(BlockRegistry.ICHOR_CRYSTALLIZER_BLOCK, MagicItemsRegistry.defaultItemProperties().setISTER(() -> IchorCrystallizerRenderer::getISTER)).setRegistryName(LibBlockNames.ICHOR_CRYSTALLIZER));
            registry.register(new BlockItem(BlockRegistry.PSYGLYPHIC_AMALG_BLOCK, MagicItemsRegistry.defaultItemProperties().setISTER(()-> PsyglyphicAmalgamatorRenderer.ISRender::new)).setRegistryName(LibBlockNames.PSYGLYPHIC_AMALGAMATOR));
            registry.register(new BlockItem(BlockRegistry.SPLINTERED_PEDESTAL, MagicItemsRegistry.defaultItemProperties()).setRegistryName(LibBlockNames.SPLINTERED_PEDESTAL));
            registry.register(new AnimBlockItem(BlockRegistry.SCRIBES_BLOCK, MagicItemsRegistry.defaultItemProperties().setISTER(() -> ScribesRenderer::getISTER)).setRegistryName(LibBlockNames.SCRIBES_BLOCK));
            registry.register(new BlockItem(BlockRegistry.PORTAL_BLOCK, new Item.Properties()).setRegistryName(LibBlockNames.PORTAL));
            registry.register(new FluidBlockItem(BlockRegistry.LAVA_LILY, MagicItemsRegistry.defaultItemProperties().fireResistant()).setRegistryName(LibBlockNames.LAVA_LILY));
            registry.register(new BlockItem(BlockRegistry.DOMINION_BERRY_BUSH, MagicItemsRegistry.defaultItemProperties().food(MagicItemsRegistry.DOMINION_BERRY_FOOD)).setRegistryName(LibItemNames.DOMINION_BERRY));
            registry.register(new BlockItem(BlockRegistry.CREATIVE_DOMINION_JAR, MagicItemsRegistry.defaultItemProperties()).setRegistryName(LibBlockNames.CREATIVE_DOMINION_JAR));
            registry.register(new AnimBlockItem(BlockRegistry.RITUAL_BLOCK, MagicItemsRegistry.defaultItemProperties().setISTER(() -> RitualVesselRenderer::getISTER)).setRegistryName(LibBlockNames.RITUAL_CIRCLE));
            registry.register(getDefaultBlockItem(BlockRegistry.DOMINION_GEM_BLOCK, LibBlockNames.DOMINION_GEM_BLOCK));
            registry.register(getDefaultBlockItem(BlockRegistry.SCONCE_BLOCK, LibBlockNames.SCONCE));
            registry.register(new BlockItem(BlockRegistry.ICHOR_JAR, MagicItemsRegistry.defaultItemProperties()).setRegistryName(LibBlockNames.ICHOR_JAR));
            registry.register(new BlockItem(BlockRegistry.CREATIVE_ICHOR_JAR, MagicItemsRegistry.defaultItemProperties()).setRegistryName(LibBlockNames.CREATIVE_ICHOR_JAR));
            registry.register(new AnimBlockItem(BlockRegistry.RELAY_DEPOSIT, MagicItemsRegistry.defaultItemProperties().setISTER(() -> GenericRenderer.getISTER("relay_deposit"))).setRegistryName(LibBlockNames.RELAY_DEPOSIT));
            registry.register(new AnimBlockItem(BlockRegistry.EMORTIC_RELAY, MagicItemsRegistry.defaultItemProperties().setISTER(() -> GenericRenderer.getISTER("emortic_relay"))).setRegistryName(LibBlockNames.EMORTIC_RELAY));
            registry.register(new AnimBlockItem(BlockRegistry.EMORTIC_RELAY_SPLITTER, MagicItemsRegistry.defaultItemProperties().setISTER(() -> GenericRenderer.getISTER("relay_splitter"))).setRegistryName(LibBlockNames.EMORTIC_RELAY_SPLITTER));
            registry.register(new AnimBlockItem(BlockRegistry.DOMINION_EXTRACTOR_BLOCK, MagicItemsRegistry.defaultItemProperties().fireResistant().setISTER(() -> DominionExtractorRenderer::getISTER)).setRegistryName(LibBlockNames.DOMINION_EXTRACTOR));
            registry.register(new AnimBlockItem(BlockRegistry.ICHOR_EXTRACTOR_BLOCK, MagicItemsRegistry.defaultItemProperties().fireResistant().setISTER(() -> IchorExtractorRenderer::getISTER)).setRegistryName(LibBlockNames.ICHOR_EXTRACTOR));
            registry.register(new BlockItem(BlockRegistry.CRYSTALLIZER_BLOCK, MagicItemsRegistry.defaultItemProperties()).setRegistryName(LibBlockNames.CRYSTALLIZER));
            registry.register(new BlockItem(BlockRegistry.EMORTIC_CORE_BLOCK, MagicItemsRegistry.defaultItemProperties()).setRegistryName(LibBlockNames.EMORTIC_CORE));
            registry.register(new BlockItem(BlockRegistry.RUNE_BLOCK, MagicItemsRegistry.defaultItemProperties()).setRegistryName(LibBlockNames.RUNE));
            registry.register(new AnimBlockItem(BlockRegistry.PSYGLYPHIC_CIPHER, MagicItemsRegistry.defaultItemProperties().fireResistant().setISTER(() -> PsyglyphicRenderer::getISTER)).setRegistryName(LibBlockNames.PSYGLYPHIC_CIPHER));
//            registry.register(new BlockItem(BlockRegistry.FRAGMENTED_COBBLESTONE, MagicItemsRegistry.defaultItemProperties()).setRegistryName(LibBlockNames.FRAGMENTED_COBBLESTONE));
//            registry.register(getDefaultBlockItem(BlockRegistry.JESSIC_LEAVES, LibBlockNames.JESSIC_LEAVES));
//            registry.register(getDefaultBlockItem(BlockRegistry.JESSIC_LOG, LibBlockNames.JESSIC_LOG));
//            registry.register(getDefaultBlockItem(BlockRegistry.JESSIC_SAPLING, LibBlockNames.JESSIC_SAPLING));
//            registry.register(getDefaultBlockItem(BlockRegistry.JESSIC_WOOD, LibBlockNames.JESSIC_WOOD));
//            registry.register(getDefaultBlockItem(BlockRegistry.AZULOREAL_LEAVES, LibBlockNames.AZULOREAL_LEAVES));
//            registry.register(getDefaultBlockItem(BlockRegistry.AZULOREAL_LOG, LibBlockNames.AZULOREAL_LOG));
//            registry.register(getDefaultBlockItem(BlockRegistry.AZULOREAL_SAPLING, LibBlockNames.AZULOREAL_SAPLING));
//            registry.register(getDefaultBlockItem(BlockRegistry.AZULOREAL_WOOD, LibBlockNames.AZULOREAL_WOOD));
        }
        public static Item getDefaultBlockItem(Block block, String registry) {
            return new BlockItem(block, MagicItemsRegistry.defaultItemProperties()).setRegistryName(registry);
        }
        @SubscribeEvent
        public static void registerBlockProvider(final RegistryEvent.Register<BlockStateProviderType<?>> e) {
            e.getRegistry().register(new BlockStateProviderType<>(SupplierBlockStateProvider.CODEC).setRegistryName(EmortisConstants.MOD_ID, LibBlockNames.STATE_PROVIDER));
        }
    }
    private static Boolean allowsSpawnOnLeaves(BlockState state, IBlockReader reader, BlockPos pos, EntityType<?> entity) {
        return entity == EntityType.OCELOT || entity == EntityType.PARROT;
    }
    private static boolean isntSolid(BlockState state, IBlockReader reader, BlockPos pos) {
        return false;
    }
}