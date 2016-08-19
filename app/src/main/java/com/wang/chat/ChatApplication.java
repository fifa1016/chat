package com.wang.chat;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.wang.chat.model.ProfileManager;
import com.wang.chat.exception.CrashHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shawn on 8/24/15.
 */
public class ChatApplication extends Application {
    private static final String TAG = "ChatApplication";

    public static ChatApplication from(Context context) {
        return (ChatApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ProfileManager.init(this);
        Thread.setDefaultUncaughtExceptionHandler(new CrashHandler(this));

        registerActivityLifeCallback();
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    /**
     * activity stack
     */
    private void registerActivityLifeCallback() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {
                //push
                pushActivity(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                //pop
                popActivity(activity);
            }
        });
    }

    private List<Activity> listActivity;

    private void pushActivity(Activity activity) {
        Log.d(TAG, "pushActivity: ");
        if (listActivity == null) {
            listActivity = new ArrayList<>();
        }
        listActivity.add(activity);
    }

    private void popActivity(Activity activity) {
        Log.d(TAG, "popActivity: ");
        listActivity.remove(activity);
    }

    private void clearActivityFrom(String className) {
        Log.d(TAG, "clearActivityFrom: " + className);
        for (int i = listActivity.size() - 1; i >= 0; i--) {
            Activity act = listActivity.get(i);
            boolean get = act.getClass().getSimpleName().equals(className);
            popActivity(act);
            act.finish();

            if (get) {
                break;
            }
        }
    }

}
