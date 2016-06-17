package com.wang.chat.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.wang.chat.R;


public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
    }

    public abstract int getContentViewId();

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
