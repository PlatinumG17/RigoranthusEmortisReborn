package com.platinumg17.rigoranthusemortisreborn.items;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.config.Config;
import com.platinumg17.rigoranthusemortisreborn.core.init.ItemInit;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

public enum RigoranthusArmorMaterial implements IArmorMaterial {

    DWELLER("dweller", Config.dweller_thorax_durability_multiplier.get(), new int[]{0, 0, Config.dweller_thorax_chestplate_damage_reduction.get(), 0}, Config.dweller_thorax_enchantability.get(), SoundEvents.CORAL_BLOCK_STEP, Config.dweller_thorax_toughness.get().floatValue(), Config.dweller_thorax_knockback_resistance.get().floatValue(), () -> {
        return Ingredient.of(ItemInit.DWELLER_FLESH.get());  //.getArmorModel(LivingEntity, ItemInit.DWELLER_THORAX, EquipmentSlotType.CHEST));
    }),

    APOGEAN_NETHERITE("apogean_netherite", Config.apogean_durability_multiplier.get(), new int[]{Config.apogean_boots_damage_reduction.get(), Config.apogean_leggings_damage_reduction.get(), Config.apogean_chestplate_damage_reduction.get(), Config.apogean_helmet_damage_reduction.get()}, Config.apogean_armor_enchantability.get(), SoundEvents.ARMOR_EQUIP_NETHERITE, Config.apogean_toughness.get().floatValue(), Config.apogean_knockback_resistance.get().floatValue(), () -> {
        return Ingredient.of(ItemInit.APOGEAN_NETHERITE_INGOT.get());
    }), //Config.apogean_armor_value1.get(), Config.apogean_armor_value2.get(), Config.apogean_armor_value3.get(), Config.apogean_armor_value4.get()

    AQUEOUS_NETHERITE("aqueous_netherite", Config.aqueous_durability_multiplier.get(), new int[]{Config.aqueous_boots_damage_reduction.get(), Config.aqueous_leggings_damage_reduction.get(), Config.aqueous_chestplate_damage_reduction.get(), Config.aqueous_helmet_damage_reduction.get()}, Config.aqueous_armor_enchantability.get(), SoundEvents.ARMOR_EQUIP_NETHERITE, Config.aqueous_toughness.get().floatValue(), Config.aqueous_knockback_resistance.get().floatValue(), () -> {
        return Ingredient.of(ItemInit.AQUEOUS_NETHERITE_INGOT.get());
    }),

    ATROPHYING_NETHERITE("atrophying_netherite", Config.atrophying_durability_multiplier.get(), new int[]{Config.atrophying_boots_damage_reduction.get(), Config.atrophying_leggings_damage_reduction.get(), Config.atrophying_chestplate_damage_reduction.get(), Config.atrophying_helmet_damage_reduction.get()}, Config.atrophying_armor_enchantability.get(), SoundEvents.ARMOR_EQUIP_NETHERITE, Config.atrophying_toughness.get().floatValue(), Config.atrophying_knockback_resistance.get().floatValue(), () -> {
        return Ingredient.of(ItemInit.ATROPHYING_NETHERITE_INGOT.get());
    }),

    INCORPOREAL_NETHERITE("incorporeal_netherite", Config.incorporeal_durability_multiplier.get(), new int[]{Config.incorporeal_boots_damage_reduction.get(), Config.incorporeal_leggings_damage_reduction.get(), Config.incorporeal_chestplate_damage_reduction.get(), Config.incorporeal_helmet_damage_reduction.get()}, Config.incorporeal_armor_enchantability.get(), SoundEvents.ARMOR_EQUIP_NETHERITE, Config.incorporeal_toughness.get().floatValue(), Config.incorporeal_knockback_resistance.get().floatValue(), () -> {
        return Ingredient.of(ItemInit.INCORPOREAL_NETHERITE_INGOT.get());
    }),

    INFERNAL_NETHERITE("infernal_netherite", Config.infernal_durability_multiplier.get(), new int[]{Config.infernal_boots_damage_reduction.get(), Config.infernal_leggings_damage_reduction.get(), Config.infernal_chestplate_damage_reduction.get(), Config.infernal_helmet_damage_reduction.get()}, Config.infernal_armor_enchantability.get(), SoundEvents.ARMOR_EQUIP_NETHERITE, Config.infernal_toughness.get().floatValue(), Config.infernal_knockback_resistance.get().floatValue(), () -> {
        return Ingredient.of(ItemInit.INFERNAL_NETHERITE_INGOT.get());
    }),

