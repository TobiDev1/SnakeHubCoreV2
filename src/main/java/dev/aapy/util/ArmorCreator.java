package dev.aapy.util;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.potion.PotionEffect;

import java.util.List;

/**
 * @author 7qv_ on 9/2/2022.
 * @project SnakeHub
 */
@AllArgsConstructor
public class ArmorCreator {

    private ItemStack itemStack;

    public ArmorCreator(Material material) {
        this(material, 1);
    }

    public ArmorCreator(Material material, int amount) {
        this(material, amount, (short) 0);
    }

    public ArmorCreator(Material material, int amount, short damage) {
        this(new ItemStack(material, amount, damage));
    }

    public ArmorCreator setColor(Color color) {
        LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) itemStack.getItemMeta();
        leatherArmorMeta.setColor(color);
        itemStack.setItemMeta(leatherArmorMeta);
        return this;
    }

    public ArmorCreator setType(Material material) {
        itemStack.setType(material);
        return this;
    }

    public ArmorCreator setAmount(int amount) {
        itemStack.setAmount(amount);
        return this;
    }

    public ArmorCreator setDurability(int durability) {
        itemStack.setDurability((short) durability);
        return this;
    }

    public ArmorCreator setData(int data) {
        itemStack.setData(new MaterialData(data));
        return this;
    }

    public ArmorCreator addEnchantment(Enchantment enchantment) {
        addEnchantment(enchantment, 1);
        return this;
    }

    public ArmorCreator addEnchantment(Enchantment enchantment, int level) {
        itemStack.addUnsafeEnchantment(enchantment, level);
        return this;
    }

    public ArmorCreator removeEnchantment(Enchantment enchantment) {
        itemStack.removeEnchantment(enchantment);
        return this;
    }

    public ArmorCreator clearEnchantments() {
        for (Enchantment enchantment : itemStack.getEnchantments().keySet())
            itemStack.removeEnchantment(enchantment);

        return this;
    }

    public ArmorCreator setDisplayName(String name) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ArmorCreator addLore(String lore) {
        ItemMeta itemMeta = itemStack.getItemMeta();

        List<String> lores = itemMeta.getLore();
        if (lores == null)
            lores = Lists.newArrayList();

        lores.add(CC.translate(lore));

        itemMeta.setLore(lores);

        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ArmorCreator setLore(String... lores) {
        clearLore();

        for (String lore : lores)
            addLore(lore);

        return this;
    }

    public ArmorCreator setLore(List<String> lore) {
        clearLore();

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(CC.translate(lore));
        itemStack.setItemMeta(itemMeta);

        return this;
    }

    public ArmorCreator clearLore() {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(Lists.newArrayList());
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ArmorCreator setPotionEffect(PotionEffect effect) {
        PotionMeta potionMeta = (PotionMeta) itemStack.getItemMeta();
        potionMeta.setMainEffect(effect.getType());
        potionMeta.addCustomEffect(effect, false);
        itemStack.setItemMeta(potionMeta);
        return this;
    }

    public ItemStack build() {
        ItemMeta itemMeta = itemStack.getItemMeta();
        final ItemStack clone = this.itemStack.clone();
        clone.setItemMeta(itemMeta.clone());
        return clone;
    }

    public ArmorCreator setPotionEffects(PotionEffect... effects) {
        PotionMeta potionMeta = (PotionMeta) itemStack.getItemMeta();
        potionMeta.setMainEffect(effects[0].getType());

        for (PotionEffect effect : effects)
            potionMeta.addCustomEffect(effect, false);

        itemStack.setItemMeta(potionMeta);
        return this;
    }

    public static Inventory createInventory(String title, int rows) {
        Inventory inv = Bukkit.getServer().createInventory(null, rows * 9, CC.translate(title));
        return inv;
    }

    public static ItemStack createItemStack(String name, List<String> lore, Material material, int amount, int data) {
        ItemStack item = new ItemStack(material, amount, (short) data);
        ItemMeta itemmeta = item.getItemMeta();
        itemmeta.setDisplayName(CC.translate(name));
        itemmeta.setLore(CC.translateFromArray(lore));
        item.setItemMeta(itemmeta);
        return item;
    }

    public static ItemStack createItemStackWithoutLore(String name, Material material, int amount, int data) {
        ItemStack item = new ItemStack(material, amount, (short) data);
        ItemMeta itemmeta = item.getItemMeta();
        itemmeta.setDisplayName(CC.translate(name));
        item.setItemMeta(itemmeta);
        return item;
    }

    public ArmorCreator setSkullOwner(String owner) {
        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
        skullMeta.setOwner(owner);

        itemStack.setItemMeta(skullMeta);
        return this;
    }

    public ArmorCreator setName(final String displayName) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(displayName);
        return this;
    }

    public ItemStack create() {
        return itemStack;
    }
}