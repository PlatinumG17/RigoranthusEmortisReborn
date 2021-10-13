package com.platinumg17.rigoranthusemortisreborn.core.init;

import java.util.List;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.blocks.*;
import com.platinumg17.rigoranthusemortisreborn.blocks.trees.AzulorealTree;
import com.platinumg17.rigoranthusemortisreborn.blocks.trees.JessicTree;
import com.platinumg17.rigoranthusemortisreborn.config.Config;
import com.platinumg17.rigoranthusemortisreborn.core.registry.RigoranthusItemGroup;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;

public class BuildingBlockInit {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS,	RigoranthusEmortisReborn.MOD_ID);

    public static final RegistryObject<Block> JESSIC_LOG = registerBlock("jessic_log",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F).harvestTool(ToolType.AXE)), "tooltip.block.rigoranthusemortisreborn.jessic_log");

    public static final RegistryObject<Block> JESSIC_WOOD = registerBlock("jessic_wood",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F).harvestTool(ToolType.AXE)), "tooltip.block.rigoranthusemortisreborn.jessic_wood");

    public static final RegistryObject<Block> STRIPPED_JESSIC_LOG = registerBlock("stripped_jessic_log",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F).harvestTool(ToolType.AXE)), "tooltip.block.rigoranthusemortisreborn.stripped_jessic_log");

    public static final RegistryObject<Block> STRIPPED_JESSIC_WOOD = registerBlock("stripped_jessic_wood",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F).harvestTool(ToolType.AXE)), "tooltip.block.rigoranthusemortisreborn.stripped_jessic_wood");

    public static final RegistryObject<Block> JESSIC_PLANKS = registerBlock("jessic_planks",
            () -> new Block(AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F).harvestTool(ToolType.AXE)), "tooltip.block.rigoranthusemortisreborn.jessic_planks");

    public static final RegistryObject<Block> JESSIC_STAIRS = registerBlock("jessic_stairs",
            () -> new StairsBlock(() -> JESSIC_PLANKS.get().defaultBlockState(),
                    AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F).harvestTool(ToolType.AXE)), "tooltip.block.rigoranthusemortisreborn.jessic_stairs");

    public static final RegistryObject<Block> JESSIC_FENCE = registerBlock("jessic_fence",
            () -> new FenceBlock(AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F).harvestTool(ToolType.AXE)), "tooltip.block.rigoranthusemortisreborn.jessic_fence");

    public static final RegistryObject<Block> JESSIC_FENCE_GATE = registerBlock("jessic_fence_gate",
            () -> new FenceGateBlock(AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F).harvestTool(ToolType.AXE)), "tooltip.block.rigoranthusemortisreborn.jessic_fence_gate");

    public static final RegistryObject<Block> JESSIC_SLAB = registerBlock("jessic_slab",
            () -> new SlabBlock(AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F).harvestTool(ToolType.AXE)), "tooltip.block.rigoranthusemortisreborn.jessic_slab");

    public static final RegistryObject<Block> JESSIC_BUTTON = registerBlock("jessic_button",
            () -> new WoodButtonBlock(AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F).harvestTool(ToolType.AXE).noCollission()), "tooltip.block.rigoranthusemortisreborn.jessic_button");

    public static final RegistryObject<Block> JESSIC_PRESSURE_PLATE = registerBlock("jessic_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
                    AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F).harvestTool(ToolType.AXE)), "tooltip.block.rigoranthusemortisreborn.jessic_pressure_plate");

    public static final RegistryObject<Block> JESSIC_LAMP = registerBlock("jessic_lamp",
            () -> new RedstoneLampBlock(AbstractBlock.Properties.of(Material.BUILDABLE_GLASS).lightLevel(litBlockEmission(15)).strength(0.3F).sound(SoundType.SHROOMLIGHT)), "tooltip.block.rigoranthusemortisreborn.jessic_lamp");

    public static final RegistryObject<Block> JESSIC_DOOR = registerBlock("jessic_door",
            () -> new DoorBlock(AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F).harvestTool(ToolType.AXE).noOcclusion()), "tooltip.block.rigoranthusemortisreborn.jessic_door");

    public static final RegistryObject<Block> JESSIC_TRAPDOOR = registerBlock("jessic_trapdoor",
            () -> new TrapDoorBlock(AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F).harvestTool(ToolType.AXE).noOcclusion()), "tooltip.block.rigoranthusemortisreborn.jessic_trapdoor");

    public static final RegistryObject<Block> JESSIC_LADDER = registerBlock("jessic_ladder",
            () -> new LadderBlock(AbstractBlock.Properties.of(Material.DECORATION).sound(SoundType.LADDER).strength(0.4F).harvestTool(ToolType.AXE).noOcclusion()), "tooltip.block.rigoranthusemortisreborn.jessic_ladder");

    public static final RegistryObject<Block> JESSIC_LEAVES = registerBlock("jessic_leaves",
            () -> new LeavesBlock(AbstractBlock.Properties.of(Material.LEAVES).sound(SoundType.CROP).strength(0.2F).randomTicks().noOcclusion().lightLevel((p_235455_0_) -> {return 15;})), "tooltip.block.rigoranthusemortisreborn.jessic_leaves");

    public static final RegistryObject<Block> JESSIC_SAPLING = registerBlock("jessic_sapling",
            () -> new SaplingBlock(new JessicTree(), AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "tooltip.block.rigoranthusemortisreborn.jessic_sapling");

    /*	public static final RegistryObject<Block> JESSIC_CRAFTING_TABLE = registerBlock("jessic_crafting_table",
            () -> new CraftingTableBlock(AbstractBlock.Properties.of(Material.DECORATION).sound(SoundType.WOOD).strength(2.0F, 3.0F).harvestTool(ToolType.AXE)));
    public static final RegistryObject<Block> JESSIC_SIGN = BLOCKS.register("jessic_sign",
            () -> new RigoranthusStandingSignBlock(AbstractBlock.Properties.of(Material.WOOD), RigoranthusWoodType.JESSIC));
    public static final RegistryObject<Block> JESSIC_WALL_SIGN = BLOCKS.register("jessic_wall_sign",
            () -> new RigoranthusWallSignBlock(AbstractBlock.Properties.of(Material.WOOD), RigoranthusWoodType.JESSIC));
    public static final RegistryObject<Block> AZULOREAL_SIGN = BLOCKS.register("azuloreal_sign",
            () -> new AzulorealStandingSignBlock(AbstractBlock.Properties.of(Material.WOOD), RigoranthusWoodType.AZULOREAL));
    public static final RegistryObject<Block> AZULOREAL_WALL_SIGN = BLOCKS.register("azuloreal_wall_sign",
            () -> new AzulorealWallSignBlock(AbstractBlock.Properties.of(Material.WOOD), RigoranthusWoodType.AZULOREAL)); //.noCollission().noOcclusion().strength(1.0F).sound(SoundType.WOOD)*/
    public static final RegistryObject<Block> AZULOREAL_LOG = registerBlock("azuloreal_log",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F).harvestTool(ToolType.AXE)), "tooltip.block.rigoranthusemortisreborn.azuloreal_log");

    public static final RegistryObject<Block> AZULOREAL_WOOD = registerBlock("azuloreal_wood",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F).harvestTool(ToolType.AXE)), "tooltip.block.rigoranthusemortisreborn.azuloreal_wood");

    public static final RegistryObject<Block> STRIPPED_AZULOREAL_LOG = registerBlock("stripped_azuloreal_log",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F).harvestTool(ToolType.AXE)), "tooltip.block.rigoranthusemortisreborn.stripped_azuloreal_log");

    public static final RegistryObject<Block> STRIPPED_AZULOREAL_WOOD = registerBlock("stripped_azuloreal_wood",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F).harvestTool(ToolType.AXE)), "tooltip.block.rigoranthusemortisreborn.stripped_azuloreal_wood");

    public static final RegistryObject<Block> AZULOREAL_PLANKS = registerBlock("azuloreal_planks",
            () -> new Block(AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F).harvestTool(ToolType.AXE)), "tooltip.block.rigoranthusemortisreborn.azuloreal_planks");

    public static final RegistryObject<Block> AZULOREAL_STAIRS = registerBlock("azuloreal_stairs",
            () -> new StairsBlock(() -> JESSIC_PLANKS.get().defaultBlockState(),
                    AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F).harvestTool(ToolType.AXE)), "tooltip.block.rigoranthusemortisreborn.azuloreal_stairs");

    public static final RegistryObject<Block> AZULOREAL_FENCE = registerBlock("azuloreal_fence",
            () -> new FenceBlock(AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F).harvestTool(ToolType.AXE)), "tooltip.block.rigoranthusemortisreborn.azuloreal_fence");

    public static final RegistryObject<Block> AZULOREAL_FENCE_GATE = registerBlock("azuloreal_fence_gate",
            () -> new FenceGateBlock(AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F).harvestTool(ToolType.AXE)), "tooltip.block.rigoranthusemortisreborn.azuloreal_fence_gate");

    public static final RegistryObject<Block> AZULOREAL_SLAB = registerBlock("azuloreal_slab",
            () -> new SlabBlock(AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F).harvestTool(ToolType.AXE)), "tooltip.block.rigoranthusemortisreborn.azuloreal_slab");

    public static final RegistryObject<Block> AZULOREAL_BUTTON = registerBlock("azuloreal_button",
            () -> new WoodButtonBlock(AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F).harvestTool(ToolType.AXE).noCollission()), "tooltip.block.rigoranthusemortisreborn.azuloreal_button");

    public static final RegistryObject<Block> AZULOREAL_PRESSURE_PLATE = registerBlock("azuloreal_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
                    AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F).harvestTool(ToolType.AXE)), "tooltip.block.rigoranthusemortisreborn.azuloreal_pressure_plate");

    public static final RegistryObject<Block> AZULOREAL_LAMP = registerBlock("azuloreal_lamp",
            () -> new RedstoneLampBlock(AbstractBlock.Properties.of(Material.BUILDABLE_GLASS).lightLevel(litBlockEmission(15)).strength(0.3F).sound(SoundType.SHROOMLIGHT)), "tooltip.block.rigoranthusemortisreborn.azuloreal_lamp");

    public static final RegistryObject<Block> AZULOREAL_DOOR = registerBlock("azuloreal_door",
            () -> new DoorBlock(AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F).harvestTool(ToolType.AXE).noOcclusion()), "tooltip.block.rigoranthusemortisreborn.azuloreal_door");

    public static final RegistryObject<Block> AZULOREAL_TRAPDOOR = registerBlock("azuloreal_trapdoor",
            () -> new TrapDoorBlock(AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F).harvestTool(ToolType.AXE).noOcclusion()), "tooltip.block.rigoranthusemortisreborn.azuloreal_trapdoor");

    public static final RegistryObject<Block> AZULOREAL_LADDER = registerBlock("azuloreal_ladder",
            () -> new LadderBlock(AbstractBlock.Properties.of(Material.DECORATION).sound(SoundType.LADDER).strength(0.4F).harvestTool(ToolType.AXE).noOcclusion()), "tooltip.block.rigoranthusemortisreborn.azuloreal_ladder");

    public static final RegistryObject<Block> AZULOREAL_LEAVES = registerBlock("azuloreal_leaves",
            () -> new LeavesBlock(AbstractBlock.Properties.of(Material.LEAVES).sound(SoundType.CROP).strength(0.2F).randomTicks().noOcclusion().lightLevel((p_235455_0_) -> {
                return 15;})), "tooltip.block.rigoranthusemortisreborn.azuloreal_leaves");

    public static final RegistryObject<Block> AZULOREAL_SAPLING = registerBlock("azuloreal_sapling",
            () -> new SaplingBlock(new AzulorealTree(), AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "tooltip.block.rigoranthusemortisreborn.azuloreal_sapling");
//	public static final RegistryObject<Block> POTTED_AZULOREAL_SAPLING = registerBlock("potted_azuloreal_sapling",
//			() -> new FlowerPotBlock(AZULOREAL_SAPLING, AbstractBlock.Properties.of(Material.DECORATION).instabreak().noOcclusion()), "tooltip.block.rigoranthusemortisreborn.potted_azuloreal_sapling");

    //public static final RegistryObject<SkullBlock> SUNDERED_CADAVER_HEAD = registerBlock("sundered_cadaver_head", () -> new SkullBlock(SkullBlock.Types.ZOMBIE, AbstractBlock.Properties.of(Material.DECORATION).strength(1.0F)));
    //public static final RegistryObject<WallSkullBlock> SUNDERED_CADAVER_WALL_HEAD = registerBlock("sundered_cadaver_wall_head", () -> new WallSkullBlock(SkullBlock.Types.ZOMBIE, AbstractBlock.Properties.of(Material.DECORATION).strength(1.0F).dropsLike(SKELETON_SKULL)));

    //public static final RegistryObject<ChestBlock> JESSIC_CHEST = registerBlock("jessic_chest", () -> new ChestBlock(AbstractBlock.Properties.of(Material.WOOD).strength(2.5F).sound(SoundType.WOOD), () -> {return TileEntityType.CHEST;})); // CHANGE TO JESSOC_CHEST

    //public static final RegistryObject<BarrelBlock> JESSIC_BARREL = registerBlock("jessic_barrel", () -> new BarrelBlock(AbstractBlock.Properties.of(Material.WOOD).strength(2.5F).sound(SoundType.WOOD)));


    private static ToIntFunction<BlockState> litBlockEmission(int var0) {
        return (p_lambda$litBlockEmission$34_1_) -> {
            return (Boolean)p_lambda$litBlockEmission$34_1_.getValue(BlockStateProperties.LIT) ? var0 : 0;
        };
    }

    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block, String tooltipKey) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tooltipKey);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block, String tooltipKey) {
        ItemInit.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)) {
            @Override
            public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
                tooltip.add(new TranslationTextComponent(tooltipKey));
                super.appendHoverText(stack, worldIn, tooltip, flagIn);
            }
        });
    }

    public static void register(IEventBus bus) {
        if (Config.enableNewWoodTypes.get()) {
            BLOCKS.register(bus);
        }
    }
}