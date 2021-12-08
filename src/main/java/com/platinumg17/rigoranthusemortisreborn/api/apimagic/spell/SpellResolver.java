package com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell;

import com.platinumg17.rigoranthusemortisreborn.api.RigoranthusEmortisRebornAPI;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.event.SpellCastEvent;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.event.SpellResolveEvent;
import com.platinumg17.rigoranthusemortisreborn.magica.common.capability.DominionCapability;
import com.platinumg17.rigoranthusemortisreborn.magica.common.util.PortUtil;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.interfaces.ISpellValidator;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.interfaces.SpellValidationError;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.util.SpellUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.FakePlayerFactory;

import javax.annotation.Nullable;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.platinumg17.rigoranthusemortisreborn.api.apimagic.util.DominionUtil.getPlayerDiscounts;

public class SpellResolver {
    public AbstractCastMethod castType;
    public Spell spell;
    public final SpellContext spellContext;
    public boolean silent;
    private final ISpellValidator spellValidator;

    @Deprecated // BAD
    public SpellResolver(AbstractCastMethod cast, List<AbstractSpellPart> spell, SpellContext context){
        this.castType = cast;
        this.spell = new Spell(spell);
        this.spellContext = context;
        this.spellValidator = RigoranthusEmortisRebornAPI.getInstance().getSpellCastingSpellValidator();
    }

    public SpellResolver(SpellContext spellContext){
        this.spell = spellContext.getSpell();
        this.castType = spellContext.getSpell().getCastMethod();
        this.spellContext = spellContext;
        this.spellValidator = RigoranthusEmortisRebornAPI.getInstance().getSpellCastingSpellValidator();
    }

    public SpellResolver withSilent(boolean isSilent){
        this.silent = isSilent;
        return this;
    }

    @Deprecated // Removed in favor of Spell constructor
    public SpellResolver(List<AbstractSpellPart> spell, SpellContext context) {
        this(null, spell, context);
        AbstractCastMethod method = null;
        if(spell != null && !spell.isEmpty() && spell.get(0) instanceof AbstractCastMethod)
            method = (AbstractCastMethod) spell.get(0);
        this.castType = method;
    }

    @Deprecated // Removed in favor of Spell constructor
    public SpellResolver(List<AbstractSpellPart> spell, boolean silent, SpellContext context){
        this(spell, context);
        this.silent = silent;
    }

    public boolean canCast(LivingEntity entity){
        // Validate the spell
        List<SpellValidationError> validationErrors = spellValidator.validate(spell.recipe);
        if (validationErrors.isEmpty()) {
            // Validation successful. We can check the player's dominion now.
            return enoughDominion(entity);
        } else {
            // Validation failed, explain why if applicable
            if (!silent && !entity.getCommandSenderWorld().isClientSide) {
                // Sending only the first error to avoid spam
                PortUtil.sendMessageNoSpam(entity, validationErrors.get(0).makeTextComponentExisting());
            }
            return false;
        }
    }

    boolean enoughDominion(LivingEntity entity){
        int totalCost = getCastingCost(spell, entity);
        AtomicBoolean canCast = new AtomicBoolean(false);
        DominionCapability.getDominion(entity).ifPresent(dominion -> {
            canCast.set(totalCost <= dominion.getCurrentDominion() || (entity instanceof PlayerEntity &&  ((PlayerEntity) entity).isCreative()));
            if(!canCast.get() && !entity.getCommandSenderWorld().isClientSide && !silent)
                PortUtil.sendMessageNoSpam(entity,new TranslationTextComponent("rigoranthusemortisreborn.spell.no_dominion"));
        });
        return canCast.get();
    }

    public boolean postEvent(LivingEntity entity){
        return SpellUtil.postEvent(new SpellCastEvent(entity, spell, spellContext));
    }

    // TODO: Remove world arg
    public void onCast(ItemStack stack, LivingEntity livingEntity, World world){
        if(canCast(livingEntity) && !postEvent(livingEntity))
            castType.onCast(stack, livingEntity, world, spell.getAugments( 0, livingEntity), spellContext, this);
    }

    public void onCastOnBlock(BlockRayTraceResult blockRayTraceResult, LivingEntity caster){
        if(canCast(caster) && !postEvent(caster))
            castType.onCastOnBlock(blockRayTraceResult, caster, spell.getAugments( 0, caster), spellContext, this);
    }

