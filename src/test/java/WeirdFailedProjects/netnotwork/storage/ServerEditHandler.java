package WeirdFailedProjects.netnotwork.storage;

import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import WeirdFailedProjects.netnotwork.ServerEditPacket;
import com.platinumg17.rigoranthusemortisreborn.entity.render.specialentities.EminentialEntity;
import com.platinumg17.rigoranthusemortisreborn.util.REDebug;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.chunk.ChunkStatus;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityTravelToDimensionEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

/**
 * Main class to handle the server side of edit mode.
 * Also contains some methods used on both sides.
 * @author kirderf1
 */
@Mod.EventBusSubscriber(modid = EmortisConstants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class ServerEditHandler	//TODO Consider splitting this class into two
{
    private static final Logger LOGGER = LogManager.getLogger();
    public static final ArrayList<String> commands = new ArrayList<>(Arrays.asList("effect", "gamemode", "defaultgamemode", "enchant", "xp", "tp", "spreadplayers", "kill", "clear", "spawnpoint", "setworldspawn", "give"));

//    static final Map<SburbConnection, Vector3d> lastEditmodePos = new HashMap<>();

    /**
     * Called both when any player logged out and when a player pressed the exit button.
     */
    public static void onPlayerExit(PlayerEntity player)
    {
        if(!player.level.isClientSide)
            reset(getData(player));
    }

//    @SubscribeEvent
//    public static void serverStopped(FMLServerStoppedEvent event)
//    {
////        lastEditmodePos.clear();
//    }

//    @SubscribeEvent
//    public static void onDisconnect(ConnectionClosedEvent event)
//    {
//        reset(getData(event.getMinecraftServer(), event.getConnection()));
//        lastEditmodePos.remove(event.getConnection());
//    }

//    @SubscribeEvent
//    public static void onEntry(SburbEvent.OnEntry event)
//    {
//        lastEditmodePos.remove(event.getConnection());
//    }

    @SubscribeEvent
    public static void onPlayerCloneEvent(PlayerEvent.Clone event)
    {
        EditData prevData = getData(event.getOriginal());
        if(prevData != null && event.getPlayer() instanceof ServerPlayerEntity)
        {
            //take measures to prevent editmode data from ending up with an invalid player entity
            LOGGER.error("Rigoranthus failed to prevent death or different cloning event for player {}. Applying measure to reduce problems", event.getPlayer().getName().getString());

            REExtraData data = REExtraData.get(event.getEntity().level);
            data.removeEditData(prevData);
            data.addEditData(new EditData(prevData.getEminentialProjection(), (ServerPlayerEntity) event.getPlayer(), prevData.connection));
        }
    }

    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent event)
    {
        if(event.getEntity() instanceof ServerPlayerEntity && getData((ServerPlayerEntity) event.getEntity()) != null)
            event.setCanceled(true);
    }

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event)
    {
        if(event.getEntity() instanceof ServerPlayerEntity && getData((ServerPlayerEntity) event.getEntity()) != null)
            event.setCanceled(true);
    }

    public static void reset(EditData data)
    {
        reset(null, 0, data);
    }

    /**
     * Called when the server stops editing the clients house.
     * @param damageSource If the process was cancelled by the decoy taking damage, this parameter will be the damage source. Else null.
     * @param damage If the damageSource isn't null, this is the damage taken, else this parameter is ignored.
     * @param editData edit-data that identifies the editmode session
     */
    public static void reset(DamageSource damageSource, float damage, EditData editData)
    {
        partialReset(damageSource, damage, editData);

        if(editData == null)
            return;

        REExtraData data = REExtraData.get(editData.getEditor().level);
        data.removeEditData(editData);
    }

    private static void partialReset(EditData data)
    {
        partialReset(null, 0, data);
    }
    private static void partialReset(DamageSource damageSource, float damage, EditData editData)
    {
        if(editData == null)
            return;

        ServerPlayerEntity player = editData.getEditor();

        editData.recover();	//TODO handle exception from failed recovery

        ServerEditPacket packet = ServerEditPacket.exit();
        REPacketHandler.sendToPlayer(packet, player);

        editData.getEminentialProjection().markedForDespawn = true;

        if(damageSource != null && damageSource.getDirectEntity() != player)
            player.hurt(damageSource, damage);
    }

    public static void newServerEditor(ServerPlayerEntity player, PlayerIdentifier computerOwner, PlayerIdentifier computerTarget)
    {
        if(player.isPassenger())
        {
            player.sendMessage(new StringTextComponent("You may not activate editmode while riding something"), Util.NIL_UUID);
            return;    //Don't want to bother making the decoy able to ride anything right now.
        }
//        SburbConnection c = SkaianetHandler.get(player.getServer()).getActiveConnection(computerTarget);
//        if(c != null && c.getServerIdentifier().equals(computerOwner) && getData(player.server, c) == null && getData(player) == null)
//        {
            REDebug.info("Activating edit mode on player \""+player.getName().getString()+"\", target player: \""+computerTarget+"\".");
            EminentialEntity decoy = new EminentialEntity((ServerWorld) player.level, player);
            EditData data = new EditData(decoy, player, c);

            if(!setPlayerStats(player, c))
            {
                player.sendMessage(new StringTextComponent("Failed to activate edit mode.").withStyle(TextFormatting.RED), Util.NIL_UUID);
                return;
            }
            if(c.getEditmodeInventory() != null)
                player.inventory.load(c.getEditmodeInventory());
            decoy.level.addFreshEntity(decoy);
            REExtraData.get(player.level).addEditData(data);

            BlockPos center = getEditmodeCenter(c);
            ServerEditPacket packet = ServerEditPacket.activate(computerTarget.getUsername(), center.getX(), center.getZ(), DeployList.getDeployListTag(player.getServer(), c));
//            REPacketHandler.sendToPlayer(packet, player);
//            data.sendGristCacheToEditor();
//        }
    }
