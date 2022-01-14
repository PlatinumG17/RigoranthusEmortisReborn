package com.platinumg17.rigoranthusemortisreborn.canis.client.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.platinumg17.rigoranthusemortisreborn.api.RigoranthusEmortisRebornAPI;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.feature.CanisLevel;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.feature.EnumMode;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.registry.Skill;
import com.platinumg17.rigoranthusemortisreborn.canis.client.screen.widget.BackButton;
import com.platinumg17.rigoranthusemortisreborn.canis.client.screen.widget.BooleanButton;
import com.platinumg17.rigoranthusemortisreborn.canis.client.screen.widget.ForwardButton;
import com.platinumg17.rigoranthusemortisreborn.canis.client.screen.widget.ModeButton;
import com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.CanisPacketHandler;
import com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.packet.data.*;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.CanisEntity;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.accouterments.CanisAccouterments;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.Resources;
import com.platinumg17.rigoranthusemortisreborn.canis.common.util.REUtil;
import com.platinumg17.rigoranthusemortisreborn.config.Config;
import com.platinumg17.rigoranthusemortisreborn.config.ConfigValues;
import com.platinumg17.rigoranthusemortisreborn.magica.client.gui.NoShadowTextField;
import com.platinumg17.rigoranthusemortisreborn.magica.client.gui.buttons.CanisGuiImageButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.PacketDistributor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @author PlatinumG17
 */
@OnlyIn(Dist.CLIENT)
public class CanisInfoScreen extends CanisBaseBook {

    public final CanisEntity canis;
    public final PlayerEntity player;
    private int currentPage = 0;
    private int maxPages = 1;
    private final List<Widget> skillWidgets;
    private BackButton leftButton;
    private ForwardButton rightButton;
    private final List<Skill> skillList;
    private BooleanButton obeyBtn;
    private BooleanButton attackPlayerBtn;
    private ModeButton modeButton;
    private float xMouse;
    private float yMouse;
    public NoShadowTextField nameTextField;
    public Button displayClothButton;
    public boolean clothButtonToggled;

    public CanisInfoScreen(CanisEntity canis, PlayerEntity player) {
        this.canis = canis;
        this.player = player;
        this.skillList = RigoranthusEmortisRebornAPI.SKILLS
                .getValues()
                .stream()
                .sorted(Comparator.comparing((t) -> I18n.get(t.getTranslationKey())))
                .collect(Collectors.toList());
        this.skillWidgets = new ArrayList<>();
        clothButtonToggled = false;
    }

    public static void open(CanisEntity canis) {
        Minecraft mc = Minecraft.getInstance();
        mc.setScreen(new CanisInfoScreen(canis, mc.player));
    }

