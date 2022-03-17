package dev.aapy.tablist.impl;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import dev.aapy.tablist.utils.CC;
import dev.aapy.tablist.utils.Reflection;
import dev.aapy.tablist.PlayerTablist;
import dev.aapy.tablist.TabColumn;
import dev.aapy.tablist.TabEntry;
import dev.aapy.tablist.TabNMS;
import dev.aapy.tablist.playerversion.PlayerVersion;
import dev.aapy.tablist.playerversion.PlayerVersionHandler;
import dev.aapy.tablist.skin.Skin;
import dev.aapy.tablist.utils.LegacyClient;
import net.minecraft.server.v1_8_R3.*;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo.EnumPlayerInfoAction;
import net.minecraft.server.v1_8_R3.WorldSettings.EnumGamemode;
import org.apache.commons.lang.StringEscapeUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;


public class v1_8TabImpl implements TabNMS {

    public v1_8TabImpl() {
    }

    @Override
    public TabEntry createFakePlayer(PlayerTablist playerTablist, String string, TabColumn column, Integer slot, Integer rawSlot) {
        final OfflinePlayer offlinePlayer = new OfflinePlayer() {
            private final UUID uuid = UUID.randomUUID();

            @Override
            public boolean isOnline() {
                return true;
            }

            @Override
            public String getName() {
                return string;
            }

            @Override
            public UUID getUniqueId() {
                return uuid;
            }

            @Override
            public boolean isBanned() {
                return false;
            }

            @Override
            public void setBanned(boolean b) {

            }

            @Override
            public boolean isWhitelisted() {
                return false;
            }

            @Override
            public void setWhitelisted(boolean b) {

            }

            @Override
            public Player getPlayer() {
                return null;
            }

            @Override
            public long getFirstPlayed() {
                return 0;
            }

            @Override
            public long getLastPlayed() {
                return 0;
            }


            @Override
            public boolean hasPlayedBefore() {
                return false;
            }

            @Override
            public Location getBedSpawnLocation() {
                return null;
            }

            @Override
            public Map<String, Object> serialize() {
                return null;
            }

            @Override
            public boolean isOp() {
                return false;
            }

            @Override
            public void setOp(boolean b) {

            }
        };
        final Player player = playerTablist.getPlayer();
        final PlayerVersion playerVersion = PlayerVersionHandler.getPlayerVersion(player);

        GameProfile profile = new GameProfile(offlinePlayer.getUniqueId(), (playerVersion != PlayerVersion.v1_7 ? string : LegacyClient.ENTRY.get(rawSlot - 1) + ""));

        if (playerVersion != PlayerVersion.v1_7) {
            profile.getProperties().removeAll("textures");
            profile.getProperties().put("textures", new Property("textures", Skin.DEFAULT.getValue(), Skin.DEFAULT.getSignature()));
        }

        sendPacket(playerTablist.getPlayer(), PacketPlayOutPlayerInfoWrapper.newAddPacket(profile, 1, player));

        return new TabEntry(string, offlinePlayer, "", playerTablist, Skin.DEFAULT, column, slot, rawSlot, 0);
    }

    @SuppressWarnings("unused")
    @Override
    public void updateFakeName(PlayerTablist playerTablist, TabEntry tabEntry, String text) {
        if (tabEntry.getText().equals(text)) {
            return;
        }

        final Player player = playerTablist.getPlayer();
        final PlayerVersion playerVersion = PlayerVersionHandler.getPlayerVersion(player);
        String[] newStrings = PlayerTablist.splitStrings(text, tabEntry.getRawSlot());
        if (playerVersion == PlayerVersion.v1_7) {

            Team team = player.getScoreboard().getTeam(LegacyClient.NAMES.get(tabEntry.getRawSlot() - 1));
            team.setPrefix(ChatColor.translateAlternateColorCodes('&', newStrings[0]));
            if (newStrings.length > 1) {
                team.setSuffix(ChatColor.translateAlternateColorCodes('&', newStrings[1]));
            } else {
                team.setSuffix("");
            }
        } else {

            GameProfile gameProfile = new GameProfile(tabEntry.getOfflinePlayer().getUniqueId(), tabEntry.getId());

            sendPacket(player, PacketPlayOutPlayerInfoWrapper.updateDisplayName(gameProfile, CC.translate(newStrings.length > 1 ? newStrings[0] + newStrings[1] : newStrings[0]), tabEntry.getLatency()));

        }
        tabEntry.setText(text);
    }

