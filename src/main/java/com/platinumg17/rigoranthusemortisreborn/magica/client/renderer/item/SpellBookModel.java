package com.platinumg17.rigoranthusemortisreborn.magica.client.renderer.item;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.interfaces.ISpellTier;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.magica.common.items.SpellBook;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class SpellBookModel extends TransformAnimatedModel<SpellBook> {
    ResourceLocation T1 =  new ResourceLocation(EmortisConstants.MOD_ID , "geo/spellbook_tier1.geo.json");
    ResourceLocation T2 =  new ResourceLocation(EmortisConstants.MOD_ID , "geo/spellbook_tier2.geo.json");
    ResourceLocation T3 =  new ResourceLocation(EmortisConstants.MOD_ID , "geo/spellbook_tier3.geo.json");
    ResourceLocation T3_CLOSED =  new ResourceLocation(EmortisConstants.MOD_ID , "geo/spellbook_tier3closed.geo.json");
    ResourceLocation T1_CLOSED =  new ResourceLocation(EmortisConstants.MOD_ID , "geo/spellbook_tier1closed.geo.json");
    ResourceLocation T2_CLOSED =  new ResourceLocation(EmortisConstants.MOD_ID , "geo/spellbook_tier2closed.geo.json");

    public boolean isOpen;

    @Override
    public ResourceLocation getModelLocation(SpellBook book, @Nullable ItemCameraTransforms.TransformType transformType) {

        if(transformType == ItemCameraTransforms.TransformType.GUI || transformType == ItemCameraTransforms.TransformType.FIXED){
            if(book.tier == ISpellTier.Tier.ONE)
                return T1_CLOSED;
            if(book.tier == ISpellTier.Tier.TWO)
                return T2_CLOSED;
            return T3_CLOSED;
        }

        if(book.tier == ISpellTier.Tier.ONE)
            return T1;
        if(book.tier == ISpellTier.Tier.TWO)
            return T2;
        return T3;
    }


    @Override
    public ResourceLocation getModelLocation(SpellBook object) {
        return getModelLocation(object, null);
    }

    @Override
    public ResourceLocation getTextureLocation(SpellBook object){
        return new ResourceLocation(EmortisConstants.MOD_ID, "textures/items/spellbook_purple.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SpellBook animatable) {
        return new ResourceLocation(EmortisConstants.MOD_ID , "animations/spellbook_animations.json");
    }
}