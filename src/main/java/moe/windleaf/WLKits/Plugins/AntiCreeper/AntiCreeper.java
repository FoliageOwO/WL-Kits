package moe.windleaf.WLKits.Plugins.AntiCreeper;

import moe.windleaf.WLKits.Main;
import moe.windleaf.WLKits.Plugins.AntiCreeper.Commands.anticreeper;
import moe.windleaf.WLKits.Utils;

public class AntiCreeper {
    public void load() {
        if (Main.I.config.getBoolean("enable-anticreeper")) {
            Utils.eventRegister(new Events());
            Utils.commandCRegister("anticreeper", new anticreeper());
        }
    }
}