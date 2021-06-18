package moe.windleaf.WLKits.Plugins.SimpleWarp.Commands;

import moe.windleaf.WLKits.Plugins.SimpleWarp.SimpleWarp;
import moe.windleaf.WLKits.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public class showwarp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            Utils.invalidArgs(sender, "/warphelp", "SimpleWarp");
            return false;
        } else {
            String warp = args[0];
            FileConfiguration f = SimpleWarp.warpManager.warps;
            if (SimpleWarp.warpManager.getWarps().contains(warp)) {
                Utils.broadcastPlayers(Utils.getPluginPrefix("SimpleWarp") + String.format("&a玩家 &6%s &a展示了一个地标点:", sender.getName()));
                Utils.broadcastPlayers(Utils.getPluginPrefix("SimpleWarp") +
                        String.format("&r%s &6- &2W: %s | &aX: %s | &bY: %s | &cZ: %s", warp,
                                f.getString(warp + ".world"),
                                Math.floor(f.getDouble(args[0] + ".x")),
                                Math.floor(f.getDouble(args[0] + ".y")),
                                Math.floor(f.getDouble(args[0] + ".z"))));
            } else {
                Utils.smartSendPrefix(sender, String.format("&c地标点 &6%s &c不存在!", warp), "SimpleWarp");
            }
            return true;
        }
    }
}
