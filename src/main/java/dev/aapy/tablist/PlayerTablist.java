package dev.aapy.tablist;

import dev.aapy.tablist.impl.v1_7TabImpl;
import dev.aapy.tablist.playerversion.PlayerVersion;
import dev.aapy.tablist.playerversion.PlayerVersionHandler;
import dev.aapy.tablist.skin.Skin;
import dev.aapy.tablist.utils.LegacyClient;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public class PlayerTablist {

    private final Player player;
    private Scoreboard scoreboard;
    private int lastHeaderFooter;

    private final Set<TabEntry> currentEntrySet = new HashSet<>();

    @SuppressWarnings("deprecation")
    public PlayerTablist(Player player) {
        this.player = player;

        scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

        //Scoreboard
        if (!this.player.getScoreboard().equals(Bukkit.getScoreboardManager().getMainScoreboard())) {
            scoreboard = player.getScoreboard();
        }

        player.setScoreboard(scoreboard);

        this.setup();
        Team team1 = player.getScoreboard().getTeam("\\u000181");
        if (team1 == null) {
            team1 = player.getScoreboard().registerNewTeam("\\u000181");
        }
        team1.addEntry(player.getName());
        for (Player loopPlayer : Bukkit.getServer().getOnlinePlayers()) {
            Team team = loopPlayer.getScoreboard().getTeam("\\u000181");
            if (team == null) {
                team = loopPlayer.getScoreboard().registerNewTeam("\\u000181");
            }
            team.addEntry(player.getName());
            team.addEntry(loopPlayer.getName());
            team1.addEntry(loopPlayer.getName());
            team1.addEntry(player.getName());
        }
    }

    public static String[] splitStrings(String text, int rawSlot) {
        if (text.length() > 16) {
            String prefix = text.substring(0, 16);
            String suffix;

            if (prefix.charAt(15) == ChatColor.COLOR_CHAR || prefix.charAt(15) == '&') {
                prefix = prefix.substring(0, 15);
                suffix = text.substring(15);
            } else if (prefix.charAt(14) == ChatColor.COLOR_CHAR || prefix.charAt(14) == '&') {
                prefix = prefix.substring(0, 14);
                suffix = text.substring(14);
            } else {
                suffix = ChatColor.getLastColors(ChatColor.translateAlternateColorCodes('&', prefix)) + text.substring(16);
            }

            if (suffix.length() > 16) {
                suffix = suffix.substring(0, 16);
            }

            return new String[]{
                    prefix,
                    suffix
            };
        } else {
            return new String[]{
                    text
            };
        }
    }

    private void setup() {
        final int possibleSlots = (PlayerVersionHandler.getPlayerVersion(player) == PlayerVersion.v1_7 ? 60 : 80);
        for (int i = 1; i <= possibleSlots; i++) {
            final TabColumn tabColumn = TabColumn.getFromSlot(player, i);
            if (tabColumn == null) {
                continue;
            }
            TabEntry tabEntry = Tab.getInstance().getImplementation().createFakePlayer(
                    this,
                    "0" + (i > 9 ? i : "0" + i) + "|Tab",
                    tabColumn,
                    tabColumn.getNumb(player, i),
                    i
            );
            if (Bukkit.getPluginManager().getPlugin("Featherboard") == null
                    && (PlayerVersionHandler.version.getPlayerVersion(player) == PlayerVersion.v1_7
                    || Tab.getInstance().getImplementation() instanceof v1_7TabImpl)) {
                Team team = player.getScoreboard().getTeam(LegacyClient.NAMES.get(i - 1));
                if (team != null) {
                    team.unregister();
                }
                team = player.getScoreboard().registerNewTeam(LegacyClient.NAMES.get(i - 1));
                team.setPrefix("");
                team.setSuffix("");

                team.addEntry(LegacyClient.ENTRY.get(i - 1));

            }
            currentEntrySet.add(tabEntry);
        }

        if (Bukkit.getPluginManager().getPlugin("Featherboard") != null) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    for (int i = 1; i <= possibleSlots; i++) {
                        if (PlayerVersionHandler.version.getPlayerVersion(player) == PlayerVersion.v1_7 || Tab.getInstance().getImplementation() instanceof v1_7TabImpl) {
                            Team team = player.getScoreboard().getTeam(LegacyClient.NAMES.get(i - 1));
                            if (team != null) {
                                team.unregister();
                            }
                            team = player.getScoreboard().registerNewTeam(LegacyClient.NAMES.get(i - 1));
                            team.setPrefix("");
                            team.setSuffix("");

                            team.addEntry(LegacyClient.ENTRY.get(i - 1));

                        }
                    }
                }
            }.runTaskLater(Tab.getInstance().getPlugin(), 40);
        }
    }

    public void update() {
        if (PlayerVersionHandler.getPlayerVersion(player) != PlayerVersion.v1_7) {
            List<String> header = Tab.getInstance().getProvider().getHeader(player);
            List<String> footer = Tab.getInstance().getProvider().getFooter(player);

            int headerFooter = header.hashCode() + footer.hashCode();

            if (headerFooter != lastHeaderFooter) {
                lastHeaderFooter = headerFooter;
                Tab.getInstance().getImplementation().updateHeaderAndFooter(player, header, footer);
            }
        }


        Set<TabEntry> lastSet = new HashSet<>(currentEntrySet);
        for (TabLayout layout : Tab.getInstance().getProvider().getProvider(player)) {
            TabEntry tabEntry = getEntry(layout.getColumn(), layout.getSlot());
            if (tabEntry != null) {
                lastSet.remove(tabEntry);
                Tab.getInstance().getImplementation().updateFakeName(this, tabEntry, layout.getText());
                Tab.getInstance().getImplementation().updateFakeLatency(this, tabEntry, layout.getPing());
                if (PlayerVersionHandler.getPlayerVersion(player) != PlayerVersion.v1_7) {
                    if (layout.getSkin() != null || !tabEntry.getSkin().equals(layout.getSkin())) {

                        Tab.getInstance().getImplementation().updateFakeSkin(this, tabEntry, layout.getSkin());
                    }
                }
            }
        }
        for (TabEntry tabEntry : lastSet) {
            Tab.getInstance().getImplementation().updateFakeName(this, tabEntry, "");
            Tab.getInstance().getImplementation().updateFakeLatency(this, tabEntry, -1);
            if (PlayerVersionHandler.getPlayerVersion(player) != PlayerVersion.v1_7) {
                Tab.getInstance().getImplementation().updateFakeSkin(this, tabEntry, Skin.DEFAULT);
            }
        }
        lastSet.clear();
    }

    public TabEntry getEntry(TabColumn column, Integer slot) {
        for (TabEntry entry : currentEntrySet) {
            if (entry.getColumn().name().equalsIgnoreCase(column.name()) && entry.getSlot() == slot) {
                return entry;
            }
        }
        return null;
    }
}
