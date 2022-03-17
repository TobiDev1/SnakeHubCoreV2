package dev.aapy.util.bungee;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import dev.aapy.SnakeHub;
import org.bukkit.entity.Player;

/**
 * @author 7qv_ on 16/2/2022.
 * @project SnakeHub
 */
public class BungeeChannel {

    public static void sendToServer(final Player player, final String server) {
        final ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(server);
        player.sendPluginMessage(SnakeHub.getInst(), "BungeeCord", out.toByteArray());
    }

}