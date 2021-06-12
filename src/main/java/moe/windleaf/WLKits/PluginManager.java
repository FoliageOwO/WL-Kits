package moe.windleaf.WLKits;

import moe.windleaf.WLKits.Plugins.AntiCreeper.AntiCreeper;
import moe.windleaf.WLKits.Plugins.Disenchant.Disenchant;
import moe.windleaf.WLKits.Plugins.JoinInfo.JoinInfo;
import moe.windleaf.WLKits.Plugins.BackDeath.BackDeath;
import moe.windleaf.WLKits.Plugins.MineBoard.MineBoard;
import moe.windleaf.WLKits.Plugins.PlayerTag.PlayerTag;
import moe.windleaf.WLKits.Plugins.RecipeAdder.RecipeAdder;
import moe.windleaf.WLKits.Plugins.SimpleBack.SimpleBack;
import moe.windleaf.WLKits.Plugins.SimpleHome.SimpleHome;
import moe.windleaf.WLKits.Plugins.SimpleTpa.SimpleTpa;
import moe.windleaf.WLKits.Plugins.SimpleWarp.SimpleWarp;
import moe.windleaf.WLKits.Plugins.SkipNight.SkipNight;
import moe.windleaf.WLKits.Plugins.Suicide.Suicide;

import java.util.ArrayList;

public class PluginManager {
    public PluginManager() { super(); }

    public static ArrayList<String> getPlugins() {
        ArrayList<String> plugins = new ArrayList<>();
        plugins.add("AntiCreeper");
        plugins.add("JoinInfo");
        plugins.add("BackDeath");
        plugins.add("PlayerTag");
        plugins.add("SkipNight");
        plugins.add("Suicide");
        plugins.add("SimpleWarp");
        plugins.add("SimpleTpa");
        plugins.add("SimpleBack");
        plugins.add("RecipeAdder");
        plugins.add("MineBoard");
        plugins.add("SimpleHome");
        plugins.add("Disenchant");
        return plugins;
    }

    public static ArrayList<String> getMPlugins() {
        ArrayList<String> plugins = new ArrayList<>();
        plugins.add("AntiCreeper");
        plugins.add("SkipNight");
        plugins.add("Disenchant");
        return plugins;
    }

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
        RecipeAdder.load();
        MineBoard.load();
        SimpleHome.load();
        Disenchant.load();
    }
}
