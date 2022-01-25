package com.platinumg17.rigoranthusemortisreborn.magica.common.spell.method;

import com.platinumg17.rigoranthusemortisreborn.magica.GlyphLib;
import com.platinumg17.rigoranthusemortisreborn.magica.common.network.Networking;
import com.platinumg17.rigoranthusemortisreborn.magica.common.network.PacketREEffect;
import com.platinumg17.rigoranthusemortisreborn.magica.common.spell.augment.AugmentSensitive;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.AbstractAugment;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.AbstractCastMethod;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.SpellContext;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.SpellResolver;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

public class MethodTouch extends AbstractCastMethod {
    public static MethodTouch INSTANCE = new MethodTouch();

    private MethodTouch() {
        super(GlyphLib.MethodTouchID, "Touch");
    }

    @Override
    public int getDominionCost() {
        return 5;
    }

    @Override
    public void onCast(ItemStack stack, LivingEntity caster, World world, List<AbstractAugment> augments, SpellContext context, SpellResolver resolver) { }

    @Override
    public void onCastOnBlock(ItemUseContext context, List<AbstractAugment> augments, SpellContext spellContext, SpellResolver resolver) {
        World world = context.getLevel();
        BlockRayTraceResult res = new BlockRayTraceResult(context.getClickLocation(), context.getClickedFace(), context.getClickedPos(), false);
        resolver.onResolveEffect(world, context.getPlayer(), res);
        resolver.expendDominion(context.getPlayer());
        Networking.sendToNearby(context.getLevel(), context.getPlayer(),
                new PacketREEffect(PacketREEffect.EffectType.BURST, res.getBlockPos(), spellContext.colors));
    }

    @Override
    public void onCastOnBlock(BlockRayTraceResult res, LivingEntity caster, List<AbstractAugment> augments, SpellContext spellContext, SpellResolver resolver) {
        resolver.onResolveEffect(caster.getCommandSenderWorld(),caster, res);
        resolver.expendDominion(caster);
        Networking.sendToNearby(caster.level, caster, new PacketREEffect(PacketREEffect.EffectType.BURST, res.getBlockPos(), spellContext.colors));
    }

    @Override
    public void onCastOnEntity(ItemStack stack, LivingEntity caster, Entity target, Hand hand, List<AbstractAugment> augments, SpellContext spellContext, SpellResolver resolver) {
        resolver.onResolveEffect(caster.getCommandSenderWorld(), caster, new EntityRayTraceResult(target));
        if(spellContext.getType() != SpellContext.CasterType.RUNE)
            resolver.expendDominion(caster);
        Networking.sendToNearby(caster.level, caster, new PacketREEffect(PacketREEffect.EffectType.BURST, target.blockPosition(), spellContext.colors));
    }

    @Override
    public boolean wouldCastSuccessfully(@Nullable ItemStack stack, LivingEntity playerEntity, World world, List<AbstractAugment> augments, SpellResolver resolver) {
        return false;
    }

    @Override
    public boolean wouldCastOnBlockSuccessfully(ItemUseContext context, List<AbstractAugment> augments, SpellResolver resolver) {
        World world = context.getLevel();
        BlockRayTraceResult res = new BlockRayTraceResult(context.getClickLocation(), context.getClickedFace(), context.getClickedPos(), false);
        return resolver.wouldAllEffectsDoWork(res, world, context.getPlayer(), augments);
    }

    @Override
    public boolean wouldCastOnBlockSuccessfully(BlockRayTraceResult blockRayTraceResult, LivingEntity caster, List<AbstractAugment> augments, SpellResolver resolver) {
        return resolver.wouldAllEffectsDoWork(blockRayTraceResult, caster.getCommandSenderWorld(), caster, augments);
    }

    @Override
    public boolean wouldCastOnEntitySuccessfully(@Nullable ItemStack stack, LivingEntity caster, Entity target, Hand hand, List<AbstractAugment> augments, SpellResolver resolver) {
        return resolver.wouldAllEffectsDoWork(new EntityRayTraceResult(target), caster.getCommandSenderWorld(), caster, augments);
    }

    @Nonnull
    @Override
    public Set<AbstractAugment> getCompatibleAugments() {
        return augmentSetOf(AugmentSensitive.INSTANCE);
    }

    @Override
    public String getBookDescription() {
        return "Applies spells at the block or entity that is targeted.";
    }


    @Override
    public Item getCraftingReagent() {
        return Items.STONE_BUTTON;
    }

    @Override
    public boolean defaultedStarterGlyph() {
        return true;
    }
}