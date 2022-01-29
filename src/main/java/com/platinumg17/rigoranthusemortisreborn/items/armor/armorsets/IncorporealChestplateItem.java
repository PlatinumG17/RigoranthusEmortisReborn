package com.platinumg17.rigoranthusemortisreborn.items.armor.armorsets;

import com.platinumg17.rigoranthusemortisreborn.config.Config;
import com.platinumg17.rigoranthusemortisreborn.core.init.Registration;
import com.platinumg17.rigoranthusemortisreborn.magica.common.network.Networking;
import com.platinumg17.rigoranthusemortisreborn.magica.common.network.PacketIncorporealDoubleJump;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import javax.annotation.Nullable;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class IncorporealChestplateItem extends IncorporealArmor {

    public IncorporealChestplateItem(EquipmentSlotType slot) {
        super(slot);
        MinecraftForge.EVENT_BUS.register(new DoubleJumpHandler());
        addListener(EventPriority.HIGHEST, LivingFallEvent.class, this::onLivingFall);
    }

    public boolean isEquippedBy(@Nullable LivingEntity entity) {
        if (entity instanceof PlayerEntity && Config.enableArmorSetBonuses.get()) {
            ItemStack boots = entity.getItemBySlot(EquipmentSlotType.FEET);
            ItemStack legs = entity.getItemBySlot(EquipmentSlotType.LEGS);
            ItemStack chest = entity.getItemBySlot(EquipmentSlotType.CHEST);
            ItemStack helm = entity.getItemBySlot(EquipmentSlotType.HEAD);
            return boots.getItem() == Registration.INCORPOREAL_N_BOOTS && legs.getItem() == Registration.INCORPOREAL_N_LEGS && chest.getItem() == Registration.INCORPOREAL_N_CHEST.get() && helm.getItem() == Registration.INCORPOREAL_N_HELMET;
        }
        else return false;
    }
    protected <T extends Event, S extends LivingEntity> void addListener(EventPriority priority, Class<T> eventClass, BiConsumer<T, S> listener, Function<T, S> wearerSupplier) {
        MinecraftForge.EVENT_BUS.addListener(priority, true, eventClass, event -> {
            S wearer = wearerSupplier.apply(event);
            if (isEquippedBy(wearer)) {
                listener.accept(event, wearer);
            }
        });
    }

    private void onLivingFall(LivingFallEvent event, LivingEntity wearer) {
        event.setDistance(Math.max(0, event.getDistance() - 6));
    }

    protected <T extends Event, S extends LivingEntity> void addListener(Class<T> eventClass, BiConsumer<T, S> listener, Function<T, S> wearerSupplier) {
        addListener(EventPriority.NORMAL, eventClass, listener, wearerSupplier);
    }

    protected <T extends LivingEvent> void addListener(EventPriority priority, Class<T> eventClass, BiConsumer<T, LivingEntity> listener) {
        addListener(priority, eventClass, listener, LivingEvent::getEntityLiving);
    }

    protected <T extends LivingEvent> void addListener(Class<T> eventClass, BiConsumer<T, LivingEntity> listener) {
        addListener(EventPriority.NORMAL, eventClass, listener);
    }


    public void jump(PlayerEntity player) {
        if (Config.enableArmorSetBonuses.get()) {
            player.fallDistance = 0;
            double upwardsMotion = 0.5;
            if (player.hasEffect(Effects.JUMP)) {
                // noinspection ConstantConditions
                upwardsMotion += 0.1 * (player.getEffect(Effects.JUMP).getAmplifier() + 1);
            }
            upwardsMotion *= player.isSprinting() ? 2 : 1;

            Vector3d motion = player.getDeltaMovement();
            double motionMultiplier = player.isSprinting() ? 2 : 0;
            float direction = (float) (player.yRot * Math.PI / 180);
            player.setDeltaMovement(player.getDeltaMovement().add(
                    -MathHelper.sin(direction) * motionMultiplier,
                    upwardsMotion - motion.y,
                    MathHelper.cos(direction) * motionMultiplier)
            );
            player.hasImpulse = true;
            net.minecraftforge.common.ForgeHooks.onLivingJump(player);

            player.awardStat(Stats.JUMP);
            if (player.isSprinting()) {
                player.causeFoodExhaustion(0.1F);
            } else {
                player.causeFoodExhaustion(0.025F);
            }
            player.playSound(SoundEvents.WOOL_FALL, 1, 0.9F + player.getRandom().nextFloat() * 0.2F);
        }
    }

    private class DoubleJumpHandler {

        @OnlyIn(Dist.CLIENT)
        private boolean canDoubleJump;

        @OnlyIn(Dist.CLIENT)
        private boolean hasReleasedJumpKey;

        @SubscribeEvent
        @OnlyIn(Dist.CLIENT)
        @SuppressWarnings("unused")
        public void onClientTick(TickEvent.ClientTickEvent event) {
            ClientPlayerEntity player = Minecraft.getInstance().player;
            if (Config.enableArmorSetBonuses.get()) {
                if (event.phase == TickEvent.Phase.END && player != null && player.input != null) {
                    if ((player.isOnGround() || player.onClimbable()) && !player.isInWater()) {
                        hasReleasedJumpKey = false;
                        canDoubleJump = true;
                    } else if (!player.input.jumping) {
                        hasReleasedJumpKey = true;
                    } else if (!player.abilities.flying && canDoubleJump && hasReleasedJumpKey) {
                        canDoubleJump = false;
                        if (isEquippedBy(player)) {
                            Networking.INSTANCE.sendToServer(new PacketIncorporealDoubleJump());
                            jump(player);
                        }
                    }
                }
            }
        }
    }
}