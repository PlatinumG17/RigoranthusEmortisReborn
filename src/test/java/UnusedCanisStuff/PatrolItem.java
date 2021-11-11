package UnusedCanisStuff;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.canis.common.util.NBTUtilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import com.platinumg17.rigoranthusemortisreborn.api.feature.DataKey;
import com.platinumg17.rigoranthusemortisreborn.api.interfaces.AbstractCanisEntity;
import com.platinumg17.rigoranthusemortisreborn.api.interfaces.ICanisItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//public class PatrolItem extends Item implements ICanisItem {
//
//    public static DataKey<List<BlockPos>> POS = DataKey.make();
//
//    public PatrolItem(Properties properties) {
//        super(properties);
//    }
//
//    @Override
//    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
//        this.addPosToStack(playerIn.getItemInHand(handIn), playerIn.blockPosition());
//        return ActionResult.pass(playerIn.getItemInHand(handIn));
//    }
//
//    public void addPosToStack(ItemStack stackIn, BlockPos posIn) {
//        CompoundNBT tag = stackIn.getOrCreateTag();
//        ListNBT list = tag.getList("patrolPos", Constants.NBT.TAG_COMPOUND);
//        CompoundNBT pos = new CompoundNBT();
//        NBTUtilities.putBlockPos(pos, posIn);
//        list.add(pos);
//        tag.put("patrolPos", list);
//    }
//
//    public List<BlockPos> getPos(ItemStack stackIn) {
//        if (stackIn.hasTag() && stackIn.getTag().contains("patrolPos", Constants.NBT.TAG_LIST)) {
//            ListNBT list = stackIn.getTag().getList("patrolPos", Constants.NBT.TAG_COMPOUND);
//            List<BlockPos> pos = new ArrayList<>(list.size());
//            for (int i = 0; i < list.size(); i++) {
//                pos.add(NBTUtilities.getBlockPos(list.getCompound(i)));
//            }
//            return pos;
//        }
//        return Collections.emptyList();
//    }
//
//    @Override
//    public ActionResultType processInteract(AbstractCanisEntity canisIn, World worldIn, PlayerEntity playerIn, Hand handIn) {
//        List<BlockPos> pos = getPos(playerIn.getItemInHand(handIn));
//        RigoranthusEmortisReborn.LOGGER.debug("{}", pos);
//        canisIn.setData(POS, pos);
//        return ActionResultType.SUCCESS;
//    }
//}