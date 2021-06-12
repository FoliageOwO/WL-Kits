package moe.windleaf.WLKits.Plugins.BackDeath.Commands;

import moe.windleaf.WLKits.MessageGetter;
import moe.windleaf.WLKits.Plugins.BackDeath.Events;
import moe.windleaf.WLKits.Utils;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class backdeath implements CommandExecutor {
    MessageGetter m = new MessageGetter("BackDeath");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Utils.mustPlayer(sender, "BackDeath");
        } else {
            Player player = (Player) sender;
            Location location = Events.tpLogs.get(player);
            if (location == null) {
                Utils.smartSendPrefix(player, m.get("无法返回"), "BackDeath");
            } else {
                player.teleport(location);
                Utils.smartSendPrefix(player, m.get("成功返回"), "BackDeath");
            }
        }
        return false;
    }
}
