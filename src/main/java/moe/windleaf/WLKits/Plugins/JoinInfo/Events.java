package moe.windleaf.WLKits.Plugins.JoinInfo;

import moe.windleaf.WLKits.MessageGetter;
import moe.windleaf.WLKits.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;

public class Events implements Listener {
    MessageGetter m = new MessageGetter("JoinInfo");
    HashMap<String, String> i = new HashMap<>();

    @EventHandler
    public void _PlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage("");
        sendS(player, "&a", "加入");
        i.put("playerName", player.getName());
        Utils.broadcastPlayers(Utils.insert(m.get("加入"), i));
    }

    @EventHandler
    public void _PlayerQuitEvent(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        event.setQuitMessage("");
        sendS(player, "&c", "离开");
        i.put("playerName", player.getName());
        Utils.broadcastPlayers(Utils.insert(m.get("离开"), i));
    }

    public void sendS(Player player, String color, String type) {
        Utils.logInfoPrefixCustom(Utils.getPluginPrefix("JoinInfo"), color + "玩家 " +
                player.getName() + " (" + player.getUniqueId() + ") [" + player.getAddress() + "] " + type + "了服务器!");
    }
}
