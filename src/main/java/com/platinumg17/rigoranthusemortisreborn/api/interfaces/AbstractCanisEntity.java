package com.platinumg17.rigoranthusemortisreborn.api.interfaces;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import com.platinumg17.rigoranthusemortisreborn.api.feature.EnumGender;
import com.platinumg17.rigoranthusemortisreborn.api.feature.ICanis;

import javax.annotation.Nullable;
import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Function;

public abstract class AbstractCanisEntity extends TameableEntity implements ICanis {

    protected AbstractCanisEntity(EntityType<? extends TameableEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public void setAttributeModifier(Attribute attribute, UUID modifierUUID, BiFunction<AbstractCanisEntity, UUID, AttributeModifier> modifierGenerator) {
        ModifiableAttributeInstance attributeInst = this.getAttribute(attribute);
        AttributeModifier currentModifier = attributeInst.getModifier(modifierUUID);
        if (currentModifier != null) {
            attributeInst.removeModifier(modifierUUID);
        }
        AttributeModifier newModifier = modifierGenerator.apply(this, modifierUUID);
        if (newModifier != null) {
            attributeInst.addTransientModifier(newModifier);
        }
    }
    public void removeAttributeModifier(Attribute attribute, UUID modifierUUID) {this.getAttribute(attribute).removeModifier(modifierUUID);}
    @Override public AbstractCanisEntity getCanis() {
        return this;
    }
    @Override public float getSoundVolume() {
        return super.getSoundVolume();
    }
    @Override public void spawnTamingParticles(boolean play) {
        super.spawnTamingParticles(play);
    }

    public void consumeItemFromStack(@Nullable Entity entity, ItemStack stack) {
        if (entity instanceof PlayerEntity) {
            stack.shrink(1);
        } else {
            stack.shrink(1);
        }
    }
    public abstract TranslationTextComponent getTranslationKey(Function<EnumGender, String> function);
    public TranslationTextComponent getGenderPronoun() {return this.getTranslationKey(EnumGender::getUnlocalisedPronoun);}
    public TranslationTextComponent getGenderSubject() {return this.getTranslationKey(EnumGender::getUnlocalisedSubject);}
    public TranslationTextComponent getGenderTitle() {
        return this.getTranslationKey(EnumGender::getUnlocalisedTitle);
    }
    public TranslationTextComponent getGenderTip() {
        return this.getTranslationKey(EnumGender::getUnlocalisedTip);
    }
    public TranslationTextComponent getGenderName() {
        return this.getTranslationKey(EnumGender::getUnlocalisedName);
    }
}