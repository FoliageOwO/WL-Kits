package moe.windleaf.WLKits.plugins.BackDeath.commands;

import moe.windleaf.WLKits.MessageGetter;
import moe.windleaf.WLKits.Sender;
import moe.windleaf.WLKits.plugins.BackDeath.Events;
import moe.windleaf.WLKits.Util;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class backdeath implements CommandExecutor {
    MessageGetter m = new MessageGetter("BackDeath");
    Sender s = new Sender("BackDeath");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Util.mustPlayer(sender, "BackDeath");
        } else {
            Player player = (Player) sender;
            Location location = Events.tpLogs.get(player);
            if (location == null) {
                s.send(player, m.get("无法返回"));
            } else {
                player.teleport(location);
                s.send(player, m.get("成功返回"));
            }
        }
        return false;
    }
}
