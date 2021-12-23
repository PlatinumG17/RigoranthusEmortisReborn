package com.platinumg17.rigoranthusemortisreborn.magica.common.datagen;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.BlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.entity.EntityType;
import net.minecraft.loot.LootTable;

public class LootTables extends BaseLootTableProvider {
    public LootTables(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }

    @Override
    protected void addTables() {
        System.out.println(BlockRegistry.DOMINION_JAR);
        blockTables.put(BlockRegistry.DOMINION_JAR, createDominionManchineTable("dominion_jar", BlockRegistry.DOMINION_JAR));
        System.out.println(BlockRegistry.ICHOR_JAR);
        blockTables.put(BlockRegistry.ICHOR_JAR, createIchorJarTable("ichor_jar", BlockRegistry.ICHOR_JAR));
        putStandardLoot(BlockRegistry.TABLE_BLOCK);
//        putStandardLoot(BlockRegistry.RE_LILLY_PAD);
    }

    public void putStandardLoot(Block block){
        blockTables.put(block, createStandardTable(block.getRegistryName().toString().replace(EmortisConstants.MOD_ID + ":", "") , block));
    }
    public void putEntityTable(EntityType e, LootTable.Builder table){
        entityTables.put(e.getDefaultLootTable(), table);
    }
}