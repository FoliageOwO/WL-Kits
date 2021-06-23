package moe.windleaf.WLKits.plugins.WebSocket;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.java_websocket.WebSocket;

import java.util.Set;

@SuppressWarnings("unused")
public class ConnectionPool {
    private static final BiMap<WebSocket, String> connectionPool = HashBiMap.create();

    public static void add(WebSocket connection, String ID) { connectionPool.put(connection, ID); }

    public static void remove(WebSocket connection) { connectionPool.remove(connection); }

    public static void remove(String ID) { connectionPool.inverse().remove(ID); }

    public static WebSocket getConnection(String ID) { return connectionPool.inverse().get(ID); }

    public static void sendMessage(String message) {
        Set<WebSocket> keySet = connectionPool.keySet();
        for (WebSocket connection : keySet) {
            String ID = connectionPool.get(connection);
            if (connection != null) { connection.send(message); }
        }
    }
}
