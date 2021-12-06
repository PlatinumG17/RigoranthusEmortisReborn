package com.platinumg17.rigoranthusemortisreborn.magica;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = EmortisConstants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@OnlyIn(Dist.CLIENT)
public class TextureEvent {

    @SubscribeEvent
    public static void textEvent(TextureStitchEvent.Pre event){
        if(event.getMap().location().toString().equals("minecraft:textures/atlas/chest.png")) {
            ResourceLocation jessicNormal = new ResourceLocation(EmortisConstants.MOD_ID,"entity/chest/jessic/normal");
            ResourceLocation jessicNormalLeft = new ResourceLocation(EmortisConstants.MOD_ID,"entity/chest/jessic/normal_left");
            ResourceLocation jessicNormalRight = new ResourceLocation(EmortisConstants.MOD_ID,"entity/chest/jessic/normal_right");
            ResourceLocation jessicTrapped = new ResourceLocation(EmortisConstants.MOD_ID,"entity/chest/jessic/trapped");
            ResourceLocation jessicTrappedLeft = new ResourceLocation(EmortisConstants.MOD_ID,"entity/chest/jessic/trapped_left");
            ResourceLocation jessicTrappedRight = new ResourceLocation(EmortisConstants.MOD_ID,"entity/chest/jessic/trapped_right");
            ResourceLocation azulorealNormal = new ResourceLocation(EmortisConstants.MOD_ID,"entity/chest/azuloreal/normal");
            ResourceLocation azulorealNormalLeft = new ResourceLocation(EmortisConstants.MOD_ID,"entity/chest/azuloreal/normal_left");
            ResourceLocation azulorealNormalRight = new ResourceLocation(EmortisConstants.MOD_ID,"entity/chest/azuloreal/normal_right");
            ResourceLocation azulorealTrapped = new ResourceLocation(EmortisConstants.MOD_ID,"entity/chest/azuloreal/trapped");
            ResourceLocation azulorealTrappedLeft = new ResourceLocation(EmortisConstants.MOD_ID,"entity/chest/azuloreal/trapped_left");
            ResourceLocation azulorealTrappedRight = new ResourceLocation(EmortisConstants.MOD_ID,"entity/chest/azuloreal/trapped_right");
            event.addSprite(jessicNormal);
            event.addSprite(jessicNormalLeft);
            event.addSprite(jessicNormalRight);
            event.addSprite(jessicTrapped);
            event.addSprite(jessicTrappedLeft);
            event.addSprite(jessicTrappedRight);
            event.addSprite(azulorealNormal);
            event.addSprite(azulorealNormalLeft);
            event.addSprite(azulorealNormalRight);
            event.addSprite(azulorealTrapped);
            event.addSprite(azulorealTrappedLeft);
            event.addSprite(azulorealTrappedRight);
        }
        if(event.getMap().location().toString().equals("minecraft:textures/atlas/signs.png")) {
            ResourceLocation jessicSign = new ResourceLocation(EmortisConstants.MOD_ID,"entity/signs/jessic");
            ResourceLocation azulorealSign = new ResourceLocation(EmortisConstants.MOD_ID,"entity/signs/jessic");
            event.addSprite(jessicSign);
            event.addSprite(azulorealSign);
        }
    }
}
/*
        jei:textures/atlas/gui.png-atlas
        minecraft:textures/atlas/blocks.png-atlas
        minecraft:textures/atlas/signs.png-atlas
        minecraft:textures/atlas/banner_patterns.png-atlas
        minecraft:textures/atlas/shield_patterns.png-atlas
        minecraft:textures/atlas/chest.png-atlas
        minecraft:textures/atlas/beds.png-atlas
        minecraft:textures/atlas/particles.png-atlas
        minecraft:textures/atlas/paintings.png-atlas
        minecraft:textures/atlas/mob_effects.png-atlas
*/