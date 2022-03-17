package dev.aapy.file;


import dev.aapy.SnakeHub;
import org.bukkit.configuration.file.*;
import org.bukkit.plugin.*;

import java.io.File;

/**
 * @author 7qv_ on 10/2/2022.
 * @project SnakeHub
 */
public class Tablist extends YamlConfiguration
{
    private static Tablist config;
    private Plugin plugin;
    private File configFile;

    public static Tablist getConfig() {
        if (Tablist.config == null) {
            Tablist.config = new Tablist();
        }
        return Tablist.config;
    }

    private Plugin main() {
        return (Plugin) SnakeHub.getInst();
    }

    public Tablist() {
        this.plugin = this.main();
        this.configFile = new File(this.plugin.getDataFolder(), "tab.yml");
        if (!this.configFile.exists()) {
            this.plugin.saveResource("tab.yml", false);
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
