package moe.windleaf.WLKits.Plugins.WebSocket;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.java_websocket.WebSocket;

import java.util.Map;
import java.util.Set;

public class ConnectionPool {
    private static final BiMap<WebSocket, String> connectionPool = HashBiMap.create();

    public static void add(WebSocket connection, String ID) { connectionPool.put(connection, ID); }

    public static void remove(WebSocket connection) { connectionPool.remove(connection); }

    public static void remove(String ID) { connectionPool.inverse().remove(ID); }

    public static WebSocket getConnection(String ID) { return (WebSocket) connectionPool.inverse().get(ID); }

    public static void sendMessage(String message) {
        Set<WebSocket> keySet = connectionPool.keySet();
        for (WebSocket connection : keySet) {
            String ID = (String) connectionPool.get(connection);
            if (connection != null) { connection.send(message); }
        }
    }
}
