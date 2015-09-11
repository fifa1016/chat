package com.wang.chatlib.display;

import android.app.Activity;
import android.content.Intent;
import com.wang.chatlib.Display;
import com.wang.chatlib.activity.AboutActivity;
import com.wang.chatlib.activity.MainActivity;

/**
 * Created by shawn on 9/11/15.
 */
public class BaseDisplay extends Display {
    public BaseDisplay(Activity activity) {
        super(activity);
    }

    @Override
    public void startAboutActivity() {
        mActivity.startActivity(new Intent(mActivity, AboutActivity.class));
    }

    @Override
    public void startMainActivity() {
        mActivity.startActivity(new Intent(mActivity, MainActivity.class));
    }
}
