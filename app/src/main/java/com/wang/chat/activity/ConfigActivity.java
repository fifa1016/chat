package com.wang.chat.activity;

import android.app.Fragment;

/**
 * A screen set user's login id & icon
 * <p>
 */
public class ConfigActivity extends BaseActivity {
    static final String TAG = "ConfigActivity";

    @Override
    public void init() {
        display.showAccount();
    }


}
