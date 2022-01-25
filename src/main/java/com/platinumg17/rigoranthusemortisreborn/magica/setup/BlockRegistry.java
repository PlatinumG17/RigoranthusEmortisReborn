package com.platinumg17.rigoranthusemortisreborn.magica.setup;

import com.minecraftabnormals.abnormals_core.common.blocks.AbnormalsFlowerBlock;
import com.minecraftabnormals.abnormals_core.common.blocks.wood.AbnormalsSaplingBlock;
import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.blocks.custom.BrainBlock;
import com.platinumg17.rigoranthusemortisreborn.blocks.custom.OpulentMagmaBlock;
import com.platinumg17.rigoranthusemortisreborn.blocks.custom.skull.HangingSkullBlock;
import com.platinumg17.rigoranthusemortisreborn.blocks.custom.skull.RESkullBlock;
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
    public static AbstractBlock.Properties LOG_PROP = AbstractBlock.Properties.of(Material.WOOD).harvestLevel(0).harvestTool(ToolType.AXE).strength(2.0F).sound(SoundType.WOOD);
    public static AbstractBlock.Properties SAP_PROP = AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS);
    public static AbstractBlock.Properties FLOWER_POT_PROP = AbstractBlock.Properties.of(Material.DECORATION).strength(0.0F).noOcclusion();
    public static AbstractBlock.Properties PLANKS = AbstractBlock.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD);

    public static AbstractBlock.Properties LAMP_PROP = AbstractBlock.Properties.of(Material.DECORATION).strength(0.0F).noOcclusion();
    public static AbstractBlock.Properties NETHERITE_PROP = AbstractBlock.Properties.of(Material.METAL, MaterialColor.METAL).lightLevel((value) -> { return 5; }).strength(25f, 30f).requiresCorrectToolForDrops().sound(SoundType.STONE);

    public static AbstractBlock.Properties ABYSSAL_PROP = AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).harvestTool(ToolType.PICKAXE).harvestLevel(3).strength(60f, 1800f).requiresCorrectToolForDrops().sound(SoundType.STONE);
    public static AbstractBlock.Properties OXY_PROP = AbstractBlock.Properties.of(Material.ICE_SOLID, MaterialColor.COLOR_BLUE).harvestTool(ToolType.PICKAXE).harvestLevel(3).strength(12f, 20f).friction(0.5f).requiresCorrectToolForDrops().sound(SoundType.GLASS);
    public static AbstractBlock.Properties FORTIFIED_PROP = AbstractBlock.Properties.of(Material.METAL, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).strength(8f, 10f).requiresCorrectToolForDrops().sound(SoundType.STONE);
    public static AbstractBlock.Properties SOUL_PROP = AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops().strength(5.0F, 12.0F).sound(SoundType.SOUL_SAND);
    public static AbstractBlock.Properties AMALGAM_PROP = AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).harvestTool(ToolType.PICKAXE).harvestLevel(1).strength(10f, 15f).requiresCorrectToolForDrops().sound(SoundType.STONE);
    public static AbstractBlock.Properties HIMALAYAN_PROP = AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_PINK).harvestTool(ToolType.PICKAXE).harvestLevel(1).strength(7f, 10f).requiresCorrectToolForDrops().sound(SoundType.NETHER_ORE);
    public static AbstractBlock.Properties BRAIN_PROP = AbstractBlock.Properties.of(Material.SPONGE, MaterialColor.COLOR_PINK).harvestLevel(0).strength(0.2f, 0.2f).noOcclusion().sound(SoundType.SLIME_BLOCK);

    public static AbstractBlock.Properties FRAG_COBBLE_PROP =  AbstractBlock.Properties.of(Material.SAND, MaterialColor.STONE).harvestTool(ToolType.SHOVEL).harvestLevel(0).strength(0.8f, 0.8f).sound(SoundType.GRAVEL);
    public static AbstractBlock.Properties FRAG_NETHER_PROP =  AbstractBlock.Properties.of(Material.SAND, MaterialColor.NETHER).harvestTool(ToolType.SHOVEL).harvestLevel(0).strength(0.6f, 0.6f).sound(SoundType.NETHERRACK);
    public static AbstractBlock.Properties ESOTERIC_PROP = AbstractBlock.Properties.of(Material.SAND, MaterialColor.STONE).harvestTool(ToolType.SHOVEL).harvestLevel(0).strength(1.0f, 6.0f).sound(SoundType.GRAVEL);
    public static AbstractBlock.Properties PINK_SALT_PROP =  AbstractBlock.Properties.of(Material.SAND, MaterialColor.COLOR_PINK).harvestTool(ToolType.SHOVEL).harvestLevel(0).strength(0.6f, 0.6f).sound(SoundType.GRAVEL);
    public static AbstractBlock.Properties SANDESITE_PROP =  AbstractBlock.Properties.of(Material.SAND, MaterialColor.CLAY).harvestTool(ToolType.SHOVEL).harvestLevel(0).strength(0.6f, 0.6f).sound(SoundType.GRAVEL);


    //____________________________ W O O D   T Y P E S ____________________________//
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


    //____________________________ F L O W E R S ____________________________//
    @ObjectHolder(LibBlockNames.AZULOREAL_ORCHID) public static AbnormalsFlowerBlock AZULOREAL_ORCHID;
    @ObjectHolder(LibBlockNames.IRIDESCENT_SPROUTS) public static AbnormalsFlowerBlock IRIDESCENT_SPROUTS;
    @ObjectHolder(LibBlockNames.SPECTABILIS_BUSH) public static SpectabilisBushBlock SPECTABILIS_BUSH;
    @ObjectHolder(LibBlockNames.LISIANTHUS) public static TallFlowerBlock LISIANTHUS;

    @ObjectHolder(LibBlockNames.POTTED_AZULOREAL_ORCHID) public static FlowerPotBlock POTTED_AZULOREAL_ORCHID;
    @ObjectHolder(LibBlockNames.POTTED_IRIDESCENT_SPROUTS) public static FlowerPotBlock POTTED_IRIDESCENT_SPROUTS;
    @ObjectHolder(LibBlockNames.POTTED_AZULOREAL_SAPLING) public static FlowerPotBlock POTTED_AZULOREAL_SAPLING;
    @ObjectHolder(LibBlockNames.POTTED_JESSIC_SAPLING) public static FlowerPotBlock POTTED_JESSIC_SAPLING;



    //____________________________ B L O C K S ____________________________//
    @ObjectHolder(LibBlockNames.AZULOREAL_LAMP) public static RedstoneLampBlock AZULOREAL_LAMP;
    @ObjectHolder(LibBlockNames.JESSIC_LAMP) public static RedstoneLampBlock JESSIC_LAMP;

    @ObjectHolder(LibBlockNames.APOGEAN_NETHERITE_BLOCK) public static ModBlock APOGEAN_NETHERITE_BLOCK;
    @ObjectHolder(LibBlockNames.AQUEOUS_NETHERITE_BLOCK) public static ModBlock AQUEOUS_NETHERITE_BLOCK;
    @ObjectHolder(LibBlockNames.ATROPHYING_NETHERITE_BLOCK) public static ModBlock ATROPHYING_NETHERITE_BLOCK;
    @ObjectHolder(LibBlockNames.OPULENT_NETHERITE_BLOCK) public static ModBlock OPULENT_NETHERITE_BLOCK;
    @ObjectHolder(LibBlockNames.PERNICIOUS_NETHERITE_BLOCK) public static ModBlock PERNICIOUS_NETHERITE_BLOCK;
    @ObjectHolder(LibBlockNames.PHANTASMAL_NETHERITE_BLOCK) public static ModBlock PHANTASMAL_NETHERITE_BLOCK;
    @ObjectHolder(LibBlockNames.INCORPOREAL_NETHERITE_BLOCK) public static ModBlock INCORPOREAL_NETHERITE_BLOCK;
    @ObjectHolder(LibBlockNames.INFERNAL_NETHERITE_BLOCK) public static ModBlock INFERNAL_NETHERITE_BLOCK;
    @ObjectHolder(LibBlockNames.REMEX_NETHERITE_BLOCK) public static ModBlock REMEX_NETHERITE_BLOCK;

    @ObjectHolder(LibBlockNames.CADAVER_SKULL) public static RESkullBlock CADAVER_SKULL;
    @ObjectHolder(LibBlockNames.DWELLER_BRAIN) public static BrainBlock DWELLER_BRAIN;
    @ObjectHolder(LibBlockNames.OPULENT_MAGMA) public static OpulentMagmaBlock OPULENT_MAGMA;
    @ObjectHolder(LibBlockNames.FRAGMENTED_COBBLESTONE) public static GravelBlock FRAGMENTED_COBBLESTONE;
    @ObjectHolder(LibBlockNames.FRAGMENTED_NETHERRACK) public static GravelBlock FRAGMENTED_NETHERRACK;
    @ObjectHolder(LibBlockNames.BLOCK_OF_ESOTERICUM) public static GravelBlock BLOCK_OF_ESOTERICUM;
    @ObjectHolder(LibBlockNames.PINK_SALT) public static GravelBlock PINK_SALT;
    @ObjectHolder(LibBlockNames.SANDESITE) public static GravelBlock SANDESITE;
    @ObjectHolder(LibBlockNames.FORTIFIED_SANDESITE) public static ModBlock FORTIFIED_SANDESITE;
    @ObjectHolder(LibBlockNames.ABYSSALITE) public static ModBlock ABYSSALITE;
    @ObjectHolder(LibBlockNames.OXYLITE) public static ModBlock OXYLITE;

    @ObjectHolder(LibBlockNames.SOUL_COAL_BLOCK) public static ModBlock SOUL_COAL_BLOCK;
    @ObjectHolder(LibBlockNames.GOLD_AMALGAM) public static ModBlock GOLD_AMALGAM;
    @ObjectHolder(LibBlockNames.HIMALAYAN_SALT) public static ModBlock HIMALAYAN_SALT;


    //____________________________ T I L E   E N T I T Y   B L O C K S ____________________________//
    @ObjectHolder(LibBlockNames.HANGING_CADAVER_SKULL) public static HangingSkullBlock hangingCadaverSkull;
    @ObjectHolder(LibBlockNames.HANGING_CADAVER_SKULL) public static TileEntityType<HangingSkullTile> hangingCadaverSkullTile;

    @ObjectHolder(LibBlockNames.DECAYING_BLOCK) public static DecayingBlock DECAYING_BLOCK;
    @ObjectHolder(LibBlockNames.DECAYING_BLOCK) public static TileEntityType<DecayingBlockTile> DECAYING_TILE;
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
            registry.register(new IchorExtractorBlock());
            registry.register(new LightBlock());
            registry.register(new TableBlock());
            registry.register(new DecayingBlock());
            registry.register(new IntangibleAirBlock());
            registry.register(new PortalBlock());
