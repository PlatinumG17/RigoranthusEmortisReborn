package com.platinumg17.rigoranthusemortisreborn.core.init.fluid;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.blocks.BlockInit;
import com.platinumg17.rigoranthusemortisreborn.core.init.ItemInit;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class CadaverousIchorFluid {
    public static final ResourceLocation WATER_STILL_RL = new ResourceLocation("block/water_still");
    public static final ResourceLocation WATER_FLOWING_RL = new ResourceLocation("block/water_flow");
    public static final ResourceLocation WATER_OVERLAY_RL = new ResourceLocation("block/water_overlay");

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, RigoranthusEmortisReborn.MOD_ID);

    public static final RegistryObject<FlowingFluid> CADAVEROUS_ICHOR_FLUID
            = FLUIDS.register("cadaverous_ichor_fluid", () -> new ForgeFlowingFluid.Source(CadaverousIchorFluid.CADAVEROUS_ICHOR_PROPERTIES));

    public static final RegistryObject<FlowingFluid> CADAVEROUS_ICHOR_FLOWING
            = FLUIDS.register("cadaverous_ichor_flowing", () -> new ForgeFlowingFluid.Flowing(CadaverousIchorFluid.CADAVEROUS_ICHOR_PROPERTIES));

    public static final ForgeFlowingFluid.Properties CADAVEROUS_ICHOR_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> CADAVEROUS_ICHOR_FLUID.get(), () -> CADAVEROUS_ICHOR_FLOWING.get(), FluidAttributes.builder(WATER_STILL_RL, WATER_FLOWING_RL)
            .density(20).luminosity(4).viscosity(20).sound(SoundEvents.HONEY_DRINK).overlay(WATER_OVERLAY_RL).color(0xffa52a2a)).slopeFindDistance(1).levelDecreasePerBlock(2)
            .block(() -> CadaverousIchorFluid.CADAVEROUS_ICHOR_BLOCK.get()).bucket(() -> ItemInit.BUCKET_OF_CADAVEROUS_ICHOR.get());

    public static final RegistryObject<FlowingFluidBlock> CADAVEROUS_ICHOR_BLOCK = BlockInit.BLOCKS.register("cadaverous_ichor",
            () -> new FlowingFluidBlock(() -> CadaverousIchorFluid.CADAVEROUS_ICHOR_FLUID.get(), AbstractBlock.Properties.of(Material.WATER)
                    .noOcclusion().strength(100f).noDrops()));

    public static void register(IEventBus bus) {
        FLUIDS.register(bus);
    }
}
