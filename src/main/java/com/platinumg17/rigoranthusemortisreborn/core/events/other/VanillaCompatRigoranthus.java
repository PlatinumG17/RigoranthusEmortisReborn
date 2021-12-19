package com.platinumg17.rigoranthusemortisreborn.core.events.other;

import com.minecraftabnormals.abnormals_core.core.util.DataUtil;
import com.platinumg17.rigoranthusemortisreborn.blocks.DecorativeOrStorageBlocks;
import com.platinumg17.rigoranthusemortisreborn.blocks.BuildingBlockInit;
import com.platinumg17.rigoranthusemortisreborn.core.init.ItemInit;
import com.platinumg17.rigoranthusemortisreborn.core.init.Registration;
import com.platinumg17.rigoranthusemortisreborn.entity.item.BoneArrowEntity;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.BlockRegistry;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.MagicItemsRegistry;
import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.*;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class VanillaCompatRigoranthus {

    private static final IDispenseItemBehavior BUCKET_DISPENSE_BEHAVIOR = new DefaultDispenseItemBehavior() {
        @Nonnull
        @Override
        public ItemStack execute(@Nonnull IBlockSource source, @Nonnull ItemStack stack) {
            World world = source.getLevel();
            BucketItem bucket = (BucketItem) stack.getItem();
            BlockPos pos = source.getPos().relative(source.getBlockState().getValue(DispenserBlock.FACING));
            if (bucket.emptyBucket(null, world, pos, null)) {
                bucket.checkExtraContent(world, stack, pos);
                return new ItemStack(Items.BUCKET);
            }
            return super.execute(source, stack);
        }
    };

    public static void registerDispenserBehaviors() {
        DispenserBlock.registerBehavior(ItemInit.BUCKET_OF_CADAVEROUS_ICHOR.get(), BUCKET_DISPENSE_BEHAVIOR);

        DispenserBlock.registerBehavior(MagicItemsRegistry.BONE_ARROW, new ProjectileDispenseBehavior() {
            protected ProjectileEntity getProjectile(World worldIn, IPosition position, ItemStack stackIn) {
                return new BoneArrowEntity(worldIn, position.x(), position.y(), position.z());
            }
        });
    }

    public static void registerCompostables() {
        // Compostable
        DataUtil.registerCompostable(BlockRegistry.AZULOREAL_LEAVES, 0.3F);
        DataUtil.registerCompostable(BlockRegistry.AZULOREAL_SAPLING, 0.3F);
        DataUtil.registerCompostable(DecorativeOrStorageBlocks.AZULOREAL_LEAF_CARPET.get(), 0.3F);

        DataUtil.registerCompostable(BlockRegistry.JESSIC_LEAVES, 0.3F);
        DataUtil.registerCompostable(BlockRegistry.JESSIC_SAPLING, 0.3F);
        DataUtil.registerCompostable(DecorativeOrStorageBlocks.JESSIC_LEAF_CARPET.get(), 0.3F);

        DataUtil.registerCompostable(BlockRegistry.AZULOREAL_ORCHID, 0.65F);
        DataUtil.registerCompostable(BlockRegistry.IRIDESCENT_SPROUTS, 0.65F);
        DataUtil.registerCompostable(BlockRegistry.LISIANTHUS, 0.65F);

        DataUtil.registerCompostable(BlockRegistry.SPECTABILIS_BUSH, 0.65F);
        DataUtil.registerCompostable(BlockRegistry.DOMINION_BERRY_BUSH, 0.65F);
    }
    public static void registerFlammables() {
        // Flammability
        DataUtil.registerFlammable(BlockRegistry.AZULOREAL_LEAVES, 30, 60);
        DataUtil.registerFlammable(BlockRegistry.AZULOREAL_LOG, 5, 5);
        DataUtil.registerFlammable(BlockRegistry.STRIPPED_AZULOREAL_LOG, 5, 5);
        DataUtil.registerFlammable(BlockRegistry.AZULOREAL_WOOD, 5, 5);
        DataUtil.registerFlammable(BlockRegistry.STRIPPED_AZULOREAL_WOOD, 5, 5);
        DataUtil.registerFlammable(DecorativeOrStorageBlocks.AZULOREAL_PLANKS.get(), 5, 20);
        DataUtil.registerFlammable(DecorativeOrStorageBlocks.AZULOREAL_SLAB.get(), 5, 20);
        DataUtil.registerFlammable(DecorativeOrStorageBlocks.AZULOREAL_STAIRS.get(), 5, 20);
        DataUtil.registerFlammable(DecorativeOrStorageBlocks.AZULOREAL_FENCE.get(), 5, 20);
        DataUtil.registerFlammable(DecorativeOrStorageBlocks.AZULOREAL_FENCE_GATE.get(), 5, 20);
        DataUtil.registerFlammable(DecorativeOrStorageBlocks.VERTICAL_AZULOREAL_PLANKS.get(), 5, 20);
        DataUtil.registerFlammable(DecorativeOrStorageBlocks.VERTICAL_AZULOREAL_SLAB.get(), 5, 20);
        DataUtil.registerFlammable(DecorativeOrStorageBlocks.AZULOREAL_POST.get(), 5, 20);
        DataUtil.registerFlammable(DecorativeOrStorageBlocks.STRIPPED_AZULOREAL_POST.get(), 5, 20);
        DataUtil.registerFlammable(DecorativeOrStorageBlocks.AZULOREAL_HEDGE.get(), 5, 20);
        DataUtil.registerFlammable(DecorativeOrStorageBlocks.AZULOREAL_BOOKSHELF.get(), 30, 20);

        DataUtil.registerFlammable(BlockRegistry.JESSIC_LEAVES, 30, 60);
        DataUtil.registerFlammable(BlockRegistry.JESSIC_LOG, 5, 5);
        DataUtil.registerFlammable(BlockRegistry.STRIPPED_JESSIC_LOG, 5, 5);
        DataUtil.registerFlammable(BlockRegistry.JESSIC_WOOD, 5, 5);
        DataUtil.registerFlammable(BlockRegistry.STRIPPED_JESSIC_WOOD, 5, 5);
        DataUtil.registerFlammable(DecorativeOrStorageBlocks.JESSIC_PLANKS.get(), 5, 20);
        DataUtil.registerFlammable(DecorativeOrStorageBlocks.JESSIC_SLAB.get(), 5, 20);
        DataUtil.registerFlammable(DecorativeOrStorageBlocks.JESSIC_STAIRS.get(), 5, 20);
        DataUtil.registerFlammable(DecorativeOrStorageBlocks.JESSIC_FENCE.get(), 5, 20);
        DataUtil.registerFlammable(DecorativeOrStorageBlocks.JESSIC_FENCE_GATE.get(), 5, 20);
        DataUtil.registerFlammable(DecorativeOrStorageBlocks.VERTICAL_JESSIC_PLANKS.get(), 5, 20);
        DataUtil.registerFlammable(DecorativeOrStorageBlocks.VERTICAL_JESSIC_SLAB.get(), 5, 20);
        DataUtil.registerFlammable(DecorativeOrStorageBlocks.JESSIC_POST.get(), 5, 20);
        DataUtil.registerFlammable(DecorativeOrStorageBlocks.STRIPPED_JESSIC_POST.get(), 5, 20);
        DataUtil.registerFlammable(DecorativeOrStorageBlocks.JESSIC_HEDGE.get(), 5, 20);
        DataUtil.registerFlammable(DecorativeOrStorageBlocks.JESSIC_BOOKSHELF.get(), 30, 20);
    }
}
