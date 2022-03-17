package dev.aapy.listeners;

import dev.aapy.SnakeHub;
import dev.aapy.file.Config;
import dev.aapy.util.CC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * @author 7qv_ on 11/2/2022.
 * @project SnakeHub
 */
public class ChatFormatListener implements Listener {

    @EventHandler
    public void onChatFormat(AsyncPlayerChatEvent event) {
        Player p = event.getPlayer();

        for (String chat : Config.getConfig().getStringList("CHAT-FORMAT")) {

            chat = chat.replace("{prefix}", SnakeHub.getInst().getPermission().getPermission().getPrefix(p));
            chat = chat.replace("{ign}", p.getName());

            chat = chat.replace("{message}", event.getMessage());

            event.setFormat(CC.translate(chat));
        }
    }
}
