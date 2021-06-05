package moe.windleaf.WLKits.Plugins.JoinInfo;

import moe.windleaf.WLKits.Utils;

public class JoinInfo {
    public void load() {
        Utils.eventRegister(new Events());
    }
}
