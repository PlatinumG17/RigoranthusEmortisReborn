package com.platinumg17.rigoranthusemortisreborn.canis.common.skill;

import com.platinumg17.rigoranthusemortisreborn.core.registry.RigoranthusSoundRegistry;
import com.platinumg17.rigoranthusemortisreborn.canis.CanisItems;
import com.platinumg17.rigoranthusemortisreborn.canis.CanisSkills;
import com.platinumg17.rigoranthusemortisreborn.canis.common.util.EntityUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import com.platinumg17.rigoranthusemortisreborn.api.feature.DataKey;
import com.platinumg17.rigoranthusemortisreborn.api.feature.EnumMode;
import com.platinumg17.rigoranthusemortisreborn.api.interfaces.AbstractCanisEntity;
import com.platinumg17.rigoranthusemortisreborn.api.registry.Skill;
import com.platinumg17.rigoranthusemortisreborn.api.registry.SkillInstance;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Predicate;

public class SummonerSkill extends SkillInstance {
    private static DataKey<EntityAISummoner> SUMMONER_AI = DataKey.make();

    public SummonerSkill(Skill SkillIn, int levelIn) {
        super(SkillIn, levelIn);
    }

    @Override
    public void init(AbstractCanisEntity canisIn) {
        if (!canisIn.hasData(SUMMONER_AI)) {
            EntityAISummoner summonerAI = new EntityAISummoner(canisIn, 1.0D, 8F, entity -> !(entity instanceof TameableEntity));
            canisIn.goalSelector.addGoal(7, summonerAI);
            canisIn.setData(SUMMONER_AI, summonerAI);
        }
    }

    public static int getMaxFollowers(int level) {
        switch(level) {
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 4;
            case 4:
                return 8;
            case 5:
                return 16;
            default:
                return 0;
        }
    }

    public static class EntityAISummoner extends Goal {

        protected final AbstractCanisEntity canis;
        private final World world;
        private final double followSpeed;
        private final float maxDist;
        private final PathNavigator canisPathfinder;
        private final Predicate<ItemStack> holdingPred;
        private final Predicate<AnimalEntity> predicate;
        private final Comparator<Entity> sorter;

        private int timeToRecalcPath;
        private LivingEntity owner;
        protected List<AnimalEntity> targets;
        private float oldWaterCost;

        private int MAX_FOLLOW = 5;

        public EntityAISummoner(AbstractCanisEntity canisIn, double speedIn, float range, @Nullable Predicate<AnimalEntity> targetSelector) {
            this.canis = canisIn;
            this.world = canisIn.level;
            this.canisPathfinder = canisIn.getNavigation();
            this.followSpeed = speedIn;
            this.maxDist = range;
            this.predicate = (entity) -> {
                double d0 = EntityUtil.getFollowRange(this.canis);
                if (entity.isInvisible()) {
                    return false;
                }
                else if (targetSelector != null && !targetSelector.test(entity)) {
                    return false;
                } else {
                    return entity.distanceTo(this.canis) > d0 ? false : entity.canSee(this.canis);
                }
            };
            this.holdingPred = (stack) -> {
                return stack.getItem() == CanisItems.WHISTLE.get(); // TODO
            };
            this.sorter = new EntityUtil.Sorter(canisIn);
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            if (this.canis.getMode() != EnumMode.DOCILE) {
                return false;
            } else if (this.canis.getLevel(CanisSkills.SUMMONER) <= 0) {
                return false;
            } else {
                LivingEntity owner = this.canis.getOwner();
                if (owner == null) {
                    return false;
                } else if (owner instanceof PlayerEntity && owner.isSpectator()) {
                    return false;
                } else if (!EntityUtil.isHolding(owner, CanisItems.WHISTLE.get(), (nbt) -> nbt.contains("mode") && nbt.getInt("mode") == 4)) {
                    return false;
                } else {
                    List<AnimalEntity> list = this.world.getEntitiesOfClass(AnimalEntity.class, this.canis.getBoundingBox().inflate(12D, 4.0D, 12D), this.predicate);
                    Collections.sort(list, this.sorter);
                    if (list.isEmpty()) {
                        return false;
                    }
                    else {
                        this.MAX_FOLLOW = SummonerSkill.getMaxFollowers(this.canis.getLevel(CanisSkills.SUMMONER));
                        this.targets = list.subList(0, Math.min(MAX_FOLLOW, list.size()));
                        this.owner = owner;
                        return true;
                    }
                }
            }
        }

        @Override
        public boolean canContinueToUse() {
            if (this.canis.getMode() != EnumMode.DOCILE) {
                return false;
            } else if (this.canis.getLevel(CanisSkills.SUMMONER) <= 0) {
                return false;
            } else if (!EntityUtil.isHolding(owner, CanisItems.WHISTLE.get(), (nbt) -> nbt.contains("mode") && nbt.getInt("mode") == 4)) {
                return false;
            } else if (this.targets.isEmpty()) {
                return false;
            } else {
                return true;
            }
        }

