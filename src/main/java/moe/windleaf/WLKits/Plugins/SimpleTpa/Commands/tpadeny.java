package moe.windleaf.WLKits.Plugins.SimpleTpa.Commands;

import moe.windleaf.WLKits.Plugins.SimpleTpa.SimpleTpa;
import moe.windleaf.WLKits.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class tpadeny implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Utils.mustPlayer(sender);
            return false;
        } else {
            Player player = SimpleTpa.tpaLogs.get(sender);
            if (player != null) {
                SimpleTpa.tpaLogs.remove(sender);
                Utils.sendPrefix(sender, String.format("&a已拒绝玩家 &6%s &a的传送请求!", player.getName()));

                Utils.sendPrefix(player, String.format("&c玩家 &6%s &c拒绝了你的传送请求!", sender.getName()));
            } else {
                Utils.sendPrefix(sender, "&c你没有待处理的传送请求!");
            }
            return true;
        }
    }
}
