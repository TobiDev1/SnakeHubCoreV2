package dev.aapy.commands.plugin;

import dev.alex.net.utilities.command.command.CommandExecutor;

/**
 * @author 7qv_ and Alexito2060 on 19/2/2022.
 * @project SnakeHub
 */

public class HubCore extends CommandExecutor {

    public HubCore() {
        super("HubCore", "HubCore command");
        registerArgument(new HubInfo());
        registerArgument(new HubReload());
    }
}
