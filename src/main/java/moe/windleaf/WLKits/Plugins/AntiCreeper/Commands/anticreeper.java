package moe.windleaf.WLKits.Plugins.AntiCreeper.Commands;

import moe.windleaf.WLKits.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class anticreeper implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("wlkits.command.anticreeper")) {
            boolean if_enabled = Main.INSTANCE.config.getBoolean("enable-anticreeper");
            String color;
            if (if_enabled) { color = "§a"; } else { color = "§c"; }
            if (args.length == 0) {
                sender.sendMessage(Main.INSTANCE.prefix + "§6AntiCreeper 开启状态: " + color + if_enabled);
            } else {
                switch (args[0]) {
                    case "on":
                        if (if_enabled) { sender.sendMessage(Main.INSTANCE.prefix + "§9AntiCreeper 已经处于开启状态!"); } else {
                            Main.INSTANCE.config.set("enable-anticreeper", true);
                            Main.INSTANCE.saveConfig();
                            sender.sendMessage(Main.INSTANCE.prefix + "§aAntiCreeper 已开启!");
                        }
                        break;
                    case "off":
                        if (!if_enabled) { sender.sendMessage(Main.INSTANCE.prefix + "§9AntiCreeper 已经处于关闭状态!"); } else {
                            Main.INSTANCE.config.set("enable-anticreeper", false);
                            Main.INSTANCE.saveConfig();
                            sender.sendMessage(Main.INSTANCE.prefix + "§cAntiCreeper 已关闭!");
                        }
                        break;
                    default:
                        sender.sendMessage(Main.INSTANCE.prefix + "§c错误的参数, 用法: §6/anticreeper [on|off]§c.");
                }
            }
            return true;
        } else {
            sender.sendMessage(Main.INSTANCE.prefix + "§c你没有权限!");
            return false;
        }
    }
}
