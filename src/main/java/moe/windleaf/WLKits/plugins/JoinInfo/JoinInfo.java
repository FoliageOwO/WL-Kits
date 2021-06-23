package moe.windleaf.WLKits.plugins.JoinInfo;

import moe.windleaf.WLKits.Util;

@SuppressWarnings("unused")
public class JoinInfo {
    public static void load() {
        Util.eventRegister(new Events());
    }
}
