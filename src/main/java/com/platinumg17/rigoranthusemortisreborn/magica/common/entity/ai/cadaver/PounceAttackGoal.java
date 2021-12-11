package com.platinumg17.rigoranthusemortisreborn.magica.common.entity.ai.cadaver;

//import com.platinumg17.rigoranthusemortisreborn.entity.mobs.SunderedCadaverEntity;
//import com.platinumg17.rigoranthusemortisreborn.magica.common.entity.ai.AnimatedAttackGoal;
//import net.minecraft.entity.LivingEntity;
//import net.minecraft.util.math.MathHelper;
//
//import java.util.function.Supplier;
//
//public class PounceAttackGoal extends AnimatedAttackGoal {
//
//    SunderedCadaverEntity cadaver;
//    public PounceAttackGoal(SunderedCadaverEntity entity, boolean followUnseen, Supplier<Boolean> canAttack, int animationID, int animationLength, int attackRange) {
//        super(entity, followUnseen, canAttack, animationID, animationLength, attackRange, 1.2f);
//        this.cadaver = entity;
//    }
//
//    @Override
//    public void start() {
//        super.start();
//    }
//
//    @Override
//    public void stop() {
//        super.stop();
//        cadaver.getEntityData().set(SunderedCadaverEntity.POUNCING, false);
//    }
//
//    @Override
//    protected void attack(LivingEntity target) {
//        super.attack(target);
//        target.knockback(1.2F, MathHelper.sin(cadaver.yRot * ((float)Math.PI / 180F)), -MathHelper.cos(cadaver.yRot * ((float)Math.PI / 180F)));
//        cadaver.pounceCooldown = 60;
//    }
//
//    @Override
//    public void onArrive() {
//        super.onArrive();
//        cadaver.getEntityData().set(SunderedCadaverEntity.POUNCING, true);
//    }
//
//    @Override
//    public void look(LivingEntity entity) { }
//}