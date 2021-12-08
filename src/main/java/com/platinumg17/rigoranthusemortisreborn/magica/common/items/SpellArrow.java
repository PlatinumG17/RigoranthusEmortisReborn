package com.platinumg17.rigoranthusemortisreborn.magica.common.items;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.item.ICasterTool;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.dominion.IDominion;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.*;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.magica.common.capability.DominionCapability;
import com.platinumg17.rigoranthusemortisreborn.magica.common.entity.EntitySpellArrow;
import com.platinumg17.rigoranthusemortisreborn.magica.common.spell.augment.AugmentPierce;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.MagicItemsRegistry;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.interfaces.ISpellCaster;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import java.util.List;

public class SpellArrow extends ArrowItem {

    public AbstractSpellPart part;
    public int numParts;

    public SpellArrow(String registryName, AbstractAugment augment, int numParts) {
        super(MagicItemsRegistry.defaultItemProperties());
        setRegistryName(EmortisConstants.MOD_ID, registryName);
        this.part = augment;
        this.numParts = numParts;
    }

    public void modifySpell(Spell spell){
        for(int i = 0; i < numParts; i++){
            spell.recipe.add(part);
        }
    }

    @Override
    public AbstractArrowEntity createArrow(World world, ItemStack stack, LivingEntity shooter) {
        IDominion dominion = DominionCapability.getDominion(shooter).orElse(null);
        if(dominion == null)
            return new ArrowEntity(world, shooter);
        EntitySpellArrow spellArrow = new EntitySpellArrow(world, shooter);
        if(!(shooter instanceof PlayerEntity) || !(( shooter).getMainHandItem().getItem() instanceof ICasterTool))
            return super.createArrow(world, stack, shooter);
        PlayerEntity entity = (PlayerEntity)shooter;
        ICasterTool caster = (ICasterTool) entity.getMainHandItem().getItem();
        ISpellCaster spellCaster = caster.getSpellCaster(entity.getMainHandItem());
        Spell spell = spellCaster.getSpell();
        modifySpell(spell);
        spell.setCost(spell.getCastingCost() - part.getDominionCost() * numParts);
        spellArrow.spellResolver = new SpellResolver(new SpellContext(spell, entity)).withSilent(true);
        spellArrow.pierceLeft = spell.getBuffsAtIndex(0, shooter, AugmentPierce.class);
        return spellArrow;
    }

    @Override
    public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("rigoranthusemortisreborn.spell_arrow.desc"));
        Spell spell = new Spell();
        for(int i = 0; i < numParts; i++){
            spell.recipe.add(part);
        }
        tooltip.add(new StringTextComponent(spell.getDisplayString()));
    }
}