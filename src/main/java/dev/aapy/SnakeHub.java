package dev.aapy;

import dev.aapy.file.Config;
import dev.aapy.file.Message;
import dev.aapy.file.Scoreboard;
import dev.aapy.file.Tablist;
import dev.aapy.listeners.hotbar.PvPListener;
import dev.aapy.manager.Manager;
import dev.aapy.manager.managers.PermissionManager;
import dev.aapy.tablist.Tab;
import dev.aapy.tablist.provider.TablistProvider;
import dev.aapy.util.CC;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class SnakeHub extends JavaPlugin {

    private static SnakeHub plugin;
    private PermissionManager permission;
    private Manager manager;
    private PvPListener pvpListener;

    @Override
    public void onEnable() {
        plugin = this;

        this.manager = new Manager(this);
        this.manager.enable();
        CC.log("&cSnakeHub &f" + this.manager.getManagers().size() + " &amanagers have been registered");

        CC.log("&f");
        this.permission = new PermissionManager(this);
        this.permission.loadHook();

        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        CC.log("&aBungeeCoord connecting!");

        Message.getConfig().load();
        Scoreboard.getConfig().load();
        Tablist.getConfig().load();
        Config.getConfig().load();

        CC.log("&7&m=========================");
        CC.log("");
        CC.log("&cPlugin Name: &fSnakeHub");
        CC.log("&cVersion: &f1.2.0-HOTFIX");
        CC.log("&cAuthor: &fAapy#0001");
        CC.log("");
        CC.log("&7&m=========================");

        for (World world : Bukkit.getWorlds()) {
            world.setGameRuleValue("doDaylightCycle", "false");
            world.setGameRuleValue("doMobSpawning", "false");
            world.setTime(6000L);
        }

        if (Config.getConfig().getBoolean("BOOLEANS.TABLIST")) {
            new Tab(this, new TablistProvider());
        }

        Message.getConfig().save();
        Scoreboard.getConfig().save();
        Tablist.getConfig().save();
        Config.getConfig().save();
    }

    @Override
    public void onDisable() {
        this.manager.disable();
        Message.getConfig().save();
        Scoreboard.getConfig().save();
        Tablist.getConfig().save();
        Config.getConfig().save();
    }

    public static SnakeHub getInst() {
        return plugin;
    }
}