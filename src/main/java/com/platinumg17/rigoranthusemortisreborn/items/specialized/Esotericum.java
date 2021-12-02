package com.platinumg17.rigoranthusemortisreborn.items.specialized;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import net.minecraft.advancements.Advancement;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class Esotericum extends Item {
    public Esotericum(Properties properties) {
        super(properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
        if (entity instanceof PlayerEntity) {
            if (!world.isClientSide) {
                ServerPlayerEntity player = ((ServerPlayerEntity) entity);
                if (!stack.hasTag()) {
                    if (player.getAdvancements().getOrStartProgress(Advancement.Builder.advancement().
                                build(new ResourceLocation("rigoranthusemortisreborn:adventure/listen_to_a_forgotten_record"))).isDone()) {
                        CompoundNBT tag = new CompoundNBT();
                        tag.putBoolean(EmortisConstants.MOD_ID + ".hasAdvancement", true);
                        stack.setTag(tag);
                    }
                }
            }
        }
        super.inventoryTick(stack, world, entity, itemSlot, isSelected);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {

        if (stack.hasTag()) {
            if (stack.hasTag() && stack.getTag().getBoolean(EmortisConstants.MOD_ID + ".hasAdvancement")) {
                tooltip.add(new TranslationTextComponent("tooltip." + EmortisConstants.MOD_ID + ".esotericum").setStyle(Style.EMPTY));
                tooltip.add(new TranslationTextComponent("tooltip." + EmortisConstants.MOD_ID + ".esotericum2").setStyle(Style.EMPTY));
            }
        } else {
            tooltip.add(new TranslationTextComponent("tooltip." + EmortisConstants.MOD_ID + ".esotericum_obfuscated").setStyle(Style.EMPTY));
        }
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
    }
}