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
//    public boolean isFlying;
////    public GameType gameType;
//    public String username;
//    private UUID playerId;
//    private FoodStats foodStats;
//    private CompoundNBT foodStatsNBT;
//    public CompoundNBT capabilities = new CompoundNBT();
//
//    public boolean markedForDespawn;
//    private double originX, originY, originZ;
//    private DecoyPlayer player;
//
//    public PlayerInventory inventory;
//
////    public EminentialEntity(World worldIn, LivingEntity livingEntityIn) {super(SpecializedEntityTypes.EMINENTIAL_PROJECTION.get(), livingEntityIn, worldIn);}
//    public EminentialEntity(FMLPlayMessages.SpawnEntity player, World worldIn) {super(SpecializedEntityTypes.EMINENTIAL_PROJECTION.get(), worldIn);}
//
//    public EminentialEntity(EntityType<? extends MobEntity> type, World world) {
//        super(type, world);
//        //super(SpecializedEntityTypes.EMINENTIAL_PROJECTION.get(), world);
//        inventory = new PlayerInventory(null);
//        if(!world.isClientSide)	//If not spawned the way it should
//            markedForDespawn = true;
//    }
//
//    public EminentialEntity(ServerWorld world, ServerPlayerEntity player) {        //ServerWorld world, ServerPlayerEntity player) {
//        super(SpecializedEntityTypes.EMINENTIAL_PROJECTION.get(), world);
//        this.setBoundingBox(player.getBoundingBox());
//        this.player = new DecoyPlayer(world, this, player);
//        this.setBoundingBox(player.getBoundingBox());
//        this.player = new DecoyPlayer(world, this, player);
//        for(String key : player.getPersistentData().getAllKeys())
//            this.player.getPersistentData().put(key, player.getPersistentData().get(key).copy());
//        this.setPos(player.getX(), player.getY(), player.getZ());
//        originX = this.getX();
//        this.xChunk = player.xChunk;
//        originY = this.getY();
//        this.yChunk = player.yChunk;
//        originZ = this.getZ();
//        this.zChunk = player.zChunk;
//        this.xRot = player.xRot;
//        this.yRot = player.yRot;
//        this.yHeadRot = player.yHeadRot;
//        this.yBodyRot = player.yBodyRot;
////        this.gameType = player.gameMode.getGameModeForPlayer();
//        initInventory(player);
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
//        buffer.writeUUID(playerId);
//        buffer.writeFloat(yHeadRot);
//        buffer.writeBoolean(isFlying);
//    }
//
//    @Override
//    public void readSpawnData(PacketBuffer additionalData) {
//        username = additionalData.readUtf(16);
//        playerId = additionalData.readUUID();
//        yHeadRot = additionalData.readFloat();
//        isFlying = additionalData.readBoolean();
//        yHeadRotO = yHeadRot;
//        this.yRot = yHeadRot;	//I don't know how much of this that is necessary
//        yRotO = yRot;
//        yBodyRot = yRot;
//    }
//
//    @Override
//    public IPacket<?> getAddEntityPacket() {return NetworkHooks.getEntitySpawningPacket(this);}
//
//    public UUID getPlayerID() {return playerId;}
//
//    @Override
//    public void tick() {
//        if(markedForDespawn) {
//            this.remove();
//            return;
//        }
//        super.tick();
//
//        yHeadRot = yHeadRotO;	//Neutralize the effect of the LookHelper
//        yRot = yRotO;
//        xRot = xRotO;
//
//        if(isFlying)
//            this.setPos(this.getX(), yo, this.getZ());
//
//        if(!level.isClientSide) {
//            if(foodStats != null)
//                foodStats.tick(player);
////            if(this.locationChanged())
////                ServerEditHandler.reset(ServerEditHandler.getData(this));
//        }
//    }
//
//    public boolean locationChanged() {
//        return originX >= this.getX()+1 || originX <= this.getX()-1 ||
//                originY >= this.getY()+1 || originY <= this.getY()-1 ||
//                originZ >= this.getZ()+1 || originZ <= this.getZ()-1;
//    }
//
////    @Override
////    public boolean hurt(DamageSource damageSource, float par2) {
////        if (!level.isClientSide && (!gameType.equals(GameType.CREATIVE) || damageSource.isBypassInvul())) {
////            return super.hurt(damageSource, par2);
////        }
//////            ServerEditHandler.reset(damageSource, par2, ServerEditHandler.getData(this));
////        return super.hurt(damageSource, par2);
////    }
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
//
//    @Override
//    public boolean removeWhenFarAway(double distanceToClosestPlayer) {return false;}
//
//    private static class DecoyPlayer extends FakePlayer {
//        EminentialEntity eminentialProjection;
//        DecoyPlayer(ServerWorld par1World, EminentialEntity eminentialProjection, ServerPlayerEntity player) {
//            super(par1World, player.getGameProfile());
//            player.getServer().getPlayerList().getPlayerAdvancements(player);
//            //Fixes annoying NullPointerException when unlocking advancement, caused by just creating the fake player
//            this.eminentialProjection = eminentialProjection;
//            this.setHealth(eminentialProjection.getHealth());
//        }
//        @Override
//        public void heal(float par1) {eminentialProjection.heal(par1);}
//    }
//}