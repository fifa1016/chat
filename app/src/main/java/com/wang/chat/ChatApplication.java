package com.wang.chat;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.multidex.MultiDex;

import com.wang.chat.data.ProfileManager;
import com.wang.chat.exception.CrashHandler;

/**
 * Created by shawn on 8/24/15.
 */
public class ChatApplication extends Application {
    public static ChatApplication from(Context context) {
        return (ChatApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ProfileManager.init(this);
        Thread.setDefaultUncaughtExceptionHandler(new CrashHandler(this));
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
