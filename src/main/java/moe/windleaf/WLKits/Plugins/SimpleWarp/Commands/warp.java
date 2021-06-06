package moe.windleaf.WLKits.Plugins.SimpleWarp.Commands;

import moe.windleaf.WLKits.Plugins.SimpleWarp.SimpleWarp;
import moe.windleaf.WLKits.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class warp implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Utils.mustPlayer(sender);
            return false;
        } else {
            if (args.length < 1) {
                Utils.invalidArgs(sender, "/warphelp");
                return false;
            } else {
                if (SimpleWarp.warpManager.warps.contains(args[0])) {
                    Location location = new Location(
                            Bukkit.getWorld(get(args[0] + ".world")), getDouble(args[0] + ".x"),
                            getDouble(args[0] + ".y"), getDouble(args[0] + ".z"),
                            getFloat(args[0] + ".yaw"), getFloat(args[0] + ".pitch"));
                    ((Player) sender).teleport(location);
                    Utils.sendPrefix(sender, String.format("&a成功传送到地标 &6%s&a!", args[0]));
                } else {
                    Utils.sendPrefix(sender, String.format("&c没有叫做 &6%s &c的地标!", args[0]));
                }
                return true;
            }
        }
    }

    private static String get(String path) {
        return SimpleWarp.warpManager.warps.getString(path);
    }

    private static Double getDouble(String path) {
        return SimpleWarp.warpManager.warps.getDouble(path);
    }

    private static float getFloat(String path) {
        return SimpleWarp.warpManager.warps.getInt(path);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> tmp = SimpleWarp.warpManager.getWarps();
        List<Object> filter = Arrays.stream(tmp.toArray()).filter(s -> s.toString().startsWith(args[0])).collect(Collectors.toList());
        List<String> warps = new ArrayList<>();
        for (Object i : filter) { warps.add(i.toString()); }
        return warps;
    }
}
