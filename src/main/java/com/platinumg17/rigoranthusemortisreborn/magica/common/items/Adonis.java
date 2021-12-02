package com.platinumg17.rigoranthusemortisreborn.magica.common.items;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.*;
import com.platinumg17.rigoranthusemortisreborn.core.registry.RigoranthusSoundRegistry;
import com.platinumg17.rigoranthusemortisreborn.magica.client.renderer.item.AdonisRenderer;
import com.platinumg17.rigoranthusemortisreborn.magica.common.entity.EntitySpellArrow;
import com.platinumg17.rigoranthusemortisreborn.magica.common.spell.augment.AugmentSplit;
import com.platinumg17.rigoranthusemortisreborn.magica.common.spell.method.MethodProjectile;
import com.platinumg17.rigoranthusemortisreborn.magica.common.util.PortUtil;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.MagicItemsRegistry;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.item.ICasterTool;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.interfaces.ISpellCaster;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Adonis extends BowItem implements IAnimatable, ICasterTool {
    public Adonis() {
        super(MagicItemsRegistry.defaultItemProperties().stacksTo(1).setISTER(() -> AdonisRenderer::new));
    }
    public boolean canPlayerCastSpell(ItemStack bow, PlayerEntity playerentity){
        ISpellCaster caster = getSpellCaster(bow);
        return new SpellResolver(new SpellContext(caster.getSpell(), playerentity)).withSilent(true).canCast(playerentity);
    }

    public ItemStack findAmmo(PlayerEntity playerEntity, ItemStack shootable) {
        if (!(shootable.getItem() instanceof ShootableItem)) {
            return ItemStack.EMPTY;
        } else {
            Predicate<ItemStack> predicate = ((ShootableItem)shootable.getItem()).getSupportedHeldProjectiles()
                    .and(i -> !(i.getItem() instanceof SpellArrow) || (i.getItem() instanceof SpellArrow && canPlayerCastSpell(shootable, playerEntity)));
            ItemStack itemstack = ShootableItem.getHeldProjectile(playerEntity, predicate);
            if (!itemstack.isEmpty()) {
                return itemstack;
            } else {
                predicate = ((ShootableItem)shootable.getItem()).getAllSupportedProjectiles().and(i -> !(i.getItem() instanceof SpellArrow) || (i.getItem() instanceof SpellArrow && canPlayerCastSpell(shootable, playerEntity)));

                for(int i = 0; i < playerEntity.inventory.getContainerSize(); ++i) {
                    ItemStack itemstack1 = playerEntity.inventory.getItem(i);
                    if (predicate.test(itemstack1)) {
                        return itemstack1;
                    }
                }
                return playerEntity.abilities.instabuild ? new ItemStack(Items.ARROW) : ItemStack.EMPTY;
            }
        }
    }

    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        ISpellCaster caster = getSpellCaster(playerIn.getItemInHand(handIn));
        boolean hasAmmo = !findAmmo(playerIn, itemstack).isEmpty();

        ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, worldIn, playerIn, handIn, hasAmmo);
        if (ret != null) return ret;
        if(hasAmmo || (caster.getSpell() != null && new SpellResolver(new SpellContext(caster.getSpell(), playerIn)).withSilent(true).canCast(playerIn))){
            playerIn.startUsingItem(handIn);
            return ActionResult.consume(itemstack);
        }
        if (!playerIn.abilities.instabuild && !hasAmmo) {
            return ActionResult.fail(itemstack);
        } else {
            playerIn.startUsingItem(handIn);
            return ActionResult.consume(itemstack);
        }
    }

    public EntitySpellArrow buildSpellArrow(World worldIn, PlayerEntity playerentity, ISpellCaster caster, boolean isSpellArrow){
        EntitySpellArrow spellArrow = new EntitySpellArrow(worldIn, playerentity);
        spellArrow.spellResolver = new SpellResolver(new SpellContext(caster.getSpell(), playerentity).withColors(caster.getColor())).withSilent(true);
        spellArrow.setColors(caster.getColor().r, caster.getColor().g, caster.getColor().b);
        if(isSpellArrow)
            spellArrow.setBaseDamage(0);
        return spellArrow;
    }

    @Override
    public void releaseUsing(ItemStack bowStack, World worldIn, LivingEntity entityLiving, int timeLeft) {
        //Copied from BowItem so we can spawn arrows in case there are no items.
        if (!(entityLiving instanceof PlayerEntity))
            return;
        PlayerEntity playerentity = (PlayerEntity)entityLiving;
        boolean isInfinity = playerentity.abilities.instabuild || EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, bowStack) > 0;
        ItemStack arrowStack = findAmmo(playerentity, bowStack);

        int useTime = this.getUseDuration(bowStack) - timeLeft;
        useTime = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(bowStack, worldIn, playerentity, useTime, !arrowStack.isEmpty() || isInfinity);
        if (useTime < 0) return;
        boolean canFire = false;
        if (!arrowStack.isEmpty() || isInfinity) {
            if (arrowStack.isEmpty()) {
                arrowStack = new ItemStack(Items.ARROW);
            }
            canFire = true;
        }
        ISpellCaster caster = getSpellCaster(bowStack);
        boolean isSpellArrow = false;
        if(arrowStack.isEmpty() && caster.getSpell() != null && new SpellResolver(new SpellContext(caster.getSpell(), playerentity)).canCast(playerentity)){
            canFire = true;
            isSpellArrow = true;
        }
        if(!canFire)
            return;
        float f = getPowerForTime(useTime);
        if (((double)f >= 0.1D) && canFire) {
            boolean isArrowInfinite = playerentity.abilities.instabuild || (arrowStack.getItem() instanceof ArrowItem && ((ArrowItem)arrowStack.getItem()).isInfinite(arrowStack, bowStack, playerentity));
            if (!worldIn.isClientSide) {
                ArrowItem arrowitem = (ArrowItem)(arrowStack.getItem() instanceof ArrowItem ? arrowStack.getItem() : Items.ARROW);
                AbstractArrowEntity abstractarrowentity = arrowitem.createArrow(worldIn, arrowStack, playerentity);
                abstractarrowentity = customArrow(abstractarrowentity);

                List<AbstractArrowEntity> arrows = new ArrayList<>();
                boolean didCastSpell = false;
                if(arrowitem == Items.ARROW && caster.getSpell() != null && new SpellResolver(new SpellContext(caster.getSpell(), playerentity)).withSilent(true).canCast(playerentity)){
                    abstractarrowentity = buildSpellArrow(worldIn, playerentity, caster, isSpellArrow);
                    new SpellResolver(new SpellContext(caster.getSpell(), playerentity)).expendDominion(playerentity);
                    didCastSpell = true;
                }else if(arrowitem instanceof SpellArrow){
                    if(caster.getSpell() == null || !(new SpellResolver(new SpellContext(caster.getSpell(), playerentity)).canCast(playerentity))){
                        return;
                    }else if(new SpellResolver(new SpellContext(caster.getSpell(), playerentity)).canCast(playerentity)){
                        new SpellResolver(new SpellContext(caster.getSpell(), playerentity)).expendDominion(playerentity);
                        didCastSpell = true;
                    }
                }
                arrows.add(abstractarrowentity);
                if(caster.getSpell() != null && caster.getSpell().isValid() && didCastSpell){
                    int numSplits = caster.getSpell().getBuffsAtIndex(0, playerentity, AugmentSplit.class);
                    if(abstractarrowentity instanceof EntitySpellArrow){
                        numSplits = ((EntitySpellArrow) abstractarrowentity).spellResolver.spell.getBuffsAtIndex(0, playerentity, AugmentSplit.class);
                    }
                    for(int i =1; i < numSplits + 1; i++){
                        Direction offset = playerentity.getDirection().getClockWise();
                        if(i%2==0) offset = offset.getOpposite();
                        // Alternate sides
                        BlockPos projPos = playerentity.blockPosition().relative(offset, i);
                        projPos = projPos.offset(0, 1.5, 0);
                        EntitySpellArrow spellArrow = buildSpellArrow(worldIn, playerentity, caster, isSpellArrow);
                        spellArrow.setPos(projPos.getX(), spellArrow.blockPosition().getY(), projPos.getZ());
                        arrows.add(spellArrow);
                    }
                }
                for(AbstractArrowEntity arr : arrows){
                    arr.shootFromRotation(playerentity, playerentity.xRot, playerentity.yRot, 0.0F, f * 3.0F, 1.0F);
                    if (f >= 1.0F) {
                        arr.setCritArrow(true);
                    }
                    addArrow(arr, bowStack, arrowStack, isArrowInfinite, playerentity);
                }
            }
            worldIn.playSound(null, playerentity.getX(), playerentity.getY(), playerentity.getZ(), RigoranthusSoundRegistry.SHOOT_BOW.get(), SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
            if (!isArrowInfinite && !playerentity.abilities.instabuild) {
                arrowStack.shrink(1);
            }
        }
    }

    public void addArrow(AbstractArrowEntity abstractarrowentity, ItemStack bowStack,ItemStack arrowStack, boolean isArrowInfinite, PlayerEntity playerentity){
        int power = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, bowStack);
        if (power > 0) {
            abstractarrowentity.setBaseDamage(abstractarrowentity.getBaseDamage() + (double)power * 0.5D + 0.5D);
        }
        int punch = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PUNCH_ARROWS, bowStack);
        if (punch > 0) {
            abstractarrowentity.setKnockback(punch);
        }
        if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAMING_ARROWS, bowStack) > 0) {
            abstractarrowentity.setSecondsOnFire(100);
        }
        if (isArrowInfinite || playerentity.abilities.instabuild && (arrowStack.getItem() == Items.SPECTRAL_ARROW || arrowStack.getItem() == Items.TIPPED_ARROW)) {
            abstractarrowentity.pickup = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
        }
        playerentity.level.addFreshEntity(abstractarrowentity);
    }
    /**
     * Get the predicate to match ammunition when searching the player's inventory, not their main/offhand
     */
    public Predicate<ItemStack> getAllSupportedProjectiles() {return ARROW_ONLY.or(i -> i.getItem() instanceof SpellArrow);}
    @Override public void registerControllers(AnimationData data) {}
    @Override public AbstractArrowEntity customArrow(AbstractArrowEntity arrow) {
        return super.customArrow(arrow);
    }
    public AnimationFactory factory = new AnimationFactory(this);
    @Override public AnimationFactory getFactory() {
        return factory;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip2, ITooltipFlag flagIn) {
        getInformation(stack, worldIn, tooltip2, flagIn);
        super.appendHoverText(stack, worldIn, tooltip2, flagIn);
    }
    @Override
    public boolean isScribedSpellValid(ISpellCaster caster, PlayerEntity player, Hand hand, ItemStack stack, Spell spell) {
        return spell.recipe.stream().noneMatch(s -> s instanceof AbstractCastMethod);
    }
    @Override
    public void sendInvalidMessage(PlayerEntity player) {
        PortUtil.sendMessageNoSpam(player, new TranslationTextComponent("rigoranthusemortisreborn.bow.invalid"));
    }

    @Override
    public boolean setSpell(ISpellCaster caster, PlayerEntity player, Hand hand, ItemStack stack, Spell spell) {
        ArrayList<AbstractSpellPart> recipe = new ArrayList<>();
        recipe.add(MethodProjectile.INSTANCE);
        recipe.addAll(spell.recipe);
        spell.recipe = recipe;
        return ICasterTool.super.setSpell(caster, player, hand, stack, spell);
    }
    @Override public int getEnchantmentValue() {
        return super.getEnchantmentValue();
    }
    @Override public boolean isEnchantable(ItemStack stack) {
        return true;
    }
    @Override public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return true;
    }
}