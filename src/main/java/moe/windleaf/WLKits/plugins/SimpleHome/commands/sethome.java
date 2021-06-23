package moe.windleaf.WLKits.plugins.SimpleHome.commands;

import moe.windleaf.WLKits.MessageGetter;
import moe.windleaf.WLKits.Sender;
import moe.windleaf.WLKits.plugins.SimpleHome.SimpleHome;
import moe.windleaf.WLKits.Util;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class sethome implements CommandExecutor {
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
                s.send(player, m.get("重复设置"));
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
                SimpleHome.homes.put(Util.getUUIDString(player), sb.toString());
                Util.saveHashMap(SimpleHome.homes, SimpleHome.path);
                s.send(player, m.get("设置成功"));
            }
            return true;
        }
    }
}
