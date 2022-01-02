package com.platinumg17.rigoranthusemortisreborn.magica.setup;

import com.minecraftabnormals.abnormals_core.common.blocks.AbnormalsFlowerBlock;
import com.minecraftabnormals.abnormals_core.common.blocks.wood.AbnormalsSaplingBlock;
import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.blocks.custom.skull.HangingSkullBlock;
import com.platinumg17.rigoranthusemortisreborn.blocks.tileentity.HangingSkullTile;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.magica.client.renderer.tile.*;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.*;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.*;
import com.platinumg17.rigoranthusemortisreborn.magica.common.items.AnimBlockItem;
import com.platinumg17.rigoranthusemortisreborn.magica.common.lib.LibBlockNames;
import com.platinumg17.rigoranthusemortisreborn.magica.common.lib.LibItemNames;
import com.platinumg17.rigoranthusemortisreborn.world.plants.SpectabilisBushBlock;
import com.platinumg17.rigoranthusemortisreborn.world.plants.VerdurousLeavesBlock;
import com.platinumg17.rigoranthusemortisreborn.world.trees.AzulorealTree;
import com.platinumg17.rigoranthusemortisreborn.world.trees.JessicTree;
import com.platinumg17.rigoranthusemortisreborn.world.trees.StrippableLog;
import com.platinumg17.rigoranthusemortisreborn.world.trees.SupplierBlockStateProvider;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effects;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.gen.blockstateprovider.BlockStateProviderType;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import javax.annotation.Nullable;
import java.util.List;

@ObjectHolder(EmortisConstants.MOD_ID)
public class BlockRegistry {
    public static AbstractBlock.Properties LOG_PROP = AbstractBlock.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD);
    public static AbstractBlock.Properties SAP_PROP = AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS);
    public static AbstractBlock.Properties FLOWER_POT_PROP = AbstractBlock.Properties.of(Material.DECORATION).strength(0.0F).noOcclusion();

    @ObjectHolder(LibBlockNames.HANGING_CADAVER_SKULL) public static HangingSkullBlock hangingCadaverSkull;
    @ObjectHolder(LibBlockNames.HANGING_CADAVER_SKULL) public static TileEntityType<HangingSkullTile> hangingCadaverSkullTile;

    ///    WOOD / TREES    /////
    @ObjectHolder(LibBlockNames.AZULOREAL_LOG) public static StrippableLog AZULOREAL_LOG;
    @ObjectHolder(LibBlockNames.AZULOREAL_LEAVES) public static VerdurousLeavesBlock AZULOREAL_LEAVES;
    @ObjectHolder(LibBlockNames.AZULOREAL_SAPLING) public static AbnormalsSaplingBlock AZULOREAL_SAPLING;
    @ObjectHolder(LibBlockNames.AZULOREAL_WOOD) public static StrippableLog AZULOREAL_WOOD;
    @ObjectHolder(LibBlockNames.STRIPPED_AZULOREAL_LOG) public static RotatedPillarBlock STRIPPED_AZULOREAL_LOG;
    @ObjectHolder(LibBlockNames.STRIPPED_AZULOREAL_WOOD) public static RotatedPillarBlock STRIPPED_AZULOREAL_WOOD;

    @ObjectHolder(LibBlockNames.JESSIC_LOG) public static StrippableLog JESSIC_LOG;
    @ObjectHolder(LibBlockNames.JESSIC_LEAVES) public static VerdurousLeavesBlock JESSIC_LEAVES;
    @ObjectHolder(LibBlockNames.JESSIC_SAPLING) public static AbnormalsSaplingBlock JESSIC_SAPLING;
    @ObjectHolder(LibBlockNames.JESSIC_WOOD) public static StrippableLog JESSIC_WOOD;
    @ObjectHolder(LibBlockNames.STRIPPED_JESSIC_LOG) public static RotatedPillarBlock STRIPPED_JESSIC_LOG;
    @ObjectHolder(LibBlockNames.STRIPPED_JESSIC_WOOD) public static RotatedPillarBlock STRIPPED_JESSIC_WOOD;

    ///    FLOWERS    /////
    @ObjectHolder(LibBlockNames.AZULOREAL_ORCHID) public static AbnormalsFlowerBlock AZULOREAL_ORCHID;
    @ObjectHolder(LibBlockNames.IRIDESCENT_SPROUTS) public static AbnormalsFlowerBlock IRIDESCENT_SPROUTS;
    @ObjectHolder(LibBlockNames.SPECTABILIS_BUSH) public static SpectabilisBushBlock SPECTABILIS_BUSH;
    @ObjectHolder(LibBlockNames.LISIANTHUS) public static TallFlowerBlock LISIANTHUS;

    @ObjectHolder(LibBlockNames.POTTED_AZULOREAL_ORCHID) public static FlowerPotBlock POTTED_AZULOREAL_ORCHID;
    @ObjectHolder(LibBlockNames.POTTED_IRIDESCENT_SPROUTS) public static FlowerPotBlock POTTED_IRIDESCENT_SPROUTS;
    @ObjectHolder(LibBlockNames.POTTED_AZULOREAL_SAPLING) public static FlowerPotBlock POTTED_AZULOREAL_SAPLING;
    @ObjectHolder(LibBlockNames.POTTED_JESSIC_SAPLING) public static FlowerPotBlock POTTED_JESSIC_SAPLING;

    @ObjectHolder(LibBlockNames.FRAGMENTED_COBBLESTONE) public static FragmentedBlock FRAGMENTED_COBBLESTONE;
    @ObjectHolder(LibBlockNames.PHANTOM_BLOCK) public static PhantomBlock PHANTOM_BLOCK;
    @ObjectHolder(LibBlockNames.PHANTOM_BLOCK) public static TileEntityType<PhantomBlockTile> PHANTOM_TILE;
    @ObjectHolder(LibBlockNames.LIGHT_BLOCK) public static LightBlock LIGHT_BLOCK;
    @ObjectHolder(LibBlockNames.LIGHT_BLOCK) public static TileEntityType<LightTile> LIGHT_TILE;
    @ObjectHolder(LibBlockNames.PSYGLYPHIC_AMALGAMATOR) public static TileEntityType<PsyglyphicAmalgamatorTile> PSYGLYPHIC_AMALG_TILE;
    @ObjectHolder(LibBlockNames.PSYGLYPHIC_AMALGAMATOR) public static PsyglyphicAmalgamatorBlock PSYGLYPHIC_AMALG_BLOCK;
    @ObjectHolder(LibBlockNames.EMORTIC_CRAFTING_PRESS) public static EmorticCraftingPressBlock EMORTIC_CRAFTING_PRESS_BLOCK;
    @ObjectHolder(LibBlockNames.EMORTIC_CRAFTING_PRESS) public static TileEntityType<EmorticCraftingPressTile> EMORTIC_CRAFTING_PRESS_TILE;
    @ObjectHolder(LibBlockNames.SPLINTERED_PEDESTAL) public static TileEntityType<SplinteredPedestalTile> SPLINTERED_PEDESTAL_TILE;
    @ObjectHolder(LibBlockNames.SPLINTERED_PEDESTAL) public static SplinteredPedestal SPLINTERED_PEDESTAL;
    @ObjectHolder(LibBlockNames.DOMINION_JAR) public static DominionJar DOMINION_JAR;
    @ObjectHolder(LibBlockNames.DOMINION_JAR) public static TileEntityType<DominionJarTile> DOMINION_JAR_TILE;
    @ObjectHolder(LibBlockNames.TABLE_BLOCK) public static TableBlock TABLE_BLOCK;
    @ObjectHolder(LibBlockNames.TABLE_BLOCK) public static TileEntityType<TableTile> TABLE_TILE;
    @ObjectHolder(LibBlockNames.PORTAL) public static PortalBlock PORTAL_BLOCK;
    @ObjectHolder(LibBlockNames.PORTAL) public static TileEntityType<PortalTile> PORTAL_TILE_TYPE;
    @ObjectHolder(LibBlockNames.INTANGIBLE_AIR) public static IntangibleAirBlock INTANGIBLE_AIR;
    @ObjectHolder(LibBlockNames.INTANGIBLE_AIR) public static  TileEntityType<IntangibleAirTile> INTANGIBLE_AIR_TYPE;
