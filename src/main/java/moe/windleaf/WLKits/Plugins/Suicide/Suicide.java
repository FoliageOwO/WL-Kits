package moe.windleaf.WLKits.Plugins.Suicide;

import moe.windleaf.WLKits.Plugins.Suicide.Commands.suicide;
import moe.windleaf.WLKits.Utils;

public class Suicide {
    public static String lastSuicide = "";

    public void load() {
        Utils.commandRegister("suicide", new suicide());
        Utils.eventRegister(new Events());
    }
}
