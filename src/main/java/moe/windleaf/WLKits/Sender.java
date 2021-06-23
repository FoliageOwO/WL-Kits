package moe.windleaf.WLKits;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Sender {
    private final String pluginName;

    public Sender(String name) { pluginName = name; }

    public void send(CommandSender sender, String string) {
        if (sender instanceof Player) {
            Util.send((Player) sender, Util.getPluginPrefix(pluginName) + string);
        } else { Util.send(sender, Util.getPluginPrefix(pluginName) + string); }
    }
}
