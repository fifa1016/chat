package com.wang.chat.model.websocket;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;

/**
 * Created by wang on 16-7-7.
 */
public interface ServerStateListener {
    void onOpen(WebSocket conn, ClientHandshake handshake);

    void onMessage(WebSocket conn, String msg);

    void onClose(WebSocket conn, int code, String reason, boolean remote);

    void onError(WebSocket conn, Exception ex);
}
