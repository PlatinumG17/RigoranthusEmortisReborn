package com.platinumg17.rigoranthusemortisreborn.magica.common.items;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.entity.familiar.AbstractFamiliarHolder;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.entity.familiar.FamiliarCap;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.entity.familiar.IFamiliarCap;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class FamiliarScript extends ModItem{
    public AbstractFamiliarHolder familiar;

    public FamiliarScript(AbstractFamiliarHolder familiar){
        super("familiar_" + familiar.id);
        this.familiar = familiar;
    }
    public FamiliarScript(Properties properties) {
        super(properties);
    }

    public FamiliarScript(Properties properties, String registryName) {
        super(properties, registryName);
    }

    public FamiliarScript(String registryName) {
        super(registryName);
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if(worldIn.isClientSide)
            return super.use(worldIn, playerIn, handIn);

        IFamiliarCap familiarCap = FamiliarCap.getFamiliarCap(playerIn).orElse(null);
        if(familiarCap != null){
            if(familiarCap.ownsFamiliar(familiar.getId())){
                playerIn.sendMessage(new TranslationTextComponent("rigoranthusemortisreborn.familiar.owned"), Util.NIL_UUID);
                return super.use(worldIn, playerIn, handIn);
            }
            familiarCap.unlockFamiliar(familiar.getId());
            FamiliarCap.syncFamiliars(playerIn);
            playerIn.sendMessage(new TranslationTextComponent("rigoranthusemortisreborn.familiar.unlocked"), Util.NIL_UUID);
            playerIn.getItemInHand(handIn).shrink(1);
        }
        return super.use(worldIn, playerIn, handIn);
    }


    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip2, ITooltipFlag flagIn) {
        tooltip2.add(new TranslationTextComponent("rigoranthusemortisreborn.familiar.script"));
    }
}