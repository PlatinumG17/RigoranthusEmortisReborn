package com.platinumg17.rigoranthusemortisreborn.core.events;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.core.init.ItemInit;
import com.platinumg17.rigoranthusemortisreborn.core.init.Registration;
import com.platinumg17.rigoranthusemortisreborn.core.registry.effects.RigoranthusEffectRegistry;
import com.platinumg17.rigoranthusemortisreborn.entity.RigoranthusEntityTypes;
import com.platinumg17.rigoranthusemortisreborn.entity.mobs.CanisChordataEntity;
import com.platinumg17.rigoranthusemortisreborn.entity.mobs.LanguidDwellerEntity;
import com.platinumg17.rigoranthusemortisreborn.entity.mobs.NecrawFasciiEntity;
import com.platinumg17.rigoranthusemortisreborn.entity.mobs.SunderedCadaverEntity;
import com.platinumg17.rigoranthusemortisreborn.fluid.CadaverousIchorFluid;
import com.platinumg17.rigoranthusemortisreborn.items.RigoranthusSpawnEgg;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.*;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

@Mod.EventBusSubscriber(modid = RigoranthusEmortisReborn.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RigoranthusEventBusEvents {
    @SubscribeEvent
    public static void addEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(RigoranthusEntityTypes.CANIS_CHORDATA.get(), CanisChordataEntity.setCustomAttributes().build());
        event.put(RigoranthusEntityTypes.SUNDERED_CADAVER.get(), SunderedCadaverEntity.setCustomAttributes().build());
        event.put(RigoranthusEntityTypes.NECRAW_FASCII.get(), NecrawFasciiEntity.setCustomAttributes().build());
        event.put(RigoranthusEntityTypes.LANGUID_DWELLER.get(), LanguidDwellerEntity.setCustomAttributes().build());
    }

    @SubscribeEvent
    public static void onRegisterEntities(RegistryEvent.Register<EntityType<?>> event) {
        RigoranthusSpawnEgg.initSpawnEggs();
    }

    @SubscribeEvent
    public void onPlayerAttackEntityEvent(AttackEntityEvent event) {
        if (event.getPlayer().getItemBySlot(EquipmentSlotType.HEAD).getItem() == Registration.INCORPOREAL_NETHERITE_HELMET && event.getTarget() instanceof LivingEntity) {
            float f1 = 2;
            ((LivingEntity) event.getTarget()).knockback(f1 * 0.5F, MathHelper.sin(event.getPlayer().yRot * ((float) Math.PI / 180F)), -MathHelper.cos(event.getPlayer().yRot * ((float) Math.PI / 180F)));
        }
    }


    @SubscribeEvent
    public void onUseItem(PlayerInteractEvent.RightClickItem event) {
        if (event.getItemStack().getItem() == Items.GLASS_BOTTLE) { // && Config.ichorBottleEnabled) {
            RayTraceResult raytraceresult = rayTrace(event.getWorld(), event.getPlayer(), RayTraceContext.FluidMode.SOURCE_ONLY);
            if (raytraceresult.getType() == RayTraceResult.Type.ENTITY) {
                BlockPos blockpos = ((BlockRayTraceResult) raytraceresult).getBlockPos();
                if (event.getWorld().mayInteract(event.getPlayer(), blockpos)) {
                    if (event.getWorld().getFluidState(blockpos).getType().equals(CadaverousIchorFluid.CADAVEROUS_ICHOR_BLOCK.get())) {
                        event.getWorld().playSound(event.getPlayer(), event.getPlayer().getX(), event.getPlayer().getY(), event.getPlayer().getZ(), SoundEvents.BOTTLE_FILL, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                        event.getPlayer().awardStat(Stats.ITEM_USED.get(Items.GLASS_BOTTLE));
                        //event.getPlayer().setSecondsOnFire(6);
                        if (!event.getPlayer().addItem(new ItemStack(ItemInit.BOTTLE_OF_ICHOR.get()))) {
                            event.getPlayer().spawnAtLocation(new ItemStack(ItemInit.BOTTLE_OF_ICHOR.get()));
                        }
                        event.getPlayer().swing(event.getHand());
                        if (!event.getPlayer().isCreative()) {
                            event.getItemStack().shrink(1);
                        }
                    }
                }
            }
        }
    }

    protected static BlockRayTraceResult rayTrace(World worldIn, PlayerEntity player, RayTraceContext.FluidMode fluidMode) {
        float f = player.xRot;
        float f1 = player.yRot;
        Vector3d vector3d = player.getEyePosition(1.0F);
        float f2 = MathHelper.cos(-f1 * ((float) Math.PI / 180F) - (float) Math.PI);
        float f3 = MathHelper.sin(-f1 * ((float) Math.PI / 180F) - (float) Math.PI);
        float f4 = -MathHelper.cos(-f * ((float) Math.PI / 180F));
        float f5 = MathHelper.sin(-f * ((float) Math.PI / 180F));
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        double d0 = player.getAttribute(net.minecraftforge.common.ForgeMod.REACH_DISTANCE.get()).getValue();
        Vector3d vector3d1 = vector3d.add((double) f6 * d0, (double) f5 * d0, (double) f7 * d0);
        return worldIn.clip(new RayTraceContext(vector3d, vector3d1, RayTraceContext.BlockMode.OUTLINE, fluidMode, player));
    }
//    private BlockPos getDownPos(BlockPos entered, IWorld world) {
//        int i = 0;
//        while (world.isEmptyBlock(entered) && i < 3) {
//            entered = entered.below();
//            i++;
//        }
//        return entered;
//    }
    @SubscribeEvent
    public void onFOVUpdate(FOVUpdateEvent event) {
        if (event.getEntity().hasEffect(RigoranthusEffectRegistry.NECROTIZING_FASCIITIS)) {
            event.setNewfov(1.0F);
        }
    }
}
//    @SubscribeEvent
//    public void onArmorTickEvent(LivingEvent.LivingUpdateEvent event) {
//        if (event.getEntityLiving() instanceof PlayerEntity) {
//            if ((event.getEntityLiving().getItemBySlot(EquipmentSlotType.HEAD).getItem() == Registration.APOGEAN_NETHERITE_HELMET)
//                    && (event.getEntityLiving().getItemBySlot(EquipmentSlotType.CHEST).getItem() == Registration.APOGEAN_NETHERITE_CHESTPLATE)
//                    && (event.getEntityLiving().getItemBySlot(EquipmentSlotType.LEGS).getItem() == Registration.APOGEAN_NETHERITE_LEGGINGS)
//                    && (event.getEntityLiving().getItemBySlot(EquipmentSlotType.FEET).getItem() == Registration.APOGEAN_NETHERITE_BOOTS))
//            {
//                event.getEntityLiving().addEffect(new EffectInstance(RigoranthusEffectRegistry.APOGEAN_SET_BONUS));
//            }
//            if ((event.getEntityLiving().getItemBySlot(EquipmentSlotType.HEAD).getItem() == Registration.AQUEOUS_NETHERITE_HELMET)
//                    && (event.getEntityLiving().getItemBySlot(EquipmentSlotType.CHEST).getItem() == Registration.AQUEOUS_NETHERITE_CHESTPLATE)
//                    && (event.getEntityLiving().getItemBySlot(EquipmentSlotType.LEGS).getItem() == Registration.AQUEOUS_NETHERITE_LEGGINGS)
//                    && (event.getEntityLiving().getItemBySlot(EquipmentSlotType.FEET).getItem() == Registration.AQUEOUS_NETHERITE_BOOTS))
//            {
//                event.getEntityLiving().addEffect(new EffectInstance(RigoranthusEffectRegistry.AQUEOUS_SET_BONUS));
//            }
//            if ((event.getEntityLiving().getItemBySlot(EquipmentSlotType.HEAD).getItem() == Registration.ATROPHYING_NETHERITE_HELMET)
//                    && (event.getEntityLiving().getItemBySlot(EquipmentSlotType.CHEST).getItem() == Registration.ATROPHYING_NETHERITE_CHESTPLATE)
//                    && (event.getEntityLiving().getItemBySlot(EquipmentSlotType.LEGS).getItem() == Registration.ATROPHYING_NETHERITE_LEGGINGS)
//                    && (event.getEntityLiving().getItemBySlot(EquipmentSlotType.FEET).getItem() == Registration.ATROPHYING_NETHERITE_BOOTS))
//            {
//                event.getEntityLiving().addEffect(new EffectInstance(RigoranthusEffectRegistry.ATROPHYING_SET_BONUS));
//            }
//            if ((event.getEntityLiving().getItemBySlot(EquipmentSlotType.HEAD).getItem() == Registration.INCORPOREAL_NETHERITE_HELMET)
//                    && (event.getEntityLiving().getItemBySlot(EquipmentSlotType.CHEST).getItem() == Registration.INCORPOREAL_NETHERITE_CHESTPLATE)
//                    && (event.getEntityLiving().getItemBySlot(EquipmentSlotType.LEGS).getItem() == Registration.INCORPOREAL_NETHERITE_LEGGINGS)
//                    && (event.getEntityLiving().getItemBySlot(EquipmentSlotType.FEET).getItem() == Registration.INCORPOREAL_NETHERITE_BOOTS))
//            {
//                event.getEntityLiving().addEffect(new EffectInstance(RigoranthusEffectRegistry.INCORPOREAL_SET_BONUS));
//            }
//            if ((event.getEntityLiving().getItemBySlot(EquipmentSlotType.HEAD).getItem() == Registration.INFERNAL_NETHERITE_HELMET)
//                    && (event.getEntityLiving().getItemBySlot(EquipmentSlotType.CHEST).getItem() == Registration.INFERNAL_NETHERITE_CHESTPLATE)
//                    && (event.getEntityLiving().getItemBySlot(EquipmentSlotType.LEGS).getItem() == Registration.INFERNAL_NETHERITE_LEGGINGS)
//                    && (event.getEntityLiving().getItemBySlot(EquipmentSlotType.FEET).getItem() == Registration.INFERNAL_NETHERITE_BOOTS))
//            {
//                event.getEntityLiving().addEffect(new EffectInstance(RigoranthusEffectRegistry.INFERNAL_SET_BONUS));
//            }
//            if ((event.getEntityLiving().getItemBySlot(EquipmentSlotType.HEAD).getItem() == Registration.OPULENT_NETHERITE_HELMET)
//                    && (event.getEntityLiving().getItemBySlot(EquipmentSlotType.CHEST).getItem() == Registration.OPULENT_NETHERITE_CHESTPLATE)
//                    && (event.getEntityLiving().getItemBySlot(EquipmentSlotType.LEGS).getItem() == Registration.OPULENT_NETHERITE_LEGGINGS)
//                    && (event.getEntityLiving().getItemBySlot(EquipmentSlotType.FEET).getItem() == Registration.OPULENT_NETHERITE_BOOTS))
//            {
//                event.getEntityLiving().addEffect(new EffectInstance(RigoranthusEffectRegistry.OPULENT_SET_BONUS));
//            }
//            if ((event.getEntityLiving().getItemBySlot(EquipmentSlotType.HEAD).getItem() == Registration.PERNICIOUS_NETHERITE_HELMET)
//                    && (event.getEntityLiving().getItemBySlot(EquipmentSlotType.CHEST).getItem() == Registration.PERNICIOUS_NETHERITE_CHESTPLATE)
//                    && (event.getEntityLiving().getItemBySlot(EquipmentSlotType.LEGS).getItem() == Registration.PERNICIOUS_NETHERITE_LEGGINGS)
//                    && (event.getEntityLiving().getItemBySlot(EquipmentSlotType.FEET).getItem() == Registration.PERNICIOUS_NETHERITE_BOOTS))
//            {
//                event.getEntityLiving().addEffect(new EffectInstance(RigoranthusEffectRegistry.PERNICIOUS_SET_BONUS));
//            }
//            if ((event.getEntityLiving().getItemBySlot(EquipmentSlotType.HEAD).getItem() == Registration.PHANTASMAL_NETHERITE_HELMET)
//                    && (event.getEntityLiving().getItemBySlot(EquipmentSlotType.CHEST).getItem() == Registration.PHANTASMAL_NETHERITE_CHESTPLATE)
//                    && (event.getEntityLiving().getItemBySlot(EquipmentSlotType.LEGS).getItem() == Registration.PHANTASMAL_NETHERITE_LEGGINGS)
//                    && (event.getEntityLiving().getItemBySlot(EquipmentSlotType.FEET).getItem() == Registration.PHANTASMAL_NETHERITE_BOOTS))
//            {
//                event.getEntityLiving().addEffect(new EffectInstance(RigoranthusEffectRegistry.PHANTASMAL_SET_BONUS));
//            }
//            if ((event.getEntityLiving().getItemBySlot(EquipmentSlotType.HEAD).getItem() == Registration.REMEX_NETHERITE_HELMET)
//                    && (event.getEntityLiving().getItemBySlot(EquipmentSlotType.CHEST).getItem() == Registration.REMEX_NETHERITE_CHESTPLATE)
//                    && (event.getEntityLiving().getItemBySlot(EquipmentSlotType.LEGS).getItem() == Registration.REMEX_NETHERITE_LEGGINGS)
//                    && (event.getEntityLiving().getItemBySlot(EquipmentSlotType.FEET).getItem() == Registration.REMEX_NETHERITE_BOOTS))
//            {
//                event.getEntityLiving().addEffect(new EffectInstance(RigoranthusEffectRegistry.REMEX_SET_BONUS));
//            }
//
////            if (event.getEntityLiving().getEyeHeight() < event.getEntityLiving().getBbHeight() * 0.5D) {
////                event.getEntityLiving().refreshDimensions();
////            }
////            ModifiableAttributeInstance modifiableattributeinstance = event.getEntityLiving().getAttribute(Attributes.MOVEMENT_SPEED);
////            if (event.getEntityLiving().getItemBySlot(EquipmentSlotType.FEET).getItem() == Registration.AQUEOUS_NETHERITE_BOOTS || modifiableattributeinstance.hasModifier(APOGEAN_SET_BONUS)) {
////                boolean sand = event.getEntityLiving().level.getBlockState(getDownPos(event.getEntityLiving().blockPosition(), event.getEntityLiving().level)).getBlock().is(BlockTags.SAND);
////                if (sand && !modifiableattributeinstance.hasModifier(APOGEAN_SET_BONUS)) {
////                    modifiableattributeinstance.addPermanentModifier(APOGEAN_SET_BONUS);
////                }
////                if (event.getEntityLiving().tickCount % 25 == 0 && (event.getEntityLiving().getItemBySlot(EquipmentSlotType.FEET).getItem() != Registration.AQUEOUS_NETHERITE_BOOTS || !sand) && modifiableattributeinstance.hasModifier(APOGEAN_SET_BONUS)) {
////                    modifiableattributeinstance.removeModifier(APOGEAN_SET_BONUS);
////                }
////            }
////            if (event.getEntityLiving().getItemBySlot(EquipmentSlotType.HEAD).getItem() == Registration.APOGEAN_NETHERITE_HELMET || modifiableattributeinstance.hasModifier(APOGEAN_HELMET_BONUS)) {
////                if (event.getEntityLiving().isCrouching() && !modifiableattributeinstance.hasModifier(APOGEAN_HELMET_BONUS)) {
////                    modifiableattributeinstance.addPermanentModifier(APOGEAN_HELMET_BONUS);
////                }
////                if ((!event.getEntityLiving().isCrouching() || event.getEntityLiving().getItemBySlot(EquipmentSlotType.HEAD).getItem() != Registration.APOGEAN_NETHERITE_HELMET) && modifiableattributeinstance.hasModifier(APOGEAN_HELMET_BONUS)) {
////                    modifiableattributeinstance.removeModifier(APOGEAN_HELMET_BONUS);
////                }
////            }
//        }
//    }
//}

//        if (event.getEntityLiving().getItemBySlot(EquipmentSlotType.LEGS).getItem() == Registration.AQUEOUS_NETHERITE_LEGGINGS) {
//            if (event.getEntityLiving().horizontalCollision && !event.getEntityLiving().isInWater()) {
//                event.getEntityLiving().fallDistance = 0.0F;
//                Vector3d motion = event.getEntityLiving().getDeltaMovement();
//                double d0 = MathHelper.clamp(motion.x, -0.15F, 0.15F);
//                double d1 = MathHelper.clamp(motion.z, -0.15F, 0.15F);
//                double d2 = 0.1D;
//                if (d2 < 0.0D && !event.getEntityLiving().getFeetBlockState().isScaffolding(event.getEntityLiving()) && event.getEntityLiving().isSuppressingSlidingDownLadder()) {
//                    d2 = 0.0D;
//                }
//                motion = new Vector3d(d0, d2, d1);
//                event.getEntityLiving().setDeltaMovement(motion);
//            }
//
//
//        }
//    }
        /*
    @SubscribeEvent
    public void onLivingAttack(LivingAttackEvent event) {
        if(!event.getEntityLiving().getUseItem().isEmpty() && event.getSource() != null && event.getSource().getEntity() != null){
            if(event.getEntityLiving().getUseItem().getItem() == Registration.PHANTASMAL_NETHERITE_BOOTS){
                Entity attacker = event.getSource().getEntity();
                if(attacker instanceof LivingEntity){
                    boolean flag = false;
                    if(attacker.distanceTo(event.getEntityLiving()) <= 4 && !((LivingEntity)attacker).hasEffect(RigoranthusEffectRegistry.EXSANGUINATION)){
                        ((LivingEntity) attacker).addEffect(new EffectInstance(RigoranthusEffectRegistry.EXSANGUINATION, 60, 2));
                        flag = true;
                    }
                    if(event.getEntityLiving().isInWaterOrBubble()){
                        event.getEntityLiving().setAirSupply(Math.min(event.getEntityLiving().getMaxAirSupply(), event.getEntityLiving().getAirSupply() + 150));
                        flag = true;
                    }
                    if(flag){
                        event.getEntityLiving().getUseItem().hurtAndBreak(1, event.getEntityLiving(), (playerIn) -> {
                            playerIn.broadcastBreakEvent(event.getEntityLiving().getUsedItemHand());
                        });
                    }
                }
            }
        }
    }
}*/