package com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.client.ITooltipProvider;
import com.platinumg17.rigoranthusemortisreborn.canis.CanisItems;
import com.platinumg17.rigoranthusemortisreborn.canis.common.block.tileentity.FoodBowlTileEntity;
import com.platinumg17.rigoranthusemortisreborn.canis.common.inventory.screens.CanisScreens;
import com.platinumg17.rigoranthusemortisreborn.canis.common.util.InventoryUtil;
import com.platinumg17.rigoranthusemortisreborn.canis.common.util.WorldUtil;
import com.platinumg17.rigoranthusemortisreborn.core.init.ItemInit;
import com.platinumg17.rigoranthusemortisreborn.magica.client.particle.ParticleColor;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.PsyglyphicBlock;
import com.platinumg17.rigoranthusemortisreborn.magica.common.lib.LibBlockNames;
import com.platinumg17.rigoranthusemortisreborn.magica.common.network.Networking;
import com.platinumg17.rigoranthusemortisreborn.magica.common.network.PacketREEffect;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.BlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.EmptyHandler;
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

public class PsyglyphicCipherTile extends TileEntity implements IAnimatable, ITooltipProvider, ITickableTileEntity {

    public boolean hasBeenUsed;

    public PsyglyphicCipherTile() {
        super(BlockRegistry.PSYGLYPHIC_TILE);
        hasBeenUsed = false;
    }

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

    @Override
    public List<String> getTooltip() {
        List<String> tooltips = new ArrayList<>();
            tooltips.add("Psyglyphic Cipher");
            if (this.hasBeenUsed) {
                tooltips.add(new TranslationTextComponent("rigoranthusemortisreborn.tooltip.cipher_used").getString());
                return tooltips;
            } else {
                tooltips.add(new TranslationTextComponent("rigoranthusemortisreborn.tooltip.cipher_not_used").getString());
            }
        return tooltips;
    }

    @Override
    public void load(BlockState state, CompoundNBT tag) {
        hasBeenUsed = tag.getBoolean("transcribed");
        super.load(state, tag);
    }

    @Override
    public CompoundNBT save(CompoundNBT tag) {
//        if(this.hasBeenUsed) {
            tag.putBoolean("transcribed", hasBeenUsed);
//        }
        return super.save(tag);
    }

    @SubscribeEvent
    public void rightClick(PlayerInteractEvent.RightClickBlock event) {
        if (!(event.getWorld().getBlockEntity(event.getPos()) instanceof PsyglyphicCipherTile))
            return;
        World world = event.getWorld();
        BlockPos pos = event.getPos();
        if (world.getBlockState(pos).getBlock() instanceof PsyglyphicBlock) {
            BlockRegistry.PSYGLYPHIC_CIPHER.use(world.getBlockState(pos), world, pos, event.getPlayer(), event.getHand(), null);
            event.setCanceled(true);
        }
    }

//    @SuppressWarnings("deprecation")
//    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
//        PsyglyphicCipherTile cipher = WorldUtil.getTileEntity(worldIn, pos, PsyglyphicCipherTile.class);
//        if (cipher != null) {
//            ItemStack stack = player.getItemInHand(handIn);
//            if (!stack.isEmpty() && stack.getItem() == Items.BOOK && !this.hasBeenUsed) {
//                worldIn.playSound(null, pos, SoundEvents.VILLAGER_WORK_CARTOGRAPHER, SoundCategory.BLOCKS, 0.8F, 0.7F);
//                player.inventory.add(ItemInit.PSYGLYPHIC_SCRIPT.get().getDefaultInstance());
//                this.hasBeenUsed = true;
//                update();
//                stack.shrink(1);
//                Networking.sendToNearby(level, getBlockPos(), new PacketREEffect(PacketREEffect.EffectType.BURST, this.worldPosition, new ParticleColor.IntWrapper(255, 0, 0)));
//            }
//            return ActionResultType.SUCCESS;
//        } else {
//            return ActionResultType.FAIL;
//        }
//    }
    public boolean update() {
        if(this.worldPosition != null && this.level != null){
            level.sendBlockUpdated(this.worldPosition, level.getBlockState(worldPosition), level.getBlockState(worldPosition), 2);
            return true;
        }
        return false;
    }

    @Override
    public void tick() {
        if(level.isClientSide)
            return;
        if(level.getGameTime() % 20 == 0 && !this.hasBeenUsed){
            for(ItemEntity i : level.getEntitiesOfClass(ItemEntity.class, new AxisAlignedBB(worldPosition).inflate(1.0))){
                ItemStack containerItem = i.getItem().getContainerItem();
                i.getItem().shrink(1);
                this.hasBeenUsed = true;
                this.setChanged();
                if(!containerItem.isEmpty()){
                    level.addFreshEntity(new ItemEntity(level, i.getX(), i.getY(), i.getZ(), containerItem));
                }
                Networking.sendToNearby(level, getBlockPos(),
                        new PacketREEffect(PacketREEffect.EffectType.BURST, i.blockPosition(), new ParticleColor.IntWrapper(255, 0, 0)));
                return;
            }
        }
    }
    @Override @Nullable public SUpdateTileEntityPacket getUpdatePacket() {return new SUpdateTileEntityPacket(this.worldPosition, 3, this.getUpdateTag());}

    @Override public CompoundNBT getUpdateTag() {return this.save(new CompoundNBT());}

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        super.onDataPacket(net, pkt);
        handleUpdateTag(level.getBlockState(worldPosition),pkt.getTag());
    }
}