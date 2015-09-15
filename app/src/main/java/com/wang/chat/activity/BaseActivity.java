package com.wang.chat.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.wang.chatlib.Display;
import com.wang.chat.R;
import com.wang.chatlib.presenter.BaseUiPresenter;


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
        return R.layout.activity_base;
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
