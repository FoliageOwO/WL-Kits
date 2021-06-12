package moe.windleaf.WLKits.Plugins.SimpleWarp.Commands;

import moe.windleaf.WLKits.MessageGetter;
import moe.windleaf.WLKits.Plugins.SimpleWarp.SimpleWarp;
import moe.windleaf.WLKits.Utils;
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

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) {
            Utils.invalidArgs(sender, "/warphelp", "SimpleWarp");
            return false;
        } else {
            HashMap<String, String> i = new HashMap<>();
            i.put("name", args[0]);
            i.put("playerName", sender.getName());
            if (SimpleWarp.warpManager.warps.getKeys(false).contains(args[0])) {
                SimpleWarp.warpManager.warps.set(args[0], null);
                Utils.smartSendPrefix(sender, Utils.insert(m.get("删除"), i), "SimpleWarp");
                Utils.broadcastPlayersPrefix(Utils.getPluginPrefix("SimpleWarp") + Utils.insert(m.get("广播"), i));
            } else {
                Utils.smartSendPrefix(sender, Utils.insert(m.get("不存在"), i), "SimpleWarp");
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
