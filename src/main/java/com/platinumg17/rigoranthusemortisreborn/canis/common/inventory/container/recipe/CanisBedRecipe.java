package com.platinumg17.rigoranthusemortisreborn.canis.common.inventory.container.recipe;

import com.platinumg17.rigoranthusemortisreborn.canis.common.util.CanisRecipeSerializers;
import com.platinumg17.rigoranthusemortisreborn.canis.common.util.CanisBedUtil;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.SpecialRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.crafting.IShapedRecipe;
import com.platinumg17.rigoranthusemortisreborn.api.RigoranthusEmortisRebornAPI;
import com.platinumg17.rigoranthusemortisreborn.api.registry.IBeddingMaterial;
import com.platinumg17.rigoranthusemortisreborn.api.registry.ICasingMaterial;

public class CanisBedRecipe extends SpecialRecipe implements IShapedRecipe<CraftingInventory> {

    public CanisBedRecipe(ResourceLocation resource) {
        super(resource);
    }

    @Override
    public boolean matches(CraftingInventory inv, World worldIn) {
        IBeddingMaterial beddingId = null;
        ICasingMaterial casingId = null;
        for (int col = 0; col < 3; col++) {
            for (int row = 0; row < 3; row++) {
                if (col == 1 && row < 2) {
                    IBeddingMaterial id = CanisBedUtil.getBeddingFromStack(RigoranthusEmortisRebornAPI.BEDDING_MATERIAL, inv.getItem(row * inv.getWidth() + col));
                    if (id == null) {return false;}
                    if (beddingId == null) {beddingId = id;}
                    else if (beddingId != id) {return false;}
                }
                else {
                    ICasingMaterial id = CanisBedUtil.getCasingFromStack(RigoranthusEmortisRebornAPI.CASING_MATERIAL, inv.getItem(row * inv.getWidth() + col));
                    if (id == null) {return false;}
                    if (casingId == null) {casingId = id;}
                    else if (casingId != id) {return false;}
                }
            }
        }
        return true;
    }

    @Override
    public ItemStack assemble(CraftingInventory inv) {
        IBeddingMaterial beddingId = CanisBedUtil.getBeddingFromStack(RigoranthusEmortisRebornAPI.BEDDING_MATERIAL, inv.getItem(1));
        ICasingMaterial casingId = CanisBedUtil.getCasingFromStack(RigoranthusEmortisRebornAPI.CASING_MATERIAL, inv.getItem(0));
        return CanisBedUtil.createItemStack(casingId, beddingId);
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingInventory inv) {
        NonNullList<ItemStack> nonnulllist = NonNullList.<ItemStack>withSize(inv.getContainerSize(), ItemStack.EMPTY);
        for (int i = 0; i < nonnulllist.size(); ++i) {
            ItemStack itemstack = inv.getItem(i);
            nonnulllist.set(i, net.minecraftforge.common.ForgeHooks.getContainerItem(itemstack));
        }
        return nonnulllist;
    }
    //Is on a 3x3 grid or bigger
    @Override public boolean canCraftInDimensions(int width, int height) {return width >= 3 && height >= 3;}
    @Override public IRecipeSerializer<?> getSerializer() {return CanisRecipeSerializers.CANIS_BED.get();}
    @Override public int getRecipeWidth() {return 3;}
    @Override public int getRecipeHeight() {return 3;}
}
