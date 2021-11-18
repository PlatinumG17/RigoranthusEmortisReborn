package com.platinumg17.rigoranthusemortisreborn.canis.common.skill;

import com.platinumg17.rigoranthusemortisreborn.canis.CanisAttributes;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.entity.AbstractCanisEntity;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.registry.Skill;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.registry.SkillInstance;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import java.util.UUID;

public class  CavalierSkill extends SkillInstance {

    private static final UUID CAVALIER_JUMP = UUID.fromString("7f338124-f223-4630-8515-70ee0bfbc653");

    public CavalierSkill(Skill SkillIn, int levelIn) {
        super(SkillIn, levelIn);
    }

    @Override
    public void init(AbstractCanisEntity canisIn) {
        canisIn.setAttributeModifier(CanisAttributes.JUMP_POWER.get(), CAVALIER_JUMP, this::createSpeedModifier);
    }

    @Override
    public void set(AbstractCanisEntity canisIn, int level) {
        canisIn.setAttributeModifier(CanisAttributes.JUMP_POWER.get(), CAVALIER_JUMP, this::createSpeedModifier);
    }

    public AttributeModifier createSpeedModifier(AbstractCanisEntity canisIn, UUID uuidIn) {
        if (this.level() > 0) {
            double speed = 0.06D * this.level();

            if (this.level() >= 5) {
                speed += 0.04D;
            }
            return new AttributeModifier(uuidIn, "Cavalier", speed, AttributeModifier.Operation.ADDITION);
        }
        return null;
    }

    @Override
    public ActionResultType processInteract(AbstractCanisEntity canisIn, World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack stack = playerIn.getItemInHand(handIn);

        if (stack.isEmpty()) { // Held item
            if (canisIn.canInteract(playerIn) && this.level() > 0) { // Canis
                if (playerIn.getVehicle() == null && !playerIn.isOnGround()) { // Player
                    if (!canisIn.level.isClientSide) {
                        canisIn.setOrderedToSit(false);
                        playerIn.yRot = canisIn.yRot;
                        playerIn.xRot = canisIn.xRot;
                        playerIn.startRiding(canisIn);
                    }
                    return ActionResultType.SUCCESS;
                }
            }
        }
        return ActionResultType.PASS;
    }

    @Override
    public void livingTick(AbstractCanisEntity canis) {
        if (canis.isVehicle() && canis.getCanisHunger() < 1) {
            canis.getControllingPassenger().sendMessage(new TranslationTextComponent("skill.rigoranthusemortisreborn.cavalier.exhausted", canis.getName()), canis.getUUID());
            canis.ejectPassengers();
        }
    }

    @Override
    public ActionResult<Integer> hungerTick(AbstractCanisEntity canisIn, int hungerTick) {
        if (canisIn.isControlledByLocalInstance()) {
            hungerTick += this.level() < 5 ? 3 : 1;
            return ActionResult.success(hungerTick);
        }
        return ActionResult.pass(hungerTick);
    }

    @Override
    public ActionResult<Float> calculateFallDistance(AbstractCanisEntity canisIn, float distance) {
        if (this.level() >= 5) {
            return ActionResult.success(distance - 1F);
        }
        return ActionResult.pass(0F);
    }

    @Override
    public ActionResultType hitByEntity(AbstractCanisEntity canisIn, Entity entity) {
        // If the attacking entity is riding block
        return canisIn.isPassengerOfSameVehicle(entity) ? ActionResultType.SUCCESS : ActionResultType.PASS;
    }
}