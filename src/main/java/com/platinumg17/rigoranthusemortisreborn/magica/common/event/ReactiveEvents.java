package com.platinumg17.rigoranthusemortisreborn.magica.common.event;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.util.MathUtil;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.magica.client.particle.ParticleColor;
import com.platinumg17.rigoranthusemortisreborn.magica.common.enchantment.EnchantmentRegistry;
import com.platinumg17.rigoranthusemortisreborn.magica.common.network.Networking;
import com.platinumg17.rigoranthusemortisreborn.magica.common.network.PacketReactiveSpell;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EmortisConstants.MOD_ID)
public class ReactiveEvents {
    @SubscribeEvent
    public static void livingHitEvent(LivingHurtEvent e){
        LivingEntity entity = e.getEntityLiving();
        if(entity.getCommandSenderWorld().isClientSide || !(entity instanceof PlayerEntity))
            return;
        for(ItemStack s : entity.getArmorSlots()){
            castSpell((PlayerEntity) entity, s);
        }
    }
    // TODO: Replace ray casting with unified casting on look vector
    public static void castSpell(PlayerEntity playerIn, ItemStack s){
        if(EnchantmentHelper.getItemEnchantmentLevel(EnchantmentRegistry.REACTIVE_ENCHANTMENT, s) * .05 >= Math.random()/* && s.hasTag() && s.getTag().contains("spell")*/){
//            Spell spell = Spell.deserialize(s.getOrCreateTag().getString("spell"));
//            SunderedCadaverEntity cadaver = new SunderedCadaverEntity(playerIn.level);
            ParticleColor.IntWrapper color = ParticleColor.IntWrapper.deserialize(s.getOrCreateTag().getString("spell_color"));
            color.makeVisible();
//            SpellResolver resolver = new SpellResolver(new SpellContext(spell, playerIn).withColors(color)).withSilent(true);
            RayTraceResult result = playerIn.pick(5, 0, false);

            EntityRayTraceResult entityRes = MathUtil.getLookedAtEntity(playerIn, 25);
            ItemStack stack = playerIn.getMainHandItem();
            Hand handIn = Hand.MAIN_HAND;
            if(entityRes != null && entityRes.getEntity() instanceof LivingEntity){
//                resolver.onCastOnEntity(stack, playerIn, entityRes.getEntity(), handIn);
                return;
            }
//            resolver.onCast(stack,playerIn,playerIn.getCommandSenderWorld());
        }
    }
    @SubscribeEvent
    public static void playerAttackEntity(AttackEntityEvent e){
        LivingEntity entity = e.getEntityLiving();
        if(entity == null || entity.getCommandSenderWorld().isClientSide || !(entity instanceof PlayerEntity) && !(entity instanceof TameableEntity))
            return;
        ItemStack s = e.getEntityLiving().getMainHandItem();
        castSpell((PlayerEntity) entity, s);
    }
    @SubscribeEvent
    public static void leftClickAir(PlayerInteractEvent.LeftClickEmpty e){
        LivingEntity entity = e.getEntityLiving();
        if(!(entity instanceof PlayerEntity))
            return;
        if(EnchantmentHelper.getItemEnchantmentLevel(EnchantmentRegistry.REACTIVE_ENCHANTMENT, e.getItemStack()) > 0)
            Networking.INSTANCE.sendToServer(new PacketReactiveSpell());
    }
}