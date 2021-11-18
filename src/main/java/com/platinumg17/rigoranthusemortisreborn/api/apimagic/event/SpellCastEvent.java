package com.platinumg17.rigoranthusemortisreborn.api.apimagic.event;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.AbstractSpellPart;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.Spell;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.SpellContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.Cancelable;

import java.util.List;

@Cancelable
public class SpellCastEvent extends LivingEvent {

    public Spell spell;
    public SpellContext context;

    @Deprecated // Marked for removal.
    public SpellCastEvent(LivingEntity entity, List<AbstractSpellPart> spell){
        super(entity);
        this.spell = new Spell(spell);
        context = new SpellContext(spell, entity);
    }

    @Deprecated
    public SpellCastEvent(LivingEntity entity, Spell spell){
        super(entity);
        this.spell = spell;
        context = new SpellContext(spell, entity);
    }

    public SpellCastEvent(LivingEntity entity, Spell spell, SpellContext context){
        super(entity);
        this.spell = spell;
        this.context = context;
    }

    public World getWorld(){
        return this.getEntityLiving().level;
    }
}