package dev.aapy.listeners;

import dev.aapy.listeners.hotbar.PvPListener;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

/**
 * @author 7qv_ on 7/2/2022.
 * @project SnakeHub
 */
public class WorldListener implements Listener {

    @EventHandler
    public void onSpawnMobs(CreatureSpawnEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player p = event.getPlayer();
        if (p.hasPermission("hub.build")) {
            event.setCancelled(false);
        } else {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDropItems(PlayerDropItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onSpawn(EntitySpawnEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPvP(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            if (event.getEntity() instanceof Player) {

                Player damaged = (Player) event.getEntity();
                Player damager = (Player) event.getDamager();

                if (PvPListener.pvpEnable.contains(damaged) && PvPListener.pvpEnable.contains(damager)) {
                    event.setCancelled(false);
                } else {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onClickInventory(InventoryClickEvent event) {
        if (event.getWhoClicked().getGameMode().equals((Object) GameMode.CREATIVE)) {
            event.setCancelled(false);
            return;
        }
        event.setCancelled(true);
    }


    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Player p = event.getPlayer();
        if (p.hasPermission("hub.build")) {
            event.setCancelled(false);
        } else {
          event.setCancelled(true);
        }
    }
}
