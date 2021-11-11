package WeirdFailedProjects.netnotwork.storage;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.core.init.aspects.Title;
import WeirdFailedProjects.netnotwork.AspectProgressBarDataPacket;
import WeirdFailedProjects.netnotwork.DataCheckPermissionPacket;
import WeirdFailedProjects.netnotwork.PrimevalCoinDataPacket;
import WeirdFailedProjects.netnotwork.TitleDataPacket;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod.EventBusSubscriber(modid = EmortisConstants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientPlayerData {
    private static final Logger LOGGER = LogManager.getLogger();

//    private static Modus modus;
    private static Title title;
    private static int level;
    private static float levelProgress;
    private static long primevalCoins;
//    private static int consortReputation;
//    private static GristSet playerGrist;
//    private static GristSet targetGrist;
    private static int playerColor;
    private static boolean displaySelectionGui;
    private static boolean dataCheckerAccess;

    @SubscribeEvent
    public static void onLoggedIn(ClientPlayerNetworkEvent.LoggedInEvent event) {
//        modus = null;
        title = null;
        level = -1;
        playerColor = -1;
        displaySelectionGui = false;
    }
//    public static Modus getModus() {return modus;}

    public static Title getTitle() {return title;}

    public static int getProgress() {return level;}

    /**
     * Note: Unlike the value used on the logical server side, this vale is a fraction going from 0 to 1
     */
    public static float getLevelProgress() {return levelProgress;}
    public static long getPrimevalCoins() {return primevalCoins;}

//    public static int getConsortReputation() {return consortReputation;}
//    public static GristSet getClientGrist() {return ClientEditHandler.isActive() ? targetGrist : playerGrist;}

    public static int getPlayerColor() {return playerColor;}

//    public static void selectColor(int colorIndex) {
//        REPacketHandler.sendToServer(new ColorSelectPacket(colorIndex));
//        playerColor = ColorHandler.getColor(colorIndex);
//    }

    public static boolean shouDisplayColorSelection() {return displaySelectionGui;}

    public static void clearDisplayColorSelection() {displaySelectionGui = false;}

    public static boolean hasDataCheckerAccess() {return dataCheckerAccess;}

//    public static void handleDataPacket(ModusDataPacket packet) {
//        modus = CaptchaDeckHandler.readFromNBT(packet.getNBT(), null);
//        if(modus != null)
//            REScreenFactories.updateSylladexScreen();
//        else LOGGER.debug("Player lost their modus after update packet");
//    }

    public static void handleDataPacket(TitleDataPacket packet) {title = packet.getTitle();}

    public static void handleDataPacket(AspectProgressBarDataPacket packet) {
        level = packet.getLevel();
        levelProgress = packet.getProgress();
    }

    public static void handleDataPacket(PrimevalCoinDataPacket packet) {
        primevalCoins = packet.getPrimevalCoins();
    }

//    public static void handleDataPacket(ConsortReputationDataPacket packet) {consortReputation = packet.getCount();}
//
//    public static void handleDataPacket(GristCachePacket packet) {
//        if(packet.isEditmode)
//            targetGrist = packet.gristCache;
//        else playerGrist = packet.gristCache;
//    }

//    public static void handleDataPacket(ColorDataPacket packet)
//    {
//        if(packet.hasNoColor())
//        {
//            ClientPlayerData.playerColor = ColorHandler.DEFAULT_COLOR;
//            ClientPlayerData.displaySelectionGui = true;
//        } else ClientPlayerData.playerColor = packet.getColor();
//    }
    public static void handleDataPacket(DataCheckPermissionPacket packet) {dataCheckerAccess = packet.isDataCheckerAvailable();}
}