//    @ObjectHolder(LibBlockNames.RE_LILLY_PAD) public static RELilyPad RE_LILLY_PAD;
    @ObjectHolder(LibBlockNames.DOMINION_BERRY_BUSH) public static DominionBerryBush DOMINION_BERRY_BUSH;
    @ObjectHolder(LibBlockNames.CREATIVE_DOMINION_JAR) public static CreativeDominionJar CREATIVE_DOMINION_JAR;
    @ObjectHolder(LibBlockNames.CREATIVE_DOMINION_JAR) public static TileEntityType<CreativeDominionJarTile> CREATIVE_DOMINION_JAR_TILE;
    @ObjectHolder(LibBlockNames.RITUAL_VESSEL) public static RitualVesselBlock RITUAL_BLOCK;
    @ObjectHolder(LibBlockNames.RITUAL_VESSEL) public static TileEntityType<RitualTile> RITUAL_TILE;
    @ObjectHolder(LibBlockNames.DOMINION_GEM_BLOCK) public static ModBlock DOMINION_GEM_BLOCK;

    @ObjectHolder(LibBlockNames.ICHOR_JAR) public static IchorJar ICHOR_JAR;
    @ObjectHolder(LibBlockNames.ICHOR_JAR) public static TileEntityType<IchorJarTile> ICHOR_JAR_TILE;
    @ObjectHolder(LibBlockNames.CREATIVE_ICHOR_JAR) public static CreativeIchorJar CREATIVE_ICHOR_JAR;
    @ObjectHolder(LibBlockNames.CREATIVE_ICHOR_JAR) public static TileEntityType<CreativeIchorJarTile> CREATIVE_ICHOR_JAR_TILE;

    @ObjectHolder(LibBlockNames.RELAY_DEPOSIT) public static RelayDepositBlock RELAY_DEPOSIT;
    @ObjectHolder(LibBlockNames.RELAY_DEPOSIT) public static TileEntityType<RelayDepositTile> RELAY_DEPOSIT_TILE;
    @ObjectHolder(LibBlockNames.EMORTIC_RELAY) public static TileEntityType<EmorticRelayTile> EMORTIC_RELAY_TILE;
    @ObjectHolder(LibBlockNames.EMORTIC_RELAY) public static EmorticRelay EMORTIC_RELAY;
    @ObjectHolder(LibBlockNames.RELAY_SPLITTER) public static RelaySplitterBlock RELAY_SPLITTER;
    @ObjectHolder(LibBlockNames.RELAY_SPLITTER) public static TileEntityType<RelaySplitterTile> RELAY_SPLITTER_TILE;
