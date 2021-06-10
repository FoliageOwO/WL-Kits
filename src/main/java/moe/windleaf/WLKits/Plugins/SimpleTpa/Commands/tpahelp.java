package moe.windleaf.WLKits.Plugins.SimpleTpa.Commands;

import moe.windleaf.WLKits.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.Map;

public class tpahelp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Map<String, String> helps = new HashMap<>();
        helps.put("/tpahelp", "查看此帮助");
        helps.put("/tpa [player]", "给玩家发送一个传送请求");
        helps.put("/tpaccept", "同意传送请求");
        helps.put("/tpadeny", "拒绝传送请求");
        helps.put("/tpacancel", "取消传送请求");
        Utils.sendHelp(sender, helps);
        return true;
    }
}
