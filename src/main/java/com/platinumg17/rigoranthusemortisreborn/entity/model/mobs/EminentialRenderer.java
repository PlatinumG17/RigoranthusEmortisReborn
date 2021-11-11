package com.platinumg17.rigoranthusemortisreborn.entity.model.mobs;

//import com.mojang.blaze3d.matrix.MatrixStack;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.network.play.ClientPlayNetHandler;
//import net.minecraft.client.network.play.NetworkPlayerInfo;
//import net.minecraft.client.renderer.IRenderTypeBuffer;
//import net.minecraft.client.renderer.entity.EntityRendererManager;
//import net.minecraft.client.renderer.entity.MobRenderer;
//import net.minecraft.client.renderer.entity.layers.ArrowLayer;
//import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
//import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
//import net.minecraft.client.renderer.entity.model.BipedModel;
//import net.minecraft.client.renderer.entity.model.PlayerModel;
//import net.minecraft.client.resources.DefaultPlayerSkin;
//import net.minecraft.util.ResourceLocation;
//
//import java.util.UUID;
//
//public class EminentialRenderer extends MobRenderer<EminentialEntity, PlayerModel<EminentialEntity>> {
//    private final PlayerModel<EminentialEntity> DEFAULT_MODEL = new PlayerModel<>(0F, false);
//    private final PlayerModel<EminentialEntity> SLIM_MODEL = new PlayerModel<>(0F, true);
//
//    public EminentialRenderer(EntityRendererManager manager) {
//        super(manager, new PlayerModel<>(0F, false), 0F);
//        this.addLayer(new BipedArmorLayer<>(this, new BipedModel<>(0.5F), new BipedModel<>(1.0F)));
//        this.addLayer(new HeldItemLayer<>(this));
//        this.addLayer(new ArrowLayer<>(this));
//    }
//
//    @Override
//    public void render(EminentialEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
//        NetworkPlayerInfo info = getPlayerInfo(entityIn.getPlayerID());
//        model = getModelForType(info != null ? info.getModelName() : DefaultPlayerSkin.getSkinModelName(entityIn.getPlayerID()));
//        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
//    }
//
//    @Override
//    public ResourceLocation getTextureLocation(EminentialEntity entity) {
//        NetworkPlayerInfo info = getPlayerInfo(entity.getPlayerID());
//        return info != null ? info.getSkinLocation() : DefaultPlayerSkin.getDefaultSkin(entity.getPlayerID());
//    }
//
//    private PlayerModel<EminentialEntity> getModelForType(String type) {return type.equals("slim") ? SLIM_MODEL : DEFAULT_MODEL;}
//    private static NetworkPlayerInfo getPlayerInfo(UUID id) {
//        ClientPlayNetHandler playNetHandler = Minecraft.getInstance().getConnection();
//        return playNetHandler != null ? playNetHandler.getPlayerInfo(id) : null;
//    }
//}