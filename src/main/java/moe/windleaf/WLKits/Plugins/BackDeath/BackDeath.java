package moe.windleaf.WLKits.Plugins.BackDeath;

import moe.windleaf.WLKits.Plugins.BackDeath.Commands.backdeath;
import moe.windleaf.WLKits.Utils;

public class BackDeath {
    public void load() {
        Utils.eventRegister(new Events());
        Utils.commandRegister("backdeath", new backdeath());
    }
}
