package moe.windleaf.WLKits.Plugins.MineBoard.Commands;

import moe.windleaf.WLKits.Main;
import moe.windleaf.WLKits.MessageGetter;
import moe.windleaf.WLKits.Plugins.MineBoard.Events;
import moe.windleaf.WLKits.Plugins.MineBoard.MineBoard;
import moe.windleaf.WLKits.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.scoreboard.DisplaySlot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class mineboard implements CommandExecutor, TabCompleter {
    MessageGetter m = new MessageGetter("MineBoard");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (Utils.hasCommandPermission(sender, "mineboard")) {
            boolean if_enabled = Main.config().getBoolean("enable-mineboard");
            String color;
            if (if_enabled) { color = "&a"; } else { color = "&c"; }
            if (args.length == 0) {
                Utils.smartSendPrefix(sender, "&6MineBoard 开启状态: " + color + if_enabled, "MineBoard");
            } else {
                switch (args[0]) {
                    case "on":
                        if (if_enabled) { Utils.smartSendPrefix(sender, "&9MineBoard 已经处于开启状态!", "MineBoard"); } else {
                            Main.config().set("enable-mineboard", true);
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
                            Main.config().set("enable-mineboard", false);
                            Main.I.saveConfig();
                            MineBoard.scoreboard.clearSlot(DisplaySlot.SIDEBAR);
                            Utils.smartSendPrefix(sender, "&cMineBoard 已关闭!", "MineBoard");
                        }
                        break;
                    case "clear":
                        for (String string : MineBoard.scores.keySet()) { MineBoard.scores.replace(string, 0); }
                        Events.clear();
                        Events.refreshBoard();
                        Utils.smartSendPrefix(sender, m.get("清除"), "MineBoard");
                        break;
                    default:
                        Utils.smartSendPrefix(sender, m.get("参数错误"), "MineBoard");
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
