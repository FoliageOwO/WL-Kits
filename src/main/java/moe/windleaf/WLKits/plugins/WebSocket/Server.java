package moe.windleaf.WLKits.plugins.WebSocket;

import moe.windleaf.WLKits.Util;
import org.java_websocket.WebSocket;
import org.java_websocket.drafts.Draft;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.handshake.ServerHandshakeBuilder;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;

public class Server extends WebSocketServer {
    public Server(int port) {
        super(new InetSocketAddress(port));
    }

    public void onOpen(WebSocket connection, ClientHandshake handshake) {
        String ID = Util.getRandomString(10);
        ConnectionPool.add(connection, ID);
        Util.logInfo(Util.getPluginPrefix("WebSocket") + String.format("&a允许通过来自 WebSocket Client &f[%s] &a的新连接.", connection.getRemoteSocketAddress()));
    }

    public void onClose(WebSocket connection, int code, String reason, boolean b) {
        Util.logInfo(Util.getPluginPrefix("WebSocket") + String.format("&cWebSocket Client &f[%s] &c已断开连接: &6%s", connection.getRemoteSocketAddress(), reason));
        ConnectionPool.remove(connection);
    }

    public void onMessage(WebSocket connection, String message) { }

    public void onError(WebSocket connection, Exception e) {
        Util.logInfo(Util.getPluginPrefix("WebSocket") + "&c无法创建 WebSocket 服务进程!");
        e.printStackTrace();
    }

    public void onStart() {
        Util.logInfo(Util.getPluginPrefix("WebSocket") + "&f正在启动 WebSocket 服务...");
        Util.logInfo(Util.getPluginPrefix("WebSocket") + String.format("&aWebSocket 服务启动成功: &6ws://127.0.0.1:%s&a!", this.getPort()));
    }

    public ServerHandshakeBuilder onWebsocketHandshakeReceivedAsServer(WebSocket connection, Draft draft, ClientHandshake request) throws InvalidDataException {
        return super.onWebsocketHandshakeReceivedAsServer(connection, draft, request);
    }
}
