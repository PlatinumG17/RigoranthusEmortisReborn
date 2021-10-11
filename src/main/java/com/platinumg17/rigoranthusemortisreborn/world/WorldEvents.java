package com.platinumg17.rigoranthusemortisreborn.world;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.world.gen.EmortisOreGen;
import com.platinumg17.rigoranthusemortisreborn.world.gen.EmortisTreeGen;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = RigoranthusEmortisReborn.MOD_ID)
public class WorldEvents {
    @SubscribeEvent
    public static void biomeLoadinGevent(final BiomeLoadingEvent event) {
        EmortisOreGen.generateOres(event);
        //EmortisFlowerGen.generateFlowers(event);
        EmortisTreeGen.generateTrees(event);
    }
}