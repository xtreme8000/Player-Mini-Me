package com.gitlab.indicode.fabric.paperdoll;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfiglite.api.ConfigScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.text.TranslatableText;

/**
 * @author Indigo A.
 */
@Environment(EnvType.CLIENT)
public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            ConfigScreen screen = ConfigScreen.create(new TranslatableText("text.paperdoll.title"), parent);

            screen.add(
                    new TranslatableText("text.paperdoll.option.render_height"),
                    PaperDoll.config.render_height,
                    () -> 50,
                    o -> {
                        PaperDoll.config.render_height = (int) o;
                        PaperDoll.saveConfig();
                    }
            );

            screen.add(
                    new TranslatableText("text.paperdoll.option.render_width"),
                    PaperDoll.config.render_width,
                    () -> 25,
                    o -> {
                        PaperDoll.config.render_width = (int) o;
                        PaperDoll.saveConfig();
                    }
            );

            screen.add(
                    new TranslatableText("text.paperdoll.option.rotation"),
                    PaperDoll.config.rotation,
                    () -> 20,
                    o -> {
                        PaperDoll.config.rotation = (int) o;
                        PaperDoll.saveConfig();
                    }
            );

            screen.add(
                    new TranslatableText("text.paperdoll.option.dynamic_scale"),
                    PaperDoll.config.dynamic_scale,
                    () -> false,
                    o -> {
                        PaperDoll.config.dynamic_scale = (boolean) o;
                        PaperDoll.saveConfig();
                    }
            );

            screen.add(
                    new TranslatableText("text.paperdoll.option.x"),
                    PaperDoll.config.x,
                    () -> 10,
                    o -> {
                        PaperDoll.config.x = (int) o;
                        PaperDoll.saveConfig();
                    }
            );

            screen.add(
                    new TranslatableText("text.paperdoll.option.y"),
                    PaperDoll.config.y,
                    () -> 10,
                    o -> {
                        PaperDoll.config.y = (int) o;
                        PaperDoll.saveConfig();
                    }
            );

            screen.add(
                    new TranslatableText("text.paperdoll.option.only_activity"),
                    PaperDoll.config.only_activity,
                    () -> false,
                    o -> {
                        PaperDoll.config.only_activity = (boolean) o;
                        PaperDoll.saveConfig();
                    }
            );

            screen.add(
                    new TranslatableText("text.paperdoll.option.change_swim_fly"),
                    PaperDoll.config.change_swim_fly,
                    () -> true,
                    o -> {
                        PaperDoll.config.change_swim_fly = (boolean) o;
                        PaperDoll.saveConfig();
                    }
            );

            return screen.get();
        };
    }
}
