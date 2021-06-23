package moe.windleaf.WLKits.plugins.BackDeath;

import moe.windleaf.WLKits.plugins.BackDeath.commands.backdeath;
import moe.windleaf.WLKits.Util;

@SuppressWarnings("unused")
public class BackDeath {
    public static void load() {
        Util.eventRegister(new Events());
        Util.commandRegister("backdeath", new backdeath());
    }
}
