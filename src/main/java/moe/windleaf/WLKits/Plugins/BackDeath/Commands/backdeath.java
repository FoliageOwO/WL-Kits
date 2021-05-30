package moe.windleaf.WLKits.Plugins.BackDeath.Commands;

import moe.windleaf.WLKits.Main;
import moe.windleaf.WLKits.Plugins.BackDeath.Events;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class backdeath implements CommandExecutor {
    private final String prefix = Main.INSTANCE.prefix;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        Location location = Events.tpLogs.get(player);
        if (location == null) {
            player.sendMessage(prefix + "§c无法返回上一个死亡点, 可能你没有死过!");
        } else {
            player.teleport(location);
            player.sendMessage(prefix + "§a已返回上一个死亡点!");
        }
        return false;
    }
}
