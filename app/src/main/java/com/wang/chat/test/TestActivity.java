package com.wang.chat.test;

import android.app.Activity;
import android.os.Bundle;

import com.wang.chat.R;

/**
 * Created by wang on 16-8-8.
 */

public class TestActivity extends Activity{
    private static final String TAG = "TestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }
}
