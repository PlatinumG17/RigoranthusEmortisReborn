package com.platinumg17.rigoranthusemortisreborn.tileentity;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;

import com.platinumg17.rigoranthusemortisreborn.core.init.Registration;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RigoranthusTileEntities {
	public static DeferredRegister<TileEntityType<?>> TILE_ENTITIES =
			DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, RigoranthusEmortisReborn.MOD_ID);
	
	public static RegistryObject<TileEntityType<MasterfulSmelteryTile>> MASTERFUL_SMELTERY_TILE =
			TILE_ENTITIES.register("masterful_smeltery_tile", () -> TileEntityType.Builder.of(
					MasterfulSmelteryTile::new, Registration.MASTERFUL_SMELTERY.get()).build(null));

//	public static final RegistryObject<TileEntityType<RigoranthusSignTileEntity>> SIGN_TILE_ENTITIES =
//			TILE_ENTITIES.register("sign", () -> TileEntityType.Builder.of(RigoranthusSignTileEntity::new,
//					Registration.JESSIC_SIGN.get(),
//					Registration.JESSIC_WALL_SIGN.get(),
//					Registration.AZULOREAL_SIGN.get(),
//					Registration.AZULOREAL_WALL_SIGN.get()
//			).build(null));

	public static void register(IEventBus bus) {
		TILE_ENTITIES.register(bus);
	}
}
