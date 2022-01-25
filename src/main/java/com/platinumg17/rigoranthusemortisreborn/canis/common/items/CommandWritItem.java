package com.platinumg17.rigoranthusemortisreborn.canis.common.items;

import com.platinumg17.rigoranthusemortisreborn.api.apicanis.feature.DataKey;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.feature.EnumMode;
import com.platinumg17.rigoranthusemortisreborn.canis.CanisSkills;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.CanisEntity;
import com.platinumg17.rigoranthusemortisreborn.canis.common.skill.SeizingSnarlSkill;
import com.platinumg17.rigoranthusemortisreborn.canis.common.util.EntityUtil;
import com.platinumg17.rigoranthusemortisreborn.config.ConfigValues;
import com.platinumg17.rigoranthusemortisreborn.core.registry.RigoranthusSoundRegistry;
import com.platinumg17.rigoranthusemortisreborn.magica.common.potions.ModPotions;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ProPercivalalb, edited by PlatinumG17
 */
public class CommandWritItem extends Item {
    public static final String BOOK_MODE_TAG = "mode";
    public static DataKey<List<BlockPos>> POS = DataKey.make();

    public CommandWritItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (player.isShiftKeyDown()) {
            if (!world.isClientSide) {
                if (!stack.hasTag()) {
                    stack.setTag(new CompoundNBT());
//                    stack.getTag().putByte(BOOK_MODE_TAG, (byte)0);
                    stack.getTag().putInt(BOOK_MODE_TAG, 0);
                }
                int mode = stack.getTag().getInt(BOOK_MODE_TAG);
                stack.getTag().putInt(BOOK_MODE_TAG, (mode + 1) % 6);
            }

            return new ActionResult<ItemStack>(ActionResultType.SUCCESS, stack);
        }
        else {
            int mode = 0;
//            stack.getOrCreateTag().putInt(CommandWritItem.BOOK_MODE_TAG, 0);

            if (stack.hasTag() && stack.getTag().contains(BOOK_MODE_TAG, Constants.NBT.TAG_ANY_NUMERIC)) {
                mode = stack.getTag().getByte(BOOK_MODE_TAG);
            }

            List<CanisEntity> caniList = world.getEntitiesOfClass(CanisEntity.class, player.getBoundingBox().inflate(100D, 50D, 100D), canis -> canis.isOwnedBy(player));
            boolean successful = false;

            if (mode == 0) { // Stand
                if (!world.isClientSide) {
                    for (CanisEntity canis : caniList) {
                        canis.setOrderedToSit(false);
                        canis.getNavigation().stop();
                        canis.setTarget(null);
                        if (canis.getMode() == EnumMode.WANDERING) {
                            canis.setMode(EnumMode.DOCILE);
                        }
                        successful = true;
                    }
                    if (ConfigValues.WHISTLE_SOUNDS)
                        world.playSound(null, player.blockPosition(), RigoranthusSoundRegistry.WHISTLE_LONG.get(), SoundCategory.PLAYERS, 0.6F + world.random.nextFloat() * 0.1F, 0.8F + world.random.nextFloat() * 0.2F);
                    if (successful) {
                        player.sendMessage(new TranslationTextComponent("mastersbehest.come"), Util.NIL_UUID);
                    }
                }

                return new ActionResult<ItemStack>(ActionResultType.SUCCESS, stack);
            }
            else if (mode == 1) { // All Follow
                if (!world.isClientSide) {
                    for (CanisEntity canis : caniList) {
                        if (!canis.isInSittingPose() && canis.getMode() != EnumMode.WANDERING) {
                            if (canis.distanceToSqr(canis.getOwner()) > 9) { // Only tp if less than 3 blocks away
                                EntityUtil.tryToTeleportNearEntity(canis, canis.getNavigation(), canis.getOwner(), 2);
                            }
                            successful = true;
                        }
                    }

                    if (ConfigValues.WHISTLE_SOUNDS)
                        world.playSound(null, player.blockPosition(), RigoranthusSoundRegistry.WHISTLE_LONG.get(), SoundCategory.PLAYERS, 0.6F + world.random.nextFloat() * 0.1F, 0.8F + world.random.nextFloat() * 0.2F);

                    if (successful) {
                        player.sendMessage(new TranslationTextComponent("mastersbehest.heel"), Util.NIL_UUID);
                    }
                }

                return new ActionResult<ItemStack>(ActionResultType.SUCCESS, stack);
            }
            else if (mode == 2) { // Stay
                if (!world.isClientSide) {
                    for (CanisEntity canis : caniList) {
                        canis.setOrderedToSit(true);
                        canis.getNavigation().stop();
                        canis.setTarget(null);
                        if (canis.getMode() == EnumMode.WANDERING) {
                            canis.setMode(EnumMode.DOCILE);
                        }
                        successful = true;
                    }

                    if (ConfigValues.WHISTLE_SOUNDS)
                        world.playSound(null, player.blockPosition(), RigoranthusSoundRegistry.WHISTLE_SHORT.get(), SoundCategory.PLAYERS, 0.6F + world.random.nextFloat() * 0.1F, 0.8F + world.random.nextFloat() * 0.2F);

                    if (successful) {
                        player.sendMessage(new TranslationTextComponent("mastersbehest.stay"), Util.NIL_UUID);
                    }
                }
                return new ActionResult<>(ActionResultType.SUCCESS, stack);
            }
            else if (mode == 3) { // Ok
                if (!world.isClientSide) {
                    for (CanisEntity canis : caniList) {
                        if (canis.getMaxHealth() / 2 >= canis.getHealth()) {
                            canis.setOrderedToSit(true);
                            canis.getNavigation().stop();
                            canis.setTarget(null);
                        }
                        else {
                            canis.setOrderedToSit(false);
                            canis.getNavigation().stop();
                            canis.setTarget(null);
                        }
                        successful = true;
                    }
                    if (ConfigValues.WHISTLE_SOUNDS)
                        world.playSound(null, player.blockPosition(), RigoranthusSoundRegistry.WHISTLE_LONG.get(), SoundCategory.PLAYERS, 0.6F + world.random.nextFloat() * 0.1F, 0.4F + world.random.nextFloat() * 0.2F);
                    if (successful) {
                        player.sendMessage(new TranslationTextComponent("mastersbehest.ok"), Util.NIL_UUID);
                    }
                    return new ActionResult<>(ActionResultType.SUCCESS, stack);
                }
            }
            else if (mode == 4) {
                if (!world.isClientSide) {
                    if (ConfigValues.WHISTLE_SOUNDS)
                        world.playSound(null, player.blockPosition(), RigoranthusSoundRegistry.WHISTLE_SHORT.get(), SoundCategory.PLAYERS, 0.6F + world.random.nextFloat() * 0.1F, 0.8F + world.random.nextFloat() * 0.2F);
                }

                return new ActionResult<>(ActionResultType.SUCCESS, stack);
//            } else if (mode == 5) {
//                if (!world.isClientSide) {
//                    if (ConfigValues.WHISTLE_SOUNDS)
//                        world.playSound((PlayerEntity)null, player.blockPosition(), SoundEvents.ARROW_SHOOT, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
//                    this.addPosToStack(player.getItemInHand(hand), player.blockPosition());
//                    CanisBeamEntity canisBeam = new CanisBeamEntity(world, player);
//                    canisBeam.shootFromRotation(player, player.xRot, player.yRot, 0.0F, 2.0F, 1.0F);
//                    world.addFreshEntity(canisBeam);
//                }
//                return new ActionResult<>(ActionResultType.CONSUME, player.getItemInHand(hand));
            } else if (mode == 5) {
                if (!world.isClientSide) {
                    List<CanisEntity> snarlingCani = caniList.stream().filter(canis -> canis.getLevel(CanisSkills.SNARL) > 0).collect(Collectors.toList());
                    if (snarlingCani.isEmpty()) {
                        player.displayClientMessage(new TranslationTextComponent("skill.rigoranthusemortisreborn.snarl.level"), true);
                    } else {
                        List<CanisEntity> cooldownCani = snarlingCani.stream().filter(canis -> canis.getDataOrDefault(SeizingSnarlSkill.COOLDOWN, canis.tickCount) <= canis.tickCount).collect(Collectors.toList());
                        if (cooldownCani.isEmpty()) {
                            player.displayClientMessage(new TranslationTextComponent("skill.rigoranthusemortisreborn.snarl.cooldown"), true);
                        } else {
                            boolean anyHits = false;

                            for (CanisEntity canis : caniList) {
                                int level = canis.getLevel(CanisSkills.SNARL);
                                int snarlCooldown = canis.tickCount;

                                byte damage = (byte)(level > 4 ? level * 2 : level);
                                /*
                                 * If level = 1, set duration to  20 ticks (1 second); level = 2, set duration to 24 ticks (1.2 seconds)
                                 * If level = 3, set duration to 36 ticks (1.8 seconds); If level = 4, set duration to 48 ticks (2.4 seconds)
                                 * If level = max (5), set duration to 70 ticks (3.5 seconds);
                                 */
                                byte effectDuration = (byte)(level > 4 ? level * 14 : level * (level == 1 ? 20 : 12));
                                byte knockback = (byte)level;

                                boolean hit = false;
                                List<LivingEntity> list = canis.level.<LivingEntity>getEntitiesOfClass(LivingEntity.class, canis.getBoundingBox().inflate(level * 4, 4D, level * 4));
                                for (LivingEntity mob : list) {
                                    if (mob instanceof IMob) {
                                        hit = true;
                                        mob.hurt(DamageSource.GENERIC, damage);
                                        mob.addEffect(new EffectInstance(ModPotions.SNARE_EFFECT, effectDuration, 127, false, false));
                                        mob.addEffect(new EffectInstance(Effects.GLOWING, effectDuration, 1, false, false));
                                        mob.push(MathHelper.sin(mob.yRot * (float) Math.PI / 180.0F) * knockback * 0.5F, 0.1D, -MathHelper.cos(mob.yRot * (float) Math.PI / 180.0F) * knockback * 0.5F);
                                    }
                                }
                                if (hit) {
                                    canis.playSound(RigoranthusSoundRegistry.CANIS_AMBIENT.get(), 1.0F, 1.0F);
                                    snarlCooldown += level >= 5 ? 60 : 100;
                                    anyHits = true;
                                } else {
                                    canis.playSound(RigoranthusSoundRegistry.CANIS_HUFF.get(), 1F, 1.2F);
                                    snarlCooldown += level >= 5 ? 30 : 50;
                                }
                                canis.setData(SeizingSnarlSkill.COOLDOWN, snarlCooldown);
                            }
                            if (!anyHits) {
                                player.displayClientMessage(new TranslationTextComponent("skill.rigoranthusemortisreborn.snarl.miss"), true);
                            }
                        }
                    }
                }
                return new ActionResult<>(ActionResultType.SUCCESS, player.getItemInHand(hand));
            }
        }
        return new ActionResult<>(ActionResultType.FAIL, player.getItemInHand(hand));
    }

    public static String getModeName(CompoundNBT tag, int mode){
    switch (mode) {
        case 0:
            return new TranslationTextComponent("item.rigoranthusemortisreborn.whistle.0").getString();
        case 1:
            return new TranslationTextComponent("item.rigoranthusemortisreborn.whistle.1").getString();
        case 2:
            return new TranslationTextComponent("item.rigoranthusemortisreborn.whistle.2").getString();
        case 3:
            return new TranslationTextComponent("item.rigoranthusemortisreborn.whistle.3").getString();
        case 4:
            return new TranslationTextComponent("item.rigoranthusemortisreborn.whistle.4").getString();
        case 5:
            return new TranslationTextComponent("item.rigoranthusemortisreborn.whistle.5").getString();
        default:
            throw new IllegalStateException("Unable to get next Command of " + mode);
    }
//        if(mode == 0)
//            return new TranslationTextComponent("item.rigoranthusemortisreborn.whistle.0").getString();
//        return tag.getString( "item.rigoranthusemortisreborn.whistle." + mode);
    }

    public static String getModeName(CompoundNBT tag){
        return getModeName(tag, getMode(tag));
    }

    public static void setMode(CompoundNBT tag, int mode) {
        tag.putByte(CommandWritItem.BOOK_MODE_TAG, (byte) mode);
    }

    public static int getMode(CompoundNBT tag){
        return tag.getByte(CommandWritItem.BOOK_MODE_TAG);
    }

