package WeirdFailedProjects.netnotwork;

//import com.platinumg17.rigoranthusemortisreborn.core.init.aspects.Title;
//import WeirdFailedProjects.netnotwork.interfaces.IPlayerToClientPacket;
//import WeirdFailedProjects.netnotwork.storage.ClientPlayerData;
//import net.minecraft.network.PacketBuffer;
//
//import java.util.Objects;
//
//public class TitleDataPacket implements IPlayerToClientPacket {
//    private final Title title;
//
//    private TitleDataPacket(Title title) {this.title = Objects.requireNonNull(title);}
//
//    public static TitleDataPacket create(Title title) {return new TitleDataPacket(title);}
//
//    @Override public void encode(PacketBuffer buffer) {title.write(buffer);}
//
//    public static TitleDataPacket decode(PacketBuffer buffer) {
//        Title title = Title.read(buffer);
//        return new TitleDataPacket(title);
//    }
//
//    @Override
//    public void execute() {
//        ClientPlayerData.handleDataPacket(this);}
//
//    public Title getTitle() {return title;}
//}