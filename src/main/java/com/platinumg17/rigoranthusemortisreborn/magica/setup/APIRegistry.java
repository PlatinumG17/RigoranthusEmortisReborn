package com.platinumg17.rigoranthusemortisreborn.magica.setup;

import com.platinumg17.rigoranthusemortisreborn.api.RigoranthusEmortisRebornAPI;
import com.platinumg17.rigoranthusemortisreborn.magica.common.spell.augment.*;
import com.platinumg17.rigoranthusemortisreborn.magica.common.spell.effect.*;
import com.platinumg17.rigoranthusemortisreborn.magica.common.spell.method.*;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.entity.familiar.AbstractFamiliarHolder;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.AbstractSpellPart;

public class APIRegistry {

//    public static void registerApparatusRecipes() {
//        registerApparatusRecipe(new ReactiveEnchantmentRecipe(new ItemStack[]{new ItemStack(MagicItemsRegistry.spellParchment),
//                new ItemStack(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(GlyphLib.AugmentAmplifyID)),
//                new ItemStack(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(GlyphLib.AugmentAmplifyID)),
//                new ItemStack(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(GlyphLib.AugmentAmplifyID))}, 3000));
//
//        registerApparatusRecipe(new SpellWriteRecipe());
//    }

//    public static void registerApparatusRecipe(IEnchantingRecipe recipe) {
//        RigoranthusEmortisRebornAPI.getInstance().getEnchantingApparatusRecipes().add(recipe);
//    }

    public static void registerSpells() {
        registerSpell(MethodProjectile.INSTANCE);
        registerSpell(MethodTouch.INSTANCE);
        registerSpell(MethodSelf.INSTANCE);
        registerSpell(EffectBreak.INSTANCE);
        registerSpell(EffectHarm.INSTANCE);
        registerSpell(EffectIgnite.INSTANCE);
        registerSpell(EffectPhantomBlock.INSTANCE);
        registerSpell(EffectHeal.INSTANCE);
        registerSpell(EffectGrow.INSTANCE);
        registerSpell(EffectKnockback.INSTANCE);
        registerSpell(EffectHaste.INSTANCE);
        registerSpell(EffectLight.INSTANCE);
        registerSpell(EffectDispel.INSTANCE);
        registerSpell(EffectFreeze.INSTANCE);
        registerSpell(EffectLaunch.INSTANCE);
        registerSpell(EffectPull.INSTANCE);
        registerSpell(EffectBlink.INSTANCE);
        registerSpell(EffectExplosion.INSTANCE);
        registerSpell(EffectLightning.INSTANCE);
        registerSpell(EffectSlowfall.INSTANCE);
        registerSpell(EffectShield.INSTANCE);
        registerSpell(EffectAquatic.INSTANCE);
        registerSpell(EffectFangs.INSTANCE);
        registerSpell(EffectSummonVex.INSTANCE);
        registerSpell(EffectStrength.INSTANCE);
        registerSpell(AugmentAccelerate.INSTANCE);
        registerSpell(AugmentSplit.INSTANCE);
        registerSpell(AugmentAmplify.INSTANCE);
        registerSpell(AugmentAOE.INSTANCE);
        registerSpell(AugmentExtendTime.INSTANCE);
        registerSpell(AugmentPierce.INSTANCE);
        registerSpell(AugmentDampen.INSTANCE);
        registerSpell(AugmentExtract.INSTANCE);
        registerSpell(AugmentFortune.INSTANCE);
        registerSpell(EffectEnderChest.INSTANCE);
        registerSpell(EffectHarvest.INSTANCE);
        registerSpell(EffectFell.INSTANCE);
        registerSpell(EffectPickup.INSTANCE);
        registerSpell(EffectInteract.INSTANCE);
        registerSpell(EffectPlaceBlock.INSTANCE);
        registerSpell(EffectSnare.INSTANCE);
        registerSpell(EffectSmelt.INSTANCE);
        registerSpell(EffectLeap.INSTANCE);
        registerSpell(EffectDelay.INSTANCE);
        registerSpell(EffectRedstone.INSTANCE);
        registerSpell(EffectIntangible.INSTANCE);
        registerSpell(EffectInvisibility.INSTANCE);
        registerSpell(AugmentDurationDown.INSTANCE);
        registerSpell(EffectWither.INSTANCE);
        registerSpell(EffectExchange.INSTANCE);
        registerSpell(EffectCraft.INSTANCE);
        registerSpell(EffectFlare.INSTANCE);
        registerSpell(EffectColdSnap.INSTANCE);
        registerSpell(EffectConjureWater.INSTANCE);
        registerSpell(EffectGravity.INSTANCE);
        registerSpell(EffectCut.INSTANCE);
        registerSpell(EffectCrush.INSTANCE);
        registerSpell(EffectSummonWolves.INSTANCE);
        registerSpell(EffectSummonSteed.INSTANCE);
        registerSpell(EffectSummonDecoy.INSTANCE);
        registerSpell(EffectHex.INSTANCE);
        registerSpell(MethodUnderfoot.INSTANCE);
        registerSpell(EffectGlider.INSTANCE);
        registerSpell(MethodOrbit.INSTANCE);
//        registerSpell(EffectRune.INSTANCE);
//        registerRitual(new RitualDig());
//        registerRitual(new RitualMoonfall());
//        registerRitual(new RitualCloudshaper());
//        registerRitual(new RitualSunrise());
//        registerRitual(new RitualDisintegration());
//        registerRitual(new RitualPillagerRaid());
//        registerRitual(new RitualOvergrowth());
//        registerRitual(new RitualBreed());
//        registerRitual(new RitualHealing());
//        registerRitual(new RitualWarp());
//        registerRitual(new ScryingRitual());
//        registerRitual(new RitualFlight());
//        registerRitual(new RitualWildenSummoning());
//        registerRitual(new RitualBinding());
//        registerRitual(new RitualAwakening());
//        registerFamiliar(new CarbuncleFamiliar());
//        registerFamiliar(new DrygmyFamiliar());
//        registerFamiliar(new SylphFamiliar());
//        registerFamiliar(new WixieFamiliar());
//        registerFamiliar(new BookwyrmFamiliar());

        registerSpell(EffectFirework.INSTANCE);
        registerSpell(EffectToss.INSTANCE);
        registerSpell(EffectBounce.INSTANCE);
        registerSpell(AugmentSensitive.INSTANCE);
        registerSpell(EffectWindshear.INSTANCE);
        registerSpell(EffectEvaporate.INSTANCE);
        registerSpell(EffectLinger.INSTANCE);
    }

    public static void registerFamiliar(AbstractFamiliarHolder familiar){
        RigoranthusEmortisRebornAPI.getInstance().registerFamiliar(familiar);
    }

    public static void registerSpell(AbstractSpellPart spellPart) {
        RigoranthusEmortisRebornAPI.getInstance().registerSpell(spellPart.getTag(), spellPart);
    }

//    public static void registerRitual(AbstractRitual ritual){
//        RigoranthusEmortisRebornAPI.getInstance().registerRitual(ritual.getID(), ritual);
//    }

    public static void registerSpell(String id, AbstractSpellPart spellPart) {
        RigoranthusEmortisRebornAPI.getInstance().registerSpell(id, spellPart);
    }

    private APIRegistry() {
    }
}