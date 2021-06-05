package moe.windleaf.WLKits.Plugins.PlayerTag;

import moe.windleaf.WLKits.Main;
import moe.windleaf.WLKits.Plugins.PlayerTag.Commands.playertag;
import moe.windleaf.WLKits.Utils;

import java.io.File;
import java.util.HashMap;

public class PlayerTag {
    public static String path = Main.prefixPath + "PlayerTag" + File.separator + "playerTags.bin";
    public static HashMap<String, String> playerTags;

    @SuppressWarnings("unchecked")
    public void load() {
        Utils.makeDir(Main.prefixPath + "PlayerTag");
        playerTags = (HashMap<String, String>) Utils.loadHashMap(path);
        Utils.eventRegister(new Events());
        Utils.commandCRegister("playertag", new playertag());
    }
}