//    @ObjectHolder(LibBlockNames.DOMINION_EXTRACTOR) public static DominionExtractorBlock DOMINION_EXTRACTOR_BLOCK;
//    @ObjectHolder(LibBlockNames.DOMINION_EXTRACTOR) public static  TileEntityType<DominionExtractorTile> DOMINION_EXTRACTOR_TILE;

    @ObjectHolder(LibBlockNames.ICHOR_EXTRACTOR) public static IchorExtractorBlock ICHOR_EXTRACTOR_BLOCK;
    @ObjectHolder(LibBlockNames.ICHOR_EXTRACTOR) public static TileEntityType<IchorExtractorTile> ICHOR_EXTRACTOR_TILE;
    @ObjectHolder(LibBlockNames.EMORTIC_CORTEX) public static EmorticCortex EMORTIC_CORTEX_BLOCK;
    @ObjectHolder(LibBlockNames.EMORTIC_CORTEX) public static TileEntityType<EmorticCortexTile> EMORTIC_CORTEX_TILE;
    @ObjectHolder(LibBlockNames.ICHOR_CRYSTALLIZER) public static IchorCrystallizerBlock ICHOR_CRYSTALLIZER_BLOCK;
    @ObjectHolder(LibBlockNames.ICHOR_CRYSTALLIZER) public static TileEntityType<IchorCrystallizerTile> ICHOR_CRYSTALLIZER_TILE;

    @ObjectHolder(LibBlockNames.PSYGLYPHIC_CIPHER) public static CipherBlock PSYGLYPHIC_CIPHER;
    @ObjectHolder(LibBlockNames.PSYGLYPHIC_CIPHER) public static TileEntityType<PsyglyphicCipherTile> PSYGLYPHIC_TILE;

    @ObjectHolder(LibBlockNames.STATE_PROVIDER) public static BlockStateProviderType stateProviderType;
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            IForgeRegistry<Block> registry = blockRegistryEvent.getRegistry();
            registry.register(new StrippableLog(LOG_PROP, LibBlockNames.AZULOREAL_LOG, () -> BlockRegistry.STRIPPED_AZULOREAL_LOG));
            registry.register(new RotatedPillarBlock(LOG_PROP).setRegistryName(LibBlockNames.STRIPPED_AZULOREAL_LOG));
            registry.register(createAzulorealLeavesBlock().setRegistryName(LibBlockNames.AZULOREAL_LEAVES));
            registry.register(new StrippableLog(LOG_PROP, LibBlockNames.AZULOREAL_WOOD, () -> BlockRegistry.STRIPPED_AZULOREAL_WOOD));
            registry.register(new RotatedPillarBlock(LOG_PROP).setRegistryName(LibBlockNames.STRIPPED_AZULOREAL_WOOD));
            registry.register(new AbnormalsSaplingBlock(new AzulorealTree(), SAP_PROP).setRegistryName(LibBlockNames.AZULOREAL_SAPLING));
            registry.register(new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, () -> AZULOREAL_SAPLING, FLOWER_POT_PROP).setRegistryName(LibBlockNames.POTTED_AZULOREAL_SAPLING));

            registry.register(new StrippableLog(LOG_PROP, LibBlockNames.JESSIC_LOG, () -> BlockRegistry.STRIPPED_JESSIC_LOG));
            registry.register(new RotatedPillarBlock(LOG_PROP).setRegistryName(LibBlockNames.STRIPPED_JESSIC_LOG));
            registry.register(createJessicLeavesBlock().setRegistryName(LibBlockNames.JESSIC_LEAVES));
            registry.register(new StrippableLog(LOG_PROP, LibBlockNames.JESSIC_WOOD, () -> BlockRegistry.STRIPPED_JESSIC_WOOD));
            registry.register(new RotatedPillarBlock(LOG_PROP).setRegistryName(LibBlockNames.STRIPPED_JESSIC_WOOD));
            registry.register(new AbnormalsSaplingBlock(new JessicTree(), SAP_PROP).setRegistryName(LibBlockNames.JESSIC_SAPLING));
            registry.register(new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, () -> JESSIC_SAPLING, FLOWER_POT_PROP).setRegistryName(LibBlockNames.POTTED_JESSIC_SAPLING));

            registry.register(createAzulorealOrchidBlock().setRegistryName(LibBlockNames.AZULOREAL_ORCHID));
            registry.register(createIridescentSproutBlock().setRegistryName(LibBlockNames.IRIDESCENT_SPROUTS));
            registry.register(new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, () -> AZULOREAL_ORCHID, FLOWER_POT_PROP).setRegistryName(LibBlockNames.POTTED_AZULOREAL_ORCHID));
            registry.register(new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, () -> IRIDESCENT_SPROUTS, FLOWER_POT_PROP).setRegistryName(LibBlockNames.POTTED_IRIDESCENT_SPROUTS));
            registry.register(new TallFlowerBlock(SAP_PROP).setRegistryName(LibBlockNames.LISIANTHUS));
            registry.register(new FragmentedBlock(AbstractBlock.Properties.of(Material.SAND, MaterialColor.STONE).strength(0.8f, 0.8f).harvestTool(ToolType.SHOVEL).harvestLevel(0).sound(SoundType.GRAVEL)));//.withTooltip(new TranslationTextComponent("tooltip.block.rigoranthusemortisreborn.fragmented_cobblestone")));
            registry.register(new DominionBerryBush(AbstractBlock.Properties.of(Material.PLANT).randomTicks().noCollission().sound(SoundType.SWEET_BERRY_BUSH)));
            registry.register(new SpectabilisBushBlock(AbstractBlock.Properties.of(Material.PLANT).randomTicks().noCollission().sound(SoundType.SWEET_BERRY_BUSH)));

            registry.register(new HangingSkullBlock(LibBlockNames.HANGING_CADAVER_SKULL));
            registry.register(new ModBlock(ModBlock.defaultProperties().noOcclusion().lightLevel((s) -> 6), LibBlockNames.DOMINION_GEM_BLOCK));
            registry.register(new SplinteredPedestal());
            registry.register(new PsyglyphicAmalgamatorBlock());
            registry.register(new EmorticCortex());
            registry.register(new EmorticCraftingPressBlock());
            registry.register(new RitualVesselBlock(LibBlockNames.RITUAL_VESSEL));
            registry.register(new CipherBlock());
            registry.register(new IchorJar());
            registry.register(new CreativeIchorJar());
            registry.register(new DominionJar());
            registry.register(new CreativeDominionJar());
            registry.register(new EmorticRelay());
            registry.register(new RelayDepositBlock());
            registry.register(new RelaySplitterBlock());
            registry.register(new IchorCrystallizerBlock());
//            registry.register(new DominionExtractorBlock());
            registry.register(new IchorExtractorBlock());
            registry.register(new LightBlock());
            registry.register(new TableBlock());
            registry.register(new PhantomBlock());
            registry.register(new IntangibleAirBlock());
            registry.register(new PortalBlock());
