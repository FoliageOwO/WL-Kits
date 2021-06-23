package moe.windleaf.WLKits.plugins.SimpleTpa;

import moe.windleaf.WLKits.plugins.SimpleTpa.commands.*;
import moe.windleaf.WLKits.Util;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class SimpleTpa {
    public static HashMap<Player, Player> tpaLogs = new HashMap<>();
    public static final String name = "SimpleTpa";

    public static void load() {
        Util.commandRegister("tpa", new tpa());
        Util.commandRegister("tpacancel", new tpacancel());
        Util.commandRegister("tpaccept", new tpaccept());
        Util.commandRegister("tpadeny", new tpadeny());
        Util.commandRegister("tpahelp", new tpahelp());
    }
}
