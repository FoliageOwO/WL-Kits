package moe.windleaf.WLKits.Plugins.SimpleWarp.Commands;

import moe.windleaf.WLKits.Plugins.SimpleWarp.SimpleWarp;
import moe.windleaf.WLKits.Utils;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class setwarp implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Utils.mustPlayer(sender);
            return false;
        } else {
            if (args.length < 1) {
                Utils.invalidArgs(sender, "/warphelp");
                return false;
            } else {
                if (args[0].length() > 15) {
                    Utils.sendPrefix(sender, "&c最大名称上限为 &615 &c个字符!");
                    return false;
                } else {
                    if (SimpleWarp.warpManager.warps.getKeys(false).contains(args[0])) {
                        Utils.sendPrefix(sender, String.format("&c地标 &6%s &c已存在, 无法重复创建!", args[0]));
                        return true;
                    } else {
                        Location location = ((Player) sender).getLocation();
                        SimpleWarp.warpManager.warps.set(args[0] + ".x", location.getX());
                        SimpleWarp.warpManager.warps.set(args[0] + ".y", location.getY());
                        SimpleWarp.warpManager.warps.set(args[0] + ".z", location.getZ());
                        SimpleWarp.warpManager.warps.set(args[0] + ".world", Objects.requireNonNull(location.getWorld()).getName());
                        SimpleWarp.warpManager.warps.set(args[0] + ".yaw", location.getYaw());
                        SimpleWarp.warpManager.warps.set(args[0] + ".pitch", location.getPitch());
                        try {
                            SimpleWarp.warpManager.warps.save(SimpleWarp.warpManager.file);
                            Utils.sendPrefix(sender, String.format("&a创建地标点 &6%s &a成功!", args[0]));
                            Utils.broadcastPlayersPrefix(String.format("&a玩家 &6%s &a新建了一个地标点: &9%s&a,", sender.getName(), args[0]));
                            Utils.broadcastPlayersPrefix(String.format("&a使用 &6/warp %s &a传送到该地标点.", args[0]));
                            return true;
                        } catch (IOException e) {
                            if (sender.isOp()) {
                                Utils.sendPrefix(sender, "&c创建地标点失败: &8内部错误, &c请查看服务器后台日志!");
                            } else {
                                Utils.sendPrefix(sender, "&c创建地标点失败: &8内部错误, &c请尝试联系管理员!");
                            }
                            e.printStackTrace();
                            return false;
                        }
                    }
                }
            }
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return new ArrayList<>();
    }
}
