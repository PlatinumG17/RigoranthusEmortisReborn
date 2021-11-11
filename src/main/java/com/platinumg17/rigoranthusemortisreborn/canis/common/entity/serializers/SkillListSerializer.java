package com.platinumg17.rigoranthusemortisreborn.canis.common.entity.serializers;

import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.IDataSerializer;
import com.platinumg17.rigoranthusemortisreborn.api.RigoranthusEmortisRebornAPI;
import com.platinumg17.rigoranthusemortisreborn.api.registry.SkillInstance;

import java.util.ArrayList;
import java.util.List;

public class SkillListSerializer implements IDataSerializer<List<SkillInstance>> {

    @Override
    public void write(PacketBuffer buf, List<SkillInstance> value) {
        buf.writeInt(value.size());
        for (SkillInstance inst : value) {
            buf.writeRegistryIdUnsafe(RigoranthusEmortisRebornAPI.SKILLS, inst.getSkill());
            inst.writeToBuf(buf);
        }
    }

    @Override
    public List<SkillInstance> read(PacketBuffer buf) {
        int size = buf.readInt();
        List<SkillInstance> newInst = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            SkillInstance inst = buf.readRegistryIdUnsafe(RigoranthusEmortisRebornAPI.SKILLS).getDefault();
            inst.readFromBuf(buf);
            newInst.add(inst);
        }
        return newInst;
    }

    @Override
    public DataParameter<List<SkillInstance>> createAccessor(int id) {
        return new DataParameter<>(id, this);
    }

    @Override
    public List<SkillInstance> copy(List<SkillInstance> value) {
        List<SkillInstance> newInst = new ArrayList<>(value.size());

        for (SkillInstance inst : value) {
        newInst.add(inst.copy());
        }
        return newInst;
    }
}
