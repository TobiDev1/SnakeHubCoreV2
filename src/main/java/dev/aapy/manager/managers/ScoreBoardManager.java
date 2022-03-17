package dev.aapy.manager.managers;

import dev.aapy.SnakeHub;
import dev.aapy.file.Config;
import dev.aapy.manager.handler.Handler;
import dev.aapy.scoreboard.ScoreBoardProvider;
import dev.aapy.scoreboard.assamble.Assemble;
import dev.aapy.scoreboard.assamble.AssembleStyle;
import dev.aapy.util.ScoreFooterTask;
import dev.aapy.util.ScoreTitleTask;
import lombok.Getter;

/**
 * @author 7qv_ on 20/2/2022.
 * @project SnakeHub
 */

@Getter
public final class ScoreBoardManager extends Handler {

    private ScoreFooterTask scoreTask;
    private ScoreTitleTask scoreTitleTask;

    public ScoreBoardManager(SnakeHub plugin) {
        super(plugin);
    }

    @Override
    public void enable() {
        Assemble assemble = new Assemble(this.getPlugin(), new ScoreBoardProvider());
        assemble.setTicks(2);
        assemble.setAssembleStyle(AssembleStyle.VIPER);

        if (Config.getConfig().getBoolean("BOOLEANS.SCOREBOARD.ANIMATED.FOOTER")) {
            (this.scoreTask = new ScoreFooterTask()).runTaskTimerAsynchronously(this.getPlugin(), 0L, 20L);
        }
        if (Config.getConfig().getBoolean("BOOLEANS.SCOREBOARD.ANIMATED.TITLE")) {
            (this.scoreTitleTask = new ScoreTitleTask()).runTaskTimerAsynchronously(this.getPlugin(), 0L, 20L);
        }
    }
}