//            registry.register(new RELilyPad());
        }
        public static AbnormalsFlowerBlock createAzulorealOrchidBlock() {
            return new AbnormalsFlowerBlock(()-> Effects.HEAL, 8, AbstractBlock.Properties.copy(Blocks.AZURE_BLUET));
        }
        public static AbnormalsFlowerBlock createIridescentSproutBlock() {
            return new AbnormalsFlowerBlock(()-> Effects.NIGHT_VISION, 10, AbstractBlock.Properties.copy(Blocks.NETHER_SPROUTS));
        }
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

            event.getRegistry().register(TileEntityType.Builder.of(HangingSkullTile::new, BlockRegistry.hangingCadaverSkull).build(null).setRegistryName(LibBlockNames.HANGING_CADAVER_SKULL));

            event.getRegistry().register(TileEntityType.Builder.of(PhantomBlockTile::new, BlockRegistry.PHANTOM_BLOCK).build(null).setRegistryName(LibBlockNames.PHANTOM_BLOCK));
            event.getRegistry().register(TileEntityType.Builder.of(DominionJarTile::new, BlockRegistry.DOMINION_JAR).build(null).setRegistryName(LibBlockNames.DOMINION_JAR));
            event.getRegistry().register(TileEntityType.Builder.of(LightTile::new, BlockRegistry.LIGHT_BLOCK).build(null).setRegistryName(LibBlockNames.LIGHT_BLOCK));
            event.getRegistry().register(TileEntityType.Builder.of(EmorticCraftingPressTile::new, BlockRegistry.EMORTIC_CRAFTING_PRESS_BLOCK).build(null).setRegistryName(LibBlockNames.EMORTIC_CRAFTING_PRESS));
            event.getRegistry().register(TileEntityType.Builder.of(PsyglyphicAmalgamatorTile::new, BlockRegistry.PSYGLYPHIC_AMALG_BLOCK).build(null).setRegistryName(LibBlockNames.PSYGLYPHIC_AMALGAMATOR));
            event.getRegistry().register(TileEntityType.Builder.of(SplinteredPedestalTile::new, BlockRegistry.SPLINTERED_PEDESTAL).build(null).setRegistryName(LibBlockNames.SPLINTERED_PEDESTAL));
            event.getRegistry().register(TileEntityType.Builder.of(TableTile::new, BlockRegistry.TABLE_BLOCK).build(null).setRegistryName(LibBlockNames.TABLE_BLOCK));
            event.getRegistry().register(TileEntityType.Builder.of(PortalTile::new, BlockRegistry.PORTAL_BLOCK).build(null).setRegistryName(LibBlockNames.PORTAL));
            event.getRegistry().register(TileEntityType.Builder.of(IntangibleAirTile::new, BlockRegistry.INTANGIBLE_AIR).build(null).setRegistryName(LibBlockNames.INTANGIBLE_AIR));
            event.getRegistry().register(TileEntityType.Builder.of(CreativeDominionJarTile::new, BlockRegistry.CREATIVE_DOMINION_JAR).build(null).setRegistryName(LibBlockNames.CREATIVE_DOMINION_JAR));
            event.getRegistry().register(TileEntityType.Builder.of(RitualTile::new, BlockRegistry.RITUAL_BLOCK).build(null).setRegistryName(LibBlockNames.RITUAL_VESSEL));
            event.getRegistry().register(TileEntityType.Builder.of(IchorJarTile::new, BlockRegistry.ICHOR_JAR).build(null).setRegistryName(LibBlockNames.ICHOR_JAR));
            event.getRegistry().register(TileEntityType.Builder.of(CreativeIchorJarTile::new, BlockRegistry.CREATIVE_ICHOR_JAR).build(null).setRegistryName(LibBlockNames.CREATIVE_ICHOR_JAR));
            event.getRegistry().register(TileEntityType.Builder.of(RelayDepositTile::new, BlockRegistry.RELAY_DEPOSIT).build(null).setRegistryName(LibBlockNames.RELAY_DEPOSIT));
            event.getRegistry().register(TileEntityType.Builder.of(EmorticRelayTile::new, BlockRegistry.EMORTIC_RELAY).build(null).setRegistryName(LibBlockNames.EMORTIC_RELAY));
