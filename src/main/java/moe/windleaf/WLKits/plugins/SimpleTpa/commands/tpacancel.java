package moe.windleaf.WLKits.plugins.SimpleTpa.commands;

import moe.windleaf.WLKits.Sender;
import moe.windleaf.WLKits.plugins.SimpleTpa.SimpleTpa;
import moe.windleaf.WLKits.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;

public class tpacancel implements CommandExecutor {
    Sender s = new Sender("SimpleTpa");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Util.mustPlayer(sender, SimpleTpa.name);
            return false;
        } else {
            Player toPlayer = SimpleTpa.tpaLogs.get(sender);
            Player player = getKeyByValue(SimpleTpa.tpaLogs, toPlayer);
            if (toPlayer != null && player != null && player == sender) {
                SimpleTpa.tpaLogs.remove(sender);
                s.send(sender, "&a已取消传送请求!");
            } else {
                s.send(sender, "&c你没有待处理的传送请求!");
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
