package moe.windleaf.WLKits.plugins.PlayerTag;

import moe.windleaf.WLKits.Main;
import moe.windleaf.WLKits.plugins.PlayerTag.commands.playertag;
import moe.windleaf.WLKits.Util;

import java.util.HashMap;

public class PlayerTag {
    public static String path = Main.prefixPath + "PlayerTags.bin";
    public static HashMap<String, String> playerTags;

    @SuppressWarnings("unchecked")
    public static void load() {
        playerTags = (HashMap<String, String>) Util.loadHashMap(path);
        Util.eventRegister(new Events());
        Util.commandCRegister("playertag", new playertag());
    }
}
