package com.platinumg17.rigoranthusemortisreborn.core.events.keybinds;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.screen.inventory.CreativeScreen;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraft.inventory.container.Slot;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

//@Mod.EventBusSubscriber(modid = EmortisConstants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
//public class REKeyHandler {
//
//    public static final String CATEGORY = "key.categories.rigoranthusemortisreborn";
//    public static final String STATS_GUI = "key.rigoranthusemortisreborn.stats_gui";
//    public static final String CANIS_COMMAND = "key.rigoranthusemortisreborn.canis_command";
//    public static final String ASPECT_EFFECT_TOGGLE = "key.rigoranthusemortisreborn.emortic_aspect_effect_toggle";
//
//    public static KeyBinding statKey;
//    public static KeyBinding canisCommand;
//    public static KeyBinding emorticAspectToggle;
//
//    public static void registerKeys() {
//        if(statKey != null)
//            throw new IllegalStateException("Rigoranthus keys have already been registered!");
//
//        statKey = new KeyBinding(STATS_GUI, GLFW.GLFW_KEY_G, CATEGORY);
//        ClientRegistry.registerKeyBinding(statKey);
//        canisCommand = new KeyBinding(CANIS_COMMAND, GLFW.GLFW_KEY_V, CATEGORY);
//        ClientRegistry.registerKeyBinding(canisCommand);
//        emorticAspectToggle = new KeyBinding(ASPECT_EFFECT_TOGGLE, KeyConflictContext.IN_GAME, InputMappings.Type.KEYSYM, GLFW.GLFW_KEY_BACKSLASH, CATEGORY);
//        ClientRegistry.registerKeyBinding(emorticAspectToggle);
//    }
//
//    @SubscribeEvent
//    public static void guiKeyInput(GuiScreenEvent.KeyboardKeyPressedEvent.Post event) {
//        InputMappings.Input input = InputMappings.getKey(event.getKeyCode(), event.getScanCode());
//
//        if(canisCommand.isActiveAndMatches(input) && Minecraft.getInstance().screen instanceof ContainerScreen<?>) {
//            canisCommandInGui((ContainerScreen<?>) Minecraft.getInstance().screen);
//            event.setCanceled(true);
//        }
//    }
//    private static boolean isNotRelease(InputEvent.KeyInputEvent event) {return event.getAction() != 0;}
//
//    @SubscribeEvent
//    public static void onKeyInput(InputEvent.KeyInputEvent event) {     //This is only called during the game, when no gui is active
//        if(isNotRelease(event) && Minecraft.getInstance().screen == null){
//            InputMappings.Input input = InputMappings.getKey(event.getKey(), event.getScanCode());
//
//            if(statKey.isActiveAndMatches(input))
//                PlayerStatsScreen.openGui(false);
//
//            if(emorticAspectToggle.isActiveAndMatches(input))
//                ClientAspectHandler.onKeyPressed();
//
//            if(canisCommand.isActiveAndMatches(input))
//                canisCommandInGame();
//
//            if(emorticAspectToggle.isActiveAndMatches(input))
//                REPacketHandler.sendToServer(new AspectTogglePacket());
//
//            if(emorticAspectToggleTest.isActiveAndMatches(input) && ClientPlayerData.getAspectScreen() != null)
//                REScreenFactories.displayAspectScreen(ClientPlayerData.getAspectScreen());
//        }
//    }
//
//    private static void canisCommandInGame() {
//        if(!Minecraft.getInstance().player.getMainHandItem().isEmpty())
//            REPacketHandler.sendToServer(CanisCommandPacket.caniscommand());
//    }
//
//    private static void canisCommandInGui(ContainerScreen<?> screen) {
//        if(!(screen instanceof CreativeScreen)) {
//            Slot slot = screen.getSlotUnderMouse();
//            if(slot != null && slot.hasItem())
//                REPacketHandler.sendToServer(CanisCommandPacket.caniscommandInv(slot.index, screen.getMenu().containerId));
//        }
//    }
//}