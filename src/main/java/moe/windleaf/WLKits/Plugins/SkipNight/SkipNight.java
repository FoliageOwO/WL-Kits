package moe.windleaf.WLKits.Plugins.SkipNight;


import moe.windleaf.WLKits.Utils;

public class SkipNight {
    public static void load() {
        Utils.eventRegister(new Events());
    }
}
