package com.platinumg17.rigoranthusemortisreborn.magica.common.items;

//import com.platinumg17.rigoranthusemortisreborn.magica.common.lib.LibItemNames;
//import com.platinumg17.rigoranthusemortisreborn.magica.setup.MagicItemsRegistry;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.entity.player.ServerPlayerEntity;
//import net.minecraft.item.ItemStack;
//import net.minecraft.util.ActionResult;
//import net.minecraft.util.ActionResultType;
//import net.minecraft.util.Hand;
//import net.minecraft.util.registry.Registry;
//import net.minecraft.world.World;
//import vazkii.patchouli.api.PatchouliAPI;
//
//import javax.annotation.Nonnull;
//
//public class EmorticOrigins extends ModItem{
//
//    public EmorticOrigins() {
//        super(MagicItemsRegistry.defaultItemProperties().stacksTo(1), LibItemNames.EMORTIC_ORIGINS);
//    }
//
//    @Nonnull
//    @Override
//    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
//        ItemStack stack = playerIn.getItemInHand(handIn);
//
//        if(playerIn instanceof ServerPlayerEntity) {
//            ServerPlayerEntity player=  (ServerPlayerEntity) playerIn;
//            PatchouliAPI.instance.openBookGUI((ServerPlayerEntity) playerIn, Registry.ITEM.getKey(this));
//        }
//
//        return new ActionResult<>(ActionResultType.PASS, stack);
//    }
//}