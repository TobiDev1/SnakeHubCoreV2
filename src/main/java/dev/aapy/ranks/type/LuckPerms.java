package dev.aapy.ranks.type;

import dev.aapy.SnakeHub;
import dev.aapy.ranks.Permission;
import dev.aapy.util.CC;
import org.bukkit.OfflinePlayer;


public class LuckPerms implements Permission {

    @Override
    public String getName(OfflinePlayer player) {
        return CC.translate(SnakeHub.getInst().getManager().getPlugin().getPermission().getChat().getPrimaryGroup(String.valueOf(SnakeHub.getInst().getManager().getPlugin().getPermission().getPlugin().getServer().getWorlds().get(0).getName()), player));
    }

    @Override
    public String getPrefix(OfflinePlayer player) {
        return CC.translate(SnakeHub.getInst().getManager().getPlugin().getPermission().getChat().getPlayerPrefix(String.valueOf(SnakeHub.getInst().getManager().getPlugin().getPermission().getPlugin().getServer().getWorlds().get(0).getName()), player));
    }

    @Override
    public String getSuffix(OfflinePlayer player) {
        return CC.translate(SnakeHub.getInst().getManager().getPlugin().getPermission().getChat().getPlayerSuffix(String.valueOf(SnakeHub.getInst().getManager().getPlugin().getPermission().getPlugin().getServer().getWorlds().get(0).getName()), player));
    }

    @Override
    public String getColor(OfflinePlayer player) {
        return CC.translate(SnakeHub.getInst().getManager().getPlugin().getPermission().getChat().getPrimaryGroup(String.valueOf(SnakeHub.getInst().getManager().getPlugin().getPermission().getPlugin().getServer().getWorlds().get(0).getName()), player));
    }
}
