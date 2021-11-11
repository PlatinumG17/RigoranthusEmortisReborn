package WeirdFailedProjects.netnotwork.storage;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.core.init.aspects.AspectProgressBar;
import com.platinumg17.rigoranthusemortisreborn.core.init.aspects.IdentifierHandler;
import com.platinumg17.rigoranthusemortisreborn.core.init.aspects.Title;
import WeirdFailedProjects.netnotwork.PrimevalCoinDataPacket;
import WeirdFailedProjects.netnotwork.TitleDataPacket;
import com.platinumg17.rigoranthusemortisreborn.util.ColorHandler;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author kirderf1
 */
@Mod.EventBusSubscriber(modid = EmortisConstants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class PlayerData {
    private static final Logger LOGGER = LogManager.getLogger();

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event)
    {
        ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
        PlayerSavedData.getData(player).onPlayerLoggedIn(player);
//        REDimensions.sendDimensionData(player);
    }

    @SubscribeEvent
    public static void onPlayerChangeDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
        ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
        PlayerSavedData.getData(player);//.sendConsortReputation(player);
    }

    final PlayerIdentifier identifier;

    private final PlayerSavedData savedData;
    private final AspectProgressBar aspectProgressBar;
    private int color = ColorHandler.DEFAULT_COLOR;

//    private boolean givenModus;
//    private Modus modus;
    private long primevalcoin;
//    private ImmutableGristSet gristCache;	//This is immutable in order to control where it can be changed

    private final Map<ResourceLocation, Integer> consortReputation = new HashMap<>();

    private Title title;
    private boolean effectToggle;
    private boolean hasLoggedIn;

    PlayerData(PlayerSavedData savedData, PlayerIdentifier player) {
        this.savedData = savedData;
        this.identifier = player;
        aspectProgressBar = new AspectProgressBar(savedData, player);
//        gristCache = new ImmutableGristSet(GristTypes.BUILD, 20);
        hasLoggedIn = false;
    }

    PlayerData(PlayerSavedData savedData, CompoundNBT nbt) {
        this.savedData = savedData;
        this.identifier = IdentifierHandler.load(nbt, "player");

        aspectProgressBar = new AspectProgressBar(savedData, identifier);
        aspectProgressBar.loadAspectProgressBar(nbt);
        if (nbt.contains("color"))
            this.color = nbt.getInt("color");

//        if (nbt.contains("modus")) {
//            this.modus = CaptchaDeckHandler.readFromNBT(nbt.getCompound("modus"), savedData);
//            givenModus = true;
//        }
//        else givenModus = nbt.getBoolean("given_modus");
        primevalcoin = nbt.getLong("primevalcoin");
//        gristCache = NonNegativeGristSet.read(nbt.getList("grist_cache", Constants.NBT.TAG_COMPOUND)).asImmutable();

//        ListNBT list = nbt.getList("consort_reputation", Constants.NBT.TAG_COMPOUND);
//        for(int i = 0; i < list.size(); i++) {
//            CompoundNBT dimensionRep = list.getCompound(i);
//            ResourceLocation dimension = ResourceLocation.tryParse(dimensionRep.getString("dim"));
//            if(dimension != null)
//                consortReputation.put(dimension, dimensionRep.getInt("rep"));
//        }

        title = Title.tryRead(nbt, "title");
        effectToggle = nbt.getBoolean("effect_toggle");

        hasLoggedIn = true;
    }

    CompoundNBT writeToNBT() {
        CompoundNBT nbt = new CompoundNBT();
        identifier.saveToNBT(nbt, "player");
        aspectProgressBar.saveAspectProgressBar(nbt);
        nbt.putInt("color", color);

//        if (this.modus != null)
//            nbt.put("modus", CaptchaDeckHandler.writeToNBT(modus));
//        else nbt.putBoolean("given_modus", givenModus);
        nbt.putLong("primevalcoin", primevalcoin);
//        nbt.put("grist_cache", gristCache.write(new ListNBT()));

//        ListNBT list = new ListNBT();
//        for(Map.Entry<ResourceLocation, Integer> entry : consortReputation.entrySet()) {
//            CompoundNBT dimensionRep = new CompoundNBT();
//            dimensionRep.putString("dim", entry.getKey().toString());
//            dimensionRep.putInt("rep", entry.getValue());
//            list.add(dimensionRep);
//        }
//        nbt.put("consort_reputation", list);

        if(title != null)
            title.write(nbt, "title");
        nbt.putBoolean("effect_toggle", effectToggle);

        return nbt;
    }

    private void markDirty() {savedData.setDirty();}
    public AspectProgressBar getAspectProgressBar() {return aspectProgressBar;}
    public int getColor() {return color;}

