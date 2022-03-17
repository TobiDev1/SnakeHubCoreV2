package dev.aapy.listeners;

import com.lunarclient.bukkitapi.LunarClientAPI;
import com.lunarclient.bukkitapi.nethandler.client.LCPacketTitle;
import dev.aapy.SnakeHub;
import dev.aapy.file.Config;
import dev.aapy.file.Message;
import dev.aapy.util.CC;
import dev.aapy.util.ArmorCreator;
import me.activated.core.plugin.AquaCoreAPI;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.concurrent.TimeUnit;

/**
 * @author 7qv_ on 7/2/2022.
 * @project SnakeHub
 */
public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        event.setJoinMessage(null);

        p.setHealth(20);
        p.setFoodLevel(20);
        p.getInventory().setHelmet(new ArmorCreator(Material.LEATHER_HELMET).setColor(Color.AQUA).create());
        p.getInventory().setChestplate(new ArmorCreator(Material.LEATHER_CHESTPLATE).setColor(Color.AQUA).create());
        p.getInventory().setLeggings(new ArmorCreator(Material.LEATHER_LEGGINGS).setColor(Color.AQUA).create());
        p.getInventory().setBoots(new ArmorCreator(Material.LEATHER_BOOTS).setColor(Color.AQUA).create());
        p.playSound(p.getLocation(), Sound.WITHER_SPAWN, 1F, 1F);
        // MESSAGE
        if (Message.getConfig().getBoolean("TITLE.ENABLED")) {
            LunarClientAPI.getInstance().sendPacket(p, new LCPacketTitle("TITLE", CC.translate(PlaceholderAPI.setPlaceholders(p, Message.getConfig().getString("TITLE.JOIN.TITLE.MESSAGE"))), TimeUnit.MILLISECONDS.toSeconds(10), TimeUnit.MILLISECONDS.toSeconds(10), TimeUnit.MILLISECONDS.toSeconds(10)));
            LunarClientAPI.getInstance().sendPacket(p, new LCPacketTitle("SUBTITLE", CC.translate(PlaceholderAPI.setPlaceholders(p, Message.getConfig().getString("TITLE.JOIN.SUBTITLE.MESSAGE"))), TimeUnit.MILLISECONDS.toSeconds(10), TimeUnit.MILLISECONDS.toSeconds(10), TimeUnit.MILLISECONDS.toSeconds(10)));
        }
        if (Config.getConfig().getBoolean("BOOLEANS.JOIN-MESSAGE")) {
            for (final String msg : Message.getConfig().getStringList("JOIN.MESSAGE")) {
                p.sendMessage(CC.translate(msg)
                        .replace("{ign}", p.getName())
                        .replace("{rank}", CC.translate(SnakeHub.getInst().getPermission().getPermission().getPrefix(p)))
                        .replace("{store}", CC.translate(Config.getConfig().getString("SOCIAL.STORE")))
                        .replace("{team-speak}", CC.translate(Config.getConfig().getString("SOCIAL.TEAMSPEAK")))
                        .replace("{twitter}", CC.translate(Config.getConfig().getString("SOCIAL.TWITTER")))
                        .replace("{discord}", CC.translate(Config.getConfig().getString("SOCIAL.DISCORD")))
                        .replace("{web-site}", CC.translate(Config.getConfig().getString("SOCIAL.WEBSITE"))));
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getCause() == EntityDamageEvent.DamageCause.FALL) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDisconect(PlayerQuitEvent event) {
        Player p = event.getPlayer();
        event.setQuitMessage(null);
        p.getInventory().clear();

        if (Config.getConfig().getBoolean("BOOLEANS.QUIT-MESSAGE")) {
            for (final String msg : Message.getConfig().getStringList("QUIT.MESSAGE")) {
                p.sendMessage(CC.translate(msg)
                        .replace("{ign}", p.getName())
                        .replace("{rank}", CC.translate(AquaCoreAPI.INSTANCE.getPlayerRank(p.getUniqueId()).getColor().toString() + AquaCoreAPI.INSTANCE.getPlayerRank(p.getUniqueId()).getPrefix().toString())));
            }
        }
    }

    @EventHandler
    public void onFoodChange(FoodLevelChangeEvent event) {
        if (event.getEntityType() == EntityType.PLAYER) {
            Player p = (Player) event.getEntity();
            event.setCancelled(true);
            if (p.getFoodLevel() < 19.0)
                p.setFoodLevel(20);
        }
    }
}