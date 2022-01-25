package com.platinumg17.rigoranthusemortisreborn.util.climbing.movement;

//import com.platinumg17.rigoranthusemortisreborn.util.climbing.IClimberEntity;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.MobEntity;
//import net.minecraft.pathfinding.Path;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.World;
//
//public class BetterSpiderPathNavigator<T extends MobEntity & IClimberEntity> extends AdvancedClimberPathNavigator<T> {
//    private boolean useVanillaBehaviour;
//    private BlockPos targetPosition;
//
//    public BetterSpiderPathNavigator(T entity, World worldIn, boolean useVanillaBehaviour) {
//        super(entity, worldIn, false, true, true);
//        this.useVanillaBehaviour = useVanillaBehaviour;
//    }
//
//    @Override
//    public Path getPathToPos(BlockPos pos, int p_179680_2_) {
//        this.targetPosition = pos;
//        return super.getPathToPos(pos, p_179680_2_);
//    }
//
//    @Override
//    public Path getPathToEntity(Entity entityIn, int p_75494_2_) {
//        this.targetPosition = entityIn.blockPosition();
//        return super.getPathToEntity(entityIn, p_75494_2_);
//    }
//
//    @Override
//    public boolean tryMoveToEntityLiving(Entity entityIn, double speedIn) {
//        Path path = this.getPathToEntity(entityIn, 0);
//        if(path != null) {
//            return this.setPath(path, speedIn);
//        } else {
//            this.targetPosition = entityIn.blockPosition();
//            this.speedModifier = speedIn;
//            return true;
//        }
//    }
//
//    @Override
//    public void tick() {
//        if(!this.isDone()) {
//            super.tick();
//        } else {
//            if(this.targetPosition != null && this.useVanillaBehaviour) {
//                // FORGE: Fix MC-94054
//                if(!this.targetPosition.withinDistance(this.mob.position(), Math.max((double) this.mob.getBbWidth(), 1.0D)) && (!(this.mob.getY() > (double) this.targetPosition.getY()) || !(new BlockPos((double) this.targetPosition.getX(), this.mob.getY(), (double) this.targetPosition.getZ())).withinDistance(this.mob.position(), Math.max((double) this.mob.getBbWidth(), 1.0D)))) {
//                    this.mob.getMoveHelper().setMoveTo((double) this.targetPosition.getX(), (double) this.targetPosition.getY(), (double) this.targetPosition.getZ(), this.speedModifier);
//                } else {
//                    this.targetPosition = null;
//                }
//            }
//
//        }
//    }
//}