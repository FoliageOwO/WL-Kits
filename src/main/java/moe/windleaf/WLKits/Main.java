package moe.windleaf.WLKits;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Logger;

public final class Main extends JavaPlugin {
    public static Main INSTANCE;
    public Logger logger = Logger.getLogger("WL-Kits");
    public String name = "WL-Kits";
    public String prefix = getPrefix(name, "§a");
    public org.bukkit.plugin.PluginManager pluginManager = getServer().getPluginManager();
    public FileConfiguration config;

    @Override
    public void onEnable() {
        long startTime = System.currentTimeMillis();
        String version = "0.0.2";
        
        logger.info("§f------------------------------------------------");
        logger.info("§f- §aWL-Kits §3[moe.windleaf.WLKits] §eversion: " + version + "§f -");
        logger.info("§f- §1by: WindLeaf §b[www.github.com/WindLeaf233]    §f-");
        logger.info("§f------------------------------------------------");

        config = getConfig();
        config.options().copyDefaults();
        saveDefaultConfig();

        INSTANCE = this;

        PluginManager pluginManager = new PluginManager();
        pluginManager.load();

        ArrayList<String> plugins = getPlugins();
        for (String i : plugins) {
            logger.info("§f加载插件 §3" + i + "§f 中...");
        }

        long endTime = System.currentTimeMillis();

        logger.info("§f加载完成, 用时 §e" + (endTime - startTime) + "ms§f!");
    }

    @Override
    public void onDisable() {
        logger.info("§f已卸载!");
    }

    public void eventRegister(Listener listener) {
        pluginManager.registerEvents(listener, this);
    }

    public void commandRegister(String name, CommandExecutor executor) {
        Objects.requireNonNull(getCommand(name)).setExecutor(executor);
    }

    public void commandCompleterRegister(String name, TabCompleter completer) {
        Objects.requireNonNull(getCommand(name)).setTabCompleter(completer);
    }

    private ArrayList<String> getPlugins() {
        ArrayList<String> plugins = new ArrayList<>();
        plugins.add("AntiCreeper");
        plugins.add("JoinInfo");
        plugins.add("BackDeath");
        plugins.add("PlayerTag");
        return plugins;
    }

    public String getPrefix(String name, String color) {
        return "§f[" + color + name + "§f]" + " ";
    }

    public String getPrefix(String name) {
        return "§f[§3" + name + "§f]" + " ";
    }
}
