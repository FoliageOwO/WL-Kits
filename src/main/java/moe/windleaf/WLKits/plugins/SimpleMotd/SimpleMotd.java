package moe.windleaf.WLKits.plugins.SimpleMotd;

import moe.windleaf.WLKits.Main;
import moe.windleaf.WLKits.plugins.SimpleMotd.commands.motd;
import moe.windleaf.WLKits.Util;
import moe.windleaf.WLKits.YmlConfig;

public class SimpleMotd {
    public static YmlConfig yml = new YmlConfig(Main.prefixPath + "motd.yml");
    public static Boolean enabled = Main.config().getBoolean("enable-simplemotd");

    public static void load() {
        Util.commandRegister("motd", new motd());
        Util.eventRegister(new Events());
    }
}
