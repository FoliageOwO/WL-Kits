package moe.windleaf.WLKits;

import moe.windleaf.WLKits.Plugins.AntiCreeper.AntiCreeper;
import moe.windleaf.WLKits.Plugins.JoinInfo.JoinInfo;
import moe.windleaf.WLKits.Plugins.BackDeath.BackDeath;
import moe.windleaf.WLKits.Plugins.PlayerTag.PlayerTag;
import moe.windleaf.WLKits.Plugins.SimpleBack.SimpleBack;
import moe.windleaf.WLKits.Plugins.SimpleTpa.SimpleTpa;
import moe.windleaf.WLKits.Plugins.SimpleWarp.SimpleWarp;
import moe.windleaf.WLKits.Plugins.SkipNight.SkipNight;
import moe.windleaf.WLKits.Plugins.Suicide.Suicide;

public class PluginManager {
    public PluginManager() { super(); }

    public void load() {
        AntiCreeper.load();
        JoinInfo.load();
        BackDeath.load();
        PlayerTag.load();
        SkipNight.load();
        Suicide.load();
        SimpleWarp.load();
        SimpleTpa.load();
        SimpleBack.load();
    }
}
