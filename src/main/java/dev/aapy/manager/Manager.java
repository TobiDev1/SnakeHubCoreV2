package dev.aapy.manager;

import com.google.common.collect.Lists;
import dev.aapy.SnakeHub;
import dev.aapy.manager.handler.Handler;
import dev.aapy.manager.managers.*;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/**
 * @author 7qv_ and Alexito2060 on 19/2/2022.
 * @project SnakeHub
 */

@Getter
public class Manager {

    private final SnakeHub plugin;
    private final List<Handler> managers = Lists.newArrayList();

    private ScoreBoardManager scoreboard;
    private CommandManager command;
    private ListenerManager listener;

    public Manager(SnakeHub plugin) {
        this.plugin = plugin;
    }

    public void enable() {
        this.scoreboard = new ScoreBoardManager(this.plugin);
        this.command = new CommandManager(this.plugin);
        this.listener = new ListenerManager(this.plugin);
        this.managers.addAll(Arrays.asList(this.scoreboard, this.command, this.listener));
        this.managers.forEach(Handler::enable);
    }

    public void disable() {
        this.managers.forEach(Handler::disable);
    }
    
}
