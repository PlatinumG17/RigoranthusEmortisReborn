package com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell;


import com.platinumg17.rigoranthusemortisreborn.api.RigoranthusEmortisRebornAPI;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.AbstractAugment;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.AbstractCastMethod;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.AbstractSpellPart;
import net.minecraft.entity.LivingEntity;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class RigSpell {

    public static final com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.RigSpell EMPTY = new com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.RigSpell();

    public List<AbstractSpellPart> recipe = new ArrayList<>();
    private int cost;

    public RigSpell(List<AbstractSpellPart> recipe){
        this.recipe = recipe == null ? new ArrayList<>() : recipe; // Safe check for tiles initializing a null
        this.cost = getInitialCost();
    }

    public RigSpell(){ }

    public RigSpell(AbstractSpellPart... spellParts){
        super();
        add(spellParts);
    }

    public com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.RigSpell add(AbstractSpellPart spellPart){
        recipe.add(spellPart);
        return this;
    }

    public com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.RigSpell add(AbstractSpellPart... spellParts){
        for(AbstractSpellPart part : spellParts)
            add(part);
        return this;
    }

    public com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.RigSpell add(AbstractSpellPart spellPart, int count){
        for(int i = 0; i < count; i++)
            recipe.add(spellPart);
        return this;
    }

    public int getSpellSize(){
        return recipe.size();
    }

    public @Nullable
    AbstractCastMethod getCastMethod(){
        if(this.recipe == null || this.recipe.isEmpty())
            return null;
        return this.recipe.get(0) instanceof AbstractCastMethod ? (AbstractCastMethod) recipe.get(0) : null;
    }

    public List<AbstractAugment> getAugments(int startPosition, @Nullable LivingEntity caster){
        ArrayList<AbstractAugment> augments = new ArrayList<>();
        if(recipe == null || recipe.isEmpty())
            return augments;
        for(int j = startPosition + 1; j < recipe.size(); j++){
            AbstractSpellPart next_spell = recipe.get(j);
            if(next_spell instanceof AbstractAugment){
                augments.add((AbstractAugment) next_spell);
            }else{
                break;
            }
        }
        return augments;
    }

    public int getInstanceCount(AbstractSpellPart spellPart){
        int count = 0;
        for (AbstractSpellPart abstractSpellPart : this.recipe) {
            if (abstractSpellPart.equals(spellPart))
                count++;
        }
        return count;
    }

    @Deprecated
    public int getBuffsAtIndex(int startPosition, @Nullable LivingEntity caster, Class<? extends AbstractAugment> augmentClass){
        return (int) getAugments(startPosition, caster).stream().filter(a -> a.getClass().equals(augmentClass)).count();
    }

    public int getBuffsAtIndex(int startPosition, @Nullable LivingEntity caster, AbstractAugment augment){
        return (int) getAugments(startPosition, caster).stream().filter(a -> a.equals(augment)).count();
    }

    private int getInitialCost(){
        int cost = 0; // TODO Rem
        if(recipe == null)
            return cost;
        for (int i = 0; i < recipe.size(); i++) {
            AbstractSpellPart spell = recipe.get(i);
            if (!(spell instanceof AbstractAugment)) {
                List<AbstractAugment> augments = getAugments(i, null);
                cost += spell.getAdjustedDominionCost(augments);
            }
        } //TODO Rem
        return cost;
    }

    public int getCastingCost(){
        return Math.max(0, cost);
    }

    public void setCost(int cost){
        this.cost = Math.max(0, cost);
    }

    public boolean isEmpty(){
        return recipe == null || recipe.isEmpty();
    }

    public String serialize(){
        List<String> tags = new ArrayList<>();
        for(AbstractSpellPart slot : recipe){
            tags.add(slot.tag);
        }
        return tags.toString();
    }

    public static com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.RigSpell deserialize(String recipeStr){
        ArrayList<AbstractSpellPart> recipe = new ArrayList<>();
        if (recipeStr.length() <= 3) // Account for empty strings and '[,]'
            return new com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.RigSpell(recipe);
        String[] recipeList = recipeStr.substring(1, recipeStr.length() - 1).split(",");
        for(String id : recipeList){
            if (RigoranthusEmortisRebornAPI.getInstance().getSpell_map().containsKey(id.trim()))
                recipe.add(RigoranthusEmortisRebornAPI.getInstance().getSpell_map().get(id.trim()));
        }
        return new com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.RigSpell(recipe);
    }

    public String getDisplayString(){
        StringBuilder str = new StringBuilder();
        String lastStr = "";

        for(int i = 0; i < recipe.size(); i++){
            AbstractSpellPart spellPart = recipe.get(i);
            int num = 1;
            for(int j = i + 1; j < recipe.size(); j++){
                if(spellPart.name.equals(recipe.get(j).name))
                    num++;
                else
                    break;
            }
            if(num > 1){
                str.append(spellPart.getLocaleName()).append(" x").append(num);
                i += num - 1;
            }else{
                str.append(spellPart.getLocaleName());
            }
            if(i < recipe.size() - 1){
                str.append(" -> ");
            }
        }
        return str.toString();
    }

    public boolean isValid(){
        return this.recipe != null && !this.recipe.isEmpty();
    }

    public static class Builder{
        private com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.RigSpell spell;
        public Builder(){
            this.spell = new com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.RigSpell();
        }
        public com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.RigSpell.Builder add(AbstractSpellPart spellPart){
            this.spell.add(spellPart);
            return this;
        }
        public com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.RigSpell.Builder add(AbstractSpellPart spellPart, int count){ // TODO Rem
            for(int i = 0; i < count; i++)
                this.spell.add(spellPart);
            return this;
        } // TODO Rem
        public com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.RigSpell build(){
            return spell;
        }
    }
}