//
//    static boolean setPlayerStats(ServerPlayerEntity player, SburbConnection c)
//    {
//
//        double posX, posY, posZ;
////        ServerWorld world = player.getServer().getLevel(c.hasEntered() ? c.getClientDimension() : c.getClientComputer().getPosForEditmode().dimension());
//
//        if(lastEditmodePos.containsKey(c))
//        {
//            Vector3d lastPos = lastEditmodePos.get(c);
//            posX = lastPos.x;
//            posZ = lastPos.z;
//        } else
//        {
//            BlockPos center = getEditmodeCenter(c);
//            posX = center.getX() + 0.5;
//            posZ = center.getZ() + 0.5;
//        }
//        posY = getMotionBlockingY(world, MathHelper.floor(posX), MathHelper.floor(posZ));
//
////        if(Teleport.teleportEntity(player, world, posX, posY, posZ) == null)
////            return false;
//
//        player.closeContainer();
//        player.inventory.clearContent();
//
//        player.setGameMode(GameType.CREATIVE);
//        player.onUpdateAbilities();
//
//        return true;
//    }

    //Helper function to force a chunk to load, to then get the top block
    private static int getMotionBlockingY(ServerWorld world, int x, int z)
    {
        return world.getChunk(x >> 4, z >> 4, ChunkStatus.FULL, true).getHeight(Heightmap.Type.MOTION_BLOCKING, x & 0xF, x & 0xF) + 1;
    }

