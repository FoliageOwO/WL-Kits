package moe.windleaf.WLKits.Plugins.BackDeath;

import java.util.HashMap;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class Events implements Listener {
    public static HashMap<Player, Location> tpLogs = new HashMap<>();

    @EventHandler
    public void _PlayerDeathEvent(PlayerDeathEvent event) {
        tpLogs.put(event.getEntity(), event.getEntity().getLocation());
    }
}
