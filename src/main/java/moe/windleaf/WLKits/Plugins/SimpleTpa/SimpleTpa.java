package moe.windleaf.WLKits.Plugins.SimpleTpa;

import moe.windleaf.WLKits.Plugins.SimpleTpa.Commands.*;
import moe.windleaf.WLKits.Utils;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class SimpleTpa {
    public static HashMap<Player, Player> tpaLogs = new HashMap<>();

    public static void load() {
        Utils.commandRegister("tpa", new tpa());
        Utils.commandRegister("tpacancel", new tpacancel());
        Utils.commandRegister("tpaccept", new tpaccept());
        Utils.commandRegister("tpadeny", new tpadeny());
        Utils.commandRegister("tpahelp", new tpahelp());
    }
}
