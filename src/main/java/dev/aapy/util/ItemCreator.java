package dev.aapy.util;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.HashMap;
import java.util.List;

/**
 * @author 7qv_ on 18/2/2022.
 * @project SnakeHub
 */
public class ItemCreator {
    private Material material;
    private Short durability;
    private String title;
    private int amount = 1;
    private List<String> lores;
    @SuppressWarnings("unused")
    private byte materialData;
    private HashMap<Enchantment, Integer> enchantments = new HashMap<>();
    private ItemStack itemStack;

    public ItemCreator() {
        this.itemStack = new ItemStack(Material.AIR);
    }

    public ItemCreator(Material material) {
        this.itemStack = new ItemStack(material);
    }

    public ItemCreator(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public ItemCreator material(Material material){
        this.material = material;
        return this;
    }

    public ItemCreator durability(short durability){
        this.durability = durability;
        return this;
    }

    public ItemCreator title(String title){
        this.title = title;
        return this;
    }

    public ItemCreator amount(int amount){
        this.amount = amount;
        return this;
    }

    public ItemCreator lores(List<String> lores){
        this.lores = lores;
        return this;
    }
    public ItemStack build(){
        ItemStack itemStack = this.itemStack;
        if (this.material != null) {
            itemStack.setType(this.material);
        }
        //TODO Enchantments
        for (Enchantment enchantment : enchantments.keySet()) {
            itemStack.addUnsafeEnchantment(enchantment, enchantments.get(enchantment));
        }
        ItemMeta meta = itemStack.getItemMeta();
        if (this.amount > 0)
            itemStack.setAmount(this.amount);
        if (this.durability != null)
            itemStack.setDurability(this.durability);
        if (this.title != null)
            meta.setDisplayName(CC.translate("&r" + this.title));
        if (this.lores != null && this.lores.size() > 0)
            meta.setLore(CC.translate(this.lores));
        itemStack.setItemMeta(meta);
        return itemStack;
    }
    public ItemCreator color(Color color) {
        ItemMeta meta = this.itemStack.getItemMeta();
        if (!(meta instanceof LeatherArmorMeta)) {
            throw new UnsupportedOperationException("Cannot set color of a non-leather armor item.");
        }
        ((LeatherArmorMeta)meta).setColor(color);
        this.itemStack.setItemMeta(meta);
        return this;
    }
}
