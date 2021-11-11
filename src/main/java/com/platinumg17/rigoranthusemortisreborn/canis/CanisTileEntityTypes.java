package com.platinumg17.rigoranthusemortisreborn.canis;

import com.platinumg17.rigoranthusemortisreborn.canis.common.block.tileentity.CanisBedTileEntity;
import com.platinumg17.rigoranthusemortisreborn.canis.common.block.tileentity.FoodBowlTileEntity;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class CanisTileEntityTypes {

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, EmortisConstants.MOD_ID);

    public static final RegistryObject<TileEntityType<CanisBedTileEntity>> CANIS_BED = register("canis_bed", CanisBedTileEntity::new, CanisBlocks.CANIS_BED);
    public static final RegistryObject<TileEntityType<FoodBowlTileEntity>> FOOD_BOWL = register("food_bowl", FoodBowlTileEntity::new, CanisBlocks.FOOD_BOWL);

    private static <T extends TileEntity> RegistryObject<TileEntityType<T>> register(final String name, final Supplier<T> sup, Supplier<? extends Block> validBlock) {
        return register(name, () -> TileEntityType.Builder.of(sup, validBlock.get()).build(null));
    }

    private static <T extends TileEntityType<?>> RegistryObject<T> register(final String name, final Supplier<T> sup) {
        return TILE_ENTITIES.register(name, sup);
    }
}