//    public void trySetColor(int color)
//    {
//        if(SburbHandler.canSelectColor(identifier, savedData.mcServer) && this.color != color)
//        {
//            this.color = color;
//            markDirty();
//
//            sendColor(getPlayer(), false);
//        }
//    }
//
//    public Modus getModus(){return modus;}
//
//    public void setModus(Modus modus){
//        if(this.modus != modus){
//            this.modus = modus;
//            if(modus != null)
//                setGivenModus();
//            markDirty();
//        }
//    }

//    public boolean hasGivenModus() {return givenModus;}
//    private void setGivenModus() {
//        if(!givenModus) {
//            givenModus = true;
//            markDirty();
//        }
//    }

    public long getPrimevalCoins() {return primevalcoin;}

    public void addPrimevalCoins(long amount) {
        if(amount < 0)
            throw new IllegalArgumentException("PrimevalCoin amount may not be negative.");
        else if(amount > 0) {
            primevalcoin += amount;
            markDirty();
            sendPrimevalCoins(getPlayer());
        }
    }

    public void takePrimevalCoins(long amount) {
        if(amount < 0)
            throw new IllegalArgumentException("PrimevalCoin amount may not be negative.");
        else if(amount > 0) {
            if(primevalcoin - amount < 0)
                throw new IllegalStateException("Can't go to negative primevalcoin");

            primevalcoin -= amount;
            markDirty();
            sendPrimevalCoins(getPlayer());
        }
    }

    public boolean tryTakePrimevalCoins(long amount) {
        if(getPrimevalCoins() - amount < 0)
            return false;
        else {
            takePrimevalCoins(amount);
            return true;
        }
    }

    public void setPrimevalCoins(long amount) {
        if(amount < 0)
            throw new IllegalArgumentException("PrimevalCoin amount may not be negative.");
        else if(amount != primevalcoin) {
            primevalcoin = amount;
            markDirty();
            sendPrimevalCoins(getPlayer());
        }
    }

//    public int getConsortReputation(RegistryKey<World> dim)
//    {
//        return consortReputation.getOrDefault(dim.location(), 0);
//    }
//
//    public void addConsortReputation(int amount, RegistryKey<World> dim)
//    {
//        int oldRep = getConsortReputation(dim);
//        int newRep = MathHelper.clamp(oldRep + amount, -10000, 10000);
//
//        if(newRep != oldRep)
//        {
//            consortReputation.put(dim.location(), newRep);
//            markDirty();
//            sendConsortReputation(getPlayer());
//        }
//    }

//    public ImmutableGristSet getGristCache() {return gristCache;}
//
//    public void setGristCache(NonNegativeGristSet cache) {
//        gristCache = cache.asImmutable();
//        markDirty();
//        updateGristCache(getPlayer());
//    }

    public Title getTitle() {return title;}

    public void setTitle(Title newTitle) {
        if(title == null) {
            title = Objects.requireNonNull(newTitle);
            markDirty();
            sendTitle(getPlayer());
        } else throw new IllegalStateException("Can't set title for player "+ identifier.getUsername()+" because they already have one");
    }

    public boolean effectToggle() {return effectToggle;}

    public void effectToggle(boolean toggle) {
        if(effectToggle != toggle) {
            effectToggle = toggle;
            markDirty();
        }
    }

