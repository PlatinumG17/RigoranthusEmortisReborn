package com.platinumg17.rigoranthusemortisreborn.entity;
//
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
//public class REBoatEntityJessic extends BoatEntity {
//
//    private static final DataParameter<String> WOOD_TYPE = EntityDataManager.defineId(REBoatEntityJessic.class, DataSerializers.STRING);
//
//    public REBoatEntityJessic(EntityType<? extends BoatEntity> type, World world) {
//        super(type, world);
//        this.blocksBuilding = true;
//    }
//
//    public REBoatEntityJessic(World worldIn, double x, double y, double z) {
//        this(SpecializedEntityTypes.JESSIC_BOAT.get(), worldIn);
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
//        this.entityData.define(WOOD_TYPE, "jessic");
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
//            case "jessic":
//                return ItemInit.JESSIC_BOAT.get();
//            default:
//                return ItemInit.JESSIC_BOAT.get();
//        }
//    }
//
//    @Override
//    public ItemStack getPickedResult(RayTraceResult target) {
//        return new ItemStack(ForgeRegistries.ITEMS.getValue(
//               RigoranthusEmortisReborn.rl(this.getWoodType() + "_boat")));
//    }
//
//    @Nonnull
//    @Override
//    public IPacket<?> getAddEntityPacket() {
//        return NetworkHooks.getEntitySpawningPacket(this);
//    }
//}





/*
    private static final DataParameter<Integer> WOOD_TYPE = EntityDataManager.defineId(REBoatEntityJessic.class, DataSerializers.INT);

//    private static final DataParameter<String> WOOD_TYPE
//            = EntityDataManager.defineId(REBoatEntityJessic.class, DataSerializers.STRING);

    public REBoatEntityJessic(EntityType<? extends BoatEntity> type, World world) {
        super(type, world);
        this.blocksBuilding = true;
    }

    public REBoatEntityJessic(World worldIn, double x, double y, double z) {
        this(ModEntities.RE_BOAT, worldIn);
        this.setPos(x, y, z);
        this.setDeltaMovement(Vector3d.ZERO);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(WOOD_TYPE, AZULOREAL.ordinal());
    }

    @Override
    protected void addAdditionalSaveData(CompoundNBT nbt) {
        nbt.putString("Type", this.getWoodType().getName());
    }

    @Override
    protected void readAdditionalSaveData(CompoundNBT nbt) {
        if (nbt.contains("Type", 8)) {
            this.setWoodType(REBoatEntityJessic.Type.byName(nbt.getString("Type")));
        }
    }

    public void setWoodType(REBoatEntityJessic.Type wood) {
        this.entityData.set(WOOD_TYPE, wood.ordinal());
    }

    @Override
    public Item getDropItem() {
        switch(this.getWoodType()) {
            case AZULOREAL:
            default:
                return MagicItemsRegistry.JESSIC_BOAT;
            case JESSIC:
                return MagicItemsRegistry.AZULOREAL_BOAT;
        }
    }

    public REBoatEntityJessic.Type getWoodType() {
        return REBoatEntityJessic.Type.byId(this.entityData.get(WOOD_TYPE));
    }
    @Override
    public ItemStack getPickedResult(RayTraceResult target) {
        return new ItemStack(ForgeRegistries.ITEMS.getValue(
                RigoranthusEmortisReborn.rl(this.getWoodType() + "_boat")));
    }

    @Nonnull
    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public enum Type {
        AZULOREAL(BlockRegistry.AZULOREAL_PLANKS, "azuloreal"),
        JESSIC(BlockRegistry.JESSIC_PLANKS, "jessic");

        private final String name;
        private final Block planks;

        private Type(Block block, String woodType) {
            this.name = woodType;
            this.planks = block;
        }

        public String getName() {
            return this.name;
        }

        public Block getPlanks() {
            return this.planks;
        }

        public String toString() {
            return this.name;
        }

        public static REBoatEntityJessic.Type byId(int id) {
            REBoatEntityJessic.Type[] aboatentity$type = values();
            if (id < 0 || id >= aboatentity$type.length) {
                id = 0;
            }
            return aboatentity$type[id];
        }

        public static REBoatEntityJessic.Type byName(String name) {
            REBoatEntityJessic.Type[] aboatentity$type = values();
            for(int i = 0; i < aboatentity$type.length; ++i) {
                if (aboatentity$type[i].getName().equals(name)) {
                    return aboatentity$type[i];
                }
            }
            return aboatentity$type[0];
        }
    }
}*/
