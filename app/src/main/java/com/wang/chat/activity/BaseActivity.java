package com.wang.chat.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.wang.chat.presenter.*;
import com.wang.chat.ChatApplication;
import com.wang.chat.Display;
import com.wang.chat.R;


public abstract class BaseActivity extends Activity{

    Display mDisplay;
    BaseUiPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());

        mDisplay = newDisplay();

        handleIntent(getIntent(), mDisplay );
    }

    public int getContentViewId() {
        return R.layout.activity_base;
    }

    protected void handleIntent(Intent intent, Display display){

    }

    protected abstract Display newDisplay();
    public abstract BaseUiPresenter getPresenter();

    @Override
    protected void onResume() {
        super.onResume();
        getPresenter().attachDisplay(mDisplay);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getPresenter().detachDisplay();
    }
}
