package com.platinumg17.rigoranthusemortisreborn.magica.common.datagen;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

import static com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn.MOD_ID;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModDatagen {

    @SubscribeEvent
    public static void datagen(GatherDataEvent event){
        System.out.println("calling datagen");
        event.getGenerator().addProvider(new LootTables(event.getGenerator()));
        event.getGenerator().addProvider(new DefaultTableProvider(event.getGenerator()));
//        event.getGenerator().addProvider(new ItemModelGenerator(event.getGenerator(), EmortisConstants.MOD_ID, event.getExistingFileHelper()));
//        event.getGenerator().addProvider(new LangDatagen(event.getGenerator(), EmortisConstants.MOD_ID, "en_us"));
        event.getGenerator().addProvider(new SpellDocProvider(event.getGenerator()));
        event.getGenerator().addProvider(new Recipes(event.getGenerator()));
//        event.getGenerator().addProvider(new BlockTagProvider(event.getGenerator()));
//        event.getGenerator().addProvider(new BlockStatesDatagen(event.getGenerator(), EmortisConstants.MOD_ID, event.getExistingFileHelper()));
        event.getGenerator().addProvider(new GlyphRecipeProvider(event.getGenerator()));
        event.getGenerator().addProvider(new PsyglyphicRecipeProvider(event.getGenerator()));
        event.getGenerator().addProvider(new PatchouliProvider(event.getGenerator()));
        event.getGenerator().addProvider(new DungeonLootGenerator(event.getGenerator(), MOD_ID));
    }
}