    @Override
    public void updateFakeLatency(PlayerTablist playerTablist, TabEntry tabEntry, Integer latency) {
        if (tabEntry.getLatency() == latency) {
            return;
        }

        GameProfile profile = new GameProfile(tabEntry.getOfflinePlayer().getUniqueId(), LegacyClient.ENTRY.get(tabEntry.getRawSlot() - 1) + "");

        sendPacket(playerTablist.getPlayer(), PacketPlayOutPlayerInfoWrapper.updateLatency(profile, CC.translate(tabEntry.getText()), latency));

        tabEntry.setLatency(latency);
    }

    @Override
    public void updateFakeSkin(PlayerTablist playerTablist, TabEntry tabEntry, Skin skin) {
        if (tabEntry.getSkin() == skin) {
            return;
        }
        GameProfile profile = new GameProfile(tabEntry.getOfflinePlayer().getUniqueId(), LegacyClient.ENTRY.get(tabEntry.getRawSlot() - 1) + "");
        profile.getProperties().removeAll("textures");
        profile.getProperties().put("textures", new Property("textures", skin.getValue(), skin.getSignature()));

        sendPacket(playerTablist.getPlayer(), PacketPlayOutPlayerInfoWrapper.newRemovePacket(profile, tabEntry.getLatency()));
        sendPacket(playerTablist.getPlayer(), PacketPlayOutPlayerInfoWrapper.newAddPacket(profile, tabEntry.getLatency(), playerTablist.getPlayer()));

        tabEntry.setSkin(skin);
    }

