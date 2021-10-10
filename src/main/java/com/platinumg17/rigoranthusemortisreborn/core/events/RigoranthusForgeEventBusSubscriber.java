//package com.platinumg17.rigoranthusemortisreborn.core.events;
//
//import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
//import com.platinumg17.rigoranthusemortisreborn.core.init.Registration;
//import com.platinumg17.rigoranthusemortisreborn.core.registry.effects.RigoranthusEffectRegistry;
//import fictioncraft.wintersteve25.fclib.api.events.PlayerInventoryChangedEvent;
//import net.minecraft.entity.LivingEntity;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.entity.player.ServerPlayerEntity;
//import net.minecraft.item.ItemStack;
//import net.minecraft.potion.EffectInstance;
//import net.minecraft.potion.Effects;
//import net.minecraft.world.World;
//import net.minecraftforge.event.TickEvent;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//import net.minecraftforge.fml.common.Mod;
//
//@Mod.EventBusSubscriber(modid = RigoranthusEmortisReborn.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
//public class RigoranthusForgeEventBusSubscriber {
//
//    @SubscribeEvent
//    public static void playerInventoryChangedEvent(PlayerInventoryChangedEvent inventoryChangedEvent) {
//        ServerPlayerEntity player = inventoryChangedEvent.getServerPlayer();
//        ItemStack boots = player.inventory.getArmor(0).getStack();
//        ItemStack leggings = player.inventory.getArmor(1).getStack();
//        ItemStack breastplate = player.inventory.getArmor(2).getStack();
//        ItemStack helmet = player.inventory.getArmor(3).getStack();
//        inventoryChangedEvent.getServerPlayer();
////        if (!(Config.enableNetheriteAdditions.get())) {
//            if (!(boots.isEmpty() && leggings.isEmpty() && breastplate.isEmpty() && helmet.isEmpty())) {
//                if (boots.getItem() == Registration.APOGEAN_NETHERITE_BOOTS && leggings.getItem() == Registration.APOGEAN_NETHERITE_LEGGINGS &&
//                        breastplate.getItem() == Registration.APOGEAN_NETHERITE_CHESTPLATE && helmet.getItem() == Registration.APOGEAN_NETHERITE_HELMET) {
//                    hasFullApogean = true;
//                    return;
//                }
//                if (boots.getItem() == Registration.AQUEOUS_NETHERITE_BOOTS && leggings.getItem() == Registration.AQUEOUS_NETHERITE_LEGGINGS &&
//                        breastplate.getItem() == Registration.AQUEOUS_NETHERITE_CHESTPLATE && helmet.getItem() == Registration.AQUEOUS_NETHERITE_HELMET) {
//                    hasFullAqueous = true;
//                    return;
//                }
//                if (boots.getItem() == Registration.ATROPHYING_NETHERITE_BOOTS && leggings.getItem() == Registration.ATROPHYING_NETHERITE_LEGGINGS &&
//                        breastplate.getItem() == Registration.ATROPHYING_NETHERITE_CHESTPLATE && helmet.getItem() == Registration.ATROPHYING_NETHERITE_HELMET) {
//                    hasFullAtrophying = true;
//                    return;
//                }
//                if (boots.getItem() == Registration.INCORPOREAL_NETHERITE_BOOTS && leggings.getItem() == Registration.INCORPOREAL_NETHERITE_LEGGINGS &&
//                        breastplate.getItem() == Registration.INCORPOREAL_NETHERITE_CHESTPLATE && helmet.getItem() == Registration.INCORPOREAL_NETHERITE_HELMET) {
//                    hasFullIncorporeal = true;
//                    return;
//                }
//                if (boots.getItem() == Registration.INFERNAL_NETHERITE_BOOTS && leggings.getItem() == Registration.INFERNAL_NETHERITE_LEGGINGS &&
//                        breastplate.getItem() == Registration.INFERNAL_NETHERITE_CHESTPLATE && helmet.getItem() == Registration.INFERNAL_NETHERITE_HELMET) {
//                    hasFullInfernal = true;
//                    return;
//                }
//                if (boots.getItem() == Registration.OPULENT_NETHERITE_BOOTS && leggings.getItem() == Registration.OPULENT_NETHERITE_LEGGINGS &&
//                        breastplate.getItem() == Registration.OPULENT_NETHERITE_CHESTPLATE && helmet.getItem() == Registration.OPULENT_NETHERITE_HELMET) {
//                    hasFullOpulent = true;
//                    return;
//                }
//                if (boots.getItem() == Registration.PERNICIOUS_NETHERITE_BOOTS && leggings.getItem() == Registration.PERNICIOUS_NETHERITE_LEGGINGS &&
//                        breastplate.getItem() == Registration.PERNICIOUS_NETHERITE_CHESTPLATE && helmet.getItem() == Registration.PERNICIOUS_NETHERITE_HELMET) {
//                    hasFullPernicious = true;
//                    return;
//                }
//                if (boots.getItem() == Registration.PHANTASMAL_NETHERITE_BOOTS && leggings.getItem() == Registration.PHANTASMAL_NETHERITE_LEGGINGS &&
//                        breastplate.getItem() == Registration.PHANTASMAL_NETHERITE_CHESTPLATE && helmet.getItem() == Registration.PHANTASMAL_NETHERITE_HELMET) {
//                    hasFullPhantasmal = true;
//                    return;
//                }
//                if (boots.getItem() == Registration.REMEX_NETHERITE_BOOTS && leggings.getItem() == Registration.REMEX_NETHERITE_LEGGINGS &&
//                        breastplate.getItem() == Registration.REMEX_NETHERITE_CHESTPLATE && helmet.getItem() == Registration.REMEX_NETHERITE_HELMET) {
//                    hasFullRemex = true;
//                    return;
//                }
//            }
//            hasFullApogean = false;
//            hasFullAqueous = false;
//            hasFullAtrophying = false;
//            hasFullIncorporeal = false;
//            hasFullInfernal = false;
//            hasFullOpulent = false;
//            hasFullPernicious = false;
//            hasFullPhantasmal = false;
//            hasFullRemex = false;
// //       }
//    }
//    public static boolean isHasFullApogean(LivingEntity player) {
//        return player.hasEffect(RigoranthusEffectRegistry.APOGEAN_SET_BONUS);
//    }
//    public static boolean isHasFullAqueous(LivingEntity player) {
//        return player.hasEffect(RigoranthusEffectRegistry.AQUEOUS_SET_BONUS);
//    }
//    public static boolean isHasFullAtrophying(LivingEntity player) {
//        return player.hasEffect(RigoranthusEffectRegistry.ATROPHYING_SET_BONUS);
//    }
//    public static boolean isHasFullPernicious(LivingEntity player) {
//        return player.hasEffect(RigoranthusEffectRegistry.PERNICIOUS_SET_BONUS);
//    }
//    public static boolean isHasFullPhantasmal(LivingEntity player) {
//        return player.hasEffect(RigoranthusEffectRegistry.PHANTASMAL_SET_BONUS);
//    }
//    public static boolean isHasFullOpulent(LivingEntity player) {
//        return player.hasEffect(RigoranthusEffectRegistry.OPULENT_SET_BONUS);
//    }
//    public static boolean isHasFullIncorporeal(LivingEntity player) {
//        return player.hasEffect(RigoranthusEffectRegistry.INCORPOREAL_SET_BONUS);
//    }
//    public static boolean isHasFullInfernal(LivingEntity player) {
//        return player.hasEffect(RigoranthusEffectRegistry.INFERNAL_SET_BONUS);
//    }
//    public static boolean isHasFullRemex(LivingEntity player) {
//        return player.hasEffect(RigoranthusEffectRegistry.REMEX_SET_BONUS);
//    }
//    private static boolean hasFullApogean = false;
//    private static boolean hasFullAqueous = false;
//    private static boolean hasFullAtrophying = false;
//    private static boolean hasFullIncorporeal = false;
//    private static boolean hasFullInfernal = false;
//    private static boolean hasFullOpulent = false;
//    private static boolean hasFullPernicious = false;
//    private static boolean hasFullPhantasmal = false;
//    private static boolean hasFullRemex = false;
//
//    @SubscribeEvent
//    public static void onArmorTick(LivingEntity player) {
//        if (hasFullApogean) {
//            player.addEffect(new EffectInstance(RigoranthusEffectRegistry.APOGEAN_SET_BONUS, 400, 1));
//        }
//
//        if (hasFullAqueous) {
//            player.addEffect(new EffectInstance(RigoranthusEffectRegistry.AQUEOUS_SET_BONUS, 400, 1));
//        }
//
//        if (hasFullAtrophying) {
//            player.addEffect(new EffectInstance(RigoranthusEffectRegistry.ATROPHYING_SET_BONUS, 400, 1));
//        }
//
//        if (hasFullIncorporeal) {
//            player.addEffect(new EffectInstance(RigoranthusEffectRegistry.INCORPOREAL_SET_BONUS, 400, 1));
//        }
//
//        if (hasFullInfernal) {
//            player.addEffect(new EffectInstance(RigoranthusEffectRegistry.INFERNAL_SET_BONUS, 400, 1));
//        }
//
//        if (hasFullOpulent) {
//            player.addEffect(new EffectInstance(RigoranthusEffectRegistry.OPULENT_SET_BONUS, 400, 1));
//        }
//
//        if (hasFullPernicious) {
//            player.addEffect(new EffectInstance(RigoranthusEffectRegistry.PERNICIOUS_SET_BONUS, 400, 1));
//        }
//
//        if (hasFullPhantasmal) {
//            player.addEffect(new EffectInstance(RigoranthusEffectRegistry.PHANTASMAL_SET_BONUS, 400, 1));
//        }
//
//        if (hasFullRemex) {
//            player.addEffect(new EffectInstance(RigoranthusEffectRegistry.REMEX_SET_BONUS, 400, 1));
//        }
//    }
//
//    @SubscribeEvent
//    public static void playerTick(TickEvent.PlayerTickEvent tickEvent) {
//        PlayerEntity player = tickEvent.player;
//        World world = player.getCommandSenderWorld();
//        if (!world.isClientSide()) {
//            if (hasFullApogean) {
//                player.addEffect(new EffectInstance(RigoranthusEffectRegistry.APOGEAN_SET_BONUS, 400, 1));
//            }
//
//            if (hasFullAqueous) {
//                player.addEffect(new EffectInstance(RigoranthusEffectRegistry.AQUEOUS_SET_BONUS, 400, 1));
//            }
//
//            if (hasFullAtrophying) {
//                player.addEffect(new EffectInstance(RigoranthusEffectRegistry.ATROPHYING_SET_BONUS, 400, 1));
//            }
//
//            if (hasFullIncorporeal) {
//                player.addEffect(new EffectInstance(RigoranthusEffectRegistry.INCORPOREAL_SET_BONUS, 400, 1));
//            }
//
//            if (hasFullInfernal) {
//                player.addEffect(new EffectInstance(RigoranthusEffectRegistry.INFERNAL_SET_BONUS, 400, 1));
//            }
//
//            if (hasFullOpulent) {
//                player.addEffect(new EffectInstance(RigoranthusEffectRegistry.OPULENT_SET_BONUS, 400, 1));
//            }
//
//            if (hasFullPernicious) {
//                player.addEffect(new EffectInstance(RigoranthusEffectRegistry.PERNICIOUS_SET_BONUS, 400, 1));
//            }
//
//            if (hasFullPhantasmal) {
//                player.addEffect(new EffectInstance(RigoranthusEffectRegistry.PHANTASMAL_SET_BONUS, 400, 1));
//            }
//
//            if (hasFullRemex) {
//                player.addEffect(new EffectInstance(RigoranthusEffectRegistry.REMEX_SET_BONUS, 400, 1));
//            }
//        }
//    }
//}