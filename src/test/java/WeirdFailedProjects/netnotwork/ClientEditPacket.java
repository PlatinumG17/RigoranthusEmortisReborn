package WeirdFailedProjects.netnotwork;

import WeirdFailedProjects.netnotwork.interfaces.IPlayerToServerPacket;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.server.management.OpEntry;

public class ClientEditPacket implements IPlayerToServerPacket {
    private final int user;
    private final int target;

    private ClientEditPacket(int user, int target) {
        this.user = user;
        this.target = target;
    }
    public static ClientEditPacket exit() {return new ClientEditPacket(-1, 0);}
    public static ClientEditPacket activate(int user, int target) {return new ClientEditPacket(user, target);}

    @Override
    public void encode(PacketBuffer buffer) {
        if(user != -1) {
            buffer.writeInt(user);
            buffer.writeInt(target);
        }
    }

    public static ClientEditPacket decode(PacketBuffer buffer) {
        if(buffer.readableBytes() > 0) {
            int user = buffer.readInt();
            int target = buffer.readInt();
            return activate(user, target);
        }
        return exit();
    }

    @Override
    public void execute(ServerPlayerEntity player) {
        if(player == null || player.getServer() == null)
            return;
        OpEntry opsEntry = player.getServer().getPlayerList().getOps().get(player.getGameProfile());
    }
}
