package moe.windleaf.WLKits.plugins.SimpleWarp.commands;

import moe.windleaf.WLKits.MessageGetter;
import moe.windleaf.WLKits.Sender;
import moe.windleaf.WLKits.plugins.SimpleWarp.SimpleWarp;
import moe.windleaf.WLKits.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class delwarp implements CommandExecutor, TabCompleter {
    MessageGetter m = new MessageGetter("SimpleWarp");
    Sender s = new Sender("SimpleWarp");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) {
            Util.invalidArgs(sender, "/warphelp", "SimpleWarp");
            return false;
        } else {
            HashMap<String, String> i = new HashMap<>();
            i.put("name", args[0]);
            i.put("playerName", sender.getName());
            if (SimpleWarp.warpManager.warps.getKeys(false).contains(args[0])) {
                SimpleWarp.warpManager.warps.set(args[0], null);
                s.send(sender, Util.insert(m.get("删除"), i));
                Util.broadcastPlayers(Util.getPluginPrefix("SimpleWarp") + Util.insert(m.get("广播"), i));
            } else {
                s.send(sender, Util.insert(m.get("不存在"), i));
            }
            return true;
        }
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