    @Override
    public void init() {
        super.init();
        clothButtonToggled = false;
        CanisInfoScreen.this.minecraft.keyboardHandler.setSendRepeatsToGui(true);
        nameTextField = new NoShadowTextField(this.font, bookLeft + 56, bookTop + 5, 200, 20,  new TranslationTextComponent("canisgui.enter_name"));
        if(nameTextField.getValue().isEmpty())
            nameTextField.setSuggestion(new TranslationTextComponent("canisgui.enter_name").getString());
        nameTextField.setResponder(text ->  { CanisPacketHandler.send(PacketDistributor.SERVER.noArg(), new CanisNameData(CanisInfoScreen.this.canis.getId(), text)); });
        nameTextField.setFocus(false);
        nameTextField.setBordered(false);
        nameTextField.setMaxLength(32);
        nameTextField.setTextColor(12694931);
        if (this.canis.hasCustomName()) { nameTextField.setValue(this.canis.getCustomName().getContents()); }
        this.addButton(nameTextField);

        if (this.canis.isOwnedBy(this.player)) {
            obeyBtn = new BooleanButton(this.bookMiddle + 47, this.bookBottom - 48, new StringTextComponent(String.valueOf(this.canis.willObeyOthers())), (btn) -> {
                btn.setMessage(new StringTextComponent(String.valueOf(!this.canis.willObeyOthers())));
                CanisPacketHandler.send(PacketDistributor.SERVER.noArg(), new CanisObeyData(this.canis.getId(), !this.canis.willObeyOthers()));
            });
            this.addButton(obeyBtn);

            attackPlayerBtn = new BooleanButton(this.bookMiddle + 47, this.bookBottom - 83, new StringTextComponent(String.valueOf(this.canis.canPlayersAttack())), button -> {
                button.setMessage(new StringTextComponent(String.valueOf(!this.canis.canPlayersAttack())));
                CanisPacketHandler.send(PacketDistributor.SERVER.noArg(), new FriendlyFireData(this.canis.getId(), !this.canis.canPlayersAttack()));
            });
            this.addButton(attackPlayerBtn);

            displayClothButton = new DisplayClothButton(this.bookLeft + 34, this.bookBottom - 105, new StringTextComponent(String.valueOf(this.canis.doDisplayCloth())), button -> {
                clothButtonToggled = !this.canis.doDisplayCloth();
                button.setMessage(new StringTextComponent(String.valueOf(!this.canis.doDisplayCloth())));
                CanisPacketHandler.send(PacketDistributor.SERVER.noArg(), new CanisSaddleClothData(this.canis.getId(), !this.canis.doDisplayCloth()));
            }) {
                @Override
                public void renderToolTip(MatrixStack stack, int mouseX, int mouseY) {
                    List<ITextComponent> list = new ArrayList<>();
                    if (this.active) { list.add(new StringTextComponent(String.valueOf(CanisInfoScreen.this.canis.doDisplayCloth()))); }
                    CanisInfoScreen.this.renderComponentTooltip(stack, list, mouseX, mouseY);
                }
            };
            this.addButton(displayClothButton);
        }
        modeButton = new ModeButton(this.bookLeft + 44, this.bookBottom - 48, new TranslationTextComponent(this.canis.getMode().getUnlocalisedName()), button -> {
            EnumMode mode = CanisInfoScreen.this.canis.getMode().nextMode();
            if (mode == EnumMode.WANDERING && !CanisInfoScreen.this.canis.getBowlPos().isPresent()) {
                button.setMessage(new TranslationTextComponent(mode.getUnlocalisedName()).withStyle(TextFormatting.RED));
            } else { button.setMessage(new TranslationTextComponent(mode.getUnlocalisedName())); }
            CanisPacketHandler.send(PacketDistributor.SERVER.noArg(), new CanisModeData(CanisInfoScreen.this.canis.getId(), mode));
        }) {
            @Override
            public void renderToolTip(MatrixStack stack, int mouseX, int mouseY) {
                List<ITextComponent> list = new ArrayList<>();
                String str = I18n.get(canis.getMode().getUnlocalisedInfo());
                list.addAll(ScreenUtil.splitInto(str, 150, CanisInfoScreen.this.font));
                if (CanisInfoScreen.this.canis.getMode() == EnumMode.WANDERING) {
                    if (CanisInfoScreen.this.canis.getBowlPos().isPresent()) {
                        double distance = CanisInfoScreen.this.canis.blockPosition().distSqr(CanisInfoScreen.this.canis.getBowlPos().get());
                        if (distance > 256D) { list.add(new TranslationTextComponent("canis.mode.docile.distance", (int) Math.sqrt(distance)).withStyle(TextFormatting.RED)); }
                        else { list.add(new TranslationTextComponent("canis.mode.docile.bowl", (int) Math.sqrt(distance)).withStyle(TextFormatting.GREEN)); }
                    } else { list.add(new TranslationTextComponent("canis.mode.docile.nobowl").withStyle(TextFormatting.RED)); }
                }
                CanisInfoScreen.this.renderComponentTooltip(stack, list, mouseX, mouseY);
            }
        };
        this.addButton(modeButton);
        // Skill level-up buttons
        int size = RigoranthusEmortisRebornAPI.SKILLS.getKeys().size();
        int perPage = Math.max(MathHelper.floor((this.height - 10) / (double) 20) - 2, 1);
        this.currentPage = 0;
        this.recalculatePage(perPage);
        if (perPage < size) {
            this.leftButton = new BackButton(bookLeft - 48, perPage * 20 + 10, ITextComponent.nullToEmpty(""), (btn) -> {
                this.currentPage = Math.max(0, this.currentPage - 1);
                btn.active = this.currentPage > 0;
                this.rightButton.active = true;
                this.recalculatePage(perPage);
            }) { @Override public void renderToolTip(MatrixStack stack, int mouseX, int mouseY) { CanisInfoScreen.this.renderTooltip(stack, new TranslationTextComponent("canisgui.prevpage").withStyle(TextFormatting.ITALIC), mouseX, mouseY); }};
            this.leftButton.active = false;
            this.rightButton = new ForwardButton(bookLeft - 25, perPage * 20 + 10, ITextComponent.nullToEmpty(""), (btn) -> {
                this.currentPage = Math.min(this.maxPages - 1, this.currentPage + 1);
                btn.active = this.currentPage < this.maxPages - 1;
                this.leftButton.active = true;
                this.recalculatePage(perPage);
                this.rightButton.visible = this.currentPage < this.maxPages;
            }) { @Override public void renderToolTip(MatrixStack stack, int mouseX, int mouseY) { CanisInfoScreen.this.renderTooltip(stack, new TranslationTextComponent("canisgui.nextpage").withStyle(TextFormatting.ITALIC), mouseX, mouseY); }};
            this.addButton(this.leftButton);
            this.addButton(this.rightButton);
        }
        this.addButton(new CanisGuiImageButton(bookRight - 68, bookBottom - 7, 0,0,41, 12, 41, 12, "textures/gui/clear_icon.png", (e) -> {Minecraft.getInstance().setScreen(null);}));
    }

