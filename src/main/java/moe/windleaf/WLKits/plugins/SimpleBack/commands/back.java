package moe.windleaf.WLKits.plugins.SimpleBack.commands;

import moe.windleaf.WLKits.MessageGetter;
import moe.windleaf.WLKits.Sender;
import moe.windleaf.WLKits.plugins.SimpleBack.SimpleBack;
import moe.windleaf.WLKits.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class back implements CommandExecutor {
    MessageGetter m = new MessageGetter("SimpleBack");
    Sender s = new Sender("SimpleBack");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Util.mustPlayer(sender, "SimpleBack");
            return false;
        } else {
            if (SimpleBack.teleportLog.containsKey(sender)) {
                ((Player) sender).teleport(SimpleBack.teleportLog.get(sender));
                s.send(sender, m.get("成功"));
            } else {
                s.send(sender, m.get("失败"));
            }
            return true;
        }
    }
}
