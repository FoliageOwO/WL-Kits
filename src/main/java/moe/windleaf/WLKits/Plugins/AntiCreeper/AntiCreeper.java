package moe.windleaf.WLKits.Plugins.AntiCreeper;

import moe.windleaf.WLKits.Main;
import moe.windleaf.WLKits.Utils;

public class AntiCreeper {
    public static void load() {
        if (Main.config().getBoolean("enable-anticreeper")) {
            Utils.eventRegister(new Events());
        }
    }
}