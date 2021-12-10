package com.platinumg17.rigoranthusemortisreborn.magica.client;

import com.platinumg17.rigoranthusemortisreborn.api.apimagic.spell.Spell;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.util.MappingUtil;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.magica.common.block.TableBlock;
import com.platinumg17.rigoranthusemortisreborn.magica.common.enchantment.EnchantmentRegistry;
import com.platinumg17.rigoranthusemortisreborn.magica.common.items.SpellBook;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.FirstPersonRenderer;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import static com.platinumg17.rigoranthusemortisreborn.api.apimagic.util.DropDistribution.rand;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = EmortisConstants.MOD_ID)
public class PlayerEvent {

    private static final Minecraft minecraft = Minecraft.getInstance();

    @SubscribeEvent
    public static void onTick(final TickEvent.RenderTickEvent evt) {
    }

    @SubscribeEvent
    public static void onBlock(final PlayerInteractEvent.RightClickBlock event) {
        PlayerEntity entity = event.getPlayer();
        if(!event.getWorld().isClientSide || event.getHand() != Hand.MAIN_HAND || event.getWorld().getBlockState(event.getPos()).getBlock() instanceof TableBlock)
            return;
        if(entity.getItemInHand(event.getHand()).getItem() instanceof SpellBook){
            event.setCanceled(true);
            ObfuscationReflectionHelper.setPrivateValue(FirstPersonRenderer.class, minecraft.getItemInHandRenderer(), 1f, MappingUtil.getEquippedProgressMainhand());
        }
    }

    @SubscribeEvent
    public static void onTooltip(final ItemTooltipEvent event){
        ItemStack stack = event.getItemStack();
        int level = EnchantmentHelper.getItemEnchantmentLevel(EnchantmentRegistry.REACTIVE_ENCHANTMENT, stack);
        if(level > 0 && stack.hasTag() && stack.getTag().contains("spell")){
            Spell spell = Spell.deserialize(stack.getTag().getString("spell"));
            event.getToolTip().add(new StringTextComponent(spell.getDisplayString()));
        }
    }

    @SubscribeEvent
    public static void onItem(final PlayerInteractEvent.RightClickItem event) {
        PlayerEntity entity = event.getPlayer();
        if(!event.getWorld().isClientSide || event.getHand() != Hand.MAIN_HAND)
            return;
        if(entity.getItemInHand(event.getHand()).getItem() instanceof SpellBook){
            event.setCanceled(true);
            ObfuscationReflectionHelper.setPrivateValue(FirstPersonRenderer.class, minecraft.getItemInHandRenderer(), 1f, MappingUtil.getEquippedProgressMainhand());
        }
    }

// TODO use this to implement the Esotericum Powder tooltip effect

//    @SubscribeEvent
//    public static void playerLoginEvent(final net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent event){
//        if(!event.getPlayer().level.isClientSide/* && event.getPlayer().hasEffect(ModPotions.HERB_VISION_EFFECT)*/){
//            CompoundNBT tag = event.getPlayer().getPersistentData().getCompound(PlayerEntity.PERSISTED_NBT_TAG);
//            Networking.INSTANCE.send(PacketDistributor.PLAYER.with(()-> (ServerPlayerEntity) event.getPlayer()), new PacketGetPersistentData(tag));
//        }
//    }

    @SubscribeEvent
    public static void playerRender(final RenderPlayerEvent event) {
    }

//    @SubscribeEvent
//    public static void playerTickEvent(final TickEvent.PlayerTickEvent event){
//        if(event.side == LogicalSide.CLIENT && event.phase == TickEvent.Phase.END && event.player.getEffect(ModPotions.X_R_A_EFFECT) != null && ClientInfo.ticksInGame % 30 == 0){
//            List<BlockPos> herbPos = new ArrayList<>();
//            CompoundNBT tag = ClientInfo.persistentData;
//            if(!tag.contains("herb_vision"))
//                return;
//            PlayerEntity playerEntity = event.player;
//            World world = playerEntity.level;
//            for(BlockPos p : BlockPos.withinManhattan(playerEntity.blockPosition(), 20, 120, 20)){
//                if(p.getY() >= world.getMaxBuildHeight() || world.getBlockState(p).isAir(world, p))
//                    continue;
//                if(herbPos.size() >= 50)
//                    break;
//
//                if(world.getBlockState(p).getBlock().getRegistryName().toString().equals(tag.getString("herb_vision"))) {
//                    raPos.add(new BlockPos(p));
//                }
//            }
//            ClientInfo.herbPositions = raPos;
//        }
//    }

//    @SubscribeEvent
//    public static void onRenderWorldLast(final RenderWorldLastEvent event) {
//        final PlayerEntity playerEntity = Minecraft.getInstance().player;
//        if (playerEntity == null || playerEntity.getEffect(ModPotions.X_R_A_EFFECT) == null)
//            return;
//        Vector3d vector3d = Minecraft.getInstance().gameRenderer.getMainCamera().getPosition();
//        ClientWorld world = Minecraft.getInstance().level;
//        double xView = vector3d.x();
//        double yView = vector3d.y();
//        double zView = vector3d.z();
//        if(Minecraft.getInstance().isPaused())
//            return;
//        for(BlockPos p : ClientInfo.herbPositions){
//            ParticleColor color = new ParticleColor(
//                    rand.nextInt(255),
//                    rand.nextInt(255),
//                    rand.nextInt(255));
//            BlockPos renderPos = new BlockPos(p);
//            if(Math.abs(yView - p.getY()) >= 30){
//                renderPos = new BlockPos(p.getX(), p.getY() > yView ? yView + 20 : yView - 20, p.getZ());
//                color = new ParticleColor(
//                        rand.nextInt(30),
//                        rand.nextInt(255),
//                        rand.nextInt(50));
//            }
//            if(Math.abs(yView - p.getY()) >= 60){
//                renderPos = new BlockPos(p.getX(), p.getY() > yView ? yView + 20 : yView - 20, p.getZ());
//                color =  new ParticleColor(
//                        rand.nextInt(50),
//                        rand.nextInt(50),
//                        rand.nextInt(255));
//            }
//            world.addParticle(
//                    GlowParticleData.createData(color, true),
//                    renderPos.getX() + 0.5 + ParticleUtil.inRange(-0.1, 0.1), renderPos.getY() + 0.2 + ParticleUtil.inRange(-0.1, 0.1), renderPos.getZ() + 0.5 + ParticleUtil.inRange(-0.1, 0.1),
//                    0, 0.03f, 0);
//        }
//    }
}