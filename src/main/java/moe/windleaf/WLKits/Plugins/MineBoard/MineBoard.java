package moe.windleaf.WLKits.Plugins.MineBoard;

import moe.windleaf.WLKits.Main;
import moe.windleaf.WLKits.Plugins.MineBoard.Commands.mineboard;
import moe.windleaf.WLKits.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.HashMap;

public class MineBoard {
    public static String path = Main.prefixPath + "MineBoardScores.bin";
    @SuppressWarnings("unchecked") public static HashMap<String, Integer> scores = (HashMap<String, Integer>) Utils.loadHashMap(path);
    public static ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
    @SuppressWarnings("all") public static Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
    public static Objective objective;

    public static void load() {
        if (Main.config().getBoolean("enable-mineboard")) { register(); }
        Utils.eventRegister(new Events());
        Utils.commandRegister("mineboard", new mineboard());
    }

    public static void register() {
        if (scoreboard.getObjective("mineboard") == null) {
            objective = scoreboard.registerNewObjective(
                    "mineboard",
                    "dummy",
                    ChatColor.GOLD + "" + ChatColor.BOLD + "挖掘榜");
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        }
    }

    public static void unload() {
        objective.unregister();
        Objective objective = scoreboard.getObjective(DisplaySlot.SIDEBAR);
        if (objective != null) { objective.unregister(); }
    }
}
