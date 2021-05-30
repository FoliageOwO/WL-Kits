package moe.windleaf.WLKits.Plugins.PlayerTag.Commands;

import moe.windleaf.WLKits.Main;
import moe.windleaf.WLKits.Plugins.PlayerTag.PlayerTag;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class playertag implements CommandExecutor {
    public static HashMap<Player, String> playerTags = PlayerTag.playerTags;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("wlkits.command.playertag")) {
            if (args.length == 0) {
                sender.sendMessage(Main.INSTANCE.prefix + "§a使用 §6/playertag help §a查看帮助!");
            } else {
                switch (args[0]) {
                    case "help":
                        sender.sendMessage("§f-------------------------------");
                        sender.sendMessage("§6/playertag help §f: §a查看此帮助");
                        sender.sendMessage("§6/playertag set [player] [tag] §f: §a设置一个玩家的称号");
                        sender.sendMessage("§6/playertag remove [player] §f: §a删除一个玩家的称号");
                        sender.sendMessage("§6/playertag check [player] §f: §a查看一个玩家的称号");
                        sender.sendMessage("§6/playertag reset [player] §f: §a重置一个玩家的称号 / 名称");
                        sender.sendMessage("§f-------------------------------");
                        return true;
                    case "set":
                        if (args.length < 3) {
                            invalidArgs(sender);
                            return false;
                        } else {
                            // 设置
                            Player player = Bukkit.getPlayer(args[1]);
                            if (player == null) { noPlayer(sender, args[0]); } else {
                                playerTags.put(player, rp(args[2]) + " ");
                                sender.sendMessage(Main.INSTANCE.prefix + "§a成功将玩家 §6" + player.getName() + " §a的称号设置为 \"" + rp(args[2]) + "\".");
                                return true;
                            }
                        }
                    case "remove":
                        if (args.length < 2) {
                            invalidArgs(sender);
                            return false;
                        } else {
                            // 删除
                            Player player = Bukkit.getPlayer(args[1]);
                            if (player == null) { noPlayer(sender, args[1]); } else {
                                playerTags.remove(player);
                                sender.sendMessage(Main.INSTANCE.prefix + "§a成功删除玩家 §6" + player.getName() + " §a的称号!");
                                return true;
                            }
                        }
                    case "check":
                        if (args.length < 2) {
                            invalidArgs(sender);
                            return false;
                        } else {
                            // 查看
                            Player player = Bukkit.getPlayer(args[1]);
                            if (player == null) { noPlayer(sender, args[1]); } else {
                                String tag = playerTags.get(player);
                                if (tag == null) {
                                    noTag(sender, player.getName());
                                } else if (tag.equals("")){
                                    noTag(sender, player.getName());
                                } else {
                                    sender.sendMessage(Main.INSTANCE.prefix + "§a玩家 §6" + player.getName() + " §a的称号为: §6" + tag + "§a.");
                                }
                                return true;
                            }
                        }
                    case "reset":
                        if (args.length < 2) {
                            invalidArgs(sender);
                            return false;
                        } else {
                            // 重置
                            Player player = Bukkit.getPlayer(args[1]);
                            if (player == null) { noPlayer(sender, args[1]); } else {
                                playerTags.remove(player);
                                player.setDisplayName(player.getName());
                                sender.sendMessage(Main.INSTANCE.prefix + "§a玩家 §6" + player.getName() + " §a称号 / 名称重置成功!");
                            }
                            return true;
                        }
                    default:
                        invalidArgs(sender);
                }
            }
            return false;
        } else {
            sender.sendMessage(Main.INSTANCE.prefix + "§c你没有权限!");
            return false;
        }
    }

    public void invalidArgs(CommandSender sender) {
        sender.sendMessage(Main.INSTANCE.prefix + "§c错误的参数, 使用 §6/playertag help §c查看帮助!");
    }

    public String rp(String str) {
        return str.replace("&", "§");
    }

    public void noTag(CommandSender sender, String name) {
        sender.sendMessage(Main.INSTANCE.prefix + "§c玩家 §6" + name + " §c没有称号!");
    }

    public void noPlayer(CommandSender sender, String name) {
        sender.sendMessage(Main.INSTANCE.prefix + "§c玩家 §6" + name + " §c不存在!");
    }
}
