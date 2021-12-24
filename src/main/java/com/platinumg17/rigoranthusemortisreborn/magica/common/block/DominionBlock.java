package com.platinumg17.rigoranthusemortisreborn.magica.common.block;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.dominion.AbstractDominionTile;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.MagicItemsRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public abstract class DominionBlock extends ModBlock {
    public DominionBlock(String registryName) {
        super(registryName);
    }

    public DominionBlock(Properties properties, String registry) {
        super(properties, registry);
    }

    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(!worldIn.isClientSide && handIn == Hand.MAIN_HAND){
            if(worldIn.getBlockEntity(pos) instanceof AbstractDominionTile){
                AbstractDominionTile tile = (AbstractDominionTile) worldIn.getBlockEntity(pos);
                if(player.getItemInHand(handIn).getItem() == MagicItemsRegistry.bucketOfDominion){
                    if(tile.getMaxDominion() - tile.getCurrentDominion() >= 1000){
                        tile.addDominion(1000);
                        worldIn.playSound(player, pos, SoundEvents.BUCKET_EMPTY, SoundCategory.PLAYERS, 1.0f, 1.0f);
                        if(!player.isCreative())
                            player.setItemInHand(handIn, new ItemStack(Items.BUCKET));
                    }
                    return super.use(state, worldIn, pos, player, handIn, hit);
                }else if(player.getItemInHand(handIn).getItem() instanceof BucketItem && ((BucketItem)player.getItemInHand(handIn).getItem()).getFluid() == Fluids.EMPTY){
                    if(tile.getCurrentDominion() >= 1000) {
                        if(player.getItemInHand(handIn).getCount() == 1) {
                            player.setItemInHand(handIn, new ItemStack(MagicItemsRegistry.bucketOfDominion));
                            player.level.playSound(null, player.blockPosition(), SoundEvents.BUCKET_FILL, SoundCategory.PLAYERS, 1.0f, 1.0f);
                            tile.removeDominion(1000);
                        }else if(player.addItem(new ItemStack(MagicItemsRegistry.bucketOfDominion))) {
                            player.level.playSound(null, player.blockPosition(), SoundEvents.BUCKET_FILL, SoundCategory.PLAYERS, 1.0f, 1.0f);
                            player.getItemInHand(handIn).shrink(1);
                            tile.removeDominion(1000);
                        }
                    }else if(tile.getCurrentDominion() >= 1000 && player.getItemInHand(handIn).getCount() == 1){
                        player.level.playSound(null, player.blockPosition(), SoundEvents.BUCKET_FILL, SoundCategory.PLAYERS, 1.0f, 1.0f);
                        tile.removeDominion(1000);
                        player.setItemInHand(player.getUsedItemHand(),new ItemStack(MagicItemsRegistry.bucketOfDominion));
                    }
                }
            }
        }
        return super.use(state, worldIn, pos, player, handIn, hit);
    }
}