package moe.windleaf.WLKits.Plugins.Suicide.Commands;

import moe.windleaf.WLKits.Plugins.Suicide.Suicide;
import moe.windleaf.WLKits.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class suicide implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Utils.mustPlayer(sender);
            return false;
        } else {
            Suicide.lastSuicide = (Player) sender;
            ((Player) sender).setHealth(0);
            return true;
        }
    }
}
