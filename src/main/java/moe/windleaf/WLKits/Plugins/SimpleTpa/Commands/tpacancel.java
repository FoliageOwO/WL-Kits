package moe.windleaf.WLKits.Plugins.SimpleTpa.Commands;

import moe.windleaf.WLKits.Plugins.SimpleTpa.SimpleTpa;
import moe.windleaf.WLKits.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;

public class tpacancel implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Utils.mustPlayer(sender);
            return false;
        } else {
            Player toPlayer = SimpleTpa.tpaLogs.get(sender);
            Player player = getKeyByValue(SimpleTpa.tpaLogs, toPlayer);
            if (toPlayer != null && player != null && player == sender) {
                SimpleTpa.tpaLogs.remove(sender);
                Utils.sendPrefix(sender, "&a已取消传送请求!");
            } else {
                Utils.sendPrefix(sender, "&c你没有待处理的传送请求!");
            }
            return true;
        }
    }

    public Player getKeyByValue(Map<Player, Player> map, Player value) {
        for (Player i : map.values()) {
            if (map.get(i) == value) {
                return i;
            }
        }
        return null;
    }
}
