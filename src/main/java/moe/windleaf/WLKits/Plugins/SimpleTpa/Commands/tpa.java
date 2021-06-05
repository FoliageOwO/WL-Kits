package moe.windleaf.WLKits.Plugins.SimpleTpa.Commands;

import moe.windleaf.WLKits.Plugins.SimpleTpa.SimpleTpa;
import moe.windleaf.WLKits.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class tpa implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Utils.mustPlayer(sender);
            return false;
        } else {
            if (args.length < 1) {
                Utils.invalidArgs(sender, "/tpahelp");
                return false;
            } else {
                Player player = Bukkit.getPlayer(args[0]);
                if (player == sender) {
                    Utils.sendPrefix(sender, "&c你不能给自己发送传送请求!");
                    return false;
                } else {
                    SimpleTpa.tpaLogs.put(player, (Player) sender);

                    assert player != null;
                    Utils.sendPrefix(player, "&a你有一个待接受的传送请求:");
                    Utils.sendPrefix(player, String.format("&a玩家 &6%s &a想传送到你这里,", sender.getName()));
                    Utils.sendPrefix(player, "&a接受请求: &2/tpaccept&a, 拒绝请求: &4/tpadeny&a.");

                    Utils.sendPrefix(sender, String.format("&a已向玩家 &6%s &a发送一个传送请求, 等待对方接受!", player.getName()));
                    Utils.sendPrefix(sender, "&a想要取消这个传送请求, 你可以输入 &6/tpacancel&a.");
                    return true;
                }
            }
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> list = new ArrayList<>();
        for (Player p : Bukkit.getOnlinePlayers()) { list.add(p.getName()); }
            return list;
    }
}
