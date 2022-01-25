package com.platinumg17.rigoranthusemortisreborn.magica.common.spell.effect;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.*;
import com.platinumg17.rigoranthusemortisreborn.magica.common.lib.GlyphLib;
import com.platinumg17.rigoranthusemortisreborn.magica.common.potions.ModPotions;
import com.platinumg17.rigoranthusemortisreborn.magica.common.spell.augment.AugmentExtendTime;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeConfigSpec;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

public class EffectSnare extends AbstractEffect {
    public static EffectSnare INSTANCE = new EffectSnare();

    private EffectSnare() {
        super(GlyphLib.EffectSnareID, "Snare");
    }

    @Override
    public void onResolveEntity(EntityRayTraceResult rayTraceResult, World world, @Nullable LivingEntity shooter, SpellStats spellStats, SpellContext spellContext) {
        Entity livingEntity = rayTraceResult.getEntity();
        if(!(livingEntity instanceof LivingEntity))
            return;

        ((LivingEntity)livingEntity).addEffect(new EffectInstance(ModPotions.SNARE_EFFECT, (int) (POTION_TIME.get() * 20  + 20 * EXTEND_TIME.get() * spellStats.getDurationMultiplier()), 20));
        livingEntity.setDeltaMovement(0,0,0);
        livingEntity.hurtMarked = true;
    }

    @Override
    public void buildConfig(ForgeConfigSpec.Builder builder) {
        super.buildConfig(builder);
        addPotionConfig(builder, 8);
        addExtendTimeConfig(builder, 1);
    }

    @Override
    public boolean wouldSucceed(RayTraceResult rayTraceResult, World world, LivingEntity shooter, List<AbstractAugment> augments) {
        return livingEntityHitSuccess(rayTraceResult);
    }

    @Nonnull
    @Override
    public Set<AbstractAugment> getCompatibleAugments() {
        return augmentSetOf(AugmentExtendTime.INSTANCE);
    }

    @Override
    public String getBookDescription() {
        return "Stops entities from moving and jumping. Extend Time will increase the duration of this effect.";
    }

    @Override
    public Item getCraftingReagent() {
        return Items.COBWEB;
    }

    @Override
    public int getDominionCost() {
        return 100;
    }

    @Nonnull
    @Override
    public Set<SpellSchool> getSchools() {
        return setOf(SpellSchools.ELEMENTAL_EARTH);
    }
}