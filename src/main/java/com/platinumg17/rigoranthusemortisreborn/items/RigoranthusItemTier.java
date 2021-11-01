package com.platinumg17.rigoranthusemortisreborn.items;

import com.platinumg17.rigoranthusemortisreborn.config.Config;
import com.platinumg17.rigoranthusemortisreborn.core.init.ItemInit;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

import java.util.function.Supplier;

public enum RigoranthusItemTier implements IItemTier {
    APOGEAN(4, Config.apogean_tier_durability.get(), Config.apogean_tier_speed.get().floatValue(), Config.apogean_tier_damage.get().floatValue(), Config.apogean_tier_enchantability.get(),
            () -> Ingredient.of(ItemInit.APOGEAN_NETHERITE_INGOT.get())),
    AQUEOUS(4, Config.aqueous_tier_durability.get(), Config.aqueous_tier_speed.get().floatValue(), Config.aqueous_tier_damage.get().floatValue(), Config.aqueous_tier_enchantability.get(),
            () -> Ingredient.of(ItemInit.AQUEOUS_NETHERITE_INGOT.get())),
    ATROPHYING(4, Config.atrophying_tier_durability.get(), Config.atrophying_tier_speed.get().floatValue(), Config.atrophying_tier_damage.get().floatValue(), Config.atrophying_tier_enchantability.get(),
            () -> Ingredient.of(ItemInit.ATROPHYING_NETHERITE_INGOT.get())),
    INCORPOREAL(4, Config.incorporeal_tier_durability.get(), Config.incorporeal_tier_speed.get().floatValue(), Config.incorporeal_tier_damage.get().floatValue(), Config.incorporeal_tier_enchantability.get(),
            () -> Ingredient.of(ItemInit.INCORPOREAL_NETHERITE_INGOT.get())),
    INFERNAL(4, Config.infernal_tier_durability.get(), Config.infernal_tier_speed.get().floatValue(), Config.infernal_tier_damage.get().floatValue(), Config.infernal_tier_enchantability.get(),
            () -> Ingredient.of(ItemInit.INFERNAL_NETHERITE_INGOT.get())),
    OPULENT(4, Config.opulent_tier_durability.get(), Config.opulent_tier_speed.get().floatValue(), Config.opulent_tier_damage.get().floatValue(), Config.opulent_tier_enchantability.get(),
            () -> Ingredient.of(ItemInit.OPULENT_NETHERITE_INGOT.get())),
    PERNICIOUS(4, Config.pernicious_tier_durability.get(), Config.pernicious_tier_speed.get().floatValue(), Config.pernicious_tier_damage.get().floatValue(), Config.pernicious_tier_enchantability.get(),
            () -> Ingredient.of(ItemInit.PERNICIOUS_NETHERITE_INGOT.get())),
    PHANTASMAL(4, Config.phantasmal_tier_durability.get(), Config.phantasmal_tier_speed.get().floatValue(), Config.phantasmal_tier_damage.get().floatValue(), Config.phantasmal_tier_enchantability.get(),
            () -> Ingredient.of(ItemInit.PHANTASMAL_NETHERITE_INGOT.get())),
    REMEX(4, Config.remex_tier_durability.get(), Config.remex_tier_speed.get().floatValue(), Config.remex_tier_damage.get().floatValue(), Config.remex_tier_enchantability.get(),
            () -> Ingredient.of(ItemInit.REMEX_NETHERITE_INGOT.get())),
    BONE(2, Config.bone_tier_durability.get(), Config.bone_tier_speed.get().floatValue(), Config.bone_tier_damage.get().floatValue(), Config.bone_tier_enchantability.get(),
            () -> Ingredient.of(ItemInit.BONE_FRAGMENT.get()));

    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final LazyValue<Ingredient> repairIngredient;

    RigoranthusItemTier(int level, int uses, float speed, float damage, int enchantmentValue, Supplier<Ingredient> repairIngredient) {
        this.level = level;
        this.uses = uses;
        this.speed = speed;
        this.damage = damage;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = new LazyValue<>(repairIngredient);
    }
    @Override public int getUses() {
        return 0;
    }
    @Override public float getSpeed() {
        return 0;
    }
    @Override public float getAttackDamageBonus() {
        return 0;
    }
    @Override public int getLevel() {
        return 0;
    }
    @Override public int getEnchantmentValue() {
        return 0;
    }
    @Override public Ingredient getRepairIngredient() {
        return repairIngredient.get();
    }
}
