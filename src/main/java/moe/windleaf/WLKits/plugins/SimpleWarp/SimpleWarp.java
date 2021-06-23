package moe.windleaf.WLKits.plugins.SimpleWarp;

import moe.windleaf.WLKits.Main;
import moe.windleaf.WLKits.plugins.SimpleWarp.commands.*;
import moe.windleaf.WLKits.Util;

public class SimpleWarp {
    public static String path = Main.prefixPath  + "Warps.yml";
    public static final WarpManager warpManager = new WarpManager();

    public static void load() {
        warpManager.init();
        Util.commandRegister("setwarp", new setwarp());
        Util.commandRegister("delwarp", new delwarp());
        Util.commandRegister("warps", new warps());
        Util.commandRegister("warp", new warp());
        Util.commandRegister("warphelp", new warphelp());
        Util.commandRegister("showwarp", new showwarp());
    }
}
