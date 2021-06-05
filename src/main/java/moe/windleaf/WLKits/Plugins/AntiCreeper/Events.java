package moe.windleaf.WLKits.Plugins.AntiCreeper;

import moe.windleaf.WLKits.Main;
import moe.windleaf.WLKits.Utils;
import org.bukkit.Location;
import org.bukkit.entity.Creeper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class Events implements Listener{
    private final String prefix = Utils.getPrefix("AntiCreeper");

    @EventHandler
    public void _EntityExplodeEvent(EntityExplodeEvent event) {
        if (Main.I.config.getBoolean("enable-anticreeper")) {
            if (event.getEntity() instanceof Creeper) {
                Location location = event.getLocation();
                event.setCancelled(true);
                Main.I.logger.info(
                        prefix + "§a成功阻止 §fCreeper §a爆炸破坏地形! 爆炸坐标: " +
                                "§3X:" + location.getBlockX() + "§f, " +
                                "§aY:" + location.getBlockY() + "§f, " +
                                "§cZ:" + location.getBlockZ() + "§f.");
            }
        }
    }
}
