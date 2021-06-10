package moe.windleaf.WLKits.Plugins.SimpleTpa.Commands;

import moe.windleaf.WLKits.Plugins.SimpleTpa.SimpleTpa;
import moe.windleaf.WLKits.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class tpaccept implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Utils.mustPlayer(sender, SimpleTpa.name);
            return false;
        } else {
            Player toPlayer = SimpleTpa.tpaLogs.get(sender);
            if (toPlayer != null) {
                toPlayer.teleport((Player) sender);
                SimpleTpa.tpaLogs.remove(sender);
                Utils.smartSendPrefix(sender, "&a已接受, 传送成功!", "SimpleTpa");
                Utils.smartSendPrefix(toPlayer, "&a已接受, 传送成功!", "SimpleTpa");
            } else {
                Utils.smartSendPrefix(sender, "&c你没有待处理的传送请求!", "SimpleTpa");
            }
            return true;
        }
    }
}
