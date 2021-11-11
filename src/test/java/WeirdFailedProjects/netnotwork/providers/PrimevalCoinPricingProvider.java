package WeirdFailedProjects.netnotwork.providers;

import com.google.gson.GsonBuilder;
import com.platinumg17.rigoranthusemortisreborn.util.PrimevalCoinPriceManager;
import com.platinumg17.rigoranthusemortisreborn.util.PrimevalCoinPricing;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IDataProvider;
import net.minecraft.util.ResourceLocation;
import com.google.gson.Gson;
import net.minecraft.data.DirectoryCache;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.loot.IRandomRange;
import net.minecraft.loot.RandomValueRange;
import net.minecraft.util.IItemProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import static com.platinumg17.rigoranthusemortisreborn.core.init.ItemInit.*;
import static net.minecraft.item.Items.*;

public class PrimevalCoinPricingProvider implements IDataProvider {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private final Map<ResourceLocation, PrimevalCoinPricing> pricings = new HashMap<>();
    private final DataGenerator dataGenerator;
    private final String modid;

    public PrimevalCoinPricingProvider(DataGenerator dataGenerator, String modid) {
        this.dataGenerator = dataGenerator;
        this.modid = modid;
    }

    protected void registerPricings() {
        add(BILIS_BERRY.get(), 100, 150);
        add(MUSIC_DISK_KICKSTART.get(), 60, 90);
        add(MUSIC_DISK_NEON_LIGHTS.get(), 200, 250);

        add(LILY_PAD, 24, 31);
        add(POTATO, 12, 15);
        add(MUSHROOM_STEW, 95, 130);
        add(CARROT, 15, 18);
        add(APPLE, 25, 30);
        add(WHEAT, 10, 15);
        add(WHEAT_SEEDS, 15, 20);
        add(BEETROOT_SOUP, 70, 90);
        add(BEETROOT, 10, 15);
        add(BEEF, 110, 130);
        add(RED_MUSHROOM, 15, 20);
        add(BROWN_MUSHROOM, 10, 15);
        add(COCOA_BEANS, 25, 35);
        add(POTION, 5, 10);
        add(SALMON, 40, 60);
        add(MILK_BUCKET, 40, 50);
        add(EGG, 30, 45);
        add(SUGAR, 50, 80);
        add(RABBIT_STEW, 130, 150);
        add(POISONOUS_POTATO, 50, 60);
        add(MELON, 70, 80);
        add(COD, 90, 100);
        add(COOKIE, 120, 150);
        add(PUMPKIN_PIE, 120, 160);
        add(GOLDEN_APPLE, 2500, 2500);
        add(LAPIS_LAZULI, 25, 35);
        add(FEATHER, 25, 35);
        add(FLINT, 5, 10);
        add(STONE_AXE, 250, 300);
        add(EMERALD, 400, 500);
        add(SLIME_BALL, 30, 40);
        add(COAL, 70, 90);
        add(CHARCOAL, 50, 70);
        add(CLAY_BALL, 5, 10);
        add(IRON_INGOT, 90, 120);
        add(QUARTZ, 60, 80);
        add(BLAZE_POWDER, 80, 100);
        add(NETHER_BRICK, 5, 10);
        add(OAK_SAPLING, 60, 90);
        add(SPRUCE_SAPLING, 60, 90);
        add(BIRCH_SAPLING, 60, 90);
        add(JUNGLE_SAPLING, 60, 90);
        add(ACACIA_SAPLING, 60, 90);
        add(DARK_OAK_SAPLING, 60, 90);
        add(DIAMOND, 800, 1200);
        add(PRISMARINE_CRYSTALS, 100, 150);
        add(PRISMARINE_SHARD, 10, 15);
        add(GOLD_INGOT, 120, 180);
        add(LEATHER, 65, 80);
        add(GOLDEN_SWORD, 900, 1200);
        add(REDSTONE, 30, 40);
        add(GUNPOWDER, 50, 65);
        add(BUCKET, 50, 65);
        add(CLOCK, 150, 200);
        add(RABBIT_FOOT, 80, 100);
        add(BOOK, 50, 65);
        add(ROTTEN_FLESH, 1, 5);
        add(OAK_LOG, 20, 32);
        add(SPRUCE_LOG, 20, 32);
        add(BIRCH_LOG, 20, 32);
        add(JUNGLE_LOG, 20, 32);
        add(POLISHED_GRANITE, 5, 10);
        add(POLISHED_ANDESITE, 5, 10);
        add(NETHER_BRICKS, 5, 10);
        add(RED_NETHER_BRICKS, 10, 15);
        add(OAK_PLANKS, 5, 8);
        add(PRISMARINE, 5, 10);
        add(PRISMARINE_BRICKS, 10, 15);
        add(CACTUS, 30, 40);
        add(SANDSTONE, 5, 10);
        add(CHISELED_SANDSTONE, 10, 15);
        add(SMOOTH_SANDSTONE, 5, 10);
        add(RED_SANDSTONE, 5, 10);
        add(CHISELED_RED_SANDSTONE, 10, 15);
        add(SMOOTH_RED_SANDSTONE, 5, 10);
        add(STONE_BRICKS, 5, 10);
        add(CHISELED_STONE_BRICKS, 10, 15);
        add(BREAD, 90, 130);
        add(EGG, 50, 100);
        add(ELYTRA, 500, 1000);
        add(OBSIDIAN, 8, 20);
        add(PAPER, 5, 20);
        add(GLOWSTONE_DUST, 20, 40);
//        add(CHORUS_FRUIT, 420, 420);
//        add(DRAGON_BREATH, 50, 100);
    }
    protected void add(IItemProvider item, int min, int max) {add(Ingredient.of(item), new RandomValueRange(min, max), Objects.requireNonNull(item.asItem().getRegistryName()).getPath());}
    protected void add(Ingredient ingredient, IRandomRange range, String name) {add(new PrimevalCoinPricing(ingredient, range), new ResourceLocation(modid, name));}
    protected void add(PrimevalCoinPricing pricing, ResourceLocation name) {pricings.put(name, pricing);}

    @Override
    public void run(DirectoryCache cache) {
        registerPricings();
        Path outputPath = dataGenerator.getOutputFolder();

        for(Map.Entry<ResourceLocation, PrimevalCoinPricing> entry : pricings.entrySet()) {
            Path pricingPath = getPath(outputPath, entry.getKey());
            try {
                IDataProvider.save(GSON, cache, PrimevalCoinPriceManager.parsePrice(entry.getValue()), pricingPath);
            } catch(IOException e) {LOGGER.error("Couldn't save primeval coin pricing {}", pricingPath, e);}
        }
    }
    private static Path getPath(Path outputPath, ResourceLocation id) {return outputPath.resolve("data/" + id.getNamespace() + "/rigoranthusemortisreborn/primeval_coin_prices/" + id.getPath() + ".json");}
    @Override public String getName() {return "PrimevalCoin pricings";}
}