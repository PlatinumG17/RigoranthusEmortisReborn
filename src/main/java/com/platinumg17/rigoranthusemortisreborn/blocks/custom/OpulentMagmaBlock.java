package com.platinumg17.rigoranthusemortisreborn.blocks.custom;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.MagmaBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class OpulentMagmaBlock extends MagmaBlock {

    public OpulentMagmaBlock() {
        super(AbstractBlock.Properties.of(Material.STONE, MaterialColor.GOLD).lightLevel((p_235452_0_) -> { return 15; }).strength(8f, 10f)
                .harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().sound(SoundType.LANTERN));
    }

    @Override
    public void stepOn(World world, BlockPos pos, Entity entityIn) {
        if (!entityIn.fireImmune() && !(entityIn instanceof PlayerEntity) && !EnchantmentHelper.hasFrostWalker((LivingEntity)entityIn)) {
            entityIn.hurt(DamageSource.HOT_FLOOR, 1.0F);
        }
        super.stepOn(world, pos, entityIn);
    }
}