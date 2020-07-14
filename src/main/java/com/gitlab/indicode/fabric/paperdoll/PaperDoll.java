package com.gitlab.indicode.fabric.paperdoll;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;

/**
 * @author Indigo A.
 */
public class PaperDoll implements ModInitializer {
    public static Config config;

    @Override
    public void onInitialize() {
        AutoConfig.register(Config.class, Toml4jConfigSerializer::new);
        config = AutoConfig.getConfigHolder(Config.class).getConfig();
    }
}
