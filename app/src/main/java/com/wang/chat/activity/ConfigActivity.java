package com.wang.chat.activity;


import com.wang.chat.data.network.test.TestRetrofit;

/**
 * A screen set user's login id & icon
 * <p/>
 */
public class ConfigActivity extends BaseActivity {
    static final String TAG = "ConfigActivity";

    @Override
    public void init() {
        display.showAccount();
        TestRetrofit.asyncTest();
//        TestRetrofit.syncTest();
    }



}
