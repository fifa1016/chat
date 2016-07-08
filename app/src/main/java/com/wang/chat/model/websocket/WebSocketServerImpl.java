package com.wang.chat.model.websocket;

import android.util.Log;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;

/**
 * Created by wang on 16-6-14.
 */
public class WebSocketServerImpl extends WebSocketServer {
    private static final String TAG = "WebSocketServerImpl";

    public WebSocketServerImpl(int port) {
        super(new InetSocketAddress(port));
        Log.d(TAG, "WebSocketServerImpl: constructor");
    }

    public WebSocketServerImpl(int port, ServerStateListener listener) {
        super(new InetSocketAddress(port));
        this.listener = listener;
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        Log.d(TAG, "onOpen: ");
        if (listener != null) {
            listener.onOpen(conn, handshake);
        }
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        Log.d(TAG, "onClose: ");
        if (listener != null) {
            listener.onClose(conn, code, reason, remote);
        }
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        Log.d(TAG, "onMessage: " + message);
        if (listener != null) {
            listener.onMessage(conn, message);
        }
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        Log.d(TAG, "onError: " + ex.getMessage());
        ex.printStackTrace();
        if (listener != null) {
            listener.onError(conn, ex);
        }
    }


    private ServerStateListener listener;

    public void setListener(ServerStateListener listener) {
        this.listener = listener;
    }

}
