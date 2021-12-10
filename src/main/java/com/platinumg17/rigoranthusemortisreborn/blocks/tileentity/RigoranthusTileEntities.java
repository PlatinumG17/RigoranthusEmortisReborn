package com.platinumg17.rigoranthusemortisreborn.blocks.tileentity;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.core.init.Registration;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RigoranthusTileEntities {
	public static DeferredRegister<TileEntityType<?>> TILE_ENTITIES =
			DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, EmortisConstants.MOD_ID);

	public static RegistryObject<TileEntityType<MasterfulSmelteryTile>> MASTERFUL_SMELTERY_TILE =
			TILE_ENTITIES.register("masterful_smeltery_tile", () -> TileEntityType.Builder.of(
					MasterfulSmelteryTile::new, Registration.MASTERFUL_SMELTERY.get()).build(null));

	public static void register(IEventBus modEventBus) {
		TILE_ENTITIES.register(modEventBus);
	}
}