package moe.windleaf.WLKits.Plugins.SimpleWarp.Commands;

import moe.windleaf.WLKits.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class warphelp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Utils.sendHelp(sender, new String[]{
                "/warphelp &f: &a查看此帮助",
                "/setwarp [name] &f: &a设置地标点",
                "/delwarp [name] &f: &a删除地标点",
                "/warp [name] &f: &a传送至地标点"
        });
        return true;
    }
}
