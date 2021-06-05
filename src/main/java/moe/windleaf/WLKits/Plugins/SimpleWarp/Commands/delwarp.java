package moe.windleaf.WLKits.Plugins.SimpleWarp.Commands;

import moe.windleaf.WLKits.Plugins.SimpleWarp.SimpleWarp;
import moe.windleaf.WLKits.Plugins.SimpleWarp.WarpManager;
import moe.windleaf.WLKits.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class delwarp implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) {
            Utils.invalidArgs(sender, "/warphelp");
            return false;
        } else {
            if (SimpleWarp.warpManager.warps.getKeys(false).contains(args[0])) {
                SimpleWarp.warpManager.warps.set(args[0], null);
                Utils.sendPrefix(sender, String.format("&a成功删除地标 &6%s &a!", args[0]));
            } else {
                Utils.sendPrefix(sender, String.format("&c地标 &6%s &c不存在!", args[0]));
            }
            return true;
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return SimpleWarp.warpManager.getWarps();
    }
}
