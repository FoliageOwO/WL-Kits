package moe.windleaf.WLKits.Plugins.SimpleBack;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class Events implements Listener {
    @EventHandler
    public void _PlayerTeleportEvent(PlayerTeleportEvent event) {
        SimpleBack.teleportLog.put(event.getPlayer(), event.getFrom());
    }
}