//            event.getRegistry().register(TileEntityType.Builder.of(DominionExtractorTile::new, BlockRegistry.DOMINION_EXTRACTOR_BLOCK).build(null).setRegistryName(LibBlockNames.DOMINION_EXTRACTOR));
            event.getRegistry().register(TileEntityType.Builder.of(IchorExtractorTile::new, BlockRegistry.ICHOR_EXTRACTOR_BLOCK).build(null).setRegistryName(LibBlockNames.ICHOR_EXTRACTOR));
            event.getRegistry().register(TileEntityType.Builder.of(RelaySplitterTile::new, BlockRegistry.RELAY_SPLITTER).build(null).setRegistryName(LibBlockNames.RELAY_SPLITTER));
            event.getRegistry().register(TileEntityType.Builder.of(EmorticCortexTile::new, BlockRegistry.EMORTIC_CORTEX_BLOCK).build(null).setRegistryName(LibBlockNames.EMORTIC_CORTEX));
            event.getRegistry().register(TileEntityType.Builder.of(IchorCrystallizerTile::new, BlockRegistry.ICHOR_CRYSTALLIZER_BLOCK).build(null).setRegistryName(LibBlockNames.ICHOR_CRYSTALLIZER));
            event.getRegistry().register(TileEntityType.Builder.of(PsyglyphicCipherTile::new, BlockRegistry.PSYGLYPHIC_CIPHER).build(null).setRegistryName(LibBlockNames.PSYGLYPHIC_CIPHER));
        }
        @SubscribeEvent
        public static void onMagicItemsRegistry(final RegistryEvent.Register<Item> itemRegistryEvent) {
            IForgeRegistry<Item> registry = itemRegistryEvent.getRegistry();
            Item dominionBerry = new BlockNamedItem(BlockRegistry.DOMINION_BERRY_BUSH, MagicItemsRegistry.defaultItemProperties().food(MagicItemsRegistry.DOMINION_BERRY_FOOD)).setRegistryName(LibItemNames.DOMINION_BERRY);
            Item bilisBerry = new BlockNamedItem(BlockRegistry.SPECTABILIS_BUSH, MagicItemsRegistry.defaultItemProperties().food(MagicItemsRegistry.BILIS_BERRY_FOOD)).setRegistryName(LibItemNames.BILIS_BERRY);

            ComposterBlock.COMPOSTABLES.putIfAbsent(dominionBerry, 0.3f);
            ComposterBlock.COMPOSTABLES.putIfAbsent(bilisBerry, 0.3f);
            registry.register(dominionBerry);
            registry.register(bilisBerry);
            registry.register(getDefaultBlockItem(BlockRegistry.JESSIC_LEAVES, LibBlockNames.JESSIC_LEAVES));
            registry.register(getDefaultBlockItem(BlockRegistry.JESSIC_LOG, LibBlockNames.JESSIC_LOG));
            registry.register(getDefaultBlockItem(BlockRegistry.JESSIC_SAPLING, LibBlockNames.JESSIC_SAPLING));
            registry.register(getDefaultBlockItem(BlockRegistry.JESSIC_WOOD, LibBlockNames.JESSIC_WOOD));
            registry.register(getDefaultBlockItem(BlockRegistry.AZULOREAL_LEAVES, LibBlockNames.AZULOREAL_LEAVES));
            registry.register(getDefaultBlockItem(BlockRegistry.AZULOREAL_LOG, LibBlockNames.AZULOREAL_LOG));
            registry.register(getDefaultBlockItem(BlockRegistry.AZULOREAL_SAPLING, LibBlockNames.AZULOREAL_SAPLING));
            registry.register(getDefaultBlockItem(BlockRegistry.AZULOREAL_WOOD, LibBlockNames.AZULOREAL_WOOD));
            registry.register(getDefaultBlockItem(BlockRegistry.STRIPPED_JESSIC_LOG, LibBlockNames.STRIPPED_JESSIC_LOG));
            registry.register(getDefaultBlockItem(BlockRegistry.STRIPPED_JESSIC_WOOD, LibBlockNames.STRIPPED_JESSIC_WOOD));
            registry.register(getDefaultBlockItem(BlockRegistry.STRIPPED_AZULOREAL_LOG, LibBlockNames.STRIPPED_AZULOREAL_LOG));
            registry.register(getDefaultBlockItem(BlockRegistry.STRIPPED_AZULOREAL_WOOD, LibBlockNames.STRIPPED_AZULOREAL_WOOD));
            registry.register(getDefaultBlockItem(BlockRegistry.LISIANTHUS, LibBlockNames.LISIANTHUS));
            registry.register(getDefaultBlockItem(BlockRegistry.AZULOREAL_ORCHID, LibBlockNames.AZULOREAL_ORCHID));
            registry.register(getDefaultBlockItem(BlockRegistry.IRIDESCENT_SPROUTS, LibBlockNames.IRIDESCENT_SPROUTS));
            registry.register(getDefaultBlockItem(BlockRegistry.DOMINION_GEM_BLOCK, LibBlockNames.DOMINION_GEM_BLOCK));

            registry.register(new AnimBlockItem(BlockRegistry.hangingCadaverSkull, MagicItemsRegistry.defaultItemProperties().setISTER(() -> HangingSkullRenderer::getISTER)).setRegistryName(LibBlockNames.HANGING_CADAVER_SKULL));

//            registry.register(new BlockItem(BlockRegistry.SPLINTERED_PEDESTAL, MagicItemsRegistry.defaultItemProperties()).setRegistryName(LibBlockNames.SPLINTERED_PEDESTAL));
//            registry.register(new BlockItem(BlockRegistry.PSYGLYPHIC_AMALG_BLOCK, MagicItemsRegistry.defaultItemProperties()).setRegistryName(LibBlockNames.PSYGLYPHIC_AMALGAMATOR));
//            registry.register(new AnimBlockItem(BlockRegistry.EMORTIC_CORTEX_BLOCK, MagicItemsRegistry.defaultItemProperties().setISTER(() -> EmorticCortexRenderer::getISTER)).setRegistryName(LibBlockNames.EMORTIC_CORTEX));
//            registry.register(new AnimBlockItem(BlockRegistry.EMORTIC_CRAFTING_PRESS_BLOCK, MagicItemsRegistry.defaultItemProperties().setISTER(() -> EmorticCraftingPressRenderer::getISTER)).setRegistryName(LibBlockNames.EMORTIC_CRAFTING_PRESS));
            registry.register(new AnimBlockItem(BlockRegistry.RITUAL_BLOCK, MagicItemsRegistry.defaultItemProperties().setISTER(() -> RitualVesselRenderer::getISTER)).setRegistryName(LibBlockNames.RITUAL_VESSEL));

            registry.register(new BlockItem(BlockRegistry.DOMINION_JAR, MagicItemsRegistry.defaultItemProperties()).setRegistryName(LibBlockNames.DOMINION_JAR));
            registry.register(new BlockItem(BlockRegistry.CREATIVE_DOMINION_JAR, MagicItemsRegistry.defaultItemProperties()).setRegistryName(LibBlockNames.CREATIVE_DOMINION_JAR));
            registry.register(new BlockItem(BlockRegistry.ICHOR_JAR, MagicItemsRegistry.defaultItemProperties()).setRegistryName(LibBlockNames.ICHOR_JAR));
            registry.register(new BlockItem(BlockRegistry.CREATIVE_ICHOR_JAR, MagicItemsRegistry.defaultItemProperties()).setRegistryName(LibBlockNames.CREATIVE_ICHOR_JAR));
//            registry.register(new AnimBlockItem(BlockRegistry.EMORTIC_RELAY, MagicItemsRegistry.defaultItemProperties().setISTER(() -> EmorticRelayRenderer::getISTER)).setRegistryName(LibBlockNames.EMORTIC_RELAY));
//            registry.register(new AnimBlockItem(BlockRegistry.RELAY_DEPOSIT, MagicItemsRegistry.defaultItemProperties().setISTER(() -> RelayDepositRenderer::getISTER)).setRegistryName(LibBlockNames.RELAY_DEPOSIT));
//            registry.register(new AnimBlockItem(BlockRegistry.RELAY_SPLITTER, MagicItemsRegistry.defaultItemProperties().setISTER(() -> RelaySplitterRenderer::getISTER)).setRegistryName(LibBlockNames.RELAY_SPLITTER));
//            registry.register(new AnimBlockItem(BlockRegistry.DOMINION_EXTRACTOR_BLOCK, MagicItemsRegistry.defaultItemProperties().fireResistant().setISTER(() -> DominionExtractorRenderer::getISTER)).setRegistryName(LibBlockNames.DOMINION_EXTRACTOR));
//            registry.register(new AnimBlockItem(BlockRegistry.ICHOR_EXTRACTOR_BLOCK, MagicItemsRegistry.defaultItemProperties().fireResistant().setISTER(() -> IchorExtractorRenderer::getISTER)).setRegistryName(LibBlockNames.ICHOR_EXTRACTOR));
//            registry.register(new AnimBlockItem(BlockRegistry.ICHOR_CRYSTALLIZER_BLOCK, MagicItemsRegistry.defaultItemProperties().setISTER(() -> IchorCrystallizerRenderer::getISTER)).setRegistryName(LibBlockNames.ICHOR_CRYSTALLIZER));
//            registry.register(new AnimBlockItem(BlockRegistry.PSYGLYPHIC_CIPHER, MagicItemsRegistry.defaultItemProperties().fireResistant().setISTER(() -> CipherRenderer::getISTER)).setRegistryName(LibBlockNames.PSYGLYPHIC_CIPHER));

            registry.register(new AnimBlockItem(BlockRegistry.TABLE_BLOCK, MagicItemsRegistry.defaultItemProperties().setISTER(() -> TableRenderer::getISTER)).setRegistryName(LibBlockNames.TABLE_BLOCK));
            registry.register(new BlockItem(BlockRegistry.PHANTOM_BLOCK, MagicItemsRegistry.defaultItemProperties()).setRegistryName(LibBlockNames.PHANTOM_BLOCK));
            registry.register(new BlockItem(BlockRegistry.LIGHT_BLOCK, new Item.Properties()).setRegistryName(LibBlockNames.LIGHT_BLOCK));
            registry.register(new BlockItem(BlockRegistry.PORTAL_BLOCK, new Item.Properties()).setRegistryName(LibBlockNames.PORTAL));
//            registry.register(new FluidBlockItem(BlockRegistry.RE_LILLY_PAD, MagicItemsRegistry.defaultItemProperties().fireResistant()).setRegistryName(LibBlockNames.RE_LILLY_PAD));

            Item relayDeposit = new AnimBlockItem(BlockRegistry.RELAY_DEPOSIT, new Item.Properties().tab(RigoranthusEmortisReborn.RIGORANTHUS_EMORTIS_GROUP).setISTER(() -> RelayDepositRenderer::getISTER)) {
                @Override public void appendHoverText(ItemStack stack, @Nullable World worldIn, List< ITextComponent > tooltip, ITooltipFlag flagIn) {
                    if (Screen.hasShiftDown()) {
                        tooltip.add(new TranslationTextComponent("tooltip.block." + EmortisConstants.MOD_ID + ".relay_deposit"));
                        tooltip.add(new TranslationTextComponent("tooltip.block." + EmortisConstants.MOD_ID + ".relay_deposit2"));
                        tooltip.add(new TranslationTextComponent("tooltip.block." + EmortisConstants.MOD_ID + ".relay_deposit3"));
                        tooltip.add(new TranslationTextComponent("tooltip.block." + EmortisConstants.MOD_ID + ".relay_deposit4"));
                    } else {
                        tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".hold_shift").setStyle(Style.EMPTY)); }
                    super.appendHoverText(stack, worldIn, tooltip, flagIn);
                }
            }.setRegistryName(LibBlockNames.RELAY_DEPOSIT);

            Item relaySplitter = new AnimBlockItem(BlockRegistry.RELAY_SPLITTER, new Item.Properties().tab(RigoranthusEmortisReborn.RIGORANTHUS_EMORTIS_GROUP).setISTER(() -> RelaySplitterRenderer::getISTER)) {
                @Override public void appendHoverText(ItemStack stack, @Nullable World worldIn, List< ITextComponent > tooltip, ITooltipFlag flagIn) {
                    if (Screen.hasShiftDown()) {
                        tooltip.add(new TranslationTextComponent("tooltip.block." + EmortisConstants.MOD_ID + ".relay_splitter"));
                        tooltip.add(new TranslationTextComponent("tooltip.block." + EmortisConstants.MOD_ID + ".relay_splitter2"));
                        tooltip.add(new TranslationTextComponent("tooltip.block." + EmortisConstants.MOD_ID + ".relay_splitter3"));
                        tooltip.add(new TranslationTextComponent("tooltip.block." + EmortisConstants.MOD_ID + ".relay_splitter4"));
                        tooltip.add(new TranslationTextComponent("tooltip.block." + EmortisConstants.MOD_ID + ".relay_splitter5"));
                        tooltip.add(new TranslationTextComponent("tooltip.block." + EmortisConstants.MOD_ID + ".relay_splitter6"));
                    } else {
                        tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".hold_shift").setStyle(Style.EMPTY)); }
                    super.appendHoverText(stack, worldIn, tooltip, flagIn);
                }
            }.setRegistryName(LibBlockNames.RELAY_SPLITTER);

            Item emorticRelay = new AnimBlockItem(BlockRegistry.EMORTIC_RELAY, new Item.Properties().tab(RigoranthusEmortisReborn.RIGORANTHUS_EMORTIS_GROUP).setISTER(() -> EmorticRelayRenderer::getISTER)) {
                @Override public void appendHoverText(ItemStack stack, @Nullable World worldIn, List< ITextComponent > tooltip, ITooltipFlag flagIn) {
                    if (Screen.hasShiftDown()) {
                        tooltip.add(new TranslationTextComponent("tooltip.block." + EmortisConstants.MOD_ID + ".emortic_relay"));
                        tooltip.add(new TranslationTextComponent("tooltip.block." + EmortisConstants.MOD_ID + ".emortic_relay2"));
                        tooltip.add(new TranslationTextComponent("tooltip.block." + EmortisConstants.MOD_ID + ".emortic_relay3"));
                        tooltip.add(new TranslationTextComponent("tooltip.block." + EmortisConstants.MOD_ID + ".emortic_relay4"));
                        tooltip.add(new TranslationTextComponent("tooltip.block." + EmortisConstants.MOD_ID + ".emortic_relay5"));
                        tooltip.add(new TranslationTextComponent("tooltip.block." + EmortisConstants.MOD_ID + ".emortic_relay6"));
                        tooltip.add(new TranslationTextComponent("tooltip.block." + EmortisConstants.MOD_ID + ".emortic_relay7"));
                        tooltip.add(new TranslationTextComponent("tooltip.block." + EmortisConstants.MOD_ID + ".emortic_relay8"));
                    } else {
                        tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".hold_shift").setStyle(Style.EMPTY)); }
                    super.appendHoverText(stack, worldIn, tooltip, flagIn);
                }
            }.setRegistryName(LibBlockNames.EMORTIC_RELAY);

            Item emorticCortex = new AnimBlockItem(BlockRegistry.EMORTIC_CORTEX_BLOCK, new Item.Properties().tab(RigoranthusEmortisReborn.RIGORANTHUS_EMORTIS_GROUP).setISTER(() -> EmorticCortexRenderer::getISTER)) {
                @Override public void appendHoverText(ItemStack stack, @Nullable World worldIn, List< ITextComponent > tooltip, ITooltipFlag flagIn) {
                if (Screen.hasShiftDown()) {
                    tooltip.add(new TranslationTextComponent("tooltip.block." + EmortisConstants.MOD_ID + ".emortic_cortex"));
                    tooltip.add(new TranslationTextComponent("tooltip.block." + EmortisConstants.MOD_ID + ".emortic_cortex2"));
                    tooltip.add(new TranslationTextComponent("tooltip.block." + EmortisConstants.MOD_ID + ".emortic_cortex3"));
                } else {
                    tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".hold_shift").setStyle(Style.EMPTY)); }
                    super.appendHoverText(stack, worldIn, tooltip, flagIn);
                }
            }.setRegistryName(LibBlockNames.EMORTIC_CORTEX);

            Item ichorExtractor = new AnimBlockItem(BlockRegistry.ICHOR_EXTRACTOR_BLOCK, new Item.Properties().tab(RigoranthusEmortisReborn.RIGORANTHUS_EMORTIS_GROUP).fireResistant().setISTER(() -> IchorExtractorRenderer::getISTER)) {
                @Override public void appendHoverText(ItemStack stack, @Nullable World worldIn, List< ITextComponent > tooltip, ITooltipFlag flagIn) {
                    if (Screen.hasShiftDown()) {
                        tooltip.add(new TranslationTextComponent("tooltip.block." + EmortisConstants.MOD_ID + ".ichor_extractor"));
                        tooltip.add(new TranslationTextComponent("tooltip.block." + EmortisConstants.MOD_ID + ".ichor_extractor2"));
                        tooltip.add(new TranslationTextComponent("tooltip.block." + EmortisConstants.MOD_ID + ".ichor_extractor3"));
                        tooltip.add(new TranslationTextComponent("tooltip.block." + EmortisConstants.MOD_ID + ".ichor_extractor4"));
                        tooltip.add(new TranslationTextComponent("tooltip.block." + EmortisConstants.MOD_ID + ".ichor_extractor5"));
                        tooltip.add(new TranslationTextComponent("tooltip.block." + EmortisConstants.MOD_ID + ".ichor_extractor6"));
                        tooltip.add(new TranslationTextComponent("tooltip.block." + EmortisConstants.MOD_ID + ".ichor_extractor7"));
                        tooltip.add(new TranslationTextComponent("tooltip.block." + EmortisConstants.MOD_ID + ".ichor_extractor8"));
                    } else {
                        tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".hold_shift").setStyle(Style.EMPTY)); }
                    super.appendHoverText(stack, worldIn, tooltip, flagIn);
                }
            }.setRegistryName(LibBlockNames.ICHOR_EXTRACTOR);

            Item cipherBlock = new AnimBlockItem(BlockRegistry.PSYGLYPHIC_CIPHER, new Item.Properties().tab(RigoranthusEmortisReborn.RIGORANTHUS_EMORTIS_GROUP).fireResistant().setISTER(() -> CipherRenderer::getISTER)) {
                @Override public void appendHoverText(ItemStack stack, @Nullable World worldIn, List< ITextComponent > tooltip, ITooltipFlag flagIn) {
                    if (Screen.hasShiftDown()) {
                        tooltip.add(new TranslationTextComponent("tooltip.block." + EmortisConstants.MOD_ID + ".psyglyphic_cipher"));
                        tooltip.add(new TranslationTextComponent("tooltip.block." + EmortisConstants.MOD_ID + ".psyglyphic_cipher2"));
                    } else {
                        tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".hold_shift").setStyle(Style.EMPTY)); }
                    super.appendHoverText(stack, worldIn, tooltip, flagIn);
                }
            }.setRegistryName(LibBlockNames.PSYGLYPHIC_CIPHER);

            Item ichorCrystallizer = new AnimBlockItem(BlockRegistry.ICHOR_CRYSTALLIZER_BLOCK, new Item.Properties().tab(RigoranthusEmortisReborn.RIGORANTHUS_EMORTIS_GROUP).fireResistant().setISTER(() -> IchorCrystallizerRenderer::getISTER)) {
                @Override public void appendHoverText(ItemStack stack, @Nullable World worldIn, List< ITextComponent > tooltip, ITooltipFlag flagIn) {
                    if (Screen.hasShiftDown()) {
                        tooltip.add(new TranslationTextComponent("tooltip.block." + EmortisConstants.MOD_ID + ".ichor_crystallizer"));
                        tooltip.add(new TranslationTextComponent("tooltip.block." + EmortisConstants.MOD_ID + ".ichor_crystallizer2"));
                        tooltip.add(new TranslationTextComponent("tooltip.block." + EmortisConstants.MOD_ID + ".ichor_crystallizer3"));
                    } else {
                        tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".hold_shift").setStyle(Style.EMPTY)); }
                    super.appendHoverText(stack, worldIn, tooltip, flagIn);
                }
            }.setRegistryName(LibBlockNames.ICHOR_CRYSTALLIZER);

            Item craftingPress = new AnimBlockItem(BlockRegistry.EMORTIC_CRAFTING_PRESS_BLOCK, new Item.Properties().tab(RigoranthusEmortisReborn.RIGORANTHUS_EMORTIS_GROUP).fireResistant().setISTER(() -> EmorticCraftingPressRenderer::getISTER)) {
                @Override public void appendHoverText(ItemStack stack, @Nullable World worldIn, List< ITextComponent > tooltip, ITooltipFlag flagIn) {
                    if (Screen.hasShiftDown()) {
                        tooltip.add(new TranslationTextComponent("tooltip.block." + EmortisConstants.MOD_ID + ".crafting_press"));
                        tooltip.add(new TranslationTextComponent("tooltip.block." + EmortisConstants.MOD_ID + ".crafting_press2"));
                        tooltip.add(new TranslationTextComponent("tooltip.block." + EmortisConstants.MOD_ID + ".crafting_press3"));
                        tooltip.add(new TranslationTextComponent("tooltip.block." + EmortisConstants.MOD_ID + ".crafting_press4"));
                        tooltip.add(new TranslationTextComponent("tooltip.block." + EmortisConstants.MOD_ID + ".crafting_press5"));
                        tooltip.add(new TranslationTextComponent("tooltip.block." + EmortisConstants.MOD_ID + ".crafting_press6"));
                        tooltip.add(new TranslationTextComponent("tooltip.block." + EmortisConstants.MOD_ID + ".crafting_press7"));
                        tooltip.add(new TranslationTextComponent("tooltip.block." + EmortisConstants.MOD_ID + ".crafting_press8"));
                        tooltip.add(new TranslationTextComponent("tooltip.block." + EmortisConstants.MOD_ID + ".crafting_press9"));
                        tooltip.add(new TranslationTextComponent("tooltip.block." + EmortisConstants.MOD_ID + ".crafting_press10"));
                        tooltip.add(new TranslationTextComponent("tooltip.block." + EmortisConstants.MOD_ID + ".crafting_press11"));
                        tooltip.add(new TranslationTextComponent("tooltip.block." + EmortisConstants.MOD_ID + ".crafting_press12"));
                        tooltip.add(new TranslationTextComponent("tooltip.block." + EmortisConstants.MOD_ID + ".crafting_press13"));
                    } else {
                        tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".hold_shift").setStyle(Style.EMPTY)); }
                    super.appendHoverText(stack, worldIn, tooltip, flagIn);
                }
            }.setRegistryName(LibBlockNames.EMORTIC_CRAFTING_PRESS);

            registry.register(registerTooltipBlockItem(LibBlockNames.FRAGMENTED_COBBLESTONE, BlockRegistry.FRAGMENTED_COBBLESTONE,"fragmented_cobblestone", "fragmented_cobblestone2"));

            registry.register(emorticRelay);
            registry.register(relaySplitter);
            registry.register(relayDeposit);

            registry.register(cipherBlock);
            registry.register(ichorExtractor);
            registry.register(ichorCrystallizer);
            registry.register(emorticCortex);
            registry.register(registerTooltipBlockItem(LibBlockNames.PSYGLYPHIC_AMALGAMATOR, BlockRegistry.PSYGLYPHIC_AMALG_BLOCK,"psyglyphic_amalgamator", "psyglyphic_amalgamator2"));
            registry.register(registerTooltipBlockItem(LibBlockNames.SPLINTERED_PEDESTAL, BlockRegistry.SPLINTERED_PEDESTAL,"splintered_pedestal", "splintered_pedestal2"));
            registry.register(craftingPress);
        }
        public static Item getDefaultBlockItem(Block block, String registry) {
            return new BlockItem(block, MagicItemsRegistry.defaultItemProperties()).setRegistryName(registry);
        }
