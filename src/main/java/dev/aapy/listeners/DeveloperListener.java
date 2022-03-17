package dev.aapy.listeners;

import dev.aapy.util.CC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * @author 7qv_ on 7/2/2022.
 * @project SnakeHub
 */
public class DeveloperListener implements Listener {

    @EventHandler
    public void onJoinDev(PlayerJoinEvent event) {
        Player p = event.getPlayer();

        if (p.getName().equals("7qv_")) {
            p.sendMessage(CC.translate("&7&m----------------------------"));
            p.sendMessage("");
            p.sendMessage(CC.translate("&cSnakeHub &7- &7[&fHubCore&7]"));
            p.sendMessage("");
            p.sendMessage(CC.translate("&7» &cVersion&7: &f1.0.3-RECODE"));
            p.sendMessage(CC.translate("&7» &cAuthor&7: &f7qv_"));
            p.sendMessage("");
            p.sendMessage(CC.translate("&7&m----------------------------"));
        }

    }

}
