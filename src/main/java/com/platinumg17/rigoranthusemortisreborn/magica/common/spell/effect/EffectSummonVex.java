package com.platinumg17.rigoranthusemortisreborn.magica.common.spell.effect;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.*;
import com.platinumg17.rigoranthusemortisreborn.magica.common.lib.GlyphLib;
import com.platinumg17.rigoranthusemortisreborn.magica.common.entity.EntityAllyVex;
import com.platinumg17.rigoranthusemortisreborn.magica.common.potions.ModPotions;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeConfigSpec;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;

public class EffectSummonVex extends AbstractEffect {
    public static EffectSummonVex INSTANCE = new EffectSummonVex();

    private EffectSummonVex() {
        super(GlyphLib.EffectSummonVexID, "Summon Vex");
    }

    @Override
    public void onResolve(RayTraceResult rayTraceResult, World world, @Nullable LivingEntity shooter, SpellStats spellStats, SpellContext spellContext) {
        if(!canSummon(shooter))
            return;

        Vector3d vector3d = safelyGetHitPos(rayTraceResult);
        int ticks = (int) (20 * (GENERIC_INT.get() + EXTEND_TIME.get() * spellStats.getDurationMultiplier()));
        BlockPos pos = new BlockPos(vector3d);

        for(int i = 0; i < 3; ++i) {
            BlockPos blockpos = pos.offset(-2 + shooter.getRandom().nextInt(5), 2, -2 + shooter.getRandom().nextInt(5));
            EntityAllyVex vexentity = new EntityAllyVex(world, shooter);
            vexentity.moveTo(blockpos, 0.0F, 0.0F);
            vexentity.finalizeSpawn((IServerWorld) world, world.getCurrentDifficultyAt(blockpos), SpawnReason.MOB_SUMMONED, null, null);
            vexentity.setOwner(shooter);
            vexentity.setBoundOrigin(blockpos);
            vexentity.setLimitedLife(ticks);
            world.addFreshEntity(vexentity);
        }
        shooter.addEffect(new EffectInstance(ModPotions.SUMMONING_COOLDOWN, ticks));
    }

    @Override
    public void buildConfig(ForgeConfigSpec.Builder builder) {
        super.buildConfig(builder);
        addGenericInt(builder, 15, "Base duration in seconds", "duration");
        addExtendTimeConfig(builder, 10);
    }

    @Override
    public int getDominionCost() {
        return 150;
    }

    @Nullable
    @Override
    public Item getCraftingReagent() {
        return Items.TOTEM_OF_UNDYING;
    }

    @Override
    public Tier getTier() {
        return Tier.THREE;
    }

    @Nonnull
    @Override
    public Set<AbstractAugment> getCompatibleAugments() {
        return SUMMON_AUGMENTS;
    }

    @Override
    public String getBookDescription() {
        return "Summons three Vex allies that will attack nearby hostile enemies. These Vex will last a short time until they begin to take damage, but time may be extended with the " +
                "Extend Time augment.";
    }

    @Nonnull
    @Override
    public Set<SpellSchool> getSchools() {
        return setOf(SpellSchools.CONJURATION);
    }
}