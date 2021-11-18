package com.platinumg17.rigoranthusemortisreborn.magica.common.event;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.AbstractSpellPart;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.SpellContext;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.SpellResolver;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.util.MathUtil;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.magica.common.enchantment.EnchantmentRegistry;
import com.platinumg17.rigoranthusemortisreborn.magica.common.items.SpellParchment;
import com.platinumg17.rigoranthusemortisreborn.magica.common.network.Networking;
import com.platinumg17.rigoranthusemortisreborn.magica.common.network.PacketReactiveSpell;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

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

    public static void castSpell(PlayerEntity playerIn, ItemStack s){
        if(EnchantmentHelper.getItemEnchantmentLevel(EnchantmentRegistry.REACTIVE_ENCHANTMENT, s) * .25 >= Math.random() && s.hasTag() && s.getTag().contains("spell")){
            List<AbstractSpellPart> list = SpellParchment.getSpellRecipe(s);
            SpellResolver resolver = new SpellResolver(list, true, new SpellContext(list, playerIn));
            RayTraceResult result = playerIn.pick(5, 0, false);

            EntityRayTraceResult entityRes = MathUtil.getLookedAtEntity(playerIn, 25);
            ItemStack stack = playerIn.getMainHandItem();
            Hand handIn = Hand.MAIN_HAND;
            if(entityRes != null && entityRes.getEntity() instanceof LivingEntity){
                resolver.onCastOnEntity(stack, playerIn, (LivingEntity) entityRes.getEntity(), handIn);
                return;
            }
            if(result.getType() == RayTraceResult.Type.BLOCK){
                ItemUseContext context = new ItemUseContext(playerIn, handIn, (BlockRayTraceResult) result);
                resolver.onCastOnBlock(context);
                return;
            }
            resolver.onCast(stack,playerIn,playerIn.getCommandSenderWorld());
        }
    }

    @SubscribeEvent
    public static void leftClickBlock(PlayerInteractEvent.LeftClickBlock e){
        LivingEntity entity = e.getEntityLiving();
        if(entity.getCommandSenderWorld().isClientSide || !(entity instanceof PlayerEntity))
            return;
        ItemStack s = e.getItemStack();
        castSpell((PlayerEntity) entity, s);
    }

    @SubscribeEvent
    public static void playerAttackEntity(AttackEntityEvent e){
        LivingEntity entity = e.getEntityLiving();
        if(entity == null || entity.getCommandSenderWorld().isClientSide || !(entity instanceof PlayerEntity))
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