package moe.windleaf.WLKits.Plugins.SimpleWarp.Commands;

import moe.windleaf.WLKits.MessageGetter;
import moe.windleaf.WLKits.Plugins.SimpleWarp.SimpleWarp;
import moe.windleaf.WLKits.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class warps implements CommandExecutor {
    MessageGetter m = new MessageGetter("SimpleWarp");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (SimpleWarp.warpManager.getWarps().size() == 0) {
            Utils.smartSendPrefix(sender, m.get("没有地标"), "SimpleWarp");
        } else {
            Utils.smartSendPrefix(sender, "&a当前可传送的地标如下:", "SimpleWarp");
            Utils.smartSendPrefix(sender, SimpleWarp.warpManager.getWarps().toString(), "SimpleWarp");
        }
        return true;
    }
}
