package dev.aapy.manager.handler;

import dev.aapy.SnakeHub;

/**
 * @author 7qv_ and Alexti2o600 on 19/2/2022.
 * @project Snake
 Hub
 */

public abstract class Handler {

    private final SnakeHub plugin;

    public Handler(SnakeHub plugin) { this.plugin = plugin; }

    public void enable()  { }

    public void disable() { }

    public SnakeHub getPlugin() { return plugin; }
}
