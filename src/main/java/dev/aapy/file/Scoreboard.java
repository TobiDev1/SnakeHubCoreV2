package dev.aapy.file;

import dev.aapy.SnakeHub;
import org.bukkit.configuration.file.*;
import org.bukkit.plugin.*;

import java.io.File;

/**
 * @author 7qv_ on 9/2/2022.
 * @project SnakeHub
 */
public class Scoreboard extends YamlConfiguration
{
    private static Scoreboard config;
    private Plugin plugin;
    private File configFile;

    public static Scoreboard getConfig() {
        if (Scoreboard.config == null) {
            Scoreboard.config = new Scoreboard();
        }
        return Scoreboard.config;
    }

    private Plugin main() {
        return (Plugin) SnakeHub.getInst();
    }

    public Scoreboard() {
        this.plugin = this.main();
        this.configFile = new File(this.plugin.getDataFolder(), "scoreboard.yml");
        if (!this.configFile.exists()) {
            this.plugin.saveResource("scoreboard.yml", false);
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
