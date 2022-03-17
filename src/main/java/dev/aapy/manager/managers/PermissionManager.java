package dev.aapy.manager.managers;

import dev.aapy.SnakeHub;
import dev.aapy.ranks.Permission;
import dev.aapy.ranks.type.AquaCore;
import dev.aapy.ranks.type.Default;
import dev.aapy.ranks.type.LuckPerms;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

import net.milkbowl.vault.chat.Chat;

@Getter
public final class PermissionManager {

    private final SnakeHub plugin;
    private String name;
    private Permission permission;
    private net.luckperms.api.LuckPerms luckperms;
    private Chat chat;

    public PermissionManager(SnakeHub plugin) {  this.plugin = plugin; }

    public void loadHook() {
        if (Bukkit.getPluginManager().getPlugin("AquaCore") != null) this.setPermission(new AquaCore(), "AquaCore");
        if (Bukkit.getPluginManager().getPlugin("Vault") != null && this.loadVault() && this.getChat().getName().contains("LuckPerms")) this.setPermission(new LuckPerms(), "LuckPerms");
        this.setPermission(new Default(), "None");
    }

    private boolean loadVault() {
        RegisteredServiceProvider<Chat> service = this.plugin.getServer().getServicesManager().getRegistration(Chat.class);
        if (service != null) this.chat = service.getProvider();
        return this.chat != null;
    }

    private void setPermission(Permission permission, String name) {
        this.permission = permission;
        this.name = name;
    }
}