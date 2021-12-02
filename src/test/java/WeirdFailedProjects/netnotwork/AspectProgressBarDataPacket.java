package WeirdFailedProjects.netnotwork;
//
//import com.platinumg17.rigoranthusemortisreborn.core.init.aspects.AspectProgressBar;
//import WeirdFailedProjects.netnotwork.interfaces.IPlayerToClientPacket;
//import WeirdFailedProjects.netnotwork.storage.ClientPlayerData;
//import net.minecraft.client.Minecraft;
//import net.minecraft.network.PacketBuffer;
//import net.minecraft.util.Util;
//import net.minecraft.util.text.TranslationTextComponent;
//
//public class AspectProgressBarDataPacket implements IPlayerToClientPacket {
//    private final int level;
//    private final float progress;
//    private final boolean sendMessage;
//
//    private AspectProgressBarDataPacket(int level, float progress, boolean sendMessage) {
//        this.level = level;
//        this.progress = progress;
//        this.sendMessage = sendMessage;
//    }
//
//    public static AspectProgressBarDataPacket create(int level, float progress, boolean sendMessage) {return new AspectProgressBarDataPacket(level, progress, sendMessage);}
//
//    public static AspectProgressBarDataPacket init(int level, float progress) {return new AspectProgressBarDataPacket(level, progress, false);}
//
//    @Override
//    public void encode(PacketBuffer buffer) {
//        buffer.writeInt(level);
//        buffer.writeFloat(progress);
//        buffer.writeBoolean(sendMessage);
//    }
//
//    public static AspectProgressBarDataPacket decode(PacketBuffer buffer) {
//        int level = buffer.readInt();
//        float progress = buffer.readFloat();
//        boolean sendMessage = buffer.readBoolean();
//
//        return create(level, progress, sendMessage);
//    }
//
//    @Override
//    public void execute() {
//        int prev = ClientPlayerData.getProgress();
//        ClientPlayerData.handleDataPacket(this);
//        if(sendMessage)
//            for(prev++; prev <= level; prev++) {
//                TranslationTextComponent level = new TranslationTextComponent(AspectProgressBar.translationKey(prev));
//                Minecraft.getInstance().player.sendMessage(new TranslationTextComponent(AspectProgressBar.NEW_LEVEL, level), Util.NIL_UUID);
//            }
//        else AspectProgressScreen.animatedAspectLevel = AspectProgressScreen.lastAspectLevel = level;
//    }
//    public int getLevel() {return level;}
//    public float getProgress() {return progress;}
//}