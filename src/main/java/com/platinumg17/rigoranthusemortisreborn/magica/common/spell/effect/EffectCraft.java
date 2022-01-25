package com.platinumg17.rigoranthusemortisreborn.magica.common.spell.effect;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.*;
import com.platinumg17.rigoranthusemortisreborn.magica.common.lib.GlyphLib;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.inventory.container.WorkbenchContainer;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;

public class EffectCraft extends AbstractEffect {

    public static EffectCraft INSTANCE = new EffectCraft();

    private EffectCraft() {
        super(GlyphLib.EffectCraftID, "Craft");
    }

    private static final ITextComponent CONTAINER_NAME = new TranslationTextComponent("container.crafting");

    @Override
    public void onResolve(RayTraceResult rayTraceResult, World world, @Nullable LivingEntity shooter, SpellStats spellStats, SpellContext spellContext) {
        if(shooter instanceof PlayerEntity && isRealPlayer(shooter)){
            PlayerEntity playerEntity = (PlayerEntity) shooter;
            playerEntity.openMenu(new SimpleNamedContainerProvider((id, inventory, player) -> new CustomWorkbench(id, inventory, IWorldPosCallable.create(player.getCommandSenderWorld(), player.blockPosition())), CONTAINER_NAME));
        }
    }

    @Override
    public int getDominionCost() {
        return 50;
    }

    public static class CustomWorkbench extends WorkbenchContainer{
        public CustomWorkbench(int id, PlayerInventory playerInventory) {
            super(id, playerInventory);
        }

        public CustomWorkbench(int id, PlayerInventory playerInventory, IWorldPosCallable p_i50090_3_) {
            super(id, playerInventory, p_i50090_3_);
        }

        @Override
        public boolean stillValid(PlayerEntity playerIn) {
            return true;
        }
    }

    @Override
    public Item getCraftingReagent() {
        return Items.CRAFTING_TABLE;
    }

    @Nonnull
    @Override
    public Set<AbstractAugment> getCompatibleAugments() {
        return augmentSetOf();
    }

    @Override
    public String getBookDescription() {
        return "Opens the crafting menu.";
    }

    @Nonnull
    @Override
    public Set<SpellSchool> getSchools() {
        return setOf(SpellSchools.MANIPULATION);
    }
}