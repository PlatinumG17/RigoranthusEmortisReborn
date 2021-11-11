package com.platinumg17.rigoranthusemortisreborn.canis.common.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import com.platinumg17.rigoranthusemortisreborn.api.interfaces.AbstractCanisEntity;
import com.platinumg17.rigoranthusemortisreborn.api.interfaces.ICanisItem;

public class SizeBoneItem extends Item implements ICanisItem {

    private final SizeBoneItem.Type type;

    public static enum Type {
        TINY("tiny_bone"),
        BIG("big_bone");

        String n;

        Type(String n) {
            this.n = n;
        }

        public String getName() {
            return this.n;
        }
    }

    public SizeBoneItem(SizeBoneItem.Type typeIn, Properties properties) {
        super(properties);
        this.type = typeIn;
    }

    @Override
    public ActionResultType processInteract(AbstractCanisEntity canisIn, World worldIn, PlayerEntity playerIn, Hand handIn) {
//        if (canisIn.getAge() < 0) {
//
//            if (!playerIn.level.isClientSide){
//                playerIn.sendMessage(new TranslationTextComponent("treat."+this.type.getName()+".too_young"), canisIn.getUUID());
//            }
//
//            return ActionResultType.FAIL;
//        }
//        else {
            if (!playerIn.abilities.instabuild) {
                playerIn.getItemInHand(handIn).shrink(1);
            }

//            if (!playerIn.level.isClientSide) {
//                canisIn.setCanisSize(canisIn.getCanisSize() + (this.type == Type.BIG ? 1 : -1));
//                canisIn.setCanisSize(canisIn.getCanisSize() + (this.type == Type.BIG ? 1 : -1));
//            }
            return ActionResultType.SUCCESS;
//        }
    }
}