package com.wang.chat.activity;


import android.util.Log;

import com.wang.chat.data.network.test.TestRetrofit;

/**
 * A screen set user's login id & icon
 * <p/>
 */
public class ConfigActivity extends BaseActivity {
    static final String TAG = "ConfigActivity";

    @Override
    public void init(){
        Log.d(TAG, "init: ");
        display.showAccount();
        TestRetrofit.asyncTest();
//        TestRetrofit.syncTest();
    }

    @Override
    protected void onResume() {
        super.onResume();
        test();
    }

    public void test(){
        throw new RuntimeException("Test exception");
    }


}
