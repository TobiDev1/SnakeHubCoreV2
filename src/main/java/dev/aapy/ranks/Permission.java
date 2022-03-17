package dev.aapy.ranks;

import org.bukkit.OfflinePlayer;

public interface Permission {

    String getName(OfflinePlayer offlinePlayer);

    String getPrefix(OfflinePlayer offlinePlayer);

    String getSuffix(OfflinePlayer offlinePlayer);

    String getColor(OfflinePlayer offlinePlayer);
}
