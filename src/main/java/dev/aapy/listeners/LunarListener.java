package dev.aapy.listeners;

import com.lunarclient.bukkitapi.LunarClientAPI;
import dev.aapy.SnakeHub;
import dev.aapy.util.CC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 7qv_ on 9/2/2022.
 * @project SnakeHub
 */
public class LunarListener implements Listener {

    public void updateNameTag( Player player) {
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
            Player player2 = onlinePlayer.getPlayer();
            scheduler.scheduleSyncRepeatingTask((Plugin) SnakeHub.getInst(), () -> LunarClientAPI.getInstance().overrideNametag(player2, (List)this.resetNameTag(player2), player), 0L, 20L);
        }
    }

    public List<String> resetNameTag(Player player) {
        List<String> tag = new ArrayList<String>(); {
            tag.add(CC.translate(SnakeHub.getInst().getPermission().getPermission().getPrefix(player)));
        }
        tag.add(CC.translate(SnakeHub.getInst().getPermission().getPermission().getPrefix(player) + player.getName()));
        return tag;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        this.updateNameTag(player);
    }
}