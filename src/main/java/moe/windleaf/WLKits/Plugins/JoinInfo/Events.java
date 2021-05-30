package moe.windleaf.WLKits.Plugins.JoinInfo;

import moe.windleaf.WLKits.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Events implements Listener {
    private final String prefix = Main.INSTANCE.getPrefix("JoinInfo");

    @EventHandler
    public void _PlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage("");
        Main.INSTANCE.logger.info(prefix + "§a玩家 " +
                player.getName() + " (" + player.getUniqueId() + ") [" + player.getAddress() + "] 加入了服务器");
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.sendMessage("§a[+] 玩家 " + player.getName() + " 加入了服务器");
        }
    }

    @EventHandler
    public void _PlayerQuitEvent(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        event.setQuitMessage("");
        Main.INSTANCE.logger.info(prefix + "§c玩家 " +
                player.getName() + " (" + player.getUniqueId() + ") [" + player.getAddress() + "] 离开了服务器");
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.sendMessage("§c[-] 玩家 " + player.getName() + " 离开了服务器");
        }
    }
}
