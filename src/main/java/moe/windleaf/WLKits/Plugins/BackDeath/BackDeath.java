package moe.windleaf.WLKits.Plugins.BackDeath;

import moe.windleaf.WLKits.Main;
import moe.windleaf.WLKits.Plugins.BackDeath.Commands.backdeath;

public class BackDeath {
    public void load() {
        Main.INSTANCE.eventRegister(new Events());
        Main.INSTANCE.commandRegister("backdeath", new backdeath());
    }
}
