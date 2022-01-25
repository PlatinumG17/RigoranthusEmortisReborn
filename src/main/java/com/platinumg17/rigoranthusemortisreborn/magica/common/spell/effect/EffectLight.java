package com.platinumg17.rigoranthusemortisreborn.magica.common.spell.effect;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.entity.REFakePlayer;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.*;
import com.platinumg17.rigoranthusemortisreborn.magica.common.lib.GlyphLib;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.LightTile;
import com.platinumg17.rigoranthusemortisreborn.magica.common.spell.augment.AugmentAmplify;
import com.platinumg17.rigoranthusemortisreborn.magica.common.spell.augment.AugmentDampen;
import com.platinumg17.rigoranthusemortisreborn.magica.common.spell.augment.AugmentDurationDown;
import com.platinumg17.rigoranthusemortisreborn.magica.common.spell.augment.AugmentExtendTime;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.BlockRegistry;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.interfaces.ILightable;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.util.BlockUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeConfigSpec;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;

public class EffectLight extends AbstractEffect {
    public static EffectLight INSTANCE = new EffectLight();

    private EffectLight() {
        super(GlyphLib.EffectLightID, "Summon Light");
    }

    @Override
    public void onResolveEntity(EntityRayTraceResult rayTraceResult, World world, @Nullable LivingEntity shooter, SpellStats spellStats, SpellContext spellContext) {
        if(rayTraceResult.getEntity() instanceof ILightable){
            ((ILightable) rayTraceResult.getEntity()).onLight(rayTraceResult, world, shooter, spellStats, spellContext);
        }
        if(!(rayTraceResult.getEntity() instanceof LivingEntity))
            return;
        if (shooter == null || !shooter.equals(rayTraceResult.getEntity())) {
            applyConfigPotion((LivingEntity) rayTraceResult.getEntity(), Effects.GLOWING, spellStats);
        }
        applyConfigPotion((LivingEntity) rayTraceResult.getEntity(), Effects.NIGHT_VISION, spellStats);
    }

    @Override
    public void onResolveBlock(BlockRayTraceResult rayTraceResult, World world, @Nullable LivingEntity shooter, SpellStats spellStats, SpellContext spellContext) {
        BlockPos pos = rayTraceResult.getBlockPos().relative(rayTraceResult.getDirection());
        if(!BlockUtil.destroyRespectsClaim(getPlayer(shooter, (ServerWorld) world), world, pos))
            return;

        if(world.getBlockEntity( rayTraceResult.getBlockPos()) instanceof ILightable){
            ((ILightable) world.getBlockEntity(rayTraceResult.getBlockPos())).onLight(rayTraceResult, world, shooter, spellStats, spellContext);
            return;
        }
        if (world.getBlockState(pos).getMaterial().isReplaceable() && world.isUnobstructed(BlockRegistry.LIGHT_BLOCK.defaultBlockState(), pos, ISelectionContext.of(REFakePlayer.getPlayer((ServerWorld) world)))) {
//            world.setBlockAndUpdate(pos, BlockRegistry.LIGHT_BLOCK.defaultBlockState().setValue(SconceBlock.LIGHT_LEVEL, Math.max(0,Math.min(15, 14 + (int) spellStats.getAmpMultiplier()))));
            LightTile tile = ((LightTile)world.getBlockEntity(pos));
            if(tile != null){
                tile.red = spellContext.colors.r;
                tile.green = spellContext.colors.g;
                tile.blue = spellContext.colors.b;
            }
        }
    }

    @Override
    public void buildConfig(ForgeConfigSpec.Builder builder) {
        super.buildConfig(builder);
        addPotionConfig(builder, 30);
    }

    @Override
    public boolean dampenIsAllowed() {
        return true;
    }

    @Override
    public int getDominionCost() {
        return 25;
    }

    @Nullable
    @Override
    public Item getCraftingReagent(){return Items.LANTERN;}

    @Nonnull
    @Override
    public Set<AbstractAugment> getCompatibleAugments() {
        return augmentSetOf(AugmentAmplify.INSTANCE, AugmentDurationDown.INSTANCE, AugmentDampen.INSTANCE, AugmentExtendTime.INSTANCE);
    }

    @Override
    public String getBookDescription() {
        return "If cast on a block, a permanent light source is created. May be amplified up to Glowstone brightness, or Dampened for a lower light level. When cast on yourself, you will receive night vision. When cast on other entities, they will receive Night Vision and Glowing.";
    }

    @Nonnull
    @Override
    public Set<SpellSchool> getSchools() {
        return setOf(SpellSchools.CONJURATION);
    }
}