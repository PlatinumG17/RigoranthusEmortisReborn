package com.platinumg17.rigoranthusemortisreborn.api.apimagic.dominion;

import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.IchorTile;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.eventbus.api.Event;

import java.util.*;

public class AbsorptionEventQueue {
        public static Map<String, Set<BlockPos>> posMap = new HashMap<>();

        public static void addPosition(World world, BlockPos pos) {
            String key = world.dimension().getRegistryName().toString();
            if (!posMap.containsKey(key))
                posMap.put(key, new HashSet<>());
            posMap.get(key).add(pos);
        }

        public static void addIchorEvent(World world, Class<? extends IchorTile> tileType, int amount, Event event, BlockPos ichorPos) {
            List<BlockPos> stalePos = new ArrayList<>();
            Set<BlockPos> worldList = posMap.getOrDefault(world.dimension().getRegistryName().toString(), new HashSet<>());
            for (BlockPos p : worldList) {
                if(!world.isLoaded(p))
                    continue;
                TileEntity entity = world.getBlockEntity(p);
                if (world.getBlockEntity(p) == null || !(entity instanceof IchorTile)) {
                    stalePos.add(p);
                    continue;
                }
                if (entity.getClass().equals(tileType) && ((IchorTile) entity).eventInRange(ichorPos, event) && ((IchorTile) entity).canAcceptIchor()) {
                    ((IchorTile) entity).getIchorEvent(ichorPos, amount);
                    break;
                }
            }
            for (BlockPos p : stalePos) {
                worldList.remove(p);
            }
        }
}