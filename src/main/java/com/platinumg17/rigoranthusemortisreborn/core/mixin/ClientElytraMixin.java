package com.platinumg17.rigoranthusemortisreborn.core.mixin;

//import com.platinumg17.rigoranthusemortisreborn.magica.common.potions.ModPotions;
//import net.minecraft.client.entity.player.ClientPlayerEntity;
//import net.minecraft.entity.LivingEntity;
//import net.minecraft.item.ItemStack;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Redirect;
//
//@Mixin(ClientPlayerEntity.class)
//public class ClientElytraMixin {
//
//    @Redirect(
//            method = "aiStep",
//            at = @At(
//                    value = "INVOKE",
//                    target = "Lnet/minecraft/item/ItemStack;canElytraFly(Lnet/minecraft/entity/LivingEntity;)Z",
//                    remap = false
//            )
//    )
//    public boolean elytraOverride(ItemStack stack, LivingEntity entity) {
//        return entity.getEffect(ModPotions.GLIDER_EFFECT) != null || stack.canElytraFly(entity);
//    }
//}