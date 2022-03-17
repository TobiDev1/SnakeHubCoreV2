package dev.aapy.listeners.hotbar;

import dev.aapy.file.Config;
import dev.aapy.util.CC;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

/**
 * @author 7qv_ on 7/2/2022.
 * @project SnakeHub
 */
public class EnderButtListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();

            ItemStack enderbutt = new ItemStack(Material.getMaterial(Config.getConfig().getString("ENDERBUTT.MATERIAL")));
            ItemMeta endermeta = enderbutt.getItemMeta();
            endermeta.setDisplayName(CC.translate(Config.getConfig().getString("ENDERBUTT.DISPLAYNAME")));
            ArrayList<String> lore = new ArrayList<>();
            lore.add(CC.translate(Config.getConfig().getString("ENDERBUTT.LORE")));
            endermeta.addEnchant(Enchantment.DURABILITY, 3, Config.getConfig().getBoolean("ENDERBUTT.ENCHANTED"));
            endermeta.setLore(lore);
            enderbutt.setItemMeta(endermeta);
            if (Config.getConfig().getBoolean("ENDERBUTT.ENABLED")) {
                p.getInventory().setItem(Config.getConfig().getInt("ENDERBUTT.SLOT"), enderbutt);
            }
    }


    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.hasItem()) {
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (event.getItem().getItemMeta() == null) {
                    return;
                }
                if (event.getItem().getItemMeta().getDisplayName() == null) {
                    return;
                }
                if (event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(CC.translate(Config.getConfig().getString("ENDERBUTT.DISPLAYNAME")))) {
                    event.getPlayer().setVelocity(event.getPlayer().getLocation().getDirection().multiply(2.5F));

                    event.setCancelled(true);
                    event.setUseItemInHand(Event.Result.DENY);

                    event.getPlayer().updateInventory();

                    event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.valueOf(Config.getConfig().getString("ENDERBUTT.SOUND")), 2.0f, 2.0f);
                }
            }
        }
    }
}
