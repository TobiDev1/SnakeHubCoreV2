package dev.aapy.tablist.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

public class CC {

    public static Enchantment FAKE_GLOW;

    public static String translate(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static List<String> translate(List<String> s) {
        return s.stream().map(CC::translate).collect(Collectors.toList());
    }

    public static ItemStack nameItem(ItemStack item, String name, short durability, int amount, List<String> lores) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(CC.translate(name));
        meta.setLore(CC.translate(lores));
        item.setItemMeta(meta);
        item.setAmount(amount);
        item.setDurability(durability);
        return item;
    }

    public static ItemStack nameItem(Material item, String name, short durability, int amount, List<String> lores) {
        return nameItem(new ItemStack(item), name, durability, amount, lores);

    }

    public static ItemStack enumItem(ItemStack item, short durability) {
        item.setDurability(durability);
        return item;
    }

    public static ItemStack enumItem(Material item, short durability) {
        return enumItem(new ItemStack(item), durability);

    }

    public static void registerFakeEnchantmentGlow() {
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);

            Enchantment.registerEnchantment(FAKE_GLOW);
        } catch (IllegalArgumentException e1) {
            FAKE_GLOW = Enchantment.getById(70);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    static {
        FAKE_GLOW = new FakeGlow(70);
    }
}
