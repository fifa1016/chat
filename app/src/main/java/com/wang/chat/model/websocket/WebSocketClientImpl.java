package com.wang.chat.model.websocket;

import android.util.Log;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

/**
 * Created by wang on 16-6-14.
 */
public class WebSocketClientImpl extends WebSocketClient {
    private static final String TAG = "WebSocketClientImpl";

    public WebSocketClientImpl(URI serverURI) {
        super(serverURI);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        Log.d(TAG, "onOpen: ");
        if (listener != null) listener.onOpen(handshakedata);
    }

    @Override
    public void onMessage(String message) {
        Log.d(TAG, "onMessage: " + message);
        if (listener != null) listener.onMessage(message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        Log.d(TAG, "onClose: " + reason);
        if (listener != null) listener.onClose(code, reason, remote);
    }

    @Override
    public void onError(Exception ex) {
        Log.d(TAG, "onError: " + ex.getMessage());
        if (listener != null) listener.onError(ex);
    }

    private ClientStateListener listener;

    public void setListener(ClientStateListener listener) {
        this.listener = listener;
    }

    public interface ClientStateListener {
        void onOpen(ServerHandshake handshake);

        void onMessage(String msg);

        void onClose(int code, String reason, boolean remote);

        void onError(Exception ex);
    }

}
