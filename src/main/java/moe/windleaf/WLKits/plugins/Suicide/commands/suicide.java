package moe.windleaf.WLKits.plugins.Suicide.commands;

import moe.windleaf.WLKits.plugins.Suicide.Suicide;
import moe.windleaf.WLKits.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class suicide implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Util.mustPlayer(sender, "Suicide");
            return false;
        } else {
            Suicide.lastSuicide = (Player) sender;
            ((Player) sender).setHealth(0);
            return true;
        }
    }
}
