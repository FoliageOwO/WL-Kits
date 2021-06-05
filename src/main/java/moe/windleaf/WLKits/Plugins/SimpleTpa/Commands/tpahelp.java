package moe.windleaf.WLKits.Plugins.SimpleTpa.Commands;

import moe.windleaf.WLKits.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class tpahelp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Utils.sendHelp(sender, new String[]{
                "/tpahelp &f: &a查看此帮助",
                "/tpa [player] &f: &a给玩家发送一个传送请求",
                "/tpaccept &f: &a同意传送请求",
                "/tpadeny &f: &a拒绝传送请求",
                "/tpacancel &f: &a取消传送请求"
        });
        return true;
    }
}
