package com.wang.chat.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.wang.chat.R;

public abstract class BaseActivity extends AppCompatActivity {

    protected Display display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewLayout());

        display = new AndroidDisplay(this);
        init();
    }

    public int getContentViewLayout(){
        return R.layout.activity_base;
    }

    public void init() {
    }

    @Override
    public void onBackPressed() {
        if( getSupportFragmentManager().getBackStackEntryCount() > 1 ){
            getSupportFragmentManager().popBackStack();
        }else {
            super.onBackPressed();
        }
    }
}
