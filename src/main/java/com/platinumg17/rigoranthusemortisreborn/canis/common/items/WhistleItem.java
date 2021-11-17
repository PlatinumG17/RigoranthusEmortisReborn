package com.platinumg17.rigoranthusemortisreborn.canis.common.items;

import com.platinumg17.rigoranthusemortisreborn.canis.common.util.EntityUtil;
import com.platinumg17.rigoranthusemortisreborn.config.ConfigValues;
import com.platinumg17.rigoranthusemortisreborn.core.registry.RigoranthusSoundRegistry;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.CanisEntity;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.CanisBeamEntity;
import com.platinumg17.rigoranthusemortisreborn.canis.CanisSkills;
import com.platinumg17.rigoranthusemortisreborn.canis.common.skill.TempestSkill;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import com.platinumg17.rigoranthusemortisreborn.api.feature.EnumMode;
import java.util.List;
import java.util.stream.Collectors;

public class WhistleItem extends Item {

    public WhistleItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (player.isShiftKeyDown()) {
            if (!world.isClientSide) {
                if (!stack.hasTag()) {
                    stack.setTag(new CompoundNBT());
                    stack.getTag().putByte("mode", (byte)0);
                }

                int mode = stack.getTag().getInt("mode");
                stack.getTag().putInt("mode", (mode + 1) % 7);
            }

            return new ActionResult<ItemStack>(ActionResultType.SUCCESS, stack);
        }
        else {
            byte mode = 0;

            if (stack.hasTag() && stack.getTag().contains("mode", Constants.NBT.TAG_ANY_NUMERIC)) {
                mode = stack.getTag().getByte("mode");
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
            else if (mode == 1) { // Heel
                if (!world.isClientSide) {
                    for (CanisEntity canis : caniList) {
                        if (!canis.isInSittingPose() && canis.getMode() != EnumMode.WANDERING) {
                            if (canis.distanceToSqr(canis.getOwner()) > 9) { // Only heel if less than 3 blocks away
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
            } else if (mode == 5) {
                if (!world.isClientSide) {
                    if (ConfigValues.WHISTLE_SOUNDS)
                        world.playSound((PlayerEntity)null, player.blockPosition(), SoundEvents.ARROW_SHOOT, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));

                    CanisBeamEntity canisBeam = new CanisBeamEntity(world, player);
                    canisBeam.shootFromRotation(player, player.xRot, player.yRot, 0.0F, 2.0F, 1.0F);
                    world.addFreshEntity(canisBeam);
                }

                return new ActionResult<>(ActionResultType.CONSUME, player.getItemInHand(hand));
            } else if (mode == 6) {
                if (!world.isClientSide) {
                    List<CanisEntity> tempestCani = caniList.stream().filter(canis -> canis.getLevel(CanisSkills.TEMPEST) > 0).collect(Collectors.toList());
                    if (tempestCani.isEmpty()) {
                        player.displayClientMessage(new TranslationTextComponent("skill.rigoranthusemortisreborn.tempest.level"), true);
                    } else {
                        List<CanisEntity> cooldownCani = tempestCani.stream().filter(canis -> canis.getDataOrDefault(TempestSkill.COOLDOWN, canis.tickCount) <= canis.tickCount).collect(Collectors.toList());
                        if (cooldownCani.isEmpty()) {
                            player.displayClientMessage(new TranslationTextComponent("skill.rigoranthusemortisreborn.tempest.cooldown"), true);
                        } else {
                            boolean anyHits = false;

                            for (CanisEntity canis : caniList) {
                                int level = canis.getLevel(CanisSkills.TEMPEST);
                                int tempestCooldown = canis.tickCount;

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
                                        mob.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, effectDuration, 127, false, false));
                                        mob.addEffect(new EffectInstance(Effects.GLOWING, effectDuration, 1, false, false));
                                        mob.push(MathHelper.sin(mob.yRot * (float) Math.PI / 180.0F) * knockback * 0.5F, 0.1D, -MathHelper.cos(mob.yRot * (float) Math.PI / 180.0F) * knockback * 0.5F);
                                    }
                                }
                                if (hit) {
                                    canis.playSound(RigoranthusSoundRegistry.CANIS_HUFF.get(), 0.7F, 1.0F);
                                    tempestCooldown += level >= 5 ? 60 : 100;
                                    anyHits = true;
                                } else {
                                    canis.playSound(RigoranthusSoundRegistry.CANIS_AMBIENT.get(), 1F, 1.2F);
                                    tempestCooldown += level >= 5 ? 30 : 50;
                                }

                                canis.setData(TempestSkill.COOLDOWN, tempestCooldown);
                            }

                            if (!anyHits) {
                                player.displayClientMessage(new TranslationTextComponent("skill.rigoranthusemortisreborn.tempest.miss"), true);
                            }
                        }
                    }
                }
                return new ActionResult<>(ActionResultType.SUCCESS, player.getItemInHand(hand));
            }

            //world.playSound(null, player.getPosition(), RigoranthusSoundRegistry.WHISTLE_LONG, SoundCategory.PLAYERS, 0.8F, 0.8F + world.rand.nextFloat() * 0.2F);
            //world.playSound(null, player.getPosition(), RigoranthusSoundRegistry.WHISTLE_SHORT, SoundCategory.PLAYERS, 0.8F, 0.6F + world.rand.nextFloat() * 0.2F);
        }
        return new ActionResult<>(ActionResultType.FAIL, player.getItemInHand(hand));
    }

    @Override
    public String getDescriptionId(ItemStack stack) {
        byte mode = 0;

        if (stack.hasTag() && stack.getTag().contains("mode", Constants.NBT.TAG_ANY_NUMERIC)) {
            mode = stack.getTag().getByte("mode");
        }
        return this.getDescriptionId() + "." + mode;
    }
    @Override
    public Rarity getRarity(ItemStack stack) {
        return Rarity.UNCOMMON;
    }
}