//            registry.register(new RELilyPad());


            registry.register(new RedstoneLampBlock(LAMP_PROP).setRegistryName(LibBlockNames.AZULOREAL_LAMP));
            registry.register(new RedstoneLampBlock(LAMP_PROP).setRegistryName(LibBlockNames.JESSIC_LAMP));

            registry.register(new GravelBlock(FRAG_COBBLE_PROP).setRegistryName(LibBlockNames.FRAGMENTED_COBBLESTONE));
            registry.register(new GravelBlock(FRAG_NETHER_PROP).setRegistryName(LibBlockNames.FRAGMENTED_NETHERRACK));
            registry.register(new GravelBlock(ESOTERIC_PROP).setRegistryName(LibBlockNames.BLOCK_OF_ESOTERICUM));
            registry.register(new GravelBlock(PINK_SALT_PROP).setRegistryName(LibBlockNames.PINK_SALT));
            registry.register(new GravelBlock(SANDESITE_PROP).setRegistryName(LibBlockNames.SANDESITE));

            registry.register(new BrainBlock(BRAIN_PROP, LibBlockNames.DWELLER_BRAIN));
            registry.register(new RESkullBlock(LibBlockNames.CADAVER_SKULL));
            registry.register(new OpulentMagmaBlock(LibBlockNames.OPULENT_MAGMA));

            registry.register(new ModBlock(FORTIFIED_PROP, LibBlockNames.FORTIFIED_SANDESITE));
            registry.register(new ModBlock(ABYSSAL_PROP, LibBlockNames.ABYSSALITE));
            registry.register(new ModBlock(OXY_PROP, LibBlockNames.OXYLITE));
            registry.register(new ModBlock(SOUL_PROP, LibBlockNames.SOUL_COAL_BLOCK));
            registry.register(new ModBlock(AMALGAM_PROP, LibBlockNames.GOLD_AMALGAM));
            registry.register(new ModBlock(HIMALAYAN_PROP, LibBlockNames.HIMALAYAN_SALT));

            registry.register(new ModBlock(NETHERITE_PROP, LibBlockNames.APOGEAN_NETHERITE_BLOCK));
            registry.register(new ModBlock(NETHERITE_PROP, LibBlockNames.AQUEOUS_NETHERITE_BLOCK));
            registry.register(new ModBlock(NETHERITE_PROP, LibBlockNames.ATROPHYING_NETHERITE_BLOCK));
            registry.register(new ModBlock(NETHERITE_PROP, LibBlockNames.OPULENT_NETHERITE_BLOCK));
            registry.register(new ModBlock(NETHERITE_PROP, LibBlockNames.PERNICIOUS_NETHERITE_BLOCK));
            registry.register(new ModBlock(NETHERITE_PROP, LibBlockNames.PHANTASMAL_NETHERITE_BLOCK));
            registry.register(new ModBlock(NETHERITE_PROP, LibBlockNames.INCORPOREAL_NETHERITE_BLOCK));
            registry.register(new ModBlock(NETHERITE_PROP, LibBlockNames.INFERNAL_NETHERITE_BLOCK));
            registry.register(new ModBlock(NETHERITE_PROP, LibBlockNames.REMEX_NETHERITE_BLOCK));
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

            event.getRegistry().register(TileEntityType.Builder.of(DecayingBlockTile::new, BlockRegistry.DECAYING_BLOCK).build(null).setRegistryName(LibBlockNames.DECAYING_BLOCK));
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

