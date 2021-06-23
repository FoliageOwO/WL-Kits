package moe.windleaf.WLKits.plugins.Suicide;

import moe.windleaf.WLKits.plugins.Suicide.commands.suicide;
import moe.windleaf.WLKits.Util;
import org.bukkit.entity.Player;

public class Suicide {
    public static Player lastSuicide = null;

    public static void load() {
        Util.commandRegister("suicide", new suicide());
        Util.eventRegister(new Events());
    }
}
