package moe.windleaf.WLKits.plugins.WebSocket;

import moe.windleaf.WLKits.Main;
import moe.windleaf.WLKits.Util;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;

public class WebSocket {
    public static Server server = new Server(Main.config().getInt("websocket-port"));
    public static Boolean enabled = Main.config().getBoolean("enable-websocket");

    public static void load() {
        if (enabled) {
            Util.eventRegister(new Events());
            new BukkitRunnable() {
                @Override
                public void run() { server.start(); }
            }.runTaskAsynchronously(Main.I);
        }
    }

    public static void unload() {
        if (enabled) {
            try {
                server.stop();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
