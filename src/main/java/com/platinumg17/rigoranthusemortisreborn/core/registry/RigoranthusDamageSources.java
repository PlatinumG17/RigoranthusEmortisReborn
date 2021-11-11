package com.platinumg17.rigoranthusemortisreborn.core.registry;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IndirectEntityDamageSource;

import javax.annotation.Nullable;

public class RigoranthusDamageSources {
    public static final DamageSource NECROTIZING_FASCIITIS = createDamageSource("necrotizingFasciitis");
    public static final DamageSource RAZOR_TOOTH = createDamageSource("razorTooth");

    public static DamageSource causeBoneArrowDamage(Entity source, @Nullable Entity indirectEntityIn) {
        return (new IndirectEntityDamageSource(EmortisConstants.MOD_ID + ".boneArrow", source, indirectEntityIn)).setProjectile();
    }

    private static DamageSource createDamageSource(String name) {
        return new DamageSource(EmortisConstants.MOD_ID + "." + name);
    }
}