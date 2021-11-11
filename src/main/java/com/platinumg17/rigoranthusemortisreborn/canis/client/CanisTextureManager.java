package com.platinumg17.rigoranthusemortisreborn.canis.client;

//import com.google.common.collect.Maps;
//import com.google.gson.Gson;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
//import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.CanisEntity;
//import com.platinumg17.rigoranthusemortisreborn.canis.common.entity.texture.CanisTextureServer;
//import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.Resources;
//import com.platinumg17.rigoranthusemortisreborn.config.ConfigValues;
//import com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.CanisPacketHandler;
//import com.platinumg17.rigoranthusemortisreborn.canis.common.canisnetwork.packet.data.RequestSkinData;
//import com.platinumg17.rigoranthusemortisreborn.canis.common.util.REUtil;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.renderer.texture.Texture;
//import net.minecraft.client.renderer.texture.TextureManager;
//import net.minecraft.client.resources.SkinManager;
//import net.minecraft.resources.IResource;
//import net.minecraft.resources.IResourceManager;
//import net.minecraft.util.JSONUtils;
//import net.minecraft.util.ResourceLocation;
//import net.minecraftforge.fml.network.PacketDistributor;
//import net.minecraftforge.resource.IResourceType;
//import net.minecraftforge.resource.ISelectiveResourceReloadListener;
//import net.minecraftforge.resource.VanillaResourceType;
//import org.apache.commons.io.FileUtils;
//import org.apache.commons.io.IOUtils;
//import java.io.*;
//import java.util.*;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.nio.charset.StandardCharsets;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
//import java.util.function.Function;
//import java.util.function.Predicate;
//import javax.annotation.Nullable;
//
//public class CanisTextureManager extends CanisTextureServer implements ISelectiveResourceReloadListener {
//    public static final CanisTextureManager INSTANCE = new CanisTextureManager();
//    private static final Gson GSON = new Gson();
//    private static final ResourceLocation OVERRIDE_RESOURCE_LOCATION = REUtil.getResource("textures/entity/canis/custom/overrides.json");
//    private final Map<String, SkinRequest> hashToSkinRequest = Maps.newConcurrentMap();
//    protected final Map<String, ResourceLocation> skinHashToLoc = Maps.newHashMap();
//    protected final Map<ResourceLocation, String> locToSkinHash = Maps.newHashMap();
//    protected final List<ResourceLocation> customSkinLoc = new ArrayList<>(20);
//    public SkinRequest getRequestStatus(String hash) {return this.hashToSkinRequest.getOrDefault(hash, SkinRequest.UNREQUESTED);}
//    public void setRequestHandled(String hash) {this.hashToSkinRequest.put(hash, SkinRequest.RECEIVED);}
//    public void setRequestFailed(String hash) {this.hashToSkinRequest.put(hash, SkinRequest.FAILED);}
//    public void setRequested(String hash) {this.hashToSkinRequest.put(hash, SkinRequest.REQUESTED);}
//    public List<ResourceLocation> getAll() {return Collections.unmodifiableList(this.customSkinLoc);}
//    public ResourceLocation getLocFromHashOrGet(String hash, Function<? super String, ? extends ResourceLocation> mappingFunction) {return this.skinHashToLoc.computeIfAbsent(hash, mappingFunction);}
//    public String getTextureHash(ResourceLocation loc) {return this.locToSkinHash.getOrDefault(loc, "MISSING_MAPPING");}
//    public ResourceLocation getTextureLoc(String loc) {return this.skinHashToLoc.getOrDefault(loc, null); // TODO return missing not null
//    }
//
//    public File getClientFolder() {
//        Minecraft mc = Minecraft.getInstance();
//        SkinManager skinManager = mc.getSkinManager();
//        return new File(skinManager.skinsDirectory.getParentFile(), "skins_canis");
//    }
//
//    @Nullable
//    public byte[] getResourceBytes(ResourceLocation loc) throws IOException {
//        InputStream stream = null;
//        try {
//            stream = this.getResourceStream(loc);
//            return IOUtils.toByteArray(stream);
//        } finally  {
//            IOUtils.closeQuietly(stream);
//        }
//    }
//
//    @Nullable
//    public InputStream getResourceStream(ResourceLocation loc) throws IOException {
//        Minecraft mc = Minecraft.getInstance();
//        IResourceManager resourceManager = mc.getResourceManager();
//        IResource resource = resourceManager.getResource(loc);
//        return resource.getInputStream();
//    }
//
//    //   TODO  Make this return a different texture for each evolution [ maybe get rid of custom texture ]
//
//    public ResourceLocation getTexture(CanisEntity canis) {
//        if (canis.hasCustomSkin()) {
//            String hash = canis.getSkinHash();
//            return CanisTextureManager.INSTANCE.getLocFromHashOrGet(hash, this::getCached);
//        }
//        return Resources.CHORDATA_TEXTURE;
//    }
//
//
//    public Texture getOrLoadTexture(File baseFolder, String hash) {
//        Minecraft mc = Minecraft.getInstance();
//        TextureManager textureManager = mc.getTextureManager();
//        File cacheFile = getCacheFile(baseFolder, hash);
//        ResourceLocation loc = getResourceLocation(hash);
//        Texture texture = textureManager.getTexture(loc);
//        if (texture == null && cacheFile.isFile() && cacheFile.exists()) {
//            texture = new CachedFileTexture(loc, cacheFile);
//            textureManager.register(loc, texture);
//        }
//        return texture;
//    }
//
//    /**
//     * @param baseFolder The top level folder
//     * @param data The data
//     */
//    public String saveTextureAndLoad(File baseFolder, byte[] data) throws IOException {
//        Minecraft mc = Minecraft.getInstance();
//        TextureManager textureManager = mc.getTextureManager();
//        String hash = this.getHash(data);
//        File cacheFile = this.getCacheFile(baseFolder, hash);
//        ResourceLocation loc = this.getResourceLocation(hash);
//        Texture texture = textureManager.getTexture(loc);
//        if (texture == null) {
//            RigoranthusEmortisReborn.LOGGER.debug("Saved canis texture to local cache ({})", cacheFile);
//            FileUtils.writeByteArrayToFile(cacheFile, data);
//            RigoranthusEmortisReborn.LOGGER.debug("Texture not currently loaded trying to load");
//            textureManager.register(loc, new CachedFileTexture(loc, cacheFile));
//        }
//        return hash;
//    }
//
//    public ResourceLocation getCached(String hash) {
////        if (!ConfigValues.DISPLAY_OTHER_CANIS_SKINS) {  //  TODO maybe delete this bit
////            return Resources.CHORDATA_TEXTURE;
////        }
//        ResourceLocation loc = this.getResourceLocation(hash);
//        Texture texture = getOrLoadTexture(getClientFolder(), hash);
//        if (texture != null) {
//            return loc;
//        }
//        if (!this.getRequestStatus(hash).requested()) {
//            RigoranthusEmortisReborn.LOGGER.debug("Marked {} canis skin to be requested from the server", hash);
//            this.setRequested(hash);
//            CanisPacketHandler.send(PacketDistributor.SERVER.noArg(), new RequestSkinData(hash));
//        }
//        return Resources.CHORDATA_TEXTURE;
//    }
//    @Override public IResourceType getResourceType() {return VanillaResourceType.TEXTURES;}
//    @Override
//    public void onResourceManagerReload(IResourceManager resourceManager, Predicate<IResourceType> resourcePredicate) {
//        if (resourcePredicate.test(this.getResourceType())) {
//            this.skinHashToLoc.clear();
//            this.customSkinLoc.clear();
//            Collection<ResourceLocation> resources = resourceManager.listResources("textures/entity/canis/custom", (fileName) -> {return fileName.endsWith(".png");
//            });
//            for (ResourceLocation rl : resources) {
//                try {
//                    IResource resource = resourceManager.getResource(rl);
//                    if (resource == null) {
//                        RigoranthusEmortisReborn.LOGGER.warn("Could not get resource");
//                        continue;
//                    }
//                    this.loadCanisSkinResource(resource);
//                } catch (FileNotFoundException e) {
//                    ;
//                } catch (Exception exception) {
//                    RigoranthusEmortisReborn.LOGGER.warn("Skipped custom canis skin file: {} ({})", rl, exception);
//                }
//            }
//            try {
//                List<IResource> resourcelocation = resourceManager.getResources(OVERRIDE_RESOURCE_LOCATION);
//                this.loadOverrideData(resourcelocation);
//            } catch (FileNotFoundException e) {
//                // ;
//            }  catch (IOException | RuntimeException runtimeexception) {
//                RigoranthusEmortisReborn.LOGGER.warn("Unable to parse canis skin override data: {}", runtimeexception);
//            }
//        }
//    }
//
//    private synchronized void loadCanisSkinResource(IResource resource) {
//        InputStream inputstream = null;
//        try {
//            inputstream = resource.getInputStream();
//            String hash = this.getHash(IOUtils.toByteArray(inputstream));
//            ResourceLocation rl = resource.getLocation();
//            if (this.skinHashToLoc.containsKey(hash)) {
//                RigoranthusEmortisReborn.LOGGER.warn("The loaded resource packs contained a duplicate custom canis skin ({} & {})", resource.getLocation(), this.skinHashToLoc.get(hash));
//            } else {
//                RigoranthusEmortisReborn.LOGGER.info("Found custom canis skin at {} with hash {}", rl, hash);
//                this.skinHashToLoc.put(hash, rl);
//                this.locToSkinHash.put(rl, hash);
//                this.customSkinLoc.add(rl);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            IOUtils.closeQuietly(inputstream);
//        }
//    }
//
//    private void loadOverrideData(List<IResource> resourcesList) {
//        for (IResource iresource : resourcesList) {
//            InputStream inputstream = iresource.getInputStream();
//            try {
//                this.loadLocaleData(inputstream);
//            } finally {
//                IOUtils.closeQuietly(inputstream);
//            }
//        }
//    }
//
//    private synchronized void loadLocaleData(InputStream inputStreamIn) {
//        JsonElement jsonelement = GSON.fromJson(new InputStreamReader(inputStreamIn, StandardCharsets.UTF_8), JsonElement.class);
//        JsonObject jsonobject = JSONUtils.convertToJsonObject(jsonelement, "strings");
//        for (Entry<String, JsonElement> entry : jsonobject.entrySet()) {
//            String hash = entry.getKey();
//            ResourceLocation texture = new ResourceLocation(JSONUtils.convertToString(entry.getValue(), hash));
//            ResourceLocation previous = this.skinHashToLoc.put(hash, texture);
//            if (previous != null) {
//            } else {
//            }
//            RigoranthusEmortisReborn.LOGGER.info("Loaded override for {} -> {}", hash, texture);
//        }
//    }
//}