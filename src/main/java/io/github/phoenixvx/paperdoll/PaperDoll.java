package io.github.phoenixvx.paperdoll;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

/**
 * @author Indigo A.
 */
public class PaperDoll implements ModInitializer {
    public static final String MOD_ID = "minime_paperdoll";
    public static Config config;
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    public static final File configFile = new File(FabricLoader.getInstance().getConfigDir().toFile(), "paperdoll.json");

    @Override
    public void onInitialize() {
        config = Config.loadConfig(configFile);
    }

    public static void saveConfig () {
        config.saveConfig(configFile);
    }
}
