package moe.windleaf.WLKits.Commands;

import moe.windleaf.WLKits.Main;
import moe.windleaf.WLKits.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class wlkits implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) {
            Utils.invalidArgs(sender, "/wlkits help");
            return false;
        } else {
            switch (args[0]) {
                case "help":
                    Utils.sendHelp(sender, new String[]{
                            "/wlkits help &f: &a查看此帮助",
                            "/wlkits version &f: &a查看 &6WL-Kits &a插件版本"
                    });
                    break;
                case "version":
                    if (sender instanceof Player) {
                        Utils.send(sender, "&f------------------------------------------------");
                        Utils.send(sender, "&f- &aWL-Kits &3[moe.windleaf.WLKits] &eversion: " + Main.version + "&f -");
                        Utils.send(sender, "&f- &1by: WindLeaf &b[www.github.com/WindLeaf233]    &f-");
                        Utils.send(sender, "&f------------------------------------------------");
                    } else {
                        Utils.logInfo("&f------------------------------------------------");
                        Utils.logInfo("&f- &aWL-Kits &3[moe.windleaf.WLKits] &eversion: " + Main.version + "&f -");
                        Utils.logInfo("&f- &1by: WindLeaf &b[www.github.com/WindLeaf233]    &f-");
                        Utils.logInfo("&f------------------------------------------------");
                    }
                    break;
                default:
                    Utils.invalidArgs(sender, "/wlkits help");
            }
            return true;
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        String[] subCommands = {"help", "version"};
        if (args.length > 1) return new ArrayList<>();
        if (args.length == 0) return Arrays.asList(subCommands);
        return Arrays.stream(subCommands).filter(s -> s.startsWith(args[0])).collect(Collectors.toList());
    }
}
