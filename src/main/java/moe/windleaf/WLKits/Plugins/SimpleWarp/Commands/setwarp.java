package moe.windleaf.WLKits.Plugins.SimpleWarp.Commands;

import moe.windleaf.WLKits.MessageGetter;
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
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class setwarp implements CommandExecutor, TabCompleter {
    MessageGetter m = new MessageGetter("SimpleWarp");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Utils.mustPlayer(sender, "SimpleWarp");
            return false;
        } else {
            if (args.length < 1) {
                Utils.invalidArgs(sender, "/warphelp", "SimpleWarp");
                return false;
            } else {
                if (args[0].length() > 15) {
                    Utils.smartSendPrefix(sender, m.get("超过最大上限"), "SimpleWarp");
                    return false;
                } else {
                    HashMap<String, String> i = new HashMap<>();
                    i.put("name", args[0]);
                    if (SimpleWarp.warpManager.warps.getKeys(false).contains(args[0])) {
                        Utils.smartSendPrefix(sender, Utils.insert(m.get("已存在"), i), "SimpleWarp");
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
                            Utils.smartSendPrefix(sender, Utils.insert(m.get("创建成功"), i), "SimpleWarp");
                            Utils.broadcastPlayersPrefix(Utils.getPluginPrefix("SimpleWarp") + String.format("&a玩家 &6%s &a新建了一个地标点: &9%s&a,", sender.getName(), args[0]));
                            Utils.broadcastPlayersPrefix(Utils.getPluginPrefix("SimpleWarp") + String.format("&a使用 &6/warp %s &a传送到该地标点.", args[0]));
                            return true;
                        } catch (IOException e) {
                            if (sender.isOp()) {
                                Utils.smartSendPrefix(sender, m.get("创建失败-管理员"), "SimpleWarp");
                            } else {
                                Utils.smartSendPrefix(sender, m.get("创建失败-玩家"), "SimpleWarp");
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
