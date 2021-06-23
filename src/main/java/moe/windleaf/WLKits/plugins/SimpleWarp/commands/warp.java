package moe.windleaf.WLKits.plugins.SimpleWarp.commands;

import moe.windleaf.WLKits.MessageGetter;
import moe.windleaf.WLKits.Sender;
import moe.windleaf.WLKits.plugins.SimpleWarp.SimpleWarp;
import moe.windleaf.WLKits.Util;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class warp implements CommandExecutor, TabCompleter {
    MessageGetter m = new MessageGetter("SimpleWarp");
    Sender s = new Sender("SimpleWarp");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Util.mustPlayer(sender, "SimpleWarp");
            return false;
        } else {
            if (args.length < 1) {
                Util.invalidArgs(sender, "/warphelp", "SimpleWarp");
                return false;
            } else {
                HashMap<String, String> i = new HashMap<>();
                i.put("name", args[0]);
                if (SimpleWarp.warpManager.warps.contains(args[0])) {
                    Location location = new Location(
                            Bukkit.getWorld(get(args[0] + ".world")), getDouble(args[0] + ".x"),
                            getDouble(args[0] + ".y"), getDouble(args[0] + ".z"),
                            getFloat(args[0] + ".yaw"), getFloat(args[0] + ".pitch"));
                    ((Player) sender).teleport(location);
                    s.send(sender, Util.insert(m.get("传送成功"), i));
                } else {
                    s.send(sender, Util.insert(m.get("找不到地标"), i));
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
