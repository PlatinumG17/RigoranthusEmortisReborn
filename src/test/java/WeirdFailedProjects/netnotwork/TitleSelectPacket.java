package WeirdFailedProjects.netnotwork;

//import com.platinumg17.rigoranthusemortisreborn.core.init.aspects.Title;
//import WeirdFailedProjects.netnotwork.interfaces.IPlayerToBothPacket;
//import net.minecraft.entity.player.ServerPlayerEntity;
//import net.minecraft.network.PacketBuffer;
//
//public class TitleSelectPacket implements IPlayerToBothPacket {
//    private final Title title;
//
//    public TitleSelectPacket() {title = null;}
//
//    public TitleSelectPacket(Title title) {this.title = title;}
//
//    @Override
//    public void encode(PacketBuffer buffer) {
//        if(title != null) {title.write(buffer);}
//    }
//
//    public static TitleSelectPacket decode(PacketBuffer buffer) {
//        if(buffer.readableBytes() > 0) {
//            Title title = Title.read(buffer);
//            return new TitleSelectPacket(title);
//        } else return new TitleSelectPacket();
//    }
//    @Override public void execute() {REScreenFactories.displayTitleSelectScreen(title);}
//    @Override public void execute(ServerPlayerEntity player) {TitleSelectionHook.handleTitleSelection(player, title);}
//}
