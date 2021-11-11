package com.platinumg17.rigoranthusemortisreborn.items.weapons.type.projectiles;

import com.minecraftabnormals.abnormals_core.core.util.item.filling.TargetedItemGroupFiller;
import com.platinumg17.rigoranthusemortisreborn.entity.item.BoneArrowEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

public class BoneArrowItem extends ArrowItem {
    private static final TargetedItemGroupFiller FILLER = new TargetedItemGroupFiller(() -> Items.SPECTRAL_ARROW);

    public BoneArrowItem(Properties builder) {
        super(builder);
    }

    @Override
    public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {
        return new BoneArrowEntity(worldIn, shooter);
    }

    @Override
    public boolean isInfinite(ItemStack stack, ItemStack bow, PlayerEntity player) {
        return false;
    }

    @Override
    public void fillItemCategory(ItemGroup group, NonNullList<ItemStack> items) {
        FILLER.fillItem(this, group, items);
    }
}
