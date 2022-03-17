package dev.aapy.listeners.hotbar;

import dev.aapy.file.Config;
import dev.aapy.util.CC;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

/**
 * @author 7qv_ on 21/2/2022.
 * @propect SnakeHub
 */
public class PvPListener implements Listener {

    public static ArrayList<Player> pvpEnable = new ArrayList();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();

        ItemStack pvps = new ItemStack(Material.getMaterial(Config.getConfig().getString("PVP.MATERIAL")));
        ItemMeta pvpmeta = pvps.getItemMeta();
        pvpmeta.setDisplayName(CC.translate(Config.getConfig().getString("PVP.DISPLAYNAME")));
        ArrayList<String> lore = new ArrayList<>();
        lore.add(CC.translate(Config.getConfig().getString("PVP.LORE")));
        pvpmeta.setLore(lore);
        pvpmeta.addEnchant(Enchantment.DURABILITY, 3, Config.getConfig().getBoolean("PVP.ENCHANTED"));
        pvps.setItemMeta(pvpmeta);
        if (Config.getConfig().getBoolean("PVP.ENABLED")) {
            p.getInventory().setItem(Config.getConfig().getInt("PVP.SLOT"), pvps);
        }
    }

        public static void enablePvPMode(Player p) {
            p.setFireTicks(0);
            for (PotionEffect effect : p.getActivePotionEffects()) {
                p.removePotionEffect(effect.getType());
            }

            p.getInventory().clear();
            pvpEnable.add(p);
            giveKitPvP((PlayerInteractEvent) p);
            p.sendMessage(CC.translate("&aYou turned on your PvP-Mode and you can receive damage."));
        }

        @EventHandler
        public void leaveGameInPvPMode(PlayerQuitEvent event) {
            Player p = event.getPlayer();
            if (pvpEnable.contains(p)) {
                pvpEnable.remove(p);
                Bukkit.getConsoleSender().sendMessage(CC.translate("&cThe player " + p.getName() + " &chas " +
                        "left when your pvp-mode is enabled, and has been removed for minor bugs"));
            }
        }

    @EventHandler
    public static void giveKitPvP(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player p = event.getPlayer();

            if (p.getInventory().getItemInHand().getType() == Material.getMaterial(Config.getConfig().getString("PVP.MATERIAL"))) {
                p.playSound(p.getLocation(), Sound.valueOf(Config.getConfig().getString("PVP.SOUND")), 2.0f, 2.0f);


                p.getInventory().clear();
                if(p.getGameMode() == GameMode.CREATIVE){
                    p.sendMessage(CC.translate("&cUps... You need change your gamemode after poin!."));
                    return;
                }else{

                    ItemStack he = new ItemStack(310, 1, (short)0);
                    ItemStack ch = new ItemStack(310, 1, (short)0);
                    ItemStack leg = new ItemStack(310, 1, (short)0);
                    ItemStack bo = new ItemStack(310, 1, (short)0);
                    ItemStack sword = new ItemStack(276, 1, (short)0);
                    ItemStack enderpearl = new ItemStack(368, 16, (short)0);
                    ItemStack steack = new ItemStack(364, 32, (short)0);
                    PotionEffect speed = new PotionEffect(PotionEffectType.SPEED, 99999, 1);
                    PotionEffect fireres = new PotionEffect(PotionEffectType.SPEED, 99999, 1);

                    bo.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
                    ch.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
                    leg.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
                    bo.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
                    bo.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
                    sword.addEnchantment(Enchantment.DAMAGE_ALL, 1);


                    p.getInventory().setHelmet(he);
                    p.getInventory().setChestplate(ch);
                    p.getInventory().setLeggings(leg);
                    p.getInventory().setBoots(bo);
                    p.getInventory().setItem(0, sword);
                    p.getInventory().setItem(1, enderpearl);
                    p.getInventory().setItem(2, steack);

                    p.addPotionEffect(speed);
                    p.addPotionEffect(fireres);
                    for (int i = 3; i < 34; i++) {
                        ItemStack healthPotions = new ItemStack(373, 1, (short)16421);
                        p.getInventory().setItem(i, healthPotions);
                    }
                }
            }
        }
    }
}
