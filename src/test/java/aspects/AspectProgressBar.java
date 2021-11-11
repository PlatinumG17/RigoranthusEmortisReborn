package aspects;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import WeirdFailedProjects.netnotwork.AspectProgressBarDataPacket;
import WeirdFailedProjects.netnotwork.storage.PlayerIdentifier;
import WeirdFailedProjects.netnotwork.storage.PlayerSavedData;
import com.platinumg17.rigoranthusemortisreborn.core.registry.RigoranthusSoundRegistry;
import com.platinumg17.rigoranthusemortisreborn.util.REDebug;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

@Mod.EventBusSubscriber(modid = EmortisConstants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class AspectProgressBar {

    public static final String NEW_LEVEL = "progressLvl.new_level";

    public static final int LEVEL_COUNT = 50;
    public static final byte UNDERLING_BONUS_OFFSET = 0;
    public static final byte ALCHEMY_BONUS_OFFSET = 15;

    private static final UUID progressLvlHealthBoostModifierUUID = UUID.fromString("5b49a45b-ff22-4720-8f10-dfd745c3abb8");    //TODO Might be so that only one is needed, as we only add one modifier for each attribute.
    private static final UUID progressLvlDamageBoostModifierUUID = UUID.fromString("a74176fd-bf4e-4153-bb68-197dbe4109b2");
    private static final int[] UNDERLING_BONUSES = new int[]{10, 120, 450, 1200, 2500};    //Bonuses for first time killing an underling
    private static final int[] ALCHEMY_BONUSES = new int[]{30, 400, 3000};
    // 0				4						9							14							  19								24									29										34
    private static final int[] PRIMEVAL_COINS = new int[]{0, 50, 75, 105, 140, 170, 200, 250, 320, 425, 575, 790, 1140, 1630, 2230, 2980, 3850, 4800, 6000, 7500, 9500, 11900, 15200, 19300, 24400, 45000, 68000, 95500, 124000, 180000, 260000, 425000, 632000, 880000, 1000000};

    public static void increaseProgress(PlayerIdentifier player, World world, int progress) {
        PlayerSavedData.getData(player, world).getAspectProgressBar().increaseProgress(progress);
    }
    public static void increaseProgress(ServerPlayerEntity player, int progress) {
        PlayerSavedData.getData(player).getAspectProgressBar().increaseProgress(progress);
    }

    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        AspectProgressBar progressLvl = PlayerSavedData.getData((ServerPlayerEntity) event.getPlayer()).getAspectProgressBar();
        progressLvl.updateAspectProgressBarBonuses((ServerPlayerEntity) event.getPlayer());
        if(Config.levelHealthOnRespawn.get())
            event.getPlayer().heal(event.getPlayer().getMaxHealth());
    }

    private final PlayerSavedData savedData;
    private final PlayerIdentifier identifier;
    private int level;
    private int progress;

    private boolean[] underlingBonuses = new boolean[UNDERLING_BONUSES.length];
    private boolean[] alchemyBonuses = new boolean[ALCHEMY_BONUSES.length];

    public AspectProgressBar(PlayerSavedData savedData, PlayerIdentifier identifier) {
        this.savedData = savedData;
        this.identifier = identifier;
    }
    private int getAspectLevelProgressReq() {return 15 * level + 10;}

    public void increaseProgress(int exp) {
        //for each level, the experience is divided and approaches 0 (at infinity). That means there is a certain level for each experience amount where it becomes less than one and no longer capable of contributing
        exp = (int) ((exp / (level + 1) * 2) + .5D);
//        Optional<SburbConnection> c = SkaianetHandler.get(savedData.mcServer).getPrimaryConnection(identifier, true);
        int maxAspectLevel = 50; //c.map(SburbConnection::hasEntered).orElse(false) ? LEVEL_COUNT - 1 : Config.preEntryAspectLevelLimit.get();
        int expReq = getAspectLevelProgressReq();

        if(level >= maxAspectLevel)
            return;

        int prevAspectLevel = level;
        int prevExp = exp;
        REDebug.debugf("Adding %s exp(modified) to player %s's progressLvl (previously at level %s progress %s/%s)", exp, identifier.getUsername(), level, progress, expReq);
        long primevalCoinsGained = 0;

        increment:
        {
            while(progress + exp >= expReq) {
                level++;
                primevalCoinsGained += PRIMEVAL_COINS[Math.min(level, PRIMEVAL_COINS.length - 1)];
                exp -= (expReq - progress);
                progress = 0;
                savedData.setDirty();
                expReq = getAspectLevelProgressReq();
                if(level >= maxAspectLevel)
                    break increment;
                if(level > prevAspectLevel + 1)
                    exp = (int) (exp / 1.5);
                REDebug.debugf("Increased level to %s, remaining exp is %s", level, exp);
            }
            if(exp >= 1) {
                progress += exp;
                savedData.setDirty();
                REDebug.debugf("Added remainder exp to progress, which is now at %s", progress);
            } else
                REDebug.debugf("Remaining exp %s is below 1, and will therefore be ignored", exp);
        }

        savedData.getData(identifier).addPrimevalCoins(primevalCoinsGained);

        REDebug.debugf("Finished progressLvl climbing for %s at %s with progress %s", identifier.getUsername(), level, progress);
        ServerPlayerEntity player = identifier.getPlayer(savedData.mcServer);
        if(player != null) {
            sendDataPacket(player, true);
            if(level != prevAspectLevel) {
                updateAspectProgressBarBonuses(player);
                player.level.playSound(null, player.getX(), player.getY(), player.getZ(), RigoranthusSoundRegistry.ASPECT_PROG_BAR_INCREASE, SoundCategory.AMBIENT, 1F, 1F);
            }
        }
    }

    public void checkBonus(byte type) {
        if(type >= UNDERLING_BONUS_OFFSET && type < UNDERLING_BONUS_OFFSET + underlingBonuses.length && !underlingBonuses[type - UNDERLING_BONUS_OFFSET]) {
            underlingBonuses[type - UNDERLING_BONUS_OFFSET] = true;
            savedData.setDirty();
            increaseProgress(UNDERLING_BONUSES[type - UNDERLING_BONUS_OFFSET]);
        } else if(type >= ALCHEMY_BONUS_OFFSET && type < ALCHEMY_BONUS_OFFSET + alchemyBonuses.length && !alchemyBonuses[type - ALCHEMY_BONUS_OFFSET]) {
            alchemyBonuses[type - ALCHEMY_BONUS_OFFSET] = true;
            savedData.setDirty();
            increaseProgress(ALCHEMY_BONUSES[type - ALCHEMY_BONUS_OFFSET]);
        }
    }
    public int getAspectLevel() {return level;}
    public float getProgress() {return ((float) progress) / getAspectLevelProgressReq();}
    public double getUnderlingDamageModifier() {return getUnderlingDamageModifier(level);}
    public double getUnderlingProtectionModifier() {return getUnderlingProtectionModifier(level);}

    public void updateAspectProgressBarBonuses(ServerPlayerEntity player) {
        int healthBonus = healthBoost(level);
        double damageBonus = attackBonus(level);

        updateAttribute(player.getAttribute(Attributes.MAX_HEALTH), new AttributeModifier(progressLvlHealthBoostModifierUUID, "AspectProgressBar Health Boost", healthBonus, AttributeModifier.Operation.ADDITION));    //If this isn't saved, your health goes to 10 hearts (if it was higher before) when loading the save file.
        updateAttribute(player.getAttribute(Attributes.ATTACK_DAMAGE), new AttributeModifier(progressLvlDamageBoostModifierUUID, "AspectProgressBar Damage Boost", damageBonus, AttributeModifier.Operation.MULTIPLY_BASE));
    }

    public void updateAttribute(ModifiableAttributeInstance attribute, AttributeModifier modifier) {
        if(attribute.hasModifier(modifier))
            attribute.removeModifier(attribute.getModifier(modifier.getId()));
        attribute.addPermanentModifier(modifier);
    }

    public void saveAspectProgressBar(CompoundNBT nbt) {
        nbt.putInt("level", level);
        nbt.putInt("levelProgress", progress);

        byte[] bonuses = new byte[ALCHEMY_BONUS_OFFSET + alchemyBonuses.length];    //Booleans would be stored as bytes anyways
        for(int i = 0; i < underlingBonuses.length; i++)
            bonuses[i + UNDERLING_BONUS_OFFSET] = (byte) (underlingBonuses[i] ? 1 : 0);
        for(int i = 0; i < alchemyBonuses.length; i++)
            bonuses[i + ALCHEMY_BONUS_OFFSET] = (byte) (alchemyBonuses[i] ? 1 : 0);
        nbt.putByteArray("levelBonuses", bonuses);
    }

    public void loadAspectProgressBar(CompoundNBT nbt) {
        level = nbt.getInt("level");
        progress = nbt.getInt("levelProgress");

        byte[] bonuses = nbt.getByteArray("levelBonuses");
        for(int i = 0; i < underlingBonuses.length && i + UNDERLING_BONUS_OFFSET < bonuses.length; i++)
            underlingBonuses[i] = bonuses[i + UNDERLING_BONUS_OFFSET] != 0;
        for(int i = 0; i < alchemyBonuses.length && i + ALCHEMY_BONUS_OFFSET < bonuses.length; i++)
            alchemyBonuses[i] = bonuses[i + ALCHEMY_BONUS_OFFSET] != 0;
    }

    public static double attackBonus(int level) {return Math.pow(1.015, level) - 1;}
    public static int healthBoost(int level) {return (int) (40 * (level / (float) (AspectProgressBar.LEVEL_COUNT - 1)));} //At max level, the player will have three rows of hearts
    public static double getUnderlingDamageModifier(int level) {return 1 + level * 0.04D;}
    public static double getUnderlingProtectionModifier(int level) {return 1 / (level * 0.06D + 1);}

    public void setByCommand(int level, double progress) {
        int prevAspectLevel = this.level;
        int prevProgress = this.progress;

        this.level = MathHelper.clamp(level, 0, LEVEL_COUNT - 1);

        if(level != LEVEL_COUNT - 1) {
            this.progress = (int) (getAspectLevelProgressReq() * progress);
            if(this.progress >= getAspectLevelProgressReq())
                this.progress--;
        } else this.progress = 0;

        if(prevProgress != this.progress || prevAspectLevel != this.level) {
            savedData.setDirty();
            ServerPlayerEntity player = identifier.getPlayer(savedData.mcServer);
            if(player != null && (Config.progressLvlProgress.get() || prevAspectLevel != this.level)) {
                sendDataPacket(player, false);
                if(prevAspectLevel != this.level)
                    updateAspectProgressBarBonuses(player);
            }
        }
    }

    public void sendInitialPacket(ServerPlayerEntity player) {
        AspectProgressBarDataPacket packet = AspectProgressBarDataPacket.init(getAspectLevel(), Config.progressLvlProgress.get() ? getProgress() : 0F);
        REPacketHandler.sendToPlayer(packet, player);
    }

    public void sendDataPacket(ServerPlayerEntity player, boolean sendMessage) {
        AspectProgressBarDataPacket packet = AspectProgressBarDataPacket.create(getAspectLevel(), Config.progressLvlProgress.get() ? getProgress() : 0F, sendMessage);
        REPacketHandler.sendToPlayer(packet, player);
    }

    public static String translationKey(int level) {
        return "progressLvl.level." + level;
    }
}
