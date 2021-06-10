package moe.windleaf.WLKits.Plugins.SimpleHome.Commands;

import moe.windleaf.WLKits.Plugins.SimpleHome.SimpleHome;
import moe.windleaf.WLKits.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class sethome implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Utils.mustPlayer(sender, "SimpleHome");
            return false;
        } else {
            Player player = (Player) sender;
            if (SimpleHome.homes.containsKey(Utils.getUUIDString(player))) {
                Utils.smartSendPrefix(player, "&c你已经设置过家了, 不能重复设置!", "SimpleHome");
            } else {
                SimpleHome.homes.put(Utils.getUUIDString(player), player.getLocation());
                Utils.saveHashMap(SimpleHome.homes, SimpleHome.path);
                Utils.smartSendPrefix(player, "&a设置家成功, 你以后可以使用 &6/home &a来回家了!", "SimpleHome");
            }
            return true;
        }
    }
}