//    public static void resendEditmodeStatus(ServerPlayerEntity editor)
//    {
//        EditData data = getData(editor);
//        if(data != null)
//        {
//            BlockPos center = getEditmodeCenter(data.connection);
//            ServerEditPacket packet = ServerEditPacket.activate(data.connection.getClientIdentifier().getUsername(), center.getX(), center.getZ(), DeployList.getDeployListTag(editor.getServer(), data.connection));
//            REPacketHandler.sendToPlayer(packet, editor);
//            data.sendGristCacheToEditor();
//        } else
//        {
//            ServerEditPacket packet = ServerEditPacket.exit();
//            REPacketHandler.sendToPlayer(packet, editor);
//        }
//    }

    public static EditData getData(PlayerEntity editor)
    {
        return REExtraData.get(editor.level).findEditData(editData -> editData.getEditor() == editor);
    }

//    public static EditData getData(MinecraftServer server, SburbConnection c)
//    {
//        return REExtraData.get(server).findEditData(editData -> editData.connection.getClientIdentifier().equals(c.getClientIdentifier()) && editData.connection.getServerIdentifier().equals(c.getServerIdentifier()));
//    }

    public static EditData getData(EminentialEntity eminentialProjection) {
        return REExtraData.get(eminentialProjection.getCommandSenderWorld()).findEditData(editData -> editData.getEminentialProjection() == eminentialProjection);
    }

//    private static BlockPos getEditmodeCenter(SburbConnection connection)
//    {
//        GlobalPos computerPos = connection.getClientComputer().getPosForEditmode();
//        if(computerPos == null)
//            throw new IllegalStateException("Connection has to be active with a computer position to be used here");
//        if(connection.hasEntered())
//            return new BlockPos(0, 0, 0);
//        else return computerPos.pos();
//    }

    @SubscribeEvent
    public static void tickEnd(TickEvent.PlayerTickEvent event) {
        if(event.phase != TickEvent.Phase.END || event.side == LogicalSide.CLIENT)
            return;
        ServerPlayerEntity player = (ServerPlayerEntity) event.player;

        EditData data = getData(player);
        if(data == null)
            return;

//        SburbConnection c = data.connection;
//        int range = REDimensions.isLandDimension(player.server, player.level.dimension()) ? Config.landEditRange.get() : Config.overworldEditRange.get();
        BlockPos center = getEditmodeCenter(c);
//
//        updateInventory(player, c);
        updatePosition(player, range, center.getX(), center.getZ());

        player.setPortalCooldown();
    }
//
//    @SubscribeEvent
//    public static void onTossEvent(ItemTossEvent event)
//    {
//        PlayerInventory inventory = event.getPlayer().inventory;
//        if(!event.getEntity().level.isClientSide && getData(event.getPlayer()) != null)
//        {
//            EditData data = getData(event.getPlayer());
//            ItemStack stack = event.getEntityItem().getItem();
//            DeployEntry entry = DeployList.getEntryForItem(stack, data.connection, event.getEntity().level);
//            if(entry != null && !isBlockItem(stack.getItem()))
//            {
//                GristSet cost = entry.getCurrentCost(data.connection);
//                if(GristHelper.canAfford(PlayerSavedData.getData(data.connection.getClientIdentifier(), event.getPlayer().level).getGristCache(), cost))
//                {
//                    GristHelper.decrease(event.getPlayer().level, data.connection.getClientIdentifier(), cost);
//                    GristHelper.notifyEditPlayer(event.getPlayer().level.getServer(), data.connection.getClientIdentifier(), cost, false);
//                    data.connection.setHasGivenItem(entry);
//                    if(!data.connection.isMain())
//                        SburbHandler.giveItems(event.getPlayer().getServer(), data.connection.getClientIdentifier());
//                }
//                else event.setCanceled(true);
//            }
//            else
//            {
//                event.setCanceled(true);
//            }
//            if(event.isCanceled())
//            {
//                event.getEntityItem().remove();
//                if(!inventory.getCarried().isEmpty())
//                    inventory.setCarried(ItemStack.EMPTY);
//                else inventory.setItem(inventory.selected, ItemStack.EMPTY);
//            }
//        }
//    }

    @SubscribeEvent
    public static void onItemPickupEvent(EntityItemPickupEvent event)
    {
        if(!event.getEntity().level.isClientSide && getData(event.getPlayer()) != null)
            event.setCanceled(true);
    }
