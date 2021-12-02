package WeirdFailedProjects.netnotwork;
//
//import WeirdFailedProjects.netnotwork.interfaces.IPlayerToClientPacket;
//import WeirdFailedProjects.netnotwork.storage.ClientPlayerData;
//import net.minecraft.network.PacketBuffer;
//import net.minecraftforge.fml.network.NetworkDirection;
//import net.minecraftforge.fml.network.NetworkEvent;
//
//import java.util.function.Supplier;
//
//public class DataCheckPermissionPacket implements IPlayerToClientPacket {
//    private final boolean available;
//
//    /**
//     * @param available if the player has access to and thus should see the data checker
//     */
//    public DataCheckPermissionPacket(boolean available) {this.available = available;
//    }
//    @Override public void encode(PacketBuffer buffer) {buffer.writeBoolean(available);}
//
//    public static DataCheckPermissionPacket decode(PacketBuffer buffer) {
//        boolean dataChecker = buffer.readBoolean();
//        return new DataCheckPermissionPacket(dataChecker);
//    }
//
//    public void consume(Supplier<NetworkEvent.Context> ctx) {
//        if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
//            ctx.get().enqueueWork(this::execute);
//        ctx.get().setPacketHandled(true);
//    }
//    @Override public void execute() {
//        ClientPlayerData.handleDataPacket(this);}
//    public boolean isDataCheckerAvailable() {return available;}
//}
