package com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile;

import com.platinumg17.rigoranthusemortisreborn.core.init.ItemInit;
import com.platinumg17.rigoranthusemortisreborn.entity.mobs.SunderedCadaverEntity;
import com.platinumg17.rigoranthusemortisreborn.magica.client.particle.ParticleColor;
import com.platinumg17.rigoranthusemortisreborn.magica.common.entity.ModEntities;
import com.platinumg17.rigoranthusemortisreborn.magica.common.network.Networking;
import com.platinumg17.rigoranthusemortisreborn.magica.common.network.PacketREEffect;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.BlockRegistry;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.MagicItemsRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeHooks;
import software.bernie.geckolib3.core.IAnimatable;

import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicReference;

public class IchorExtractorTile extends IchorTile implements IAnimatable {

    public IchorExtractorTile(){
        super(BlockRegistry.ICHOR_EXTRACTOR_TILE);
    }

    @Override
    public int getMaxIchor() {
        return 2000;
    }

    @Override
    public int getTransferRate() {
        return 1000;
    }

    @Override
    public void tick() {
        super.tick();
        if(level.isClientSide)
            return;
        if(level.getGameTime() % 20 == 0 && this.canAcceptIchor()) {

            for (ItemEntity i : level.getEntitiesOfClass(ItemEntity.class, new AxisAlignedBB(worldPosition).inflate(1.0))) {
                int ichor = getIchorValue(i.getItem());
                if (ichor > 0) {
                    this.addIchor(ichor);
                    ItemStack containerItem = i.getItem().getContainerItem();
                    i.getItem().shrink(1);
                    if (!containerItem.isEmpty()) {
                        level.addFreshEntity(new ItemEntity(level, i.getX(), i.getY(), i.getZ(), containerItem));
                    }
                    Networking.sendToNearby(level, getBlockPos(),
                            new PacketREEffect(PacketREEffect.EffectType.BURST, i.blockPosition(), new ParticleColor.IntWrapper(255, 0, 0)));
                    return;
                }
            }
            for (SplinteredPedestalTile i : getSurroundingPedestals()) {
                int ichorValue = getIchorValue(i.getItem(0));
                if (ichorValue > 0) {
                    this.addIchor(ichorValue);
                    ItemStack containerItem = i.getItem(0).getContainerItem();
                    i.removeItem(0, 1);
                    i.setItem(0, containerItem);
                    Networking.sendToNearby(level, getBlockPos(),
                            new PacketREEffect(PacketREEffect.EffectType.BURST, i.getBlockPos().above(), new ParticleColor.IntWrapper(255, 0, 0)));
                }
            }
        }
//        if(!level.isClientSide && level.getGameTime() % 20 == 0) {
//            int ichor = 100;
//            BlockPos ichorPos = findNearbyIchor(level, worldPosition);
//            IchorJarTile tile = (IchorJarTile) level.getBlockEntity(ichorPos);
//            if (ichorPos != null) { tile.removeIchor(100); }
////            if (IchorUtil.hasIchorNearby(this.worldPosition.below(), this.level, 0, ichor)) {
////                IchorUtil.takeIchorNearby(this.worldPosition.below(), this.level, 0, ichor);
////                this.addIchor(ichor);
////            }
//        }
    }

    public int getIchorValue(ItemStack i) {
        int ichor = 0;
        int progress = 0;
        int extractionTime = ForgeHooks.getBurnTime(i, null) ;
        if(extractionTime > 0) {
            ichor = extractionTime / 12; //TODO  --> Edit this value [12]
            progress = 1;
        }

        if(i.getItem().getItem() == MagicItemsRegistry.DWELLER_FLESH.asItem()) {
            ichor += 500;
            progress += 10; // 5 used to be highest
        }
        if(i.getItem().getItem() == ItemInit.BUCKET_OF_CADAVEROUS_ICHOR.get().asItem()) {
            ichor += 400;
            progress += 9; // 5 used to be highest
        }
        if(i.getItem().getItem() == ItemInit.BLIGHT_ICHOR.get().asItem()) {
            ichor += 200; // was 100
            progress += 5;
        }
        if(i.getItem().getItem() == MagicItemsRegistry.BOTTLE_OF_ICHOR || i.getItem().getItem() == Items.FERMENTED_SPIDER_EYE) {
            ichor += 100; //was 50
            progress += 3;
        }
        if (i.getItem().getItem() == Items.SPIDER_EYE) {
            ichor += 75;
            progress += 3;
        }
        if (i.getItem().getItem() == Items.BONE_BLOCK) {
            ichor += 45;
            progress += 1;
        }
        if (i.getItem().getItem() == Items.BONE || i.getItem().getItem() == Items.ROTTEN_FLESH) {
            ichor += 15;
            progress += 1;
        }
        else if(i.getItem().getItem() == Items.BONE_MEAL || i.getItem().getItem() == ItemInit.BONE_FRAGMENT.get()) {
            ichor += 5;
            progress += 1;
        }

        this.progress += progress;
        return ichor;
    }


