//import com.platinumg17.rigoranthusemortisreborn.core.init.ItemInit;
//import com.platinumg17.rigoranthusemortisreborn.core.registry.RigoranthusItemGroup;
//import com.platinumg17.rigoranthusemortisreborn.core.registry.effects.weapons.BunnyHopEffect;
//import com.platinumg17.rigoranthusemortisreborn.core.registry.effects.weapons.OnHitEffect;
//import com.platinumg17.rigoranthusemortisreborn.core.registry.effects.weapons.RightClickBlockEffect;
//import com.platinumg17.rigoranthusemortisreborn.items.itemeffects.DestroyBlockEffect;
//import com.platinumg17.rigoranthusemortisreborn.items.itemeffects.FinishUseItemEffect;
//import com.platinumg17.rigoranthusemortisreborn.items.itemeffects.ItemRightClickEffect;
//import com.platinumg17.rigoranthusemortisreborn.items.tooltypes.ToolRegistry;
//import net.minecraft.block.Blocks;
//import net.minecraft.item.Item;
//import net.minecraft.item.ItemTier;
//import net.minecraft.item.Items;
//import net.minecraft.item.Rarity;
//import net.minecraft.potion.Effects;
//import net.minecraft.util.SoundEvents;
//
//public class WeaponsTest {
    //hammers
