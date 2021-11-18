package com.platinumg17.rigoranthusemortisreborn.canis.client.entity.render;

import java.util.Map;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import com.google.common.collect.Maps;
import net.minecraftforge.registries.IRegistryDelegate;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.client.IAccoutrementRenderer;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.client.ISkillRenderer;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.registry.Accoutrement;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.registry.Skill;

public class CollarRenderManager {

    private static Map<IRegistryDelegate<Accoutrement>, IAccoutrementRenderer<?>> accoutrementRenderMap = Maps.newConcurrentMap();
    private static Map<IRegistryDelegate<Skill>, ISkillRenderer<?>> skillRendererMap = Maps.newConcurrentMap();

    public static void registerRenderer(Supplier<? extends Accoutrement> entityClass, IAccoutrementRenderer<?> shader) {
        registerRenderer(entityClass.get(), shader);
    }

    /**
     * Register a renderer for a collar type
     * Call this during {@link net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent}.
     * This method is safe to call during parallel mod loading.
     */
    public static void registerRenderer(Accoutrement entityClass, IAccoutrementRenderer<?> shader) {
        accoutrementRenderMap.put(entityClass.delegate, shader);
    }

    public static void registerRenderer(Supplier<? extends Skill> entityClass, ISkillRenderer<?> shader) {
        registerRenderer(entityClass.get(), shader);
    }

    public static void registerRenderer(Skill entityClass, ISkillRenderer<?> shader) {
        skillRendererMap.put(entityClass.delegate, shader);
    }


    public static boolean hasRenderer(Accoutrement type) {
        return accoutrementRenderMap.containsKey(type.delegate);
    }

    @Nullable
    public static IAccoutrementRenderer<?> getRendererFor(Accoutrement type) {
        return accoutrementRenderMap.get(type.delegate);
    }

    @Nullable
    public static ISkillRenderer<?> getRendererFor(Skill skill) {
        return skillRendererMap.get(skill.delegate);
    }
}