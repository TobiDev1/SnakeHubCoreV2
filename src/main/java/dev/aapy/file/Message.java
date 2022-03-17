package dev.aapy.file;
import dev.aapy.SnakeHub;
import org.bukkit.configuration.file.*;
import org.bukkit.plugin.*;

import java.io.File;

/**
 * @author 7qv_ on 9/2/2022.
 * @project SnakeHub
 */
public class Message extends YamlConfiguration
{
    private static Message config;
    private Plugin plugin;
    private File configFile;

    public static Message getConfig() {
        if (Message.config == null) {
            Message.config = new Message();
        }
        return Message.config;
    }

    private Plugin main() {
        return (Plugin) SnakeHub.getInst();
    }

    public Message() {
        this.plugin = this.main();
        this.configFile = new File(this.plugin.getDataFolder(), "lang.yml");
        if (!this.configFile.exists()) {
            this.plugin.saveResource("lang.yml", false);
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
