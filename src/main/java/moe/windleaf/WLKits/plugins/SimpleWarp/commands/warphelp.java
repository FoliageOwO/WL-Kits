package moe.windleaf.WLKits.plugins.SimpleWarp.commands;

import moe.windleaf.WLKits.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.Map;

public class warphelp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Map<String, String> helps = new HashMap<>();
        helps.put("/warphelp", "查看此帮助");
        helps.put("/setwarp [name]", "设置地标点");
        helps.put("/delwarp [name]", "删除地标点");
        helps.put("/warp [name]", "传送至地标点");
        helps.put("/showwarp [name]", "向其他玩家展示地标点");
        Util.sendHelp(sender, helps);
        return true;
    }
}
