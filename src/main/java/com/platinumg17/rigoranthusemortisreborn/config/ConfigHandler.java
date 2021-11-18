package com.platinumg17.rigoranthusemortisreborn.config;

import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;
import com.platinumg17.rigoranthusemortisreborn.api.RigoranthusEmortisRebornAPI;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.registry.Skill;

import java.util.HashMap;
import java.util.Map;

public class ConfigHandler {

    private static ClientConfig CLIENT;
    private static SkillConfig SKILL;
    private static ForgeConfigSpec CONFIG_CLIENT_SPEC;
    private static ForgeConfigSpec CONFIG_SKILL_SPEC;

    public static void init(IEventBus modEventBus) {
        Pair<ClientConfig, ForgeConfigSpec> clientPair = new ForgeConfigSpec.Builder().configure(ClientConfig::new);
        CONFIG_CLIENT_SPEC = clientPair.getRight();
        CLIENT = clientPair.getLeft();

        RigoranthusEmortisReborn.LOGGER.debug("Register configs");

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, CONFIG_CLIENT_SPEC);

        modEventBus.addListener(ConfigHandler::loadConfig);
        modEventBus.addListener(ConfigHandler::reloadConfig);
    }

    public static void initSkillConfig() {
        Pair<SkillConfig, ForgeConfigSpec> skillPair = new ForgeConfigSpec.Builder().configure(SkillConfig::new);
        CONFIG_SKILL_SPEC = skillPair.getRight();
        SKILL = skillPair.getLeft();

        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, CONFIG_SKILL_SPEC, "rigoranthusemortisreborn-skills.toml");
    }

    public static void loadConfig(final ModConfig.Loading event) {
        ModConfig config = event.getConfig();

        if (config.getSpec() == ConfigHandler.CONFIG_CLIENT_SPEC) {
            ConfigHandler.refreshClient();
        } else if (config.getSpec() == ConfigHandler.CONFIG_SKILL_SPEC) {
            ConfigHandler.refreshSkills();
        }
    }

    public static void reloadConfig(final ModConfig.Reloading event) {
        ModConfig config = event.getConfig();
        if (config.getSpec() == ConfigHandler.CONFIG_CLIENT_SPEC) {
            ConfigHandler.refreshClient();
        } else if (config.getSpec() == ConfigHandler.CONFIG_SKILL_SPEC) {
            ConfigHandler.refreshSkills();
        }
    }

    public static void refreshClient() {
        RigoranthusEmortisReborn.LOGGER.debug("Refresh Client Config");
        ConfigValues.HOMINI_PARTICLES       = CLIENT.HOMINI_PARTICLES.get();
        ConfigValues.RENDER_CHEST           = CLIENT.RENDER_CHEST.get();
        ConfigValues.SHOW_SMELTERY_ERRORS   = CLIENT.SHOW_SMELTERY_ERRORS.get();
        ConfigValues.cache_capacity         = CLIENT.cache_capacity.get();
        ConfigValues.enableJeiPlugin        = CLIENT.enableJeiPlugin.get();
        ConfigValues.enableJeiCatalysts     = CLIENT.enableJeiCatalysts.get();
        ConfigValues.enableJeiClickArea     = CLIENT.enableJeiClickArea.get();
        ConfigValues.smelteryXPDropValue    = CLIENT.smelteryXPDropValue.get();
        ConfigValues.smelteryXPDropValue2   = CLIENT.smelteryXPDropValue2.get();
        ConfigValues.masterfulSmelterySpeed = CLIENT.masterfulSmelterySpeed.get();
    }

    public static void refreshSkills() {
        RigoranthusEmortisReborn.LOGGER.debug("Refreshing Skills Config");
        ConfigValues.DISABLED_SKILLS.clear();
        SKILL.DISABLED_SKILLS.forEach((skill, val) -> {
            if (!val.get()) {ConfigValues.DISABLED_SKILLS.add(skill);}
        });
    }

    static class ClientConfig {

        public ForgeConfigSpec.BooleanValue HOMINI_PARTICLES;
        public ForgeConfigSpec.BooleanValue RENDER_CHEST;
        public ForgeConfigSpec.BooleanValue SHOW_SMELTERY_ERRORS;
        public ForgeConfigSpec.IntValue cache_capacity;
        public ForgeConfigSpec.IntValue smelteryXPDropValue;
        public ForgeConfigSpec.IntValue smelteryXPDropValue2;
        public ForgeConfigSpec.IntValue masterfulSmelterySpeed;
        public ForgeConfigSpec.BooleanValue enableJeiPlugin;
        public ForgeConfigSpec.BooleanValue enableJeiCatalysts;
        public ForgeConfigSpec.BooleanValue enableJeiClickArea;

        public ClientConfig(ForgeConfigSpec.Builder builder) {
            builder.push("General");
            builder.pop();

            builder.push("Canis Rendering");
                    HOMINI_PARTICLES = builder
                            .comment(" Enables the particle effect on Homini Level 30 cani.")
                            .translation("rigoranthusemortisreborn.config.client.enable_homini_particles")
                            .define("enable_homini_particles", true);
                    RENDER_CHEST = builder
                            .comment(" When enabled, Cani with points in Wayward Traveller will have chests on their side.")
                            .translation("rigoranthusemortisreborn.config.client.render_chest")
                            .define("render_chest", true);
            builder.pop();

            builder.push("JEI Settings");
                    enableJeiPlugin = builder
                            .comment(" Enable or disable the JeiPlugin for the mod.")
                            .translation("rigoranthusemortisreborn.config.client.enable_jei")
                            .define("enable_jei", true);
                    enableJeiCatalysts = builder
                            .comment(" Enable or disable the Catalysts in Jei for the mod.")
                            .translation("rigoranthusemortisreborn.config.client.enable_jei_catalysts")
                            .define("enable_jei_catalysts", true);
                    enableJeiClickArea = builder
                            .comment(" Enable or disable the Clickable Area inside the Masterful Smeltery's GUI.")
                            .translation("rigoranthusemortisreborn.config.client.enable_jei_click_area")
                            .define("enable_jei_click_area", true);
            builder.pop();

            builder.push("Masterful Smeltery Settings");
                    cache_capacity = builder
                            .comment(" The capacity of the recipe cache, higher values use more memory.\n Default: 10")
                            .translation("rigoranthusemortisreborn.config.client.recipe_cache")
                            .defineInRange("recipe_cache", 10, 1, 100);
                    masterfulSmelterySpeed = builder
                            .comment(" Number of ticks per 'Smelting Operation.'\n Vanilla Furnace = 200 ticks.\n 1 Second = 20 Ticks\n Default: 160")
                            .translation("rigoranthusemortisreborn.config.client.masterful_smeltery_speed")
                            .defineInRange("masterful_smeltery_speed", 160, 2, 72000);
                    smelteryXPDropValue = builder
                            .comment(" Value indicating when the Masterful Smeltery should 'overload' and auto-eject the stored xp. \n Default: 10, Recipes")
                            .translation("rigoranthusemortisreborn.config.client.xp_value")
                            .defineInRange("xp_value", 10, 1, 500);
                    smelteryXPDropValue2 = builder
                            .comment(" Value indicating when the smeltery should 'overload' and auto-eject the stored xp. \n Default: 100000, Single recipe uses")
                            .translation("rigoranthusemortisreborn.config.client.xp_value_two")
                            .defineInRange("xp_value_two", 100000, 1, 1000000);
            builder.pop();

            builder.push("Miscellaneous");
                    SHOW_SMELTERY_ERRORS = builder
                            .comment(" Debugging Tool that prints Smeltery Settings errors in chat.")
                            .translation("rigoranthusemortisreborn.config.client.display_smeltery_errors")
                            .define("display_smeltery_errors", false);
            builder.pop();
        }
    }

    static class SkillConfig {
        public Map<Skill, ForgeConfigSpec.BooleanValue> DISABLED_SKILLS;
        public SkillConfig(ForgeConfigSpec.Builder builder) {
            builder.comment("Here you can disable skills.").push("Skills");
            DISABLED_SKILLS = new HashMap<Skill, ForgeConfigSpec.BooleanValue>();
            RigoranthusEmortisRebornAPI.SKILLS.forEach((loc) -> DISABLED_SKILLS.put(loc, builder.define(loc.getRegistryName().toString(), true)));
            builder.pop();
        }
    }
}