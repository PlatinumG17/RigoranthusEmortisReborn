package com.platinumg17.rigoranthusemortisreborn.core.registry.effects.weapons;

import com.platinumg17.rigoranthusemortisreborn.items.itemeffects.ItemRightClickEffect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class PropulsionEffect implements ItemRightClickEffect {
    public static final PropulsionEffect SELF_PROPEL = new PropulsionEffect(3);//, EnumAspect.PROPEL);

    private final double velocity;
//    private final EnumAspect aspect;

    public PropulsionEffect(double velocity) {         //, EnumAspect aspect) {
        this.velocity = velocity;
//        this.aspect = aspect;
    }

    @Override
    public ActionResult<ItemStack> onRightClick(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        propulsionAction(player, itemStack, getVelocityMod(), hand);
        return ActionResult.pass(itemStack);
    }

    private double getVelocityMod() {return velocity;}

    void propulsionAction(PlayerEntity player, ItemStack stack, double velocity, Hand hand) {
//        Title title = null;
//        if(player.level.isClientSide) {
//            title = ClientPlayerData.getTitle();
//        if(player instanceof ServerPlayerEntity) {
//            title = PlayerSavedData.getData((ServerPlayerEntity) player).getTitle();
            if(player.getCooldowns().getCooldownPercent(stack.getItem(), 1F) <= 0 && (player.isCreative()))
                propulsionActionSound(player.level, player);
//        }

        if(player.isCreative()) {
            Vector3d lookVec = player.getLookAngle().scale(velocity);
            if(player.isFallFlying()) {
                lookVec = lookVec.scale(velocity / 12D);
            }
            player.push(lookVec.x, lookVec.y * 0.4D, lookVec.z);

            player.swing(hand, true);
            player.getCooldowns().addCooldown(stack.getItem(), 100);
            stack.hurtAndBreak(4, player, playerEntity -> playerEntity.broadcastBreakEvent(Hand.MAIN_HAND));
        }
    }

    void propulsionActionSound(World world, PlayerEntity player) {
        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.TRIDENT_RIPTIDE_2, SoundCategory.PLAYERS, 1.75F, 1.6F);
    }
}