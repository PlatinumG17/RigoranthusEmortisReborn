package com.platinumg17.rigoranthusemortisreborn.util.climbing.movement;

//import javax.annotation.Nullable;
//import com.platinumg17.rigoranthusemortisreborn.util.climbing.IClimberEntity;
//import net.minecraft.entity.MobEntity;
//import net.minecraft.entity.ai.controller.JumpController;
//import net.minecraft.util.math.vector.Vector3d;
//
//public class ClimberJumpController<T extends MobEntity & IClimberEntity> extends JumpController {
//    protected final T climber;
//
//    @Nullable
//    protected Vector3d dir;
//
//    public ClimberJumpController(T mob) {
//        super(mob);
//        this.climber = mob;
//    }
//
//    @Override
//    public void jump() {
//        this.setJumping(null);
//    }
//
//    public void setJumping(Vector3d dir) {
//        super.jump();
//        this.dir = dir;
//    }
//
//    @Override
//    public void tick() {
//        this.climber.setJumping(this.jump);
//        if(this.jump) {
//            this.climber.setJumpDirection(this.dir);
//        } else if(this.dir == null) {
//            this.climber.setJumpDirection(null);
//        }
//        this.jump = false;
//    }
//}