package dev.aapy.commands.plugin;

import dev.alex.net.utilities.command.command.CommandArgument;
import dev.aapy.file.Config;
import dev.aapy.file.Message;
import dev.aapy.file.Scoreboard;
import dev.aapy.file.Tablist;
import dev.aapy.util.CC;
import org.bukkit.command.CommandSender;

/**
 * @author 7qv_ and Alexito2060 on 19/2/2022.
 * @project SnakeHub
 */
public class HubReload extends CommandArgument {

    public HubReload() { super("reload", "Reload configs file", "hub.reload"); }

    @Override
    public String getUsage(String s) { return getName(); }

    @Override
    public boolean onCommand(CommandSender sender, String label, String[] args) {
        Config.getConfig().reload();
        Message.getConfig().reload();
        Scoreboard.getConfig().reload();
        Tablist.getConfig().reload();

        sender.sendMessage(CC.translate("&aAll files has been successfully reloaded."));

        return true;
    }
}
