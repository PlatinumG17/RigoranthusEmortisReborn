package com.platinumg17.rigoranthusemortisreborn.canis.client.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.platinumg17.rigoranthusemortisreborn.api.RigoranthusEmortisRebornAPI;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.feature.CanisLevel;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.feature.EnumMode;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.registry.Skill;
import com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.CanisPacketHandler;
import com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.packet.data.*;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.CanisEntity;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.accouterments.CanisAccouterments;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.canis.common.util.REUtil;
import com.platinumg17.rigoranthusemortisreborn.config.Config;
import com.platinumg17.rigoranthusemortisreborn.config.ConfigValues;
import com.platinumg17.rigoranthusemortisreborn.magica.client.gui.NoShadowTextField;
import com.platinumg17.rigoranthusemortisreborn.magica.client.gui.buttons.CanisGuiImageButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.button.ChangePageButton;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.network.PacketDistributor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @author PlatinumG17 edit of ProPerciliv
 */
public class CanisInfoScreen extends CanisBaseBook {
    public final CanisEntity canis;
    public final PlayerEntity player;
    private int currentPage = 0;
    private int maxPages = 1;
    private List<Widget> skillWidgets = new ArrayList<>(16);
    private Widget iconWidget;
    private CanisGuiImageButton leftBtn, rightBtn;
    private List<Skill> skillList;

    private float xMouse;
    private float yMouse;

    public List<SkillButton> skillButtons;
    public int page = 0;
    ChangePageButton nextButton;
    ChangePageButton previousButton;
    public NoShadowTextField nameTextField;

    public CanisInfoScreen(CanisEntity canis, PlayerEntity player) {
        this.canis = canis;
        this.player = player;
        this.skillList = RigoranthusEmortisRebornAPI.SKILLS
                .getValues()
                .stream()
                .sorted(Comparator.comparing((t) -> I18n.get(t.getTranslationKey())))
                .collect(Collectors.toList());
    }

    public static void open(CanisEntity canis) {
        Minecraft mc = Minecraft.getInstance();
        mc.setScreen(new CanisInfoScreen(canis, mc.player));
    }

