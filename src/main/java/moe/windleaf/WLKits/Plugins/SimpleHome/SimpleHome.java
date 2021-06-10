package moe.windleaf.WLKits.Plugins.SimpleHome;

import moe.windleaf.WLKits.Main;
import moe.windleaf.WLKits.Plugins.SimpleHome.Commands.delhome;
import moe.windleaf.WLKits.Plugins.SimpleHome.Commands.home;
import moe.windleaf.WLKits.Plugins.SimpleHome.Commands.homehelp;
import moe.windleaf.WLKits.Plugins.SimpleHome.Commands.sethome;
import moe.windleaf.WLKits.Utils;
import org.bukkit.Location;

import java.io.File;
import java.util.HashMap;

public class SimpleHome {
    public static String path = Main.prefixPath + "SimpleHome" + File.separator + "homes.bin";
    @SuppressWarnings("unchecked") public static HashMap<String, Location> homes = (HashMap<String, Location>) Utils.loadHashMap(path);

    public static void load() {
        Utils.commandRegister("sethome", new sethome());
        Utils.commandRegister("delhome", new delhome());
        Utils.commandRegister("home", new home());
        Utils.commandRegister("homehelp", new homehelp());
    }
}
