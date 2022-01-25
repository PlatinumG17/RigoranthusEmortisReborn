package com.platinumg17.rigoranthusemortisreborn.util.climbing;

//import java.util.List;
//import javax.annotation.Nullable;
//
//import com.platinumg17.rigoranthusemortisreborn.util.climbing.movement.IAdvancedPathFindingEntity;
//import com.platinumg17.rigoranthusemortisreborn.util.climbing.movement.Orientation;
//import org.apache.commons.lang3.tuple.Pair;
//import net.minecraft.block.BlockState;
//import net.minecraft.util.Direction;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.math.vector.Vector3d;
//
///**
// * @author TheCyberBrick
// * https://github.com/TheCyberBrick/Spiders-2.0/blob/1.16.3/src/main/java/tcb/spiderstpo/common/mob/IClimberEntity.java*/
//public interface IClimberEntity extends IAdvancedPathFindingEntity {
//    public float getAttachmentOffset(Direction.Axis axis, float partialTicks);
//
//    public float getVerticalOffset(float partialTicks);
//
//    public Orientation getOrientation();
//
//    public Orientation calculateOrientation(float partialTicks);
//
//    public void setRenderOrientation(Orientation orientation);
//
//    @Nullable
//    public Orientation getRenderOrientation();
//
//    public float getMovementSpeed();
//
//    public Pair<Direction, Vector3d> getGroundDirection();
//
//    public boolean shouldTrackPathingTargets();
//
//    @Nullable
//    public Vector3d getTrackedMovementTarget();
//
//    @Nullable
//    public List<PathingTarget> getTrackedPathingTargets();
//
//    public boolean canClimbOnBlock(BlockState state, BlockPos pos);
//
//    public boolean canAttachToSide(Direction side);
//
//    public float getBlockSlipperiness(BlockPos pos);
//
//    public boolean canClimberTriggerWalking();
//
//    public boolean canClimbInWater();
//
//    public void setCanClimbInWater(boolean value);
//
//    public boolean canClimbInLava();
//
//    public void setCanClimbInLava(boolean value);
//
//    public float getCollisionsInclusionRange();
//
//    public void setCollisionsInclusionRange(float range);
//
//    public float getCollisionsSmoothingRange();
//
//    public void setCollisionsSmoothingRange(float range);
//
//    public void setJumpDirection(@Nullable Vector3d dir);
//}