//            registry.register(new AnimBlockItem(BlockRegistry.hangingCadaverSkull, MagicItemsRegistry.defaultItemProperties().setISTER(() -> HangingSkullRenderer::getISTER)).setRegistryName(LibBlockNames.HANGING_CADAVER_SKULL));

            registry.register(new BlockItem(BlockRegistry.DOMINION_JAR, MagicItemsRegistry.defaultItemProperties()).setRegistryName(LibBlockNames.DOMINION_JAR));
            registry.register(new BlockItem(BlockRegistry.CREATIVE_DOMINION_JAR, MagicItemsRegistry.defaultItemProperties()).setRegistryName(LibBlockNames.CREATIVE_DOMINION_JAR));
            registry.register(new BlockItem(BlockRegistry.ICHOR_JAR, MagicItemsRegistry.defaultItemProperties()).setRegistryName(LibBlockNames.ICHOR_JAR));
            registry.register(new BlockItem(BlockRegistry.CREATIVE_ICHOR_JAR, MagicItemsRegistry.defaultItemProperties()).setRegistryName(LibBlockNames.CREATIVE_ICHOR_JAR));
            registry.register(new AnimBlockItem(BlockRegistry.RITUAL_BLOCK, MagicItemsRegistry.defaultItemProperties().setISTER(() -> RitualVesselRenderer::getISTER)).setRegistryName(LibBlockNames.RITUAL_VESSEL));
            registry.register(new AnimBlockItem(BlockRegistry.TABLE_BLOCK, MagicItemsRegistry.defaultItemProperties().setISTER(() -> TableRenderer::getISTER)).setRegistryName(LibBlockNames.TABLE_BLOCK));
            registry.register(new BlockItem(BlockRegistry.DECAYING_BLOCK, MagicItemsRegistry.defaultItemProperties()).setRegistryName(LibBlockNames.DECAYING_BLOCK));
            registry.register(new BlockItem(BlockRegistry.LIGHT_BLOCK, new Item.Properties()).setRegistryName(LibBlockNames.LIGHT_BLOCK));
            registry.register(new BlockItem(BlockRegistry.PORTAL_BLOCK, new Item.Properties()).setRegistryName(LibBlockNames.PORTAL));
