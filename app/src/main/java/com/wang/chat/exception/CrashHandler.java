package com.wang.chat.exception;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.lang.ref.WeakReference;

/**
 * Created by wang on 16-7-1.
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static final String TAG = "CrashHandler";
    //    private Thread.UncaughtExceptionHandler defaultHandler;
    private WeakReference<Context> contextWeakReference;

    public CrashHandler(Context context) {
        super();
        contextWeakReference = new WeakReference(context);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {

        if (ex != null) {
            ex.printStackTrace();
        }
        Log.e(TAG, "uncaughtException: "  );
        Intent intent = new Intent("com.wang.chat.CRASH");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        contextWeakReference.get().startActivity(intent);

        System.exit(1);
    }
}
