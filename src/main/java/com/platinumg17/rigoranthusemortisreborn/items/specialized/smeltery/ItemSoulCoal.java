package com.platinumg17.rigoranthusemortisreborn.items.specialized.smeltery;

import java.util.List;
import javax.annotation.Nullable;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.config.Config;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ItemSoulCoal extends Item {
    public ItemSoulCoal(Properties properties) {
        super(properties);
        setRegistryName(EmortisConstants.MOD_ID, "soul_coal");
    }
    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("tooltip." + EmortisConstants.MOD_ID + ".soul_coal").setStyle(Style.EMPTY));
        tooltip.add(new TranslationTextComponent("tooltip." + EmortisConstants.MOD_ID + ".soul_coal2").setStyle(Style.EMPTY));
    }
    @Override
    public int getBurnTime(ItemStack itemStack) {
        return Config.soulCoalBurnTime.get();
    }
}