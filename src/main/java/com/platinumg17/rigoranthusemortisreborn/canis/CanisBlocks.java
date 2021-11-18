package com.platinumg17.rigoranthusemortisreborn.canis;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.canis.common.block.CanisBedBlock;
import com.platinumg17.rigoranthusemortisreborn.canis.common.block.FoodBowlBlock;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.function.Function;
import java.util.function.Supplier;

public class CanisBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, EmortisConstants.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = CanisItems.ITEMS;

    public static final RegistryObject<CanisBedBlock> CANIS_BED = registerWithItem("canis_bed", CanisBedBlock::new, (prop) -> prop.tab(RigoranthusEmortisReborn.RIGORANTHUS_EMORTIS_GROUP));
//    public static final RegistryObject<CanisBathBlock> CANIS_BATH = registerWithItem("canis_bath", CanisBathBlock::new);
    public static final RegistryObject<FoodBowlBlock> FOOD_BOWL = registerWithItem("food_bowl", FoodBowlBlock::new);

    private static Item.Properties createInitialProp() {
        return new Item.Properties().tab(RigoranthusEmortisReborn.RIGORANTHUS_EMORTIS_GROUP);
    }

    private static BlockItem makeItemBlock(Block block) {
        return makeItemBlock(block, null);
    }

    private static BlockItem makeItemBlock(Block block, @Nullable Function<Item.Properties, Item.Properties> extraPropFunc) {
        Item.Properties prop = createInitialProp();
        return new BlockItem(block, extraPropFunc != null ? extraPropFunc.apply(prop) : prop);
    }

    private static <T extends Block> RegistryObject<T> registerWithItem(final String name, final Supplier<T> blockSupplier, @Nullable Function<Item.Properties, Item.Properties> extraPropFunc) {
        return register(name, blockSupplier, (b) -> makeItemBlock(b.get(), extraPropFunc));
    }

    private static <T extends Block> RegistryObject<T> registerWithItem(final String name, final Supplier<T> blockSupplier) {
        return register(name, blockSupplier, (b) -> makeItemBlock(b.get()));
    }

    private static <T extends Block> RegistryObject<T> register(final String name, final Supplier<T> blockSupplier, final Function<RegistryObject<T>, Item> itemFunction) {
        RegistryObject<T> blockObj = register(name, blockSupplier);
        ITEMS.register(name, () -> itemFunction.apply(blockObj));
        return blockObj;
    }

    private static <T extends Block> RegistryObject<T> register(final String name, final Supplier<T> blockSupplier) {
        return BLOCKS.register(name, blockSupplier);
    }

//    public static void registerBlockColors(final ColorHandlerEvent.Block event) {
//        BlockColors blockColors = event.getBlockColors();
//
////        REUtil.allMatch(CanisBlocks.CANIS_BATH, (block) -> {
////            blockColors.register((state, world, pos, tintIndex) -> {
////                return world != null && pos != null ? BiomeColors.getAverageWaterColor(world, pos) : -1;
////            }, block);
////        }, CanisBlocks::logError);
//    }

    public static void logError() {
        // Only try to register if blocks were successfully registered
        // Trying to avoid as reports may say
        // Rigoranthus Emortis crashed but is not the CAUSE of the crash

        RigoranthusEmortisReborn.LOGGER.info("Items/Blocks were not registered for some reason... probably beacuse you didn't watch the bee movie in 3D");
    }
}