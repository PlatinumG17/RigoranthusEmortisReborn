package com.platinumg17.rigoranthusemortisreborn.core.registry.fluid;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.blocks.BlockInit;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.core.init.ItemInit;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
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

public class FluidRegistry {

    public static final ResourceLocation CADAVEROUS_ICHOR_STILL_RL = RigoranthusEmortisReborn.rl("blocks/cadaverous_ichor_still");
    public static final ResourceLocation CADAVEROUS_ICHOR_FLOWING_RL = RigoranthusEmortisReborn.rl("blocks/cadaverous_ichor_flow");
    public static final ResourceLocation CADAVEROUS_ICHOR_OVERLAY_RL = RigoranthusEmortisReborn.rl("blocks/cadaverous_ichor_overlay");
    public static final Material CADAVEROUS_ICHOR_MATERIAL = (new Material.Builder(MaterialColor.COLOR_RED)).noCollider().nonSolid().replaceable().liquid().build();

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, EmortisConstants.MOD_ID);

    public static final RegistryObject<FlowingFluid> CADAVEROUS_ICHOR_FLUID
            = FLUIDS.register("cadaverous_ichor_fluid", () -> new ForgeFlowingFluid.Source(FluidRegistry.CADAVEROUS_ICHOR_PROPERTIES));

    public static final RegistryObject<FlowingFluid> CADAVEROUS_ICHOR_FLOWING
            = FLUIDS.register("cadaverous_ichor_flowing", () -> new ForgeFlowingFluid.Flowing(FluidRegistry.CADAVEROUS_ICHOR_PROPERTIES));

    public static final ForgeFlowingFluid.Properties CADAVEROUS_ICHOR_PROPERTIES = new ForgeFlowingFluid.Properties (
        () -> CADAVEROUS_ICHOR_FLUID.get(),
        () -> CADAVEROUS_ICHOR_FLOWING.get(),
            FluidAttributes.builder(CADAVEROUS_ICHOR_STILL_RL, CADAVEROUS_ICHOR_FLOWING_RL)
                .density(1500).luminosity(15).viscosity(2000)
                .sound(SoundEvents.BUCKET_FILL_FISH, SoundEvents.WATER_AMBIENT).overlay(CADAVEROUS_ICHOR_OVERLAY_RL)
                .color(0xffa52a2a)).slopeFindDistance(2).levelDecreasePerBlock(2)
                .block(
        () -> FluidRegistry.CADAVEROUS_ICHOR_BLOCK.get())
                .bucket(
        () -> ItemInit.BUCKET_OF_CADAVEROUS_ICHOR.get());

    public static final RegistryObject<FlowingFluidBlock> CADAVEROUS_ICHOR_BLOCK = BlockInit.BLOCKS.register("cadaverous_ichor",
            () -> new FlowingFluidBlock(() -> FluidRegistry.CADAVEROUS_ICHOR_FLUID.get(), /*new Vector3d(0.8, 0.0, 0.0), 0.25f,*/ AbstractBlock.Properties.of(Material.WATER, MaterialColor.COLOR_RED)
                    .noOcclusion().strength(100f).noDrops()));

    public static void register(IEventBus modEventBus) {
        FLUIDS.register(modEventBus);
    }
}