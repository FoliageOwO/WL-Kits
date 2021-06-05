package moe.windleaf.WLKits.Plugins.Suicide;

import moe.windleaf.WLKits.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class Events implements Listener {
    @EventHandler
    public void _PlayerDeathEvent(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if (player.getName().equals(Suicide.lastSuicide)) {
            event.setDeathMessage("");
            Utils.broadcastPlayers(String.format("&c玩家 &6%s &c结束了他的生命.", player.getName()));
            Suicide.lastSuicide = "";
        }
    }
}