    public void doRandomAction() {
        if(level.isClientSide)
            return;
        if(level.isDay())
            return;

        BlockPos skullPos = getBlockInArea(BlockRegistry.hangingCadaverSkull, 1);
        if(skullPos != null && progress >= 200) {
            SunderedCadaverEntity sunderedCadaver = ModEntities.SUNDERED_CADAVER.create(level);
            SunderedCadaverEntity sunderedCadaver2 = ModEntities.SUNDERED_CADAVER.create(level);
//            if ((Math.random() < 0.1)) {
                if (level instanceof ServerWorld) {

                    LightningBoltEntity lightningBoltEntity = EntityType.LIGHTNING_BOLT.create(level);

                    lightningBoltEntity.absMoveTo(worldPosition.getX(), worldPosition.getY(), worldPosition.getZ(), 0.0F, 0.0F);
                    lightningBoltEntity.setVisualOnly(true);

                    level.addFreshEntity(lightningBoltEntity);

                    sunderedCadaver.absMoveTo(worldPosition.getX() + 1, worldPosition.getY(), worldPosition.getZ() + 1, 0.0F, 0.0F);
                    sunderedCadaver2.absMoveTo(worldPosition.getX() - 1, worldPosition.getY(), worldPosition.getZ() - 1, 0.0F, 0.0F);

                    sunderedCadaver.finalizeSpawn((ServerWorld)level, level.getCurrentDifficultyAt(worldPosition), SpawnReason.MOB_SUMMONED, (ILivingEntityData)null, (CompoundNBT)null);
                    sunderedCadaver2.finalizeSpawn((ServerWorld)level, level.getCurrentDifficultyAt(worldPosition), SpawnReason.MOB_SUMMONED, (ILivingEntityData)null, (CompoundNBT)null);

                    level.addFreshEntity(sunderedCadaver);
                    level.addFreshEntity(sunderedCadaver2);

                    sunderedCadaver.setCustomName(new TranslationTextComponent("entity.rigoranthusemortisreborn.summoned_servant").withStyle(Style.EMPTY.withItalic(true)));
                    sunderedCadaver2.setCustomName(new TranslationTextComponent("entity.rigoranthusemortisreborn.summoned_servant").withStyle(Style.EMPTY.withItalic(true)));

                    sunderedCadaver.setCustomNameVisible(true);
                    sunderedCadaver2.setCustomNameVisible(true);
                }
//            }
//            level.setBlockAndUpdate(skullPos, Blocks.LAVA.defaultBlockState());
            progress -= 200;
            return;
        }
    }

    public BlockPos getBlockInArea(Block block, int range){
        AtomicReference<BlockPos> posFound = new AtomicReference<>();
        BlockPos.betweenClosedStream(worldPosition.offset(5, -3, 5), worldPosition.offset(-5, 3, -5)).forEach(blockPos -> {
            blockPos = blockPos.immutable();
            if(posFound.get() == null && level.getBlockState(blockPos).getBlock() == block)
                posFound.set(blockPos);
        });
        return posFound.get();
    }

    public static @Nullable
    BlockPos findNearbyIchor(World level, BlockPos worldPosition){
        for(BlockPos p : BlockPos.withinManhattan(worldPosition.below(1), 0, 0,0)){
            if(level.getBlockEntity(p) instanceof IchorJarTile) {
                IchorJarTile tile = (IchorJarTile) level.getBlockEntity(p);
                if (tile.getCurrentIchor() >= 100) {
                    return p;
                }
            }
        }
        return null;
    }
}