//    @Override
//    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
//        if(!stack.hasTag())
//            stack.setTag(new CompoundNBT());
//
//        if(!worldIn.isClientSide && worldIn.getGameTime() % 5 == 0 && !stack.hasTag()) {
//            CompoundNBT tag = new CompoundNBT();
//            tag.putByte(CommandWritItem.BOOK_MODE_TAG, (byte) 0);
//            stack.setTag(tag);
//        }
//        super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
//    }
//    public void addPosToStack(ItemStack stackIn, BlockPos posIn) {
//        CompoundNBT tag = stackIn.getOrCreateTag();
//        ListNBT list = tag.getList("patrolPos", Constants.NBT.TAG_COMPOUND);
//        CompoundNBT pos = new CompoundNBT();
//        NBTUtilities.putBlockPos(pos, posIn);
//        list.add(pos);
//        tag.put("patrolPos", list);
//    }
//
//    public List<BlockPos> getPos(ItemStack stackIn) {
//        if (stackIn.hasTag() && stackIn.getTag().contains("patrolPos", Constants.NBT.TAG_LIST)) {
//            ListNBT list = stackIn.getTag().getList("patrolPos", Constants.NBT.TAG_COMPOUND);
//            List<BlockPos> pos = new ArrayList<>(list.size());
//            for (int i = 0; i < list.size(); i++) {
//                pos.add(NBTUtilities.getBlockPos(list.getCompound(i)));
//            }
//            return pos;
//        }
//        return Collections.emptyList();
//    }
//    @Override
//    @OnlyIn(Dist.CLIENT)
//    public void appendHoverText(final ItemStack stack, @Nullable final World world, final List<ITextComponent> tooltip, final ITooltipFlag flag) {
//        super.appendHoverText(stack, world, tooltip, flag);
//        if(stack.hasTag()) {
//            tooltip.add(new StringTextComponent(CommandWritItem.getModeName(stack.getTag())));
//            tooltip.add(new TranslationTextComponent("rigoranthusemortisreborn.whistle.select", KeyBinding.createNameSupplier(REKeyBindings.OPEN_COMMAND_SELECTION.getKeyBinding().getName()).get().getString()));
////            tooltip.add(new TranslationTextComponent("rigoranthusemortisreborn.whistle.inventory", KeyBinding.createNameSupplier(REKeyBindings.OPEN_CANIS_INV.getKeyBinding().getName()).get().getString()));
//        }
//    }
    @Override
    public String getDescriptionId(ItemStack stack) {
        byte mode = 0;

        if (stack.hasTag() && stack.getTag().contains(BOOK_MODE_TAG, Constants.NBT.TAG_ANY_NUMERIC)) {
            mode = stack.getTag().getByte(BOOK_MODE_TAG);
        }
        return this.getDescriptionId() + "." + mode;
    }
    @Override
    public Rarity getRarity(ItemStack stack) {
        return Rarity.UNCOMMON;
    }
}