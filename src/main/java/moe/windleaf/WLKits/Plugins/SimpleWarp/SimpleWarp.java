package moe.windleaf.WLKits.Plugins.SimpleWarp;

import moe.windleaf.WLKits.Main;
import moe.windleaf.WLKits.Plugins.SimpleWarp.Commands.*;
import moe.windleaf.WLKits.Utils;

import java.io.File;

public class SimpleWarp {
    public static String path = Main.prefixPath + "SimpleWarp" + File.separator + "warps.yml";
    public static final WarpManager warpManager = new WarpManager();

    public void load() {
        warpManager.init();
        Utils.commandRegister("setwarp", new setwarp());
        Utils.commandRegister("delwarp", new delwarp());
        Utils.commandRegister("warps", new warps());
        Utils.commandRegister("warp", new warp());
        Utils.commandRegister("warphelp", new warphelp());
    }
}
