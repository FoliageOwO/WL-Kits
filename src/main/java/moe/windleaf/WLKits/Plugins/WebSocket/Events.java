package moe.windleaf.WLKits.Plugins.WebSocket;

import com.google.gson.JsonObject;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.event.server.*;

import java.util.HashMap;
import java.util.Map;

public class Events implements Listener {
    public static String formatJSON(Map<String, String> map) {
        JsonObject object = new JsonObject();
        for (String k : map.keySet()) { object.addProperty(k, map.get(k)); }
        return String.valueOf(object);
    }

    public static void send(Map<String, String> map) { ConnectionPool.sendMessage(formatJSON(map)); }

    public static HashMap<String, String> put(HashMap<String, String> map, String k, String v) {
        map.put(k, v);
        return map;
    }

    // ---------------------------------- Server Events ----------------------------------

    @EventHandler
    public void _BroadcastMessageEvent(BroadcastMessageEvent e) {
        HashMap<String, String> map = new HashMap<>();
        map.put("event", e.getEventName());
        map.put("message", e.getMessage());
        send(map);
    }

    @EventHandler
    public void _MapInitializeEvent(MapInitializeEvent e) {
        HashMap<String, String> map = new HashMap<>();
        map.put("event", e.getEventName());
        map.put("center_x", String.valueOf(e.getMap().getCenterX()));
        map.put("center_z", String.valueOf(e.getMap().getCenterZ()));
        map.put("world", String.valueOf(e.getMap().getWorld()));
        send(map);
    }

    @EventHandler
    public void _PluginDisableEvent(PluginDisableEvent e) {
        HashMap<String, String> map = new HashMap<>();
        map.put("event", e.getEventName());
        map.put("plugin_name", e.getPlugin().getName());
        send(map);
    }

    @EventHandler
    public void _PluginEnableEvent(PluginEnableEvent e) {
        HashMap<String, String> map = new HashMap<>();
        map.put("event", e.getEventName());
        map.put("plugin_name", e.getPlugin().getName());
        send(map);
    }

    @EventHandler
    public void _RemoteServerCommandEvent(RemoteServerCommandEvent e) {
        HashMap<String, String> map = new HashMap<>();
        map.put("event", e.getEventName());
        map.put("command", e.getCommand());
        send(map);
    }

    @EventHandler
    public void _ServerListPingEvent(ServerListPingEvent e) {
        HashMap<String, String> map = new HashMap<>();
        map.put("event", e.getEventName());
        map.put("motd", e.getMotd());
        map.put("address", String.valueOf(e.getAddress()));
        map.put("max_players", String.valueOf(e.getMaxPlayers()));
        map.put("num_players", String.valueOf(e.getNumPlayers()));
        send(map);
    }

    @EventHandler
    public void _ServerCommandEvent(ServerCommandEvent e) {
        HashMap<String, String> map = new HashMap<>();
        map.put("event", e.getEventName());
        map.put("command", e.getCommand());
        send(map);
    }

    // ---------------------------------- Player Events ----------------------------------

    @EventHandler
    public void _AsyncPlayerChatEvent(AsyncPlayerChatEvent e) {
        HashMap<String, String> map = new HashMap<>();
        map.put("event", e.getEventName());
        map.put("player_name", e.getPlayer().getName());
        map.put("message", e.getMessage());
        send(map);
    }

    @EventHandler
    public void _AsyncPlayerPreLoginEvent(AsyncPlayerPreLoginEvent e) {
        HashMap<String, String> map = new HashMap<>();
        map.put("event", e.getEventName());
        map.put("name", e.getName());
        map.put("player_uuid", String.valueOf(e.getUniqueId()));
        map.put("address", String.valueOf(e.getAddress()));
        map.put("kick_message", e.getKickMessage());
        send(map);
    }

    @EventHandler
    public void _PlayerAdvancementDoneEvent(PlayerAdvancementDoneEvent e) {
        HashMap<String, String> map = new HashMap<>();
        map.put("event", e.getEventName());
        map.put("player_name", e.getPlayer().getName());
        map.put("name", e.getAdvancement().getClass().getSimpleName());
        send(map);
    }
    
    @EventHandler
    public void _PlayerBedEnterEvent(PlayerBedEnterEvent e) {
        HashMap<String, String> map = new HashMap<>();
        map.put("event", e.getEventName());
        map.put("player_name", e.getPlayer().getName());
        send(map);
    }
    
    @EventHandler
    public void _PlayerBedLeaveEvent(PlayerBedLeaveEvent e) {
        HashMap<String, String> map = new HashMap<>();
        map.put("event", e.getEventName());
        map.put("player_name", e.getPlayer().getName());
        send(map);
    }
    
    @EventHandler
    public void _PlayerBucketEmptyEvent(PlayerBucketEmptyEvent e) {
        HashMap<String, String> map = new HashMap<>();
        map.put("event", e.getEventName());
        map.put("player_name", e.getPlayer().getName());
        map.put("bucket_name", e.getBucket().name());
        send(map);
    }
    
    @EventHandler
    public void _PlayerBucketFillEvent(PlayerBucketFillEvent e) {
        HashMap<String, String> map = new HashMap<>();
        map.put("event", e.getEventName());
        map.put("player_name", e.getPlayer().getName());
        map.put("bucket_name", e.getBucket().name());
        send(map);
    }

    @EventHandler
    public void _PlayerChangedWorldEvent(PlayerChangedWorldEvent e) {
        HashMap<String, String> map = new HashMap<>();
        map.put("event", e.getEventName());
        map.put("player_name", e.getPlayer().getName());
        map.put("from", e.getFrom().getName());
        send(map);
    }

    @EventHandler
    public void _PlayerCommandSendEvent(PlayerCommandSendEvent e) {
        HashMap<String, String> map = new HashMap<>();
        map.put("event", e.getEventName());
        map.put("player_name", e.getPlayer().getName());
        map.put("command", e.getCommands().toString());
        send(map);
    }

    @EventHandler
    public void _PlayerLoginEvent(PlayerLoginEvent e) {
        HashMap<String, String> map = new HashMap<>();
        map.put("event", e.getEventName());
        map.put("player_name", e.getPlayer().getName());
        map.put("player_ip", String.valueOf(e.getPlayer().getAddress()));
        map.put("player_uuid", String.valueOf(e.getPlayer().getUniqueId()));
        send(map);
    }

    @EventHandler
    public void _PlayerLJoinEvent(PlayerJoinEvent e) {
        HashMap<String, String> map = new HashMap<>();
        map.put("event", e.getEventName());
        map.put("player_name", e.getPlayer().getName());
        map.put("player_ip", String.valueOf(e.getPlayer().getAddress()));
        map.put("player_uuid", String.valueOf(e.getPlayer().getUniqueId()));
        send(map);
    }
}
