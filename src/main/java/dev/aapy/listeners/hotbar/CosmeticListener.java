package dev.aapy.listeners.hotbar;

import dev.aapy.file.Config;
import dev.aapy.listeners.hotbar.particles.Particles;
import dev.aapy.util.ArmorCreator;
import dev.aapy.util.ItemCreator;
import dev.aapy.util.CC;
import org.bukkit.Bukkit;
import org.bukkit.Material;
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
import java.util.Arrays;
import java.util.List;

/**
 * @author 7qv_ on 15/2/2022.
 * @project SnakeHub
 */
public class CosmeticListener implements Listener {

@EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();

        ItemStack hubmenu = new ItemStack(Material.getMaterial(Config.getConfig().getString("COSMETIC.MATERIAL")));
        ItemMeta hubmeta = hubmenu.getItemMeta();
        hubmeta.setDisplayName(CC.translate(Config.getConfig().getString("COSMETIC.DISPLAYNAME")));
        ArrayList<String> lore = new ArrayList<>();
        lore.add(CC.translate(Config.getConfig().getString("COSMETIC.LORE")));
        hubmeta.addEnchant(Enchantment.DURABILITY, 3, Config.getConfig().getBoolean("COSMETIC.ENCHANTED"));
        hubmeta.setLore(lore);
        hubmenu.setItemMeta(hubmeta);
        if (Config.getConfig().getBoolean("COSMETIC.ENABLED")) {
            p.getInventory().setItem(Config.getConfig().getInt("COSMETIC.SLOT"), hubmenu);
        }
    }


    public void CosmeticInventory(Player p) {
        Inventory inv = Bukkit.createInventory(null, 9 * 3, CC.translate("&cCosmetic Menu"));
        List<String> armors = Arrays.asList("", "&7Change your outfit", "", "&aClick to view outfits");
        List<String> armorsno = Arrays.asList("", "&7Change your outfit", "", "&aClick to view outfits", Config.getConfig().getString("SOCIAL.STORE"));

        List<String> hats = Arrays.asList("", "&7Change your tag", "", "&aClick to view tags");
        List<String> hatsno = Arrays.asList("", "&7Change your tag", "", "&aClick to view tags", Config.getConfig().getString("SOCIAL.STORE"));

        List<String> Particles = Arrays.asList("", "&7Change your particles", "", "&aClick to view particles");
        List<String> Particlesno = Arrays.asList("", "&7Change your particles", "", "&aClick to view particles", Config.getConfig().getString("SOCIAL.STORE"));

        if (p.hasPermission("hub.admin") && p.hasPermission("hub.donator")) {
            inv.setItem(16 - 1, new ItemCreator(Material.NAME_TAG).title("&cTags").lores(hats).build());
        }
        else{
            inv.setItem(16 - 1, new ItemCreator(Material.NAME_TAG).title("&cTags").lores(hatsno).build());
        }

        if (p.hasPermission("hub.admin") && p.hasPermission("hub.donator")) {
            inv.setItem(14 - 1, new ItemCreator(Material.DIAMOND_CHESTPLATE).title("&cArmors").lores(armors).build());
        }else{
            inv.setItem(14 - 1, new ItemCreator(Material.DIAMOND_CHESTPLATE).title("&cArmors").lores(armorsno).build());
        }

        if (p.hasPermission("hub.admin") && p.hasPermission("hub.donator")) {
            inv.setItem(12 - 1, new ItemCreator(Material.BLAZE_POWDER).title("&cParticles").lores(Particles).build());
        }else{
            inv.setItem(12 - 1, new ItemCreator(Material.BLAZE_POWDER).title("&cParticles").lores(Particlesno).build());
        }

        if (Config.getConfig().getBoolean("COSMETIC.GLASS_PANEL.ENABLED")) {
            ItemStack panel = new ArmorCreator(Material.STAINED_GLASS_PANE, 1, (short) Config.getConfig().getInt("COSMETIC.GLASS_PANEL.ID")).setName("").build();

            for (int i = 0; i < inv.getSize(); i++) {
                if (inv.getItem(i) == null) {
                    inv.setItem(i, panel);
                }
            }

        }

        p.openInventory(inv);
    }


    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getAction().equals((Object) Action.RIGHT_CLICK_AIR) || event.getAction().equals((Object) Action.RIGHT_CLICK_BLOCK)) {
            Player player = event.getPlayer();
            if (player.getItemInHand().getType() == Material.EMERALD) {
                this.CosmeticInventory(player);
            }
        }
    }

    @EventHandler
    public void onClickInventory(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack item = event.getCurrentItem();
        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR || !event.getCurrentItem().hasItemMeta()) {
            return;
        }
        if (event.getCurrentItem().getItemMeta() == null) {
            return;
        }
        if (event.getClickedInventory().getTitle().equalsIgnoreCase(CC.translate("&cCosmetic Menu"))) {
            if (event.getSlot() == 14 - 1) {
                if(player.hasPermission("hub.donator")){
                    OutfitsListener.OutfitInv(player);
                    event.setCancelled(true);
                }else{
                    player.closeInventory();
                    player.sendMessage(CC.translate(Config.getConfig().getString("SOCIAL.STORE")));
                }
            } else if (event.getSlot() == 12 - 1) {
                if(player.hasPermission("hub.donator")){
                    Particles.ParticleInv(player);
                    event.setCancelled(true);
                }else{
                    player.closeInventory();
                    player.sendMessage(CC.translate(Config.getConfig().getString("SOCIAL.STORE")));
                }
            } else if (event.getSlot() == 16 - 1) {
                if(player.hasPermission("hub.donator")){
                    player.performCommand("tags");
                    event.setCancelled(true);
                }else{
                    player.closeInventory();
                    player.sendMessage(CC.translate(Config.getConfig().getString("SOCIAL.STORE")));
                }
            }
        }
    }
}