//    private void tryGiveStartingModus(ServerPlayerEntity player)
//    {
//        List<String> startingTypes = MinestuckConfig.SERVER.startingModusTypes.get();
//        if(!startingTypes.isEmpty())
//        {
//            String type = startingTypes.get(player.level.random.nextInt(startingTypes.size()));
//            if(type.isEmpty())
//            {
//                setGivenModus();
//                return;
//            }
//            ResourceLocation name = ResourceLocation.tryParse(type);
//            if(name == null)
//                LOGGER.error("Unable to parse starting modus type {} as a resource location!", type);
//            else
//            {
//                Modus modus = CaptchaDeckHandler.createServerModus(name, savedData);
//                if(modus != null)
//                {
//                    modus.initModus(null, player, null, MinestuckConfig.SERVER.initialModusSize.get());
//                    setModus(modus);
//                } else LOGGER.warn("Couldn't create a starting modus type by name {}.", type);
//            }
//        }
//    }

    public void onPlayerLoggedIn(ServerPlayerEntity player) {
        getAspectProgressBar().updateAspectProgressBarBonuses(player);

//        if(getModus() == null && !hasGivenModus())
//            tryGiveStartingModus(player);
//
//        if(getModus() != null) {
//            Modus modus = getModus();
//            REPacketHandler.sendToPlayer(ModusDataPacket.create(CaptchaDeckHandler.writeToNBT(modus)), player);
//        }

        aspectProgressBar.sendInitialPacket(player);
//        sendColor(player, !hasLoggedIn);
        sendPrimevalCoins(player);
//        updateGristCache(player);
        sendTitle(player);

        hasLoggedIn = true;
    }

//    private void sendColor(ServerPlayerEntity player, boolean firstTime)
//    {
//        if(player == null)
//            return;
//        if(firstTime && !player.isSpectator())
//            REPacketHandler.sendToPlayer(ColorDataPacket.selector(), player);
//        else
//        {
//            ColorDataPacket packet = ColorDataPacket.data(getColor());
//            REPacketHandler.sendToPlayer(packet, player);
//        }
//    }

    private void sendPrimevalCoins(ServerPlayerEntity player) {
        if(player == null)
            return;
        PrimevalCoinDataPacket packet = PrimevalCoinDataPacket.create(getPrimevalCoins());
        REPacketHandler.sendToPlayer(packet, player);
    }

//    private void sendConsortReputation(ServerPlayerEntity player)
//    {
//        if(player == null)
//            return;
//        ConsortReputationDataPacket packet = ConsortReputationDataPacket.create(getConsortReputation(player.level.dimension()));
//        //REPacketHandler.sendToPlayer(packet, player);
//    }

//    private void updateGristCache(ServerPlayerEntity player) {
//        GristSet gristSet = getGristCache();
//
//        //Send to the player
//        if(player != null) {
//            GristCachePacket packet = new GristCachePacket(gristSet, false);
//            REPacketHandler.sendToPlayer(packet, player);
//        }
//
//        //Also send to the editing player, if there is any
//        SburbConnection c = SkaianetHandler.get(savedData.mcServer).getActiveConnection(identifier);
//        if(c != null) {
//            EditData data = ServerEditHandler.getData(savedData.mcServer, c);
//            if(data != null) {
//                data.sendGristCacheToEditor();
//            }
//        }
//    }

    private void sendTitle(ServerPlayerEntity player) {
        Title newTitle = getTitle();
        if(newTitle == null || player == null)
            return;
        TitleDataPacket packet = TitleDataPacket.create(newTitle);
        REPacketHandler.sendToPlayer(packet, player);
    }
    private ServerPlayerEntity getPlayer() {return identifier.getPlayer(savedData.mcServer);}
}