    public void onCastOnBlock(ItemUseContext context){
        if(canCast(context.getPlayer()) && !postEvent(context.getPlayer()))
            castType.onCastOnBlock(context, spell.getAugments( 0, context.getPlayer()), spellContext, this);
    }

    public void onCastOnEntity(ItemStack stack, LivingEntity playerIn, Entity target, Hand hand){
        if(canCast(playerIn) && !postEvent(playerIn))
            castType.onCastOnEntity(stack, playerIn, target, hand, spell.getAugments(0, playerIn), spellContext, this);
    }

    public void onResolveEffect(World world, LivingEntity shooter, RayTraceResult result){
        SpellResolver.resolveEffects(world, shooter, result, spell, spellContext);
    }

    public static void resolveEffects(World world, LivingEntity shooter, RayTraceResult result, Spell spell, SpellContext spellContext){
        spellContext.resetCastCounter();
        shooter = getUnwrappedCaster(world, shooter, spellContext);
        SpellResolveEvent.Pre spellResolveEvent = new SpellResolveEvent.Pre(world, shooter, result, spell, spellContext);
        MinecraftForge.EVENT_BUS.post(spellResolveEvent);
        if(spellResolveEvent.isCanceled())
            return;

        for(int i = 0; i < spell.recipe.size(); i++){
            if(spellContext.isCanceled())
                break;
            AbstractSpellPart part = spellContext.nextSpell();
            if(part == null)
                return;
            SpellStats.Builder builder = new SpellStats.Builder();
            SpellStats stats = builder
                    .setAugments(spell.getAugments(i, shooter))
                    .addItemsFromEntity(shooter)
                    .build(part, result, world, shooter, spellContext);
            if(part instanceof AbstractEffect){
                ((AbstractEffect) part).onResolve(result, world, shooter, stats, spellContext);
            }
        }
        MinecraftForge.EVENT_BUS.post(new SpellResolveEvent.Post(world, shooter, result, spell, spellContext));
    }

    // Safely unwrap the living entity in the case that the caster is null, aka being cast by a non-player.
    public static LivingEntity getUnwrappedCaster(World world, LivingEntity shooter, SpellContext spellContext){
        if(shooter == null && spellContext.castingTile != null) {
            shooter = FakePlayerFactory.getMinecraft((ServerWorld) world);
            BlockPos pos = spellContext.castingTile.getBlockPos();
            shooter.setPos(pos.getX(), pos.getY(), pos.getZ());
        }
        shooter = shooter == null ? FakePlayerFactory.getMinecraft((ServerWorld) world) : shooter;
        return shooter;
    }

    public boolean wouldAllEffectsDoWork(RayTraceResult result, World world, LivingEntity entity, List<AbstractAugment> augments){
        for(AbstractSpellPart spellPart : spell.recipe){
            if(spellPart instanceof AbstractEffect){
                if(!((AbstractEffect) spellPart).wouldSucceed(result, world, entity, augments)){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean wouldCastSuccessfully(@Nullable ItemStack stack, LivingEntity caster, World world, List<AbstractAugment> augments){
        return castType.wouldCastSuccessfully(stack, caster, world, augments, this);
    }

    public boolean wouldCastOnBlockSuccessfully(ItemUseContext context, List<AbstractAugment> augments){
        return castType.wouldCastOnBlockSuccessfully(context, augments,this );
    }

    public boolean wouldCastOnBlockSuccessfully(BlockRayTraceResult blockRayTraceResult, LivingEntity caster){
        return castType.wouldCastOnBlockSuccessfully(blockRayTraceResult, caster,  spell.getAugments(0, caster), this);
    }

    public boolean wouldCastOnEntitySuccessfully(@Nullable ItemStack stack, LivingEntity caster, LivingEntity target, Hand hand, List<AbstractAugment> augments){
        return castType.wouldCastOnEntitySuccessfully(stack, caster, target, hand, augments,this);
    }

    public void expendDominion(LivingEntity entity){
        int totalCost = getCastingCost(spell, entity);
        DominionCapability.getDominion(entity).ifPresent(dominion -> dominion.removeDominion(totalCost));
    }

    public int getCastingCost(Spell spell, LivingEntity e){
        int cost = spell.getCastingCost() - getPlayerDiscounts(e);
        return Math.max(cost, 0);
    }
}