    @Override
    public void updateHeaderAndFooter(Player player, List<String> header, List<String> footer) {
        IChatBaseComponent headerComponent = IChatBaseComponent.ChatSerializer.a("{text:\"" + StringEscapeUtils.escapeJava(this.getListFromString(CC.translate(header))) + "\"}");
        IChatBaseComponent footerComponent = IChatBaseComponent.ChatSerializer.a("{text:\"" + StringEscapeUtils.escapeJava(this.getListFromString(CC.translate(footer))) + "\"}");
        PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();

        try {
            Field headerField = packet.getClass().getDeclaredField("a");
            headerField.setAccessible(true);
            headerField.set(packet, headerComponent);
            headerField.setAccessible(!headerField.isAccessible());

            Field footerField = packet.getClass().getDeclaredField("b");
            footerField.setAccessible(true);
            footerField.set(packet, footerComponent);
            footerField.setAccessible(!footerField.isAccessible());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        sendPacket(player, packet);
    }

    public String getListFromString(List<String> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < list.size(); ++i) {
            stringBuilder.append(list.get(i));
            if (i != list.size() - 1) {
                stringBuilder.append('\n');
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public Skin getSkin(Player player) {
        if (Skin.CACHE.containsKey(player.getUniqueId())) return Skin.CACHE.get(player.getUniqueId());

        try {
            CraftPlayer craftPlayer = (CraftPlayer) player;
            EntityPlayer entityPlayer = craftPlayer.getHandle();

            Property property = entityPlayer.getProfile().getProperties().get("textures").stream().findFirst().orElse(null);

            if (property != null) {
                return Skin.CACHE.put(player.getUniqueId(), new Skin(property.getValue(), property.getSignature()));
            }
        } catch (Exception ignored) {
            // ignored
        }

        return Skin.STEVE;
    }

    private void sendPacket(Player player, Packet packet) {
        getEntity(player).playerConnection.sendPacket(packet);
    }

    private EntityPlayer getEntity(Player player) {
        return ((CraftPlayer) player).getHandle();
    }


    public static class PacketPlayOutPlayerInfoWrapper {

        private static MethodHandle PLAYER_INFO_SETTER;
        private static MethodHandle ACTION_SETTER;

        static {
            try {
                MethodHandles.Lookup lookup = MethodHandles.lookup();

                PLAYER_INFO_SETTER = lookup.unreflectSetter(Reflection.setAccessibleAndGet(PacketPlayOutPlayerInfo.class, "b"));
                ACTION_SETTER = lookup.unreflectSetter(Reflection.setAccessibleAndGet(PacketPlayOutPlayerInfo.class, "a"));
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }

        public static PacketPlayOutPlayerInfo newAddPacket(com.mojang.authlib.GameProfile gameProfile, Integer ping, Player playerTab) {
            PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo();
            PlayerVersion playerVersion = PlayerVersionHandler.getPlayerVersion(playerTab);

            try {
                PacketPlayOutPlayerInfo.PlayerInfoData infoData = new PacketPlayOutPlayerInfo.PlayerInfoData(gameProfile, ping,
                        EnumGamemode.NOT_SET, new ChatComponentText(


                        (playerVersion != PlayerVersion.v1_7 ? "" : gameProfile.getName())));

                PLAYER_INFO_SETTER.invokeExact(packet, Collections.singletonList(infoData));
                ACTION_SETTER.invokeExact(packet, EnumPlayerInfoAction.ADD_PLAYER);
            } catch (Throwable t) {
                t.printStackTrace();
            }

            return packet;
        }

        public static PacketPlayOutPlayerInfo newRemovePacket(GameProfile gameProfile, Integer ping) {
            PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo();

            try {
                PacketPlayOutPlayerInfo.PlayerInfoData infoData = new PacketPlayOutPlayerInfo.PlayerInfoData(gameProfile, ping,
                        EnumGamemode.NOT_SET, new ChatComponentText(gameProfile.getName()));

                PLAYER_INFO_SETTER.invokeExact(packet, Collections.singletonList(infoData));
                ACTION_SETTER.invokeExact(packet, EnumPlayerInfoAction.REMOVE_PLAYER);
            } catch (Throwable t) {
                t.printStackTrace();
            }

            return packet;
        }

        public static PacketPlayOutPlayerInfo updateDisplayName(GameProfile profile, String displayName, Integer ping) {
            PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo();

            try {
                PacketPlayOutPlayerInfo.PlayerInfoData infoData = new PacketPlayOutPlayerInfo.PlayerInfoData(profile, ping,
                        EnumGamemode.NOT_SET, new ChatComponentText(displayName));

                PLAYER_INFO_SETTER.invokeExact(packet, Collections.singletonList(infoData));
                ACTION_SETTER.invokeExact(packet, EnumPlayerInfoAction.UPDATE_DISPLAY_NAME);
            } catch (Throwable t) {
                t.printStackTrace();
            }

            return packet;
        }


        public static PacketPlayOutPlayerInfo updateLatency(GameProfile profile, String displayName, Integer ping) {
            PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo();

            try {
                PacketPlayOutPlayerInfo.PlayerInfoData infoData = new PacketPlayOutPlayerInfo.PlayerInfoData(

                        profile,
                        ping,
                        EnumGamemode.NOT_SET,
                        new ChatComponentText(displayName));

                PLAYER_INFO_SETTER.invokeExact(packet, Collections.singletonList(infoData));
                ACTION_SETTER.invokeExact(packet, EnumPlayerInfoAction.UPDATE_LATENCY);
            } catch (Throwable t) {
                t.printStackTrace();
            }

            return packet;
        }

    }


}
