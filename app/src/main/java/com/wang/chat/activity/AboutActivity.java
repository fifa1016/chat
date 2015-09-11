package com.wang.chat.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;
import com.wang.chat.Display;
import com.wang.chat.R;
import com.wang.chat.display.AboutDisplay;
import com.wang.chat.presenter.AboutPresenter;
import com.wang.chat.presenter.BasePresenter;
import com.wang.chat.presenter.BaseUiPresenter;

/**
 * Created by shawn on 8/25/15.
 */
public class AboutActivity extends BaseActivity{

    @Override
    public int getContentViewId() {
        return R.layout.activity_about;
    }

    @Override
    protected void handleIntent(Intent intent, Display display) {
        ((AboutDisplay)display).showAboutFragment();
    }

    @Override
    protected Display newDisplay() {
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
