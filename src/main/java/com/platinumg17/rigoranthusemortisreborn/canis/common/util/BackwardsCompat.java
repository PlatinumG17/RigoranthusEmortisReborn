package com.platinumg17.rigoranthusemortisreborn.canis.common.util;

import com.platinumg17.rigoranthusemortisreborn.canis.CanisItems;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.accouterments.CanisAccouterments;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.serializers.DimensionDependantArg;
import com.platinumg17.rigoranthusemortisreborn.api.RigoranthusEmortisRebornAPI;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.feature.EnumMode;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.registry.AccoutrementInstance;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.registry.Skill;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.registry.SkillInstance;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

public class BackwardsCompat {

    public static void readMode(CompoundNBT compound, Consumer<EnumMode> consumer) {
        consumer.accept(EnumMode.byIndex(compound.getInt("mode")));
    }

    public static void readHasBone(CompoundNBT compound, Consumer<ItemStack> consumer) {
        if (compound.contains("hasBone", Constants.NBT.TAG_BYTE) && compound.getBoolean("hasBone")) {
            int variant = compound.getInt("boneVariant");
            if (variant == 0) {
                consumer.accept(new ItemStack(CanisItems.THROW_BONE.get()));
            } else if (variant == 1) {
                consumer.accept(new ItemStack(CanisItems.THROW_STICK.get()));
            }
        }
    }

    public static void readBowlLocations(CompoundNBT compound, DimensionDependantArg<Optional<BlockPos>> bowlsData) {
        if (compound.contains("bowlPosX", Constants.NBT.TAG_ANY_NUMERIC)) {
            bowlsData.put(World.OVERWORLD, Optional.of(new BlockPos(compound.getInt("bowlPosX"), compound.getInt("bowlPosY"), compound.getInt("bowlPosZ"))));
        }
    }

    public static void readBedLocations(CompoundNBT compound, DimensionDependantArg<Optional<BlockPos>> bedsData) {
        if (compound.contains("bedPosX", Constants.NBT.TAG_ANY_NUMERIC)) {
            bedsData.put(World.OVERWORLD, Optional.of(new BlockPos(compound.getInt("bedPosX"), compound.getInt("bedPosY"), compound.getInt("bedPosZ"))));
        }
    }

    public static void readAccouterments(CompoundNBT compound, List<AccoutrementInstance> accouterments) {
        if (compound.contains("sunglasses", Constants.NBT.TAG_BYTE) && compound.getBoolean("sunglasses")) {
            accouterments.add(CanisAccouterments.SUNGLASSES.get().getDefault());
        }
        if (compound.contains("collarColor", Constants.NBT.TAG_ANY_NUMERIC)) {
            int value = compound.getInt("collarColor");

            if (value >= 0) {
                accouterments.add(CanisAccouterments.DYEABLE_COLLAR.get().create(value));
            } else if (value >= -1) {
                accouterments.add(CanisAccouterments.DYEABLE_COLLAR.get().getDefault());
            } else if (value == -3) {
                accouterments.add(CanisAccouterments.GOLDEN_COLLAR.get().getDefault());
            }// -4 and below were not used
        }
    }

    public static void readSkillMapping(CompoundNBT compound, List<SkillInstance> skillMap) {
//        if (compound.contains("skills", Constants.NBT.TAG_STRING)) {String[] split = compound.getString("skills").split(":");
//            if (split.length > 0 && split.length % 2 == 0) {
//                for (int i = 0; i < split.length; i += 2) {final int levelIndex = i + 1;
//                    BackwardsCompat.getSkillMap(split[i]).ifPresent((skill) -> {int level = 0;
//                        try {level = Integer.valueOf(split[levelIndex]);} catch (Exception e) {return;}
//                        if (skill != null) { // Only load if skill existsskillMap.add(skill.get().getDefault(level));}});}}
//        } else
        if (compound.contains("skill_level_list", Constants.NBT.TAG_LIST)) {
            ListNBT list = compound.getList("skill_level_list", Constants.NBT.TAG_COMPOUND);
            for (int i = 0; i < list.size(); i++) {
                CompoundNBT skillCompound = list.getCompound(i);
                Skill skill = NBTUtilities.getRegistryValue(skillCompound, "skill", RigoranthusEmortisRebornAPI.SKILLS);

                if (skill != null) { // Only load if skill exists
                    int level = skillCompound.getInt("level");
                    skillMap.add(skill.getDefault(level));
                }
            }
        }
    }
//    public static void readCanisTexture(CompoundNBT compound, Consumer<String> consumer) {
//        if (compound.contains("canisTex", Constants.NBT.TAG_ANY_NUMERIC)) {
//            int value = compound.getInt("canisTex");
//            String[] textures = {"bad647ebc2ac822563eaedaa3cb64881d8d7fd24", "c22b2f6d7a902c13d2f9c377f360127b6ae2dd65", "bea8cace65c013ca9cdae76b0664f4176502e4fb", "df167655cf5db4147e28d6920862636ce94c22cd", "b82d3e99a8ef342fbdc5f5a3f6c91b3940f80f55",};
//            if (value >= 0 && value < textures.length) {consumer.accept(textures[value]);}
//        }
//    }
}