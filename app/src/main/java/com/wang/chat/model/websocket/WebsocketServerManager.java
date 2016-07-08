package com.wang.chat.model.websocket;

import android.util.Log;

import org.java_websocket.server.WebSocketServer;

/**
 * Created by wang on 16-7-7.
 */
public class WebsocketServerManager {
    private static final String TAG = "WebsocketServerManager";

    private volatile static WebSocketServer server;
    private volatile static int serverPort;
    private ServerStateListener serverStateListener;

    public static WebSocketServer startServer(int port) {
        if (port < 1 || port > 65535) {
            Log.e(TAG, "startServer: port should between 1~65545");
            return null;
        }
        if (server == null) {
            synchronized (WebsocketServerManager.class) {
                if (server == null) {
                    server = new WebSocketServerImpl(port);
                    server.start();
                    serverPort = port;
                }
            }
        }

        return server;
    }

    public static WebSocketServer getServer(){
        return server;
    }
}
