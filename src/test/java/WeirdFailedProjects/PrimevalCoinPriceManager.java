package WeirdFailedProjects;

import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import net.minecraft.client.resources.JsonReloadListener;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.BinomialRange;
import net.minecraft.loot.ConstantRange;
import net.minecraft.loot.RandomValueRange;
import net.minecraft.profiler.IProfiler;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

//@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
//public class PrimevalCoinPriceManager extends JsonReloadListener {
//    private static final Logger LOGGER = LogManager.getLogger();
//    private static final Gson GSON = new GsonBuilder().registerTypeAdapter(PrimevalCoinPricing.class, new PrimevalCoinPricing.Serializer())
//            .registerTypeAdapter(RandomValueRange.class, new RandomValueRange.Serializer())
//            .registerTypeAdapter(BinomialRange.class, new BinomialRange.Serializer())
//            .registerTypeAdapter(ConstantRange.class, new ConstantRange.Serializer()).create();
//    private List<PrimevalCoinPricing> pricings;
//
//    public PrimevalCoinPriceManager() {super(GSON, "rigoranthusemortisreborn/primeval_coin_prices");}
//    private static PrimevalCoinPriceManager INSTANCE;
//    public static PrimevalCoinPriceManager getInstance() {return Objects.requireNonNull(INSTANCE);}
//
//    @Override
//    protected void apply(Map<ResourceLocation, JsonElement> jsonEntries, IResourceManager resourceManager, IProfiler profiler) {
//        ImmutableList.Builder<PrimevalCoinPricing> pricings = ImmutableList.builder();
//        for(Map.Entry<ResourceLocation, JsonElement> entry : jsonEntries.entrySet()) {
//            try {
//                PrimevalCoinPricing pricing = GSON.fromJson(entry.getValue(), PrimevalCoinPricing.class);
//                pricings.add(pricing);
//            } catch(Exception e) {
//                LOGGER.error("Couldn't parse primeval coin pricing {}", entry.getKey(), e);
//            }
//        }
//        this.pricings = pricings.build();
//        LOGGER.info("Loaded {} primeval coin prices", this.pricings.size());
//    }
//    public Optional<Integer> findPrice(ItemStack stack, Random rand) {return pricings.stream().filter(pricing -> pricing.appliesTo(stack)).findAny().map(pricing -> pricing.generatePrice(rand));}
//    public static JsonElement parsePrice(PrimevalCoinPricing pricing) {return GSON.toJsonTree(pricing);}
//    @SubscribeEvent public static void onResourceReload(AddReloadListenerEvent event) {event.addListener(INSTANCE = new PrimevalCoinPriceManager());}
//}
