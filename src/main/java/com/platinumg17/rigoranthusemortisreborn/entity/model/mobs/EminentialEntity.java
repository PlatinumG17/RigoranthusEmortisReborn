package com.platinumg17.rigoranthusemortisreborn.entity.model.mobs;

//import com.platinumg17.rigoranthusemortisreborn.canis.common.SpecializedEntityTypes;
//import com.platinumg17.rigoranthusemortisreborn.entity.RigoranthusEntityTypes;
//import com.platinumg17.rigoranthusemortisreborn.util.REDebug;
//import net.minecraft.entity.*;
//import net.minecraft.entity.ai.attributes.Attributes;
//import net.minecraft.entity.monster.MonsterEntity;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.entity.player.PlayerInventory;
//import net.minecraft.entity.player.ServerPlayerEntity;
//import net.minecraft.entity.projectile.ThrowableEntity;
//import net.minecraft.inventory.EquipmentSlotType;
//import net.minecraft.item.ItemStack;
//import net.minecraft.nbt.CompoundNBT;
//import net.minecraft.network.IPacket;
//import net.minecraft.network.PacketBuffer;
//import net.minecraft.util.DamageSource;
//import net.minecraft.util.FoodStats;
//import net.minecraft.util.Util;
//import net.minecraft.util.text.ITextComponent;
//import net.minecraft.util.text.StringTextComponent;
//import net.minecraft.world.GameType;
//import net.minecraft.world.World;
//import net.minecraft.world.server.ServerWorld;
//import net.minecraftforge.common.util.FakePlayer;
//import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
//import net.minecraftforge.fml.network.FMLPlayMessages;
//import net.minecraftforge.fml.network.NetworkHooks;
//
//import java.util.UUID;
//
//public class EminentialEntity extends MobEntity implements IEntityAdditionalSpawnData {

//    public EminentialEntity(ServerWorld world, ServerPlayerEntity player) {        //ServerWorld world, ServerPlayerEntity player) {
//        super(SpecializedEntityTypes.EMINENTIAL_PROJECTION.get(), world);
//        this.setBoundingBox(player.getBoundingBox());
//        this.player = new DecoyPlayer(world, this, player);
//        this.setBoundingBox(player.getBoundingBox());
//        this.player = new DecoyPlayer(world, this, player);


//        this.getAttribute(Attributes.MAX_HEALTH).replaceFrom(player.getAttribute(Attributes.MAX_HEALTH));
//        this.player.getAttribute(Attributes.MAX_HEALTH).replaceFrom(player.getAttribute(Attributes.MAX_HEALTH));
//        this.setHealth(player.getHealth());
//        username = player.getGameProfile().getName();
//        playerId = player.getUUID();
//        isFlying = player.abilities.flying;
//        player.abilities.addSaveData(this.capabilities);
//        foodStatsNBT = new CompoundNBT();
//        player.getFoodData().addAdditionalSaveData(foodStatsNBT);
//        initFoodStats(player);
//    }
//
//    @Override
//    public EntitySize getDimensions(Pose poseIn) {return EntityType.PLAYER.getDimensions();}
//
//    private void initInventory(ServerPlayerEntity player) {
//        inventory = this.player.inventory;
//        inventory.replaceWith(player.inventory);
//    }
//
//    private void initFoodStats(ServerPlayerEntity sourcePlayer) {
//        try {
//            try {foodStats = new FoodStats();}
//            catch(NoSuchMethodError e) {
//                REDebug.info("Custom constructor detected for FoodStats. Trying with player as parameter...");
//                try {
//                    foodStats = FoodStats.class.getConstructor(PlayerEntity.class).newInstance(player);}
//                catch(NoSuchMethodException ex) {
//                    throw new NoSuchMethodException("Found no known constructor for net.minecraft.util.FoodStats.");
//                }
//            }
//            foodStats.readAdditionalSaveData(foodStatsNBT);	//Exact copy of food stack
//        } catch(Exception e) {
//            foodStats = null;
//            REDebug.logger.error("Couldn't initiate food stats for player Eminential Projection. Proceeding to not simulate food stats.", e);
//            sourcePlayer.sendMessage(new StringTextComponent("An issue came up while creating the Eminential Projection. More info in the server logs."), Util.NIL_UUID);
//        }
//    }
//
//    public CompoundNBT getFoodStatsNBT() {
//        if(foodStats != null) {
//            CompoundNBT nbt = new CompoundNBT();
//            foodStats.addAdditionalSaveData(nbt);
//            return nbt;
//        } else return foodStatsNBT;
//    }
//
//    @Override
//    public void writeSpawnData(PacketBuffer buffer) {
//        buffer.writeUtf(username, 16);
//    }
//
//    @Override
//    public void readSpawnData(PacketBuffer additionalData) {
//        username = additionalData.readUtf(16);
//    }

//    @Override
//    public void tick() {
//        if(!level.isClientSide) {
//            if(foodStats != null)
//                foodStats.tick(player);
//        }
//    }
//
//    @Override
//    public boolean shouldShowName() {return username != null;}
//
//    @Override
//    public ITextComponent getName() {return new StringTextComponent(username != null ? username : "EMINENTIAL PROJECTION");}
//
//    @Override
//    public ItemStack getItemBySlot(EquipmentSlotType slotIn) {
//        if(slotIn == EquipmentSlotType.MAINHAND)
//            return inventory.getSelected();
//        else if(slotIn == EquipmentSlotType.OFFHAND)
//            return inventory.offhand.get(0);
//        else return inventory.armor.get(slotIn.getIndex());
//    }
//
//    @Override
//    public void setItemSlot(EquipmentSlotType slotIn, ItemStack stack) {
//        if(slotIn == EquipmentSlotType.MAINHAND)
//            inventory.setItem(inventory.selected, stack);
//        else if(slotIn == EquipmentSlotType.OFFHAND)
//            inventory.offhand.set(0, stack);
//        else inventory.armor.set(slotIn.getIndex(), stack);
//    }
//
//    @Override
//    public void setHealth(float par1) {
//        if(player != null)
//            player.setHealth(par1);
//        super.setHealth(par1);
//    }
//
//    @Override
//    public Iterable<ItemStack> getArmorSlots() {
//        return inventory.armor;
//    }
//}