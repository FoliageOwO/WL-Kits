package moe.windleaf.WLKits;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.io.*;
import java.util.*;

public class Utils {
    static MessageGetter m = new MessageGetter("Utils");

    public static void send(CommandSender sender, String string) { sender.sendMessage(formatColor(string)); }

    public static void send(Player player, String string) { player.sendMessage(formatColor(string)); }

    public static void sendPrefix(CommandSender sender, String string) { sender.sendMessage(Main.I.prefix + formatColor(string)); }

    public static void sendPrefix(Player player, String string) { player.sendMessage(Main.I.prefix + formatColor(string)); }

    public static void logInfo(String string) { Main.I.logger.log(formatColor(string)); }

    public static void logInfoPrefix(String string) { Main.I.logger.log(Main.I.prefix + formatColor(string)); }

    public static void logInfoPrefixCustom(String prefix, String string) { Main.I.logger.log(prefix + formatColor(string)); }

    public static void autoLogSend(String string) { logInfo(string); }

    @SuppressWarnings("unused")
    public static void autoLogSend(CommandSender sender, String string) { send(sender, string); }

    public static void autoLogSendPrefix(CommandSender sender, String string) { sendPrefix(sender, string); }

    @SuppressWarnings("unused")
    public static void broadcast(String string) { Bukkit.broadcastMessage(formatColor(string)); }

    public static void broadcast(String string, Boolean formatColor) {
        if (formatColor) { Bukkit.broadcastMessage(formatColor(string)); } else { Bukkit.broadcastMessage(string); }
    }

    public static void broadcastPlayers(String string) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            send(player, string);
        }
    }

    public static void doNotHavePermission(CommandSender sender) { sendPrefix(sender, m.get("没有权限")); }

    @SuppressWarnings("unused")
    public static void doNotHavePermission(Player player) { sendPrefix(player, m.get("没有权限")); }

    @SuppressWarnings("unused")
    public static void invalidArgs(Player player, String usage, String name) {
        invalidArgs((CommandSender) player, usage, name);
    }

    public static void invalidArgs(CommandSender sender, String usage, String name) {
        String prefix;
        if (!name.equals("")) { prefix = getPluginPrefix(name); } else { prefix = getPrefix("WL-Kits"); }
        HashMap<String, String> i = new HashMap<>();
        i.put("usage", usage);
        send(sender, prefix + insert(m.get("参数错误"), i));
    }

    public static boolean hasCommandPermission(CommandSender sender, String name) { return sender.hasPermission("wlkits.command." + name); }

    public static void eventRegister(Listener listener) {
        Main.I.pluginManager.registerEvents(listener, Main.I);
    }

    public static void commandRegister(String name, CommandExecutor executor) {
        Objects.requireNonNull(Main.I.getCommand(name)).setExecutor(executor);
    }

    @SuppressWarnings("unused")
    public static void commandCompleterRegister(String name, TabCompleter completer) {
        Objects.requireNonNull(Main.I.getCommand(name)).setTabCompleter(completer);
    }

    public static void commandCRegister(String name, CommandExecutor executor) {
        Objects.requireNonNull(Main.I.getCommand(name)).setExecutor(executor);
        Objects.requireNonNull(Main.I.getCommand(name)).setTabCompleter((TabCompleter) executor);
    }

    public static String getPluginPrefix(String name) { return formatColor(String.format("&a[WL-Kits] &8%s &b&l>> ", name)); }

    public static String getPrefix(String name) {
        return formatColor(String.format("&a[%s] &b&l>> ", name));
    }

    public static void sendHelp(CommandSender sender, Map<String, String> helps) {
        _sendHelp(sender, helps);
    }

    @SuppressWarnings("unused")
    public static void sendHelp(Player player, Map<String, String> helps) {
        _sendHelp(player, helps);
    }

    @SuppressWarnings("unused")
    public static void hyphen(Player player) { Utils.send(player, "&f-----------------------------------------------"); }

    @SuppressWarnings("unused")
    public static void hyphen(CommandSender sender) { Utils.send(sender, "&f-----------------------------------------------"); }

    private static void _sendHelp(CommandSender sender, Map<String, String> helps) {
        // hyphen(sender);
        for (String i : helps.keySet()) { Utils.send(sender, String.format("&8» &6%s &f- &a%s"
                , i, helps.get(i)).replace("|", "&2|&6")); }
    }

    public static String formatColor(String string) { return string.replace("&", "§"); }

    @SuppressWarnings("unused")
    public static UUID getUUID(Player player) { return player.getUniqueId(); }

    public static String getUUIDString(Player player) { return String.valueOf(player.getUniqueId()); }

    public static void saveHashMap(HashMap<?, ?> map, String path) {
        try {
            if (new File(path).exists()) {
                ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(path));
                os.writeObject(map);
                os.flush();
                os.close();
            } else {
                File file = new File(path);
                boolean createdDIR = file.getParentFile().mkdirs();
                boolean createdFILE = file.createNewFile();
                if (!createdDIR && !createdFILE) {
                    throw new IOException("无法新建数据文件！");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static HashMap<?, ?> loadHashMap(String path) {
        try {
            if (new File(path).exists()) {
                ObjectInputStream os = new ObjectInputStream(new FileInputStream(path));
                return (HashMap<?, ?>) os.readObject();
            } else {
                makeFile(path);
                saveHashMap(new HashMap<>(), path);
                return loadHashMap(path);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }

    public static String getPath() { return System.getProperty("user.dir") + File.separator; }

    public static void makeDir(String path) {
        File file = new File(path);
        if (!file.exists()) {
            @SuppressWarnings("unused") boolean f = file.mkdir();
        }
    }

    public static void makeFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            try {
                @SuppressWarnings("unused") boolean createdDIR = file.getParentFile().mkdirs();
                @SuppressWarnings("unused") boolean createdFILE = file.createNewFile();
            } catch (IOException e) {
                logInfoPrefix("&c无法新建数据文件!");
                e.printStackTrace();
            }
        }
    }

    public static void mustPlayer(CommandSender sender, String name) {
        send(sender, getPluginPrefix(name) + m.get("玩家使用"));
    }

    @SuppressWarnings("unused")
    public static boolean contains(String[] list, String source) {
        List<?> tempList = Arrays.asList(list);
        return tempList.contains(source);
    }

    public static void smartSendPrefix(CommandSender sender, String string, String name) {
        if (sender instanceof Player) {
            send((Player) sender, getPluginPrefix(name) + string);
        } else {
            send(sender, getPluginPrefix(name) + string);
        }
    }

    @SuppressWarnings("unused")
    public static String getKeyByValue(Map<?, ?> map, Object value) {
        Set<?> keys = map.keySet();
        for (Object i : keys) { if (map.get(i) == value) { return i.toString(); } }
        return null;
    }

    public static String insert(String string, Map<String, String> insertMap) {
        String a = string;
        for (String i : insertMap.keySet()) {
            a = a.replace("{" + i + "}", insertMap.get(i));
        }
        return a;
    }

    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; ++i) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }

        return sb.toString();
    }
}
