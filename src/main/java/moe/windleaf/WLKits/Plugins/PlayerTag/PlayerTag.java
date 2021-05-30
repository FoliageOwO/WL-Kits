package moe.windleaf.WLKits.Plugins.PlayerTag;

import moe.windleaf.WLKits.Main;
import moe.windleaf.WLKits.Plugins.PlayerTag.Commands.playertag;
import moe.windleaf.WLKits.Plugins.PlayerTag.Commands.playertag_Completer;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class PlayerTag {
    public static HashMap<Player, String> playerTags = new HashMap<>();

    public void load() {
        Main.INSTANCE.eventRegister(new Events());
        Main.INSTANCE.commandRegister("playertag", new playertag());
        Main.INSTANCE.commandCompleterRegister("playertag", new playertag_Completer());
    }
}
