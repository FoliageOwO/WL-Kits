package moe.windleaf.WLKits.plugins.SkipNight;


import moe.windleaf.WLKits.Util;

@SuppressWarnings("unused")
public class SkipNight {
    public static void load() {
        Util.eventRegister(new Events());
    }
}
