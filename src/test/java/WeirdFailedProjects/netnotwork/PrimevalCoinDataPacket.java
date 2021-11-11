package WeirdFailedProjects.netnotwork;

import WeirdFailedProjects.netnotwork.interfaces.IPlayerToClientPacket;
import WeirdFailedProjects.netnotwork.storage.ClientPlayerData;
import net.minecraft.network.PacketBuffer;

public class PrimevalCoinDataPacket implements IPlayerToClientPacket {
    private final long count;

    private PrimevalCoinDataPacket(long count) {this.count = count;}

    public static PrimevalCoinDataPacket create(long count) {return new PrimevalCoinDataPacket(count);}

    @Override public void encode(PacketBuffer buffer) {buffer.writeLong(count);}

    public static PrimevalCoinDataPacket decode(PacketBuffer buffer) {
        long count = buffer.readLong();
        return create(count);
    }
    @Override public void execute() {
        ClientPlayerData.handleDataPacket(this);}
    public long getPrimevalCoins() {return count;}
}