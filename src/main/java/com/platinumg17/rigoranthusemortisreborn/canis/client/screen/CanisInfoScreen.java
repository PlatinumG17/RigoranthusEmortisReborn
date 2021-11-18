package com.platinumg17.rigoranthusemortisreborn.canis.client.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.platinumg17.rigoranthusemortisreborn.api.RigoranthusEmortisRebornAPI;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.feature.CanisLevel;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.feature.EnumMode;
import com.platinumg17.rigoranthusemortisreborn.api.apicanis.registry.Skill;
import com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.CanisPacketHandler;
import com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.packet.data.*;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.CanisEntity;
import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.accouterments.CanisAccouterments;
import com.platinumg17.rigoranthusemortisreborn.canis.common.util.REUtil;
import com.platinumg17.rigoranthusemortisreborn.config.Config;
import com.platinumg17.rigoranthusemortisreborn.config.ConfigValues;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
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

public class CanisInfoScreen extends Screen {
    public final CanisEntity canis;
    public final PlayerEntity player;
    private int currentPage = 0;
    private int maxPages = 1;
    private List<Widget> skillWidgets = new ArrayList<>(16);
    private Button leftBtn, rightBtn;
    private List<Skill> skillList;
//    private List<ResourceLocation> customSkinList;
//    public int textureIndex;

    public CanisInfoScreen(CanisEntity canis, PlayerEntity player) {
        super(new TranslationTextComponent("canisskills.screen.canis.title"));
        this.canis = canis;
        this.player = player;
        this.skillList = RigoranthusEmortisRebornAPI.SKILLS
                .getValues()
                .stream()
                .sorted(Comparator.comparing((t) -> I18n.get(t.getTranslationKey())))
                .collect(Collectors.toList());
//        this.customSkinList = CanisTextureManager.INSTANCE.getAll();
//        this.textureIndex = this.customSkinList.indexOf(CanisTextureManager.INSTANCE.getTextureLoc(canis.getSkinHash()));
//        this.textureIndex = this.textureIndex >= 0 ? this.textureIndex : 0;
    }

    public static void open(CanisEntity canis) {
        Minecraft mc = Minecraft.getInstance();
        mc.setScreen(new CanisInfoScreen(canis, mc.player));
    }

