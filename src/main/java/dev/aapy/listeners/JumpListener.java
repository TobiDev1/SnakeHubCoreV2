package dev.aapy.listeners;

import dev.aapy.SnakeHub;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.plugin.Plugin;

/**
 * @author 7qv_ on 17/2/2022.
 * @project SnakeHub
 */
public class JumpListener implements Listener {

    @EventHandler
    public void onPlayerToggleFlight(final PlayerToggleFlightEvent event) {
        final Player player = event.getPlayer();
        final Sound sound = Sound.ZOMBIE_INFECT;
        if (player.getGameMode() == GameMode.CREATIVE) {
            return;
        }
        event.setCancelled(true);
        player.setAllowFlight(false);
        player.setFlying(false);
        player.setVelocity(player.getLocation().getDirection().multiply(1.5).setY(1));
        player.playSound(player.getLocation(), sound, 1.0f, 0.0f);
    }

    @EventHandler
    public void onPlayerMove(final PlayerMoveEvent event) {
        final Player player = event.getPlayer();
        if (player.getGameMode() != GameMode.CREATIVE && player.getLocation().subtract(0.0, 1.0, 0.0).getBlock().getType() != Material.AIR && !player.isFlying()) {
            player.setAllowFlight(true);
        }
    }

    @EventHandler
    public void onFallDamage(final EntityDamageEvent e) {
        if (e.getEntity() instanceof Player && e.getCause() == EntityDamageEvent.DamageCause.FALL) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onVoidDamage(final EntityDamageEvent e) {
        if (e.getEntity() instanceof  Player && e.getCause() == EntityDamageEvent.DamageCause.VOID) {
            Player p = (Player) e;

            e.setCancelled(true);
            p.teleport(p.getWorld().getSpawnLocation());
        }
    }

    @EventHandler
    public void move(final PlayerMoveEvent e) {
        final Player f = e.getPlayer();
        if (e.getTo().getY() < 2.0) {
            SnakeHub.getInst().getServer().getScheduler().scheduleSyncDelayedTask((Plugin) SnakeHub.getInst(), (Runnable)new Runnable() {
                @Override
                public void run() {
                    final double y = f.getLocation().getY() - 2.0;
                    final Location l = new Location(f.getLocation().getWorld(), f.getLocation().getX(), y, f.getLocation().getZ(), f.getLocation().getYaw(), f.getLocation().getPitch());
                    f.getWorld().playEffect(l, Effect.ENDER_SIGNAL, 50, 30);
                }
            }, 10L);
        }
    }
}