//        private static void createTooltipList(List<ITextComponent> tooltip, String... tooltips) {
//            String textComp = new TranslationTextComponent(Arrays.stream(tooltips).toArray(String[]::new));
//            tooltip.add(textComp);
//        }
        private static Item registerTooltipBlockItem(String name, Block block, String tooltipKey, String tooltipKey2) {
            return new BlockItem(block, new Item.Properties().tab(RigoranthusEmortisReborn.RIGORANTHUS_EMORTIS_GROUP)) {
                @Override
                public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
                    if (Screen.hasShiftDown()) {
                        tooltip.add(new TranslationTextComponent("tooltip.block.rigoranthusemortisreborn." + tooltipKey));
                        tooltip.add(new TranslationTextComponent("tooltip.block.rigoranthusemortisreborn." + tooltipKey2));
                    } else {
                        tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".hold_shift").setStyle(Style.EMPTY));
                    }
                    super.appendHoverText(stack, worldIn, tooltip, flagIn);
                }
            }.setRegistryName(name);
        }
        private static Item registerTooltipAnimBlockItem(String name, Block block, String tooltipKey) {
            return new AnimBlockItem(block, new Item.Properties().tab(RigoranthusEmortisReborn.RIGORANTHUS_EMORTIS_GROUP)) {
                @Override
                public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
                    if (Screen.hasShiftDown()) {
                        tooltip.add(new TranslationTextComponent(tooltipKey));
                    } else {
                        tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".hold_shift").setStyle(Style.EMPTY));
                    }
                    super.appendHoverText(stack, worldIn, tooltip, flagIn);
                }
            }.setRegistryName(name);
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











