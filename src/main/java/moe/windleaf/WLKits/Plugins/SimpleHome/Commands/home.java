package moe.windleaf.WLKits.Plugins.SimpleHome.Commands;

import moe.windleaf.WLKits.Plugins.SimpleHome.SimpleHome;
import moe.windleaf.WLKits.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class home implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Utils.mustPlayer(sender, "SimpleHome");
            return false;
        } else {
            Player player = (Player) sender;
            if (SimpleHome.homes.containsKey(Utils.getUUIDString(player))) {
                player.teleport(SimpleHome.homes.get(Utils.getUUIDString(player)));
            } else {
                Utils.smartSendPrefix(player, "&c你还没有家, 快去设置一个吧!", "SimpleHome");
            }
            return true;
        }
    }
}
