package com.platinumg17.rigoranthusemortisreborn.api.apimagic.event;

import com.platinumg17.rigoranthusemortisreborn.magica.ITimedEvent;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.Spell;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.SpellContext;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.SpellResolver;
import com.platinumg17.rigoranthusemortisreborn.magica.client.particle.ParticleUtil;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class DelayedSpellEvent implements ITimedEvent {
    private int duration;
    private final Spell spell;
    private final SpellContext context;
    private final RayTraceResult result;
    private final World world;
    private final @Nullable LivingEntity shooter;

    public DelayedSpellEvent(int delay, Spell spell, RayTraceResult result, World world, @Nullable LivingEntity shooter, SpellContext context){
        this.duration = delay;
        this.spell = spell;
        this.result = result;
        this.world = world;
        this.shooter = shooter;
        this.context = context;
    }

    @Override
    public void tick(boolean serverSide) {
        duration--;
        if(duration <= 0 && serverSide){
            resolveSpell();
        }else if(!serverSide && result != null){
            BlockPos hitVec = result instanceof EntityRayTraceResult ? ((EntityRayTraceResult) result).getEntity().blockPosition() : new BlockPos(result.getLocation());
            ParticleUtil.spawnTouch((ClientWorld) world, hitVec, context.colors.toParticleColor());
        }
    }

    public void resolveSpell(){
        if(world == null)
            return;
        SpellResolver.resolveEffects(world, shooter, result, spell, context);
    }
    @Override public boolean isExpired() {
        return duration <= 0 || world == null;
    }
}