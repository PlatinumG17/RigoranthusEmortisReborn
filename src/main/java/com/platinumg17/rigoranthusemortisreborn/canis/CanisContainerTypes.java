package com.platinumg17.rigoranthusemortisreborn.canis;

import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.CanisEntity;
import com.platinumg17.rigoranthusemortisreborn.canis.common.inventory.container.CanisInventoriesContainer;
import com.platinumg17.rigoranthusemortisreborn.canis.common.inventory.container.FoodBowlContainer;
import com.platinumg17.rigoranthusemortisreborn.canis.common.inventory.container.TreatBagContainer;
import com.platinumg17.rigoranthusemortisreborn.canis.common.inventory.container.WaywardTravellerContainer;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.IntArray;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class CanisContainerTypes {

    public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, EmortisConstants.MOD_ID);

    public static final RegistryObject<ContainerType<FoodBowlContainer>> FOOD_BOWL = register("food_bowl", (windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        return new FoodBowlContainer(windowId, inv.player.level, pos, inv, inv.player);
    });
    public static final RegistryObject<ContainerType<WaywardTravellerContainer>> WAYWARD_TRAVELLER = register("wayward_traveller", (windowId, inv, data) -> {
        Entity entity = inv.player.level.getEntity(data.readInt());
        return entity instanceof CanisEntity ? new WaywardTravellerContainer(windowId, inv, (CanisEntity) entity) : null;
    });
    public static final RegistryObject<ContainerType<TreatBagContainer>> TREAT_BAG = register("treat_bag", (windowId, inv, data) -> {
        int slotId = data.readByte();
        return new TreatBagContainer(windowId, inv, slotId, data.readItem());
    });
    public static final RegistryObject<ContainerType<CanisInventoriesContainer>> CANIS_INVENTORIES = register("canis_inventories", (windowId, inv, data) -> {
        int noCani = data.readInt();
        List<CanisEntity> cani = new ArrayList<>(noCani);
        IntArray array = new IntArray(noCani);
        for (int i = 0; i < noCani; i++) {
            Entity entity = inv.player.level.getEntity(data.readInt());
            if (entity instanceof CanisEntity) {
                cani.add((CanisEntity) entity);
                array.set(i, entity.getId());
            }
        }
        return !cani.isEmpty() ? new CanisInventoriesContainer(windowId, inv, array) : null;
    });

    private static <X extends Container, T extends ContainerType<X>> RegistryObject<ContainerType<X>> register(final String name, final IContainerFactory<X> factory) {
        return register(name, () -> IForgeContainerType.create(factory));
    }

    private static <T extends ContainerType<?>> RegistryObject<T> register(final String name, final Supplier<T> sup) {
        return CONTAINERS.register(name, sup);
    }
}