//            registry.register(new WoodButtonBlock(AbstractBlock.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD)).setRegistryName(LibBlockNames.JESSIC_BUTTON));
//            registry.register(new StairsBlock(()-> JESSIC_PLANKS.defaultBlockState(),woodProp).setRegistryName(LibBlockNames.JESSIC_STAIRS));
//            registry.register(new SlabBlock(woodProp).setRegistryName(LibBlockNames.JESSIC_SLAB));
//            registry.register(new FenceGateBlock(woodProp).setRegistryName(LibBlockNames.JESSIC_FENCE_GATE));
//            registry.register(new FenceBlock(woodProp).setRegistryName(LibBlockNames.JESSIC_FENCE));
//            registry.register(new DoorBlock(woodProp).setRegistryName(LibBlockNames.JESSIC_DOOR));
//            registry.register(new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, woodProp).setRegistryName(LibBlockNames.JESSIC_PRESSURE_PLATE));
//            registry.register(new TrapDoorBlock(woodProp).setRegistryName(LibBlockNames.JESSIC_TRAPDOOR));
//            registry.register(new WoodButtonBlock(AbstractBlock.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD)).setRegistryName(LibBlockNames.AZULOREAL_BUTTON));
//            registry.register(new StairsBlock(()-> AZULOREAL_PLANKS.defaultBlockState(),woodProp).setRegistryName(LibBlockNames.AZULOREAL_STAIRS));
//            registry.register(new SlabBlock(woodProp).setRegistryName(LibBlockNames.AZULOREAL_SLAB));
//            registry.register(new FenceGateBlock(woodProp).setRegistryName(LibBlockNames.AZULOREAL_FENCE_GATE));
//            registry.register(new FenceBlock(woodProp).setRegistryName(LibBlockNames.AZULOREAL_FENCE));
//            registry.register(new DoorBlock(woodProp).setRegistryName(LibBlockNames.AZULOREAL_DOOR));
//            registry.register(new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, woodProp).setRegistryName(LibBlockNames.AZULOREAL_PRESSURE_PLATE));
//            registry.register(new TrapDoorBlock(woodProp).setRegistryName(LibBlockNames.AZULOREAL_TRAPDOOR));

