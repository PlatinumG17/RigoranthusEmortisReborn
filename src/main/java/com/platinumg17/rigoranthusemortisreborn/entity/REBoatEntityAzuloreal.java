package com.platinumg17.rigoranthusemortisreborn.entity;

//import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
//import com.platinumg17.rigoranthusemortisreborn.canis.common.SpecializedEntityTypes;
//import com.platinumg17.rigoranthusemortisreborn.core.init.ItemInit;
//import net.minecraft.entity.EntityType;
//import net.minecraft.entity.item.BoatEntity;
//import net.minecraft.item.Item;
//import net.minecraft.item.ItemStack;
//import net.minecraft.nbt.CompoundNBT;
//import net.minecraft.network.IPacket;
//import net.minecraft.network.datasync.DataParameter;
//import net.minecraft.network.datasync.DataSerializers;
//import net.minecraft.network.datasync.EntityDataManager;
//import net.minecraft.util.math.RayTraceResult;
//import net.minecraft.util.math.vector.Vector3d;
//import net.minecraft.world.World;
//import net.minecraftforge.fml.network.NetworkHooks;
//import net.minecraftforge.registries.ForgeRegistries;
//
//import javax.annotation.Nonnull;
//
//public class REBoatEntityAzuloreal extends BoatEntity {
//    private static final DataParameter<String> WOOD_TYPE
//            = EntityDataManager.defineId(REBoatEntityAzuloreal.class, DataSerializers.STRING);
//
//    public REBoatEntityAzuloreal(EntityType<? extends BoatEntity> type, World world) {
//        super(type, world);
//        this.blocksBuilding = true;
//    }
//
//    public REBoatEntityAzuloreal(World worldIn, double x, double y, double z) {
//        this(SpecializedEntityTypes.AZULOREAL_BOAT.get(), worldIn);
//        this.setPos(x, y, z);
//        this.setDeltaMovement(Vector3d.ZERO);
//        this.xo = x;
//        this.yo = y;
//        this.zo = z;
//    }
//
//    @Override
//    protected void defineSynchedData() {
//        super.defineSynchedData();
//        this.entityData.define(WOOD_TYPE, "azuloreal");
//    }
//
//    @Override
//    protected void readAdditionalSaveData(CompoundNBT compound) {
//        super.readAdditionalSaveData(compound);
//        compound.putString("Type", this.getWoodType());
//    }
//
//    @Override
//    protected void addAdditionalSaveData(CompoundNBT compound) {
//        super.addAdditionalSaveData(compound);
//        compound.putString("Type", this.getWoodType());
//    }
//
//    public String getWoodType() {
//        return this.entityData.get(WOOD_TYPE);
//    }
//
//    public void setWoodType(String wood) {
//        this.entityData.set(WOOD_TYPE, wood);
//    }
//
//    @Override
//    public Item getDropItem() {
//        switch(this.getWoodType()) {
//            case "azuloreal":
//                return ItemInit.AZULOREAL_BOAT.get();
//            default:
//                return ItemInit.AZULOREAL_BOAT.get();
//        }
//    }
//
//    @Override
//    public ItemStack getPickedResult(RayTraceResult target) {
//        return new ItemStack(ForgeRegistries.ITEMS.getValue(
//                RigoranthusEmortisReborn.rl(this.getWoodType() + "_boat")));
//    }
//
//    @Nonnull
//    @Override
//    public IPacket<?> getAddEntityPacket() {
//        return NetworkHooks.getEntitySpawningPacket(this);
//    }
//}