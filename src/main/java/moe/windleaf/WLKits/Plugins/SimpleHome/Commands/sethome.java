package moe.windleaf.WLKits.Plugins.SimpleHome.Commands;

import moe.windleaf.WLKits.MessageGetter;
import moe.windleaf.WLKits.Plugins.SimpleHome.SimpleHome;
import moe.windleaf.WLKits.Utils;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class sethome implements CommandExecutor {
    MessageGetter m = new MessageGetter("SimpleHome");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Utils.mustPlayer(sender, "SimpleHome");
            return false;
        } else {
            Player player = (Player) sender;
            if (SimpleHome.homes.containsKey(Utils.getUUIDString(player))) {
                Utils.smartSendPrefix(player, m.get("重复设置"), "SimpleHome");
            } else {
                StringBuilder sb = new StringBuilder();
                Location location = player.getLocation();
                sb.append(location.getWorld())
                        .append(" ")
                        .append(location.getX())
                        .append(" ")
                        .append(location.getY())
                        .append(" ")
                        .append(location.getZ())
                        .append(" ")
                        .append(location.getYaw())
                        .append(" ")
                        .append(location.getPitch());
                SimpleHome.homes.put(Utils.getUUIDString(player), sb.toString());
                Utils.saveHashMap(SimpleHome.homes, SimpleHome.path);
                Utils.smartSendPrefix(player, m.get("设置成功"), "SimpleHome");
            }
            return true;
        }
    }
}
