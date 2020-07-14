package com.gitlab.indicode.fabric.paperdoll;

import io.github.prospector.modmenu.api.ConfigScreenFactory;
import io.github.prospector.modmenu.api.ModMenuApi;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;

/**
 * @author Indigo A.
 */
@Environment(EnvType.CLIENT)
public class ModMenuIntegration implements ModMenuApi {
    public String getModId() {
        return "paperdoll";
    }

    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return screen -> (Screen)AutoConfig.getConfigScreen(Config.class, screen).get();
    }
}
