package moe.windleaf.WLKits.Plugins.SimpleHome.Commands;

import moe.windleaf.WLKits.MessageGetter;
import moe.windleaf.WLKits.Plugins.SimpleHome.SimpleHome;
import moe.windleaf.WLKits.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class delhome implements CommandExecutor {
    MessageGetter m = new MessageGetter("SimpleHome");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Utils.mustPlayer(sender, "SimpleHome");
            return false;
        } else {
            Player player = (Player) sender;
            if (SimpleHome.homes.containsKey(Utils.getUUIDString(player))) {
                SimpleHome.homes.remove(Utils.getUUIDString(player));
                Utils.saveHashMap(SimpleHome.homes, SimpleHome.path);

                Utils.smartSendPrefix(player, m.get("删除成功"), "SimpleHome");
            } else {
                Utils.smartSendPrefix(player, m.get("没有家"), "SimpleHome");
            }
            return true;
        }
    }
}
