package com.platinumg17.rigoranthusemortisreborn.magica.common.items;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.MagicItemsRegistry;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ModItem extends Item {
    public List<ITextComponent> tooltip;
    public Rarity rarity;
    public ModItem(Properties properties) {
        super(properties);
    }

    public ModItem(Properties properties, String registryName){
        this(properties);
        setRegistryName(EmortisConstants.MOD_ID, registryName);
    }

    public ModItem(String registryName){
        this(MagicItemsRegistry.defaultItemProperties(), registryName);
    }

    public ModItem withTooltip(ITextComponent tip){
        tooltip = new ArrayList<>();
        tooltip.add(tip);
        return this;
    }

    public ModItem withRarity(Rarity rarity){
        this.rarity = rarity;
        return this;
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return rarity != null ? rarity : super.getRarity(stack);
    }

    public ItemStack getStack(){
        return new ItemStack(this);
    }

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip2, ITooltipFlag flagIn) {
        if(tooltip != null && !tooltip.isEmpty()){
            tooltip2.addAll(tooltip);
        }
    }
}