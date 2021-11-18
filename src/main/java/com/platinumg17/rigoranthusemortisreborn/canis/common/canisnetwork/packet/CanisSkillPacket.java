package com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.packet;

import com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.packet.data.CanisSkillData;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.CanisEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import com.platinumg17.rigoranthusemortisreborn.api.RigoranthusEmortisRebornAPI;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.registry.Skill;

import java.util.function.Supplier;

public class CanisSkillPacket extends CanisPacket<CanisSkillData> {

    @Override
    public void encode(CanisSkillData data, PacketBuffer buf) {
        super.encode(data, buf);
        buf.writeRegistryIdUnsafe(RigoranthusEmortisRebornAPI.SKILLS, data.skill);
    }

    @Override
    public CanisSkillData decode(PacketBuffer buf) {
        int entityId = buf.readInt();
        Skill skill = buf.readRegistryIdUnsafe(RigoranthusEmortisRebornAPI.SKILLS);
        return new CanisSkillData(entityId, skill);
    }

    @Override
    public void handleCanis(CanisEntity canisIn, CanisSkillData data, Supplier<NetworkEvent.Context> ctx) {
        if (!canisIn.canInteract(ctx.get().getSender())) {
            return;
        }
//        if (!ConfigHandler.Skill.getFlag(data.skill)) {
//            RigoranthusEmortisReborn.LOGGER.info("{} tried to level a disabled skill ({})",
//                    ctx.get().getSender().getGameProfile().getName(),
//                    data.skill.getRegistryName());
//            return;
//        }
        int level = canisIn.getLevel(data.skill);

        if (level < data.skill.getMaxLevel() && canisIn.canSpendPoints(data.skill.getLevelCost(level + 1))) {
            canisIn.setSkillLevel(data.skill, level + 1);
        }
    }
}
