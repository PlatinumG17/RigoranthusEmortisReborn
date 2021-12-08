package com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.core.init.ItemInit;
import com.platinumg17.rigoranthusemortisreborn.magica.common.spell.augment.AugmentAmplify;
import com.platinumg17.rigoranthusemortisreborn.magica.common.spell.augment.AugmentDampen;
import com.platinumg17.rigoranthusemortisreborn.magica.common.util.SpellPartConfigUtil;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.MagicItemsRegistry;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.interfaces.ISpellTier;
import net.minecraft.item.Item;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.ForgeConfigSpec;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

public abstract class AbstractSpellPart implements ISpellTier, Comparable<AbstractSpellPart> {
    // TODO: Clarify that this is the default cost.
    public abstract int getDominionCost();
    public String tag;
    public String name;

    public String getTag(){
        return this.tag;
    }
    public String getIcon(){return this.tag + ".png";}

    protected AbstractSpellPart(String tag, String name){
        this.tag = tag;
        this.name = name;
        for(SpellSchool spellSchool : getSchools()){
            spellSchool.addSpellPart(this);
        }
    }
    // Final dominion cost
    public int getAdjustedDominionCost(List<AbstractAugment> augmentTypes){
        int cost = getConfigCost();
        for(AbstractAugment a: augmentTypes){
            if(a instanceof AugmentDampen && !dampenIsAllowed()){
                continue;
            }
            cost += a.getConfigCost();
        }
        return Math.max(cost, 0);
    }

    public int getConfigCost(){
        return COST == null ? getDominionCost() : COST.get();
    }

    @Nullable
    public Item getCraftingReagent(){
        return null;
    }

    // Check for dominion reduction exploit
    public boolean dampenIsAllowed(){
        return getCompatibleAugments().contains(AugmentDampen.INSTANCE);
    }

    public String getName(){return this.name;}

    public ISpellTier.Tier getTier() {
        return ISpellTier.Tier.ONE;
    }
    // TODO: Move to SpellStats
    @Deprecated
    public static int getBuffCount(List<AbstractAugment> augments, Class<? extends AbstractSpellPart> spellClass){
        return (int) augments.stream().filter(spellClass::isInstance).count();
    }

    public boolean hasBuff(List<AbstractAugment> augments, Class spellClass){
        return getBuffCount(augments, spellClass) > 0;
    }

    public int getAmplificationBonus(List<AbstractAugment> augmentTypes){
        return getBuffCount(augmentTypes, AugmentAmplify.class) - getBuffCount(augmentTypes, AugmentDampen.class);
    }

    /**
     * Returns the set of augments that this spell part can be enhanced by.
     *
     * @see AbstractSpellPart#augmentSetOf(AbstractAugment...) for easy syntax to make the Set.
     */
    public abstract @Nonnull Set<AbstractAugment> getCompatibleAugments();

    /**
     * Syntax support to easily make a set for {@link AbstractSpellPart#getCompatibleAugments()}
     */
    protected Set<AbstractAugment> augmentSetOf(AbstractAugment... augments) {
        return setOf(augments);
    }

    public @Nonnull Set<SpellSchool> getSchools(){
        return setOf();
    }

    protected <T> Set<T> setOf(T... list) {
        return Collections.unmodifiableSet(new HashSet<>(Arrays.asList(list)));
    }

    @Override
    public int compareTo(AbstractSpellPart o) {
        return this.getTier().ordinal() - o.getTier().ordinal();
    }

