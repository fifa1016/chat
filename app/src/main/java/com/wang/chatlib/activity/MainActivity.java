package com.wang.chatlib.activity;

import android.content.Intent;
import android.os.Bundle;
import com.wang.chatlib.Display;
import com.wang.chatlib.R;
import com.wang.chatlib.display.MainDisplay;
import com.wang.chatlib.presenter.BaseUiPresenter;
import com.wang.chatlib.presenter.MainPresenter;

public class MainActivity extends BaseActivity<MainDisplay> {


    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void handleIntent(Intent intent, MainDisplay display) {
        display.showMainFragment();
    }

    @Override
    protected MainDisplay newDisplay() {
        return new MainDisplay(this);
    }

    @Override
    public BaseUiPresenter getPresenter() {
        if( mPresenter == null )
            mPresenter = new MainPresenter();
        return mPresenter;
    }


}