//            registry.register(getDefaultBlockItem(BlockRegistry.JESSIC_PLANKS, LibBlockNames.JESSIC_PLANKS));
//            registry.register(getDefaultBlockItem(BlockRegistry.JESSIC_BUTTON, LibBlockNames.JESSIC_BUTTON));
//            registry.register(getDefaultBlockItem(BlockRegistry.JESSIC_STAIRS, LibBlockNames.JESSIC_STAIRS));
//            registry.register(getDefaultBlockItem(BlockRegistry.JESSIC_SLAB, LibBlockNames.JESSIC_SLAB));
//            registry.register(getDefaultBlockItem(BlockRegistry.JESSIC_FENCE_GATE, LibBlockNames.JESSIC_FENCE_GATE));
//            registry.register(getDefaultBlockItem(BlockRegistry.JESSIC_FENCE, LibBlockNames.JESSIC_FENCE));
//            registry.register(getDefaultBlockItem(BlockRegistry.JESSIC_TRAPDOOR, LibBlockNames.JESSIC_TRAPDOOR));
//            registry.register(getDefaultBlockItem(BlockRegistry.JESSIC_DOOR, LibBlockNames.JESSIC_DOOR));
//            registry.register(getDefaultBlockItem(BlockRegistry.JESSIC_PRESSURE_PLATE, LibBlockNames.JESSIC_PRESSURE_PLATE));
//            registry.register(getDefaultBlockItem(BlockRegistry.AZULOREAL_PLANKS, LibBlockNames.AZULOREAL_PLANKS));
//            registry.register(getDefaultBlockItem(BlockRegistry.AZULOREAL_BUTTON, LibBlockNames.AZULOREAL_BUTTON));
//            registry.register(getDefaultBlockItem(BlockRegistry.AZULOREAL_STAIRS, LibBlockNames.AZULOREAL_STAIRS));
//            registry.register(getDefaultBlockItem(BlockRegistry.AZULOREAL_SLAB, LibBlockNames.AZULOREAL_SLAB));
//            registry.register(getDefaultBlockItem(BlockRegistry.AZULOREAL_FENCE_GATE, LibBlockNames.AZULOREAL_FENCE_GATE));
//            registry.register(getDefaultBlockItem(BlockRegistry.AZULOREAL_FENCE, LibBlockNames.AZULOREAL_FENCE));
//            registry.register(getDefaultBlockItem(BlockRegistry.AZULOREAL_TRAPDOOR, LibBlockNames.AZULOREAL_TRAPDOOR));
//            registry.register(getDefaultBlockItem(BlockRegistry.AZULOREAL_DOOR, LibBlockNames.AZULOREAL_DOOR));
//            registry.register(getDefaultBlockItem(BlockRegistry.AZULOREAL_PRESSURE_PLATE, LibBlockNames.AZULOREAL_PRESSURE_PLATE));