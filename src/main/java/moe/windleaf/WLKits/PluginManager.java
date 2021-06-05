package moe.windleaf.WLKits;

import moe.windleaf.WLKits.Plugins.AntiCreeper.AntiCreeper;
import moe.windleaf.WLKits.Plugins.JoinInfo.JoinInfo;
import moe.windleaf.WLKits.Plugins.BackDeath.BackDeath;
import moe.windleaf.WLKits.Plugins.PlayerTag.PlayerTag;
import moe.windleaf.WLKits.Plugins.SimpleTpa.SimpleTpa;
import moe.windleaf.WLKits.Plugins.SimpleWarp.SimpleWarp;
import moe.windleaf.WLKits.Plugins.SkipNight.SkipNight;
import moe.windleaf.WLKits.Plugins.Suicide.Suicide;

public class PluginManager {
    public PluginManager() { }

    public void load() {
        new AntiCreeper().load();
        new JoinInfo().load();
        new BackDeath().load();
        new PlayerTag().load();
        new SkipNight().load();
        new Suicide().load();
        new SimpleWarp().load();
        new SimpleTpa().load();
    }
}