//            registry.register(new FluidBlockItem(BlockRegistry.RE_LILLY_PAD, MagicItemsRegistry.defaultItemProperties().fireResistant()).setRegistryName(LibBlockNames.RE_LILLY_PAD));

            Item hangingCadaverSkull = new AnimBlockItem(BlockRegistry.hangingCadaverSkull, new Item.Properties().tab(RigoranthusEmortisReborn.RIGORANTHUS_EMORTIS_GROUP).fireResistant().setISTER(() -> HangingSkullRenderer::getISTER)) {
                @Override public void appendHoverText(ItemStack stack, @Nullable World worldIn, List< ITextComponent > tooltip, ITooltipFlag flagIn) {
                    if (Screen.hasShiftDown()) {
                        tooltip.add(new TranslationTextComponent("tooltip.block." + EmortisConstants.MOD_ID + ".hanging_cadaver_skull"));
                    } else {
                        tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".hold_shift").setStyle(Style.EMPTY)); }
                    super.appendHoverText(stack, worldIn, tooltip, flagIn);
                }
            }.setRegistryName(LibBlockNames.HANGING_CADAVER_SKULL);

            Item relayDeposit = new AnimBlockItem(BlockRegistry.RELAY_DEPOSIT, new Item.Properties().tab(RigoranthusEmortisReborn.RIGORANTHUS_EMORTIS_GROUP).fireResistant().setISTER(() -> RelayDepositRenderer::getISTER)) {
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

            Item relaySplitter = new AnimBlockItem(BlockRegistry.RELAY_SPLITTER, new Item.Properties().tab(RigoranthusEmortisReborn.RIGORANTHUS_EMORTIS_GROUP).fireResistant().setISTER(() -> RelaySplitterRenderer::getISTER)) {
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

            Item emorticRelay = new AnimBlockItem(BlockRegistry.EMORTIC_RELAY, new Item.Properties().tab(RigoranthusEmortisReborn.RIGORANTHUS_EMORTIS_GROUP).fireResistant().setISTER(() -> EmorticRelayRenderer::getISTER)) {
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

            Item emorticCortex = new AnimBlockItem(BlockRegistry.EMORTIC_CORTEX_BLOCK, new Item.Properties().tab(RigoranthusEmortisReborn.RIGORANTHUS_EMORTIS_GROUP).fireResistant().setISTER(() -> EmorticCortexRenderer::getISTER)) {
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

            registry.register(hangingCadaverSkull);
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


            registry.register(getDefaultBlockItem(BlockRegistry.AZULOREAL_LAMP, LibBlockNames.AZULOREAL_LAMP));
            registry.register(getDefaultBlockItem(BlockRegistry.JESSIC_LAMP, LibBlockNames.JESSIC_LAMP));

            registry.register(registerTooltipBlockItem(LibBlockNames.FRAGMENTED_COBBLESTONE, BlockRegistry.FRAGMENTED_COBBLESTONE, LibBlockNames.FRAGMENTED_COBBLESTONE, "fragmented_cobblestone2"));
            registry.register(registerTooltipBlockItem(LibBlockNames.FRAGMENTED_NETHERRACK, BlockRegistry.FRAGMENTED_NETHERRACK, LibBlockNames.FRAGMENTED_NETHERRACK, "fragmented_netherrack2"));

            registry.register(registerSingleTooltipBlockItem(LibBlockNames.ABYSSALITE, BlockRegistry.ABYSSALITE, LibBlockNames.ABYSSALITE));
            registry.register(registerSingleTooltipBlockItem(LibBlockNames.OXYLITE, BlockRegistry.OXYLITE, LibBlockNames.OXYLITE));
            registry.register(registerSingleTooltipBlockItem(LibBlockNames.SOUL_COAL_BLOCK, BlockRegistry.SOUL_COAL_BLOCK, LibBlockNames.SOUL_COAL_BLOCK));
            registry.register(registerSingleTooltipBlockItem(LibBlockNames.OPULENT_MAGMA, BlockRegistry.OPULENT_MAGMA, LibBlockNames.OPULENT_MAGMA));
            registry.register(registerSingleTooltipBlockItem(LibBlockNames.DWELLER_BRAIN, BlockRegistry.DWELLER_BRAIN, LibBlockNames.DWELLER_BRAIN));
            registry.register(registerSingleTooltipBlockItem(LibBlockNames.HIMALAYAN_SALT, BlockRegistry.HIMALAYAN_SALT, LibBlockNames.HIMALAYAN_SALT));
            registry.register(registerSingleTooltipBlockItem(LibBlockNames.GOLD_AMALGAM, BlockRegistry.GOLD_AMALGAM, LibBlockNames.GOLD_AMALGAM));
            registry.register(registerSingleTooltipBlockItem(LibBlockNames.BLOCK_OF_ESOTERICUM, BlockRegistry.BLOCK_OF_ESOTERICUM, LibBlockNames.BLOCK_OF_ESOTERICUM));

            registry.register(registerSingleTooltipBlockItem(LibBlockNames.SANDESITE, BlockRegistry.SANDESITE, LibBlockNames.SANDESITE));
            registry.register(registerSingleTooltipBlockItem(LibBlockNames.FORTIFIED_SANDESITE, BlockRegistry.FORTIFIED_SANDESITE, LibBlockNames.FORTIFIED_SANDESITE));
            registry.register(registerSingleTooltipBlockItem(LibBlockNames.PINK_SALT, BlockRegistry.PINK_SALT, LibBlockNames.PINK_SALT));
            registry.register(registerSingleTooltipBlockItem(LibBlockNames.CADAVER_SKULL, BlockRegistry.CADAVER_SKULL, LibBlockNames.CADAVER_SKULL));

            registry.register(getDefaultBlockItem(BlockRegistry.APOGEAN_NETHERITE_BLOCK, LibBlockNames.APOGEAN_NETHERITE_BLOCK));
            registry.register(getDefaultBlockItem(BlockRegistry.AQUEOUS_NETHERITE_BLOCK, LibBlockNames.AQUEOUS_NETHERITE_BLOCK));
            registry.register(getDefaultBlockItem(BlockRegistry.ATROPHYING_NETHERITE_BLOCK, LibBlockNames.ATROPHYING_NETHERITE_BLOCK));
            registry.register(getDefaultBlockItem(BlockRegistry.OPULENT_NETHERITE_BLOCK, LibBlockNames.OPULENT_NETHERITE_BLOCK));
            registry.register(getDefaultBlockItem(BlockRegistry.PERNICIOUS_NETHERITE_BLOCK, LibBlockNames.PERNICIOUS_NETHERITE_BLOCK));
            registry.register(getDefaultBlockItem(BlockRegistry.PHANTASMAL_NETHERITE_BLOCK, LibBlockNames.PHANTASMAL_NETHERITE_BLOCK));
            registry.register(getDefaultBlockItem(BlockRegistry.INCORPOREAL_NETHERITE_BLOCK, LibBlockNames.INCORPOREAL_NETHERITE_BLOCK));
            registry.register(getDefaultBlockItem(BlockRegistry.INFERNAL_NETHERITE_BLOCK, LibBlockNames.INFERNAL_NETHERITE_BLOCK));
            registry.register(getDefaultBlockItem(BlockRegistry.REMEX_NETHERITE_BLOCK, LibBlockNames.REMEX_NETHERITE_BLOCK));
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
        private static Item registerSingleTooltipBlockItem(String name, Block block, String tooltipKey) {
            return new BlockItem(block, new Item.Properties().tab(RigoranthusEmortisReborn.RIGORANTHUS_EMORTIS_GROUP)) {
                @Override
                public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
                    if (Screen.hasShiftDown()) {
                        tooltip.add(new TranslationTextComponent("tooltip.block.rigoranthusemortisreborn." + tooltipKey));
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