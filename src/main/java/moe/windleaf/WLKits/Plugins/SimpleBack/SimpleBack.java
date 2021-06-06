package moe.windleaf.WLKits.Plugins.SimpleBack;

import moe.windleaf.WLKits.Plugins.SimpleBack.Commands.back;
import moe.windleaf.WLKits.Utils;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class SimpleBack {
    public static HashMap<Player, Location> teleportLog = new HashMap<>();

    public static void load() {
        Utils.eventRegister(new Events());
        Utils.commandRegister("back", new back());
    }
}
