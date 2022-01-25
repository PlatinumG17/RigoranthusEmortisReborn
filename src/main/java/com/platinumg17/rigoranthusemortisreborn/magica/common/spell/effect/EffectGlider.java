package com.platinumg17.rigoranthusemortisreborn.magica.common.spell.effect;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.*;
import com.platinumg17.rigoranthusemortisreborn.magica.common.lib.GlyphLib;
import com.platinumg17.rigoranthusemortisreborn.magica.common.potions.ModPotions;
import com.platinumg17.rigoranthusemortisreborn.magica.common.spell.augment.AugmentDurationDown;
import com.platinumg17.rigoranthusemortisreborn.magica.common.spell.augment.AugmentExtendTime;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.interfaces.ISpellTier;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeConfigSpec;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;

public class EffectGlider extends AbstractEffect {

    public static EffectGlider INSTANCE = new EffectGlider();

    private EffectGlider() {
        super(GlyphLib.EffectGliderID, "Glider");
    }

    @Override
    public void onResolveEntity(EntityRayTraceResult rayTraceResult, World world, @Nullable LivingEntity shooter, SpellStats spellStats, SpellContext spellContext) {
        if(rayTraceResult.getEntity() instanceof LivingEntity){
            applyConfigPotion(((LivingEntity) rayTraceResult.getEntity()), ModPotions.GLIDER_EFFECT, spellStats);
        }
    }

    @Override
    public void buildConfig(ForgeConfigSpec.Builder builder) {
        super.buildConfig(builder);
        addPotionConfig(builder, 180);
        addExtendTimeConfig(builder, 120);
    }

    @Override
    public int getDominionCost() {
        return 100;
    }

    @Override
    public String getBookDescription() {
        return "Grants the Glider effect, allowing the wearer to fly as if they were wearing an Elytra. ";
    }

    @Override
    public Tier getTier() {
        return ISpellTier.Tier.THREE;
    }

    @Nullable
    @Override
    public Item getCraftingReagent() {
        return Items.ELYTRA;
    }

    @Nonnull
    @Override
    public Set<AbstractAugment> getCompatibleAugments() {
        return augmentSetOf(AugmentExtendTime.INSTANCE, AugmentDurationDown.INSTANCE);
    }

    @Nonnull
    @Override
    public Set<SpellSchool> getSchools() {
        return setOf(SpellSchools.ELEMENTAL_AIR);
    }
}