/*

                    public static final RegistryObject<Item> NAME = ITEMS.register("name",
                    () -> new REWeaponItem(new REWeaponItem.Builder(ItemTier.IRON, 4, -3.2F).efficiency(4.0F).set(ToolRegistry.HAMMER_TOOL), new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("sledge_hammer"));

                    public static final RegistryObject<Item> SMITHY = ITEMS.register("smithy",
                    () -> new REWeaponItem(new REWeaponItem.Builder(ItemTier.IRON, 5, -3.2F).efficiency(3.5F).set(ToolRegistry.HAMMER_TOOL), new Item.Properties().defaultDurability(450).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)));

                    public static final RegistryObject<Item> CLAW_HAMMER = ITEMS.register("claw_hammer",
			        () -> new REWeaponItem(new REWeaponItem.Builder(ItemTier.IRON, 2, -2.8F).efficiency(1.0F).set(ToolRegistry.HAMMER_TOOL), new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)));

// this ones been used
 			public static final RegistryObject<Item> PITFALL = ITEMS.register("pitfall",
			() -> new REWeaponItem(new REWeaponItem.Builder(RigoranthusItemTier.DENIZEN_TIER, 7, -3.2F).efficiency(7.0F).set(ToolRegistry.HAMMER_TOOL).add(
			OnHitEffect.TIME_SLOWNESS_AOE).add(OnHitEffect.enemyPotionEffect(() -> new
			EffectInstance(Effects.SLOWNESS, 100, 1))), new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).rarity(Rarity.RARE)));

                public static final RegistryObject<Item> NAME = ITEMS.register("name",
                () -> new REWeaponItem(new REWeaponItem.Builder(RigoranthusItemTier.JAHY_TIER, 9, -3.2F).efficiency(9.1F).set(ToolRegistry.HAMMER_TOOL).set(
                BunnyHopEffect.EFFECT_02).add(BunnyHopEffect.EFFECT_02, OnHitEffect.playSound(() ->
                MSSoundEvents.ITEM_E_HIT, 1.5F, 1.0F)), new Item.Properties().defaultDurability(6114).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)));

                    public static final RegistryObject<Item> NAME = ITEMS.register("name",
                    () -> new REWeaponItem(new REWeaponItem.Builder(ToolRegistry.ZILLY_TIER, 8, -3.2F).efficiency(15.0F).set(ToolRegistry.HAMMER_TOOL), new Item.Properties().tab(
                    RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).rarity(Rarity.EPIC)).setRegistryName("zillyhoo_hammer"));

                    public static final RegistryObject<Item> NAME = ITEMS.register("name",
                    () -> new REWeaponItem(new REWeaponItem.Builder(ToolRegistry.ZILLY_TIER, 6, -3.2F).efficiency(15.0F).set(ToolRegistry.HAMMER_TOOL).add(
                    OnHitEffect.RANDOM_DAMAGE), new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).rarity(Rarity.RARE)).setRegistryName("popamatic_vrillyhoo"));

                    public static final RegistryObject<Item> NAME = ITEMS.register("name",
                    () -> new REWeaponItem(new REWeaponItem.Builder(RigoranthusItemTier.CORUNDUM_TIER, 9, -3.2F).efficiency(4.0F).set(ToolRegistry.HAMMER_TOOL).add(
                    OnHitEffect.setOnFire(50)), new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).rarity(Rarity.RARE)).setRegistryName("scarlet_lyho"));

    //blades

			public static final RegistryObject<Item> NAME = ITEMS.register("name",
			() -> new REWeaponItem(new REWeaponItem.Builder(RigoranthusItemTier.JAHY_TIER, 3, -3.0F).efficiency(1.0F).set(ToolRegistry.SWORD_TOOL).add(
			OnHitEffect.SWORD_DROP), new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("sord"));

                    //			public static final RegistryObject<Item> NAME = ITEMS.register("name",
                    //			() -> new REWeaponItem(new REWeaponItem.Builder(ItemTier.STONE, 3, -2.4F).efficiency(1.0F).set(ToolRegistry.MISC_TOOL).add(
                    //			OnHitEffect.SWEEP).set(ItemRightClickEffect.absorbFluid(() -> Blocks.LAVA, () -> ItemInit.WET_PUMORD)), new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("pumord"));

                    //			public static final RegistryObject<Item> NAME = ITEMS.register("name",
                    //			() -> new REWeaponItem(new REWeaponItem.Builder(ItemTier.STONE, 3, -2.6F).efficiency(1.0F).set(ToolRegistry.MISC_TOOL).add(
                    //			OnHitEffect.SWEEP).set(RightClickBlockEffect.placeFluid(() -> Blocks.LAVA, () -> ItemInit.PUMORD)).add(OnHitEffect.playSound(() -> SoundEvents.ITEM_BUCKET_EMPTY_LAVA, 1.0F, 0.2F)).add(OnHitEffect.setOnFire(10)), new Item.Properties()).setRegistryName("wet_pumord"));

                    public static final RegistryObject<Item> NAME = ITEMS.register("name",
                    () -> new REWeaponItem(new REWeaponItem.Builder(ItemTier.IRON, 3, -2.4F).efficiency(15.0F).set(ToolRegistry.SWORD_TOOL).add(
                    OnHitEffect.SWEEP), new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("katana"));

                    //			public static final RegistryObject<Item> NAME = ITEMS.register("",
                    //			() -> new REWeaponItem(new REWeaponItem.Builder(ItemTier.IRON, 5, -2.4F).efficiency(15.0F).set(ToolRegistry.SWORD_TOOL).add(
                    //			OnHitEffect.SWEEP).add(OnHitEffect.HOPE_RESISTANCE), new Item.Properties().defaultDurability(2048).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).rarity(Rarity.UNCOMMON)));

                    public static final RegistryObject<Item> CERBER = ITEMS.register("name",
                    () -> new REWeaponItem(new REWeaponItem.Builder(ItemTier.IRON, 4, -2.4F).efficiency(15.0F).set(ToolRegistry.SWORD_TOOL).add(
                    OnHitEffect.SWEEP).add(OnHitEffect.setOnFire(30)), new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("fire_poker"));



                    public static final RegistryObject<Item> NAME = ITEMS.register("name",
                    () -> new REWeaponItem(new REWeaponItem.Builder(ItemTier.DIAMOND, 7, -2.4F).efficiency(15.0F).set(ToolRegistry.SWORD_TOOL).add(
                    OnHitEffect.SWEEP), new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).rarity(Rarity.RARE)).setRegistryName("caledscratch"));

                    public static final RegistryObject<Item> NAME = ITEMS.register("name",
                    () -> new REWeaponItem(new REWeaponItem.Builder(RigoranthusItemTier.DENIZEN_TIER, 7, -2.4F).efficiency(15.0F).set(ToolRegistry.SWORD_TOOL).add(
                    OnHitEffect.SWEEP), new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).rarity(Rarity.EPIC)).setRegistryName("royal_deringer"));

                    public static final RegistryObject<Item> NAME = ITEMS.register("name",
                    () -> new REWeaponItem(new REWeaponItem.Builder(ItemTier.IRON, 5, -2.6F).efficiency(15.0F).set(ToolRegistry.SWORD_TOOL).add(
                    OnHitEffect.SWEEP), new Item.Properties().defaultDurability(600).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("claymore"));

                    public static final RegistryObject<Item> NAME = ITEMS.register("name",
                    () -> new REWeaponItem(new REWeaponItem.Builder(ToolRegistry.ZILLY_TIER, 6, -2.4F).efficiency(15.0F).set(ToolRegistry.SWORD_TOOL).add(
                    OnHitEffect.SWEEP), new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).rarity(Rarity.EPIC)).setRegistryName("cutlass_of_zillywair"));

                    public static final RegistryObject<Item> NAME = ITEMS.register("name",
                    () -> new REWeaponItem(new REWeaponItem.Builder(RigoranthusItemTier.REGI_TIER, 5, -2.4F).efficiency(15.0F).set(ToolRegistry.SWORD_TOOL).add(
                    OnHitEffect.SWEEP), new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("regisword"));

                    public static final RegistryObject<Item> NAME = ITEMS.register("name",
                    () -> new REWeaponItem(new REWeaponItem.Builder(ItemTier.GOLD, 7, -2.4F).efficiency(15.0F).set(ToolRegistry.SWORD_TOOL).add(OnHitEffect.SWEEP).add(
                    OnHitEffect.setOnFire(30)), new Item.Properties().defaultDurability(300).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("cobalt_sabre"));

			public static final RegistryObject<Item> SLIGHT_OF_HAND = ITEMS.register("name",
			() -> new REWeaponItem(new REWeaponItem.Builder(ItemTier.IRON, 0, -2.0F).efficiency(1.0F).set(ToolRegistry.MISC_TOOL).add(
			OnHitEffect.backstab(3)), new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)));

			public static final RegistryObject<Item> NAME = ITEMS.register("name",
			() -> new REWeaponItem(new REWeaponItem.Builder(RigoranthusItemTier.EMERALD_TIER, 1, -2.0F).efficiency(1.0F).set(ToolRegistry.MISC_TOOL).add(
			OnHitEffect.backstab(4)), new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("light_of_my_knife"));

			public static final RegistryObject<Item> NAME = ITEMS.register("name",
			() -> new REWeaponItem(new REWeaponItem.Builder(RigoranthusItemTier.CORUNDUM_TIER, 1, -2.0F).efficiency(1.0F).set(ToolRegistry.MISC_TOOL).add(
			OnHitEffect.backstab(9)), new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("starshard_tri_blade"));

			public static final RegistryObject<Item> NAME = ITEMS.register("name",
			() -> new REWeaponItem(new REWeaponItem.Builder(RigoranthusItemTier.ORGANIC_TIER, 3, -2.0F).efficiency(1.0F).set(ToolRegistry.MISC_TOOL).add(
			OnHitEffect.backstab(4)), new Item.Properties().defaultDurability(1200).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("toothripper"));

            //  Axes

			public static final RegistryObject<Item> NAME = ITEMS.register("name",
			() -> new REWeaponItem(new REWeaponItem.Builder(RigoranthusItemTier.JAHY_TIER, 3, -3.5F).efficiency(1.0F).set(ToolRegistry.AXE_TOOL).add(
			OnHitEffect.SWORD_DROP), new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("batleacks"));

			public static final RegistryObject<Item> NAME = ITEMS.register("name",
			() -> new REWeaponItem(new REWeaponItem.Builder(ItemTier.STONE, 5, -3.0F).efficiency(6.0F).disableShield().set(ToolRegistry.AXE_TOOL).set(new
			FarmineEffect(Integer.MAX_VALUE, 20)), new Item.Properties().defaultDurability(400).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("copse_crusher"));

			public static final RegistryObject<Item> NAME = ITEMS.register("name",
			() -> new REWeaponItem(new REWeaponItem.Builder(RigoranthusItemTier.ORGANIC_TIER, 7, -3.0F).efficiency(2.0F).
			disableShield().set(ToolRegistry.AXE_TOOL).setEating(FinishUseItemEffect.foodEffect(6, 0.6F, 75)), new Item.Properties().defaultDurability(500).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("quench_crusher"));

			public static final RegistryObject<Item> NAME = ITEMS.register("name",
			() -> new REWeaponItem(new REWeaponItem.Builder(RigoranthusItemTier.ORGANIC_TIER, 8, -3.0F).efficiency(3.0F).
			disableShield().set(ToolRegistry.AXE_TOOL).set(
			DestroyBlockEffect.extraHarvests(true, 0.6F, 20, () -> Items.MELON_SLICE, () -> Blocks.MELON)), new Item.Properties().defaultDurability(400).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("melonsbane"));

			public static final RegistryObject<Item> NAME = ITEMS.register("name",
			() -> new REWeaponItem(new REWeaponItem.Builder(RigoranthusItemTier.ORGANIC_TIER, 7, -3.0F).efficiency(3.0F).
			disableShield().set(ToolRegistry.AXE_TOOL).set(
			DestroyBlockEffect.DOUBLE_FARM), new Item.Properties().defaultDurability(800).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("crop_chop"));

			public static final RegistryObject<Item> NAME = ITEMS.register("name",
			() -> new REWeaponItem(new REWeaponItem.Builder(RigoranthusItemTier.ORGANIC_TIER, 9, -3.0F).efficiency(3.0F).
			disableShield().set(ToolRegistry.AXE_TOOL).set(
			DestroyBlockEffect.DOUBLE_FARM), new Item.Properties().defaultDurability(950).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("the_last_straw"));

			public static final RegistryObject<Item> NAME = ITEMS.register("name",
			() -> new REWeaponItem(new REWeaponItem.Builder(ItemTier.IRON, 8, -3.0F).efficiency(3.0F).
			disableShield().set(ToolRegistry.AXE_TOOL), new Item.Properties().defaultDurability(600).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("battleaxe"));

			public static final RegistryObject<Item> NAME = ITEMS.register("name",
			() -> new REWeaponItem(new REWeaponItem.Builder(ItemTier.IRON, 8, -3.0F).efficiency(6.0F).
			disableShield().set(ToolRegistry.AXE_TOOL).set(ItemRightClickEffect.switchTo(() -> SHOCK_AXE_UNPOWERED)).add(
			OnHitEffect.DROP_FOE_ITEM).add(InventoryTickEffect.DROP_WHEN_IN_WATER).add(OnHitEffect.playSound(() -> MSSoundEvents.EVENT_ELECTRIC_SHOCK, 0.6F, 1.0F)), new Item.Properties().defaultDurability(800).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("shock_axe"));

			public static final RegistryObject<Item> NAME = ITEMS.register("name",
			() -> new REWeaponItem(new REWeaponItem.Builder(ItemTier.IRON, 8, -3.0F).efficiency(6.0F).
			disableShield().set(ToolRegistry.AXE_TOOL).set(ItemRightClickEffect.switchTo(() -> ItemInit.SHOCK_AXE)), new Item.Properties().defaultDurability(800)).setRegistryName("shock_axe_unpowered"));

			public static final RegistryObject<Item> NAME = ITEMS.register("name",
			() -> new REWeaponItem(new REWeaponItem.Builder(ItemTier.IRON, 6, -3.0F).efficiency(7.0F).
			disableShield().set(ToolRegistry.AXE_TOOL).add(OnHitEffect.SPACE_TELEPORT), new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).rarity(Rarity.UNCOMMON)).setRegistryName("lorentz_distransformationer"));

			public static final RegistryObject<Item> NAME = ITEMS.register("name",
			() -> new REWeaponItem(new REWeaponItem.Builder(RigoranthusItemTier.RESILE_TIER, 6, -3.0F).efficiency(2.0F).
			disableShield().set(ToolRegistry.AXE_HAMMER_TOOL).set(new FarmineEffect(Integer.MAX_VALUE, 50)).set(
			BunnyHopEffect.EFFECT_06).add(BunnyHopEffect.EFFECT_06), new Item.Properties().defaultDurability(800).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("piston_powered_pogo_axehammer"));

                    public static final RegistryObject<Item> NAME = ITEMS.register("name",
                    () -> new REWeaponItem(new REWeaponItem.Builder(RigoranthusItemTier.CORUNDUM_TIER, 7, -3.0F).efficiency(9.0F).
                    disableShield().set(ToolRegistry.AXE_TOOL).add(
                    OnHitEffect.setOnFire(30)), new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).rarity(Rarity.UNCOMMON)).setRegistryName("hephaestus_lumberjack"));

			public static final RegistryObject<Item> NAME = ITEMS.register("name",
			() -> new REWeaponItem(new REWeaponItem.Builder(ItemTier.IRON, 7, -3.0F).efficiency(5.0F).disableShield().set(ToolRegistry.AXE_HAMMER_TOOL).set(new FarmineEffect(Integer.MAX_VALUE, 100)).set(
			BunnyHopEffect.EFFECT_07).add(BunnyHopEffect.EFFECT_07), new Item.Properties().defaultDurability(2048).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).rarity(Rarity.UNCOMMON)).setRegistryName("fission_focused_fault_feller"));

            // Misc weapons
                    //			public static final RegistryObject<Item> NAME = ITEMS.register("name",
                    //			() -> new REWeaponItem(new REWeaponItem.Builder(ItemTier.DIAMOND, 4, -3.0F).efficiency(1.0F).add(OnHitEffect.RANDOM_DAMAGE), new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).defaultDurability(4096).rarity(Rarity.EPIC)).setRegistryName("fluorite_octet"));


			public static final RegistryObject<Item> NAME = ITEMS.register("name",
			() -> new REWeaponItem(new REWeaponItem.Builder(ItemTier.IRON, 2, -1.5F).efficiency(10.0F).set(ToolRegistry.CLAWS_TOOL).set(
			ItemRightClickEffect.switchTo(() -> ItemInit.CAT_CLAWS_SHEATHED)), new Item.Properties().defaultDurability(500).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("cat_claws_drawn"));

			public static final RegistryObject<Item> NAME = ITEMS.register("name",
			() -> new REWeaponItem(new REWeaponItem.Builder(ItemTier.IRON, -1, -1.0F).efficiency(10.0F).set(
			ItemRightClickEffect.switchTo(() -> ItemInit.CAT_CLAWS_DRAWN)), new Item.Properties().defaultDurability(500)).setRegistryName("cat_claws_sheathed"));

			public static final RegistryObject<Item> NAME = ITEMS.register("name",
			() -> new REWeaponItem(new REWeaponItem.Builder(RigoranthusItemTier.ORGANIC_TIER, 5, -1.5F).efficiency(10.0F).set(ToolRegistry.CLAWS_TOOL).set(
			ItemRightClickEffect.switchTo(() -> ItemInit.SKELETONIZER_SHEATHED)), new Item.Properties().defaultDurability(750).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("skeletonizer_drawn"));

			public static final RegistryObject<Item> NAME = ITEMS.register("name",
			() -> new REWeaponItem(new REWeaponItem.Builder(RigoranthusItemTier.ORGANIC_TIER, -1, -1.0F).efficiency(10.0F).set(
			ItemRightClickEffect.switchTo(() -> ItemInit.SKELETONIZER_DRAWN)), new Item.Properties().defaultDurability(750)).setRegistryName("skeletonizer_sheathed"));



			public static final RegistryObject<Item> NAME = ITEMS.register("tears_of_the_enderlich_drawn",
			() -> new REWeaponItem(new REWeaponItem.Builder(RigoranthusItemTier.CORUNDUM_TIER, 4, -1.5F).efficiency(10.0F).set(ToolRegistry.CLAWS_TOOL).set(
			ItemRightClickEffect.switchTo(() -> ItemInit.TEARS_OF_THE_ENDERLICH_SHEATHED)).add(OnHitEffect.targetSpecificAdditionalDamage(6, () ->
			MSEntityTypes.LICH)), new Item.Properties().defaultDurability(2000).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).rarity(Rarity.UNCOMMON)));

			public static final RegistryObject<Item> NAME = ITEMS.register("tears_of_the_enderlich_sheathed",
			() -> new REWeaponItem(new REWeaponItem.Builder(RigoranthusItemTier.CORUNDUM_TIER, -4, -1.0F).efficiency(10.0F).set(
			ItemRightClickEffect.switchTo(() -> ItemInit.TEARS_OF_THE_ENDERLICH_DRAWN)), new Item.Properties().defaultDurability(2000).rarity(Rarity.UNCOMMON)));

			public static final RegistryObject<Item> NAME = ITEMS.register("name",
			() -> new REWeaponItem(new REWeaponItem.Builder(ItemTier.IRON, 2, -1.5F).efficiency(10.0F).set(ToolRegistry.AXE_TOOL).set(
			ItemRightClickEffect.switchTo(() -> ItemInit.STICK)), new Item.Properties().defaultDurability(250).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("stick_chainsaw"));

			public static final RegistryObject<Item> NAME = ITEMS.register("name",
			() -> new REWeaponItem(new REWeaponItem.Builder(ItemTier.WOOD, -1, -0.5F).efficiency(10.0F).set(
			ItemRightClickEffect.switchTo(() -> ItemInit.STICK_CHAINSAW)), new Item.Properties().defaultDurability(250)).setRegistryName("stick"));

			public static final RegistryObject<Item> NAME = ITEMS.register("name",
			() -> new REWeaponItem(new REWeaponItem.Builder(RigoranthusItemTier.ORGANIC_TIER, 4, -1.0F).efficiency(2.0F).set(
			ItemRightClickEffect.switchTo(() -> ItemInit.THISTLEBLOWER_LIPSTICK)), new Item.Properties().defaultDurability(500).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("thistleblower"));

			public static final RegistryObject<Item> NAME = ITEMS.register("name",
			() -> new REWeaponItem(new REWeaponItem.Builder(ItemTier.WOOD, -1, -0.5F).efficiency(10.0F).set(
			ItemRightClickEffect.switchTo(() -> ItemInit.THISTLEBLOWER)), new Item.Properties().defaultDurability(500)).setRegistryName("thistleblower_lipstick"));

			public static final RegistryObject<Item> NAME = ITEMS.register("name",
			() -> new REWeaponItem(new REWeaponItem.Builder(RigoranthusItemTier.EMERALD_TIER, 3, -1.5F).efficiency(10.0F).set(ToolRegistry.AXE_TOOL).set(
			ItemRightClickEffect.switchTo(() -> ItemInit.EMERALD_IMMOLATOR_LIPSTICK)).add(OnHitEffect.setOnFire(5)), new Item.Properties().defaultDurability(1024).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("emerald_immolator"));

			public static final RegistryObject<Item> NAME = ITEMS.register("name",
			() -> new REWeaponItem(new REWeaponItem.Builder(ItemTier.WOOD, -1, -0.5F).efficiency(10.0F).set(
			ItemRightClickEffect.switchTo(() -> ItemInit.EMERALD_IMMOLATOR)), new Item.Properties().defaultDurability(1024)).setRegistryName("emerald_immolator_lipstick"));

			public static final RegistryObject<Item> NAME = ITEMS.register("name",
			() -> new REWeaponItem(new REWeaponItem.Builder(RigoranthusItemTier.ICE_TIER, 5, -1.5F).efficiency(10.0F).set(ToolRegistry.AXE_TOOL).set(
			ItemRightClickEffect.switchTo(() -> ItemInit.FROSTTOOTH_LIPSTICK)).add(OnHitEffect.ICE_SHARD), new Item.Properties().defaultDurability(1536).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("frosttooth"));

			public static final RegistryObject<Item> NAME = ITEMS.register("name",
			() -> new REWeaponItem(new REWeaponItem.Builder(ItemTier.WOOD, -1, -0.5F).efficiency(10.0F).set(
			ItemRightClickEffect.switchTo(() -> ItemInit.FROSTTOOTH)), new Item.Properties().defaultDurability(1536)).setRegistryName("frosttooth_lipstick"));

			public static final RegistryObject<Item> NAME = ITEMS.register("name",
			() -> new REWeaponItem(new REWeaponItem.Builder(RigoranthusItemTier.CORUNDUM_TIER, 4, -1.5F).efficiency(10.0F).set(ToolRegistry.AXE_TOOL).set(
			ItemRightClickEffect.switchTo(() -> ItemInit.OBSIDIATOR_LIPSTICK)), new Item.Properties().defaultDurability(2048).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).rarity(Rarity.UNCOMMON)).setRegistryName("obsidiator"));

			public static final RegistryObject<Item> NAME = ITEMS.register("name",
			() -> new REWeaponItem(new REWeaponItem.Builder(ItemTier.WOOD, -1, -0.5F).efficiency(10.0F).set(
			ItemRightClickEffect.switchTo(() -> ItemInit.OBSIDIATOR)), new Item.Properties().defaultDurability(2048).rarity(Rarity.UNCOMMON)).setRegistryName("obsidiator_lipstick"));

			public static final RegistryObject<Item> NAME = ITEMS.register("name",
			() -> new REWeaponItem(new REWeaponItem.Builder(ItemTier.IRON, 3, -2.5F).efficiency(1.0F).set(ToolRegistry.MISC_TOOL), new
			Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("lucerne_hammer"));

			public static final RegistryObject<Item> NAME = ITEMS.register("name",
			() -> new REWeaponItem(new REWeaponItem.Builder(ItemTier.IRON, 4, -2.5F).efficiency(1.0F).set(ToolRegistry.MISC_TOOL), new Item.Properties().
			defaultDurability(2048).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).rarity(Rarity.UNCOMMON)).setRegistryName("lucerne_hammer_of_undying"));
			 //Special property in ServerEventHandler

			public static final RegistryObject<Item> NAME = ITEMS.register("name",
			() -> new REWeaponItem(new REWeaponItem.Builder(RigoranthusItemTier.PRISMARINE_TIER, 2, -2.0F).efficiency(1.5F).set(ToolRegistry.MISC_TOOL), new Item.Properties().maxDamage(100).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("obsidian_axe_knife"));


			public static final RegistryObject<Item> NAME = ITEMS.register("name",
			() -> new REWeaponItem(new REWeaponItem.Builder(RigoranthusItemTier.PAPER_TIER, 1, -1.0F).efficiency(1.5F).set(ToolRegistry.MISC_TOOL).set(
			ItemRightClickEffect.extinguishFire(1)).add(OnHitEffect.enemyKnockback(1.5F)), new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("fan"));

			public static final RegistryObject<Item> NAME = ITEMS.register("name",
			() -> new REWeaponItem(new REWeaponItem.Builder(RigoranthusItemTier.DENIZEN_TIER, 2, -1.0F).efficiency(1.5F).set(ToolRegistry.MISC_TOOL).set(
			ItemRightClickEffect.extinguishFire(3)).add(OnHitEffect.BREATH_LEVITATION_AOE).add(OnHitEffect.enemyKnockback(2.0F)), new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).rarity(Rarity.RARE)).setRegistryName("typhonic_trivializer"));


    //sickles

			public static final RegistryObject<Item> NAME = ITEMS.register("name",
			() -> new REWeaponItem(new REWeaponItem.Builder(RigoranthusItemTier.CACTUS_TIER, 4, -2.2F).efficiency(1.0F).disableShield().set(ToolRegistry.SICKLE_TOOL), new
			Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("thorny_subject"));

			public static final RegistryObject<Item> NAME = ITEMS.register("name",
			() -> new REWeaponItem(new REWeaponItem.Builder(ItemTier.IRON, 5, -2.2F).efficiency(1.0F).disableShield().set(ToolRegistry.SICKLE_TOOL), new
			Item.Properties().defaultDurability(550).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("hemeoreaper"));

			public static final RegistryObject<Item> NAME = ITEMS.register("name",
			() -> new REWeaponItem(new REWeaponItem.Builder(RigoranthusItemTier.REGI_TIER, 4, -2.2F).efficiency(4.0F).disableShield().set(ToolRegistry.SICKLE_TOOL), new
			Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("regisickle"));

			public static final RegistryObject<Item> NAME = ITEMS.register("name",
			() -> new REWeaponItem(new REWeaponItem.Builder(ItemTier.GOLD, 9, -2.2F).efficiency(4.0F).disableShield().set(ToolRegistry.SICKLE_TOOL), new
			Item.Properties().defaultDurability(1024).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("hereticus_aururm"));

			public static final RegistryObject<Item> NAME = ITEMS.register("name",
			() -> new REWeaponItem(new REWeaponItem.Builder(ItemTier.IRON, 3, -2.6F).efficiency(1.5F).disableShield().set(ToolRegistry.SICKLE_TOOL).add(
			OnHitEffect.RANDOM_DAMAGE).set(ItemRightClickEffect.EIGHTBALL), new Item.Properties().defaultDurability(600).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("eightball_scythe"));


    //clubs

			public static final RegistryObject<Item> NAME = ITEMS.register("name",
			() -> new REWeaponItem(new REWeaponItem.Builder(RigoranthusItemTier.MEAT_TIER, 4, -2.8F).efficiency(2.0F).set(ToolRegistry.MISC_TOOL).add(
			OnHitEffect.playSound(() -> SoundEvents.ENTITY_GUARDIAN_FLOP)), new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("glub_club"));

			public static final RegistryObject<Item> NAME = ITEMS.register("name",
			() -> new REWeaponItem(new REWeaponItem.Builder(RigoranthusItemTier.ORGANIC_TIER, 6, -2.8F).efficiency(2.0F).set(ToolRegistry.MISC_TOOL).add(
			OnHitEffect.enemyPotionEffect(() -> new EffectInstance(Effects.POISON, 140, 0))), new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("red_eyes"));

			public static final RegistryObject<Item> NAME = ITEMS.register("name",
			() -> new REWeaponItem(new REWeaponItem.Builder(RigoranthusItemTier.ICE_TIER, 5, -2.8F).efficiency(2.0F).set(ToolRegistry.MISC_TOOL).add(
			OnHitEffect.ICE_SHARD), new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("club_zero"));

			public static final RegistryObject<Item> NAME = ITEMS.register("name",
			() -> new REWeaponItem(new REWeaponItem.Builder(RigoranthusItemTier.RESILE_TIER, 4, -2.8F).efficiency(2.0F).set(ToolRegistry.MISC_TOOL).set(
			BunnyHopEffect.EFFECT_05).add(BunnyHopEffect.EFFECT_05), new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("pogo_club"));

			public static final RegistryObject<Item> NAME = ITEMS.register("name",
			() -> new REWeaponItem(new REWeaponItem.Builder(ItemTier.IRON, 5, -2.8F).efficiency(2.0F).set(ToolRegistry.MISC_TOOL).add(
			OnHitEffect.RAGE_STRENGTH, OnHitEffect.playSound(() -> MSSoundEvents.ITEM_HORN_USE, 1.5F, 1)), new Item.Properties().defaultDurability(2048).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).rarity(Rarity.UNCOMMON)).setRegistryName("clown_club"));

			public static final RegistryObject<Item> NAME = ITEMS.register("name",
			() -> new REWeaponItem(new REWeaponItem.Builder(ItemTier.IRON, 7, -2.8F).efficiency(2.0F).set(ToolRegistry.MISC_TOOL).add(
			OnHitEffect.setOnFire(35)), new Item.Properties().defaultDurability(750).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("blazing_glory"));


    //canes

			public static final RegistryObject<Item> ADRENALINE_SHOT = ITEMS.register("adrenaline_shot",
			() -> new REWeaponItem(new REWeaponItem.Builder(ItemTier.WOOD, 3, -2.0F).efficiency(1.0F).set(ToolRegistry.MISC_TOOL).add(OnHitEffect.notAtPlayer(
			OnHitEffect.enemyPotionEffect(() -> new EffectInstance(Effects.STRENGTH, 140, 1)))), new Item.Properties().defaultDurability(150).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)));

			public static final RegistryObject<Item> NAME = ITEMS.register("name",
			() -> new REWeaponItem(new REWeaponItem.Builder(ItemTier.WOOD, 2, -2.0F).efficiency(1.0F).set(ToolRegistry.MISC_TOOL).add(
			InventoryTickEffect.BREATH_SLOW_FALLING), new Item.Properties().defaultDurability(350).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("umbrella"));

                        //			public static final RegistryObject<Item> NAME = ITEMS.register("iron_cane",
                        //			() -> new REWeaponItem(new REWeaponItem.Builder(ItemTier.IRON, 2, -2.0F).efficiency(1.0F).set(ToolRegistry.MISC_TOOL), new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)));

			public static final RegistryObject<Item> NAME = ITEMS.register("name",
			() -> new REWeaponItem(new REWeaponItem.Builder(ItemTier.IRON, 3, -2.0F).efficiency(1.0F).set(ToolRegistry.MISC_TOOL).set(
			PropelEffect.BREATH_PROPEL), new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).defaultDurability(2048).rarity(Rarity.UNCOMMON)).setRegistryName("zephyr_cane"));

			public static final RegistryObject<Item> NAME = ITEMS.register("name",
			() -> new REWeaponItem(new REWeaponItem.Builder(ItemTier.IRON, 3, -2.0F).efficiency(1.0F).set(ToolRegistry.MISC_TOOL), new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("spear_cane"));

			public static final RegistryObject<Item> NAME = ITEMS.register("name",
			() -> new REWeaponItem(new REWeaponItem.Builder(RigoranthusItemTier.ORGANIC_TIER, 3, -2.0F).efficiency(1.0F).set(ToolRegistry.MISC_TOOL), new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("paradises_portabello"));

			public static final RegistryObject<Item> NAME = ITEMS.register("name",
			() -> new REWeaponItem(new REWeaponItem.Builder(RigoranthusItemTier.REGI_TIER, 4, -2.0F).efficiency(1.0F).set(ToolRegistry.MISC_TOOL), new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("regi_cane"));

			public static final RegistryObject<Item> NAME = ITEMS.register("name",
			() -> new REWeaponItem(new REWeaponItem.Builder(RigoranthusItemTier.RESILE_TIER, 2, -2.0F).efficiency(1.0F).set(ToolRegistry.MISC_TOOL).set(
			BunnyHopEffect.EFFECT_06).add(BunnyHopEffect.EFFECT_06), new Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("pogo_cane"));

			public static final RegistryObject<Item> NAME = ITEMS.register("name",
			() -> new REWeaponItem(new REWeaponItem.Builder(ItemTier.WOOD, 3, -2.0F).efficiency(1.0F).set(ToolRegistry.MISC_TOOL), new
			Item.Properties().tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("prim_and_proper_walking_pole"));

			public static final RegistryObject<Item> NAME = ITEMS.register("name",
			() -> new REWeaponItem(new REWeaponItem.Builder(ItemTier.IRON, 3, -2.0F).efficiency(1.0F).set(ToolRegistry.MISC_TOOL).set(
			ItemRightClickEffect.switchTo(() -> ItemInit.LESS_PROPER_WALKING_STICK_SHEATHED)), new Item.Properties().defaultDurability(600).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP)).setRegistryName("less_proper_walking_stick"));

			public static final RegistryObject<Item> NAME = ITEMS.register("name",
			() -> new REWeaponItem(new REWeaponItem.Builder(ItemTier.WOOD, 3, -2.0F).efficiency(1.0F).set(
			ItemRightClickEffect.switchTo(() -> ItemInit.LESS_PROPER_WALKING_STICK)), new Item.Properties().defaultDurability(600)).setRegistryName("less_proper_walking_stick_sheathed"));

			public static final RegistryObject<Item> NAME = ITEMS.register("name",
			() -> new REWeaponItem(new REWeaponItem.Builder(ItemTier.IRON, 5, -2.0F).efficiency(1.0F).set(ToolRegistry.MISC_TOOL).set(
			ItemRightClickEffect.switchTo(() -> ItemInit.ROCKEFELLERS_WALKING_BLADECANE_SHEATHED)), new Item.Properties().defaultDurability(800).tab(RigoranthusItemGroup.RIGORANTHUS_EMORTIS_GROUP).rarity(Rarity.UNCOMMON)).setRegistryName("rockefellers_walking_bladecane"));

			public static final RegistryObject<Item> NAME = ITEMS.register("name",
			() -> new REWeaponItem(new REWeaponItem.Builder(ItemTier.WOOD, 5, -2.0F).efficiency(1.0F).set(
			ItemRightClickEffect.switchTo(() -> ItemInit.ROCKEFELLERS_WALKING_BLADECANE)), new Item.Properties().defaultDurability(800).rarity(Rarity.UNCOMMON)).setRegistryName("rockefellers_walking_bladecane_sheathed"));

		*/
//}