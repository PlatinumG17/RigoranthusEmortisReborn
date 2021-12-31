package com.platinumg17.rigoranthusemortisreborn.api.apimagic.piratesbooty;

import com.platinumg17.rigoranthusemortisreborn.api.RigoranthusEmortisRebornAPI;
import com.platinumg17.rigoranthusemortisreborn.core.init.ItemInit;
import com.platinumg17.rigoranthusemortisreborn.magica.common.datagen.DungeonLootGenerator;
import com.platinumg17.rigoranthusemortisreborn.magica.common.items.RitualOffering;
import com.platinumg17.rigoranthusemortisreborn.magica.common.potions.ModPotions;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.BlockRegistry;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.MagicItemsRegistry;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.interfaces.ISpellCaster;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.Spell;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.SpellCaster;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class LootTables {

    public static List<Supplier<ItemStack>> BASIC_LOOT = new ArrayList<>();
    public static List<Supplier<ItemStack>> UNCOMMON_LOOT = new ArrayList<>();
    public static List<Supplier<ItemStack>> RARE_LOOT = new ArrayList<>();
    public static Random r = new Random();
    static {
        BASIC_LOOT.add(() -> new ItemStack(MagicItemsRegistry.dominionGem,1 + r.nextInt(5)));
        BASIC_LOOT.add(() -> new ItemStack(ItemInit.BONE_FRAGMENT.get(),1 + r.nextInt(3)));
        BASIC_LOOT.add(() -> new ItemStack(BlockRegistry.DOMINION_BERRY_BUSH, 1 + r.nextInt(3)));
        BASIC_LOOT.add(() ->{
            ItemStack stack = new ItemStack(Items.POTION);
            PotionUtils.setPotion(stack, ModPotions.LONG_DOMINION_REGEN_POTION);
            return stack;
        });
        BASIC_LOOT.add(() ->{
            ItemStack stack = new ItemStack(Items.POTION);
            PotionUtils.setPotion(stack, ModPotions.STRONG_DOMINION_REGEN_POTION);
            return stack;
        });
        BASIC_LOOT.add(() ->{
            ItemStack stack = new ItemStack(Items.POTION);
            PotionUtils.setPotion(stack, ModPotions.DOMINION_REGEN_POTION);
            return stack;
        });
        UNCOMMON_LOOT.add(() -> new ItemStack(MagicItemsRegistry.BONE_ARROW, 16 + r.nextInt(16)));

        UNCOMMON_LOOT.add(() ->{
            List<RitualOffering> offerings = new ArrayList<>(RigoranthusEmortisRebornAPI.getInstance().getRitualItemMap().values());
            return new ItemStack(offerings.get(r.nextInt(offerings.size())));
        });

//        RARE_LOOT.add(() -> makeTome("Xacris' Tiny Hut", new Spell()
//                        .add(MethodUnderfoot.INSTANCE)
//                        .add(EffectPhantomBlock.INSTANCE)
//                        .add(AugmentAOE.INSTANCE, 3)
//                        .add(AugmentPierce.INSTANCE, 3),
//                "Builds a small hut around the user."
//        ));
//        RARE_LOOT.add(() -> makeTome("Glow Trap", new Spell()
//                        .add(MethodTouch.INSTANCE)
//                        .add(EffectRune.INSTANCE)
//                        .add(EffectSnare.INSTANCE)
//                        .add(AugmentExtendTime.INSTANCE)
//                        .add(EffectLight.INSTANCE),
//                "Snares the target and grants other targets Glowing."
//        ));
//        RARE_LOOT.add(() -> makeTome("Bailey's Bovine Rocket", new Spell()
//                        .add(MethodProjectile.INSTANCE)
//                        .add(EffectLaunch.INSTANCE)
//                        .add(AugmentAmplify.INSTANCE, 2)
//                        .add(EffectDelay.INSTANCE)
//                        .add(EffectExplosion.INSTANCE)
//                        .add(AugmentAmplify.INSTANCE)
//        ));
//        RARE_LOOT.add(() -> makeTome("Arachne's Weaving", new Spell()
//                        .add(MethodProjectile.INSTANCE)
//                        .add(AugmentSplit.INSTANCE, 2)
//                        .add(EffectSnare.INSTANCE )
//                        .add(AugmentExtendTime.INSTANCE)
//                        .add(AugmentExtendTime.INSTANCE),
//                "Creates three snaring projectiles."
//        ));
//        RARE_LOOT.add(() -> makeTome("Warp Impact", new Spell()
//                        .add(MethodProjectile.INSTANCE)
//                        .add(EffectBlink.INSTANCE)
//                        .add(EffectExplosion.INSTANCE )
//                        .add(AugmentAOE.INSTANCE),
//                "Teleportation, with style!"
//        ));
//        RARE_LOOT.add(() -> makeTome("Farfalla's Frosty Flames", new Spell()
//                        .add(MethodProjectile.INSTANCE)
//                        .add(EffectIgnite.INSTANCE)
//                        .add(EffectDelay.INSTANCE )
//                        .add(EffectConjureWater.INSTANCE)
//                        .add(EffectFreeze.INSTANCE),
//                "Creates a fire that quickly freezes to ice."
//        ));
//        RARE_LOOT.add(() -> makeTome("Gootastic's Telekinetic Fishing Rod", new Spell()
//                        .add(MethodProjectile.INSTANCE)
//                        .add(EffectLaunch.INSTANCE)
//                        .add(AugmentAmplify.INSTANCE,2)
//                        .add(EffectDelay.INSTANCE)
//                        .add(EffectPull.INSTANCE)
//                        .add(AugmentAmplify.INSTANCE,2),
//                "The squid's Lovecraftian roots appear to make it immune."
//        ));
//        RARE_LOOT.add(() -> makeTome("Potent Toxin", new Spell()
//                        .add(MethodProjectile.INSTANCE)
//                        .add(EffectHex.INSTANCE)
//                        .add(EffectHarm.INSTANCE)
//                        .add(AugmentExtendTime.INSTANCE),
//                "Poisons that target and causes them to take additional damage from all sources."
//        ));
//        RARE_LOOT.add(() -> makeTome("The Shadow's Temporary Tunnel", new Spell()
//                        .add(MethodTouch.INSTANCE)
//                        .add(EffectIntangible.INSTANCE)
//                        .add(AugmentAOE.INSTANCE, 2)
//                        .add(AugmentPierce.INSTANCE, 5)
//                        .add(AugmentExtendTime.INSTANCE),
//                "Creates a temporary tunnel of blocks."
//        ));
//        RARE_LOOT.add(() -> makeTome("Vault", new Spell()
//                        .add(MethodSelf.INSTANCE)
//                        .add(EffectLaunch.INSTANCE)
//                        .add(EffectDelay.INSTANCE)
//                        .add(EffectLeap.INSTANCE)
//                        .add(EffectSlowfall.INSTANCE),
//                "Sometimes you just need to get over that wall."
//        ));
//        RARE_LOOT.add(() -> makeTome("Fireball!", new Spell()
//                        .add(MethodProjectile.INSTANCE)
//                        .add(EffectIgnite.INSTANCE)
//                        .add(EffectExplosion.INSTANCE)
//                        .add(AugmentAmplify.INSTANCE, 2)
//                        .add(AugmentAOE.INSTANCE, 2),
//                "A classic."
//        ));
//        RARE_LOOT.add(() -> makeTome("Rune of Renewing", new Spell()
//                        .add(MethodTouch.INSTANCE)
//                        .add(EffectRune.INSTANCE)
//                        .add(EffectDispel.INSTANCE)
//                        .add(EffectHeal.INSTANCE)
//                        .add(AugmentExtendTime.INSTANCE),
//                "Cures status effects and grants regeneration."
//        ));
//        RARE_LOOT.add(() -> makeTome("Knocked out of Orbit", new Spell()
//                        .add(MethodOrbit.INSTANCE)
//                        .add(EffectLaunch.INSTANCE)
//                        .add(AugmentAmplify.INSTANCE, 2)
//                        .add(EffectDelay.INSTANCE)
//                        .add(EffectKnockback.INSTANCE)
//                        .add(AugmentAmplify.INSTANCE, 2),
//                "Summons orbiting projectiles that will launch nearby enemies.")
//        );
//        RARE_LOOT.add(() -> makeTome("Takeoff!", new Spell().add(MethodSelf.INSTANCE)
//                        .add(EffectLaunch.INSTANCE, 2)
//                        .add(EffectGlider.INSTANCE)
//                        .add(AugmentDurationDown.INSTANCE),
//                "Launches the caster into the air and grants temporary elytra flight!"
//        ));
    }

    public static ItemStack getRandomItem(List<Supplier<ItemStack>> pool){
        return pool.isEmpty() ? ItemStack.EMPTY : pool.get(r.nextInt(pool.size())).get();
    }

    public static List<ItemStack> getRandomRoll(DungeonLootGenerator.DungeonLootEnhancerModifier modifier){
        List<ItemStack> stacks = new ArrayList<>();

        for(int i = 0; i < modifier.commonRolls; i++){
            if(r.nextDouble() <= modifier.commonChance)
                stacks.add(getRandomItem(BASIC_LOOT));
        }
        for(int i = 0; i < modifier.uncommonRolls; i++){
            if(r.nextDouble() <= modifier.uncommonChance)
                stacks.add(getRandomItem(UNCOMMON_LOOT));
        }
        for(int i = 0; i < modifier.rareRolls; i++){
            if(r.nextDouble() <= modifier.rareChance)
                stacks.add(getRandomItem(RARE_LOOT));
        }
        return stacks;
    }

    public static ItemStack makeTome(String name, Spell spell){
        ItemStack stack = new ItemStack(MagicItemsRegistry.CASTER_TOME);
        ISpellCaster spellCaster = SpellCaster.deserialize(stack);
        spellCaster.setSpell(spell);
        stack.setHoverName(new StringTextComponent(name).setStyle(Style.EMPTY.withColor(TextFormatting.DARK_PURPLE).withItalic(true)));
        return stack;
    }

    public static ItemStack makeTome(String name, Spell spell, String flavorText){
        ItemStack stack = makeTome(name, spell);
        ISpellCaster spellCaster = SpellCaster.deserialize(stack);
        spellCaster.setFlavorText(flavorText);
        return stack;
    }
}