package dev.aapy.listeners;

import dev.aapy.file.Config;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

/**
 * @author 7qv_ on 17/2/2022.
 * @project SnakeHub
 */
public class LaunchPadListener implements Listener {

    @EventHandler
    public void onUseLaunch(PlayerMoveEvent event) {
        Player p = event.getPlayer();
        if (p.getLocation().getBlock().getType() == Material.getMaterial(Config.getConfig().getString("LAUNCH-PAD.MATERIAL"))) {
            Vector vector = p.getLocation().getDirection().multiply(2.0).setY(1.0);
            p.setVelocity(vector);

            p.playSound(p.getLocation(), Sound.valueOf(Config.getConfig().getString("LAUNCH-PAD.SOUND")), 2.0f, 2.0f);

        }


    }
}
