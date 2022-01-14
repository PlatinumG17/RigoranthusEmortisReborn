package com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork;

import com.platinumg17.rigoranthusemortisreborn.canis.CanisItems;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.CanisEntity;
import com.platinumg17.rigoranthusemortisreborn.canis.common.skill.PredatorSkill;
import com.platinumg17.rigoranthusemortisreborn.config.Config;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.monster.AbstractSkeletonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LootingLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CanisEventHandler {

//    @SubscribeEvent
//    public void rightClickEntity(final PlayerInteractEvent.EntityInteract event) {
//
//        World world = event.getWorld();
//        ItemStack stack = event.getItemStack();
//        Entity target = event.getTarget();
//
//        if (target.getType() == RigoranthusEntityTypes.FERAL_CANIS.get() && stack.getItem() == ItemInit.PACT_OF_SERVITUDE.get()) {
//            event.setCanceled(true);
//            FeralCanisEntity feralCanis = (FeralCanisEntity) target;
//            PlayerEntity player = event.getPlayer();
//            BlockPos blockpos = event.getPos();
//
//            if (feralCanis.isAlive()) {     //&& feralCanis.isTame() && feralCanis.isOwnedBy(player)) {
//                if (!world.isClientSide) {
//                    if (!player.abilities.instabuild) {
//                        stack.shrink(1);
//                    }
//                    world.playSound(player, blockpos, SoundEvents.BOOK_PAGE_TURN, SoundCategory.NEUTRAL, 1.0F,(world.random.nextFloat() - world.random.nextFloat()) * 0.4F + 1.0F); // was  0.2F + 1.0F
//                    if ((Math.random() <= 0.15)) {
//                        if (player.level.isClientSide) {
//                            feralCanis.level.addParticle(ParticleTypes.SOUL, feralCanis.getRandomX(1.0D), feralCanis.getRandomY() + 0.5D, feralCanis.getRandomZ(1.0D), 0.0D, 0.0D, 0.0D); //((ServerWorld) level).sendParticles(player, true, 5, 3, 3, IPacket <?> level) //    (ParticleTypes.SOUL, feralCanis.blockPosition(), 5, 3, 3, 3, 1);
//                            feralCanis.level.addParticle(ParticleTypes.SOUL, feralCanis.getRandomX(1.5D), feralCanis.getRandomY() + 0.8D, feralCanis.getRandomZ(1.5D), 0.0D, 0.0D, 0.0D);
//                        }
//                        world.playSound(null, blockpos, SoundEvents.WOLF_HOWL, SoundCategory.NEUTRAL, 1f, 0.8f);
//                        feralCanis.setNoActionTime(60); // feralCanis.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 100, 7));
//                        feralCanis.setSecondsOnFire(3);
//                        if (player.level.isClientSide) {
//                            player.displayClientMessage(new StringTextComponent("\u00A76The Pact was Successful. \u00A7cThe Beasts Impurities will now be Expelled."), (true));
//                        }
//                        CanisEntity canis = SpecializedEntityTypes.CANIS.get().create(world);
//                        canis.setTame(true);
//                        canis.setOwnerUUID(player.getUUID());  // TODO --> setOwnerUUID may not be needed if canis.tame(player) is already here
//                        canis.setHealth(canis.getMaxHealth());
//                        canis.setOrderedToSit(false);
////                        canis.setAge(1);
//                        canis.absMoveTo(feralCanis.getX(), feralCanis.getY(), feralCanis.getZ(), feralCanis.yRot, feralCanis.xRot);
//                        world.addFreshEntity(canis);
//                        feralCanis.remove();
//                    }
//                }
//                event.setCancellationResult(ActionResultType.SUCCESS);
//            } else {
//                event.setCancellationResult(ActionResultType.FAIL);
//            }
//        }
//    }

    @SubscribeEvent
    public void onEntitySpawn(final EntityJoinWorldEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof AbstractSkeletonEntity) {
            AbstractSkeletonEntity skeleton = (AbstractSkeletonEntity) entity;
            skeleton.goalSelector.addGoal(3, new AvoidEntityGoal<>(skeleton, CanisEntity.class, 6.0F, 1.0D, 1.2D)); // Same goal as in AbstractSkeletonEntity
        }
    }

    @SubscribeEvent
    public void playerLoggedIn(final PlayerLoggedInEvent event) {
        if (Config.STARTING_ITEMS.get()) {
            PlayerEntity player = event.getPlayer();
            CompoundNBT tag = player.getPersistentData();
            if (!tag.contains(PlayerEntity.PERSISTED_NBT_TAG)) {
                tag.put(PlayerEntity.PERSISTED_NBT_TAG, new CompoundNBT());
            }
            CompoundNBT persistTag = tag.getCompound(PlayerEntity.PERSISTED_NBT_TAG);
            if (!persistTag.getBoolean("gotCanisStartingItems")) {
                persistTag.putBoolean("gotCanisStartingItems", true);
                player.inventory.add(new ItemStack(CanisItems.CANIS_SUMMONING_CHARM.get()));
                player.inventory.add(new ItemStack(CanisItems.WHISTLE.get()));
            }
        }
    }
    @SubscribeEvent
    public void onLootDrop(final LootingLevelEvent event) {
        PredatorSkill.onLootDrop(event);
    }
}