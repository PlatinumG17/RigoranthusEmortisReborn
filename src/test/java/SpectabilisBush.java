//import com.platinumg17.rigoranthusemortisreborn.core.init.ItemInit;
//import com.platinumg17.rigoranthusemortisreborn.core.init.Registration;
//import net.minecraft.block.*;
//import net.minecraft.item.BlockItemUseContext;
//import net.minecraft.item.Item;
//import net.minecraft.state.DirectionProperty;
//import net.minecraft.state.StateContainer;
//import net.minecraft.state.properties.BlockStateProperties;
//import net.minecraft.util.Direction;
//import javax.annotation.Nullable;
//
//public class SpectabilisBush extends StemGrownBlock {
//    public static final DirectionProperty FACING = BlockStateProperties.FACING;
//
//    public SpectabilisBush(Properties properties) {
//        super(properties);
//        registerDefaultState(defaultBlockState().setValue(FACING, Direction.UP));
//    }
//    @Override protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {builder.add(FACING);}
//
//    @Nullable
//    @Override
//    public BlockState getStateForPlacement(BlockItemUseContext context) {
//        Direction direction = context.getNearestLookingDirection();
//        return this.defaultBlockState().setValue(FACING, direction);
//    }
//    @Override public StemBlock getStem() {return (StemBlock) Registration.SPECTABILIS_STEM.get();}
//    @Override public AttachedStemBlock getAttachedStem() {return (AttachedStemBlock) Registration.ATTACHED_SPECTABILIS_STEM.get();}
//
//    public static class AttachedStem extends AttachedStemBlock {
//        public AttachedStem(StemGrownBlock crop, Properties properties) {
//            super(crop, properties);
//        }
//        @Override
//        protected Item getSeedItem() {
//            return ItemInit.BILIS_BERRY.get();
//        }
//    }
//
//    public static class Stem extends StemBlock {
//        public Stem(StemGrownBlock crop, Properties properties) {
//            super(crop, properties);
//        }
//        @Nullable
//        @Override
//        protected Item getSeedItem() {return ItemInit.BILIS_BERRY.get();}
//    }
//}
