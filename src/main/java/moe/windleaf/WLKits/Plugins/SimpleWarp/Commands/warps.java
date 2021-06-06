package moe.windleaf.WLKits.Plugins.SimpleWarp.Commands;

import moe.windleaf.WLKits.Plugins.SimpleWarp.SimpleWarp;
import moe.windleaf.WLKits.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class warps implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (SimpleWarp.warpManager.getWarps().size() == 0) {
            Utils.sendPrefix(sender, "&c没有可传送的地标, 快去创建一个地标吧!");
        } else {
            Utils.hyphen(sender);
            Utils.send(sender, "&a当前可传送的地标如下:");
            Utils.send(sender, SimpleWarp.warpManager.getWarps().toString());
            Utils.hyphen(sender);
        }
        return true;
    }
}
