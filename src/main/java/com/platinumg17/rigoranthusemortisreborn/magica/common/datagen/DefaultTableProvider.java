package com.platinumg17.rigoranthusemortisreborn.magica.common.datagen;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.platinumg17.rigoranthusemortisreborn.core.init.Registration;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.ScribesBlock;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.BlockRegistry;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.MagicItemsRegistry;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.item.Items;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.BlockStateProperty;
import net.minecraft.state.Property;
import net.minecraft.state.properties.BedPart;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class DefaultTableProvider extends LootTableProvider {
    public DefaultTableProvider(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }
    private static final float[] DEFAULT_SAPLING_DROP_RATES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};
    public static class BlockLootTable extends BlockLootTables {
        public List<Block> list = new ArrayList<>();
        @Override
        protected void addTables() {
            registerDropSelf(BlockRegistry.DOMINION_GEM_BLOCK);
//            registerDrop(BlockRegistry.RECONDITE_ORE, Registration.POWDERED_ESOTERICUM.get());
            registerDropSelf(BlockRegistry.SCONCE_BLOCK);
            registerBedCondition(BlockRegistry.SCRIBES_BLOCK, ScribesBlock.PART, BedPart.HEAD);
        }
        protected <T extends Comparable<T> & IStringSerializable> void registerBedCondition(Block block, Property<T> prop, T isValue) {
            list.add(block);
            this.add(block, LootTable.lootTable().withPool(applyExplosionCondition(block, LootPool.lootPool().setRolls(ConstantRange.exactly(1))
                    .add(ItemLootEntry.lootTableItem(block).when(BlockStateProperty.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(prop, isValue)))))));
        }
        public void registerLeavesAndSticks(Block leaves, Block sapling){
            list.add(leaves);
            this.add(leaves, l_state -> createLeavesDrops(l_state, sapling, DEFAULT_SAPLING_DROP_RATES));
        }
        public void registerDropDoor(Block block){
            list.add(block);
            this.add(block, BlockLootTables::createDoorTable);
        }
        public void registerDropSelf(Block block){
            list.add(block);
            dropSelf(block);
        }
        public void registerDrop(Block input, IItemProvider output){
            list.add(input);
            dropOther(input, output);
        }
        @Override
        protected Iterable<Block> getKnownBlocks()
        {
            return list;
        }
    }

    private final List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> tables = ImmutableList.of(
            Pair.of(BlockLootTable::new, LootParameterSets.BLOCK)
    );

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> getTables() {
        return tables;
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationTracker validationtracker)
    {
        map.forEach((p_218436_2_, p_218436_3_) -> {
            LootTableManager.validate(validationtracker, p_218436_2_, p_218436_3_);
        });
    }
}