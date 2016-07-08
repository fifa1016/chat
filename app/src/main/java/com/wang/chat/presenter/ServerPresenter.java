package com.wang.chat.presenter;

import com.wang.chat.contract.ServerContract;
import com.wang.chat.model.websocket.WebsocketServerManager;

import org.java_websocket.server.WebSocketServer;

/**
 * Created by wang on 16-7-7.
 */
public class ServerPresenter implements ServerContract.Presenter{
    private static final String TAG = "ServerPresenter";

    @Override
    public void start() {

    }

    @Override
    public void startServer(int port) {
        WebsocketServerManager.startServer(port);
    }
}
