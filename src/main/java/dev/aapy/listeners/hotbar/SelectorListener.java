package dev.aapy.listeners.hotbar;

import dev.aapy.file.Config;
import dev.aapy.util.ArmorCreator;
import dev.aapy.util.CC;
import dev.aapy.util.bungee.BungeeChannel;
import me.clip.placeholderapi.PlaceholderAPI;
import me.signatured.ezqueuespigot.EzQueueAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

/**
 * @author 7qv_ on 16/2/2022.
 * @project SnakeHub
 */
public class SelectorListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        ItemStack selectormenu = new ItemStack(Material.valueOf(Config.getConfig().getString("SELECTOR.MATERIAL")));
        ItemMeta selectormeta = selectormenu.getItemMeta();
        selectormeta.setDisplayName(CC.translate(Config.getConfig().getString("SELECTOR.DISPLAYNAME")));
        ArrayList<String> lore = new ArrayList<>();
        lore.add(CC.translate(Config.getConfig().getString("SELECTOR.LORE")));
        selectormeta.setLore(lore);
        if (Config.getConfig().getBoolean("SELECTOR.ENCHANTED"))
            selectormeta.addEnchant(Enchantment.DURABILITY, 3, true);
        selectormenu.setItemMeta(selectormeta);
        if (Config.getConfig().getBoolean("SELECTOR.ENABLED"))
            player.getInventory().setItem(Config.getConfig().getInt("SELECTOR.SLOT"), selectormenu);
    }

    @EventHandler
    public void onInteractSelector(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player player = event.getPlayer();
            if (player.getInventory().getItemInHand().getType() == Material.valueOf(Config.getConfig().getString("SELECTOR.MATERIAL"))) {
                if (Config.getConfig().getBoolean("SELECTOR.SOUND_ENABLED"))
                    player.playSound(player.getLocation(), Sound.valueOf(Config.getConfig().getString("SELECTOR.SOUND")), 2.0F, 2.0F);
                Inventory inv = Bukkit.createInventory(null, 27, CC.translate(Config.getConfig().getString("SELECTOR.TITLE")));
                ItemStack item = new ItemStack(Material.valueOf(Config.getConfig().getString("SELECTOR.SERVERS.1.MATERIAL")));
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(CC.translate(Config.getConfig().getString("SELECTOR.SERVERS.1.DISPLAYNAME")));
                ArrayList<String> lore = new ArrayList<>();
                lore.addAll(CC.translate(PlaceholderAPI.setPlaceholders(player, Config.getConfig().getStringList("SELECTOR.SERVERS.1.LORE"))));
                if (Config.getConfig().getBoolean("SELECTOR.SERVERS.1.ENCHANTED"))
                    meta.addEnchant(Enchantment.DURABILITY, 3, true);
                meta.setLore(lore);
                item.setItemMeta(meta);
                ItemStack item2 = new ItemStack(Material.valueOf(Config.getConfig().getString("SELECTOR.SERVERS.2.MATERIAL")));
                ItemMeta itemMeta1 = item2.getItemMeta();
                itemMeta1.setDisplayName(CC.translate(Config.getConfig().getString("SELECTOR.SERVERS.2.DISPLAYNAME")));
                ArrayList<String> arrayList1 = new ArrayList<>();
                arrayList1.addAll(CC.translate(PlaceholderAPI.setPlaceholders(player, Config.getConfig().getStringList("SELECTOR.SERVERS.2.LORE"))));
                if (Config.getConfig().getBoolean("SELECTOR.SERVERS.2.ENCHANTED"))
                    itemMeta1.addEnchant(Enchantment.DURABILITY, 3, true);
                itemMeta1.setLore(arrayList1);
                item2.setItemMeta(itemMeta1);
                ItemStack item3 = new ItemStack(Material.valueOf(Config.getConfig().getString("SELECTOR.SERVERS.3.MATERIAL")));
                ItemMeta itemMeta2 = item3.getItemMeta();
                itemMeta2.setDisplayName(CC.translate(Config.getConfig().getString("SELECTOR.SERVERS.3.DISPLAYNAME")));
                ArrayList<String> arrayList2 = new ArrayList<>();
                arrayList2.addAll(CC.translate(PlaceholderAPI.setPlaceholders(player, Config.getConfig().getStringList("SELECTOR.SERVERS.3.LORE"))));
                if (Config.getConfig().getBoolean("SELECTOR.SERVERS.3.ENCHANTED"))
                    itemMeta2.addEnchant(Enchantment.DURABILITY, 3, true);
                itemMeta2.setLore(arrayList2);
                item3.setItemMeta(itemMeta2);
                inv.setItem(Config.getConfig().getInt("SELECTOR.SERVERS.1.SLOT"), item);
                inv.setItem(Config.getConfig().getInt("SELECTOR.SERVERS.2.SLOT"), item2);
                inv.setItem(Config.getConfig().getInt("SELECTOR.SERVERS.3.SLOT"), item3);
                if (Config.getConfig().getBoolean("SELECTOR.GLASS_PANEL.ENABLED")) {
                    ItemStack panel = (new ArmorCreator(Material.STAINED_GLASS_PANE, 1, (short)Config.getConfig().getInt("SELECTOR.GLASS_PANEL.ID"))).setName("").build();
                    for (int i = 0; i < inv.getSize(); i++) {
                        if (inv.getItem(i) == null)
                            inv.setItem(i, panel);
                    }
                }
                if (player.isOnline())
                    player.openInventory(inv);
            }
        }
    }

    @EventHandler
    public void onClickItemSelector(InventoryClickEvent event) {
        Player player = (Player)event.getWhoClicked();
        if (event.getInventory().getName().equalsIgnoreCase(CC.translate(Config.getConfig().getString("SELECTOR.TITLE"))) &&
                event.getCurrentItem().getType() == Material.valueOf(Config.getConfig().getString("SELECTOR.SERVERS.1.MATERIAL"))) {
                player.sendMessage(CC.translate(Config.getConfig().getString("SELECTOR.SERVERS.1.MESSAGE")));
            if (Config.getConfig().getBoolean("SELECTOR.SERVERS.1.BUNGEE.ENABLED")) {
                BungeeChannel.sendToServer(player, Config.getConfig().getString("SELECTOR.SERVERS.1.BUNGEE.SERVER"));
            } else {
                if (Config.getConfig().getBoolean("SELECTOR.SERVERS.1.EZQUEUE.ENABLED")) {
                    EzQueueAPI.addToQueue(player, Config.getConfig().getString("SELECTOR.SERVERS.1.EZQUEUE.QUEUE"));
                } else {
                    player.sendMessage(CC.translate("&cPlease activate the mode of travel to the mode."));
                }
            }
            event.setCancelled(true);
            player.closeInventory();
        }
        if (event.getCurrentItem().getType() == Material.valueOf(Config.getConfig().getString("SELECTOR.SERVERS.2.MATERIAL"))) {
            player.sendMessage(CC.translate(Config.getConfig().getString("SELECTOR.SERVERS.2.MESSAGE")));
            event.setCancelled(true);
            player.closeInventory();
            if (Config.getConfig().getBoolean("SELECTOR.SERVERS.2.BUNGEE.ENABLED")) {
                BungeeChannel.sendToServer(player, Config.getConfig().getString("SELECTOR.SERVERS.2.BUNGEE.SERVER"));
            } else {
                if (Config.getConfig().getBoolean("SELECTOR.SERVERS.2.EZQUEUE.ENABLED")) {
                    EzQueueAPI.addToQueue(player, Config.getConfig().getString("SELECTOR.SERVERS.2.EZQUEUE.QUEUE"));
                } else {
                    player.sendMessage(CC.translate("&cPlease activate the mode of travel to the mode."));
                }
            }
        }
        if (event.getCurrentItem().getType() == Material.valueOf(Config.getConfig().getString("SELECTOR.SERVERS.3.MATERIAL"))) {
            event.setCancelled(true);
            player.closeInventory();
            player.sendMessage(CC.translate(Config.getConfig().getString("SELECTOR.SERVERS.3.MESSAGE")));
            if (Config.getConfig().getBoolean("SELECTOR.SERVERS.3.BUNGEE.ENABLED")) {
                BungeeChannel.sendToServer(player, Config.getConfig().getString("SELECTOR.SERVERS.3.BUNGEE.SERVER"));
            } else {
                if (Config.getConfig().getBoolean("SELECTOR.SERVERS.3.EZQUEUE.ENABLED")) {
                    EzQueueAPI.addToQueue(player, Config.getConfig().getString("SELECTOR.SERVERS.3.EZQUEUE.QUEUE"));
                } else {
                    player.sendMessage(CC.translate("&cPlease activate the mode of travel to the mode."));
                }
            }
        }
    }
}
