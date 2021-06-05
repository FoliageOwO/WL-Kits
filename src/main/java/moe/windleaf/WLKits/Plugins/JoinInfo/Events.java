package moe.windleaf.WLKits.Plugins.JoinInfo;

import moe.windleaf.WLKits.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Events implements Listener {
    @EventHandler
    public void _PlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage("");
        sendS(player, "&a", "加入");
        sendP(player, "&a", "[+]", "加入");
    }

    @EventHandler
    public void _PlayerQuitEvent(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        event.setQuitMessage("");
        sendS(player, "&c", "离开");
        sendP(player, "&c", "[-]", "离开");
    }

    public void sendS(Player player, String color, String type) {
        Utils.logInfoPrefixCustom(Utils.getPrefix("JoinInfo"), color + "玩家 " +
                player.getName() + " (" + player.getUniqueId() + ") [" + player.getAddress() + "] " + type + "了服务器!");
    }

    public void sendP(Player player, String color, String type1, String type2) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            Utils.send(p, color + type1 + " 玩家 " + player.getName() + " " + type2 + "了服务器");
        }
    }
}
