package com.gitlab.indicode.fabric.paperdoll;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;

/**
 * @author Indigo A.
 */
@me.sargunvohra.mcmods.autoconfig1u.annotation.Config(name = "paperdoll")
public class Config implements ConfigData {
    public int render_height = 50;
    public int render_width = 25;
    public int y = 10;
    public int x = 10;
    public int rotation = 20;
    public boolean change_swim_fly = true;
    public boolean dynamic_scale = false;
    public boolean only_activity = false;
}
