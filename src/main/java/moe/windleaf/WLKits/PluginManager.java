package moe.windleaf.WLKits;

import moe.windleaf.WLKits.Plugins.AntiCreeper.AntiCreeper;
import moe.windleaf.WLKits.Plugins.JoinInfo.JoinInfo;
import moe.windleaf.WLKits.Plugins.BackDeath.BackDeath;
import moe.windleaf.WLKits.Plugins.PlayerTag.PlayerTag;

public class PluginManager {
    public PluginManager() { }

    public void load() {
        new AntiCreeper().load();
        new JoinInfo().load();
        new BackDeath().load();
        new PlayerTag().load();
    }
}
