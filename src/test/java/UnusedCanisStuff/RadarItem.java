package UnusedCanisStuff;

import com.platinumg17.rigoranthusemortisreborn.canis.CanisItems;
import com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.packet.data.storage.CanisLocationData;
import com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.packet.data.storage.CanisLocationStorage;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

//public class RadarItem extends Item {
//
//    public RadarItem(Properties properties) {
//        super(properties);
//    }
//
//    @Override
//    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
//        ItemStack stack = playerIn.getItemInHand(handIn);
////        if (worldIn.isRemote) {
////            DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> RadarScreen.open(playerIn));
////        }
//
//        if (!worldIn.isClientSide) {
//            if (playerIn.isShiftKeyDown()) {
//                CanisLocationStorage locationManager = CanisLocationStorage.get(worldIn);
//                for (UUID uuid : locationManager.getAllUUID()) {
//                    playerIn.sendMessage(new TranslationTextComponent(locationManager.getData(uuid).toString()), Util.NIL_UUID);
//                }
//                return new ActionResult<>(ActionResultType.FAIL, playerIn.getItemInHand(handIn));
//            }
//
//            RegistryKey<World> dimCurr = playerIn.level.dimension();
//
//            playerIn.sendMessage(new TranslationTextComponent(""), Util.NIL_UUID);
//
//            CanisLocationStorage locationManager = CanisLocationStorage.get(worldIn);
//            List<CanisLocationData> ownCani = locationManager.getCani(playerIn, dimCurr).collect(Collectors.toList());
//
//            if (ownCani.isEmpty()) {
//                playerIn.sendMessage(new TranslationTextComponent("canisradar.errornull", dimCurr.location()), Util.NIL_UUID);
//            } else {
//                boolean flag = false;
//
//                for (CanisLocationData loc : ownCani) {
//                    if (loc.shouldDisplay(worldIn, playerIn, handIn)) {
//                        flag = true;
//
//                        String translateStr = RadarItem.getDirectionTranslationKey(loc, playerIn);
//                        int distance = MathHelper.ceil(loc.getPos() != null ? loc.getPos().distanceTo(playerIn.position()) : -1);
//
//                        playerIn.sendMessage(new TranslationTextComponent(translateStr, loc.getName(worldIn), distance), Util.NIL_UUID);
//                    }
//                }
//
//                if (!flag) {
//                    playerIn.sendMessage(new TranslationTextComponent("canisradar.errornoradio"), Util.NIL_UUID);
//                }
//            }
//
//            List<RegistryKey<World>> otherCani = new ArrayList<>();
//            List<RegistryKey<World>> noCani = new ArrayList<>();
//            for (RegistryKey<World> worldkey : worldIn.getServer().levelKeys()) {
//                if (worldkey.equals(worldIn.dimension()))  continue;
//                ownCani = locationManager.getCani(playerIn, worldkey).collect(Collectors.toList()); // Check if radio collar is on
//
//                (ownCani.size() > 0 ? otherCani : noCani).add(worldkey);
//            }
//
//            if (otherCani.size() > 0) {
//                playerIn.sendMessage(new TranslationTextComponent("canisradar.notindim", otherCani.stream().map(RegistryKey::location).map(Objects::toString).collect(Collectors.joining(", "))), Util.NIL_UUID);
//            }
//
//            if (noCani.size() > 0 && stack.getItem() == CanisItems.CREATIVE_RADAR.get()) {
//                playerIn.sendMessage(new TranslationTextComponent("canisradar.errornull", noCani.stream().map(RegistryKey::location).map(Objects::toString).collect(Collectors.joining(", "))), Util.NIL_UUID);
//            }
//        }
//        return new ActionResult<>(ActionResultType.FAIL, stack);
//    }
//
//    public static String getDirectionTranslationKey(CanisLocationData loc, Entity entity) {
//        if (loc.getPos() == null) {
//            return "canisradar.unknown";
//        }
//        Vector3d diff = loc.getPos().add(entity.position().reverse());
//        double angle = MathHelper.atan2(diff.x(), diff.z());
//
//        if (angle < -Math.PI + Math.PI / 8) {
//            return "canisradar.north";
//        } else if (angle < -Math.PI + 3 * Math.PI / 8) {
//            return "canisradar.north.west";
//        } else if (angle < -Math.PI + 5 * Math.PI / 8) {
//            return "canisradar.west";
//        } else if (angle < -Math.PI + 7 * Math.PI / 8) {
//            return "canisradar.south.west";
//        } else if (angle < -Math.PI + 9 * Math.PI / 8) {
//            return "canisradar.south";
//        } else if (angle < -Math.PI + 11 * Math.PI / 8) {
//            return "canisradar.south.east";
//        } else if (angle < -Math.PI + 13 * Math.PI / 8) {
//            return "canisradar.east";
//        } else if (angle < -Math.PI + 15 * Math.PI / 8) {
//            return "canisradar.north.east";
//        } else {
//            return "canisradar.north";
//        }
//    }
//}