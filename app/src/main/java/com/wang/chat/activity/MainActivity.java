package com.wang.chat.activity;

import android.content.Intent;
import com.wang.chat.display.MainDisplay;
import com.wang.chatlib.presenter.BaseUiPresenter;
import com.wang.chatlib.presenter.MainPresenter;

public class MainActivity extends BaseActivity<MainDisplay> {

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
