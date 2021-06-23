package moe.windleaf.WLKits.plugins.PlayerTag;

import moe.windleaf.WLKits.Util;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.HashMap;

public class Events implements Listener {
    public static HashMap<String, String> playerTags = PlayerTag.playerTags;

    @EventHandler
    public void _AsyncPlayerChatEvent(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (playerTags.get(Util.getUUIDString(player)) != null) {
            event.setCancelled(true);
            Util.broadcast(playerTags.get(Util.getUUIDString(player)) + "§r<" + player.getName() + "> " + event.getMessage(), player.isOp());
        }
    }
}
