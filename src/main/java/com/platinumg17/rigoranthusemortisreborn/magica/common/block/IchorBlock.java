package com.platinumg17.rigoranthusemortisreborn.magica.common.block;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.dominion.AbstractIchorTile;
import com.platinumg17.rigoranthusemortisreborn.core.init.ItemInit;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public abstract class IchorBlock extends ModBlock {
    public IchorBlock(String registryName) {
        super(registryName);
    }

    public IchorBlock(Properties properties, String registry) {
        super(properties, registry);
    }

    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(!worldIn.isClientSide && handIn == Hand.MAIN_HAND){
            if(worldIn.getBlockEntity(pos) instanceof AbstractIchorTile){
                AbstractIchorTile tile = (AbstractIchorTile) worldIn.getBlockEntity(pos);
                if(player.getItemInHand(handIn).getItem() == ItemInit.BUCKET_OF_CADAVEROUS_ICHOR.get()){
                    if(tile.getMaxIchor() - tile.getCurrentIchor() >= 1000){
                        tile.addIchor(1000);
                        player.level.playSound(null, player.blockPosition(), SoundEvents.BUCKET_EMPTY, SoundCategory.PLAYERS, 1.0f, 1.0f);
                        if(!player.isCreative())
                            player.setItemInHand(handIn, new ItemStack(Items.BUCKET));
                    }
                    return super.use(state, worldIn, pos, player, handIn, hit);
                }else if(player.getItemInHand(handIn).getItem() instanceof BucketItem && ((BucketItem)player.getItemInHand(handIn).getItem()).getFluid() == Fluids.EMPTY){
                    if(tile.getCurrentIchor() >= 1000){
                        if(player.getItemInHand(handIn).getCount() == 1){
                            player.setItemInHand(handIn, new ItemStack(ItemInit.BUCKET_OF_CADAVEROUS_ICHOR.get()));
                            player.level.playSound(null, player.blockPosition(), SoundEvents.BUCKET_FILL, SoundCategory.PLAYERS, 1.0f, 1.0f);
                            tile.removeIchor(1000);
                        }else if(player.addItem(new ItemStack(ItemInit.BUCKET_OF_CADAVEROUS_ICHOR.get()))) {
                            player.level.playSound(null, player.blockPosition(), SoundEvents.BUCKET_FILL, SoundCategory.PLAYERS, 1.0f, 1.0f);
                            player.getItemInHand(handIn).shrink(1);
                            tile.removeIchor(1000);
                        }
                    }else if(tile.getCurrentIchor() >= 1000 && player.getItemInHand(handIn).getCount() == 1){
                        player.level.playSound(null, player.blockPosition(), SoundEvents.BUCKET_FILL, SoundCategory.PLAYERS, 1.0f, 1.0f);
                        tile.removeIchor(1000);
                        player.setItemInHand(player.getUsedItemHand(),new ItemStack(ItemInit.BUCKET_OF_CADAVEROUS_ICHOR.get()));
                    }
                }
            }
        }
        return super.use(state, worldIn, pos, player, handIn, hit);
    }
}