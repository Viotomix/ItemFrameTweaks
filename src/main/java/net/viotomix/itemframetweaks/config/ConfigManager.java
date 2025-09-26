package net.viotomix.itemframetweaks.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.viotomix.itemframetweaks.ItemFrameTweaks;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ConfigManager {
    // Code referenced from Patbox's Styled Nicknames mod, because I had no other frame of reference
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().setLenient().create();


    private static Config CONFIG;
    public static Config getConfig() {
        return CONFIG;
    }

    public static boolean loadConfig() {
        CONFIG = null;
        try {
            ConfigData config;
            File configFile = new File(FabricLoader.getInstance().getConfigDir().toFile(), "item-frame-tweaks.json");

            if(configFile.exists()) {
                String json = IOUtils.toString(new InputStreamReader(new FileInputStream(configFile), StandardCharsets.UTF_8));
                config = GSON.fromJson(json, ConfigData.class);
            } else {
                config = new ConfigData();
            }

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(configFile), StandardCharsets.UTF_8));
            writer.write(GSON.toJson(config));
            writer.close();

            CONFIG = new Config(config);
            return true;
        } catch(IOException exception) {
            ItemFrameTweaks.LOGGER.error("An error occured while reading the config file!");
            exception.printStackTrace();
            CONFIG = new Config(new ConfigData());
            return false;
        }
    }
}