    @Override
    public void init() {
        super.init();
        this.minecraft.keyboardHandler.setSendRepeatsToGui(true);
        int topX = this.width / 2;
        int topY = this.height / 2;

        TextFieldWidget nameTextField = new TextFieldWidget(this.font, topX - 100, topY + 50, 200, 20,  new TranslationTextComponent("canisInfo.enterName"));
        nameTextField.setResponder(text ->  {
            CanisPacketHandler.send(PacketDistributor.SERVER.noArg(), new CanisNameData(CanisInfoScreen.this.canis.getId(), text));
        });
        nameTextField.setFocus(false);
        nameTextField.setMaxLength(32);
        if (this.canis.hasCustomName()) {
            nameTextField.setValue(this.canis.getCustomName().getContents());
        }
        this.addButton(nameTextField);

        if (this.canis.isOwnedBy(this.player)) {
            Button obeyBtn = new Button(this.width - 64, topY + 77, 42, 20, new StringTextComponent(String.valueOf(this.canis.willObeyOthers())), (btn) -> {
                btn.setMessage(new StringTextComponent(String.valueOf(!this.canis.willObeyOthers())));
                CanisPacketHandler.send(PacketDistributor.SERVER.noArg(), new CanisObeyData(this.canis.getId(), !this.canis.willObeyOthers()));
            });
            this.addButton(obeyBtn);
        }
        Button attackPlayerBtn = new Button(this.width - 64, topY - 5, 42, 20, new StringTextComponent(String.valueOf(this.canis.canPlayersAttack())), button -> {
            button.setMessage(new StringTextComponent(String.valueOf(!this.canis.canPlayersAttack())));
            CanisPacketHandler.send(PacketDistributor.SERVER.noArg(), new FriendlyFireData(this.canis.getId(), !this.canis.canPlayersAttack()));
        });

        this.addButton(attackPlayerBtn);

//        //if (ConfigValues.USE_ALTERNATE_TEXTURES) {
//        Button addBtn = new Button(this.width - 42, topY + 30, 20, 20, new StringTextComponent("+"), (btn) -> {
//            this.textureIndex += 1;
//            this.textureIndex %= this.customSkinList.size();
//            ResourceLocation rl = this.customSkinList.get(this.textureIndex);
//
//            this.setCanisTexture(rl);
//        });
//        Button lessBtn = new Button(this.width - 64, topY + 30, 20, 20, new StringTextComponent("-"), (btn) -> {
//            this.textureIndex += this.customSkinList.size() - 1;
//            this.textureIndex %= this.customSkinList.size();
//            ResourceLocation rl = this.customSkinList.get(this.textureIndex);
//            this.setCanisTexture(rl);
//        });
//        this.addButton(addBtn);
//        this.addButton(lessBtn);

        Button modeBtn = new Button(topX + 40, topY + 25, 60, 20, new TranslationTextComponent(this.canis.getMode().getUnlocalisedName()), button -> {
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
            this.leftBtn = new Button(25, perPage * 21 + 10, 20, 20, new StringTextComponent("<"), (btn) -> {
                this.currentPage = Math.max(0, this.currentPage - 1);
                btn.active = this.currentPage > 0;
                this.rightBtn.active = true;
                this.recalculatePage(perPage);
            }) {
                @Override
                public void renderToolTip(MatrixStack stack, int mouseX, int mouseY) {
                    CanisInfoScreen.this.renderTooltip(stack, new TranslationTextComponent("canisgui.prevpage").withStyle(TextFormatting.ITALIC), mouseX, mouseY);
                }
            };
            this.leftBtn.active = false;

            this.rightBtn = new Button(48, perPage * 21 + 10, 20, 20, new StringTextComponent(">"), (btn) -> {
                this.currentPage = Math.min(this.maxPages - 1, this.currentPage + 1);
                btn.active = this.currentPage < this.maxPages - 1;
                this.leftBtn.active = true;
                this.recalculatePage(perPage);}) {
                @Override
                public void renderToolTip(MatrixStack stack, int mouseX, int mouseY) {
                    CanisInfoScreen.this.renderTooltip(stack, new TranslationTextComponent("canisgui.nextpage").withStyle(TextFormatting.ITALIC), mouseX, mouseY);
                }
            };
            this.addButton(this.leftBtn);
            this.addButton(this.rightBtn);
        }
    }

//    private void setCanisTexture(ResourceLocation rl) {
//        if (ConfigValues.SEND_SKIN) {
//            try {
//                byte[] data = CanisTextureManager.INSTANCE.getResourceBytes(rl);CanisPacketHandler.send(PacketDistributor.SERVER.noArg(), new SendSkinData(this.canis.getId(), data));
//            } catch (IOException e) {RigoranthusEmortisReborn.LOGGER.error("Was unable to get resource data for {}, {}", rl, e);}
//        } else {CanisPacketHandler.send(PacketDistributor.SERVER.noArg(), new CanisTextureData(this.canis.getId(), CanisTextureManager.INSTANCE.getTextureHash(rl)));}
//    }

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

    @Override
    public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        //Background
        int topX = this.width / 2;
        int topY = this.height / 2;
        this.renderBackground(stack);
        // Background
        String health = REUtil.format1DP(this.canis.getHealth());
        String healthMax = REUtil.format1DP(this.canis.getMaxHealth());
        String speedValue = REUtil.format2DP(this.canis.getAttribute(Attributes.MOVEMENT_SPEED).getValue());
        String armorValue = REUtil.format2DP(this.canis.getAttribute(Attributes.ARMOR).getValue());
        String ageValue = REUtil.format2DP(this.canis.getAge());
        String ageRel = I18n.get(this.canis.isBaby() ? "canisgui.age.baby" : "canisgui.age.adult");
        String ageString = ageValue + " " + ageRel;
        String tamedString = "";
        if (this.canis.isTame()) {
            if (this.canis.isOwnedBy(this.player)) {
                tamedString = I18n.get("canisgui.owner.you");
            } else if (this.canis.getOwnersName().isPresent()) {
                tamedString = this.canis.getOwnersName().get().getString();
            }
        }

        //this.font.drawString(I18n.format("canisgui.health") + healthState, this.width - 160, topY - 110, 0xFFFFFF);
        this.font.draw(stack, I18n.get("canisgui.speed") + " " + speedValue, this.width - 160, topY - 100, 0xFFFFFF);
        this.font.draw(stack, I18n.get("canisgui.owner") + " " + tamedString, this.width - 160, topY - 90, 0xFFFFFF);
        this.font.draw(stack, I18n.get("canisgui.age") + " " + ageString, this.width - 160, topY - 80, 0xFFFFFF);
        this.font.draw(stack, I18n.get("canisgui.armor") + " " + armorValue, this.width - 160, topY - 70, 0xFFFFFF);
        if (Config.CANIS_GENDER.get() == true) {this.font.draw(stack, I18n.get("canisgui.gender") + " "+ I18n.get(this.canis.getGender().getUnlocalisedName()), this.width - 160, topY - 60, 0xFFFFFF);}
        this.font.draw(stack, I18n.get("canisgui.newname"), topX - 100, topY + 38, 4210752);
        this.font.draw(stack, I18n.get("canisgui.level") + " " + this.canis.getLevel().getLevel(CanisLevel.Type.CHORDATA), topX - 65, topY + 75, 0xFF10F9);
        this.font.draw(stack, I18n.get("canisgui.levelhomini") + " " + this.canis.getLevel().getLevel(CanisLevel.Type.HOMINI), topX, topY + 75, 0xFF10F9);
        if (this.canis.getAccoutrement(CanisAccouterments.GOLDEN_COLLAR.get()).isPresent()) {
            this.font.draw(stack, TextFormatting.GOLD + "Unlimited Points", topX - 38, topY + 89, 0xFFFFFF); //TODO translation
        } else {
            this.font.draw(stack, I18n.get("canisgui.pointsleft") + " " + this.canis.getSpendablePoints(), topX - 38, topY + 89, 0xFFFFFF);
        }
//        this.font.draw(stack, I18n.get("canisgui.textureindex"), this.width - 80, topY + 20, 0xFFFFFF);
//        this.font.draw(stack, this.canis.getSkinHash().substring(0, Math.min(this.canis.getSkinHash().length(), 10)), this.width - 73, topY + 54, 0xFFFFFF);
        if (this.canis.isOwnedBy(this.player)) {
            this.font.draw(stack, I18n.get("canisgui.obeyothers"), this.width - 76, topY + 67, 0xFFFFFF);
        }
        this.font.draw(stack, I18n.get("canisgui.friendlyfire"), this.width - 76, topY - 15, 0xFFFFFF);
        this.buttons.forEach(widget -> {
            if (widget instanceof SkillButton) {
                SkillButton sklBTN = (SkillButton)widget;
                this.font.draw(stack, I18n.get(sklBTN.skill.getTranslationKey()), sklBTN.x + 25, sklBTN.y + 7, 0xFFFFFF);
            }
        });
        super.render(stack, mouseX, mouseY, partialTicks);
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

    private static class SkillButton extends Button {
        protected Skill skill;
        private SkillButton(int x, int y, int widthIn, int heightIn, ITextComponent buttonText, Skill skill, Consumer<SkillButton> onPress) {
            super(x, y, widthIn, heightIn, buttonText, button -> onPress.accept((SkillButton) button));
            this.skill = skill;
        }
    }
}
