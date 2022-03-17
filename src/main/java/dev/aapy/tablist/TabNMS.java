package dev.aapy.tablist;

import dev.aapy.tablist.skin.Skin;
import org.bukkit.entity.Player;

import java.util.List;

public interface TabNMS {

    TabEntry createFakePlayer(
            PlayerTablist playerTablist, String string, TabColumn column, Integer slot, Integer rawSlot
    );

    void updateFakeName(
            PlayerTablist playerTablist, TabEntry tabEntry, String text
    );

    void updateFakeLatency(
            PlayerTablist playerTablist, TabEntry tabEntry, Integer latency
    );

    void updateFakeSkin(
            PlayerTablist playerTablist, TabEntry tabEntry, Skin skin
    );

    void updateHeaderAndFooter(
            Player player, List<String> header, List<String> footer
    );

    Skin getSkin(
            Player player);

}
