package moe.windleaf.WLKits.plugins.SimpleTpa.commands;

import moe.windleaf.WLKits.Sender;
import moe.windleaf.WLKits.plugins.SimpleTpa.SimpleTpa;
import moe.windleaf.WLKits.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class tpadeny implements CommandExecutor {
    Sender s = new Sender("SimpleTpa");

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Util.mustPlayer(sender, SimpleTpa.name);
            return false;
        } else {
            Player player = SimpleTpa.tpaLogs.get(sender);
            if (player != null) {
                SimpleTpa.tpaLogs.remove(sender);
                s.send(sender, String.format("&a已拒绝玩家 &6%s &a的传送请求!", player.getName()));
                s.send(player, String.format("&c玩家 &6%s &c拒绝了你的传送请求!", sender.getName()));
            } else {
                s.send(sender, "&c你没有待处理的传送请求!");
            }
            return true;
        }
    }
}
