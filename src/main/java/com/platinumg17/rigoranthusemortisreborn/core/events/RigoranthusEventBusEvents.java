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
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.stats.Stats;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.*;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

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
//    }
        /*
    @SubscribeEvent
    public void onLivingAttack(LivingAttackEvent event) {
        if(!event.getEntityLiving().getUseItem().isEmpty() && event.getSource() != null && event.getSource().getEntity() != null){
            if(event.getEntityLiving().getUseItem().getItem() == Registration.PHANTASMAL_NETHERITE_BOOTS){
                Entity attacker = event.getSource().getEntity();
                if(attacker instanceof LivingEntity){
                    boolean flag = false;
                    if(attacker.distanceTo(event.getEntityLiving()) <= 4 && !((LivingEntity)attacker).hasEffect(RigoranthusEffectRegistry.)){
                        ((LivingEntity) attacker).addEffect(new EffectInstance(RigoranthusEffectRegistry., 60, 2));
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