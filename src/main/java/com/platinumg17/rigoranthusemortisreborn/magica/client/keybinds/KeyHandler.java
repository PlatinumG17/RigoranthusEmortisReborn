package com.platinumg17.rigoranthusemortisreborn.magica.client.keybinds;

import com.platinumg17.rigoranthusemortisreborn.api.RigoranthusEmortisRebornAPI;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.util.StackUtil;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.magica.client.gui.GuiRadialMenu;
import com.platinumg17.rigoranthusemortisreborn.magica.client.gui.book.GuiSpellBook;
import com.platinumg17.rigoranthusemortisreborn.magica.common.items.SpellBook;
import com.platinumg17.rigoranthusemortisreborn.magica.common.network.Networking;
import com.platinumg17.rigoranthusemortisreborn.magica.common.network.PacketUpdateSpellbook;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = EmortisConstants.MOD_ID)
public class KeyHandler {
    private static final Minecraft MINECRAFT = Minecraft.getInstance();

    public static void checkKeysPressed(int key){
        ItemStack stack = StackUtil.getHeldSpellbook(MINECRAFT.player);

        if(key == REKeyBindings.NEXT_SLOT.getKey().getValue()  && stack.getItem() instanceof SpellBook){
            if(!stack.hasTag())
                return;
            CompoundNBT tag = stack.getTag();
            int newMode = SpellBook.getMode(tag) + 1;
            if(newMode > 10)
                newMode = 0;

            sendUpdatePacket(tag, newMode);
            return;
        }

        if(key == REKeyBindings.PREVIOUS__SLOT.getKey().getValue()  && stack.getItem() instanceof SpellBook){
            if(!stack.hasTag())
                return;
            CompoundNBT tag = stack.getTag();
            int newMode = SpellBook.getMode(tag) - 1;
            if(newMode < 0)
                newMode = 10;

            sendUpdatePacket(tag, newMode);
            return;
        }

        if(key == REKeyBindings.OPEN_SPELL_SELECTION.getKey().getValue()){
            if(MINECRAFT.screen instanceof GuiRadialMenu) {
                MINECRAFT.player.closeContainer();
                return;
            }
            if(stack.getItem() instanceof SpellBook && stack.hasTag() && MINECRAFT.screen == null){
                MINECRAFT.setScreen(new GuiRadialMenu(stack.getTag()));
            }
        }

        if(key == REKeyBindings.OPEN_BOOK.getKey().getValue()){
            if(MINECRAFT.screen instanceof GuiSpellBook && !((GuiSpellBook) MINECRAFT.screen).spell_name.isFocused()) {
                MINECRAFT.player.closeContainer();
                return;
            }

            if(stack.getItem() instanceof SpellBook && stack.hasTag() && MINECRAFT.screen == null){
                GuiSpellBook.open(RigoranthusEmortisRebornAPI.getInstance(), stack.getTag(), ((SpellBook) stack.getItem()).getTier().ordinal(), SpellBook.getUnlockedSpellString(stack.getTag()));
            }
        }
    }
    @SubscribeEvent
    public static void mouseEvent(final InputEvent.MouseInputEvent event) {

        if(MINECRAFT.player == null || MINECRAFT.screen != null || event.getAction() != 1)
            return;
        checkKeysPressed(event.getButton());
    }
    @SubscribeEvent
    public static void keyEvent(final InputEvent.KeyInputEvent event) {
        if(MINECRAFT.player == null || MINECRAFT.screen != null || event.getAction() != 1)
            return;
        checkKeysPressed(event.getKey());
    }

    public static void sendUpdatePacket(CompoundNBT tag, int newMode){
        String recipe = SpellBook.getRecipeString(tag, newMode);
        String name = SpellBook.getSpellName(tag, newMode);
        Networking.INSTANCE.sendToServer(new PacketUpdateSpellbook(recipe, newMode, name));
    }
}