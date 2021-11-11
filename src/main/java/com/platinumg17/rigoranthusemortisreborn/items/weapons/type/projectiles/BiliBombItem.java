package com.platinumg17.rigoranthusemortisreborn.items.weapons.type.projectiles;

import com.platinumg17.rigoranthusemortisreborn.canis.common.SpecializedEntityTypes;
import com.platinumg17.rigoranthusemortisreborn.entity.item.BiliBombEntitiy;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class BiliBombItem extends Item {
    public BiliBombItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack item = playerIn.getItemInHand(handIn);
        if(!playerIn.isCreative()) {
            item.shrink(1);
        }
        worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.TNT_PRIMED, SoundCategory.NEUTRAL, 1.0F, 1.0F);

        if(!worldIn.isClientSide) {
            BiliBombEntitiy bomb = new BiliBombEntitiy(SpecializedEntityTypes.BILI_BOMB.get(), playerIn, worldIn, playerIn.abilities.mayBuild);
            bomb.setItem(item);
            bomb.shootFromRotation(playerIn, playerIn.xRot, playerIn.yRot, -20.0F, 0.7F, 1.0F);
            worldIn.addFreshEntity(bomb);
        }
        playerIn.awardStat(Stats.ITEM_USED.get(this));
        return ActionResult.success(item);
    }
}
