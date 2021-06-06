package moe.windleaf.WLKits.Plugins.SkipNight;

import moe.windleaf.WLKits.Main;
import moe.windleaf.WLKits.Utils;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

public class Events implements Listener {
    @EventHandler
    public void _PlayerBedEnterEvent(PlayerBedEnterEvent event) {
        if (Main.I.config.getBoolean("enable-skipnight")) {
            World world = event.getPlayer().getWorld();
            world.setTime(100);
            Utils.logInfoPrefixCustom(Utils.getPrefix("SkipNight"), String.format("&a玩家 &6%s &a跳过了夜晚!", event.getPlayer().getName()));
        }
    }
}
