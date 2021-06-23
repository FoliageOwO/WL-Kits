package moe.windleaf.WLKits.plugins.SimpleHome.commands;

import moe.windleaf.WLKits.MessageGetter;
import moe.windleaf.WLKits.Sender;
import moe.windleaf.WLKits.plugins.SimpleHome.SimpleHome;
import moe.windleaf.WLKits.Util;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class home implements CommandExecutor {
    MessageGetter m = new MessageGetter("SimpleHome");
    Sender S = new Sender("SimpleHome");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Util.mustPlayer(sender, "SimpleHome");
            return false;
        } else {
            Player player = (Player) sender;
            if (SimpleHome.homes.containsKey(Util.getUUIDString(player))) {
                String s = SimpleHome.homes.get(Util.getUUIDString(player));
                String[] ss = s.split("\\s+");
                World world = Bukkit.getWorld(ss[0]);
                double x = Double.parseDouble(ss[1]);
                double y = Double.parseDouble(ss[2]);
                double z = Double.parseDouble(ss[3]);
                float yaw = Float.parseFloat(ss[4]);
                float pitch = Float.parseFloat(ss[5]);
                player.teleport(new Location(world, x, y, z, yaw, pitch));
                S.send(player, m.get("回家"));
            } else {
                S.send(player, m.get("没有家"));
            }
            return true;
        }
    }
}
