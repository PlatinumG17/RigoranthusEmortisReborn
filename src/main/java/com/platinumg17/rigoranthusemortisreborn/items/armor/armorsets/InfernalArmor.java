package com.platinumg17.rigoranthusemortisreborn.items.armor.armorsets;

import com.platinumg17.rigoranthusemortisreborn.config.Config;
import com.platinumg17.rigoranthusemortisreborn.core.init.Registration;
import com.platinumg17.rigoranthusemortisreborn.magica.common.potions.ModPotions;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static com.platinumg17.rigoranthusemortisreborn.core.init.Registration.ARMOR_PROP;
import static com.platinumg17.rigoranthusemortisreborn.items.armor.RigoranthusArmorMaterial.INFERNAL_NETHERITE;

public class InfernalArmor extends ArmorItem {
    private boolean previousEquip = false;

    public InfernalArmor(EquipmentSlotType slot) {
        super(INFERNAL_NETHERITE, slot, ARMOR_PROP);
    }

    public static IFormattableTextComponent newTip(String tip) {
        return new TranslationTextComponent("tooltip.rigoranthusemortisreborn" + tip).setStyle(Style.EMPTY);
    }

    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tip, ITooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tip, flagIn);
        if (Screen.hasShiftDown()) {
            tip.add(newTip(".infernal_ingot"));
            tip.add(newTip(".infernal_ingot2"));
            tip.add(newTip(".infernal_ingot3"));
            tip.add(newTip(".infernal_ingot4"));
            tip.add(newTip(".infernal_ingot5"));
            tip.add(newTip(".infernal_ingot6"));
            tip.add(newTip(".infernal_ingot7"));
            tip.add(newTip(".infernal_ingot8"));
            tip.add(newTip(".infernal_ingot9"));
            tip.add(newTip(".infernal_ingot10"));
            tip.add(newTip(".infernal_ingot11"));
            tip.add(newTip(".infernal_ingot12"));
            tip.add(newTip(".infernal_ingot13"));
            tip.add(newTip(".infernal_ingot14"));
            tip.add(newTip(".infernal_ingot15"));
            tip.add(newTip(".infernal_ingot16"));
            tip.add(newTip(".infernal_ingot17"));
            tip.add(newTip(".infernal_ingot18"));
        } else {
            tip.add(newTip(".hold_shift"));
        }
    }

    public void onArmorTick(ItemStack itemStack, World world, PlayerEntity player) {
        if (Config.enableArmorSetBonuses.get()) {
            ItemStack boots = player.getItemBySlot(EquipmentSlotType.FEET);
            ItemStack legs = player.getItemBySlot(EquipmentSlotType.LEGS);
            ItemStack chest = player.getItemBySlot(EquipmentSlotType.CHEST);
            ItemStack helm = player.getItemBySlot(EquipmentSlotType.HEAD);
            if (boots.getItem() == Registration.INFERNAL_N_BOOTS && legs.getItem() == Registration.INFERNAL_N_LEGS && chest.getItem() == Registration.INFERNAL_N_CHEST && helm.getItem() == Registration.INFERNAL_N_HELMET) {
                player.addEffect(new EffectInstance(ModPotions.INFERNAL_SET_BONUS, 5, 1));
                this.previousEquip = true;
            } else if (this.previousEquip) {
                player.removeEffect(ModPotions.INFERNAL_SET_BONUS);
                this.previousEquip = false;
            }
        }
    }
}