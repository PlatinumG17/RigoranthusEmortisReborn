package com.platinumg17.rigoranthusemortisreborn.canis.client.event;

import java.util.Map;

import org.lwjgl.opengl.GL11;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.recipebook.RecipeBookGui;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.inventory.CreativeScreen;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.renderer.BlockModelShapes;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.model.BlockModel;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.client.renderer.model.ModelRotation;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.client.event.GuiContainerEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.registries.ForgeRegistries;
import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
import com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.CanisPacketHandler;
import com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.packet.data.OpenCanisScreenData;
import com.platinumg17.rigoranthusemortisreborn.canis.CanisBlocks;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.CanisEntity;
import com.platinumg17.rigoranthusemortisreborn.canis.client.block.model.CanisBedModel;
import com.platinumg17.rigoranthusemortisreborn.canis.client.screen.widget.CanisInventoryButton;

public class ClientEventHandler {

    public static void onModelBakeEvent(final ModelBakeEvent event) {
        Map<ResourceLocation, IBakedModel> modelRegistry = event.getModelRegistry();
        try {
            ResourceLocation resourceLocation = ForgeRegistries.BLOCKS.getKey(CanisBlocks.CANIS_BED.get());
            ResourceLocation unbakedModelLoc = new ResourceLocation(resourceLocation.getNamespace(), "block/" + resourceLocation.getPath());
            BlockModel model = (BlockModel) event.getModelLoader().getModel(unbakedModelLoc);
            IBakedModel customModel = new CanisBedModel(event.getModelLoader(), model, model.bake(event.getModelLoader(), model, ModelLoader.defaultTextureGetter(), ModelRotation.X180_Y180, unbakedModelLoc, true));
            CanisBlocks.CANIS_BED.get().getStateDefinition().getPossibleStates().forEach(state -> {modelRegistry.put(BlockModelShapes.stateToModelLocation(state), customModel);});
            modelRegistry.put(new ModelResourceLocation(resourceLocation, "inventory"), customModel);
        }
        catch(Exception e) {
            RigoranthusEmortisReborn.LOGGER.warn("Could not get base Canis Bed model. Reverting to default textures...");
            e.printStackTrace();
        }
    }

    @SubscribeEvent
    public void onInputEvent(final InputUpdateEvent event) {
        if (event.getMovementInput().jumping) {
            Entity entity = event.getPlayer().getVehicle();
            if (event.getPlayer().isPassenger() && entity instanceof CanisEntity) {
                CanisEntity canis = (CanisEntity) entity;
                if (canis.canJump()) {
                    canis.setJumpPower(100);
                }
            }
        }
    }

    @SubscribeEvent
    public void onScreenInit(final GuiScreenEvent.InitGuiEvent.Post event) {
        Screen screen = event.getGui();
        if (screen instanceof InventoryScreen || screen instanceof CreativeScreen) {
            boolean creative = screen instanceof CreativeScreen;
            Minecraft mc = Minecraft.getInstance();
            int width = mc.getWindow().getGuiScaledWidth();
            int height = mc.getWindow().getGuiScaledHeight();
            int sizeX = creative ? 195 : 176;
            int sizeY = creative ? 136 : 166;
            int guiLeft = (width - sizeX) / 2;
            int guiTop = (height - sizeY) / 2;
            int x = guiLeft + (creative ? 36 : sizeX / 2 - 10);
            int y = guiTop + (creative ? 7 : 48);
            event.addWidget(new CanisInventoryButton(x, y, screen, (btn) -> {
                CanisPacketHandler.send(PacketDistributor.SERVER.noArg(), new OpenCanisScreenData());
                btn.active = false;
            }));
        }
    }

    @SubscribeEvent
    public void onScreenDrawForeground(final GuiContainerEvent.DrawForeground event) {
        Screen screen = event.getGuiContainer();
        if (screen instanceof InventoryScreen || screen instanceof CreativeScreen) {
            boolean creative = screen instanceof CreativeScreen;
            CanisInventoryButton btn = null;
            //TODO just create a static variable in this class
            for (Widget widget : screen.buttons) {
                if (widget instanceof CanisInventoryButton) {
                    btn = (CanisInventoryButton) widget;
                    break;
                }
            }
            if (btn.visible && btn.isHovered()) {
                Minecraft mc = Minecraft.getInstance();
                int width = mc.getWindow().getGuiScaledWidth();
                int height = mc.getWindow().getGuiScaledHeight();
                int sizeX = creative ? 195 : 176;
                int sizeY = creative ? 136 : 166;
                int guiLeft = (width - sizeX) / 2;
                int guiTop = (height - sizeY) / 2;
                if (!creative) {
                    RecipeBookGui recipeBook = ((InventoryScreen) screen).getRecipeBookComponent();
                    if (recipeBook.isVisible()) {
                        guiLeft += 76;
                    }
                }
                RenderSystem.translated(-guiLeft, -guiTop, 0);
                btn.renderToolTip(event.getMatrixStack(), event.getMouseX(), event.getMouseY());
                RenderSystem.translated(guiLeft, guiTop, 0);
            }
        }
    }

