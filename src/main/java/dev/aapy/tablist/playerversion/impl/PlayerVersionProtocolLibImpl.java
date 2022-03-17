package dev.aapy.tablist.playerversion.impl;



import com.comphenix.protocol.ProtocolLibrary;
import dev.aapy.tablist.playerversion.IPlayerVersion;
import dev.aapy.tablist.playerversion.PlayerVersion;
import org.bukkit.entity.Player;

public class PlayerVersionProtocolLibImpl implements IPlayerVersion {
    @Override
    public PlayerVersion getPlayerVersion(Player player) {
        return PlayerVersion.getVersionFromRaw(ProtocolLibrary.getProtocolManager().getProtocolVersion(player));
    }
}
