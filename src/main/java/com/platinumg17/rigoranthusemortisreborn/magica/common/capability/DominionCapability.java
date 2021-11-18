package com.platinumg17.rigoranthusemortisreborn.magica.common.capability;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.dominion.IDominion;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;

import static com.platinumg17.rigoranthusemortisreborn.magica.setup.InjectionUtil.Null;

public class DominionCapability {

    @CapabilityInject(IDominion.class)
    public static final Capability<IDominion> DOMINION_CAPABILITY = Null();

    public static final Direction DEFAULT_FACING = null;

    public static final ResourceLocation ID = new ResourceLocation(EmortisConstants.MOD_ID, "mana");

    public static void register(){

        CapabilityManager.INSTANCE.register(IDominion.class, new Capability.IStorage<IDominion>() {
            @Nullable
            @Override
            public INBT writeNBT(Capability<IDominion> capability, IDominion instance, Direction side) {
                CompoundNBT tag = new CompoundNBT();
                tag.putDouble("current", instance.getCurrentDominion());
                tag.putInt("max", instance.getMaxDominion());
                tag.putInt("glyph", instance.getGlyphBonus());
                tag.putInt("book_tier", instance.getBookTier());
                return tag;
            }

            @Override
            public void readNBT(Capability<IDominion> capability, IDominion instance, Direction side, INBT nbt) {
                if(!(nbt instanceof CompoundNBT))
                    return;
                CompoundNBT tag = (CompoundNBT)nbt;
                instance.setMaxDominion(tag.getInt("max"));
                instance.setDominion(tag.getDouble("current"));
                instance.setBookTier(tag.getInt("book_tier"));
                instance.setGlyphBonus(tag.getInt("glyph"));
            }
        }, () -> new Dominion(null));
        System.out.println("Finished Registering DominionCapability");
    }

    /**
     * Get the {@link IDominion} from the specified entity.
     *
     * @param entity The entity
     * @return A lazy optional containing the IDominion, if any
     */
    public static LazyOptional<IDominion> getDominion(final LivingEntity entity){
        return entity.getCapability(DOMINION_CAPABILITY, DEFAULT_FACING);
    }

    public static ICapabilityProvider createProvider(final IDominion mana) {
        return new SerializableCapabilityProvider<>(DOMINION_CAPABILITY, DEFAULT_FACING, mana);
    }

    /**
     * Event handler for the {@link IDominion} capability.
     */
    @SuppressWarnings("unused")
    @Mod.EventBusSubscriber(modid = EmortisConstants.MOD_ID)
    private static class EventHandler {

        /**
         * Attach the {@link IDominion} capability to all living entities.
         *
         * @param event The event
         */
        @SubscribeEvent
        public static void attachCapabilities(final AttachCapabilitiesEvent<Entity> event) {

            if (event.getObject() instanceof PlayerEntity) {
                final Dominion dominion = new Dominion((LivingEntity) event.getObject());
                event.addCapability(ID, createProvider(dominion));
            }
        }

        /**
         * Copy the player's mana when they respawn after dying or returning from the end.
         *
         * @param event The event
         */
        @SubscribeEvent
        public static void playerClone(final PlayerEvent.Clone event) {
            getDominion(event.getOriginal()).ifPresent(oldMaxDominion -> {
                getDominion(event.getPlayer()).ifPresent(newMaxDominion -> {
                    newMaxDominion.setMaxDominion(oldMaxDominion.getMaxDominion());
                    newMaxDominion.setDominion(oldMaxDominion.getCurrentDominion());
                    newMaxDominion.setBookTier(oldMaxDominion.getBookTier());
                    newMaxDominion.setGlyphBonus(oldMaxDominion.getGlyphBonus());
                });
            });
        }
    }
}