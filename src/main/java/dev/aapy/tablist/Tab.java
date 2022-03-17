package dev.aapy.tablist;

import dev.aapy.tablist.impl.v1_7TabImpl;
import dev.aapy.tablist.impl.v1_8TabImpl;
import dev.aapy.tablist.playerversion.PlayerVersionHandler;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public class Tab {

    @Getter
    private static Tab instance;

    private final JavaPlugin plugin;
    private final TabProvider provider;
    private final Map<UUID, PlayerTablist> tablists;
    private TabThread thread;
    private TabNMS implementation;
    private TabListener listeners;


    public Tab(JavaPlugin plugin, TabProvider provider) {
        instance = this;

        this.plugin = plugin;
        this.provider = provider;
        this.tablists = new ConcurrentHashMap<>();

        new PlayerVersionHandler();

        this.registerImplementation();

        this.setup();
    }

    private void registerImplementation() {
        String serverVersion = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];

        if (serverVersion.equalsIgnoreCase("v1_7_R4")) {
            this.implementation = new v1_7TabImpl();
            System.out.println("Registered Implementation with 1.7 Tab");
            return;
        }
        if (serverVersion.equalsIgnoreCase("v1_8_R3")) {
            this.implementation = new v1_8TabImpl();
            System.out.println("Registered Implementation with 1.8 Tab");
            return;
        }

        if (Bukkit.getPluginManager().getPlugin("ProtocolLib") != null) {
            plugin.getLogger().info("Registered Implementation with ProtocolLib");
            return;
        }

    }

    @SuppressWarnings("deprecation")
    private void setup() {
        listeners = new TabListener();
        //Register Events
        this.plugin.getServer().getPluginManager().registerEvents(listeners, this.plugin);

        //Ensure that the thread has stopped running
        if (this.thread != null) {
            this.thread.stop();
            this.thread = null;
        }

        //Start Thread
        this.thread = new TabThread(this);
    }

    @SuppressWarnings("deprecation")
    public void disable() {
        if (this.thread != null) {
            this.thread.stop();
            this.thread = null;
        }

        if (listeners != null) {
            HandlerList.unregisterAll(listeners);
            listeners = null;
        }
    }
}
