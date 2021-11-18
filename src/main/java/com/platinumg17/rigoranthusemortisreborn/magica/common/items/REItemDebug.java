package com.platinumg17.rigoranthusemortisreborn.magica.common.items;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.event.EventQueue;
import com.platinumg17.rigoranthusemortisreborn.magica.common.network.Networking;
import com.platinumg17.rigoranthusemortisreborn.magica.common.network.PacketTimedEvent;
import com.platinumg17.rigoranthusemortisreborn.magica.common.event.timed.EruptionEvent;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.util.BlockUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class REItemDebug extends ModItem{
    public REItemDebug() {
        super(new Item.Properties());
        setRegistryName("debug");
    }

    @Override
    public ActionResultType useOn(ItemUseContext context) {
        if(!context.getLevel().isClientSide){

            for(BlockPos p : BlockPos.betweenClosed(context.getClickedPos().immutable().east(20).north(20), context.getClickedPos().immutable().south(20).west(20))){
                if(context.getLevel().random.nextFloat() < 0.03) {
                    double distance = BlockUtil.distanceFrom(p, context.getClickedPos());
                    int time = (int) (40 + distance * 5 + context.getLevel().random.nextInt(10));
                    EruptionEvent event = new EruptionEvent(context.getLevel(), p.immutable(), time, (int) (distance*2));
                    EventQueue.getServerInstance().addEvent(event);
                    Networking.sendToNearby(context.getLevel(), context.getClickedPos(), new PacketTimedEvent(event.serialize(new CompoundNBT())));
                }
            }
        }
        return super.useOn(context);
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity playerIn, Hand handIn) {

        return ActionResult.success(playerIn.getItemInHand(handIn));
    }
}