package com.platinumg17.rigoranthusemortisreborn.magica.common.entity;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.entity.familiar.AbstractFamiliarHolder;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.entity.familiar.IFamiliar;
import com.platinumg17.rigoranthusemortisreborn.entity.mobs.SunderedCadaverEntity;
import com.platinumg17.rigoranthusemortisreborn.magica.common.entity.familiar.FamiliarCadaver;
import net.minecraft.world.World;

public class CadaverFamiliar extends AbstractFamiliarHolder {

    public CadaverFamiliar(){
        super("cadaver", (e) -> e instanceof SunderedCadaverEntity);
    }

    @Override
    public IFamiliar getSummonEntity(World world) {
        return new FamiliarCadaver(ModEntities.FAMILIAR_CADAVER, world);
    }

    @Override
    public String getBookName() {
        return "cadaver";
    }

    @Override
    public String getEntityKey() {
        return "cadaver";
    }
    @Override
    public String getBookDescription() {
        return "";
    }
}