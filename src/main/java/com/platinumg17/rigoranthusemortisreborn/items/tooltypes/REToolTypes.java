package com.platinumg17.rigoranthusemortisreborn.items.tooltypes;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class REToolTypes {

    private final Set<Material> harvestMaterials = new HashSet<>();
    private final Set<Enchantment> enchantments = new HashSet<>();
    private final Set<ToolType> toolType = new HashSet<>();

    public REToolTypes(ToolType toolType, Material... materials) {
        this(materials);
        this.toolType.add(toolType);
    }
    public REToolTypes(Material... materials) {harvestMaterials.addAll(Arrays.asList(materials));}
    public REToolTypes() {}

    public REToolTypes(REToolTypes... classCombo) {
        for(REToolTypes cls : classCombo) {
            harvestMaterials.addAll(cls.harvestMaterials);
            enchantments.addAll(cls.enchantments);
            toolType.addAll(cls.toolType);
        }
    }

    public boolean canHarvest(BlockState state) {
        if(harvestMaterials.contains(state.getMaterial()))
            return true;

        for(ToolType type : toolType)
            if(state.getBlock().isToolEffective(state, type))
                return true;

        return false;
    }

    public REToolTypes addToolType(ToolType... toolType) {
        this.toolType.addAll(Arrays.asList(toolType));
        return this;
    }

    //TODO Tool types WILL be created before mod enchantments are created and registered. We should use suppliers instead
    public REToolTypes addEnchantments(Enchantment... enchantments) {
        this.enchantments.addAll(Arrays.asList(enchantments));
        return this;
    }

    public REToolTypes addEnchantments(List<Enchantment> enchantments) {
        this.enchantments.addAll(enchantments);
        return this;
    }

    public REToolTypes addEnchantments(EnchantmentType... enchantmentTypes) {
        for(EnchantmentType type : enchantmentTypes) {
            ForgeRegistries.ENCHANTMENTS.forEach(enchantment ->
            {if(enchantment.category == type) addEnchantments(enchantment);});
        }
        return this;
    }

    public Set<ToolType> getToolTypes() {return toolType;}
    public Set<Material> getHarvestMaterials() {return harvestMaterials;}
    public Set<Enchantment> getEnchantments() {return enchantments;}  //TODO Consider --> functions that check if a material/enchant is valid, rather than make the whole lists accessible.
}
