package com.platinumg17.rigoranthusemortisreborn.canis.client.block.model;

import com.platinumg17.rigoranthusemortisreborn.canis.common.util.CanisBedUtil;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemOverrideList;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import org.apache.commons.lang3.tuple.Pair;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.registry.IBeddingMaterial;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.registry.ICasingMaterial;

import javax.annotation.Nullable;

public class CanisBedItemOverride extends ItemOverrideList {
    @Override
    public IBakedModel resolve(IBakedModel modelOriginal, ItemStack stack, @Nullable ClientWorld world, @Nullable LivingEntity livingEntity) {
        if (modelOriginal instanceof CanisBedModel) {
            Pair<ICasingMaterial, IBeddingMaterial> materials = CanisBedUtil.getMaterials(stack);
            return ((CanisBedModel) modelOriginal).getModelVariant(materials.getLeft(), materials.getRight(), Direction.NORTH);}
        return modelOriginal;
    }
}
