package com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile;

import com.platinumg17.rigoranthusemortisreborn.blocks.BlockInit;
import com.platinumg17.rigoranthusemortisreborn.core.init.ItemInit;
import com.platinumg17.rigoranthusemortisreborn.magica.client.particle.ParticleColor;
import com.platinumg17.rigoranthusemortisreborn.magica.common.network.Networking;
import com.platinumg17.rigoranthusemortisreborn.magica.common.network.PacketREEffect;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.BlockRegistry;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import software.bernie.geckolib3.core.IAnimatable;

import javax.annotation.Nullable;

public class IchorExtractorTile extends IchorTile implements IAnimatable {

    public IchorExtractorTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public IchorExtractorTile(){
        super(BlockRegistry.ICHOR_EXTRACTOR_TILE);
    }

    @Override
    public int getMaxIchor() {
        return 20000;
    }

    @Override
    public int getTransferRate() {
        return 10000;
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
        if(!level.isClientSide && level.getGameTime() % 20 == 0){
            BlockPos ichorPos = findNearbyIchor(level, worldPosition);
//            BlockPos needlePos = findNearbyNeedle(level, worldPosition);
//            if(needlePos != null) {
                if (ichorPos != null) {
                    IchorJarTile tile = (IchorJarTile) level.getBlockEntity(ichorPos);
                    int ichor = 100;

                    addIchor(ichor);
                    tile.removeIchor(100);
//                }
            }
        }
    }

    public int getIchorValue(ItemStack i) {
        int ichor = 0;
        int progress = 0;
        int extractionTime = ForgeHooks.getBurnTime(i, null) ;
        if(extractionTime > 0) {
            ichor = extractionTime / 12; //TODO  --> Edit this value [12]
            progress = 1;
        }
        if(i.getItem().getItem() == ItemInit.BUCKET_OF_CADAVEROUS_ICHOR.get().asItem()) {
            ichor += 400;
            progress += 10; // 5 used to be highest
        }
        if(i.getItem().getItem() == ItemInit.BLIGHT_ICHOR.get().asItem()) {
            ichor += 200; // was 100
            progress += 5;
        }
        else if(i.getItem().getItem() == ItemInit.BOTTLE_OF_ICHOR.get().asItem()) {
            ichor += 100; //was 50
            progress += 3;
        }
        this.progress += progress;
        return ichor;
    }
    public static @Nullable
    BlockPos findNearbyIchor(World level, BlockPos worldPosition){
        for(BlockPos p : BlockPos.withinManhattan(worldPosition.below(1), 1, 1,1)){
            if(level.getBlockEntity(p) instanceof IchorJarTile) {
                IchorJarTile tile = (IchorJarTile) level.getBlockEntity(p);
                if (tile.getCurrentIchor() >= 100) {
                    return p;
                }
            }
        }
        return null;
    }
    public static @Nullable
    BlockPos findNearbyNeedle(World level, BlockPos worldPosition) {
        for(BlockPos p : BlockPos.withinManhattan(worldPosition.above(1), 0, 1, 0)) {
            if(level.getBlockState(p).is(BlockInit.BLOCK_OF_ESOTERICUM.get())) {
//                IchorJarTile tile = (IchorJarTile) level.getBlockEntity(p);
                return p;
            }
        }
        return null;
    }
}