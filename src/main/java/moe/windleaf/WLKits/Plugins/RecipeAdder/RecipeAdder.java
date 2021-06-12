package moe.windleaf.WLKits.Plugins.RecipeAdder;

import moe.windleaf.WLKits.Main;
import moe.windleaf.WLKits.MessageGetter;
import moe.windleaf.WLKits.Plugins.RecipeAdder.Commands.recipeadder;
import moe.windleaf.WLKits.Utils;

public class RecipeAdder {
    public static String prefixPath = Main.prefixPath + "Recipes";
    public static RecipeManager recipeManager = new RecipeManager();
    public static MessageGetter m = new MessageGetter("RecipeAdder");

    public static void load() {
        Utils.makeDir(prefixPath);
        Utils.commandRegister("recipeadder", new recipeadder());
    }
}
