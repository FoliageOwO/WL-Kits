package moe.windleaf.WLKits.Plugins.SimpleBack.Commands;

import moe.windleaf.WLKits.Plugins.SimpleBack.SimpleBack;
import moe.windleaf.WLKits.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class back implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Utils.mustPlayer(sender);
            return false;
        } else {
            if (SimpleBack.teleportLog.containsKey(sender)) {
                ((Player) sender).teleport(SimpleBack.teleportLog.get(sender));
                Utils.sendPrefix(sender, "&a成功返回上一位置.");
            } else {
                Utils.sendPrefix(sender, "&c无法返回上一位置!");
            }
            return true;
        }
    }
}
