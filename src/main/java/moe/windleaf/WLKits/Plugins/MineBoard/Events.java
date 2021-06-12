package moe.windleaf.WLKits.Plugins.MineBoard;

import moe.windleaf.WLKits.Main;
import moe.windleaf.WLKits.Utils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Score;

import java.util.*;
import java.util.Map.Entry;

public class Events implements Listener {
    private static final String path = Main.prefixPath + "MineBoardScoresSorted.bin";
    @SuppressWarnings("unchecked") private static final HashMap<Integer, ArrayList<Object>> playerScores = (HashMap<Integer, ArrayList<Object>>) Utils.loadHashMap(path);

    @EventHandler
    public void _PlayerJoinEvent(PlayerJoinEvent event) {
        boolean if_enabled = Main.config().getBoolean("enable-mineboard");
        if (if_enabled) {
            Player player = event.getPlayer();
            if (!MineBoard.scores.containsKey(player.getName())) { MineBoard.scores.put(player.getName(), 0); }
            refreshBoard();
        }
    }

    @EventHandler
    public void _BlockBreakEvent(BlockBreakEvent event) {
        boolean if_enabled = Main.config().getBoolean("enable-mineboard");
        if (if_enabled) {
            Player player = event.getPlayer();
            if (player.getGameMode() == GameMode.SURVIVAL) {
                if (MineBoard.scores.containsKey(player.getName())) {
                    Integer y = MineBoard.scores.get(player.getName());
                    MineBoard.scores.replace(player.getName(), y+1);
                } else { MineBoard.scores.put(player.getName(), 1); }
                refreshBoard();
            }
        }
    }

    public static void refreshBoard() {
        try {
            for (String entry : MineBoard.scoreboard.getEntries()) { MineBoard.scoreboard.resetScores(entry); }

            Comparator<Map.Entry<String, Integer>> valCmp = (o1, o2) -> o2.getValue() - o1.getValue();
            List<Map.Entry<String, Integer>> list = new ArrayList<>(MineBoard.scores.entrySet());
            list.sort(valCmp);

            for (Entry<String, Integer> stringIntegerEntry : list) {
                ArrayList<Object> playerScore = new ArrayList<>();
                playerScore.add(stringIntegerEntry.getKey());
                playerScore.add(stringIntegerEntry.getValue());
                playerScores.put(list.indexOf(stringIntegerEntry), playerScore);
            }

            Utils.saveHashMap(playerScores, path);

            for (Integer i : playerScores.keySet()) {
                Score score = MineBoard.objective.getScore(
                        Utils.formatColor(String.format("&b%s. &f%s", i+1, playerScores.get(i).get(0))));
                score.setScore((Integer) playerScores.get(i).get(1));
            }

            for (Player p : Bukkit.getOnlinePlayers()) {
                Score hy = MineBoard.objective.getScore(Utils.formatColor("&6------------------"));
                hy.setScore(-1);
                Score info = MineBoard.objective.getScore(
                        Utils.formatColor(String.format(
                                "&a你的挖掘数: &b%s", MineBoard.scores.get(p.getName()))));
                info.setScore(-2);
                Score pm = MineBoard.objective.getScore(
                        Utils.formatColor(
                                String.format("&a当前排名: &b%s", getIndex(p.getName())+1)));
                pm.setScore(-3);
                p.setScoreboard(MineBoard.scoreboard);
            }
            Utils.saveHashMap(MineBoard.scores, MineBoard.path);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private static Integer getIndex(String name) {
        for (Integer i : playerScores.keySet()) {
            if (playerScores.get(i).get(0) == name) { return i; }
        }
        return 0;
    }

    public static void clear() {
        for (String string : MineBoard.scoreboard.getEntries()) { MineBoard.scoreboard.resetScores(string); }
    }
}
