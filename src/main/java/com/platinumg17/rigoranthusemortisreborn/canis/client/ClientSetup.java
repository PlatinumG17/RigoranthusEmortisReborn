package com.platinumg17.rigoranthusemortisreborn.canis.client;

import com.platinumg17.rigoranthusemortisreborn.blocks.tileentity.gui.MasterfulSmelteryScreen;
import com.platinumg17.rigoranthusemortisreborn.canis.CanisContainerTypes;
import com.platinumg17.rigoranthusemortisreborn.canis.CanisSkills;
import com.platinumg17.rigoranthusemortisreborn.canis.CanisTileEntityTypes;
import com.platinumg17.rigoranthusemortisreborn.canis.client.entity.render.CollarRenderManager;
import com.platinumg17.rigoranthusemortisreborn.canis.client.entity.render.layer.SaviorCanisRenderer;
import com.platinumg17.rigoranthusemortisreborn.canis.client.entity.render.layer.WaywardTravellerRenderer;
import com.platinumg17.rigoranthusemortisreborn.canis.client.entity.render.layer.accoutrement.ArmorAccoutrementRenderer;
import com.platinumg17.rigoranthusemortisreborn.canis.client.entity.render.layer.accoutrement.DefaultAccoutrementRenderer;
import com.platinumg17.rigoranthusemortisreborn.canis.client.entity.render.layer.accoutrement.DyeableAccoutrementRenderer;
import com.platinumg17.rigoranthusemortisreborn.canis.client.screen.CanisInventoriesScreen;
import com.platinumg17.rigoranthusemortisreborn.canis.client.screen.FoodBowlScreen;
import com.platinumg17.rigoranthusemortisreborn.canis.client.screen.TreatBagScreen;
import com.platinumg17.rigoranthusemortisreborn.canis.client.screen.WaywardTravellerScreen;
import com.platinumg17.rigoranthusemortisreborn.canis.client.tileentity.renderer.CanisBedRenderer;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.accouterments.CanisAccouterments;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.Resources;
import com.platinumg17.rigoranthusemortisreborn.core.init.Registration;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = EmortisConstants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

    public static void setupScreenManagers(final FMLClientSetupEvent event) {
        ScreenManager.register(CanisContainerTypes.FOOD_BOWL.get(), FoodBowlScreen::new);
        ScreenManager.register(CanisContainerTypes.WAYWARD_TRAVELLER.get(), WaywardTravellerScreen::new);
        ScreenManager.register(CanisContainerTypes.TREAT_BAG.get(), TreatBagScreen::new);
        ScreenManager.register(CanisContainerTypes.CANIS_INVENTORIES.get(), CanisInventoriesScreen::new);
        ScreenManager.register(Registration.MASTERFUL_SMELTERY_CONTAINER.get(), MasterfulSmelteryScreen::new);
    }

    public static void setupTileEntityRenderers(final FMLClientSetupEvent event) {
        ClientRegistry.bindTileEntityRenderer(CanisTileEntityTypes.CANIS_BED.get(), CanisBedRenderer::new);
    }

    public static void setupCollarRenderers(final FMLClientSetupEvent event) {
        CollarRenderManager.registerRenderer(CanisAccouterments.DYEABLE_COLLAR, new DyeableAccoutrementRenderer(Resources.COLLAR_DEFAULT));
        CollarRenderManager.registerRenderer(CanisAccouterments.GOLDEN_COLLAR, new DefaultAccoutrementRenderer(Resources.COLLAR_GOLDEN));
        CollarRenderManager.registerRenderer(CanisAccouterments.SUNGLASSES, new DefaultAccoutrementRenderer(Resources.GLASSES_SUNGLASSES));

        CollarRenderManager.registerRenderer(CanisAccouterments.DIAMOND_HELMET, new ArmorAccoutrementRenderer(Resources.DIAMOND_HELMET));
        CollarRenderManager.registerRenderer(CanisAccouterments.IRON_HELMET, new ArmorAccoutrementRenderer(Resources.IRON_HELMET));
        CollarRenderManager.registerRenderer(CanisAccouterments.GOLDEN_HELMET, new ArmorAccoutrementRenderer(Resources.GOLDEN_HELMET));
        CollarRenderManager.registerRenderer(CanisAccouterments.CHAINMAIL_HELMET, new ArmorAccoutrementRenderer(Resources.CHAINMAIL_HELMET));
        CollarRenderManager.registerRenderer(CanisAccouterments.TURTLE_HELMET, new ArmorAccoutrementRenderer(Resources.TURTLE_HELMET));
        CollarRenderManager.registerRenderer(CanisAccouterments.NETHERITE_HELMET, new ArmorAccoutrementRenderer(Resources.NETHERITE_HELMET));

        CollarRenderManager.registerRenderer(CanisAccouterments.IRON_BODY_PIECE, new ArmorAccoutrementRenderer(Resources.IRON_BODY_PIECE));
        CollarRenderManager.registerRenderer(CanisAccouterments.DIAMOND_BODY_PIECE, new ArmorAccoutrementRenderer(Resources.DIAMOND_BODY_PIECE));
        CollarRenderManager.registerRenderer(CanisAccouterments.GOLDEN_BODY_PIECE, new ArmorAccoutrementRenderer(Resources.GOLDEN_BODY_PIECE));
        CollarRenderManager.registerRenderer(CanisAccouterments.CHAINMAIL_BODY_PIECE, new ArmorAccoutrementRenderer(Resources.CHAINMAIL_BODY_PIECE));
        CollarRenderManager.registerRenderer(CanisAccouterments.NETHERITE_BODY_PIECE, new ArmorAccoutrementRenderer(Resources.NETHERITE_BODY_PIECE));

        CollarRenderManager.registerRenderer(CanisAccouterments.IRON_BOOTS, new ArmorAccoutrementRenderer(Resources.IRON_BOOTS));
        CollarRenderManager.registerRenderer(CanisAccouterments.DIAMOND_BOOTS, new ArmorAccoutrementRenderer(Resources.DIAMOND_BOOTS));
        CollarRenderManager.registerRenderer(CanisAccouterments.GOLDEN_BOOTS, new ArmorAccoutrementRenderer(Resources.GOLDEN_BOOTS));
        CollarRenderManager.registerRenderer(CanisAccouterments.CHAINMAIL_BOOTS, new ArmorAccoutrementRenderer(Resources.CHAINMAIL_BOOTS));
        CollarRenderManager.registerRenderer(CanisAccouterments.NETHERITE_BOOTS, new ArmorAccoutrementRenderer(Resources.NETHERITE_BOOTS));

        CollarRenderManager.registerRenderer(CanisAccouterments.LEATHER_HELMET, new ArmorAccoutrementRenderer(Resources.LEATHER_HELMET));
        CollarRenderManager.registerRenderer(CanisAccouterments.LEATHER_BODY_PIECE, new ArmorAccoutrementRenderer(Resources.LEATHER_BODY_PIECE));
        CollarRenderManager.registerRenderer(CanisAccouterments.LEATHER_BOOTS, new ArmorAccoutrementRenderer(Resources.LEATHER_BOOTS));

        CollarRenderManager.registerRenderer(CanisSkills.WAYWARD_TRAVELLER, new WaywardTravellerRenderer());
        CollarRenderManager.registerRenderer(CanisSkills.SAVIOR, new SaviorCanisRenderer());
    }
}




//        CollarRenderManager.registerRenderer(CanisAccouterments.CAPE, new DefaultAccoutrementRenderer(Resources.CAPE));