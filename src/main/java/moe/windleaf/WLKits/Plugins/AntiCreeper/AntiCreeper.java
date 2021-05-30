package moe.windleaf.WLKits.Plugins.AntiCreeper;

import moe.windleaf.WLKits.Main;
import moe.windleaf.WLKits.Plugins.AntiCreeper.Commands.anticreeper;
import moe.windleaf.WLKits.Plugins.AntiCreeper.Commands.anticreeper_Completer;

public class AntiCreeper {
    public void load() {
        if (Main.INSTANCE.config.getBoolean("enable-anticreeper")) {
            Main.INSTANCE.eventRegister(new Events());
            Main.INSTANCE.commandRegister("anticreeper", new anticreeper());
            Main.INSTANCE.commandCompleterRegister("anticreeper", new anticreeper_Completer());
        }
    }
}