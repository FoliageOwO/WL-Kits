package moe.windleaf.WLKits.Plugins.PlayerTag;

import moe.windleaf.WLKits.Utils;
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
        if (playerTags.get(Utils.getUUIDString(player)) != null) {
            event.setCancelled(true);
            Utils.broadcast(playerTags.get(Utils.getUUIDString(player)) + "§r<" + player.getName() + "> " + event.getMessage(), player.isOp());
        }
    }
}
