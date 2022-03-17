package dev.aapy.manager.managers;

import com.google.common.collect.Lists;
import dev.aapy.SnakeHub;
import dev.aapy.file.Config;
import dev.aapy.listeners.*;
import dev.aapy.listeners.hotbar.*;
import dev.aapy.listeners.hotbar.particles.Particles;
import dev.aapy.manager.handler.Handler;
import lombok.Getter;
import org.bukkit.plugin.PluginManager;

import java.util.List;

/**
 * @author 7qv_ and Alexito2060 on 19/2/2022.
 * @project SnakeHub
 */

@Getter
public final class ListenerManager extends Handler {

    private final List<Handler> listener = Lists.newArrayList();

    public ListenerManager(SnakeHub plugin) { super(plugin); }

    @Override
    public void enable() {
        PluginManager manager = getPlugin().getServer().getPluginManager();
        manager.registerEvents(new PlayerListener(),getPlugin());
        if (Config.getConfig().getBoolean("BOOLEANS.LUNAR-CLIENT.NAMETAG")) {
            manager.registerEvents(new LunarListener(), getPlugin());
        }
        manager.registerEvents(new DeveloperListener(), getPlugin());
        manager.registerEvents(new EnderButtListener(), getPlugin());
        manager.registerEvents(new ChatFormatListener(), getPlugin());
        manager.registerEvents(new JumpListener(), getPlugin());
        manager.registerEvents(new LaunchPadListener(), getPlugin());
        manager.registerEvents(new Particles(), getPlugin());
        manager.registerEvents(new PlayerInvisibilityListener(), getPlugin());
        manager.registerEvents(new PvPListener(), getPlugin());
        manager.registerEvents(new OutfitsListener(), getPlugin());
        manager.registerEvents(new CosmeticListener(), getPlugin());
        manager.registerEvents(new SelectorListener(), getPlugin());
        manager.registerEvents(new CosmeticListener(), getPlugin());
        manager.registerEvents(new ChatFormatListener(), getPlugin());
        manager.registerEvents(new WorldListener(), getPlugin());
    }
}