    /**
    * @author ProPerciliv*/
    private void recalculatePage(int perPage) {
        GlStateManager._pushMatrix();
        if(scaleFactor != 1) {
            GlStateManager._scalef(scaleFactor, scaleFactor, scaleFactor);
            xMouse /= scaleFactor;
            yMouse /= scaleFactor;
        }
        this.skillWidgets.forEach(this::removeWidget);
        this.skillWidgets.clear();
        this.maxPages = MathHelper.ceil(this.skillList.size() / (double) perPage);
        for (int i = 0; i < perPage; ++i) {
            int index = this.currentPage * perPage + i;
            if (index >= this.skillList.size()) break;
            Skill skill = this.skillList.get(index);
            Button skillButton = new SkillButton(this.bookLeft - 135, 10 + i * 20, ITextComponent.nullToEmpty(""), skill, (btn) -> {
                int level = CanisInfoScreen.this.canis.getLevel(skill);
                if (level < skill.getMaxLevel() && CanisInfoScreen.this.canis.canSpendPoints(skill.getLevelCost(level + 1))) {
                    CanisPacketHandler.send(PacketDistributor.SERVER.noArg(), new CanisSkillData(CanisInfoScreen.this.canis.getId(), skill));
                }}) {
                @Override
                public void renderToolTip(MatrixStack stack, int mouseX, int mouseY) {
                    List<ITextComponent> list = new ArrayList<>();
                    list.add(new TranslationTextComponent(skill.getTranslationKey()).withStyle(TextFormatting.GREEN));
                    if (this.active) {
                        list.add(new StringTextComponent("Level: " + CanisInfoScreen.this.canis.getLevel(skill)));
                        list.add(new StringTextComponent("--------------------------------").withStyle(TextFormatting.GRAY));
                        list.addAll(ScreenUtil.splitInto(I18n.get(skill.getInfoTranslationKey()), 200, CanisInfoScreen.this.font));
                    } else { list.add(new StringTextComponent("Skill disabled").withStyle(TextFormatting.RED)); }
                    CanisInfoScreen.this.renderComponentTooltip(stack, list, mouseX, mouseY);
                }
            };
            skillButton.active = !ConfigValues.DISABLED_SKILLS.contains(skill);
            this.skillWidgets.add(skillButton);
            this.addButton(skillButton);
        }
        GlStateManager._popMatrix();
    }

    @OnlyIn(Dist.CLIENT)
    protected void renderBg(MatrixStack matrixStack, float partialTicks, int xMouse, int yMouse) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        InventoryScreen.renderEntityInInventory(bookLeft + 76, bookTop + 127, 17, (float)(bookLeft + 30) - this.xMouse, (float)(bookTop + 175 - 150) - this.yMouse, this.canis);
    }

    @Override
    public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(stack);
        this.xMouse = (float)mouseX;
        this.yMouse = (float)mouseY;
