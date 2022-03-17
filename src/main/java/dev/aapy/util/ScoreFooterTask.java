package dev.aapy.util;

import dev.aapy.file.Scoreboard;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author 7qv_ on 11/2/2022.
 * @project SnakeHub
 */
public class ScoreFooterTask extends BukkitRunnable {

    public static String footer = "";

    private int left;

    public ScoreFooterTask() {
        this.left = 3;
    }

    @Deprecated
    public void run() {
        --this.left;
        if (this.left < 0) {
            this.left = 3;
            this.sendFooter();
        }
        if (Bukkit.getOnlinePlayers().size() < 0) {
            this.cancel();
        }

    }

    public String sendFooter() {
        String message = footer;
        if (this.left <= 3) {
            if (this.left < 4) {
                message = Scoreboard.getConfig().getString("SCOREBOARD.ANIMATED.FOOTER.LINES.1");
            }
            if (this.left < 3) {
                message = Scoreboard.getConfig().getString("SCOREBOARD.ANIMATED.FOOTER.LINES.2");
            }
            if (this.left < 2) {
                message = Scoreboard.getConfig().getString("SCOREBOARD.ANIMATED.FOOTER.LINES.3");
            }
            if (this.left < 1) {
                message = Scoreboard.getConfig().getString("SCOREBOARD.ANIMATED.FOOTER.LINES.4");
            }
        }
        return message;
    }
}