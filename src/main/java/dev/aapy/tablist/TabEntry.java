package dev.aapy.tablist;


import dev.aapy.tablist.skin.Skin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.OfflinePlayer;

@Getter
@Setter
@AllArgsConstructor
public class TabEntry {

    private String id;
    private OfflinePlayer offlinePlayer;
    private String text;
    private PlayerTablist tab;
    private Skin skin;
    private TabColumn column;
    private int slot;
    private int rawSlot;
    private int latency;

}