    public TranslationTextComponent getBookDescLang(){
        return new TranslationTextComponent("rigoranthusemortisreborn.glyph_desc." + getTag());
    }
    /**
     * Converts to a patchouli documentation page
     */
    public JsonElement serialize() {
        JsonObject jsonobject = new JsonObject();

        jsonobject.addProperty("name", this.getName());
        jsonobject.addProperty("icon", EmortisConstants.MOD_ID + ":" + getItemID());
        jsonobject.addProperty("category", "spells_"+(getTier().ordinal() + 1));
        jsonobject.addProperty("sortnum", this instanceof AbstractCastMethod ? 1 : this instanceof AbstractEffect ? 2 : 3);
        JsonArray jsonArray = new JsonArray();
        JsonObject descPage = new JsonObject();
        descPage.addProperty("type", "text");
        descPage.addProperty("text","rigoranthusemortisreborn.glyph_desc." + tag);

        JsonObject infoPage = new JsonObject();
        infoPage.addProperty("type", "glyph_recipe");
        infoPage.addProperty("recipe", EmortisConstants.MOD_ID + ":" + "glyph_" + this.tag);
        infoPage.addProperty("tier",this.getTier().name());

        String dominionCost = this.getDominionCost() < 20 ? "Low" : "Medium";
        dominionCost = this.getDominionCost() > 50 ? "High" : dominionCost;
        infoPage.addProperty("dominion_cost", dominionCost);
        if(this.getCraftingReagent() != null){
            String ingredient;
            if(this.getTier() == Tier.ONE){
                ingredient = ItemInit.GHAST_IRON_INGOT.get().getRegistryName().toString();
            }else if(this.getTier() == Tier.TWO){
                ingredient = ItemInit.BLIGHT_ICHOR.get().getRegistryName().toString();
            }else{
                ingredient = MagicItemsRegistry.DWELLER_FLESH.getRegistryName().toString();
            }
            infoPage.addProperty("ingredient", ingredient);
            infoPage.addProperty("reagent", this.getCraftingReagent().getRegistryName().toString());
        }
        jsonArray.add(descPage);
        jsonArray.add(infoPage);
        jsonobject.add("pages", jsonArray);
        return jsonobject;
    }
/**     Can be null if addons do not create a config. PLEASE REGISTER THESE IN A CONFIG. See RegistryHelper*/
    public @Nullable ForgeConfigSpec CONFIG;
    public @Nullable ForgeConfigSpec.IntValue COST;
    public @Nullable ForgeConfigSpec.BooleanValue ENABLED;
    public @Nullable ForgeConfigSpec.BooleanValue STARTER_SPELL;
    public @Nullable ForgeConfigSpec.IntValue PER_SPELL_LIMIT;

    public void buildConfig(ForgeConfigSpec.Builder builder){
        builder.comment("General settings").push("general");
        ENABLED = builder.comment("Is Enabled?").define("enabled", true);
        COST = builder.comment("Cost").defineInRange("cost", getDominionCost(), Integer.MIN_VALUE, Integer.MAX_VALUE);
        STARTER_SPELL = builder.comment("Is Starter Glyph?").define("starter", defaultedStarterGlyph());
        PER_SPELL_LIMIT = builder.comment("The maximum number of times this glyph may appear in a single spell").defineInRange("per_spell_limit", Integer.MAX_VALUE, 1, Integer.MAX_VALUE);
    }

    /** Returns the number of times that this glyph may be modified by the given augment. */
    public int getAugmentLimit(String augmentTag) {
        if (augmentLimits == null) {
            return Integer.MAX_VALUE;
        } else {
            return augmentLimits.getAugmentLimit(augmentTag);
        }
    }

    // Augment limits only apply to cast forms and effects, but not augments.
    private SpellPartConfigUtil.AugmentLimits augmentLimits;

    /** Registers the glyph_limits configuration entry for augmentation limits. */
    protected void buildAugmentLimitsConfig(ForgeConfigSpec.Builder builder, Map<String, Integer> defaults) {
        this.augmentLimits = SpellPartConfigUtil.buildAugmentLimitsConfig(builder, defaults);
    }

    /** Override this method to provide defaults for the augmentation limits configuration. */
    protected Map<String, Integer> getDefaultAugmentLimits() {
        return new HashMap<>();
    }

    // Default value for the starter spell config
    public boolean defaultedStarterGlyph(){
        return false;
    }

    public String getItemID(){
        return "glyph_" + this.getTag();
    }

    public String getBookDescription(){
        return "";
    }

    public String getLocalizationKey() {
        return "rigoranthusemortisreborn.glyph_name." + tag;
    }

    public String getLocaleName(){
        return new TranslationTextComponent(getLocalizationKey()).getString();
    }
}