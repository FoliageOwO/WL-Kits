package moe.windleaf.WLKits;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class wlkits implements CommandExecutor, TabCompleter {
    private static final String line1 = "&f-------------------------------------------------------";
    private static final String line2 = "&f   &aWL-Kits &3[moe.windleaf.WLKits] &9version: %s";
    private static final String line3 = "&f      &1by: WindLeaf  &b<www.github.com/WindLeaf233>";
    private static final String line4 = "&f-------------------------------------------------------";
    private static final String path = Main.prefixPath + "plugins.bin";
    private static HashMap<String, Boolean> pluginsStatus = new HashMap<>();

    @SuppressWarnings("unchecked")
    public static void load() {
        File f = new File(path);
        if (!f.exists() || f.length() == 0) {
            pluginsStatus.put("AntiCreeper", true);
            pluginsStatus.put("SkipNight", true);
            Util.saveHashMap(pluginsStatus, path);
        } else {
            pluginsStatus = (HashMap<String, Boolean>) Util.loadHashMap(path);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) {
            Util.invalidArgs(sender, "/wlkits help", "");
            return false;
        } else {
            switch (args[0]) {
                case "help":
                    Map<String, String> helps = new HashMap<>();
                    helps.put("/wlkits help", "查看此帮助");
                    helps.put("/wlkits version", "查看 &6WL-Kits &a插件版本");
                    helps.put("/wlkits plugins", "查看所有加载的所有子插件");
                    helps.put("/wlkits plugin [name] <on|off|check>", "开启/关闭/查看特殊子插件");
                    Util.sendHelp(sender, helps);
                    break;
                case "version":
                    if (sender instanceof Player) {
                        Util.autoLogSendPrefix(sender, "&aWL-Kits &3[moe.windleaf.WLKits]");
                        Util.autoLogSendPrefix(sender, String.format("&9Version: %s", Main.version));
                        Util.autoLogSendPrefix(sender, String.format("&eServer is running on &2%s", Bukkit.getServer().getVersion()));
                        Util.autoLogSendPrefix(sender, "&1by: WindLeaf &b<https://www.github.com/WindLeaf233>");
                    } else {
                        Util.autoLogSend(line1);
                        Util.autoLogSend(String.format(line2, Main.version));
                        Util.autoLogSend(line3);
                        Util.autoLogSend(line4);
                    }
                    break;
                case "plugins":
                    for (String s : PluginManager.getPlugins()) Util.sendPrefix(sender, "&a- &6" + s);
                    break;
                case "plugin":
                    if (args.length == 1) Util.invalidArgs(sender, "/wlkits help", "");
                    if (args.length == 3) {
                        switch (args[2]) {
                            case "on":
                                if (hasPlugin(args[1])) {
                                    if (isMPlugin(args[1])) {
                                        pluginsStatus.replace(args[1], true);
                                        Util.saveHashMap(pluginsStatus, path);
                                        Main.config().set("enable-" + args[1].toLowerCase(), true);
                                        Main.I.saveConfig();
                                        Util.sendPrefix(sender, String.format("&a开启子插件 &6%s &a成功!", args[1]));
                                    } else {
                                        cannotManage(sender, args[1]);
                                    }
                                } else noThisPlugin(sender, args[1]);
                                break;
                            case "off":
                                if (hasPlugin(args[1])) {
                                    if (isMPlugin(args[1])) {
                                        pluginsStatus.replace(args[1], false);
                                        Util.saveHashMap(pluginsStatus, path);
                                        Main.config().set("enable-" + args[1].toLowerCase(), false);
                                        Main.I.saveConfig();
                                        Util.sendPrefix(sender, String.format("&c关闭子插件 &6%s &c成功!", args[1]));
                                    } else {
                                        cannotManage(sender, args[1]);
                                    }
                                } else noThisPlugin(sender, args[1]);
                                break;
                            case "check":
                                if (hasPlugin(args[1])) {
                                    if (isMPlugin(args[1])) {
                                        if (pluginsStatus.get(args[1]) == null) {
                                            pluginsStatus.put(args[1], Main.config().getBoolean("enable-" + args[1].toLowerCase()));
                                            Util.saveHashMap(pluginsStatus, path);
                                        }
                                        g(args, sender);
                                    } else {
                                        cannotManage(sender, args[1]);
                                        break;
                                    }
                                } else noThisPlugin(sender, args[1]);
                                break;
                            default:
                                Util.invalidArgs(sender, "/wlkits help", "");
                                break;
                        }
                    }
                    break;
                default:
                    Util.invalidArgs(sender, "/wlkits help", "");
                    break;
            }
            return true;
        }
    }

    private static void g(String[] args, CommandSender sender) {
        if (pluginsStatus.get(args[1])) { Util.sendPrefix(sender, String.format("&a子插件 &6%s &a为开启状态!", args[1]));
        } else { Util.sendPrefix(sender, String.format("&c子插件 &6%s &a为关闭状态!", args[1])); }

    }

    private static boolean isMPlugin(String name) { return PluginManager.getMPlugins().contains(name); }

    private static boolean hasPlugin(String name) { return PluginManager.getPlugins().contains(name); }

    private static void noThisPlugin(CommandSender sender, String name) { Util.sendPrefix(sender, String.format("&c没有找到子插件 &6%s&c!", name)); }

    private static void cannotManage(CommandSender sender, String name) { Util.sendPrefix(sender, String.format("&c无法开启/关闭/查询 &6%s&c, 请使用对应的命令管理!", name)); }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        String[] subCommands = {"help", "version", "plugins", "plugin"};
        String[] pluginSubCommands = {"on", "off", "check"};
        if (args.length == 0) return Arrays.asList(subCommands);
        if (args.length == 1) return Arrays.stream(subCommands).filter(s -> s.startsWith(args[0])).collect(Collectors.toList());
        if (args[0].equals("plugin")) {
            Object[] MPluginsO = PluginManager.getMPlugins().toArray();
            String[] MPlugins = Arrays.copyOf(MPluginsO, MPluginsO.length, String[].class);
            if (args.length == 2) return Arrays.stream(MPlugins).filter(s -> s.startsWith(args[1])).collect(Collectors.toList());
            if (args.length == 3) return Arrays.stream(pluginSubCommands).filter(s -> s.startsWith(args[2])).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
