package moe.windleaf.WLKits.Plugins.RecipeAdder.Commands;

import moe.windleaf.WLKits.Plugins.RecipeAdder.RecipeAdder;
import moe.windleaf.WLKits.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class recipeadder implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            Utils.invalidArgs(sender, "/recipeadder help");
            return false;
        } else {
            switch (args[0]) {
                case "help":
                    Utils.sendHelp(sender, new String[] {
                            "/recipeadder help &f: &a查看此帮助",
                            "/recipeadder <load|add> [name] &f: &a加载配方",
                            "/recipeadder <unload|remove> [name] &f: &a删除配方",
                            "/recipeadder list &f: &a查看已加载配方"
                    });
                    break;
                case "load":
                case "add":
                    if (args.length < 2) { Utils.invalidArgs(sender, "/recipeadder help"); } else {
                        RecipeAdder.recipeManager.loadRecipe(sender, args[1]);
                    }
                    break;
                case "unload":
                case "remove":
                    if (args.length < 2) { Utils.invalidArgs(sender, "/recipeadder help"); } else {
                        RecipeAdder.recipeManager.removeRecipe(sender, args[1]);
                    }
                    break;
                case "list":
                    RecipeAdder.recipeManager.listRecipe(sender);
                    break;
                default:
                    Utils.invalidArgs(sender, "/recipeadder help");
                    break;
            }
            return true;
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        String[] subCommands = {"help", "load", "add", "unload", "remove", "list"};
        if (args.length > 1) return new ArrayList<>();
        if (args.length == 0) return Arrays.asList(subCommands);
        return Arrays.stream(subCommands).filter(s -> s.startsWith(args[0])).collect(Collectors.toList());
    }
}