    public void drawSelectionBox(MatrixStack matrixStackIn, PlayerEntity player, float particleTicks, AxisAlignedBB boundingBox) {
        RenderSystem.disableAlphaTest();
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        RenderSystem.color4f(0.0F, 0.0F, 0.0F, 0.7F);
        //TODO Used when drawing outline of bounding box
        RenderSystem.lineWidth(2.0F);
        RenderSystem.disableTexture();
        Vector3d vec3d = Minecraft.getInstance().gameRenderer.getMainCamera().getPosition();
        double d0 = vec3d.x();
        double d1 = vec3d.y();
        double d2 = vec3d.z();
        BufferBuilder buf = Tessellator.getInstance().getBuilder();
        buf.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION_COLOR);
        WorldRenderer.renderLineBox(matrixStackIn, buf, boundingBox.move(-d0, -d1, -d2), 1F, 1F, 0F, 0.8F);
        Tessellator.getInstance().end();
        RenderSystem.color4f(0.0F, 0.0F, 0.0F, 0.3F);
        RenderSystem.depthMask(true);
        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
        RenderSystem.enableAlphaTest();
    }

    @SubscribeEvent
    public void onPreRenderGameOverlay(final RenderGameOverlayEvent.Post event) {
        label: if (event.getType() == RenderGameOverlayEvent.ElementType.HEALTHMOUNT) {
            Minecraft mc = Minecraft.getInstance();
            if (mc.player == null || !(mc.player.getVehicle() instanceof CanisEntity)) {break label;}
            MatrixStack stack = event.getMatrixStack();

            CanisEntity canis = (CanisEntity) mc.player.getVehicle();
            int width = mc.getWindow().getGuiScaledWidth();
            int height = mc.getWindow().getGuiScaledHeight();
            RenderSystem.pushMatrix();
            mc.getTextureManager().bind(Screen.GUI_ICONS_LOCATION);
            RenderSystem.enableBlend();
            int left = width / 2 + 91;
            int top = height - ForgeIngameGui.right_height;
            ForgeIngameGui.right_height += 10;
            int level = MathHelper.ceil((canis.getCanisHunger() / canis.getMaxHunger()) * 20.0D);
            for (int i = 0; i < 10; ++i) {
                int idx = i * 2 + 1;
                int x = left - i * 8 - 9;
                int y = top;
                int icon = 16;
                byte backgound = 12;
                mc.gui.blit(stack, x, y, 16 + backgound * 9, 27, 9, 9);
                if (idx < level) {
                    mc.gui.blit(stack, x, y, icon + 36, 27, 9, 9);
                } else if (idx == level) {
                    mc.gui.blit(stack, x, y, icon + 45, 27, 9, 9);
                }
            }
            RenderSystem.disableBlend();
            RenderSystem.enableBlend();
            left = width / 2 + 91;
            top = height - ForgeIngameGui.right_height;
            RenderSystem.color4f(1.0F, 1.0F, 0.0F, 1.0F);
            int l6 = canis.getAirSupply();
            int j7 = canis.getMaxAirSupply();
            if (canis.isEyeInFluid(FluidTags.WATER) || l6 < j7) {
                int air = canis.getAirSupply();
                int full = MathHelper.ceil((air - 2) * 10.0D / 300.0D);
                int partial = MathHelper.ceil(air * 10.0D / 300.0D) - full;
                for (int i = 0; i < full + partial; ++i) {
                    mc.gui.blit(stack, left - i * 8 - 9, top, (i < full ? 16 : 25), 18, 9, 9);
                }
                ForgeIngameGui.right_height += 10;
            }
            RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.disableBlend();
            RenderSystem.popMatrix();
        }
    }
}