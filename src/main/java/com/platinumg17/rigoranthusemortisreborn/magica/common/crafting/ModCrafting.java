package com.platinumg17.rigoranthusemortisreborn.magica.common.crafting;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import static com.platinumg17.rigoranthusemortisreborn.magica.setup.InjectionUtil.Null;

public class ModCrafting {

    @ObjectHolder(EmortisConstants.MOD_ID)
    @Mod.EventBusSubscriber(modid = EmortisConstants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class Recipes {
        public static IRecipeSerializer<BookUpgradeRecipe> BOOK_UPGRADE_RECIPE = Null();
        public static IRecipeSerializer<DyeRecipe> DYE_RECIPE = Null();

        @SubscribeEvent
        public static void register(final RegistryEvent.Register<IRecipeSerializer<?>> event) {
            BOOK_UPGRADE_RECIPE = new BookUpgradeRecipe.Serializer();
            DYE_RECIPE = new DyeRecipe.Serializer();
            event.getRegistry().registerAll(
                    BOOK_UPGRADE_RECIPE.setRegistryName(new ResourceLocation(EmortisConstants.MOD_ID, "book_upgrade")),
                    DYE_RECIPE.setRegistryName(new ResourceLocation(EmortisConstants.MOD_ID, "dye"))
            );
        }
    }
}