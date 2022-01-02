package com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.client.ITooltipProvider;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.CipherBlock;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.BlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.state.BooleanProperty;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class PsyglyphicCipherTile extends TileEntity implements IAnimatable {//, ITooltipProvider, ITickableTileEntity {
//    public int tickCounter;
//    public boolean hasBeenUsed;
//    public static final BooleanProperty UTILIZED = BooleanProperty.create("transcribed");

    public PsyglyphicCipherTile() {
        super(BlockRegistry.PSYGLYPHIC_TILE);
    }
//    @Override
//    public void tick() {
//        if (level.isClientSide)
//            return;
//        if (!hasBeenUsed) {
//            transcriptionEffect();
//            return;
//        }
//    }
//    public void transcriptionEffect() {
//        tickCounter++;
//    }

    AnimationFactory manager = new AnimationFactory(this);
    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController(this, "float_controller", 0, this::idlePredicate));
    }

    private <E extends TileEntity & IAnimatable > PlayState idlePredicate(AnimationEvent<E> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("float", true));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return manager;
    }

//    @Override
//    public List<String> getTooltip() {
//        List<String> tooltips = new ArrayList<>();
//            tooltips.add("Psyglyphic Cipher");
//            if (this.hasBeenUsed) {
//                tooltips.add(new TranslationTextComponent("rigoranthusemortisreborn.tooltip.cipher_used").getString());
//            } else {
//                tooltips.add(new TranslationTextComponent("rigoranthusemortisreborn.tooltip.cipher_not_used").getString());
//            }
//        return tooltips;
//    }

//    @Override
//    public void load(BlockState state, CompoundNBT tag) {
//        super.load(state, tag);
//        hasBeenUsed = tag.getBoolean("transcribed");
//    }
//
//    @Override
//    public CompoundNBT save(CompoundNBT tag) {
//        tag.putBoolean("transcribed", hasBeenUsed);
//        return super.save(tag);
//    }

//    @SubscribeEvent
//    public void rightClick(PlayerInteractEvent.RightClickBlock event) {
//        if (!(event.getWorld().getBlockEntity(event.getPos()) instanceof PsyglyphicCipherTile))
//            return;
//
//        World world = event.getWorld();
//        BlockPos pos = event.getPos();
//
//        if (world.getBlockState(pos).getBlock() instanceof CipherBlock) {
//            BlockRegistry.PSYGLYPHIC_CIPHER.use(world.getBlockState(pos), world, pos, event.getPlayer(), event.getHand(), null);
//            event.setCanceled(true);
//        }
//    }
    @Override
    @Nullable
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.worldPosition, 3, this.getUpdateTag());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return this.save(new CompoundNBT());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        super.onDataPacket(net, pkt);
        handleUpdateTag(this.level.getBlockState(this.worldPosition),pkt.getTag());
    }

//    @Override
//    public void getUpdatePacket() {
//        super.getUpdatePacket();
//
//        if(this.level != null)
//            level.markAndNotifyBlock(worldPosition, level.getChunkAt(worldPosition), level.getBlockState(worldPosition).getBlock().defaultBlockState(), level.getBlockState(worldPosition), 3, 3);
//    }
//    @Override
//    public void tick() {
//        if (level.isClientSide)
//            return;
//        if (level.getGameTime() % 20 == 0 && !this.hasBeenUsed) {
//            for(ItemEntity i : level.getEntitiesOfClass(ItemEntity.class, new AxisAlignedBB(worldPosition).inflate(1.0))){
//                ItemStack containerItem = i.getItem().getContainerItem();
//                i.getItem().shrink(1);
//                this.hasBeenUsed = true;
//                this.setChanged();
//                if(!containerItem.isEmpty()){
//                    level.addFreshEntity(new ItemEntity(level, i.getX(), i.getY(), i.getZ(), containerItem));
//                }
//                Networking.sendToNearby(level, getBlockPos(),
//                        new PacketREEffect(PacketREEffect.EffectType.BURST, i.blockPosition(), new ParticleColor.IntWrapper(255, 0, 0)));
//                return;
//            }
//        }
//    }
}