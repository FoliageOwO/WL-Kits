package moe.windleaf.WLKits.plugins.SimpleMotd;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class Events implements Listener {
    @EventHandler
    public void _ServerListPingEvent(ServerListPingEvent event) {
        if (SimpleMotd.enabled) {
            String line1 = pd((String) SimpleMotd.yml.get("line1"));
            String line2 = pd((String) SimpleMotd.yml.get("line2"));
            event.setMotd(line1 + "\n" + line2);
        } else {
            event.setMotd(Bukkit.getMotd());
        }
    }

    public String pd(String s) {
        if (s == null) { return ""; } else { return s; }
    }
}
