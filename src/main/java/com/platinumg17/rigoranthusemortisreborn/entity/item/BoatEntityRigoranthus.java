package com.platinumg17.rigoranthusemortisreborn.entity.item;

import com.platinumg17.rigoranthusemortisreborn.core.init.BuildingBlockInit;
import com.platinumg17.rigoranthusemortisreborn.core.init.ItemInit;
import com.platinumg17.rigoranthusemortisreborn.entity.RigoranthusEntityTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.Arrays;
import java.util.function.Supplier;

public class BoatEntityRigoranthus extends BoatEntity {
    public BoatEntityRigoranthus(EntityType<? extends BoatEntityRigoranthus> type, World world) {
        super(type, world);
    }

    public BoatEntityRigoranthus(World world, double x, double y, double z) {
        super(RigoranthusEntityTypes.BOAT, world);
        setPos(x, y, z);
        setDeltaMovement(Vector3d.ZERO);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    public BoatEntityRigoranthus(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        this(world, spawnEntity.getPosX(), spawnEntity.getPosY(), spawnEntity.getPosZ());
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void addAdditionalSaveData(CompoundNBT nbt) {
        nbt.putString("model", getModel().getName());
    }

    @Override
    protected void readAdditionalSaveData(CompoundNBT nbt) {
        if (nbt.contains("model", Constants.NBT.TAG_STRING)) {
            this.entityData.set(DATA_ID_TYPE, BoatEntityRigoranthus.BoatModel.byName(nbt.getString("model")).ordinal());
        }
    }

    @Override
    protected void checkFallDamage(double y, boolean onGround, BlockState state, BlockPos pos) {
        this.lastYd = getDeltaMovement().y;
        if (!isPassenger()) {
            if (onGround) {
                if (this.fallDistance > 3f) {
                    if (this.status != Status.ON_LAND) {
                        this.fallDistance = 0f;
                        return;
                    }
                    causeFallDamage(this.fallDistance, 1f);
                    if (!this.level.isClientSide && !this.removed) {
                        this.remove();
                        if (this.level.getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
                            for (int i = 0; i < 3; ++i) {
                                spawnAtLocation(getModel().getPlanks());
                            }
                            for (int j = 0; j < 2; ++j) {
                                spawnAtLocation(Items.STICK);
                            }
                        }
                    }
                }
                this.fallDistance = 0f;
            } else if (!this.level.getFluidState(this.blockPosition().below()).is(FluidTags.WATER) && y < 0d) {
                this.fallDistance = (float) ((double) this.fallDistance - y);
            }
        }
    }

    @Override
    public Item getDropItem() {
        switch (BoatModel.byId(this.entityData.get(DATA_ID_TYPE))) {
            case JESSIC:
                return ItemInit.JESSIC_BOAT.get();
            case AZULOREAL:
                return ItemInit.AZULOREAL_BOAT.get();
        }
        return Items.OAK_BOAT;
    }

    public BoatEntityRigoranthus withModel(BoatModel type) {
        this.entityData.set(DATA_ID_TYPE, type.ordinal());
        return this;
    }

    public BoatModel getModel() {
        return BoatModel.byId(this.entityData.get(DATA_ID_TYPE));
    }

    @Deprecated
    @Override
    public void setType(Type vanillaType) {
    }

    @Deprecated
    @Override
    public Type getBoatType() {
        return Type.OAK;
    }

    public enum BoatModel {
        JESSIC("jessic", BuildingBlockInit.JESSIC_PLANKS),
        AZULOREAL("azuloreal", BuildingBlockInit.AZULOREAL_PLANKS);

        private final String name;
        private final Supplier<Block> supplierPlanks;

        BoatModel(String name, Supplier<Block> supplierPlanks) {
            this.name = name;
            this.supplierPlanks = supplierPlanks;
        }

        public String getName() {
            return this.name;
        }

        public Block getPlanks() {
            return this.supplierPlanks.get();
        }

        public String toString() {
            return this.name;
        }

        public static BoatModel byId(int id) {
            BoatModel[] type = values();
            return type[id < 0 || id >= type.length ? 0 : id];
        }

        public static BoatModel byName(String aName) {
            BoatModel[] type = values();
            return Arrays.stream(type).filter(t -> t.getName().equals(aName)).findFirst().orElse(type[0]);
        }
    }
}