    @Override
    public void init() {
        super.init();
//        layoutParts();
        this.minecraft.keyboardHandler.setSendRepeatsToGui(true);
        nameTextField = new NoShadowTextField(this.font, bookLeft + 56, bookTop + 5, 200, 20,  new TranslationTextComponent("canisgui.enter_name"));
        if(nameTextField.getValue().isEmpty())
            nameTextField.setSuggestion(new TranslationTextComponent("canisgui.enter_name").getString());
        nameTextField.setResponder(text ->  {
            CanisPacketHandler.send(PacketDistributor.SERVER.noArg(), new CanisNameData(CanisInfoScreen.this.canis.getId(), text));
        });
        nameTextField.setFocus(false);
        nameTextField.setBordered(false);
        nameTextField.setMaxLength(32);
        nameTextField.setTextColor(12694931);

        if (this.canis.hasCustomName()) {
            nameTextField.setValue(this.canis.getCustomName().getContents());
        }
        this.addButton(nameTextField);

        if (this.canis.isOwnedBy(this.player)) {
            Button obeyBtn = new Button(this.bookMiddle + 46, this.bookBottom - 48, 42, 20, new StringTextComponent(String.valueOf(this.canis.willObeyOthers())), (btn) -> {
                btn.setMessage(new StringTextComponent(String.valueOf(!this.canis.willObeyOthers())));
                CanisPacketHandler.send(PacketDistributor.SERVER.noArg(), new CanisObeyData(this.canis.getId(), !this.canis.willObeyOthers()));
            });
            this.addButton(obeyBtn);
        }
        Button attackPlayerBtn = new Button(this.bookMiddle + 46, this.bookBottom - 83, 42, 20, new StringTextComponent(String.valueOf(this.canis.canPlayersAttack())), button -> {
            button.setMessage(new StringTextComponent(String.valueOf(!this.canis.canPlayersAttack())));
            CanisPacketHandler.send(PacketDistributor.SERVER.noArg(), new FriendlyFireData(this.canis.getId(), !this.canis.canPlayersAttack()));
        }); // this button is  HIGHER  remember that
        this.addButton(attackPlayerBtn);

        Button modeBtn = new Button(this.bookLeft + 46, this.bookBottom - 48, 60, 20, new TranslationTextComponent(this.canis.getMode().getUnlocalisedName()), button -> {
            EnumMode mode = CanisInfoScreen.this.canis.getMode().nextMode();
            if (mode == EnumMode.WANDERING && !CanisInfoScreen.this.canis.getBowlPos().isPresent()) {
                button.setMessage(new TranslationTextComponent(mode.getUnlocalisedName()).withStyle(TextFormatting.RED));
            } else {
                button.setMessage(new TranslationTextComponent(mode.getUnlocalisedName()));
            }
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
                        if (distance > 256D) {
                            list.add(new TranslationTextComponent("canis.mode.docile.distance", (int) Math.sqrt(distance)).withStyle(TextFormatting.RED));
                        } else {
                            list.add(new TranslationTextComponent("canis.mode.docile.bowl", (int) Math.sqrt(distance)).withStyle(TextFormatting.GREEN));
                        }
                    } else {
                        list.add(new TranslationTextComponent("canis.mode.docile.nobowl").withStyle(TextFormatting.RED));
                    }
                }
                CanisInfoScreen.this.renderComponentTooltip(stack, list, mouseX, mouseY);
            }
        };
        this.addButton(modeBtn);
        // Skill level-up buttons
        int size = RigoranthusEmortisRebornAPI.SKILLS.getKeys().size();
        int perPage = Math.max(MathHelper.floor((this.height - 10) / (double) 21) - 2, 1);
        this.currentPage = 0;
        this.recalculatePage(perPage);
        if (perPage < size) {
            this.leftBtn = new CanisGuiImageButton(25, perPage * 21 + 10, 33, 6, 20, 20, 256, 256, "textures/gui/page_selection.png", (btn) -> {
                this.currentPage = Math.max(0, this.currentPage - 1);
                btn.active = this.currentPage > 0;
                this.rightBtn.active = true;
                this.recalculatePage(perPage);
            }) {
//                @Override
//                public void renderToolTip(MatrixStack stack, int mouseX, int mouseY) {
//                    CanisInfoScreen.this.renderTooltip(stack, new TranslationTextComponent("canisgui.prevpage").withStyle(TextFormatting.ITALIC), mouseX, mouseY);
//                }
            }.withTooltip(this, new TranslationTextComponent("canisgui.prevpage"));
            this.leftBtn.active = false;
            this.rightBtn = new CanisGuiImageButton(48, perPage * 21 + 10, 12, 6, 20, 20, 256, 256, "textures/gui/page_selection.png", (btn) -> {
                this.currentPage = Math.min(this.maxPages - 1, this.currentPage + 1);
                btn.active = this.currentPage < this.maxPages - 1;

                this.leftBtn.active = true;
                this.recalculatePage(perPage);
                this.rightBtn.visible = this.currentPage < this.maxPages;})
            {
//                @Override
//                public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
//                    this.isHovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
//                }
            }.withTooltip(this, new TranslationTextComponent("canisgui.nextpage"));
            this.addButton(this.leftBtn);
            this.addButton(this.rightBtn);
        }
        this.addButton(new CanisGuiImageButton(bookRight - 68, bookBottom - 7, 0,0,41, 12, 41, 12, "textures/gui/clear_icon.png", (e) -> {Minecraft.getInstance().setScreen(null);}));
    }

    private void recalculatePage(int perPage) {
        this.skillWidgets.forEach(this::removeWidget);
        this.skillWidgets.clear();
        this.maxPages = MathHelper.ceil(this.skillList.size() / (double) perPage);
        for (int i = 0; i < perPage; ++i) {
            int index = this.currentPage * perPage + i;
            if (index >= this.skillList.size()) break;
            Skill skill = this.skillList.get(index);
            Button button = new SkillButton(25, 10 + i * 21, 20, 20, new StringTextComponent("+"), skill, (btn) -> {
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
                    } else {
                        list.add(new StringTextComponent("Skill disabled").withStyle(TextFormatting.RED));
                    }
                    CanisInfoScreen.this.renderComponentTooltip(stack, list, mouseX, mouseY);
                }
            };
            button.active = !ConfigValues.DISABLED_SKILLS.contains(skill);
            this.skillWidgets.add(button);
            this.addButton(button);
        }
    }

    protected void renderBg(MatrixStack matrixStack, float partialTicks, int xMouse, int yMouse) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bind(background);
        InventoryScreen.renderEntityInInventory(bookLeft + 73, bookTop + 133, 17, (float)(bookLeft + 30) - this.xMouse, (float)(bookTop + 175 - 150) - this.yMouse, this.canis);
    }
    @Override
    public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        //Background
        int xStart = bookLeft + 20;
        int yStart = bookTop + 34;
        int xOffset = 20;
        int yOffset = 18;
        int topX = this.width / 2;
        int topY = this.height / 2;
        this.renderBackground(stack);
        this.xMouse = (float)mouseX;
        this.yMouse = (float)mouseY;
        // Background
