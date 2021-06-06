package moe.windleaf.WLKits.Plugins.JoinInfo;

import moe.windleaf.WLKits.Utils;

public class JoinInfo {
    public static void load() {
        Utils.eventRegister(new Events());
    }
}
