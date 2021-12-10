package com.platinumg17.rigoranthusemortisreborn.magica.common.items;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.ritual.AbstractRitual;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.RitualVesselBlock;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile.RitualTile;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.MagicItemsRegistry;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class RitualOffering extends ModItem{
    public AbstractRitual ritual;

    public RitualOffering(Properties properties) {
        super(properties);
    }

    public RitualOffering(String registryName, AbstractRitual ritual){
        super(MagicItemsRegistry.defaultItemProperties(), registryName);
        this.ritual = ritual;
    }

    @Override
    public ActionResultType useOn(ItemUseContext context) {
        if(context.getLevel().getBlockState(context.getClickedPos()).getBlock() instanceof RitualVesselBlock){
            World world = context.getLevel();
            BlockPos pos = context.getClickedPos();
            RitualTile tile = (RitualTile) world.getBlockEntity(pos);
            tile.setRitual(ritual.getID());
            if(!context.getPlayer().isCreative())
                context.getItemInHand().shrink(1);
            return ActionResultType.CONSUME;
        }
        return ActionResultType.PASS;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip2, ITooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip2, flagIn);
        tooltip2.add(new TranslationTextComponent("tooltip.rigoranthusemortisreborn.offering"));
    }
}