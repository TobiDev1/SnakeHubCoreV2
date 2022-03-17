package dev.aapy.tablist.provider;

import dev.aapy.file.Tablist;
import me.activated.core.plugin.AquaCoreAPI;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import dev.aapy.tablist.TabColumn;
import dev.aapy.tablist.TabLayout;
import dev.aapy.tablist.TabProvider;
import dev.aapy.tablist.skin.Skin;
import dev.aapy.tablist.utils.CC;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class TablistProvider implements TabProvider {
    YamlConfiguration config;

    public TablistProvider() {
        this.config = Tablist.getConfig();
    }

    @Override
    public List<String> getFooter(Player player) {
        return CC.translate(Tablist.getConfig().getStringList("TAB.FOOTER"));
    }


    @Override
    public List<String> getHeader(Player player) {
        return CC.translate(Tablist.getConfig().getStringList("TAB.HEADER"));
    }

    @SuppressWarnings({"deprecation", "unchecked", "rawtypes"})
    public String factions(Player player, String path) {
        path = path.replace("{players_online}", String.valueOf(Bukkit.getOnlinePlayers().size()));
        if (path.contains("|")) {
            path = path.replace("|", "\u2503");
        }
        if (path.contains("{rank}")) {
            path = path.replace("{rank}", AquaCoreAPI.INSTANCE.getPlayerRank(player.getUniqueId()).getColor().toString() + AquaCoreAPI.INSTANCE.getPlayerRank(player.getUniqueId()).getPrefix().toString());
        }
        if (path.contains("{players_max_online}")) {
            path = path.replace("{players_max_online}", String.valueOf(Bukkit.getMaxPlayers()));
        }
        if (path.contains("{ign}")) {
            path = path.replace("{ign}",player.getName());
        }
        return path;
    }


    private TabLayout getInfo(int slot, TabColumn place, Player p) {
        TabLayout object = new TabLayout();

        String text = "";
        switch (place) {
            case LEFT: {
                if (this.config.getString("TAB.LEFT." + slot + ".TEXT") == null ||
                        this.config.getString("TAB.LEFT." + slot + ".TEXT") == " " ||
                        this.config.getString("TAB.LEFT." + slot + ".TEXT") == "") {
                    text = " ";
                    break;
                }
                text = this.config.getString("TAB.LEFT." + slot + ".TEXT");
                break;
            }
            case RIGHT: {
                if (this.config.getString("TAB.RIGHT." + slot + ".TEXT") == null ||
                        this.config.getString("TAB.RIGHT." + slot + ".TEXT") == " " ||
                        this.config.getString("TAB.RIGHT." + slot + ".TEXT") == "") {
                    text = " ";
                    break;
                }
                text = this.config.getString("TAB.RIGHT." + slot + ".TEXT");
                break;
            }
            case MIDDLE: {
                if (this.config.getString("TAB.CENTER." + slot + ".TEXT") == null ||
                        this.config.getString("TAB.CENTER." + slot + ".TEXT") == " " ||
                        this.config.getString("TAB.CENTER." + slot + ".TEXT") == "") {
                    text = " ";
                    break;
                }
                text = this.config.getString("TAB.CENTER." + slot + ".TEXT");
                break;
            }
            case FAR_RIGHT: {
                if (this.config.getString("TAB.FARRIGHT." + slot + ".TEXT") == null ||
                        this.config.getString("TAB.FARRIGHT." + slot + ".TEXT") == " " ||
                        this.config.getString("TAB.FARRIGHT." + slot + ".TEXT") == "") {
                    text = " ";
                    break;
                }
                text = this.config.getString("TAB.FARRIGHT." + slot + ".TEXT");
                break;
            }
        }
        text = factions(p, text);
        object.column(place);
        if (Bukkit.getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {

            object.text(PlaceholderAPI.setPlaceholders(p, text));
        } else {
            object.text(text);

        }
        object.slot(slot);

        String skin = "";
        switch (place) {
            case LEFT: {
                if (this.config.getString("TAB.LEFT." + slot + ".SKIN") == null || this.config.getString("TAB.LEFT." + slot + ".SKIN") == " " || this.config.getString("TAB.LEFT." + slot + ".SKIN") == "") {
                    skin = " ";
                    break;
                }
                skin = this.config.getString("TAB.LEFT." + slot + ".SKIN");
                break;
            }
            case RIGHT: {
                if (this.config.getString("TAB.RIGHT" + slot + ".SKIN") == null || this.config.getString("TAB.RIGHT." + slot + ".SKIN") == " " || this.config.getString("TAB.RIGHT." + slot + ".SKIN") == "") {
                    skin = " ";
                    break;
                }
                skin = this.config.getString("TAB.RIGHT." + slot + ".SKIN");
                break;
            }
            case MIDDLE: {
                if (this.config.getString("TAB.CENTER." + slot + ".SKIN") == null || this.config.getString("TAB.CENTER." + slot + ".SKIN") == " " || this.config.getString("TAB.CENTER." + slot + ".SKIN") == "") {
                    skin = " ";
                    break;
                }
                skin = this.config.getString("TAB.CENTER." + slot + ".SKIN");
                break;
            }
            case FAR_RIGHT: {
                if (this.config.getString("TAB.FARRIGHT." + slot + ".SKIN") == null || this.config.getString("TAB.FARRIGHT." + slot + ".SKIN") == " " || this.config.getString("TAB.FARRIGHT." + slot + ".SKIN") == "") {
                    skin = " ";
                    break;
                }
                skin = this.config.getString("TAB.FARRIGHT." + slot + ".SKIN");
                break;
            }
        }

        object.skin(skins(p, skin));
        return object;
    }


    public Skin skins(Player player, String skinTab) {
        Skin skinDefault = Skin.DEFAULT;


        if (skinTab.contains("{player}")) {
            skinDefault = Skin.getSkin(player);
        }
        if (skinTab.contains("{discord}")) {
            skinDefault = Skin.DISCORD_SKIN;
        }
        if (skinTab.contains("{youtube}")) {
            skinDefault = Skin.YOUTUBE_SKIN;
        }
        if (skinTab.contains("{twitter}")) {
            skinDefault = Skin.TWITTER_SKIN;

        }
        if (skinTab.contains("{facebook}")) {
            skinDefault = Skin.FACEBOOK_SKIN;

        }
        if (skinTab.contains("{store}")) {
            skinDefault = Skin.STORE_SKIN;
        }

        if (skinTab.contains("{green}")) {
            skinDefault = Skin.getDot(ChatColor.GREEN);
        }
        if (skinTab.contains("{blue}")) {
            skinDefault = Skin.getDot(ChatColor.BLUE);
        }
        if (skinTab.contains("{dark_blue}")) {
            skinDefault = Skin.getDot(ChatColor.DARK_BLUE);
        }
        if (skinTab.contains("{dark_aqua}")) {
            skinDefault = Skin.getDot(ChatColor.DARK_AQUA);
        }
        if (skinTab.contains("{dark_purple}")) {
            skinDefault = Skin.getDot(ChatColor.DARK_PURPLE);
        }
        if (skinTab.contains("{light_purple}")) {
            skinDefault = Skin.getDot(ChatColor.LIGHT_PURPLE);
        }
        if (skinTab.contains("{gray}")) {
            skinDefault = Skin.getDot(ChatColor.GRAY);
        }
        if (skinTab.contains("{red}")) {
            skinDefault = Skin.getDot(ChatColor.RED);
        }
        if (skinTab.contains("{yellow}")) {
            skinDefault = Skin.getDot(ChatColor.YELLOW);
        }
        if (skinTab.contains("{dark_green}")) {
            skinDefault = Skin.getDot(ChatColor.DARK_GREEN);
        }
        if (skinTab.contains("{dark_red}")) {
            skinDefault = Skin.getDot(ChatColor.DARK_RED);
        }
        if (skinTab.contains("{gold}")) {
            skinDefault = Skin.getDot(ChatColor.GOLD);
        }
        if (skinTab.contains("{aqua}")) {
            skinDefault = Skin.getDot(ChatColor.AQUA);
        }
        if (skinTab.contains("{white}")) {
            skinDefault = Skin.getDot(ChatColor.WHITE);
        }
        if (skinTab.contains("{dark_gray}")) {
            skinDefault = Skin.getDot(ChatColor.DARK_GRAY);
        }
        if (skinTab.contains("{black}")) {
            skinDefault = Skin.getDot(ChatColor.BLACK);
        }
        if (skinTab.contains("{warning}")) {
            skinDefault = Skin.WARNING_SKIN;
        }
        if (skinTab.contains("{website}")) {
            skinDefault = Skin.WEBSITE_SKIN;
        }
        if (skinTab.contains("{watch}")) {
            skinDefault = Skin.QUEUE_SKIN;
        }
        if (skinTab.contains("{information}")) {
            skinDefault = Skin.INFORMATION_SKIN;
        }
        if (skinTab.contains("{wood_shield}")) {
            skinDefault = Skin.WOOD_SHIELD_SKIN;
        }
        if (skinTab.contains("{diamond_shield}")) {
            skinDefault = Skin.DIAMOND_SHIELD_SKIN;
        }
        if (skinTab.contains("{bow}")) {
            skinDefault = Skin.BOW_SKIN;
        }
        if (skinTab.contains("{potion}")) {
            skinDefault = Skin.POTION_SKIN;
        }
        if (skinTab.contains("{telegram}")) {
            skinDefault = Skin.TELEGRAM_SKIN;
        }
        if (skinTab.contains("{enderchest}")) {
            skinDefault = Skin.ENDERCHEST_SKIN;
        }
        if (skinTab.contains("{coin}")) {
            skinDefault = Skin.COIN_SKIN;
        }
        if (skinTab.contains("{heart}")) {
            skinDefault = Skin.HEART_SKIN;
        }
        if (skinTab.contains("{earth}")) {
            skinDefault = Skin.EARTH_SKIN;
        }
        if (skinTab.contains("{crown}")) {
            skinDefault = Skin.CROWN_SKIN;
        }
        if (skinTab.contains("{castle}")) {
            skinDefault = Skin.CASTLE_SKIN;
        }
        if (skinTab.contains("{ping}")) {
            skinDefault = Skin.PING_SKIN;
        }
        if (skinTab.contains("{stats}")) {
            skinDefault = Skin.STATS_SKIN;
        }
        if (skinTab.contains("{compass}")) {
            skinDefault = Skin.COMPASS_SKIN;
        }

        return skinDefault;

    }


    @Override
    public Set<TabLayout> getProvider(Player player) {
        Set<TabLayout> toReturn = new HashSet<>();


        for (int i = 1; i < 21; ++i) {
            toReturn.add(this.getInfo(i, TabColumn.LEFT, player));
        }
        for (int i = 1; i < 21; ++i) {
            toReturn.add(this.getInfo(i, TabColumn.MIDDLE, player));
        }
        for (int i = 1; i < 21; ++i) {
            toReturn.add(this.getInfo(i, TabColumn.RIGHT, player));
        }
        for (int i = 1; i < 21; ++i) {
            toReturn.add(this.getInfo(i, TabColumn.FAR_RIGHT, player));
        }

        return toReturn;
    }


}