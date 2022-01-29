package com.platinumg17.rigoranthusemortisreborn.items.armor.armorsets;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.config.Config;
import com.platinumg17.rigoranthusemortisreborn.core.init.ItemInit;
import com.platinumg17.rigoranthusemortisreborn.items.armor.models.DwellerThoraxModel;
import com.platinumg17.rigoranthusemortisreborn.magica.common.potions.ModPotions;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

import static com.platinumg17.rigoranthusemortisreborn.core.init.Registration.BONE_PROP;
import static com.platinumg17.rigoranthusemortisreborn.items.armor.RigoranthusArmorMaterial.DWELLER;

public class DwellerThoraxArmor extends ArmorItem {
//    private boolean previousEquip = false;

    public DwellerThoraxArmor(Properties properties) {
        super(DWELLER, EquipmentSlotType.CHEST, properties);
    }

    public static IFormattableTextComponent newTip(String tip) {
        return new TranslationTextComponent("tooltip.rigoranthusemortisreborn" + tip).setStyle(Style.EMPTY);
    }

    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tip, ITooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tip, flagIn);
        if (Screen.hasShiftDown()) {
            tip.add(newTip(".dweller_thorax"));
            tip.add(newTip(".dweller_thorax2"));
            tip.add(newTip(".dweller_thorax3"));
            tip.add(newTip(".dweller_thorax4"));
        } else {
            tip.add(newTip(".hold_shift"));
        }
    }
    @Override
    @OnlyIn(Dist.CLIENT)
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack stack, EquipmentSlotType armorSlot, A _default) {
        return DwellerThoraxModel.getModel(armorSlot, entityLiving);
    }
    @Override public boolean isEnchantable(ItemStack stack) {
        return true;
    }
    @Override public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return true;
    }
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
        return RigoranthusEmortisReborn.MOD_ID + ":textures/models/armor/dweller_layer_1.png";
    }
//    public void onArmorTick(ItemStack itemStack, World world, PlayerEntity player) {
//        if (Config.enableArmorSetBonuses.get()) {
//            ItemStack chest = player.getItemBySlot(EquipmentSlotType.CHEST);
//            if (chest.getItem() == ItemInit.DWELLER_THORAX.get()) {
//                player.addEffect(new EffectInstance(ModPotions.DOMINION_REGEN_EFFECT, 5, 1));
//                this.previousEquip = true;
//            } else if (this.previousEquip) {
//                player.removeEffect(ModPotions.DOMINION_REGEN_EFFECT);
//                this.previousEquip = false;
//            }
//        }
//    }
}