//
//    //TODO Slightly unsafe with this approach to check, and then execute in a different event listener. It is probably better to try first, and then reset if we tried and the event got cancelled.
//    @SubscribeEvent(priority=EventPriority.NORMAL)
//    public static void onRightClickBlockControl(PlayerInteractEvent.RightClickBlock event)
//    {
//        if(!event.getWorld().isClientSide && getData(event.getPlayer()) != null)
//        {
//            EditData data = getData(event.getPlayer());
//            Block block = event.getWorld().getBlockState(event.getPos()).getBlock();
//            ItemStack stack = event.getPlayer().getMainHandItem();
//            event.setUseBlock(stack.isEmpty() && (block instanceof DoorBlock || block instanceof TrapDoorBlock || block instanceof FenceGateBlock) ? Event.Result.ALLOW : Event.Result.DENY);
//            if(event.getUseBlock() == Event.Result.ALLOW)
//                return;
//            if(stack.isEmpty() || !isBlockItem(stack.getItem()) || event.getHand().equals(Hand.OFF_HAND))
//            {
//                event.setCanceled(true);
//                return;
//            }
//
//            cleanStackNBT(stack, data.connection, event.getWorld());
//
//            DeployEntry entry = DeployList.getEntryForItem(stack, data.connection, event.getEntity().level);
//            if(entry != null)
//            {
//                GristSet cost = entry.getCurrentCost(data.connection);
//                if(!GristHelper.canAfford(event.getWorld(), data.connection.getClientIdentifier(), cost))
//                {
//                    if(cost != null)
//                        event.getPlayer().sendMessage(cost.createMissingMessage(), Util.NIL_UUID);
//                    event.setCanceled(true);
//                }
//            }
//            else if(!isBlockItem(stack.getItem()) || !GristHelper.canAfford(event.getWorld(), data.connection.getClientIdentifier(), GristCostRecipe.findCostForItem(stack, null, false, event.getWorld())))
//            {
//                event.setCanceled(true);
//            }
//            if(event.getUseItem() == Event.Result.DEFAULT)
//                event.setUseItem(Event.Result.ALLOW);
//        }
//    }
//
//    @SubscribeEvent(priority=EventPriority.NORMAL)
//    public static void onLeftClickBlockControl(PlayerInteractEvent.LeftClickBlock event)
//    {
//        if(!event.getWorld().isClientSide && getData(event.getPlayer()) != null)
//        {
//            EditData data = getData(event.getPlayer());
//            BlockState block = event.getWorld().getBlockState(event.getPos());
//            if(block.getDestroySpeed(event.getWorld(), event.getPos()) < 0 || block.getMaterial() == Material.PORTAL
//                    || (GristHelper.getGrist(event.getEntity().level, data.connection.getClientIdentifier(), GristTypes.BUILD) <= 0 && !Config.refund.get()))
//                event.setCanceled(true);
//        }
//    }

    @SubscribeEvent(priority=EventPriority.NORMAL)
    public static void onItemUseControl(PlayerInteractEvent.RightClickItem event)
    {
        if(!event.getWorld().isClientSide && getData(event.getPlayer()) != null)
        {
            event.setCanceled(true);
        }
    }

