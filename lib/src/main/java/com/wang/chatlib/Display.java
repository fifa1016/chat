package com.wang.chatlib;

import android.app.Activity;

/**
 * Created by shawn on 9/9/15.
 */
public abstract class Display {
    protected Activity mActivity;
    public Display(Activity activity){
        mActivity = activity;
    }

    public abstract void startAboutActivity();
    public abstract void startMainActivity();
}
