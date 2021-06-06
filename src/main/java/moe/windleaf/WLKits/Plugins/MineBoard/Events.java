package moe.windleaf.WLKits.Plugins.MineBoard;

import moe.windleaf.WLKits.Main;
import moe.windleaf.WLKits.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Score;

import java.util.*;

public class Events implements Listener {
    @EventHandler
    public void _PlayerJoinEvent(PlayerJoinEvent event) {
        boolean if_enabled = Main.I.config.getBoolean("enable-mineboard");
        if (if_enabled) {
            Player player = event.getPlayer();
            if (!MineBoard.scores.containsKey(Utils.getUUIDString(player))) { MineBoard.scores.put(Utils.getUUIDString(player), 0); }
            refreshBoard();
        }
    }

    @EventHandler
    public void _BlockBreakEvent(BlockBreakEvent event) {
        boolean if_enabled = Main.I.config.getBoolean("enable-mineboard");
        if (if_enabled) {
            Player player = event.getPlayer();
            if (MineBoard.scores.containsKey(Utils.getUUIDString(player))) {
                Integer y = MineBoard.scores.get(Utils.getUUIDString(player));
                MineBoard.scores.replace(Utils.getUUIDString(player), y+1);
            } else { MineBoard.scores.put(Utils.getUUIDString(player), 1); }
            refreshBoard();
        }
    }

    public static void refreshBoard() {
        try {
            ArrayList<Integer> s = new ArrayList<>(MineBoard.scores.values());
            s.sort(Collections.reverseOrder());

            ArrayList<Player> players = new ArrayList<>();

            for (Integer integer : s) {
                if (integer != 0) {
                    String playerUUID = getKeyByValue(MineBoard.scores, integer);
                    if (playerUUID != null) {
                        players.add(Bukkit.getPlayer(UUID.fromString(playerUUID)));
                    }
                }
            }

            for (Player player : players) { show(player, players.indexOf(player)); }
            Utils.saveHashMap(MineBoard.scores, MineBoard.path);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public static String getKeyByValue(Map<?, ?> map, Integer value) {
        Set<?> keys = map.keySet();
        for (Object i : keys) { if (map.get(i) == value) { return i.toString(); } }
        return null;
    }

    private static void show(Player player, Integer index) {
        if (player != null && index != null) {
            Score score = MineBoard.objective.getScore(
                    Utils.formatColor(String.format("&b%s. &f%s", index + 1, player.getName())));
            score.setScore(MineBoard.scores.get(Utils.getUUIDString(player)));
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.setScoreboard(MineBoard.scoreboard);
            }
        }
    }
}
