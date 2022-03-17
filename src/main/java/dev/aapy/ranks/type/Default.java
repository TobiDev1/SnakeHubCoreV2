package dev.aapy.ranks.type;

import dev.aapy.ranks.Permission;
import org.bukkit.OfflinePlayer;


public class Default implements Permission {

    @Override
    public String getName(OfflinePlayer player) {
        return "None";
    }

    @Override
    public String getPrefix(OfflinePlayer player) {
        return "None";
    }

    @Override
    public String getSuffix(OfflinePlayer player) {
        return "None";
    }

    @Override
    public String getColor(OfflinePlayer player) {
        return "None";
    }
}
