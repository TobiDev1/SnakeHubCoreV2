package dev.aapy.listeners.hotbar.particles;

import dev.aapy.SnakeHub;
import dev.aapy.file.Config;
import dev.aapy.util.ArmorCreator;
import dev.aapy.util.CC;
import dev.aapy.util.ItemCreator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;
import java.util.List;

public class Particles implements Listener {

    public static void ParticleInv(Player p) {
        Inventory inv = Bukkit.createInventory(null, 9 * 4, CC.translate("&cParticle Menu"));
        List<String> clearparticles = Arrays.asList("","&6Click to clear particle","");

        inv.setItem(12 - 1, new ItemCreator(Material.BLAZE_POWDER).title("&6Flame Particle").build());
        inv.setItem(14 - 1, new ItemCreator(Material.TNT).title("&eExplosion Particle").build());
        inv.setItem(16 - 1, new ItemCreator(Material.REDSTONE).title("&cHeart Particle").build());
        inv.setItem(18 - 1, new ItemCreator(Material.WATER_BUCKET).title("&dWater Particle").build());

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
    public void onClickInventory (InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack item = event.getCurrentItem();
        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR || !event.getCurrentItem().hasItemMeta()) {
            return;
        }
        if (event.getCurrentItem().getItemMeta() == null) {
            return;
        }
        if (event.getClickedInventory().getTitle().equalsIgnoreCase(CC.translate("&cParticle Menu"))) {
            if (player.hasPermission("hub.admin") && player.hasPermission("hub.donator")) {
                if (event.getSlot() == 12 - 1) {
                    if(ParticleParameter.set.containsKey(player)){
                        Bukkit.getScheduler().cancelTask((int)ParticleParameter.set.get(player));
                        ParticleParameter.set.replace(player, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask((Plugin) SnakeHub.getInst(), () -> ParticleEffect.FLAME.display(10.0f, 15.0f, 0.0f, 0.0f, 0, player.getLocation().add(-0.0, 2.0, 0.0), 10.0), 0L, 0L));
                        player.sendMessage(CC.translate("&fYou has been Changed your &cParticle!"));
                        player.closeInventory();
                    }else {
                        ParticleParameter.set.put(player, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask((Plugin) SnakeHub.getInst(), () -> ParticleEffect.FLAME.display(10.0f, 15.0f, 0.0f, 0.0f, 0, player.getLocation().add(-0.0, 2.0, 0.0), 10.0), 0L, 0L));
                        player.sendMessage(CC.translate("&fYou has been set &6Flame &fParticle!"));
                        player.closeInventory();
                    }
                    }else if(event.getSlot() == 14 - 1){
                    if(ParticleParameter.set.containsKey(player)){
                        Bukkit.getScheduler().cancelTask((int)ParticleParameter.set.get(player));
                        ParticleParameter.set.replace(player, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask((Plugin) SnakeHub.getInst(), () -> ParticleEffect.EXPLOSION_NORMAL.display(10.0f, 15.0f, 0.0f, 0.0f, 0, player.getLocation().add(-0.0, 2.0, 0.0), 10.0), 0L, 0L));
                        player.sendMessage(CC.translate("&fYou has been Changed your &cParticle!"));
                        player.closeInventory();
                    }else {
                        ParticleParameter.set.put(player, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask((Plugin) SnakeHub.getInst(), () -> ParticleEffect.EXPLOSION_NORMAL.display(10.0f, 15.0f, 0.0f, 0.0f, 0, player.getLocation().add(-0.0, 2.0, 0.0), 10.0), 0L, 0L));
                        player.sendMessage(CC.translate("&fYou has been set &eExplosion &fParticle!"));
                        player.closeInventory();
                    }
                    }else if(event.getSlot() == 16 - 1){
                    if(ParticleParameter.set.containsKey(player)){
                        Bukkit.getScheduler().cancelTask((int)ParticleParameter.set.get(player));
                        ParticleParameter.set.replace(player, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask((Plugin) SnakeHub.getInst(), () -> ParticleEffect.HEART.display(10.0f, 15.0f, 0.0f, 0.0f, 0, player.getLocation().add(-0.0, 2.0, 0.0), 10.0), 0L, 0L));
                        player.sendMessage(CC.translate("&fYou has been Changed your &cParticle!"));
                        player.closeInventory();
                    }else {
                        ParticleParameter.set.put(player, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask((Plugin) SnakeHub.getInst(), () -> ParticleEffect.HEART.display(10.0f, 15.0f, 0.0f, 0.0f, 0, player.getLocation().add(-0.0, 2.0, 0.0), 10.0), 0L, 0L));
                        player.sendMessage(CC.translate("&fYou has been set &cHeart &fParticle!"));
                        player.closeInventory();
                    }
                    }else if(event.getSlot() == 18 - 1) {
                    if (ParticleParameter.set.containsKey(player)) {
                        Bukkit.getScheduler().cancelTask((int) ParticleParameter.set.get(player));
                        ParticleParameter.set.replace(player, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask((Plugin) SnakeHub.getInst(), () -> ParticleEffect.DRIP_WATER.display(10.0f, 15.0f, 0.0f, 0.0f, 0, player.getLocation().add(-0.0, 2.0, 0.0), 10.0), 0L, 0L));
                        player.sendMessage(CC.translate("&fYou has been Changed your &cParticle!"));
                        player.closeInventory();
                    } else {
                        ParticleParameter.set.put(player, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask((Plugin) SnakeHub.getInst(), () -> ParticleEffect.DRIP_WATER.display(10.0f, 15.0f, 0.0f, 0.0f, 0, player.getLocation().add(-0.0, 2.0, 0.0), 10.0), 0L, 0L));
                        player.sendMessage(CC.translate("&fYou has been set &cWater &fParticle!"));
                        player.closeInventory();
                    }
                }
            } else {
                player.sendMessage(CC.translate(Config.getConfig().getString("SOCIAL.STORE")));
                player.closeInventory();
                event.setCancelled(true);
            }
        }
    }
}
