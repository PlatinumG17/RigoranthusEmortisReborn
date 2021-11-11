package com.platinumg17.rigoranthusemortisreborn.core.init.fluid;

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
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;

public class FluidRegistry {

    public static final ResourceLocation CADAVEROUS_ICHOR_STILL_RL = new ResourceLocation(EmortisConstants.MOD_ID + ":blocks/cadaverous_ichor_still");
    public static final ResourceLocation CADAVEROUS_ICHOR_FLOWING_RL = new ResourceLocation(EmortisConstants.MOD_ID + ":blocks/cadaverous_ichor_flow");
    public static final ResourceLocation CADAVEROUS_ICHOR_OVERLAY_RL = new ResourceLocation(EmortisConstants.MOD_ID + ":blocks/cadaverous_ichor_overlay");
    public static final Material CADAVEROUS_ICHOR = (new Material.Builder(MaterialColor.COLOR_RED)).noCollider().nonSolid().replaceable().liquid().build();

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, EmortisConstants.MOD_ID);

    public static final RegistryObject<CadaverousIchorFluid.Source> CADAVEROUS_ICHOR_FLUID
            = FLUIDS.register("cadaverous_ichor_fluid", () -> new CadaverousIchorFluid.Source(FluidRegistry.CADAVEROUS_ICHOR_PROPERTIES));

    public static final RegistryObject<CadaverousIchorFluid.Flowing> CADAVEROUS_ICHOR_FLOWING
            = FLUIDS.register("cadaverous_ichor_flowing", () -> new CadaverousIchorFluid.Flowing(FluidRegistry.CADAVEROUS_ICHOR_PROPERTIES));

    public static final CadaverousIchorFluid.Properties CADAVEROUS_ICHOR_PROPERTIES = new CadaverousIchorFluid.Properties(//() ->
             CADAVEROUS_ICHOR_FLUID, CADAVEROUS_ICHOR_FLOWING, FluidAttributes.builder(CADAVEROUS_ICHOR_STILL_RL, CADAVEROUS_ICHOR_FLOWING_RL)
            .density(1500).luminosity(15).viscosity(2000)
            .sound(SoundEvents.HONEY_DRINK).overlay(CADAVEROUS_ICHOR_OVERLAY_RL)
            .color(0xffa52a2a)).slopeFindDistance(2).levelDecreasePerBlock(2)
            .block(FluidRegistry.CADAVEROUS_ICHOR_BLOCK)
            .bucket(ItemInit.BUCKET_OF_CADAVEROUS_ICHOR);

    public static final RegistryObject<FlowingCadaverousIchorBlock> CADAVEROUS_ICHOR_BLOCK = BlockInit.BLOCKS.register("cadaverous_ichor",
            () -> new FlowingCadaverousIchorBlock(FluidRegistry.CADAVEROUS_ICHOR_FLUID,
                    new Vector3d(0.8, 0.0, 0.0), 0.25f,
                    FlowingCadaverousIchorBlock.Properties.of(FluidRegistry.CADAVEROUS_ICHOR)
                    .noOcclusion().strength(100f).noDrops()));

    public static void register(IEventBus modEventBus) {
        FLUIDS.register(modEventBus);
    }
}