package UnusedCanisStuff;

//import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
//import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.CanisEntity;
//import com.platinumg17.rigoranthusemortisreborn.canis.common.items.PatrolItem;
//import net.minecraft.entity.ai.goal.Goal;
//import net.minecraft.pathfinding.PathNavigator;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.math.MathHelper;
//import com.platinumg17.rigoranthusemortisreborn.api.feature.EnumMode;
//
//import java.util.EnumSet;
//import java.util.List;

//public class PatrolAreaGoal extends Goal {
//
//    public final CanisEntity canis;
//    private final PathNavigator navigator;
//    public int index;
//    private int timeToRecalcPath;
//
//    public PatrolAreaGoal(CanisEntity canisIn) {
//        this.canis = canisIn;
//        this.navigator = canisIn.getNavigation();
//        this.setFlags(EnumSet.of(Flag.MOVE));
//    }
//
//    @Override
//    public boolean canUse() {
//        return this.canis.getMode() == EnumMode.PATROL && this.canis.getTarget() == null && !this.canis.getData(PatrolItem.POS).isEmpty();
//    }
//
//    @Override
//    public boolean canContinueToUse() {
//        return this.canis.getMode() == EnumMode.PATROL && this.canis.getTarget() == null && !this.canis.getData(PatrolItem.POS).isEmpty();
//    }
//
//    @Override
//    public void start() {
//        this.timeToRecalcPath = 0;
//        this.index = 0;
//    }
//
//    @Override
//    public void stop() {
//        this.canis.getNavigation().stop();
//    }
//
//    @Override
//    public void tick() {
//        if (!this.canis.isInSittingPose()) {
//            if (--this.timeToRecalcPath <= 0) {
//                this.timeToRecalcPath = 10;
//                List<BlockPos> patrolPos = this.canis.getData(PatrolItem.POS);
//                this.index = MathHelper.clamp(this.index, 0, patrolPos.size() - 1);
//                BlockPos pos = patrolPos.get(this.index);
//                RigoranthusEmortisReborn.LOGGER.info("Update" + this.index);
//                if (this.canis.blockPosition().closerThan(pos, 2D) || !this.navigator.moveTo(pos.getX(), pos.getY(), pos.getZ(), 0.8D)) {
//                    ++this.index;
//                    this.index %= patrolPos.size();
//                }
//            }
//        }
//    }
//}