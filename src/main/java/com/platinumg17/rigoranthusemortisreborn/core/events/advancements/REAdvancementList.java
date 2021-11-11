package com.platinumg17.rigoranthusemortisreborn.core.events.advancements;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import javax.annotation.Nullable;
import com.google.common.collect.Maps;
import com.platinumg17.rigoranthusemortisreborn.canis.CanisItems;
import com.platinumg17.rigoranthusemortisreborn.canis.common.util.REUtil;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.advancements.IRequirementsStrategy;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class REAdvancementList implements Consumer<Consumer<Advancement>> {
    @Override
    public void accept(Consumer<Advancement> register) {
        Advancement advancement = Advancement.Builder.advancement().display(CanisItems.TRAINING_TREAT.get(), new TranslationTextComponent("advancements.canis.root.title"), new
                TranslationTextComponent("advancements.canis.root.description"), new ResourceLocation("textures/gui/advancements/backgrounds/stone.png"), FrameType.TASK, false, false, false).
                addCriterion("crafting_table", InventoryChangeTrigger.Instance.hasItems(Blocks.CRAFTING_TABLE)).save(register, REUtil.getResourcePath("canis/find_canis"));


        Advancement advancement1 = Advancement.Builder.advancement().parent(advancement).display(Items.WOODEN_PICKAXE, new TranslationTextComponent("advancements.canis.mine_stone.title"), new
                TranslationTextComponent("advancements.canis.mine_stone.description"), null, FrameType.TASK, true, true, false).
                addCriterion("get_stone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE)).save(register, REUtil.getResourcePath("canis/level_skill"));


//        Advancement advancement2 = Advancement.Builder.advancement().parent(advancement1).display(CanisItems.CAPE.get(), new TranslationTextComponent("advancements.canis.upgrade_tools.title"), new
//                TranslationTextComponent("advancements.canis.upgrade_tools.description"), null, FrameType.TASK, true, true, false).
//                addCriterion("stone_pickaxe", InventoryChangeTrigger.Instance.hasItems(Items.STONE_PICKAXE)).save(register, REUtil.getResourcePath("canis/accumulating_accoutrements"));
//
//
//        Advancement advancement3 = Advancement.Builder.advancement().parent(advancement2).display(CanisItems.RADIO_COLLAR.get(), new TranslationTextComponent("advancements.canis.smelt_iron.title"), new
//                TranslationTextComponent("advancements.canis.smelt_iron.description"), null, FrameType.TASK, true, true, false).
//                addCriterion("iron", InventoryChangeTrigger.Instance.hasItems(Items.IRON_INGOT)).save(register, REUtil.getResourcePath("canis/radio_collar"));
    }

    public static class Builder {
        private ResourceLocation parentId;
        private Advancement parent;
        private DisplayInfo display;
        private AdvancementRewards rewards = AdvancementRewards.EMPTY;
        private Map<String, Criterion> criteria = Maps.newLinkedHashMap();
        private String[][] requirements;
        private IRequirementsStrategy requirementsStrategy = IRequirementsStrategy.AND;

        private Builder(@Nullable ResourceLocation parentIdIn, @Nullable DisplayInfo displayIn, AdvancementRewards rewardsIn, Map<String, Criterion> criteriaIn, String[][] requirementsIn) {
            this.parentId = parentIdIn;
            this.display = displayIn;
            this.rewards = rewardsIn;
            this.criteria = criteriaIn;
            this.requirements = requirementsIn;
        }

        private Builder() {}

        public static REAdvancementList.Builder builder() {return new REAdvancementList.Builder();}

        public REAdvancementList.Builder withParent(Advancement parentIn) {
            this.parent = parentIn;
            return this;
        }

        public REAdvancementList.Builder withParentId(ResourceLocation parentIdIn) {
            this.parentId = parentIdIn;
            return this;
        }

        public REAdvancementList.Builder withDisplay(ItemStack stack, ITextComponent title, ITextComponent description, @Nullable ResourceLocation background, FrameType frame, boolean showToast, boolean announceToChat, boolean hidden) {
            return this.withDisplay(new DisplayInfo(stack, title, description, background, frame, showToast, announceToChat, hidden));
        }

        public REAdvancementList.Builder withDisplay(IItemProvider itemIn, ITextComponent title, ITextComponent description, @Nullable ResourceLocation background, FrameType frame, boolean showToast, boolean announceToChat, boolean hidden) {
            return this.withDisplay(new DisplayInfo(new ItemStack(itemIn.asItem()), title, description, background, frame, showToast, announceToChat, hidden));
        }

        public REAdvancementList.Builder withDisplay(DisplayInfo displayIn) {
            this.display = displayIn;
            return this;
        }

        public REAdvancementList.Builder withRewards(AdvancementRewards.Builder rewardsBuilder) {return this.withRewards(rewardsBuilder.build());}

        public REAdvancementList.Builder withRewards(AdvancementRewards rewards) {
            this.rewards = rewards;
            return this;
        }

        /**
         * Adds a criterion to the list of criteria
         */
        public REAdvancementList.Builder withCriterion(String key, ICriterionInstance criterionIn) {
            return this.withCriterion(key, new Criterion(criterionIn));
        }

        /**
         * Adds a criterion to the list of criteria
         */
        public REAdvancementList.Builder withCriterion(String key, Criterion criterionIn) {
            if (this.criteria.containsKey(key)) {
                throw new IllegalArgumentException("Duplicate criterion " + key);
            } else {
                this.criteria.put(key, criterionIn);
                return this;
            }
        }

        public REAdvancementList.Builder withRequirementsStrategy(IRequirementsStrategy strategy) {
            this.requirementsStrategy = strategy;
            return this;
        }

        /**
         * Tries to resolve the parent of this advancement, if possible. Returns true on success.
         */
        public boolean resolveParent(Function<ResourceLocation, Advancement> lookup) {
            if (this.parentId == null) {
                return true;
            } else {
                if (this.parent == null) {
                    this.parent = lookup.apply(this.parentId);
                }
                return this.parent != null;
            }
        }

        public Advancement build(ResourceLocation id) {
            if (!this.resolveParent((parentID) -> {return null;})) {
                throw new IllegalStateException("Tried to build incomplete advancement!");
            } else {
                if (this.requirements == null) {
                    this.requirements = this.requirementsStrategy.createRequirements(this.criteria.keySet());
                }
                return new Advancement(id, this.parent, this.display, this.rewards, this.criteria, this.requirements);
            }
        }
        public Advancement register(Consumer<Advancement> consumer, String id) {
            Advancement advancement = this.build(new ResourceLocation(id));
            consumer.accept(advancement);
            return advancement;
        }
    }
}
