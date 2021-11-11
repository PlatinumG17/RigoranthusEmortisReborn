package com.platinumg17.rigoranthusemortisreborn.canis.common.inventory.screens;

import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.CanisEntity;
import com.platinumg17.rigoranthusemortisreborn.canis.common.block.tileentity.FoodBowlTileEntity;
import com.platinumg17.rigoranthusemortisreborn.canis.common.inventory.container.CanisInventoriesContainer;
import com.platinumg17.rigoranthusemortisreborn.canis.common.inventory.container.TreatBagContainer;
import com.platinumg17.rigoranthusemortisreborn.canis.common.inventory.container.WaywardTravellerContainer;
import com.platinumg17.rigoranthusemortisreborn.canis.CanisItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IntArray;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.network.NetworkHooks;
import com.platinumg17.rigoranthusemortisreborn.api.interfaces.AbstractCanisEntity;

import java.util.List;

public class CanisScreens {

    public static class WaywardTravellerContainerProvider implements INamedContainerProvider {

        private AbstractCanisEntity canis;

        public WaywardTravellerContainerProvider(AbstractCanisEntity canisIn) {
            this.canis = canisIn;
        }

        @Override
        public Container createMenu(int windowId, PlayerInventory inventory, PlayerEntity player) {
            return new WaywardTravellerContainer(windowId, inventory, this.canis);
        }

        @Override
        public ITextComponent getDisplayName() {
            return new TranslationTextComponent("container.rigoranthusemortisreborn.wayward_traveller");
        }
    }

    public static class CanisInventoriesContainerProvider implements INamedContainerProvider {

        private List<CanisEntity> canis;

        public CanisInventoriesContainerProvider(List<CanisEntity> canisIn) {
            this.canis = canisIn;
        }

        @Override
        public Container createMenu(int windowId, PlayerInventory inventory, PlayerEntity player) {
            IntArray array = new IntArray(this.canis.size());
            for (int i = 0; i < array.getCount(); i++) {
                array.set(i, this.canis.get(i).getId());
            }
            return new CanisInventoriesContainer(windowId, inventory, array);
        }

        @Override
        public TextComponent getDisplayName() {
            return new TranslationTextComponent("container.rigoranthusemortisreborn.canis_inventories");
        }
    }

    public static class TreatBagContainerProvider implements INamedContainerProvider {

        private ItemStack stack;
        private int slotId;

        public TreatBagContainerProvider(ItemStack stackIn, int slotId) {
            this.stack = stackIn;
            this.slotId = slotId;
        }

        @Override
        public Container createMenu(int windowId, PlayerInventory inventory, PlayerEntity player) {
            return new TreatBagContainer(windowId, inventory, this.slotId, this.stack);
        }

        @Override
        public ITextComponent getDisplayName() {
            return new TranslationTextComponent("container.rigoranthusemortisreborn.treat_bag");
        }
    }

    public static void openWaywardTravellerScreen(ServerPlayerEntity player, AbstractCanisEntity canisIn) {
        if (canisIn.isAlive()) {
            NetworkHooks.openGui(player, new WaywardTravellerContainerProvider(canisIn), (buf) -> {
                buf.writeInt(canisIn.getId());
            });
        }
    }

    public static void openCanisInventoriesScreen(ServerPlayerEntity player, List<CanisEntity> canisIn) {
        if (!canisIn.isEmpty()) {
            NetworkHooks.openGui(player, new CanisInventoriesContainerProvider(canisIn), (buf) -> {
                buf.writeInt(canisIn.size());
                for (CanisEntity canis : canisIn) {
                    buf.writeInt(canis.getId());
                }
            });
        }
    }

    public static void openFoodBowlScreen(ServerPlayerEntity player, FoodBowlTileEntity foodBowl) {
        NetworkHooks.openGui(player, foodBowl, foodBowl.getBlockPos());
    }

    public static void openTreatBagScreen(ServerPlayerEntity player, ItemStack stackIn, int slotId) {
        if (stackIn.getItem() == CanisItems.TREAT_BAG.get()) {
            NetworkHooks.openGui(player, new TreatBagContainerProvider(stackIn, slotId), buf -> {
                buf.writeVarInt(slotId);
                buf.writeItem(stackIn);
            });
        }
    }
}