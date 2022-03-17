package dev.aapy.util;

import dev.aapy.file.Scoreboard;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author 7qv_ on 16/2/2022.
 * @project SnakeHub
 */
public class ScoreTitleTask extends BukkitRunnable {

    public static String title = "";

    private int left;

    public ScoreTitleTask() {
        this.left = 8;
    }

    @Deprecated
    public void run() {
        --this.left;
        if (this.left < 0) {
            this.left = 8;
            this.sendTitle();
        }
        if (Bukkit.getOnlinePlayers().size() < 0) {
            this.cancel();
        }

    }

    public String sendTitle() {
        String message = title;
        if (this.left <= 8) {
            if (this.left < 7) {
                message = Scoreboard.getConfig().getString("SCOREBOARD.ANIMATED.TITLE.LINES.1");
            }
            if (this.left < 6) {
                message = Scoreboard.getConfig().getString("SCOREBOARD.ANIMATED.TITLE.LINES.2");
            }
            if (this.left < 5) {
                message = Scoreboard.getConfig().getString("SCOREBOARD.ANIMATED.TITLE.LINES.3");
            }
            if (this.left < 4) {
                message = Scoreboard.getConfig().getString("SCOREBOARD.ANIMATED.TITLE.LINES.4");
            }
            if (this.left < 3) {
                message = Scoreboard.getConfig().getString("SCOREBOARD.ANIMATED.TITLE.LINES.5");
            }
            if (this.left < 2) {
                message = Scoreboard.getConfig().getString("SCOREBOARD.ANIMATED.TITLE.LINES.6");
            }
            if (this.left < 1) {
                message = Scoreboard.getConfig().getString("SCOREBOARD.ANIMATED.TITLE.LINES.7");
            }
        }
        return message;
    }
}
