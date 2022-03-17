package dev.aapy.listeners.hotbar;

import dev.aapy.file.Config;
import dev.aapy.util.CC;
import dev.aapy.util.ItemCreator;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

/**
 * @author 7qv_ on 18/2/2022.
 * @project SnakeHub
 */
public class OutfitsListener implements Listener {

    public static void OutfitInv(Player p) {
        Inventory inv = Bukkit.createInventory(null, 9 * 2, CC.translate("&6Outfit Menu"));
        List<String> lore = Arrays.asList("","&6Click to select Outfit","");
        List<String> clearlore = Arrays.asList("","&7Click to Clear Outfits","");

        inv.setItem(1 - 1, new ItemCreator(Material.LEATHER_HELMET).title("&eBlack Outfit").lores(lore).color(convert("0")).build());
        inv.setItem(2 - 1, new ItemCreator(Material.LEATHER_HELMET).title("&eBlue Outfit").lores(lore).color(convert("1")).build());
        inv.setItem(3 - 1, new ItemCreator(Material.LEATHER_HELMET).title("&eGreen Outfit").lores(lore).color(convert("2")).build());
        inv.setItem(4 - 1, new ItemCreator(Material.LEATHER_HELMET).title("&eCyan Outfit").lores(lore).color(convert("3")).build());
        inv.setItem(5 - 1, new ItemCreator(Material.LEATHER_HELMET).title("&eRed Outfit").lores(lore).color(convert("4")).build());
        inv.setItem(6 - 1, new ItemCreator(Material.LEATHER_HELMET).title("&ePurple Outfit").lores(lore).color(convert("5")).build());
        inv.setItem(7 - 1, new ItemCreator(Material.LEATHER_HELMET).title("&eOrange Outfit").lores(lore).color(convert("6")).build());
        inv.setItem(8 - 1, new ItemCreator(Material.LEATHER_HELMET).title("&eSilver Outfit").lores(lore).color(convert("7")).build());
        inv.setItem(9 - 1, new ItemCreator(Material.LEATHER_HELMET).title("&eGray Outfit").lores(lore).color(convert("8")).build());
        inv.setItem(10 - 1, new ItemCreator(Material.LEATHER_HELMET).title("&eLime Outfit").lores(lore).color(convert("a")).build());
        inv.setItem(11 - 1, new ItemCreator(Material.LEATHER_HELMET).title("&eYellow Outfit").lores(lore).color(convert("e")).build());
        inv.setItem(12 - 1, new ItemCreator(Material.LEATHER_HELMET).title("&eAqua Outfit").lores(lore).color(convert("b")).build());
        inv.setItem(13 - 1, new ItemCreator(Material.LEATHER_HELMET).title("&eFuchsia Outfit").lores(lore).color(convert("d")).build());
        inv.setItem(14 - 1, new ItemCreator(Material.LEATHER_HELMET).title("&eWhite Outfit").lores(lore).color(convert("f")).build());

        if (p.hasPermission("hub.admin") && p.hasPermission("hub.donator")) {
            inv.setItem(18 - 1, new ItemCreator(Material.FEATHER).title("&cClear Outfit!").lores(clearlore).build());
        }
        p.openInventory(inv);
    }
    @EventHandler
    public void onClickInventory (InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack item = event.getCurrentItem();
        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR || !event.getCurrentItem().hasItemMeta()) {
            return;
        }
        if (event.getCurrentItem().getItemMeta() == null) {
            return;
        }
        if (event.getClickedInventory().getTitle().equalsIgnoreCase(CC.translate("&6Outfit Menu"))) {
            if (player.hasPermission("hub.admin") && player.hasPermission("hub.donator")) {
                if (event.getSlot() == 1 - 1) {
                    ItemStack blackh = new ItemCreator(Material.LEATHER_HELMET).color(OutfitsListener.convert("0")).build();
                    ItemStack blackc = new ItemCreator(Material.LEATHER_CHESTPLATE).color(OutfitsListener.convert("0")).build();
                    ItemStack blackl = new ItemCreator(Material.LEATHER_LEGGINGS).color(OutfitsListener.convert("0")).build();
                    ItemStack blackb = new ItemCreator(Material.LEATHER_BOOTS).color(OutfitsListener.convert("0")).build();

                    player.getInventory().setHelmet(blackh);
                    player.getInventory().setChestplate(blackc);
                    player.getInventory().setLeggings(blackl);
                    player.getInventory().setBoots(blackb);

                    player.sendMessage(CC.translate("&fYou has been change your Outfit at &6Black&f Outfit!"));
                    player.closeInventory();
                    event.setCancelled(true);
                } else if (event.getSlot() == 2 - 1) {
                    ItemStack blueh = new ItemCreator(Material.LEATHER_HELMET).color(OutfitsListener.convert("1")).build();
                    ItemStack bluec = new ItemCreator(Material.LEATHER_CHESTPLATE).color(OutfitsListener.convert("1")).build();
                    ItemStack bluel = new ItemCreator(Material.LEATHER_LEGGINGS).color(OutfitsListener.convert("1")).build();
                    ItemStack blueb = new ItemCreator(Material.LEATHER_BOOTS).color(OutfitsListener.convert("1")).build();

                    player.getInventory().setHelmet(blueh);
                    player.getInventory().setChestplate(bluec);
                    player.getInventory().setLeggings(bluel);
                    player.getInventory().setBoots(blueb);

                    player.sendMessage(CC.translate("&fYou has been change your Outfit at &6Blue&f Outfit!"));
                    player.closeInventory();
                    event.setCancelled(true);
                } else if (event.getSlot() == 3 - 1) {
                    ItemStack greenh = new ItemCreator(Material.LEATHER_HELMET).color(OutfitsListener.convert("2")).build();
                    ItemStack greenc = new ItemCreator(Material.LEATHER_CHESTPLATE).color(OutfitsListener.convert("2")).build();
                    ItemStack greenl = new ItemCreator(Material.LEATHER_LEGGINGS).color(OutfitsListener.convert("2")).build();
                    ItemStack greenb = new ItemCreator(Material.LEATHER_BOOTS).color(OutfitsListener.convert("2")).build();

                    player.getInventory().setHelmet(greenh);
                    player.getInventory().setChestplate(greenc);
                    player.getInventory().setLeggings(greenl);
                    player.getInventory().setBoots(greenb);
                    player.sendMessage(CC.translate("&fYou has been change your Outfit at &6Green&f Outfit!"));

                    player.closeInventory();
                    event.setCancelled(true);
                } else if (event.getSlot() == 4 - 1) {
                    ItemStack cyanh = new ItemCreator(Material.LEATHER_HELMET).color(OutfitsListener.convert("3")).build();
                    ItemStack cyanc = new ItemCreator(Material.LEATHER_CHESTPLATE).color(OutfitsListener.convert("3")).build();
                    ItemStack cyanl = new ItemCreator(Material.LEATHER_LEGGINGS).color(OutfitsListener.convert("3")).build();
                    ItemStack cyanb = new ItemCreator(Material.LEATHER_BOOTS).color(OutfitsListener.convert("3")).build();

                    player.getInventory().setHelmet(cyanh);
                    player.getInventory().setChestplate(cyanc);
                    player.getInventory().setLeggings(cyanl);
                    player.getInventory().setBoots(cyanb);
                    player.sendMessage(CC.translate("&fYou has been change your Outfit at &6Cyan&f Outfit!"));

                    player.closeInventory();
                    event.setCancelled(true);
                } else if (event.getSlot() == 5 - 1) {
                    ItemStack armor = new ItemCreator(Material.LEATHER_HELMET).color(OutfitsListener.convert("4")).build();
                    ItemStack armor1 = new ItemCreator(Material.LEATHER_CHESTPLATE).color(OutfitsListener.convert("4")).build();
                    ItemStack armor2 = new ItemCreator(Material.LEATHER_LEGGINGS).color(OutfitsListener.convert("4")).build();
                    ItemStack armor3 = new ItemCreator(Material.LEATHER_BOOTS).color(OutfitsListener.convert("4")).build();

                    player.getInventory().setHelmet(armor);
                    player.getInventory().setChestplate(armor1);
                    player.getInventory().setLeggings(armor2);
                    player.getInventory().setBoots(armor3);
                    player.sendMessage(CC.translate("&fYou has been change your Outfit at &6Red&f Outfit!"));

                    player.closeInventory();
                    event.setCancelled(true);
                } else if (event.getSlot() == 6 - 1) {
                    ItemStack armor = new ItemCreator(Material.LEATHER_HELMET).color(OutfitsListener.convert("5")).build();
                    ItemStack armor1 = new ItemCreator(Material.LEATHER_CHESTPLATE).color(OutfitsListener.convert("5")).build();
                    ItemStack armor2 = new ItemCreator(Material.LEATHER_LEGGINGS).color(OutfitsListener.convert("5")).build();
                    ItemStack armor3 = new ItemCreator(Material.LEATHER_BOOTS).color(OutfitsListener.convert("5")).build();

                    player.getInventory().setHelmet(armor);
                    player.getInventory().setChestplate(armor1);
                    player.getInventory().setLeggings(armor2);
                    player.getInventory().setBoots(armor3);
                    player.sendMessage(CC.translate("&fYou has been change your Outfit at &6Purple&f Outfit!"));

                    player.closeInventory();
                    event.setCancelled(true);
                } else if (event.getSlot() == 7 - 1) {
                    ItemStack armor = new ItemCreator(Material.LEATHER_HELMET).color(OutfitsListener.convert("6")).build();
                    ItemStack armor1 = new ItemCreator(Material.LEATHER_CHESTPLATE).color(OutfitsListener.convert("6")).build();
                    ItemStack armor2 = new ItemCreator(Material.LEATHER_LEGGINGS).color(OutfitsListener.convert("6")).build();
                    ItemStack armor3 = new ItemCreator(Material.LEATHER_BOOTS).color(OutfitsListener.convert("6")).build();

                    player.getInventory().setHelmet(armor);
                    player.getInventory().setChestplate(armor1);
                    player.getInventory().setLeggings(armor2);
                    player.getInventory().setBoots(armor3);
                    player.sendMessage(CC.translate("&fYou has been change your Outfit at &6Orange&f Outfit!"));

                    player.closeInventory();
                    event.setCancelled(true);
                } else if (event.getSlot() == 8 - 1) {
                    ItemStack armor = new ItemCreator(Material.LEATHER_HELMET).color(OutfitsListener.convert("7")).build();
                    ItemStack armor1 = new ItemCreator(Material.LEATHER_CHESTPLATE).color(OutfitsListener.convert("7")).build();
                    ItemStack armor2 = new ItemCreator(Material.LEATHER_LEGGINGS).color(OutfitsListener.convert("7")).build();
                    ItemStack armor3 = new ItemCreator(Material.LEATHER_BOOTS).color(OutfitsListener.convert("7")).build();

                    player.getInventory().setHelmet(armor);
                    player.getInventory().setChestplate(armor1);
                    player.getInventory().setLeggings(armor2);
                    player.getInventory().setBoots(armor3);

                    player.sendMessage(CC.translate("&fYou has been change your Outfit at &6Silver&f Outfit!"));

                    player.closeInventory();
                    event.setCancelled(true);
                } else if (event.getSlot() == 9 - 1) {
                    ItemStack armor = new ItemCreator(Material.LEATHER_HELMET).color(OutfitsListener.convert("8")).build();
                    ItemStack armor1 = new ItemCreator(Material.LEATHER_CHESTPLATE).color(OutfitsListener.convert("8")).build();
                    ItemStack armor2 = new ItemCreator(Material.LEATHER_LEGGINGS).color(OutfitsListener.convert("8")).build();
                    ItemStack armor3 = new ItemCreator(Material.LEATHER_BOOTS).color(OutfitsListener.convert("8")).build();

                    player.getInventory().setHelmet(armor);
                    player.getInventory().setChestplate(armor1);
                    player.getInventory().setLeggings(armor2);
                    player.getInventory().setBoots(armor3);
                    player.sendMessage(CC.translate("&fYou has been change your Outfit at &6Gray&f Outfit!"));

                    player.closeInventory();
                    event.setCancelled(true);
                } else if (event.getSlot() == 10 - 1) {
                    ItemStack armor = new ItemCreator(Material.LEATHER_HELMET).color(OutfitsListener.convert("a")).build();
                    ItemStack armor1 = new ItemCreator(Material.LEATHER_CHESTPLATE).color(OutfitsListener.convert("a")).build();
                    ItemStack armor2 = new ItemCreator(Material.LEATHER_LEGGINGS).color(OutfitsListener.convert("a")).build();
                    ItemStack armor3 = new ItemCreator(Material.LEATHER_BOOTS).color(OutfitsListener.convert("a")).build();

                    player.getInventory().setHelmet(armor);
                    player.getInventory().setChestplate(armor1);
                    player.getInventory().setLeggings(armor2);
                    player.getInventory().setBoots(armor3);
                    player.sendMessage(CC.translate("&fYou has been change your Outfit at &6Lime&f Outfit!"));

                    player.closeInventory();
                    event.setCancelled(true);
                } else if (event.getSlot() == 11 - 1) {
                    ItemStack armor = new ItemCreator(Material.LEATHER_HELMET).color(OutfitsListener.convert("e")).build();
                    ItemStack armor1 = new ItemCreator(Material.LEATHER_CHESTPLATE).color(OutfitsListener.convert("e")).build();
                    ItemStack armor2 = new ItemCreator(Material.LEATHER_LEGGINGS).color(OutfitsListener.convert("e")).build();
                    ItemStack armor3 = new ItemCreator(Material.LEATHER_BOOTS).color(OutfitsListener.convert("e")).build();

                    player.getInventory().setHelmet(armor);
                    player.getInventory().setChestplate(armor1);
                    player.getInventory().setLeggings(armor2);
                    player.getInventory().setBoots(armor3);
                    player.sendMessage(CC.translate("&fYou has been change your Outfit at &6Yellow&f Outfit!"));

                    player.closeInventory();
                    event.setCancelled(true);
                } else if (event.getSlot() == 12 - 1) {
                    ItemStack armor = new ItemCreator(Material.LEATHER_HELMET).color(OutfitsListener.convert("b")).build();
                    ItemStack armor1 = new ItemCreator(Material.LEATHER_CHESTPLATE).color(OutfitsListener.convert("b")).build();
                    ItemStack armor2 = new ItemCreator(Material.LEATHER_LEGGINGS).color(OutfitsListener.convert("b")).build();
                    ItemStack armor3 = new ItemCreator(Material.LEATHER_BOOTS).color(OutfitsListener.convert("b")).build();

                    player.getInventory().setHelmet(armor);
                    player.getInventory().setChestplate(armor1);
                    player.getInventory().setLeggings(armor2);
                    player.getInventory().setBoots(armor3);

                    player.sendMessage(CC.translate("&fYou has been change your Outfit at &6Aqua&f Outfit!"));

                    player.closeInventory();
                    event.setCancelled(true);
                } else if (event.getSlot() == 13 - 1) {
                    ItemStack armor = new ItemCreator(Material.LEATHER_HELMET).color(OutfitsListener.convert("d")).build();
                    ItemStack armor1 = new ItemCreator(Material.LEATHER_CHESTPLATE).color(OutfitsListener.convert("d")).build();
                    ItemStack armor2 = new ItemCreator(Material.LEATHER_LEGGINGS).color(OutfitsListener.convert("d")).build();
                    ItemStack armor3 = new ItemCreator(Material.LEATHER_BOOTS).color(OutfitsListener.convert("d")).build();

                    player.getInventory().setHelmet(armor);
                    player.getInventory().setChestplate(armor1);
                    player.getInventory().setLeggings(armor2);
                    player.getInventory().setBoots(armor3);
                    player.sendMessage(CC.translate("&fYou has been change your Outfit at &6Fuchsia&f Outfit!"));

                    player.closeInventory();
                    event.setCancelled(true);
                } else if (event.getSlot() == 14 - 1) {
                    ItemStack armor = new ItemCreator(Material.LEATHER_HELMET).color(OutfitsListener.convert("f")).build();
                    ItemStack armor1 = new ItemCreator(Material.LEATHER_CHESTPLATE).color(OutfitsListener.convert("f")).build();
                    ItemStack armor2 = new ItemCreator(Material.LEATHER_LEGGINGS).color(OutfitsListener.convert("f")).build();
                    ItemStack armor3 = new ItemCreator(Material.LEATHER_BOOTS).color(OutfitsListener.convert("f")).build();

                    player.getInventory().setHelmet(armor);
                    player.getInventory().setChestplate(armor1);
                    player.getInventory().setLeggings(armor2);
                    player.getInventory().setBoots(armor3);
                    player.sendMessage(CC.translate("&fYou has been change your Outfit at &6White&f Outfit!"));

                    player.closeInventory();
                    event.setCancelled(true);
                } else if (event.getSlot() == 18 - 1) {
                    if (player.getInventory().getHelmet() == null) {
                        player.sendMessage(CC.translate("&cYou do not have any Outfit available, buy them at store.serpentmc.club"));
                        player.closeInventory();
                        event.setCancelled(true);
                    } else {
                        player.getInventory().setHelmet(null);
                        player.getInventory().setChestplate(null);
                        player.getInventory().setLeggings(null);
                        player.getInventory().setBoots(null);
                        player.sendMessage(CC.translate("&cYou have taken off your outfits, put them back on to look cooler!"));

                        player.closeInventory();
                        event.setCancelled(true);
                    }
                }
            } else {
                player.sendMessage(CC.translate(Config.getConfig().getString("SOCIAL.STORE")));
                player.closeInventory();
                event.setCancelled(true);
            }
        }
    }
    public static Color convert(String colorString) {
        if (colorString.contains("0")) {
            return Color.BLACK;
        }
        if (colorString.contains("1")) {
            return Color.BLUE;
        }
        if (colorString.contains("2")) {
            return Color.GREEN;
        }
        if (colorString.contains("3")) {
            return Color.TEAL;
        }
        if (colorString.contains("4")) {
            return Color.RED;
        }
        if (colorString.contains("5")) {
            return Color.PURPLE;
        }
        if (colorString.contains("6")) {
            return Color.ORANGE;
        }
        if (colorString.contains("7")) {
            return Color.SILVER;
        }
        if (colorString.contains("8")) {
            return Color.GRAY;
        }
        if (colorString.contains("9")) {
            return Color.BLUE;
        }
        if (colorString.contains("a")) {
            return Color.LIME;
        }
        if (colorString.contains("e")) {
            return Color.YELLOW;
        }
        if (colorString.contains("b")) {
            return Color.AQUA;
        }
        if (colorString.contains("d")) {
            return Color.FUCHSIA;
        }
        if (colorString.contains("f")) {
            return Color.WHITE;
        }
        return Color.BLACK;
    }
}
