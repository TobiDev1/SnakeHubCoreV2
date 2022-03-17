package dev.aapy.scoreboard;

import dev.aapy.SnakeHub;
import dev.aapy.file.Config;
import dev.aapy.file.Scoreboard;
import dev.aapy.manager.managers.ScoreBoardManager;
import dev.aapy.scoreboard.assamble.AssembleAdapter;
import dev.aapy.util.CC;
import dev.aapy.util.SnakeUtil;
import me.clip.placeholderapi.PlaceholderAPI;
import org.apache.commons.lang.StringEscapeUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 7qv_ on 7/2/2022.
 * @project SnakeHub
 */

public class ScoreBoardProvider implements AssembleAdapter {

    private final ScoreBoardManager scoreboard = SnakeHub.getInst().getManager().getScoreboard();

    @Override
    public String getTitle(Player player) {
        return Config.getConfig().getBoolean("BOOLEANS.SCOREBOARD.ANIMATED.TITLE") ? this.scoreboard.getScoreTitleTask().sendTitle() : CC.translate(Scoreboard.getConfig().getString("SCOREBOARD.TITLE"));
    }

    @Override
    public List<String> getLines(Player player) {
        final List<String> list = new ArrayList<>();
        for (String lines : Scoreboard.getConfig().getStringList("SCOREBOARD.LINES")) {
                lines = lines.replace("{arrow}", StringEscapeUtils.unescapeJava("\\u2a20"));
                lines = lines.replace("{placeholder:date}", SnakeUtil.getDate());
                lines = lines.replace("{placeholder:time}", SnakeUtil.getHour());
                lines = lines.replace("{players_online}", Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getServer().getMaxPlayers());
                lines = lines.replace("{rank}", SnakeHub.getInst().getPermission().getPermission().getPrefix(player));
                lines = lines.replace("{ign}", player.getName());
                lines = lines.replace("{footers}", Config.getConfig().getBoolean("BOOLEANS.SCOREBOARD.ANIMATED.FOOTER") ? this.scoreboard.getScoreTask().sendFooter() : CC.translate(Scoreboard.getConfig().getString("SCOREBOARD.FOOTER")));

                list.add(PlaceholderAPI.setPlaceholders(player, lines));
        }
        return list;
    }
}