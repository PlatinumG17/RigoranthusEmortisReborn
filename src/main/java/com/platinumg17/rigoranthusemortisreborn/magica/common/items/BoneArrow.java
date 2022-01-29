package com.platinumg17.rigoranthusemortisreborn.magica.common.items;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.entity.item.BoneArrowEntity;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.MagicItemsRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BoneArrow extends ArrowItem {

    public BoneArrow() {
        super(MagicItemsRegistry.defaultItemProperties());
        setRegistryName("bone_arrow");
    }

    @Override
    public AbstractArrowEntity createArrow(World world, ItemStack stack, LivingEntity shooter) {
        BoneArrowEntity boneArrow = new BoneArrowEntity(world, shooter);
        if(!(shooter instanceof PlayerEntity))
            return super.createArrow(world, stack, shooter);
        return boneArrow;
    }

//    @Override
//    public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
//        tooltip.add(new TranslationTextComponent("rigoranthusemortisreborn.bone_arrow.desc"));
//    }
}
