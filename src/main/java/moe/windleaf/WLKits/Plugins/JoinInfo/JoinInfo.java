package moe.windleaf.WLKits.Plugins.JoinInfo;

import moe.windleaf.WLKits.Main;

public class JoinInfo {
    public void load() {
        Main.INSTANCE.eventRegister(new Events());
    }
}
