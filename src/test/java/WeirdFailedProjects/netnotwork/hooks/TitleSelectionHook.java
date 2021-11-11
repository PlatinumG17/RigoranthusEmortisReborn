package WeirdFailedProjects.netnotwork.hooks;
import com.platinumg17.rigoranthusemortisreborn.core.init.aspects.IdentifierHandler;
import com.platinumg17.rigoranthusemortisreborn.core.init.aspects.Title;
import WeirdFailedProjects.netnotwork.TitleSelectPacket;
import WeirdFailedProjects.netnotwork.storage.PlayerIdentifier;
import WeirdFailedProjects.netnotwork.storage.PlayerSavedData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.vector.Vector3d;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * A class that determines when to stop entry and tell the player to pick a title,
 * and to then handle the selection once it's been sent back.
 */
public class TitleSelectionHook {
    private static final Logger LOGGER = LogManager.getLogger();

    static Map<PlayerEntity, Vector3d> playersInTitleSelection = new HashMap<>();

    /**
     * Checks if the player has the go-ahead to enter.
     * If the player should get the title selection screen, this will send that packet to the player and then return false.
     */
    public static boolean performEntryCheck(ServerPlayerEntity player) {
//        if(!Config.playerSelectedTitle.get())
//            return true;

        PlayerIdentifier identifier = IdentifierHandler.encode(player);
        Session s = SessionHandler.get(player.level).getPlayerSession(identifier);

        if(s != null && s.predefinedPlayers.containsKey(identifier) && s.predefinedPlayers.get(identifier).getTitle() != null
                || PlayerSavedData.getData(identifier, player.server).getTitle() != null)
            return true;

        playersInTitleSelection.put(player, new Vector3d(player.getX(), player.getY(), player.getZ()));
        TitleSelectPacket packet = new TitleSelectPacket();
        REPacketHandler.sendToPlayer(packet, player);
        return false;
    }

    public static void cancelSelection(ServerPlayerEntity player) {playersInTitleSelection.remove(player);}

    public static void handleTitleSelection(ServerPlayerEntity player, Title title) {
        if(Config.playerSelectedTitle.get() && playersInTitleSelection.containsKey(player))
        {
            PlayerIdentifier identifier = IdentifierHandler.encode(player);

            if(title == null)
                ModHandler.generateAndSetTitle(player.level, identifier);
            else
            {
                Session s = SessionHandler.get(player.server).getPlayerSession(identifier);
                if(s != null && s.getUsedTitles(identifier).contains(title))
                {
                    // Title is already used in session; inform the player that they can't pick this title
                    REPacketHandler.sendToPlayer(new TitleSelectPacket(title), player);
                    return;
                }

                PlayerSavedData.getData(identifier, player.server).setTitle(title);
            }

            //Once the title selection has finished successfully, restore player position and trigger entry
            Vector3d pos = playersInTitleSelection.remove(player);

            player.setPos(pos.x, pos.y, pos.z);

            EntryProcess process = new EntryProcess();
            process.onArtifactActivated(player);

        } else LOGGER.warn("{} tried to select a title without entering.", player.getName().getString());
    }
}