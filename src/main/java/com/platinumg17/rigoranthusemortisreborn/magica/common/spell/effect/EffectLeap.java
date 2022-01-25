package com.platinumg17.rigoranthusemortisreborn.magica.common.spell.effect;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.*;
import com.platinumg17.rigoranthusemortisreborn.core.init.ItemInit;
import com.platinumg17.rigoranthusemortisreborn.magica.common.lib.GlyphLib;
import com.platinumg17.rigoranthusemortisreborn.magica.common.spell.augment.AugmentAmplify;
import com.platinumg17.rigoranthusemortisreborn.magica.common.spell.augment.AugmentDampen;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeConfigSpec;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

public class EffectLeap extends AbstractEffect {
    public static EffectLeap INSTANCE = new EffectLeap();

    private EffectLeap() {
        super(GlyphLib.EffectLeapID, "Leap");
    }

    @Override
    public void onResolveEntity(EntityRayTraceResult rayTraceResult, World world, @Nullable LivingEntity shooter, SpellStats spellStats, SpellContext spellContext) {
        if(rayTraceResult.getEntity() instanceof LivingEntity){
            LivingEntity e = (LivingEntity) rayTraceResult.getEntity();
            double bonus = /*GENERIC_DOUBLE.get() + AMP_VALUE.get()*/2 * spellStats.getAmpMultiplier();
            e.setDeltaMovement(e.getLookAngle().x * bonus, e.getLookAngle().y * bonus, e.getLookAngle().z * bonus);
            e.fallDistance = 0;
            e.hurtMarked = true;
        }
    }

    @Override
    public void buildConfig(ForgeConfigSpec.Builder builder) {
        super.buildConfig(builder);
        addGenericDouble(builder, 1.5, "Base knockup amount", "knock_up");
        addAmpConfig(builder, 1.0);
    }

    @Override
    public boolean wouldSucceed(RayTraceResult rayTraceResult, World world, LivingEntity shooter, List<AbstractAugment> augments) {
        return livingEntityHitSuccess(rayTraceResult);
    }

    @Nonnull
    @Override
    public Set<AbstractAugment> getCompatibleAugments() {
        return augmentSetOf(AugmentAmplify.INSTANCE, AugmentDampen.INSTANCE);
    }

    @Override
    public String getBookDescription() {
        return "Launches the target in the direction they are looking. Amplification will increase the distance moved.";
    }

    @Override
    public Item getCraftingReagent() {
        return ItemInit.IRON_SLIME_BALL.get();
    }

    @Override
    public int getDominionCost() {
        return 25;
    }

    @Nonnull
    @Override
    public Set<SpellSchool> getSchools() {
        return setOf(SpellSchools.ELEMENTAL_AIR);
    }
}