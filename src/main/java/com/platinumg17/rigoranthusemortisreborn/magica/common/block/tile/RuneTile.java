package com.platinumg17.rigoranthusemortisreborn.magica.common.block.tile;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.AbstractSpellPart;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.EntitySpellResolver;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.Spell;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.SpellContext;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.interfaces.IPickupResponder;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.util.BlockUtil;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.util.DominionUtil;
import com.platinumg17.rigoranthusemortisreborn.magica.client.particle.ParticleColor;
import com.platinumg17.rigoranthusemortisreborn.magica.client.particle.ParticleUtil;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.RuneBlock;
import com.platinumg17.rigoranthusemortisreborn.magica.common.spell.method.MethodTouch;
import com.platinumg17.rigoranthusemortisreborn.magica.common.util.PortUtil;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.BlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.items.IItemHandler;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.UUID;

public class RuneTile extends AnimatedTile implements IPickupResponder, IAnimatable {
    public Spell spell = Spell.EMPTY;
    public boolean isTemporary;
    public boolean isCharged;
    public int ticksUntilCharge;
    public UUID uuid;
    public ParticleColor color = ParticleUtil.defaultParticleColor();
    public Entity touchedEntity;
    public RuneTile() {
        super(BlockRegistry.RUNE_TILE);
        isCharged = true;
        isTemporary = false;
        ticksUntilCharge = 0;
    }

    public void setSpell(Spell spell) {
        this.spell = spell;
    }

    public void castSpell(Entity entity){
        if(entity == null)
            return;
        if(!this.isCharged || spell.isEmpty() || !(level instanceof ServerWorld) || !(spell.recipe.get(0) instanceof MethodTouch))
            return;
        try {
            PlayerEntity playerEntity = uuid != null ? level.getPlayerByUUID(uuid) : FakePlayerFactory.getMinecraft((ServerWorld) level);
            playerEntity = playerEntity == null ?  FakePlayerFactory.getMinecraft((ServerWorld) level) : playerEntity;
            EntitySpellResolver resolver = new EntitySpellResolver(new SpellContext(spell, playerEntity).withCastingTile(this).withType(SpellContext.CasterType.RUNE));
            resolver.onCastOnEntity(ItemStack.EMPTY, playerEntity, entity, Hand.MAIN_HAND);
            if (this.isTemporary) {
                level.destroyBlock(worldPosition, false);
                return;
            }
            this.isCharged = false;

            level.setBlockAndUpdate(worldPosition, level.getBlockState(worldPosition).cycle(RuneBlock.POWERED));
            ticksUntilCharge = 20 * 2;
        }catch (Exception e){
            PortUtil.sendMessage(entity, new TranslationTextComponent("rigoranthusemortisreborn.rune.error"));
            e.printStackTrace();
            level.destroyBlock(worldPosition, false);
        }
    }

    public void setParsedSpell(List<AbstractSpellPart> spell){
        if(spell.size() <= 1){
            this.spell = Spell.EMPTY;
            return;
        }
        spell.set(0, MethodTouch.INSTANCE);
        this.spell = new Spell(spell);
    }

    @Override
    public CompoundNBT save(CompoundNBT tag) {
        tag.putString("spell", spell.serialize());
        tag.putBoolean("charged", isCharged);
        tag.putBoolean("temp", isTemporary);
        tag.putInt("cooldown", ticksUntilCharge);
        if(uuid != null)
            tag.putUUID("uuid", uuid);
        if(color != null)
            tag.putString("color", color.toWrapper().serialize());
        return super.save(tag);
    }

    @Override
    public void load( BlockState state, CompoundNBT tag) {
        this.spell = Spell.deserialize(tag.getString("spell"));
        this.isCharged = tag.getBoolean("charged");
        this.isTemporary = tag.getBoolean("temp");
        this.ticksUntilCharge = tag.getInt("cooldown");
        if(tag.contains("uuid"))
            this.uuid = tag.getUUID("uuid");
        this.color = ParticleColor.IntWrapper.deserialize(tag.getString("color")).toParticleColor();
        super.load(state, tag);
    }

    @Override
    public void tick() {
        if(level == null)
            return;
        if(!level.isClientSide) {
            if (ticksUntilCharge > 0) {
                ticksUntilCharge -= 1;
                return;
            }
        }
        if(this.isCharged)
            return;
        if(!level.isClientSide && this.isTemporary){
            level.destroyBlock(this.worldPosition, false);
        }
        if(!level.isClientSide){
            BlockPos fromPos = DominionUtil.takeDominionNearbyWithParticles(worldPosition, level, 10, 100);
            if(fromPos != null) {
                this.isCharged = true;
                level.setBlockAndUpdate(worldPosition, level.getBlockState(worldPosition).cycle(RuneBlock.POWERED));
            }else
                ticksUntilCharge = 20 * 3;
        }
    }

    @Override
    public List<IItemHandler> getInventory() {
        return BlockUtil.getAdjacentInventories(level, worldPosition);
    }

    @Override
    public @Nonnull ItemStack onPickup(ItemStack stack) {
        return BlockUtil.insertItemAdjacent(level, worldPosition, stack);
    }

    @Override
    public void registerControllers(AnimationData data) {
    }
    AnimationFactory factory = new AnimationFactory(this);
    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}