package moe.windleaf.WLKits.Plugins.SimpleMotd.Commands;

import moe.windleaf.WLKits.Plugins.SimpleMotd.SimpleMotd;
import moe.windleaf.WLKits.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class motd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (SimpleMotd.enabled) {
            if (args.length == 0) {
                Utils.invalidArgs(sender, "/motd help", "SimpleMotd");
                return false;
            } else {
                ArrayList<String> m = new ArrayList<>(Arrays.asList(Bukkit.getMotd().split("\\n")));
                if (m.size() == 1) { m.add(""); }
                switch (args[0]) {
                    case "help":
                        HashMap<String, String> helps = new HashMap<>();
                        helps.put("/motd help", "&a查看此帮助");
                        helps.put("/motd set <line1|line2> [text]", "&a设置 Motd");
                        helps.put("/motd clear", "&a清除 Motd");
                        helps.put("/motd check", "&a查看 Motd");
                        Utils.sendHelp(sender, helps);
                        break;
                    case "set":
                        if (args.length >= 3) {
                            StringBuilder sb = new StringBuilder();
                            ArrayList<String> argList = new ArrayList<>(Arrays.asList(args));
                            argList.remove(0);
                            argList.remove("line1");
                            argList.remove("line2");
                            for (String i : argList) { sb.append(i).append(" "); }
                            String s = sb.toString();
                            String line = Utils.formatColor(s.substring(0, s.length()-1));
                            if (line.startsWith("\"")) { line = line.substring(1); }
                            if (line.endsWith("\"")) { line = line.substring(0, line.length() - 1); }
                            ArrayList<Character> n = new ArrayList<>();
                            for (char ch : line.toCharArray()) { n.add(ch); }
                            if (n.contains('"')) {
                                Utils.smartSendPrefix(sender, "&c不能带有英文引号, 如要使用引号请使用中文引号!", "SimpleMotd");
                            } else {
                                if (args[1].equals("line1")) {
                                    SimpleMotd.yml.setAndSave("line1", line);
                                    success(sender, m.get(0), line);
                                } else if (args[1].equals("line2")) {
                                    SimpleMotd.yml.setAndSave("line2", line);
                                    success(sender, m.get(1), line);
                                } else {
                                    Utils.invalidArgs(sender, "/motd help", "SimpleMotd");
                                }
                            }
                        } else {
                            Utils.invalidArgs(sender, "/motd help", "SimpleMotd");
                        }
                        break;
                    case "check":
                        Utils.smartSendPrefix(sender, "&a当前的 Motd:", "SimpleMotd");
                        Utils.hyphen(sender);
                        Utils.send(sender, "&r" + m.get(0));
                        Utils.send(sender, "&r" + m.get(1));
                        Utils.hyphen(sender);
                        break;
                    case "clear":
                        SimpleMotd.yml.setAndSave("line1", m.get(0));
                        SimpleMotd.yml.setAndSave("line2", m.get(1));
                        Utils.smartSendPrefix(sender, "&a已清除 Motd!", "SimpleMotd");
                        break;
                    default:
                        Utils.invalidArgs(sender, "/motd help", "SimpleMotd");

                }
                return true;
            }
        } else {
            Utils.smartSendPrefix(sender, "&c未启用 &6SimpleMotd&6!", "SimpleMotd");
            return true;
        }
    }

    private void success(CommandSender sender, String nm, String line) {
        Utils.smartSendPrefix(sender, "&a修改成功!", "SimpleMotd");
        Utils.smartSendPrefix(sender, "&r" + nm, "SimpleMotd");
        Utils.smartSendPrefix(sender, "&a↓↓", "SimpleMotd");
        Utils.smartSendPrefix(sender, "&r" + line, "SimpleMotd");
    }
}
