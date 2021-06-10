package moe.windleaf.WLKits.Plugins.BackDeath.Commands;

import moe.windleaf.WLKits.Plugins.BackDeath.Events;
import moe.windleaf.WLKits.Utils;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class backdeath implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Utils.mustPlayer(sender, "BackDeath");
        } else {
            Player player = (Player) sender;
            Location location = Events.tpLogs.get(player);
            if (location == null) {
                Utils.smartSendPrefix(player, "&c无法返回上一个死亡点!", "BackDeath");
            } else {
                player.teleport(location);
                Utils.smartSendPrefix(player, "&a已返回上一个死亡点!", "BackDeath");
            }
        }
        return false;
    }
}
