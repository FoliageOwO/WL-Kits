package moe.windleaf.WLKits.Plugins.SimpleMotd;

import moe.windleaf.WLKits.Main;
import moe.windleaf.WLKits.Plugins.SimpleMotd.Commands.motd;
import moe.windleaf.WLKits.Utils;
import moe.windleaf.WLKits.YmlConfig;

public class SimpleMotd {
    public static YmlConfig yml = new YmlConfig(Main.prefixPath + "motd.yml");
    public static Boolean enabled = Main.config().getBoolean("enable-simplemotd");

    public static void load() {
        Utils.commandRegister("motd", new motd());
        Utils.eventRegister(new Events());
    }
}
