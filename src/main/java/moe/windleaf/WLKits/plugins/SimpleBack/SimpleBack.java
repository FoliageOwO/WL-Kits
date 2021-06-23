package moe.windleaf.WLKits.plugins.SimpleBack;

import moe.windleaf.WLKits.plugins.SimpleBack.commands.back;
import moe.windleaf.WLKits.Util;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class SimpleBack {
    public static HashMap<Player, Location> teleportLog = new HashMap<>();

    public static void load() {
        Util.eventRegister(new Events());
        Util.commandRegister("back", new back());
    }
}
