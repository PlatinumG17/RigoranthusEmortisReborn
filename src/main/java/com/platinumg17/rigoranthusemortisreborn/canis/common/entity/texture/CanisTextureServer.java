package com.platinumg17.rigoranthusemortisreborn.canis.common.entity.texture;
//
//import com.google.common.hash.Hashing;
//import com.platinumg17.rigoranthusemortisreborn.RigoranthusEmortisReborn;
//import com.platinumg17.rigoranthusemortisreborn.canis.common.util.REUtil;
//import net.minecraft.util.ResourceLocation;
//import net.minecraftforge.fml.server.ServerLifecycleHooks;
//import org.apache.commons.io.FileUtils;
//import org.apache.commons.io.IOUtils;
//import javax.annotation.Nullable;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//
//public class CanisTextureServer {
//
//    public static final CanisTextureServer INSTANCE = new CanisTextureServer();
//
//    /**
//     * Get the dedicated servers cache location
//     */
//    public File getServerFolder() {
//        return new File(ServerLifecycleHooks.getCurrentServer().getServerDirectory(), "canis_skins");
//    }
//
//    @Nullable
//    public byte[] getCachedBytes(File baseFolder, String hash) {
//        InputStream stream = getCachedStream(baseFolder, hash);
//        try {
//            return stream != null ? IOUtils.toByteArray(stream) : null;
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            IOUtils.closeQuietly(stream);
//        }
//        return null;
//    }
//
//    @Nullable
//    public InputStream getCachedStream(File baseFolder, String hash) {
//        File cacheFile = getCacheFile(baseFolder, hash);
//
//        if (cacheFile.isFile() && cacheFile.exists()) {
//            try {
//                FileInputStream stream = new FileInputStream(cacheFile);
//                RigoranthusEmortisReborn.LOGGER.debug("Loaded canis texture from local cache ({})", cacheFile);
//                return stream;
//            } catch (FileNotFoundException e) {
//                RigoranthusEmortisReborn.LOGGER.debug("Failed to load canis texture from local cache ({})", cacheFile);
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }
//
//    public String getHash(byte[] targetArray) {
//        return Hashing.sha1().hashBytes(targetArray).toString();
//    }
//
//    public File getCacheFile(File baseFolder, String name) {
//        File subFolder = new File(baseFolder, name.length() > 2 ? name.substring(0, 2) : "xx");
//        File cacheFile = new File(subFolder, name);
//        return cacheFile;
//    }
//
//    public ResourceLocation getResourceLocation(String name) {
//        return REUtil.getResource("canisskins/" + name);
//    }
//
//    /**
//     * Normally used to save server side
//     */
//    public boolean saveTexture(File baseFolder, byte[] stream) throws IOException {
//        String hash = getHash(stream);
//        File cacheFile = getCacheFile(baseFolder, hash);
//        if (!cacheFile.isFile()) {
//            RigoranthusEmortisReborn.LOGGER.debug("Saved canis texture to local cache ({})", cacheFile);
//            FileUtils.writeByteArrayToFile(cacheFile, stream);
//            return true;
//        }
//        RigoranthusEmortisReborn.LOGGER.debug("Server already has cache for canis texture ({})", cacheFile);
//        return false;
//    }
//}