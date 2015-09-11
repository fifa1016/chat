package com.wang.chatlib.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.wang.chatlib.presenter.*;
import com.wang.chatlib.Display;
import com.wang.chatlib.R;


public abstract class BaseActivity<D extends Display> extends Activity{

    D mDisplay;
    BaseUiPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());

        mDisplay = newDisplay();

        handleIntent(getIntent(), mDisplay );
    }

    public int getContentViewId(){
        return R.id.fragment_main;
    }

    protected abstract void handleIntent(Intent intent, D display);

    protected abstract D newDisplay();
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
