package moe.windleaf.WLKits.plugins.SimpleHome;

import moe.windleaf.WLKits.Main;
import moe.windleaf.WLKits.plugins.SimpleHome.commands.delhome;
import moe.windleaf.WLKits.plugins.SimpleHome.commands.home;
import moe.windleaf.WLKits.plugins.SimpleHome.commands.homehelp;
import moe.windleaf.WLKits.plugins.SimpleHome.commands.sethome;
import moe.windleaf.WLKits.Util;

import java.util.HashMap;

public class SimpleHome {
    public static String path = Main.prefixPath + "Homes.bin";
    @SuppressWarnings("unchecked") public static HashMap<String, String> homes = (HashMap<String, String>) Util.loadHashMap(path);

    public static void load() {
        Util.commandRegister("sethome", new sethome());
        Util.commandRegister("delhome", new delhome());
        Util.commandRegister("home", new home());
        Util.commandRegister("homehelp", new homehelp());
    }
}
