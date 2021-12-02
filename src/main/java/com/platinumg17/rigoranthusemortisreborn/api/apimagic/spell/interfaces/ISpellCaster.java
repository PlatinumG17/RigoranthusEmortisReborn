package com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.interfaces;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.Spell;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.SpellContext;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.SpellResolver;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.util.MathUtil;
import com.platinumg17.rigoranthusemortisreborn.magica.client.particle.ParticleColor;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.IntangibleAirTile;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.PhantomBlockTile;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.ScribesTile;
import com.platinumg17.rigoranthusemortisreborn.magica.common.spell.augment.AugmentSensitive;
import com.platinumg17.rigoranthusemortisreborn.magica.common.util.PortUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.Map;

/**
 * An interface for handling NBT as it relates to items that may cast spells.
 * See SpellCaster for implementation.
 */
public interface ISpellCaster {

    @Nonnull
    Spell getSpell();

    Spell getSpell(int slot);

    int getMaxSlots();

    int getCurrentSlot();

    void setCurrentSlot(int slot);

    void setSpell(Spell spell, int slot);

    void setSpell(Spell spell);

    void setColor(ParticleColor.IntWrapper color);

    void setFlavorText(String str);

    String getFlavorText();

    @Nonnull ParticleColor.IntWrapper getColor();

    Map<Integer, Spell> getSpells();

    default Spell getSpell(World world, PlayerEntity playerEntity, Hand hand, ISpellCaster caster){
        return caster.getSpell();
    }

    default ActionResult<ItemStack> castSpell(World worldIn, PlayerEntity playerIn, Hand handIn, TranslationTextComponent invalidMessage, Spell spell){
        ItemStack stack = playerIn.getItemInHand(handIn);

        if(worldIn.isClientSide)
            return ActionResult.pass(playerIn.getItemInHand(handIn));
        if(spell == null) {
            PortUtil.sendMessageNoSpam(playerIn,invalidMessage);
            return new ActionResult<>(ActionResultType.SUCCESS, stack);
        }
        SpellResolver resolver = new SpellResolver(new SpellContext(spell, playerIn)
                .withColors(getColor()));
        boolean isSensitive = resolver.spell.getBuffsAtIndex(0, playerIn, AugmentSensitive.INSTANCE) > 0;
        RayTraceResult result = playerIn.pick(5, 0, isSensitive);
        if(result instanceof BlockRayTraceResult && worldIn.getBlockEntity(((BlockRayTraceResult) result).getBlockPos()) instanceof ScribesTile)
            return new ActionResult<>(ActionResultType.SUCCESS, stack);
        if(result instanceof BlockRayTraceResult && !playerIn.isShiftKeyDown()){
            if(worldIn.getBlockEntity(((BlockRayTraceResult) result).getBlockPos()) != null &&
                    !(worldIn.getBlockEntity(((BlockRayTraceResult) result).getBlockPos()) instanceof IntangibleAirTile
                            ||(worldIn.getBlockEntity(((BlockRayTraceResult) result).getBlockPos()) instanceof PhantomBlockTile))) {
                return new ActionResult<>(ActionResultType.SUCCESS, stack);
            }
        }
        EntityRayTraceResult entityRes = MathUtil.getLookedAtEntity(playerIn, 25);

        if(entityRes != null && entityRes.getEntity() instanceof LivingEntity){
            resolver.onCastOnEntity(stack, playerIn, (LivingEntity) entityRes.getEntity(), handIn);
            return new ActionResult<>(ActionResultType.CONSUME, stack);
        }
        if(result.getType() == RayTraceResult.Type.BLOCK || (isSensitive && result instanceof BlockRayTraceResult)){
            ItemUseContext context = new ItemUseContext(playerIn, handIn, (BlockRayTraceResult) result);
            resolver.onCastOnBlock(context);
            return new ActionResult<>(ActionResultType.CONSUME, stack);
        }
        resolver.onCast(stack,playerIn,worldIn);
        return new ActionResult<>(ActionResultType.CONSUME, stack);
    }
    default ActionResult<ItemStack> castSpell(World worldIn, PlayerEntity playerIn, Hand handIn, TranslationTextComponent invalidMessage){
        return castSpell(worldIn, playerIn, handIn, invalidMessage, getSpell(worldIn, playerIn, handIn, this));
    }
    default void copySlotFrom(ISpellCaster caster){
        setColor(caster.getColor());
        setSpell(caster.getSpell());
        setFlavorText(caster.getFlavorText());
    }
}