package net.viotomix.itemframetweaks;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.viotomix.itemframetweaks.config.ConfigManager;

public class ItemFrameTweaks implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger("ItemFrameTweaks");

    @Override
    public void onInitialize() {
        ClientLifecycleEvents.CLIENT_STARTED.register((s) -> {
            ConfigManager.loadConfig();
        });
    }
}
