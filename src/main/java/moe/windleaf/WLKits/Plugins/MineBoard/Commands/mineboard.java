package moe.windleaf.WLKits.Plugins.MineBoard.Commands;

import moe.windleaf.WLKits.Main;
import moe.windleaf.WLKits.Plugins.MineBoard.Events;
import moe.windleaf.WLKits.Plugins.MineBoard.MineBoard;
import moe.windleaf.WLKits.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

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
                Utils.smartSendPrefix(sender, "&6MineBoard 开启状态: " + color + if_enabled, "MineBoard");
            } else {
                switch (args[0]) {
                    case "on":
                        if (if_enabled) { Utils.smartSendPrefix(sender, "&9MineBoard 已经处于开启状态!", "MineBoard"); } else {
                            Main.I.config.set("enable-mineboard", true);
                            Main.I.saveConfig();
                            MineBoard.unload();
                            MineBoard.register();
                            MineBoard.objective.setDisplaySlot(DisplaySlot.SIDEBAR);
                            Events.refreshBoard();
                            Utils.smartSendPrefix(sender, "&aMineBoard 已开启!", "MineBoard");
                        }
                        break;
                    case "off":
                        if (!if_enabled) { Utils.smartSendPrefix(sender, "&9MineBoard 已经处于关闭状态!", "MineBoard"); } else {
                            Main.I.config.set("enable-mineboard", false);
                            Main.I.saveConfig();
                            MineBoard.scoreboard.clearSlot(DisplaySlot.SIDEBAR);
                            Utils.smartSendPrefix(sender, "&cMineBoard 已关闭!", "MineBoard");
                        }
                        break;
                    case "clear":
                        for (String string : MineBoard.scores.keySet()) { MineBoard.scores.replace(string, 0); }
                        Events.clear();
                        for (Player player : Bukkit.getOnlinePlayers()) { Events.show(player, 0, true); }
                        Events.refreshBoard();
                        Utils.smartSendPrefix(sender, "&a已清除 &6MineBoard&a!", "MineBoard");
                        break;
                    default:
                        Utils.smartSendPrefix(sender, "&c错误的参数, 用法: &6/mineboard [on|off|clear]&c.", "MineBoard");
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
        String[] subCommands = {"on", "off", "clear"};
        if (args.length > 1) return new ArrayList<>();
        if (args.length == 0) return Arrays.asList(subCommands);
        return Arrays.stream(subCommands).filter(s -> s.startsWith(args[0])).collect(Collectors.toList());
    }
}
