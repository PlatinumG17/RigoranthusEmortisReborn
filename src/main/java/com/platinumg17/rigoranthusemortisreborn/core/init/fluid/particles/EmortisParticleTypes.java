package com.platinumg17.rigoranthusemortisreborn.core.init.fluid.particles;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EmortisParticleTypes {

    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, EmortisConstants.MOD_ID);
    public static final RegistryObject<BasicParticleType> ICHOR = PARTICLES.register("cadaverous_ichor_particle", () -> new BasicParticleType(true));
}
