package moe.windleaf.WLKits;

import moe.windleaf.WLKits.Commands.wlkits;
import moe.windleaf.WLKits.Plugins.RecipeAdder.RecipeAdder;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

/*
         ___       __       ___                          ___  __        ___      _________    ________
        |\  \     |\  \    |\  \                        |\  \|\  \     |\  \    |\___   ___\ |\   ____\
        \ \  \    \ \  \   \ \  \         ____________  \ \  \/  /|_   \ \  \   \|___ \  \_| \ \  \___|_
         \ \  \  __\ \  \   \ \  \       |\____________\ \ \   ___  \   \ \  \       \ \  \   \ \_____  \
          \ \  \|\__\_\  \   \ \  \____  \|____________|  \ \  \\ \  \   \ \  \       \ \  \   \|____|\  \
           \ \____________\   \ \_______\                  \ \__\\ \__\   \ \__\       \ \__\    ____\_\  \
            \|____________|    \|_______|                   \|__| \|__|    \|__|        \|__|   |\_________\
                                                                                                \|_________|
 */

public final class Main extends JavaPlugin {
    public static Main I;
    public static String version;
    public SLogger logger = new SLogger("WL-Kits");
    public String name = "WL-Kits";
    public String prefix = Utils.getPrefix(name);
    public org.bukkit.plugin.PluginManager pluginManager = getServer().getPluginManager();
    public static String prefixPath = Utils.getPath() + "plugins" + File.separator + "WL-Kits" + File.separator;
    public static Map<String, Object> messages;
    public static YmlConfig configYml;

    @Override
    public void onEnable() {
        // 相关初始化
        I = this;
        long startTime = System.currentTimeMillis();
        version = "0.0.5";

        // 显示 WL-Kits 相关的信息

        Utils.logInfo("&f-------------------------------------------------------");
        Utils.logInfo(String.format("&f   &aWL-Kits &3[moe.windleaf.WLKits] &9version: %s", version));
        Utils.logInfo("&f      &1by: WindLeaf  &b<www.github.com/WindLeaf233>");
        Utils.logInfo("&f-------------------------------------------------------");

        // 配置文件
        smartSaveResource("config.yml");
        smartSaveResource("messages.yml");
        configYml = new YmlConfig(prefixPath + "config.yml");
        messages = new YmlConfig(prefixPath + "messages.yml").ob;

        // pluginManager 初始化
        PluginManager pluginManager = new PluginManager();
        pluginManager.load();

        // wlkits 命令初始化
        wlkits.load();

        // 依次加载子插件
        ArrayList<String> plugins = PluginManager.getPlugins();
        for (String i : plugins) { Utils.logInfoPrefix("&f加载子插件 &3" + i + "&f..."); }

        // 注册 wlkits 命令
        Utils.commandRegister("wlkits", new wlkits());

        // 记录加载完成时的时间
        long endTime = System.currentTimeMillis();

        // 显示加载完成
        Utils.logInfoPrefix("&f加载完成, 用时 &e" + (endTime - startTime) + "ms&f!");
    }

    @Override
    public void onDisable() {
        for (String i : RecipeAdder.recipeManager.loadedRecipes.keySet()) { Bukkit.removeRecipe(new NamespacedKey(this, i)); }
        Bukkit.removeRecipe(new NamespacedKey(this, "disenchantmentbook"));
        Utils.logInfo("&f已卸载!");
    }

    public static FileConfiguration config() {
        return I.getConfig();
    }

    public static void smartSaveResource(String name) {
        if (!(new File(prefixPath + name).exists())) { I.saveResource(name, false); }
    }
}
