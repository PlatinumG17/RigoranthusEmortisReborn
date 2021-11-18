package com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.interfaces;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.SpellContext;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.SpellStats;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public interface ILightable {
    /**
     * Called when a light spell is cast on this block or entity.
     */
    void onLight(RayTraceResult rayTraceResult, World world, LivingEntity shooter, SpellStats augments, SpellContext spellContext);
}