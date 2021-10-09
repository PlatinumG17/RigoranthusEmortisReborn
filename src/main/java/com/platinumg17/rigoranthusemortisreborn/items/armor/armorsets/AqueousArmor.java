package com.platinumg17.rigoranthusemortisreborn.items.armor.armorsets;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.config.Config;
import com.platinumg17.rigoranthusemortisreborn.core.init.Registration;
import com.platinumg17.rigoranthusemortisreborn.core.registry.effects.RigoranthusEffectRegistry;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class AqueousArmor extends ArmorItem {
    private boolean previousEquip = false;

    public AqueousArmor(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
        super(materialIn, slot, builder);
    }

    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        if (Screen.hasShiftDown()) {
            tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".aqueous_ingot").setStyle(Style.EMPTY));
            tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".aqueous_ingot2").setStyle(Style.EMPTY));
            tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".aqueous_ingot3").setStyle(Style.EMPTY));
            tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".aqueous_ingot4").setStyle(Style.EMPTY));
            tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".aqueous_ingot5").setStyle(Style.EMPTY));
            tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".aqueous_ingot6").setStyle(Style.EMPTY));
            tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".aqueous_ingot7").setStyle(Style.EMPTY));
            tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".aqueous_ingot8").setStyle(Style.EMPTY));
            tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".aqueous_ingot9").setStyle(Style.EMPTY));
            tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".aqueous_ingot10").setStyle(Style.EMPTY));
            tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".aqueous_ingot11").setStyle(Style.EMPTY));
            tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".aqueous_ingot12").setStyle(Style.EMPTY));
            tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".aqueous_ingot13").setStyle(Style.EMPTY));
        } else {
            tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".aqueous_ingot_shift").setStyle(Style.EMPTY));
        }
    }

    public void onArmorTick(ItemStack itemStack, World world, PlayerEntity player) {
        if (Config.enableArmorSetBonuses.get()) {
            ItemStack boots = player.getItemBySlot(EquipmentSlotType.FEET);
            ItemStack legs = player.getItemBySlot(EquipmentSlotType.LEGS);
            ItemStack chest = player.getItemBySlot(EquipmentSlotType.CHEST);
            ItemStack helm = player.getItemBySlot(EquipmentSlotType.HEAD);
            if (boots.getItem() == Registration.AQUEOUS_NETHERITE_BOOTS && legs.getItem() == Registration.AQUEOUS_NETHERITE_LEGGINGS && chest.getItem() == Registration.AQUEOUS_NETHERITE_CHESTPLATE && helm.getItem() == Registration.AQUEOUS_NETHERITE_HELMET) {
                player.addEffect(new EffectInstance(RigoranthusEffectRegistry.AQUEOUS_SET_BONUS, 5, 1));
                this.previousEquip = true;
            } else if (this.previousEquip) {
                player.removeEffect(RigoranthusEffectRegistry.AQUEOUS_SET_BONUS);
                this.previousEquip = false;
            }
        }
    }
}