package com.wang.chat.presenter;

import com.wang.chat.Display;

/**
 * Created by shawn on 9/9/15.
 */
public abstract class BasePresenter {

    private Display mDisplay;

    public void attachDisplay(Display display) {
        mDisplay = display;
    }

    public void detachDisplay() {
        mDisplay = null;
    }
}
