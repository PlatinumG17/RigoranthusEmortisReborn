package com.platinumg17.rigoranthusemortisreborn.items.itemeffects;

import com.platinumg17.rigoranthusemortisreborn.core.init.network.REPacketHandler;
import com.platinumg17.rigoranthusemortisreborn.core.registry.RigoranthusSoundRegistry;
import com.platinumg17.rigoranthusemortisreborn.core.registry.effects.weapons.MagicEffectPacket;
import com.platinumg17.rigoranthusemortisreborn.util.MagicHitTargetUtil;
import net.minecraft.command.arguments.EntityAnchorArgument;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.PacketDistributor;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class MagicAttackRightClickEffect implements ItemRightClickEffect {
    private final int distance;
    private final int damage;
    private final Supplier<EffectInstance> effect;
    private final Supplier<SoundEvent> sound;
    private final float pitch;
    @Nullable
    private final MagicHitTargetUtil.Type type;

    private static final EntityPredicate visiblePredicate = (new EntityPredicate());//.setLineOfSiteRequired(); TODO should something else be done with the predicate?

    public static final MagicAttackRightClickEffect NOVICE_MAGIC = new SbahjMagicEffect(10, 1, null, null, 1.0F, MagicHitTargetUtil.Type.GREEN);
    public static final MagicAttackRightClickEffect TYRO_MAGIC = new AimbotMagicEffect(14, 2, null, null, 1.0F, MagicHitTargetUtil.Type.CRIT);
    public static final MagicAttackRightClickEffect PROHIBATE_MAGIC = new MagicAttackRightClickEffect(15, 3, null, null, 1.0F, MagicHitTargetUtil.Type.ENCHANT);
    public static final MagicAttackRightClickEffect SPECTIVE_MAGIC = new MagicAttackRightClickEffect(18, 4, null, null, 1.0F, MagicHitTargetUtil.Type.RED);
    public static final MagicAttackRightClickEffect CIRCEAN_MAGIC = new MagicAttackRightClickEffect(30, 8, null, null, 1.0F, MagicHitTargetUtil.Type.CIRCEAN);
    public static final MagicAttackRightClickEffect APOGEAN_MAGIC = new MagicAttackRightClickEffect(50, 9, null, null, 1.0F, MagicHitTargetUtil.Type.APOGEE);
    public static final MagicAttackRightClickEffect PSYCHO_BLIGHT_MAGIC = new MagicAttackRightClickEffect(20, 5, () -> new EffectInstance(
            Effects.WITHER, 100, 0), () -> RigoranthusSoundRegistry.ITEM_GRIMOIRE_USE.get(), 1.2F, MagicHitTargetUtil.Type.INK);

    protected MagicAttackRightClickEffect(int distance, int damage, Supplier<EffectInstance> effect, Supplier<SoundEvent> sound, float pitch, @Nullable MagicHitTargetUtil.Type type) {
        this.distance = distance;
        this.damage = damage;
        this.effect = effect;
        this.sound = sound;
        this.pitch = pitch;
        this.type = type;
    }

    @Override
    public ActionResult<ItemStack> onRightClick(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStackIn = player.getItemInHand(hand);

        if(player instanceof ServerPlayerEntity)
            magicAttack(world, (ServerPlayerEntity) player);

        if(player.isCreative())
            player.getCooldowns().addCooldown(itemStackIn.getItem(), 10);
        else
            player.getCooldowns().addCooldown(itemStackIn.getItem(), 50);

        player.swing(hand, true);
        itemStackIn.hurtAndBreak(6, player, playerEntity -> playerEntity.broadcastBreakEvent(Hand.MAIN_HAND));
        player.awardStat(Stats.ITEM_USED.get(itemStackIn.getItem()));

        return ActionResult.success(itemStackIn);
    }

    private void magicAttack(World world, ServerPlayerEntity player) {
        if(sound != null && player.getRandom().nextFloat() < .1F) //optional sound effect adding
            world.playSound(null, player.getX(), player.getY(), player.getZ(), sound.get(), SoundCategory.PLAYERS, 0.7F, pitch);
        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.EVOKER_CAST_SPELL, SoundCategory.PLAYERS, 1.0F, 1.6F);

        targetEffect(player);

        Vector3d eyePos = player.getEyePosition(1.0F);
        Vector3d lookVec = player.getLookAngle();
//  Uses the float i value to increase the distance away from where the player is looking and creating a sort of raytrace
        for(int step = 0; step < distance * 2; step++) {
            Vector3d vecPos = eyePos.add(lookVec.scale(step / 2D));

            boolean hitObstacle = checkCollisionInPath(world, player, vecPos);

            if(hitObstacle) {
                sendEffectPacket(world, eyePos, lookVec, step, true);
                return;
            }
        }
        sendEffectPacket(world, eyePos, lookVec, distance * 2, false);
    }

    protected void sendEffectPacket(World world, Vector3d pos, Vector3d lookVec, int length, boolean collides) {
        if(type != null)
            REPacketHandler.sendToNear(new MagicEffectPacket(type, pos, lookVec, length, collides),
                    new PacketDistributor.TargetPoint(pos.x, pos.y, pos.z, 64, world.dimension()));
    }

    protected void targetEffect(ServerPlayerEntity player) {}

    private boolean checkCollisionInPath(World world, ServerPlayerEntity player, Vector3d vecPos) {
        BlockPos blockPos = new BlockPos(vecPos);

        if(!world.getBlockState(blockPos).isPathfindable(world, blockPos, PathType.LAND)) {
            return true;
        }

        AxisAlignedBB axisAlignedBB = new AxisAlignedBB(blockPos);
        // gets entities in a bounding box around each vector position in the for loop
        LivingEntity closestTarget = player.level.getNearestEntity(LivingEntity.class, visiblePredicate, player, vecPos.x, vecPos.y, vecPos.z, axisAlignedBB);
        if(closestTarget != null) {
//            int playerLevel = PlayerSavedData.getData(player).getAspectProgress().getLevel();
//
//            if(closestTarget instanceof MonsterEntity)
//                closestTarget.hurt(DamageSource.playerAttack(player).setMagic(), damage + playerLevel / 5F);
//            else
                closestTarget.hurt(DamageSource.playerAttack(player).setMagic(), damage/* + playerLevel / 10F*/);
            if(effect != null && player.getRandom().nextFloat() < .25F)
                closestTarget.addEffect(effect.get());

            return true;
        } else return false;
    }

    private static class SbahjMagicEffect extends MagicAttackRightClickEffect {
        SbahjMagicEffect(int distance, int damage, Supplier<EffectInstance> effect, Supplier<SoundEvent> sound, float pitch, @Nullable MagicHitTargetUtil.Type type) {
            super(distance, damage, effect, sound, pitch, type);
        }

        @Override
        protected void targetEffect(ServerPlayerEntity player) {
            Vector3d randomFacingVecPos = new Vector3d(player.getX() + player.getRandom().nextInt(10) - 5, player.getY() + player.getRandom().nextInt(10) - 5, player.getZ() + player.getRandom().nextInt(10) - 5);
            player.lookAt(player.createCommandSourceStack().getAnchor(), randomFacingVecPos);
        }
    }

    private static class AimbotMagicEffect extends MagicAttackRightClickEffect
    {
        AimbotMagicEffect(int distance, int damage, Supplier<EffectInstance> effect, Supplier<SoundEvent> sound, float pitch, @Nullable MagicHitTargetUtil.Type type) {
            super(distance, damage, effect, sound, pitch, type);
        }

        @Override
        protected void targetEffect(ServerPlayerEntity player) {
            BlockPos playerEyePos = new BlockPos(player.getX(), player.getEyeY(), player.getZ());
            LivingEntity closestVisibleTarget = player.level.getNearestEntity(LivingEntity.class, visiblePredicate, player, player.getX(), player.getEyeY(), player.getZ(), new AxisAlignedBB(playerEyePos).inflate(11));
            if(closestVisibleTarget != null)
                player.lookAt(EntityAnchorArgument.Type.EYES, closestVisibleTarget, EntityAnchorArgument.Type.EYES);
        }
    }
}