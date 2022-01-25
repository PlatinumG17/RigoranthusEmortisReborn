package com.platinumg17.rigoranthusemortisreborn.util.climbing.movement;

//import javax.annotation.Nullable;
//import com.google.common.collect.ImmutableSet;
//import com.platinumg17.rigoranthusemortisreborn.util.climbing.IClimberEntity;
//import net.minecraft.block.BlockState;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.MobEntity;
//import net.minecraft.entity.ai.controller.MovementController;
//import net.minecraft.network.DebugPacketSender;
//import net.minecraft.pathfinding.Path;
//import net.minecraft.pathfinding.PathNodeType;
//import net.minecraft.pathfinding.PathPoint;
//import net.minecraft.pathfinding.PathType;
//import net.minecraft.util.Direction;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.math.MathHelper;
//import net.minecraft.util.math.shapes.VoxelShape;
//import net.minecraft.util.math.vector.Vector3d;
//import net.minecraft.world.IBlockReader;
//import net.minecraft.world.World;
//
//public class AdvancedClimberPathNavigator<T extends MobEntity & IClimberEntity> extends AdvancedGroundPathNavigator<T> {
//    protected final IClimberEntity climber;
//
//    protected Direction verticalFacing = Direction.DOWN;
//
//    protected boolean findDirectPathPoints = false;
//
//    public AdvancedClimberPathNavigator(T entity, World worldIn, boolean checkObstructions, boolean canPathWalls, boolean canPathCeiling) {
//        super(entity, worldIn, checkObstructions);
//
//        this.climber = entity;
//
//        if(this.nodeEvaluator instanceof AdvancedWalkNodeProcessor) {
//            AdvancedWalkNodeProcessor processor = (AdvancedWalkNodeProcessor) this.nodeEvaluator;
//            processor.setStartPathOnGround(false);
//            processor.setCanPathWalls(canPathWalls);
//            processor.setCanPathCeiling(canPathCeiling);
//        }
//    }
//
//    @Override
//    protected Vector3d getEntityPosition() {
//        return this.mob.position().add(0, this.mob.getBbHeight() / 2.0f, 0);
//    }
//
//    @Override
//    @Nullable
//    public Path getPathToPos(BlockPos pos, int checkpointRange) {
//        return this.createPath(ImmutableSet.of(pos), 8, false, checkpointRange);
//    }
//
//    @Override
//    @Nullable
//    public Path getPathToEntity(Entity entityIn, int checkpointRange) {
//        return this.createPath(ImmutableSet.of(entityIn.blockPosition()), 16, true, checkpointRange);
//    }
//
//    @Override
//    public void tick() {
//        ++this.totalTicks;
//
//        if(this.hasDelayedRecomputation) {
//            this.updatePath();
//        }
//
//        if(!this.isDone()) {
//            if(this.canUpdatePath()) {
//                this.pathFollow();
//            } else if(this.path != null && !this.path.isFinished()) {
//                Vector3d pos = this.getEntityPosition();
//                Vector3d targetPos = this.path.getPosition(this.mob);
//
//                if(pos.y > targetPos.y && !this.mob.isOnGround() && MathHelper.floor(pos.x) == MathHelper.floor(targetPos.x) && MathHelper.floor(pos.z) == MathHelper.floor(targetPos.z)) {
//                    this.path.incrementPathIndex();
//                }
//            }
//
//            DebugPacketSender.sendPathFindingPacket(this.level, this.mob, this.path, this.maxDistanceToWaypoint);
//
//            if(!this.isDone()) {
//                PathPoint targetPoint = this.path.getPathPointFromIndex(this.path.getNextNodeIndex());
//
//                Direction dir = null;
//
//                if(targetPoint instanceof DirectionalPathPoint) {
//                    dir = ((DirectionalPathPoint) targetPoint).getPathSide();
//                }
//
//                if(dir == null) {
//                    dir = Direction.DOWN;
//                }
//
//                Vector3d targetPos = this.getExactPathingTarget(this.level, targetPoint.asBlockPos(), dir);
//
//                MovementController moveController = this.mob.getMoveHelper();
//
//                if(moveController instanceof ClimberMoveController && targetPoint instanceof DirectionalPathPoint && ((DirectionalPathPoint) targetPoint).getPathSide() != null) {
//                    ((ClimberMoveController) moveController).setWantedPosition(targetPos.x, targetPos.y, targetPos.z, targetPoint.asBlockPos().offset(dir), ((DirectionalPathPoint) targetPoint).getPathSide(), this.speedModifier);
//                } else {
//                    moveController.setWantedPosition(targetPos.x, targetPos.y, targetPos.z, this.speedModifier);
//                }
//            }
//        }
//    }
//
//    public Vector3d getExactPathingTarget(IBlockReader blockaccess, BlockPos pos, Direction dir) {
//        BlockPos offsetPos = pos.offset(dir);
//
//        VoxelShape shape = blockaccess.getBlockState(offsetPos).getCollisionShape(blockaccess, offsetPos);
//
//        Direction.Axis axis = dir.getAxis();
//
//        int sign = dir.getStepX() + dir.getStepY() + dir.getStepZ();
//        double offset = shape.isEmpty() ? sign /*undo offset if no collider*/ : (sign > 0 ? shape.getStart(axis) - 1 : shape.getEnd(axis));
//
//        double marginXZ = 1 - (this.mob.getBbWidth() % 1);
//        double marginY = 1 - (this.mob.getBbHeight() % 1);
//
//        double pathingOffsetXZ = (int)(this.mob.getBbWidth() + 1.0F) * 0.5D;
//        double pathingOffsetY = (int)(this.mob.getBbHeight() + 1.0F) * 0.5D - this.mob.getBbHeight() * 0.5f;
//
//        double x = offsetPos.getX() + pathingOffsetXZ + dir.getStepX() * marginXZ;
//        double y = offsetPos.getY() + pathingOffsetY  + (dir == Direction.DOWN ? -pathingOffsetY : 0.0D) + (dir == Direction.UP ? -pathingOffsetY + marginY : 0.0D);
//        double z = offsetPos.getZ() + pathingOffsetXZ + dir.getStepZ() * marginXZ;
//
//        switch(axis) {
//            default:
//            case X:
//                return new Vector3d(x + offset, y, z);
//            case Y:
//                return new Vector3d(x, y + offset, z);
//            case Z:
//                return new Vector3d(x, y, z + offset);
//        }
//    }
//
//    @Override
//    protected void pathFollow() {
//        Vector3d pos = this.getEntityPosition();
//
//        this.maxDistanceToWaypoint = this.mob.getBbWidth() > 0.75F ? this.mob.getBbWidth() / 2.0F : 0.75F - this.mob.getBbWidth() / 2.0F;
//        float maxDistanceToWaypointY = Math.max(1 /*required for e.g. slabs*/, this.mob.getBbHeight() > 0.75F ? this.mob.getBbHeight() / 2.0F : 0.75F - this.mob.getBbHeight() / 2.0F);
//
//        int sizeX = MathHelper.ceil(this.mob.getBbWidth());
//        int sizeY = MathHelper.ceil(this.mob.getBbHeight());
//        int sizeZ = sizeX;
//
//        Orientation orientation = this.climber.getOrientation();
//        Vector3d upVector = orientation.getGlobal(this.mob.yRot, -90);
//
//        this.verticalFacing = Direction.getNearest((float) upVector.x, (float) upVector.y, (float) upVector.z);
//
//        //Look up to 4 nodes ahead so it doesn't backtrack on positions with multiple path sides when changing/updating path
//        for(int i = 4; i >= 0; i--) {
//            if(this.path.getNextNodeIndex() + i < this.path.getpathLength()) {
//                PathPoint currentTarget = this.path.getPathPointFromIndex(this.path.getNextNodeIndex() + i);
//
//                double dx = Math.abs(currentTarget.x + (int) (this.mob.getBbWidth() + 1.0f) * 0.5f - this.mob.getX());
//                double dy = Math.abs(currentTarget.y - this.mob.getY());
//                double dz = Math.abs(currentTarget.z + (int) (this.mob.getBbWidth() + 1.0f) * 0.5f - this.mob.getZ());
//
//                boolean isWaypointInReach = dx < this.maxDistanceToWaypoint && dy < maxDistanceToWaypointY && dz < this.maxDistanceToWaypoint;
//
//                boolean isOnSameSideAsTarget = false;
//                if(this.canFloat() && (currentTarget.type == PathNodeType.WATER || currentTarget.type == PathNodeType.WATER_BORDER || currentTarget.type == PathNodeType.LAVA)) {
//                    isOnSameSideAsTarget = true;
//                } else if(currentTarget instanceof DirectionalPathPoint) {
//                    Direction targetSide = ((DirectionalPathPoint) currentTarget).getPathSide();
//                    isOnSameSideAsTarget = targetSide == null || this.climber.getGroundDirection().getLeft() == targetSide;
//                } else {
//                    isOnSameSideAsTarget = true;
//                }
//
//                if(isOnSameSideAsTarget && (isWaypointInReach || (i == 0 && this.mob.func_233660_b_(this.path.func_237225_h_().nodeType) && this.isNextTargetInLine(pos, sizeX, sizeY, sizeZ, 1 + i)))) {
//                    this.path.setpathIndex(this.path.getNextNodeIndex() + 1 + i);
//                    break;
//                }
//            }
//        }
//
//        if(this.findDirectPathPoints) {
//            Direction.Axis verticalAxis = this.verticalFacing.getAxis();
//
//            int firstDifferentHeightPoint = this.path.getpathLength();
//
//            switch(verticalAxis) {
//                case X:
//                    for(int i = this.path.getNextNodeIndex(); i < this.path.getpathLength(); ++i) {
//                        if(this.path.getPathPointFromIndex(i).x != Math.floor(pos.x)) {
//                            firstDifferentHeightPoint = i;
//                            break;
//                        }
//                    }
//                    break;
//                case Y:
//                    for(int i = this.path.getNextNodeIndex(); i < this.path.getpathLength(); ++i) {
//                        if(this.path.getPathPointFromIndex(i).y != Math.floor(pos.y)) {
//                            firstDifferentHeightPoint = i;
//                            break;
//                        }
//                    }
//                    break;
//                case Z:
//                    for(int i = this.path.getNextNodeIndex(); i < this.path.getpathLength(); ++i) {
//                        if(this.path.getPathPointFromIndex(i).z != Math.floor(pos.z)) {
//                            firstDifferentHeightPoint = i;
//                            break;
//                        }
//                    }
//                    break;
//            }
//
//            for(int i = firstDifferentHeightPoint - 1; i >= this.path.getNextNodeIndex(); --i) {
//                if(this.isDirectPathBetweenPoints(pos, this.path.getVectorFromIndex(this.mob, i), sizeX, sizeY, sizeZ)) {
//                    this.path.setpathIndex(i);
//                    break;
//                }
//            }
//        }
//
//        this.checkForStuck(pos);
//    }
//
//    private boolean isNextTargetInLine(Vector3d pos, int sizeX, int sizeY, int sizeZ, int offset) {
//        if(this.path.getNextNodeIndex() + offset >= this.path.getpathLength()) {
//            return false;
//        } else {
//            Vector3d currentTarget = Vector3d.atBottomCenterOf(this.path.func_242948_g());
//
//            if(!pos.closerThan(currentTarget, 2.0D)) {
//                return false;
//            } else {
//                Vector3d nextTarget = Vector3d.atBottomCenterOf(this.path.getNodePos(this.path.getNextNodeIndex() + offset));
//                Vector3d targetDir = nextTarget.subtract(currentTarget);
//                Vector3d currentDir = pos.subtract(currentTarget);
//
//                if(targetDir.dot(currentDir) > 0.0D) {
//                    Direction.Axis ax, ay, az;
//                    boolean invertY;
//
//                    switch(this.verticalFacing.getAxis()) {
//                        case X:
//                            ax = Direction.Axis.Z;
//                            ay = Direction.Axis.X;
//                            az = Direction.Axis.Y;
//                            invertY = this.verticalFacing.getStepX() < 0;
//                            break;
//                        default:
//                        case Y:
//                            ax = Direction.Axis.X;
//                            ay = Direction.Axis.Y;
//                            az = Direction.Axis.Z;
//                            invertY = this.verticalFacing.getStepY() < 0;
//                            break;
//                        case Z:
//                            ax = Direction.Axis.Y;
//                            ay = Direction.Axis.Z;
//                            az = Direction.Axis.X;
//                            invertY = this.verticalFacing.getStepZ() < 0;
//                            break;
//                    }
//
//                    //Make sure that the mob can stand at the next point in the same orientation it currently has
//                    return this.isSafeToStandAt(MathHelper.floor(nextTarget.x), MathHelper.floor(nextTarget.y), MathHelper.floor(nextTarget.z), sizeX, sizeY, sizeZ, currentTarget, 0, 0, -1, ax, ay, az, invertY);
//                }
//
//                return false;
//            }
//        }
//    }
//
//    @Override
//    protected boolean canMoveDirectly(Vector3d start, Vector3d end, int sizeX, int sizeY, int sizeZ) {
//        switch(this.verticalFacing.getAxis()) {
//            case X:
//                return this.isDirectPathBetweenPoints(start, end, sizeX, sizeY, sizeZ, Direction.Axis.Z, Direction.Axis.X, Direction.Axis.Y, 0.0D, this.verticalFacing.getStepX() < 0);
//            case Y:
//                return this.isDirectPathBetweenPoints(start, end, sizeX, sizeY, sizeZ, Direction.Axis.X, Direction.Axis.Y, Direction.Axis.Z, 0.0D, this.verticalFacing.getStepY() < 0);
//            case Z:
//                return this.isDirectPathBetweenPoints(start, end, sizeX, sizeY, sizeZ, Direction.Axis.Y, Direction.Axis.Z, Direction.Axis.X, 0.0D, this.verticalFacing.getStepZ() < 0);
//        }
//        return false;
//    }
//
//    protected static double swizzle(Vector3d vec, Direction.Axis axis) {
//        switch(axis) {
//            case X:
//                return vec.x;
//            case Y:
//                return vec.y;
//            case Z:
//                return vec.z;
//        }
//        return 0;
//    }
//
//    protected static int swizzle(int x, int y, int z, Direction.Axis axis) {
//        switch(axis) {
//            case X:
//                return x;
//            case Y:
//                return y;
//            case Z:
//                return z;
//        }
//        return 0;
//    }
//
//    protected static int unswizzle(int x, int y, int z, Direction.Axis ax, Direction.Axis ay, Direction.Axis az, Direction.Axis axis) {
//        Direction.Axis unswizzle;
//        if(axis == ax) {
//            unswizzle = Direction.Axis.X;
//        } else if(axis == ay) {
//            unswizzle = Direction.Axis.Y;
//        } else {
//            unswizzle = Direction.Axis.Z;
//        }
//        return swizzle(x, y, z, unswizzle);
//    }
//
//    protected boolean isDirectPathBetweenPoints(Vector3d start, Vector3d end, int sizeX, int sizeY, int sizeZ, Direction.Axis ax, Direction.Axis ay, Direction.Axis az, double minDotProduct, boolean invertY) {
//        int bx = MathHelper.floor(swizzle(start, ax));
//        int bz = MathHelper.floor(swizzle(start, az));
//        double dx = swizzle(end, ax) - swizzle(start, ax);
//        double dz = swizzle(end, az) - swizzle(start, az);
//        double dSq = dx * dx + dz * dz;
//
//        int by = (int) swizzle(start, ay);
//
//        int sizeX2 = swizzle(sizeX, sizeY, sizeZ, ax);
//        int sizeY2 = swizzle(sizeX, sizeY, sizeZ, ay);
//        int sizeZ2 = swizzle(sizeX, sizeY, sizeZ, az);
//
//        if(dSq < 1.0E-8D) {
//            return false;
//        } else {
//            double d3 = 1.0D / Math.sqrt(dSq);
//            dx = dx * d3;
//            dz = dz * d3;
//            sizeX2 = sizeX2 + 2;
//            sizeZ2 = sizeZ2 + 2;
//
//            if(!this.isSafeToStandAt(
//                    unswizzle(bx, by, bz, ax, ay, az, Direction.Axis.X), unswizzle(bx, by, bz, ax, ay, az, Direction.Axis.Y), unswizzle(bx, by, bz, ax, ay, az, Direction.Axis.Z),
//                    unswizzle(sizeX2, sizeY2, sizeZ2, ax, ay, az, Direction.Axis.X), unswizzle(sizeX2, sizeY2, sizeZ2, ax, ay, az, Direction.Axis.Y), unswizzle(sizeX2, sizeY2, sizeZ2, ax, ay, az, Direction.Axis.Z),
//                    start, dx, dz, minDotProduct, ax, ay, az, invertY)) {
//                return false;
//            } else {
//                sizeX2 = sizeX2 - 2;
//                sizeZ2 = sizeZ2 - 2;
//                double stepX = 1.0D / Math.abs(dx);
//                double stepZ = 1.0D / Math.abs(dz);
//                double relX = (double)bx - swizzle(start, ax);
//                double relZ = (double)bz - swizzle(start, az);
//
//                if(dx >= 0.0D) {
//                    ++relX;
//                }
//
//                if(dz >= 0.0D) {
//                    ++relZ;
//                }
//
//                relX = relX / dx;
//                relZ = relZ / dz;
//                int dirX = dx < 0.0D ? -1 : 1;
//                int dirZ = dz < 0.0D ? -1 : 1;
//                int ex = MathHelper.floor(swizzle(end, ax));
//                int ez = MathHelper.floor(swizzle(end, az));
//                int offsetX = ex - bx;
//                int offsetZ = ez - bz;
//
//                while(offsetX * dirX > 0 || offsetZ * dirZ > 0) {
//                    if(relX < relZ) {
//                        relX += stepX;
//                        bx += dirX;
//                        offsetX = ex - bx;
//                    } else {
//                        relZ += stepZ;
//                        bz += dirZ;
//                        offsetZ = ez - bz;
//                    }
//
//                    if(!this.isSafeToStandAt(
//                            unswizzle(bx, by, bz, ax, ay, az, Direction.Axis.X), unswizzle(bx, by, bz, ax, ay, az, Direction.Axis.Y), unswizzle(bx, by, bz, ax, ay, az, Direction.Axis.Z),
//                            unswizzle(sizeX2, sizeY2, sizeZ2, ax, ay, az, Direction.Axis.X), unswizzle(sizeX2, sizeY2, sizeZ2, ax, ay, az, Direction.Axis.Y), unswizzle(sizeX2, sizeY2, sizeZ2, ax, ay, az, Direction.Axis.Z),
//                            start, dx, dz, minDotProduct, ax, ay, az, invertY)) {
//                        return false;
//                    }
//                }
//
//                return true;
//            }
//        }
//    }
//
//    protected boolean isSafeToStandAt(int x, int y, int z, int sizeX, int sizeY, int sizeZ, Vector3d start, double dx, double dz, double minDotProduct, Direction.Axis ax, Direction.Axis ay, Direction.Axis az, boolean invertY) {
//        int sizeX2 = swizzle(sizeX, sizeY, sizeZ, ax);
//        int sizeZ2 = swizzle(sizeX, sizeY, sizeZ, az);
//
//        int bx = swizzle(x, y, z, ax) - sizeX2 / 2;
//        int bz = swizzle(x, y, z, az) - sizeZ2 / 2;
//
//        int by = swizzle(x, y, z, ay);
//
//        if(!this.isPositionClear(
//                unswizzle(bx, y, bz, ax, ay, az, Direction.Axis.X), unswizzle(bx, y, bz, ax, ay, az, Direction.Axis.Y), unswizzle(bx, y, bz, ax, ay, az, Direction.Axis.Z),
//                sizeX, sizeY, sizeZ, start, dx, dz, minDotProduct, ax, ay, az)) {
//            return false;
//        } else {
//            for(int obx = bx; obx < bx + sizeX2; ++obx) {
//                for(int obz = bz; obz < bz + sizeZ2; ++obz) {
//                    double offsetX = (double)obx + 0.5D - swizzle(start, ax);
//                    double offsetZ = (double)obz + 0.5D - swizzle(start, az);
//
//                    if(offsetX * dx + offsetZ * dz >= minDotProduct) {
//                        PathNodeType nodeTypeBelow = this.nodeEvaluator.getBlockPathType(
//                                this.level,
//                                unswizzle(obx, by + (invertY ? 1 : -1), obz, ax, ay, az, Direction.Axis.X), unswizzle(obx, by + (invertY ? 1 : -1), obz, ax, ay, az, Direction.Axis.Y), unswizzle(obx, by + (invertY ? 1 : -1), obz, ax, ay, az, Direction.Axis.Z),
//                                this.mob, sizeX, sizeY, sizeZ, true, true);
//
//                        if(nodeTypeBelow == PathNodeType.WATER) {
//                            return false;
//                        }
//
//                        if(nodeTypeBelow == PathNodeType.LAVA) {
//                            return false;
//                        }
//
//                        if(nodeTypeBelow == PathNodeType.OPEN) {
//                            return false;
//                        }
//
//                        PathNodeType nodeType = this.nodeEvaluator.getBlockPathType(
//                                this.level,
//                                unswizzle(obx, by, obz, ax, ay, az, Direction.Axis.X), unswizzle(obx, by, obz, ax, ay, az, Direction.Axis.Y), unswizzle(obx, by, obz, ax, ay, az, Direction.Axis.Z),
//                                this.mob, sizeX, sizeY, sizeZ, true, true);
//                        float f = this.mob.getPathfindingMalus(nodeType);
//
//                        if(f < 0.0F || f >= 8.0F) {
//                            return false;
//                        }
//
//                        if(nodeType == PathNodeType.DAMAGE_FIRE || nodeType == PathNodeType.DANGER_FIRE || nodeType == PathNodeType.DAMAGE_OTHER) {
//                            return false;
//                        }
//                    }
//                }
//            }
//
//            return true;
//        }
//    }
//
//    protected boolean isPositionClear(int x, int y, int z, int sizeX, int sizeY, int sizeZ, Vector3d start, double dx, double dz, double minDotProduct, Direction.Axis ax, Direction.Axis ay, Direction.Axis az) {
//        for(BlockPos pos : BlockPos.betweenClosed(new BlockPos(x, y, z), new BlockPos(x + sizeX - 1, y + sizeY - 1, z + sizeZ - 1))) {
//            double offsetX = swizzle(pos.getX(), pos.getY(), pos.getZ(), ax) + 0.5D - swizzle(start, ax);
//            double pffsetZ = swizzle(pos.getX(), pos.getY(), pos.getZ(), az) + 0.5D - swizzle(start, az);
//
//            if(offsetX * dx + pffsetZ * dz >= minDotProduct) {
//                BlockState state = this.level.getBlockState(pos);
//
//                if(!state.isPathfindable(this.level, pos, PathType.LAND)) {
//                    return false;
//                }
//            }
//        }
//
//        return true;
//    }
//}