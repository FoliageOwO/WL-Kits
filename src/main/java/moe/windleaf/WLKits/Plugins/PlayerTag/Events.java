package moe.windleaf.WLKits.Plugins.PlayerTag;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashMap;

public class Events implements Listener {
    public static HashMap<Player, String> playerTags = PlayerTag.playerTags;

    @EventHandler
    public void _AsyncPlayerChatEvent(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (playerTags.get(player) != null) {
            event.setCancelled(true);
            Bukkit.broadcastMessage(playerTags.get(player) + "§r<" + player.getName() + "> " + event.getMessage());
        }
    }
}
