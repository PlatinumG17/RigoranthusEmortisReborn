package com.platinumg17.rigoranthusemortisreborn.magica.common.spell.effect;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.*;
import com.platinumg17.rigoranthusemortisreborn.canis.CanisItems;
import com.platinumg17.rigoranthusemortisreborn.magica.common.lib.GlyphLib;
import com.platinumg17.rigoranthusemortisreborn.magica.common.entity.ModEntities;
import com.platinumg17.rigoranthusemortisreborn.magica.common.entity.SummonWolf;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeConfigSpec;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;

public class EffectSummonWolves extends AbstractEffect {
    public static EffectSummonWolves INSTANCE = new EffectSummonWolves();

    private EffectSummonWolves() {
        super(GlyphLib.EffectSummonWolvesID, "Summon Wolves");
    }

    @Override
    public void onResolve(RayTraceResult rayTraceResult, World world, @Nullable LivingEntity shooter, SpellStats spellStats, SpellContext spellContext) {
        if(!canSummon(shooter))
            return;
        Vector3d hit = rayTraceResult.getLocation();
        int ticks = (int) (20 * (GENERIC_INT.get() + EXTEND_TIME.get() * spellStats.getDurationMultiplier()));
        for(int i = 0; i < 2; i++){
            SummonWolf wolf = new SummonWolf(ModEntities.SUMMON_WOLF, world);
            wolf.ticksLeft = ticks;
            wolf.setPos(hit.x(), hit.y(), hit.z());
            wolf.setTarget(shooter.getLastHurtMob());
            wolf.setAggressive(true);
            wolf.setTame(true);
            wolf.tame((PlayerEntity) shooter);
            summonLivingEntity(rayTraceResult, world, shooter, spellStats, spellContext, wolf);
        }
        applySummoningSickness(shooter, ticks);
    }

    @Override
    public void buildConfig(ForgeConfigSpec.Builder builder) {
        super.buildConfig(builder);
        addGenericInt(builder, 60, "Base duration in seconds", "duration");
        addExtendTimeConfig(builder, 60);
    }

    @Override
    public int getDominionCost() {
        return 100;
    }

    @Nonnull
    @Override
    public Set<AbstractAugment> getCompatibleAugments() {
        // SummonEvent captures augments, but no uses of that field were found
        return SUMMON_AUGMENTS;
    }

    @Override
    public String getBookDescription() {
        return "Summons two wolves that will fight with you. Extend Time will increase the amount of time on the summons. Applies a Summoning Cooldown to the caster, preventing summoning other Servants.";
    }

    @Override
    public Tier getTier() {
        return Tier.ONE;
    }

    @Nullable
    @Override
    public Item getCraftingReagent() {
        return CanisItems.BIG_BONE.get();
    }

    @Nonnull
    @Override
    public Set<SpellSchool> getSchools() {
        return setOf(SpellSchools.CONJURATION);
    }
}