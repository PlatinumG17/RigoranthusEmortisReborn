package com.platinumg17.rigoranthusemortisreborn.api.apimagic.event;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.AbstractAugment;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.SpellContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.eventbus.api.Event;

import javax.annotation.Nullable;
import java.util.List;

public class DispelEvent extends Event {
    public RayTraceResult rayTraceResult;
    public World world;
    public LivingEntity shooter;
    public List<AbstractAugment> augments;
    public SpellContext context;

    public DispelEvent(RayTraceResult rayTraceResult, World world, @Nullable LivingEntity shooter, List<AbstractAugment> augments, SpellContext spellContext){
        this.rayTraceResult = rayTraceResult;
        this.world = world;
        this.shooter = shooter;
        this.augments = augments;
        this.context = spellContext;
    }
}