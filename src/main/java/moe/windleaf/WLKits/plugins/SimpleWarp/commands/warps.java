package moe.windleaf.WLKits.plugins.SimpleWarp.commands;

import moe.windleaf.WLKits.MessageGetter;
import moe.windleaf.WLKits.Sender;
import moe.windleaf.WLKits.plugins.SimpleWarp.SimpleWarp;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class warps implements CommandExecutor {
    MessageGetter m = new MessageGetter("SimpleWarp");
    Sender s = new Sender("SimpleWarp");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (SimpleWarp.warpManager.getWarps().size() == 0) {
            s.send(sender, m.get("没有地标"));
        } else {
            s.send(sender, "&a当前可传送的地标如下:");
            s.send(sender, SimpleWarp.warpManager.getWarps().toString());
        }
        return true;
    }
}
