package dev.aapy.listeners.hotbar;

import dev.aapy.file.Config;
import dev.aapy.file.Message;
import dev.aapy.util.CC;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Iterator;

/**
 * @author 7qv_ on 14/3/2022.
 * @project SnakeHub
 */
public class PlayerInvisibilityListener implements Listener {


    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        addItem(p);
    }

    public void addItem(Player p) {
        if (Config.getConfig().getBoolean("INVISIBILITY.ENABLED")) {
            p.getInventory().setItem(Config.getConfig().getInt("INVISIBILITY.ITEM.SHOW-PLAYER.SLOT"), ShowPlayer());
        }
    }

    public static ItemStack ShowPlayer() {
        ItemStack shows = new ItemStack(Material.getMaterial(Config.getConfig().getString("INVISIBILITY.ITEM.SHOW-PLAYER.MATERIAL")));
        ItemMeta showsm = shows.getItemMeta();
        showsm.setDisplayName(CC.translate(Config.getConfig().getString("INVISIBILITY.ITEM.SHOW-PLAYER.NAME")));
        shows.setItemMeta(showsm);
        return shows;
    }

    public static ItemStack HidePlayer() {
        ItemStack hides = new ItemStack(Material.getMaterial(Config.getConfig().getString("INVISIBILITY.ITEM.HIDE-PLAYER.MATERIAL")));
        ItemMeta hidesm = hides.getItemMeta();
        hidesm.setDisplayName(CC.translate(Config.getConfig().getString("INVISIBILITY.ITEM.HIDE-PLAYER.NAME")));
        hides.setItemMeta(hidesm);
        return hides;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onInvis(final PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack stack = player.getItemInHand();
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
            Iterator iterator;
            Player player1;
            if (stack != null && stack.isSimilar(HidePlayer())) {
                event.setCancelled(true);
                iterator = Bukkit.getServer().getOnlinePlayers().iterator();

                while (iterator.hasNext()) {
                    player1 = (Player) iterator.next();
                    player.hidePlayer(player1);
                }

                player.setItemInHand(ShowPlayer());
                player.updateInventory();
                player.playSound(player.getLocation(), Sound.CLICK, 1.0F, 1.0F);
                player.sendMessage(CC.translate(Message.getConfig().getString("HIDE-PLAYER")));
            }

            if (stack != null && stack.isSimilar(ShowPlayer())) {
                event.setCancelled(true);
                iterator = Bukkit.getServer().getOnlinePlayers().iterator();

                while (iterator.hasNext()) {
                    player1 = (Player) iterator.next();
                    player.showPlayer(player1);
                }

                player.setItemInHand(HidePlayer());
                player.updateInventory();
                player.playSound(player.getLocation(), Sound.CLICK, 1.0F, 1.0F);
                player.sendMessage(CC.translate(Message.getConfig().getString("SHOW-PLAYER")));
            }
        }
    }
}