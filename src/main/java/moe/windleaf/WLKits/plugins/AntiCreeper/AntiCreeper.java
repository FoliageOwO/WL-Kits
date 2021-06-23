package moe.windleaf.WLKits.plugins.AntiCreeper;

import moe.windleaf.WLKits.Main;
import moe.windleaf.WLKits.Util;

@SuppressWarnings("unused")
public class AntiCreeper {
    public static void load() {
        if (Main.config().getBoolean("enable-anticreeper")) {
            Util.eventRegister(new Events());
        }
    }
}