//        String health = REUtil.format1DP(this.canis.getHealth());//        String healthMax = REUtil.format1DP(this.canis.getMaxHealth());//        String ageValue = REUtil.format2DP(this.canis.getAge());//        String ageRel = I18n.get(this.canis.isBaby() ? "canisgui.age.baby" : "canisgui.age.adult");//        String ageString = ageValue + " " + ageRel;
//        this.font.draw(stack, I18n.get("canisgui.levelhomini") + " " + this.canis.getLevel().getLevel(CanisLevel.Type.HOMINI), topX, topY + 75, 0xFF10F9);
        this.buttons.forEach(widget -> {
            if (widget instanceof SkillButton) {
                SkillButton sklBTN = (SkillButton)widget;
                this.font.draw(stack, I18n.get(sklBTN.skill.getTranslationKey()), sklBTN.x + 25, sklBTN.y + 7, 0x808080);
            }
        });
        super.render(stack, mouseX, mouseY, partialTicks);
        this.renderBg(stack, partialTicks, mouseX, mouseY);
        nameTextField.setSuggestion(nameTextField.getValue().isEmpty() ? new TranslationTextComponent("canisgui.enter_name").getString() : "");
        for (Widget widget : this.buttons) {
            if (widget.isHovered()) {
                widget.renderToolTip(stack, mouseX, mouseY);
                break;
            }
        }
    }
    @Override public void removed() {super.removed();this.minecraft.keyboardHandler.setSendRepeatsToGui(false);}
    @Override public boolean isPauseScreen() {return false;}
    protected <T extends Widget> T removeWidget(T widgetIn) {
        this.buttons.remove(widgetIn);
        this.children.remove(widgetIn);
        return widgetIn;
    }

    @Override
    public void drawBackgroundElements(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        super.drawBackgroundElements(stack, mouseX, mouseY, partialTicks);
        String ownersName = this.canis.getOwnersName().get().getString();
        String speedValue = REUtil.format2DP(this.canis.getAttribute(Attributes.MOVEMENT_SPEED).getValue());
        String armorValue = REUtil.format2DP(this.canis.getAttribute(Attributes.ARMOR).getValue());
        drawFromTexture(new ResourceLocation(EmortisConstants.MOD_ID, "textures/gui/create_paper.png"), 216, 181, 0, 0, 56, 15,56,15, stack);

        this.font.draw(stack, I18n.get("canisgui.speed") + "     " + speedValue, 177, 40, 0xFFFFFF);
        String tamedString = "";
        if (this.canis.isTame()) {
            if (this.canis.isOwnedBy(this.player)) {
                tamedString = I18n.get("canisgui.owner.you");
            } else if (this.canis.getOwnersName().isPresent()) {
                tamedString = this.canis.getOwnersName().get().getString();
            }
        }
        this.font.draw(stack, I18n.get("canisgui.owner") + "     " + tamedString, 177, 52, 0xFFFFFF);
        this.font.draw(stack, I18n.get("canisgui.armor") + "     " + armorValue, 177, 64, 0xFFFFFF);

        if (Config.CANIS_GENDER.get() == true) {
            this.font.draw(stack, I18n.get("canisgui.gender") + " " + I18n.get(this.canis.getGender().getUnlocalisedName()), 177, 76, 0xFFFFFF);}
        minecraft.font.draw(stack, I18n.get("canisgui.friendlyfire"), 181, 97, 0x808080);
        if (this.canis.isOwnedBy(this.player)) {
            minecraft.font.draw(stack, I18n.get("canisgui.obeyothers"), 182, 132, 0x808080);
        }

        this.font.draw(stack, TextFormatting.RED + I18n.get("canisgui.level") + TextFormatting.ITALIC + TextFormatting.DARK_PURPLE + this.canis.getLevel().getLevel(CanisLevel.Type.CHORDATA), 21, 58, 0xFF10F9);
        //        this.font.draw(stack, I18n.get("canisgui.levelhomini") + " " + this.canis.getLevel().getLevel(CanisLevel.Type.HOMINI), topX, topY + 75, 0xFF10F9);
        if (this.canis.getAccoutrement(CanisAccouterments.GOLDEN_COLLAR.get()).isPresent()) {
            minecraft.font.draw(stack, I18n.get("canisgui.unlimited"), 21, 68, 0x808080);
        } else if (this.canis.getSpendablePoints() >= 5) {
            minecraft.font.draw(stack, I18n.get("canisgui.pointsleft.high") + this.canis.getSpendablePoints(), 21, 68, 0x808080);
        } else if (this.canis.getSpendablePoints() >= 1) {
            minecraft.font.draw(stack, I18n.get("canisgui.pointsleft.medium") + this.canis.getSpendablePoints(), 21, 68, 0x808080);
        } else {
            minecraft.font.draw(stack, I18n.get("canisgui.pointsleft.low") + this.canis.getSpendablePoints(), 21, 68, 0x808080);
        }

        minecraft.font.draw(stack, ownersName + I18n.get("canisgui.book_title1"), 21, 32, 0x808080);
        minecraft.font.draw(stack, I18n.get("canisgui.book_title2"), 21, 42, 0x808080);
        minecraft.font.draw(stack, new TranslationTextComponent("canisgui.close"), 237, 186, -8355712);
    }

    private static class SkillButton extends Button {
        protected Skill skill;
        private SkillButton(int x, int y, int widthIn, int heightIn, ITextComponent buttonText, Skill skill, Consumer<SkillButton> onPress) {
            super(x, y, widthIn, heightIn, buttonText, button -> onPress.accept((SkillButton) button));
            this.skill = skill;
        }
    }
    public static TranslationTextComponent langDesc = new TranslationTextComponent("rigoranthusemortisreborn.canis_desc");
    public static TranslationTextComponent langName = new TranslationTextComponent("entity.rigoranthusemortisreborn.canis_chordata");

//    private static class CanisIconButton extends Button {
//        protected CanisEntity canis;
//        private CanisIconButton(int x, int y, ITextComponent buttonText, CanisEntity canis, Consumer<CanisIconButton> onPress) {
//            super(x, y,16,16, buttonText, button -> onPress.accept((CanisIconButton) button));
//            this.x = x;
//            this.y = y;
//            this.width = 16;
//            this.height = 16;
//            this.canis = canis;
//        }
//    }
}