//    @SubscribeEvent(priority=EventPriority.LOWEST)
//    public static void onBlockBreak(PlayerInteractEvent.LeftClickBlock event)
//    {
//        if(!event.getEntity().level.isClientSide && getData(event.getPlayer()) != null)
//        {
//            EditData data = getData(event.getPlayer());
//            if(!Config.Refund.get())
//            {
//                GristHelper.decrease(event.getWorld(), data.connection.getClientIdentifier(), new GristSet(GristTypes.BUILD,1));
//                GristHelper.notifyEditPlayer(event.getWorld().getServer(), data.connection.getClientIdentifier(), new GristSet(GristTypes.BUILD, 1), false);
//            }
//            else
//            {
//                BlockState block = event.getWorld().getBlockState(event.getPos());
//                ItemStack stack = block.getBlock().getPickBlock(block, null, event.getWorld(), event.getPos(), event.getPlayer());
//                GristSet set = GristCostRecipe.findCostForItem(stack, null, false, event.getWorld());
//                if(set != null && !set.isEmpty())
//                    GristHelper.increase(event.getWorld(), data.connection.getClientIdentifier(), set);
//                GristHelper.notifyEditPlayer(event.getWorld().getServer(), data.connection.getClientIdentifier(), new GristSet(GristTypes.BUILD, 1), true);
//            }
//        }
//    }
//
//    @SubscribeEvent(priority=EventPriority.LOW)
//    public static void onBlockPlaced(BlockEvent.EntityPlaceEvent event)
//    {
//        if(event.getEntity() instanceof ServerPlayerEntity)
//        {
//            ServerPlayerEntity player = (ServerPlayerEntity) event.getEntity();
//            if(getData(player) != null)
//            {
//                EditData data = getData(player);
//                if(event.isCanceled())    //If the event was cancelled server side and not client side, notify the client.
//                {
//                    data.sendGivenItemsToEditor();
//                    return;
//                }
//
//                ItemStack stack = player.getMainHandItem();	//TODO Make sure offhand isn't used in editmode?
//                SburbConnection c = data.connection;
//                DeployEntry entry = DeployList.getEntryForItem(stack, c, player.level);
//                if(entry != null)
//                {
//                    GristSet cost = entry.getCurrentCost(c);
//                    c.setHasGivenItem(entry);
//                    if(!c.isMain())
//                        SburbHandler.giveItems(player.server, c.getClientIdentifier());
//                    if(!cost.isEmpty())
//                    {
//                        GristHelper.decrease(player.level, c.getClientIdentifier(), cost);
//                        GristHelper.notifyEditPlayer(player.level.getServer(), c.getClientIdentifier(), cost, false);
//                    }
//                    player.inventory.items.set(player.inventory.selected, ItemStack.EMPTY);
//                } else
//                {
//                    GristHelper.decrease(player.level, data.connection.getClientIdentifier(), GristCostRecipe.findCostForItem(stack, null, false, player.getCommandSenderWorld()));
//                    GristHelper.notifyEditPlayer(player.level.getServer(), data.connection.getClientIdentifier(), GristCostRecipe.findCostForItem(stack, null, false, player.getCommandSenderWorld()), false);
//                }
//            }
//        }
//    }

    @SubscribeEvent(priority=EventPriority.NORMAL)
    public static void onAttackEvent(AttackEntityEvent event)
    {
        if(!event.getEntity().level.isClientSide && getData(event.getPlayer()) != null)
            event.setCanceled(true);
    }

    @SubscribeEvent(priority=EventPriority.NORMAL)
    public static void onInteractEvent(PlayerInteractEvent.EntityInteract event)
    {
        if(!event.getEntity().level.isClientSide && getData(event.getPlayer()) != null)
            event.setCanceled(true);
    }

    @SubscribeEvent(priority=EventPriority.NORMAL)
    public static void onInteractEvent(PlayerInteractEvent.EntityInteractSpecific event)
    {
        if(!event.getEntity().level.isClientSide && getData(event.getPlayer()) != null)
            event.setCanceled(true);
    }

    /**
     * Used on both server and client side.
     */
    public static void updatePosition(PlayerEntity player, double range, int centerX, int centerZ) {
        double y = player.getY();
        if(y < 0) {
            y = 0;
            player.setDeltaMovement(player.getDeltaMovement().multiply(1, 0, 1));
            player.abilities.flying = true;
        }

        double newX = player.getX();
        double newZ = player.getZ();
        double offset = player.getBoundingBox().maxX-player.getX();

        if(range >= 1) {
            if(player.getX() > centerX+range-offset)
                newX = centerX+range-offset;
            else if(player.getX() < centerX-range+offset)
                newX = centerX-range+offset;
            if(player.getZ() > centerZ+range-offset)
                newZ = centerZ+range-offset;
            else if(player.getZ() < centerZ-range+offset)
                newZ = centerZ-range+offset;
        }

        if(newX != player.getX())
            player.setDeltaMovement(player.getDeltaMovement().multiply(0, 1, 1));

        if(newZ != player.getZ())
            player.setDeltaMovement(player.getDeltaMovement().multiply(1, 1, 0));

        if(newX != player.getX() || newZ != player.getZ() || y != player.getY())
        {
            if(player.level.isClientSide)
                player.setPos(newX, y, newZ);
            else player.teleportTo(newX, y, newZ);
        }
    }

    public static void updateInventory(ServerPlayerEntity player)//, SburbConnection connection)
    {
//        List<DeployEntry> deployList = DeployList.getItemList(player.getServer(), connection);
//        deployList.removeIf(entry -> entry.getCurrentCost(connection) == null);
        List<ItemStack> itemList = new ArrayList<>();
//        deployList.forEach(deployEntry -> itemList.add(deployEntry.getItemStack(connection, player.level)));

        boolean inventoryChanged = false;
        for(int i = 0; i < player.inventory.items.size(); i++)
        {
            ItemStack stack = player.inventory.items.get(i);
            if(stack.isEmpty())
                continue;
//            if(GristCostRecipe.findCostForItem(stack, null, false, player.getCommandSenderWorld()) == null || !isBlockItem(stack.getItem())) {
//                listSearch :{
//                    for(ItemStack deployStack : itemList)
//                        if(ItemStack.matches(deployStack, stack))
//                            break listSearch;
//                    player.inventory.items.set(i, ItemStack.EMPTY);
//                    inventoryChanged = true;
//                }
//            }
            else if(stack.hasTag()) {
                listSearch :{
                    for(ItemStack deployStack : itemList)
                        if(ItemStack.matches(deployStack, stack))
                            break listSearch;
                    stack.setTag(null);
                    inventoryChanged = true;
                }
            }
            if(stack.getCount() > 1) {
                stack.setCount(1);
                inventoryChanged = true;
            }
        }
        if(inventoryChanged)
            player.getServer().getPlayerList().sendAllPlayerInfo(player);
    }


    @SubscribeEvent
    public static void onEntityTeleport(EntityTravelToDimensionEvent event) {
        if(event.getEntity() instanceof ServerPlayerEntity) {
            EditData data = getData((ServerPlayerEntity) event.getEntity());
            // Prevent edit player teleportation unless it is recovering to its initial state
            if(data != null && !data.isRecovering())
                event.setCanceled(true);
        }
    }

    public static boolean isBlockItem(Item item) {return item instanceof BlockItem;}

//    public static void cleanStackNBT(ItemStack stack, SburbConnection c, World world) {
//        if(!DeployList.containsItemStack(stack, c, world))
//            stack.setTag(null);
//    }

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
        UUID id = player.getGameProfile().getId();
        EditData.PlayerRecovery recovery = REExtraData.get(player.level).removePlayerRecovery(id);
        if(recovery != null)
            recovery.recover(player, false);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {ServerEditHandler.onPlayerExit(event.getPlayer());}

    @SubscribeEvent
    public static void onServerStopping(FMLServerStoppingEvent event) {REExtraData.get(event.getServer()).forEachAndClear(ServerEditHandler::partialReset);}

    @SubscribeEvent
    public static void serverStarted(FMLServerStartedEvent event) {
//        SkaianetHandler skaianet = SkaianetHandler.get(event.getServer());
//        REExtraData.get(event.getServer()).recoverConnections(recovery -> recovery.recover(skaianet.getActiveConnection(recovery.getClientPlayer())));
    }
}