//        String health = REUtil.format1DP(this.canis.getHealth());//        String healthMax = REUtil.format1DP(this.canis.getMaxHealth());//        String ageValue = REUtil.format2DP(this.canis.getAge());//        String ageRel = I18n.get(this.canis.isBaby() ? "canisgui.age.baby" : "canisgui.age.adult");//        String ageString = ageValue + " " + ageRel;//        this.font.draw(stack, I18n.get("canisgui.levelhomini") + " " + this.canis.getLevel().getLevel(CanisLevel.Type.HOMINI), topX, topY + 75, 0xFF10F9);
        IFormattableTextComponent modeButtonData = this.modeButton.getMessage().copy();
        IFormattableTextComponent attackBoolean = this.attackPlayerBtn.getMessage().copy();
        IFormattableTextComponent obeyBoolean = this.obeyBtn.getMessage().copy();

        super.render(stack, mouseX, mouseY, partialTicks);
        this.renderBg(stack, partialTicks, mouseX, mouseY);
        drawCenteredString(stack, this.font, modeButtonData, modeButton.x + 32, modeButton.y + 6, 43520);
        drawCenteredString(stack, this.font, attackBoolean, attackPlayerBtn.x + 20, attackPlayerBtn.y + 6,  43520);
        drawCenteredString(stack, this.font, obeyBoolean, obeyBtn.x + 20, obeyBtn.y + 6,  43520);
        this.buttons.forEach(widget -> {
            if (widget instanceof SkillButton) {
                SkillButton sklBTN = (SkillButton)widget;
                if (CanisInfoScreen.this.canis.getSkill(sklBTN.skill).isPresent()) {
                    if (CanisInfoScreen.this.canis.getSkill(sklBTN.skill).get().level() >= 5) {
                        this.font.draw(stack, I18n.get(sklBTN.skill.getTranslationKey()), sklBTN.x + 28, sklBTN.y + 6, 11184810);
                    } else { this.font.draw(stack, I18n.get(sklBTN.skill.getTranslationKey()), sklBTN.x + 28, sklBTN.y + 6, 11141290); }
                } else { this.font.draw(stack, I18n.get(sklBTN.skill.getTranslationKey()), sklBTN.x + 28, sklBTN.y + 6, 11141290); }
            }
        });
        nameTextField.setSuggestion(nameTextField.getValue().isEmpty() ? new TranslationTextComponent("canisgui.enter_name").getString() : "");
        for (Widget widget : this.buttons) {
            if (widget.isHovered()) {
                widget.renderToolTip(stack, mouseX, mouseY);
                break;
            }
        }
    }
    @Override public void removed() { super.removed();this.minecraft.keyboardHandler.setSendRepeatsToGui(false); }
    @Override public boolean isPauseScreen() { return false; }
    protected <T extends Widget> T removeWidget(T widgetIn) {
        this.buttons.remove(widgetIn);
        this.children.remove(widgetIn);
        return widgetIn;
    }

    @Override
    public void drawBackgroundElements(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        super.drawBackgroundElements(stack, mouseX, mouseY, partialTicks);
        String tamedString = "";
        if (this.canis.isTame()) {
            if (this.canis.isOwnedBy(this.player)) { tamedString = I18n.get("canisgui.owner.you"); }
            else if (this.canis.getOwnersName().isPresent()) { tamedString = this.canis.getOwnersName().get().getString(); }
        }
        String ownersName = "";
        if (this.canis.isTame()) {
            if (this.canis.getOwnersName().isPresent()) { ownersName = this.canis.getOwnersName().get().getString(); }
        }
        String speedValue = REUtil.format2DP(this.canis.getAttribute(Attributes.MOVEMENT_SPEED).getValue());
        String armorValue = REUtil.format2DP(this.canis.getAttribute(Attributes.ARMOR).getValue());
        drawFromTexture(new ResourceLocation(EmortisConstants.MOD_ID, "textures/gui/create_paper.png"), 216, 181, 0, 0, 56, 15,56,15, stack);

        this.font.draw(stack, I18n.get("canisgui.speed") + "     " + speedValue, 177, 40, 0xFFFFFF);
        this.font.draw(stack, I18n.get("canisgui.owner") + "     " + tamedString, 177, 52, 0xFFFFFF);
        this.font.draw(stack, I18n.get("canisgui.armor") + "     " + armorValue, 177, 64, 0xFFFFFF);

        if (Config.CANIS_GENDER.get()) { this.font.draw(stack, I18n.get("canisgui.gender") + I18n.get(this.canis.getGender().getUnlocalisedName()), 177, 76, 0xFFFFFF);}
        minecraft.font.draw(stack, I18n.get("canisgui.friendlyfire"), 181, 97, 0x808080);
        if (this.canis.isOwnedBy(this.player)) { minecraft.font.draw(stack, I18n.get("canisgui.obeyothers"), 182, 132, 0x808080); }

        this.font.draw(stack, TextFormatting.RED + I18n.get("canisgui.level") + TextFormatting.ITALIC + TextFormatting.DARK_PURPLE + this.canis.getLevel().getLevel(CanisLevel.Type.CHORDATA), 21, 58, 0xFF10F9);
        //        this.font.draw(stack, I18n.get("canisgui.levelhomini") + " " + this.canis.getLevel().getLevel(CanisLevel.Type.HOMINI), topX, topY + 75, 0xFF10F9);
        if (this.canis.getAccoutrement(CanisAccouterments.GOLDEN_COLLAR.get()).isPresent()) { minecraft.font.draw(stack, I18n.get("canisgui.unlimited"), 21, 68, 0x808080);
        } else if (this.canis.getSpendablePoints() >= 5) { minecraft.font.draw(stack, I18n.get("canisgui.pointsleft.high") + this.canis.getSpendablePoints(), 21, 68, 0x808080);
        } else if (this.canis.getSpendablePoints() >= 1) { minecraft.font.draw(stack, I18n.get("canisgui.pointsleft.medium") + this.canis.getSpendablePoints(), 21, 68, 0x808080);
        } else { minecraft.font.draw(stack, I18n.get("canisgui.pointsleft.low") + this.canis.getSpendablePoints(), 21, 68, 0x808080); }
        minecraft.font.draw(stack, ownersName + I18n.get("canisgui.book_title1"), 21, 32, 0x808080);
        minecraft.font.draw(stack, I18n.get("canisgui.book_title2"), 21, 42, 0x808080);
        minecraft.font.draw(stack, new TranslationTextComponent("canisgui.close"), 237, 186, -8355712);
    }

    private class SkillButton extends Button {
        protected Skill skill;
        private SkillButton(int x, int y, ITextComponent buttonText, Skill skill, Consumer<SkillButton> onPress) {
            super(x, y, 130, 19, buttonText, button -> onPress.accept((SkillButton) button));
            this.skill = skill;
        }
        @Override
        public void renderButton(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
            Minecraft mc = Minecraft.getInstance();
            mc.getTextureManager().bind(Resources.SKILL_BUTTON);
            RenderSystem.color4f(1.0F, 1.0F, 1.0F, this.alpha);
            int i = this.getYImage(this.isHovered());
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            if (CanisInfoScreen.this.canis.getSkill(this.skill).isPresent()) {
                if (CanisInfoScreen.this.canis.getSkill(this.skill).get().level() >= 5) {
                    this.blit(stack, this.x, this.y, 0, 0, this.width, this.height);
                } else { this.blit(stack, this.x, this.y, 0, 38 + (i * 19), this.width, this.height); }
            } else { this.blit(stack, this.x, this.y, 0, i * 19, this.width, this.height); }
            this.renderBg(stack, mc, mouseX, mouseY);
        }
    }

    public class DisplayClothButton extends Button {
        public DisplayClothButton(int x, int y, ITextComponent text, IPressable onPress) {
            super(x, y, 14, 14, text, onPress);
        }
        @Override
        public void renderButton(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
            Minecraft mc = Minecraft.getInstance();
            mc.getTextureManager().bind(Resources.SMALL_WIDGETS);
            RenderSystem.color4f(1.0F, 1.0F, 1.0F, this.alpha);
            int i = this.getYImage(this.isHovered());
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            if (CanisInfoScreen.this.clothButtonToggled) { this.blit(stack, this.x, this.y, 48, 0, this.width, this.height); }
                else { this.blit(stack, this.x, this.y, 48, i * 14, this.width, this.height); }
            this.renderBg(stack, mc, mouseX, mouseY);
        }
    }
    public static TranslationTextComponent langDesc = new TranslationTextComponent("rigoranthusemortisreborn.canis_desc");
}