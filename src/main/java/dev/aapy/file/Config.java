package dev.aapy.file;

import dev.aapy.SnakeHub;
import org.bukkit.configuration.file.*;
import org.bukkit.plugin.*;

import java.io.File;

/**
 * @author 7qv_ on 8/2/2022.
 * @project SnakeHub
 */
public class Config extends YamlConfiguration
{
    private static Config config;
    private Plugin plugin;
    private File configFile;

    public static Config getConfig() {
        if (Config.config == null) {
            Config.config = new Config();
        }
        return Config.config;
    }

    private Plugin main() {
        return (Plugin) SnakeHub.getInst();
    }

    public Config() {
        this.plugin = this.main();
        this.configFile = new File(this.plugin.getDataFolder(), "config.yml");
        if (!this.configFile.exists()) {
            this.plugin.saveResource("config.yml", false);
        }
        this.reload();
    }

    public void reload() {
        try {
            super.load(this.configFile);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void load() {
        try {
            super.save(this.configFile);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        this.load();
        this.reload();
    }
}
