package com.wang.chatlib;

import android.app.Application;
import android.content.Context;

/**
 * Created by shawn on 8/24/15.
 */
public class ChatApplication extends Application{

    public static ChatApplication from(Context context){
        return (ChatApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
