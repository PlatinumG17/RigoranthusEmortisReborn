package com.platinumg17.rigoranthusemortisreborn.magica.common.spell.method;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.AbstractAugment;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.AbstractCastMethod;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.SpellContext;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.SpellResolver;
import com.platinumg17.rigoranthusemortisreborn.magica.common.lib.GlyphLib;
import com.platinumg17.rigoranthusemortisreborn.magica.common.entity.EntityProjectileSpell;
import com.platinumg17.rigoranthusemortisreborn.magica.common.spell.augment.AugmentAccelerate;
import com.platinumg17.rigoranthusemortisreborn.magica.common.spell.augment.AugmentPierce;
import com.platinumg17.rigoranthusemortisreborn.magica.common.spell.augment.AugmentSensitive;
import com.platinumg17.rigoranthusemortisreborn.magica.common.spell.augment.AugmentSplit;
import net.minecraft.command.arguments.EntityAnchorArgument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Items;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MethodProjectile extends AbstractCastMethod {
    public static MethodProjectile INSTANCE = new MethodProjectile();

    private MethodProjectile() {
        super(GlyphLib.MethodProjectileID, "Projectile");
    }

    @Override
    public int getDominionCost() {
        return 10;
    }

    public void summonProjectiles(World world, LivingEntity shooter, List<AbstractAugment> augments, SpellResolver resolver){
        ArrayList<EntityProjectileSpell> projectiles = new ArrayList<>();
        EntityProjectileSpell projectileSpell = new EntityProjectileSpell(world, resolver);
        projectiles.add(projectileSpell);
        int numSplits = getBuffCount(augments, AugmentSplit.class);

        for(int i =1; i < numSplits + 1; i++){
            Direction offset =shooter.getDirection().getClockWise();
            if(i%2==0) offset = offset.getOpposite();
            // Alternate sides
            BlockPos projPos = shooter.blockPosition().relative(offset, i);
            projPos = projPos.offset(0, 1.5, 0);
            EntityProjectileSpell spell = new EntityProjectileSpell(world, resolver);
            spell.setPos(projPos.getX(), projPos.getY(), projPos.getZ());
            projectiles.add(spell);
        }
        float velocity = 1.0f + getBuffCount(augments, AugmentAccelerate.class);

        for(EntityProjectileSpell proj : projectiles) {
            proj.shoot(shooter, shooter.xRot, shooter.yRot, 0.0F, velocity, 0.8f);
            world.addFreshEntity(proj);
        }
    }

    // Summons the projectiles directly above the block, facing downwards.
    public void summonProjectiles(World world, BlockPos pos, LivingEntity shooter, List<AbstractAugment> augments, SpellResolver resolver){
        ArrayList<EntityProjectileSpell> projectiles = new ArrayList<>();
        EntityProjectileSpell projectileSpell = new EntityProjectileSpell(world, resolver);
        projectileSpell.setPos(pos.getX(), pos.getY() + 1, pos.getZ());
        projectiles.add(projectileSpell);

        int numSplits = getBuffCount(augments, AugmentSplit.class);

        for(int i =1; i < numSplits + 1; i++){
            Direction offset = shooter.getDirection().getClockWise();
            if(i%2==0) offset = offset.getOpposite();
            // Alternate sides
            BlockPos projPos = pos.relative(offset, i);
            projPos = projPos.offset(0, 1.5, 0);
            EntityProjectileSpell spell = new EntityProjectileSpell(world, resolver);
            spell.setPos(projPos.getX(), projPos.getY(), projPos.getZ());
            projectiles.add(spell);
        }
        for(EntityProjectileSpell proj : projectiles) {
            proj.setDeltaMovement(new Vector3d(0, -0.1, 0));
            world.addFreshEntity(proj);
        }
    }

    @Override
    public void onCast(ItemStack stack, LivingEntity shooter, World world, List<AbstractAugment> augments, SpellContext context, SpellResolver resolver) {
        summonProjectiles(world, shooter, augments, resolver);
        resolver.expendDominion(shooter);
    }

    @Override
    public void onCastOnBlock(ItemUseContext context, List<AbstractAugment> augments, SpellContext spellContext, SpellResolver resolver) {
        World world = context.getLevel();
        PlayerEntity shooter = context.getPlayer();
        summonProjectiles(world, shooter, augments, resolver);
        resolver.expendDominion(shooter);
    }

    /**
     * Cast by entities.
     */
    @Override
    public void onCastOnBlock(BlockRayTraceResult blockRayTraceResult, LivingEntity caster, List<AbstractAugment> augments, SpellContext spellContext, SpellResolver resolver) {
        caster.lookAt(EntityAnchorArgument.Type.EYES, blockRayTraceResult.getLocation().add(0, 0, 0));
        summonProjectiles(caster.getCommandSenderWorld(), blockRayTraceResult.getBlockPos(), caster, augments, resolver);
        resolver.expendDominion(caster);
    }

    @Override
    public void onCastOnEntity(ItemStack stack, LivingEntity caster, Entity target, Hand hand, List<AbstractAugment> augments, SpellContext spellContext, SpellResolver resolver) {
        summonProjectiles(caster.getCommandSenderWorld(), caster, augments, resolver);
        resolver.expendDominion(caster);
    }

    @Override
    public boolean wouldCastSuccessfully(@Nullable ItemStack stack, LivingEntity playerEntity, World world, List<AbstractAugment> augments, SpellResolver resolver) {
        return true;
    }

    @Override
    public boolean wouldCastOnBlockSuccessfully(ItemUseContext context, List<AbstractAugment> augments, SpellResolver resolver) {
        return true;
    }

    @Override
    public boolean wouldCastOnBlockSuccessfully(BlockRayTraceResult blockRayTraceResult, LivingEntity caster, List<AbstractAugment> augments, SpellResolver resolver) {
        return true;
    }

    @Override
    public boolean wouldCastOnEntitySuccessfully(@Nullable ItemStack stack, LivingEntity caster, Entity target, Hand hand, List<AbstractAugment> augments, SpellResolver resolver) {
        return true;
    }

    @Nonnull
    @Override
    public Set<AbstractAugment> getCompatibleAugments() {
        return augmentSetOf(AugmentPierce.INSTANCE, AugmentSplit.INSTANCE, AugmentAccelerate.INSTANCE, AugmentSensitive.INSTANCE);
    }

    @Override
    public String getBookDescription() {
        return "A spell you start with. Summons a projectile that applies spell effects when this projectile hits a target or block. Sensitive will allow Projectiles to break plants or other materials that do not block motion.";
    }

    @Override
    public Item getCraftingReagent() {
        return Items.BOW;
    }

    @Override
    public boolean defaultedStarterGlyph() {
        return true;
    }
}