    OPULENT_NETHERITE("opulent_netherite", Config.opulent_durability_multiplier.get(), new int[]{Config.opulent_boots_damage_reduction.get(), Config.opulent_leggings_damage_reduction.get(), Config.opulent_chestplate_damage_reduction.get(), Config.opulent_helmet_damage_reduction.get()}, Config.opulent_armor_enchantability.get(), SoundEvents.ARMOR_EQUIP_NETHERITE, Config.opulent_toughness.get().floatValue(), Config.opulent_knockback_resistance.get().floatValue(), () -> {
        return Ingredient.of(ItemInit.OPULENT_NETHERITE_INGOT.get());
    }),

    PERNICIOUS_NETHERITE("pernicious_netherite", Config.pernicious_durability_multiplier.get(), new int[]{Config.pernicious_boots_damage_reduction.get(), Config.pernicious_leggings_damage_reduction.get(), Config.pernicious_chestplate_damage_reduction.get(), Config.pernicious_helmet_damage_reduction.get()}, Config.pernicious_armor_enchantability.get(), SoundEvents.ARMOR_EQUIP_NETHERITE, Config.pernicious_toughness.get().floatValue(), Config.pernicious_knockback_resistance.get().floatValue(), () -> {
        return Ingredient.of(ItemInit.PERNICIOUS_NETHERITE_INGOT.get());
    }),

    PHANTASMAL_NETHERITE("phantasmal_netherite", Config.phantasmal_durability_multiplier.get(), new int[]{Config.phantasmal_boots_damage_reduction.get(), Config.phantasmal_leggings_damage_reduction.get(), Config.phantasmal_chestplate_damage_reduction.get(), Config.phantasmal_helmet_damage_reduction.get()}, Config.phantasmal_armor_enchantability.get(), SoundEvents.ARMOR_EQUIP_NETHERITE, Config.phantasmal_toughness.get().floatValue(), Config.phantasmal_knockback_resistance.get().floatValue(), () -> {
        return Ingredient.of(ItemInit.PHANTASMAL_NETHERITE_INGOT.get());
    }),

    REMEX_NETHERITE("remex_netherite", Config.remex_durability_multiplier.get(), new int[]{Config.remex_boots_damage_reduction.get(), Config.remex_leggings_damage_reduction.get(), Config.remex_chestplate_damage_reduction.get(), Config.remex_helmet_damage_reduction.get()}, Config.remex_armor_enchantability.get(), SoundEvents.ARMOR_EQUIP_NETHERITE, Config.remex_toughness.get().floatValue(), Config.remex_knockback_resistance.get().floatValue(), () -> {
        return Ingredient.of(ItemInit.REMEX_NETHERITE_INGOT.get());
    });

    private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
    private final String name;
    private final int durabilityMultiplier;
    private final int[] slotProtections;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyValue<Ingredient> repairIngredient;

    private RigoranthusArmorMaterial(String p_i231593_3_, int p_i231593_4_, int[] p_i231593_5_, int p_i231593_6_, SoundEvent p_i231593_7_, float p_i231593_8_, float p_i231593_9_, Supplier<Ingredient> p_i231593_10_) {
        this.name = p_i231593_3_;
        this.durabilityMultiplier = p_i231593_4_;
        this.slotProtections = p_i231593_5_;
        this.enchantmentValue = p_i231593_6_;
        this.sound = p_i231593_7_;
        this.toughness = p_i231593_8_;
        this.knockbackResistance = p_i231593_9_;
        this.repairIngredient = new LazyValue(p_i231593_10_);
    }

    public int getDurabilityForSlot(EquipmentSlotType p_200896_1_) {
        return HEALTH_PER_SLOT[p_200896_1_.getIndex()] * this.durabilityMultiplier;
    }

    public int getDefenseForSlot(EquipmentSlotType p_200902_1_) {
        return this.slotProtections[p_200902_1_.getIndex()];
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public SoundEvent getEquipSound() {
        return this.sound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @OnlyIn(Dist.CLIENT)
    public String getName() {
        return RigoranthusEmortisReborn.MOD_ID + ":" + this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
