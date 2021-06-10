package moe.windleaf.WLKits.Commands;

import moe.windleaf.WLKits.Main;
import moe.windleaf.WLKits.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.stream.Collectors;

public class wlkits implements CommandExecutor, TabCompleter {
    private static final String line1 = "&f-------------------------------------------------------";
    private static final String line2 = "&f   &aWL-Kits &3[moe.windleaf.WLKits] &9version: %s";
    private static final String line3 = "&f      &1by: WindLeaf  &b<www.github.com/WindLeaf233>";
    private static final String line4 = "&f-------------------------------------------------------";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) {
            Utils.invalidArgs(sender, "/wlkits help", "");
            return false;
        } else {
            switch (args[0]) {
                case "help":
                    Map<String, String> helps = new HashMap<>();
                    helps.put("/wlkits help", "查看此帮助");
                    helps.put("/wlkits version", "查看 &6WL-Kits &a插件版本");
                    Utils.sendHelp(sender, helps);
                    break;
                case "version":
                    if (sender instanceof Player) {
                        Utils.autoLogSendPrefix(sender, "&aWL-Kits &3[moe.windleaf.WLKits]");
                        Utils.autoLogSendPrefix(sender, String.format("&9Version: %s", Main.version));
                        Utils.autoLogSendPrefix(sender, String.format("&eServer is running on &2%s", Bukkit.getServer().getVersion()));
                        Utils.autoLogSendPrefix(sender, "&1by: WindLeaf &b<https://www.github.com/WindLeaf233>");
                    } else {
                        Utils.autoLogSend(line1);
                        Utils.autoLogSend(String.format(line2, Main.version));
                        Utils.autoLogSend(line3);
                        Utils.autoLogSend(line4);
                    }
                    break;
                default:
                    Utils.invalidArgs(sender, "/wlkits help", "");
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
