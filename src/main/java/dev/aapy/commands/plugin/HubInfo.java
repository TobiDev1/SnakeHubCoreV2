package dev.aapy.commands.plugin;

import dev.alex.net.utilities.command.command.CommandArgument;
import dev.aapy.util.CC;
import org.bukkit.command.CommandSender;

/**
 * @author 7qv_ and Alexito2060 on 19/2/2022.
 * @project SnakeHub
 */
public class HubInfo extends CommandArgument {

    public HubInfo() { super("info", "See SnakeHub information"); }

    @Override
    public String getUsage(String s) { return getName(); }

    @Override
    public boolean onCommand(CommandSender sender, String label, String[] args) {
        sender.sendMessage(CC.translate("&7&m----------------------------"));
        sender.sendMessage("");
        sender.sendMessage(CC.translate("&bSnakeHub &7- &7[&fHubCore&7]"));
        sender.sendMessage("");
        sender.sendMessage(CC.translate("&7» &cVersion&7: &f1.0.3-RECODE"));
        sender.sendMessage(CC.translate("&7» &cAuthor&7: &f7qv_"));
        sender.sendMessage("");
        sender.sendMessage(CC.translate("&7&m----------------------------"));
        return false;
    }
}
