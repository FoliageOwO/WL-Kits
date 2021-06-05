package moe.windleaf.WLKits.Plugins.SkipNight;


import moe.windleaf.WLKits.Plugins.SkipNight.Commands.skipnight;
import moe.windleaf.WLKits.Utils;

public class SkipNight {
    public void load() {
        Utils.commandRegister("skipnight", new skipnight());
        Utils.eventRegister(new Events());
    }
}
