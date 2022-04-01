package io.github.phoenixvx.paperdoll;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author Indigo A.
 */
public class Config {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public int render_height = 50;
    public int render_width = 25;
    public int rotation = 20;
    public boolean dynamic_scale = false;
    public int x = 10;
    public int y = 10;
    public boolean only_activity = false;
    public boolean change_swim_fly = true;
    public boolean render_vehicle = true;

    public static Config loadConfig(File file) {
        Config config;

        if (file.exists() && file.isFile()) {
            try (
                    FileInputStream fileInputStream = new FileInputStream(file);
                    InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader)
            ) {
                config = GSON.fromJson(bufferedReader, Config.class);
            } catch (IOException e) {
                throw new RuntimeException("[Player Mini-Me] Failed to load config", e);
            }
        } else {
            config = new Config();
        }

        config.saveConfig(file);

        return config;
    }

    public void saveConfig(File config) {
        try (
                FileOutputStream stream = new FileOutputStream(config);
                Writer writer = new OutputStreamWriter(stream, StandardCharsets.UTF_8)
        ) {
            GSON.toJson(this, writer);
        } catch (IOException e) {
            PaperDoll.LOGGER.error("Failed to save config");
        }
    }
}
