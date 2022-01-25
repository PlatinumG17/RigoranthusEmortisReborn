package com.platinumg17.rigoranthusemortisreborn.magica.common.spell.effect;

import com.platinumg17.rigoranthusemortisreborn.api.RigoranthusEmortisRebornAPI;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.*;
import com.platinumg17.rigoranthusemortisreborn.magica.common.lib.GlyphLib;
import com.platinumg17.rigoranthusemortisreborn.magica.common.potions.ModPotions;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeConfigSpec;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;

public class EffectHex extends AbstractEffect {
    public static EffectHex INSTANCE = new EffectHex();

    private EffectHex() {
        super(GlyphLib.EffectHexID, "Hex");
    }

    @Override
    public void onResolveEntity(EntityRayTraceResult rayTraceResult, World world, @Nullable LivingEntity shooter, SpellStats spellStats, SpellContext spellContext) {
        Entity entity = rayTraceResult.getEntity();
        if(!(entity instanceof LivingEntity))
            return;
        applyPotionWithCap((LivingEntity) entity, ModPotions.HEX_EFFECT, spellStats, POTION_TIME.get(), EXTEND_TIME.get(), 4);
    }

    @Override
    public void buildConfig(ForgeConfigSpec.Builder builder) {
        super.buildConfig(builder);
        addPotionConfig(builder, 30);
        addExtendTimeConfig(builder, 8);
    }

    @Nonnull
    @Override
    public Set<AbstractAugment> getCompatibleAugments() {
        return POTION_AUGMENTS;
    }

    @Override
    public String getBookDescription() {
        return "Applies the Hex effect up to level 5. Hex increases any damage taken by a small amount while the user is afflicted by poison, wither, or fire. Additionally, Hex cuts the rate of Dominion Regeneration and healing in half.";
    }

    @Nullable
    @Override
    public Item getCraftingReagent() {
        return RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(EffectWither.INSTANCE);
    }

    @Override
    public Tier getTier() {
        return Tier.THREE;
    }

    @Override
    public int getDominionCost() {
        return 100;
    }

    @Nonnull
    @Override
    public Set<SpellSchool> getSchools() {
        return setOf(SpellSchools.ABJURATION);
    }
}