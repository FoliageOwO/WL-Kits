package moe.windleaf.WLKits.plugins.SimpleTpa.commands;

import moe.windleaf.WLKits.Sender;
import moe.windleaf.WLKits.plugins.SimpleTpa.SimpleTpa;
import moe.windleaf.WLKits.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class tpaccept implements CommandExecutor {
    Sender s = new Sender("SimpleTpa");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Util.mustPlayer(sender, SimpleTpa.name);
            return false;
        } else {
            Player toPlayer = SimpleTpa.tpaLogs.get(sender);
            if (toPlayer != null) {
                toPlayer.teleport((Player) sender);
                SimpleTpa.tpaLogs.remove(sender);
                s.send(sender, "&a已接受, 传送成功!");
                s.send(toPlayer, "&a已接受, 传送成功!");
            } else {
                s.send(sender, "&c你没有待处理的传送请求!");
            }
            return true;
        }
    }
}
