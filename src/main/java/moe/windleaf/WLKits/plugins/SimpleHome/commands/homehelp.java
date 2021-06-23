package moe.windleaf.WLKits.plugins.SimpleHome.commands;

import moe.windleaf.WLKits.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.Map;

public class homehelp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Map<String, String> helps = new HashMap<>();
        helps.put("/homehelp", "显示此帮助");
        helps.put("/sethome", "在当前位置设置家");
        helps.put("/delhome", "删除家");
        helps.put("/home", "回家");
        Util.sendHelp(sender, helps);
        return true;
    }
}
