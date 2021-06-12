package moe.windleaf.WLKits.Plugins.RecipeAdder.Commands;

import moe.windleaf.WLKits.Main;
import moe.windleaf.WLKits.Plugins.RecipeAdder.RecipeAdder;
import moe.windleaf.WLKits.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class recipeadder implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            Utils.invalidArgs(sender, "/recipeadder help", "Recipeadder");
            return false;
        } else {
            switch (args[0]) {
                case "help":
                    Map<String, String> helps = new HashMap<>();
                    helps.put("/recipeadder help", "查看此帮助");
                    helps.put("/recipeadder <load|add> [name]", "加载配方");
                    helps.put("/recipeadder <unload|remove> [name]", "删除配方");
                    helps.put("/recipeadder list", "查看已加载配方");
                    Utils.sendHelp(sender, helps);
                    break;
                case "load":
                case "add":
                    if (args.length < 2) { Utils.invalidArgs(sender, "/recipeadder help", "Recipeadder"); } else {
                        RecipeAdder.recipeManager.loadRecipe(sender, args[1]);
                    }
                    break;
                case "unload":
                case "remove":
                    if (args.length < 2) { Utils.invalidArgs(sender, "/recipeadder help", "Recipeadder"); } else {
                        RecipeAdder.recipeManager.removeRecipe(sender, args[1]);
                    }
                    break;
                case "list":
                    RecipeAdder.recipeManager.listRecipe(sender);
                    break;
                default:
                    Utils.smartSendPrefix(sender, RecipeAdder.m.get("帮助"), "Recipeadder");
                    break;
            }
            return true;
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        // 长度为 1
        if (args.length == 1) {
            String[] subCommands = {"help", "load", "add", "unload", "remove", "list"};
            return Arrays.stream(subCommands).filter(s -> s.startsWith(args[0])).collect(Collectors.toList());
        }

        // 长度为 2
        if (args.length == 2) {
            switch (args[0]) {
                case "add":
                case "load":
                    File file = new File(Main.prefixPath + "RecipeAdder");
                    File[] files = file.listFiles();
                    ArrayList<String> filesStringList = new ArrayList<>();
                    if (files == null) { return new ArrayList<>(); } else { for (File f : files) {
                        if (!(f.getName().equals("loadedRecipes.bin"))) { filesStringList.add(f.getName().replace(".yml", "")); }
                    } }
                    if (args[1].equals("")) { return filesStringList; } else { return filesStringList.stream().filter(s -> s.startsWith(args[1])).collect(Collectors.toList()); }

                case "remove":
                case "unload":
                    return new ArrayList<>(RecipeAdder.recipeManager.loadedRecipes.keySet());

                default:
                    return new ArrayList<>();
            }
        }

        // 长度 > 2
        return new ArrayList<>();
    }
}
