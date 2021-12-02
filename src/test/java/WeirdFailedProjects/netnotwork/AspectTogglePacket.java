package WeirdFailedProjects.netnotwork;
//
//import WeirdFailedProjects.netnotwork.interfaces.IPlayerToServerPacket;
//import WeirdFailedProjects.netnotwork.storage.PlayerData;
//import WeirdFailedProjects.netnotwork.storage.PlayerSavedData;
//import net.minecraft.entity.player.ServerPlayerEntity;
//import net.minecraft.network.PacketBuffer;
//import net.minecraft.util.text.TranslationTextComponent;
//
//public class AspectTogglePacket implements IPlayerToServerPacket {
//
//    public static final String ON = "rigoranthusemortisreborn.emortic_aspect_effects.on";
//    public static final String OFF = "rigoranthusemortisreborn.emortic_aspect_effects.off";
//
//    @Override
//    public void encode(PacketBuffer buffer) {}
//
//    public static AspectTogglePacket decode(PacketBuffer buffer) {return new AspectTogglePacket();}
//
//    @Override
//    public void execute(ServerPlayerEntity player) {
//        PlayerData data = PlayerSavedData.getData(player);
//        data.aspectToggle(!data.aspectToggle());
//        if(data.aspectToggle()) {
//            player.displayClientMessage(new TranslationTextComponent(ON), true);
//        } else {
//            player.displayClientMessage(new TranslationTextComponent(OFF), true);
//        }
//    }
//}
