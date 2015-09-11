package com.wang.chatlib.activity;

import android.content.Intent;
import com.wang.chatlib.Display;
import com.wang.chatlib.R;
import com.wang.chatlib.display.AboutDisplay;
import com.wang.chatlib.presenter.AboutPresenter;
import com.wang.chatlib.presenter.BaseUiPresenter;

/**
 * Created by shawn on 8/25/15.
 */
public class AboutActivity extends BaseActivity<AboutDisplay>{

    @Override
    protected void handleIntent(Intent intent, AboutDisplay display) {
        display.showAboutFragment();
    }

    @Override
    protected AboutDisplay newDisplay() {
        return new AboutDisplay(this);
    }

    @Override
    public BaseUiPresenter getPresenter() {
        if( mPresenter == null ) {
            mPresenter = new AboutPresenter();
        }
        return mPresenter;
    }
}
