package moe.windleaf.WLKits.plugins.SimpleMotd.commands;

import moe.windleaf.WLKits.Sender;
import moe.windleaf.WLKits.plugins.SimpleMotd.SimpleMotd;
import moe.windleaf.WLKits.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class motd implements CommandExecutor {
    Sender S = new Sender("SimpleMotd");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (SimpleMotd.enabled) {
            if (args.length == 0) {
                Util.invalidArgs(sender, "/motd help", "SimpleMotd");
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
                        Util.sendHelp(sender, helps);
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
                            String line = Util.formatColor(s.substring(0, s.length()-1));
                            if (line.startsWith("\"")) { line = line.substring(1); }
                            if (line.endsWith("\"")) { line = line.substring(0, line.length() - 1); }
                            ArrayList<Character> n = new ArrayList<>();
                            for (char ch : line.toCharArray()) { n.add(ch); }
                            if (n.contains('"')) {
                                S.send(sender, "&c不能带有英文引号, 如要使用引号请使用中文引号!");
                            } else {
                                if (args[1].equals("line1")) {
                                    SimpleMotd.yml.setAndSave("line1", line);
                                    success(sender, m.get(0), line);
                                } else if (args[1].equals("line2")) {
                                    SimpleMotd.yml.setAndSave("line2", line);
                                    success(sender, m.get(1), line);
                                } else {
                                    Util.invalidArgs(sender, "/motd help", "SimpleMotd");
                                }
                            }
                        } else {
                            Util.invalidArgs(sender, "/motd help", "SimpleMotd");
                        }
                        break;
                    case "check":
                        S.send(sender, "&a当前的 Motd:");
                        Util.send(sender, "&r" + m.get(0));
                        Util.send(sender, "&r" + m.get(1));
                        break;
                    case "clear":
                        SimpleMotd.yml.setAndSave("line1", m.get(0));
                        SimpleMotd.yml.setAndSave("line2", m.get(1));
                        S.send(sender, "&a已清除 Motd!");
                        break;
                    default:
                        Util.invalidArgs(sender, "/motd help", "SimpleMotd");

                }
                return true;
            }
        } else {
            S.send(sender, "&c未启用 &6SimpleMotd&6!");
            return true;
        }
    }

    private void success(CommandSender sender, String nm, String line) {
        S.send(sender, "&a修改成功!");
        S.send(sender, "&r" + nm);
        S.send(sender, "&a↓↓");
        S.send(sender, "&r" + line);
    }
}
