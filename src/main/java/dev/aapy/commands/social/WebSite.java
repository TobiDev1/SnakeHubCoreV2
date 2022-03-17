package dev.aapy.commands.social;

import dev.alex.net.utilities.command.command.CommandExecutor;
import dev.aapy.file.Config;
import dev.aapy.file.Message;
import dev.aapy.util.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * @author 7qv_ and Alexito2060 on 19/2/2022.
 * @project SnakeHub
 */
public class WebSite extends CommandExecutor {

    public WebSite() {
        super("website", "WebSite Command","hubcore.website.command");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Message.getConfig().getStringList("SOCIAL.WEBSITE").forEach((s) -> {
            sender.sendMessage(CC.translate(s.replace("{website}", Config.getConfig().getString("SOCIAL.WEBSITE"))));
        });
        return true;
    }
}
