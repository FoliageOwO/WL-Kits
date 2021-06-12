package moe.windleaf.WLKits.Plugins.PlayerTag.Commands;

import moe.windleaf.WLKits.MessageGetter;
import moe.windleaf.WLKits.Plugins.PlayerTag.PlayerTag;
import moe.windleaf.WLKits.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.stream.Collectors;

public class playertag implements CommandExecutor, TabCompleter {
    public static HashMap<String, String> playerTags = PlayerTag.playerTags;
    MessageGetter m = new MessageGetter("PlayerTag");

    private HashMap<String, String> initMap(String name, String tag) {
        HashMap<String, String> i = new HashMap<>();
        i.put("playerName", name);
        i.put("tag", Utils.formatColor(tag));
        return i;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("wlkits.command.playertag")) {
            if (args.length == 0) {
                Utils.smartSendPrefix(sender, m.get("帮助"), "PlayerTag");
            } else {
                switch (args[0]) {
                    case "help":
                        Map<String, String> helps = new HashMap<>();
                        helps.put("/playertag help", "查看此帮助");
                        helps.put("/playertag set [player] [tag]", "设置一个玩家的称号");
                        helps.put("/playertag remove [player]", "删除一个玩家的称号");
                        helps.put("/playertag check [player]", "查看一个玩家的称号");
                        helps.put("/playertag reset [player]", "重置一个玩家的称号 | 名称");
                        Utils.sendHelp(sender, helps);
                        break;
                    case "set":
                        if (args.length < 3) {
                            Utils.invalidArgs(sender, "/playertag help", "PlayerTag");
                            return false;
                        } else {
                            // 设置
                            Player player = Bukkit.getPlayer(args[1]);
                            if (player == null) { noPlayer(sender, args[1]); } else {
                                playerTags.put(Utils.getUUIDString(player), Utils.formatColor(args[2]) + " ");
                                Utils.saveHashMap(playerTags, PlayerTag.path);
                                Utils.sendPrefix(sender, Utils.insert(m.get("设置成功"), initMap(player.getName(), args[2])));
                                return true;
                            }
                        }
                        break;
                    case "remove":
                        if (args.length < 2) {
                            Utils.invalidArgs(sender, "/playertag help", "PlayerTag");
                            return false;
                        } else {
                            // 删除
                            Player player = Bukkit.getPlayer(args[1]);
                            if (player == null) { noPlayer(sender, args[1]); } else {
                                playerTags.remove(Utils.getUUIDString(player));
                                Utils.saveHashMap(playerTags, PlayerTag.path);
                                Utils.sendPrefix(sender, Utils.insert(m.get("删除成功"), initMap(player.getName(), "")));
                                return true;
                            }
                        }
                        break;
                    case "check":
                        if (args.length < 2) {
                            Utils.invalidArgs(sender, "/playertag help", "PlayerTag");
                            return false;
                        } else {
                            // 查看
                            Player player = Bukkit.getPlayer(args[1]);
                            if (player == null) { noPlayer(sender, args[1]); } else {
                                String tag = playerTags.get(Utils.getUUIDString(player));
                                if (tag == null) { noTag(sender, player.getName()); } else if (tag.equals("")){ noTag(sender, player.getName());
                                } else {
                                    Utils.sendPrefix(sender, Utils.insert(m.get("查询称号"), initMap(player.getName(), args[2])));
                                }
                                return true;
                            }
                        }
                        break;
                    case "reset":
                        if (args.length < 2) {
                            Utils.invalidArgs(sender, "/playertag help", "PlayerTag");
                            return false;
                        } else {
                            // 重置
                            Player player = Bukkit.getPlayer(args[1]);
                            if (player == null) { noPlayer(sender, args[1]); } else {
                                playerTags.remove(Utils.getUUIDString(player));
                                player.setDisplayName(player.getName());
                                Utils.saveHashMap(playerTags, PlayerTag.path);
                                Utils.sendPrefix(sender, Utils.insert(m.get("重置成功"), initMap(player.getName(), "")));
                            }
                            return true;
                        }
                    default:
                        Utils.invalidArgs(sender, "/playertag help", "PlayerTag");
                }
                return true;
            }
        } else {
            Utils.doNotHavePermission(sender);
        }
        return false;
    }

    public void noTag(CommandSender sender, String name) {
        HashMap<String, String> i = new HashMap<>();
        i.put("playerName", name);
        Utils.sendPrefix(sender, Utils.insert(m.get("没有称号"), i));

    }

    public void noPlayer(CommandSender sender, String name) {
        HashMap<String, String> i = new HashMap<>();
        i.put("playerName", name);
        Utils.sendPrefix(sender, Utils.insert(m.get("玩家不存在"), i));
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        String[] subCommands = {"help", "set", "remove", "check", "reset"};
        if (args.length > 1) {
            List<String> list = new ArrayList<>();
            for (Player p : Bukkit.getOnlinePlayers()) { list.add(p.getName()); }
            return list;
        } else { if (args.length == 0) return Arrays.asList(subCommands); }
        return Arrays.stream(subCommands).filter(s -> s.startsWith(args[0])).collect(Collectors.toList());
    }
}
