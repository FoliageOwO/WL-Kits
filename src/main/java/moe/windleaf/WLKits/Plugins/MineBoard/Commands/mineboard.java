package moe.windleaf.WLKits.Plugins.MineBoard.Commands;

import moe.windleaf.WLKits.Main;
import moe.windleaf.WLKits.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class mineboard implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (Utils.hasCommandPermission(sender, "mineboard")) {
            boolean if_enabled = Main.I.config.getBoolean("enable-mineboard");
            String color;
            if (if_enabled) { color = "&a"; } else { color = "&c"; }
            if (args.length == 0) {
                Utils.sendPrefix(sender, "&6MineBoard 开启状态: " + color + if_enabled);
            } else {
                switch (args[0]) {
                    case "on":
                        if (if_enabled) { Utils.sendPrefix(sender, "&9MineBoard 已经处于开启状态!"); } else {
                            Main.I.config.set("enable-mineboard", true);
                            Main.I.saveConfig();
                            Utils.sendPrefix(sender, "&aMineBoard 已开启!");
                        }
                        break;
                    case "off":
                        if (!if_enabled) { Utils.sendPrefix(sender, "&9MineBoard 已经处于关闭状态!"); } else {
                            Main.I.config.set("enable-mineboard", false);
                            Main.I.saveConfig();
                            Utils.sendPrefix(sender, "&cMineBoard 已关闭!");
                        }
                        break;
                    default:
                        Utils.sendPrefix(sender, "&c错误的参数, 用法: &6/mineboard [on|off]&c.");
                }
            }
            return true;
        } else {
            Utils.doNotHavePermission(sender);
            return false;
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        String[] subCommands = {"on", "off"};
        if (args.length > 1) return new ArrayList<>();
        if (args.length == 0) return Arrays.asList(subCommands);
        return Arrays.stream(subCommands).filter(s -> s.startsWith(args[0])).collect(Collectors.toList());
    }
}
