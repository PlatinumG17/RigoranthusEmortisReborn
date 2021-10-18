package com.platinumg17.rigoranthusemortisreborn.items.armor.armorsets;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.config.Config;
import com.platinumg17.rigoranthusemortisreborn.core.init.Registration;
import com.platinumg17.rigoranthusemortisreborn.core.registry.effects.RigoranthusEffectRegistry;
import com.platinumg17.rigoranthusemortisreborn.entity.model.LanguidDwellerModel;
import com.platinumg17.rigoranthusemortisreborn.models.DwellerThoraxModel;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class DwellerThoraxArmor extends ArmorItem {
    //private boolean previousEquip = false;

    public DwellerThoraxArmor(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
        super(materialIn, slot, builder);
    }

    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        if (Screen.hasShiftDown()) {
            tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".dweller_thorax").setStyle(Style.EMPTY));
            tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".dweller_thorax2").setStyle(Style.EMPTY));
            tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".dweller_thorax3").setStyle(Style.EMPTY));
            tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".dweller_thorax4").setStyle(Style.EMPTY));
        } else {
            tooltip.add(new TranslationTextComponent("tooltip." + RigoranthusEmortisReborn.MOD_ID + ".hold_shift").setStyle(Style.EMPTY));
        }
    }
    @Override
    @OnlyIn(Dist.CLIENT)
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack stack, EquipmentSlotType armorSlot, A _default) {
        return DwellerThoraxModel.getModel(armorSlot, entityLiving);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
        return RigoranthusEmortisReborn.MOD_ID + ":textures/models/armor/dweller_layer_1.png";
    }
//    public void onArmorTick(ItemStack itemStack, World world, PlayerEntity player) {
//        if (Config.enableArmorSetBonuses.get()) {
//            ItemStack chest = player.getItemBySlot(EquipmentSlotType.CHEST);
//            if (chest.getItem() == Registration.DWELLER_THORAX) {
//                player.addEffect(new EffectInstance(RigoranthusEffectRegistry.DWELLER_SET_BONUS, 5, 1));
//                this.previousEquip = true;
//            } else if (this.previousEquip) {
//                player.removeEffect(RigoranthusEffectRegistry.DWELLER_SET_BONUS);
//                this.previousEquip = false;
//            }
//        }
//    }
}