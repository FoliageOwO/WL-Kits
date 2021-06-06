package moe.windleaf.WLKits.Plugins.Suicide;

import moe.windleaf.WLKits.Plugins.Suicide.Commands.suicide;
import moe.windleaf.WLKits.Utils;
import org.bukkit.entity.Player;

public class Suicide {
    public static Player lastSuicide = null;

    public static void load() {
        Utils.commandRegister("suicide", new suicide());
        Utils.eventRegister(new Events());
    }
}
