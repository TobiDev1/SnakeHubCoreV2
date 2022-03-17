package dev.aapy.tablist.playerversion;

import dev.aapy.tablist.playerversion.impl.PlayerVersion1_7Impl;
import dev.aapy.tablist.playerversion.impl.PlayerVersionProtocolLibImpl;
import dev.aapy.tablist.playerversion.impl.PlayerVersionViaVersionImpl;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;

public class PlayerVersionHandler {

    public static IPlayerVersion version;

    public PlayerVersionHandler() {
        /* Plugin Manager */
        PluginManager pluginManager = Bukkit.getServer().getPluginManager();

        /* 1.7 Protocol */
        String serverVersion = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];

        if (serverVersion.equalsIgnoreCase("v1_7_R4")) {
            version = new PlayerVersion1_7Impl();
            return;
        }

        /* ViaVersion */
        if (pluginManager.getPlugin("ViaVersion") != null) {
            version = new PlayerVersionViaVersionImpl();
            return;
        }

        /* ProtocolLib */
        if (pluginManager.getPlugin("ProtocolLib") != null) {
            version = new PlayerVersionProtocolLibImpl();
            return;
        }
    }

    public static PlayerVersion getPlayerVersion(Player player) {
        return version.getPlayerVersion(player);
    }
}
