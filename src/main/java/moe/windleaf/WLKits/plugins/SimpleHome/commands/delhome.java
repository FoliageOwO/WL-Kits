package moe.windleaf.WLKits.plugins.SimpleHome.commands;

import moe.windleaf.WLKits.MessageGetter;
import moe.windleaf.WLKits.Sender;
import moe.windleaf.WLKits.plugins.SimpleHome.SimpleHome;
import moe.windleaf.WLKits.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class delhome implements CommandExecutor {
    MessageGetter m = new MessageGetter("SimpleHome");
    Sender s = new Sender("SimpleHome");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Util.mustPlayer(sender, "SimpleHome");
            return false;
        } else {
            Player player = (Player) sender;
            if (SimpleHome.homes.containsKey(Util.getUUIDString(player))) {
                SimpleHome.homes.remove(Util.getUUIDString(player));
                Util.saveHashMap(SimpleHome.homes, SimpleHome.path);

                s.send(player, m.get("删除成功"));
            } else {
                s.send(player, m.get("没有家"));
            }
            return true;
        }
    }
}
