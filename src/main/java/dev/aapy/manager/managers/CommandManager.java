package dev.aapy.manager.managers;

import dev.alex.net.utilities.command.Command;
import dev.aapy.SnakeHub;
import dev.aapy.commands.plugin.HubCore;
import dev.aapy.commands.social.*;
import dev.aapy.manager.handler.Handler;

/**
 * @author 7qv_ and Alexito2060 on 19/2/2022.
 * @project SnakeHub
 *
**/

public final class CommandManager extends Handler {

    private Command command;

    public CommandManager(SnakeHub plugin) { super(plugin); }

    @Override
    public void enable() {
        this.command = new Command("", false);
        command.register(new HubCore());
        command.register(new WebSite());
        command.register(new Twitter());
        command.register(new TeamSpeak());
        command.register(new Store());
        command.register(new Discord());
        this.command.registerAll();
    }

    @Override
    public void disable() {
        this.command.unregisterAll();
    }
}