        @Override
        public void start() {
            this.timeToRecalcPath = 0;
            this.oldWaterCost = this.canis.getPathfindingMalus(PathNodeType.WATER);
            this.canis.setPathfindingMalus(PathNodeType.WATER, 0.0F);
        }

        @Override
        public void tick() {
            if (!this.canis.isInSittingPose()) {
                if (--this.timeToRecalcPath <= 0) {
                    this.timeToRecalcPath = 10;
                    // Pick up more animals
                    if (this.targets.size() < MAX_FOLLOW) {
                        List<AnimalEntity> list = this.world.getEntitiesOfClass(AnimalEntity.class,
                                this.canis.getBoundingBox().inflate(16, 4.0D, 16), this.predicate);
                        list.removeAll(this.targets);
                        Collections.sort(list, this.sorter);
                        this.targets.addAll(list.subList(0, Math.min(MAX_FOLLOW - this.targets.size(), list.size())));
                    }

                    Collections.sort(this.targets, this.sorter);
                    boolean teleport = this.owner.distanceTo(this.targets.get(0)) > 16;

                    for (AnimalEntity target : this.targets) {
                        //target.goalSelector.addGoal(0, new );
                        double distanceAway = target.distanceTo(this.owner);
                        target.getLookControl().setLookAt(this.owner, 10.0F, target.getMaxHeadXRot());
                        if (teleport) {
                            if (!target.isLeashed() && !target.isPassenger()) {
                                EntityUtil.tryToTeleportNearEntity(target, target.getNavigation(), this.owner, 4);
                            }
                        }
                        else if (distanceAway >= 5) {
                            if (!target.getNavigation().moveTo(this.owner, 1.2D)) {
                                if (!target.isLeashed() && !target.isPassenger() && distanceAway >= 20) {
                                    EntityUtil.tryToTeleportNearEntity(target, target.getNavigation(), this.owner, 4);
                                }
                            }
                        }
                        else {
                            target.getNavigation().stop();
                        }
                    }

                    Vector3d vec = Vector3d.ZERO;
                    // Calculate average pos of targets
                    for (AnimalEntity target : this.targets) {
                        vec = vec.add(target.position());
                    }
                    vec = vec.scale(1D / this.targets.size());

                    double dPosX = vec.x - this.owner.getX();
                    double dPosZ = vec.z - this.owner.getZ();
                    double size = Math.sqrt(dPosX * dPosX + dPosZ * dPosZ);
                    double j3 = vec.x + dPosX / size * (2 + this.targets.size() / 16);
                    double k3 = vec.z + dPosZ / size * (2 + this.targets.size() / 16);

                    if (teleport) {
                        EntityUtil.tryToTeleportNearEntity(this.canis, this.canisPathfinder, new BlockPos(j3, this.canis.getY(), k3), 1);
                    }

                    this.canis.getLookControl().setLookAt(this.owner, 10.0F, this.canis.getMaxHeadXRot());
                    if (!this.canisPathfinder.moveTo(j3, this.owner.getBoundingBox().minY, k3, this.followSpeed)) {
                        if (this.canis.distanceToSqr(j3, this.owner.getBoundingBox().minY, k3) > 144D) {
                            if (!this.canis.isLeashed() && !this.canis.isPassenger()) {
                                EntityUtil.tryToTeleportNearEntity(this.canis, this.canisPathfinder, new BlockPos(j3, this.canis.getY(), k3), 4);
                            }
                        }
                    }

                    if (this.canis.distanceTo(this.owner) > 40) {
                        EntityUtil.tryToTeleportNearEntity(this.canis, this.canisPathfinder, this.owner, 2);
                    }
                    // Play woof sound
                    if (this.canis.getRandom().nextFloat() < 0.15F) {
                        this.canis.playSound(RigoranthusSoundRegistry.CANIS_HUFF.get(), this.canis.getSoundVolume() + 1.0F, (this.canis.getRandom().nextFloat() - this.canis.getRandom().nextFloat()) * 0.1F + 0.9F);
                    }
                    // Remove dead or faraway entities
                    List<AnimalEntity> toRemove = new ArrayList<>();
                    for (AnimalEntity target : this.targets) {
                        if (!target.isAlive() || target.distanceTo(this.canis) > 25D)
                            toRemove.add(target);
                    }
                    this.targets.removeAll(toRemove);
                }
            }
        }

        @Override
        public void stop() {
            this.owner = null;
            for (AnimalEntity target : this.targets) {
                target.getNavigation().stop();
            }
            this.canisPathfinder.stop();
            this.canis.setPathfindingMalus(PathNodeType.WATER, this.oldWaterCost);
        }
    }
}